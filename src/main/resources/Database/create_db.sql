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
-- Create schema login
--

CREATE DATABASE IF NOT EXISTS login;
USE login;

--
-- Definition of table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `FK_user_roles` (`user_id`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_role_id`,`user_id`,`authority`) VALUES 
 (1,100,'ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`,`username`,`password`,`enabled`) VALUES 
 (100,'username','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

--
-- Create schema projvidakjonaspatrikdb
--

CREATE DATABASE IF NOT EXISTS projvidakjonaspatrikdb;
USE projvidakjonaspatrikdb;

--
-- Definition of table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
CREATE TABLE `applicant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `surname` varchar(45) CHARACTER SET latin1 NOT NULL,
  `email` varchar(45) CHARACTER SET latin1 NOT NULL,
  `telephone` varchar(45) CHARACTER SET latin1 NOT NULL,
  `dateofbirth` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicant`
--

/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` (`id`,`name`,`surname`,`email`,`telephone`,`dateofbirth`) VALUES 
 (1,'John','Smith','john.smith@mail.com','555-707 310','1960-09-10'),
 (2,'Jane','Doe','jane.doe@outlook.com','555-789 455','1979-06-02');
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;


--
-- Definition of table `applicantavailability`
--

DROP TABLE IF EXISTS `applicantavailability`;
CREATE TABLE `applicantavailability` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `applicantID` int(10) unsigned NOT NULL,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_applicantavailability` (`applicantID`),
  CONSTRAINT `FK_applicantavailability` FOREIGN KEY (`applicantID`) REFERENCES `applicant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicantavailability`
--

/*!40000 ALTER TABLE `applicantavailability` DISABLE KEYS */;
INSERT INTO `applicantavailability` (`id`,`applicantID`,`dateFrom`,`dateTo`) VALUES 
 (17,1,'2014-04-07','2014-08-31'),
 (18,2,'2014-03-02','2014-06-18'),
 (19,2,'2014-06-23','2014-07-25');
/*!40000 ALTER TABLE `applicantavailability` ENABLE KEYS */;


--
-- Definition of table `applicantexperience`
--

DROP TABLE IF EXISTS `applicantexperience`;
CREATE TABLE `applicantexperience` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `applicantID` int(10) unsigned NOT NULL,
  `expertiseID` int(10) unsigned NOT NULL,
  `yearsOfExperience` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `applicantID` (`applicantID`),
  KEY `expertiseID` (`expertiseID`),
  CONSTRAINT `applicantID` FOREIGN KEY (`applicantID`) REFERENCES `applicant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `expertiseID` FOREIGN KEY (`expertiseID`) REFERENCES `expertiselist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicantexperience`
--

/*!40000 ALTER TABLE `applicantexperience` DISABLE KEYS */;
INSERT INTO `applicantexperience` (`id`,`applicantID`,`expertiseID`,`yearsOfExperience`) VALUES 
 (25,1,1,3),
 (26,1,3,7),
 (27,2,4,5);
/*!40000 ALTER TABLE `applicantexperience` ENABLE KEYS */;


--
-- Definition of table `expertiselist`
--

DROP TABLE IF EXISTS `expertiselist`;
CREATE TABLE `expertiselist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expertise_en` varchar(100) CHARACTER SET latin1 NOT NULL,
  `expertise_sv_SE` varchar(100) NOT NULL,
  `expertise_es_ES` varchar(100) NOT NULL,
  `expertise_zh_CN` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `expertiselist`
--

/*!40000 ALTER TABLE `expertiselist` DISABLE KEYS */;
INSERT INTO `expertiselist` (`id`,`expertise_en`,`expertise_sv_SE`,`expertise_es_ES`,`expertise_zh_CN`) VALUES 
 (1,'Programming','Programmering','Programar','编程'),
 (2,'Project Management','Projekthantering','Manejar Proyectos','项目管理'),
 (3,'Managing Clients','Hantera Kundrelationer','Gestionar los Clientes','管理客户端'),
 (4,'Project Planning','Planera Projekt','Planificación de Proyectos','项目规划');
/*!40000 ALTER TABLE `expertiselist` ENABLE KEYS */;


--
-- Definition of procedure `fillout`
--

DROP PROCEDURE IF EXISTS `fillout`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `fillout`()
BEGIN
Truncate table `expertiselist`;
INSERT INTO `expertiselist` (`expertise`) VALUES ("Advertising");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Alternative/Renewable fuels");
INSERT INTO `expertiselist` (`expertise`) VALUES ("American Government");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Anthropology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Archaeology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Arms Control");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Art Education (K-12)");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Asian American Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Auditing");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Biofuels");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Behavior change and the environment");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Biomedical Sciences");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Book History / Print Culture History");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Brand Management");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Broadcasting");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Business Planning");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Business school education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Business Statistics and Data (US and Int'l government)");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Career and Technical education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Career Information");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Censorship");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Children's and young adult literature");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Climate change");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Communication");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Company and industry information");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Comparative Government");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Competitive intelligence");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Consulting");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Corporate and business theory");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Country data");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Curriculum and instruction");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Early childhood education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("E-commerce");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Economic statistics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Educational policy, organization and leadership");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Educational psychology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Elections and Voting");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Elementary education (K-9)");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Employee motivation");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Employment Relations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Energy efficiency");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Entrepreneurship");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Environmental laws/regulations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Environmental and energy data sets and statistics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Environmental education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Environmental Policy Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Epidemiology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("European Union Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Evidence-Based Practice");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Finance");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Financial statistics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Financial structure");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Gender and Women's Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Geography");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Global Health");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Global Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Great lakes environment");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Green business");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Green Government");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Green Libraries");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Green Manufacturing/industrial practices");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Grey Literature");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Health");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Health Literacy");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Historical financial and economic statistics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Human Resources Management");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Industry analysis");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Information science");
INSERT INTO `expertiselist` (`expertise`) VALUES ("International Business");
INSERT INTO `expertiselist` (`expertise`) VALUES ("International Relations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("International Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Internet");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Investments and securities");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Journalism");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Knowledge management");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Labor arbitration");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Labor economics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Labor Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Latina/o Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Lesbian, Gay,  Bisexual and Transgender Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Library Science");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Management theory");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Market Research");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Mass Media");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Medical Informatics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Medicine");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Mergers and acquisitions");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Mergers and acquisitions");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Museum Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Neuroscience / Neuropsychology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("New product development");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Non-Governmental Organizations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Nonprofit organizations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Nursing");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Organizational Behavior");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Organizational design");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Peace Research");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Policy Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Political Communication");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Political Economy");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Political Science");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Pollution prevention");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Psychology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Public Health");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Public Opinion");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Public Relations");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Publishing");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Radio");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Real estate");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Recycling");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Renewable energy");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Second language acquisition & teacher education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Secondary education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Security Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Small business");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Smart growth");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social informatics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social Psychology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social Science Research Methods");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social Science Statistical Resources");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social Services");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Social Work");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Sociology");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Special education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Speech");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Storytelling");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Strategic planning");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Supply chain management");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Sustainability");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Sustainable electronics");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Teacher education");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Technology management");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Technology transfer");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Television");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Testing resources");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Training");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Transnational Migration Studies");
INSERT INTO `expertiselist` (`expertise`) VALUES ("United Nations (and its Specialized Agencies)");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Usability Testing");
INSERT INTO `expertiselist` (`expertise`) VALUES ("Venture Capital");

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
