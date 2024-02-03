package org.jeecg.modules.message.handle.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.dto.message.MessageDTO;
import org.jeecg.modules.message.handle.ISendMsgHandle;
import org.jeecg.modules.message.service.ThirdAppModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发钉钉消息模板
 * @author: jeecg-boot
 */
@Slf4j
@Component("ddSendMsgHandle")
public class DdSendMsgHandle implements ISendMsgHandle {

	@Autowired
	private ThirdAppModuleService thirdAppModuleService;

	@Override
	public void sendMsg(String esReceiver, String esTitle, String esContent) {
		log.info("发微信消息模板");
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setToUser(esReceiver);
		messageDTO.setTitle(esTitle);
		messageDTO.setContent(esContent);
		messageDTO.setToAll(false);
		messageDTO.setTenantId(0);
		sendMessage(messageDTO);
	}

	@Override
	public void sendMessage(MessageDTO messageDTO) {
		thirdAppModuleService.sendMessage(messageDTO, true,messageDTO.getTenantId());
	}

}
