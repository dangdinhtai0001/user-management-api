-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: user_management
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_exception`
--

LOCK TABLES `fw_exception` WRITE;
/*!40000 ALTER TABLE `fw_exception` DISABLE KEYS */;
INSERT INTO `fw_exception` VALUES (1,'001001','Wrong user credentials',400),(2,'001002','Your account has been locked',401),(3,'001003','Your account has expired',401),(4,'001004','Access denied, You do not have permission to access this feature.',403),(5,'000006','There was an error saving data to the database',500),(6,'001005','Invalid JWT refresh token',400),(7,'000001','Bad request',400),(8,'000002','Internal error',500),(9,'000003','Not found',404),(10,'000004','Method not allow',405),(11,'000005','Not acceptable',406),(12,'002001','Account name has been used',409);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_parameter`
--

LOCK TABLES `fw_parameter` WRITE;
/*!40000 ALTER TABLE `fw_parameter` DISABLE KEYS */;
INSERT INTO `fw_parameter` VALUES ('DEFAULT_PASSWORD_SALT','ncskbskasufaoifa'),('PUBLIC_URLS_MATCHER','/**/login'),('PUBLIC_URLS_MATCHER','/**/ping'),('PUBLIC_URLS_MATCHER','/**/test/**'),('PUBLIC_URLS_MATCHER','/auth/refresh');
/*!40000 ALTER TABLE `fw_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_service_metadata`
--

LOCK TABLES `fw_service_metadata` WRITE;
/*!40000 ALTER TABLE `fw_service_metadata` DISABLE KEYS */;
INSERT INTO `fw_service_metadata` VALUES (1,'ping','com.phoenix.base.service.imp.CommonServiceImpl','CommonService','ping','common','PUT',1,'abc xyz'),(2,'create','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','create','user','POST',1,'Create user'),(3,'find','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','find','user','POST',1,'Find user');
/*!40000 ALTER TABLE `fw_service_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (6,'test','123456',NULL,NULL,NULL,'228185ee-23d2-4e9a-a8ef-b26d67b6fc76',1,'raw','NONE','2021-10-13 21:23:28','NONE','2021-11-20 13:34:45'),(10,'user_0','$2a$10$AZim8vSCAnSfOy.km4aP/OKqvSTmTN.G92UovMkMriNZy42DRlFAa',NULL,NULL,NULL,'3edc0e8c-2f90-475b-a27b-c55afd31521d',1,'bcrypt','NONE','2021-10-14 14:36:16','NONE','2021-12-02 23:23:01'),(11,'user_1','$2a$10$4DeKDYX3q10SBNHQRvgK6usXHYGFmFeihSVBY9DpB7.22qIVBW1XO',NULL,NULL,NULL,NULL,4,'bcrypt','NONE','2021-10-15 00:20:26','NONE','2021-12-02 23:50:24'),(12,'user_2','$2a$10$oC8t.LVYynG0txSPIxZMnuAQXrzs9G5vwgP4Ed9nm5qXHu0nvj1QK',NULL,NULL,NULL,NULL,4,'bcrypt','NONE','2021-10-31 00:28:15','NONE','2021-12-02 23:50:24'),(21,'user_3','$2a$10$ilDf9qQ9xmZguj4hvS3Qr.ZvBh8vF2dJ7AyPCurRL3LvR1iAfEily',NULL,NULL,NULL,NULL,4,'bcrypt','NONE','2021-11-20 13:35:11','NONE','2021-12-02 23:50:24'),(22,'user_4','$2a$10$5nqDDFJLuGCywJrUTW08heJlEVJYIZWtHTqpgb66VKaUHqOaUbsMy',NULL,NULL,NULL,NULL,4,'bcrypt','NONE','2021-12-03 00:01:49','NONE','2021-12-03 00:01:49');
/*!40000 ALTER TABLE `fw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group`
--

LOCK TABLES `fw_user_group` WRITE;
/*!40000 ALTER TABLE `fw_user_group` DISABLE KEYS */;
INSERT INTO `fw_user_group` VALUES (1,'administrator');
/*!40000 ALTER TABLE `fw_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group_mapping`
--

LOCK TABLES `fw_user_group_mapping` WRITE;
/*!40000 ALTER TABLE `fw_user_group_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `fw_user_group_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group_service_mapping`
--

LOCK TABLES `fw_user_group_service_mapping` WRITE;
/*!40000 ALTER TABLE `fw_user_group_service_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `fw_user_group_service_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_status`
--

LOCK TABLES `fw_user_status` WRITE;
/*!40000 ALTER TABLE `fw_user_status` DISABLE KEYS */;
INSERT INTO `fw_user_status` VALUES (1,'Tài khoản đã được kích hoạt','ENABLED'),(2,'Tài khoản đã bị khóa','LOCKED'),(3,'Tài khoản đã hết hạn','EXPIRED'),(4,'Tài khoản chưa được kích hoạt','DISABLED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('fce4541f-f18a-4a9d-9909-4441ab596804','d8d8139a-2d3e-423e-9546-bdc596ed15a0',1638462153439,1638464525109,1800,1638466325109,'user_0');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('fce4541f-f18a-4a9d-9909-4441ab596804','SPRING_SECURITY_CONTEXT',_binary '�\�\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0&\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0&\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0&\0L\0rolet\0Ljava/lang/String;xpt\0nullxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0&\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$d8d8139a-2d3e-423e-9546-bdc596ed15a0t\0D{bcrypt}$2a$10$AZim8vSCAnSfOy.km4aP/OKqvSTmTN.G92UovMkMriNZy42DRlFAat\0user_0');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_title`
--

LOCK TABLES `user_title` WRITE;
/*!40000 ALTER TABLE `user_title` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_title` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-03  0:07:08
