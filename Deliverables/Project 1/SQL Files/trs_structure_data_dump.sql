-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ticket_registration_system
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `CardNumber` int NOT NULL,
  `RUEmail` varchar(320) NOT NULL,
  `AccountType` varchar(10) DEFAULT NULL,
  `ExpDate` varchar(5) DEFAULT NULL,
  `CVV` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`CardNumber`),
  KEY `RUEmail` (`RUEmail`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`RUEmail`) REFERENCES `reguser` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (11111111,'user1@gmail.com','Credit','01/25','111'),(22222222,'user2@gmail.com','Debit','01/25','222'),(33333333,'user3@gmail.com','Credit','01/25','333'),(44444444,'user3@gmail.com','Debit','01/25','444'),(55555555,'user4@gmail.com','Credit','01/25','555'),(66666666,'user4@gmail.com','Debit','01/25','666'),(77777777,'user2@gmail.com','Credit','01/25','777');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `Name` varchar(30) NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `ReleaseDate` date DEFAULT NULL,
  `Genre` varchar(20) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `PubliclyAnnounced` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('Cars','Movie about cars','2006-06-09','Animation',10,1),('Finding Nemo','Movie about fish','2003-05-30','Animation',11,0),('Ratatouille - Unannounced','Movie about rats','2007-06-29','Animation',15,0),('Soul','Movie about life','2012-12-25','Animation',12,1),('The Incredibles','Movie about superheros','2004-11-05','Animation',13,0),('Toy Story','Movie about toys','2011-11-22','Animation',14,1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reguser`
--

DROP TABLE IF EXISTS `reguser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reguser` (
  `Email` varchar(320) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `FName` varchar(20) DEFAULT NULL,
  `LName` varchar(20) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `AccountFeeStatus` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reguser`
--

LOCK TABLES `reguser` WRITE;
/*!40000 ALTER TABLE `reguser` DISABLE KEYS */;
INSERT INTO `reguser` VALUES ('user1@gmail.com','password','Liam','Smith','8125 Blackburn Ave  Kentville, NS','Paid'),('user2@gmail.com','password','Emma','Taylor','8603 W Lake Forest St Meadow Lake, SK','Paid'),('user3@gmail.com','password','Benjamin','Miller','98 Rockledge Rd Belledune, NB','Paid'),('user4@gmail.com','password','Evelyn','Harris','324 Crescent Lane Meadow Lake, SK','Unpaid');
/*!40000 ALTER TABLE `reguser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruticket`
--

DROP TABLE IF EXISTS `ruticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruticket` (
  `RUEmail` varchar(320) NOT NULL,
  `TicketNumber` int NOT NULL,
  PRIMARY KEY (`RUEmail`,`TicketNumber`),
  KEY `TicketNumber` (`TicketNumber`),
  CONSTRAINT `ruticket_ibfk_1` FOREIGN KEY (`RUEmail`) REFERENCES `reguser` (`Email`),
  CONSTRAINT `ruticket_ibfk_2` FOREIGN KEY (`TicketNumber`) REFERENCES `ticket` (`TicketNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruticket`
--

LOCK TABLES `ruticket` WRITE;
/*!40000 ALTER TABLE `ruticket` DISABLE KEYS */;
INSERT INTO `ruticket` VALUES ('user1@gmail.com',10000),('user2@gmail.com',10001),('user3@gmail.com',10002);
/*!40000 ALTER TABLE `ruticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruvoucher`
--

DROP TABLE IF EXISTS `ruvoucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruvoucher` (
  `RUEmail` varchar(320) NOT NULL,
  `VoucherNumber` int NOT NULL,
  PRIMARY KEY (`RUEmail`,`VoucherNumber`),
  KEY `VoucherNumber` (`VoucherNumber`),
  CONSTRAINT `ruvoucher_ibfk_1` FOREIGN KEY (`RUEmail`) REFERENCES `reguser` (`Email`),
  CONSTRAINT `ruvoucher_ibfk_2` FOREIGN KEY (`VoucherNumber`) REFERENCES `voucher` (`VoucherNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruvoucher`
--

LOCK TABLES `ruvoucher` WRITE;
/*!40000 ALTER TABLE `ruvoucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `ruvoucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtime`
--

DROP TABLE IF EXISTS `showtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtime` (
  `Time` datetime NOT NULL,
  `TheatreName` varchar(30) NOT NULL,
  `RoomNumber` int NOT NULL,
  `MovieName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Time`,`TheatreName`,`RoomNumber`),
  KEY `TheatreName` (`TheatreName`),
  KEY `MovieName` (`MovieName`),
  CONSTRAINT `showtime_ibfk_1` FOREIGN KEY (`TheatreName`) REFERENCES `theatre` (`Name`),
  CONSTRAINT `showtime_ibfk_2` FOREIGN KEY (`MovieName`) REFERENCES `movie` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtime`
--

LOCK TABLES `showtime` WRITE;
/*!40000 ALTER TABLE `showtime` DISABLE KEYS */;
INSERT INTO `showtime` VALUES ('2020-12-25 17:00:00','Cineplex Crowfoot',1,'Cars'),('2020-12-25 18:00:00','Cineplex Crowfoot',3,'Cars'),('2020-12-25 19:00:00','Cineplex Crowfoot',2,'Cars'),('2020-12-25 19:00:00','Cineplex Crowfoot',1,'Finding Nemo'),('2020-12-25 20:00:00','Cineplex Crowfoot',3,'Finding Nemo'),('2020-12-25 21:00:00','Cineplex Crowfoot',3,'Ratatouille - Unannounced'),('2020-12-25 18:00:00','Cineplex Crowfoot',2,'Soul'),('2020-12-25 19:00:00','Cineplex Crowfoot',3,'Soul'),('2020-12-25 17:00:00','Cineplex Crowfoot',2,'The Incredibles'),('2020-12-25 20:00:00','Cineplex Crowfoot',1,'The Incredibles'),('2020-12-25 17:00:00','Cineplex Crowfoot',3,'Toy Story'),('2020-12-25 18:00:00','Cineplex Crowfoot',1,'Toy Story'),('2020-12-25 20:00:00','Cineplex Crowfoot',2,'Toy Story');
/*!40000 ALTER TABLE `showtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre` (
  `Name` varchar(30) NOT NULL,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES ('Cineplex Crowfoot','91 Crowfoot Terrace NW'),('Cineplex Eau Claire','Eau Claire Market, 200 Barclay Parade SW'),('Cineplex Sunridge','2555 32 St NE');
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `TicketNumber` int NOT NULL,
  `TheatreName` varchar(30) DEFAULT NULL,
  `MovieName` varchar(30) DEFAULT NULL,
  `ShowTime` datetime DEFAULT NULL,
  `RoomNumber` int DEFAULT NULL,
  `SeatRow` varchar(1) DEFAULT NULL,
  `SeatCol` int DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TicketNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (10000,'Cineplex Crowfoot','Cars','2020-12-25 17:00:00',1,'A',1,'Sold'),(10001,'Cineplex Crowfoot','Cars','2020-12-25 17:00:00',1,'B',2,'Sold'),(10002,'Cineplex Crowfoot','Cars','2020-12-25 17:00:00',1,'C',3,'Sold'),(10003,'Cineplex Crowfoot','Cars','2020-12-25 17:00:00',1,'A',3,'Sold'),(10004,'Cineplex Crowfoot','Cars','2020-12-25 17:00:00',1,'B',1,'Cancelled'),(10005,'Cineplex Crowfoot','Cars','2020-12-25 18:00:00',3,'A',1,'Sold'),(10006,'Cineplex Crowfoot','Cars','2020-12-25 18:00:00',3,'C',2,'Sold'),(10007,'Cineplex Crowfoot','Cars','2020-12-25 18:00:00',3,'A',2,'Sold'),(10008,'Cineplex Crowfoot','Cars','2020-12-25 18:00:00',3,'A',3,'Sold'),(10009,'Cineplex Crowfoot','Cars','2020-12-25 19:00:00',2,'B',2,'Sold'),(10010,'Cineplex Crowfoot','Finding Nemo','2020-12-25 19:00:00',1,'A',1,'Sold'),(10011,'Cineplex Crowfoot','Finding Nemo','2020-12-25 19:00:00',1,'B',1,'Sold'),(10012,'Cineplex Crowfoot','Finding Nemo','2020-12-25 19:00:00',1,'C',1,'Sold'),(10013,'Cineplex Crowfoot','Finding Nemo','2020-12-25 20:00:00',3,'A',3,'Sold'),(10014,'Cineplex Crowfoot','Finding Nemo','2020-12-25 20:00:00',3,'C',1,'Sold'),(10015,'Cineplex Crowfoot','Soul','2020-12-25 18:00:00',2,'B',3,'Sold'),(10016,'Cineplex Crowfoot','Soul','2020-12-25 19:00:00',3,'C',2,'Sold'),(10017,'Cineplex Crowfoot','The Incredibles','2020-12-25 17:00:00',2,'A',1,'Sold'),(10018,'Cineplex Crowfoot','The Incredibles','2020-12-25 17:00:00',2,'B',2,'Sold'),(10019,'Cineplex Crowfoot','The Incredibles','2020-12-25 17:00:00',2,'B',3,'Sold'),(10020,'Cineplex Crowfoot','The Incredibles','2020-12-25 20:00:00',1,'A',1,'Sold'),(10021,'Cineplex Crowfoot','The Incredibles','2020-12-25 20:00:00',1,'A',3,'Sold'),(10022,'Cineplex Crowfoot','The Incredibles','2020-12-25 20:00:00',1,'C',1,'Sold'),(10023,'Cineplex Crowfoot','The Incredibles','2020-12-25 20:00:00',1,'C',3,'Sold'),(10024,'Cineplex Crowfoot','Toy Story','2020-12-25 17:00:00',3,'A',1,'Sold'),(10025,'Cineplex Crowfoot','Toy Story','2020-12-25 18:00:00',1,'B',2,'Sold'),(10026,'Cineplex Crowfoot','Toy Story','2020-12-25 20:00:00',2,'C',3,'Sold');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `VoucherNumber` int NOT NULL,
  `Amount` double DEFAULT NULL,
  `IssuedDate` date DEFAULT NULL,
  `ExpDate` date DEFAULT NULL,
  `ClaimedStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`VoucherNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (5000,15,'2020-11-29','2021-11-29','Unclaimed'),(5001,15,'2020-11-29','2021-11-29','Unclaimed'),(5002,16,'2020-11-28','2021-11-28','Unclaimed'),(5003,17,'2020-11-27','2021-11-27','Unclaimed'),(5004,18,'2020-11-26','2021-11-26','Unclaimed'),(5005,19,'2020-11-25','2021-11-25','Unclaimed');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-01 15:52:32
