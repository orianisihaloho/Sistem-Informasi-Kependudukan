/*
SQLyog Community v11.24 (32 bit)
MySQL - 5.6.21 : Database - universitas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`universitas` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `universitas`;

/*Table structure for table `mahasiswa` */

DROP TABLE IF EXISTS `mahasiswa`;

CREATE TABLE `mahasiswa` (
  `Nim` int(11) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(25) NOT NULL,
  `Jurusan` varchar(7) NOT NULL,
  `Alamat` varchar(30) NOT NULL,
  PRIMARY KEY (`Nim`)
) ENGINE=InnoDB AUTO_INCREMENT=21113010 DEFAULT CHARSET=latin1;

/*Data for the table `mahasiswa` */

insert  into `mahasiswa`(`Nim`,`Nama`,`Jurusan`,`Alamat`) values (21113009,'Ariesotni','TI','Laguboti');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
