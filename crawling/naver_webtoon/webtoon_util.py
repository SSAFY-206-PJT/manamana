from colorthief import ColorThief
#from HSL.hsl import rgb_to_hsl, hsl_to_rgb
import colorsys
import urllib.request
import numpy as np
from time import sleep
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By


def image_main_color_to_hsl(url):
    
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36'})
    req = urllib.request.urlopen(req)
    
    # arr = np.asarray(bytearray(req.read()), dtype=np.uint8)
    ct = ColorThief(req)
    dominant_color = ct.get_color(quality = 1)
    
    
    r,g,b = dominant_color
    ONE_255 = 1.0 / 255.0
    h, l, s = map(lambda x: int(round(x*100, 0)), colorsys.rgb_to_hls(r * ONE_255, g * ONE_255, b * ONE_255))
    #h,s,l = map(lambda x: int(round(x*100, 0)), rgb_to_hsl(r * ONE_255, g * ONE_255, b * ONE_255))
    
    return f'{int(round(h*3.6,0))},{s},{l}'

def scroll_down(driver):
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
        
        #새로 얻어온 높이와 이전높이가 같으면 한번더 대기.
        if new_height == last_height:
            sleep(2)
            if new_height == last_height:
                break
        last_height = new_height
        
def scroll_page_down(driver):
    
    last_height = driver.execute_script("return document.body.scrollHeight")
    
    count = 0
    while True:
        driver.find_element(By.CSS_SELECTOR,'body').send_keys(Keys.PAGE_DOWN)
        new_height = scroll_location = driver.execute_script("return document.body.scrollHeight")
        
        if last_height == new_height: count+=1
        else: count = 0
        ## 5번만 넘어도 가능한데 혹시 모를 사태를 대비해서 15정도로 잡음.
        if(count >= 15): break
        sleep(0.5)
        
        last_height = new_height
