/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : tgshop_cloud

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 28/07/2019 16:20:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_freight_charge_template
-- ----------------------------
DROP TABLE IF EXISTS `item_freight_charge_template`;
CREATE TABLE `item_freight_charge_template`  (
  `template_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运费id',
  `store_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '店铺id',
  `template_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运费名称',
  `template_type` int(11) NOT NULL DEFAULT 1 COMMENT '类型 1 免邮 2 满额包邮 3 支付免邮 4 不包邮',
  `fulfil_price` decimal(14, 2) NOT NULL DEFAULT 0.00 COMMENT '满额',
  `freight_price` decimal(14, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
  `is_del` int(11) NOT NULL DEFAULT 0 COMMENT '删除状态',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`template_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运费模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_freight_charge_template
-- ----------------------------
INSERT INTO `item_freight_charge_template` VALUES ('0', '0', '免邮', 1, 0.00, 0.00, 0, '0', '2019-07-28 16:08:15', NULL, NULL, 0);
INSERT INTO `item_freight_charge_template` VALUES ('2', '0', ' 满98包邮', 2, 98.00, 0.00, 0, '0', '2019-07-28 16:10:11', NULL, NULL, 0);
INSERT INTO `item_freight_charge_template` VALUES ('3', '0', '在线支付免邮', 3, 0.00, 0.00, 0, '0', '2019-07-28 16:10:49', NULL, NULL, 0);
INSERT INTO `item_freight_charge_template` VALUES ('4', '0', '同城邮费', 4, 0.00, 6.00, 0, '0', '2019-07-28 16:11:54', NULL, NULL, 0);
INSERT INTO `item_freight_charge_template` VALUES ('5', '0', '三通一达邮费', 4, 0.00, 12.00, 0, '0', '2019-07-28 16:13:20', NULL, NULL, 0);
INSERT INTO `item_freight_charge_template` VALUES ('6', '0', '顺丰邮费', 4, 0.00, 18.00, 0, '0', '2019-07-28 16:14:23', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
