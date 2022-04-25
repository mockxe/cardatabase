-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Feb 2019 um 01:35
-- Server-Version: 10.1.37-MariaDB
-- PHP-Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `cardb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `brands`
--

CREATE TABLE `brands` (
  `Id_brand` int(11) NOT NULL,
  `name` varchar(10) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `brands`
--

INSERT INTO `brands` (`Id_brand`, `name`) VALUES
(1, 'VW'),
(2, 'BMW'),
(3, 'Audi'),
(4, 'Volvo'),
(5, 'Seat'),
(6, 'Fiat'),
(7, 'Opel');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `cars`
--

CREATE TABLE `cars` (
  `Id_car` int(11) NOT NULL,
  `Id_brand` int(11) NOT NULL,
  `Id_engine` int(11) NOT NULL,
  `displacement` int(11) NOT NULL,
  `date` date NOT NULL,
  `doors` int(11) NOT NULL,
  `color` varchar(7) CHARACTER SET utf8 COLLATE utf8_german2_ci NOT NULL,
  `weight` int(11) NOT NULL,
  `chassis` varchar(17) CHARACTER SET utf8 COLLATE utf8_german2_ci NOT NULL,
  `pollutant` int(11) NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `cars`
--

INSERT INTO `cars` (`Id_car`, `Id_brand`, `Id_engine`, `displacement`, `date`, `doors`, `color`, `weight`, `chassis`, `pollutant`, `description`) VALUES
(1, 1, 1, 2000, '2019-01-16', 5, '#00ff00', 1500, 'W0L000051T2123456', 1, 'The very first real car in the database.\r\nThat\'s amazing!'),
(2, 2, 3, 1998, '2005-01-02', 3, '#ffffff', 1200, 'JNKCV61E09M303716', 5, 'first webform example'),
(4, 4, 4, 2000, '2007-01-24', 5, '#0000ff', 2500, 'JN8AZ2NC5B9300256', 4, 'dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'),
(5, 5, 2, 2500, '1998-01-12', 2, '#fb0000', 1000, '2T1BR18E5WC056406', 5, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. '),
(8, 7, 5, 0, '2018-11-12', 5, '#000000', 1750, '2C4GP74L71R457516', 2, 'BIYAC = Biyac Is Yet Another Car'),
(10, 6, 1, 3500, '2010-10-07', 3, '#fffa15', 1750, '1G4AH51N1K6437778', 3, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut'),
(12, 3, 2, 3000, '2016-12-25', 5, '#909090', 1500, '1HGCR2F83FA005192', 6, 'abc def');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `engines`
--

CREATE TABLE `engines` (
  `Id_engine` int(11) NOT NULL,
  `name` varchar(15) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `engines`
--

INSERT INTO `engines` (`Id_engine`, `name`) VALUES
(1, 'Gasoline'),
(2, 'Diesel'),
(3, 'Natural Gas'),
(4, 'Hybrid'),
(5, 'Electric');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`Id_brand`);

--
-- Indizes für die Tabelle `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`Id_car`);

--
-- Indizes für die Tabelle `engines`
--
ALTER TABLE `engines`
  ADD PRIMARY KEY (`Id_engine`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `brands`
--
ALTER TABLE `brands`
  MODIFY `Id_brand` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `cars`
--
ALTER TABLE `cars`
  MODIFY `Id_car` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT für Tabelle `engines`
--
ALTER TABLE `engines`
  MODIFY `Id_engine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
