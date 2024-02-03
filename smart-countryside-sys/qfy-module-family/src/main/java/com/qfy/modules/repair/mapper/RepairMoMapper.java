package com.qfy.modules.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.repair.entity.RepairMo;
import com.qfy.modules.repair.entity.RepairMoLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 维修工单
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
public interface RepairMoMapper extends BaseMapper<RepairMo> {

    @Select("select * FROM sys_user where FIND_IN_SET(jurisdictions,#{jurisdictions}) and del_flag = 0")
    List<Map<String,Object>> queryUserList(@Param("jurisdictions")String jurisdictions);

    /**
     *  前端查询记录列表
     * @param page 分页参数
     * @param repairMo 条件查询
     * @return
     */
    List<RepairMo> queryRepairMoList(Page<RepairMo> page, @Param("repairMo")RepairMo repairMo);

    /**
     *  前端查询记录列表
     * @param repairMo 条件查询
     * @return
     */
    Integer queryCountList(@Param("repairMo")RepairMo repairMo);

    List<Map<String,Object>>  queryQyName(@Param("repairMo")RepairMo repairMo);

    //隐患库数量
    Integer queryCarCountList(@Param("repairMo")RepairMo repairMo);

    //任务数量
    Integer queryMoCount(@Param("repairMo")RepairMo repairMo);

    // 总数量
    Integer queryAllCount(@Param("repairMo")RepairMo repairMo);

    @Select("SELECT b.cunt,b.subcode,orz.name from (SELECT COUNT(1) cunt,a.subcode from " +
            "(select *,substring(wc_code,1,6) subcode from qv_danger_all_v1 ) " +
            "a GROUP BY a.subcode ) b LEFT JOIN qfy_sys_organization orz on  b.subcode=orz.code " +
            "where orz.`name` is not null ORDER BY cunt desc limit 0,5")
    public List<Map<String,Object>> queryQxCOUNT();

    @Select("select fault_id,fault_resons, cout from( select fault_id,fault_resons,COUNT(1) cout from qfy_andon_call_car_sub GROUP BY fault_id   UNION ALL " +
            "select fault_id,fault_resons,COUNT(1) cout from qfy_andon_call_company_sub GROUP BY fault_id   UNION ALL " +
            "select fault_id,fault_resons,COUNT(1) cout from qfy_andon_call_driver_sub GROUP BY fault_id   UNION ALL " +
            "select fault_id,fault_resons,COUNT(1) cout from qfy_andon_call_publicize_sub GROUP BY fault_id   UNION ALL " +
            "select fault_id,fault_resons,COUNT(1) cout from qfy_andon_call_road_sub GROUP BY fault_id  ) a where a.fault_resons !='其它' ORDER BY a.cout desc LIMIT 0,5")
    public List<Map<String,Object>> querySubCOUNT();

    @Select("select * from qfy_andon_repair_mo where src_doc_type=#{srcDocType} and src_doc_code=#{srcDocCode} order " +
            "by create_time desc limit 1")
    public RepairMo queryMoParn(@Param("srcDocType")String srcDocType,@Param("srcDocCode")String srcDocCode);

    @Select("select * from qfy_andon_repair_mo_log where mo_code=#{moCode} ORDER BY create_time asc,s1 asc")
    public List<RepairMoLog> queryMoLogParn(@Param("moCode")String moCode);
}
