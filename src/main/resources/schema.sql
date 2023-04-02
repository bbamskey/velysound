/* ACCOUNT */
DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT (
                         USER_NUM INT NOT NULL AUTO_INCREMENT,
                         EMAIL VARCHAR(50) NOT NULL UNIQUE,
                         NICKNAME VARCHAR(20) NOT NULL UNIQUE,
                         PASSWORD VARCHAR(200) NOT NULL,
                         AUTHORITY VARCHAR(20) NOT NULL,
                         JOIN_DT VARCHAR(8) DEFAULT DATE_FORMAT(NOW(), '%Y%m%d'),
                         AUTO_LOGIN VARCHAR(1) NOT NULL DEFAULT 'N',
                         SLEEP_STATUS VARCHAR(1) NOT NULL DEFAULT 'N',
                         DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                         REG_ID VARCHAR(30) DEFAULT NULL,
                         REG_DT DATETIME(6) DEFAULT NOW(),
                         ALT_ID VARCHAR(30),
                         ALT_DT DATETIME(6),
                         PRIMARY KEY (USER_NUM)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE ACCOUNT AUTO_INCREMENT = 100;
/* //ACCOUNT */


/* REFRESH_TOKEN */
DROP TABLE IF EXISTS REFRESH_TOKEN;
CREATE TABLE REFRESH_TOKEN (
                               RT_KEY VARCHAR(255) NOT NULL,
                               RT_VALUE VARCHAR(255) DEFAULT NULL,
                               ALT_ID VARCHAR(30),
                               ALT_DT DATETIME(6),
                               PRIMARY KEY (RT_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //REFRESH_TOKEN */


/* VIDEO */
DROP TABLE IF EXISTS VIDEO;
CREATE TABLE VIDEO (
                       VIDEO_ID INT NOT NULL AUTO_INCREMENT,
                       CATEGORY_CODE VARCHAR(3) NOT NULL,
                       DISPLAY_TYPE VARCHAR(20) NOT NULL DEFAULT 'V',
                       VIDEO_TITLE VARCHAR(100) NOT NULL,
                       VIDEO_LINK VARCHAR(100) NOT NULL,
                       VIDEO_THUMB_PATH VARCHAR(200),
                       DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                       SHEET_ID INT,
                       REG_ID VARCHAR(30) DEFAULT NULL,
                       REG_DT DATETIME(6) DEFAULT NOW(),
                       ALT_ID VARCHAR(30),
                       ALT_DT DATETIME(6),
                       PRIMARY KEY (VIDEO_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //VIDEO */


/* SHEET */
DROP TABLE IF EXISTS SHEET;
CREATE TABLE SHEET (
                       SHEET_ID INT NOT NULL AUTO_INCREMENT,
                       CATEGORY_CODE VARCHAR(3) NOT NULL,
                       DISPLAY_TYPE VARCHAR(20) NOT NULL DEFAULT 'S',
                       SHEET_TITLE VARCHAR(100) NOT NULL,
                       SHEET_CONTENT VARCHAR(4000),
                       SHEET_ORIGINAL_KEY VARCHAR(2) NOT NULL,
                       SHEET_KEYS VARCHAR(100) NOT NULL,
                       SHEET_PAGE INT DEFAULT 1,
                       SHEET_PRICE INT NOT NULL,
                       SHEET_TAGS VARCHAR(300),
                       SHEET_VIEW_COUNT INT DEFAULT 0,
                       VIDEO_ID INT,
                       DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                       REG_ID VARCHAR(30) DEFAULT NULL,
                       REG_DT DATETIME(6) DEFAULT NOW(),
                       ALT_ID VARCHAR(30),
                       ALT_DT DATETIME(6),
                       PRIMARY KEY (SHEET_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //SHEET */


/* SHEET_IMAGE */
DROP TABLE IF EXISTS SHEET_IMAGE;
CREATE TABLE SHEET_IMAGE (
                             SHEET_ID INT NOT NULL,
                             IMAGE_SORT INT NOT NULL,
                             IMAGE_PATH VARCHAR(200) NOT NULL,
                             IMAGE_MAIN VARCHAR(1) NOT NULL DEFAULT 'N',
                             DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                             REG_ID VARCHAR(30) DEFAULT NULL,
                             REG_DT DATETIME(6) DEFAULT NOW(),
                             ALT_ID VARCHAR(30),
                             ALT_DT DATETIME(6),
                             PRIMARY KEY (SHEET_ID, IMAGE_SORT)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //SHEET_IMAGE */


/* ORDER */
DROP TABLE IF EXISTS CART;
CREATE TABLE CART (
                      USER_NUM INT NOT NULL,
                      SHEET_ID INT NOT NULL,
                      SHEET_KEY VARCHAR(2) NOT NULL,
                      CART_COUNT INT NOT NULL DEFAULT 1,
                      REG_ID VARCHAR(30) DEFAULT NULL,
                      REG_DT DATETIME(6) DEFAULT NOW(),
                      ALT_ID VARCHAR(30),
                      ALT_DT DATETIME(6),
                      PRIMARY KEY (USER_NUM, SHEET_ID, SHEET_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //ORDER */


/* ORDER */
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
                        ORDER_ID INT NOT NULL AUTO_INCREMENT,
                        USER_NUM INT NOT NULL,
                        ORDER_PRICE INT NOT NULL,
                        ORDER_DATE DATETIME(6) DEFAULT NOW(),
                        ORDER_LIMIT_DATE DATETIME(6) DEFAULT DATE_ADD(NOW(), INTERVAL+3 MONTH),
                        DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                        REG_ID VARCHAR(30) DEFAULT NULL,
                        REG_DT DATETIME(6) DEFAULT NOW(),
                        ALT_ID VARCHAR(30),
                        ALT_DT DATETIME(6),
                        PRIMARY KEY (ORDER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //ORDER */


/* ORDER_DETAIL */
DROP TABLE IF EXISTS ORDER_DETAIL;
CREATE TABLE ORDER_DETAIL (
                              ORDER_ID INT NOT NULL,
                              SHEET_ID INT NOT NULL,
                              SHEET_KEY VARCHAR(2) NOT NULL,
                              SHEET_PRICE INT NOT NULL,
                              DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                              REG_ID VARCHAR(30) DEFAULT NULL,
                              REG_DT DATETIME(6) DEFAULT NOW(),
                              ALT_ID VARCHAR(30),
                              ALT_DT DATETIME(6),
                              PRIMARY KEY (ORDER_ID, SHEET_ID, SHEET_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //ORDER_DETAIL */


/* PDF */
DROP TABLE IF EXISTS PDF;
CREATE TABLE PDF (
                     PDF_ID INT NOT NULL AUTO_INCREMENT,
                     SHEET_ID INT NOT NULL,
                     SHEET_KEY VARCHAR(2) NOT NULL,
                     PDF_PATH VARCHAR(200) NOT NULL,
                     PDF_DOWN_COUNT INT DEFAULT 0,
                     PDF_LAST_DOWN_DT DATETIME(6),
                     DELETE_YN VARCHAR(1) NOT NULL DEFAULT 'N',
                     REG_ID VARCHAR(30) DEFAULT NULL,
                     REG_DT DATETIME(6) DEFAULT NOW(),
                     ALT_ID VARCHAR(30),
                     ALT_DT DATETIME(6),
                     PRIMARY KEY (PDF_ID, SHEET_ID, SHEET_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //PDF */


/* HISTORY */
DROP TABLE IF EXISTS HISTORY;
CREATE TABLE HISTORY (
                         HISTORY_ID INT NOT NULL AUTO_INCREMENT,
                         USER_NUM INT NOT NULL,
                         HISTORY_TYPE VARCHAR(10) NOT NULL,
                         HISTORY_CONTENT VARCHAR(4000) NOT NULL,
                         REG_ID VARCHAR(30) DEFAULT NULL,
                         REG_DT DATETIME(6) DEFAULT NOW(),
                         PRIMARY KEY (HISTORY_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/* //HISTORY */