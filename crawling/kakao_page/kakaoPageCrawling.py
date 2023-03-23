import requests
from bs4 import BeautifulSoup as bs
from selenium import webdriver
from selenium.webdriver.common.by import By
from urllib.request import urlopen
from webtoonUtil import *
from tqdm import tqdm
import webtoon
import time
import re

driver = webdriver.Chrome("./chromedriver")
time.sleep(2)

login(driver)  # 로그인
time.sleep(2)

f = open("./webtoon-json.txt", 'a', encoding="UTF-8")  # json을 저장할 파일 지정

error_list = []

"""
요일별 크롤링 시작
"""
day_id = ["1", "2", "3", "4", "5", "6", "7", "12"]
# day_id = ["1"]

# for d_id in tqdm(day_id):  # 배포할때 tqdm 빼야함
for d_id in day_id:

    url = "https://page.kakao.com/menu/10/screen/5?tab_uid="
    driver.get(url + d_id)

    time.sleep(1)

    html = driver.page_source
    soup = bs(html, 'html.parser')

    """
	스크롤 처리
	"""
    # 스크롤 높이 가져옴
    last_height = driver.execute_script("return document.body.scrollHeight")
    while True:
        # 끝까지 스크롤 다운
        driver.execute_script(
            "window.scrollTo(0, document.body.scrollHeight);")

    # 2초 대기
        time.sleep(2)

    # 스크롤 다운 후 스크롤 높이 다시 가져옴
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height

    time.sleep(1)

    html = driver.page_source
    soup = bs(html, 'html.parser')

    """
	해당 요일의 웹툰 id 전부 가져오기
	"""
    i = 1
    total_url = []
    while True:
        temp = str(soup.select('#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div > div.flex.grow.flex-col > div.flex.grow.flex-col > div > div:nth-child(3) > div > div > div:nth-child(' + str(i) + ') > div > a'))
        if len(temp) == 2:
            break
        temp = temp[temp.find("href") + 15:temp.find(">")-1]
        total_url.append(temp)
        i += 1

    # 크롬 메모리초과 방지
    ##########################
    driver.quit()

    driver = webdriver.Chrome("./chromedriver")

    login(driver)
    time.sleep(2)
    ##########################

    """
    웹툰 정보 크롤링
    """
    # for webtoon_id in tqdm(total_url):  # 배포할때 tqdm 빼야됨
    for webtoon_id in total_url:

        try:

            webtoon_info = webtoon.Webtoon()  # 웹툰 클래스 생성

            webtoon_base_url = "https://page.kakao.com/content/"
            webtoon_url = webtoon_base_url + webtoon_id

            webtoon_info.webtoon_id = webtoon_id
            webtoon_info.webtoon_url = webtoon_url

            driver.get(webtoon_url)
            time.sleep(2)

            html = driver.page_source
            soup = bs(html, 'html.parser')

            webtoon_info.name = soup.select_one(
                '#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.flex.w-320pxr.flex-col > div:nth-child(1) > div.w-320pxr.css-0 > div > div.css-0 > div.relative.text-center.mx-32pxr.py-24pxr > span').get_text()

            authors = soup.select_one('#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.flex.w-320pxr.flex-col > div:nth-child(1) > div.w-320pxr.css-0 > div > div.css-0 > div.relative.text-center.mx-32pxr.py-24pxr > div.flex.items-center.justify-center.mt-4pxr.flex-col.text-el-50.opacity-100.all-child\:font-small2 > div.mt-4pxr > span').get_text()
            webtoon_info.authors_arr = re.split(',\s|,', authors)

            day_status = soup.select_one('#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.flex.w-320pxr.flex-col > div:nth-child(1) > div.w-320pxr.css-0 > div > div.css-0 > div.relative.text-center.mx-32pxr.py-24pxr > div.flex.items-center.justify-center.mt-4pxr.flex-col.text-el-50.opacity-100.all-child\:font-small2 > div:nth-child(1) > span').get_text()
            day_status = re.split(',\s|\s', day_status)
            webtoon_info.day_arr = day_status[:-1]
            webtoon_info.status = day_status[-1]

            image = str(soup.select('#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.flex.w-320pxr.flex-col > div:nth-child(1) > div.w-320pxr.css-0 > div > div.css-0 > div.mx-auto.css-1cyn2un-ContentOverviewThumbnail > div > div > img'))
            image = image[image.find("src")+5:-4]
            webtoon_info.image = image

            total_ep = soup.select_one('#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.ml-4px.flex.w-632pxr.flex-col > div:nth-child(2) > div:nth-child(1) > div.flex.h-44pxr.w-full.flex-row.items-center.justify-between.px-15pxr.bg-bg-a-20 > div.flex.h-full.flex-1.items-center.space-x-8pxr > span').get_text()
            webtoon_info.total_ep = total_ep[3:]

            webtoon_info.start_date = soup.select_one(
                '#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.ml-4px.flex.w-632pxr.flex-col > div:nth-child(2) > div:nth-child(1) > div.min-h-360pxr > ul > li:nth-child(1) > div > div > a > div > div.flex.flex-col > div.text-ellipsis.line-clamp-1.font-small2.text-el-60 > span').get_text()

            # 작품소개로 이동
            info_url = webtoon_url + "?tab_type=about"
            driver.get(info_url)
            time.sleep(1.5)

            html = driver.page_source
            soup = bs(html, 'html.parser')

            genre_arr = soup.select_one(
                '#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.ml-4px.flex.w-632pxr.flex-col > div.flex-1.bg-bg-a-20 > div.flex.pr-32pxr > div:nth-child(1) > div.mt-16pxr.px-32pxr > div:nth-child(1) > div > span:nth-child(3)').get_text().split()
            if "액션/무협" in genre_arr:
                webtoon_info.genre_arr = ["액션", "무협"]
            else:
                webtoon_info.genre_arr = genre_arr

            webtoon_info.plot = soup.select_one(
                '#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.ml-4px.flex.w-632pxr.flex-col > div.flex-1.bg-bg-a-20 > div.text-el-60.break-keep.py-20pxr.pt-31pxr.pb-32pxr > span').get_text()

            grade = soup.select_one(
                '#__next > div > div.flex.w-full.grow.flex-col.px-122pxr > div.flex.h-full.flex-1 > div.mb-28pxr.ml-4px.flex.w-632pxr.flex-col > div.flex-1.bg-bg-a-20 > div.flex.pr-32pxr > div:nth-child(1) > div.mt-16pxr.px-32pxr > div:nth-child(3) > div').get_text()
            if (grade == "15세이용가") or ("15" in grade):
                grade = "전체이용가"
            if (grade == "19세이용가") or ("19" in grade):
                grade = "성인"
            webtoon_info.grade = grade

            hsl = image_main_color_hsl(image)
            webtoon_info.colorHsl = hsl

            webtoon_info.done()

            time.sleep(3)

        except:
            error_list.append(webtoon_id)
            pass

driver.quit()
webtoon_json = webtoon_info.make_json()
f.write(webtoon_json)
f.write("\n")
f.close()

print(len(error_list))
