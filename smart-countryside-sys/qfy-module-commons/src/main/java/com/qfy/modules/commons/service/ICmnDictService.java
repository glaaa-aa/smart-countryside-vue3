package com.qfy.modules.commons.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.commons.entity.CmnDict;
import org.jeecg.common.system.vo.DictModel;

import java.util.List;

/**
 * @Description: 关联类数据字典
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface ICmnDictService extends IService<CmnDict> {

    /**
     * 根据业务查询数据字典
     * @param refSrc 类型
     * @param refData 自定义关联ID
     * @return
     */
    public List<DictModel> dictByRef(String refSrc,String refData);

    /**
     * 根据业务查询数据字典
     * @param refSrc 类型
     * @param refData 自定义关联ID
     * @return
     */
    public CmnDict getDictByrefAndCode(CmnDict sysDict);

    /**
     * 验证编号是否存在
     * @param sysDict
     * @return
     */
    public long countDict(CmnDict sysDict);
}
