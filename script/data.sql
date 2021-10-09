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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','Wrong user credentials',400),(2,'AUTH_002','Your account has been locked',401),(3,'AUTH_003','Your account has expired',401),(4,'AUTH_004','Access denied, You do not have permission to access this feature.',403),(5,'DB_001','There was an error saving data to the database',500),(6,'AUTH_005','Invalid JWT refresh token',400),(7,'COM_001','Bad request',400),(8,'COM_002','Internal error',500);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_parameter`
--

LOCK TABLES `fw_parameter` WRITE;
/*!40000 ALTER TABLE `fw_parameter` DISABLE KEYS */;
INSERT INTO `fw_parameter` VALUES ('PUBLIC_URLS_MATCHER','/**/login'),('PUBLIC_URLS_MATCHER','/**/ping'),('PUBLIC_URLS_MATCHER','/**/test/**'),('PUBLIC_URLS_MATCHER','/auth/refresh');
/*!40000 ALTER TABLE `fw_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource_action`
--

LOCK TABLES `fw_resource_action` WRITE;
/*!40000 ALTER TABLE `fw_resource_action` DISABLE KEYS */;
INSERT INTO `fw_resource_action` VALUES (1,'ping','com.phoenix.base.service.imp.CommonServiceImpl','CommonService','ping','common','Ping service');
/*!40000 ALTER TABLE `fw_resource_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `fw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group`
--

LOCK TABLES `fw_user_group` WRITE;
/*!40000 ALTER TABLE `fw_user_group` DISABLE KEYS */;
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
INSERT INTO `fw_user_status` VALUES (1,'Tài khoản đã được kích hoạt','ENABLED'),(2,'Tài khoản đã bị khóa','LOCKED'),(3,'Tài khoản đã hết hạn','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('aa9cbb32-8a03-45fb-b1da-f5acee75c81b','cd868941-ac0d-4d7e-b00d-e583cae82fc7',1633769585901,1633769650549,1800,1633771450549,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('aa9cbb32-8a03-45fb-b1da-f5acee75c81b','SPRING_SECURITY_SAVED_REQUEST',_binary '�\�\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HD�6d�\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0�t\0/api/v0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\09org.springframework.security.web.savedrequest.SavedCookie@+����f\0I\0maxAgeZ\0secureI\0versionL\0commentq\0~\0L\0domainq\0~\0L\0nameq\0~\0L\0pathq\0~\0L\0valueq\0~\0xp����\0\0\0\0\0ppt\0SESSIONpt\00Y2Q4Njg5NDEtYWMwZC00ZDdlLWIwMGQtZTU4M2NhZTgyZmM3xsr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\�\�\0\0xpw\0\0\0t\0acceptsq\0~\0\0\0\0w\0\0\0t\0*/*xt\0accept-encodingsq\0~\0\0\0\0w\0\0\0t\0gzip, deflate, brxt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0hostsq\0~\0\0\0\0w\0\0\0t\0127.0.0.1:8090xt\0\rpostman-tokensq\0~\0\0\0\0w\0\0\0t\0$f1eefd64-f373-46ff-a90c-2fc1ec7049f0xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0PostmanRuntime/7.28.4xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~�`�0�\�\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xp����t\0USt\0\0t\0enq\0~\0\'q\0~\0\'xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/api/v0/common/ping2t\0)http://127.0.0.1:8090/api/v0/common/ping2t\0httpt\0	127.0.0.1t\0\r/common/ping2');
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

-- Dump completed on 2021-10-09 16:01:50
