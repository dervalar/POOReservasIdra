CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(10) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `capacidad` int NOT NULL,
  `precio_base` decimal(10,2) NOT NULL,
  `estado` varchar(20) NOT NULL DEFAULT 'DISPONIBLE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`),
  CONSTRAINT `habitacion_chk_1` CHECK ((`capacidad` > 0)),
  CONSTRAINT `habitacion_chk_2` CHECK ((`precio_base` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'101','simple',1,25000.00,'DISPONIBLE'),(2,'102','doble',2,38000.00,'DISPONIBLE'),(7,'666','DOBLE',6,666.00,'DISPONIBLE'),(8,'999','SIMPLE',2,963852.00,'DISPONIBLE'),(9,'852','DOBLE',66,666222.00,'CLAUSURADA'),(10,'103','DOBLE',2,123123.00,'OCUPADA');
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'12345678','Juan Pérez','juan@test.com','1234-5678'),(3,'46745985','Lizzandro Van Frende','vanpenelizzza@yahoo.ar',''),(4,'74852963','Ricardo','',''),(5,'43741777','Mestre','matigaystre@yahooo.net','2235185112'),(6,'43741789','Charo de Don Pedro','charoddp@gmail.com','2235185112');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `habitacion_id` int NOT NULL,
  `persona_id` int NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `monto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `estado` varchar(20) NOT NULL DEFAULT 'PENDIENTE',
  PRIMARY KEY (`id`),
  KEY `fk_reserva_hab` (`habitacion_id`),
  KEY `fk_reserva_per` (`persona_id`),
  CONSTRAINT `fk_reserva_hab` FOREIGN KEY (`habitacion_id`) REFERENCES `habitacion` (`id`),
  CONSTRAINT `fk_reserva_per` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `chk_fechas` CHECK ((`check_out` > `check_in`))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (2,7,4,'2025-10-13','2025-10-30',11322.00,'CANCELADA'),(3,8,1,'2025-10-30','2025-10-31',963852.00,'FINALIZADA'),(4,9,5,'2025-10-23','2025-10-31',5329776.00,'FINALIZADA'),(5,10,6,'2025-11-02','2025-11-05',369369.00,'EN CURSO');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-03 19:15:10
