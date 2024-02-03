package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.SysRoleFuncMapper;
import com.qfy.modules.city3d.po.SysRoleFunc;
import com.qfy.modules.city3d.po.SysRoleFuncExample;
import com.qfy.modules.city3d.service.SysRoleFuncService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.stereotype.Service;

@Service
public class SysRoleFuncServiceImpl extends ServiceImpl<SysRoleFuncMapper, SysRoleFunc> implements SysRoleFuncService {

	@Override
	public int deleteSysRoleFunc(String roleId) {
		SysRoleFuncExample example = new SysRoleFuncExample();
		example.or().andRoleIdEqualTo(roleId);
		int result = baseMapper.deleteByExample(example);
		return result;
	}

	@Override
	public int insertSysRoleFunc(SysRoleFunc sysRoleFunc) {
		int result = baseMapper.insert(sysRoleFunc);
		return result;
	}

	@Override
	public void saveRolePermission(String roleId, String permissionIds, String lastPermissionIds) {
		List<String> diff = getDiff(lastPermissionIds,permissionIds);
		if(CollectionUtils.isNotEmpty(diff)) {
			List<SysRoleFunc> list = new ArrayList<>();
			for (String id : diff) {
				if(oConvertUtils.isNotEmpty(id)) {
					SysRoleFunc roleFunc = new SysRoleFunc();
					roleFunc.setRoleId(roleId);
					roleFunc.setFuncId(id);
					list.add(roleFunc);
				}
			}
			this.saveBatch(list);
		}

		List<String> diff2 = getDiff(permissionIds, lastPermissionIds);
		if (CollectionUtils.isNotEmpty(diff2)){
			UpdateWrapper<SysRoleFunc> wrapper = new UpdateWrapper<>();
			wrapper.lambda().in(SysRoleFunc::getFuncId, diff2);
			this.remove(wrapper);
		}
	}

	/**
	 * 从diff中找出main中没有的元素
	 * @param main
	 * @param diff
	 * @return
	 */
	private List<String> getDiff(String main,String diff){
		if(oConvertUtils.isEmpty(diff)) {
			return Collections.emptyList();
		}
		if(oConvertUtils.isEmpty(main)) {
			return Arrays.asList(diff.split(","));
		}

		String[] mainArr = main.split(",");
		String[] diffArr = diff.split(",");
		List<String> res = new ArrayList<String>();
		for (String key : diffArr) {
			if(oConvertUtils.isNotEmpty(key) && !ArrayUtils.contains(mainArr, key)) {
				res.add(key);
			}
		}
		return res;
	}
}
