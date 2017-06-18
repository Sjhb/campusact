-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: campusact
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  `signTime` datetime NOT NULL,
  `endSignTime` datetime NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `detail` text,
  `photo` json DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `sponsor` varchar(200) DEFAULT NULL,
  `stateId` int(11) NOT NULL,
  `engage` json DEFAULT NULL,
  `admin_view` text,
  `summary` text,
  PRIMARY KEY (`id`),
  KEY `organizationId` (`organizationId`),
  KEY `stateId` (`stateId`),
  CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`stateId`) REFERENCES `state` (`state`),
  CONSTRAINT `activity_organization_id_fk` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (131,'花式滑板大赛',100,'2017-05-24 10:05:00','2017-05-25 09:05:00','2017-05-26 09:05:00','2017-05-27 14:05:00','给你一个展示自己滑板技术的机会，这里大牛云集，你敢来吗？','[\"13145.296467437640175.jpg\"]','体育大馆前','无',2000,'[\"201301010101\", \"201301010101\", \"201301010101\", \"201307260115\"]',NULL,NULL),(132,'夏季滑板切磋赛',101,'2017-05-28 10:05:00','2017-05-29 21:05:00','2017-05-30 10:05:00','2017-05-30 17:05:00','我们不争高低，我们只是分享技术。我们只要要追求想要的生活！','[\"13253.304599048021295.jpg\"]','家和食堂门口','无',4000,'[\"201301010101\", \"201301010101\", \"201301010101\", \"201301010101\"]',NULL,NULL),(133,'滑板交流会',101,'2017-03-28 10:05:00','2017-03-29 21:05:00','2017-03-30 10:05:00','2017-03-30 17:05:00','我们不争高低，我们只是分享技术。我们只要要追求想要的生活！','[\"13253.304599048021295.jpg\"]','建行楼1层111','雪碧',4000,'[\"201301010101\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\"]',NULL,NULL),(134,'滑板竞技大赛',101,'2017-05-25 10:05:00','2017-05-26 21:05:00','2017-05-30 10:05:00','2017-05-30 17:05:00','我们不争高低，我们只是分享技术。我们只要要追求想要的生活！','[\"13253.304599048021295.jpg\"]','家和食堂门口','无',3000,'[]',NULL,NULL),(135,'滑板大师：托尼·霍克',101,'2016-11-11 08:05:00','2016-11-12 21:05:00','2016-11-16 13:00:00','2016-11-16 16:00:00','托尼·霍克是滑板界元老级人物，是他将滑板推向全时间，让我们来了解一下他吧！','[\"13253.304599048021295.jpg\"]','养贤府二楼','无',4000,'[\"201301010101\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\"]',NULL,NULL),(136,'春季滑板交流会',101,'2016-04-08 12:00:00','2016-04-09 21:00:00','2016-04-10 18:30:00','2016-04-10 21:00:00','我们不争高低，我们只是分享技术。我们只要要追求想要的生活！','[\"13253.304599048021295.jpg\"]','体育馆大馆门前','',4000,'[\"201301010101\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\", \"1\"]',NULL,NULL),(137,'t',101,'2017-05-04 06:05:00','2017-05-11 10:05:00','2017-05-03 05:05:00','2017-05-11 10:05:00','无','[\"13714.375808915969813.jpg\"]','体育馆','无',3000,'[]',NULL,NULL),(139,'6',101,'2017-05-11 10:05:00','2017-05-17 09:05:00','2017-05-11 10:05:00','2017-05-12 11:05:00','6','[\"13958.289235033962974.jpg\"]','6','6',3000,'[]',NULL,NULL),(140,'乔布斯追忆会',117,'2017-06-03 19:06:00','2017-06-03 23:06:00','2017-06-04 10:06:00','2017-06-04 12:06:00','追忆乔布斯','[\"14025.266261923145883.jpg\", \"14069.39115397537896.jpg\", \"1406.156179799842687.jpg\"]','建行楼B204','无',4000,'[\"201307260114\"]',NULL,NULL),(141,'狼人杀专场',111,'2017-06-04 17:06:00','2017-06-04 23:06:00','2017-06-05 09:06:00','2017-06-05 18:06:00','谁是狼王','[\"14147.76844576941428.jpg\"]','养贤府三楼','无',2000,'[]',NULL,NULL),(142,'谁是卧底',111,'2017-06-06 17:06:00','2017-06-07 11:06:00','2017-06-09 11:06:00','2017-06-09 11:06:00','谁是卧底？','[\"14271.32564679440712.jpg\"]','健行楼一楼','无',2000,'[\"201307260124\"]',NULL,NULL),(143,'t',101,'2017-06-16 19:06:00','2017-06-15 10:06:00','2017-06-08 10:06:00','2017-06-15 10:06:00','t','[\"14320.507832920376767.jpg\", \"14398.95602884918334.jpg\", \"1434.183797121795174.jpg\"]','t','t',5000,'[]',NULL,NULL),(144,'电脑清灰',117,'2017-06-06 09:06:00','2017-06-13 09:06:00','2017-06-14 19:06:00','2017-06-14 22:06:00','免费为同学们清理电脑的灰尘，赶紧抓住机会来吧！','[\"14488.59496576981752.jpg\"]','支干路','无',2000,'[]',NULL,NULL),(145,'夏季篮球赛',112,'2017-06-08 10:06:00','2017-06-09 11:06:00','2017-06-22 10:06:00','2017-06-16 15:06:00','学院篮球赛，欢迎大家来参加！','[\"14562.394141035778425.jpg\", \"14571.67407874491731.jpg\"]','篮球场','无',2000,'[]',NULL,NULL),(146,'第十六届社团文化节',119,'2017-06-08 10:06:00','2017-06-10 07:06:00','2017-06-15 10:06:00','2017-06-16 15:06:00','为进一步丰富广大学生的课余生活，展现优秀社团的风采，浙江工业大学校团委、校社团联盟决定于“第十六届社团文化节”期间举办精品立项活动。','[\"14696.31418390564316.jpg\"]','屏峰校区健行楼报告厅','无',2000,'[]',NULL,NULL),(147,'和山达人秀',119,'2017-06-09 10:06:00','2017-06-12 12:06:00','2017-06-22 18:06:00','2017-06-23 11:06:00','和山达人秀是由浙江科技学院、浙江外国语学院、浙江工业大学和浙江长征职业技术学院四校共青团委员会主办，四校学生社团联合会承办，面向和山地区大学生的一项大型娱乐活动，旨在挖掘大学生的才艺与技能。迄今为止，和山达人秀已经成功举办三届，而本届和山达人秀将于浙江工业大学屏峰校区举行、由浙江工业大学社团联盟承办。','[\"14760.81999726221936.jpg\", \"14779.34561799088014.jpg\"]','浙江工业大学屏峰校区','浙江科技学院、浙江外国语学院、浙江工业大学和浙江长征职业技术学院',2000,'[]',NULL,NULL),(148,'优秀大学生暑期夏令营',120,'2017-06-09 10:06:00','2017-06-10 07:06:00','2017-07-10 00:07:00','2017-06-14 00:06:00','夏令营期间，将举办专业介绍、学术讲座、实验室参观、课题组体验、研究生学长专业领航座谈、参观浙江工业大学、研讨交流等多种形式活动，并组织营员开展活动成果交流与分享。\n查询网址：https://www.sojump.com/resultquery.aspx?activity=14205704','[\"14814.109523126036693.jpg\"]','暑期夏令营','教科学院',2000,'[]',NULL,NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administor`
--

DROP TABLE IF EXISTS `administor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administor` (
  `id` bigint(20) NOT NULL,
  `icon` varchar(25) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL DEFAULT '123456',
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `administor_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administor`
--

LOCK TABLES `administor` WRITE;
/*!40000 ALTER TABLE `administor` DISABLE KEYS */;
INSERT INTO `administor` VALUES (200,'1000000000001.jpg','王汉祥','200',20);
/*!40000 ALTER TABLE `administor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `administor_change_with_login_insert`
AFTER INSERT ON `administor`
FOR EACH ROW
  BEGIN
    INSERT INTO login VALUES (new.id,new.password,new.role);
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `administor_change_with_login_update`
AFTER UPDATE ON `administor`
FOR EACH ROW
  BEGIN
    IF  old.id !=new.id THEN
      UPDATE  login SET  login.id=new.id WHERE login.id=old.id;
    END IF;

    IF old.password !=new.password THEN
    UPDATE login SET login.password=new.password WHERE id=new.id;
    END IF ;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `administor_change_with_login_delete`
AFTER DELETE ON `administor`
FOR EACH ROW
  BEGIN
    DELETE FROM login WHERE login.id=old.id;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `behave`
--

DROP TABLE IF EXISTS `behave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `behave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `behave` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `behave`
--

LOCK TABLES `behave` WRITE;
/*!40000 ALTER TABLE `behave` DISABLE KEYS */;
/*!40000 ALTER TABLE `behave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` bigint(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) DEFAULT NULL,
  KEY `login_role_roleId_fk` (`role`),
  CONSTRAINT `login_role_roleId_fk` FOREIGN KEY (`role`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (200,'200',20),(100,'100',30),(101,'t',30),(110,'123456',30),(111,'t',30),(112,'t',30),(113,'t',30),(114,'123456',30),(115,'t',30),(116,'t',30),(201301010100,'s',10),(201307260115,'s',10),(201307260110,'s',10),(201307260112,'s',10),(201307260111,'s',10),(201307260113,'s',10),(201307260114,'s',10),(201307260116,'s',10),(201307260117,'s',10),(201307260118,'s',10),(201307260119,'s',10),(201307260120,'s',10),(201307260121,'s',10),(201307260122,'s',10),(201307260123,'s',10),(201307260124,'ss',10),(201307260125,'s',10),(201307260126,'s',10),(201307260127,'s',10),(201307260128,'s',10),(201307260129,'s',10),(201307260130,'s',10),(201307260131,'s',10),(201307260132,'s',10),(201307260133,'s',10),(201307260134,'s',10),(201307260135,'s',10),(201307260136,'s',10),(201307260137,'s',10),(201307260138,'s',10),(201307260139,'s',10),(201307260140,'s',10),(117,'123456',30),(201365948,'t',10),(119,'t',30),(120,'t',30),(121,'t',30);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(25) DEFAULT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL DEFAULT '123456',
  `phone` varchar(200) NOT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `detail` text,
  `address` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `document` varchar(20) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '0',
  `resetpass` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `organization_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (100,'300000000001.jpg','教科体育部','100','15868149250','jktybu@163.com','教育技术与科技学院体育部，一群活力四射的青年','家和东苑十幢架空层教科办公室',30,NULL,1,NULL),(101,'101.jpg','滑板社','t','15868149250','hbs@zjut.edu.cn','校园滑板社，极限运动','无',30,'',1,NULL),(110,'110.jpg','5','123456','5',NULL,'5','5',30,'110.jpg',3,2),(111,'111.jpg','动漫社','t','15235461983','dms@163.com','一群热满动漫的青年。','无',30,'111.png',1,NULL),(112,'112.jpg','教育科技与技术学院组织部','t','17763566564','jkzzbu@163.com','教育技术与科技学院体育部，一群活力四射的青年.','家和东苑十幢架空层教科办公室',30,'112.png',1,NULL),(113,'113.jpg','天堂手语社','t','15664464556','sys@163.com','学习手语的社团、很有爱心。','养贤府三楼',30,'113.png',1,NULL),(114,'114.jpg','乐乐吧爱心手工社','123456','14555559824','sgs@163.com','手工社的活动还是比较多的，印象很深的是开学时候、手工社做了200多个纸袋，贴在各个新生寝室的门外以供放置漫天飞的各种传单。虽然简单，但是很贴心。','无',30,'114.png',1,2),(115,'115.jpg','灰姑娘话剧社','t','15547648676','hjs@163.com','很值得加入的社团。每学年末都有一个话剧专场、虽然大家都不是专业、但是很投入、很开心、很感动。学校大大小小的晚会上也能经常看见他们的话剧。让舞台不仅仅只是像循环播放的唱歌。','养贤府三楼',30,'115.png',1,NULL),(116,'116.jpg','星工厂','t','15645955645','xgc@163.com','教学舞蹈为主的社团、各种跳舞的人才、而且去年用社团的名义办了场舞蹈专场、华丽丽的H翻舞台。','养贤府三楼',30,'116.png',1,NULL),(117,'117.jpg','青团社','123456','12546345968','1234648@qq.com','专注IT类知识，拥抱新科技、新技术。','养贤府',30,'117.png',1,2),(119,'119.jpg','社团联盟盟','t','15816465992','stlm@163.com','浙江工业大学圆梦社协会（以下简称“圆梦社”）挂靠在软件/软职学院，我们因为暑期支教而走在了一起，我们期望这份爱心能够继续延续下去，我们唯一的目的就是让更多的同学了解贫困，帮助贫困。协会以开展学生间互助行动为主旨，以开展大学生与希望小学贫困学生的结对子帮助行动为主要工作内容。 以暑期支教、提供义工服务信息及办社报的形式实现圆梦社作为公益性社团的意义，支持教育事业。 开展过慈善资助，公益代购，敬老院义工服务，扶贫助学等活动 由于我们社团是公益性社团，不收取会费。','浙江省杭州市朝晖六区',30,'119.jpg',1,NULL),(120,'120.png','教科学院','t','15734354656','jk@zjut.edu.cn','浙江工业大学教育科学与技术学院、职业技术教育学院的前身是浙江工学院技术师范系，创建于1985年7月，是我国第一所在普通工科院校中创办职业技术师范教育的院系。经过三十多年的建设和发展，在学科建设、人才培养、科学研究和社会服务等方面均取得了丰硕成果，已形成较鲜明的办学特色，在国内同行中具有较高的学术地位。目前是教育部重点建设的职教师资培养培训基地单位，是中国职业技术教育学会职教师资专业委员会副主任理事单位、全国本科院校高等职业教育协作会、中国高等职业技术教育研究会等全国性协会组织的成员单位。','浙江工业大学畅远楼',30,'120.png',1,NULL),(121,'121.jpg','t','t','15845656566','t@163.com','t','t',30,'121.png',1,NULL);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `organization_change_with_login_insert`
AFTER INSERT ON `organization`
FOR EACH ROW
  BEGIN
    INSERT INTO login VALUES (new.id,new.password,new.role);
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER organization_change_with_login_update

AFTER UPDATE ON organization

FOR EACH ROW

  BEGIN

    UPDATE login SET password=new.password ,login.id=NEW.id WHERE id=old.id;

  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `organization_change_with_login_delete`
AFTER DELETE ON `organization`
FOR EACH ROW
  BEGIN
    DELETE FROM login WHERE login.id=old.id;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL,
  `detail` varchar(20) DEFAULT NULL,
  `behaves` json DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (10,'student',NULL),(20,'admin',NULL),(30,'organization',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `state` int(11) NOT NULL,
  `detail` varchar(20) NOT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1000,'等待审核'),(2000,'审核通过（未举办）'),(3000,'审核不通过'),(4000,'举办完成'),(5000,'举办中');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `icon` varchar(25) DEFAULT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL DEFAULT '123456',
  `sex` char(2) DEFAULT '男',
  `phone` varchar(11) DEFAULT NULL,
  `major` varchar(15) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `college` varchar(15) NOT NULL,
  `role` int(11) DEFAULT '10',
  `resetpass` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (201365948,'201365948.jpg','t','t','男','12345674864','t','t','t',10,NULL),(201301010100,'201301010101.jpg','章云','s','男','15700086344','土木工程','一班','建筑学院',10,NULL),(201307260110,'201307260110.jpg','徐宇昊','s','男','15866422596','教育技术学','2013（01）班','教科',10,NULL),(201307260111,'201307260111.jpg','张观博','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260112,'201307260112.jpg','杨倩雪','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260113,'201307260113.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260114,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260115,'201307260115.jpg','王欢','s','男','15868149250','教育技术学','2013（01）班','教科',10,1),(201307260116,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260117,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260118,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260119,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260120,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260121,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260122,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,1),(201307260123,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260124,'default.jpg','张呈双','ss','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260125,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260126,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260127,'default.jpg','张呈双','s','男','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260128,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260129,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260130,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260131,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260132,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260133,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260134,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260135,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260136,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260137,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260138,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260139,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL),(201307260140,'default.jpg','张呈双','s','女','15834566895','教育技术学','2013（01）班','教科',10,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER  change_with_login_insert AFTER INSERT
  ON student FOR EACH ROW
  BEGIN
      INSERT INTO login VALUES (new.id,new.password,new.role);
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `student_change_with_login_update`
AFTER update ON `student`
FOR EACH ROW
  BEGIN
    UPDATE login SET password=new.password WHERE id=old.id;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `student_change_with_login_delete`
AFTER DELETE ON `student`
FOR EACH ROW
  BEGIN
    DELETE FROM login WHERE login.id=old.id;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-18  9:23:58
