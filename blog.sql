/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.21 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `blog`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order` int NOT NULL DEFAULT '2',
  `status` int NOT NULL DEFAULT '1',
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order` (`order`),
  CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`order`) REFERENCES `role` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`email`,`order`,`status`,`date`) values 
(1,'admin','123456','123@qq.com',1,1,'2021-06-02 22:16:52'),
(2,'user','123456','123456@qq.com',1,1,'2021-06-09 15:51:22'),
(5,'kit','123','123@qq.com',2,1,'2021-06-05 14:42:10'),
(10,'lin','202cb962ac59075b964b07152d234b70','123456@qq.com',1,1,'2021-06-08 23:34:14');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `sub_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text NOT NULL,
  `author_id` int NOT NULL,
  `author_name` varchar(100) NOT NULL,
  `date` varchar(200) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `view` int NOT NULL DEFAULT '0',
  `good` int NOT NULL DEFAULT '0',
  `collect` int NOT NULL DEFAULT '0',
  `state` int NOT NULL DEFAULT '1',
  `cover` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/img/gaomu.png',
  `label` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`sub_title`,`content`,`author_id`,`author_name`,`date`,`view`,`good`,`collect`,`state`,`cover`,`label`) values 
(18,'JAVA复习(一)','<p>类的管理及常用工具类</p>','<h2 style=\"box-sizing: border-box; outline: 0px; margin: 8px 0px 16px; padding: 0px; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun; font-size: 24px; color: #4f4f4f; line-height: 32px; overflow-wrap: break-word; font-variant-ligatures: common-ligatures; background-color: #ffffff;\">java复习： 类的管理及常用工具类</h2>\r\n<h5 style=\"box-sizing: border-box; outline: 0px; margin: 8px 0px 16px; padding: 0px; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun; font-size: 18px; color: #4f4f4f; line-height: 26px; overflow-wrap: break-word; font-variant-ligatures: common-ligatures; background-color: #ffffff;\"><a id=\"_1\" style=\"box-sizing: border-box; outline: none; margin: 0px; padding: 0px; font-weight: normal; cursor: pointer; background-color: transparent; color: #4ea1db; overflow-wrap: break-word;\"></a>包</h5>\r\n<ul style=\"box-sizing: border-box; outline: 0px; margin: 0px 0px 24px; padding: 0px; list-style: none; font-size: 16px; overflow: auto hidden; overflow-wrap: break-word; color: rgba(0, 0, 0, 0.75); font-family: -apple-system, \'SF UI Text\', Arial, \'PingFang SC\', \'Hiragino Sans GB\', \'Microsoft YaHei\', \'WenQuanYi Micro Hei\', sans-serif; font-variant-ligatures: common-ligatures; background-color: #ffffff;\">\r\n<li style=\"box-sizing: border-box; outline: 0px; margin: 8px 0px 0px 32px; padding: 0px; list-style: disc; overflow-wrap: break-word;\">写在程序文件的第一行</li>\r\n<li style=\"box-sizing: border-box; outline: 0px; margin: 8px 0px 0px 32px; padding: 0px; list-style: disc; overflow-wrap: break-word;\">一个Java 源文件中只能声明一个包，<br style=\"box-sizing: border-box; outline: 0px; overflow-wrap: break-word;\" />且声明语句只能作为源文件的第一条指令</li>\r\n<li style=\"box-sizing: border-box; outline: 0px; margin: 8px 0px 0px 32px; padding: 0px; list-style: disc; overflow-wrap: break-word;\">导入类能导入非public类，但是不能用因为在其他包缺省的权限用不了</li>\r\n</ul>',6,'kit','2021-06-16 10:58:38',59,26,1,1,'/img/gaomu.png','<ul>\r\n<li>java</li>\r\n</ul>'),
(19,'JAVA复习(二)','<p>包装器类</p>','<p>包装器类</p>\r\n<p>Byte 、Short 、Integer 、Long 、Float 、Double 、</p>\r\n<p>Character 、Boolean</p>\r\n<p>&nbsp;</p>\r\n<p>包装器类的2种构造函数</p>\r\n<p>&nbsp;</p>\r\n<p>包装器类名（基本类型值）</p>\r\n<p>包装器类名（基本类型值的字符串）</p>\r\n<p>Character类构造函数只能是字符参数；</p>\r\n<p>Boolean包装器对ture/false不区分大小写</p>\r\n<p>&nbsp;</p>',6,'kit','2021-06-16 11:01:08',53,5,1,1,'/img/gaomu.png','<ul>\r\n<li>java</li>\r\n<li>包装器</li>\r\n</ul>'),
(20,'高木同学','<p>高木同学</p>','<p><span style=\"font-size: 36pt;\">高木同学</span></p>\r\n<p>&nbsp;</p>',6,'kit','2021-06-18 11:01:08',5,1,1,1,'/img/gaomu.png','<ul>\r\n<li>高木同学</li>\r\n</ul>'),
(21,'守望先锋','<p>守望先锋英雄一览</p>','<p><span style=\"font-size: 36pt;\">守望先锋英雄</span></p>\r\n<ul>\r\n<li><span style=\"font-size: 24pt;\">麦克雷</span></li>\r\n<li><span style=\"font-size: 32px;\">士兵76人</span></li>\r\n<li><span style=\"font-size: 32px;\">艾什</span></li>\r\n<li><span style=\"font-size: 32px;\">小美</span></li>\r\n</ul>',6,'kit','2021-06-23 19:29:30',21,10,1,1,'/img/1920x1080-px-Combat-Medic-Ziegler-digital-art-Genji-Overwatch-Mercy-Overwatch-Overwatch-1480697-wallhere.com.jpg','<ul>\r\n<li>overwatch</li>\r\n</ul>');

/*Table structure for table `collect` */

DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `collect_id` int NOT NULL,
  PRIMARY KEY (`id`,`article_id`,`collect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `collect` */

