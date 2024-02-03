package com.qfy.modules.messages.app.service;

import com.qfy.modules.messages.app.dto.MessageDTO;
import java.util.List;

public interface IAppService {

    /**
     * 获取AccessToken
     * @return String
     */
    String getAccessToken();

    /**
     * 发送消息
     *
     * @param message
     * @param verifyConfig 是否验证配置（未启用的APP会拒绝发送）
     * @return
     */
    boolean sendMessage(MessageDTO message, boolean verifyConfig, List<String> userIds);

    /**
     * 发送消息
     * @param message
     * @return boolean
     */
    boolean sendMessage(MessageDTO message, List<String> userIds);
}
