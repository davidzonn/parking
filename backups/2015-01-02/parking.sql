/*
Navicat MySQL Data Transfer

Source Server         : mysqlConnection
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2015-01-02 21:59:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for destination
-- ----------------------------
DROP TABLE IF EXISTS `destination`;
CREATE TABLE `destination` (
  `ID_DESTINATION` int(11) NOT NULL AUTO_INCREMENT,
  `DESTINATION_NAME` varchar(1024) DEFAULT NULL,
  `DESTINATION_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_DESTINATION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of destination
-- ----------------------------
INSERT INTO `destination` VALUES ('1', 'train station', '15');
INSERT INTO `destination` VALUES ('2', 'airport', '50');
INSERT INTO `destination` VALUES ('3', 'main square', '25');

-- ----------------------------
-- Table structure for distance_entrance
-- ----------------------------
DROP TABLE IF EXISTS `distance_entrance`;
CREATE TABLE `distance_entrance` (
  `ID_DISTANCE_ENTRANCE` int(11) NOT NULL AUTO_INCREMENT,
  `DISTANCE_NAME` varchar(1024) DEFAULT NULL,
  `DISTANCE_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_DISTANCE_ENTRANCE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of distance_entrance
-- ----------------------------
INSERT INTO `distance_entrance` VALUES ('1', 'Close', '15');
INSERT INTO `distance_entrance` VALUES ('2', 'Average', '7');
INSERT INTO `distance_entrance` VALUES ('3', 'Far', '5');
INSERT INTO `distance_entrance` VALUES ('4', 'Very Far', '0');

-- ----------------------------
-- Table structure for on_demand
-- ----------------------------
DROP TABLE IF EXISTS `on_demand`;
CREATE TABLE `on_demand` (
  `ID_ON_DEMAND` int(11) NOT NULL AUTO_INCREMENT,
  `ID_RANGE` int(11) DEFAULT NULL,
  `STARTTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_ON_DEMAND`),
  KEY `FK_RELATIONSHIP_9` (`ID_RANGE`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`ID_RANGE`) REFERENCES `ranges` (`ID_RANGE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of on_demand
-- ----------------------------

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `ID_OPERATION` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SHUTTLE_SERVICE` int(11) DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_REGULAR_RESERVATION` int(11) DEFAULT NULL,
  `ID_ON_DEMAND` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_OPERATION`),
  KEY `FK_RELATIONSHIP_12` (`ID_USER`),
  KEY `FK_RELATIONSHIP_6` (`ID_REGULAR_RESERVATION`),
  KEY `FK_RELATIONSHIP_7` (`ID_SHUTTLE_SERVICE`),
  KEY `FK_RELATIONSHIP_8` (`ID_ON_DEMAND`),
  CONSTRAINT `FK_RELATIONSHIP_12` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`),
  CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`ID_REGULAR_RESERVATION`) REFERENCES `regular_reservation` (`ID_REGULAR_RESERVATION`),
  CONSTRAINT `FK_RELATIONSHIP_7` FOREIGN KEY (`ID_SHUTTLE_SERVICE`) REFERENCES `shuttle_service` (`ID_SHUTTLE_SERVICE`),
  CONSTRAINT `FK_RELATIONSHIP_8` FOREIGN KEY (`ID_ON_DEMAND`) REFERENCES `on_demand` (`ID_ON_DEMAND`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of operation
-- ----------------------------

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking` (
  `ID_PARKING` int(11) NOT NULL AUTO_INCREMENT,
  `PARKING_NAME` varchar(1024) DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PARKING`),
  KEY `PARKING_USER_FK` (`ID_USER`),
  CONSTRAINT `PARKING_USER_FK` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parking
-- ----------------------------

-- ----------------------------
-- Table structure for parking_place
-- ----------------------------
DROP TABLE IF EXISTS `parking_place`;
CREATE TABLE `parking_place` (
  `ID_PARKING_PLACE` int(11) NOT NULL AUTO_INCREMENT,
  `PARKING_PLACE_NUMBER` int(11) DEFAULT NULL,
  `ID_DISTANCE_ENTRANCE` int(11) DEFAULT NULL,
  `ID_PARKING` int(11) DEFAULT NULL,
  `ID_STATUS` int(11) DEFAULT NULL,
  `ID_TYPE_RESERVATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PARKING_PLACE`),
  KEY `fk_place_distance_entrance_1` (`ID_DISTANCE_ENTRANCE`),
  KEY `fk_place_parking_1` (`ID_PARKING`),
  KEY `fk_place_status_1` (`ID_STATUS`),
  KEY `fk_place_type_reservation_1` (`ID_TYPE_RESERVATION`),
  CONSTRAINT `fk_place_distance_entrance_1` FOREIGN KEY (`ID_DISTANCE_ENTRANCE`) REFERENCES `distance_entrance` (`ID_DISTANCE_ENTRANCE`),
  CONSTRAINT `fk_place_parking_1` FOREIGN KEY (`ID_PARKING`) REFERENCES `parking` (`ID_PARKING`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_place_status_1` FOREIGN KEY (`ID_STATUS`) REFERENCES `status` (`ID_STATUS`),
  CONSTRAINT `fk_place_type_reservation_1` FOREIGN KEY (`ID_TYPE_RESERVATION`) REFERENCES `type_reservation` (`ID_TYPE_RESERVATION`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parking_place
-- ----------------------------

-- ----------------------------
-- Table structure for ranges
-- ----------------------------
DROP TABLE IF EXISTS `ranges`;
CREATE TABLE `ranges` (
  `ID_RANGE` int(11) NOT NULL AUTO_INCREMENT,
  `RANGE_NAME` varchar(1024) DEFAULT NULL,
  `RANGE_KM` int(11) DEFAULT NULL,
  `RANGE_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_RANGE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ranges
-- ----------------------------
INSERT INTO `ranges` VALUES ('1', 'Close', '5', '10');
INSERT INTO `ranges` VALUES ('2', 'Average', '10', '18');
INSERT INTO `ranges` VALUES ('3', 'Far', '15', '25');
INSERT INTO `ranges` VALUES ('4', 'Very Far', '50', '30');

-- ----------------------------
-- Table structure for regular_reservation
-- ----------------------------
DROP TABLE IF EXISTS `regular_reservation`;
CREATE TABLE `regular_reservation` (
  `ID_REGULAR_RESERVATION` int(11) NOT NULL AUTO_INCREMENT,
  `STARTTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ENDTS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LENGHTTS` int(11) DEFAULT NULL,
  `ID_PARKING_PLACE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_REGULAR_RESERVATION`),
  KEY `fk_regular_reservation_parking_1` (`ID_PARKING_PLACE`),
  CONSTRAINT `fk_regular_reservation_parking_1` FOREIGN KEY (`ID_PARKING_PLACE`) REFERENCES `parking_place` (`ID_PARKING_PLACE`),
  CONSTRAINT `fk_regular_reservation_parking_place_1` FOREIGN KEY (`ID_PARKING_PLACE`) REFERENCES `parking_place` (`ID_PARKING_PLACE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of regular_reservation
-- ----------------------------

-- ----------------------------
-- Table structure for shuttle_service
-- ----------------------------
DROP TABLE IF EXISTS `shuttle_service`;
CREATE TABLE `shuttle_service` (
  `ID_SHUTTLE_SERVICE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_DESTINATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_SHUTTLE_SERVICE`),
  KEY `FK_RELATIONSHIP_10` (`ID_DESTINATION`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`ID_DESTINATION`) REFERENCES `destination` (`ID_DESTINATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of shuttle_service
-- ----------------------------

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `ID_STATUS` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS_NAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID_STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', 'Ocupied');
INSERT INTO `status` VALUES ('2', 'Reserved');
INSERT INTO `status` VALUES ('3', 'Empty');
INSERT INTO `status` VALUES ('4', 'In Maintenance');

-- ----------------------------
-- Table structure for type_reservation
-- ----------------------------
DROP TABLE IF EXISTS `type_reservation`;
CREATE TABLE `type_reservation` (
  `ID_TYPE_RESERVATION` int(11) NOT NULL AUTO_INCREMENT,
  `RESERVATION_TYPE` varchar(1024) DEFAULT NULL,
  `RESERVATION_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_TYPE_RESERVATION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of type_reservation
-- ----------------------------
INSERT INTO `type_reservation` VALUES ('1', 'Car', '20');
INSERT INTO `type_reservation` VALUES ('2', 'Truck', '50');
INSERT INTO `type_reservation` VALUES ('3', 'VIP', '40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(1024) DEFAULT NULL,
  `PASSWORD` varchar(1024) DEFAULT NULL,
  `ACCESS_LEVEL` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'david', 'admin', '2');
INSERT INTO `user` VALUES ('2', 'rato', 'admin', '2');
INSERT INTO `user` VALUES ('3', 'passinhas', 'admin', '2');
INSERT INTO `user` VALUES ('4', 'rick', 'admin', '2');
INSERT INTO `user` VALUES ('5', 'gonzalo', 'admin', '2');
INSERT INTO `user` VALUES ('6', 'pedro', 'pedro', '1');
INSERT INTO `user` VALUES ('8', 'alice', 'abcde', '1');
INSERT INTO `user` VALUES ('9', 'bob', 'password', '1');
INSERT INTO `user` VALUES ('10', 'vitor', 'admin', '3');
