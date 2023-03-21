from colorthief import ColorThief
from HSL.hsl import rgb_to_hsl, hsl_to_rgb
from time import sleep
import urllib.request
import os


def image_main_color_hsl(url,tmp_file='tmp.jpg'):
    """
    이미지의 메인 컬러를 추출하는 함수
        Args:
            url (str): 이미지 주소
            tmp_file (str): 임시로 이미지를 저장할 이름
        Returns:
            hsl (str): 이미지의 메인 컬러(HSL)
    """
    req = urllib.request.Request(
        url=url,
        headers={'User-Agent': 'Mozilla/5.0'}
    )
    img=urllib.request.urlopen(req).read()
    with open(tmp_file, mode="wb") as f:
        f.write(img)
    color_thief = ColorThief(tmp_file)
    dominant_color = color_thief.get_color(quality=1)
    # os.remove("test.png")
    r, g, b = dominant_color
    print(r,g,b)
    ONE_255 = 1.0 / 255.0

    h, s, l = map(lambda x: int(round(x*100, 0)), rgb_to_hsl(r * ONE_255, g * ONE_255, b * ONE_255))
    
    return f'{int(round(h*3.6,0))},{s},{l}'



def scroll_bottom(driver):
    """
    맨 아래까지 스크롤하하는 함수
        Args:
            driver (selenium): webdriver
    """
    SCROLL_PAUSE_SEC = 1

    # 스크롤 높이 가져옴
    last_height = driver.execute_script("return document.body.scrollHeight")

    while True:
        # 끝까지 스크롤 다운
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

        # 1초 대기
        sleep(SCROLL_PAUSE_SEC)

        # 스크롤 다운 후 스크롤 높이 다시 가져옴
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height


# 천천히 스크롤하며 모든 이미지 불러오기
def scroll_slow(driver):
    """
    맨 아래까지 천천히 스크롤하하는 함수
        Args:
            driver (selenium): webdriver
    """
    SCROLL_PAUSE_SEC = 1

    # 스크롤 높이 가져옴
    last_height = driver.execute_script("return document.body.scrollHeight")

    while True:
        # 끝까지 스크롤 다운
        for h in range(0,last_height,last_height//10):
            driver.execute_script(f'window.scrollTo(0, {h});')
            sleep(0.5)

        # 1초 대기
        sleep(SCROLL_PAUSE_SEC)

        # 스크롤 다운 후 스크롤 높이 다시 가져옴
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height
