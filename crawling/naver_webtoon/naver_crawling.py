import requests
from bs4 import BeautifulSoup
from time import sleep
from naver_auth import login
from webdriver import create_webdriver
from webtoon_util import scroll_down, image_main_color_to_hsl,scroll_page_down
import json
# import sys,io


# sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
# sys.stderr = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')

search_url = "https://comic.naver.com/webtoon?tab="
provider_url = "https://comic.naver.com"
fail_list = []
# weeks = ['mon','tue','wed','thu','fri','sat','sun','dailyPlus','finish']
weeks = ['tue']

def episode_parse(soup):
    
    #총 회차 정보 빼내기.
    episode = soup.select('#container > .content_wrap > .content > .EpisodeListView__episode_list_wrap--q0VYg > .EpisodeListView__episode_list_head--PapRv > .EpisodeListView__count--fTMc5')
    episode_text = episode[0].get_text()
    
    return_value = ""
    
    episode_text = episode_text.split(" ")[1]
    
    for i in range(0,len(episode_text)):
        
        #숫자가 아니면 종료
        if not episode_text[i].isdigit():
            break
            
        return_value += episode_text[i]
    print(int(return_value))
    return int(return_value)

## 이름
def title_parse(content_info):
    
    content_info = content_info.find('h2', class_="EpisodeListInfo__title--mYLjC")
    
    title = content_info.get_text()
    
    print(title)

    return title

## 작가
def author_parse(content_info):
    
    author_set = set()
    
    content_infos = content_info.find_all('a', class_='ContentMetaInfo__link--xTtO6')
    
    # 글 그림 원작으로 나눠져있을 수도 있음.
    for content_info in content_infos:
        for temp in content_info.get_text().split('/'):
            author_set.add(temp.strip())
    
    print(author_set)
    return author_set

## 요일
def day_parse(content_info):
    content_info = content_info.find('span', class_='ContentMetaInfo__info_item--utGrf').get_text()
    content_info = content_info.split('∙')[0].strip()
    print(content_info)
    return content_info

## 연재등급
def grade_parse(content_info):
    
    content_info = content_info.find('span', class_='ContentMetaInfo__info_item--utGrf').get_text()
    content_info = content_info.split('∙')[1].strip()
    print(content_info)
    
    return content_info

## 줄거리
def plot_parse(content_info):
    content_info = content_info.find('p',class_='EpisodeListInfo__summary--Jd1WG')
    
    plot = content_info.get_text()
    
    print(plot)
    
    return plot

## 이미지
def image_parse(content_info):
    
    content_info = content_info.find('img', class_='Poster__image--d9XTI')
    image_url = content_info.attrs['src']
    print(image_url)
    
    return image_url


## 태그 - 장르
def gerne_parse(content_info):
    
    result = set()
    
    genres = content_info.find_all('a', class_='TagGroup__tag--xu0OH')
    
    for genre in genres:
        result.add(genre.get_text()[1:])
    
    print(result)
    return result

##시작일
def start_date_parse(soup):
    
    content_info = soup.select('#container > .content_wrap > .content > .EpisodeListView__episode_list_wrap--q0VYg > .EpisodeListList__episode_list--_N3ks > .EpisodeListList__item--M8zq4')
    date = content_info[0].find('span', class_='date').get_text()
    
    print(date)
    
    return date

def crawling_start():
    
    driver = create_webdriver()
    
    # 성인웹툰 크롤링을 위해 네이버 로그인
    login(driver)
    sleep(2)
    
    #요일별 웹툰 저장을 위한 딕셔너리 객체 - 모든 요일하면 많으니, 각요일별로 모아서 저장.
    weebtoons_dict = dict()
    weebtoons_dict["data"] = list()
    
    for week in weeks:
        
        # 네이버 웹툰 접속 - 요일별 탭 접속
        driver.get(search_url + week)
        
        ## 모든 웹툰 로딩을 위해 스크롤
        # scroll_down(driver)
        scroll_page_down(driver)
        sleep(2)
        
        ## 해당 페이지의 html 가져오기
        html = driver.page_source
        ##soup에 넣어서 파싱준비
        soup = BeautifulSoup(html, 'html.parser',from_encoding='utf-8')
        
        ##각 웹툰 별 식별ID 뽑기
        urls = soup.select('.component_wrap > .ContentList__content_list--q5KXY > li > .Poster__link--sopnC')
        
        
        ##각 url 마다 들어가서 확인.
        for url in urls:
            
            # 웹툰 상세페이지 url 가져오기
            webtoon_detail_url = url.attrs["href"]
            
            # 웹툰 식별Id
            webtoon_id = webtoon_detail_url.split('=')[1]
            
            #웹툰 이동url
            webtoon_url = webtoon_detail_url
            
            #해당 주소로 이동.
            driver.get(provider_url + webtoon_detail_url + "&page=1&sort=ASC")
            sleep(0.5)
            
            ##상세 페이지 html
            detail_html = driver.page_source
            
            soup = BeautifulSoup(detail_html,'html.parser',from_encoding='utf-8')
            
            ##웹툰 정보
            content_info = soup.select('#container > .content_wrap > .content > .EpisodeListInfo__comic_info--yRAu0')
            
            #웹툰 이미지
            image = image_parse(content_info[0])
            
            #색상 변환값
            color_hsl = image_main_color_to_hsl(image)
            
            #줄거리
            plot = plot_parse(content_info[0])
            
            #작가
            authors_arr = author_parse(content_info[0])
            
            #장르
            genre_arr = gerne_parse(content_info[0])
            
            
            content_info = soup.select('#container > .content_wrap > .content > .EpisodeListInfo__comic_info--yRAu0 > .EpisodeListInfo__info_area--hkinm > .ContentMetaInfo__meta_info--GbTg4')
            #요일
            day_arr = day_parse(content_info[0])
            
            #연재정보
            grade = grade_parse(content_info[0])
            
            
            #총 회차 
            total_ep = episode_parse(soup)
            
            #시작일
            start_date = start_date_parse(soup)
            
            
            print("++++++++++++++++++++++++++++++++++++")
            
            
        
        
        
        
        # ##json 파일로 저장.
        # with open('./webtoon_json/naver_webtoon.json','a') as f:
        #     json.dump(weebtoon_dict, f, ensure_ascii=False, indent=4)
            
        

    
    

    

    
    
    
    





