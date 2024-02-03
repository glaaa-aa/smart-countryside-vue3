package com.qfy.modules.commons.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.commons.entity.CmnDict;
import com.qfy.modules.commons.mapper.CmnDictMapper;
import com.qfy.modules.commons.service.ICmnDictService;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.system.vo.DictModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 关联类数据字典
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Service
public class CmnDictServiceImpl extends ServiceImpl<CmnDictMapper, CmnDict> implements ICmnDictService {

    @Override
    public List<DictModel> dictByRef(String refSrc, String refData) {
        List<CmnDict> list = baseMapper.dictByRef(refSrc,refData);
        if(list == null) {
            return null;
        }
        List<DictModel> result = new ArrayList<>();
//        DictModel dict1 = new DictModel();
//        dict1.setValue("-9999");
//        dict1.setText("请选择");
//        result.add(dict1);

        for (CmnDict dict : list){
            DictModel dictModel = new DictModel();
            dictModel.setValue(dict.getItemValue());
            dictModel.setText(dict.getItemText());
            dictModel.setBgColor(dict.getBgColor());
            result.add(dictModel);
        }
        return result;
    }

    @Override
    public CmnDict getDictByrefAndCode(CmnDict sysDict) {
        List<CmnDict> list = baseMapper.getDictByrefAndCode(sysDict);
        if(list == null || list.size() <= 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public long countDict(CmnDict sysDict) {
        if(StringUtils.isEmpty(sysDict.getRefData())){
            sysDict.setRefData("0");
        }
        if(sysDict.getId() == null){
            return baseMapper.countDictByRef(sysDict);
        }else {
            return baseMapper.countDictByUpdateRef(sysDict);
        }
    }
}
