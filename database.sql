-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: assignment1
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6m6c3mscbn3eohv8sslxlp12c` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
INSERT INTO `agency` VALUES ('6b5d9098-a7fd-4484-b421-5feb8d79a09f','UTCN');
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `user_id` varchar(36) NOT NULL,
  `package_id` varchar(36) NOT NULL,
  KEY `FKk6at2rbmpwaiqmb7yf98h7un2` (`package_id`),
  KEY `FK65bh1tn1y443fxcah5u36e8fy` (`user_id`),
  CONSTRAINT `FK65bh1tn1y443fxcah5u36e8fy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKk6at2rbmpwaiqmb7yf98h7un2` FOREIGN KEY (`package_id`) REFERENCES `package` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination` (
  `id` varchar(36) NOT NULL,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kw349sqcyo1k39xa0wn3k3q2r` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES ('ae68ddd5-6385-4c7f-b7cc-4a91690e1bfd','Pula (din Botswana) 2');
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package` (
  `id` varchar(36) NOT NULL,
  `details` varchar(2048) DEFAULT NULL,
  `end_date` date NOT NULL,
  `name` varchar(256) NOT NULL,
  `no_of_spots` int NOT NULL,
  `price` int NOT NULL,
  `start_date` date NOT NULL,
  `agency_id` varchar(36) NOT NULL,
  `destination_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmqxwld0cpjbvjo647o5u5r7s2` (`agency_id`),
  KEY `FKtq1gn2h0fe7r8ddc34f6tlit1` (`destination_id`),
  CONSTRAINT `FKmqxwld0cpjbvjo647o5u5r7s2` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`),
  CONSTRAINT `FKtq1gn2h0fe7r8ddc34f6tlit1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES ('35664062-7672-4930-a555-08c64aed7201','dormi de rupi','2021-05-04','Neptun 3 nopti la hamac',10,100,'2021-05-01','6b5d9098-a7fd-4484-b421-5feb8d79a09f','ae68ddd5-6385-4c7f-b7cc-4a91690e1bfd'),('70f90828-3161-404b-9bf5-d29aaf8c8304','dormi de rupi','2021-05-04','Vama 3 nopti la hamac',10,100,'2021-05-01','6b5d9098-a7fd-4484-b421-5feb8d79a09f','ae68ddd5-6385-4c7f-b7cc-4a91690e1bfd'),('9a5bb38f-f352-43c1-a05c-0c2d43f879ec','dormi de rupi','2021-05-04','Eforie Sud 3 nopti la hamac',10,100,'2021-05-01','6b5d9098-a7fd-4484-b421-5feb8d79a09f','ae68ddd5-6385-4c7f-b7cc-4a91690e1bfd');
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `password_hash` varchar(64) NOT NULL,
  `username` varchar(256) NOT NULL,
  `agency_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FK3x269n17oftkff7pg3uxj0niy` (`agency_id`),
  CONSTRAINT `FK3x269n17oftkff7pg3uxj0niy` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

-- Dump completed on 2022-03-21  0:46:24
