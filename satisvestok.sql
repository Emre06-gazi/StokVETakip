-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 29 Kas 2023, 15:11:44
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `satisvestok`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `accounts`
--

CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL,
  `YetkiId` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL,
  `Sifre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `ParentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteri`
--

CREATE TABLE `musteri` (
  `Id` int(11) NOT NULL,
  `AdiSoyadi` varchar(255) DEFAULT NULL,
  `Telefon` varchar(255) DEFAULT NULL,
  `Adres` varchar(255) DEFAULT NULL,
  `Tel` varchar(255) DEFAULT NULL,
  `SehirId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `satis`
--

CREATE TABLE `satis` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) DEFAULT NULL,
  `MusteriId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Adet` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `stok`
--

CREATE TABLE `stok` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Adet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `KategoriId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Fiyat` decimal(10,0) DEFAULT NULL,
  `SehirId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetkiler`
--

CREATE TABLE `yetkiler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `musteri`
--
ALTER TABLE `musteri`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `satis`
--
ALTER TABLE `satis`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `yetkiler`
--
ALTER TABLE `yetkiler`
  ADD PRIMARY KEY (`Id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `accounts`
--
ALTER TABLE `accounts`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `musteri`
--
ALTER TABLE `musteri`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `satis`
--
ALTER TABLE `satis`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `stok`
--
ALTER TABLE `stok`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `yetkiler`
--
ALTER TABLE `yetkiler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
