
DROP TABLE IF EXISTS T_FT_CATEGORY;

DROP TABLE IF EXISTS T_FT_IMG_LIST;

DROP TABLE IF EXISTS T_FT_SETMEAL;

DROP TABLE IF EXISTS T_FT_REVIEWS_IMG_LIST;

DROP TABLE IF EXISTS T_FT_REVIEWS;

DROP TABLE IF EXISTS T_FT_FRUIT;

DROP TABLE IF EXISTS T_FT_THEME;

DROP TABLE IF EXISTS T_FT_ORDER_LIST;

DROP TABLE IF EXISTS T_FT_ORDER_SNAPSHOT;

DROP TABLE IF EXISTS T_FT_ORDER;

DROP TABLE IF EXISTS T_FT_PARAMS;

DROP TABLE IF EXISTS T_FT_NOTICE;

DROP TABLE IF EXISTS T_FT_USER_ADDRESS;

DROP TABLE IF EXISTS T_FT_USER_SEARCH_KEYWORD_RECORD;

DROP TABLE IF EXISTS T_FT_USER_INFO;

/*==============================================================*/
/* Table: T_FT_CATEGORY                                         */
/*==============================================================*/
CREATE TABLE T_FT_CATEGORY
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   PARENTID                       INT,
   NAME                           VARCHAR(100)                   NOT NULL,
   CODE                           VARCHAR(30)                    NOT NULL,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
)COMMENT = '基础类型：配送方式


          配送方式 DM01

                     同城配送¥0.00  DM0101
                     到店取货¥0.00   DM0102

          ';

/*==============================================================*/
/* Table: T_FT_FRUIT                                            */
/*==============================================================*/
CREATE TABLE T_FT_FRUIT
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   THEMEID                        INT                            NOT NULL,
   THEMENAME                      VARCHAR(32)                    NOT NULL,
   NAME                           VARCHAR(30)                    NOT NULL,
   ISSETMEAL                      CHAR(1),
   SETMEALNAME                    VARCHAR(30),
   TITLE                          VARCHAR(100)                   NOT NULL,
   DESCRIPTION                    TEXT,
   COVERURL                       TEXT,
   POSTER                         TEXT,
   POSTERSTATUS                   CHAR(1)                        NOT NULL DEFAULT '0',
   OLDPRICE                       NUMERIC(10,2)                  NOT NULL,
   PRICE                          NUMERIC(10,2)                  NOT NULL,
   STOCK                          INT                            DEFAULT 0,
   COUNT                          INT                            DEFAULT 0,
   STATUS                         CHAR(1)                        NOT NULL DEFAULT '0',
   FLAG                           CHAR(1)                        NOT NULL,
   SEARCH_COUNT                   INT                            DEFAULT 0,
   CREATER                        INT                            NOT NULL,
   CREATERNAME                    VARCHAR(100),
   CREATERTIME                    DATETIME                       NOT NULL,
   MODIFY                         INT,
   MODIFYNAME                     VARCHAR(100),
   MODIFYTIME                     DATETIME,
   REMARK                         VARCHAR(100),
   EXTEND1                        TEXT,
   EXTEND2                        TEXT,
   EXTEND3                        TEXT,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_11_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_11_FK
(
   themeid
);*/

/*==============================================================*/
/* Table: T_FT_IMG_LIST                                         */
/*==============================================================*/
CREATE TABLE T_FT_IMG_LIST
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   FRUITID                        INT                            NOT NULL,
   SEQNO                          INT,
   ISTOP                          CHAR(1)                        NOT NULL DEFAULT '0',
   STATUS                         CHAR(1)                        NOT NULL DEFAULT '0',
   IMGPATH                        TEXT                           NOT NULL,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
)COMMENT = '表示商品详情信息，是以图片列表方式显示，无文字描述

          滚图规格：

          列表图规格：';

/*==============================================================*/
/* INDEX: "Reference_6_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_6_FK
(
   fruitid
);*/

