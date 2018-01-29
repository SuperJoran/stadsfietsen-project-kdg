/* 
 * Dit create script is niet nodig, want we werken vanuit code-first approach!
*/

CREATE SCHEMA stadsfietsen;

CREATE TABLE `t_stad` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  `postCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `t_station` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `karakteristiek` varchar(255) DEFAULT NULL,
  `stadId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_secpacrj359lmjs4oi0hduovn` (`stadId`),
  CONSTRAINT `FK_secpacrj359lmjs4oi0hduovn` FOREIGN KEY (`stadId`) REFERENCES `t_stad` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

CREATE TABLE `t_fietstype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `dagPrijs` double DEFAULT NULL,
  `naam` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `t_fiets` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `fietsTypeId` int(11) NOT NULL,
  `stadId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_47d6uxs6v1tw3y1wlxdt1gqej` (`fietsTypeId`),
  KEY `FK_lc1tchtulcggno7hfugv7d6rf` (`stadId`),
  CONSTRAINT `FK_47d6uxs6v1tw3y1wlxdt1gqej` FOREIGN KEY (`fietsTypeId`) REFERENCES `t_fietstype` (`Id`),
  CONSTRAINT `FK_lc1tchtulcggno7hfugv7d6rf` FOREIGN KEY (`stadId`) REFERENCES `t_stad` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=latin1;

CREATE TABLE `t_slot` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `fietsId` int(11) DEFAULT NULL,
  `stationId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_fs7otl7qmcf4kiglylwbjkotm` (`fietsId`),
  KEY `FK_7gorryceab6y8qgfxne99ns3d` (`stationId`),
  CONSTRAINT `FK_7gorryceab6y8qgfxne99ns3d` FOREIGN KEY (`stationId`) REFERENCES `t_station` (`Id`),
  CONSTRAINT `FK_fs7otl7qmcf4kiglylwbjkotm` FOREIGN KEY (`fietsId`) REFERENCES `t_fiets` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=565 DEFAULT CHARSET=latin1;

CREATE TABLE `t_pastype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `t_klant` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  `postCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;

CREATE TABLE `t_verkoop` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` datetime DEFAULT NULL,
  `klantId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_mmvekfbuo75gk9jqyjpwkialt` (`klantId`),
  CONSTRAINT `FK_mmvekfbuo75gk9jqyjpwkialt` FOREIGN KEY (`klantId`) REFERENCES `t_klant` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;

CREATE TABLE `t_pas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `beginDatum` datetime DEFAULT NULL,
  `eindDatum` datetime DEFAULT NULL,
  `wachtwoord` varchar(255) DEFAULT NULL,
  `fietsTypeId` int(11) NOT NULL,
  `pasTypeId` int(11) NOT NULL,
  `verkoopId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_98f73uxwgfuslcajf1ejg0f54` (`fietsTypeId`),
  KEY `FK_p4eto9xxydrem2ctk3ty9gu62` (`pasTypeId`),
  KEY `FK_4yucueb6k5lhqpjci7l0wx85y` (`verkoopId`),
  CONSTRAINT `FK_4yucueb6k5lhqpjci7l0wx85y` FOREIGN KEY (`verkoopId`) REFERENCES `t_verkoop` (`Id`),
  CONSTRAINT `FK_98f73uxwgfuslcajf1ejg0f54` FOREIGN KEY (`fietsTypeId`) REFERENCES `t_fietstype` (`Id`),
  CONSTRAINT `FK_p4eto9xxydrem2ctk3ty9gu62` FOREIGN KEY (`pasTypeId`) REFERENCES `t_pastype` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20001 DEFAULT CHARSET=latin1;

CREATE TABLE `t_rit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `beginDatum` datetime DEFAULT NULL,
  `eindDatum` datetime DEFAULT NULL,
  `beginStationId` int(11) DEFAULT NULL,
  `eindStationId` int(11) DEFAULT NULL,
  `fietsId` int(11) NOT NULL,
  `pasId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_7te3326e331i9s8wg593336y3` (`beginStationId`),
  KEY `FK_omqopc4487vs3mslugtnr0qod` (`eindStationId`),
  KEY `FK_einxm97aou951s36655g4013i` (`fietsId`),
  KEY `FK_523pdkcb295qbkn0yk7ho1l00` (`pasId`),
  CONSTRAINT `FK_523pdkcb295qbkn0yk7ho1l00` FOREIGN KEY (`pasId`) REFERENCES `t_pas` (`Id`),
  CONSTRAINT `FK_7te3326e331i9s8wg593336y3` FOREIGN KEY (`beginStationId`) REFERENCES `t_station` (`Id`),
  CONSTRAINT `FK_einxm97aou951s36655g4013i` FOREIGN KEY (`fietsId`) REFERENCES `t_fiets` (`Id`),
  CONSTRAINT `FK_omqopc4487vs3mslugtnr0qod` FOREIGN KEY (`eindStationId`) REFERENCES `t_station` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4220001 DEFAULT CHARSET=latin1;

