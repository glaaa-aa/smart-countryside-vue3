package com.qfy.modules.messages.mail.service;

import com.qfy.modules.messages.mail.entity.MailMessage;
import org.jeecg.common.api.vo.Result;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/12/20 9:55
 */
public interface IEmailService {

    /**
     * 发送文本或者html内容邮件（不带附件和图片）
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendMessage(MailMessage message);

    /**
     * 发送文本邮件（不带附件）
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendText(MailMessage message);

    /**
     * 发送文本邮件（带附件）
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendTextWithAttach(MailMessage message);

    /**
     * 发送文本邮件（可带附件或者图片）
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendTextWithFiles(MailMessage message);

    /**
     * 发送文本邮件（带图片）
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendTextWithImages(MailMessage message);

    /**
     * 发送网页邮件
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 10:01
     * @description:
     */
    Result sendHtml(MailMessage message);

}
