/*
Navicat PGSQL Data Transfer

Source Server         : 139.198.2.158_copy
Source Server Version : 90603
Source Host           : 139.198.2.158:15432
Source Database       : pgis
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90603
File Encoding         : 65001

Date: 2017-10-29 15:35:30
*/


-- ----------------------------
-- Sequence structure for t_config_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_config_id_seq";
CREATE SEQUENCE "t_config_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."t_config_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for t_content_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_content_id_seq";
CREATE SEQUENCE "t_content_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_control_card_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_control_card_id_seq";
CREATE SEQUENCE "t_control_card_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_flow_info_data_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_flow_info_data_id_seq";
CREATE SEQUENCE "t_flow_info_data_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 855
 CACHE 1;
SELECT setval('"public"."t_flow_info_data_id_seq"', 855, true);

-- ----------------------------
-- Sequence structure for t_gateway_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_gateway_id_seq";
CREATE SEQUENCE "t_gateway_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."t_gateway_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for t_geomagnetic_sensor_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_geomagnetic_sensor_id_seq";
CREATE SEQUENCE "t_geomagnetic_sensor_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_induce_plate_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_induce_plate_id_seq";
CREATE SEQUENCE "t_induce_plate_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_organization_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_organization_id_seq";
CREATE SEQUENCE "t_organization_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."t_organization_id_seq"', 4, true);

-- ----------------------------
-- Sequence structure for t_parking_space_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_parking_space_id_seq";
CREATE SEQUENCE "t_parking_space_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;
SELECT setval('"public"."t_parking_space_id_seq"', 14, true);

-- ----------------------------
-- Sequence structure for t_parking_space_screen_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_parking_space_screen_id_seq";
CREATE SEQUENCE "t_parking_space_screen_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."t_parking_space_screen_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for t_program_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_program_user_id_seq";
CREATE SEQUENCE "t_program_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_repeater_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_repeater_id_seq";
CREATE SEQUENCE "t_repeater_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for t_screen_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_screen_id_seq";
CREATE SEQUENCE "t_screen_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."t_screen_id_seq"', 1, true);

-- ----------------------------
-- Sequence structure for t_sim_card_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_sim_card_id_seq";
CREATE SEQUENCE "t_sim_card_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."t_sim_card_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for t_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "t_user_id_seq";
CREATE SEQUENCE "t_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 43
 CACHE 1;
