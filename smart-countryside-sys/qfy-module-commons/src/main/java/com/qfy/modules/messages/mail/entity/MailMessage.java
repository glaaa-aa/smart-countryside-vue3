package com.qfy.modules.messages.mail.entity;

import lombok.Data;

/**
 * 邮件
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/12/20 10:08
 */
@Data
public class MailMessage {
    /** 发件人 */
    private String fromUser;
    /** 收件人 */
    private String[] toUsers;
    /** 邮件主题 */
    private String subject;
    /** 邮件内容 */
    private String content;
    /** 内容类型 1：text（文本），2：html（网页） 默认为文本 */
    private Integer contentType = 1;
    /** 邮件附件（路径） */
    private String[] attachment;
    /** 图片（路径） */
    private String[] images;
}
