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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20)          DEFAULT NULL,
  `balance` BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts`
  DISABLE KEYS */;
INSERT INTO `accounts` VALUES (4, 8, 17600), (5, 9, 800), (6, 10, 800);
/*!40000 ALTER TABLE `accounts`
  ENABLE KEYS */;
UNLOCK TABLES;

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
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username`  VARCHAR(64) NOT NULL,
  `series`    VARCHAR(64) NOT NULL,
  `token`     VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins`
  ENABLE KEYS */;
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
INSERT INTO `seats_tickets`
VALUES (2, 39, 7), (3, 39, 7), (1, 40, 8), (2, 40, 8), (3, 40, 8), (1, 41, 8), (2, 41, 8), (3, 41, 8), (4, 41, 8),
  (2, 42, 7), (3, 43, 7);
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
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 44
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (39, 8, 1, 400, 400, 'NO_DISCOUNT', 0), (40, 8, 2, 800, 800, 'NO_DISCOUNT', 0),
  (41, 8, 2, 1000, 1000, 'NO_DISCOUNT', 0), (42, 9, 1, 200, 200, 'NO_DISCOUNT', 0),
  (43, 10, 1, 200, 200, 'NO_DISCOUNT', 0);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(45)         DEFAULT NULL,
  `firstName` VARCHAR(45)         DEFAULT NULL,
  `lastName`  VARCHAR(45)         DEFAULT NULL,
  `password`  VARCHAR(100)        DEFAULT NULL,
  `enabled`   SMALLINT(6)         DEFAULT NULL,
  `birthDate` DATE                DEFAULT NULL,
  `roles`     VARCHAR(45)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
  (9, 'user@epam.com', 'User', 'User', '$2a$10$wcYdEaWe7jWKNnqdfhtMbuNWG2CMoUEZ3PKumO0uEwqe0wJkw2h8q', 1, '2016-03-28',
   'REGISTERED_USER'),
  (10, 'manager@epam.com', 'Manager', 'Manager', '$2a$10$FXr7bqbPYLQ3/qAyc.68ru56j9eWxSV8EqbMhg8M38hSDltlY4iNu', 1,
   '2016-03-28', 'BOOKING_MANAGER,REGISTERED_USER');
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

-- Dump completed on 2016-04-04 16:08:30
