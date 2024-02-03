package com.qfy.modules.city3d.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.EquipmentRelMapper;
import com.qfy.modules.city3d.po.EquipmentRel;
import com.qfy.modules.city3d.po.EquipmentRelExample;
import com.qfy.modules.city3d.service.EquipmentRelService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentRelServiceImpl implements EquipmentRelService {
	@Autowired
	private EquipmentRelMapper equipmentRelMapper;

	@Override
	public int deleteEquipmentRel(String relId) {
		EquipmentRelExample example = new EquipmentRelExample();
		example.or().andRelIdEqualTo(relId);
		int result = equipmentRelMapper.deleteByExample(example);
		return result;
	}

	@Override
	public int insertEquipmentRel(EquipmentRel equipmentRel) {
		int result = equipmentRelMapper.insert(equipmentRel);
		return result;
	}

}
