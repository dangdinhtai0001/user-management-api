-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: user_management
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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','Wrong user credentials',400),(2,'AUTH_002','Your account has been locked',401),(3,'AUTH_003','Your account has expired',401),(4,'AUTH_004','Access denied, You do not have permission to access this feature.',403),(5,'DB_001','There was an error saving data to the database',500),(6,'AUTH_005','Invalid JWT refresh token',400),(7,'COM_001','Bad request',400),(8,'COM_002','Internal error',500),(9,'COM_003','Not found',404),(10,'COM_004','Method not allow',405),(11,'COM_005','Not acceptable',406);
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
-- Dumping data for table `fw_resource_action`
--

LOCK TABLES `fw_resource_action` WRITE;
/*!40000 ALTER TABLE `fw_resource_action` DISABLE KEYS */;
INSERT INTO `fw_resource_action` VALUES (1,'ping','com.phoenix.base.service.imp.CommonServiceImpl','CommonService','ping','common','GET',1,'Ping service'),(2,'create','com.phoenix.base.service.imp.UserServiceImpl','FW_USER_SERVICES','create','user','POST',1,'User service'),(3,'find','com.phoenix.base.service.imp.UserServiceImpl','FW_USER_SERVICES','find','user','POST',1,'User service'),(4,'setUserRepository','com.phoenix.base.service.imp.UserServiceImpl','FW_USER_SERVICES','setUserRepository','user','POST',1,'User service'),(5,'setPasswordEncoder','com.phoenix.base.service.imp.UserServiceImpl','FW_USER_SERVICES','setPasswordEncoder','user','POST',1,'User service');
/*!40000 ALTER TABLE `fw_resource_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (6,'raw','123456',NULL,NULL,NULL,'c0854a0a-ced8-4d71-957c-b9b532b09e1a',1,'test','NONE','2021-10-13 21:23:28','NONE','2021-10-15 00:19:47'),(10,'bcrypt','$2a$10$AZim8vSCAnSfOy.km4aP/OKqvSTmTN.G92UovMkMriNZy42DRlFAa',NULL,NULL,NULL,'80417524-b1fd-4347-97ac-081b702935d3',1,'user_0','NONE','2021-10-14 14:36:16','NONE','2021-10-15 00:19:44'),(11,'bcrypt','$2a$10$4DeKDYX3q10SBNHQRvgK6usXHYGFmFeihSVBY9DpB7.22qIVBW1XO',NULL,NULL,NULL,NULL,NULL,'user_1','NONE','2021-10-15 00:20:26','NONE','2021-10-15 00:20:26');
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
-- Dumping data for table `fw_user_group_resource_action_mapping`
--

LOCK TABLES `fw_user_group_resource_action_mapping` WRITE;
/*!40000 ALTER TABLE `fw_user_group_resource_action_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `fw_user_group_resource_action_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_status`
--

LOCK TABLES `fw_user_status` WRITE;
/*!40000 ALTER TABLE `fw_user_status` DISABLE KEYS */;
INSERT INTO `fw_user_status` VALUES (1,'T√†i kho·∫£n ƒë√£ ƒë∆∞·ª£c k√≠ch ho·∫°t','ENABLED'),(2,'T√†i kho·∫£n ƒë√£ b·ªã kh√≥a','LOCKED'),(3,'T√†i kho·∫£n ƒë√£ h·∫øt h·∫°n','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('a1712640-4626-496a-a13f-9f70382eaa2d','3b987e75-8c85-4730-a9d4-44a9ff9e1ae8',1634227848350,1634232026447,1800,1634233826447,'test');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('a1712640-4626-496a-a13f-9f70382eaa2d','SPRING_SECURITY_CONTEXT',_binary '¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0&\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0&\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0&\0L\0rolet\0Ljava/lang/String;xpt\0nullxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0&\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$3b987e75-8c85-4730-a9d4-44a9ff9e1ae8t\0{raw}123456t\0test');
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

--
-- Dumping events for database 'user_management'
--

--
-- Dumping routines for database 'user_management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-15  0:22:13
