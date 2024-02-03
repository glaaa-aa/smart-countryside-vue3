package com.qfy.modules.family.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 积分记录导入实体对象
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/27 15:31
 */
@Data
public class IntegralRecordImport extends IntegralRecordExport implements Serializable {
    private static final long serialVersionUID = -7993122527176895653L;
    private String id;
}
