package com.qfy.modules.city3d.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.qfy.modules.city3d.po.SysFunc;

public interface FuncService extends IService<SysFunc> {
	public List<SysFunc> getFunc();
	public int insert(SysFunc sysFunc);
	public int updateByPrimaryKey(SysFunc sysFunc);
	public int deleteByPrimaryKey(String funcId);
	public List<SysFunc> getFuncByLevel(SysFunc sysFunc);
	public List<SysFunc> getFuncAndRel(String roleId);
	public List<SysFunc> getFuncBySrc(String funcSrc);
	List<SysFunc> queryFuncByRoleId(String roleId,String funcSrc);
}
