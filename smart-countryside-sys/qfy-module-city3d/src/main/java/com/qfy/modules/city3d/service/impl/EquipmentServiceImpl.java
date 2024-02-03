package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.EquipmentMapper;
import com.qfy.modules.city3d.po.Equipment;
import com.qfy.modules.city3d.po.EquipmentExample;
import com.qfy.modules.city3d.service.EquipmentService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public int insertEquipment(Equipment equipment) {
		int result = equipmentMapper.insert(equipment);
		return result;
	}

	@Override
	public int deleteEquipment(String equipmentId) {
		int result = equipmentMapper.deleteByPrimaryKey(equipmentId);
		return result;
	}

	@Override
	public int updateEquipment(Equipment equipment) {
		int result = equipmentMapper.updateByPrimaryKey(equipment);
		return result;
	}

	@Override
	public List<Equipment> selectEquipmentByType(String equipmentType) {
		EquipmentExample equipmentExample = new EquipmentExample();
		equipmentExample.createCriteria().andEquipmentTypeEqualTo(equipmentType);
		List<Equipment> result = equipmentMapper.selectByExample(equipmentExample);
		return result;
	}

	@Override
	public List<Equipment> selectEquipmentAllOrderByType() {
		EquipmentExample equipmentExample = new EquipmentExample();
		equipmentExample.setOrderByClause("equipment_type");
		List<Equipment> result = equipmentMapper.selectByExample(equipmentExample);
		return result;
	}

}
