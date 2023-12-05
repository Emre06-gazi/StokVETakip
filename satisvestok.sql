-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 05 Ara 2023, 07:24:20
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

--
-- Tablo döküm verisi `accounts`
--

INSERT INTO `accounts` (`Id`, `YetkiId`, `PersonelId`, `Sifre`) VALUES
(1, 1, 1, '123'),
(2, 2, 2, '123');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `ParentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`Id`, `Adi`, `ParentId`) VALUES
(1, 'Dizüstü Bilgisayar', 0),
(2, 'Masaüstü Bilgisayar', 0),
(3, 'Telefon', 0),
(4, 'Televizyon', 0),
(5, 'Erkek Giyim', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteri`
--

CREATE TABLE `musteri` (
  `Id` int(11) NOT NULL,
  `AdiSoyadi` varchar(255) DEFAULT NULL,
  `Telefon` varchar(255) DEFAULT NULL,
  `Adres` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `musteri`
--

INSERT INTO `musteri` (`Id`, `AdiSoyadi`, `Telefon`, `Adres`) VALUES
(1, 'Halil Yılmaz', '0312 250 6255', 'Ankara/Sincan Pınarbaşı Mahallesi Vatan Caddesi 110/5'),
(2, 'Sude Günok', '0312 250 6258', 'Ankara/Sincan Andiçen Mahallesi Polatlı Caddesi 120/15');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `id` int(11) NOT NULL,
  `AdiSoyadi` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`id`, `AdiSoyadi`, `Email`) VALUES
(1, 'Emre Korkmaz', 'emre_0606@gmail.com'),
(2, 'Ahmet Esen', 'ahmet@gmail.com');

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

--
-- Tablo döküm verisi `satis`
--

INSERT INTO `satis` (`Id`, `UrunId`, `MusteriId`, `Tarih`, `Adet`, `PersonelId`) VALUES
(1, 1, 1, '2023-12-08', 1, 1),
(2, 3, 2, '2023-12-08', 2, 1),
(3, 6, 1, '2023-12-15', 3, 1);

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

--
-- Tablo döküm verisi `stok`
--

INSERT INTO `stok` (`Id`, `UrunId`, `PersonelId`, `Tarih`, `Adet`) VALUES
(1, 1, 1, '2023-12-08', -1),
(2, 2, 1, '2023-12-03', 8),
(3, 3, 1, '2023-12-08', -2),
(4, 4, 1, '2023-12-03', 60),
(5, 5, 1, '2023-12-03', 12),
(6, 6, 1, '2023-12-15', -3),
(7, 1, 1, '2023-12-03', 14),
(8, 3, 1, '2023-12-01', 24),
(9, 6, 1, '2023-12-01', 10);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `KategoriId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Fiyat` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`Id`, `Adi`, `KategoriId`, `Tarih`, `Fiyat`) VALUES
(1, 'Asus GTFX-8555 Pc', 0, '2023-12-02', 65000),
(2, 'Casper A-155 Pc', 0, '2023-12-02', 95000),
(3, 'Samsung S23 Ultra', 0, '2023-12-03', 56000),
(4, 'İphone 15 Pro Max 256 Gb', 0, '2023-12-03', 86000),
(5, 'Vestel 180\" Tv', 0, '2023-12-03', 17899),
(6, 'Erkek Pantolon XL', 0, '2023-12-03', 450);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetkiler`
--

CREATE TABLE `yetkiler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `yetkiler`
--

INSERT INTO `yetkiler` (`Id`, `Adi`) VALUES
(1, 'Admin'),
(2, 'Personel'),
(3, 'Müşteri');

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
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `musteri`
--
ALTER TABLE `musteri`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `satis`
--
ALTER TABLE `satis`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `stok`
--
ALTER TABLE `stok`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `yetkiler`
--
ALTER TABLE `yetkiler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
