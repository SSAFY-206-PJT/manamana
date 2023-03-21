from selenium import webdriver


def create_webdriver():
    
    driver = webdriver.Chrome(executable_path='./chromedriver')
    driver.implicitly_wait(10)
    
    return driver