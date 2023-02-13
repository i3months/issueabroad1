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

now = datetime.now().date()

sql = "insert into article (dtype, comment_count, content, create_date, title, view_count, origin_content, origin_title, url, web_site) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
val = ("suggest", "0", "content_text", now, "title_text", "0", "origin_content_text", "origin_title_text", "url", "web_site")

now = datetime.now()
now_format = str(now).replace("-", "").replace(" ", "").replace(":", "")

type = "자유"
moddate = now_format
regdate = now_format
view_count = 0

for i in range(1, 100):
    content = "자유게시판내용ㅁㄴㅇㄹ"
    title = "자유게시판제목ㅁㄴㅇㄹ"
    writer_email = "user" + str(i) + "@naver.com"    
    title = title + str(i)  
    content = content + str(i)
    curs.execute(f"insert into user (moddate, regdate, content, title, type, view_count, writer_email) values ({moddate}, {regdate}, '{content}', '{title}', '{type}', '{view_count}', '{writer_email}')")        

conn.commit()
curs.close()
conn.close()
