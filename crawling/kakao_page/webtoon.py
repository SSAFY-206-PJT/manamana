import json


class Webtoon:
    """
    값 넣기 -> done() -> make_json()
    name: 이름 (str)
    image: 이미지 (str)
    plot: 줄거리 (str)
    grade: 연령등급 (str)
    status: 연재정보 (str)
    webtoon_url: 웹툰 이동 url (str)
    webtoon_id: 웹툰 식별 id (int)
    start_date: 연재 시작일 (str)
    total_ep: 총 회차 (int)
    genre_arr: 장르 리스트 (list)
    day_arr: 요일 리스트 (list)
    authors_arr: 작가 리스트 (list)
    colorHsl: 이미지 대표색(HSL) (str)

    make_json(): json으로 반환
    """

    webtoons_dict = {}

    def __init__(self):
        self.name: str = None
        self.image: str = None
        self.plot: str = None
        self.grade: str = None
        self.status: str = None
        self.webtoon_url: str = None
        self.webtoon_id: int = None
        self.start_date: str = None
        self.total_ep: int = None
        self.genre_arr: list = None
        self.day_arr: list = None
        self.authors_arr: list = None
        self.colorHsl: str = None

    def done(self):
        """
        dict형태로 가공
        """
        self.webtoons_dict[self.webtoon_id] = {
            "name": self.name,
            "image": self.image,
            "plot": self.plot,
            "grade": self.grade,
            "status": self.status,
            "webtoon_url": self.webtoon_url,
            "webtoon_id": self.webtoon_id,
            "start_date": self.start_date,
            "total_ep": self.total_ep,
            "genre_arr": self.genre_arr,
            "day_arr": self.day_arr,
            "authors_arr": self.authors_arr,
            "colorHsl": self.colorHsl,
        }

    def make_json(self):
        """
        json으로 반환
        """
        temp_dict = dict()
        temp_dict["provider_id"] = 3
        temp_dict["data"] = list(self.webtoons_dict.values())

        webtoon_json = json.dumps(
            temp_dict, ensure_ascii=False, indent=4)
        return webtoon_json
