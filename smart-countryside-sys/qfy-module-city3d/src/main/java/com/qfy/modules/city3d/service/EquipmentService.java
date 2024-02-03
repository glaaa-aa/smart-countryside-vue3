package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Equipment;

public interface EquipmentService {
	public int insertEquipment(Equipment equipment);
	public int deleteEquipment(String equipmentId);
	public int updateEquipment(Equipment equipment);
	public List<Equipment> selectEquipmentByType(String equipmentType);
	public List<Equipment> selectEquipmentAllOrderByType();

}
