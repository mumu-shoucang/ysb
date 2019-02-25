
create database fcr DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

create table NN_RESOURCE
(
  ID              INT UNSIGNED AUTO_INCREMENT,
  RESOURCE_CODE   varchar(32) not null,
  RESOURCE_NAME   varchar(32) not null,
  RESOURCE_URL    varchar(512) not null,
  RESOURCE_STATUS varchar(1) not null,
  PARENT_CODE     varchar(32),
  primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table NN_RESOURCE add unique(RESOURCE_CODE);
alter table NN_RESOURCE add unique(RESOURCE_NAME);

create table NN_ROLE
(
  ID          INT UNSIGNED AUTO_INCREMENT,
  ROLE_CODE   varchar(32) not null,
  ROLE_NAME   varchar(32) not null,
  ROLE_STATUS varchar(1) not null,
  primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table NN_ROLE add unique(ROLE_NAME);
alter table NN_ROLE add unique(ROLE_CODE);


create table NN_ROLE_RESOURCE
(
  ID            INT UNSIGNED AUTO_INCREMENT,
  ROLE_CODE     varchar(32) not null,
  RESOURCE_CODE varchar(32) not null,
  primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table NN_USER
(
  ID          INT UNSIGNED AUTO_INCREMENT,
  USER_CODE   varchar(32) not null,
  USER_NAME   varchar(32) not null,
  LOGIN_NAME  varchar(32) not null,
  LOGIN_PWD   varchar(32) not null,
  USER_SALT   varchar(32) not null,
  USER_STATUS varchar(1) not null,
  CREATE_DATE DATETIME not null,
  primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table NN_USER add unique(USER_CODE);
alter table NN_USER add unique(LOGIN_NAME);


create table NN_USER_ROLE
(
  ID        INT UNSIGNED AUTO_INCREMENT,
  USER_CODE varchar(32) not null,
  ROLE_CODE varchar(32) not null,
  primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
