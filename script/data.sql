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
-- Dumping data for table `fw_resource_action`
--

LOCK TABLES `fw_resource_action` WRITE;
/*!40000 ALTER TABLE `fw_resource_action` DISABLE KEYS */;
INSERT INTO `fw_resource_action` VALUES (1,'ping','com.phoenix.base.service.imp.CommonServiceImpl','CommonService','ping','common','GET',1,'Ping service'),(2,'create','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','create','user','POST',1,'User service'),(3,'find','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','find','user','POST',1,'User service'),(4,'setPasswordEncoder','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','setPasswordEncoder','user','POST',1,'User service'),(5,'setUserRepository','com.phoenix.business.service.imp.UserServiceImpl','FW_USER_SERVICES','setUserRepository','user','POST',1,'User service');
/*!40000 ALTER TABLE `fw_resource_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (6,'test','123456',NULL,NULL,NULL,'251750f4-3a65-46d0-9075-0c92c94108d4',1,'raw','NONE','2021-10-13 21:23:28','NONE','2021-10-31 12:54:51'),(10,'user_0','$2a$10$AZim8vSCAnSfOy.km4aP/OKqvSTmTN.G92UovMkMriNZy42DRlFAa',NULL,NULL,NULL,'80417524-b1fd-4347-97ac-081b702935d3',1,'bcrypt','NONE','2021-10-14 14:36:16','NONE','2021-10-15 00:19:44'),(11,'user_1','$2a$10$4DeKDYX3q10SBNHQRvgK6usXHYGFmFeihSVBY9DpB7.22qIVBW1XO',NULL,NULL,NULL,NULL,NULL,'bcrypt','NONE','2021-10-15 00:20:26','NONE','2021-10-15 00:20:26'),(12,'user_2','$2a$10$oC8t.LVYynG0txSPIxZMnuAQXrzs9G5vwgP4Ed9nm5qXHu0nvj1QK',NULL,NULL,NULL,NULL,NULL,'bcrypt','NONE','2021-10-31 00:28:15','NONE','2021-10-31 00:28:15');
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
INSERT INTO `fw_user_status` VALUES (1,'TÃ i khoáº£n Ä‘Ã£ Ä‘Æ°á»£c kÃ­ch hoáº¡t','ENABLED'),(2,'TÃ i khoáº£n Ä‘Ã£ bá»‹ khÃ³a','LOCKED'),(3,'TÃ i khoáº£n Ä‘Ã£ háº¿t háº¡n','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('79913e77-eb3e-4125-bd5a-1863f247bd61','f4e499e3-17be-4d5a-ad27-f6c2cd2a8c8c',1635659671054,1635662110560,1800,1635663910560,'test');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('79913e77-eb3e-4125-bd5a-1863f247bd61','SPRING_SECURITY_CONTEXT',_binary '¬\í\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0&\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0&\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableListü%1µ\ìŽ\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0€\Ë^÷\0L\0cq\0~\0xpsr\0java.util.ArrayListx\Ò™\Ça\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0&\0L\0rolet\0Ljava/lang/String;xpt\0nullxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0&\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$f4e499e3-17be-4d5a-ad27-f6c2cd2a8c8ct\0{raw}123456t\0test'),('79913e77-eb3e-4125-bd5a-1863f247bd61','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d”\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0št\0/api/v0sr\0java.util.ArrayListx\Ò™\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0	t\0acceptsq\0~\0\0\0\0w\0\0\0t\0*/*xt\0accept-encodingsq\0~\0\0\0\0w\0\0\0t\0gzip, deflate, brxt\0\rauthorizationsq\0~\0\0\0\0w\0\0\0t\0rBearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjM1NjUwNjkxfQ.giKG1YtDPlL7jWuWwbt0PkXHdk697nGNK7rKqVMmyKwxt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0content-lengthsq\0~\0\0\0\0w\0\0\0t\057xt\0content-typesq\0~\0\0\0\0w\0\0\0t\0application/jsonxt\0hostsq\0~\0\0\0\0w\0\0\0t\0127.0.0.1:8090xt\0\rpostman-tokensq\0~\0\0\0\0w\0\0\0t\0$e7787d21-40f5-425d-a3c1-26493f883de9xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0PostmanRuntime/7.28.4xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`œ0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USt\0\0t\0enq\0~\0,q\0~\0,xxt\0POSTsq\0~\0pw\0\0\0\0xppt\0\r/api/v0/errort\0\"http://127.0.0.1:8090/api/v0/errort\0httpt\0	127.0.0.1t\0/error');
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

-- Dump completed on 2021-11-01  0:33:29
