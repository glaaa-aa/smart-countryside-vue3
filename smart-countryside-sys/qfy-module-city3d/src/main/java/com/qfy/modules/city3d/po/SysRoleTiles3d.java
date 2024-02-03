package com.qfy.modules.city3d.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("sys_role_tiles3d")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleTiles3d {

    @TableId(type = IdType.ASSIGN_ID)
    private String roleTilesId;

    private String roleId;

    private String tilesId;

    @TableField(exist = false)
    private Tiles3d tiles3d;
}
