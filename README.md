# 🎨 SUBPJT3 : SNS 웹 큐레이팅 서비스    
> 주제: 전시회 큐레이팅 SNS  
> 팀명: 2조(B202)  

## 문서
> [SubPJT1 노션 문서](https://www.notion.so/Sub-PJT1-e0891e06df84454da750b3c8f6f18109)    
> [SubPJT2 노션 문서](https://www.notion.so/Sub-PJT2-28ab40acd8104c0585008a3fa24f8f22)    
> [SubPJT3 노션 문서](https://www.notion.so/Sub-PJT3-2fad4b5e52bb4f3fba869bddfbeecc8f)

## 🖌 팀원 역할
| 팀원   | 역할 | 비고                        |
| ------ | ---- | --------------------------- |
| 정다운 | 팀장 | 백엔드 개발, UI디자인        |
| 김지형 | 팀원 | 프론트엔드 테크리더, GIT마스터, 화면정의서    |
| 유진이 | 팀원 | 프론트엔드 개발, QA(Jira관리), UI디자인 |
| 정현우 | 팀원 | 프론트엔드 개발, QA(Jira관리), 화면정의서            |
| 정혜지 | 팀원 | 백엔드 테크리더, 화면정의서           |

## 🖌 기술 스택
![Javascript](https://img.shields.io/badge/JavaScript-ES6+-green?logo=javascript) ![VueJS](https://img.shields.io/badge/Vue.js-v4.5.10-green?logo=vue.js) ![Vuetify](https://img.shields.io/badge/Vuetify-v3-green?logo=Vuetify) ![Java](https://img.shields.io/badge/Java-v1.8-blue?logo=java) ![Spring Boot](https://img.shields.io/badge/spring-v3.9.15-blue?logo=spring) ![AWS RDS](https://img.shields.io/badge/AWS%20RDS----blue?logo=amazonaws) ![MySQL](https://img.shields.io/badge/MySQL-v8.0-blue?logo=mysql)   

## 🖌 Gantt Chat
> 매일 오전 미팅 후 업데이트  
```mermaid  
gantt
    title Artmate 진행상황
    dateFormat  YYYY-MM-DD
    section 프로젝트 준비
    SubⅢ Jira,Git 생성  :done, 2021-02-01, 6h
    SubⅡ 회고          :done, p2,2021-02-01, 12h
    UI/UX(전시회)   :done,2021-02-04,2d
    [1차]UCC컨셉, 사전조사   :done,after p2,6h
    [2차]UCC컨셉   :done,2021-02-07,1d
    UCC 제작   :done,2021-02-15,3d

    Jira,Git,MM연동   :done,after p2,6h
    NGinx&Jenkins 자동 배포 :done, 2021-02-03,5d
    중간 발표PPT: done,2021-02-04,1d
    최종 발표PPT: done,2021-02-15,3d
    QA~테스트케이스 수행: done,2021-02-15,3d
    최종 화면정의서, 와이어프레임 PDF, ERD: done,2021-02-17,2d
    배포 https 적용:done,2021-02-15,2d
    README 정리: done,2021-02-18,1d
    
    
    

    section Frontend
    SubⅡ SNS 미완성 보완          :done, 2021-02-01, 4d
    SubⅡ 반응형 보완          :done, 2021-02-01, 4d
    피드 3D 전시 페이지   :done,2021-02-06,9d
    지도페이지(카카오맵 API 적용):done,2021-02-07, 8d
    전시회 상세페이지   :done,2021-02-07,4d
    스크랩북 페이지   :done,2021-02-08,5d
    전시회 메인페이지 :done, 2021-02-08, 7d

    전체 디테일 디자인 수정   :done,2021-02-15,4d
    
    
    section Backend
    실시간 피드 알림 기능(웹소켓)          :done, 2021-02-01, 4d
    SNS로그인 연동          :done, 2021-02-01, 2d
    공공데이터API 가져오기/태그 분류          :done, p1, 2021-02-02, 1d
    취향선택 기능:done,2021-02-06,1d
    전시회 조회 기능:done,2021-02-05,6d
    추가 공공데이터API 가져오기          :done, p1, 2021-02-09, 2d
    실시간 전시회 알림 기능(웹소켓)          :done, 2021-02-08, 3d
    jsonup/네이버 검색 크롤링     :done, 2021-02-13,2d
```

---    
## ✔ 커밋 메시지
```bash
FE(BE)_날짜:완성한 기능(작업 부분-폴더명)
```
* [참고한 커밋 메시지 작성법](https://blog.ull.im/engineering/2019/03/10/logs-on-git.html)

## ✔ Branch 규칙  
```bash
develop/feature/기능명  
```

## ✔ Code Style
| FrontEnd   | BackEnd |
| ------ | ---- |
| 1. 폴더명: 첫글자 대문자<br/>→ ex) Feed/Add.vue <br/><br/>2. 파일명: 첫글자 대문자 <br/> → ex) Add.vue <br/><br/>3. 경로명: 소문자 → ex) /add | 1. 클래스명:  첫글자 대문자 + camel case <br/> ex) MainController.java <br/><br/> 2. 함수, 변수: 첫글자 소문자 + camel case <br/> ex) public void setUserName(); |
- if문
    - 한줄 일 때, Block 처리하기
    - else if / else /중괄호는 조건문 바로 옆에 붙이기

    ```java
    if(condition){
     statement;
    } else if(condition2){
     statement2;
    } else{
     statement3;
    }
    ```

