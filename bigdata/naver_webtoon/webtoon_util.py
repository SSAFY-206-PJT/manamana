from colorthief import ColorThief
from HSL.hsl import rgb_to_hsl, hsl_to_rgb
import urllib.request
import numpy as np
from time import sleep


def image_main_color_to_hsl(url):
    
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36'})
    req = urllib.request.urlopen(req)
    
    # arr = np.asarray(bytearray(req.read()), dtype=np.uint8)
    ct = ColorThief(req)
    dominant_color = ct.get_color(quality = 1)
    
    
    r,g,b = dominant_color
    ONE_255 = 1.0 / 255.0
    
    h,s,l = map(lambda x: int(round(x*100, 0)), rgb_to_hsl(r * ONE_255, g * ONE_255, b * ONE_255))
    
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