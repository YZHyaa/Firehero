/*
Navicat MySQL Data Transfer

Source Server         : firehero
Source Server Version : 50732
Source Host           : 101.37.22.13:3306
Source Database       : firemon

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2021-02-11 17:43:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_chart
-- ----------------------------
DROP TABLE IF EXISTS `t_chart`;
CREATE TABLE `t_chart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_chart
-- ----------------------------
INSERT INTO `t_chart` VALUES ('1', '2020-12-25 23:59:06', '{\"smog\":200, \"co\":200, \"co2\":200, \"pm\":200, \"temperature\":200}');
INSERT INTO `t_chart` VALUES ('2', '2020-12-26 00:02:56', '{\"smog\":200, \"co\":200, \"co2\":200, \"pm\":200, \"temperature\":200}');
INSERT INTO `t_chart` VALUES ('3', '2020-12-26 00:41:30', '{\"smog\":100, \"co\":200, \"co2\":200, \"pm\":200, \"temperature\":200}');
INSERT INTO `t_chart` VALUES ('4', '2020-12-26 00:53:56', '{\"smog\":200, \"co\":200, \"co2\":200, \"pm\":200, \"temperature\":200}');
INSERT INTO `t_chart` VALUES ('5', '2020-12-26 00:54:44', '{\"smog\":200, \"co\":100, \"co2\":300, \"pm\":200, \"temperature\":200}');
INSERT INTO `t_chart` VALUES ('6', '2020-12-26 01:02:03', '{\"smog\":200, \"co\":300, \"co2\":200, \"pm\":200, \"temperature\":100}');
INSERT INTO `t_chart` VALUES ('7', '2020-12-26 01:43:48', '{\"smog\":200, \"co\":300, \"co2\":200, \"pm\":200, \"temperature\":100}');
INSERT INTO `t_chart` VALUES ('8', '2020-12-26 15:38:41', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('9', '2020-12-26 15:38:44', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('10', '2020-12-26 15:39:00', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('11', '2020-12-26 15:39:02', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('12', '2020-12-26 15:40:18', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('13', '2020-12-26 15:40:20', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('14', '2020-12-26 17:03:37', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');
INSERT INTO `t_chart` VALUES ('15', '2020-12-26 17:09:29', '{\"smog\":200,\"co\":200,\"co2\":200,\"pm\":200,\"temperature\":200}');

-- ----------------------------
-- Table structure for t_gateway
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway`;
CREATE TABLE `t_gateway` (
  `gateway_id` int(11) NOT NULL AUTO_INCREMENT,
  `gateway_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gateway_ghz` varchar(255) NOT NULL,
  `gateway_transfer_rate` varchar(255) NOT NULL,
  `gateway_flash_memory` varchar(255) NOT NULL,
  `gateway_memory` varchar(255) NOT NULL,
  `firmware_version` varchar(255) NOT NULL,
  `gateway_mac` varchar(255) NOT NULL,
  `first_dns` varchar(255) NOT NULL,
  `next_dns` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_gateway
-- ----------------------------
INSERT INTO `t_gateway` VALUES ('1', '2020-11-21 11:13:12', '580MHz', '1167Mbps', '8MB', '64MB', 'MT7628AN', '1C-68-7E-8D-7D-56', '61.134.1.5', '218.30.19.50', '1');
INSERT INTO `t_gateway` VALUES ('4', '2020-11-21 13:21:20', '580MHz', '1167Mbps', '8MB', '64MB', 'MT7628AN', '1C-68-7E-8D-7D-56', '61.134.1.5', '218.30.19.50', '6');
INSERT INTO `t_gateway` VALUES ('5', '2020-11-21 13:24:41', '580MHz', '1167Mbps', '8MB', '64MB', 'MT7628AN', '1C-68-7E-8D-7D-56', '61.134.1.5', '218.30.19.50', '7');
INSERT INTO `t_gateway` VALUES ('6', '2020-11-21 13:28:48', '580MHz', '1167Mbps', '8MB', '64MB', 'MT7628AN', '1C-68-7E-8D-7D-56', '61.134.1.5', '218.30.19.50', '8');

-- ----------------------------
-- Table structure for t_robot
-- ----------------------------
DROP TABLE IF EXISTS `t_robot`;
CREATE TABLE `t_robot` (
  `robot_id` int(11) NOT NULL AUTO_INCREMENT,
  `gateway_id` int(11) DEFAULT NULL,
  `unit_type` varchar(255) NOT NULL,
  `robot_mac` varchar(255) NOT NULL,
  PRIMARY KEY (`robot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_robot
-- ----------------------------
INSERT INTO `t_robot` VALUES ('1', '1', 'FR200515', '2C-78-8E-9D-8D-6');
INSERT INTO `t_robot` VALUES ('2', '2', 'FR200515', '2C-78-8E-9D-8D-66');

-- ----------------------------
-- Table structure for t_sensor
-- ----------------------------
DROP TABLE IF EXISTS `t_sensor`;
CREATE TABLE `t_sensor` (
  `sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `gateway_id` int(11) DEFAULT NULL,
  `unit_type` varchar(255) DEFAULT NULL,
  `sensor_mac` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sensor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_sensor
-- ----------------------------
INSERT INTO `t_sensor` VALUES ('1', '1', 'SE200515', '1A-79-7F-8E-7E-67');
INSERT INTO `t_sensor` VALUES ('2', '2', 'SE200515', '1A-79-7F-8E-7E-67');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(32) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '陈子旭', '15592060409', '1249858341@qq.com', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `t_user` VALUES ('6', 'test', '18189093292', '1234566822@qq.com', '123456');
INSERT INTO `t_user` VALUES ('7', '张三', '15389093292', '1327584236@qq.com', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `t_user` VALUES ('8', 'Adam', '18651233256', '1955194781@qq.com', 'b29692733b10ca3657c554ea6021f12f');
INSERT INTO `t_user` VALUES ('9', 'test', '18189093292', '1234566822@qq.com', '123456');
INSERT INTO `t_user` VALUES ('10', 'test', '18189093292', '1234566822@qq.com', '123456');
INSERT INTO `t_user` VALUES ('11', 'test', '18189093292', '1234566822@qq.com', '123456');
