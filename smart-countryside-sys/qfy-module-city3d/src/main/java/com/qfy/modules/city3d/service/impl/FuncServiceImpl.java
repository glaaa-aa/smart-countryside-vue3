package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.SysFuncMapper;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.SysFuncMapperC;
import com.qfy.modules.city3d.po.SysFunc;
import com.qfy.modules.city3d.po.SysFuncExample;
import com.qfy.modules.city3d.service.FuncService;
import org.springframework.stereotype.Service;

@Service
public class FuncServiceImpl extends ServiceImpl<SysFuncMapper, SysFunc> implements FuncService {
	@Autowired
	private SysFuncMapperC sysFuncMapperC;

	@Override
	public List<SysFunc> getFunc() {
		SysFuncExample example = new SysFuncExample();
		SysFuncExample.Criteria criteria = example.createCriteria();
		criteria.andFuncTypeNotEqualTo("不可选");
		List<SysFunc> result = baseMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(SysFunc sysFunc) {
		int result = baseMapper.insert(sysFunc);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysFunc sysFunc) {
		int result = baseMapper.updateByPrimaryKey(sysFunc);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String funcId) {
		int result = baseMapper.deleteByPrimaryKey(funcId);
		return result;
	}

	@Override
	public List<SysFunc> getFuncByLevel(SysFunc sysFunc) {
		SysFuncExample example = new SysFuncExample();
		SysFuncExample.Criteria criteria = example.createCriteria();
		criteria.andFuncLevelEqualTo(sysFunc.getFuncLevel());
		criteria.andFuncSrcEqualTo(sysFunc.getFuncSrc());
		List<SysFunc> result = baseMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<SysFunc> getFuncAndRel(String roleId) {
		List<SysFunc> result = sysFuncMapperC.getFuncAndRel(roleId);
		return result;
	}


	public List<SysFunc> getFuncBySrc(String funcSrc){
		SysFuncExample example = new SysFuncExample();
		SysFuncExample.Criteria criteria = example.createCriteria();
		criteria.andFuncSrcEqualTo(funcSrc);
		List<SysFunc> result = baseMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<SysFunc> queryFuncByRoleId(String roleId, String funcSrc) {
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("b.role_id", roleId);
		if (StringUtils.isNotBlank(funcSrc)){
			wrapper.eq("a.func_src", funcSrc);
		}
		return baseMapper.queryFuncByRoleId(wrapper);
	}
}
