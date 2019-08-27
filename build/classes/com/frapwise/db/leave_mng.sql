-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2019 at 09:43 PM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leave_mng`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`, `description`) VALUES
(5, 'Frontend', ''),
(6, 'Backend', ''),
(7, 'Testing', ''),
(27, 'sdsdfsdf', '');

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `department_id` int(11) NOT NULL DEFAULT '0',
  `leave_type_id` int(11) DEFAULT '0',
  `leave_from` date DEFAULT NULL,
  `leave_to` date DEFAULT NULL,
  `applied_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_off_type` int(11) NOT NULL DEFAULT '0',
  `status` varchar(100) DEFAULT NULL,
  `approval` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`id`, `user_id`, `department_id`, `leave_type_id`, `leave_from`, `leave_to`, `applied_date`, `time_off_type`, `status`, `approval`) VALUES
(50, 13, 6, 3, '2019-08-25', '2019-08-28', '2019-08-24 18:30:00', 1, 'approved', 0),
(51, 13, 6, 3, '2019-08-30', '2019-09-03', '2019-08-24 18:30:00', 1, 'rejected', 0),
(52, 13, 6, 3, '2019-08-25', '2019-08-26', '2019-08-24 18:30:00', 1, 'rejected', 0),
(53, 13, 6, 3, '2019-08-25', '2019-08-26', '2019-08-24 18:30:00', 1, 'rejected', 0),
(54, 13, 6, 3, '2019-08-28', '2019-08-29', '2019-08-24 18:30:00', 1, 'rejected', 0),
(55, 13, 6, 3, '2019-08-27', '2019-08-28', '2019-08-24 18:30:00', 1, 'rejected', 0),
(56, 13, 6, 3, '2019-08-26', '2019-08-27', '2019-08-24 18:30:00', 1, 'rejected', 0),
(57, 13, 6, 3, '2019-08-27', '2019-08-29', '2019-08-24 18:30:00', 1, 'approved', 0),
(58, 13, 6, 3, '2019-08-27', '2019-08-29', '2019-08-24 18:30:00', 1, 'rejected', 0),
(59, 13, 6, 3, '2019-08-30', '2019-08-31', '2019-08-24 18:30:00', 1, 'rejected', 0),
(60, 13, 6, 3, '2019-08-30', '2019-08-31', '2019-08-24 18:30:00', 1, 'approved', 0),
(63, 22, 5, 3, '2019-08-26', '2019-08-28', '2019-08-25 18:30:00', 1, 'rejected', 0),
(64, 22, 5, 3, '2019-08-26', '2019-08-28', '2019-08-25 18:30:00', 1, 'rejected', 0),
(65, 22, 5, 3, '2019-08-26', '2019-08-28', '2019-08-25 18:30:00', 1, 'rejected', 0),
(66, 22, 5, 3, '2019-08-26', '2019-08-28', '2019-08-25 18:30:00', 1, 'rejected', 0),
(67, 1, 6, 4, '2019-08-29', '2019-08-31', '2019-08-25 18:30:00', 1, 'rejected', 0),
(68, 1, 6, 4, '2019-08-29', '2019-08-31', '2019-08-25 18:30:00', 1, 'pending', 0);

-- --------------------------------------------------------

--
-- Table structure for table `leave_types`
--

CREATE TABLE `leave_types` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leave_types`
--

INSERT INTO `leave_types` (`id`, `name`, `description`) VALUES
(3, 'Vacation leave', '10'),
(4, 'Sick leave', '5'),
(5, 'Unpaid leave', '0'),
(6, 'Maternity leave', '0'),
(7, 'Compensatory leave', '0');

-- --------------------------------------------------------

--
-- Table structure for table `official_leaves`
--

CREATE TABLE `official_leaves` (
  `id` int(11) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` text,
  `department_ids` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `official_leaves`
--

