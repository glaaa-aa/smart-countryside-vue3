package com.qfy.modules.repair.job;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @author lanls
 * @version 1.0
 * @date 2023/5/16 23:18
 */

@Data
@TableName("qfy_danger_add_log")
public class DangerAddJobLog {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /** 任务id */
    private String jobId;
    /** 任务日志id */
    private String logId;
    /** 类型 */
    private String type;
    /** 创建日期 */
    private Date createTime;
}
