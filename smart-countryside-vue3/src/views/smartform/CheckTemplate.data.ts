import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '编号',
    align:"center",
    dataIndex: 'serialno'
   },
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   // {
   //  title: '模板类型',
   //  align:"center",
   //  dataIndex: 'ttype'
   // },
   // {
   //  title: '是否需绑定巡检点执行',
   //  align:"center",
   //  dataIndex: 'toCheckpoint'
   // },
   {
    title: '是否计分',
    align:"center",
    dataIndex: 'bscore_dictText'
   },
  {
    title: '分值类型',
    align:"center",
    dataIndex: 'scoreType_dictText'
  },
   {
    title: '基础分',
    align:"center",
    dataIndex: 'baseScore'
   },

   {
    title: '报告模板名称',
    align:"center",
    dataIndex: 'docName'
   },
   {
    title: '模板类型',
    align:"center",
    dataIndex: 'dirType_dictText'
   },
   {
    title: '题目数',
    align:"center",
    dataIndex: 'surveyQunum'
   },
   {
    title: '回答次数',
    align:"center",
    dataIndex: 'answerNum'
   },
   {
    title: '可以回答的最少选项数目',
    align:"center",
    dataIndex: 'anitemLeastNum'
   },
   {
    title: '问卷状态  0默认设计状态   1执行中 2结束',
    align:"center",
    dataIndex: 'surveyState'
   },
   {
    title: '是否显示  1显示 0不显示',
    align:"center",
    dataIndex: 'visibility'
   },
   {
    title: '问卷标识 默认 0待审核   1审核通过  2审核未通过',
    align:"center",
    dataIndex: 'surveyTag'
   },
   {
    title: '静态HTML保存路径',
    align:"center",
    dataIndex: 'htmlPath'
   },
   {
    title: '静态HTML保存路径',
    align:"center",
    dataIndex: 'jsonPath'
   },
   {
    title: '是否公开结果  0不  1公开',
    align:"center",
    dataIndex: 'viewAnswer'
   },
   {
    title: '是否共享问卷  0不共享 1共享',
    align:"center",
    dataIndex: 'isShare'
   },
   {
    title: '引用次数',
    align:"center",
    dataIndex: 'excerptNum'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "模板类型",
      field: 'dirType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"template_type"
      },
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '模板/问卷名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入名称!'},
          ];
     },
  },
  // {
  //   label: '是否绑定巡检点',
  //   field: 'toCheckpoint',
  //   component: 'JDictSelectTag',
  //   componentProps:{
  //     dictCode:"yn"
  //   },
  //   dynamicRules: ({model,schema}) => {
  //         return [
  //                { required: true, message: '请选择是否绑定巡检点!'},
  //         ];
  //    },
  // },
  {
    label: '是否计分',
    field: 'bscore',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode:"yn"
    },
    dynamicRules: ({model,schema}) => {
      return [
        { required: true, message: '请选择是否计分!'},
      ];
    },
  },
  {
    label: '分值类型',
    field: 'scoreType',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode:"score_type"
    },
    dynamicRules: ({model,schema}) => {
      return [
        { required: true, message: '请选择是否计分!'},
      ];
    },
  },
  {
    label: '基础分',
    field: 'baseScore',
    component: 'InputNumber',
  },
  {
    label: '模板类型',
    field: 'dirType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"template_type"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入模板类型!'},
          ];
     },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
