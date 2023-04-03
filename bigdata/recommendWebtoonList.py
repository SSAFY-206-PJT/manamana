import json


class RecommendWebtoonList():

    recommend_webtoon_dict = dict()

    def __init__(self):
        self.webtoonId: int = None

    def done(self):
        self.recommend_webtoon_dict[self.webtoonId] = {
            "webtoonId": self.webtoonId,
        }

    def make_json(self):
        recommend_webtoon_dict = dict()
        recommend_webtoon_dict["result"] = list(
            self.recommend_webtoon_dict.values())

        return recommend_webtoon_dict
