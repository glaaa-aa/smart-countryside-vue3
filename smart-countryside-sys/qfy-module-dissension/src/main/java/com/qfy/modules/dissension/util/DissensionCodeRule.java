package com.qfy.modules.dissension.util;

import com.alibaba.fastjson.JSONObject;
import com.qfy.modules.dissension.mapper.DissensionMapper;
import org.jeecg.common.handler.IFillRuleHandler;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.SpringContextUtils;

import java.util.Date;

/**
 * 填值规则Demo：生成订单号
 * 使用FillRuleUtil 表结构 sys_fill_rule
 */
public class DissensionCodeRule implements IFillRuleHandler {

    // 用于自增序列号 当为空的时候 自动加载数据，
    static private Integer autoIncreaseNo = -1;
    //yyyyMMdd 用于日期判断 当属于不同日期的时候 自增序号需要自增
    static private String startDate = null;

    /**
     *
     * @Title: reload
     * @Description: 重新初始化
     * @throws
     */
    public static void reload(String prefix) {
        DissensionMapper baseMapper = (DissensionMapper) SpringContextUtils.getBean(DissensionMapper.class);
        startDate = prefix;
        Integer  maxNo = baseMapper.getMaxDissensionCode(prefix);
        if(maxNo == null){
            maxNo = 0;
        }
        // 设置初始值
        autoIncreaseNo = maxNo;
    }

    /**
     *
     * @Title: nextNO
     * @Description: 获取下一个订单号
     * @return
     * @throws
     */
    private static String nextNO() {
        String date  = DateUtils.formatDate(new Date(),"yyyyMMdd");
        if(autoIncreaseNo == -1 || !startDate.equals(date) ){
            reload(date);
        }
        //自增
        autoIncreaseNo++;
        return date + "-" + String.format("%03d", autoIncreaseNo);
    }


    @Override
    public Object execute(JSONObject params, JSONObject formData) {
        //前缀判断
//        String prefix = "";
//        if (formData != null) {
//            Object obj = formData.get("prefix");
//            if(obj != null) prefix = obj.toString();
//        }
        //获取下一个任务
        String nextNo = nextNO();
        return  nextNo;
    }

}