/*==============================================================*/
/* Table: T_FT_NOTICE                                           */
/*==============================================================*/
CREATE TABLE T_FT_NOTICE
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   TITLE                          VARCHAR(300)                   NOT NULL,
   CONTENT                        TEXT                           NOT NULL,
   COVERURL                       TEXT                           NOT NULL,
   STATUS                         CHAR(1)                        NOT NULL DEFAULT '0',
   SEQNO                          INT,
   RELEASETIME                    DATETIME,
   OPENUSERID                     TEXT,
   CREATER                        INT                            NOT NULL,
   CREATERNAME                    VARCHAR(100),
   CREATERTIME                    DATETIME                       NOT NULL,
   MODIFY                         INT,
   MODIFYNAME                     VARCHAR(100),
   MODIFYTIME                     DATETIME,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: T_FT_ORDER                                            */
/*==============================================================*/
CREATE TABLE T_FT_ORDER
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   USERID                         INT                            NOT NULL,
   USERNAME                       VARCHAR(100)                   NOT NULL,
   ORDERNO                        VARCHAR(32)                    NOT NULL,
   TRANSFERACCOUNT                VARCHAR(100),
   ORDERSTATUS                    CHAR(2)                        NOT NULL,
   LOGISTICSNAME                  VARCHAR(30),
   LOGISTICSCODE                  VARCHAR(50),
   USERADDRESSID                  INT                            NOT NULL,
   ADDRESSBACKUP                  TEXT                           NOT NULL,
   USERMSG                        VARCHAR(300),
   PAYMENTMETHOD                  CHAR(1)                        NOT NULL DEFAULT '0',
   ORDERTIME                      DATETIME                       NOT NULL,
   FINISHORDERTIME                DATETIME,
   DELIVERYTIME                   DATETIME,
   DELIVERYDISTANCE               VARCHAR(30)                    NOT NULL,
   DISTRIBUTIONMODECODE           VARCHAR(30)                    NOT NULL,
   COUNT                          INT                            NOT NULL DEFAULT 0,
   TOTALPRICE                     NUMERIC(10,2)                  NOT NULL,
   FREIGHT                        NUMERIC(10,2)                  NOT NULL,
   REMARK                         VARCHAR(100),
   EXTEND1                        TEXT,
   EXTEND2                        TEXT,
   EXTEND3                        TEXT,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_13_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_13_FK
(
   userid
);*/

/*==============================================================*/
/* Table: T_FT_ORDER_LIST                                       */
/*==============================================================*/
CREATE TABLE T_FT_ORDER_LIST
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   ORDERID                        INT                            NOT NULL,
   FRUITID                        INT                            NOT NULL,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_18_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_18_FK
(
   orderid
);*/
/*==============================================================*/
/* INDEX: "Reference_19_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_19_FK
(
   fruitid
);*/

/*==============================================================*/
/* Table: T_FT_ORDER_SNAPSHOT                                   */
/*==============================================================*/
CREATE TABLE T_FT_ORDER_SNAPSHOT
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   THEMEID                        INT,
   THEMECOVERURL                  TEXT,
   THEMENAME                      VARCHAR(30),
   SETMEALID                      INT,
   SETMEALCOVERURL                TEXT,
   SETMEALNAME                    VARCHAR(30),
   SETMEALDESC                    TEXT,
   SETMEALOLDPRICE                NUMERIC(10,2),
   SETMEALPRICE                   NUMERIC(10,2),
   FRUITID                        INT                            NOT NULL,
   NAME                           VARCHAR(30),
   COVERURL                       TEXT,
   TITLE                          VARCHAR(100),
   DESCRIPTION                    TEXT,
   OLDPRICE                       NUMERIC(10,2),
   PRICE                          NUMERIC(10,2),
   COUNT                          INT,
   REMARK                         VARCHAR(100),
   EXTEND1                        TEXT,
   EXTEND2                        TEXT,
   EXTEND3                        TEXT,
   PRIMARY KEY (ID)
)COMMENT = '永久保留用户的订单交易记录，以便后续核对用户订单详情！';

