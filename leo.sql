-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Apr 22, 2019 at 04:11 PM
-- Server version: 10.2.17-MariaDB-1:10.2.17+maria~bionic
-- PHP Version: 7.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leo`
--

-- --------------------------------------------------------

--
-- Table structure for table `applicant_matches`
--

CREATE TABLE `applicant_matches` (
  `id` bigint(20) NOT NULL,
  `applicant_id` bigint(20) NOT NULL,
  `match_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicant_matches`
--

INSERT INTO `applicant_matches` (`id`, `applicant_id`, `match_id`) VALUES
(1, 1, 1),
(2, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `applicant_rankings`
--

CREATE TABLE `applicant_rankings` (
  `id` bigint(20) NOT NULL,
  `sequence` int(11) NOT NULL,
  `match_id` bigint(20) DEFAULT NULL,
  `applicant_match_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicant_rankings`
--

INSERT INTO `applicant_rankings` (`id`, `sequence`, `match_id`, `applicant_match_id`, `position_id`) VALUES
(29, 1, 1, 2, 1),
(18, 1, 1, 1, 1),
(27, 2, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `applicant_ranking_end_date` datetime DEFAULT NULL,
  `recruiter_ranking_end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `summary_ranking_end_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`id`, `name`, `applicant_ranking_end_date`, `recruiter_ranking_end_date`, `start_date`, `summary_ranking_end_date`, `description`) VALUES
(1, 'SIT Career Day', '2019-02-02 00:00:00', '2019-02-16 00:00:00', '2019-01-01 00:00:00', '2019-02-28 00:00:00', 'we\'re hiring IT guys.');

-- --------------------------------------------------------

--
-- Table structure for table `positions`
--

CREATE TABLE `positions` (
  `id` bigint(20) NOT NULL,
  `capacity` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `recruiter_match_id` bigint(20) DEFAULT NULL,
  `match_id` bigint(20) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `positions`
--

INSERT INTO `positions` (`id`, `capacity`, `name`, `recruiter_match_id`, `match_id`, `money`) VALUES
(1, 2, 'Software Engineer', 1, 1, '฿30,000-฿45,000'),
(2, 1, 'Developer', 1, 1, '฿10,000-฿15,000');

-- --------------------------------------------------------

--
-- Table structure for table `recruiter_matches`
--

CREATE TABLE `recruiter_matches` (
  `id` bigint(20) NOT NULL,
  `recruiter_id` bigint(20) NOT NULL,
  `match_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `recruiter_matches`
--

INSERT INTO `recruiter_matches` (`id`, `recruiter_id`, `match_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `recruiter_rankings`
--

CREATE TABLE `recruiter_rankings` (
  `id` bigint(20) NOT NULL,
  `sequence` int(11) NOT NULL,
  `match_id` bigint(20) DEFAULT NULL,
  `applicant_match_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `recruiter_rankings`
--

INSERT INTO `recruiter_rankings` (`id`, `sequence`, `match_id`, `applicant_match_id`, `position_id`) VALUES
(22, 2, 1, 2, 1),
(21, 1, 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applicant_matches`
--
ALTER TABLE `applicant_matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmq6ti2x3cr6yekbxo2rxf1coa` (`match_id`);

--
-- Indexes for table `applicant_rankings`
--
ALTER TABLE `applicant_rankings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6obavefet84r86hp8fh8ki4vi` (`match_id`),
  ADD KEY `FKlhuocs4dlhulw645u7l4nne2t` (`applicant_match_id`),
  ADD KEY `FKax70rgyesgxkf8fjwu8tub8uf` (`position_id`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `positions`
--
ALTER TABLE `positions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqygl7i4slg8bypjqgchpbud3b` (`match_id`),
  ADD KEY `FKkru74vep3ko68i05y8hqfj09j` (`recruiter_match_id`);

--
-- Indexes for table `recruiter_matches`
--
ALTER TABLE `recruiter_matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdb2gxu61p0mmqchyj3d1yjb7f` (`match_id`);

--
-- Indexes for table `recruiter_rankings`
--
ALTER TABLE `recruiter_rankings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4s2phvqgwpr3kxep86uw8ob6a` (`match_id`),
  ADD KEY `FKe0wvgtbptb4xlcnieh6vfuybm` (`applicant_match_id`),
  ADD KEY `FKip4g2xi5ma9qcc4by8v2lgovk` (`position_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applicant_matches`
--
ALTER TABLE `applicant_matches`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `applicant_rankings`
--
ALTER TABLE `applicant_rankings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `positions`
--
ALTER TABLE `positions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `recruiter_matches`
--
ALTER TABLE `recruiter_matches`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `recruiter_rankings`
--
ALTER TABLE `recruiter_rankings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
