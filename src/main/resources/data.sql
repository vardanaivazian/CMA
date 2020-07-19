/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.7.25-log : Database - cma-db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cma-db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `cma-db`;

/*Data for the table `orders` */

/*Data for the table `product_in_orders` */

/*Data for the table `products` */

/*Data for the table `roles` */

insert  into `roles`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Data for the table `tables` */

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values 
(11,1),
(12,2),
(13,1),
(14,1);

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`first_name`,`last_name`,`password`) values 
(11,'hresh@gayl.com','Gor','Vardanyan','$2a$10$TC9ORktgpmgPaRBYP0E.duQQS066eT3gDTLX.jsZ/o4EWUHo4hxEe'),
(12,'hresh@gayl2.com','Gor','Vardanyan','$2a$10$mFlZGEn3RvCSIv1tKxbiDOFpcVvqlTX5wu5HujXNrtfCIAdw8jSWC'),
(13,'admin@mail.com','ManagerFirstName','ManagerLastName','$2a$10$GWzNf0V9uzKAmchUs2hojeMGbzyb5IRsQyEzrQ6B3Lwhm0GjH3z2K'),
(14,'user@mail.com','ManagerFirstName','ManagerLastName','$2a$10$xl9hLlbMxRju6brzATxFquz.26eN.ZBEjNd05usQQFj1P/UNOX9qu');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
