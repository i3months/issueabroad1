## issueabroad
> 해외 커뮤니티 사이트 모아보기
<br>

## 소개
해외 웹 사이트의 게시글들을 가져오고 한국어로 번역해서 제공하는 웹 애플리케이션 입니다. <br>
기본적인 커뮤니티 게시판의 기능들 또한 제공합니다.
<br>
<br>


## Tech Stack
`Spring Boot` `Thymeleaf` `JavaScript` `JPA`  `MariaDB` `Spring Security` `etc...`
<br>
<br>


## E-R Diagram
<img width="464" alt="image" src="https://user-images.githubusercontent.com/85468465/218659944-c114b538-6408-47bd-8351-37cba58fee24.png">
<br>
<br>


## 화면 구성
|![로그인](https://user-images.githubusercontent.com/85468465/218661734-17251cdd-86c5-4eb9-b726-dc63dadd31f3.png)|![회원가입](https://user-images.githubusercontent.com/85468465/218661889-81975410-626c-4238-b722-ffae215e2f95.png)|![메인화면1](https://user-images.githubusercontent.com/85468465/218663767-3b412239-85e3-405e-befe-3179dde4a042.png)|![메인화면2](https://user-images.githubusercontent.com/85468465/218666470-6f86b329-3651-4fa6-a797-d67d6712553e.png)|
|:--:|:--:|:--:|:--:|
|로그인|회원가입|메인화면1|메인화면2|

|![글1](https://user-images.githubusercontent.com/85468465/218667083-844d45d0-458e-4af9-95da-d7f28db09389.png)|![글2](https://user-images.githubusercontent.com/85468465/218667461-32250cde-bda7-4ece-a957-17d366b6f92f.png)|![게시판](https://user-images.githubusercontent.com/85468465/218667773-c7db650b-8e31-40e3-9261-ce16263bc6ca.png)|![검색](https://user-images.githubusercontent.com/85468465/218667968-c50f14d3-a6d1-40b1-9a77-fb813da530b1.png)|
|:--:|:--:|:--:|:--:|
|글1|글2|게시판|검색|

|![댓글](https://user-images.githubusercontent.com/85468465/218668237-12405ce2-8d7e-41cd-994b-9d74fbcf73f8.png)|![글 작성](https://user-images.githubusercontent.com/85468465/218668612-8940e5f4-e589-4fc7-b1a9-69e970381235.png)|![내가 쓴 글](https://user-images.githubusercontent.com/85468465/218669688-bb212e4f-9d81-430e-b7cf-10b5b8d987c9.png)|![글 수정 및 삭제](https://user-images.githubusercontent.com/85468465/218669834-fe477b7a-4743-4de7-8e80-d8ab86f9ab8b.png)|
|:--:|:--:|:--:|:--:|
|댓글|글 작성|내가 쓴 글|글 수정 및 삭제|

<br><br>

## 간단한 설명

### 로그인
- 기본적으로 로그인 한 사용자만 서비스를 이용할 수 있도록 설계했습니다. 
- 스프링 시큐리티를 사용했고 비밀번호는 PasswordEncoder를 사용해 암호화 한 후 저장합니다.


### 회원가입
- 회원 가입 시 입력되는 정보는 BindingResult를 사용해 검증을 진행합니다.
- 조건에 부합하지 않는 정보는 BindingResult를 통해 뷰 계층으로 에러 메세지가 전송됩니다.


### 게시글과 댓글
- 게시글은 해외 웹 사이트에서 가져온 게시글을 보여주는 카테고리와 사용자가 직접 작성한 카테고리로 나뉩니다. 
- 자신이 작성한 게시글 및 댓글만 수정하거나 삭제할 수 있습니다.
- 댓글 구현은 REST방식과 ajax기술을 사용해 처리합니다.
- 게시글의 페이징은 Spring Data JPA기술을 사용해 처리하고, 기본적인 CRUD는 JPQL과 Spring Data JPA를 사용해서 처리합니다.


### 게시글 검색
- 해외 웹 사이트에서 가져온 게시글의 제목을 기준으로 게시글을 검색할 수 있습니다.
- 한국어 제목과 원문 제목을 모두 인식합니다.


### 크롤링
- Quora 웹 사이트에서 게시글을 가져옵니다.
- 무한스크롤으로 게시글을 적당히 불러온 후 불러온 게시글들을 하나씩 눌러서 내용을 가져옵니다.
- 크롤링은 ChromeDriver와 Selenium을 사용해서 진행하고, 번역은 파이썬의 googletrans 모듈을 사용해서 처리합니다.



 
 



