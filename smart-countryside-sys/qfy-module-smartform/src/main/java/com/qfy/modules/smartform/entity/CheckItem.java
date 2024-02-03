package com.qfy.modules.smartform.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import com.qfy.modules.commons.entity.BaseEntity;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 模板项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_item")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_item对象", description="模板项")
public class CheckItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;


	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private String name;
	/**检查方法 目视、手动测试或其它*/
	@Excel(name = "检查方法 目视、手动测试或其它", width = 15)
    @ApiModelProperty(value = "检查方法 目视、手动测试或其它")
    private String chkmethod;
	/**检查内容*/
	@Excel(name = "检查内容", width = 15)
    @ApiModelProperty(value = "检查内容")
    private String help;
	/**检查类项 安全检查、合规检查、质量检查等*/
	@Excel(name = "检查类项 安全检查、合规检查、质量检查等", width = 15)
    @ApiModelProperty(value = "检查类项 安全检查、合规检查、质量检查等")
    private String itemtype;
	/**默认值*/
	@Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
    private String defaultVal;
	/**是否评分 1 评分 0 不评*/
	@Excel(name = "是否评分 1 评分 0 不评", width = 15)
    @ApiModelProperty(value = "是否评分 1 评分 0 不评")
    private String bgrade;
	/**如结果是合格时的分数*/
	@Excel(name = "如结果是合格时的分数", width = 15)
    @ApiModelProperty(value = "如结果是合格时的分数")
    private Double qualifiedscore;
	/**如结果是不合格时的分数*/
	@Excel(name = "如结果是不合格时的分数", width = 15)
    @ApiModelProperty(value = "如结果是不合格时的分数")
    private Double unqualifiedscore;
	/**标准示意图*/
	@Excel(name = "标准示意图", width = 15)
    @ApiModelProperty(value = "标准示意图")
    private String vtandardPic;
	/**违规示意图*/
	@Excel(name = "违规示意图", width = 15)
    @ApiModelProperty(value = "违规示意图")
    private String violationPic;
	/**是否拍照*/
	@Excel(name = "是否拍照", width = 15)
    @ApiModelProperty(value = "是否拍照")
    private String isPic;
	/**关联模板表*/
	@Excel(name = "关联模板表", width = 15)
    @ApiModelProperty(value = "关联模板表")
    private String templateid;
	/**题干*/
	@Excel(name = "题干", width = 15)
    @ApiModelProperty(value = "题干")
    private String quTitle;
	/**排序id*/
	@Excel(name = "排序id", width = 15)
    @ApiModelProperty(value = "排序id")
    private String orderById;
	/**是非题的选项*/
	@Excel(name = "是非题的选项", width = 15)
    @ApiModelProperty(value = "是非题的选项")
    private String yesnoOption;
	/**是否必答 0非必答 1必答*/
	@Excel(name = "是否必答 0非必答 1必答", width = 15)
    @ApiModelProperty(value = "是否必答 0非必答 1必答")
    private String isRequired;
	/**题目类型*/
	@Excel(name = "题目类型", width = 15)
    @ApiModelProperty(value = "题目类型")
    private String checkType;
	/**枚举题 枚举项数目 ,评分题起始分值*/
	@Excel(name = "枚举题 枚举项数目 ,评分题起始分值", width = 15)
    @ApiModelProperty(value = "枚举题 枚举项数目 ,评分题起始分值")
    private String paramint01;
	/**评分题，最大分值*/
	@Excel(name = "评分题，最大分值", width = 15)
    @ApiModelProperty(value = "评分题，最大分值")
    private String paramint02;
	/**是否显示 0不显示   1显示*/
	@Excel(name = "是否显示 0不显示   1显示", width = 15)
    @ApiModelProperty(value = "是否显示 0不显示   1显示")
    private String visibility;
	/**如果是复制的题，则有复制于那一题*/
	@Excel(name = "如果是复制的题，则有复制于那一题", width = 15)
    @ApiModelProperty(value = "如果是复制的题，则有复制于那一题")
    private String copyFromId;
	/**1水平显示 2垂直显示*/
	@Excel(name = "1水平显示 2垂直显示", width = 15)
    @ApiModelProperty(value = "1水平显示 2垂直显示")
    private String hv;
	/**选项随机排列  1随机排列 0不随机排列*/
	@Excel(name = "选项随机排列  1随机排列 0不随机排列", width = 15)
    @ApiModelProperty(value = "选项随机排列  1随机排列 0不随机排列")
    private String randOrder;
	/**按列显示时，列数*/
	@Excel(name = "按列显示时，列数", width = 15)
    @ApiModelProperty(value = "按列显示时，列数")
    private String cellCount;
	/**填空题的宽度*/
	@Excel(name = "填空题的宽度", width = 15)
    @ApiModelProperty(value = "填空题的宽度")
    private String answerinputwidth;
	/**填空题的input 类型*/
	@Excel(name = "填空题的input 类型", width = 15)
    @ApiModelProperty(value = "填空题的input 类型")
    private String answerinputrow;
	/**填报类型  多次填报最后生效  多次填报重复生效*/
	@Excel(name = "填报类型  多次填报最后生效  多次填报重复生效", width = 15)
    @ApiModelProperty(value = "填报类型  多次填报最后生效  多次填报重复生效")
    private String txType;
	/**如设计到分段 模板版增加分段统计  项表设置到表中的段内*/
	@Excel(name = "如设计到分段 模板版增加分段统计  项表设置到表中的段内", width = 15)
    @ApiModelProperty(value = "如设计到分段 模板版增加分段统计  项表设置到表中的段内")
    private String fd;
	/**是否是大小题    1默认题  2大题  3大题下面的小题*/
	@Excel(name = "是否是大小题    1默认题  2大题  3大题下面的小题", width = 15)
    @ApiModelProperty(value = "是否是大小题    1默认题  2大题  3大题下面的小题")
    private String qutag;
	/**所属大题  只有小题才有此属性 即quTag=3的题*/
	@Excel(name = "所属大题  只有小题才有此属性 即quTag=3的题", width = 15)
    @ApiModelProperty(value = "所属大题  只有小题才有此属性 即quTag=3的题")
    private String parentQuId;
	/**单选或者多选  扣除基础分，积分清零*/
	@Excel(name = "单选或者多选  扣除基础分，积分清零", width = 15)
    @ApiModelProperty(value = "单选或者多选  扣除基础分，积分清零")
    private String xzSate;


    @Excel(name = "有效值上限", width = 15)
    @ApiModelProperty(value = "有效值上限")
    private Integer yxSx;

    @Excel(name = "有效值下限", width = 15)
    @ApiModelProperty(value = "有效值下限")
    private Integer yxXx;




    @Excel(name = "合格值上限", width = 15)
    @ApiModelProperty(value = "合格值上限")
    private Integer hgSx;

    @Excel(name = "合格值下限", width = 15)
    @ApiModelProperty(value = "合格值下限")
    private Integer hgXx;

    @Excel(name = "小数点位数", width = 15)
    @ApiModelProperty(value = "小数点位数")
    private Integer point;


    @ApiModelProperty(value = "单选题项集合")
    @TableField(exist = false)
    private List<CheckItemRadio> quRadios=new ArrayList<CheckItemRadio>();

    @ApiModelProperty(value = "多选题项集合")
    @TableField(exist = false)
    private List<CheckItemCheckbox> quCheckboxs=new ArrayList<CheckItemCheckbox>();

    @ApiModelProperty(value = "多项填空题集合")
    @TableField(exist = false)
    private List<CheckItemMultiFillblank> quMultiFillblanks=new ArrayList<CheckItemMultiFillblank>();

    @ApiModelProperty(value = "评分题行集合")
    @TableField(exist = false)
    private List<CheckItemScore> quScores=new ArrayList<CheckItemScore>();

    @ApiModelProperty(value = "排序题集合")
    @TableField(exist = false)
    private List<CheckItemOrderby> quOrderbys=new ArrayList<CheckItemOrderby>();

    //逻辑设置
    @ApiModelProperty(value = "逻辑设置集合")
    @TableField(exist = false)
    private List<CheckItemLogic> questionLogics;


    @ApiModelProperty(value = "小题集合")
    @TableField(exist = false)
    private List<CheckItem> questions=new ArrayList<CheckItem>();


}
