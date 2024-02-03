package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.SysFunc;
import java.util.List;

@DS("postgresql")
public interface SysFuncMapperC {
    List<SysFunc> getFuncAndRel(String RoleId);
}