INSERT INTO `official_leaves` (`id`, `name`, `date`, `description`, `department_ids`) VALUES
(1, 'diwali', '2019-10-27', 'diwali', '5,7');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `id` varchar(300) NOT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  `ip_address` varchar(45) DEFAULT NULL,
  `user_agent` text,
  `payload` text,
  `last_activity` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`id`, `uid`, `ip_address`, `user_agent`, `payload`, `last_activity`) VALUES
('2jCeyPChILExprtj', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3c3ee776', '2019-08-17 19:35:12'),
('3EHBZrBKq1OEHWPp', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@79a717b', '2019-08-18 09:21:32'),
('3OcUamH7RQFhAHUY', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3e1c5bb1', '2019-08-19 07:44:02'),
('4VCVL3IHzAb9yXJ1', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@77b2fb72', '2019-08-24 20:34:41'),
('5MRUSoRyRn0FTkxH', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@7e68f633', '2019-08-22 09:40:33'),
('5YJZBnnWaAb0AvZE', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@17629c85', '2019-08-19 07:36:28'),
('6HSCMnmE81TueF89', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@10916155', '2019-08-19 08:56:43'),
('6tyNtnhhoJ2Pdx8d', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@370f8484', '2019-08-26 10:08:10'),
('7dsaj6EUkiddKyGv', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@1b1a455b', '2019-08-21 05:50:07'),
('8eXRuFDVuxJ1S7Hr', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@6691f71e', '2019-08-23 07:50:47'),
('8f7oUPTCQaJXlIy2', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3468b0d9', '2019-08-26 07:53:48'),
('alI5KAqlZ560F2Zq', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3dbe7b96', '2019-08-26 12:29:30'),
('bMXbZZkBabTE3Dhy', 12, '', '', 'org.apache.catalina.session.StandardSessionFacade@4f0d3b64', '2019-08-22 18:41:49'),
('cp2h4yKm347GJmh6', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@6a70e2b0', '2019-08-24 07:57:56'),
('EBNqtntl5b5k9xXN', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@13e65e98', '2019-08-19 06:29:50'),
('EeLCZCWP4RkRhE3A', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@2468f426', '2019-08-25 12:04:34'),
('Fm1j0Z70q4AS6Fxx', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@47148b85', '2019-08-18 07:52:45'),
('FMCs9MJPGSkPtJN2', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@548875a2', '2019-08-23 19:49:02'),
('fyA3TeKWpGxltSNj', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@19954ad', '2019-08-24 12:01:04'),
('fydHHAKAvVHuOXUk', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@47d0d220', '2019-08-23 06:39:13'),
('gL4YVXgEBFS6mJ92', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@7c3a9266', '2019-08-25 21:33:08'),
('i9lZTncJHZ9sIGng', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@3fd59a94', '2019-08-22 06:15:01'),
('iAga1uDptb1bZWHu', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@4288a79a', '2019-08-25 12:06:05'),
('iajnhmq1XG5EblNb', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3544c7db', '2019-08-25 06:58:27'),
('IES7LaezOWVtpyT2', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@11c34065', '2019-08-24 20:24:16'),
('IWlulecKJVphBShc', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@df9c499', '2019-08-23 11:23:37'),
('j4Rly7d0h4l28xGV', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@1c12459f', '2019-08-22 12:11:49'),
('kYB8hUH9tfT6kjXm', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@4928a1ca', '2019-08-23 11:17:44'),
('lnUZ9gu7KB0jRarr', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@47148b85', '2019-08-18 07:47:52'),
('LXGT7yFDi2y7szNP', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@294db9c6', '2019-08-23 16:36:43'),
('MUOCV1MunkieW98Z', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@348904a5', '2019-08-19 17:38:11'),
('mXzutyRVootLlXMm', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@67b63377', '2019-08-26 10:03:40'),
('N7nqLXc7pX3fDnSD', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@4f5bdf87', '2019-08-20 10:30:54'),
('NPrfIW9W0M9E4PpU', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@1f087d45', '2019-08-18 07:56:47'),
('oGl2DG9op4OYntb6', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@66da0ac', '2019-08-18 08:23:50'),
('OyhxKPJFXArA3Rxt', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@4fc80e13', '2019-08-23 11:29:07'),
('p3VFhak3DXWWmlTy', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@78af244f', '2019-08-24 10:27:21'),
('p9GlyKdgH1sAhUI7', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@23e1a895', '2019-08-25 11:44:50'),
('ptG8mjdMzmsWbalD', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@5e613373', '2019-08-24 13:28:39'),
('ptgl1i1QxEnrI4lO', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@396cac1', '2019-08-19 11:36:36'),
('Rp87S01Xu8TrLSnM', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@7d74b8fb', '2019-08-26 12:26:15'),
('rWrvgKF61xk1rHXT', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@4f0d3b64', '2019-08-22 18:33:30'),
('rYF2hxYM7mjZpEz7', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@59219fcd', '2019-08-24 14:06:14'),
('T1os3Vk5vkbSRbkB', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@422ca450', '2019-08-24 17:15:58'),
('TECSGTWE3QWPkReK', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@77cccdd7', '2019-08-26 05:51:52'),
('TZJ8vSdnZAgFDL3q', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@3b778515', '2019-08-19 06:19:31'),
('UDTqEgPVHoMmqh3B', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@38c5ea37', '2019-08-21 18:52:51'),
('UgiCItbrMeS7nlR1', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@2762c8b9', '2019-08-20 18:06:01'),
('uktWxQtjhsxHZ6XY', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@7ba32f26', '2019-08-22 12:25:18'),
('uP28965mYPJWP6hG', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@620c2bd', '2019-08-22 07:06:35'),
('UuopUVBKXLpKrc75', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@14a0d168', '2019-08-18 08:43:46'),
('vae5YJLBjRjXkeu6', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@6b36f82c', '2019-08-20 06:40:43'),
('VMyelHaGtIYOmIVo', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@4ff7df6f', '2019-08-25 12:24:15'),
('vRtpj0RMGHEkDDVr', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@6f746ce7', '2019-08-22 21:46:55'),
('vSfekHZzy929y7mT', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@406798b5', '2019-08-21 20:41:00'),
('vzz2EoCqeXsm9QkM', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@47148b85', '2019-08-18 07:49:33'),
('WKS1q2z4p3oW6qSY', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@bbdd6db', '2019-08-26 12:15:49'),
('XUOYc57sAxYgJxii', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@384b5588', '2019-08-21 10:50:12'),
('ybiNl09ngVKTGI7t', 1, '', '', 'org.apache.catalina.session.StandardSessionFacade@524a3f2d', '2019-08-25 16:28:46'),
('YKE30Nqz10kGrPB9', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@6691f71e', '2019-08-23 07:50:46'),
('YYx7Do3pblJkv4Xg', 0, '', '', 'org.apache.catalina.session.StandardSessionFacade@14a0d168', '2019-08-18 08:42:41'),
('zDZgCPukeSlaCaMR', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@39d52c13', '2019-08-23 18:20:05'),
('zG13mu7sicj8bkVj', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@281e9d25', '2019-08-22 07:46:19'),
('ZIDLLJY5VhIB4Uhl', 8, '', '', 'org.apache.catalina.session.StandardSessionFacade@592b4dc6', '2019-08-21 09:25:54'),
('zNknCCnxjcViBIEv', 13, '', '', 'org.apache.catalina.session.StandardSessionFacade@51d00405', '2019-08-24 18:52:58');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fname` varchar(300) DEFAULT NULL,
  `lname` varchar(300) DEFAULT NULL,
  `username` varchar(300) DEFAULT NULL,
  `home_office` varchar(300) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `remember_token` varchar(500) DEFAULT NULL,
  `role` varchar(300) DEFAULT NULL,
  `status` varchar(300) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fname`, `lname`, `username`, `home_office`, `department_id`, `email`, `password`, `remember_token`, `role`, `status`, `created_at`, `updated_at`) VALUES
(1, 'siddhartha', 'singh', 'sidd5sci', 'Banglore', 6, 'admin@example.com', '8Bpr03G5PP7yxysIL0c45w==', NULL, 'Admin', 'enabled', '2019-08-16 11:36:14', '2019-08-16 11:36:14'),
(13, 'Jhon', 'Doe', 'jhon5due', 'Banglore', 6, 'agent@example.com', '8Bpr03G5PP7yxysIL0c45w==', NULL, 'Employee', 'enabled', '2019-08-22 19:01:30', '2019-08-22 19:01:30'),
(22, 'test', 'user', 'test', 'Banglore', 5, 'test@example.com', '8Bpr03G5PP7yxysIL0c45w==', NULL, 'Employee', 'enabled', '2019-08-26 11:49:54', '2019-08-26 11:49:54');

-- --------------------------------------------------------

--
-- Table structure for table `user_leave_maper`
--

CREATE TABLE `user_leave_maper` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  `leave_type_id` int(11) NOT NULL DEFAULT '0',
  `leave_max` int(11) NOT NULL DEFAULT '0',
  `leave_taken` int(11) NOT NULL DEFAULT '0',
  `leave_availible` int(11) NOT NULL DEFAULT '0',
  `time_duration` int(11) NOT NULL DEFAULT '0',
  `assigned_from` date DEFAULT NULL,
  `assigned_to` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_leave_maper`
--

INSERT INTO `user_leave_maper` (`id`, `uid`, `leave_type_id`, `leave_max`, `leave_taken`, `leave_availible`, `time_duration`, `assigned_from`, `assigned_to`, `created_at`) VALUES
(1, 1, 3, 10, 0, 10, 0, '2019-08-22', '2019-09-21', '2019-08-21 13:12:06'),
(24, 13, 3, 10, 9, 1, 0, '2019-08-23', '2019-08-23', '2019-08-22 19:01:31'),
(25, 13, 4, 5, 0, 5, 0, '2019-08-23', '2019-08-23', '2019-08-22 19:01:31'),
(26, 13, 5, 0, 0, 0, 0, '2019-08-23', '2019-08-23', '2019-08-22 19:01:31'),
(27, 13, 6, 0, 0, 0, 0, '2019-08-23', '2019-08-23', '2019-08-22 19:01:31'),
(28, 13, 7, 0, 0, 0, 0, '2019-08-23', '2019-08-23', '2019-08-22 19:01:31'),
(29, 1, 4, 5, 2, 3, 0, '2019-01-01', '2019-03-01', '2019-08-22 21:28:54'),
(35, 22, 3, 10, 0, 10, 0, '2019-08-26', '2019-08-26', '2019-08-26 11:49:54'),
(36, 22, 4, 5, 0, 5, 0, '2019-08-26', '2019-08-26', '2019-08-26 11:49:55'),
(37, 22, 5, 0, 0, 0, 0, '2019-08-26', '2019-08-26', '2019-08-26 11:49:55'),
(38, 22, 6, 0, 0, 0, 0, '2019-08-26', '2019-08-26', '2019-08-26 11:49:55'),
(39, 22, 7, 0, 0, 0, 0, '2019-08-26', '2019-08-26', '2019-08-26 11:49:55'),
(40, 1, 5, 5, 0, 5, 11291, '2019-01-01', '2019-12-31', '2019-08-26 12:19:04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `leave_types`
--
ALTER TABLE `leave_types`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `official_leaves`
--
ALTER TABLE `official_leaves`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `remember_token` (`remember_token`);

--
-- Indexes for table `user_leave_maper`
--
ALTER TABLE `user_leave_maper`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `leaves`
--
ALTER TABLE `leaves`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `leave_types`
--
ALTER TABLE `leave_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `official_leaves`
--
ALTER TABLE `official_leaves`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `user_leave_maper`
--
ALTER TABLE `user_leave_maper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
