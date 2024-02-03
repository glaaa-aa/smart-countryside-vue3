package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.SysAppMapper;
import com.qfy.modules.city3d.po.SysApp;
import com.qfy.modules.city3d.service.SysAppService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SysAppServiceImpl extends ServiceImpl<SysAppMapper, SysApp> implements SysAppService {

	@Override
	public List<SysApp> getSysApp() {
		List<SysApp> result = baseMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(SysApp sysApp) {
		int result = baseMapper.insert(sysApp);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysApp sysApp) {
		int result = baseMapper.updateByPrimaryKey(sysApp);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String appId) {
		int result = baseMapper.deleteByPrimaryKey(appId);
		return result;
	}

	@Override
	public SysApp getAppById(String appId) {
		SysApp result = baseMapper.selectByPrimaryKey(appId);
		return result;
	}
}
