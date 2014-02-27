-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.72-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema projvidakjonaspatrikdb
--

CREATE DATABASE IF NOT EXISTS projvidakjonaspatrikdb;
USE projvidakjonaspatrikdb;

--
-- Definition of table `applicantavailability`
--

DROP TABLE IF EXISTS `applicantavailability`;
CREATE TABLE `applicantavailability` (
  `applicantID` int(10) unsigned NOT NULL,
  `from` date NOT NULL,
  `to` date NOT NULL,
  PRIMARY KEY (`applicantID`,`from`),
  CONSTRAINT `applicantID2` FOREIGN KEY (`applicantID`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `applicantavailability`
--

/*!40000 ALTER TABLE `applicantavailability` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicantavailability` ENABLE KEYS */;


--
-- Definition of table `experience`
--

DROP TABLE IF EXISTS `experience`;
CREATE TABLE `experience` (
  `applicantID` int(10) unsigned NOT NULL,
  `expertiseID` int(10) unsigned NOT NULL,
  `yearsOfExperience` int(10) unsigned NOT NULL,
  PRIMARY KEY (`applicantID`),
  KEY `expertiseID` (`expertiseID`),
  CONSTRAINT `applicantID` FOREIGN KEY (`applicantID`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `expertiseID` FOREIGN KEY (`expertiseID`) REFERENCES `expertiselist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `experience`
--

/*!40000 ALTER TABLE `experience` DISABLE KEYS */;
/*!40000 ALTER TABLE `experience` ENABLE KEYS */;


--
-- Definition of table `expertiselist`
--

DROP TABLE IF EXISTS `expertiselist`;
CREATE TABLE `expertiselist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expertise` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expertiselist`
--

/*!40000 ALTER TABLE `expertiselist` DISABLE KEYS */;
/*!40000 ALTER TABLE `expertiselist` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `dateofbirth` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`,`name`,`surname`,`email`,`telephone`,`dateofbirth`) VALUES 
 (1,'Andrea','Palacio','andrea.palacio@live.se','0737829123','2001-01-01');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
