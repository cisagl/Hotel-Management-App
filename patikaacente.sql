-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 09, 2023 at 10:50 AM
-- Server version: 8.0.31
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `patikaacente`
--

-- --------------------------------------------------------

--
-- Table structure for table `facility`
--

CREATE TABLE `facility` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `facility`
--

INSERT INTO `facility` (`id`, `hotel_id`, `features`) VALUES
(46, 1, 'Ücretsiz Otopark'),
(47, 1, 'Hotel Concierge'),
(48, 1, 'SPA'),
(49, 1, 'Ücretsiz Otopark'),
(50, 1, 'Ücretsiz WiFi'),
(51, 1, 'Fitness Center'),
(52, 1, 'Yüzme Havuzu'),
(53, 1, 'Hotel Concierge'),
(54, 1, 'SPA'),
(55, 1, '7/24 Oda Servisi'),
(56, 3, 'Ücretsiz Otopark'),
(57, 3, 'Hotel Concierge'),
(58, 5, 'SPA'),
(59, 6, 'Fitness Center'),
(60, 6, 'Yüzme Havuzu'),
(61, 7, 'Ücretsiz Otopark'),
(62, 7, 'Ücretsiz WiFi'),
(63, 7, 'Yüzme Havuzu'),
(64, 7, '7/24 Oda Servisi'),
(65, 8, 'Ücretsiz Otopark'),
(66, 8, 'Ücretsiz WiFi'),
(67, 8, 'Hotel Concierge'),
(68, 9, 'Ücretsiz Otopark'),
(69, 9, 'Ücretsiz WiFi'),
(70, 9, 'Yüzme Havuzu');

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `district` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `city`, `district`, `address`, `email`, `phone`, `star`) VALUES
(1, 'Cheltikov Hotel', 'Kars', 'Merkez', 'Yusufpaşa, Şht. Hulusi Aytekin Cd. No:63, 36000', 'hotel@cheltikov.com', '0474 212 00 36', '3'),
(2, 'Ramada Hotel', 'İstanbul', 'Güngören', 'Güven, Adnan Kahveci Blv. No:83, 34160', 'hotel@ramada.com', '800 4488 21423', '5'),
(3, 'The Green Park Hotel', 'İstanbul', 'Güngören', 'Abdurrahman Nafiz Gürman, Nazım Erten Sk. No:13, 34173', 'hotel@greenpark.com', '0212 507 73 73', '3'),
(4, 'Peracity Hotel', 'Ankara', 'Altındağ', 'Anafartalar, Rüzgarlı Cd. No:10, 06050', 'hotel@peracity.com', '0312 312 12 69', '4'),
(5, 'Privado Hotel', 'Antalya', 'Muratpaşa', 'Balbey Mh. Fahrettin Altay Cd, 441. Sk. No:2/201, 07040', 'hotel@privado.com', '0242 244 97 97 ', '2'),
(6, 'Liya Boutique Hotel', 'Muğla', 'Ula', 'Akyaka, Nail Çakırhan Sk. No:15, 48650', 'hotel@liya.com', '0546 244 39 42', '5'),
(7, 'Yay Grand Hotel', 'Mardin', 'Artuklu', 'Diyarbakır Yolu 1. Km. Yay Grand Otel Merkez, 47100', 'hotel@yaygrand.com', '0482 212 57 77', '4'),
(8, 'Grand Rio Hotel', 'Trabzon', 'Ortahisar', 'Sanayi, Mevkii, Degirmendere, Carşı İci Sk No.5, 61000', 'hotel@grandrio.com', '0530 082 72 00', '3'),
(9, 'Holiday Inn Express', 'Manisa', 'Yunusemre', 'Keçili Köy, 5608 Sk No:5, 45010', 'hotel@holidayinn.com', '0236 302 40 00', '4'),
(10, 'Villa Da Butik & Hotel', 'Artvin', 'Merkez', 'Çayağzı, Kazım Karabekir Cd. No:101, 08000', 'hotel@villadabutik.com', '0466 212 16 00', '4');

-- --------------------------------------------------------

--
-- Table structure for table `pension`
--

