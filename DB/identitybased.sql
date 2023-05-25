# MySQL-Front 5.1  (Build 1.5)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: identitybased
# ------------------------------------------------------
# Server version 5.0.24a-community-nt

DROP DATABASE IF EXISTS `identitybased`;
CREATE DATABASE `identitybased` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `identitybased`;

#
# Source for table register
#

CREATE TABLE `register` (
  `userid` varchar(255) NOT NULL default '',
  `username` varchar(30) default NULL,
  `password` varchar(20) default NULL,
  `retypepass` varchar(30) NOT NULL default '',
  `age` varchar(3) default NULL,
  `gender` varchar(10) default NULL,
  `address` varchar(100) default NULL,
  `contact` varchar(20) default NULL,
  `email` varchar(40) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table register
#
LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;

INSERT INTO `register` VALUES ('UID814','jhansi','jhansi','jhansi','22','Female','chennai','9898678568','jhansi@gmail.com');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
