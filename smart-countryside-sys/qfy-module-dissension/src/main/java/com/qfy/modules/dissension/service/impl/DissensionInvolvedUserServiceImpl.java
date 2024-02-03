package com.qfy.modules.dissension.service.impl;

import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import com.qfy.modules.dissension.mapper.DissensionInvolvedUserMapper;
import com.qfy.modules.dissension.service.IDissensionInvolvedUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 矛盾纠纷涉及人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Service
public class DissensionInvolvedUserServiceImpl extends ServiceImpl<DissensionInvolvedUserMapper, DissensionInvolvedUser> implements IDissensionInvolvedUserService {

	@Autowired
	private DissensionInvolvedUserMapper dissensionInvolvedUserMapper;

	@Override
	public List<DissensionInvolvedUser> selectByMainId(String mainId) {
		return dissensionInvolvedUserMapper.selectByMainId(mainId);
	}
}
