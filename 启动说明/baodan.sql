CREATE DATABASE baodan;

USE baodan;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

/*用户*/
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(200) NOT NULL,
  `passwd` varchar(200) NOT NULL,
  `record_status` int(2) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` int(2) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 0, null,1,'管理员');

/*消息*/
CREATE TABLE `message` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(200) NOT NULL,
	`content` varchar(2000) NOT NULL,
	`read_flag` int(2) NOT NULL,
	`record_status` int (2) NOT NULL,
	`type` int(2) NOT NULL,
	`user_id` int(11) NOT NULL,
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*保单*/
CREATE TABLE `baodan`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`code` varchar(200) NOT NULL,  /*保单编号 0为保单模板*/
	`name` varchar(200) NOT NULL,
	`status` int(2) NOT NULL,/*未提交 0 ，待审批 1，审批通过 2，审批未通过 3*/
	`sbjine` int(20) NOT NULL,/*涉保金额*/
	`eff_date` timestamp NOT NULL,
	`exp_date` timestamp NOT NULL,
	`create_time` timestamp NOT NULL,
	`user_id` int(20) NOT NULL,/*投保人ID*/
	`type` int(11) NOT NULL,/*险种*/
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/**表单项*/
CREATE TABLE `form_item`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`baodan_id` int(11) NOT NULL,
	`form_type` varchar(200) NOT NULL,  /*综合保险主表单  附加表单 */
	`form_type_desc` varchar(200),
	`is_mult` int(1) NOT NULL, 		/*多值字段 1:多值 0：单值*/
	`field_1` varchar(200),  /*input_name_regex_value*/
	`field_2` varchar(200),
	`field_3` varchar(200),
	`field_4` varchar(200),
	`field_5` varchar(200),
	`field_6` varchar(200),
	`field_7` varchar(200),
	`field_8` varchar(200),
	`field_9` varchar(200),
	`field_10` varchar(200),
	`field_11` varchar(200),
	`field_12` varchar(200),
	`field_13` varchar(200),
	`field_14` varchar(200),
	`field_15` varchar(200),
	`field_16` varchar(200),
	`field_17` varchar(200),
	`field_18` varchar(200),
	`field_19` varchar(200),
	`field_20` varchar(200),
	`field_21` varchar(200),
	`field_22` varchar(200),
	`field_23` varchar(200),
	`field_24` varchar(200),
	`field_25` varchar(200),
	`field_26` varchar(200),
	`field_27` varchar(200),
	`field_28` varchar(200),
	`field_29` varchar(200),
	`field_30` varchar(200),
	`field_31` varchar(200),
	`field_32` varchar(200),
	`field_33` varchar(200),
	`field_34` varchar(200),
	`field_35` varchar(200),
	`field_36` varchar(200),
	`field_37` varchar(200),
	`field_38` varchar(200),
	`field_39` varchar(200),
	`field_40` varchar(200),
	`field_41` varchar(200),
	`field_42` varchar(200),
	`field_43` varchar(200),
	`field_44` varchar(200),
	`field_45` varchar(200),
	`field_46` varchar(200),
	`field_47` varchar(200),
	`field_48` varchar(200),
	`field_49` varchar(200),
	`field_50` varchar(200),
	`field_51` varchar(200),
	`field_52` varchar(200),
	`field_53` varchar(200),
	`field_54` varchar(200),
	`field_55` varchar(200),
	`field_56` varchar(200),
	`field_57` varchar(200),
	`field_58` varchar(200),
	`field_59` varchar(200),
	`field_60` varchar(200),
	`field_61` varchar(200),
	`field_62` varchar(200),
	`field_63` varchar(200),
	`field_64` varchar(200),
	`field_65` varchar(200),
	`field_66` varchar(200),
	`field_67` varchar(200),
	`field_68` varchar(200),
	`field_69` varchar(200),
	`field_70` varchar(200),
	`field_71` varchar(200),
	`field_72` varchar(200),
	`field_73` varchar(200),
	`field_74` varchar(200),
	`field_75` varchar(200),
	`field_76` varchar(200),
	`field_77` varchar(200),
	`field_78` varchar(200),
	`field_79` varchar(200),
	`field_80` varchar(200),
	`field_81` varchar(200),
	`field_82` varchar(200),
	`field_83` varchar(200),
	`field_84` varchar(200),
	`field_85` varchar(200),
	`field_86` varchar(200),
	`field_87` varchar(200),
	`field_88` varchar(200),
	`field_89` varchar(200),
	`field_90` varchar(200),
	`field_91` varchar(200),
	`field_92` varchar(200),
	`field_93` varchar(200),
	`field_94` varchar(200),
	`field_95` varchar(200),
	`field_96` varchar(200),
	`field_97` varchar(200),
	`field_98` varchar(200),
	`field_99` varchar(200),
	`field_100` varchar(200),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
