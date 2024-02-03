DROP TABLE IF EXISTS `qfy_assist_enterprise_rel`;
CREATE TABLE `qfy_assist_enterprise_rel` (
    `id` VARCHAR(32) NOT NULL COMMENT '编码id',
    `family_number` varchar(32) NULL COMMENT '帮扶农户编号',
    `eid` varchar(32) NULL COMMENT '帮扶企业id',
    `assist_method` int(2) NULL COMMENT '联农带农方式',
    `years` varchar(32) NULL COMMENT '数据年份',
    `ext1` varchar(100) DEFAULT NULL COMMENT '预留扩展字段1',
    `ext2` varchar(100) DEFAULT NULL COMMENT '预留扩展字段2',
    `ext3` varchar(100) DEFAULT NULL COMMENT '预留扩展字段3',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='龙头企业帮扶关系表';

INSERT INTO `flyway_schema_history` VALUES (null,'1.24.0111','upgrade','SQL','V1_24_0111__upgrade.sql',0,'root',now(),1,1);
