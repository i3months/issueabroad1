import time
import mariadb
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
import undetected_chromedriver as uc
from webdriver_manager.chrome import ChromeDriverManager
import requests
import googletrans
from datetime import datetime

conn = mariadb.connect(
        host='127.0.0.1', 
        user='issueabroad1', 
        password='issueabroad1', 
        database='issueabroad1', 
        port=3307,         
) 

curs = conn.cursor()

now = datetime.now()
now_format = str(now).replace("-", "").replace(" ", "").replace(":", "")

content = ""
origin_content = ""
title = ""
origin_title = ""
type = "미국"
url = ""
moddate = now_format
regdate = now_format
view_count = 0
web_site = "Quora"

curs.execute(f"insert into scrap (moddate, regdate, content, origin_content, title, origin_title, type, url, view_count, web_site) values ({moddate}, {regdate}, '{content}', '{origin_content}', '{title}', '{origin_title}', '{type}', '{url}', '{view_count}', '{web_site}')")
print("저장됐어요")

conn.commit()
curs.close()
conn.close()