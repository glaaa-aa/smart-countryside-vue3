ALTER TABLE `qfy-smart-countryside`.`qfy_andon_repair_mo`
    MODIFY COLUMN `sys_org_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属行政区' AFTER `id`,
    MODIFY COLUMN `notifier` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主要负责人' AFTER `rec_date`,
    MODIFY COLUMN `notifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主要负责人姓名' AFTER `notifier`,
    MODIFY COLUMN `notifier_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人电话' AFTER `notifier_name`,
    MODIFY COLUMN `notice_depart` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门' AFTER `notifier_phone`,
    MODIFY COLUMN `shenp_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调解时间' AFTER `shenpjz_time`,
    MODIFY COLUMN `s1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与人员' AFTER `other_fee`,
    MODIFY COLUMN `s2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调解次数' AFTER `s1`,
    MODIFY COLUMN `mo_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '纠纷编号' AFTER `src_doc_code`,
    MODIFY COLUMN `duty_man` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '填报人员' AFTER `duty_depart`,
    MODIFY COLUMN `duty_man_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '填报人员uid' AFTER `duty_man`,
    MODIFY COLUMN `shenp_time` datetime NULL DEFAULT NULL COMMENT '调解时间' AFTER `shenpjz_time`;

ALTER TABLE `qfy-smart-countryside`.`qfy_andon_repair_mo_log`
    CHANGE COLUMN `opt_type` `handle_date` datetime NULL DEFAULT NULL COMMENT '受理时间' AFTER `opt_content`,
    MODIFY COLUMN `mo_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纠纷编号' AFTER `id`,
    MODIFY COLUMN `opt_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处治人员' AFTER `mo_code`,
    MODIFY COLUMN `opt_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处治人员' AFTER `opt_id`,
    MODIFY COLUMN `result_msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '反馈内容' AFTER `opt_data_json`,
    MODIFY COLUMN `s2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关材料附件' AFTER `s1`;