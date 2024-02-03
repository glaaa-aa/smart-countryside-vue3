/*
 Navicat Premium Data Transfer

 Source Server         : qfydb
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.0.169:3306
 Source Schema         : qfy-smart-countryside

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 29/01/2024 09:59:24
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qfy_andon_repair_mo_man
-- ----------------------------
DROP TABLE IF EXISTS `qfy_andon_repair_mo_man`;
CREATE TABLE `qfy_andon_repair_mo_man`
(
    `id`           varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
    `send_type`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '矛盾纠纷类型',
    `mo_code`      varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '矛盾纠纷编号',
    `duty_depart`  varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人员部门',
    `duty_man_id`  varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人员id',
    `duty_man`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人',
    `weix_stdate`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开始处理时间',
    `weix_eddate`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成处理时间  ',
    `weix_times`   int(11) NULL DEFAULT NULL COMMENT '处理用时(分钟) 完成时间-开始时间',
    `stop_times`   int(11) NULL DEFAULT NULL COMMENT '实际处理工时 独立核算时，可能用得到',
    `weix_amount` double (10, 2) NULL DEFAULT NULL COMMENT '维修费用 如委外或独立核算时，可能需要计算费用',
    `weix_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容或步骤 该人维修动作的内容或步骤',
    `create_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time`  date NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time`  date NULL DEFAULT NULL COMMENT '修改时间',
    `sys_org_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门编码',
    `tenant_id`    int(11) NULL DEFAULT NULL COMMENT '租户id',
    `remarks`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `del_flag`     int(11) NULL DEFAULT 0 COMMENT '是否删除 0：否，1：是',
    `unit`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
    `address`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位地点',
    `phone`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `princi_pal`   int(10) NULL DEFAULT NULL COMMENT '主要负责人 0：否，1：是',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修保养派工记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qfy_andon_repair_mo_man
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
