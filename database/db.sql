-- MySQL dump 10.13  Distrib 5.6.20, for Win32 (x86)
--
-- Host: localhost    Database: parking
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destination` (
  `ID_DESTINATION` int(11) NOT NULL AUTO_INCREMENT,
  `DESTINATION_NAME` varchar(1024) DEFAULT NULL,
  `DESTINATION_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_DESTINATION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES (1,'train station',15),(2,'airport',50),(3,'main square',25);
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distance_entrance`
--

DROP TABLE IF EXISTS `distance_entrance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distance_entrance` (
  `ID_DISTANCE_ENTRANCE` int(11) NOT NULL AUTO_INCREMENT,
  `DISTANCE_NAME` varchar(1024) DEFAULT NULL,
  `DISTANCE_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_DISTANCE_ENTRANCE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distance_entrance`
--

LOCK TABLES `distance_entrance` WRITE;
/*!40000 ALTER TABLE `distance_entrance` DISABLE KEYS */;
INSERT INTO `distance_entrance` VALUES (1,'Close',15),(2,'Average',7),(3,'Far',5),(4,'Very Far',0);
/*!40000 ALTER TABLE `distance_entrance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `on_demand`
--

DROP TABLE IF EXISTS `on_demand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `on_demand` (
  `ID_ON_DEMAND` int(11) NOT NULL AUTO_INCREMENT,
  `ID_RANGE` int(11) DEFAULT NULL,
  `STARTTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_ON_DEMAND`),
  KEY `FK_RELATIONSHIP_9` (`ID_RANGE`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`ID_RANGE`) REFERENCES `ranges` (`ID_RANGE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `on_demand`
--

LOCK TABLES `on_demand` WRITE;
/*!40000 ALTER TABLE `on_demand` DISABLE KEYS */;
/*!40000 ALTER TABLE `on_demand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `ID_PARKING` int(11) NOT NULL AUTO_INCREMENT,
  `PARKING_NAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID_PARKING`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (13,'p1'),(14,'p1'),(15,'p1');
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_place`
--

DROP TABLE IF EXISTS `parking_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_place` (
  `ID_PARKING_PLACE` int(11) NOT NULL AUTO_INCREMENT,
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
  CONSTRAINT `fk_place_parking_1` FOREIGN KEY (`ID_PARKING`) REFERENCES `parking` (`ID_PARKING`),
  CONSTRAINT `fk_place_status_1` FOREIGN KEY (`ID_STATUS`) REFERENCES `status` (`ID_STATUS`),
  CONSTRAINT `fk_place_type_reservation_1` FOREIGN KEY (`ID_TYPE_RESERVATION`) REFERENCES `type_reservation` (`ID_TYPE_RESERVATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_place`
--

LOCK TABLES `parking_place` WRITE;
/*!40000 ALTER TABLE `parking_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranges`
--

DROP TABLE IF EXISTS `ranges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranges` (
  `ID_RANGE` int(11) NOT NULL AUTO_INCREMENT,
  `RANGE_NAME` varchar(1024) DEFAULT NULL,
  `RANGE_KM` int(11) DEFAULT NULL,
  `RANGE_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_RANGE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranges`
--

LOCK TABLES `ranges` WRITE;
/*!40000 ALTER TABLE `ranges` DISABLE KEYS */;
INSERT INTO `ranges` VALUES (1,'Close',5,10),(2,'Average',10,18),(3,'Far',15,25),(4,'Very Far',50,30);
/*!40000 ALTER TABLE `ranges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_reservation`
--

DROP TABLE IF EXISTS `regular_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_reservation`
--

LOCK TABLES `regular_reservation` WRITE;
/*!40000 ALTER TABLE `regular_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `regular_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_service`
--

DROP TABLE IF EXISTS `shuttle_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_service` (
  `ID_SHUTTLE_SERVICE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_DESTINATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_SHUTTLE_SERVICE`),
  KEY `FK_RELATIONSHIP_10` (`ID_DESTINATION`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`ID_DESTINATION`) REFERENCES `destination` (`ID_DESTINATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_service`
--

LOCK TABLES `shuttle_service` WRITE;
/*!40000 ALTER TABLE `shuttle_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `shuttle_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `ID_STATUS` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS_NAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID_STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Ocupied'),(2,'Reserved'),(3,'Empty'),(4,'In Maintenance');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_reservation`
--

DROP TABLE IF EXISTS `type_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_reservation` (
  `ID_TYPE_RESERVATION` int(11) NOT NULL AUTO_INCREMENT,
  `RESERVATION_TYPE` varchar(1024) DEFAULT NULL,
  `RESERVATION_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_TYPE_RESERVATION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_reservation`
--

LOCK TABLES `type_reservation` WRITE;
/*!40000 ALTER TABLE `type_reservation` DISABLE KEYS */;
INSERT INTO `type_reservation` VALUES (1,'Car',20),(2,'Truck',50),(3,'VIP',40);
/*!40000 ALTER TABLE `type_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(1024) DEFAULT NULL,
  `PASSWORD` varchar(1024) DEFAULT NULL,
  `ACCESS_LEVEL` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'david','admin',2),(2,'rato','admin',2),(3,'passinhas','admin',2),(4,'rick','admin',2),(5,'gonzalo','admin',2),(6,'pedro','pedro',1),(8,'alice','abcde',1),(9,'bob','password',1),(10,'vitor','admin',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-30 19:30:45
