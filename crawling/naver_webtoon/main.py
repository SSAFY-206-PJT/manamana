from naver_crawling import crawling_start
from webtoon_util import image_main_color_to_hsl
import sys
from datetime import datetime

weeks = ['mon','tue','wed','thu','fri','sat','sun','dailyPlus','finish']

##요일 탭 별로 로직 실행
crawling_start(weeks)


if __name__ == "__main__":
    
    arguments = sys.argv[1:]
    today = datetime.today().weekday()
    
    for argument in arguments:
        if argument not in weeks:
            print("인자를 잘못 입력했습니다")
            exit()
            
    if len(arguments) == 0: crawling_start([weeks[today]])
    
    elif arguments[0] == "all": crawling_start(weeks)
    
    else: crawling_start(arguments)