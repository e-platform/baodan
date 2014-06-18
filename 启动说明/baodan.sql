drop database if exists baodan;
CREATE DATABASE baodan;

USE baodan;

/*用户*/
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(200) NOT NULL,
  `passwd` varchar(200) NOT NULL,
  `record_status` int(2) comment '记录状态 0：正常 1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_time` timestamp,
  `role` int(2) NOT NULL comment '角色 0：普通用户  1：系统管理员',
  `name` varchar(200) NOT NULL comment '名称', 
  `address` varchar(200) comment '地址',
  `phone` varchar(20) comment '电话',
  `zipcode` varchar(20) comment '邮编',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 0, null,null,2,'超级管理员','','','');
INSERT INTO `user` VALUES (2, 'xingzl', 'xingzl', 0, null,null,1,'管理员','','','');
INSERT INTO `user` VALUES (3, 'zhangsan', 'zhangsan', 0, null,null,0,'张三','','','');

/*消息*/
CREATE TABLE `message` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(200) NOT NULL,
	`content` varchar(2000),
	`read_flag` int(2) NOT NULL comment '已读标识0：未读， 1：已读',
	`record_status` int(2) NOT NULL comment '记录状态 0：正常 1：已删除',
	`type` int(2) NOT NULL comment '消息分类1:保单受理通知,2:理赔受理通知, 3:系统通知',
	`user_id` int(11) NOT NULL,
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*保单*/
CREATE TABLE `baodan`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`code` varchar(200) NOT NULL,  /*保单编号 0为保单模板*/
	`name` varchar(200) NOT NULL,
	`status` int(2) NOT NULL,/*待审批 1，审批通过 2，审批未通过 3*/
	`sbjine` int(20) NOT NULL,/*涉保金额*/
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`eff_date` timestamp NOT NULL,
	`exp_date` timestamp NOT NULL,
	`user_id` int(20) NOT NULL,/*投保人ID*/
	`record_status` int(2) NOT NULL comment '记录状态 0：正常 1：已删除',
	`assurance_class_id` int(11) NOT NULL comment '险种',
	`reason` varchar(1000) COMMENT '审批不通过时填写的理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*附件*/
CREATE TABLE `attachment` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(200) NOT NULL,
	`size` int(11) NOT NULL,
	`extension` varchar(20) NOT NULL,
	`path` varchar(200) NOT NULL comment '文件存储路径',
	`user_id` int(11) NOT NULL,
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*理赔单 */
create table `lipei`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`code` varchar(200) NOT NULL comment '理赔单编号',
	`baodan_id` int(11) NOT NULL,
	`status` int(2) NOT NULL comment '理赔状态',
	`sbjine` int(11) NOT NULL comment '涉保金额',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`record_status` int(2) NOT NULL comment '记录状态 0：正常 1：已删除',
	`eff_date` timestamp NOT NULL,
	`exp_date` timestamp NOT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*理赔单 附件关系*/
create table `lipei_attachment`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`lipei_id` int(11) NOT NULL comment '理赔单外键',
	`attachment_id` int(2) NOT NULL comment '附件外键',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*报单 附件关系*/
create table `baodan_attachment`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`baodan_id` int(11) NOT NULL comment '保单外键',
	`attachment_id` int(2) NOT NULL comment '附件外键',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*保险分类*/
create table `assurance_class`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`parent_id` int(11) NOT NULL comment '父级分类ID',
	`name` varchar(200) NOT NULL comment '分类名称',
	`depth` int(11) NOT NULL COMMENT '深度',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;