insert  into `collect`(`id`,`article_id`,`collect_id`) values 
(68,18,6),
(69,19,6),
(70,20,6),
(73,19,9),
(74,21,9);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `subject` varchar(200) NOT NULL,
  `message` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `comment` */

insert  into `comment`(`id`,`username`,`email`,`subject`,`message`) values 
(1,'123','123','123','123');

/*Table structure for table `follow` */

DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `follow_id` int NOT NULL,
  `follower_id` int NOT NULL,
  PRIMARY KEY (`id`,`follow_id`,`follower_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `follow` */

insert  into `follow`(`id`,`follow_id`,`follower_id`) values 
(19,6,9),
(20,9,6);

/*Table structure for table `label` */

DROP TABLE IF EXISTS `label`;

CREATE TABLE `label` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `count` int NOT NULL DEFAULT '1',
  `hot` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `label` */

insert  into `label`(`id`,`name`,`count`,`hot`) values 
(4,'java',2,31),
(5,'包装器',2,16),
(6,'overwatch',1,12),
(7,'高木同学',1,14);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `value` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`value`) values 
(1,'超级管理员','ALL',0),
(2,'一级普通管理员','all',1),
(3,'二级普通管理员','allExceptAdmin',2);

/*Table structure for table `search_key` */

DROP TABLE IF EXISTS `search_key`;

CREATE TABLE `search_key` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `search_key` */

insert  into `search_key`(`id`,`name`,`count`) values 
(1,'java',63),
(2,'包装器',11),
(3,'高木同学',7),
(5,'overwatch',1),
(6,'kit',16),
(7,'i',2),
(8,'lin',7);

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `value` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `status` */

insert  into `status`(`id`,`name`,`value`) values 
(1,'启用',1),
(2,'禁用',2),
(3,'审核',3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `status` int NOT NULL DEFAULT '3',
  `head_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'img/avatar.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`email`,`password`,`status`,`head_img`) values 
(6,'kit','123@qq.com','YN3Y7XOAOrQv9cwpz7jPaA',3,'img/avatar.jpg'),
(9,'lin','123@qq.com','YN3Y7XOAOrQv9cwpz7jPaA',3,'img/avatar.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
