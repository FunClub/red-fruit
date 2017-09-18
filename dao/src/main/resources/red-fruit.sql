/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : red-fruit

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-13 00:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `passwords` varchar(24) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `born` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `profile_img` mediumtext,
  `original_profile_img` mediumtext,
  `city` varchar(20) DEFAULT NULL,
  `profession` varchar(20) DEFAULT NULL,
  `hobby` varchar(20) DEFAULT NULL,
  `character` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201328 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('201327', '9046202721@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', '害羞的八月瓜', '1995/1/1', '1', 'profile/defaultMeImg.png', 'profile/defaultMeImg.png', '重庆', null, null, null);