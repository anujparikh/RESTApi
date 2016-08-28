CREATE DATABASE  IF NOT EXISTS `restaurant_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `restaurant_db`;
-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant_db
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_info` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `role` char(1) NOT NULL DEFAULT 'M',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

LOCK TABLES `admin_info` WRITE;
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES ('anujparikh','123456','Anuj','Parikh','M');
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_info`
--

DROP TABLE IF EXISTS `customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_info` (
  `CustomerId` int(5) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `PhoneNo` varchar(12) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_info`
--

LOCK TABLES `customer_info` WRITE;
/*!40000 ALTER TABLE `customer_info` DISABLE KEYS */;
INSERT INTO `customer_info` VALUES (1,'Anuj','Parikh','248-877-8677','anujparikh1@gmail.com'),(2,'Palak','Mehta','901-60-25354','mehta.palak.p@gmail.com'),(3,'Karthik','Padthe','123-456-7890','karthik.padthe@gmail.com'),(4,'Timir','Desai','123-123-1234','desai.timir@gmail.com');
/*!40000 ALTER TABLE `customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_info`
--

DROP TABLE IF EXISTS `reservation_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_info` (
  `ReservationId` int(5) NOT NULL AUTO_INCREMENT,
  `CustomerId` int(5) NOT NULL,
  `Date` date NOT NULL,
  `FromTime` time(3) NOT NULL,
  `ToTime` time(3) NOT NULL,
  `PartySize` int(3) DEFAULT '1',
  `Notes` varchar(100) DEFAULT NULL,
  `Status` char(1) NOT NULL DEFAULT 'W',
  `TableAssigned` int(5) DEFAULT NULL,
  PRIMARY KEY (`ReservationId`),
  KEY `CustomerId_idx` (`CustomerId`),
  CONSTRAINT `FKCustomerId` FOREIGN KEY (`CustomerId`) REFERENCES `customer_info` (`CustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_info`
--

LOCK TABLES `reservation_info` WRITE;
/*!40000 ALTER TABLE `reservation_info` DISABLE KEYS */;
INSERT INTO `reservation_info` VALUES (1,1,'2015-09-01','16:00:00.000','17:00:00.000',2,'notes','W',NULL),(2,2,'2015-09-01','18:00:00.000','19:00:00.000',2,'notes','W',NULL),(3,1,'2015-09-01','14:00:00.000','16:00:00.000',2,'notes','W',NULL),(4,1,'2015-09-01','18:00:00.000','19:00:00.000',2,'Candle Light','W',NULL),(5,2,'2015-09-01','16:00:00.000','17:00:00.000',3,'nothing','W',102),(6,3,'2015-09-01','15:00:00.000','16:00:00.000',2,'making reservatoin','W',NULL),(7,1,'2015-09-02','16:00:00.000','16:00:00.000',2,'Notes','W',NULL),(8,4,'2015-09-03','19:00:00.000','21:00:00.000',4,'Timir','C',NULL);
/*!40000 ALTER TABLE `reservation_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_profile`
--

DROP TABLE IF EXISTS `restaurant_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_profile` (
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `PhoneNo` varchar(12) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `AutoAssign` char(1) NOT NULL DEFAULT 'Y',
  `CloseDays` varchar(45) NOT NULL,
  `OpenTime` time NOT NULL,
  `CloseTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_profile`
--

LOCK TABLES `restaurant_profile` WRITE;
/*!40000 ALTER TABLE `restaurant_profile` DISABLE KEYS */;
INSERT INTO `restaurant_profile` VALUES ('myRESTAURANT','anujparikh1@gmail.com','248-877-8677','5440 Cass Ave Detroit','N','M, T','09:00:00','23:30:00');
/*!40000 ALTER TABLE `restaurant_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_info`
--

DROP TABLE IF EXISTS `table_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_info` (
  `tableId` int(5) NOT NULL,
  `resId` int(5) DEFAULT NULL,
  `custId` int(5) DEFAULT NULL,
  `tableCap` int(2) DEFAULT NULL,
  `tableStatus` char(1) DEFAULT 'N',
  PRIMARY KEY (`tableId`),
  UNIQUE KEY `resId_UNIQUE` (`resId`),
  KEY `fkReservationId_idx` (`resId`),
  KEY `fkCustomerId_idx` (`custId`),
  CONSTRAINT `fkCustIdTab` FOREIGN KEY (`custId`) REFERENCES `customer_info` (`CustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkResIdTab` FOREIGN KEY (`resId`) REFERENCES `reservation_info` (`ReservationId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_info`
--

LOCK TABLES `table_info` WRITE;
/*!40000 ALTER TABLE `table_info` DISABLE KEYS */;
INSERT INTO `table_info` VALUES (101,5,3,8,'N'),(102,2,3,8,'Y'),(103,3,2,6,'Y'),(104,6,1,4,'N'),(105,1,2,2,'N'),(106,4,1,6,'N');
/*!40000 ALTER TABLE `table_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-08  9:53:49
