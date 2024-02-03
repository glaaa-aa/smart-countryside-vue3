package com.qfy.modules.messages.mail.service.impl;

import com.qfy.modules.messages.mail.entity.MailMessage;
import com.qfy.modules.messages.mail.service.IEmailService;
import java.io.File;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 邮件发送服务
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/12/20 9:56
 */
@Service
public class EmailServiceImpl implements IEmailService {

    public static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromUser;

    @Override
    public Result sendMessage(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
            helper.setTo(message.getToUsers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getContent(), message.getContentType().intValue() == 1 ? false : true);
            // 附件
            for (String attachment : message.getAttachment()) {
                File file = new File(attachment);
                if (file.exists()){
                    helper.addAttachment(file.getName(),file);
                }
            }
            mailSender.send(mimeMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }

    /**
     * 简单文本邮件，不带附件
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 11:08
     * @description:
     */
    @Override
    public Result sendText(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
        mailMessage.setTo(message.getToUsers());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getContent());
        try {
            mailSender.send(mailMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }

    /**
     * 文本邮件带附件
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 11:09
     * @description:
     */
    @Override
    public Result sendTextWithAttach(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
            helper.setTo(message.getToUsers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getContent());
            // 附件
            for (String attachment : message.getAttachment()) {
                File file = new File(attachment);
                if (file.exists()){
                    helper.addAttachment(file.getName(),file);
                }
            }
            mailSender.send(mimeMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }

    /**
     * 文本邮件可带附件或者图片
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 11:09
     * @description:
     */
    @Override
    public Result sendTextWithFiles(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
            helper.setTo(message.getToUsers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getContent());
            // 附件
            if (ArrayUtils.isNotEmpty(message.getAttachment())){
                for (String attachment : message.getAttachment()) {
                    File file = new File(attachment);
                    if (file.exists()){
                        helper.addAttachment(file.getName(),file);
                    }
                }
            }

            // 图片
            if (ArrayUtils.isNotEmpty(message.getImages())){
                for (String img : message.getImages()) {
                    File file = new File(img);
                    if (file.exists()){
                        helper.addInline(file.getName(), new FileSystemResource(file));
                    }
                }
            }
            mailSender.send(mimeMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }

    /**
     * 文本邮件带图片
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 11:13
     * @description:
     */
    @Override
    public Result sendTextWithImages(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
            helper.setTo(message.getToUsers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getContent());
            // 图片
            for (String img : message.getImages()) {
                File file = new File(img);
                if (file.exists()){
                    helper.addInline(file.getName(), new FileSystemResource(file));
                }
            }
            mailSender.send(mimeMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }

    /**
     * html网页内容邮件
     * @param message
     * @return: org.jeecg.common.api.vo.Result
     * @author: lanls
     * @date: 2022/12/20 11:14
     * @description:
     */
    @Override
    public Result sendHtml(MailMessage message) {

        if (ArrayUtils.isEmpty(message.getToUsers())){
            logger.error("邮件发送失败：没有收件人!");
            return Result.error("邮件发送失败!");
        }

        logger.info("---邮件发送开始---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(StringUtils.isNotBlank(message.getFromUser()) ? message.getFromUser() : fromUser);
            helper.setTo(message.getToUsers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getContent(), true);
            mailSender.send(mimeMessage);
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            return Result.OK("邮件发送成功!");
        } catch(Exception e) {
            logger.info("---邮件发送结束---{}", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss"));
            logger.error("邮件发送失败：", e);
            return Result.error("邮件发送失败!");
        }
    }
}
