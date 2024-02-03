package com.qfy.modules.smartform.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class InspectionDto implements Serializable {

	private static final long serialVersionUID = -1343686467545869236L;

	private Integer id;

	private Integer taskid;

	/** 巡检计划 */
	private Integer chkPlanId;
	/** 巡检路线 */
	private Integer chkRouteId;
	/** 巡检点 */
	private Integer chkPoiId;
	/** 编号 */
	private String serialno;
	/** 巡检点名称 */
	private String name;
	/** 点类项 */
	private String ptype;
	/** gps经度 */
	private String gpsjd;
	/** gps纬度 */
	private String gpswd;
	/** 是否合格 */
	private Integer isvalid;
	/** 该点得分 */
	private Double score;
	/** 二维码id */
	private String qrid;
	/** nfc芯片id */
	private String nfcid;
	/** 频率 */
	private Integer sortno;
	/** 照片ID */
	private String attachId;
	private Date submittime;

	private String templateId;// 模板ID

	private Integer status;

	private String submitter;// 填报人姓名

	private String telephone;// 填报人联系电话

	private String openid; // 微信、小程序、公众号id

	/** 选项值LIST */
	private List<CheckListDto> checkListDtoList;

	private List<ChkGroupPoint> groups;

}
