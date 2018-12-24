-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tetristimewarpdb
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievements`
--
DROP TABLE IF EXISTS player;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE player (
  playerid int(11) auto_increment,
  username varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  email varchar(45) DEFAULT NULL,
  currency double DEFAULT '0',
  experience double DEFAULT '0',
  avatar varchar(100) DEFAULT NULL,
  clanid int(11) DEFAULT NULL,
  PRIMARY KEY (playerid),
  UNIQUE KEY playerid_UNIQUE (playerid),
  UNIQUE KEY username_UNIQUE (username),
  UNIQUE KEY playercol_UNIQUE (email),
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS achievements;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE achievements (
  id int(11) NOT NULL,
  name varchar(45) DEFAULT NULL,
  description varchar(45) DEFAULT NULL,
  requirement varchar(45) DEFAULT NULL,
  coinReward int(11) DEFAULT NULL,
  expReward double DEFAULT NULL,
  PRIMARY KEY (id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements`
--
INSERT INTO achievements VALUES (0,'Block destroyer','create 100 rows','100 rows',100, 100.00);
INSERT INTO achievements VALUES (1,'Play games','play 5 games','5 games',75, 75.00);

--
-- Table structure for table `blocks`
--

DROP TABLE IF EXISTS blocks;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE blocks (
  blockid int(11) NOT NULL,
  blockcolor varchar(45) DEFAULT NULL,
  blockvalue int(11) DEFAULT NULL,
  PRIMARY KEY (blockid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blocks`
--
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (1, '#ADD8E6', 4369);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (2, '#0000A0', 275);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (3, '#FFA500', 785);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (4, '#FFFF00', 51);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (5, '#00FF00', 561);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (6, '#800080', 114);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (7, '#FF0000', 306);
INSERT INTO blocks (blockid, blockcolor, blockvalue) VALUES (8, '#808080', 19);

--
-- Table structure for table `clan`
--

DROP TABLE IF EXISTS clan;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE clan (
  clanid int(11) NOT NULL,
  clanname varchar(45) NOT NULL,
  `owner` int(11) NOT NULL,
  clanexperience double DEFAULT NULL,
  clanavatar varchar(100) DEFAULT NULL,
  questid int(11) DEFAULT NULL,
  PRIMARY KEY (clanid),
  UNIQUE KEY clanid_UNIQUE (clanid),
  UNIQUE KEY clanname_UNIQUE (clanname),
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clan`
--


--
-- Table structure for table `eras`
--

DROP TABLE IF EXISTS eras;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE eras (
  eraid int(11) NOT NULL,
  eraname varchar(45) DEFAULT NULL,
  erabackground varchar(45) DEFAULT NULL,
  eramusic varchar(45) DEFAULT NULL,
  PRIMARY KEY (eraid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO eras VALUES (0,'egypt','../assets/img/egyptBackground.jpg','egyptMusic');
INSERT INTO eras VALUES (1,'japan','../assets/img/japanBackground.jpg','japanMusic');
INSERT INTO eras VALUES (2,'crusades','../assets/img/crusadesBackground.jpg','crusadesMusic');
INSERT INTO eras VALUES (3,'future','../assets/img/futureBackground.jpeg','futureMusic');

--
-- Dumping data for table `eras`
--



--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS events;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  eventid int(11) NOT NULL,
  effect varchar(100) NOT NULL,
  eventname varchar(45) NOT NULL,
  cost int(11) DEFAULT NULL,
  PRIMARY KEY (eventid),
  UNIQUE KEY eventid_UNIQUE (eventid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--


--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS friends;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE friends (
  playerid int(11) NOT NULL,
  player2id int(11) NOT NULL,
  PRIMARY KEY (playerid,player2id),
  CONSTRAINT player1 FOREIGN KEY (playerid) REFERENCES player (playerid),
  CONSTRAINT player2 FOREIGN KEY (player2id) REFERENCES player (playerid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--


--
-- Table structure for table `heroes`
--

DROP TABLE IF EXISTS heroes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE heroes (
  heroid int(11) NOT NULL,
  name varchar(45) NOT NULL,
  avatar varchar(45) NOT NULL,
  requiredlevel int(11) DEFAULT '0',
  cost int(11) DEFAULT '0',
  eraid int(11) NOT NULL,
  heropower varchar(100) DEFAULT NULL,
  PRIMARY KEY (heroid),
  UNIQUE KEY heroid_UNIQUE (heroid),
  UNIQUE KEY name_UNIQUE (name),
  CONSTRAINT heroeraid FOREIGN KEY (eraid) REFERENCES eras (eraid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO heroes VALUES (0,'warrior','../assets/img/warrior.png',0,0,0,'rage');
INSERT INTO heroes VALUES (1,'pharao','../assets/img/pharao.png',0,0,0,'enslave');
INSERT INTO heroes VALUES (2,'sphinx','../assets/img/sphinx.png',0,0,0,'growl');

INSERT INTO heroes VALUES (3,'ninja','../assets/img/ninja.png',0,0,1,'pickpocket');
INSERT INTO heroes VALUES (4,'sumo','../assets/img/sumo.png',0,0,1,'kobe');
INSERT INTO heroes VALUES (5,'samurai','../assets/img/samurai.png',0,0,1,'slice');

INSERT INTO heroes VALUES (6,'priest','../assets/img/priest.png',0,0,2,'burn');
INSERT INTO heroes VALUES (7,'crusader','../assets/img/crusader.png',0,0,2,'');
INSERT INTO heroes VALUES (8,'blacksmith','../assets/img/blacksmith.png',0,0,2,'smash');

INSERT INTO heroes VALUES (9,'alien','../assets/img/alien.png',0,0,3,'laser');
INSERT INTO heroes VALUES (10,'cyborg','../assets/img/cyborg.png',0,0,3,'Ill be back');
INSERT INTO heroes VALUES (11,'ai','../assets/img/ai.png',0,0,3,'complete takeover');






--
-- Dumping data for table `heroes`
--


--
-- Table structure for table `player`
--



--
-- Dumping data for table `player`
--

INSERT INTO player VALUES (0,'test','9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08','test',0,0,NULL,NULL);
INSERT INTO player VALUES (1,'test2','testeroo','test@test',0,0,NULL,NULL);

--
-- Table structure for table `playerachievements`
--

DROP TABLE IF EXISTS playerachievements;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE playerachievements (
  playerid int(11) NOT NULL,
  id int(11) NOT NULL,
  PRIMARY KEY (playerid,id),
  CONSTRAINT achievementwithid FOREIGN KEY (id) REFERENCES achievements (id),
  CONSTRAINT playerwithid FOREIGN KEY (playerid) REFERENCES player (playerid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerachievements`
--
INSERT INTO playerachievements VALUES (0,0);
INSERT INTO playerachievements VALUES (1,1);

--
-- Table structure for table `playerheroes`
--

DROP TABLE IF EXISTS playerheroes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE playerheroes (
  playerid int(11) NOT NULL,
  heroid int(11) NOT NULL,
  `equipped?` tinyint(4) DEFAULT '0',
  PRIMARY KEY (playerid,heroid),
  CONSTRAINT herowithid FOREIGN KEY (heroid) REFERENCES heroes (heroid),
  CONSTRAINT playerherowithid FOREIGN KEY (playerid) REFERENCES player (playerid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

insert into playerheroes VALUES (0,0,0);
insert into playerheroes VALUES (0,1,0);

insert into playerheroes VALUES (0,3,0);
insert into playerheroes VALUES (0,4,0);

insert into playerheroes VALUES (0,6,0);

insert into playerheroes VALUES (0,9,0);

--
-- Dumping data for table `playerheroes`
--


--
-- Table structure for table `playerunlockables`
--

DROP TABLE IF EXISTS playerunlockables;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE playerunlockables (
  playerid int(11) NOT NULL,
  unlockableid int(11) NOT NULL,
  PRIMARY KEY (playerid,unlockableid),
  
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerunlockables`
--


--
-- Table structure for table `quests`
--

DROP TABLE IF EXISTS quests;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE quests (
  questid int(11) NOT NULL,
  questname varchar(45) NOT NULL,
  questdescription varchar(100) DEFAULT NULL,
  requirement varchar(45) DEFAULT NULL,
  questrewardexp double DEFAULT NULL,
  PRIMARY KEY (questid),
  UNIQUE KEY questname_UNIQUE (questname)
);
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO quests VALUES (0,'Block destroyer','create 100 rows','100 rows', 100.00);
INSERT INTO quests VALUES (1,'Play games','play 5 games','5 games', 75.00);
INSERT INTO quests VALUES (2,'MVP','win 3 games','3 wins', 125.00);


--
-- Dumping data for table `quests`
--


--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS stats;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE stats (
  playerid int(11) NOT NULL,
  gamesplayed double DEFAULT NULL,
  wins int(11) DEFAULT NULL,
  highscore double DEFAULT NULL,
  amountoflines double DEFAULT NULL,
  amountoftetris int(11) DEFAULT NULL,
  winstreak int(11) DEFAULT NULL,
  PRIMARY KEY (playerid),
  CONSTRAINT playerid FOREIGN KEY (playerid) REFERENCES player (playerid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO stats VALUES (0,59,36,5640,1254,100,6);

--
-- Dumping data for table `stats`
--


--
-- Table structure for table `unlockables`
--

DROP TABLE IF EXISTS unlockables;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE unlockables (
  unlockableid int(11) NOT NULL,
  unlockabletype varchar(45) DEFAULT NULL,
  unlockablename varchar(45) DEFAULT NULL,
  unlockablemedia varchar(45) DEFAULT NULL,
  unlockablecost double DEFAULT NULL,
  PRIMARY KEY (unlockableid)
);
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE player
ADD CONSTRAINT playerclanid FOREIGN KEY (clanid) REFERENCES clan (clanid);


ALTER TABLE clan
ADD CONSTRAINT clanquestid FOREIGN KEY (questid) REFERENCES quests (questid);

ALTER TABLE clan
ADD CONSTRAINT `owner` FOREIGN KEY (`owner`) REFERENCES player (playerid);

ALTER TABLE playerunlockables
ADD CONSTRAINT playerunlockwithid FOREIGN KEY (playerid) REFERENCES player (playerid);

ALTER TABLE playerunlockables
ADD CONSTRAINT unlockablewithid FOREIGN KEY (unlockableid) REFERENCES unlockables (unlockableid);


--
-- Dumping data for table `unlockables`
--


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
