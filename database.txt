create database bookstore;

use bookstore;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `img_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (1, '高等數學', 10.00, '牛頓', 3, 100, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (3, 'Java入門到放棄', 55.00, 'James Gosling', 6, 99, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (4, '易筋經：中國式瑜珈', 360.00, '譚浩強', 126, 184, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (5, '三體', 50.00, '劉慈欣', 27, 3, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (6, '論文寫作指導', 49.00, '劉慈欣', 65, 35, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (7, '囚狐', 133.05, '雷豐陽', 125, 185, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (8, '自動控制原理', 89.15, '王萬良', 23, 7, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (9, '第七日', 49.00, '余華', 66, 34, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (10, '人生', 133.05, '路遙', 122, 188, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (11, '活著', 89.15, '余華', 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (12, '禿子跟著月亮走', 999.00, '韓國瑜', 0, 1000, 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('16444160853253', '2022-02-09 22:14:45', 925.00, 1, 3);
INSERT INTO `t_order` VALUES ('16444161578231', '2022-02-09 22:15:57', 346.10, 2, 1);
INSERT INTO `t_order` VALUES ('16444162162311', '2022-02-09 22:16:56', 178.30, 1, 1);
INSERT INTO `t_order` VALUES ('16444165692211', '2022-02-09 22:22:49', 187.15, 0, 1);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `total_price` decimal(11, 2) NULL DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (8, 'Java入門到放棄', 1, 55.00, 55.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (9, '易筋經：中國式瑜珈', 2, 360.00, 720.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (10, '三體', 3, 50.00, 150.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (11, '高等數學', 3, 10.00, 30.00, '16444161578231');
INSERT INTO `t_order_item` VALUES (12, '三體', 1, 50.00, 50.00, '16444161578231');
INSERT INTO `t_order_item` VALUES (13, '囚狐', 2, 133.05, 266.10, '16444161578231');
INSERT INTO `t_order_item` VALUES (14, '自動控制原理', 2, 89.15, 178.30, '16444162162311');
INSERT INTO `t_order_item` VALUES (15, '第七日', 2, 49.00, 98.00, '16444165692211');
INSERT INTO `t_order_item` VALUES (16, '自動控制原理', 1, 89.15, 89.15, '16444165692211');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'admin@example.com');
INSERT INTO `t_user` VALUES (2, 'aaa', 'aaa', 'aaa@a.com');
INSERT INTO `t_user` VALUES (3, 'ccc', 'ccc', 'ccc@c.com');
INSERT INTO `t_user` VALUES (4, 'guest', 'guest', 'xxx@x.com');

SET FOREIGN_KEY_CHECKS = 1;
