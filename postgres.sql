-- 1，创建登录的用户角色（包含密码）
DROP USER IF EXISTS pgis;
CREATE ROLE pgis WITH LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT NOREPLICATION CONNECTION LIMIT -1 PASSWORD 'Mozilla10.0.2';

-- 2，创建表空间（如果出现：﻿ERROR:  could not set permissions on directory "/home/data/pgis": Operation not permitted，在DOS中执行：chown postgres /home/data/pgis）
DROP TABLESPACE IF EXISTS pgis;
CREATE TABLESPACE pgis OWNER pgis LOCATION '/home/data/pgis'; -- ﻿/Users/young/upload/postgres/pgis、Docker中是：﻿/var/lib/postgresql/data，其他有权限问题

-- 3，创建数据库
DROP DATABASE IF EXISTS pgis;
CREATE DATABASE pgis WITH OWNER = pgis ENCODING = 'UTF8' TABLESPACE = pgis CONNECTION LIMIT = -1;


/*==============================================================*/
/* table: t_config                                              */
/*==============================================================*/
drop table if exists t_config;
create table t_config (
  id            serial          not null,
  name          varchar(32)     not null, -- 配置名称
  value         varchar(1024)     not null, -- 配置值
  description   varchar(1024)    null, -- 描述
  constraint pk_t_config primary key (id)
);

insert into t_config (name, value) values ('system_name', '停车诱导系统');
insert into t_config (name, value, description) values ('show_verify', '0', '是否在登录的时候显示验证码，0 显示；1 不显示'); -- 0 显示；1 不显示


/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
drop table if exists t_user;
create table t_user (
    id                   serial8              not null, -- 主键id
    username             varchar(100)         not null, -- 用户名
    password             varchar(32)          not null, -- 密码
    real_name            varchar(100)         null, -- 真实姓名
    phone                varchar(11)          null, -- 手机号
    salt                 varchar(8)           not null, -- 密码盐
    type                 int2                 not null default 0, -- 用户类型：0 普通用户；1 系统管理员
    last_login_ip        varchar(15)          null, -- 最后登录ip
    last_login_time      timestamp            null, -- 最后登录时间
    create_time          timestamp            not null default current_timestamp, -- 创建时间
    constraint pk_t_user primary key (id)
);

-- 插入初始化数据
insert into t_user (username, password, real_name, phone, salt, type)
values ('admin', 'c93ccd78b2076528346216b3b2f701e6', '系统管理员', '12345678911', '1234', 1); -- 密码：admin