CREATE TABLE `pension` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pension`
--

INSERT INTO `pension` (`id`, `hotel_id`, `features`) VALUES
(1, 1, 'Ultra Herşey Dahil'),
(2, 1, 'Herşey Dahil'),
(3, 1, 'Oda Kahvaltı'),
(4, 1, 'Tam Pansiyon'),
(5, 1, 'Yarım Pansiyon'),
(6, 1, 'Sadece Yatak'),
(7, 1, 'Alkol Hariç Full credit'),
(46, 2, 'Oda Kahvaltı'),
(47, 2, 'Tam Pansiyon'),
(48, 2, 'Alkol Hariç Full credit'),
(49, 3, 'Herşey Dahil'),
(50, 3, 'Oda Kahvaltı'),
(51, 3, 'Tam Pansiyon'),
(52, 3, 'Alkol Hariç Full credit'),
(53, 4, 'Tam Pansiyon'),
(54, 4, 'Alkol Hariç Full credit'),
(55, 5, 'Herşey Dahil'),
(56, 6, 'Ultra Herşey Dahil'),
(57, 6, 'Herşey Dahil'),
(58, 7, 'Ultra Herşey Dahil'),
(59, 7, 'Herşey Dahil'),
(60, 8, 'Yarım Pansiyon'),
(61, 8, 'Sadece Yatak'),
(62, 9, 'Herşey Dahil'),
(63, 9, 'Oda Kahvaltı'),
(64, 9, 'Sadece Yatak'),
(65, 10, 'Herşey Dahil'),
(66, 10, 'Oda Kahvaltı'),
(67, 10, 'Sadece Yatak'),
(68, 10, 'Alkol Hariç Full credit'),
(87, 10, 'Herşey Dahil'),
(88, 10, 'Oda Kahvaltı'),
(89, 10, 'Tam Pansiyon'),
(90, 10, 'Sadece Yatak');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int NOT NULL,
  `customer_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_idcard_no` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_reservation_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `howmanydays` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `customer_name`, `customer_phone`, `customer_email`, `customer_idcard_no`, `customer_reservation_note`, `howmanydays`, `room_id`) VALUES
(10, 'Cahit İsaoğlu', '+905554443322', 'mail@cahitisaoglu.com', '11111111111', 'Odamda bir şişe su bulunsun', '3', 95),
(14, 'Ali Veli', '+905553332211', 'mail@aliveli.com', '22222222222', 'Rezervasyon notu yok', '2', 97),
(15, 'Ahmet Mehmet', '+905552221100', 'mail@ahmetmehmet.com', '33333333333', 'Rezervasyon notu yok', '4', 97),
(16, 'Patika Dev', '+905559998877', 'mail@patikadev.com', '44444444444', 'Oda servisi istemiyorum', '6', 100),
(17, 'Falan Filan', '+905558887766', 'mail@falanfilan.com', '55555555555', 'Falan filan', '7', 100);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL,
  `season_id` int NOT NULL,
  `pension_id` int NOT NULL,
  `stock` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `name`, `hotel_id`, `season_id`, `pension_id`, `stock`, `adult_price`, `child_price`) VALUES
(95, 'Tek Kişilik', 1, 1, 2, 29, 300, 150),
(96, 'Suit', 6, 12, 56, 28, 1500, 750),
(97, 'Junior Suit', 5, 10, 55, 28, 600, 300),
(100, 'Engelli', 3, 6, 50, 25, 200, 100),
(101, 'Kral', 9, 17, 62, 29, 1000, 500),
(105, 'sada', 8, 16, 60, 232, 23, 23);

-- --------------------------------------------------------

--
-- Table structure for table `roomfeatures`
--

CREATE TABLE `roomfeatures` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `features` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roomfeatures`
--

INSERT INTO `roomfeatures` (`id`, `room_id`, `features`) VALUES
(63, 95, 'Oyun Konsolu'),
(64, 95, 'Projeksiyon'),
(65, 95, 'Klima'),
(66, 95, '4'),
(67, 96, 'Televizyon'),
(68, 96, 'Kasa'),
(69, 96, '3');

-- --------------------------------------------------------

--
-- Table structure for table `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL,
  `start_date` date NOT NULL,
  `finish_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `season`
--

INSERT INTO `season` (`id`, `name`, `hotel_id`, `start_date`, `finish_date`) VALUES
(1, 'Kış Sezonu', 1, '2023-01-01', '2023-05-31'),
(2, 'Yaz Sezonu', 1, '2023-06-01', '2023-12-31'),
(3, 'Kış Sezonu', 2, '2023-01-01', '2023-05-31'),
(4, 'Yaz Sezonu', 2, '2023-06-01', '2023-12-31'),
(5, 'Kış Sezonu', 3, '2023-01-01', '2023-05-31'),
(6, 'Yaz Sezonu', 3, '2023-06-01', '2023-12-31'),
(7, 'Kış Sezonu', 4, '2023-01-01', '2023-05-31'),
(8, 'Yaz Sezonu', 4, '2023-06-01', '2023-12-31'),
(9, 'Kış Sezonu', 5, '2023-01-01', '2023-05-31'),
(10, 'Yaz Sezonu', 5, '2023-06-01', '2023-12-31'),
(11, 'Kış Sezonu', 6, '2023-01-01', '2023-05-31'),
(12, 'Yaz Sezonu', 6, '2023-06-01', '2023-12-31'),
(13, 'Kış Sezonu', 7, '2023-01-01', '2023-05-31'),
(14, 'Yaz Sezonu', 7, '2023-06-01', '2023-12-31'),
(15, 'Kış Sezonu', 8, '2023-01-01', '2023-05-31'),
(16, 'Yaz Sezonu', 8, '2023-06-01', '2023-12-31'),
(17, 'Kış Sezonu', 9, '2023-01-01', '2023-05-31'),
(18, 'Yaz Sezonu', 9, '2023-06-01', '2023-12-31'),
(19, 'Kış Sezonu', 10, '2023-01-01', '2023-05-31'),
(20, 'Yaz Sezonu', 10, '2023-06-01', '2023-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','worker') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Hotel''s users';

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'admin', 'a', '1', 'admin'),
(2, 'worker', 'w', '1', 'worker');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facility`
--
ALTER TABLE `facility`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pension`
--
ALTER TABLE `pension`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roomfeatures`
--
ALTER TABLE `roomfeatures`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `facility`
--
ALTER TABLE `facility`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `pension`
--
ALTER TABLE `pension`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `roomfeatures`
--
ALTER TABLE `roomfeatures`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
