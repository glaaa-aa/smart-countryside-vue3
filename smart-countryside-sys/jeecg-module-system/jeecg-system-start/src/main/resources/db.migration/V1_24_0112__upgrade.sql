ALTER TABLE `sys_user`
    MODIFY COLUMN `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号' AFTER `id`,
    MODIFY COLUMN `realname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名' AFTER `username`,
    MODIFY COLUMN `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码' AFTER `realname`,
    MODIFY COLUMN `sort_order` int(5) NOT NULL DEFAULT 100 COMMENT '排序, 越大越靠前' AFTER `bpm_status`,
    MODIFY COLUMN `tenant_id` int(10) NOT NULL DEFAULT 0 COMMENT '租户ID' AFTER `sort_order`,
    MODIFY COLUMN `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户类型' AFTER `tenant_id`;
