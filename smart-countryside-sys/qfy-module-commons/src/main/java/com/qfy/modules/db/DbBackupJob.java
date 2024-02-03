package com.qfy.modules.db;

import cn.hutool.core.io.FileUtil;
import com.qfy.modules.messages.mail.entity.MailMessage;
import com.qfy.modules.messages.mail.service.IEmailService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.config.StaticConfig;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/11/11 16:42
 */
public class DbBackupJob implements Job {

    public static final Logger logger = LoggerFactory.getLogger(DbBackupJob.class);

    @Resource
    private IEmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时任务【备份数据库】开启，时间：{}", DateUtils.getTimestamp());
        backupAll();
        logger.info("定时任务【备份数据库】结束，时间：{}", DateUtils.getTimestamp());
    }

    /**
     * 备份整个数据库
     * @return: void
     * @author: lanls
     * @date: 2022/11/11 16:43
     * @description:
     */
    public void backupAll(){
        StaticConfig config = SpringContextUtils.getBean(StaticConfig.class);
        String exportPath = config.getExportPath();
        String cmdPath = config.getDumpPath();
        String host = config.getHost();
        String port = config.getPort();
        String secretKey = config.getSecretKey();
        String dbName = config.getDbName();
        int backupDays = config.getBackupDays().intValue();

        File file = new File(exportPath);
        String sqlFileName = dbName + "_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmm") + ".sql";
        if (!file.exists()){
            file.mkdirs();
        }

        Runtime runtime = Runtime.getRuntime();
        try {
            StringBuffer buffer = new StringBuffer();
            String tempFile = file.getCanonicalPath() + File.separator + sqlFileName;
            buffer.append(cmdPath).append("/mysqldump -h").append(host).append(" -P").append(port)
                                    .append(" -uroot -p").append(secretKey)
                                    .append(" --default-character-set=utf8 --databases ").append(dbName)
                                    .append(" --ignore-table=").append(dbName).append(".sys_log")
                                    .append(" >").append(tempFile);
            String command = buffer.toString();
            logger.debug("数据库导出命令：{}", command);
            String[] commands = new String[3];
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("linux")){
                commands[0] = config.getLinuxCmd();
                commands[1] = "-c";
                commands[2] = command;
            } else {
                commands[0] = "cmd";
                commands[1] = "/c";
                commands[2] = command;
            }
            Process process = runtime.exec(commands);
            String zipFile = tempFile.replace("sql","zip");
            if (process.waitFor() == 0){
                logger.debug("数据库备份成功!");
                // 以下步骤实现sql文件压缩
                final boolean exist = FileUtil.exist(zipFile);
                if (!exist){
                    FileUtil.newFile(zipFile);
                }
                byte[] buf = new byte[1024];
                final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
                File temp = new File(tempFile);
                final FileInputStream fis = new FileInputStream(temp);
                zos.putNextEntry(new ZipEntry(temp.getName()));
                int len;
                while ((len = fis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
                zos.close();
                FileUtils.deleteQuietly(temp);
                // 发送邮件
                backup2Mail(zipFile);
            } else {
                logger.error("数据库备份失败!");
            }

            // 统计备份目录下的全部文件数
            File[] files = file.listFiles();
            int length = files.length;
            // 少于最多保存天数，则跳过，不删除任何备份文件
            if (length < backupDays){
                return;
            }
            // 备份文件按时间排倒序
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    long diff = f1.lastModified() - f2.lastModified();
                    if (diff > 0){
                        return -1;
                    } else if (diff == 0){
                        return 0;
                    } else {
                        return 1;
                    }
                }

                public boolean equals(Object obj){
                    return true;
                }
            });

            // 删除前面超过备份天数的文件
            for (int i = 0; i < length; i++) {
                if (i >= backupDays){
                    FileUtils.deleteQuietly(files[i]);
                }
            }
        } catch(Exception e) {
            logger.error("数据库导出异常：", e);
        }
    }

    /**
     邮件发送
     * @return: void
     * @author: lanls
     * @date: 2022/12/20 9:48
     * @description:
     */
    public void backup2Mail(String attachment){

        StaticConfig config = SpringContextUtils.getBean(StaticConfig.class);
        if (config.getEnabled()){
            MailMessage message = new MailMessage();
            message.setToUsers(config.getToUser());
            message.setContent("数据库备份");
            message.setSubject("数据库备份");
            String[] attachments = {attachment};
            message.setAttachment(attachments);
            emailService.sendTextWithAttach(message);
        }
    }
}
