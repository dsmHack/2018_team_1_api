create table user (
  USER_GUID CHAR(36) NOT NULL,
  USER_ID VARCHAR(36) NOT NULL,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL,
  ROLE VARCHAR(50) NOT NULL,
  PRIMARY KEY (USER_GUID));

create table project (
  PROJ_GUID CHAR(36) NOT NULL,
  PROJ_ID VARCHAR(20) NOT NULL,
  ORG_GUID CHAR(36) NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  DESCRIPTION VARCHAR(250) NOT NULL,
  START_DT TIMESTAMP NOT NULL,
  END_DT TIMESTAMP null default null,
  CONSTRAINT PRJ_ORG_FK FOREIGN KEY (ORG_GUID) REFERENCES ORGANIZATION (ORG_GUID),
  PRIMARY KEY (PROJ_GUID));

create table user_org (
  ORG_GUID CHAR(36) NOT NULL,
  USER_GUID CHAR(36) NOT NULL,
  CONSTRAINT USR_ORG_U_FK FOREIGN KEY (USER_GUID) REFERENCES USER (USER_GUID),
  CONSTRAINT USR_ORG_O_FK FOREIGN KEY (ORG_GUID) REFERENCES ORGANIZATION (ORG_GUID),
  PRIMARY KEY (ORG_GUID,USER_GUID));

create table user_project (
  PROJ_GUID CHAR(36) NOT NULL,
  USER_GUID CHAR(36) NOT NULL,
  CONSTRAINT USR_PRJ_U_FK FOREIGN KEY (USER_GUID) REFERENCES USER (USER_GUID),
  CONSTRAINT USR_PRJ_P_FK FOREIGN KEY (PROJ_GUID) REFERENCES PROJECT (PROJ_GUID),
  PRIMARY KEY (PROJ_GUID,USER_GUID));

create table login_token (
  USER_GUID CHAR(36) NOT NULL,
  TOKEN CHAR(6) NOT NULL,
  TOKEN_EXP_DATE TIMESTAMP NOT NULL,
  CONSTRAINT login_user_FK FOREIGN KEY (USER_GUID) REFERENCES user (USER_GUID),
  PRIMARY KEY (USER_GUID,TOKEN,TOKEN_EXP_DATE));

create table check_in (
  USER_GUID CHAR(36) NOT NULL,
  PROJ_GUID CHAR(20) NOT NULL,
  TIME_IN TIMESTAMP NOT NULL,
  TIME_OUT TIMESTAMP null DEFAULT null,
  PRIMARY KEY (USER_GUID,PROJ_GUID,TIME_IN));
