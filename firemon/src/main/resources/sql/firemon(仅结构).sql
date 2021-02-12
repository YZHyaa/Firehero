/*
Navicat MySQL Data Transfer

Source Server         : firehero
Source Server Version : 50732
Source Host           : 101.37.22.13:3306
Source Database       : firemon

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2021-02-11 17:44:01
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
