---------------------------------------------
-- Export file for user YSB                --
-- Created by NewNet on 2018/8/2, 17:55:38 --
---------------------------------------------

spool create_foreign.log

prompt
prompt Creating table NN_CALENDAR
prompt ==========================
prompt
create table NN_CALENDAR
(
  ID       VARCHAR2(32) default SYS_GUID() not null,
  CALENDAR TIMESTAMP(6),
  STATUS   VARCHAR2(6)
)
;
alter table NN_CALENDAR
  add primary key (ID);
alter table NN_CALENDAR
  add constraint CALENDAR unique (CALENDAR);

prompt
prompt Creating table NN_GLOBAL_CFG
prompt ============================
prompt
create table NN_GLOBAL_CFG
(
  ID                         VARCHAR2(32) default SYS_GUID() not null,
  SINGLE_LIMIT               NUMBER(10) not null,
  DAY_LIMIT                  NUMBER(10) not null,
  MONTH_LIMIT                NUMBER(10) not null,
  DAY_COUNT                  NUMBER(10) not null,
  MONTH_COUNT                NUMBER(10) not null,
  TRADE_FEE                  NUMBER(10,6) not null,
  T1_WITHDRAW_FEE            NUMBER(10,6) default 0 not null,
  T0_WITHDRAW_FEE            NUMBER(10,6) not null,
  T0_WITHDRAW_WORKDAY_FEE    NUMBER(10,6) default 0 not null,
  T0_WITHDRAW_NONWORKDAY_FEE NUMBER(10,6) default 0 not null
)
;
comment on column NN_GLOBAL_CFG.SINGLE_LIMIT
  is '商户单笔限额';
comment on column NN_GLOBAL_CFG.DAY_LIMIT
  is '商户日限额';
comment on column NN_GLOBAL_CFG.MONTH_LIMIT
  is '商户月限额';
comment on column NN_GLOBAL_CFG.DAY_COUNT
  is '商户日累积次数';
comment on column NN_GLOBAL_CFG.MONTH_COUNT
  is '商户月累积次数';
comment on column NN_GLOBAL_CFG.TRADE_FEE
  is '交易费率';
comment on column NN_GLOBAL_CFG.T1_WITHDRAW_FEE
  is 'T1 自助结算费率';
comment on column NN_GLOBAL_CFG.T0_WITHDRAW_FEE
  is 'T0 自助结算基本费率';
comment on column NN_GLOBAL_CFG.T0_WITHDRAW_WORKDAY_FEE
  is 'T0 自助结算工作日额外费率';
comment on column NN_GLOBAL_CFG.T0_WITHDRAW_NONWORKDAY_FEE
  is 'T0 自助结算非工作日额外费率';
alter table NN_GLOBAL_CFG
  add primary key (ID);

prompt
prompt Creating table NN_MERCHANT
prompt ==========================
prompt
create table NN_MERCHANT
(
  ID                  VARCHAR2(32) default SYS_GUID() not null,
  MERCHANT_CODE       VARCHAR2(32),
  LOGIN_MOBILE        VARCHAR2(32) not null,
  LOGIN_PWD           VARCHAR2(32) not null,
  REF_SIGN            VARCHAR2(32) not null,
  BIND_MOBILE         VARCHAR2(32),
  BANK_ACCOUNT_NAME   VARCHAR2(32),
  BANK_ACCOUNT_NO     VARCHAR2(20),
  BANK_NAME           VARCHAR2(32),
  ID_CARD             VARCHAR2(32),
  ID_CARD_START       TIMESTAMP(6),
  ID_CARD_END         TIMESTAMP(6),
  FEE_SET_FLAG        VARCHAR2(1) not null,
  FEE_SET_TYPE        VARCHAR2(32),
  CREATE_DATE         TIMESTAMP(6) not null,
  YEE_CUSTOMER_NUMBER VARCHAR2(15)
)
;
comment on column NN_MERCHANT.REF_SIGN
  is '推荐人标识';
