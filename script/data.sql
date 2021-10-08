-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: user_management
-- ------------------------------------------------------
-- Server version	8.0.25

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
INSERT INTO `fw_resource_action` VALUES (7,'ping','com.phoenix.base.service.imp.PingServiceImpl',NULL,NULL,NULL,NULL),(8,'findAllCasbinRules','com.phoenix.base.repository.imp.AuthorizationRepositoryImp',NULL,NULL,NULL,NULL),(9,'logout','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(10,'login','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(11,'refreshToken','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(12,'generateToken','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(13,'loadModelFromPath','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(14,'getModelTextFromFilePath','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(15,'clearPolicies','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(16,'loadPolicies','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(17,'enforce','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(18,'loadUserByUsername','com.phoenix.base.service.imp.DefaultUserDetailService',NULL,NULL,NULL,NULL),(19,'ping','com.phoenix.base.service.imp.PingServiceImpl',NULL,NULL,NULL,NULL),(20,'getCurrentSecurityToken','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(21,'saveDataByListClassName','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(22,'getResourceAction','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(23,'findAllCasbinRules','com.phoenix.base.repository.imp.AuthorizationRepositoryImp',NULL,NULL,NULL,NULL),(24,'generateToken','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(25,'refreshToken','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(26,'login','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(27,'logout','com.phoenix.base.service.imp.AuthenticationServiceImpl',NULL,NULL,NULL,NULL),(28,'loadPolicies','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(29,'clearPolicies','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(30,'enforce','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(31,'loadModelFromPath','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(32,'getModelTextFromFilePath','com.phoenix.base.service.imp.AuthorizationServiceImp',NULL,NULL,NULL,NULL),(33,'loadUserByUsername','com.phoenix.base.service.imp.DefaultUserDetailService',NULL,NULL,NULL,NULL),(34,'ping','com.phoenix.base.service.imp.PingServiceImpl',NULL,NULL,NULL,NULL),(35,'saveDataByListClassName','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(36,'getResourceAction','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(37,'getCurrentSecurityToken','com.phoenix.base.service.imp.ResourceActionServiceImp',NULL,NULL,NULL,NULL),(38,'ping','com.phoenix.base.service.imp.PingServiceImpl',NULL,NULL,NULL,NULL),(39,'ping','com.phoenix.base.service.imp.PingServiceImpl',NULL,NULL,NULL,NULL);
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
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
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

-- Dump completed on 2021-10-08 20:35:32
