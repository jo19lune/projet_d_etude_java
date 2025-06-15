-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 26 mai 2025 à 16:00
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_vente_moto_scooter`
--
CREATE DATABASE IF NOT EXISTS `gestion_vente_moto_scooter` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `gestion_vente_moto_scooter`;

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `id_vente` int NOT NULL AUTO_INCREMENT,
  `date_vente` date NOT NULL,
  `id_client` int NOT NULL,
  `id_employe` int NOT NULL,
  `id_vehicule` int NOT NULL,
  `id_facture` int DEFAULT NULL,
  `quantite` int NOT NULL,
  `prix_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_vente`),
  KEY `fk_client` (`id_client`),
  KEY `fk_employe` (`id_employe`),
  KEY `fk_vehicule` (`id_vehicule`),
  KEY `idx_achat_client` (`id_client`),
  KEY `idx_achat_employe` (`id_employe`),
  KEY `idx_achat_vehicule` (`id_vehicule`)
) ENGINE=MyISAM AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`id_vente`, `date_vente`, `id_client`, `id_employe`, `id_vehicule`, `id_facture`, `quantite`, `prix_total`) VALUES
(1, '2025-04-17', 1, 1, 1, 1, 1, 150000.00),
(2, '2025-04-18', 1, 2, 2, 2, 1, 85000.00),
(3, '2025-04-19', 1, 3, 3, 3, 1, 120000.00),
(4, '2025-04-20', 1, 4, 4, 4, 1, 60000.00),
(5, '2025-04-21', 2, 3, 5, 5, 1, 95000.00),
(6, '2025-04-22', 2, 4, 6, 6, 2, 120000.00),
(15, '2025-04-19', 1, 1, 1, 0, 1, 50000.00),
(16, '2025-04-19', 1, 1, 1, 0, 1, 5000.00),
(17, '2025-04-19', 1, 1, 1, 15, 1, 5000.00),
(18, '2025-04-19', 1, 1, 1, 15, 2, 10000.00),
(19, '2025-04-19', 1, 1, 6, 15, 1, 1000.00),
(20, '2025-04-19', 1, 1, 10, 15, 1, 2500.00),
(21, '2025-04-19', 1, 1, 1, 16, 2, 10000.00),
(22, '2025-04-19', 1, 1, 3, 16, 1, 5000.00),
(23, '2025-04-21', 1, 1, 1, 17, 1, 5000.00),
(24, '2025-04-21', 1, 1, 2, 17, 2, 20000.00),
(25, '2025-04-21', 1, 1, 1, 18, 1, 5000.00),
(26, '2025-04-21', 1, 1, 5, 18, 1, 5000.00),
(27, '2025-04-21', 1, 1, 1, 19, 1, 5000.00),
(28, '2025-04-21', 1, 1, 2, 19, 1, 10000.00),
(29, '2025-04-21', 1, 1, 1, 20, 1, 5000.00),
(30, '2025-04-21', 1, 1, 3, 20, 1, 5000.00),
(31, '2025-04-21', 7, 2, 18, 21, 1, 1000.00),
(32, '2025-04-21', 7, 2, 6, 21, 2, 2000.00),
(33, '2025-04-21', 1, 1, 1, 22, 1, 5000.00),
(34, '2025-04-22', 1, 1, 13, 23, 1, 10000.00),
(35, '2025-04-22', 1, 1, 11, 23, 1, 10000.00),
(36, '2025-04-22', 8, 2, 11, 24, 2, 20000.00),
(37, '2025-04-22', 8, 2, 3, 24, 1, 5000.00),
(45, '2025-04-30', 2, 1, 1, 26, 1, 5000.00),
(46, '2025-05-01', 1, 1, 1, 101, 1, 150000.00),
(141, '2024-02-18', 4, 2, 15, 246, 3, 18900.00),
(140, '2024-02-15', 6, 5, 21, 245, 1, 9200.00),
(139, '2024-02-12', 7, 4, 18, 244, 2, 13400.00),
(138, '2024-02-08', 2, 1, 10, 243, 1, 5800.00),
(137, '2024-02-05', 9, 2, 14, 242, 2, 15600.00),
(136, '2024-02-01', 5, 3, 8, 241, 1, 7500.00),
(135, '2024-12-31', 4, 5, 11, 240, 2, 9900.00),
(134, '2024-12-28', 10, 4, 9, 239, 1, 6800.00),
(133, '2024-12-26', 1, 3, 5, 238, 2, 12000.00),
(132, '2024-12-24', 7, 1, 12, 237, 1, 8500.00),
(131, '2024-12-20', 6, 2, 15, 236, 3, 18000.00),
(130, '2024-12-17', 2, 5, 21, 235, 1, 9200.00),
(129, '2024-12-14', 8, 3, 18, 234, 2, 13200.00),
(128, '2024-12-10', 5, 4, 10, 233, 1, 6400.00),
(127, '2024-12-06', 9, 1, 14, 232, 2, 15500.00),
(126, '2024-12-02', 3, 2, 7, 231, 1, 7500.00),
(125, '2024-06-30', 1, 5, 11, 230, 2, 8900.00),
(124, '2024-06-27', 5, 4, 6, 229, 1, 5400.00),
(123, '2024-06-25', 3, 2, 17, 228, 1, 3800.00),
(161, '2024-01-17', 6, 2, 15, 266, 3, 17500.00),
(101, '2024-06-18', 4, 3, 15, 206, 2, 15000.00),
(100, '2024-05-10', 9, 4, 10, 205, 1, 4500.00),
(118, '2024-06-10', 4, 4, 9, 223, 1, 4900.00),
(117, '2024-06-05', 7, 1, 12, 222, 2, 14000.00),
(116, '2024-06-01', 2, 3, 5, 221, 1, 6700.00),
(115, '2024-08-23', 1, 4, 18, 220, 1, 7200.00),
(114, '2024-07-09', 10, 5, 11, 219, 1, 6800.00),
(113, '2024-06-15', 9, 3, 6, 218, 2, 8000.00),
(112, '2024-05-29', 4, 1, 17, 217, 1, 3400.00),
(122, '2024-06-22', 10, 1, 14, 227, 2, 15000.00),
(121, '2024-06-20', 9, 3, 7, 226, 1, 7200.00),
(78, '2024-07-25', 3, 1, 5, 133, 1, 7500.00),
(160, '2024-01-14', 4, 5, 21, 265, 1, 8500.00),
(98, '2024-03-08', 7, 1, 12, 203, 1, 8000.00),
(159, '2024-01-11', 9, 3, 18, 264, 2, 11900.00),
(110, '2024-03-11', 8, 3, 21, 215, 1, 5600.00),
(109, '2024-02-28', 6, 4, 16, 214, 3, 18000.00),
(158, '2024-01-08', 2, 2, 10, 263, 1, 5300.00),
(107, '2024-12-21', 7, 2, 5, 212, 2, 14000.00),
(120, '2024-06-18', 8, 5, 21, 225, 2, 12000.00),
(119, '2024-06-15', 6, 2, 16, 224, 3, 17500.00),
(88, '2024-10-10', 6, 1, 12, 143, 2, 20000.00),
(97, '2024-02-20', 5, 3, 9, 202, 2, 12000.00),
(157, '2024-01-05', 7, 4, 14, 262, 2, 12500.00),
(106, '2024-11-03', 3, 5, 8, 211, 1, 7200.00),
(105, '2024-10-12', 10, 3, 11, 210, 2, 9500.00),
(156, '2024-01-02', 3, 1, 5, 261, 1, 6800.00),
(103, '2024-08-22', 8, 2, 14, 208, 1, 7200.00),
(102, '2024-07-30', 6, 1, 7, 207, 1, 6000.00),
(142, '2024-02-22', 8, 3, 12, 247, 1, 8500.00),
(143, '2024-02-25', 1, 4, 5, 248, 2, 11000.00),
(144, '2024-02-27', 10, 5, 9, 249, 1, 6800.00),
(145, '2024-02-29', 3, 1, 11, 250, 2, 9900.00),
(146, '2024-02-03', 4, 1, 6, 251, 1, 7200.00),
(147, '2024-02-07', 8, 5, 13, 252, 2, 14200.00),
(148, '2024-02-11', 1, 2, 17, 253, 1, 5800.00),
(149, '2024-02-14', 10, 3, 20, 254, 2, 12800.00),
(150, '2024-02-17', 3, 4, 9, 255, 1, 8600.00),
(151, '2024-02-20', 6, 1, 15, 256, 3, 19200.00),
(152, '2024-02-24', 7, 2, 12, 257, 1, 8300.00),
(153, '2024-02-26', 9, 3, 5, 258, 2, 11500.00),
(154, '2024-02-28', 2, 4, 11, 259, 1, 6900.00),
(155, '2024-02-29', 5, 5, 18, 260, 2, 9700.00),
(162, '2024-01-22', 8, 1, 12, 267, 1, 7700.00),
(163, '2024-01-25', 5, 3, 9, 268, 2, 11200.00),
(164, '2024-01-27', 10, 4, 6, 269, 1, 6500.00),
(165, '2024-01-30', 1, 5, 11, 270, 2, 9400.00),
(166, '2024-08-02', 3, 1, 5, 271, 1, 6800.00),
(167, '2024-08-05', 7, 4, 14, 272, 2, 12500.00),
(168, '2024-08-08', 2, 2, 10, 273, 1, 5300.00),
(169, '2024-08-11', 9, 3, 18, 274, 2, 11900.00),
(170, '2024-08-14', 4, 5, 21, 275, 1, 8500.00),
(171, '2024-08-17', 6, 2, 15, 276, 3, 17500.00),
(172, '2024-08-22', 8, 1, 12, 277, 1, 7700.00),
(173, '2024-08-25', 5, 3, 9, 278, 2, 11200.00),
(174, '2024-08-27', 10, 4, 6, 279, 1, 6500.00),
(175, '2024-08-30', 1, 5, 11, 280, 2, 9400.00),
(180, '2025-05-04', 2, 1, 19, 283, 2, 5000.00),
(181, '2025-05-04', 2, 1, 13, 284, 1, 10000.00);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(50) NOT NULL,
  `prenom_client` varchar(50) NOT NULL,
  `cin_client` varchar(12) NOT NULL,
  `adresse` varchar(200) DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `nom_client`, `prenom_client`, `cin_client`, `adresse`, `telephone`, `email`) VALUES
(2, 'Randrianasolo', 'Jean', '123456789012', 'Antananarivo', '0341234567', 'jean@example.com'),
(3, 'Ravelo', 'Marie', '987654321012', 'Fianarantsoa', '0327654321', 'marie@example.com'),
(4, 'Ratsimba', 'Paul', '654321987654', 'Toliara', '0339876543', 'paul@example.com'),
(5, 'Andriana', 'Claire', '789012345678', 'Mahajanga', '0340987654', 'claire@example.com'),
(6, 'Razafy', 'Luc', '321654987321', 'Antsiranana', '0324567890', 'luc@example.com'),
(7, 'Raveloson', 'Laura', '987321654987', 'Toamasina', '0345678901', 'laura@example.com'),
(8, 'Rakoto', 'Simon', '654987123654', 'Ambalavao', '0331234568', 'simon@example.com'),
(9, 'Randria', 'Alice', '789654321987', 'Fianarantsoa', '0348765432', 'alice@example.com'),
(10, 'Rasolonirina', 'Kevin', '123789456012', 'Antananarivo', '0323456789', 'kevin@example.com'),
(11, 'Rabemananjara', 'Sofia', '321987654321', 'Antsirabe', '0337654321', 'sofia@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id_employe` int NOT NULL AUTO_INCREMENT,
  `nom_employe` varchar(50) NOT NULL,
  `prenom_employe` varchar(50) NOT NULL,
  `telephone_employe` varchar(10) DEFAULT NULL,
  `email_employe` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(100) DEFAULT NULL,
  `poste_employe` varchar(50) NOT NULL,
  `salaire` decimal(10,2) DEFAULT NULL,
  `date_embauche` date NOT NULL,
  `cin_employe` varchar(12) DEFAULT NULL,
  `adresse_employe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_employe`),
  UNIQUE KEY `email_employe` (`email_employe`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id_employe`, `nom_employe`, `prenom_employe`, `telephone_employe`, `email_employe`, `mot_de_passe`, `poste_employe`, `salaire`, `date_embauche`, `cin_employe`, `adresse_employe`) VALUES
(1, 'Andrianarisoa', 'Jean', '0341234567', 'jean.admin@example.com', 'jPasswordField1', 'Admin', 800000.00, '2023-01-15', '123456789012', 'Lot IVB 123, Antananarivo'),
(2, 'Randrianasolo', 'Marie', '0327654321', 'marie.manager@example.com', 'password456', 'Manager', 750000.00, '2024-10-01', '987654321098', 'Rue des Roses, Antsirabe'),
(3, 'Ratsimbazafy', 'Paul', '0339876543', 'paul.vendeur@example.com', 'password789', 'Vendeur', 500000.00, '2023-11-20', '456789123456', 'Village Fandriana, Fianarantsoa'),
(4, 'Raveloson', 'Laura', '0345678901', 'laura.admin@example.com', 'gfghfh', 'Admin', 850000.00, '2023-01-10', '321654987321', 'Quartier Mahamasina, Antananarivo'),
(5, 'Rakotoarivelo', 'Simon', '0331234568', 'simon.manager@example.com', 'password456', 'Manager', 770000.00, '2024-12-05', '654987321654', 'Avenue de la Libération, Toamasina'),
(6, 'Razafindramboa', 'Alice', '0348765432', 'alice.vendeur@example.com', 'password789', 'Vendeur', 480000.00, '2023-05-17', '789456123789', 'Centre-ville, Diego-Suarez'),
(7, 'Rasolonirina', 'Kevin', '0323456789', 'kevin.admin@example.com', '1212', 'Admin', 820000.00, '2023-01-25', '147258369147', 'Rue du Marché, Toliara'),
(8, 'Rabemananjara', 'Sofia', '0337654321', 'sofia.manager@example.com', 'password456', 'Manager', 760000.00, '2024-08-18', '258369147258', 'Rue des Palmiers, Mahajanga'),
(9, 'Rakotobe', 'Hervé', '0346543210', 'herve.vendeur@example.com', 'password789', 'Vendeur', 490000.00, '2023-06-22', '369147258369', 'Quartier Ambodivona, Antananarivo'),
(10, 'Andriamihaja', 'Sarah', '0331239876', 'sarah.manager@example.com', 'password456', 'Manager', 780000.00, '2024-09-30', '159357258159', 'Lot 45, Ambalavao'),
(14, 'Loïck', 'Joachim', NULL, 'loick@manager.com', 'manager', 'Manager', 60000.00, '2025-05-04', NULL, NULL),
(15, 'Loïck', 'Joachim', NULL, 'loick@admin.com', 'admin', 'Admin', 60000.00, '2025-05-04', NULL, NULL),
(16, 'Loïck', 'Joachim', NULL, 'loick@vendeur.com', 'vendeur', 'Vendeur', 60000.00, '2025-05-04', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id_facture` int NOT NULL AUTO_INCREMENT,
  `id_client` int NOT NULL,
  `id_employe` int NOT NULL,
  `date_facturation` date NOT NULL,
  `mode_paiement` varchar(50) NOT NULL,
  `montant_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_facture`),
  KEY `id_client` (`id_client`),
  KEY `id_employe` (`id_employe`)
) ENGINE=MyISAM AUTO_INCREMENT=285 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id_facture`, `id_client`, `id_employe`, `date_facturation`, `mode_paiement`, `montant_total`) VALUES
(5, 2, 3, '2025-04-21', 'ESPECES', 95000.00),
(6, 2, 4, '2025-04-22', 'ESPECES', 120000.00),
(23, 8, 2, '2025-04-22', 'ESPECES', 25000.00),
(101, 1, 1, '2025-05-01', 'ESPECES', 150000.00),
(240, 4, 5, '2024-12-31', 'BANCAIRE', 9900.00),
(239, 10, 4, '2024-12-28', 'ESPECES', 6800.00),
(238, 1, 3, '2024-12-26', 'BANCAIRE', 12000.00),
(237, 7, 1, '2024-12-24', 'ESPECES', 8500.00),
(236, 6, 2, '2024-12-20', 'BANCAIRE', 18000.00),
(235, 2, 5, '2024-12-17', 'ESPECES', 9200.00),
(234, 8, 3, '2024-12-14', 'BANCAIRE', 13200.00),
(233, 5, 4, '2024-12-10', 'ESPECES', 6400.00),
(232, 9, 1, '2024-12-06', 'BANCAIRE', 15500.00),
(231, 3, 2, '2024-12-02', 'ESPECES', 7500.00),
(230, 1, 5, '2024-06-30', 'BANCAIRE', 8900.00),
(229, 5, 4, '2024-06-27', 'ESPECES', 5400.00),
(228, 3, 2, '2024-06-25', 'BANCAIRE', 3800.00),
(227, 10, 1, '2024-06-22', 'ESPECES', 15000.00),
(226, 9, 3, '2024-06-20', 'BANCAIRE', 7200.00),
(225, 8, 5, '2024-06-18', 'ESPECES', 12000.00),
(224, 6, 2, '2024-06-15', 'BANCAIRE', 17500.00),
(223, 4, 4, '2024-06-10', 'ESPECES', 4900.00),
(222, 7, 1, '2024-06-05', 'BANCAIRE', 14000.00),
(266, 6, 2, '2024-01-17', 'BANCAIRE', 17500.00),
(206, 4, 3, '2024-06-18', 'ESPECES', 15000.00),
(205, 9, 4, '2024-05-10', 'BANCAIRE', 4500.00),
(219, 10, 5, '2024-07-09', 'ESPECES', 6800.00),
(218, 9, 3, '2024-06-15', 'BANCAIRE', 8000.00),
(217, 4, 1, '2024-05-29', 'ESPECES', 3400.00),
(265, 4, 5, '2024-01-14', 'ESPECES', 8500.00),
(215, 8, 3, '2024-03-11', 'ESPECES', 5600.00),
(221, 2, 3, '2024-06-01', 'ESPECES', 6700.00),
(133, 3, 1, '2024-07-25', 'BANCAIRE', 7500.00),
(264, 9, 3, '2024-01-11', 'BANCAIRE', 11900.00),
(203, 7, 1, '2024-03-08', 'BANCAIRE', 8000.00),
(214, 6, 4, '2024-02-28', 'BANCAIRE', 18000.00),
(263, 2, 2, '2024-01-08', 'ESPECES', 5300.00),
(212, 7, 2, '2024-12-21', 'BANCAIRE', 14000.00),
(211, 3, 5, '2024-11-03', 'ESPECES', 7200.00),
(220, 1, 4, '2024-08-23', 'BANCAIRE', 7200.00),
(143, 6, 1, '2024-10-10', 'ESPECES', 20000.00),
(202, 5, 3, '2024-02-20', 'ESPECES', 12000.00),
(262, 7, 4, '2024-01-05', 'BANCAIRE', 12500.00),
(210, 10, 3, '2024-10-12', 'ESPECES', 9500.00),
(261, 3, 1, '2024-01-02', 'ESPECES', 6800.00),
(208, 8, 2, '2024-08-22', 'ESPECES', 7200.00),
(207, 6, 1, '2024-07-30', 'BANCAIRE', 6000.00),
(241, 5, 3, '2024-02-01', 'ESPECES', 7500.00),
(242, 9, 2, '2024-02-05', 'BANCAIRE', 15600.00),
(243, 2, 1, '2024-02-08', 'ESPECES', 5800.00),
(244, 7, 4, '2024-02-12', 'BANCAIRE', 13400.00),
(245, 6, 5, '2024-02-15', 'ESPECES', 9200.00),
(246, 4, 2, '2024-02-18', 'BANCAIRE', 18900.00),
(247, 8, 3, '2024-02-22', 'ESPECES', 8500.00),
(248, 1, 4, '2024-02-25', 'BANCAIRE', 11000.00),
(249, 10, 5, '2024-02-27', 'ESPECES', 6800.00),
(250, 3, 1, '2024-02-29', 'BANCAIRE', 9900.00),
(251, 4, 1, '2024-02-03', 'ESPECES', 7200.00),
(252, 8, 5, '2024-02-07', 'BANCAIRE', 14200.00),
(253, 1, 2, '2024-02-11', 'ESPECES', 5800.00),
(254, 10, 3, '2024-02-14', 'BANCAIRE', 12800.00),
(255, 3, 4, '2024-02-17', 'ESPECES', 8600.00),
(256, 6, 1, '2024-02-20', 'BANCAIRE', 19200.00),
(257, 7, 2, '2024-02-24', 'ESPECES', 8300.00),
(258, 9, 3, '2024-02-26', 'BANCAIRE', 11500.00),
(259, 2, 4, '2024-02-28', 'ESPECES', 6900.00),
(260, 5, 5, '2024-02-29', 'BANCAIRE', 9700.00),
(267, 8, 1, '2024-01-22', 'ESPECES', 7700.00),
(268, 5, 3, '2024-01-25', 'BANCAIRE', 11200.00),
(269, 10, 4, '2024-01-27', 'ESPECES', 6500.00),
(270, 1, 5, '2024-01-30', 'BANCAIRE', 9400.00),
(271, 3, 1, '2024-08-02', 'ESPECES', 6800.00),
(272, 7, 4, '2024-08-05', 'BANCAIRE', 12500.00),
(273, 2, 2, '2024-08-08', 'ESPECES', 5300.00),
(274, 9, 3, '2024-08-11', 'BANCAIRE', 11900.00),
(275, 4, 5, '2024-08-14', 'ESPECES', 8500.00),
(276, 6, 2, '2024-08-17', 'BANCAIRE', 17500.00),
(277, 8, 1, '2024-08-22', 'ESPECES', 7700.00),
(278, 5, 3, '2024-08-25', 'BANCAIRE', 11200.00),
(279, 10, 4, '2024-08-27', 'ESPECES', 6500.00),
(280, 1, 5, '2024-08-30', 'BANCAIRE', 9400.00),
(283, 2, 1, '2025-05-04', 'ESPECES', 10000.00);

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `id_vehicule` int NOT NULL AUTO_INCREMENT,
  `marque` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `cyl` int NOT NULL,
  `moteur` varchar(50) NOT NULL,
  `categorie` varchar(50) NOT NULL,
  `stock` int DEFAULT NULL,
  `prix` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_vehicule`)
) ;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id_vehicule`, `marque`, `model`, `cyl`, `moteur`, `categorie`, `stock`, `prix`) VALUES
(1, 'Yamaha', 'R15', 155, 'Essence', 'Moto', 37, 5000.00),
(2, 'Honda', 'CBR500R', 500, 'Essence', 'Moto', 47, 10000.00),
(3, 'Suzuki', 'GSX250R', 250, 'Essence', 'Moto', 47, 5000.00),
(4, 'Kawasaki', 'Ninja400', 400, 'Essence', 'Moto', 50, 5000.00),
(5, 'BMW', 'G310R', 310, 'Essence', 'Moto', 49, 5000.00),
(6, 'Yamaha', 'XMAX 125', 125, 'Essence', 'Scooter', 47, 1000.00),
(7, 'Honda', 'PCX150', 150, 'Essence', 'Scooter', 50, 2500.00),
(8, 'Vespa', 'GTS300', 300, 'Essence', 'Scooter', 50, 2500.00),
(9, 'Kymco', 'Agility 50', 50, 'Essence', 'Scooter', 50, 1000.00),
(10, 'Piaggio', 'Liberty150', 150, 'Essence', 'Scooter', 49, 2500.00),
(11, 'Harley-Davidson', 'Iron 883', 883, 'Essence', 'Moto', 47, 10000.00),
(12, 'Triumph', 'Bonneville T100', 900, 'Essence', 'Moto', 50, 10000.00),
(13, 'Ducati', 'Monster 821', 821, 'Essence', 'Moto', 48, 10000.00),
(14, 'KTM', 'Duke 390', 373, 'Essence', 'Moto', 50, 5000.00),
(15, 'Royal Enfield', 'Classic 500', 499, 'Essence', 'Moto', 50, 10000.00),
(16, 'Aprilia', 'SR 160', 160, 'Essence', 'Scooter', 50, 2500.00),
(17, 'SYM', 'Cruisym 125', 125, 'Essence', 'Scooter', 50, 1000.00),
(18, 'Honda', 'Activa 6G', 110, 'Essence', 'Scooter', 49, 1000.00),
(19, 'Yamaha', 'Aerox 155', 155, 'Essence', 'Scooter', 48, 2500.00),
(20, 'TVS', 'NTorq 125', 125, 'Essence', 'Scooter', 50, 1000.00);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
