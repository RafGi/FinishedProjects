-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: tetristimewarpdb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `achievements` (
  `echievementid` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `ahievementrequirement` varchar(45) DEFAULT NULL,
  `coinReward` int(11) DEFAULT NULL,
  `expReward` double DEFAULT NULL,
  PRIMARY KEY (`echievementid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements`
--

LOCK TABLES `achievements` WRITE;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blocks`
--

DROP TABLE IF EXISTS `blocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blocks` (
  `blockid` int(11) NOT NULL,
  `blockcolor` varchar(45) DEFAULT NULL,
  `blockvalue` int(11) DEFAULT NULL,
  PRIMARY KEY (`blockid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blocks`
--

LOCK TABLES `blocks` WRITE;
/*!40000 ALTER TABLE `blocks` DISABLE KEYS */;
/*!40000 ALTER TABLE `blocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clan`
--

DROP TABLE IF EXISTS `clan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clan` (
  `clanid` int(11) NOT NULL,
  `clanname` varchar(45) NOT NULL,
  `owner` int(11) NOT NULL,
  `clanexperience` double DEFAULT NULL,
  `clanavatar` varchar(100) DEFAULT NULL,
  `questid` int(11) DEFAULT NULL,
  PRIMARY KEY (`clanid`),
  UNIQUE KEY `clanid_UNIQUE` (`clanid`),
  UNIQUE KEY `clanname_UNIQUE` (`clanname`),
  KEY `owner_idx` (`owner`),
  KEY `clanquestid_idx` (`questid`),
  CONSTRAINT `clanquestid` FOREIGN KEY (`questid`) REFERENCES `quests` (`questid`),
  CONSTRAINT `owner` FOREIGN KEY (`owner`) REFERENCES `player` (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clan`
--

LOCK TABLES `clan` WRITE;
/*!40000 ALTER TABLE `clan` DISABLE KEYS */;
/*!40000 ALTER TABLE `clan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eras`
--

DROP TABLE IF EXISTS `eras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `eras` (
  `eraid` int(11) NOT NULL,
  `eraname` varchar(45) DEFAULT NULL,
  `erabackground` varchar(45) DEFAULT NULL,
  `eramusic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`eraid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eras`
--

LOCK TABLES `eras` WRITE;
/*!40000 ALTER TABLE `eras` DISABLE KEYS */;
/*!40000 ALTER TABLE `eras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `events` (
  `eventid` int(11) NOT NULL AUTO_INCREMENT,
  `effect` varchar(100) NOT NULL,
  `eventname` varchar(45) NOT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`eventid`),
  UNIQUE KEY `eventid_UNIQUE` (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `friends` (
  `playerid` int(11) NOT NULL,
  `player2id` int(11) NOT NULL,
  PRIMARY KEY (`playerid`,`player2id`),
  KEY `friendssamengesteldeindex` (`playerid`,`player2id`) /*!80000 INVISIBLE */,
  KEY `player2_idx` (`player2id`),
  CONSTRAINT `player1` FOREIGN KEY (`playerid`) REFERENCES `player` (`playerid`),
  CONSTRAINT `player2` FOREIGN KEY (`player2id`) REFERENCES `player` (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heroes`
--

DROP TABLE IF EXISTS `heroes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `heroes` (
  `heroid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `avatar` varchar(45) NOT NULL,
  `requiredlevel` int(11) DEFAULT '0',
  `cost` int(11) DEFAULT '0',
  `eraid` int(11) NOT NULL,
  `heropower` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`heroid`),
  UNIQUE KEY `heroid_UNIQUE` (`heroid`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `heroeraid_idx` (`eraid`),
  CONSTRAINT `heroeraid` FOREIGN KEY (`eraid`) REFERENCES `eras` (`eraid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heroes`
--

LOCK TABLES `heroes` WRITE;
/*!40000 ALTER TABLE `heroes` DISABLE KEYS */;
/*!40000 ALTER TABLE `heroes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `player` (
  `playerid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `currency` double DEFAULT '0',
  `experience` double DEFAULT '0',
  `avatar` varchar(100) DEFAULT NULL,
  `clanid` int(11) DEFAULT NULL,
  PRIMARY KEY (`playerid`),
  UNIQUE KEY `playerid_UNIQUE` (`playerid`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `playercol_UNIQUE` (`email`),
  KEY `playerclanid_idx` (`clanid`),
  CONSTRAINT `playerclanid` FOREIGN KEY (`clanid`) REFERENCES `clan` (`clanid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerachievements`
--

DROP TABLE IF EXISTS `playerachievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playerachievements` (
  `playerid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`playerid`,`id`),
  KEY `playerachievement` (`playerid`,`id`),
  KEY `achievementwithid_idx` (`id`),
  CONSTRAINT `achievementwithid` FOREIGN KEY (`id`) REFERENCES `achievements` (`echievementid`),
  CONSTRAINT `playerwithid` FOREIGN KEY (`playerid`) REFERENCES `player` (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerachievements`
--

LOCK TABLES `playerachievements` WRITE;
/*!40000 ALTER TABLE `playerachievements` DISABLE KEYS */;
/*!40000 ALTER TABLE `playerachievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerheroes`
--

DROP TABLE IF EXISTS `playerheroes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playerheroes` (
  `playerid` int(11) NOT NULL,
  `heroid` int(11) NOT NULL,
  `equipped?` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`playerid`,`heroid`),
  KEY `playerhero` (`playerid`,`heroid`) /*!80000 INVISIBLE */,
  KEY `herowithid_idx` (`heroid`),
  CONSTRAINT `herowithid` FOREIGN KEY (`heroid`) REFERENCES `heroes` (`heroid`),
  CONSTRAINT `playerherowithid` FOREIGN KEY (`playerid`) REFERENCES `player` (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerheroes`
--

LOCK TABLES `playerheroes` WRITE;
/*!40000 ALTER TABLE `playerheroes` DISABLE KEYS */;
/*!40000 ALTER TABLE `playerheroes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerunlockables`
--

DROP TABLE IF EXISTS `playerunlockables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playerunlockables` (
  `playerid` int(11) NOT NULL,
  `unlockableid` int(11) NOT NULL,
  PRIMARY KEY (`playerid`,`unlockableid`),
  KEY `playerunlockables` (`playerid`,`unlockableid`),
  KEY `unlockablewithid_idx` (`unlockableid`),
  CONSTRAINT `playerunlockwithid` FOREIGN KEY (`playerid`) REFERENCES `player` (`playerid`),
  CONSTRAINT `unlockablewithid` FOREIGN KEY (`unlockableid`) REFERENCES `unlockables` (`unlockableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerunlockables`
--

LOCK TABLES `playerunlockables` WRITE;
/*!40000 ALTER TABLE `playerunlockables` DISABLE KEYS */;
/*!40000 ALTER TABLE `playerunlockables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quests`
--

DROP TABLE IF EXISTS `quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `quests` (
  `questid` int(11) NOT NULL,
  `questname` varchar(45) NOT NULL,
  `questdescription` varchar(100) DEFAULT NULL,
  `requirement` varchar(45) DEFAULT NULL,
  `questrewardexp` double DEFAULT NULL,
  PRIMARY KEY (`questid`),
  UNIQUE KEY `name_UNIQUE` (`questname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quests`
--

LOCK TABLES `quests` WRITE;
/*!40000 ALTER TABLE `quests` DISABLE KEYS */;
/*!40000 ALTER TABLE `quests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stats` (
  `playerid` int(11) NOT NULL,
  `gamesplayed` double DEFAULT NULL,
  `wins` int(11) DEFAULT NULL,
  `highscore` double DEFAULT NULL,
  `amountoflines` double DEFAULT NULL,
  `amountoftetris` int(11) DEFAULT NULL,
  `winstreak` int(11) DEFAULT NULL,
  PRIMARY KEY (`playerid`),
  UNIQUE KEY `playerid_UNIQUE` (`playerid`),
  CONSTRAINT `playerid` FOREIGN KEY (`playerid`) REFERENCES `player` (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unlockables`
--

DROP TABLE IF EXISTS `unlockables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `unlockables` (
  `unlockableid` int(11) NOT NULL,
  `unlockabletype` varchar(45) DEFAULT NULL,
  `unlockablename` varchar(45) DEFAULT NULL,
  `unlockablemedia` varchar(45) DEFAULT NULL,
  `unlockablecost` double DEFAULT NULL,
  PRIMARY KEY (`unlockableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unlockables`
--

LOCK TABLES `unlockables` WRITE;
/*!40000 ALTER TABLE `unlockables` DISABLE KEYS */;
/*!40000 ALTER TABLE `unlockables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-06  9:51:40
