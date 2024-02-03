package com.qfy.modules.city3d.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("sys_role_bim")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleBim {

    @TableId
    private String roleBimId;

    private String roleId;

    private String tilesId;

    @TableField(exist = false)
    private Bim bim;
}