/*==============================================================*/
/* Table: t_log                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_log;
CREATE TABLE t_log (
  id           SERIAL8       NOT NULL, -- 主键id
  username     VARCHAR(32)   NULL, -- 操作用户名
  url          VARCHAR(128)  NULL, -- 请求url
  http_method  VARCHAR(16)   NULL, -- http请求方法
  ip           VARCHAR(15)   NULL, -- 请求ip地址
  class_method VARCHAR(128)  NULL, -- 请求类
  args         VARCHAR(1024) NULL, -- 请求参数
  response     VARCHAR(2048) NULL, -- 相应数据
  spend_time   INT           NULL, -- 相应时间（单位：毫秒）
  create_time  TIMESTAMP     NOT NULL DEFAULT current_timestamp, -- 创建时间
  CONSTRAINT pk_t_log PRIMARY KEY (id)
);


/*==============================================================*/
/* Table: t_parking                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_parking;
CREATE TABLE t_parking (
  id               SERIAL8      NOT NULL,
  name             VARCHAR(32)  NOT NULL,
  code             VARCHAR(32)  NOT NULL,
  type             INT2         NOT NULL,
  access_mode      INT2         NOT NULL,
  lot_total_count  INT          NOT NULL,
  lot_remain_count INT          NULL,
  remark           VARCHAR(512) NULL,
  location         VARCHAR(32)  NULL,
  media_screen_id  INT8    NULL,
  unit_name VARCHAR(255) null,
  create_time      TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_t_parking PRIMARY KEY (id)
);
COMMENT ON TABLE t_parking IS '停车场';
COMMENT ON COLUMN t_parking.name IS '名称';
COMMENT ON COLUMN t_parking.code IS '编码';
COMMENT ON COLUMN t_parking.type IS '类型 1：室内 2室外 3占道 4 机械';
COMMENT ON COLUMN t_parking.access_mode IS '接入方式';
COMMENT ON COLUMN t_parking.lot_total_count IS '车位总数';
COMMENT ON COLUMN t_parking.lot_remain_count IS '剩余车位数';
COMMENT ON COLUMN t_parking.location IS '位置';
COMMENT ON COLUMN t_parking.remark IS '创建时间';
COMMENT ON COLUMN t_parking.media_screen_id IS '全息屏id';
COMMENT ON COLUMN t_parking.unit_name IS '单位名称';

/*==============================================================*/
/* Table: t_control_card                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_control_card;
CREATE TABLE t_control_card (
  id            SERIAL8     NOT NULL,
  code          VARCHAR(32) NOT NULL,
  device_status INT2        NOT NULL DEFAULT 1,
  sim_card_id   INT8        NOT NULL,
  init_status   INT2,
  remark        VARCHAR(512),
  create_time   TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_control_card PRIMARY KEY (id)
);
COMMENT ON TABLE t_control_card IS '控制卡';
COMMENT ON COLUMN t_control_card.code IS '控制卡编码';
COMMENT ON COLUMN t_control_card.device_status IS '设备状态 1：正常 2正常';
COMMENT ON COLUMN t_control_card.sim_card_id IS '控制卡ID';
COMMENT ON COLUMN t_control_card.init_status IS '控制卡初始化状态 0：异常 1 正常';
COMMENT ON COLUMN t_control_card.remark IS '描述';
COMMENT ON COLUMN t_control_card.create_time IS '创建时间';

/*==============================================================*/
/* Table: t_screen                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_screen;
CREATE TABLE t_screen (
  id              SERIAL8      NOT NULL,
  name            VARCHAR(200) NOT NULL,
  code            VARCHAR(32)  NULL,
  type            INT2         NOT NULL,
  control_card_id  INT8         NOT NULL,
  induce_plate_id INT8         NOT NULL,
  order_num       int          NOT NULL ,
  width           int ,
  height          int ,
  create_time     TIMESTAMP    NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_screen PRIMARY KEY (id)
);
COMMENT ON TABLE t_screen IS '控制卡';
COMMENT ON COLUMN t_screen.name IS '屏幕名称';
COMMENT ON COLUMN t_screen.code IS '屏幕编码';
COMMENT ON COLUMN t_screen.type IS '屏幕类型 1：车位数屏 2文字信息屏';
COMMENT ON COLUMN t_screen.control_card_id IS '控制卡ID';
COMMENT ON COLUMN t_screen.induce_plate_id IS '诱导牌ID';
COMMENT ON COLUMN t_screen.create_time IS '创建时间';
COMMENT ON COLUMN t_screen.order_num IS '序号(屏幕在诱导牌上的位置)';
COMMENT ON COLUMN t_screen.width IS '屏幕宽度';
COMMENT ON COLUMN t_screen.height IS '屏幕长度';

/*==============================================================*/
/* Table: t_induce_plate                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_induce_plate;
CREATE TABLE t_induce_plate (
  id          SERIAL8       NOT NULL,
  name        VARCHAR(256)  NOT NULL,
  code        VARCHAR(32)   NULL,
  level       INT2          NOT NULL,
  location    VARCHAR(32)   NULL,
  remark      VARCHAR(1024) NULL,
  create_time TIMESTAMP     NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_induce_plate PRIMARY KEY (id)
);
COMMENT ON TABLE t_induce_plate IS '诱导牌';
COMMENT ON COLUMN t_induce_plate.name IS '名称';
COMMENT ON COLUMN t_induce_plate.code IS '编码';
COMMENT ON COLUMN t_induce_plate.level IS '等级 1：一级诱导牌 2二级诱导牌 3 三级诱导牌；4：全点阵LED信息屏';
COMMENT ON COLUMN t_induce_plate.location IS '屏幕名称 x,y';
COMMENT ON COLUMN t_induce_plate.remark IS '描述';
COMMENT ON COLUMN t_induce_plate.create_time IS '创建时间';

/*==============================================================*/
/* Table: t_geo_sensor                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_geo_sensor;
CREATE TABLE t_geo_sensor (
  id               SERIAL8     NOT NULL,
  code             VARCHAR(32) NOT NULL,
  type             INT2        NOT NULL,
  gateway_id       INT8        NOT NULL,
  device_status    INT2        NOT NULL DEFAULT 1,
  battery_capacity INT,
  create_time      TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_geo_sensor PRIMARY KEY (id)
);
COMMENT ON TABLE t_geo_sensor IS '地磁';
COMMENT ON COLUMN t_geo_sensor.code IS '地磁编码';
COMMENT ON COLUMN t_geo_sensor.type IS '地磁类型 1、流量地磁 2泊位地磁';
COMMENT ON COLUMN t_geo_sensor.gateway_id IS '所属网关ID';
COMMENT ON COLUMN t_geo_sensor.device_status IS '设备状态 1：正常 0 异常';
COMMENT ON COLUMN t_geo_sensor.battery_capacity IS '电池容量';
COMMENT ON COLUMN t_geo_sensor.create_time IS '创建时间';

/*==============================================================*/
/* Table: t_gateway                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_gateway;
CREATE TABLE t_gateway (
  id            SERIAL8     NOT NULL,
  code          VARCHAR(32) NOT NULL,
  sim_card_id   INT8        NOT NULL,
  parking_id    INT8        NOT NULL,
  device_status INT2        NOT NULL DEFAULT 1,
  remark        VARCHAR(1024),
  create_time   TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_gateway PRIMARY KEY (id)
);
COMMENT ON TABLE t_gateway IS '网关';
COMMENT ON COLUMN t_gateway.code IS '网关编码';
COMMENT ON COLUMN t_gateway.sim_card_id IS 'sim卡ID';
COMMENT ON COLUMN t_gateway.parking_id IS '所属停车场ID';
COMMENT ON COLUMN t_gateway.device_status IS '设备状态 1：正常 0 异常';
COMMENT ON COLUMN t_gateway.remark IS '描述';
COMMENT ON COLUMN t_gateway.create_time IS '创建时间';

/*==============================================================*/
/* Table: t_gateway                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_repeater;
CREATE TABLE t_repeater (
  id            SERIAL8     NOT NULL,
  code          VARCHAR(32) NOT NULL,
  gateway_id    INT8        NOT NULL,
  device_status INT2        NOT NULL DEFAULT 1,
  remark        VARCHAR(1024),
  create_time   TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_repeater PRIMARY KEY (id)
);
COMMENT ON TABLE t_repeater IS '中继器';
COMMENT ON COLUMN t_repeater.code IS '编码';
COMMENT ON COLUMN t_repeater.gateway_id IS '网关ID';
COMMENT ON COLUMN t_repeater.device_status IS '设备状态 1：正常 1异常';
COMMENT ON COLUMN t_repeater.remark IS '描述';
COMMENT ON COLUMN t_repeater.create_time IS '创建时间';


/*==============================================================*/
/* Table: t_program                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_program;
CREATE TABLE t_program (
  id           SERIAL8       NOT NULL,
  name         VARCHAR(512)  NOT NULL,
  content      TEXT,
  user_id      INT8          NOT NULL,
  status       INT2          NOT NULL  DEFAULT 0,
  release_time TIMESTAMP,
  show_type    int2    DEFAULT 0 ,
  step         INT ,
  interval     INT,
  width   INT,
  height  INT,
  remark       VARCHAR(512),
  create_time  TIMESTAMP     NOT NULL  DEFAULT now(),
  update_time  TIMESTAMP     NOT NULL  DEFAULT now(),
  CONSTRAINT pk_t_program PRIMARY KEY (id)
);
COMMENT ON TABLE t_program IS '节目';
COMMENT ON COLUMN t_program.name IS '名称';
COMMENT ON COLUMN t_program.content IS '节目内容 文本';
COMMENT ON COLUMN t_program.user_id IS '创建节目人';
COMMENT ON COLUMN t_program.status IS '节目状态 0 待审核 -1审核失败 1待发布（审核成功） -2发布失败 2 发布成功';
COMMENT ON COLUMN t_program.release_time IS '发布时间';
COMMENT ON COLUMN t_program.show_type IS '显示效果 0：单屏静态显示 1：单行滚动显示 2：多屏轮播显示 ';
COMMENT ON COLUMN t_program.interval IS '滚动间隔 单位ms 默认50 ';
COMMENT ON COLUMN t_program.step IS '滚动速度 单位像素 ';
COMMENT ON COLUMN t_program.remark IS '描述';
COMMENT ON COLUMN t_program.create_time IS '创建时间';
COMMENT ON COLUMN t_program.update_time IS '修改时间';
COMMENT ON COLUMN t_program.interval IS '间隔时间 默认500ms';
COMMENT ON COLUMN t_program.step IS '每次移动的像素个数 默认1';

/*==============================================================*/
/* Table: t_parking_screen 停车场和车位数屏关联表                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_parking_screen;
CREATE TABLE t_parking_screen (
  id          SERIAL8   NOT NULL,
  parking_id  INT8      NOT NULL,
  screen_id   INT8      NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_parking_screen PRIMARY KEY (id)
);
COMMENT ON TABLE t_parking_screen IS '停车场和车位数屏关联表';
COMMENT ON COLUMN t_parking_screen.parking_id IS '停车场ID';
COMMENT ON COLUMN t_parking_screen.screen_id IS '屏幕ID';

/*==============================================================*/
/* Table: t_sim_card 停车场和车位数屏关联表                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_sim_card;
CREATE TABLE t_sim_card (
  id          SERIAL8   NOT NULL,
  phone_number  VARCHAR(11) NOT NULL,
   operator_type  INT8      NOT NULL,
  network_mode   INT       NOT NULL,
  device_status   INT        NULL DEFAULT  1,
  remark   VARCHAR(256)       NULL,
  create_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_sim_card PRIMARY KEY (id)
);
COMMENT ON TABLE t_sim_card IS '停车场和车位数屏关联表';
COMMENT ON COLUMN t_sim_card.phone_number IS '手机号';
COMMENT ON COLUMN t_sim_card.operator_type IS '电信运营商类型 1：移动 2联通 3电信';
COMMENT ON COLUMN t_sim_card.network_mode IS '网络模式';
COMMENT ON COLUMN t_sim_card.device_status IS '设备状态 1：正常 0 异常';
COMMENT ON COLUMN t_sim_card.remark IS '描述';
COMMENT ON COLUMN t_sim_card.create_time IS '创建时间';



/*==============================================================*/
/* Table: t_parking_screen 节目和内容屏关联表                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_program_screen;
CREATE TABLE t_program_screen (
  id          SERIAL8   NOT NULL,
  program_id  INT8      NOT NULL,
  screen_id   INT8      NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_t_program_screen PRIMARY KEY (id)
);
COMMENT ON TABLE t_program_screen IS '节目和内容屏关联表';
COMMENT ON COLUMN t_program_screen.program_id IS '节目ID';
COMMENT ON COLUMN t_program_screen.screen_id IS '屏幕ID';
COMMENT ON COLUMN t_program_screen.create_time IS '创建时间';

/*==============================================================*/
/* Table: t_parking_screen 网关车流量数据                                                 */
/*==============================================================*/
DROP TABLE IF EXISTS t_flow_info_data;
CREATE TABLE t_flow_info_data
(
  id SERIAL8 PRIMARY KEY NOT NULL,
  unique_key VARCHAR(32) NOT NULL,
  enter_num INTEGER DEFAULT 0 NOT NULL,
  exit_num INTEGER DEFAULT 0 NOT NULL,
  create_time TIMESTAMP(6) DEFAULT now() NOT NULL,
  parking_space_id INTEGER,
  gateway_code VARCHAR(32)
);
COMMENT ON COLUMN t_flow_info_data.unique_key IS '唯一标识';
COMMENT ON COLUMN t_flow_info_data.parking_space_id IS '停车场ID';
CREATE UNIQUE INDEX flow_info_data_unique_key_uindex ON t_flow_info_data (unique_key);













