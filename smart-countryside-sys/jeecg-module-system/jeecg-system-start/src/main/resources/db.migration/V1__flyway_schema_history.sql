-- 参照Flyway的数据库脚本管理方案>创建SQL的更新记录表，方便追踪每次SQL更新的记录
drop table if exists `flyway_schema_history`;
create table `flyway_schema_history`
(
    installed_rank int(11) NOT NULL AUTO_INCREMENT,
    version        varchar(50)                         null,
    description    varchar(200)                        not null,
    type           varchar(20)                         not null,
    script         varchar(1000)                       not null,
    checksum       int                                 null,
    installed_by   varchar(100)                        not null,
    installed_on   timestamp default CURRENT_TIMESTAMP not null,
    execution_time int                                 not null,
    success        tinyint(1)                          not null,
    primary key (`installed_rank`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

-- 将本次SQL的执行记录存档
INSERT INTO flyway_schema_history VALUES (null, '1', 'flyway_schema_history', 'SQL', 'V1__flyway_schema_history.sql',0, 'root', now(), 1, 1);
