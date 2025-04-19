# Stock Trading API

주식 조회 및 매매 RESTful API입니다.  
주식 주문 정보 조회, 주식 매수, 주식 매도 신청 등의 기능을 제공합니다.

## 기술 스택
- Language: Java 17
- Framework: Spring Boot 3.4.4
- Build Tool: Maven
- Database: MySQL 8
- ORM: JPA
- API 문서화: Swagger
- 테스트: JUnit5, Mockito

## API 주요 스펙
- Swagger yaml 경로 : src/main/resources/static/openapi.yaml
- Swagger Editor에서 https://editor.swagger.io/ → File > Import File 해서 openapi.yaml 열기

## 요구사항 정의
### 기능 요구사항 : https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1#G1BEbC8FKn5wVf5uqAclIm7Bn2417lty31

1. 사용자 정보
- 이름, 회원 여부, ID, 비밀번호, 전화번호로 구성
- 회원 여부와 상관없이 **모든 사용자(회원/비회원)** 는 주식 조회 가능

2. 주식 검색 조건
- 검색 조건: **주식 코드** 또는 **회사 이름**
- 주식 코드: **6자리 숫자**
- 회사 이름: **한글 또는 영어**, 최대 **30자 이내**
- 주식 코드와 회사 이름은 **2자 이상 부분 검색 가능** (`LIKE` 검색)

3. 주식 정보 조회
- 회원/비회원 모두 **주식의 시세, 이름, 주식 코드 확인 가능**
- **국내 주식만** 서비스 제공
- 시세는 **KRW(원화)** 기준으로 관리
- 주식 코드와 주식명은 앞서 설명한 규칙과 동일

4. 회원/비회원 기능 차이
- 비회원: **매수/매도 불가 (조회만 가능)**
- 회원: **계좌를 통해 주식 매수/매도 가능**

5. 주식 매매 종류
- 실시간: 주문 처리 및 체결
- 예약: 특정 금액 또는 시간 기준 매매 요청  
✅ **현재는 실시간 주문 기능만 제공**

6. API 스펙
- 주식 **매수/매도 API 제공** (`order_id`)
- 주식 **매수/매도 상태 조회 API 제공** (`order_id`)
- 주식 **매수/매도 체결 여부 확인 API 제공**

7. 매수/매도 조건 및 예외 처리
- 매수 시:
  - **잔고 부족** → 거래 실패
- 매도 시:
  - **보유 주식 수 부족** → 거래 실패
- 거래 체결 시 → **성공 코드 반환**

### 비기능 요구사항 :

성능 
API 응답시간은 조회성 API 는 200ms 이내, 처리성 api는 500ms 이내로 한다.
조회성 : 회원여부 판단, 주식 검색 및 정보확인
처리성 : 매매 요청

QPS 는 1300 QPS 이상으로 한다.
 
## 다이어그램

### 시퀀스 다이어그램
- 주식 매매 api : https://www.plantuml.com/plantuml/uml/dPDTIy9W5CVlzodEdNLHpkZAY_0JL51TT2auA325ERleoc83QLu8RJHj6vchK1Ru8hAelj3EsNTeJsxGoa8TR8FdS7x__tbvSXKfBqhLio97LowrUsGtOMC7gFjEgkKF1sWRf9fqxGIAcOsE0PyTiXMWjaQZ5zZVEpY4z7acNHOBWbZXvkR-0mtQ3RfhqgsBqne4BPqiZyiNfSIvFwX1QZV5GVZymMS2SGbPsEOpS5JALwMJidXw8HHW3L_bY6WhgDPHTTOveLWHVkiSCvs5sc4Rs7_3fXatXZuql36Znd0BM-L_vED88rC6CXnyD5RqmieAyx2lh6edWOYjB3jSVopJGmF8raZlnDC5OFDDXCYQ9DSgq4tmlNeOml44LGVe_fAinZ8Rm7yHwqQW6xut85Dcp6zoqnfrXe7Ucst3PeXc-n3-bYup4c9HT3sWaO5Fxk9MjtX-BW6e9MDtLZDpLofmxCs5d_36VW80
- 주식 매도 api : https://www.plantuml.com/plantuml/uml/dLFVIi9W5B_VKvmxwwASqPKNuHDKK5rqAJWeC8Kvk-ZAOe8q8I6bQP_3S1NAWcaXHlL2EsVlq1bE8RLm2MxZV3k_F-TtbYfeQLKhdcKbkdAerIEh1XixGDrFCjhUm4NB94FGjUsNHJ8i0pxRUAC3jUeqV87zlOD3Y9ydudarewW5QMdl7w1-gqhtDRfpS5G2lzcWzbXAPxKVTSyjGMmt9W7_Vi5dWfc596pB2JZAfOlQILuzlL0oi8RlUeXebT6ee66lIqgse2po70SyAut3Ch7_WJKn7GnzrV5MX8EHLfOgp_NZi4v21p9jV3J_guLjSH_soz7bB4BCf4GD5r_VI7Hc_YhcD640pdZnc0EOMo_F5Lpd5paneCubjQjHra0EuVo6wOrT4lmWvlb69NeO-8tUP2qs6JvO3Bv5zXkI1QVeZ8669ZuvgylTakMb241D7RiJtu6wN4OA_Yc-yAVx3G00
- 주식 매매 체결 : https://www.plantuml.com/plantuml/uml/SoWkIImgAStDuNhJjUJDdeOtIxUydhz6GTTEuStYFM-wvxxQkE7rl1blkkS0fT-sph622YkysRJbrQOD2a3yQlCUXJVJMr_tRdWpTylRhXqARsPDURDfhKA0Vt28WdwPeNvgHMfoQKbcVXuNLYD1ThauMIda5wMa5YcyRUq16lDwnnI5Dmitl9tLWrzFSKb-SZQHUd3x2QhjxTGv44VeGCs5g4FpwjkPKz_CtO7NML0T40qQO2FZYrABItDAD15kHGupFCOXnugLWf610J4HqBBnrUQjN0wfUId0Y0K0
- 주식 status 확인 : https://www.plantuml.com/plantuml/uml/SoWkIImgAStDuNhJjERDh9LlvarGqBLJUBDupvlkkQysRdWzRyQRxZblkfQyRPrZfV1w-P8t7JFqWTJh_XO5eDQIqcA5DxEclDcqLY70FpX4GR_CK1yYp8NL2ABDVhDfowjD6nI0-DNcFGflPqvzCtS7BYE1Pi8TRwLWP620rwmt2pUydTMZyAPbuwlDMxaSKlDIWBu70000

