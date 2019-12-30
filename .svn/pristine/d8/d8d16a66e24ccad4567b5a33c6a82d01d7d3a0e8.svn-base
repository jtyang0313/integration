/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : integration

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2019-08-23 17:26:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(100) CHARACTER SET utf8 NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('UserManagement', null, '$2a$10$NmUspMh37fbZM/6.XHzJSOiGW1LPCRmZMftu.dMHidoMeaBApp.7e', 'all', 'authorization_code,refresh_token', 'http://localhost:8082/client/login', null, '7200', null, null, 'true');

-- ----------------------------
-- Table structure for tb_bill
-- ----------------------------
DROP TABLE IF EXISTS `tb_bill`;
CREATE TABLE `tb_bill` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_code` varchar(30) NOT NULL,
  `bill_code` varchar(30) NOT NULL,
  `bill_date` date NOT NULL,
  `bill_amount` bigint(20) NOT NULL,
  `bill_currency` varchar(30) NOT NULL,
  `bill_memo` varchar(300) DEFAULT NULL,
  `state` tinyint(4) NOT NULL COMMENT '对账状态 0未对账 1已对账',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_agentBill_billCode` (`bill_code`) USING BTREE,
  KEY `idx_agentBill_companyCode` (`company_code`,`bill_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bill
-- ----------------------------
INSERT INTO `tb_bill` VALUES ('57', '123456', 'zcd33022522', '2019-08-22', '1000000', 'USD', '完成', '0', '2019-08-23 16:06:23');

-- ----------------------------
-- Table structure for tb_bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_bill_detail`;
CREATE TABLE `tb_bill_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bill_code` varchar(30) NOT NULL,
  `box_code` varchar(30) DEFAULT NULL,
  `cost_code` varchar(30) DEFAULT NULL,
  `cost_name` varchar(60) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `currency` varchar(30) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_agentBillDetail_billCode` (`bill_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bill_detail
-- ----------------------------
INSERT INTO `tb_bill_detail` VALUES ('162', 'zcd33022522', 'LPA040800021', null, '修箱费', '400000', 'USD', '2019-08-23 16:06:24');
INSERT INTO `tb_bill_detail` VALUES ('163', 'zcd33022522', 'LPA040800022', null, '吊机费', '200000', 'USD', '2019-08-23 16:06:24');
INSERT INTO `tb_bill_detail` VALUES ('164', 'zcd33022522', 'LPA040800023', null, '电报费', '400000', 'USD', '2019-08-23 16:06:24');

-- ----------------------------
-- Table structure for tb_company
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_code` varchar(60) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `type` tinyint(3) unsigned NOT NULL,
  `contacts` varchar(60) DEFAULT NULL,
  `tel_phone` varchar(60) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_company_companyCode` (`company_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_company
-- ----------------------------
INSERT INTO `tb_company` VALUES ('7', 'ALBB', '阿里巴巴', '2', 'yjt', null, null, '2019-07-25 16:11:06', '2019-08-08 16:25:33');
INSERT INTO `tb_company` VALUES ('8', '123456', 'method', '1', 'gingi', '15866668888', 'England', '2019-08-21 11:01:24', '2019-08-21 11:01:24');
INSERT INTO `tb_company` VALUES ('9', '123123', '义乌易镭电子', '5', 'meatmeat', '123', '', '2019-08-22 15:19:25', '2019-08-22 15:19:25');

-- ----------------------------
-- Table structure for tb_company_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_company_file`;
CREATE TABLE `tb_company_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_code` varchar(30) NOT NULL,
  `user_id` int(11) NOT NULL,
  `file_name` varchar(60) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  `memo` varchar(300) DEFAULT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_company_file
-- ----------------------------
INSERT INTO `tb_company_file` VALUES ('1', '123123', '1', 'gakki.png', 'D:/tmp/80ec2419-692a-49e4-9abd-6277c817347a.png', 'oi', '2019-08-23 02:28:19');
INSERT INTO `tb_company_file` VALUES ('2', '123123', '1', 'getStatus.xml', 'D:/tmp/e6a6e193-97d0-4295-8564-5cb29468c223.xml', 'oi', '2019-08-23 02:28:25');

-- ----------------------------
-- Table structure for tb_company_invoice
-- ----------------------------
DROP TABLE IF EXISTS `tb_company_invoice`;
CREATE TABLE `tb_company_invoice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_code` varchar(30) NOT NULL,
  `company_rise` varchar(50) NOT NULL COMMENT '企业抬头',
  `duty_paragraph` varchar(50) NOT NULL COMMENT '企业税号',
  `address` varchar(100) DEFAULT NULL,
  `bank` varchar(20) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `telephone` varchar(15) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL COMMENT '是否默认',
  `operate_name` varchar(30) DEFAULT NULL COMMENT '操作人',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_companyInvoice_companyCode` (`company_code`) USING BTREE,
  KEY `idx_companyInvoice_companyRise` (`company_rise`) USING BTREE,
  KEY `idx_companyInvoice_dutyParagraph` (`duty_paragraph`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='企业开票信息表';

-- ----------------------------
-- Records of tb_company_invoice
-- ----------------------------
INSERT INTO `tb_company_invoice` VALUES ('22', '234324', '试试', '3444', '123', '123', '123', '13939874747', '0', null, null, '2019-08-05 16:09:32', '2019-08-05 16:09:32');
INSERT INTO `tb_company_invoice` VALUES ('24', '234324', '333', '22', null, '建设银行', '636355223120', '333333333', '1', null, null, '2019-08-05 16:50:10', '2019-08-05 16:50:10');
INSERT INTO `tb_company_invoice` VALUES ('25', '234324', '5555', '5555', '义乌陆港', '中国银行', '9985665422110', '555555555', '0', null, null, '2019-08-05 16:50:20', '2019-08-05 16:50:20');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `ID` varchar(10) NOT NULL COMMENT 'ID',
  `permission_name` varchar(60) NOT NULL,
  `permission_code` varchar(60) NOT NULL,
  `parent_id` varchar(8) DEFAULT NULL COMMENT '父权限ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('01', '系统管理', 'system', null);
INSERT INTO `tb_permission` VALUES ('0101', '用户管理', 'user', '01');
INSERT INTO `tb_permission` VALUES ('010101', '用户列表', 'uesr_list', '0101');
INSERT INTO `tb_permission` VALUES ('010102', '用户修改', 'user_edit', '0101');
INSERT INTO `tb_permission` VALUES ('010103', '用户删除', 'user_del', '0101');
INSERT INTO `tb_permission` VALUES ('010104', '用户新增', 'user_add', '0101');
INSERT INTO `tb_permission` VALUES ('010105', '密码重置', 'reset_pwd', '0101');
INSERT INTO `tb_permission` VALUES ('0102', '角色管理', 'role', '01');
INSERT INTO `tb_permission` VALUES ('010201', '角色列表', 'role_list', '0102');
INSERT INTO `tb_permission` VALUES ('010202', '角色修改', 'role_edit', '0102');
INSERT INTO `tb_permission` VALUES ('010203', '角色删除', 'role_del', '0102');
INSERT INTO `tb_permission` VALUES ('010204', '角色新增', 'role_add', '0102');
INSERT INTO `tb_permission` VALUES ('0103', '密码修改', 'password', '01');
INSERT INTO `tb_permission` VALUES ('010301', '修改', 'password_edit', '0103');
INSERT INTO `tb_permission` VALUES ('11', '企业管理', 'company', null);
INSERT INTO `tb_permission` VALUES ('1101', '企业查询', 'company_query', '11');
INSERT INTO `tb_permission` VALUES ('110101', '列表', 'comapny_list', '1101');
INSERT INTO `tb_permission` VALUES ('110102', '新增', 'company_add', '1101');
INSERT INTO `tb_permission` VALUES ('110103', '修改', 'company_edit', '1101');
INSERT INTO `tb_permission` VALUES ('110104', '删除', 'company_delete', '1101');
INSERT INTO `tb_permission` VALUES ('110105', '查看', 'company_detail', '1101');
INSERT INTO `tb_permission` VALUES ('16', '开票管理', 'invoice', null);
INSERT INTO `tb_permission` VALUES ('1601', '开票列表', 'invoice_list', '16');
INSERT INTO `tb_permission` VALUES ('1602', '开票新增', 'invoice_add', '16');
INSERT INTO `tb_permission` VALUES ('1603', '开票编辑', 'invoice_edit', '16');
INSERT INTO `tb_permission` VALUES ('1604', '开票删除', 'invoice_del', '16');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_name` varchar(60) NOT NULL,
  `role_name` varchar(60) NOT NULL,
  `role_note` varchar(60) NOT NULL COMMENT '角色描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '3318961EQF', '超级管理员', '超级管理员', '2019-06-13 18:43:57', '2019-08-20 09:58:38');
INSERT INTO `tb_role` VALUES ('14', '3318961EQF', 'test', '测试账号', '2019-07-22 13:44:10', '2019-08-20 09:52:53');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL,
  `permission_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4423 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('4371', '14', '01');
INSERT INTO `tb_role_permission` VALUES ('4372', '14', '0101');
INSERT INTO `tb_role_permission` VALUES ('4373', '14', '010101');
INSERT INTO `tb_role_permission` VALUES ('4374', '14', '010102');
INSERT INTO `tb_role_permission` VALUES ('4375', '14', '010103');
INSERT INTO `tb_role_permission` VALUES ('4376', '14', '010104');
INSERT INTO `tb_role_permission` VALUES ('4377', '14', '010105');
INSERT INTO `tb_role_permission` VALUES ('4378', '14', '0102');
INSERT INTO `tb_role_permission` VALUES ('4379', '14', '010201');
INSERT INTO `tb_role_permission` VALUES ('4380', '14', '010202');
INSERT INTO `tb_role_permission` VALUES ('4381', '14', '010203');
INSERT INTO `tb_role_permission` VALUES ('4382', '14', '010204');
INSERT INTO `tb_role_permission` VALUES ('4383', '14', '0103');
INSERT INTO `tb_role_permission` VALUES ('4384', '14', '010301');
INSERT INTO `tb_role_permission` VALUES ('4385', '14', '11');
INSERT INTO `tb_role_permission` VALUES ('4386', '14', '1101');
INSERT INTO `tb_role_permission` VALUES ('4387', '14', '110101');
INSERT INTO `tb_role_permission` VALUES ('4388', '14', '110102');
INSERT INTO `tb_role_permission` VALUES ('4389', '14', '110103');
INSERT INTO `tb_role_permission` VALUES ('4390', '14', '110104');
INSERT INTO `tb_role_permission` VALUES ('4391', '14', '110105');
INSERT INTO `tb_role_permission` VALUES ('4392', '14', '16');
INSERT INTO `tb_role_permission` VALUES ('4393', '14', '1601');
INSERT INTO `tb_role_permission` VALUES ('4394', '14', '1602');
INSERT INTO `tb_role_permission` VALUES ('4395', '14', '1603');
INSERT INTO `tb_role_permission` VALUES ('4396', '14', '1604');
INSERT INTO `tb_role_permission` VALUES ('4397', '1', '01');
INSERT INTO `tb_role_permission` VALUES ('4398', '1', '0101');
INSERT INTO `tb_role_permission` VALUES ('4399', '1', '010101');
INSERT INTO `tb_role_permission` VALUES ('4400', '1', '010102');
INSERT INTO `tb_role_permission` VALUES ('4401', '1', '010103');
INSERT INTO `tb_role_permission` VALUES ('4402', '1', '010104');
INSERT INTO `tb_role_permission` VALUES ('4403', '1', '010105');
INSERT INTO `tb_role_permission` VALUES ('4404', '1', '0102');
INSERT INTO `tb_role_permission` VALUES ('4405', '1', '010201');
INSERT INTO `tb_role_permission` VALUES ('4406', '1', '010202');
INSERT INTO `tb_role_permission` VALUES ('4407', '1', '010203');
INSERT INTO `tb_role_permission` VALUES ('4408', '1', '010204');
INSERT INTO `tb_role_permission` VALUES ('4409', '1', '0103');
INSERT INTO `tb_role_permission` VALUES ('4410', '1', '010301');
INSERT INTO `tb_role_permission` VALUES ('4411', '1', '11');
INSERT INTO `tb_role_permission` VALUES ('4412', '1', '1101');
INSERT INTO `tb_role_permission` VALUES ('4413', '1', '110101');
INSERT INTO `tb_role_permission` VALUES ('4414', '1', '110102');
INSERT INTO `tb_role_permission` VALUES ('4415', '1', '110103');
INSERT INTO `tb_role_permission` VALUES ('4416', '1', '110104');
INSERT INTO `tb_role_permission` VALUES ('4417', '1', '110105');
INSERT INTO `tb_role_permission` VALUES ('4418', '1', '16');
INSERT INTO `tb_role_permission` VALUES ('4419', '1', '1601');
INSERT INTO `tb_role_permission` VALUES ('4420', '1', '1602');
INSERT INTO `tb_role_permission` VALUES ('4421', '1', '1603');
INSERT INTO `tb_role_permission` VALUES ('4422', '1', '1604');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company_code` varchar(60) NOT NULL,
  `user_name` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL COMMENT '密码',
  `real_name` varchar(60) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '234324', 'admin', '$2a$10$SCg95089nZ7QgB6epiQ4Eexg4OMJXEeIjUK.pBZGmh3cWvL.RwpcW', '义网通', '2019-06-13 15:08:42', '2019-08-15 17:46:11');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('5', '1', '1');
INSERT INTO `tb_user_role` VALUES ('6', '1', '14');
