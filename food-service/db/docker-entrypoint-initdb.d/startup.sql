/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*
    Creating this table is optional.

    However, I am creating this table so that I can insert a lot of dummy data easily.
*/

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;

INSERT INTO `food` (`id`, `name`, `price`, `description`, `image_url`, `restaurant_id`)
VALUES
	(1, "Biryani", 5.00, "Indian Cuisine best for hot weather.", "https://images.app.goo.gl/qzQRF7QSsj12S4CX8", 1),
	(2, "Mo:Mo", 3.50, "Nepali Cuisine best for cold weather.", "https://images.app.goo.gl/qzQRF7QSsj12S4CX8", 2);

/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;
