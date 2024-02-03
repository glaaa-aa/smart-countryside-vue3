

--  创建函数 获取上级组织名称

DELIMITER //
CREATE   FUNCTION `get_parent_organization`(rootid varchar(50)) RETURNS varchar(1000)  CHARSET utf8mb4
BEGIN
DECLARE str varchar(1000) default '';
DECLARE fid varchar(100) default '';
DECLARE temp_name varchar(100) default '';

WHILE rootId is not null  do

		SET fid =(SELECT pcode FROM qfy_sys_organization WHERE code = rootId limit 1 );
    SET temp_name =(SELECT name  FROM qfy_sys_organization WHERE code = fid limit 1 );
    IF temp_name is not null THEN
        SET str = concat(temp_name,' ',str  );
        SET rootId = fid;
ELSE
        SET rootId = fid;
END IF;
END WHILE;

RETURN str;
END







-- 修改组织表 将上级名称存入
update qfy_sys_organization set description = get_parent_organization(code);
-- 将本级也加入上级
update qfy_sys_organization set description = CONCAT(description,name);