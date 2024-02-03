package com.qfy.modules.dingtalk.service;/**
 * @title: ChatGroupAService
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2022/12/28 18:14
 */

import com.jeecg.dingtalk.api.core.response.Response;
import com.qfy.modules.commons.entity.ChatGroupCreateDO;

import java.util.Map;

/**
 * @author     ：马强
 * @date       ：Created in 2022/12/28 18:14
 * @description：钉钉群聊
 * @modified By：
 * @version:
 */
public interface DingtalkChatService {

    /**
     * 创建群聊
     *
     */
    public Response<Map> createChatGroup(ChatGroupCreateDO chatGroupCreateDO);



    /**
     * 分页查询所有群聊
     * @throws Exception
     */
    public void getDindtalkChat() throws Exception;


    /**
     * 发送群消息
     *
     */
    public String sendChatMessage(String chatId, String msgText,Integer tenantId);
}
