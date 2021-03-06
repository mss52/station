-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: station_schema
-- ------------------------------------------------------
-- Server version	5.7.32

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `added_by_user` bigint(20) DEFAULT NULL,
  `allowed_after_day_count` bigint(20) DEFAULT NULL,
  `car_owner_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `plate_code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `plate_number` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_filled_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtnu76t46fqchvguy85fwaa2wn` (`last_filled_id`),
  CONSTRAINT `FKtnu76t46fqchvguy85fwaa2wn` FOREIGN KEY (`last_filled_id`) REFERENCES `car_filling_history` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_code`
--

DROP TABLE IF EXISTS `car_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allowed_after_day_count` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_code`
--

LOCK TABLES `car_code` WRITE;
/*!40000 ALTER TABLE `car_code` DISABLE KEYS */;
INSERT INTO `car_code` VALUES (1,3,'A'),(2,3,'B'),(3,3,'Y'),(4,3,'G'),(5,3,'N'),(6,3,'O'),(7,3,'S'),(8,3,'T'),(9,3,'K'),(10,3,'Z'),(11,2,'M'),(12,5,'D'),(13,3,'R'),(14,2,'P'),(15,3,'*');
/*!40000 ALTER TABLE `car_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_filling_history`
--

DROP TABLE IF EXISTS `car_filling_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_filling_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `filling_user_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiaufh5oypl1rrrd36sxvkn4tb` (`filling_user_id`),
  CONSTRAINT `FKiaufh5oypl1rrrd36sxvkn4tb` FOREIGN KEY (`filling_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_filling_history`
--

LOCK TABLES `car_filling_history` WRITE;
/*!40000 ALTER TABLE `car_filling_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `car_filling_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deactivate_at` datetime DEFAULT NULL,
  `key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `app_version` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `brand` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_deactivation` datetime DEFAULT NULL,
  `fcm_token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `model` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `operating_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `operating_system` int(11) DEFAULT NULL,
  `os_version` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `uid` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `language` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localization`
--

DROP TABLE IF EXISTS `localization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `language` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localization`
--

LOCK TABLES `localization` WRITE;
/*!40000 ALTER TABLE `localization` DISABLE KEYS */;
INSERT INTO `localization` VALUES (1,'invalid.plate.number','en','Invalid Plate Number.'),(2,'invalid.plate.code','en','Invalid Plate Code.'),(3,'car.already.exsts','en','Car Already Exists.'),(4,'invalid.filling.amount','en','Invalid Filling Amount.'),(5,'invalid.car','en','Invalid Car.'),(6,'invalid.car.id','en','Invalid Car.'),(7,'filling.not.allowed','en','Car Not Allowed For Filling.'),(8,'car.not.found','en','Car Not found.'),(9,'session.not_valid','en','Session Expired. Please Login Again.'),(10,'session.expired','en','Session Expired. Please Login Again.'),(11,'device.not_active','en','Session Expired. Please Login Again.'),(12,'device.identifier_empty','en','Session Expired. Please Login Again.'),(13,'auth.password.empty','en','Invalid Username Or Password.'),(14,'auth.username_not_found','en','Invalid Username Or Password.'),(15,'auth.wrong_password','en','Invalid Username Or Password.'),(16,'auth.not_valid_login','en','Invalid Username Or Password.'),(17,'auth.user_not_active','en','User Is Inactive.'),(18,'500','en','Something Has Occurred Please contact Support.'),(19,'invalid.location','en','Invalid Location.'),(20,'invalid.location.description','en','Invalid Location Description.'),(21,'invalid.station.name','en','Invalid Station Name.'),(22,'station.already.exists','en','Station Already Exists.'),(23,'invalid.phone.number','en','Invalid Phone Number.'),(24,'invalid.station','en','Invalid Station.'),(25,'station.not.exists','en','Station Not Found.'),(26,'invalid.user.name','en','Invalid Name.'),(27,'invalid.username','en','Invalid Username.'),(28,'invalid.password','en','Invalid Password.'),(29,'username.already.exists','en','Username Already exists.'),(30,'invalid.plate.number','en','?????? ???????????? ?????? ????????.'),(31,'invalid.plate.code','en','?????? ???????????? ?????? ????????.'),(32,'car.already.exsts','en','?????????????? ????????????.'),(33,'invalid.filling.amount','en','???????? ?????????????? ?????? ????????.'),(34,'invalid.car','en','?????????? ?????? ??????????.'),(35,'invalid.car.id','en','?????????? ?????? ??????????.'),(36,'filling.not.allowed','en','?????? ?????????? ???????????? ??????????????.'),(37,'car.not.found','en','???? ?????? ???????????? ?????? ??????????????.'),(38,'session.not_valid','en','???????????? ???????????? ?????? ???????????? ???? ????????.'),(39,'session.expired','en','???????????? ???????????? ?????? ???????????? ???? ????????.'),(40,'device.not_active','en','???????????? ???????????? ?????? ???????????? ???? ????????.'),(41,'device.identifier_empty','en','???????????? ???????????? ?????? ???????????? ???? ????????.'),(42,'auth.password.empty','en','?????? ???? ?????? ???????????????? ???? ???????? ????????.'),(43,'auth.username_not_found','en','?????? ???? ?????? ???????????????? ???? ???????? ????????.'),(44,'auth.wrong_password','en','?????? ???? ?????? ???????????????? ???? ???????? ????????.'),(45,'auth.not_valid_login','en','?????? ???? ?????? ???????????????? ???? ???????? ????????.'),(46,'auth.user_not_active','en','???????????????? ?????? ??????.'),(47,'500','en','?????? ?????? ???????????? ?????????????? ????????????.'),(48,'invalid.location','en','???????? ?????? ????????.'),(49,'invalid.location.description','en','?????? ???????????? ?????? ????????.'),(50,'invalid.station.name','en','?????? ???????????? ?????? ????????.'),(51,'station.already.exists','en','???????????? ????????????.'),(52,'invalid.phone.number','en','?????? ???????????? ?????? ????????.'),(53,'invalid.station','en','???????? ?????? ????????.'),(54,'station.not.exists','en','???????????? ?????? ????????????.'),(55,'invalid.user.name','en','?????? ?????? ????????.'),(56,'invalid.username','en','?????? ???????????? ?????? ????????.'),(57,'invalid.password','en','?????? ???????? ????????.'),(58,'username.already.exists','en','?????? ???????????????? ?????????? ????????????.');
/*!40000 ALTER TABLE `localization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_verification`
--

DROP TABLE IF EXISTS `login_verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_verification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `expire_at` datetime DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_verification`
--

LOCK TABLES `login_verification` WRITE;
/*!40000 ALTER TABLE `login_verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_expired` datetime DEFAULT NULL,
  `date_last_access` datetime DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa9qm1mhb31awsnsv89ypp2b0t` (`device_id`),
  KEY `FK50m0ngjpknruaqj4rriqa9cwf` (`user_id`),
  CONSTRAINT `FK50m0ngjpknruaqj4rriqa9cwf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa9qm1mhb31awsnsv89ypp2b0t` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  `is_close` bit(1) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqlnvqs4wbjtf02m0wghvreewo` (`location_id`),
  KEY `FKn2v51hpc55owjbeuoo48eq7k2` (`status`),
  CONSTRAINT `FKn2v51hpc55owjbeuoo48eq7k2` FOREIGN KEY (`status`) REFERENCES `status` (`id`),
  CONSTRAINT `FKqlnvqs4wbjtf02m0wghvreewo` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'ACTIVE'),(2,'PENDING APPROVAL'),(3,'INACTIVE'),(4,'SUSPEDED');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `station_id` bigint(20) DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FK3epii5sxhw0lqhb92yj286y2d` (`station_id`),
  KEY `FK8tmbsnntxtkc6kg0xjai9tv9w` (`status`),
  CONSTRAINT `FK3epii5sxhw0lqhb92yj286y2d` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `FK8tmbsnntxtkc6kg0xjai9tv9w` FOREIGN KEY (`status`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2021-08-01 19:30:00