comment on column NN_MERCHANT.BIND_MOBILE
  is ' 商户注册的手机号，不一定 是银行预留手机号，与登陆手机号保持一致即可';
comment on column NN_MERCHANT.BANK_ACCOUNT_NAME
  is '银行卡开户名';
comment on column NN_MERCHANT.BANK_ACCOUNT_NO
  is '银行卡号';
comment on column NN_MERCHANT.BANK_NAME
  is '银行卡开户行';
comment on column NN_MERCHANT.ID_CARD
  is '身份证号';
comment on column NN_MERCHANT.ID_CARD_START
  is '身份证号开始时间';
comment on column NN_MERCHANT.ID_CARD_END
  is '身份证号结束时间';
comment on column NN_MERCHANT.FEE_SET_FLAG
  is '0:费率未设置；1:费率设置中；2:费率设置成功；3费率设置失败';
comment on column NN_MERCHANT.YEE_CUSTOMER_NUMBER
  is '易宝侧返回的子商户编码';
alter table NN_MERCHANT
  add primary key (ID);
alter table NN_MERCHANT
  add unique (MERCHANT_CODE);
alter table NN_MERCHANT
  add unique (LOGIN_MOBILE);
alter table NN_MERCHANT
  add unique (BANK_ACCOUNT_NO);
alter table NN_MERCHANT
  add unique (ID_CARD);

prompt
prompt Creating table NN_MERCHANT_CREDIT_CARD
prompt ======================================
prompt
create table NN_MERCHANT_CREDIT_CARD
(
  ID              VARCHAR2(32) default SYS_GUID() not null,
  MERCHANT_MOBILE VARCHAR2(32) not null,
  CARD_CODE       VARCHAR2(32) not null,
  CARD_NO         VARCHAR2(32) not null,
  BANK_CODE       VARCHAR2(32),
  BANK_NAME       VARCHAR2(32)
)
;
comment on table NN_MERCHANT_CREDIT_CARD
  is '商户信用卡列表';
alter table NN_MERCHANT_CREDIT_CARD
  add primary key (ID);
alter table NN_MERCHANT_CREDIT_CARD
  add constraint NN_MERCHANT_CREDIT_CARD_UK1 unique (CARD_NO);
alter table NN_MERCHANT_CREDIT_CARD
  add constraint NN_MERCHANT_CREDIT_CARD_UK2 unique (CARD_CODE);

prompt
prompt Creating table NN_MERCHANT_LOG
prompt ==============================
prompt
create table NN_MERCHANT_LOG
(
  ID           VARCHAR2(32) default SYS_GUID() not null,
  LOGIN_MOBILE VARCHAR2(32) not null,
  OP_TYPE      VARCHAR2(1) not null,
  OP_DATE      TIMESTAMP(6) not null,
  REMARKS      VARCHAR2(512)
)
;
comment on table NN_MERCHANT_LOG
  is '商户信息变更记录(记录变更前的注册手机号和银行卡卡号)';
alter table NN_MERCHANT_LOG
  add primary key (ID);

