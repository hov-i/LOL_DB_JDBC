# LOL_db_jdbc
### 🎮 전체 절차

1. **사용자 요구 사항 분석**
- 기능을 분석하고 구현 범위를 결정 한다.
1. **테이블 설계**

- 기본 테이블을 설계하고 제약 조건을 설정한다.
1. **필요한 기능에 대한 쿼리문을 작성한다.**
2. **쿼리문에 대한 가이드 문서(산출물을 작성 한다.)**

---

### 🎮 구현 범위 정리

1. 회원가입
2. 로그인
3. 상점/게임시작
4. 챔피언 선택/챔피언 구매
5. 스킨 선택/스킨 구매
6. 게임 끝

---

### 🎮 시스템 흐름도

![Untitled](https://user-images.githubusercontent.com/72931375/228586521-20d603a9-a921-41f2-b26b-fc1c7209161c.png)

---

### 🎮발표자료

- 발표자료
    
    [DB설계 LOL VER2.pdf](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ca1ac4c6-ea32-42e1-804f-e424a6ae6f70/DB%EC%84%A4%EA%B3%84_LOL_VER2.pdf)
---

### 🎮 테이블 구현

- 회원정보 테이블(USER_INFO)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | ID | 아이디 | VARCHAR2(20) | PRIMARY KEY |
    | PWD | 비밀번호 | VARCHAR2(20) | NOT NULL, 대문자/특수문자 1개 이상, 8~20 글자 |
    | EMAIL | 이메일 | VARCHAR2(20) |  |
    | NICKNAME | 닉네임 | VARCHAR2(20) | NOT NULL |
    | BRITH_DAY | 생년월일 | DATE | CHECK(BRITH_DAY > ‘11’) |
    | PH_NUMBER | 전화번호 | NUBMER |  |
    | GOLD | 보유 정수 | NUMBER | DEFAULT 10000 |
    | BUY_GOLD | 구매액 | NUMBER | DEFAULT 0 |
    | RANK | 랭크 | VARCHAR2(20) | DEFAULT ‘UNRANK’ |
    | POINT | 랭크점수 | NUMBER | DEFAULT 1000 |
    |  |  |  |  |
    
    ```sql
    -- 회원정보 테이블 생성
    CREATE TABLE USER_INFO(
    	ID			VARCHAR2(20) PRIMARY KEY,
    	PWD			VARCHAR2(20) NOT NULL, CHECK (REGEXP_COUNT(PWD, '[A-Z]') >= 1
                                AND REGEXP_COUNT(PWD, '[!@#$%^&*]') >= 1
                                AND LENGTH (PWD) >= 8
                                AND LENGTH(PWD) <= 20),
    	EMAIL		VARCHAR2(20),
    	NICKNAME	VARCHAR2(20) NOT NULL,
    	BIRTH_DAY	DATE CHECK (BIRTH_DAY < TO_DATE('2011/01/01', 'YYYY/MM/DD')),
    	PH_NUMBER	NUMBER, 
    	GOLD		NUMBER DEFAULT 10000,
    	BUY_GOLD	NUMBER DEFAULT 0, 
    	RANK	VARCHAR2(20) DEFAULT 'UNRANK',
    	POINT NUMBER DEFAULT 1000
    );
    
    -- 회원정보 생성
    INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) 
    	VALUES ('Faker', 'dltkdgur','Faker@T1.com', 'T1_Faker', '1996/05/07', '', '10000', '', 'CHALLENGER' );
    INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) 
    	VALUES ('ShowMaker', 'gjtn','ShowMaker@DK.com', 'DK_ShowMaker', '2000/07/22', '', '20000', '', 'GRANDMASTER' );
    ```
    
- 챔피언 테이블 (CHAMPION)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | CHP_NAME | 챔피언 이름 | VARCHAR2(20) | PRIMARY KEY |
    | CHP_PRICE | 챔피언 가격 | NUMBER | NOT NULL |
    | POSITION | 포지션 | VARCHAR2(20) |  |
    | LANE | 라인 | VARCHAR2(20) |  |
    
    ```sql
    CREATE TABLE CHAMPION(
    CHP_NAME VARCHAR2(20) PRIMARY KEY,
    CHP_PRICE NUMBER NOT NULL,
    POSITION VARCHAR2(20),
    LANE VARCHAR2(20)
    );
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('가렌','0','WARRIOR','TOP');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('아무무','0','TANKER','JUNGLE');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('라이즈','0','WIZARD','MID');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('애쉬','0','DEALER','AD');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('블리츠크랭크','0','TANKER','SUPPORTER');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('잭스','1350','WARRIOR','TOP');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('갱플랭크','3150','WARRIOR','TOP');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('마오카이','4800','TANKER','JUNGLE');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('자르반4세','4800','WARRIOR','JUNGLE');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE
    )VALUES('애니','450','WIZARD','MID');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('아우렐리온솔','4800','WIZARD','MID');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('자야','6300','DEALER','AD');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('진','4800','DEALER','AD');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('라칸','6300','WIZARD','SUPPORTER');
    INSERT INTO CHAMPION(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('노틸러스','4800','TANKER','SUPPORTER');
    ```
    
- 사용자 챔피언 구매 테이블(CHAMPION_BUY)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | CHPNO | 챔피언 번호 | NUMBER | PRIMARY KEY |
    | ID | 아이디 | VARCHAR2(20) |  |
    | CHP_NAME | 챔피언 이름 | VARCHAR2(20) | FOREIGN KEY |
    | CHP_PRICE | 챔피언 가격 | NUMBER | NOT NULL |
    | POSITION | 포지션 | VARCHAR2(20) |  |
    | LANE | 라인 | VARCHAR2(20) |  |
    
    ```sql
    CREATE TABLE CHAMPION_BUY(
    CHPNO NUMBER PRIMARY KEY,
    ID VARCHAR2(20),
    CHP_NAME VARCHAR2(40) UNIQUE,
    CHP_PRICE NUMBER NOT NULL,
    POSITION VARCHAR2(20),
    LANE VARCHAR2(20)
    );
    
    INSERT INTO CHAMPION_BUY(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('가렌','0','WARRIOR','TOP');
    INSERT INTO CHAMPION_BUY(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('아무무','0','TANKER','JUNGLE');
    INSERT INTO CHAMPION_BUY(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('라이즈','0','WIZARD','MID');
    INSERT INTO CHAMPION_BUY(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('애쉬','0','DEALER','AD');
    INSERT INTO CHAMPION_BUY(CHP_NAME,CHP_PRICE,POSITION,LANE)
    VALUES('블리츠크랭크','0','TANKER','SUPPORTER');
    
    -- 시퀀스 추가
    CREATE SEQUENCE SEQ_CHPSEQ
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    
        INSERT INTO CHAMPION_BUY(CHPNO,ID,CHP_NAME,CHP_PRICE,POSITION,LANE)
        VALUES(SEQ_CHPSEQ.NEXTVAL,'I','가렌','0','WARRIOR','TOP');
    ```
    
- 스킨 테이블(SKIN)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | CHP_NAME | 챔피언 이름 | VARCHAR2(20) | FOREIGN KEY |
    | SK_NAME | 스킨 이름 | VARCHAR2(40) | PRIMARY KEY |
    | SK_PRICE | 스킨 가격 | NUMBER |  |
    
    ```sql
    CREATE TABLE SKIN (	
    	CHP_NAME VARCHAR2(20) REFERENCES CHAMPION (CHP_NAME),
    	SK_NAME VARCHAR2(40) PRIMARY KEY,
    	SK_PRICE NUMBER
    );
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('가렌','핏빛 가렌',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('가렌','메타 삼국 가렌',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('가렌','흉포한 제독 가렌',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아무무','파라오 아무무',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아무무','작은 기사 아무무',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아무무','도자기 아무무',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라이즈','제사장 라이즈',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라이즈','백색의 라이즈',1350);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라이즈','사막의 수호자 라이즈',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애쉬','로빈훗 애쉬',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애쉬','정령용 애쉬',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애쉬','습격자 애쉬',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('잭스','골기퍼 잭스',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('잭스','마녀 가마솥 잭스',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('잭스','중간보스 잭스',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('갱플랭크','으스스한 갱플랭크',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('갱플랭크','수영장 갱플랭크',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('갱플랭크','술탄 갱플랭크',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('마오카이','토템 마오카이',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('마오카이','우주비행사 가마솥 마오카이',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('마오카이','냐옹카이',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자르반4세','대장군 자르반4세',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자르반4세','삼성 갤럭시 자르반4세',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자르반4세','수영장 파티 자르반4세',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애니','티버 애니',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애니','공포의 밤 애니',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('애니','얼음불꽃 애니',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아우렐리온솔','잿빛 군주 아우렐리온 솔',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아우렐리온솔','메카 아우렐리온 솔',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('아우렐리온솔','폭풍용 아우렐리온 솔',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자야','황혼 우주 자야',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자야','사랑의 자야',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('자야','별 수호자 자야',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('진','불의 축제 진',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('진','창공 진', 750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('진','암흑 우주 진',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라칸','사랑의 라칸',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라칸','별 수호자 라칸',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('라칸','나무정령 라칸',1350);
    
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('노틸러스','아틀란티스 노틸러스',520);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('노틸러스','우주비행사 노틸러스',750);
    INSERT INTO SKIN(CHP_NAME,SK_NAME,SK_PRICE)VALUES('노틸러스','공포의 밤 노틸러스',1350);
    ```
    
- 사용자 스킨 구매 테이블(SKIN_BUY)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | SKINNO | 스킨번호 | NUMBER | PRIMARY KEY |
    | ID | 아이디 | VARCHAR2(20) |  |
    | CHP_NAME | 챔피언 이름 | VARCHAR2(20) | FOREIGN KEY |
    | SK_NAME | 스킨 이름 | VARCHAR2(40) | UNIQUE |
    | SK_PRICE | 스킨 가격 | NUMBER |  |
    
    ```sql
    CREATE TABLE SKIN_BUY (	
    	SKNO NUMBER PRIMARY KEY,
    	ID VARCHAR2(40),
    	CHP_NAME VARCHAR2(40) REFERENCES CHAMPION (CHP_NAME),
    	SK_NAME VARCHAR2(40),
    	SK_PRICE NUMBER
    );
    
    -- 시퀀스 추가
    
    CREATE SEQUENCE S_SKSEQ
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    ```
    
- 구매내역(PURCHASE_DETAILS)
    
    
    | 이름 | 역할 | 자료형 | 제약조건 |
    | --- | --- | --- | --- |
    | PURNO | 구매번호 | NUMBER | PRIMARY KEY |
    | ID | 아이디 | VARCHAR2(20) |  |
    | PR_NAME | 상품이름 | VARCHAR2(20) |  |
    | PR_TYPE | 유형 | VARCHAR2(20) |  |
    | PR_PRICE | 상품가격 | NUMBER |  |
    
    ```sql
    CREATE TABLE PURCHASE_DETAILS(
    PDNO NUMBER PRIMARY KEY,
    ID VARCHAR(20),
    PR_NAME VARCHAR(20),
    PR_TYPE VARCHAR2(20),
    PR_PRICE NUMBER
    );
    
    -- 시퀀스 추가
    CREATE SEQUENCE S_PDSEQ
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    ```
    
- 챔피언 범위

기본 챔피언 5개 (라인별 1개씩) (가렌, 아무무, 라이즈, 애쉬, 블리츠크랭크)
상점 구매 10개 (라인별 2개씩)(잭스 갱플, 마오카이 자르반4세, 애니 아우렐리온 솔, 자야 진, 라칸, 노틸러스)

---
