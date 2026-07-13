-- MySQL dump 10.13  Distrib 8.4.10, for Linux (x86_64)
--
-- Host: localhost    Database: require4testing
-- ------------------------------------------------------
-- Server version	8.4.10-0ubuntu0.26.04.1

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
-- Current Database: `require4testing`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `require4testing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `require4testing`;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdAt` datetime(6) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `priority` varchar(20) NOT NULL,
  `status` varchar(30) NOT NULL,
  `title` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
INSERT INTO `requirements` VALUES (2,'2026-07-05 18:59:26.639000','Der Nutzer soll Produkte in einen Warenkorb legen, die Menge ändern und Artikel wieder entfernen können.','MUST','OFFEN','Warenkorb'),(3,'2026-07-05 19:01:58.473000','Der Shop soll einen Checkout-Prozess mit Adressdaten, Versandart und Zahlungsart bereitstellen.','MUST','OFFEN','Checkout-Prozess');
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_cases`
--

DROP TABLE IF EXISTS `test_cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_cases` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expectedResult` varchar(1500) NOT NULL,
  `precondition` varchar(1200) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `steps` varchar(2500) NOT NULL,
  `title` varchar(120) NOT NULL,
  `requirement_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbex52v5e8qob9iytsk9gjq66c` (`requirement_id`),
  CONSTRAINT `FKbex52v5e8qob9iytsk9gjq66c` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_cases`
--

LOCK TABLES `test_cases` WRITE;
/*!40000 ALTER TABLE `test_cases` DISABLE KEYS */;
INSERT INTO `test_cases` VALUES (2,'Produkt erscheint im Warenkorb mit Menge 1, korrekter Preis, Zwischensumme wird angezeigt.','Nutzer befindet sich auf der Produktdetailseite, Warenkorb ist leer.','ENTWURF','„In den Warenkorb“-Button klicken.','Produkt in leeren Warenkorb legen',2),(3,'Bestellung wird erfolgreich abgeschlossen, Bestätigungsseite und Bestellbestätigungs‑Mail werden angezeigt; alle gewählten Daten (Adresse, Versand, Zahlung) sind korrekt gespeichert.','Warenkorb enthält mindestens ein Produkt.','BEREIT','1. Rechnungs‑ und Lieferadresse vollständig und korrekt eingeben. \r\n2. zulässige Versandart wählen (z.B. Standardversand). \r\n3. zulässige Zahlungsart wählen (z.B. Kreditkarte oder PayPal). \r\n4. Bestellung prüfen und bestätigen.\r\n','Vollständiger Checkout mit gültiger Adresse, Versandart und Zahlungsart',3);
/*!40000 ALTER TABLE `test_cases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_executions`
--

DROP TABLE IF EXISTS `test_executions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_executions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(1500) DEFAULT NULL,
  `executedAt` datetime(6) DEFAULT NULL,
  `resultStatus` varchar(20) NOT NULL,
  `test_case_id` bigint NOT NULL,
  `test_run_id` bigint NOT NULL,
  `tester_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3w984i7v0vvyood140756ir64` (`test_case_id`),
  KEY `FK11e70o5u2o34hyeeq8jjxyu4d` (`test_run_id`),
  KEY `FKiudst8p4c3y6eur3csbq90geq` (`tester_id`),
  CONSTRAINT `FK11e70o5u2o34hyeeq8jjxyu4d` FOREIGN KEY (`test_run_id`) REFERENCES `test_runs` (`id`),
  CONSTRAINT `FK3w984i7v0vvyood140756ir64` FOREIGN KEY (`test_case_id`) REFERENCES `test_cases` (`id`),
  CONSTRAINT `FKiudst8p4c3y6eur3csbq90geq` FOREIGN KEY (`tester_id`) REFERENCES `testers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_executions`
--

LOCK TABLES `test_executions` WRITE;
/*!40000 ALTER TABLE `test_executions` DISABLE KEYS */;
INSERT INTO `test_executions` VALUES (3,NULL,NULL,'OFFEN',3,3,3),(4,NULL,NULL,'OFFEN',2,3,5);
/*!40000 ALTER TABLE `test_executions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_runs`
--

DROP TABLE IF EXISTS `test_runs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_runs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `plannedDate` date NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_runs`
--

LOCK TABLES `test_runs` WRITE;
/*!40000 ALTER TABLE `test_runs` DISABLE KEYS */;
INSERT INTO `test_runs` VALUES (3,'TL-Checkout-003','2026-07-15','GEPLANT');
/*!40000 ALTER TABLE `test_runs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testers`
--

DROP TABLE IF EXISTS `testers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(120) NOT NULL,
  `firstName` varchar(60) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8yujgbxya6j60mmoiynrx9yjp` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testers`
--

LOCK TABLES `testers` WRITE;
/*!40000 ALTER TABLE `testers` DISABLE KEYS */;
INSERT INTO `testers` VALUES (3,'m.funke@require4test.de','Martin','Funke'),(4,'t.zeppenfeld@require4test.de','Thomas','Zeppenfeld'),(5,'m.halbe@require4test.de','Michaela','Halbe');
/*!40000 ALTER TABLE `testers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'require4testing'
--

--
-- Dumping routines for database 'require4testing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-13 17:55:36
