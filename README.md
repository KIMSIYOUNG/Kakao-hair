## Kakao hair shop coding 

### 클래스 다이어그램
![Kakao-hair](https://user-images.githubusercontent.com/49060374/88496307-b17f4080-cff7-11ea-9f7d-71061e1a2ce9.png)

### 도메인
- 일반 회원
- 헤어샵
- 게시물(커트, 펌 등 헤어샵에서 제공되는 서비스)
- 쿠폰(회원, 게시물 각각 적용되는 할인 가격 +(샵에서 제공하는 쿠폰))
- 예약(회원, 디자이너, 게시물)
- 리뷰(예약에 대한 리뷰)
- 좋아요(게시물, 샵, 디자이너)
- 태그(샵)
- 디자이너 

### 개발 과정
- 이슈 관리 및 프로젝트 관리
- 도메인 설계
    - 객체와 데이터 베이스 관계
- 카카오 로그인 및 관리
    - 외부 API에 의존적인 부분은 어떻게 설계해야하는가?
    - 응답과 관련된 JSON 파싱
- 권한 관리
    - 회원과 헤어샵 직원
- 각 도메인 별(기본적인) CRUD 및 테스트 구조
    - Dto는 어디 레이어 까지 가져갈 것인가?
- API 문서화
    - Spring rest docs vs Swagger
    - 문서화는 어떤 형태로 하는 것이 사용하기 좋을까?
- 배포
    - CI 
    - CD
    - Logging
- 도메인 심화 개발 
    - 조회성 쿼리는 어떻게 관리할 것인가?
    - 위치 기반 서비스
    - 시간 단위 예약 시스템
    - 검색 기능
 
### 파트별 구분
- 일반 회원(손님) B2C : Mobile
- Shop 단위의 관리 페이지 B2B : Web
- 어플리케이션 관리자 : Web
 
### 개발 관련
- 단일 모듈 프로젝트 - 분리 가능성 고려
- Process : ATDD - TDD (controller, service, repository, domain)
- Test coverage : 95%
- back-end - front-end 구분해서 진행
- Simple Api documentation
- CI/CD : Jenkins with docker

### 기능 목록
- 카카오 로그인
- 카카오 위치기반 서비스 
- 각 도메인의 CRUD 기능
- 화면에 종속적인 조회성 Query
- 이미지 업로드
- 알림
- (채팅 혹은 쪽지) 
- 검색조건에 따른 동적 쿼리
- 검색

### 사용 기술
- java 8 
- spring boot
- gradle
- jpa, queryDsl
- h2, mysql
- jenkins, docker
- intellij, mac os

### 개발 순서
- member(with Designer) - kakao Login

- LoginArgumentResolver, Interceptor(token validation)

- Authorization
 
- shop - post - reservation
- review - like - coupon - tag 