import mariadb
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

type = "미국"
moddate = now_format
regdate = now_format
view_count = 0
url = "www.naver.com"
web_site = "NAVER"

for i in range(1, 100):
    content = "미국게시판내용ㅁㄴㅇㄹ"
    title = "미국게시판제목ㅁㄴㅇㄹ"
    origin_content = "미국게시판원문내용ㅁㄴㅇㄹ"
    origin_title = "미국게시판원문제목ㅁㄴㅇㄹ"

    title = title + str(i)  
    content = content + str(i)

    origin_content = origin_content + str(i)
    origin_title = origin_title + str(i)

    curs.execute(f"insert into scrap (moddate, regdate, content, origin_content, title, origin_title, type, url, view_count, web_site) values ({moddate}, {regdate}, '{content}', '{origin_content}', '{title}', '{origin_title}', '{type}', '{url}', '{view_count}', '{web_site}')")

conn.commit()
curs.close()
conn.close()
