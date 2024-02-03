package org.jeecg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 设置静态参数初始化
 * @author: jeecg-boot
 */
@Component
@Data
public class StaticConfig {

    @Value("${jeecg.oss.accessKey:}")
    private String accessKeyId;

    @Value("${jeecg.oss.secretKey:}")
    private String accessKeySecret;

    @Value(value = "${spring.mail.username:}")
    private String emailFrom;

    @Value("${custom.config.link-card.excel-path}")
    private String linkCardExcelPath;

    @Value("${custom.config.clear-card.excel-path}")
    private String clearCardExcelPath;

    @Value("${custom.config.link-person.access-role}")
    private String linkPersonAccessRole;

//    /**
//     * 签名密钥串
//     */
//    @Value(value = "${jeecg.signatureSecret}")
//    private String signatureSecret;


    /*@Bean
    public void initStatic() {
       DySmsHelper.setAccessKeyId(accessKeyId);
       DySmsHelper.setAccessKeySecret(accessKeySecret);
       EmailSendMsgHandle.setEmailFrom(emailFrom);
    }*/


    /**
     * linux sh命令脚本路径
     */
    @Value("${custom.config.linux-cmd:/bin/sh}")
    private String linuxCmd;

    /**
     * 数据库备份目录路径
     */
    @Value("${custom.config.export-path:/opt/db/backup/}")
    private String exportPath;

    /**
     * mysqldump 导入脚本所在目录路径
     */
    @Value("${custom.config.dump-path:/usr/local/mysql/bin}")
    private String dumpPath;

    /**
     * 数据库IP
     */
    @Value("${custom.config.host:127.0.0.1}")
    private String host;

    /**
     * 数据库端口号
     */
    @Value("${custom.config.port:3306}")
    private String port;

    /**
     * 数据库密码
     */
    @Value("${custom.config.secret-key:123456}")
    private String secretKey;

    /**
     * 数据库名
     */
    @Value("${custom.config.db-name:qfy-jeecg-system}")
    private String dbName;

    /**
     * 数据库名
     */
    @Value("${custom.config.days:15}")
    private Integer backupDays;

    @Value("${custom.config.mail:to-user}")
    private String[] toUser;

    @Value("${custom.config.mail.enabled:false}")
    private Boolean enabled;


}
