ALTER TABLE  `qfy_dissension`
    ADD COLUMN `org_code` varchar(50) NULL COMMENT '矛盾纠纷所在地网格编号' AFTER `happen_date`,
ADD COLUMN `org_full_name` varchar(300) NULL COMMENT '矛盾纠纷所在全地址' AFTER `org_code`,
ADD COLUMN `org_name` varchar(50) NULL COMMENT '矛盾纠纷所在地' AFTER `org_full_name`,
MODIFY COLUMN `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发生地点' AFTER `happen_date`;


-- 用户 管辖行政区划编码
ALTER TABLE  `sys_user`
    ADD COLUMN `district_code` varchar(50) NULL COMMENT '管辖行政区划编码' AFTER `color_code`;


-- 根据租户设置数据字典 参考项目进度管理系统
DROP TABLE IF EXISTS `qfy_system_dict`;
CREATE TABLE `qfy_system_dict`  (
                                    `id` int(10) NOT NULL AUTO_INCREMENT,
                                    `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 字典项文本 ',
                                    `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 字典项值 ',
                                    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 描述 ',
                                    `sort_order` int(5) NULL DEFAULT NULL COMMENT ' 排序 ',
                                    `bg_color` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 状态颜色 ',
                                    `status` int(4) NULL DEFAULT NULL COMMENT ' 状态（1启用 0不启用）',
                                    `tenant_id` int(10) NULL DEFAULT NULL COMMENT ' 租户id ',
                                    `ref_src` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 关联类型 ',
                                    `ref_data` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 关联id ',
                                    `s1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' s1 ',
                                    `s2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' s2 ',
                                    `s3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' s3 ',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `item_value_2`(`item_value`, `tenant_id`, `ref_src`, `ref_data`) USING BTREE,
                                    INDEX `item_value`(`item_value`) USING BTREE,
                                    INDEX `tenant_id`(`tenant_id`) USING BTREE,
                                    INDEX `ref_src`(`ref_src`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qfy_system_dict
-- ----------------------------
INSERT INTO `qfy_system_dict` VALUES (21, '查询所有数据', '10', NULL, NULL, NULL, 1, 0, 'userIdentity', '0', NULL, NULL, NULL);
INSERT INTO `qfy_system_dict` VALUES (22, '查询分管部门', '20', NULL, NULL, NULL, 1, 0, 'userIdentity', '0', NULL, NULL, NULL);
INSERT INTO `qfy_system_dict` VALUES (23, '查询部门及下级数据', '40', NULL, NULL, NULL, 1, 0, 'userIdentity', '0', NULL, NULL, NULL);
INSERT INTO `qfy_system_dict` VALUES (24, '查询本部门数据', '50', NULL, NULL, NULL, 1, 0, 'userIdentity', '0', NULL, NULL, NULL);
INSERT INTO `qfy_system_dict` VALUES (25, '查询自己的数据 ', '60', NULL, 1, '#72349d', 1, 0, 'userIdentity', '0', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

