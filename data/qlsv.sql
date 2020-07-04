CREATE DATABASE  IF NOT EXISTS `qlsv` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlsv`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: qlsv
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem` (
  `diemGK` double unsigned zerofill DEFAULT NULL,
  `diemCK` double unsigned zerofill DEFAULT NULL,
  `diemKhac` double unsigned zerofill DEFAULT NULL,
  `diemTong` double unsigned zerofill DEFAULT NULL,
  `maLopHoc` varchar(21) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maLopHoc`) USING BTREE,
  CONSTRAINT `diem_lopHoc` FOREIGN KEY (`maLopHoc`) REFERENCES `lophoc` (`maLopHoc`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giaovu`
--

DROP TABLE IF EXISTS `giaovu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giaovu` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giaovu`
--

LOCK TABLES `giaovu` WRITE;
/*!40000 ALTER TABLE `giaovu` DISABLE KEYS */;
INSERT INTO `giaovu` VALUES ('giaovu','giaovu');
/*!40000 ALTER TABLE `giaovu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `maLop` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maLop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lophoc`
--

DROP TABLE IF EXISTS `lophoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lophoc` (
  `maLopHoc` varchar(21) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mssv` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `maTkb` varchar(21) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maLopHoc`) USING BTREE,
  KEY `lopHoc_sv` (`mssv`),
  KEY `lopHoc_tkb` (`maTkb`),
  CONSTRAINT `lopHoc_sv` FOREIGN KEY (`mssv`) REFERENCES `sinhvien` (`mssv`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lopHoc_tkb` FOREIGN KEY (`maTkb`) REFERENCES `tkb` (`maTkb`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lophoc`
--

LOCK TABLES `lophoc` WRITE;
/*!40000 ALTER TABLE `lophoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `lophoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon`
--

DROP TABLE IF EXISTS `mon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon` (
  `maMon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenMon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phong` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maMon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon`
--

LOCK TABLES `mon` WRITE;
/*!40000 ALTER TABLE `mon` DISABLE KEYS */;
/*!40000 ALTER TABLE `mon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien` (
  `mssv` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hoTen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gioiTinh` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `cmnd` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `maLop` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`mssv`) USING BTREE,
  UNIQUE KEY `hoTen_UNIQUE` (`hoTen`),
  UNIQUE KEY `cmnd_UNIQUE` (`cmnd`),
  KEY `sv_lop` (`maLop`),
  CONSTRAINT `sv_lop` FOREIGN KEY (`maLop`) REFERENCES `lop` (`maLop`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb`
--

DROP TABLE IF EXISTS `tkb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tkb` (
  `maTkb` varchar(21) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `maLop` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `maMon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maTkb`) USING BTREE,
  KEY `tkb_lop` (`maLop`),
  KEY `tkb_mon` (`maMon`),
  CONSTRAINT `tkb_lop` FOREIGN KEY (`maLop`) REFERENCES `lop` (`maLop`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tkb_mon` FOREIGN KEY (`maMon`) REFERENCES `mon` (`maMon`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb`
--

LOCK TABLES `tkb` WRITE;
/*!40000 ALTER TABLE `tkb` DISABLE KEYS */;
/*!40000 ALTER TABLE `tkb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-04 22:14:24
