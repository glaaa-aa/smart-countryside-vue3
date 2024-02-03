package com.qfy.modules.org.mapper;

import com.qfy.modules.commons.model.TreeSelectModel;
import com.qfy.modules.org.entity.SysOrg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: qfy_sys_organization
 * @Author: jeecg-boot
 * @Date:   2022-06-01
 * @Version: V1.0
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    /**
     * 根据父级ID查询树节点数据
     * @param pid
     * @param query
     * @return
     */
    public List<TreeSelectModel> queryListByPid(@Param("pid")  String pid, @Param("query") Map<String, String> query);

    /**
     * 通过code查询分类字典表
     * @param code
     * @return
     */
    @Select("SELECT ID FROM qfy_sys_organization WHERE CODE = #{code,jdbcType=VARCHAR}")
    public String queryIdByCode(@Param("code")  String code);

    /**
     * 通过code查询分类字典表
     * @param code
     * @return
     */
    @Select("SELECT * FROM qfy_sys_organization WHERE CODE = #{code,jdbcType=VARCHAR}")
    public SysOrg queryByCode(@Param("code")  String code);

}