SELECT setval('"public"."t_user_id_seq"', 43, true);

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS "t_config";
CREATE TABLE "t_config" (
"id" int4 DEFAULT nextval('t_config_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"value" varchar(100) COLLATE "default" NOT NULL,
"need_auth" int2 DEFAULT 1 NOT NULL
)
WITH (OIDS=FALSE)
TABLESPACE "pgis" 

;

-- ----------------------------
-- Records of t_config
-- ----------------------------
BEGIN;
INSERT INTO "t_config" VALUES ('1', 'SYSTEM_NAME', '智能交通诱导系统', '0');
INSERT INTO "t_config" VALUES ('2', 'IDENTIFYING_CODE', '1', '0');
COMMIT;

-- ----------------------------
-- Table structure for t_content
-- ----------------------------
DROP TABLE IF EXISTS "t_content";
CREATE TABLE "t_content" (
"id" int4 DEFAULT nextval('t_content_id_seq'::regclass) NOT NULL,
"name" varchar(200) COLLATE "default",
"text" varchar(500) COLLATE "default",
"scroll" bool DEFAULT true NOT NULL,
"font_size" int4 DEFAULT 16 NOT NULL,
"color" varchar(10) COLLATE "default" DEFAULT 'red'::character varying NOT NULL,
"interval" int4 DEFAULT 50 NOT NULL,
"step" int4 DEFAULT 1 NOT NULL,
"remark" varchar(500) COLLATE "default",
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_content" IS '内容';
COMMENT ON COLUMN "t_content"."name" IS '内容名称';
COMMENT ON COLUMN "t_content"."text" IS '内容文本';
COMMENT ON COLUMN "t_content"."scroll" IS '是否滚动 默认滚动';
COMMENT ON COLUMN "t_content"."font_size" IS '字体大小';
COMMENT ON COLUMN "t_content"."color" IS '内容颜色';
COMMENT ON COLUMN "t_content"."interval" IS '滚动间隔  单位ms 默认值50';
COMMENT ON COLUMN "t_content"."step" IS '滚动速度  单位 像素 默认 1';
COMMENT ON COLUMN "t_content"."remark" IS '备注';
COMMENT ON COLUMN "t_content"."create_time" IS '创建时间';

-- ----------------------------
-- Records of t_content
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_control_card
-- ----------------------------
DROP TABLE IF EXISTS "t_control_card";
CREATE TABLE "t_control_card" (
"id" int4 DEFAULT nextval('t_control_card_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"device_status" int2,
"create_time" timestamp(6),
"sim_card_id" int8,
"init_status" int4 DEFAULT 0 NOT NULL,
"remark" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_control_card" IS '控制卡';
COMMENT ON COLUMN "t_control_card"."init_status" IS '1：初始化 成功 0:初始化失败';
COMMENT ON COLUMN "t_control_card"."remark" IS '描述';

-- ----------------------------
-- Records of t_control_card
-- ----------------------------
BEGIN;
INSERT INTO "t_control_card" VALUES ('1', '望江西路和石莲南路交口诱导牌车位显示控制卡', 'e10-a16-00137', '1', '2017-09-04 11:03:43.215', '1', '0', '加载页面请求异常,请求响应代码:500');
INSERT INTO "t_control_card" VALUES ('2', '望江西路和石莲南路交口诱导牌多媒体控制卡', 'e10-a16-00017', '1', '2017-09-11 15:10:42.357', '2', '0', '加载页面请求异常,请求响应代码:500');
COMMIT;

-- ----------------------------
-- Table structure for t_flow_info_data
-- ----------------------------
DROP TABLE IF EXISTS "t_flow_info_data";
CREATE TABLE "t_flow_info_data" (
"unique_key" varchar(32) COLLATE "default" NOT NULL,
"enter_num" int4 DEFAULT 0 NOT NULL,
"exit_num" int4 DEFAULT 0 NOT NULL,
"create_time" timestamp(6) DEFAULT now() NOT NULL,
"id" int4 DEFAULT nextval('t_flow_info_data_id_seq'::regclass) NOT NULL,
"parking_space_id" int4,
"gateway_code" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "t_flow_info_data"."unique_key" IS '唯一标识';
COMMENT ON COLUMN "t_flow_info_data"."parking_space_id" IS '停车场ID';

-- ----------------------------
-- Records of t_flow_info_data
-- ----------------------------
BEGIN;
INSERT INTO "t_flow_info_data" VALUES ('03ba2614b0044af8b95032371322cace', '8', '4', '2017-09-04 11:41:50.431', '1', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e28d988ed0784c4dbfed0ae1bfa0c9a9', '6', '6', '2017-09-04 13:46:20.446', '3', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('2bec12a0b3c14878b3b7cec0138d30c6', '2', '1', '2017-09-04 13:46:37.2', '4', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('221c29f9842f4355892a88fa7a9f2ca4', '4', '6', '2017-09-04 13:47:06.66', '5', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('1f1e3e13f45c4808a6d285cfb16e32bf', '3', '9', '2017-09-04 13:49:14.93', '6', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('393964e99a9146bc99267904c8d9668b', '0', '4', '2017-09-04 13:49:19.705', '7', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('e3c7fa8e08564f5093b383ce10c394f0', '8', '4', '2017-09-04 13:49:24.7', '8', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0dc0f29ac09a440cbf408b9eb12f2fc0', '3', '6', '2017-09-04 13:49:29.696', '9', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e5eb8be5a29f405d9b46f590a68d2075', '5', '9', '2017-09-04 13:49:34.695', '10', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('7f5177a58d6844b6aefafb8cacf12fd4', '3', '2', '2017-09-04 13:49:39.693', '11', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('6f417af0d5a341289d5390e6be962de0', '3', '2', '2017-09-04 13:49:44.696', '12', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('dc124358821f4566b5210ea8f74617e6', '6', '7', '2017-09-04 13:49:49.699', '13', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('4971f0e24ed342efb3db205c1a07e2f4', '5', '5', '2017-09-04 13:50:15.609', '14', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('230b59d2d6a94b2f9d3bf842e67eab05', '2', '0', '2017-09-04 13:50:16.449', '15', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('83c8bae6b91b469b9b5b1d969165c620', '0', '6', '2017-09-04 13:50:17.455', '16', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('ebc0596db84341e681d8d97b08f4457d', '1', '0', '2017-09-04 13:50:18.45', '17', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('3b61942e517e486599f1239f5fbc7334', '2', '4', '2017-09-04 13:50:19.451', '18', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('c10d3297734d4ec2a84301ed6e60c2e9', '7', '1', '2017-09-04 13:50:20.445', '19', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('3ae8b549e85e4ed58a7d788c88d9e7cf', '2', '9', '2017-09-04 13:50:21.465', '20', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('1556093c7d714f95a6008d59c5b5f188', '3', '8', '2017-09-04 13:50:22.447', '21', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('bd2193fb8d3d4c9984d1ad9a27dfd59d', '0', '8', '2017-09-04 13:50:23.457', '22', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('b212c31a2174445eb28d58e641aceb83', '1', '6', '2017-09-04 13:50:24.453', '23', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('37610a7cc7cc4ae3b41e5821ce3379a4', '6', '0', '2017-09-04 13:50:25.449', '24', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('3517cf3a3acd450793ab4fa6672765fb', '3', '7', '2017-09-04 13:50:26.444', '25', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('1dac2568c1614063b06b53053088099f', '3', '3', '2017-09-04 13:50:27.456', '26', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('cfa17fdb4b2c4986af844d2001790648', '0', '6', '2017-09-04 13:50:28.439', '27', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('76481f26e376491aa78ae2a18267ced0', '7', '1', '2017-09-04 13:50:29.462', '28', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('dad455a34aad4f86aa9d6f0c490ee098', '8', '4', '2017-09-04 13:50:30.451', '29', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('8c13b2d6b6914335846883d4fdff78ab', '9', '1', '2017-09-04 13:50:31.461', '30', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('f947baa829984e03a8fc364eea2a6e28', '3', '5', '2017-09-04 13:50:32.458', '31', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0708e5033ac442b2b5ee8c5045ae17be', '8', '4', '2017-09-04 13:50:33.46', '32', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('bb72136854714e85909aecffb6557ff7', '9', '6', '2017-09-04 13:50:34.446', '33', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('1bb2dd392ee7418e88d5d1846557d599', '6', '3', '2017-09-04 13:50:35.454', '34', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('294a621e90c8418b9a562064f5637a73', '5', '6', '2017-09-04 13:50:36.457', '35', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('7268000c49b84420a29b7353c8c8e07d', '9', '1', '2017-09-04 13:50:37.458', '36', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('cd022f5183fb47a388db100544eb9f39', '8', '5', '2017-09-04 13:50:38.455', '37', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('31649643710145c382335388a139b7a9', '5', '5', '2017-09-04 13:50:39.474', '38', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('50a1325d5ca04e03a928b34e1c2bb531', '4', '4', '2017-09-04 13:50:40.449', '39', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('fc2ef33778f741658371fea81539bf4d', '0', '7', '2017-09-04 13:50:41.462', '40', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('882082ca65e14ec3b1da330b28a59afb', '2', '6', '2017-09-04 13:50:42.465', '41', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('f9209a852d9149328cdc01e78224c1ac', '8', '0', '2017-09-04 13:50:43.465', '42', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5378f2f7579e4cd7933c261968f0e064', '2', '6', '2017-09-04 13:50:44.454', '43', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('0c89aab267074643aaeb319b0528885f', '1', '5', '2017-09-04 13:50:45.461', '44', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('62df257bf93f43c69dc82f71e9108e06', '3', '1', '2017-09-04 13:50:46.45', '45', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('6ebb052bf0e942a09d1643e2ed8c3e67', '0', '9', '2017-09-04 13:50:47.473', '46', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('3a287d555ecb4f7d924b2db5c79aa632', '3', '2', '2017-09-04 13:50:48.456', '47', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('a62d756241ea4a5a91281b270e1f17a0', '0', '5', '2017-09-04 13:50:49.466', '48', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0c67a2f0e6844048a2412caeebc33119', '1', '5', '2017-09-04 13:50:50.459', '49', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('2a71f4048cc74aeab3567ef958032ce9', '7', '1', '2017-09-04 13:50:51.467', '50', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('7d3501f5bd1648caaf23fc5995637df3', '6', '0', '2017-09-04 13:50:52.462', '51', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('8149c61b4cae427b834f9a36049ec203', '8', '9', '2017-09-04 13:50:53.471', '52', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('fac5e4065ab04942b95a8eec678b6778', '5', '1', '2017-09-04 13:50:54.461', '53', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('0117dbc7cadd4732a21ae1d3f974d61b', '2', '0', '2017-09-04 13:50:55.471', '54', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0ae0b015b769454182856211968bf9d5', '4', '4', '2017-09-04 13:50:56.46', '55', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('27ec64eaffe8427bb75b5ba63dcb5c86', '4', '1', '2017-09-04 13:50:57.47', '56', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('8bd9c34e74954898918024aa7836c5f9', '5', '5', '2017-09-04 13:50:58.472', '57', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ecfe3a17e9034466be14366446f0cc6b', '9', '9', '2017-09-04 13:50:59.465', '58', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('30ab33491fb9440fbc71307348c6de02', '8', '3', '2017-09-04 13:51:00.468', '59', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('9748db6af6ef4a068b872da490bd9f98', '9', '0', '2017-09-04 13:51:01.513', '60', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('ee618cfcdf6741ceaca8ac5af3881658', '1', '4', '2017-09-04 13:51:02.462', '61', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('4e6d2f877bd24a7f9a8dafadddaf4e80', '8', '9', '2017-09-04 13:51:03.473', '62', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('673c8d82d25b42af9fb5cdf51cf9f96e', '8', '3', '2017-09-04 13:51:04.466', '63', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('a6bfc7f7b75644e4bab39b538fd03736', '4', '0', '2017-09-04 13:51:05.473', '64', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e75c54f8458f4454b7a53100639e35c5', '1', '7', '2017-09-04 13:51:06.474', '65', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d4a64511b7be47a58de7112575294c8e', '2', '4', '2017-09-04 13:51:07.483', '66', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d040b3a4bb6d478f82356e9545dc1298', '7', '6', '2017-09-04 13:51:08.467', '67', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e201fc90b01b499f9b7d34a3c4028a8b', '3', '5', '2017-09-04 13:51:09.473', '68', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('cddc7e3fda6f4684a537a2203d37e6d0', '4', '2', '2017-09-04 13:51:10.461', '69', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('9a5a639b76794e2c832b534b7594d133', '6', '1', '2017-09-04 13:51:11.476', '70', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('893b9e2d54d04c9790afa6303dd71df0', '7', '6', '2017-09-04 13:51:12.492', '71', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('02a76d08c9f74c79988f612602a49923', '4', '1', '2017-09-04 13:51:13.488', '72', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d597a8a9a94342a0a70ac652c81747ce', '5', '8', '2017-09-04 13:51:14.467', '73', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e36c71fb65c34bff84d26f92b565828c', '5', '4', '2017-09-04 13:51:15.478', '74', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5092ca0c103b4f40b193f7b6579b7bb9', '1', '3', '2017-09-04 13:51:16.47', '75', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('851163471f4549f0b6a18f56c903cd53', '2', '9', '2017-09-04 13:51:17.482', '76', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d9e561e47b15438a900830309c3ac93e', '6', '3', '2017-09-04 13:51:18.467', '77', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('f2d70264cf0b4af0a552af7b4efb414a', '8', '6', '2017-09-04 13:51:19.478', '78', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('9ec311b99c754f06a4efa9fb2a3fce88', '0', '7', '2017-09-04 13:51:20.472', '79', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('6f8bc36b01da4070ad256948f5312712', '4', '2', '2017-09-04 13:51:21.483', '80', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('a601065ff9104647bcb70cb5727222b9', '4', '6', '2017-09-04 13:51:22.472', '81', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('297f94aa2c84404dabe905e92c4f88d8', '0', '4', '2017-09-04 13:51:23.491', '82', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('7f0f52929f33476184b3c08f50589774', '6', '0', '2017-09-04 13:51:24.478', '83', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('2ece232e58ce4d0db2a659e1d3f23743', '0', '5', '2017-09-04 13:51:25.481', '84', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('74e2f9041e9446a0808b3a7df8a18832', '2', '3', '2017-09-04 13:51:26.476', '85', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d3fa19fe9a26408e816957c4f0d9b6a0', '0', '4', '2017-09-04 13:51:27.483', '86', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('76bdf0bd682e4724a8dd5117bad14bce', '0', '4', '2017-09-04 13:51:28.483', '87', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('31e7322e642949b2820bc9abbbd241a4', '0', '5', '2017-09-04 13:51:29.482', '88', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('41144fdd1ea7472490e90bfff8bded40', '8', '3', '2017-09-04 13:51:30.479', '89', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('b98b69a75fa44b97a0c6aabe7ba23e51', '1', '9', '2017-09-04 13:51:31.484', '90', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d7a64cb718e34d74859abb3586c8a7fb', '6', '3', '2017-09-04 13:51:32.488', '91', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('a5aad3fb213a4aa2843535a4bc41aad8', '2', '0', '2017-09-04 13:51:33.493', '92', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('e99eec422b2a469480151da2c86277df', '7', '4', '2017-09-04 13:51:34.479', '93', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('de765cd7aec94bbcb8350b8e7244b78a', '7', '3', '2017-09-04 13:51:35.495', '94', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('516d4e80b9b34a588caad69b67331547', '8', '5', '2017-09-04 13:51:36.482', '95', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('31291bdc8c034694a8aac1c6e37254bd', '8', '8', '2017-09-04 13:51:37.487', '96', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('a9a1a3e9e64b49709fa9da1677ec94d5', '2', '9', '2017-09-04 13:51:38.489', '97', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('aa000ed00a4547f0b3dd4e62d38559b9', '6', '0', '2017-09-04 13:51:39.493', '98', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('dd6956004e0341f99284418840cb10d3', '9', '5', '2017-09-04 13:51:40.487', '99', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('c293b5c7fabd460bbaa88696378b67cf', '6', '4', '2017-09-04 13:51:41.489', '100', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('a3d2ff0db38b4da193309698df94e3e0', '0', '8', '2017-09-04 13:51:42.494', '101', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d6a2a4781c2840fc9503c66671508bc3', '8', '7', '2017-09-04 13:51:43.489', '102', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('cc33cd354b1044658ac5389fc86a2437', '1', '1', '2017-09-04 13:51:44.483', '103', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('a0d0a6ed24b8473c8db96615b840dad0', '1', '8', '2017-09-04 13:51:45.493', '104', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('7a2998c51214436793f82332caa43262', '3', '3', '2017-09-04 13:51:46.483', '105', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('fa32ed803c114899893b32902c83871e', '0', '7', '2017-09-04 13:51:47.485', '106', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('1689911665b34cc9a576c4da60b35ad4', '9', '8', '2017-09-04 13:51:48.491', '107', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('34785774ae0549b7aca5ff4a05c19740', '0', '8', '2017-09-04 13:51:49.483', '108', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('e15cb3199140426f8adb194302f9c9eb', '6', '8', '2017-09-04 13:51:50.495', '109', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('b9d042ba5a2049c79e54437763356bdd', '7', '8', '2017-09-04 13:51:51.509', '110', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('9facfe1dc636419391c937019d981fa9', '8', '4', '2017-09-04 13:51:52.502', '111', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('518d4e01a4f84f438f1fb8a8885aea80', '1', '6', '2017-09-04 13:51:53.492', '112', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('acd42d41970a4e84817849d32339d032', '6', '7', '2017-09-04 13:51:54.492', '113', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('36cde66c4b2e42e2bc4b36b449258a8b', '8', '6', '2017-09-04 13:51:55.491', '114', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ae324ef7ce6f4b2a807bab4525615efd', '8', '3', '2017-09-04 13:51:56.561', '115', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('468cb3eb07164cbaa58b7a52954a3bfb', '6', '0', '2017-09-04 13:51:57.506', '116', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d148844dbc974dfcaf13dd0f5d6cc29e', '6', '4', '2017-09-04 13:51:58.498', '117', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('9492ecaa54d34fae9c373e1c8b50f227', '7', '3', '2017-09-04 13:51:59.487', '118', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('6efced7a89b5408983cb8a67b6508b48', '1', '4', '2017-09-04 13:52:00.499', '119', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('fbe64a90c5c845f6acb2db6caf2113c8', '8', '0', '2017-09-04 13:52:01.493', '120', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('ff1d9f131b6e4c1bb1354c7a0fc29873', '1', '0', '2017-09-04 13:52:02.498', '121', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('0ca6ea9417fb49f6b10f246d35c79736', '7', '8', '2017-09-04 13:52:03.492', '122', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('46f6f78f18ba42758cc61237d775a185', '7', '7', '2017-09-04 13:52:04.513', '123', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('291cc3828b4c4529bcfcb8fb1668b663', '4', '7', '2017-09-04 13:52:05.49', '124', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('34bcc8f6ee6f4a84ab9f8054af6bb591', '7', '6', '2017-09-04 13:52:06.502', '125', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('200fb5e0673842209db3c4a163475a64', '5', '5', '2017-09-04 13:52:07.498', '126', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('98e2914e9b4f498cbeccacdc4fe1b6dc', '2', '1', '2017-09-04 13:52:08.501', '127', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('fea32ba8bd9c41b68e7c6d947729d280', '1', '0', '2017-09-04 13:52:09.497', '128', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('afdd0603bea3446f87fccdcd96d37d53', '7', '8', '2017-09-04 13:52:10.519', '129', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('3aa5a66a1fb84d1a8de40216567cd0cd', '7', '9', '2017-09-04 13:52:11.499', '130', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ee4c9e2011704462bf593ecfe5e6e7db', '8', '3', '2017-09-04 13:52:12.514', '131', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('bec631a433734e21be2677f84c6a13fe', '7', '7', '2017-09-04 13:52:13.508', '132', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('fd8daa6cc7ed47b4a86aa0ede5deaf5b', '4', '8', '2017-09-04 13:52:14.514', '133', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('b38a10c5d68b4882962599e1500eca41', '5', '4', '2017-09-04 13:52:15.523', '134', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('8a18cf936c224f95878c0a239b89e578', '5', '7', '2017-09-04 13:52:16.52', '135', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5ef1a248ae344a57b13d3fd486bda015', '8', '3', '2017-09-04 13:52:17.515', '136', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('78dbe9f94ed64819ad897a2b6e4e76e3', '2', '2', '2017-09-04 13:52:18.541', '137', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('c8b51013dd894b3283152f771b182400', '0', '0', '2017-09-04 13:52:19.507', '138', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5bdae8deb513408982aeaaf2d2e17a9a', '7', '7', '2017-09-04 13:52:20.514', '139', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('6cfc9c42210044bb8e48d420f72cc551', '9', '3', '2017-09-04 13:52:21.519', '140', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('af577d5e64aa4a11b339d4292e60e772', '3', '7', '2017-09-04 13:52:22.526', '141', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('9efc479995f44148b8ad4842f85d2ec8', '6', '6', '2017-09-04 13:52:23.519', '142', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('747e34654f4f44e6bf3da4a0042e2661', '9', '9', '2017-09-04 13:52:24.515', '143', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('f96cb1a1a4274be5aa9fed54876de5d2', '1', '2', '2017-09-04 13:52:25.514', '144', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('19c25d3919a34c7f825947847a2b6c89', '7', '9', '2017-09-04 13:52:26.518', '145', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('28d4e57ff7bd44188bae4da670b991f0', '5', '3', '2017-09-04 13:52:27.514', '146', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('81792de952824e0197dba782e7738d12', '5', '8', '2017-09-04 13:52:28.519', '147', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('fc8e179bcf444d09923d8d6d182cc83b', '8', '2', '2017-09-04 13:52:29.522', '148', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('71f6a4307d254427a32d3e9f4b6a3bda', '1', '0', '2017-09-04 13:52:30.519', '149', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('53c8f4656fca4ea2b04ed6716386ec84', '0', '9', '2017-09-04 13:52:31.517', '150', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('67a011e3cf0b4ae3b058bccdd91204fe', '2', '9', '2017-09-04 13:52:32.521', '151', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('3ba56de9ab2448bc9228a0a27ca2cfa4', '4', '0', '2017-09-04 13:52:33.511', '152', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('97027168587e47ddaee57be2ece7c30b', '4', '7', '2017-09-04 13:52:34.521', '153', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('335eb4d0e6da4199ab6043e7f9d1ef9f', '8', '9', '2017-09-04 13:52:35.512', '154', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('f4b11c2047004592bc3854da41e8e898', '5', '5', '2017-09-04 13:52:36.526', '155', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0cb51a0505104029a4a473a574b7741d', '1', '6', '2017-09-04 13:52:37.518', '156', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5d1acfde0c9b40f4ae78f03904756d06', '7', '9', '2017-09-04 13:52:38.527', '157', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d7f9adfad22e4d278924723a794bb23a', '7', '6', '2017-09-04 13:52:39.52', '158', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('f843c11a0feb4fe6a5123438086a196f', '0', '4', '2017-09-04 13:52:40.534', '159', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('c327c0efad82411d8c9900a4d289cda8', '9', '9', '2017-09-04 13:52:41.52', '160', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d0598fb7680d451395b68b641869ca9b', '9', '6', '2017-09-04 13:52:42.528', '161', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('be45d39b4a3248aeb8464df601221d7a', '5', '8', '2017-09-04 13:52:43.526', '162', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('45f53e847b154ad386fc688949a66cca', '4', '7', '2017-09-04 13:52:44.528', '163', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('e3f47a5b5eda433eb691bd75f9f518ec', '3', '5', '2017-09-04 13:52:45.519', '164', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('da02085f305c41cfa55911f4e3a46b82', '4', '6', '2017-09-04 13:52:46.527', '165', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('286b7f1534c74e8183019a93c6890bdc', '0', '0', '2017-09-04 13:52:47.527', '166', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('9ebe460719d94a09a04b383797651cf5', '6', '1', '2017-09-04 13:52:48.528', '167', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('11dfb93489d043fcaa18ede2653cfdc0', '2', '5', '2017-09-04 13:52:49.523', '168', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('5707dd1e3ab04d5da380a843b95fe1aa', '3', '2', '2017-09-04 13:52:50.539', '169', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('2f1fd3161a4a45559428e817c76cda6f', '0', '0', '2017-09-04 13:52:51.527', '170', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('8aede574414d467895ce9128e41b2506', '8', '9', '2017-09-04 13:52:52.538', '171', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('df324af9ec8f4a928b3d3efc66538eda', '6', '3', '2017-09-04 13:52:53.521', '172', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('13aac03a2b7445e4941f5f6eca4b0030', '9', '1', '2017-09-04 13:52:54.563', '173', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('51d64a19457b4799ae4cb852278efdef', '8', '9', '2017-09-04 13:52:55.524', '174', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('8d73c86485ac4ad185c9621421e397c1', '9', '5', '2017-09-04 13:52:56.539', '175', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('1c5394c3e0a845349c37bd1d55b6d6e4', '9', '0', '2017-09-04 13:52:57.534', '176', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('3e2ef7b630eb4bc4a50316031921203c', '5', '8', '2017-09-04 13:52:58.555', '177', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('eb26f17ed1764df6b3c21c5f07862019', '6', '7', '2017-09-04 13:52:59.533', '178', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('5f8eec5618e14c0e8d067393b0bc7822', '6', '6', '2017-09-04 13:53:00.541', '179', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('18f4e171d70643c2827b0968e1f519df', '2', '8', '2017-09-04 13:53:01.579', '180', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('7ca1cb14a67447dc83a851c1df7a8551', '0', '7', '2017-09-04 13:53:02.545', '181', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('a401634dc7d14ac2839642018ba14694', '4', '9', '2017-09-04 13:53:03.535', '182', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('18ac42ee2e7d41dd8381ff83820e099c', '7', '3', '2017-09-04 13:53:04.539', '183', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('7a02e941ee2347f881ea3e357a417d14', '2', '2', '2017-09-04 13:53:05.546', '184', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('16477282934840c8b09d1c2243020e5f', '5', '5', '2017-09-04 13:53:06.541', '185', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('085696f778b84c279422cc7bf746c719', '4', '5', '2017-09-04 13:53:07.547', '186', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('17d556eedbd143f69be1f2422c325b77', '6', '7', '2017-09-04 13:53:08.554', '187', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d6780704320840cd9d7ca34e8c3df3bb', '9', '9', '2017-09-04 13:53:09.535', '188', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('7445933e4b6841038df3bf15715cd4d5', '5', '3', '2017-09-04 13:53:10.535', '189', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('f426ba215daf41369f644da48e47e1d0', '8', '8', '2017-09-04 13:53:11.531', '190', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('81d42bd52388420c8df59e58a1b46834', '3', '6', '2017-09-04 13:53:12.539', '191', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d0164ffbcad34c258333a6eb4f6ca402', '7', '1', '2017-09-04 13:53:13.553', '192', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ee96dbb4c68c4d2ba04587542ca60100', '3', '7', '2017-09-04 13:53:14.546', '193', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('008f7b4754694d29a52a6488f645c16c', '3', '7', '2017-09-04 13:53:15.536', '194', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('06a1a42e01614ef8a5896e07e71be32e', '1', '1', '2017-09-04 13:53:16.546', '195', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('179325c7a0a6435bbbdecbd3b48b2510', '5', '3', '2017-09-04 13:53:17.536', '196', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('9ee4efc76878420d90d1aa6d2ed3c7b2', '0', '9', '2017-09-04 13:53:18.548', '197', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('d11f49ac945b42c4a34c3187f80c0b9d', '8', '0', '2017-09-04 13:53:19.543', '198', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('c63262d10b6d4e0db7fd56b40f21d368', '0', '3', '2017-09-04 13:53:20.545', '199', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('79e70d1a54264bdab8348b2f14836dd7', '1', '6', '2017-09-04 13:53:21.537', '200', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('b2447209c34c462fa73b28ae442c5d2b', '0', '2', '2017-09-04 13:53:22.552', '201', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('e14aac06210c4325bfe4ffceb31bf60e', '6', '5', '2017-09-04 13:53:23.542', '202', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('635217143f5c4240aaacea832af14ea8', '7', '0', '2017-09-04 13:53:24.554', '203', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('5b4647768e0f4b6689d6b503ec17ecd4', '1', '9', '2017-09-04 13:53:25.549', '204', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('ba178d17e55d495286e8b4498a7f715c', '3', '2', '2017-09-04 13:53:26.554', '205', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('0528b2431293414bb0865d38ac6d9d87', '6', '9', '2017-09-04 13:53:27.539', '206', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('f93bf55e9cae4fca92ac14649b83bc68', '7', '2', '2017-09-04 13:53:28.552', '207', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('badb6e8a67c04ea888d5dfdc42a24b29', '1', '5', '2017-09-04 13:53:29.545', '208', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('a26617d006e44f66b9f3214433ad9b36', '7', '2', '2017-09-04 13:53:30.554', '209', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('1367f52b2ffe4b0caf1c0eea4745b920', '4', '6', '2017-09-04 13:53:31.545', '210', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('5a27cbef81aa4ef3b54b165d22ccafc8', '2', '3', '2017-09-04 13:53:32.556', '211', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('c1714295218b46b1a1dd038f9830ffc0', '5', '3', '2017-09-04 13:53:33.573', '212', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('d181eb9696cc4d4b913654313f6a6fb1', '0', '9', '2017-09-04 13:53:34.566', '213', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('543a7e5064ee4c86a5f2b439932c55ef', '8', '1', '2017-09-04 13:53:35.55', '214', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('8a711138eccf42be99d003f715b10861', '4', '3', '2017-09-04 13:53:36.572', '215', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('87760d5a14024bcbae09a9b072a4b21e', '4', '0', '2017-09-04 13:53:37.55', '216', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('34631f7e506a47e4be3e1c45ffbbe492', '8', '3', '2017-09-04 13:53:38.555', '217', '12', '361');
INSERT INTO "t_flow_info_data" VALUES ('3c3c6489668b49a6bd90630008a4ecb8', '5', '2', '2017-09-04 13:53:39.546', '218', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('bdfde01465fc4fd5a9b9951987d66659', '9', '7', '2017-09-04 13:53:40.558', '219', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('123', '2', '1', '2017-09-04 17:09:52.07', '220', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ac9194581c7b4c24a2d22f4b30c74e0b', '2', '1', '2017-09-04 17:33:55.025', '221', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('5d6f9dc5e8ba4f91a6a328e57f19ba82', '2', '1', '2017-09-04 17:33:55.15', '222', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('8d2949350fca4ab1b51a1e35313fba48', '0', '4', '2017-09-06 15:21:06.763', '223', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('cef7061cf26041e89d0808faf4e4a7d6', '0', '2', '2017-09-06 15:21:09.213', '224', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('32074f7a021c427488d8881385b91ecb', '0', '5', '2017-09-06 15:21:11.394', '225', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('1d1578c99c9e4134b63aa52b881fbe30', '0', '5', '2017-09-06 15:21:13.562', '226', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('67bd03bcfc264d35a825fce76f53a536', '0', '9', '2017-09-06 15:21:15.762', '227', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('98853f21db234a50a3264e8557c05fc6', '0', '0', '2017-09-06 15:21:17.941', '228', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('fd39b40f39f14809be020837049512b2', '0', '3', '2017-09-06 15:21:20.097', '229', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('082958cf873c422396630aacb6ec53d8', '0', '1', '2017-09-06 15:21:22.254', '230', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('6428a0e964a6404d9d9aa3a3ced67b5f', '0', '4', '2017-09-06 15:21:24.419', '231', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('378231bb1c7a4688a93790afccbc95a3', '0', '4', '2017-09-06 15:21:26.575', '232', '11', '261');
INSERT INTO "t_flow_info_data" VALUES ('ae38a8eb568a46c29a9e4c568c11b5ee', '0', '9', '2017-09-06 15:23:49.541', '233', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ab8d566e95ba4635be663cf788400a31', '0', '9', '2017-09-06 15:23:51.742', '234', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('683fc78f681643cc9b323562b2d3414d', '0', '3', '2017-09-06 15:23:53.932', '235', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('49678d06f779465eac05c538d48ae90e', '0', '3', '2017-09-06 15:23:56.116', '236', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('13d388631c4e40138d834308ca218ee5', '0', '3', '2017-09-06 15:23:58.299', '237', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3502b92714c34aafa09a5a01afaf6590', '0', '9', '2017-09-06 15:24:00.504', '238', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3c0140f7cdd74253bc07dcec0f7bc254', '0', '6', '2017-09-06 15:24:02.705', '239', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4e2e579acb0241cb9cf9591415684e9a', '0', '9', '2017-09-06 15:24:04.885', '240', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a8ea4c2648e54745ad84385803debb10', '0', '1', '2017-09-06 15:24:07.07', '241', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a59b55c3398443fdb69aab91318091b8', '0', '7', '2017-09-06 15:24:09.279', '242', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f1945d04b6c644318a357ce2ec8ba031', '0', '3', '2017-09-06 15:24:11.458', '243', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('658429f32b0f4fd682ce275d012785df', '0', '1', '2017-09-06 15:24:13.653', '244', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('71ec24edb5cc4575acd6eed6f4e01839', '0', '0', '2017-09-06 15:24:15.844', '245', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('219e2581da914528bb426e13f03f1a3d', '0', '7', '2017-09-06 15:24:18.029', '246', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ecb4e5edbb5f4a3ba962dbfe50c9875e', '0', '7', '2017-09-06 15:24:20.218', '247', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('89d90da99e544d91bb41e79273ea577d', '0', '3', '2017-09-06 15:24:22.402', '248', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f397da0aa4df405ca70b079890cf2724', '0', '6', '2017-09-06 15:24:24.588', '249', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('16e71effaaed4d118acc4a505d47a82c', '0', '3', '2017-09-06 15:24:26.8', '250', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3f251e247442493fb13adcd57d490b76', '0', '9', '2017-09-06 15:24:28.995', '251', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6d9fe4e46b5a46b6a4c72aac4b3fe67f', '0', '0', '2017-09-06 15:24:31.174', '252', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('df78b8699679410887d23d5c885eb917', '0', '9', '2017-09-06 15:24:33.352', '253', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4ba6e1c6dcb24d519351806b792220be', '0', '5', '2017-09-06 15:24:35.541', '254', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f6ff1243e16f4021ac0c139e9c16102b', '0', '6', '2017-09-06 15:24:37.728', '255', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9fa0c9d4e1e74cfe946f1ca94b14bf29', '0', '9', '2017-09-06 15:24:39.939', '256', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9787daa79f5242f39e6408eabc9a6f1c', '0', '0', '2017-09-06 15:24:42.137', '257', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ba1ce1157c8d49638bb8edfb16823b55', '0', '8', '2017-09-06 15:24:44.319', '258', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d82152ced3254230ba789cf4bcedcdcd', '0', '2', '2017-09-06 15:24:46.508', '259', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('27dc993160bd4e4099f035c0853a739f', '0', '8', '2017-09-06 15:24:48.698', '260', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('95470379ba1b42fab54d16be4e577352', '0', '9', '2017-09-06 15:24:50.877', '261', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0027a72bc07b440ca7c7ae082ed6e5bd', '0', '4', '2017-09-06 15:24:53.058', '262', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('05a17d4593614e41ac52f5416c789c29', '0', '4', '2017-09-06 15:24:55.253', '263', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e7348fb0bbb34de0b964a358e5a04deb', '0', '6', '2017-09-06 15:24:57.441', '264', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('cfc77d146656474b8ed53c504a6bb4e2', '0', '2', '2017-09-06 15:24:59.634', '265', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1b3c32c562014b75ada1495f41181bf2', '0', '4', '2017-09-06 15:25:01.83', '266', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('aefe348ebecc49f9ad5c9b4d4a8153eb', '0', '4', '2017-09-06 15:25:04.027', '267', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6919321268274e49b70ce6d959f73d54', '0', '0', '2017-09-06 15:25:06.2', '268', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('00f507f33b224a89ac3b9104944cfb39', '0', '2', '2017-09-06 15:25:08.392', '269', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1ad913dc0359471aa16564322e2c1a99', '0', '9', '2017-09-06 15:25:10.582', '270', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b9eeeb17c7254e59acb4d1c2eec78ac3', '0', '8', '2017-09-06 15:25:12.765', '271', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('29dafea9f5d64236b69e651a98cac13e', '0', '5', '2017-09-06 15:25:14.945', '272', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2bf51e9a1d0645b5958af6e1202ad3a9', '0', '5', '2017-09-06 15:25:17.132', '273', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('139e85c070ae4b08adf134d764010756', '0', '8', '2017-09-06 15:25:19.329', '274', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7fa0dc5ab5324c5bb1eec2a2acc6d67c', '0', '9', '2017-09-06 15:25:21.515', '275', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('eb4f55706eea4837bd2888a3b2976372', '0', '8', '2017-09-06 15:25:23.689', '276', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4d581954e30d4136933833b6a75606f2', '0', '9', '2017-09-06 15:25:25.881', '277', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7a31ab843f2644e79d52011ecc67b519', '0', '1', '2017-09-06 15:25:28.067', '278', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e2a732b592ea48109cbddc27309d656d', '0', '9', '2017-09-06 15:25:30.241', '279', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('680bf00425584ef7baae3a5d68faf9aa', '0', '4', '2017-09-06 15:25:32.422', '280', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('82fa5aa1743e4166a86217e0aef55f91', '0', '3', '2017-09-06 15:25:34.617', '281', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('137e311320a54eeeb49f41d7ebab361c', '0', '9', '2017-09-06 15:25:36.795', '282', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('573b9402bc574aa39a04a8a990dd88cd', '0', '7', '2017-09-06 15:25:38.972', '283', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5094fd234613498587b8cd903c07c7d3', '0', '1', '2017-09-06 15:25:41.148', '284', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4b99439799a54b5b8118d8c6b7611962', '0', '3', '2017-09-06 15:25:43.326', '285', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('72792726fc3f42d4b8cae9d7fbf4ac88', '0', '9', '2017-09-06 15:25:45.529', '286', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a4f8ff7d8ba04bcd80e8aae51d5f5eb5', '0', '3', '2017-09-06 15:25:47.715', '287', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a4cebe0fcb594c4890bffeffabb00c93', '0', '4', '2017-09-06 15:25:49.892', '288', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('35273ae4073940e98dc8684d729695ba', '0', '7', '2017-09-06 15:25:52.071', '289', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1e20dadf9c9149449101de87a7906da3', '0', '6', '2017-09-06 15:25:54.248', '290', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('16781c31d48648859729dd03e0cdbbd5', '0', '3', '2017-09-06 15:25:56.499', '291', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('370e10d60b9e4c719e69b7d0efeaeef7', '0', '4', '2017-09-06 15:25:58.79', '292', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('70924ec9362643b7af6ef879d7f5494a', '0', '0', '2017-09-06 15:26:00.966', '293', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a5d4fabe17c9433db8b7af6028ecfd85', '0', '4', '2017-09-06 15:26:03.11', '294', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7a88dc5d739f489f879f311a68d5b282', '0', '4', '2017-09-06 15:26:05.281', '295', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('194860c2e63f4f3f9e06676ab41f7733', '0', '8', '2017-09-06 15:26:07.421', '296', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0264bffb6a7c40e490c5a0a6c37c3494', '0', '7', '2017-09-06 15:26:09.602', '297', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('729f4a6f32624917847692853e045a8d', '0', '7', '2017-09-06 15:26:11.748', '298', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9c55602473d047ceaa39e0d9feee03bd', '0', '2', '2017-09-06 15:26:13.924', '299', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9381025b88c94ef1b2d5038fedb54aaf', '0', '4', '2017-09-06 15:26:16.056', '300', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c9c3e6c50aee4facad05efe2b1d5c7d4', '0', '1', '2017-09-06 15:26:18.233', '301', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c84e7fbfd1ac4c94b9af9eb418225417', '0', '8', '2017-09-06 15:26:20.373', '302', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('24e03dbac007494394b6d40065bf73fb', '0', '8', '2017-09-06 15:26:22.543', '303', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2a98faf1bb2f47ec913c359da6d89055', '0', '0', '2017-09-06 15:26:24.716', '304', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('175e9a0d4ed74e2393f1be39dbd50e1e', '0', '8', '2017-09-06 15:26:26.887', '305', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a6fb7c4839404b00ab4b13c5c4338d3f', '0', '8', '2017-09-06 15:26:29.083', '306', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('dfac4623e5264751bf3b1d9d68ac989f', '0', '8', '2017-09-06 15:26:31.256', '307', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f08fb56400084412b077deb7c07aa39c', '0', '8', '2017-09-06 15:26:33.431', '308', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('46b9a06b7c664222bce9a00c2ce6190a', '0', '1', '2017-09-06 15:26:35.611', '309', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4fb8e88f1a3e4b20b1c0b1d1014187b4', '0', '0', '2017-09-06 15:26:37.78', '310', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('07702d2a7fe14a459a7759b95edf3906', '0', '5', '2017-09-06 15:26:39.952', '311', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('95ba8b012bb247aa9bb59fe2ed8cff42', '0', '9', '2017-09-06 15:26:42.127', '312', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d7e1226ec8c343e48b52fccb6e260bf3', '0', '0', '2017-09-06 15:26:44.31', '313', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4e1b0805a919484b9469c215e7e2ea9d', '0', '2', '2017-09-06 15:26:46.477', '314', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1ced3ec973f041e381db927a9a1a342d', '0', '2', '2017-09-06 15:26:48.647', '315', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('578b2230c79e4ca6a8f306340c2861eb', '0', '9', '2017-09-06 15:26:50.844', '316', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ebf9c703da97497fbafa900061727595', '0', '0', '2017-09-06 15:26:53.019', '317', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('da8b552918db405695d95d9417f496f7', '0', '9', '2017-09-06 15:26:55.203', '318', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f0c759a701e444fabb57bdf29e8b4436', '0', '6', '2017-09-06 15:26:57.381', '319', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bdc5c7de1ac94fc092bc99c3af2c77e0', '0', '8', '2017-09-06 15:26:59.547', '320', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7bdf6ef77c484c63b5bc98c10d94a3cb', '0', '9', '2017-09-06 15:27:01.723', '321', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c70484a5b2474f70bed91f531aa210bd', '0', '5', '2017-09-06 15:27:03.901', '322', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d3c0ba99ae794535ab4711aebcc900e9', '0', '7', '2017-09-06 15:27:06.077', '323', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1e8b9cade74f4cfb8aba6897a8036372', '0', '4', '2017-09-06 15:27:08.255', '324', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('aa4ff0cf272745308ef22e1c5c4c73c5', '0', '6', '2017-09-06 15:27:10.43', '325', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('31fca3de64e9449790d973dc0df7831e', '0', '0', '2017-09-06 15:27:12.596', '326', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a0a592a4c2074bab957d58677a1c86d8', '0', '3', '2017-09-06 15:27:14.78', '327', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('44fc5503e83649e0888ed55d6eb4e686', '0', '3', '2017-09-06 15:27:16.954', '328', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('70767558746f40a1bd8573a9e93ef12f', '0', '4', '2017-09-06 15:27:19.132', '329', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0c4b248c96684bfa8fe877e3553c8fff', '0', '3', '2017-09-06 15:27:21.301', '330', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e35c7dacd86047edb98fbb9acfea3550', '0', '2', '2017-09-06 15:27:23.472', '331', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6728d225146a4232807b22bcef5626f4', '0', '4', '2017-09-06 15:27:25.637', '332', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('adaabb179cbc4d8bb9156ef563d895fc', '0', '7', '2017-09-06 15:27:27.814', '333', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b10b1e9dbad6490fabb419c8139c7112', '0', '5', '2017-09-06 15:27:29.99', '334', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('06195f9f0d734153a402e0a4be235313', '0', '6', '2017-09-06 15:27:32.177', '335', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7560d8ceddaa4adab54f0fccbcaf4588', '0', '8', '2017-09-06 15:27:34.343', '336', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0146e4f0c5364572b1140404d9ba912c', '0', '9', '2017-09-06 15:27:36.516', '337', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c4b53f78e6a74763af785811e31bb6ed', '0', '8', '2017-09-06 15:27:38.726', '338', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0ee5f8f79b86438f9f1085044329bc52', '0', '2', '2017-09-06 15:27:40.901', '339', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('de5784c5c4fa46079d5db78359ef4edb', '0', '4', '2017-09-06 15:27:43.07', '340', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d89a23ccb3154d2982f6fbea0e125916', '0', '3', '2017-09-06 15:27:45.242', '341', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0957557068174ce6b435b46ad1eab248', '0', '9', '2017-09-06 15:27:47.403', '342', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5573447df7f84494a3d37fee3633c213', '0', '3', '2017-09-06 15:27:49.577', '343', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0652320bd0aa4e819941f477b134f4f1', '0', '9', '2017-09-06 15:27:51.761', '344', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ecf69f03bd4244c9b59ffde943ccdbba', '0', '8', '2017-09-06 15:27:53.944', '345', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c90da72b05964d0f80643c6f6f625fb5', '0', '9', '2017-09-06 15:27:56.119', '346', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('95b243148f1043a693a39f5ac73e87d9', '0', '8', '2017-09-06 15:27:58.301', '347', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b36b7b6b8b2a47c584b321753722b7c8', '0', '7', '2017-09-06 15:28:00.474', '348', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7376463e450a4d1aa485198d72a7591b', '0', '2', '2017-09-06 15:28:02.651', '349', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9c08ac56608544c090295e6bd3cea1ab', '0', '6', '2017-09-06 15:28:04.834', '350', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('08a21694b8b24736a0e91475f8df5547', '0', '8', '2017-09-06 15:28:07.011', '351', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9b27e234efd5492cb5200340e0b913c6', '0', '1', '2017-09-06 15:28:09.19', '352', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1728f55e28e24c3f85679369ce67a406', '0', '2', '2017-09-06 15:28:11.373', '353', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6c50ce7bb68a46fd9e8edcf035dc2660', '0', '9', '2017-09-06 15:28:13.542', '354', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d751cd6adf2341b58f08eaa3a3eb104c', '0', '2', '2017-09-06 15:28:15.714', '355', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('479b15428f914990b5cc2b6e953319ee', '0', '7', '2017-09-06 15:28:17.882', '356', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5e8790e603b84c63a43227573ba82596', '0', '1', '2017-09-06 15:28:20.05', '357', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a510105cb6b54598b6212bbf0b5a95c3', '0', '0', '2017-09-06 15:28:22.221', '358', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('927ef372bbfa49c599316fa8cd9e3b0c', '0', '0', '2017-09-06 15:28:24.404', '359', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5cf709db8b3f484ba800fe1a064657e8', '0', '6', '2017-09-06 15:28:26.576', '360', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('47f474d136a34fcbb87538dfabae78fd', '0', '1', '2017-09-06 15:28:28.764', '361', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('548a70809b71484ca1823e482626f05f', '0', '7', '2017-09-06 15:28:30.942', '362', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('66d5b3c55e5d4bac9e927a2027382bcb', '0', '4', '2017-09-06 15:28:33.115', '363', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9ed47221ff554303b3d192390079c222', '0', '5', '2017-09-06 15:28:35.28', '364', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2fc03313af9549a48f2ac6786908e098', '0', '8', '2017-09-06 15:28:37.461', '365', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b8e6928e6325485e8bd13cdbfc430b4a', '0', '4', '2017-09-06 15:28:39.635', '366', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0c77a09bf9d6484982a73408d2ca7308', '0', '5', '2017-09-06 15:28:41.814', '367', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3d7badb206d84a66a4b612f780dd8259', '0', '0', '2017-09-06 15:28:44.008', '368', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4cf1d48d5e704644bc746f3966117918', '0', '6', '2017-09-06 15:28:46.179', '369', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c6e8e1be80db4afbab34f23791588872', '0', '8', '2017-09-06 15:28:48.363', '370', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('68833586debd423cba2d5bda2840d757', '0', '1', '2017-09-06 15:28:50.545', '371', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3db2d4fe386146b9808a7e0b779057f8', '0', '6', '2017-09-06 15:28:52.726', '372', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5bfc33c30b26480984f79ed097d67588', '0', '7', '2017-09-06 15:28:54.902', '373', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3348a3f508de4d89b2489bd066a6efd5', '0', '3', '2017-09-06 15:28:57.085', '374', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6c05a32626514c05b8b3a4d26f1715a5', '0', '0', '2017-09-06 15:28:59.258', '375', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('552f4de210ae45e59abd2fdf6094e29e', '0', '8', '2017-09-06 15:29:01.431', '376', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('13c11681221347dd9c367ff4b7cf9820', '0', '1', '2017-09-06 15:29:03.608', '377', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9ef0dec296564fb693405e226359a461', '0', '4', '2017-09-06 15:29:05.786', '378', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e35f2ce55a1145938a699d7c57c194ab', '0', '1', '2017-09-06 15:29:07.959', '379', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('004a37ad732344dd9b8eb724347b62cf', '0', '4', '2017-09-06 15:29:10.141', '380', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3990e328d4a54878b6930e80fd6d3cff', '0', '6', '2017-09-06 15:29:12.318', '381', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('42cb84ad34854a65baeb1f07ebf0c7f6', '0', '0', '2017-09-06 15:29:14.495', '382', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('08013f0ed2714a5aa5e0f4327dee96a6', '0', '7', '2017-09-06 15:29:16.693', '383', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('00feae2537534fb99b4bd65ad3415aa8', '0', '6', '2017-09-06 15:29:18.874', '384', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9a29bbba2e7643318fc85cb4833f7dfc', '0', '8', '2017-09-06 15:29:21.053', '385', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('adf49071440d46cd950fca1d189278b6', '0', '1', '2017-09-06 15:29:23.238', '386', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d8de3e7ad82542148aa579f750a8b053', '0', '9', '2017-09-06 15:29:25.41', '387', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0b4104d2bbce4f3593ccbc59384d29d4', '0', '0', '2017-09-06 15:29:27.584', '388', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8da82ccfffa344d9925558bd4b93667d', '0', '7', '2017-09-06 15:29:29.763', '389', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('427db6f155ae403095053649b3746fb2', '0', '1', '2017-09-06 15:29:31.947', '390', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f8551ac4639e410ebc748b119cb27ed7', '0', '6', '2017-09-06 15:29:34.125', '391', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('92a243bed1434079bad4e513658f5763', '0', '9', '2017-09-06 15:29:36.312', '392', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('cc2cd9cf850d4f989b558e2f3a64fd1e', '0', '4', '2017-09-06 15:29:38.479', '393', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2ff26ad01d984603a5228bd18e169264', '0', '7', '2017-09-06 15:29:40.653', '394', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1525d2a003ec4e7b9285eb1738786991', '0', '6', '2017-09-06 15:29:42.84', '395', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1c35678610764bb0bc11f3f61f4f319c', '0', '7', '2017-09-06 15:29:45.021', '396', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f0bf2465fb5740cc8614c805f360699e', '0', '5', '2017-09-06 15:29:47.205', '397', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b6b2a2f0f2d944a98a6703676b6844fb', '0', '5', '2017-09-06 15:29:49.388', '398', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e59d969ab4974b7f872aa38036052b37', '0', '6', '2017-09-06 15:29:51.57', '399', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1bc5a80c76234c8d958f0e9cfa05fb61', '0', '3', '2017-09-06 15:29:53.744', '400', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f0175b73b028468a9473c0ed8f4ffd9c', '0', '2', '2017-09-06 15:29:55.917', '401', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fc9a90fd62fc42bd9c0ec00334b4452f', '0', '4', '2017-09-06 15:29:58.091', '402', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b1e819bf37ef4e6da4b6cadc446af41b', '0', '3', '2017-09-06 15:30:00.28', '403', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1360d0a896e84efaae8c81dc477ca9c7', '0', '1', '2017-09-06 15:30:02.458', '404', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('916395264c044d4c9a8fe0462cfd0f6a', '0', '8', '2017-09-06 15:30:04.633', '405', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c54a38afc2484aedb631c36e1b17c5a4', '0', '9', '2017-09-06 15:30:06.805', '406', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('55919dbf4d694661b51c985fcbeaf028', '0', '2', '2017-09-06 15:30:08.987', '407', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('818eef86a79d4ef1b6240ed7a0a92d9c', '0', '5', '2017-09-06 15:30:11.161', '408', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c6580991e7544e74a80b1b4ad70692d4', '0', '1', '2017-09-06 15:30:13.34', '409', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4aeba886a2ab4693a6eeaa511a1e8650', '0', '7', '2017-09-06 15:30:15.537', '410', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('50d137b511714d34802f5e486a4da461', '0', '3', '2017-09-06 15:30:17.719', '411', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('123ae260552243b5baf30f32a02beefe', '0', '1', '2017-09-06 15:30:19.892', '412', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2f36a1e693fa44148d424d4e27baf570', '0', '2', '2017-09-06 15:30:22.068', '413', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('41e239e11f12437aa80792e8990945aa', '0', '2', '2017-09-06 15:30:24.241', '414', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8ff7deffb1a64083b51f54bfef09c05f', '0', '4', '2017-09-06 15:30:26.42', '415', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bd011b346467499885d5932ace19c8d4', '0', '4', '2017-09-06 15:30:28.596', '416', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1f50e74bea9c4495910f756bfd4747d2', '0', '9', '2017-09-06 15:30:30.775', '417', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('08b34af36a2743f290d216a96c239487', '0', '4', '2017-09-06 15:30:32.955', '418', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('285e4c146ebd4c659e474eb31e800b1a', '0', '4', '2017-09-06 15:30:35.131', '419', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f5771313e58d4c57be8b313f8485ac28', '0', '2', '2017-09-06 15:30:37.307', '420', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('65a907ee262a42eb93d7d03df8e6a8e8', '0', '9', '2017-09-06 15:30:39.476', '421', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('04c7fe1cde254d728255b2e16403b0f8', '0', '4', '2017-09-06 15:30:41.668', '422', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e3535971b97d40c3b96f02409043f137', '0', '4', '2017-09-06 15:30:43.838', '423', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2d6c4abfa90b4edaaafac79aa29fe064', '0', '8', '2017-09-06 15:30:46.012', '424', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('edd91e30a98f46c793e99317e07bdec6', '0', '1', '2017-09-06 15:30:48.193', '425', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1b77b3cb264c4644b43588b4c7af178c', '0', '5', '2017-09-06 15:30:50.372', '426', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b02ca851f0c94d0b83f40338f156a860', '0', '2', '2017-09-06 15:30:52.548', '427', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('80f68176f6454196b1cf4bf8938748f2', '0', '5', '2017-09-06 15:30:54.721', '428', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3e92d30992654929b44e2eb5f7f6374c', '0', '9', '2017-09-06 15:30:56.919', '429', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3ba4aa1f68d743a282dec6ac55405048', '0', '3', '2017-09-06 15:30:59.094', '430', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('216a8c5d2fcc40e0a40c29c1adb09006', '0', '0', '2017-09-06 15:31:01.27', '431', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b8917d3a9ea349aaa23e4139bb4b9fb0', '0', '4', '2017-09-06 15:31:03.462', '432', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a1a0fd00ff9f41e7ad254d470e022de8', '0', '7', '2017-09-06 15:31:05.634', '433', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('927fd72918614b0e8ba75955f9d10e75', '0', '8', '2017-09-06 15:31:07.808', '434', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('522a82437266464b99c25da3ab4f13ce', '0', '6', '2017-09-06 15:31:09.982', '435', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('48b1c0f6cb3041a38ccf8097f54b716a', '0', '4', '2017-09-06 15:31:12.154', '436', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('05d1bbefc56f4511acd8c4928ee62ee8', '0', '3', '2017-09-06 15:31:14.327', '437', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7221d7aacafd4163b06120f76472ac19', '0', '7', '2017-09-06 15:31:16.5', '438', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7c6357d097d944f786f3f1326642416a', '0', '6', '2017-09-06 15:31:18.672', '439', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9f2da44181d04035825d7947f9898abd', '0', '0', '2017-09-06 15:31:20.846', '440', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c1aff8781dc54bee9eafbf236baa01ee', '0', '5', '2017-09-06 15:31:23.018', '441', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('88800f8d20a64a95b50f20e6dc4bd077', '0', '9', '2017-09-06 15:31:25.201', '442', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b8115139ea644a168070ca61194f867f', '0', '2', '2017-09-06 15:31:27.376', '443', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1f8bcc33bbeb42df99abf1e5d1180131', '0', '0', '2017-09-06 15:31:29.561', '444', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('98cea526975a4e78b619c50733b10ee6', '0', '2', '2017-09-06 15:31:31.734', '445', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e33a001a1d784ab3b560fd363a9b4485', '0', '4', '2017-09-06 15:31:33.914', '446', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4b9291fc382c45b28036d152e43866ca', '0', '0', '2017-09-06 15:31:36.095', '447', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('30dbc6036849470b8e365e9afca20ee3', '0', '1', '2017-09-06 15:31:38.272', '448', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('09f8e3d9e4424c09b80feecf06f216ba', '0', '9', '2017-09-06 15:31:40.447', '449', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2e3c366e8a1d41dabafe1cc1b3e82df7', '0', '7', '2017-09-06 15:31:42.645', '450', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e4e05ee733c04c9bbd1d3877d5ff10a4', '0', '0', '2017-09-06 15:31:44.82', '451', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('efa996f82a33430187229e9f3fd9b58b', '0', '0', '2017-09-06 15:31:47.004', '452', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0594ed4ed049474e9b1b05c1e82754b6', '0', '8', '2017-09-06 15:31:49.177', '453', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8af3eb965a8745c685151c99e11e6862', '0', '5', '2017-09-06 15:31:51.357', '454', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a988e5b2086d40a88b01a6c7430e2989', '0', '9', '2017-09-06 15:31:53.538', '455', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('245d2e110aa34f90ba8d837409530bde', '0', '7', '2017-09-06 15:31:55.718', '456', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b2d689c77a3740a0841f45d591a54a31', '0', '6', '2017-09-06 15:31:57.899', '457', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ec8d01151811441b82b7df1f3f645c44', '0', '0', '2017-09-06 15:32:00.088', '458', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f5176b17b1e849ffa62c593ddef170ea', '0', '2', '2017-09-06 15:32:02.266', '459', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('26621d41bdbd4da4a687ba30441f1026', '0', '8', '2017-09-06 15:32:04.439', '460', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9039eef7807c43a3beb7c2a536eb586f', '0', '8', '2017-09-06 15:32:06.61', '461', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9458313b56094bfe979763c5e89964d3', '0', '1', '2017-09-06 15:32:08.78', '462', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6cb0b6325fc3455db4042f8d591c9813', '0', '6', '2017-09-06 15:32:10.952', '463', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('75ec4dfeb1bf411890022fd039f12e3c', '0', '2', '2017-09-06 15:32:13.128', '464', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('84f51ed4fdac47348f56ad32c67e569a', '0', '1', '2017-09-06 15:32:15.303', '465', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e6a1d9937a3e4e56b7d668ffd9cdac78', '0', '9', '2017-09-06 15:32:17.479', '466', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('99d8d7e520cc490d99e855ca96d2002e', '0', '9', '2017-09-06 15:32:19.662', '467', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3204c7dbb4be4129b12a0081603c11dd', '0', '2', '2017-09-06 15:32:21.851', '468', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('67fe797893654cb4b354e794d7eba506', '0', '4', '2017-09-06 15:32:24.027', '469', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6db6233ea5dc416ebba2cf6e5f93baae', '0', '4', '2017-09-06 15:32:26.201', '470', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c3f439e6254841bc8b562e4965456af7', '0', '6', '2017-09-06 15:32:28.38', '471', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e7a0880d848e43aaa15aef2d2717e48b', '0', '5', '2017-09-06 15:32:30.553', '472', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3e87f2b6498e48d49fadb5d1c55c0f65', '0', '2', '2017-09-06 15:32:32.737', '473', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ebf5ada538c84025ae573e439fe35b37', '0', '8', '2017-09-06 15:32:34.909', '474', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0b320c57506e4556b11b3044ff1165a8', '0', '2', '2017-09-06 15:32:37.174', '475', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('78eef85d81d247c7990a74fb7eb9a455', '0', '2', '2017-09-06 15:32:39.341', '476', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('138938bcee044ecda08d27881a16a4fc', '0', '6', '2017-09-06 15:32:41.531', '477', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('51258f6872834a43a454100a76fed068', '0', '9', '2017-09-06 15:32:43.704', '478', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('98cf1f316d9f44faab895a9f0b2e7730', '0', '6', '2017-09-06 15:32:45.897', '479', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('211468db1d8948d7affa8cdc7c4857dc', '0', '4', '2017-09-06 15:32:48.083', '480', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fac532017a8848359915c5c6797da6f8', '0', '2', '2017-09-06 15:32:50.266', '481', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e2aef94c93da43ffb9f6dc3f7185c3da', '0', '6', '2017-09-06 15:32:52.447', '482', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('64b2d26daad247f5b76c6532cfc20c35', '0', '5', '2017-09-06 15:32:54.619', '483', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a517a05c68c444129c607ccc9ab4dc96', '0', '0', '2017-09-06 15:32:56.792', '484', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('605a84ec443d4c738906e50ca150ee22', '0', '8', '2017-09-06 15:32:58.962', '485', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b91e4427b5234cc89a34e47f7a411865', '0', '0', '2017-09-06 15:33:01.163', '486', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8fcc32c0703e47b1a5de9d6638f9643f', '0', '8', '2017-09-06 15:33:03.337', '487', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e32f41c53ce34241a9953788a310c659', '0', '0', '2017-09-06 15:33:05.523', '488', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f3f0a7e62c55424c88ab1a9d9e05325a', '0', '4', '2017-09-06 15:33:07.706', '489', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('393fba92f1514c44aec74180046370cc', '0', '5', '2017-09-06 15:33:09.885', '490', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4f030dcbc164415daf2f95aecae5351e', '0', '8', '2017-09-06 15:33:12.161', '491', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('daecb71f44ba4a02b3f25a0060d1e005', '0', '3', '2017-09-06 15:33:14.44', '492', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f442915e46414d889edc80ff8738bece', '0', '0', '2017-09-06 15:33:16.59', '493', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b858800eb56846109f9696a7cbf27a72', '0', '4', '2017-09-06 15:33:18.715', '494', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f900462685fc4307bcb92fc30d3eebce', '0', '6', '2017-09-06 15:33:20.912', '495', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('32cbd60db6bf40daba42bafdbff3fd4b', '0', '9', '2017-09-06 15:33:23.081', '496', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b4c6c6417b454e618c06c1c930d1f2d7', '0', '5', '2017-09-06 15:33:25.256', '497', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('12ee23ab322d494e9104f7ed56275a68', '0', '7', '2017-09-06 15:33:27.505', '498', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e5d43b7b4e8a4fc5a1958792e0a91f47', '0', '2', '2017-09-06 15:33:29.987', '499', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e109dc92c0524826b0c0a72cf1addc9f', '0', '4', '2017-09-06 15:33:32.594', '500', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('39f125d107524022af52d6bd058f2812', '0', '7', '2017-09-06 15:33:34.785', '501', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b5d534a896e4434b9ab75376022754aa', '0', '3', '2017-09-06 15:33:37.023', '502', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b48292a268c746c092a29c139aebe9ab', '0', '3', '2017-09-06 15:33:39.206', '503', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('67d9f21b8abb4e449bd631a5acf5bc33', '0', '3', '2017-09-06 15:33:41.423', '504', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c7c38af7c6b54eab8faa0d9f541e129e', '0', '2', '2017-09-06 15:33:43.603', '505', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('51a96053328b4460843c1e29a3fb22e9', '0', '0', '2017-09-06 15:33:45.874', '506', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9d9d0cb27b3f44f987cf58ea6535b545', '0', '1', '2017-09-06 15:33:48.385', '507', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c71759c49be14005a97dd7b6dafb84cb', '0', '5', '2017-09-06 15:33:50.632', '508', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f6eec350d29846ce80d67c6d531f00c8', '0', '2', '2017-09-06 15:33:52.901', '509', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a84a2254f5bf467fb76773b9651bbe01', '0', '0', '2017-09-06 15:33:55.166', '510', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1109a1eea6ff4080ae1c65ad99a17fcf', '0', '2', '2017-09-06 15:33:57.391', '511', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ac48e9afeb594cf0b9a7cb0e48ae078c', '0', '7', '2017-09-06 15:33:59.627', '512', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e8e67b96d2094a948743eb4641d5405d', '0', '2', '2017-09-06 15:34:01.869', '513', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ace11b4e852f4750989c5ff52667210d', '0', '7', '2017-09-06 15:34:04.164', '514', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4a461afe41774fbaac8a103b3fac3b1e', '0', '9', '2017-09-06 15:34:06.403', '515', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('44233453197c40b7b2cbd474195b731b', '0', '7', '2017-09-06 15:34:08.582', '516', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c91d193fbcab4414ae3312201d44f975', '0', '8', '2017-09-06 15:34:10.756', '517', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6efe109c73774154960e0a8ae94a5129', '0', '5', '2017-09-06 15:34:12.973', '518', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ebf7808101ad4c519e6075aefe373e5d', '0', '9', '2017-09-06 15:34:15.193', '519', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8a6e70a8d9334691a14953edaf84e2a1', '0', '8', '2017-09-06 15:34:17.371', '520', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('21d9b7cac07241f6953b041fb64158ec', '0', '9', '2017-09-06 15:34:19.579', '521', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3bc25958e27c4a819bf38fbcaa568670', '0', '6', '2017-09-06 15:34:21.748', '522', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6a18c930b25d417091a7712b79b2efe3', '0', '6', '2017-09-06 15:34:23.965', '523', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('446cb42d298745c895001fbdbf325aca', '0', '8', '2017-09-06 15:34:26.148', '524', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('796ef0b739e84ff98ba98a4d3babec8a', '0', '0', '2017-09-06 15:34:28.442', '525', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fcc0589f94834eb7a51720efba0f961c', '0', '9', '2017-09-06 15:34:30.664', '526', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f844de546990437abbaf89d0877e4c8b', '0', '6', '2017-09-06 15:34:32.823', '527', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('575b2a77dc3b4235972125029d93af12', '0', '4', '2017-09-06 15:34:35.062', '528', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c14e1e767e174b1abd15cb6a520048ef', '0', '0', '2017-09-06 15:34:37.237', '529', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8d92c33bfa734bc69fe14af108b2240c', '0', '4', '2017-09-06 15:34:39.434', '530', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1be143bdc0384fd9b191bf9c839ad79d', '0', '4', '2017-09-06 15:34:41.684', '531', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8d513141d5c94663a39a84ecd5f8dee2', '0', '0', '2017-09-06 15:34:43.946', '532', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f398e15ce76f4d48a35fd842a3cc8e92', '0', '7', '2017-09-06 15:34:46.16', '533', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bcd35405ee934b9bbe890201c3afa89f', '0', '2', '2017-09-06 15:34:48.484', '534', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('01354ae8858c4fc69430710825214627', '0', '8', '2017-09-06 15:34:50.657', '535', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f5eeb50188b34f89a7fddeae945a95d7', '0', '0', '2017-09-06 15:34:52.847', '536', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4a6102c199914498bf0f639d4e954b84', '0', '5', '2017-09-06 15:34:55.017', '537', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f4f1664fa0ca47db9de30dbe7261c990', '0', '8', '2017-09-06 15:34:57.18', '538', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e15b485321614e969a856c39bc299279', '0', '0', '2017-09-06 15:34:59.339', '539', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('78536ccde50c45ff80eff729002aea47', '0', '5', '2017-09-06 15:35:01.494', '540', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b94f4f8a060749e2bf7e58c9b7af5789', '0', '8', '2017-09-06 15:35:03.654', '541', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4485faf6f13c459597ae3bdc3b85c74f', '0', '4', '2017-09-06 15:35:05.813', '542', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('be739dc6acb34990a16cdc9a0e9ad552', '0', '5', '2017-09-06 15:35:07.998', '543', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4b20bbab312f47beb6c6d09d330492eb', '0', '7', '2017-09-06 15:35:10.151', '544', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a8f5e30a6b4d49fcb0a8a816a744860a', '0', '6', '2017-09-06 15:35:12.314', '545', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5eaa8118371a4f66a313927f14104644', '0', '1', '2017-09-06 15:35:14.458', '546', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0af4ed52f1ad4454b6bb311a2592812e', '0', '4', '2017-09-06 16:02:27.918', '547', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('862f611293ff473abc8160f1bb04876c', '0', '6', '2017-09-06 16:02:30.36', '548', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3be8fafacec946879af83e9e8c6c83c9', '0', '7', '2017-09-06 16:02:32.714', '549', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a34806c805c3405d83fdc4a70642483d', '0', '4', '2017-09-06 16:02:35.073', '550', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('594d4cc51e184ef48879896d563e9b4c', '0', '5', '2017-09-06 16:02:37.228', '551', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9efd51d35e78458dbc9955178dcccfb3', '0', '8', '2017-09-06 16:02:39.48', '552', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('19c3cf0425f0456f952b8ec29736a184', '0', '8', '2017-09-06 16:02:41.664', '553', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f4e25b865e34426ba94a7591c638c6ef', '0', '4', '2017-09-06 16:02:43.812', '554', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bb1b123e50cb400c9fc891499e2621d9', '0', '2', '2017-09-06 16:02:45.984', '555', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b6a1ae9d58ed4789817a1ed97352631c', '0', '4', '2017-09-06 16:02:48.28', '556', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1e2a3ebe15eb468e989b808de622511b', '0', '3', '2017-09-06 16:02:50.497', '557', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0d3025962c0a4288a6a8204e7dfc8dd4', '0', '0', '2017-09-06 16:02:52.77', '558', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a80178652f774e708577f08be46ae358', '0', '6', '2017-09-06 16:02:55.025', '559', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6f050456171a450a8adf2d55d928d691', '0', '9', '2017-09-06 16:02:57.434', '560', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4715eb175d1e4ab086bc4ebb65d82a1e', '0', '4', '2017-09-06 16:02:59.858', '561', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f929de43f5b44847bb65ad2a5ca1441d', '0', '3', '2017-09-06 16:03:02.294', '562', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7b2f7310487b4257a916f3fc68d39e22', '0', '9', '2017-09-06 16:03:04.475', '563', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('849a4568380f4f85b6aad21a1ca3543c', '0', '0', '2017-09-06 16:03:09.244', '564', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a4cf981ea356494786aa0518a8c62b07', '0', '8', '2017-09-06 16:03:16.229', '565', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('67598a8eb2534b3387790f9433099a8d', '0', '7', '2017-09-06 16:03:18.48', '566', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('eb198d1c841d466fb47880d3043c822f', '0', '7', '2017-09-06 16:03:20.694', '567', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ad61c0d1c99b476286a681bc8366097e', '0', '3', '2017-09-06 16:03:23.088', '568', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8f67f8e614f64c02846ebedd4d9ca2eb', '0', '0', '2017-09-06 16:04:21.062', '569', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a0e64d74d67b4a009dded862cb60ce85', '0', '5', '2017-09-06 16:04:24.1', '570', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7f26bb6204c745b59bc7f204a09a962e', '0', '7', '2017-09-06 16:05:45.327', '571', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6d9d91879afb4b7eaa701c218b184b6f', '0', '0', '2017-09-06 16:07:06.168', '572', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f2253a5a6aa74bbe9a2ba3210bee8477', '0', '7', '2017-09-06 16:07:06.352', '573', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('36e45d8511e649a9927db5645dcfacc2', '0', '7', '2017-09-06 16:07:06.762', '574', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a880f47525b54bc4bff592983cb8c626', '0', '7', '2017-09-06 16:07:06.828', '575', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('81561676ccc543a3b7dc3467fefec84e', '0', '0', '2017-09-06 16:07:06.89', '576', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a8e4be706b5e418497f90d5cf3c87bf1', '0', '5', '2017-09-06 16:10:46.904', '577', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a5164fb859bd45b285c78389de757499', '0', '7', '2017-09-06 16:10:52.259', '578', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fd3d1541f53847e2a50612408a0ffd35', '0', '8', '2017-09-06 16:10:52.852', '579', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ea02248cb68b4dc781dd134804e97d3c', '0', '3', '2017-09-06 16:10:55.017', '580', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fc8cbe9334e34b2797105abd6b08c6df', '0', '7', '2017-09-06 16:10:57.171', '581', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ce4620de2ef24dba8654edf9a079e27f', '0', '0', '2017-09-06 16:10:59.323', '582', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0ae13ce024214f32aa0cf2a8bdc06037', '0', '2', '2017-09-06 16:11:01.479', '583', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('38b1583eb2364e3297851e31e7dcbd45', '0', '2', '2017-09-06 16:11:09.748', '584', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b108eed3cd9640189713a606c656cfe8', '0', '3', '2017-09-06 16:11:11.94', '585', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('19412c449732461eb01f9e86493a307b', '0', '4', '2017-09-06 16:11:14.094', '586', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bef51882a60e40748c79e9f4d102dcc7', '0', '8', '2017-09-06 16:11:16.261', '587', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0798238a5bf047ec905f75120eed38ce', '0', '7', '2017-09-06 16:11:18.446', '588', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('34f8470dfe2d4e6aba52cd8ad5e3d678', '0', '7', '2017-09-06 16:11:20.637', '589', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('523d28b03afb409eaa0a6da2062f7990', '0', '4', '2017-09-06 16:11:22.818', '590', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f97cac2b15ff44d592009e6f34d19929', '0', '0', '2017-09-06 16:11:24.965', '591', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('55a150e825554fe5953f29039a1ac4cf', '0', '8', '2017-09-06 16:11:27.178', '592', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('da3174a673d5465f9c70561dfcc8c0e3', '0', '0', '2017-09-06 16:11:29.383', '593', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('86e1e42f2f75457db39aacb2a9725e8a', '0', '3', '2017-09-06 16:11:31.549', '594', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('11dea07c655d4ff081c8c2af5ef97f76', '0', '3', '2017-09-06 16:11:42.859', '595', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ae3ddc3b828c4362b510c9223615c78e', '0', '8', '2017-09-06 16:11:45.001', '596', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('760561f1d93649ab89e97d4c45eb71c9', '0', '9', '2017-09-06 16:11:47.153', '597', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3663bf48bd2f43a1a6b7a155c907ad4b', '0', '2', '2017-09-06 16:11:49.297', '598', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bd2f8948be3142c4b34941d22c67b32a', '0', '4', '2017-09-06 16:11:51.448', '599', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6b468106458b4bdeb02b3ea679536177', '0', '3', '2017-09-06 16:11:53.598', '600', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3e4d8730b10245c39256a00444407612', '0', '8', '2017-09-06 16:11:55.746', '601', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('79e72481b8f849fd9cef4f890064051f', '0', '9', '2017-09-06 16:11:57.874', '602', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c6a23e0de0ca44a899e4ef5963ac5a1e', '0', '6', '2017-09-06 16:12:00.009', '603', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4ca5a744d87240e69c22eeaadafdb6fe', '0', '8', '2017-09-06 16:12:02.141', '604', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('032522de9acc4a17aa3db9123ce5917e', '0', '6', '2017-09-06 16:12:09.18', '605', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c931b87ff18b44369d6c38ff4ff36fa8', '0', '6', '2017-09-06 16:12:11.319', '606', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5618fb7404734a769da244dedf9458e0', '0', '6', '2017-09-06 16:12:13.456', '607', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b81c540f53494ffea017ff56ed883620', '0', '3', '2017-09-06 16:12:15.596', '608', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('82b3b2f0844a4208a718ebb0a20ffc03', '0', '5', '2017-09-06 16:12:17.733', '609', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0c34a9cafea44fbea7564c3e1e521db8', '0', '6', '2017-09-06 16:12:20.267', '610', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('270e4dde7a4b446599dfbc1660b8d265', '0', '6', '2017-09-06 16:12:22.399', '611', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a49ada5223bd44a9aa0f31a59c17e0fe', '0', '6', '2017-09-06 16:12:24.576', '612', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('15cdda6c77bb4b57ae945910c30277e8', '0', '6', '2017-09-06 16:12:26.707', '613', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f7ab4c7f4ec2478a8ac93ae8008d7030', '0', '2', '2017-09-06 16:20:27.971', '614', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('06bef5a099454526b6b6378d42071ad0', '0', '9', '2017-09-06 16:20:30.325', '615', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7d73c3667ad04fc0b3abfc99f33a310f', '0', '6', '2017-09-06 16:20:32.461', '616', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('aa62b3b282494d32b77c1a8e992ba63f', '0', '6', '2017-09-06 16:20:34.611', '617', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('dec67b57c54d473484881896381ea0ef', '0', '6', '2017-09-06 16:20:36.751', '618', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8738396c01f246c389359165d2258b39', '0', '6', '2017-09-06 16:20:38.897', '619', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c981edc9c61b412a96ebd279c0ad3c49', '0', '1', '2017-09-06 16:20:41.048', '620', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3d8dbd0aa98849aa832f4bd2bdf2abaa', '0', '9', '2017-09-06 16:20:42.114', '621', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2801afa247b146ad8f5ccf753a13fe84', '0', '1', '2017-09-06 16:20:43.185', '622', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d2468220c024465e9b89cba6f344427b', '0', '2', '2017-09-06 16:20:45.332', '623', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2b717ace696a4a8bb570ace207b1f8cd', '0', '7', '2017-09-06 16:20:47.487', '624', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('816539292e6f47d1ab708980fa86e466', '0', '0', '2017-09-06 16:20:49.632', '625', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('36a9f36462c54a4b904fdd74d8d35932', '0', '7', '2017-09-06 16:20:51.768', '626', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('27b4dfac698a4d979be5fd66e27644f8', '0', '5', '2017-09-06 16:20:53.934', '627', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('01b0e394ffa84e28b0e9435d8c355e6f', '0', '9', '2017-09-06 16:20:56.067', '628', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('70b33f9699e24b6095938d9f23614245', '0', '7', '2017-09-06 16:20:58.259', '629', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('738787a11d5244b4af593dce84125d11', '0', '7', '2017-09-06 16:21:00.411', '630', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('93649fb09fa24ce280d54e30c5de04d0', '0', '8', '2017-09-06 16:21:02.601', '631', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8bf1c68060424fba84d6dc4d8a767184', '0', '7', '2017-09-06 16:21:04.735', '632', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3587ee91eb904e3cbcf545e2dc211356', '0', '5', '2017-09-06 16:21:06.891', '633', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9f52ca05e305471cb7789eea278e44f1', '0', '8', '2017-09-06 16:21:09.047', '634', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9cab895ba6ef458d9d473cc9451e347c', '0', '9', '2017-09-06 16:21:11.225', '635', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('56f6c21faa324029ad104ea4a47aa398', '0', '6', '2017-09-06 16:21:13.387', '636', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7b7a50f246934af585392ce717709f4d', '0', '8', '2017-09-06 16:21:15.545', '637', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('52c3552d00e145ec974a533d7f1b94d9', '0', '7', '2017-09-06 16:21:17.697', '638', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e564a2f7b5e3425386d091235d2b61e2', '0', '7', '2017-09-06 16:21:19.861', '639', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3b13bd3a0d994afaa196ae498abe7e03', '0', '0', '2017-09-06 16:21:22.037', '640', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d70e81f0d6b141098b418429f1224465', '0', '7', '2017-09-06 16:21:24.293', '641', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9a8a736352ef4b0fa3efcedd26e752a2', '0', '8', '2017-09-06 16:21:26.45', '642', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('cb03f88b3093412095c2b6ec7400dc3d', '0', '0', '2017-09-06 16:21:28.613', '643', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('77af26fbebf649d394ad6750bb104a8b', '0', '3', '2017-09-06 16:21:30.769', '644', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9b0344b75b8141089b1a492c3a572dd9', '0', '7', '2017-09-06 16:21:32.927', '645', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a8e0738532e94b05a0865639810d8696', '0', '1', '2017-09-06 16:21:35.076', '646', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('11df87d696f14e8aa3494c47eb347d5a', '0', '6', '2017-09-06 16:21:37.233', '647', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d939a34455214b2891dcb4168e071a5c', '0', '2', '2017-09-06 16:21:39.414', '648', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0e141368b51c42569a1780fa3b7cbda1', '0', '1', '2017-09-06 16:21:41.57', '649', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('de7d966d4fd7440280fde7fea09800f3', '0', '2', '2017-09-06 16:21:43.751', '650', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9853e5cd543248a2aa1d19f405cfa9fb', '0', '9', '2017-09-06 16:21:45.904', '651', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5750d31c0d294235b7df918d9c14d45c', '0', '7', '2017-09-06 16:21:48.073', '652', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('01dc8ce282224ad8bb2b3f15985ffefc', '0', '0', '2017-09-06 16:21:50.23', '653', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e99c246842724ff6ad424510b248caff', '0', '8', '2017-09-06 16:21:52.399', '654', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1014787807a546ef9f1c82bd3121b798', '0', '4', '2017-09-06 16:21:54.645', '655', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7e72dd6cae8f414ab5bb5670b71ac4c2', '0', '5', '2017-09-06 16:21:57.008', '656', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ac0a77c7f9c348c7b849f8bf89ef43c4', '0', '0', '2017-09-06 16:21:59.208', '657', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('59541358a79844b780509215e26049ae', '0', '3', '2017-09-06 16:22:01.476', '658', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8253725cea1849109b7b22d9dff52217', '0', '7', '2017-09-06 16:22:03.66', '659', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ef79e19dac794639b67e83962545e390', '0', '5', '2017-09-06 16:22:05.965', '660', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('018f9e5e4dfc427095ba03902ceca11b', '0', '8', '2017-09-06 16:22:08.436', '661', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0dc2600dd8744e25974028b0957418a7', '0', '8', '2017-09-06 16:22:10.66', '662', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('53a50c7a2b574d2f99c2b8f5fbab5781', '0', '9', '2017-09-06 16:22:13.026', '663', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f8dd2fc0abe743079267b04d4e8be453', '0', '0', '2017-09-06 16:22:15.386', '664', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ace0388156d84d0ba7229428cf228649', '0', '4', '2017-09-06 16:22:17.613', '665', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fd6210ea294649068f31b207809a9966', '0', '0', '2017-09-06 16:22:19.981', '666', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3bed726392bb460694a6a465e389ee96', '0', '5', '2017-09-06 16:22:22.683', '667', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b972fdac3bc54e7e9ace3bef65cfba22', '0', '4', '2017-09-06 16:22:25.086', '668', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('94e42c12a0124b56b82163870a4091e8', '0', '2', '2017-09-06 16:22:27.538', '669', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('49251575a4894817838769682a800f9e', '0', '0', '2017-09-06 16:22:30.143', '670', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e4c1deee3fa0468e82d49f6931dacc0b', '0', '5', '2017-09-06 16:22:32.399', '671', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('15ba2901a6a24484a5efb9d80f3391ef', '0', '8', '2017-09-06 16:22:34.77', '672', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f4e155db0ab54709b6a54406932bf84e', '0', '4', '2017-09-06 16:22:37.103', '673', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3890278cb0804f5db04f7df099c711e7', '0', '5', '2017-09-06 16:22:39.413', '674', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0a8a3c502aea436e843b341f69a7f3b8', '0', '6', '2017-09-06 16:22:41.677', '675', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2870bfdcae3b410ba94037330647c7be', '0', '7', '2017-09-06 16:22:44.036', '676', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b61b74bb9f554c3090c7f491b1ad8a77', '0', '3', '2017-09-06 16:22:46.195', '677', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fab9c108af5c47aa8e530cc69b43ef48', '0', '4', '2017-09-06 16:22:48.536', '678', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('03eb211c44ec4359bc01b1497f818cdd', '0', '3', '2017-09-06 16:22:50.706', '679', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3c91684821204e9f9c511100c6e3343a', '0', '2', '2017-09-06 16:22:53.035', '680', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('56b4e548405b4c3db8569424fedcd711', '0', '5', '2017-09-06 16:22:55.185', '681', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('39afd407ea1c45f581349f4cb1db508c', '0', '5', '2017-09-06 16:22:57.63', '682', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('df240c784968431cb2215df3521664bf', '0', '3', '2017-09-06 16:22:59.897', '683', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0d9e550bd3dc4eaa827bc898d23d013e', '0', '2', '2017-09-06 16:23:02.09', '684', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ddc8280bd3604bd0bad52e4f3cfa3aa4', '0', '7', '2017-09-06 16:23:04.321', '685', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f08a39a469e4495f96495563d45a26ee', '0', '5', '2017-09-06 16:23:06.506', '686', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('71443690e8c94a159863079c6ae00414', '0', '2', '2017-09-06 16:23:08.665', '687', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('75806bd342bf447892f7893eefa2fe4b', '0', '2', '2017-09-06 16:23:10.979', '688', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('43eefcaa05514062b98328c085f4bbb5', '0', '2', '2017-09-06 16:23:13.14', '689', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a4026f5eee824469b637d016217dbf81', '0', '4', '2017-09-06 16:23:15.48', '690', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4bf54e81ed8345678c56bbab3cb6dab7', '0', '0', '2017-09-06 16:23:17.649', '691', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f57d3919b26b45bdbceec7317433d149', '0', '6', '2017-09-06 16:23:19.936', '692', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('302f37d1af9d409ca554fdb2e50b541b', '0', '8', '2017-09-06 16:23:22.086', '693', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('dcd125cf8b3a41bfb71ce3b7a63896f4', '0', '4', '2017-09-06 16:23:24.34', '694', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0880e7fedbc24d999a9e54a227253917', '0', '4', '2017-09-06 16:23:26.557', '695', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('97058bc8a77d4088815312e51c38716f', '0', '7', '2017-09-06 16:23:28.747', '696', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('32e5ccb7362d44b79da2f0ded997dce7', '7', '0', '2017-09-06 16:44:21.595', '697', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('15e6f50011704b65b72512344f7d35c9', '4', '0', '2017-09-06 16:44:23.826', '698', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e67416e7e6d4401d92bfa2dc1081387d', '7', '0', '2017-09-06 16:44:25.98', '699', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0f9683f9906e4b17b42198009106db81', '5', '0', '2017-09-06 16:44:28.135', '700', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5b0c54157a614219b1b951a32d07d108', '4', '0', '2017-09-06 16:44:30.296', '701', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3d7832d83c634177bf93fca765fff6b2', '0', '0', '2017-09-06 16:44:32.447', '702', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4ab36b748b22424099fd0df63e60b0c2', '2', '0', '2017-09-06 16:44:34.605', '703', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('58ee68f21a1d449f9a23b433f87cf087', '2', '0', '2017-09-06 16:44:36.753', '704', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e4737c2966e642f0a4d8ba4354112ae3', '2', '0', '2017-09-06 16:44:38.908', '705', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('aa6983e8131243ab93105fedd833cf75', '9', '0', '2017-09-06 16:44:41.049', '706', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('84ae7436b94d4a4bb3c50ce04081f89c', '1', '0', '2017-09-06 16:44:43.23', '707', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2885966edab7471fb1247ec9e712dedd', '3', '0', '2017-09-06 16:44:45.379', '708', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('70e2dd7289d94c05ad86b7d8829799b8', '6', '0', '2017-09-06 16:44:47.558', '709', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0f021274f1a046819cdbf89f2002f44e', '7', '0', '2017-09-06 16:44:49.737', '710', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4bf4e14f6df44a7a97bd6976dab1a46f', '3', '0', '2017-09-06 16:44:51.906', '711', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8f03849b33534140af24a8e25f4230cc', '0', '0', '2017-09-06 16:44:54.092', '712', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9c89bbd8271b42248a674ce3a49a7cb1', '6', '0', '2017-09-06 16:44:56.27', '713', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('71f7da8cc893450396ee73f7a3e44f81', '4', '0', '2017-09-06 16:44:58.445', '714', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d635bfc5a3bd4bcebd3ff326048bb12e', '8', '0', '2017-09-06 16:45:00.622', '715', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ca07e4ee66d34d8bb832766e13856ec6', '7', '0', '2017-09-06 16:45:02.801', '716', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fed8aa55682c431291c095d7710837d8', '0', '0', '2017-09-06 16:45:04.973', '717', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('446612a1777d4c6797be90801ea9aef5', '8', '0', '2017-09-06 16:45:07.155', '718', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3934e455720f4a3ea00355878b857202', '5', '0', '2017-09-06 16:45:09.338', '719', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b2a67e3c082a4b488e2cb3a6159a5a4c', '1', '0', '2017-09-06 16:45:11.529', '720', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4f5f2ab22dd54d1691f6f33a5c954bbd', '4', '0', '2017-09-06 16:45:13.714', '721', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a3d791e72f3742f19d1cc55ec29c5444', '1', '0', '2017-09-06 16:45:15.891', '722', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('54b3b5366ea84477b5b255836db3e76e', '9', '0', '2017-09-06 16:45:18.069', '723', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('12fe372d9aaa479dba8a8713ff19fd09', '7', '0', '2017-09-06 16:45:20.25', '724', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a1f86d5bf8954970897299b52b91d367', '6', '0', '2017-09-06 16:45:22.423', '725', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('833eef4a1b0a4728a8afd37d55251d07', '5', '0', '2017-09-06 16:45:24.598', '726', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b783e53ffffb43a6bedd228cb04a39e0', '2', '0', '2017-09-06 16:45:26.776', '727', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('80f7956d3549465a9551a3ab2e050130', '4', '0', '2017-09-06 16:45:28.949', '728', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4edb7f336b0046c7aa733144ddf4d635', '1', '0', '2017-09-06 16:45:31.122', '729', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e392bed646674e67b3e32650f5718dff', '4', '0', '2017-09-06 16:45:33.321', '730', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('881a41c176904ac89ffca1a800e657e3', '9', '0', '2017-09-06 16:45:35.494', '731', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('83f23bffd7a7447a828611ec5b08e9ed', '7', '0', '2017-09-06 16:45:37.675', '732', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7712d14dd28a4818b475e7d7dd42ec1b', '8', '0', '2017-09-06 16:45:39.858', '733', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b7dbb97ca086425ab3219896c371dad2', '3', '0', '2017-09-06 16:45:42.042', '734', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('25486e496e034acfb3de012e59a7aacb', '8', '0', '2017-09-06 16:45:44.22', '735', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7e5d927e89164aa8a85b699c788784ac', '0', '0', '2017-09-06 16:45:46.41', '736', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e2e72b920125414d86ace7df33ae57f0', '4', '0', '2017-09-06 16:45:48.583', '737', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ed1e3c7ff5c84c0ca44336ea7d2f4a43', '7', '0', '2017-09-06 16:45:50.761', '738', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1a94f481c0b64ec682354daebf186230', '0', '0', '2017-09-06 16:45:52.937', '739', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c53353b4eb0843a0a618c97b82e0e493', '5', '0', '2017-09-06 16:45:55.111', '740', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('893a4f581388497793146e00761c17a6', '1', '0', '2017-09-06 16:45:57.286', '741', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('82ac0d9cf52f4f1ba96c333974ec874b', '2', '0', '2017-09-06 16:45:59.455', '742', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('40af81683c614b09a6801b6e4ab53887', '8', '0', '2017-09-06 16:46:01.628', '743', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('04c88d2f23844c7caa94a621e6b0b70c', '4', '0', '2017-09-06 16:46:03.796', '744', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9e4314befbc4426086f3c1d7294d2b1f', '0', '0', '2017-09-06 16:46:05.972', '745', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5e754781eb5c4d728321aa2dd43239f9', '3', '0', '2017-09-06 16:46:08.172', '746', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('50ae8058c8ec416eb0e708eb47b078a0', '2', '0', '2017-09-06 16:46:10.342', '747', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('57dc2142b35e47e6a7f495385ab803f5', '3', '0', '2017-09-06 16:46:12.52', '748', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b8479e8a4a084dc18a41ae9534a8560e', '8', '0', '2017-09-06 16:46:14.699', '749', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('30a5ba78fafa4c579e1410f619879df8', '4', '0', '2017-09-06 16:46:16.875', '750', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0f1c5dff1e594c88a61631ba8bd266a2', '9', '0', '2017-09-06 16:46:19.043', '751', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ad02bce4366e4895b9b56f207dd05c94', '2', '0', '2017-09-06 16:46:21.221', '752', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3838e8b68eb24d6da0261ea143922476', '2', '0', '2017-09-06 16:46:23.4', '753', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('bcb1d96836d44cee9b4695022a31d91d', '7', '0', '2017-09-06 16:46:25.577', '754', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('944ec28a173c4c1cb6d7b126477c2e95', '9', '0', '2017-09-06 16:46:27.76', '755', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6af239eee5194b989157eacaea462ddc', '6', '0', '2017-09-06 16:46:29.937', '756', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f51287f48fca4ec7afb7279ee13d0e79', '1', '0', '2017-09-06 16:46:32.109', '757', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('fe3b2e74c15e4200ab6730ff52f1dc02', '5', '0', '2017-09-06 16:46:34.282', '758', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c0e82fa8bd884ab499a987dc6c633e37', '6', '0', '2017-09-06 16:46:36.454', '759', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9408e2a830b346908d230f4c3d3d7551', '1', '0', '2017-09-06 16:46:38.626', '760', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('45af3d6d49e74dabb44bd0a4db9ae2d2', '6', '0', '2017-09-06 16:46:40.8', '761', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('04605a9e367146d28dd9ed85e59475e6', '2', '0', '2017-09-06 16:46:42.982', '762', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5cf6c3a6d40d46898fcc5d9d3ee2d8d8', '3', '0', '2017-09-06 16:46:45.153', '763', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1768453bb58b40539ca3dfef5304d4d5', '3', '0', '2017-09-06 16:46:47.324', '764', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('547b34ffd21a4d03adbe5d87214d067d', '5', '0', '2017-09-06 16:46:49.494', '765', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f17bc38e0c5449a7b601bda3a4bcc908', '5', '0', '2017-09-06 16:46:51.673', '766', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('dfc9251e23794776bdf1564f7d0a6f72', '4', '0', '2017-09-06 16:46:53.837', '767', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('17f3db0c157b486887b97066c9a7f466', '9', '0', '2017-09-06 16:46:56.008', '768', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8c3f530707ad40f0a47c6e51b8ee3048', '8', '0', '2017-09-06 16:46:58.181', '769', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('28fd194d18494257b5b70e99d0f21e7a', '6', '0', '2017-09-06 16:47:00.361', '770', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('9bd62488777440818decb6e340c4a367', '1', '0', '2017-09-06 16:47:02.532', '771', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('32eeca0ee9814847bd006646cbec63c5', '5', '0', '2017-09-06 16:47:04.705', '772', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2aa4d2f7e7d74d9badfd5b434cc8a310', '7', '0', '2017-09-06 16:47:06.878', '773', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('39e010685c844efebe8bc9a19ecec467', '7', '0', '2017-09-06 16:47:09.053', '774', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('187f7fe08b7847f7bfc709ccb5156ccd', '0', '9', '2017-09-06 16:51:55.575', '775', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e4b3f23424ed410bb7581d8515a7907e', '0', '6', '2017-09-06 16:51:57.927', '776', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('429f703e8c8a4f4ba5faae629858ac97', '0', '5', '2017-09-06 16:52:00.081', '777', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3ea4baaee78b49429721a25617fcf2ac', '0', '0', '2017-09-06 16:52:02.222', '778', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('dbe715e208e74e03ba8290cb48793c3c', '0', '5', '2017-09-06 16:52:04.365', '779', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ac779e9af62f4ad7a141cb0bf2244527', '0', '8', '2017-09-06 16:52:06.5', '780', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('712ace07585c4a418a6b24aa565ded42', '0', '4', '2017-09-06 16:52:08.645', '781', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7702124621314ccdaffa11542459ba37', '0', '3', '2017-09-06 16:52:10.79', '782', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ff9515d2901f497fa5d362649198cc39', '0', '8', '2017-09-06 16:52:12.939', '783', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('be4323ac17d242aca2ccd3b291924e7a', '0', '6', '2017-09-06 16:52:15.085', '784', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a118b77a0c23415b8177201697b4c650', '0', '3', '2017-09-06 16:52:17.255', '785', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('15fa6ecbcccf4233a422696858caaa01', '0', '6', '2017-09-06 16:52:19.39', '786', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('09ea46b6baa142b1852e722d88fe061a', '0', '2', '2017-09-06 16:52:21.56', '787', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('765dff1b6b984a79a559de1e0d221460', '0', '7', '2017-09-06 16:52:23.727', '788', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2977805aeea84635a21d726c776afcdb', '0', '4', '2017-09-06 16:52:25.887', '789', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a32232849f774181847c2333deaa1042', '0', '3', '2017-09-06 16:52:28.07', '790', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ade242da90d946958f5b6c216963974a', '0', '0', '2017-09-06 16:52:30.256', '791', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f25cc7963bdf41b794a7657bc01189e6', '0', '8', '2017-09-06 16:52:32.422', '792', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ccb1f7b90ffb4666b1faf9d67e9e12ef', '0', '8', '2017-09-06 16:52:34.58', '793', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ec75264508ea438cb84a1e4649b7ab04', '0', '6', '2017-09-06 16:52:36.741', '794', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3255d3d0c12e45bbb55e85a03d9145f6', '0', '9', '2017-09-06 16:52:38.935', '795', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e958bc9600104ffda932ab971a36d85d', '0', '5', '2017-09-06 16:52:41.178', '796', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c0af6cf2186843a2b57c77219cd3c7e8', '0', '8', '2017-09-06 16:52:43.337', '797', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('5d2486882cc742eaa0b0e44114cb500c', '0', '4', '2017-09-06 16:52:45.499', '798', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('7c2ecbf9389a4dd1a27f87af4815baba', '0', '7', '2017-09-06 16:52:47.666', '799', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('19386a97652b4760a1becc080783a615', '0', '6', '2017-09-06 16:52:49.839', '800', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4eee35e6879345a09ae97fa7d79c2dd7', '0', '1', '2017-09-06 16:52:52.003', '801', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('eddb645cb13f4286a93f8bbfbda9ce5e', '0', '0', '2017-09-06 16:52:54.177', '802', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0c1f662f2c9a44ad8b3c52a4af6df2d3', '0', '9', '2017-09-06 16:52:56.333', '803', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('273391f61e4d4132be0f46ca0f127958', '0', '2', '2017-09-06 16:52:58.504', '804', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ff54062b440c438a80625b8024061178', '0', '6', '2017-09-06 16:53:00.661', '805', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c925931b009e49a99810ed8cefc05906', '0', '7', '2017-09-06 16:53:02.822', '806', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('701f5e0b510246aa8add072c5c6ad74c', '0', '6', '2017-09-06 16:53:04.987', '807', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('8a1c5389c1054fb3bd9c85b2a7d2d067', '0', '8', '2017-09-06 16:53:07.371', '808', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ec9c5313181b48cc8223e824c19f3ea3', '0', '3', '2017-09-06 16:53:09.747', '809', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4ca8449c10c74c51b745502824e9f630', '0', '1', '2017-09-15 10:46:53.267', '810', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('42993a1f3fc64e43936beb3ea500dc17', '1', '0', '2017-09-15 10:48:17.101', '811', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c6f1d88a491840f09b65af19170b0137', '1', '0', '2017-09-15 10:51:46.363', '812', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('06b1e3f714fc4949bfb77d4ddc55d2dc', '1', '0', '2017-09-15 10:55:33.588', '813', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('643c506218224a96b12511589cc62c7a', '0', '1', '2017-09-15 10:55:55.451', '814', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b3ccd3328fed48bba38ebfe4aeebf94b', '0', '1', '2017-09-15 10:56:40.695', '815', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('2dea07d74231406c854527384fb96381', '1', '0', '2017-09-15 10:56:44.332', '816', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('53aa8c03814e47ccb0b65040d7b08014', '0', '1', '2017-09-15 10:57:02.129', '817', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1cb32179246b4eeb8dd815c4e51d477d', '1', '0', '2017-09-15 10:57:03.149', '818', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('6be6dcb36dde4ae39870908c7e12570c', '5', '0', '2017-09-15 11:09:16.05', '819', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0dc295ec333346c2bb3e05d7a832484f', '0', '1', '2017-09-15 11:09:47.241', '820', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('306909cfbf22459c977029e8b7af1b35', '1', '0', '2017-09-15 11:12:01.107', '821', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('046ab15be60d4a18b9fdf74bae85f974', '1', '0', '2017-09-15 11:12:24.545', '822', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b2fefae5785846b6b200d1d28c0b1ccb', '0', '1', '2017-09-15 11:13:39.2', '823', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3bac47f871724dd4af6ecf801fccda1d', '4', '0', '2017-09-15 11:17:34.91', '824', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('293b62dbed6448fd9c14b959afdc37b2', '0', '1', '2017-09-15 11:17:53.872', '825', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('c93961cab5d5451fa5d7697eca717354', '2', '0', '2017-09-18 09:29:51.624', '826', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('3696ad4baf2e484ba561091a9c0668ff', '0', '2', '2017-09-18 09:30:22.515', '827', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('60b2d2dc64e44d19b64db2ea01955019', '1', '0', '2017-09-18 09:30:55.806', '828', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('d8977258a6904b53a4124c3c13ebaee9', '0', '1', '2017-09-18 09:31:41.106', '829', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('90cd05b7b18442b7b96314523008ee33', '0', '1', '2017-09-18 09:31:47.105', '830', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f87e4a11cd734e2bb605fc67f36d5a07', '0', '1', '2017-09-18 09:31:53.425', '831', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('eed7e6460d9f404eb942aeebf0e8e0c7', '1', '0', '2017-09-18 09:31:57.989', '832', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e8a0a723fca145ba87a4d073f698d23c', '1', '0', '2017-09-18 09:32:07.753', '833', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0611068d562b430c8f8792a6f3432021', '0', '3', '2017-09-18 09:40:57.529', '834', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('51a53865642d41b2ae76beb644edec86', '1', '0', '2017-09-18 09:41:20.658', '835', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('1461b1ab6c764c919dccf0e0e261a7bc', '1', '0', '2017-09-19 09:24:54.154', '836', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('291737b8058b48ebbaf2e2d51e3abaa5', '1', '0', '2017-09-19 09:25:00.344', '837', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a48cdca358e74cb4a246a08e6c6d34a5', '3', '0', '2017-09-19 10:19:45.468', '838', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4913b4c0deb045f381bbd0a89184da2c', '0', '1', '2017-09-19 10:20:29.153', '839', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('60e449cb9315443093c56a63f5512c30', '3', '2', '2017-09-19 10:24:02.786', '840', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('a27bb4d6dfae41b5b29353db587b5e58', '3', '2', '2017-09-19 10:24:04.256', '841', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('edb2255997594e87b477848c7f6ecd34', '0', '3', '2017-09-19 10:33:28.275', '842', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f12586e5cc8740be8ff61740ebd341f7', '1', '0', '2017-09-19 10:49:14.65', '843', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('ea4ba255a4a84c45b9cbacae158697ff', '0', '1', '2017-09-19 10:54:27.904', '844', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('664aea82ae154091bca7d5b5380d6ffd', '1', '0', '2017-09-19 10:54:40.435', '845', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('0783c86922534d33a792ee8249b279d9', '8', '3', '2017-09-19 16:54:41.896', '846', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e2c0e78a563a434b84621b10f2c157a6', '1', '0', '2017-09-19 16:54:57.05', '847', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b754c3d154d849aaada087af526501e7', '0', '1', '2017-09-21 10:05:38.889', '848', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('4edb00fca7b54e2599b99d518a29f0a9', '1', '0', '2017-09-21 10:06:35.175', '849', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('f539ee21415741f8a3f10a4fa711292c', '3', '3', '2017-09-21 10:17:41.267', '850', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('76ce47f5f8224e7b9361627265f389c1', '0', '1', '2017-09-21 10:18:03.611', '851', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e82cc4adfa2d4ad7a0265a91050080c0', '1', '0', '2017-09-21 10:19:49.509', '852', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('b013bb76dcda49db935671b4628c9d88', '1', '0', '2017-09-21 10:20:46.602', '853', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('188ef924c742428c8a0afc4d333ef54b', '0', '1', '2017-09-21 10:21:02.411', '854', '13', '261');
INSERT INTO "t_flow_info_data" VALUES ('e6266dc1f5af462bb7ab2040b7507853', '1', '0', '2017-09-21 16:51:07.795', '855', '13', '261');
COMMIT;

-- ----------------------------
-- Table structure for t_gateway
-- ----------------------------
DROP TABLE IF EXISTS "t_gateway";
CREATE TABLE "t_gateway" (
"id" int4 DEFAULT nextval('t_gateway_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default" NOT NULL,
"device_status" int2,
"sim_card_id" int8,
"create_time" timestamp(6),
"remark" varchar(200) COLLATE "default",
"parking_space_id" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_gateway" IS '网关';
COMMENT ON COLUMN "t_gateway"."id" IS '主键';
COMMENT ON COLUMN "t_gateway"."name" IS '网关名称';
COMMENT ON COLUMN "t_gateway"."code" IS '网关唯一编码';
COMMENT ON COLUMN "t_gateway"."device_status" IS '设备状态';
COMMENT ON COLUMN "t_gateway"."sim_card_id" IS '关联sim卡Id';
COMMENT ON COLUMN "t_gateway"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_gateway"."remark" IS 'beiz';

-- ----------------------------
-- Records of t_gateway
-- ----------------------------
BEGIN;
INSERT INTO "t_gateway" VALUES ('1', '中国声谷一号网关', '261', '1', '1', '2017-09-04 11:14:50.748', null, '13');
INSERT INTO "t_gateway" VALUES ('2', '动漫基地地下停车库1号网关', '361', '1', '1', '2017-09-04 11:15:25.617', null, '12');
COMMIT;

-- ----------------------------
-- Table structure for t_geomagnetic_sensor
-- ----------------------------
DROP TABLE IF EXISTS "t_geomagnetic_sensor";
CREATE TABLE "t_geomagnetic_sensor" (
"id" int4 DEFAULT nextval('t_geomagnetic_sensor_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"type" int2,
"gateway_id" int8,
"device_status" int2,
"electricity_quantity" int4,
"create_time" timestamp(6),
"parking_space_id" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_geomagnetic_sensor" IS '地磁';
COMMENT ON COLUMN "t_geomagnetic_sensor"."type" IS '地磁类型： 1 流量地磁 2 泊位地磁';
COMMENT ON COLUMN "t_geomagnetic_sensor"."gateway_id" IS '所属网关ID';
COMMENT ON COLUMN "t_geomagnetic_sensor"."device_status" IS '设备状态';
COMMENT ON COLUMN "t_geomagnetic_sensor"."electricity_quantity" IS '地磁电量';
COMMENT ON COLUMN "t_geomagnetic_sensor"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_geomagnetic_sensor"."parking_space_id" IS '停车场ID';

-- ----------------------------
-- Records of t_geomagnetic_sensor
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_induce_plate
-- ----------------------------
DROP TABLE IF EXISTS "t_induce_plate";
CREATE TABLE "t_induce_plate" (
"id" int4 DEFAULT nextval('t_induce_plate_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"level" int2,
"location" varchar(50) COLLATE "default",
"address" varchar(200) COLLATE "default",
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_induce_plate" IS '诱导牌';
COMMENT ON COLUMN "t_induce_plate"."level" IS '诱导牌级别 1:一级 2：二级 3：三级';
COMMENT ON COLUMN "t_induce_plate"."location" IS '位置[经度,纬度]';
COMMENT ON COLUMN "t_induce_plate"."address" IS '地址';
COMMENT ON COLUMN "t_induce_plate"."create_time" IS '创建时间';

-- ----------------------------
-- Records of t_induce_plate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS "t_organization";
CREATE TABLE "t_organization" (
"id" int4 DEFAULT nextval('t_organization_id_seq'::regclass) NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL,
"code" varchar(32) COLLATE "default",
"parent_id" int8,
"create_time" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)
TABLESPACE "pgis" 

;

-- ----------------------------
-- Records of t_organization
-- ----------------------------
BEGIN;
INSERT INTO "t_organization" VALUES ('1', '合肥市', 'AHHF', null, '2017-08-18 16:23:45.135');
INSERT INTO "t_organization" VALUES ('4', '高新区', 'AHHFGX', '3', '2017-08-18 16:30:31.424');
COMMIT;

-- ----------------------------
-- Table structure for t_parking_space
-- ----------------------------
DROP TABLE IF EXISTS "t_parking_space";
CREATE TABLE "t_parking_space" (
"id" int4 DEFAULT nextval('t_parking_space_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"type" int2,
"access_mode" int2,
"charge_mode" int2,
"lot_total_count" int8,
"lot_remain_count" int4,
"user_id" int8,
"status" int2,
"create_time" timestamp(6),
"org_id" int8,
"remark" varchar(300) COLLATE "default",
"location" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_parking_space" IS '停车场';
COMMENT ON COLUMN "t_parking_space"."id" IS '主见';
COMMENT ON COLUMN "t_parking_space"."name" IS '名称';
COMMENT ON COLUMN "t_parking_space"."code" IS '唯一编码';
COMMENT ON COLUMN "t_parking_space"."type" IS '类型：1.室内 2.室外 3.占道 4.机械';
COMMENT ON COLUMN "t_parking_space"."access_mode" IS '接入方式： 1.地磁 2.系统';
COMMENT ON COLUMN "t_parking_space"."charge_mode" IS '收费模式:1. 免费 2：收费';
COMMENT ON COLUMN "t_parking_space"."lot_total_count" IS '车位总数';
COMMENT ON COLUMN "t_parking_space"."lot_remain_count" IS '剩余车位总数';
COMMENT ON COLUMN "t_parking_space"."user_id" IS '管理员用户ID';
COMMENT ON COLUMN "t_parking_space"."status" IS '状态：0 禁用 1启用';
COMMENT ON COLUMN "t_parking_space"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_parking_space"."org_id" IS '机构ID';

-- ----------------------------
-- Records of t_parking_space
-- ----------------------------
BEGIN;
INSERT INTO "t_parking_space" VALUES ('11', '中国声谷地下停车场', 'GXZGSGA1000', '1', '1', '1', '300', '300', '19', '1', '2017-08-18 16:34:07.714', '4', '真实测试数据', '31.8185250000,117.1229090000');
INSERT INTO "t_parking_space" VALUES ('12', '动漫基地地下停车库', 'GXDMJDA1000', '1', '1', '1', '200', '199', '19', '1', '2017-08-18 16:34:07.714', '4', null, '31.8185250000,117.1229090000');
INSERT INTO "t_parking_space" VALUES ('13', '中国声谷地下停车场B1层', 'GXZGSGA1001', '1', '1', '1', '300', '215', '19', '1', '2017-09-06 15:05:13.117', '4', null, null);
INSERT INTO "t_parking_space" VALUES ('14', '中国声谷地下停车场B2层', 'GXZGSGA1002', '1', '1', '1', '100', '87', '19', '1', '2017-09-06 15:05:15.995', '4', null, '31.8185250000,117.1229090000');
COMMIT;

-- ----------------------------
-- Table structure for t_parking_space_screen
-- ----------------------------
DROP TABLE IF EXISTS "t_parking_space_screen";
CREATE TABLE "t_parking_space_screen" (
"id" int4 DEFAULT nextval('t_parking_space_screen_id_seq'::regclass) NOT NULL,
"parking_space_id" int4,
"screen_id" int4,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_parking_space_screen" IS '停车场和屏 关系表';
COMMENT ON COLUMN "t_parking_space_screen"."id" IS '主键';
COMMENT ON COLUMN "t_parking_space_screen"."screen_id" IS '屏ID';
COMMENT ON COLUMN "t_parking_space_screen"."create_time" IS '创建时间';

-- ----------------------------
-- Records of t_parking_space_screen
-- ----------------------------
BEGIN;
INSERT INTO "t_parking_space_screen" VALUES ('1', '11', '1', '2017-09-04 11:07:16.399');
INSERT INTO "t_parking_space_screen" VALUES ('2', '13', '2', '2017-09-04 11:07:18.743');
COMMIT;

-- ----------------------------
-- Table structure for t_program
-- ----------------------------
DROP TABLE IF EXISTS "t_program";
CREATE TABLE "t_program" (
"id" int4 NOT NULL,
"name" varchar(100) COLLATE "default",
"user_id" int4 DEFAULT nextval('t_program_user_id_seq'::regclass) NOT NULL,
"content" varchar(200) COLLATE "default" NOT NULL,
"status" int4,
"release_time" timestamp(6),
"create_time" timestamp(6),
"remark" varchar(500) COLLATE "default",
"screen_ids" varchar(200) COLLATE "default" NOT NULL,
"scroll" bool DEFAULT true NOT NULL,
"color" varchar(10) COLLATE "default" DEFAULT 'red'::character varying NOT NULL,
"interval" int4 DEFAULT 50 NOT NULL,
"step" int4 DEFAULT 1,
"font_size" int4 DEFAULT 16 NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "t_program"."id" IS '主键';
COMMENT ON COLUMN "t_program"."name" IS '节目名称';
COMMENT ON COLUMN "t_program"."user_id" IS '发布用户';
COMMENT ON COLUMN "t_program"."content" IS '节目内容';
COMMENT ON COLUMN "t_program"."status" IS '节目状态 0 待发布 1发布成功 2 发布失败 -1 发布拒绝';
COMMENT ON COLUMN "t_program"."release_time" IS '发布时间';
COMMENT ON COLUMN "t_program"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_program"."remark" IS '备注';
COMMENT ON COLUMN "t_program"."screen_ids" IS '屏幕ID';
COMMENT ON COLUMN "t_program"."scroll" IS '是否滚动';
COMMENT ON COLUMN "t_program"."interval" IS '滚动间隔 单位ms 默认50';
COMMENT ON COLUMN "t_program"."step" IS '滚动速度 单位像素 ';
COMMENT ON COLUMN "t_program"."font_size" IS '内容字体大小';

-- ----------------------------
-- Records of t_program
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_repeater
-- ----------------------------
DROP TABLE IF EXISTS "t_repeater";
CREATE TABLE "t_repeater" (
"id" int4 DEFAULT nextval('t_repeater_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"gateway_id" int8,
"device_status" int2,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_repeater" IS '中继器';
COMMENT ON COLUMN "t_repeater"."id" IS '主键';
COMMENT ON COLUMN "t_repeater"."name" IS '中继器名称';
COMMENT ON COLUMN "t_repeater"."code" IS '唯一编码';
COMMENT ON COLUMN "t_repeater"."gateway_id" IS '所属网关ID';
COMMENT ON COLUMN "t_repeater"."device_status" IS '设备状态';
COMMENT ON COLUMN "t_repeater"."create_time" IS '创建时间';

-- ----------------------------
-- Records of t_repeater
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_screen
-- ----------------------------
DROP TABLE IF EXISTS "t_screen";
CREATE TABLE "t_screen" (
"id" int4 DEFAULT nextval('t_screen_id_seq'::regclass) NOT NULL,
"name" varchar(50) COLLATE "default",
"code" varchar(32) COLLATE "default",
"type" int2,
"control_card_id" int8,
"device_status" int2,
"induce_plate_id" int8,
"length" int4,
"width" int4,
"create_time" timestamp(6),
"order" int2 DEFAULT 1
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "t_screen" IS '屏幕';
COMMENT ON COLUMN "t_screen"."type" IS '0：车位数屏 1:多媒体显示屏';
COMMENT ON COLUMN "t_screen"."induce_plate_id" IS '所属诱导牌ID';
COMMENT ON COLUMN "t_screen"."length" IS '屏幕长度';
COMMENT ON COLUMN "t_screen"."width" IS '屏幕宽度';
COMMENT ON COLUMN "t_screen"."order" IS '屏幕位置 如 同一个诱导牌上 按照垂直级联后的位置关系';

-- ----------------------------
-- Records of t_screen
-- ----------------------------
BEGIN;
INSERT INTO "t_screen" VALUES ('1', '望江西路和石莲南路交口诱导牌车-中国声谷车位显示屏', 'SCREEN_1_1', '0', '1', '1', '1', '16', '32', '2017-09-04 11:05:39.996', '1');
INSERT INTO "t_screen" VALUES ('2', '望江西路和石莲南路交口诱导牌车-动漫基地车位显示屏', 'SCREEN_1_2', '0', '1', '1', '1', '16', '32', '2017-09-04 11:06:22.129', '2');
INSERT INTO "t_screen" VALUES ('3', '望江西路和失恋南路交口多媒体屏', 'SCREEN_2_1', '1', '2', '1', '1', '32', '160', '2017-09-11 15:12:06.699', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_sim_card
-- ----------------------------
DROP TABLE IF EXISTS "t_sim_card";
CREATE TABLE "t_sim_card" (
"id" int4 DEFAULT nextval('t_sim_card_id_seq'::regclass) NOT NULL,
"phone_number" varchar(11) COLLATE "default",
"operator_type" int2,
"network_mode" int2,
"remark" varchar(200) COLLATE "default",
"device_status" int2,
"create_time" timestamp(6),
"status" int2
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "t_sim_card"."operator_type" IS '运营商类型：1:移动2联通 3电信';
COMMENT ON COLUMN "t_sim_card"."network_mode" IS '网络模式：1:3G 2 4G ';
COMMENT ON COLUMN "t_sim_card"."remark" IS '备注';
COMMENT ON COLUMN "t_sim_card"."device_status" IS '物理状态';
COMMENT ON COLUMN "t_sim_card"."create_time" IS '创建时间';

-- ----------------------------
-- Records of t_sim_card
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "t_user";
CREATE TABLE "t_user" (
"id" int4 DEFAULT nextval('t_user_id_seq'::regclass) NOT NULL,
"username" varchar(100) COLLATE "default" NOT NULL,
"password" varchar(32) COLLATE "default" NOT NULL,
"type" int2 NOT NULL,
"org_id" int8 NOT NULL,
"phone" varchar(11) COLLATE "default" NOT NULL,
"salt" varchar(4) COLLATE "default",
"create_time" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)
TABLESPACE "pgis" 

;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO "t_user" VALUES ('1', 'admin', '90da10bd389cd21ecce062114b4c8eb7', '1', '1', '123456789', 'pgis', '2017-08-14 01:24:16.00277');
INSERT INTO "t_user" VALUES ('19', 'zgsgAdmin', 'a072da4ff1f9c53ebdcd5c792a943020', '2', '4', '13020003212', 'h70G', '2017-08-18 16:31:12.46');
INSERT INTO "t_user" VALUES ('20', 'test', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:24.373701');
INSERT INTO "t_user" VALUES ('21', 'test1', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:29.845347');
INSERT INTO "t_user" VALUES ('22', 'test2', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:43.010773');
INSERT INTO "t_user" VALUES ('23', 'test3', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:46.312698');
INSERT INTO "t_user" VALUES ('25', 'test5', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:55.120314');
INSERT INTO "t_user" VALUES ('26', 'test6', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:38:58.572936');
INSERT INTO "t_user" VALUES ('27', 'test7', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:02.265416');
INSERT INTO "t_user" VALUES ('28', 'test8', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:05.150999');
INSERT INTO "t_user" VALUES ('29', 'test9', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:08.430714');
INSERT INTO "t_user" VALUES ('30', 'test10', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:12.231347');
INSERT INTO "t_user" VALUES ('31', 'test11', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:15.370984');
INSERT INTO "t_user" VALUES ('32', 'test12', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:18.201122');
INSERT INTO "t_user" VALUES ('33', 'test13', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:21.886591');
INSERT INTO "t_user" VALUES ('35', 'test15', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:26.95898');
INSERT INTO "t_user" VALUES ('36', 'test16', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:31.265737');
INSERT INTO "t_user" VALUES ('37', 'test17', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:35.215653');
INSERT INTO "t_user" VALUES ('38', 'test18', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:38.556769');
INSERT INTO "t_user" VALUES ('39', 'test19', '90da10bd389cd21ecce062114b4c8eb7', '2', '1', '123456789', 'pgis', '2017-09-19 01:39:41.7014');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "t_config_id_seq" OWNED BY "t_config"."id";
ALTER SEQUENCE "t_content_id_seq" OWNED BY "t_content"."id";
ALTER SEQUENCE "t_control_card_id_seq" OWNED BY "t_control_card"."id";
ALTER SEQUENCE "t_flow_info_data_id_seq" OWNED BY "t_flow_info_data"."id";
ALTER SEQUENCE "t_gateway_id_seq" OWNED BY "t_gateway"."id";
ALTER SEQUENCE "t_geomagnetic_sensor_id_seq" OWNED BY "t_geomagnetic_sensor"."id";
ALTER SEQUENCE "t_induce_plate_id_seq" OWNED BY "t_induce_plate"."id";
ALTER SEQUENCE "t_organization_id_seq" OWNED BY "t_organization"."id";
ALTER SEQUENCE "t_parking_space_id_seq" OWNED BY "t_parking_space"."id";
ALTER SEQUENCE "t_parking_space_screen_id_seq" OWNED BY "t_parking_space_screen"."id";
ALTER SEQUENCE "t_program_user_id_seq" OWNED BY "t_program"."user_id";
ALTER SEQUENCE "t_repeater_id_seq" OWNED BY "t_repeater"."id";
ALTER SEQUENCE "t_screen_id_seq" OWNED BY "t_screen"."id";
ALTER SEQUENCE "t_sim_card_id_seq" OWNED BY "t_sim_card"."id";
ALTER SEQUENCE "t_user_id_seq" OWNED BY "t_user"."id";

-- ----------------------------
-- Indexes structure for table t_config
-- ----------------------------
CREATE UNIQUE INDEX "i_t_config" ON "t_config" USING btree ("id");

-- ----------------------------
-- Primary Key structure for table t_config
-- ----------------------------
ALTER TABLE "t_config" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_content
-- ----------------------------
ALTER TABLE "t_content" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_control_card
-- ----------------------------
CREATE UNIQUE INDEX "control_card_pk" ON "t_control_card" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_control_card
-- ----------------------------
ALTER TABLE "t_control_card" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_control_card
-- ----------------------------
ALTER TABLE "t_control_card" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_flow_info_data
-- ----------------------------
CREATE UNIQUE INDEX "flow_info_data_unique_key_uindex" ON "t_flow_info_data" USING btree ("unique_key");

-- ----------------------------
-- Primary Key structure for table t_flow_info_data
-- ----------------------------
ALTER TABLE "t_flow_info_data" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_gateway
-- ----------------------------
CREATE UNIQUE INDEX "gateway_pk" ON "t_gateway" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_gateway
-- ----------------------------
ALTER TABLE "t_gateway" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_gateway
-- ----------------------------
ALTER TABLE "t_gateway" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_geomagnetic_sensor
-- ----------------------------
CREATE UNIQUE INDEX "geomagnetic_sensor_pk" ON "t_geomagnetic_sensor" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_geomagnetic_sensor
-- ----------------------------
ALTER TABLE "t_geomagnetic_sensor" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_geomagnetic_sensor
-- ----------------------------
ALTER TABLE "t_geomagnetic_sensor" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_induce_plate
-- ----------------------------
ALTER TABLE "t_induce_plate" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_organization
-- ----------------------------
CREATE UNIQUE INDEX "i_t_organization" ON "t_organization" USING btree ("id");

-- ----------------------------
-- Primary Key structure for table t_organization
-- ----------------------------
ALTER TABLE "t_organization" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_parking_space
-- ----------------------------
CREATE UNIQUE INDEX "parking_space_pk" ON "t_parking_space" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_parking_space
-- ----------------------------
ALTER TABLE "t_parking_space" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_parking_space
-- ----------------------------
ALTER TABLE "t_parking_space" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_parking_space_screen
-- ----------------------------
CREATE UNIQUE INDEX "t_parking_space_screen_screen_id_uindex" ON "t_parking_space_screen" USING btree ("screen_id");

-- ----------------------------
-- Primary Key structure for table t_parking_space_screen
-- ----------------------------
ALTER TABLE "t_parking_space_screen" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_program
-- ----------------------------
ALTER TABLE "t_program" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_repeater
-- ----------------------------
CREATE UNIQUE INDEX "repeater_pk" ON "t_repeater" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_repeater
-- ----------------------------
ALTER TABLE "t_repeater" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_repeater
-- ----------------------------
ALTER TABLE "t_repeater" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_screen
-- ----------------------------
CREATE UNIQUE INDEX "screen_pk" ON "t_screen" USING btree ("id");
CREATE UNIQUE INDEX "t_screen_control_card_id_order_uindex" ON "t_screen" USING btree ("control_card_id", "order");

-- ----------------------------
-- Checks structure for table t_screen
-- ----------------------------
ALTER TABLE "t_screen" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_screen
-- ----------------------------
ALTER TABLE "t_screen" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sim_card
-- ----------------------------
CREATE UNIQUE INDEX "sim_card_pk" ON "t_sim_card" USING btree ("id");

-- ----------------------------
-- Checks structure for table t_sim_card
-- ----------------------------
ALTER TABLE "t_sim_card" ADD CHECK ((id >= 1));

-- ----------------------------
-- Primary Key structure for table t_sim_card
-- ----------------------------
ALTER TABLE "t_sim_card" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_user
-- ----------------------------
CREATE UNIQUE INDEX "i_t_user" ON "t_user" USING btree ("id");

-- ----------------------------
-- Uniques structure for table t_user
-- ----------------------------
ALTER TABLE "t_user" ADD UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "t_user" ADD PRIMARY KEY ("id");
