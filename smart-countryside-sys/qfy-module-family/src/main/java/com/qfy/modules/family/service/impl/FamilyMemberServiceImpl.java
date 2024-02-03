package com.qfy.modules.family.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.mapper.FamilyBaseMapper;
import com.qfy.modules.family.mapper.FamilyMemberMapper;
import com.qfy.modules.family.model.FamilyBaseVo;
import com.qfy.modules.family.service.IFamilyMemberService;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.constant.CommonConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
@Service
public class FamilyMemberServiceImpl extends ServiceImpl<FamilyMemberMapper, FamilyMember> implements IFamilyMemberService {

	@Resource
	private FamilyBaseMapper familyBaseMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBatch(Collection<FamilyMember> entityList) {
		return super.saveBatch(entityList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(FamilyBaseVo familyBaseVo, List<FamilyMember> familyMemberList) {
//		familyBaseMapper.insert(familyBase);
		String familyNumber = familyBaseVo.getFamilyNumber();
		String year = familyBaseVo.getYear();
		int thisYear = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);// 今年
		QueryWrapper<FamilyMember> query = new QueryWrapper<>();
		query.lambda().eq(FamilyMember::getFamilyNumber,familyNumber);
		query.lambda().eq(FamilyMember::getYear, String.valueOf(thisYear));
		query.lambda().eq(FamilyMember::getDelFlag, CommonConstant.DEL_FLAG_0);// 逻辑删除

		UpdateWrapper<FamilyMember> update = new UpdateWrapper();
		update.lambda().eq(FamilyMember::getFamilyNumber, familyNumber);
		update.lambda().eq(FamilyMember::getYear, year);
		update.lambda().set(FamilyMember::getDelFlag, CommonConstant.DEL_FLAG_1);// 逻辑删除
		// 家庭成员列表不为空，则删除列表中不存在的数据,再新增或者更新数据
		if(CollectionUtils.isNotEmpty(familyMemberList)) {
			// 第一步,先根据前端删除原来的数据
			List<String> ids = familyMemberList.stream().map(FamilyMember::getId).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(ids)){
				update.lambda().notIn(FamilyMember::getId, ids.removeIf(Objects::isNull));
				this.update(update);
			}
			// 获取最新年份的数据
			List<FamilyMember> newestList = this.list(query);
			// 第二步,更新或者亲增新数据
			for(FamilyMember entity:familyMemberList) {
				for (FamilyMember member : newestList) {
					// 同一个家庭成员的数据年份相同，则修改该数据；数据年份不相同，则添加该数据；
					if (StringUtils.equals(entity.getIdCard(), member.getIdCard())){
						if (StringUtils.equals(entity.getYear(), member.getYear())){
							entity.setId(member.getId());
						}
					}
				}

				//外键设置
				entity.setYear(year);
				entity.setFamilyNumber(familyNumber);
				entity.setDelFlag(CommonConstant.DEL_FLAG_0);
				saveOrUpdate(entity);
			}

		} else {// 家庭成员列表为空，则删除该年度的数据
			this.update(update);
		}

		// 更新家庭户的人口数量
		query.lambda().eq(FamilyMember::getS2,"1");
		long count = this.count(query);
		FamilyBase familyBase = familyBaseMapper.getByfamilyNumber(familyNumber);
		familyBase.setTotalPerson(Integer.parseInt(String.valueOf(count)));
		familyBaseMapper.updateById(familyBase);
	}

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void updateMain(FamilyBaseVo familyBaseVo,List<FamilyMember> familyMemberList) {
////		familyBaseMapper.updateById(familyBase);
//
//		//1.先删除子表数据
////		familyMemberMapper.deleteByMainId(familyBase.getId());
//
//		//2.子表数据重新插入
//		if(familyMemberList!=null && familyMemberList.size()>0) {
//			for(FamilyMember entity:familyMemberList) {
//				//外键设置
//				entity.setYear(familyBaseVo.getYear());
//				entity.setFamilyNumber(familyBaseVo.getFamilyNumber());
//				familyMemberMapper.insert(entity);
//			}
//		}
//	}



	@Override
	public List<FamilyMember> selectByMainId(String mainId) {
		return baseMapper.selectByMainId(mainId);
	}

	@Override
	public List<FamilyMember> selectByGroupByIdCard(String fimalyNumber) {
		return baseMapper.selectByGroupByIdCard(fimalyNumber);
	}

}
