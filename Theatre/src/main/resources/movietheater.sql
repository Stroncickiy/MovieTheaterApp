CREATE DATABASE  IF NOT EXISTS `movietheater` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `movietheater`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: movietheater
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `auditorium`
--

DROP TABLE IF EXISTS `auditorium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditorium` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditorium`
--

LOCK TABLES `auditorium` WRITE;
/*!40000 ALTER TABLE `auditorium` DISABLE KEYS */;
INSERT INTO `auditorium` VALUES (7,'Black Hall'),(8,'White Hall');
/*!40000 ALTER TABLE `auditorium` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `auditoriumId` int(11) DEFAULT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end` timestamp NULL DEFAULT NULL,
  `basePrice` int(11) DEFAULT NULL,
  `rating` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Star Wars',7,'2016-03-20 11:55:01','2016-03-15 22:00:00',100,'MIDDLE'),(2,'X-Men',8,'2016-03-20 11:55:01','2016-03-19 00:00:00',200,'HIGHT'),(3,'Deadpool',8,'2016-03-23 22:00:00','2016-03-23 23:00:00',200,'LOW');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seats` (
  `isVip` smallint(6) DEFAULT '0',
  `auditory` bigint(20) NOT NULL,
  `number` bigint(20) NOT NULL,
  PRIMARY KEY (`auditory`,`number`),
  KEY `auditory_idx` (`auditory`),
  CONSTRAINT `auditory` FOREIGN KEY (`auditory`) REFERENCES `auditorium` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,7,1),(1,7,2),(1,7,3),(0,7,4),(0,7,5),(0,7,6),(0,7,7),(0,7,8),(0,7,9),(0,7,10),(1,8,1),(0,8,2),(0,8,3),(0,8,4),(0,8,5);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats_tickets`
--

DROP TABLE IF EXISTS `seats_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seats_tickets` (
  `seat_number` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  `auditory_id` bigint(20) DEFAULT NULL,
  KEY `ticket_id_idx` (`ticket_id`),
  KEY `seat_id_idx` (`seat_number`),
  KEY `seats_idx` (`seat_number`,`auditory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats_tickets`
--

LOCK TABLES `seats_tickets` WRITE;
/*!40000 ALTER TABLE `seats_tickets` DISABLE KEYS */;
INSERT INTO `seats_tickets` VALUES (3,27,8),(4,27,8),(2,28,7),(3,28,7),(4,28,7),(1,29,8),(2,29,8),(3,29,8),(5,30,8),(2,31,8),(3,31,8),(4,31,8),(3,32,8),(4,32,8),(5,32,8),(4,33,8),(5,33,8),(3,34,8),(4,34,8),(5,34,8),(1,35,7),(2,35,7),(3,35,7),(2,36,8),(3,36,8),(4,36,8),(3,37,8),(2,38,7),(3,38,7);
/*!40000 ALTER TABLE `seats_tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL,
  `eventId` bigint(20) DEFAULT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  `realPrice` int(11) DEFAULT NULL,
  `discountStrategy` varchar(45) DEFAULT NULL,
  `dicountAmount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (27,1,3,400,400,'NO_DISCOUNT',0),(28,1,1,500,500,'NO_DISCOUNT',0),(29,1,2,800,800,'NO_DISCOUNT',0),(30,1,2,200,200,'NO_DISCOUNT',0),(31,1,2,600,600,'NO_DISCOUNT',0),(32,1,3,600,600,'NO_DISCOUNT',0),(33,1,2,400,400,'NO_DISCOUNT',0),(34,1,3,600,600,'NO_DISCOUNT',0),(35,1,1,600,600,'NO_DISCOUNT',0),(36,1,2,600,600,'NO_DISCOUNT',0),(37,1,3,100,200,'EVERY_10_TICKET',100),(38,1,1,400,400,'NO_DISCOUNT',0);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` smallint(6) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'stroncickiy@gmail.com','Slavik','Stroncickiy','$2a$10$be7uyWFfvFJCzEv7PG7ICuzjwL5HtL6qusOi5VwwuQmKLT085H1Ku',1,'2016-02-21');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-20 21:52:34
