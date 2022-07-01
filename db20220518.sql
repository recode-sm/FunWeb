-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: jspdb7
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `num` int NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `pass` varchar(10) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `readcount` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `file` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (2,'이길동','1234','제목','1',11,'2022-04-22 15:22:50',NULL),(3,'kim','1111','1','1',1,'2022-05-09 15:49:31',NULL),(4,'kim','1111','2','2',0,'2022-05-09 15:49:35',NULL),(5,'kim','1111','3','3',1,'2022-05-09 15:49:37',NULL),(6,'kim','1111','4','4',0,'2022-05-09 15:49:40',NULL),(7,'kim','1111','5','5',0,'2022-05-09 15:49:43',NULL),(8,'kim','1111','6','6',5,'2022-05-09 15:49:47',NULL),(9,'kim','1111','7','7',2,'2022-05-09 15:49:50',NULL),(10,'kim','1111','8','8',0,'2022-05-09 15:49:54',NULL),(11,'kim','1111','9','9',0,'2022-05-09 15:49:56',NULL),(12,'kim','1111','0','0',3,'2022-05-09 15:49:59',NULL),(14,'kim','1111','1','1',3,'2022-05-09 15:50:07',NULL),(15,'kim','1111','2','2',7,'2022-05-09 15:50:10',NULL),(16,'kim','1111','1','1',50,'2022-05-10 15:34:43','귀여운_새끼_동물01.jpg'),(17,'null','1111','',NULL,6,'2022-05-13 10:26:56',NULL),(18,'kim','1111','에디터',NULL,5,'2022-05-13 10:27:59',NULL),(19,'kim','1111','에디터','<p>테스트</p>\r\n',4,'2022-05-13 10:30:29',NULL),(20,'kim','1111','에디터','<p>테스트 진행중</p>\r\n',10,'2022-05-13 10:30:54',NULL),(21,'kim','1111','에디터','<p><span class=\"marker\"><s>에디터</s></span></p>\r\n',10,'2022-05-13 10:31:41',NULL),(22,'kim','1111','에디터','<h1>&nbsp;</h1>\r\n\r\n<pre>\r\nㄴㅇㄹㄴㅇㄹㅇㄴㄹ</pre>\r\n\r\n<p>&nbsp;</p>\r\n',17,'2022-05-13 10:32:28',NULL),(23,'null','1111','1','',5,'2022-05-13 11:57:30','귀여운_새끼_동물01.jpg'),(24,'kim','1111','1234','<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">\r\n	<tbody>\r\n		<tr>\r\n			<td>12323</td>\r\n			<td>123213</td>\r\n		</tr>\r\n		<tr>\r\n			<td>esdsd</td>\r\n			<td>tr</td>\r\n		</tr>\r\n		<tr>\r\n			<td>sdfrfww</td>\r\n			<td>wqe</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>&nbsp;</p>\r\n',18,'2022-05-13 12:30:29',NULL),(25,'kim','1111','에디터','<p><a href=\"http://www.naver.com\">http://www.naver.com</a></p>\r\n\r\n<hr />\r\n<p>asdsadsad</p>\r\n',13,'2022-05-13 17:40:15',NULL),(26,'kim','1111','123','123',1,'2022-05-18 09:08:12','귀여운_새끼_동물01.jpg'),(27,'kim','1111','123','123',4,'2022-05-18 09:09:00','귀여운_새끼_동물071.jpg'),(28,'123','1111','','<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">\r\n	<tbody>\r\n		<tr>\r\n			<td>123</td>\r\n			<td>&nbsp;</td>\r\n		</tr>\r\n		<tr>\r\n			<td>&nbsp;</td>\r\n			<td>&nbsp;</td>\r\n		</tr>\r\n		<tr>\r\n			<td>&nbsp;</td>\r\n			<td>&nbsp;</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>123123123</p>\r\n',1,'2022-05-18 10:35:24',NULL);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(10) NOT NULL,
  `pass` varchar(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `postcode` varchar(20) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('12','12','홍길동','2022-05-18 09:10:11','ddd@n.com','','','','03054','서울 종로구 청와대로 73'),('hong','1234','홍길동','2022-04-25 17:40:41',NULL,NULL,NULL,NULL,NULL,NULL),('kim','1234','김길동','2022-04-27 09:40:14','nom@nom.nom','123-4567-890','123','4567',NULL,NULL),('kim2','1234','김길동2','2022-04-25 17:43:07',NULL,NULL,NULL,NULL,NULL,NULL),('kim3','1234','홍길동','2022-04-27 12:37:16','ddd@n.com','123-4567','123-4567-890','123-',NULL,NULL),('lee','1234','이길동','2022-04-25 16:31:21',NULL,NULL,NULL,NULL,NULL,NULL),('qwe','qwe','qwe','2022-05-16 14:23:28','qwe@qwe','','','',NULL,NULL),('seung-mo',NULL,NULL,'2022-05-17 15:45:48',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `num` int NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'홍길동'),(2,'이길동'),(3,'김길동');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-18 10:53:48