/*==============================================================*/
/* Table: T_FT_PARAMS                                           */
/*==============================================================*/
CREATE TABLE T_FT_PARAMS
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   NAME                           VARCHAR(100)                   NOT NULL,
   PKEY                           VARCHAR(30)                    NOT NULL,
   PVALUE                         VARCHAR(300)                   NOT NULL,
   REMARK                         TEXT,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: T_FT_REVIEWS                                          */
/*==============================================================*/
CREATE TABLE T_FT_REVIEWS
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   PARENTID                       INT,
   FRUITID                        INT                            NOT NULL,
   USERID                         INT                            NOT NULL,
   CONTENT                        VARCHAR(500)                   NOT NULL,
   DELSTATE                       CHAR(1)                        NOT NULL DEFAULT '0',
   CREATERTIME                    DATETIME                       NOT NULL,
   AUDITSTATE                     CHAR(1)                        NOT NULL DEFAULT '0',
   AUDITOR                        INT                            NOT NULL,
   AUDITORNAME                    VARCHAR(30),
   AUDITTIME                      DATETIME,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_7_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_7_FK
(
   fruitid
);*/
/*==============================================================*/
/* INDEX: "Reference_8_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_8_FK
(
   userid
);*/

/*==============================================================*/
/* Table: T_FT_REVIEWS_IMG_LIST                                 */
/*==============================================================*/
CREATE TABLE T_FT_REVIEWS_IMG_LIST
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   REVIEWSID                      INT                            NOT NULL,
   IMGPATHS                       TEXT                           NOT NULL,
   PRIMARY KEY (ID)
)COMMENT = '每位用户每一次第一次评论，限制5张，主评有图片，追评不能添加图片


          只能上传评价图片一次，所以上传的图片路径集合，以英文分号;分割';

/*==============================================================*/
/* INDEX: "Reference_16_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_16_FK
(
   reviewsid
);*/

/*==============================================================*/
/* Table: T_FT_SETMEAL                                          */
/*==============================================================*/
CREATE TABLE T_FT_SETMEAL
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   FRUITID                        INT                            NOT NULL,
   DESCRIPTION                    TEXT,
   OLDPRICE                       NUMERIC(10,2)                  NOT NULL,
   PRICE                          NUMERIC(10,2)                  NOT NULL,
   STATUS                         CHAR(1)                        NOT NULL DEFAULT '0',
   COUNT                          INT                            DEFAULT 0,
   CREATER                        INT                            NOT NULL,
   CREATERNAME                    VARCHAR(100),
   CREATERTIME                    DATETIME                       NOT NULL,
   MODIFY                         INT,
   MODIFYNAME                     VARCHAR(100),
   MODIFYTIME                     DATETIME,
   REMARK                         VARCHAR(100),
   EXTEND1                        TEXT,
   EXTEND2                        TEXT,
   EXTEND3                        TEXT,
   PRIMARY KEY (ID)
)COMMENT = '如果水果设置了套餐，将以套餐的价格为标准';

/*==============================================================*/
/* INDEX: "Reference_15_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_15_FK
(
   fruitid
);*/

/*==============================================================*/
/* Table: T_FT_THEME                                            */
/*==============================================================*/
CREATE TABLE T_FT_THEME
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   PARENTID                       INT,
   NAME                           VARCHAR(30)                    NOT NULL,
   DESCRIPTION                    TEXT,
   COVERURL                       TEXT                           NOT NULL,
   STATUS                         CHAR(1)                        NOT NULL DEFAULT '0',
   SEQNO                          INT,
   CREATER                        INT                            NOT NULL,
   CREATERNAME                    VARCHAR(100),
   CREATERTIME                    DATETIME                       NOT NULL,
   MODIFY                         INT,
   MODIFYNAME                     VARCHAR(100),
   MODIFYTIME                     DATETIME,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: T_FT_USER_ADDRESS                                     */
