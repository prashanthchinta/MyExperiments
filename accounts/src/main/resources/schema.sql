CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` int AUTO_INCREMENT  PRIMARY KEY,
  `account_number` varchar(100) NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `street` varchar(100) NOT NULL,
  `pincode` int NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);