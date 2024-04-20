/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : 193.112.179.102:3306
 Source Schema         : flower_shop

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 20/04/2024 18:47:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mirageledger_cart
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_cart`;
CREATE TABLE `mirageledger_cart`  (
                                      `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                      `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                                      `PRODUCT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
                                      `QUANTITY_` int NOT NULL COMMENT '数量',
                                      `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_cart
-- ----------------------------
INSERT INTO `mirageledger_cart` VALUES ('df92f2d579bd1661ab349bbbafcb2967', 'admin', '2', 2, '2024-04-20 17:58:30', '2024-04-20 17:58:30');

-- ----------------------------
-- Table structure for mirageledger_category
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_category`;
CREATE TABLE `mirageledger_category`  (
                                          `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                          `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
                                          `DESC_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
                                          `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_category
-- ----------------------------
INSERT INTO `mirageledger_category` VALUES ('1', '满天星', '玫瑰花种类', '2024-04-09 13:11:52', '2024-04-09 13:11:52');
INSERT INTO `mirageledger_category` VALUES ('2', '郁金', '百合花种类aa', '2024-04-09 13:11:43', '2024-04-09 13:11:43');
INSERT INTO `mirageledger_category` VALUES ('3', '水生花', '满天星花种q', '2024-04-09 13:11:39', '2024-04-09 13:11:39');
INSERT INTO `mirageledger_category` VALUES ('4', '铃兰', '向日葵花种qq', '2024-04-09 13:11:14', '2024-04-09 13:11:14');
INSERT INTO `mirageledger_category` VALUES ('5', '紫罗兰', '紫罗兰花种', '2024-03-26 12:12:39', '2024-03-26 12:12:39');

-- ----------------------------
-- Table structure for mirageledger_order
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_order`;
CREATE TABLE `mirageledger_order`  (
                                       `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                       `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                                       `ORDER_PRICE` double NOT NULL COMMENT '订单总价',
                                       `STATUS_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态',
                                       `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `ADDRESS_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
                                       PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_order
-- ----------------------------
INSERT INTO `mirageledger_order` VALUES ('17fffde26630da6f61760f0c0b63590d', 'admin', 299.99, '待发货', '2024-04-20 02:57:19', '2024-04-20 10:57:19', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('26f026d2e04cb0017927b479b6edeb9f', 'admin', 89.99, '售后', '2024-04-20 02:55:12', '2024-04-20 10:55:12', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('78de94acf58cf2e74e9f35e9d4dd5e67', 'admin', 100, '待付款', '2024-04-20 11:51:28', '2024-04-20 11:51:28', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('9271c9efbad5e8b22dc7f3fb12dbe531', 'admin', 89.99, '已完成', '2024-04-20 10:29:32', '2024-04-20 18:29:32', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('952611648884c27499df3b17ec6679b3', 'admin', 299.99, '售后', '2024-04-20 02:57:03', '2024-04-20 10:57:03', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('a9c1c5d4c5ca26641215f7857d393d24', 'admin', 300, '售后', '2024-04-20 10:29:37', '2024-04-20 18:29:37', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('ac8d643bdf7d51819b179f7a280bf62e', 'admin', 89.99, '待收货', '2024-04-20 10:29:42', '2024-04-20 18:29:43', '3957bdcffd5b881a0e78b7b248afc2f6');
INSERT INTO `mirageledger_order` VALUES ('e379bdea8304ddcb5d2fb992e1185176', 'admin', 100, '待付款', '2024-04-20 11:15:25', '2024-04-20 11:15:25', '3957bdcffd5b881a0e78b7b248afc2f6');

-- ----------------------------
-- Table structure for mirageledger_order_product
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_order_product`;
CREATE TABLE `mirageledger_order_product`  (
                                               `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                               `ORDER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单ID',
                                               `PRODUCT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
                                               `QUANTITY_` int NOT NULL COMMENT '数量',
                                               `PRICE_` double NOT NULL COMMENT '商品单价',
                                               `TOTAL_PRICE` double NOT NULL COMMENT '商品总价',
                                               `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                               `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                               PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单商品关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_order_product
-- ----------------------------
INSERT INTO `mirageledger_order_product` VALUES ('036226673769f44246c36a42a9c74485', '72d1c18820fbf734d5d71bf788cabbf1', '11', 3, 89.99, 269.96999999999997, '2024-04-09 17:52:49', '2024-04-09 17:52:49');
INSERT INTO `mirageledger_order_product` VALUES ('0cff4922a7bef6843144df44a72f0122', '72d1c18820fbf734d5d71bf788cabbf1', '10', 1, 89.99, 89.99, '2024-04-09 17:52:49', '2024-04-09 17:52:49');
INSERT INTO `mirageledger_order_product` VALUES ('1', '1', '1', 2, 299.99, 599.98, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('1097ffff67fbc14af26ec438c49ec142', '9271c9efbad5e8b22dc7f3fb12dbe531', '9', 1, 89.99, 89.99, '2024-04-20 10:58:15', '2024-04-20 10:58:15');
INSERT INTO `mirageledger_order_product` VALUES ('12896f4cb5eb344e295eac85578f811e', '88a529859b5d6f362300894eeda10ed5', '11', 3, 89.99, 269.96999999999997, '2024-04-09 16:53:29', '2024-04-09 16:53:29');
INSERT INTO `mirageledger_order_product` VALUES ('19195fd27559c25f16bfc76ac47c8d5b', 'a17081506ac5dc551d4c66faf6306b1d', '9', 2, 89.99, 179.98, '2024-04-09 16:37:14', '2024-04-09 16:37:14');
INSERT INTO `mirageledger_order_product` VALUES ('1b907632633a0356601e48e86ef9e958', '0ae9b05fb0f08a45521b09cc82be6a3e', '6', 3, 100, 300, '2024-04-09 23:33:55', '2024-04-09 23:33:55');
INSERT INTO `mirageledger_order_product` VALUES ('1e32a26cd4cce4ba6e5b69a5a57e2dd8', '88a529859b5d6f362300894eeda10ed5', '4', 1, 59.99, 59.99, '2024-04-09 16:53:29', '2024-04-09 16:53:29');
INSERT INTO `mirageledger_order_product` VALUES ('2', '2', '2', 3, 199.99, 599.97, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('2637ac132e635dc8005fa6996f39cc7e', 'c2e986af592fc8b55016f472b7890a75', '10', 1, 89.99, 89.99, '2024-04-09 17:52:11', '2024-04-09 17:52:11');
INSERT INTO `mirageledger_order_product` VALUES ('295686a86e9b3f852a2c687e955a1de1', 'a965ceae5c27bd6c0a77f2b6689a09b8', '1', 2, 299.99, 599.98, '2024-04-09 21:16:42', '2024-04-09 21:16:42');
INSERT INTO `mirageledger_order_product` VALUES ('2b156ad15868e91a07e2b0fd32769713', 'e9c7256605b1d2e569b0e4a940f382c8', '11', 3, 89.99, 269.96999999999997, '2024-04-09 20:17:00', '2024-04-09 20:17:00');
INSERT INTO `mirageledger_order_product` VALUES ('3', '3', '3', 1, 99.99, 99.99, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('3497ff3c51f1b63bb556e898bd93f871', '47f68fc2e9911e0d489913c1c5f3bb82', '1', 2, 299.99, 599.98, '2024-04-09 17:01:59', '2024-04-09 17:01:59');
INSERT INTO `mirageledger_order_product` VALUES ('35adac5f2b6cf95b57ab14201c12b584', 'da4b62a9373e9f055f7ebdf4785835a1', '7', 1, 200, 200, '2024-03-27 23:27:03', '2024-03-27 23:27:03');
INSERT INTO `mirageledger_order_product` VALUES ('3949caf3d7a3919874a1bc6464830bf7', '17b1d0c5b3231098cac5019d5ad78d53', '4', 1, 59.99, 59.99, '2024-04-09 20:33:18', '2024-04-09 20:33:18');
INSERT INTO `mirageledger_order_product` VALUES ('3be74bbe100f5975e4105c2bfc5096aa', 'a70e4a7168b9b3990e3789cb00667651', '10', 1, 89.99, 89.99, '2024-04-09 17:52:41', '2024-04-09 17:52:41');
INSERT INTO `mirageledger_order_product` VALUES ('3c8dfac89f18063f7cc87398a7a34354', 'c2e986af592fc8b55016f472b7890a75', '11', 3, 89.99, 269.96999999999997, '2024-04-09 17:52:11', '2024-04-09 17:52:11');
INSERT INTO `mirageledger_order_product` VALUES ('4', '4', '4', 1, 59.99, 59.99, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('48722898760f02a39f5b1cd0d827e816', 'a72d7f6b29ed11bbfa752cec6d13dc3a', '1', 2, 299.99, 599.98, '2024-04-09 16:36:11', '2024-04-09 16:36:11');
INSERT INTO `mirageledger_order_product` VALUES ('4883f90747d097ddcfd24639b26557c3', '1d1dfe2353edf43bf96a92a67ac130bc', '9', 2, 89.99, 179.98, '2024-04-09 16:41:12', '2024-04-09 16:41:12');
INSERT INTO `mirageledger_order_product` VALUES ('5', '5', '5', 2, 89.99, 179.98, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('6', '6', '2', 3, 321, 599.98, '2024-03-26 12:12:55', '2024-03-26 12:12:55');
INSERT INTO `mirageledger_order_product` VALUES ('65b3758be3677edb4c2fe7c29bbdfd2e', '26f026d2e04cb0017927b479b6edeb9f', '9', 1, 89.99, 89.99, '2024-04-20 10:54:59', '2024-04-20 10:54:59');
INSERT INTO `mirageledger_order_product` VALUES ('6cd1f9ec892b16cc046e728a42802141', 'a70e4a7168b9b3990e3789cb00667651', '4', 1, 59.99, 59.99, '2024-04-09 17:52:41', '2024-04-09 17:52:41');
INSERT INTO `mirageledger_order_product` VALUES ('6fc289ea1ba8f0fe8fa46a1dffc1b126', '0aed9ab8bf963379cefd373587cfbfc1', '11', 3, 89.99, 269.96999999999997, '2024-04-09 20:14:11', '2024-04-09 20:14:11');
INSERT INTO `mirageledger_order_product` VALUES ('7839327be316375b94d1e4dba2f65600', '611548be36ec474b3b6c99011e4c677e', '11', 3, 89.99, 269.96999999999997, '2024-04-09 17:52:03', '2024-04-09 17:52:03');
INSERT INTO `mirageledger_order_product` VALUES ('7ea8bbbc35ba23860c01b73a6e1a93ff', 'a70e4a7168b9b3990e3789cb00667651', '11', 3, 89.99, 269.96999999999997, '2024-04-09 17:52:41', '2024-04-09 17:52:41');
INSERT INTO `mirageledger_order_product` VALUES ('873cfc28f32f26cf770e957bf78871f1', '78de94acf58cf2e74e9f35e9d4dd5e67', '6', 1, 100, 100, '2024-04-20 11:51:28', '2024-04-20 11:51:28');
INSERT INTO `mirageledger_order_product` VALUES ('87e125eadd00078b3bab86b72d243e32', 'a965ceae5c27bd6c0a77f2b6689a09b8', '8', 1, 300, 300, '2024-04-09 21:16:42', '2024-04-09 21:16:42');
INSERT INTO `mirageledger_order_product` VALUES ('88bdd1acdaab51b49d5d71c78f62e1a0', 'e379bdea8304ddcb5d2fb992e1185176', '6', 1, 100, 100, '2024-04-20 11:15:25', '2024-04-20 11:15:25');
INSERT INTO `mirageledger_order_product` VALUES ('89632abe8a5fdb4c086b131134f976a4', '611548be36ec474b3b6c99011e4c677e', '1', 2, 299.99, 599.98, '2024-04-09 17:52:03', '2024-04-09 17:52:03');
INSERT INTO `mirageledger_order_product` VALUES ('97f413bdbf9c2446f4ebae77d3fb5443', 'a9c1c5d4c5ca26641215f7857d393d24', '8', 1, 300, 300, '2024-04-20 11:00:51', '2024-04-20 11:00:51');
INSERT INTO `mirageledger_order_product` VALUES ('aaead7f3bdf27a316395bbe31cde114d', '339ec85035e2d7a3f6e2fa6a430c14d5', '9', 1, 89.99, 89.99, '2024-04-19 15:13:03', '2024-04-19 15:13:03');
INSERT INTO `mirageledger_order_product` VALUES ('ad8266591dee51ed752a216ac986bfd1', 'ac8d643bdf7d51819b179f7a280bf62e', '9', 1, 89.99, 89.99, '2024-04-20 11:00:00', '2024-04-20 11:00:00');
INSERT INTO `mirageledger_order_product` VALUES ('b22c763ba79f8192bc4e5f99501cb917', '611548be36ec474b3b6c99011e4c677e', '4', 1, 59.99, 59.99, '2024-04-09 17:52:03', '2024-04-09 17:52:03');
INSERT INTO `mirageledger_order_product` VALUES ('b51ff79dfeae70fb9c92132cf19cabf1', '17fffde26630da6f61760f0c0b63590d', '1', 1, 299.99, 299.99, '2024-04-20 10:57:16', '2024-04-20 10:57:16');
INSERT INTO `mirageledger_order_product` VALUES ('bab5151ebcab4ccde739e1a6d0882b8e', '611548be36ec474b3b6c99011e4c677e', '10', 1, 89.99, 89.99, '2024-04-09 17:52:03', '2024-04-09 17:52:03');
INSERT INTO `mirageledger_order_product` VALUES ('be788ff1a8f99d15b56c24b14f8f0dae', '88a529859b5d6f362300894eeda10ed5', '10', 1, 89.99, 89.99, '2024-04-09 16:53:29', '2024-04-09 16:53:29');
INSERT INTO `mirageledger_order_product` VALUES ('be7db4bcf3cf36c24537bae75cb456d8', 'e40335c304927f3226d7c4a14dd75a91', '11', 3, 89.99, 269.96999999999997, '2024-04-09 20:23:02', '2024-04-09 20:23:02');
INSERT INTO `mirageledger_order_product` VALUES ('c9d5bc5208900ccce3795cf8b039eda8', '0ae9b05fb0f08a45521b09cc82be6a3e', '8', 1, 300, 300, '2024-04-09 23:33:55', '2024-04-09 23:33:55');
INSERT INTO `mirageledger_order_product` VALUES ('ca701b20287b08b5011123466f3c1246', '47f68fc2e9911e0d489913c1c5f3bb82', '10', 1, 89.99, 89.99, '2024-04-09 17:01:59', '2024-04-09 17:01:59');
INSERT INTO `mirageledger_order_product` VALUES ('cae00ca1b5dd659410700066326bcdc5', 'c2e986af592fc8b55016f472b7890a75', '1', 2, 299.99, 599.98, '2024-04-09 17:52:11', '2024-04-09 17:52:11');
INSERT INTO `mirageledger_order_product` VALUES ('db9accbc9da3ef7086eaf34ce9453a51', '47f68fc2e9911e0d489913c1c5f3bb82', '4', 1, 59.99, 59.99, '2024-04-09 17:01:59', '2024-04-09 17:01:59');
INSERT INTO `mirageledger_order_product` VALUES ('dc865b3becf23b410e5abebdee2f7d1a', '75b478c8e1f8bb8f9a7e87dc5abd4f86', '1', 1, 299.99, 299.99, '2024-04-10 23:45:37', '2024-04-10 23:45:37');
INSERT INTO `mirageledger_order_product` VALUES ('e135e47a705a86d454ee1002b2332769', '952611648884c27499df3b17ec6679b3', '1', 1, 299.99, 299.99, '2024-04-19 16:09:39', '2024-04-19 16:09:39');
INSERT INTO `mirageledger_order_product` VALUES ('e34c0a753ccbc09ade0d2cac68f1c4a1', '373a1394f9027332c9f35310243f1a1f', '11', 3, 89.99, 269.96999999999997, '2024-04-09 20:24:34', '2024-04-09 20:24:34');
INSERT INTO `mirageledger_order_product` VALUES ('e39eff202e155de2af9b1537127d8b22', '8a2a3e62ba3c578f912b32fadb0e2181', '9', 2, 89.99, 179.98, '2024-04-09 16:39:19', '2024-04-09 16:39:19');
INSERT INTO `mirageledger_order_product` VALUES ('e91386a2525a44df934d40cedfb307cc', '72d1c18820fbf734d5d71bf788cabbf1', '4', 1, 59.99, 59.99, '2024-04-09 17:52:49', '2024-04-09 17:52:49');
INSERT INTO `mirageledger_order_product` VALUES ('ee686d8cbd574c7d2b93c6c45b8bb827', '8d5a048f31b819c46fb113c7f3fd395e', '1', 4, 299.99, 1199.96, '2024-03-29 00:49:42', '2024-03-29 00:49:42');
INSERT INTO `mirageledger_order_product` VALUES ('ef884c637a296972142b61639bd12348', 'c2e986af592fc8b55016f472b7890a75', '4', 1, 59.99, 59.99, '2024-04-09 17:52:11', '2024-04-09 17:52:11');
INSERT INTO `mirageledger_order_product` VALUES ('f0f89838b458a78614787353411cc742', '38e782e37fd33c28be492a1d295941f1', '10', 1, 89.99, 89.99, '2024-04-09 20:25:58', '2024-04-09 20:25:58');
INSERT INTO `mirageledger_order_product` VALUES ('f2aa3d7381b246c2b4911b28959803d5', '88a529859b5d6f362300894eeda10ed5', '1', 2, 299.99, 599.98, '2024-04-09 16:53:29', '2024-04-09 16:53:29');
INSERT INTO `mirageledger_order_product` VALUES ('f490732646e818c4c5d252721f924061', 'a70e4a7168b9b3990e3789cb00667651', '1', 2, 299.99, 599.98, '2024-04-09 17:52:41', '2024-04-09 17:52:41');
INSERT INTO `mirageledger_order_product` VALUES ('f87d807a0f5378e93f7a32f0c35b4281', '47f68fc2e9911e0d489913c1c5f3bb82', '11', 3, 89.99, 269.96999999999997, '2024-04-09 17:01:59', '2024-04-09 17:01:59');
INSERT INTO `mirageledger_order_product` VALUES ('f88528129410e54629c45f8f1348ed5e', '72d1c18820fbf734d5d71bf788cabbf1', '1', 2, 299.99, 599.98, '2024-04-09 17:52:49', '2024-04-09 17:52:49');
INSERT INTO `mirageledger_order_product` VALUES ('fa387eca4bc55b1bbee9b5ab3fe9b07b', 'f9317e2c6d04afb1f14b8e4d2155aa54', '11', 3, 89.99, 269.96999999999997, '2024-04-09 20:14:43', '2024-04-09 20:14:43');

-- ----------------------------
-- Table structure for mirageledger_product
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_product`;
CREATE TABLE `mirageledger_product`  (
                                         `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                         `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
                                         `CATEGORY_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别ID',
                                         `PRICE_` double NOT NULL COMMENT '商品价格',
                                         `STOCK_` int NOT NULL COMMENT '商品库存',
                                         `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                                         `DESC_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
                                         `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_product
-- ----------------------------
INSERT INTO `mirageledger_product` VALUES ('1', '满天星qq', '1', 299, 50, '4', '玫瑰花种类', '2024-03-26 12:12:39', '2024-04-20 17:22:41');
INSERT INTO `mirageledger_product` VALUES ('10', '睡莲', '3', 89.99, 120, '4', '神秘而高贵', '2024-04-06 10:21:55', '2024-04-06 10:21:55');
INSERT INTO `mirageledger_product` VALUES ('11', '康乃馨', '3', 89.99, 120, '4', '神秘而高贵', '2024-04-06 10:21:55', '2024-04-06 10:21:55');
INSERT INTO `mirageledger_product` VALUES ('12', '小雏菊', '3', 89.99, 120, '4', '神秘而高贵', '2024-04-06 10:21:56', '2024-04-06 10:21:56');
INSERT INTO `mirageledger_product` VALUES ('2', '郁金香', '2', 199.99, 100, '4', '郁金香花束', '2024-03-26 16:49:00', '2024-03-26 16:49:00');
INSERT INTO `mirageledger_product` VALUES ('3', '向日葵', '3', 99.99, 200, '4', '热烈的向日葵', '2024-03-26 16:49:15', '2024-03-26 16:49:15');
INSERT INTO `mirageledger_product` VALUES ('4', '铃兰花', '4', 59.99, 150, '4', '我叫小灵花', '2024-03-26 16:49:48', '2024-03-26 16:49:48');
INSERT INTO `mirageledger_product` VALUES ('5', '芍药', '3', 89.99, 120, '4', '神秘而高贵的芍药', '2024-04-06 10:21:58', '2024-04-20 18:36:12');
INSERT INTO `mirageledger_product` VALUES ('6', '月见草', '3', 100, 10, 'admin', 'This is product 1', '2024-04-06 10:21:59', '2024-04-06 10:21:59');
INSERT INTO `mirageledger_product` VALUES ('7', '洋水仙', '3', 200, 20, 'admin', 'This is product 2', '2024-04-06 10:21:59', '2024-04-06 10:21:59');
INSERT INTO `mirageledger_product` VALUES ('8', '香雪兰', '3', 300, 30, 'admin', 'This is product 3', '2024-04-06 10:22:00', '2024-04-06 10:22:00');
INSERT INTO `mirageledger_product` VALUES ('9', '雀梅', '3', 89.99, 120, '4', '神秘而高贵', '2024-04-06 10:22:01', '2024-04-06 21:00:31');

-- ----------------------------
-- Table structure for mirageledger_user
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_user`;
CREATE TABLE `mirageledger_user`  (
                                      `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                      `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
                                      `CODE_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
                                      `DESC_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户描述',
                                      `PASSWORD_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号密码',
                                      `CREATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `UPDATE_TIME` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_user
-- ----------------------------
INSERT INTO `mirageledger_user` VALUES ('6e66e561abdc0d129dc79b5d84c3aaf8', 'asdasd', 'test', 'asdasd', 'test123', '2024-04-20 18:37:55', '2024-04-20 18:45:37');
INSERT INTO `mirageledger_user` VALUES ('admin', 'admin', 'admin', '管理账户', 'admin123', '2024-03-26 12:13:58', '2024-03-26 12:13:58');
INSERT INTO `mirageledger_user` VALUES ('ae4dd9aab3dedbfdb1f67fbc0d0183e5', 'test01', 'test01', 'test01', 'test01', '2024-04-20 18:45:28', '2024-04-20 18:45:28');

-- ----------------------------
-- Table structure for mirageledger_user_address
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_user_address`;
CREATE TABLE `mirageledger_user_address`  (
                                              `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                              `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                              `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人姓名',
                                              `MOBILE_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人手机号码',
                                              `ADDRESS_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
                                              `AREA_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '楼号、门牌号',
                                              `FAULT_` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为默认地址（0：否，1：是）',
                                              `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                              PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_user_address
-- ----------------------------
INSERT INTO `mirageledger_user_address` VALUES ('3957bdcffd5b881a0e78b7b248afc2f6', 'admin', '杰森', '12222222222', '拉布拉多', '603', 0, '2024-04-19 15:12:51', '2024-04-19 15:12:51');

-- ----------------------------
-- Table structure for mirageledger_view_history
-- ----------------------------
DROP TABLE IF EXISTS `mirageledger_view_history`;
CREATE TABLE `mirageledger_view_history`  (
                                              `ID_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                                              `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                                              `PRODUCT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
                                              `CREATE_TIME` timestamp NOT NULL COMMENT '创建时间',
                                              `COUNT_` int NOT NULL COMMENT '浏览次数',
                                              PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户商品浏览记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mirageledger_view_history
-- ----------------------------
INSERT INTO `mirageledger_view_history` VALUES ('056fd294f31cc6b8cea2cb906700518e', 'admin', '9', '2024-04-19 16:15:00', 5);
INSERT INTO `mirageledger_view_history` VALUES ('11d7090a7d7aabf7d65e7538c85dc473', 'admin', '5', '2024-04-19 16:14:46', 3);
INSERT INTO `mirageledger_view_history` VALUES ('2f2f8e3b761f55e9b5785bd4ffd02a7c', 'admin', '4', '2024-04-20 11:50:37', 1);
INSERT INTO `mirageledger_view_history` VALUES ('37712795c94626c78877d9163c866e9b', 'admin', '8', '2024-04-20 11:00:49', 5);
INSERT INTO `mirageledger_view_history` VALUES ('4e22f7cc2c9f49a5f4f5c4b41e10733e', 'admin', '1', '2024-04-19 16:15:14', 6);
INSERT INTO `mirageledger_view_history` VALUES ('50444bc31b949e5d800e76539b5ed5d9', 'admin', '7', '2024-04-19 16:14:49', 1);
INSERT INTO `mirageledger_view_history` VALUES ('78cc71420e8c1f7f66d258f029e1d13f', 'admin', '6', '2024-04-20 11:15:24', 5);
INSERT INTO `mirageledger_view_history` VALUES ('c34e897c27e01c1944a1adb23967a673', 'admin', '3', '2024-04-19 16:15:19', 4);
INSERT INTO `mirageledger_view_history` VALUES ('c7f172a5b3d1a4dadc6a86546736cc76', 'admin', '2', '2024-04-19 16:15:17', 104);
INSERT INTO `mirageledger_view_history` VALUES ('cecbe400b92a6e8ed339af9bdb614672', 'admin', '12', '2024-04-20 11:51:25', 1);

SET FOREIGN_KEY_CHECKS = 1;
