package com.qfy.modules.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatGroupCreateDO {

    private Integer tenantId; //租户id
    private String name;
    private String title; // 群名称，长度限制为1~20个字符。
    private String owner; // dt userId 该员工必须为会话 useridlist 的成员之一
    private String owner_user_id;
    private String user_ids;  //群成员
    private String template_id; //群模版id 从钉钉平台设置
    private String uuid;


    /**
     * 新成员是否可查看100条历史消息：
     *
     * 1L：可查看
     *
     * 0：不可查看
     */
    private Long showHistoryType = 1L;

    /**
     * 群是否可以被搜索：
     *
     * 0（默认）：不可搜索
     *
     * 1L：可搜索
     */
    private Long searchable = 1L;

    /**
     *入群是否需要验证：
     *
     * 0（默认）：不验证
     *
     * 1L：入群验证
     */
    private Long validationType = 1L;

    /**
     * @all 使用范围：
     *
     * 0（默认）：所有人可使用
     *
     * 1L：仅群主可@all
     */
    private Long mentionAllAuthority = 0L;

    /**
     *群管理类型：
     *
     * 0（默认）：所有人可管理
     *
     * 1：仅群主可管理
     */
    private Long managementType = 1L;
    /**
     *是否开启群禁言：
     *
     * 0（默认）：不禁言
     *
     * 1：全员禁言
     */
    private Long chatBannedType = 0L;



}