prompt
prompt Creating table NN_ORDER
prompt =======================
prompt
create table NN_ORDER
(
  ID                       VARCHAR2(32) default SYS_GUID() not null,
  ORDER_NO                 VARCHAR2(32) not null,
  CREATE_DATE              TIMESTAMP(6) not null,
  MERCHANT_MOBILE          VARCHAR2(32) not null,
  REF_SIGN                 VARCHAR2(32) not null,
  ORDER_STATUS             VARCHAR2(2) not null,
  DEBIT_CARD_NO            VARCHAR2(32) not null,
  CREDIT_CARD_NO           VARCHAR2(32),
  ORDER_AMT                NUMBER(15,6) not null,
  TRADE_FEE                NUMBER(15,6) default 0 not null,
  T0_WITHDRAW_FEE          NUMBER(15,6) default 0 not null,
  YEE_EXTERNAL_LD          VARCHAR2(32),
  YEE_PAY_STATUS           VARCHAR2(32),
  YEE_PAY_DATE             TIMESTAMP(6),
  YEE_TRADE_FEE            NUMBER(15,6),
  YEE_WITHDRAW_STATUS      VARCHAR2(32),
  YEE_WITHDRAW_HANDLE_DATE TIMESTAMP(6),
  YEE_RECEIVER             VARCHAR2(32),
  YEE_RECEIVER_BANKCARD_NO VARCHAR2(32),
  YEE_WITHDRAW_AMT         NUMBER(15,6),
  YEE_WITHDRAW_ACTUAL_AMT  NUMBER(15,6),
  YEE_T0_WITHDRAW_FEE      NUMBER(15,6),
  YEE_T0_WITHDRAW_EXFEE    NUMBER(15,6),
  MCC                      VARCHAR2(32),
  T0_WITHDRAW_EXFEE        NUMBER(15,6),
  COMMISSTION              NUMBER(15,6)
)
;
comment on column NN_ORDER.MERCHANT_MOBILE
  is '商户手机号码';
comment on column NN_ORDER.REF_SIGN
  is '推荐人标识';
comment on column NN_ORDER.ORDER_STATUS
  is '订单状态；0已创建，1支付中，2支付成功，3结算成功';
comment on column NN_ORDER.DEBIT_CARD_NO
  is '借记卡卡号';
comment on column NN_ORDER.CREDIT_CARD_NO
  is '信用卡卡号';
comment on column NN_ORDER.ORDER_AMT
  is '订单金额';
comment on column NN_ORDER.TRADE_FEE
  is '交易手续费';
comment on column NN_ORDER.T0_WITHDRAW_FEE
  is 'T0自助结算手续费';
comment on column NN_ORDER.YEE_EXTERNAL_LD
  is '收款宝交易流水号;';
comment on column NN_ORDER.YEE_PAY_STATUS
  is '易宝支付状态INIT:未支付 SUCCESS:成功 FAIL:失败 FROZEN:冻结 THAWED:解冻 REVERSE:冲正';
comment on column NN_ORDER.YEE_PAY_DATE
  is '易宝支付成功时间';
comment on column NN_ORDER.YEE_TRADE_FEE
  is '收款订单的手续费';
comment on column NN_ORDER.YEE_WITHDRAW_STATUS
  is '易宝结算状态: RECEIVED:已接受 PROCESSING:处理中 SUCCESSED:打款成功 FAILED:打款失败 REFUNED:已退款 CANCELLED:已撤销';
comment on column NN_ORDER.YEE_WITHDRAW_HANDLE_DATE
  is '易宝结算处理时间';
comment on column NN_ORDER.YEE_RECEIVER
  is '易宝收款人';
comment on column NN_ORDER.YEE_RECEIVER_BANKCARD_NO
  is '易宝收款卡号';
comment on column NN_ORDER.YEE_WITHDRAW_AMT
  is '易宝结算金额';
comment on column NN_ORDER.YEE_WITHDRAW_ACTUAL_AMT
  is '易宝结算实际到账金额';
comment on column NN_ORDER.YEE_T0_WITHDRAW_FEE
  is '易宝T0 自助结算基本手续费';
comment on column NN_ORDER.YEE_T0_WITHDRAW_EXFEE
  is '易宝T0 自助结算额外手续费';
comment on column NN_ORDER.MCC
  is '商品分类,5311： 百货商店
4511： 航空公司
4733： 大型景区售票';
comment on column NN_ORDER.T0_WITHDRAW_EXFEE
  is 'T0自助结算额外手续费';
comment on column NN_ORDER.COMMISSTION
  is '佣金金额';
alter table NN_ORDER
  add primary key (ID);
alter table NN_ORDER
  add unique (ORDER_NO);