/*==============================================================*/
CREATE TABLE T_FT_USER_ADDRESS
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   USERID                         INT                            NOT NULL,
   NICKNAME                       TEXT                           NOT NULL,
   USERNAME                       TEXT                           NOT NULL,
   EMAIL                          TEXT,
   POSTALCODE                     TEXT                           NOT NULL,
   PROVINCENAME                   TEXT                           NOT NULL,
   CITYNAME                       TEXT                           NOT NULL,
   COUNTYNAME                     TEXT                           NOT NULL,
   DETAILINFO                     TEXT                           NOT NULL,
   NATIONALCODE                   TEXT                           NOT NULL,
   TELNUMBER                      TEXT                           NOT NULL,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_14_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_14_FK
(
   userid
);*/

/*==============================================================*/
/* Table: T_FT_USER_INFO                                        */
/*==============================================================*/
CREATE TABLE T_FT_USER_INFO
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   OPENID                         TEXT                           NOT NULL,
   UNIONID                        TEXT,
   NICKNAME                       TEXT                           NOT NULL,
   AVATARURL                      TEXT                           NOT NULL,
   GENDER                         CHAR(1)                        NOT NULL,
   ADDRESS                        TEXT,
   ISBLACKLIST                    CHAR(1)                        DEFAULT '0',
   TELEPHONE                      TEXT,
   CREATERTIME                    DATETIME                       NOT NULL,
   LASTLOGINTIME                  DATETIME                       NOT NULL,
   REMARK                         VARCHAR(100),
   PRIMARY KEY (ID)
)COMMENT = '微信获取用户信息';

/*==============================================================*/
/* Table: T_FT_USER_SEARCH_KEYWORD_RECORD                       */
/*==============================================================*/
CREATE TABLE T_FT_USER_SEARCH_KEYWORD_RECORD
(
   ID                             INT                            NOT NULL AUTO_INCREMENT,
   USERID                         INT                            NOT NULL,
   KEYWORD                        TEXT                           NOT NULL,
   CREATERTIME                    DATETIME                       NOT NULL,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* INDEX: "Reference_17_FK"                                            */
/*==============================================================*/
/* CREATE INDEX Reference_17_FK
(
   userid
);*/

ALTER TABLE T_FT_FRUIT ADD CONSTRAINT FK_REFERENCE_11 FOREIGN KEY (THEMEID)
      REFERENCES T_FT_THEME (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_IMG_LIST ADD CONSTRAINT FK_REFERENCE_6 FOREIGN KEY (FRUITID)
      REFERENCES T_FT_FRUIT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_ORDER ADD CONSTRAINT FK_REFERENCE_13 FOREIGN KEY (USERID)
      REFERENCES T_FT_USER_INFO (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_ORDER_LIST ADD CONSTRAINT FK_REFERENCE_18 FOREIGN KEY (ORDERID)
      REFERENCES T_FT_ORDER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_ORDER_LIST ADD CONSTRAINT FK_REFERENCE_19 FOREIGN KEY (FRUITID)
      REFERENCES T_FT_ORDER_SNAPSHOT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_REVIEWS ADD CONSTRAINT FK_REFERENCE_7 FOREIGN KEY (FRUITID)
      REFERENCES T_FT_FRUIT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_REVIEWS ADD CONSTRAINT FK_REFERENCE_8 FOREIGN KEY (USERID)
      REFERENCES T_FT_USER_INFO (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_REVIEWS_IMG_LIST ADD CONSTRAINT FK_REFERENCE_16 FOREIGN KEY (REVIEWSID)
      REFERENCES T_FT_REVIEWS (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_SETMEAL ADD CONSTRAINT FK_REFERENCE_15 FOREIGN KEY (FRUITID)
      REFERENCES T_FT_FRUIT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_USER_ADDRESS ADD CONSTRAINT FK_REFERENCE_14 FOREIGN KEY (USERID)
      REFERENCES T_FT_USER_INFO (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE T_FT_USER_SEARCH_KEYWORD_RECORD ADD CONSTRAINT FK_REFERENCE_17 FOREIGN KEY (USERID)
      REFERENCES T_FT_USER_INFO (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
