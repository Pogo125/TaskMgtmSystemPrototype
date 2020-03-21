-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Jan 2020 um 14:08
-- Server-Version: 10.3.16-MariaDB
-- PHP-Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `task_management_system`
--
CREATE DATABASE IF NOT EXISTS `task_management_system` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `task_management_system`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tasks`
--

CREATE TABLE `tasks` (
  `taskID` int(11) NOT NULL,
  `taskSummary` varchar(100) NOT NULL,
  `requestor` varchar(100) NOT NULL,
  `responsible` varchar(100) NOT NULL,
  `dueDate` date NOT NULL,
  `taskStatus` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `tasks`
--

INSERT INTO `tasks` (`taskID`, `taskSummary`, `requestor`, `responsible`, `dueDate`, `taskStatus`) VALUES
(1220, 'Projektabgabe machen', 'David', 'Arlt', '2222-02-02', 0),
(1221, 'Ganz wichtige Aufgabe', 'David', 'Peter', '2021-01-06', 0),
(1222, 'Frühstücken', 'David ', 'Auch der David ', '2222-02-02', 2),
(1223, ' Präsi halten', ' Susanne', 'David ', '2020-01-10', 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`taskID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `tasks`
--
ALTER TABLE `tasks`
  MODIFY `taskID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1224;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
