/* system 계정 */
-- jsp 계정 생성
CREATE USER JSP_Pjt_Sonjh IDENTIFIED BY tiger DEFAULT TABLESPACE USERS;

-- 권한 주기
GRANT CONNECT, RESOURCE TO JSP_Pjt_Sonjh;

-- 락 해제
ALTER USER JSP_Pjt_Sonjh ACCOUNT UNLOCK;


--------------------------------------------------------
--  파일이 생성됨 - 목요일-12월-06-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------

  CREATE TABLE "MEMBER" 
   (	"ID" VARCHAR2(32 BYTE), 
	"PWD" VARCHAR2(32 BYTE), 
	"NAME" VARCHAR2(10 BYTE), 
	"EMAIL" VARCHAR2(32 BYTE), 
	"PHONE" VARCHAR2(30 BYTE), 
	"ADDRESS" VARCHAR2(100 BYTE), 
	"REG_DATE" TIMESTAMP (6) DEFAULT sysdate, 
	"AUTH" CHAR(1 BYTE) DEFAULT 'G', 
	"MEMBER_IMAGE" VARCHAR2(50 BYTE) DEFAULT 'default.jpg'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into JSP_PJT_SONJH.MEMBER
SET DEFINE OFF;
Insert into JSP_PJT_SONJH.MEMBER (ID,PWD,NAME,EMAIL,PHONE,ADDRESS,REG_DATE,AUTH,MEMBER_IMAGE) values ('admin','admin','TravelASIA','mail@travelasia.co.kr','02-6402-0819','서울시 금천구 가산동',to_timestamp('18/12/05 09:51:03.000000000','RR/MM/DD HH24:MI:SSXFF'),'H','admin.jpg');
Insert into JSP_PJT_SONJH.MEMBER (ID,PWD,NAME,EMAIL,PHONE,ADDRESS,REG_DATE,AUTH,MEMBER_IMAGE) values ('bong','1234','김봉식','bong@daum.net','010974971233',null,to_timestamp('18/12/06 09:45:46.509000000','RR/MM/DD HH24:MI:SSXFF'),'G','default.jpg');
--------------------------------------------------------
--  DDL for Index SYS_C007624
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007624" ON "MEMBER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "MEMBER" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "MEMBER" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("PWD" NOT NULL ENABLE);
  
  
  COMMIT;


SELECT * FROM member ORDER BY reg_date DESC;
SELECT * FROM member WHERE auth='H';
DESC member;

select * from invoice order by ref desc;

DROP SEQUENCE product_seq;
CREATE SEQUENCE product_seq;
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999;
    
DROP SEQUENCE notice_seq;
CREATE SEQUENCE notice_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999;
    
DROP SEQUENCE invoice_seq;
CREATE SEQUENCE invoice_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999;
    
SELECT * FROM user_sequences;
select * from invoice order by reg_date desc;

delete invoice;
INSERT INTO invoice(no,name,pwd,subject,content,readCnt,ref,ref_step,reg_date) VALUES (invoice_seq.nextval,'김봉식','1234','문의1','내용1',0,0,0,sysdate);
commit;