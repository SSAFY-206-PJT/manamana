from time import sleep
from config import NAVER_AUTH_INFO
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import pyperclip



def login(driver):
    
    #로그인 페이지로 이동
    driver.get('https://nid.naver.com/nidlogin.login')
    
    
    #아이디 위치
    # (class)panel_wrap > panel_item > panel_inner > id_pw_wrap > (id)id_line > (id)id_cell >(name)id 
    # driver.find_element(By.CSS_SELECTOR, 'ul.panel_wrap > .panel_item > .panel_inner > .id_pw_wrap > #id_line > #id').send_keys(NAVER_AUTH_INFO['id'])
    id_element = driver.find_element(By.CSS_SELECTOR, 'ul.panel_wrap > .panel_item > .panel_inner > .id_pw_wrap > #id_line > #id')
    id_element.send_keys()
    pyperclip.copy(NAVER_AUTH_INFO['id'])
    id_element.send_keys(Keys.CONTROL,'v')
    sleep(1)
    
    #비밀번호 위치
    # (class)panel_wrap > panel_item > panel_inner > id_pw_wrap > (id)pw_line > (id)pw_cell >(name)pw
    # driver.find_element(By.CSS_SELECTOR, 'ul.panel_wrap > .panel_item > .panel_inner > .id_pw_wrap > #pw_line > #pw').send_keys(NAVER_AUTH_INFO['passwd'])
    pw_element = driver.find_element(By.CSS_SELECTOR, 'ul.panel_wrap > .panel_item > .panel_inner > .id_pw_wrap > #pw_line > #pw')
    pw_element.send_keys()
    pyperclip.copy(NAVER_AUTH_INFO['passwd'])
    pw_element.send_keys(Keys.CONTROL,'v')
    sleep(1)
    
    
    #로그인 버튼 위치
    # (class)panel_wrap > panel_item > pannel_inner > btn_login_wrap > (id)log.login
    driver.find_element(By.CSS_SELECTOR, 'ul.panel_wrap > .panel_item > .panel_inner > .btn_login_wrap > button').click()
    
    
    