prompt
prompt Creating table NN_ORDER_LOG
prompt ===========================
prompt
create table NN_ORDER_LOG
(
  ID           VARCHAR2(32) default SYS_GUID() not null,
  ORDER_NO     VARCHAR2(32) not null,
  ORDER_STATUS VARCHAR2(2) not null,
  OP_USER      VARCHAR2(32) not null,
  OP_TIME      TIMESTAMP(6) not null,
  REMARKS      VARCHAR2(1024)
)
;
comment on column NN_ORDER_LOG.ORDER_STATUS
  is ' 订单当前最新状态';
alter table NN_ORDER_LOG
  add primary key (ID);

prompt
prompt Creating table NN_REFERRER
prompt ==========================
prompt
create table NN_REFERRER
(
  ID              VARCHAR2(32) default SYS_GUID() not null,
  NAME            VARCHAR2(32) not null,
  MOBILE          VARCHAR2(32) not null,
  REF_SIGN        VARCHAR2(32) not null,
  REF_CODE        VARCHAR2(32) not null,
  REF_LINK        VARCHAR2(512),
  REF_STATUS      VARCHAR2(1) not null,
  COMMISSION_RATE NUMBER(10,6) not null,
  CREATE_DATE     TIMESTAMP(6) not null
)
;
comment on column NN_REFERRER.NAME
  is '姓名';
comment on column NN_REFERRER.MOBILE
  is '手机号码 ';
comment on column NN_REFERRER.REF_SIGN
  is '推荐人标识';
comment on column NN_REFERRER.REF_CODE
  is '推荐人邀请码';
comment on column NN_REFERRER.REF_LINK
  is '推荐链接';
comment on column NN_REFERRER.REF_STATUS
  is '推荐人状态／1启用，0禁用';
comment on column NN_REFERRER.COMMISSION_RATE
  is '佣金百分比';
alter table NN_REFERRER
  add primary key (ID);
alter table NN_REFERRER
  add unique (REF_SIGN);
alter table NN_REFERRER
  add unique (REF_CODE);

prompt
prompt Creating table NN_STATISTICS
prompt ============================
prompt
create table NN_STATISTICS
(
  ID              VARCHAR2(32) default SYS_GUID() not null,
  STATISTICS_TYPE VARCHAR2(32),
  STATISTICS_DATE TIMESTAMP(6),
  STATISTICS_AMT  NUMBER(15,6)
)
;
comment on column NN_STATISTICS.STATISTICS_TYPE
  is '统计类型，1，系统商费率，2，推荐人佣金';
comment on column NN_STATISTICS.STATISTICS_DATE
  is '统计日期';
comment on column NN_STATISTICS.STATISTICS_AMT
  is '金额';
alter table NN_STATISTICS
  add primary key (ID);
alter table NN_STATISTICS
  add unique (STATISTICS_DATE, STATISTICS_TYPE);


create table NN_TANSFER_LOG
(
  ID		VARCHAR2(32) default SYS_GUID() not null,
  TRANSFER_DATE		TIMESTAMP(6) not null,
  TRANSFER_USER		VARCHAR2(32) not null,
  REF_SIGN			VARCHAR2(32) not null,
  YEE_CUSTOMER_NUMBER	VARCHAR2(15) not null,
  TRANSFER_AMT		NUMBER(15,6) not null,
  TRANSFER_STATUS	VARCHAR2(1) not null,
  REMARK		VARCHAR2(512)
);
alter table NN_TANSFER_LOG add unique (TRANSFER_DATE, REF_SIGN);

create table NN_TANSFER_QUE
(
  ID		VARCHAR2(32) default SYS_GUID() not null,
  TRANSFER_DATE TIMESTAMP(6) not null,
  REF_SIGN VARCHAR2(32) not null
);
alter table NN_TANSFER_QUE add unique (TRANSFER_DATE, REF_SIGN);


spool off
