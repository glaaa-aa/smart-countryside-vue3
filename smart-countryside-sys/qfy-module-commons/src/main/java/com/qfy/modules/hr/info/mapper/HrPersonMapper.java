package com.qfy.modules.hr.info.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.hr.info.entity.HrPerson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description: 人员档案
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
public interface HrPersonMapper extends BaseMapper<HrPerson> {
    public List<HrPerson> selectByIds(@Param("ids") String ids);

    /**
     * 月份有排班的所有人
     * @param mperiod
     * @return
     */
    public List<HrPerson> selectByAtteRoster(@Param("mperiod") String mperiod);

    /**
     * 根据考勤组ID，获取人员档案信息
     * @param atteGroupId
     * @return
     */
    public List<HrPerson> selectByAtteGroupId(@Param("atteGroupId") String atteGroupId);

    /**
     * 根据卡号，获取人员档案信息
     * @param cardno
     * @return
     */
    public HrPerson selectByCardNo(@Param("cardno") String cardno);

    /**
     * 根据员工号，获取人员档案信息
     * @param personNo
     * @return
     */
    HrPerson selectByPersonNo(@Param("personNo") String personNo);

    /**
     * 头像
     * @param face
     * @param empNo
     */
    @Update("update qfy_hr_person set photo = #{face} where card_no = #{empNo} ")
    public long updateFace(@Param("face") String face,@Param("empNo") String empNo);

}
