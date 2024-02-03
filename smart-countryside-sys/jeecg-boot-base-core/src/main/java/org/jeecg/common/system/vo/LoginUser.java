package org.jeecg.common.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.desensitization.annotation.SensitiveField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 在线用户信息
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUser {

	/**
	 * 登录人id
	 */
	@SensitiveField
	private String id;

	/**
	 * 登录人账号
	 */
	@SensitiveField
	private String username;

	/**
	 * 登录人名字
	 */
	@SensitiveField
	private String realname;

	/**
	 * 登录人密码
	 */
	@SensitiveField
	private String password;

     /**
      * 当前登录部门code
      */
	@SensitiveField
    private String orgCode;
	/**
	 * 头像
	 */
	@SensitiveField
	private String avatar;

	/**
	 * 生日
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	/**
	 * 性别（1：男 2：女）
	 */
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@SensitiveField
	private String email;

	/**
	 * 电话
	 */
	@SensitiveField
	private String phone;

	/**
	 * 状态(1：正常 2：冻结 ）
	 */
	private Integer status;

	private Integer delFlag;
	/**
     * 同步工作流引擎1同步0不同步
     */
    private Integer activitiSync;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 *  默认:身份（10领导  20分管领导  40:部门主管   60:员工/干警）
	 */
	private Integer userIdentity;

	/**
	 * 管理部门ids
	 */
	@SensitiveField
	private String departIds;

	/**
	 * 职务，关联职务表
	 */
	@SensitiveField
	private String post;

	/**
	 * 座机号
	 */
	@SensitiveField
	private String telephone;

	/** 多租户ids临时用，不持久化数据库(数据库字段不存在) */
	@SensitiveField
	private String relTenantIds;

	/**设备id uniapp推送用*/
	private String clientId;

	private Integer userType;

	private Integer tenantId;
	/**
	 * 临时字段
	 */
	private String tenantName;
	/**
	 * 第三方登录类型
	 */
	private String thirdType;

	private String thirdUserId;


	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String salt;

	/** 管辖行政区划编码 **/
	private String districtCode;

	private String colorCode;
}
