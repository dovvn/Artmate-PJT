# SUBPJT3 : SNS 웹 큐레이팅 서비스    
> 주제: 전시회 큐레이팅 SNS  
> 팀명: 2조(B202)  

### [SubPJT3 노션 문서](https://www.notion.so/Sub-PJT3-2fad4b5e52bb4f3fba869bddfbeecc8f)

## 팀원 역할
| 팀원   | 역할 | 비고                        |
| ------ | ---- | --------------------------- |
| 정다운 | 팀장 | 백엔드 개발, UI디자인        |
| 김지형 | 팀원 | 프론트엔드 테크리더, GIT마스터, 화면정의서    |
| 유진이 | 팀원 | 프론트엔드 개발, QA(Jira관리), UI디자인 |
| 정현우 | 팀원 | 프론트엔드 개발, QA(Jira관리), 화면정의서            |
| 정혜지 | 팀원 | 백엔드 테크리더, 화면정의서           |

## 기술 스택
![Javascript](https://img.shields.io/badge/JavaScript-ES6+-green?logo=javascript) ![VueJS](https://img.shields.io/badge/Vue.js-v4.5.10-green?logo=vue.js) ![Vuetify](https://img.shields.io/badge/Vuetify-v3-green?logo=Vuetify) ![Java](https://img.shields.io/badge/Java-v1.8-blue?logo=java) ![Spring Boot](https://img.shields.io/badge/spring-v3.9.15-blue?logo=spring) ![AWS RDS](https://img.shields.io/badge/AWS%20RDS----blue?logo=amazonaws) ![MySQL](https://img.shields.io/badge/MySQL-v8.0-blue?logo=mysql)   

## Gantt Chat
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
    UCC 제작   :active,2021-02-15,3d

    Jira,Git,MM연동   :done,after p2,6h
    NGinx&Jenkins 자동 배포 :done, 2021-02-03,5d
    중간 발표PPT: done,2021-02-04,1d
    최종 발표PPT: active,2021-02-15,3d
    최종 화면정의서, 와이어프레임 PDF: ,2021-02-17,2d
    README 정리: ,2021-02-18,1d
    
    

    section Frontend
    SubⅡ SNS 미완성 보완          :done, 2021-02-01, 4d
    SubⅡ 반응형 보완          :done, 2021-02-01, 4d
    피드 3D 전시 페이지   :done,2021-02-06,9d
    지도페이지(카카오맵 API 적용):done,2021-02-07, 8d
    전시회 상세페이지   :done,2021-02-07,4d
    스크랩북 페이지   :done,2021-02-08,5d
    전시회 메인페이지 :done, 2021-02-08, 7d
    소개 페이지   :active,2021-02-15,2d
    전체 디테일 디자인 수정   :active,2021-02-15,4d
    
    
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

## 기능
- 전시회 SNS 커뮤니티
    - 실시간 뉴스피드 목록
    - 피드 좋아요,팔로우,SNS공유 기능
    - 피드 목록 최신순으로 업데이트
    - 팔로우 등의 알림 센터 기능
- 전시회 정보 공유
    - 사용자 취향에 맞는 맞춤형 전시회 안내(해시태그 이용)
    - 스크랩북 기능  
    - 현재 내 지역 주변의 진행중인 전시회 안내
    - 원하는 날짜에 진행중인 전시회 안내
    - 키워드 검색 기능(인기 키워드/최근 키워드)
    - 사용자 후기 피드와 연결

## 커밋 메시지
```bash
FE(BE)_날짜:완성한 기능(작업 부분-폴더명)
```
* [참고한 커밋 메시지 작성법](https://blog.ull.im/engineering/2019/03/10/logs-on-git.html)

## Branch 규칙  
```bash
develop/feature/기능명  
```

## Code Style
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

## ER 다이어그램
![artmate_erd](/uploads/188933c16471be41c1a72bc04d27ac1f/artmate_erd.png)  



## 화면 정의서

![화면정의서](./assets/화면정의서.png)

![화면정의서1](./assets/화면정의서1.png)

![화면정의서2](./assets/화면정의서2.png)

![화면정의서3](./assets/화면정의서3.png)

![화면정의서4](./assets/화면정의서4.png)



![화면정의서5](./assets/화면정의서5.png)

![화면정의서6](./assets/화면정의서6.png)

![화면정의서7](./assets/화면정의서7.png)

![화면정의서8](./assets/화면정의서8.png)

![화면정의서9](./assets/화면정의서9.png)

![화면정의서9](./assets/화면정의서9-1.png)

![화면정의서9](./assets/화면정의서9-2.png)

![화면정의서10](./assets/화면정의서10.png)

![화면정의서11](./assets/화면정의서11.png)

![화면정의서12](./assets/화면정의서12.png)

![화면정의서13](./assets/화면정의서13.png)

![화면정의서14](./assets/화면정의서14.png)

![화면정의서15](./assets/화면정의서15.png)

![화면정의서16](./assets/화면정의서16.png)

![화면정의서17](./assets/화면정의서17.png)

![화면정의서18](./assets/화면정의서18.png)

![화면정의서19](./assets/화면정의서19.png)

![화면정의서20](./assets/화면정의서20.png)

![화면정의서21](./assets/화면정의서21.png)

![화면정의서22](./assets/화면정의서22.png)

![화면정의서23](./assets/화면정의서23.png)

![화면정의서24](./assets/화면정의서24.png)

![화면정의서25](./assets/화면정의서25.png)

![화면정의서26](./assets/화면정의서26.png)

![화면정의서27](./assets/화면정의서27.png)

![화면정의서28](./assets/화면정의서28.png)

![화면정의서29](./assets/화면정의서29.png)

![화면정의서30](./assets/화면정의서30.png)

## 와이어프레임  

![슬라이드1](/uploads/cf1fc950283311e3aad335a3a1845008/슬라이드1.PNG)

![슬라이드2](/uploads/3944c578659fbb3ae2dda086f00e7714/슬라이드2.PNG)

![슬라이드3](/uploads/fc8e1f95749c20a679817ac1b1fca61d/슬라이드3.PNG)

![슬라이드4](/uploads/7e66edbfb3d294005b65ed34a0bb922c/슬라이드4.PNG)

![슬라이드5](/uploads/c52ecd2aae13f21849f281dfc259e75b/슬라이드5.PNG)

![슬라이드6](/uploads/556b254e67bd808dc2c5e03494b648fd/슬라이드6.PNG)

![슬라이드7](/uploads/2ee86e189e88dfa443304800409dfe79/슬라이드7.PNG)

![슬라이드8](/uploads/659fd40f790d0338ce8fe2cc4ed63741/슬라이드8.PNG)

![슬라이드9](/uploads/c7d988494562e5cf033a6fc6cde9774d/슬라이드9.PNG)

![슬라이드10](/uploads/8ca8df79d96ffa27b91153fb6d9aab39/슬라이드10.PNG)

![슬라이드11](/uploads/313086436669804d025fbc96a4015ef8/슬라이드11.PNG)

![슬라이드12](/uploads/8cf5a8ececabcdf25acd296c6520baa8/슬라이드12.PNG)

![슬라이드13](/uploads/10c509f0d95d6d1be0bd5cd9e4da608e/슬라이드13.PNG)

![슬라이드14](/uploads/5c9a8244467c2f59a783cdfd17cf74c8/슬라이드14.PNG)

