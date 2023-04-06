from scipy.sparse.linalg import svds
import pandas as pd
import numpy as np
import warnings
warnings.filterwarnings("ignore")


def recommand_webtoons(user_table, df_svd_preds, user_id, ori_webtoons_df, ori_scores_df, num_recommandations=5):

    # 현재는 index로 적용이 되어있으므로 user_id - 1을 해야함.
    user_row_number = user_table[user_id]
    
    # 최종적으로 만든 pred_df에서 사용자 index에 따라 웹툰 데이터 정렬 -> 웹툰 평점이 높은 순으로 정렬 됌
    sorted_user_predictions = df_svd_preds.iloc[user_row_number].sort_values(
        ascending=False)

    # 원본 평점 데이터에서 user id에 해당하는 데이터를 뽑아낸다.
    user_data = ori_scores_df[ori_scores_df.userId == user_id]

    # 위에서 뽑은 user_data와 원본 웹툰 데이터를 합친다.
    # user_history = user_data.merge(ori_webtoons_df, on = 'webtoonId').sort_values(['score'], ascending=False)
    user_history = user_data.merge(ori_webtoons_df, on='webtoonId')
    # user_history = user_data

    # 원본 웹툰 데이터에서 사용자가 본 웹툰 데이터를 제외한 데이터를 추출
    recommandations = ori_webtoons_df[~ori_webtoons_df['webtoonId'].isin(
        user_history['webtoonId'])]

    # 사용자의 웹툰 평점이 높은 순으로 정렬된 데이터와 위 recommandations을 합친다.
    recommandations = recommandations.merge(pd.DataFrame(
        sorted_user_predictions).reset_index(), on='webtoonId')

    # 컬럼 이름 바꾸고 정렬해서 return
    recommandations = recommandations.rename(columns={user_row_number: 'Predictions'}).sort_values(
        'Predictions', ascending=False).iloc[:num_recommandations, :]

    return user_history, recommandations


def recommand_to_user(data, user_id):
    """
    사용자 기반 추천
        ARGS:
            data: list
        RETURN:
            recommand: DataFrame
    """
    df = pd.DataFrame(data)

    temp = []
    user_table = {}
    for idx, id in enumerate(list(set(map(lambda x: x['userId'], data)))):
        user_table[id] = idx
    for i in set(map(lambda x: x['webtoonId'], data)):
        temp_dict = {"webtoonId": i}
        temp.append(temp_dict)
    df_ori = pd.DataFrame(temp)

    df_ori.drop_duplicates(subset=None, keep='first', inplace=False, ignore_index=False)
    df_user_webtoon_scores = df.pivot(
        index='userId',
        columns='webtoonId',
        values='score'
    ).fillna(0)
    # matrix는 pivot_table 값을 numpy matrix로 만든 것
    matrix = df_user_webtoon_scores.values

    # user_ratings_mean은 사용자의 평균 평점
    user_scores_mean = np.mean(matrix, axis=1)

    # R_user_mean : 사용자-웹툰에 대해 사용자 평균 평점을 뺀 것.
    matrix_user_mean = matrix - user_scores_mean.reshape(-1, 1)

    # scipy에서 제공해주는 svd.
    # U 행렬, sigma 행렬, V 전치 행렬을 반환.

    U, sigma, Vt = svds(matrix_user_mean, k=min(idx, 12))

    sigma = np.diag(sigma)

    # U, Sigma, Vt의 내적을 수행하면, 다시 원본 행렬로 복원이 된다.
    # 거기에 + 사용자 평균 rating을 적용한다.
    svd_user_predicted_scores = np.dot(
        np.dot(U, sigma), Vt) + user_scores_mean.reshape(-1, 1)

    df_svd_preds = pd.DataFrame(
        svd_user_predicted_scores, columns=df_user_webtoon_scores.columns)

    already_rated, predictions = recommand_webtoons(
        user_table, df_svd_preds, user_id, df_ori, df, 10)

    return predictions['webtoonId'].values.tolist()
