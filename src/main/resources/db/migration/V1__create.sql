/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : payment

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-11-22 11:27:25
*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION',
SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill` (
	`bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`bill_no` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '业务流水号',
	`bill_money` decimal(9,2) NOT NULL COMMENT '业务发生金额',
	`bill_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '业务类型：0.支付  1.退款',
	`bill_cre_dt` datetime DEFAULT NULL,
	`bill_finish_dt` datetime DEFAULT NULL,
	`payment_id` bigint(20) DEFAULT NULL COMMENT '主键id',
	`refund_id` bigint(20) DEFAULT NULL COMMENT '主键id',
	`cre_dt` datetime NOT NULL COMMENT '创建时间',
	`up_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`bill_id`),
	KEY `payment_id` (`payment_id`),
	KEY `refund_id` (`refund_id`),
	CONSTRAINT `t_bill_ibfk_1` FOREIGN KEY (`payment_id`) REFERENCES `t_payment` (`payment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `t_bill_ibfk_2` FOREIGN KEY (`refund_id`) REFERENCES `t_refund` (`refund_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='对账单';

-- ----------------------------
-- Table structure for t_payment
-- ----------------------------
DROP TABLE IF EXISTS `t_payment`;
CREATE TABLE `t_payment` (
	`payment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`payment_no` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '平台交易流水号',
	`payment_type` tinyint(4) NOT NULL COMMENT '支付类型: 0.微信 1.支付宝 2.ApplyPay 3.银联支付',
	`payment_status` tinyint(4) NOT NULL COMMENT '支付状态: 0.待支付 1.已支付 2.已关闭 3.支付失败',
	`order_money` decimal(9,2) NOT NULL COMMENT '支付金额',
	`goods_info` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '商品标题，用于各支付平台商品描述',
	`goods_details` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '商品描述',
	`out_trade_no` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付平台交易号',
	`user_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '操作员',
	`description` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '附加数据',
	`order_cre_dt` datetime NOT NULL COMMENT '支付创建时间',
	`expire_times` int(11) DEFAULT '30' COMMENT '支付失效时间：单位分钟',
	`up_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`cre_dt` datetime NOT NULL,
	PRIMARY KEY (`payment_id`),
	UNIQUE KEY `payment_no` (`payment_no`),
	UNIQUE KEY `out_trade_no` (`out_trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支付表';

-- ----------------------------
-- Table structure for t_refund
-- ----------------------------
DROP TABLE IF EXISTS `t_refund`;
CREATE TABLE `t_refund` (
	`refund_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`refund_no` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '退款流水号',
	`out_refund_no` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方支付平台退款流水号',
	`refund_description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '退款原因',
	`order_money` decimal(9,2) NOT NULL COMMENT '支付总金额',
	`refund_money` decimal(9,2) NOT NULL COMMENT '退款金额',
	`payment_id` bigint(20) NOT NULL COMMENT '主键id',
	`refund_status` tinyint(4) DEFAULT NULL COMMENT '退款状态：0.退款处理中 1.退款成功 2.退款关闭 3.退款异常',
	`fail_reason` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '退款状态说明: 如，商户余额不足，超过可退款时间',
	`up_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`cre_dt` datetime DEFAULT NULL,
	PRIMARY KEY (`refund_id`),
	UNIQUE KEY `refund_no` (`refund_no`),
	UNIQUE KEY `out_refund_no` (`out_refund_no`),
	KEY `payment_id` (`payment_id`),
	CONSTRAINT `t_refund_ibfk_1` FOREIGN KEY (`payment_id`) REFERENCES `t_payment` (`payment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='退款表';


SET SQL_MODE=@OLD_SQL_MODE;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET FOREIGN_KEY_CHECKS=1;