- for
  
    - 단순 반복문은 iterator를 i,j,k,...,z순으로 명명하기
- 주석 상대방이 이해할 수 있도록 달기
    - /**/ 설명 여러줄 필요할 때 코드 위에 작성
    - // 간단한 주석 코드 옆에 작성

---   
 

---    
## 🖌 기능
- 전시회 SNS 커뮤니티
    - 실시간 뉴스피드 목록
    - 피드 좋아요,팔로우,SNS공유 기능
    - 피드 목록 최신순으로 업데이트
    - 팔로우, 전시회 등의 알림 센터 기능
    - 내 게시글로 3D room 제작 및 공유 기능
- 전시회 정보 공유
    - 사용자 취향에 맞는 맞춤형 전시회 안내(해시태그 이용)
    - 스크랩북 기능  
    - 현재 내 지역 주변의 진행중인 전시회 안내
    - 원하는 날짜에 진행중인 전시회 안내
    - 키워드 검색 기능(인기 키워드/최근 키워드)
    - 사용자 후기 피드와 연결


## 💻 기능 스크린 화면 미리보기  
### 1. 메인 화면           
![메인](https://user-images.githubusercontent.com/59414210/108856980-f6bb2d80-762d-11eb-908e-3b38ca6940e1.gif)  



### 2. 유저검색 및 전시회 검색 화면
![검색1](https://user-images.githubusercontent.com/59414210/108856885-ddb27c80-762d-11eb-984b-72ca7d227589.gif) ![검색2](https://user-images.githubusercontent.com/59414210/108856878-dbe8b900-762d-11eb-9dd7-b926ed9cdfa9.gif)  

 
 
 
### 3. 3D 피드       
![3d_피드](https://user-images.githubusercontent.com/59414210/108856172-1d2c9900-762d-11eb-9b55-6d1ff3414ef6.gif)  



### 4. 전시회 페이지 및 스크랩북          
![전시회상세](https://user-images.githubusercontent.com/59414210/108856782-bf4c8100-762d-11eb-86ed-596d2c1170c5.gif) 
![전시회](https://user-images.githubusercontent.com/59414210/108856790-c1164480-762d-11eb-8913-bc8727d76ee2.gif)  

---    



# 📑산출물     

##  🖌ER 다이어그램   
![artmate-ERD](https://user-images.githubusercontent.com/59414210/108856347-51a05500-762d-11eb-972f-60e3168ec63d.png)  


---    

 
## 🖌 화면 정의서        
[화면정의서 링크](https://github.com/dovvn/Artmate-PJT/blob/master/%EC%82%B0%EC%B6%9C%EB%AC%BC/2.%ED%99%94%EB%A9%B4%EC%A0%95%EC%9D%98%EC%84%9C/%ED%99%94%EB%A9%B4%EC%A0%95%EC%9D%98%EC%84%9C-%EC%B5%9C%EC%A2%85.pdf)     
 


## 🖌 와이어프레임  
![슬라이드1](https://user-images.githubusercontent.com/59414210/108857315-50235c80-762e-11eb-83d6-8cd66376b946.PNG)
![슬라이드2](https://user-images.githubusercontent.com/59414210/108857320-51548980-762e-11eb-88de-474559a5d6fa.PNG)
![슬라이드3](https://user-images.githubusercontent.com/59414210/108857322-51ed2000-762e-11eb-80eb-d20cd173bf46.PNG)
![슬라이드4](https://user-images.githubusercontent.com/59414210/108857325-5285b680-762e-11eb-89d1-1be5958c6353.PNG)
![슬라이드5](https://user-images.githubusercontent.com/59414210/108857326-5285b680-762e-11eb-893d-08deb536c58d.PNG)
![슬라이드6](https://user-images.githubusercontent.com/59414210/108857332-531e4d00-762e-11eb-84c7-6f9a2174ef50.PNG)
![슬라이드7](https://user-images.githubusercontent.com/59414210/108857335-531e4d00-762e-11eb-8846-d05d831b6925.PNG)
![슬라이드8](https://user-images.githubusercontent.com/59414210/108857336-53b6e380-762e-11eb-993f-18eb869c45eb.PNG)
![슬라이드9](https://user-images.githubusercontent.com/59414210/108857337-544f7a00-762e-11eb-9f70-7dc6221d4d99.PNG)
![슬라이드10](https://user-images.githubusercontent.com/59414210/108857338-544f7a00-762e-11eb-864c-70b60bae6be0.PNG)
![슬라이드11](https://user-images.githubusercontent.com/59414210/108857340-54e81080-762e-11eb-96da-45a661131a64.PNG)
![슬라이드12](https://user-images.githubusercontent.com/59414210/108857341-54e81080-762e-11eb-9ec8-4a3143a67c3e.PNG)
![슬라이드13](https://user-images.githubusercontent.com/59414210/108857345-5580a700-762e-11eb-8308-2927dfce1469.PNG)
![슬라이드14](https://user-images.githubusercontent.com/59414210/108857348-5580a700-762e-11eb-9d1a-a9b9e969213c.PNG)
![슬라이드15](https://user-images.githubusercontent.com/59414210/108857350-56193d80-762e-11eb-859f-e40e28d05751.PNG)
![슬라이드16](https://user-images.githubusercontent.com/59414210/108857352-56b1d400-762e-11eb-9687-19380cd382c5.PNG)
![슬라이드17](https://user-images.githubusercontent.com/59414210/108857353-56b1d400-762e-11eb-9bb0-b466dc7af045.PNG)
