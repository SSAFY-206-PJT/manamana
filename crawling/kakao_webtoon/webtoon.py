import sys
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

    def check_null(self):
        arr = [("name", self.name), ("image", self.image), ("plot", self.plot), ("grade", self.grade), ("status", self.status), ("webtoon_url", self.webtoon_url), ("webtoon_id", self.webtoon_id), ("start_date", self.start_date), ("total_ep", self.total_ep), ("genre_arr", self.genre_arr), ("day_arr", self.day_arr), ("authors_arr", self.authors_arr), ("colorHsl", self.colorHsl)]
        error_arr = []
        for string, value in arr:
            if value is None:
                error_arr.append(string)
        if error_arr:
            print("====================")
            print("입력되지 않은 값이 있습니다.")
            print("----------")
            print(*error_arr, sep=",")
            print("----------")
            print("계속하려면 Y, 멈추려면 N을 입력하세요.")
            print("====================")
            while True:
                print("입력: ", end="")
                y_or_n = input().strip().upper()
                if y_or_n == "Y":
                    break
                elif y_or_n == "N":
                    print("크롤링 중지")
                    sys.exit("종료")
                print("입력좀 하지?")

    def done(self):
        """
        dict형태로 가공
        """
        # self.check_null()

        self.webtoons_dict[self.webtoon_id] = {
            "name" : self.name,
            "image" : self.image,
            "plot" : self.plot,
            "grade" : self.grade,
            "status" : self.status,
            "webtoon_url" : self.webtoon_url,
            "webtoon_id" : self.webtoon_id,
            "start_date" : self.start_date,
            "total_ep" : self.total_ep,
            "genre_arr" : self.genre_arr,
            "day_arr" : self.day_arr,
            "authors_arr" : self.authors_arr,
            "colorHsl" : self.colorHsl,
            }

    def make_json(self):
        """
        json으로 반환
        """
        webtoon_json = json.dumps(self.webtoons_dict, ensure_ascii=False, indent=4)
        return webtoon_json
        
