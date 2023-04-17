# mymemory
![image](https://user-images.githubusercontent.com/112387307/232205629-a60b08cf-7ffe-41f4-8bc6-6f5d7a9f1da6.png)

# JPA Repository / RestApi 구현 / JWT  / Spring Security (BcryptPasswordEncoder) / CI/CD / 비동기 Api 구현

배포 주소 : https://www.mymemory.site/

### 📌 프로젝트 기술스택
- 에디터 : Intellij Ultimate
- 개발 툴 : SpringBoot 2.7.8
- 자바 : JAVA 11
- 빌드 : Gradle 7.6
- 서버 : AWS EC2
- 데이터베이스 : MySql
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

## 구현 완료
**Function** | **완료** | 
:------------ | :-------------|  
**회원가입 / 로그인 구현** | :heavy_check_mark: |  
**Spring Security** | :heavy_check_mark: |  
**JWT 발급** | :heavy_check_mark: |  
**BcryptPasswordEncoder 비밀번호 암호화** | :heavy_check_mark: |  
**일기 쓰기(CRUD)** | :heavy_check_mark: |  
**Header, Footer** | :heavy_check_mark: | 
**화면 UI 개발 (헤더, 풋터, 회원가입, home, 로그인, 일기쓰기, 일기수정)** | :heavy_check_mark: | 
**front 비동기 (Ajax)** | :heavy_check_mark: |  
**날짜 캘린더 생성** | :heavy_check_mark: |  
**명언 API** | :heavy_check_mark: |  

# ERD 다이어그램

### 테스트 전용 로그인 회원
**User**
> - ID : test
> - PW : a12341234!

## 🔽 RestApi Swagger
![image](https://user-images.githubusercontent.com/112387307/232205533-e3be67ad-1e2e-4896-a1cb-3542957de7fb.png)


## 🔽 RestAPI EndPoint

| METHOD | URI                                | 기능                                                     |
| ------ | ---------------------------------- |--------------------------- |
| POST   | /api/login                | 로그인                       | 
| POST   | /api/join                | 회원가입                       | 
| GET   | /api/users/isValid       | User객체 반환                      | 
| PUT    | /api/users                      | 회원정보수정                                            |
| GET | /api/users                   |회원 가져오기                                       | 
| DELETE | /api/users/{id}     | 회원삭제                         | 
| POST  | /api/memos/write  | 일기생성                                 | 
| PUT   | /api/memos/{id}     | 일기수정    |
| DELETE | /api/memos/{userId}/{memoId}| 일기삭제     |
| GET | /api/memos/{memoId} | 일기 불러오기                                           |
| GET | /api/memos/{year}/{month}           | 일기 리스트 가져오기                                         |
| GET | /api/memos/{year}/{month}/{day}         | 날짜기준으로 일기 가져오기 |                      

## ScreenShot

<p><img src="" width="48%">
<img src="https://user-images.githubusercontent.com/51112376/232386981-5961914d-cb9a-489e-92b5-9da8219d8756.png" width="48%"></p>

<p><img src="https://user-images.githubusercontent.com/51112376/232386986-dc731a25-7eb0-4adc-a966-b7e8bf2366b5.png" width="48%">
<img src="https://user-images.githubusercontent.com/51112376/232386990-05c1c5ed-b114-4d98-ab77-e6805a66078a.png" width="48%"></p>

<p><img src="https://user-images.githubusercontent.com/51112376/232386997-5c447c5b-3d9a-4231-af71-f8e18d226744.png" width="48%"></p>

