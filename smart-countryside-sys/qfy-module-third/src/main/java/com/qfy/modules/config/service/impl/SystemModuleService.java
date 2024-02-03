package com.qfy.modules.config.service.impl;/**
 * @title: SystemModuleService
 * @projectName smart-countryside-sys
 * @description: TODO
 * @author 马强
 * @date 2023/12/20 13:54
 */

import com.alibaba.fastjson.JSONObject;
import com.jeecg.dingtalk.api.core.response.Response;
import org.jeecg.common.api.dto.message.MessageDTO;
import org.jeecg.modules.message.service.ThirdAppModuleService;
import org.jeecg.modules.system.entity.SysAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author     ：马强
 * @date       ：Created in 2023/12/20 13:54
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class SystemModuleService implements ThirdAppModuleService {

//    @Autowired
//    private ThirdAppDingtalkServiceImpl  thirdApDingtalkService;
//    @Autowired
//    private ThirdAppWechatEnterpriseServiceImpl thirdAppWechatEnterpriseService;

    @Override
    public boolean sendMessage(MessageDTO message, boolean verifyConfig, Integer tenantId) {
//        thirdApDingtalkService.sendMessage(message,verifyConfig,tenantId);
//        thirdAppWechatEnterpriseService.sendMessage(message,verifyConfig,tenantId);
        return false;
    }

    @Override
    public boolean sendMessage(MessageDTO message, Integer tenantId) {
//        thirdApDingtalkService.sendMessage(message,tenantId);
//        thirdAppWechatEnterpriseService.sendMessage(message,tenantId);
        return false;
    }

    @Override
    public Response<String> sendActionCardMessage(SysAnnouncement announcement, boolean verifyConfig, Integer tenantId) {
//        Response<String> response = thirdApDingtalkService.sendActionCardMessage(announcement,verifyConfig,tenantId);
//        return response;
        return null;
    }

    @Override
    public JSONObject sendTextCardMessage(SysAnnouncement announcement, boolean verifyConfig, Integer tenantId) {
//        JSONObject response = thirdAppWechatEnterpriseService.sendTextCardMessage(announcement,verifyConfig,tenantId);
//        return response;
        return null;
    }
}
