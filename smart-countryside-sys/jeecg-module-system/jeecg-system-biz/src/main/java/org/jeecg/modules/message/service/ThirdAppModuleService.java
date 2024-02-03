package org.jeecg.modules.message.service;/**
 * @title: ThirdAppModuleService
 * @projectName smart-countryside-sys
 * @description: TODO
 * @author 马强
 * @date 2023/12/20 13:51
 */

import com.alibaba.fastjson.JSONObject;
import com.jeecg.dingtalk.api.core.response.Response;
import org.jeecg.common.api.dto.message.MessageDTO;
import org.jeecg.modules.system.entity.SysAnnouncement;

/**
 * @author     ：马强
 * @date       ：Created in 2023/12/20 13:51
 * @description：第三方service
 * @modified By：
 * @version:
 */
public interface ThirdAppModuleService {
    /**
     * 发送消息
     * @param message
     * @param verifyConfig 是否验证配置（未启用的APP会拒绝发送）
     * @return
     */
    boolean sendMessage(MessageDTO message, boolean verifyConfig, Integer tenantId);

    /**
     * 发送消息
     * @param message
     * @return boolean
     */
    boolean sendMessage(MessageDTO message,Integer tenantId);

    Response<String> sendActionCardMessage(SysAnnouncement announcement, boolean verifyConfig, Integer tenantId);

    JSONObject sendTextCardMessage(SysAnnouncement announcement, boolean verifyConfig, Integer tenantId);
}
