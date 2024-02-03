package com.qfy.modules.commons.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.commons.entity.CmnDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 关联类数据字典
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface CmnDictMapper extends BaseMapper<CmnDict> {

    /**
     * 根据类型查询字典集合
     * @param refSrc
     * @param refData
     * @return
     */
    public List<CmnDict> dictByRef(@Param("refSrc") String refSrc,@Param("refData")String refData);

    /**
     * 根据类型查询字典集合
     * @param cmnDict
     * @return
     */
    public List<CmnDict> getDictByrefAndCode(@Param("cmnDict") CmnDict cmnDict);


    /**
     * 根据类型查询字典集合
     * @param cmnDict
     * @return
     */
    public long countDictByRef(@Param("cmnDict") CmnDict cmnDict);

    /**
     * 根据类型查询字典集合
     * @param cmnDict
     * @return
     */
    public long countDictByUpdateRef(@Param("cmnDict") CmnDict cmnDict);
}
