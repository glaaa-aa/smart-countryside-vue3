package com.qfy.modules.dissension.service.impl;

import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import com.qfy.modules.dissension.mapper.AndonRepairMoManMapper;
import com.qfy.modules.dissension.service.AndonRepairMoManService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 纠纷负责处理人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Service
public class AndonRepairMoManServiceImpl extends ServiceImpl<AndonRepairMoManMapper, AndonRepairMoMan> implements AndonRepairMoManService {

	@Autowired
	private AndonRepairMoManMapper andonRepairMoManMapper;

	@Override
	public List<AndonRepairMoMan> selectByMainId(String mainId) {
		return andonRepairMoManMapper.selectByMainId(mainId);
	}

	@Override
	public AndonRepairMoMan selectByPrincipal(String code) {
		return andonRepairMoManMapper.selectByPrincipal(code);
	}
}
