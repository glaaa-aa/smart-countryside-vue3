package com.qfy.modules.city3d.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.ManPosMapperC;
import com.qfy.modules.city3d.po.ManPos;
import com.qfy.modules.city3d.po.ManPosExample;
import com.qfy.modules.city3d.po.ManPosExample.Criteria;
import com.qfy.modules.city3d.service.ManPosService;
import org.springframework.stereotype.Service;

@Service
public class ManPosServiceImpl implements ManPosService {
	@Autowired
	private ManPosMapperC manPosMapperC;

	@Override
	public List<ManPos> getManPosAll() {
		List<ManPos> result = manPosMapperC.selectByExample(null);
		return result;
	}

	@Override
	public List<ManPos> getManPosByDn(ManPos manPos) {
		ManPosExample example = new ManPosExample();
		Criteria criteriaResult = example.or();
		if(manPos.getDeviceNum()!=null) {criteriaResult=criteriaResult.andDeviceNumEqualTo(manPos.getDeviceNum());};
		List<ManPos> result = manPosMapperC.selectByExample(example);
		return result;
	}

	@Override
	public List<ManPos> getManPosWithNameAndOrigin(String[] deviceNums) {
		List<ManPos> result = manPosMapperC.getManPosWithNameAndOrigin(deviceNums);
		return result;
	}

	@Override
	public List<ManPos> getTraceManPos2Days(Map<String, String> params) {
		List<ManPos> result = manPosMapperC.getTraceManPos2Days(params);
		return result;
	}

	@Override
	public List<ManPos> getTraceManPosInOneDay(Map<String, String> params) {
		List<ManPos> result = manPosMapperC.getTraceManPosInOneDay(params);
		return result;
	}

}
