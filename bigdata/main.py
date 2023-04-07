from fastapi import FastAPI
from pydantic import BaseModel
import recommendWebtoonList
from starlette.requests import Request
import webtoonRecommand
import userRecommend

app = FastAPI()


@app.post("/assosiation")
async def assosiateion(request: Request):

    req = await request.json()
    k = int(list(req.keys())[0])
    v = list(req.values())[0]

    webtoonRecommand.recommand_webtoon(v)

    recommand_List = webtoonRecommand.webtoons_recommand_top10(k)

    for i in recommand_List:
        recommendData = recommendWebtoonList.RecommendWebtoonList()

        recommendData.webtoonId = i

        recommendData.done()

    recommend_webtoon_json = recommendData.make_json()

    return recommend_webtoon_json


@app.post("/userbased")
async def userbased(request: Request):

    req = await request.json()
    k = int(list(req.keys())[0])
    v = list(req.values())[0][0]

    recomm_data = v['recommendApiRequestDTOS']
    userLikedWebtoon = v['userLikedWebtoon']

    recommend_list = userRecommend.recommand_to_user(
        recomm_data, k, userLikedWebtoon, 10)

    # recommend_webtoon_json = dict()
    recommendData = list()
    for i in recommend_list:
        # recommendData = recommendWebtoonList.RecommendWebtoonList()
        # recommendData.webtoonId = i
        # recommendData.done()

        recommend_webtoon_dict = dict()
        recommend_webtoon_dict["webtoonId"] = i
        recommendData.append(recommend_webtoon_dict)

    recommend_webtoon_json = dict()
    recommend_webtoon_json["result"] = recommendData

    # recommend_webtoon_json = recommendData.make_json()

    # print(recommend_list)
    print(recommend_webtoon_json)
    return recommend_webtoon_json
