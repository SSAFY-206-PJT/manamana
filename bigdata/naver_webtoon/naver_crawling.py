# import requests
# from bs4 import BeautifulSoup

from time import sleep
from naver_auth import login
from webdriver import create_webdriver



def crawling_start():
    
    driver = create_webdriver()

    # 성인웹툰 크롤링을 위해 네이버 로그인
    login(driver)
    
    sleep(5)


    # 네이버 웹툰 접속
    # driver.get('https://comic.naver.com/webtoon')
    





