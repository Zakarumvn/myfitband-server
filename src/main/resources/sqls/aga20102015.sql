-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Paź 2019, 15:03
-- Wersja serwera: 10.4.8-MariaDB
-- Wersja PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `myfitband`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `alerts`
--

CREATE TABLE `alerts` (
  `alert_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `alert_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `alert_types`
--

CREATE TABLE `alert_types` (
  `alert_type_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `configuration`
--

CREATE TABLE `configuration` (
  `configuration_id` int(11) NOT NULL,
  `active` smallint(6) DEFAULT NULL,
  `date_value` datetime DEFAULT NULL,
  `integer_value` int(11) DEFAULT NULL,
  `string_value` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `configuration_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `configuration_type`
--

CREATE TABLE `configuration_type` (
  `configuration_type_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `devices`
--

CREATE TABLE `devices` (
  `device_id` int(11) NOT NULL,
  `fire_base_token` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `gpsdata`
--

CREATE TABLE `gpsdata` (
  `gps_data_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `gpsx` double DEFAULT NULL,
  `gpsy` double DEFAULT NULL,
  `speed` double DEFAULT NULL,
  `workout_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `gpsdata`
--

INSERT INTO `gpsdata` (`gps_data_id`, `date`, `gpsx`, `gpsy`, `speed`, `workout_id`) VALUES
(12, '2019-10-20 13:46:49', 22.5276146, 51.2708819, NULL, 4),
(13, '2019-10-20 13:47:19', 22.528085, 51.270903, NULL, 4),
(14, '2019-10-20 13:47:39', 22.527542, 51.270407, NULL, 4),
(15, '2019-10-20 13:47:59', 22.528068, 51.270642, NULL, 4),
(16, '2019-10-20 13:48:19', 22.526576, 51.270446, NULL, 4),
(17, '2019-10-20 13:48:39', 22.526557, 51.26993, NULL, 4),
(18, '2019-10-20 13:48:59', 22.527206, 51.269648, NULL, 4),
(19, '2019-10-20 13:46:49', 22.549046, 51.235723, NULL, 5),
(20, '2019-10-20 13:47:19', 22.54844, 51.236049, NULL, 5),
(21, '2019-10-20 13:47:39', 22.547624, 51.236489, NULL, 5),
(22, '2019-10-20 13:47:59', 22.545861, 51.236917, NULL, 5),
(23, '2019-10-20 13:48:19', 22.543836, 51.237307, NULL, 5),
(24, '2019-10-20 13:48:39', 22.54351, 51.239161, NULL, 5),
(32, NULL, 23.186538495007397, 50.99099099099099, 0, 26),
(33, NULL, 23.186538495007397, 50.99099099099099, 0, 27),
(34, NULL, 0, 0, 0, 26),
(35, NULL, 0, 0, 0, 27),
(42, NULL, 23.186538495007397, 50.99099099099099, 0, 36),
(43, NULL, 23.186538495007397, 50.99099099099099, 0, 37),
(44, NULL, 0, 0, 0, 36),
(45, NULL, 0, 0, 0, 37),
(52, NULL, 23.186538495007397, 50.99099099099099, 0, 46),
(53, NULL, 23.186538495007397, 50.99099099099099, 0, 47),
(54, NULL, 0, 0, 0, 46),
(55, NULL, 0, 0, 0, 47),
(61, NULL, 23.186538495007397, 50.99099099099099, 0, 56),
(62, NULL, 23.186538495007397, 50.99099099099099, 0, 56),
(63, NULL, 23.186538495007397, 50.99099099099099, 0, 56),
(64, NULL, 23.186538495007397, 50.99099099099099, 0, 56),
(88, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(89, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(90, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(91, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(92, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(93, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(94, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(95, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(96, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(97, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(98, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(99, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(100, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(101, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(102, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(103, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(104, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(105, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(106, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(107, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(108, NULL, 23.186538495007397, 50.99099099099099, 0, 65),
(109, NULL, 23.186538495007397, 50.99099099099099, 0, 65);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110),
(110);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `measurements`
--

CREATE TABLE `measurements` (
  `measurement_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `value` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `measurement_type_id` int(11) DEFAULT NULL,
  `workout` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `measurements`
--

INSERT INTO `measurements` (`measurement_id`, `date`, `value`, `measurement_type_id`, `workout`) VALUES
(7, '2019-10-20 13:46:49', '42', 6, 4),
(8, '2019-10-20 13:47:49', '46', 6, 4),
(9, '2019-10-20 13:48:49', '51', 6, 4),
(10, '2019-10-20 13:49:49', '58', 6, 4),
(11, '2019-10-20 13:50:49', '63', 6, 4),
(28, '2019-10-20 13:52:51', '91.0', 6, 26),
(29, '2019-10-20 13:52:51', '91.0', 6, 27),
(30, '2019-10-20 13:52:51', '107.0', 6, 26),
(31, '2019-10-20 13:52:51', '107.0', 6, 27),
(38, '2019-10-20 13:52:51', '91.0', 6, 36),
(39, '2019-10-20 13:52:51', '91.0', 6, 37),
(40, '2019-10-20 13:52:51', '107.0', 6, 36),
(41, '2019-10-20 13:52:51', '107.0', 6, 37),
(48, '2019-10-20 13:52:51', '91.0', 6, 46),
(49, '2019-10-20 13:52:51', '91.0', 6, 47),
(50, '2019-10-20 13:52:51', '107.0', 6, 46),
(51, '2019-10-20 13:52:51', '107.0', 6, 47),
(57, '2019-10-20 14:49:06', '94.0', 6, 56),
(58, '2019-10-20 14:49:05', '72.0', 6, 56),
(59, '2019-10-20 14:49:08', '0.0', 6, 56),
(60, '2019-10-20 14:49:10', '0.0', 6, 56),
(66, '2019-10-20 14:58:01', '0.0', 6, 65),
(67, '2019-10-20 14:58:04', '98.0', 6, 65),
(68, '2019-10-20 14:58:07', '101.0', 6, 65),
(69, '2019-10-20 14:57:54', '66.0', 6, 65),
(70, '2019-10-20 14:57:52', '78.0', 6, 65),
(71, '2019-10-20 14:58:11', '48.0', 6, 65),
(72, '2019-10-20 14:58:14', '0.0', 6, 65),
(73, '2019-10-20 14:57:58', '92.0', 6, 65),
(74, '2019-10-20 14:57:49', '83.0', 6, 65),
(75, '2019-10-20 14:57:43', '64.0', 6, 65),
(76, '2019-10-20 14:58:02', '91.0', 6, 65),
(77, '2019-10-20 14:57:46', '0.0', 6, 65),
(78, '2019-10-20 14:57:56', '92.0', 6, 65),
(79, '2019-10-20 14:58:06', '120.0', 6, 65),
(80, '2019-10-20 14:57:53', '115.0', 6, 65),
(81, '2019-10-20 14:58:12', '115.0', 6, 65),
(82, '2019-10-20 14:58:09', '0.0', 6, 65),
(83, '2019-10-20 14:58:16', '77.0', 6, 65),
(84, '2019-10-20 14:57:50', '85.0', 6, 65),
(85, '2019-10-20 14:57:44', '62.0', 6, 65),
(86, '2019-10-20 14:57:57', '57.0', 6, 65),
(87, '2019-10-20 14:57:48', '82.0', 6, 65);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `measurement_types`
--

CREATE TABLE `measurement_types` (
  `measurement_type` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `measurement_types`
--

INSERT INTO `measurement_types` (`measurement_type`, `description`) VALUES
(6, 'pomiar pulsu');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `physical_properties`
--

CREATE TABLE `physical_properties` (
  `physical_property_id` int(11) NOT NULL,
  `bone_mass` double DEFAULT NULL,
  `fat_mass` double DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `muscle_mass` double DEFAULT NULL,
  `water` double DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `settings`
--

CREATE TABLE `settings` (
  `setting_id` int(11) NOT NULL,
  `active` smallint(6) DEFAULT NULL,
  `notification_time` datetime DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sports`
--

CREATE TABLE `sports` (
  `sport_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `kcal_per_hour` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `sports`
--

INSERT INTO `sports` (`sport_id`, `description`, `kcal_per_hour`, `name`) VALUES
(2, NULL, 450, 'bieganie'),
(3, NULL, 250, 'jazda na rowerze');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`user_id`, `birth_date`, `email`, `first_name`, `last_name`, `login`, `password`) VALUES
(1, '1999-10-20 13:46:49', 'testowa.kasia@gmail.com', 'Kasia', 'Testowa', 'testowa', 'kasia'),
(25, '1995-09-15 02:00:00', 'agataguli@gmail.com', 'Agata', 'Kołtun', 'agataguli', 'qwerty123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `workouts`
--

CREATE TABLE `workouts` (
  `workout_id` int(11) NOT NULL,
  `startdt` datetime DEFAULT NULL,
  `stopdt` datetime DEFAULT NULL,
  `sport` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `workouts`
--

INSERT INTO `workouts` (`workout_id`, `startdt`, `stopdt`, `sport`, `user`) VALUES
(4, '2019-10-20 13:46:49', '2019-10-20 13:46:49', 2, 1),
(5, '2019-10-20 13:46:49', '2019-10-20 13:46:49', 3, 1),
(26, '2019-10-20 13:52:51', '2019-10-20 13:53:20', 3, 25),
(27, '2019-10-20 13:52:51', '2019-10-20 13:53:20', 3, 25),
(36, '2019-10-20 13:52:51', '2019-10-20 13:54:05', 3, 25),
(37, '2019-10-20 13:52:51', '2019-10-20 13:54:05', 3, 25),
(46, '2019-10-20 13:52:51', '2019-10-20 13:59:45', 3, 25),
(47, '2019-10-20 13:52:51', '2019-10-20 13:59:45', 3, 25),
(56, '2019-10-20 14:49:03', '2019-10-20 14:50:23', 2, 25),
(65, '2019-10-20 14:57:41', '2019-10-20 15:03:28', 3, 25);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `alerts`
--
ALTER TABLE `alerts`
  ADD PRIMARY KEY (`alert_id`),
  ADD KEY `FKk30vk72sm5ikrs3eyijscu581` (`alert_type_id`);

--
-- Indeksy dla tabeli `alert_types`
--
ALTER TABLE `alert_types`
  ADD PRIMARY KEY (`alert_type_id`);

--
-- Indeksy dla tabeli `configuration`
--
ALTER TABLE `configuration`
  ADD PRIMARY KEY (`configuration_id`),
  ADD KEY `FKrso2qqxwq1nvex7ujsyxdu945` (`configuration_type_id`),
  ADD KEY `FKgy0l4n96rw2ley21h02bsp27d` (`user_id`);

--
-- Indeksy dla tabeli `configuration_type`
--
ALTER TABLE `configuration_type`
  ADD PRIMARY KEY (`configuration_type_id`);

--
-- Indeksy dla tabeli `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`device_id`);

--
-- Indeksy dla tabeli `gpsdata`
--
ALTER TABLE `gpsdata`
  ADD PRIMARY KEY (`gps_data_id`),
  ADD KEY `FKjbua8bm8piagfxmui1j3bpy1p` (`workout_id`);

--
-- Indeksy dla tabeli `measurements`
--
ALTER TABLE `measurements`
  ADD PRIMARY KEY (`measurement_id`),
  ADD KEY `FKqivkm1hqew9u8gp0lxcab62do` (`measurement_type_id`),
  ADD KEY `FKdphpmn0wvrjrr7snon4o08r1u` (`workout`);

--
-- Indeksy dla tabeli `measurement_types`
--
ALTER TABLE `measurement_types`
  ADD PRIMARY KEY (`measurement_type`);

--
-- Indeksy dla tabeli `physical_properties`
--
ALTER TABLE `physical_properties`
  ADD PRIMARY KEY (`physical_property_id`),
  ADD KEY `FKkw1xbx5ns69ls09a1144gjm4j` (`user_user_id`);

--
-- Indeksy dla tabeli `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`setting_id`),
  ADD KEY `FKo4ke5o52eci58foyh2jws0bi2` (`user`);

--
-- Indeksy dla tabeli `sports`
--
ALTER TABLE `sports`
  ADD PRIMARY KEY (`sport_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indeksy dla tabeli `workouts`
--
ALTER TABLE `workouts`
  ADD PRIMARY KEY (`workout_id`),
  ADD KEY `FKclf88bguqi4ash9aca5wgvk2x` (`sport`),
  ADD KEY `FKf296pc10h077b9ohoafccetx4` (`user`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `alerts`
--
ALTER TABLE `alerts`
  ADD CONSTRAINT `FKk30vk72sm5ikrs3eyijscu581` FOREIGN KEY (`alert_type_id`) REFERENCES `alert_types` (`alert_type_id`);

--
-- Ograniczenia dla tabeli `configuration`
--
ALTER TABLE `configuration`
  ADD CONSTRAINT `FKgy0l4n96rw2ley21h02bsp27d` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKrso2qqxwq1nvex7ujsyxdu945` FOREIGN KEY (`configuration_type_id`) REFERENCES `configuration_type` (`configuration_type_id`);

--
-- Ograniczenia dla tabeli `gpsdata`
--
ALTER TABLE `gpsdata`
  ADD CONSTRAINT `FKjbua8bm8piagfxmui1j3bpy1p` FOREIGN KEY (`workout_id`) REFERENCES `workouts` (`workout_id`);

--
-- Ograniczenia dla tabeli `measurements`
--
ALTER TABLE `measurements`
  ADD CONSTRAINT `FKdphpmn0wvrjrr7snon4o08r1u` FOREIGN KEY (`workout`) REFERENCES `workouts` (`workout_id`),
  ADD CONSTRAINT `FKqivkm1hqew9u8gp0lxcab62do` FOREIGN KEY (`measurement_type_id`) REFERENCES `measurement_types` (`measurement_type`);

--
-- Ograniczenia dla tabeli `physical_properties`
--
ALTER TABLE `physical_properties`
  ADD CONSTRAINT `FKkw1xbx5ns69ls09a1144gjm4j` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `settings`
--
ALTER TABLE `settings`
  ADD CONSTRAINT `FKo4ke5o52eci58foyh2jws0bi2` FOREIGN KEY (`user`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `workouts`
--
ALTER TABLE `workouts`
  ADD CONSTRAINT `FKclf88bguqi4ash9aca5wgvk2x` FOREIGN KEY (`sport`) REFERENCES `sports` (`sport_id`),
  ADD CONSTRAINT `FKf296pc10h077b9ohoafccetx4` FOREIGN KEY (`user`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
