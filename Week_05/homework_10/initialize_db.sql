SET NAMES utf8mb4;

CREATE DATABASE `java_000` DEFAULT CHARACTER SET utf8mb4;

USE `java_000`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` char(16) DEFAULT NULL COMMENT '学号',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `class` tinyint(4) DEFAULT NULL COMMENT '班级',
  `department` varchar(16) DEFAULT NULL COMMENT '科系',
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`),
  KEY `number_index` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;