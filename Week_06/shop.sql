
CREATE TABLE `users` (
  `id` int(11) NOT NULL DEFAULT '0',
  `user_key` varchar(16) DEFAULT NULL,
  `user_name` varchar(16) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_index` (`phone`),
  KEY `create_time_index` (`create_time`),
  KEY `user_key_index` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL DEFAULT '0',
  `sku_id` varchar(16) DEFAULT NULL COMMENT '商品ID',
  `good_name` varchar(32) DEFAULT NULL,
  `num` varchar(16) DEFAULT NULL COMMENT '库存数量',
  `price` int(11) DEFAULT NULL COMMENT '价格，精确到分',
  `category_id` varchar(16) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`create_time`),
  KEY `sku_id_index` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL DEFAULT '0',
  `order_key` varchar(16) DEFAULT NULL,
  `user_key` varchar(16) DEFAULT NULL,
  `sku_id` varchar(16) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `details` varchar(64) DEFAULT NULL COMMENT '商品描述',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`create_time`),
  KEY `order_key_index` (`order_key`),
  KEY `user_key_index` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;