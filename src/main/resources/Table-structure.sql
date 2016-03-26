-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2016 at 11:00 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `amibroker`
--

-- --------------------------------------------------------

--
-- Table structure for table `stock_holiday`
--

CREATE TABLE IF NOT EXISTS `stock_holiday` (
  `dt_holiday` date NOT NULL,
  `desc_holiday` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stock_master`
--

CREATE TABLE IF NOT EXISTS `stock_master` (
  `id_tickler` varchar(4) NOT NULL,
  `nm_tickler` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stock_trx`
--

CREATE TABLE IF NOT EXISTS `stock_trx` (
  `id_tickler` varchar(8) NOT NULL,
  `dt_trx` date NOT NULL,
  `open_prc` float DEFAULT NULL,
  `high_prc` float DEFAULT NULL,
  `low_prc` float DEFAULT NULL,
  `close_prc` float DEFAULT NULL,
  `vol_trx` float DEFAULT NULL,
  `openint` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1
/*!50100 PARTITION BY RANGE ( YEAR(dt_trx))
(PARTITION p0 VALUES LESS THAN (2010) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN (2011) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (2012) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN (2013) ENGINE = InnoDB,
 PARTITION p4 VALUES LESS THAN (2014) ENGINE = InnoDB,
 PARTITION p5 VALUES LESS THAN (2015) ENGINE = InnoDB,
 PARTITION pMax VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stock_master`
--
ALTER TABLE `stock_master`
 ADD PRIMARY KEY (`id_tickler`);

--
-- Indexes for table `stock_trx`
--
ALTER TABLE `stock_trx`
 ADD PRIMARY KEY (`id_tickler`,`dt_trx`), ADD KEY `id_tickler` (`id_tickler`,`dt_trx`), ADD KEY `dt_trx` (`dt_trx`), ADD KEY `dt_trx_2` (`dt_trx`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
