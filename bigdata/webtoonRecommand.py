from sklearn.decomposition import TruncatedSVD
from scipy.sparse.linalg import svds
import pandas as pd
import numpy as np


# 웹툰 추천
def recommand_webtoon(data: list):
    """
    웹툰 추천
        ARGS:
            data: list

    """
    webtoon_list = data  # 웹툰 데이터
    L = len(webtoon_list)
    df = pd.DataFrame(webtoon_list)

    user_webtoon_score = df.pivot_table('score', index='userId', columns='webtoonId').fillna(
        0)  # vaule: score, column: webtoon, row: user

    SVD = TruncatedSVD(n_components=12)  # 특이값 분해(latent: 12)
    matrix = SVD.fit_transform(user_webtoon_score)

    webtoon_user_score = user_webtoon_score.values.T  # column과 row 바꾸기
    matrix = SVD.fit_transform(webtoon_user_score)

    corr = np.corrcoef(matrix)  # 피어슨 상관계수 구하기

    webtoon_title = user_webtoon_score.columns

    np.save('corr.npy', corr)
    f = open("column_list.txt", 'w', encoding="UTF-8")
    f.write(' '.join(list(map(str, list(webtoon_title)))))

    return webtoon_title, corr


def webtoons_recommand_top10(target_webtoon_id: int) -> list:
    f = open("column_list.txt", 'r', encoding="UTF-8")
    webtoon_title_list = list(map(int, f.read().split()))
    corr = np.load('corr.npy')

    coffey_hands = webtoon_title_list.index(target_webtoon_id)
    corr_coffey_hands = corr[coffey_hands]

    sort_by_corr = sorted(list(
        zip(webtoon_title_list, corr_coffey_hands)), key=lambda x: x[1], reverse=True)
    recommand_webtoon = list(map(lambda x: x[0], sort_by_corr[1:]))
    return recommand_webtoon[:10]  # 가장 추천하는 10개만 리턴