- ### 배포 다이어그램
- https://www.plantuml.com/plantuml/uml/SoWkIImgAStDuShBJqbLK7BEoKpDAr5IgEQgvO8AlLcyvqttNCS0JwLgBWKWICxFBSZF0we2IxrUiqC9NJZ8nGb3p3anEIDL2SdKcJjURsjGU3Dzm-l-cK33eLR27Q2hQuTiDHVKBh04s0PK8QW5Q8AOjd0vfEQb0BqB0000

## 데이터 모델링

### 개념적 모델링 PlantUml
https://www.plantuml.com/plantuml/uml/PP3H3S8m34J_FKKMo0SRT09MG55oeX6LKzck14Bi3WcK5FhdoplxJXdKWbWSmKHWotPpYqBSdHwhLYlnidqeaduT9Y5bFfy4kapMY60AbWlhc6SN1Kti2QvP3HonHtDHXUfBLjHA-OF_tg9ry4V_RaJKcbPNlqizOMFyArmtUXpriOBFoW1EzPkU

## 로컬 실행 방법

**로컬 환경에 따라 user/password/schema 세팅 필요**
- application.properties 파일 수정
spring.datasource.url=jdbc:mysql://localhost:3306/{{db명}}?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password={{local 계정 pw}}

**DB 초기 스키마**
<pre><code>
CREATE DATABASE stock_db;
USE stock_db;

CREATE TABLE User (
    user_id     VARCHAR(255) PRIMARY KEY,
    user_name   VARCHAR(255),
    create_date_time DATETIME,
    update_date_time DATETIME
);

CREATE TABLE Account (
    account_number   VARCHAR(255) PRIMARY KEY,
    user_id          VARCHAR(255),
    account_bank     VARCHAR(255),
    real_balance     BIGINT,
    active_balance   BIGINT,
    create_date_time DATETIME,
    update_date_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Stock (
    stock_id     VARCHAR(255) PRIMARY KEY,
    stock_name   VARCHAR(255),
    stock_price  BIGINT,
    create_date_time DATETIME,
    update_date_time DATETIME
);

CREATE TABLE Order_ (
    order_id        BIGINT PRIMARY KEY,
    stock_id        VARCHAR(255),
    user_id         VARCHAR(255),
    order_type      INT,
    quantity        BIGINT,
    order_stock     VARCHAR(255),
    trading_result  INT,
    create_date_time DATETIME,
    update_date_time DATETIME,
    FOREIGN KEY (stock_id) REFERENCES Stock(stock_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Amount_of_stock (
    user_id          VARCHAR(255),
    stock_id         VARCHAR(255),
    buy_stock_amount BIGINT,
    buy_stock_price  BIGINT,
    active_amount    BIGINT,
    create_date_time DATETIME,
    update_date_time DATETIME,
    PRIMARY KEY (user_id, stock_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (stock_id) REFERENCES Stock(stock_id)
);

UPDATE account SET version = 0 WHERE version IS NULL LIMIT 1000;
ALTER TABLE Account MODIFY version BIGINT NOT NULL DEFAULT 0;
</code></pre>

## 프로젝트 구조

stockApi
- -📂 controller     # REST API 컨트롤러
- -📂 dto            # 요청 및 응답 객체
- -📂 entity         # JPA 엔티티 클래스
- -📂 enums          # enum 설정
- -📂 mapper         # Entity 리스트를 Dto로 변환
- -📂 repository     # JPA 인터페이스
- -📂 service        # 서비스 인터페이스
- -📂 serviceImpl    # 서비스 로직 구현


