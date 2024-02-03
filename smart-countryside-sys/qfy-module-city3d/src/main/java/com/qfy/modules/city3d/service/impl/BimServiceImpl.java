package com.qfy.modules.city3d.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.city3d.mapper.BimMapper;
import com.qfy.modules.city3d.po.Bim;
import com.qfy.modules.city3d.service.BimService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BimServiceImpl extends ServiceImpl<BimMapper, Bim> implements BimService {
	@Resource
	private BimMapper bimMapper;

	@Override
	public List<Bim> getBim() {
		List<Bim> result = bimMapper.selectByExample(null);
		return result;
	}

	@Override
	public int insert(Bim bim) {
		int result = bimMapper.insert(bim);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Bim bim) {
		int result = bimMapper.updateByPrimaryKey(bim);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String tilesId) {
		int result = bimMapper.deleteByPrimaryKey(tilesId);
		return result;
	}

	@Override
	public int batchDelete(List<String> accessManIds) {
		int result = bimMapper.batchDelete(accessManIds);
		return result;
	}
}
