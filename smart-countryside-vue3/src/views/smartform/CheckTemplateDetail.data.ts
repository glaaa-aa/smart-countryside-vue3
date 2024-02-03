import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '关联模板id',
    align:"center",
    dataIndex: 'templateId'
   },
   {
    title: '每台电脑或手机只能答一次',
    align:"center",
    dataIndex: 'effective'
   },
   {
    title: '有效性间隔时间',
    align:"center",
    dataIndex: 'effectiveTime'
   },
   {
    title: '每个IP只能答一次 1是 0否',
    align:"center",
    dataIndex: 'effectiveIp'
   },
   {
    title: '防刷新  1启用 0不启用',
    align:"center",
    dataIndex: 'refresh'
   },
   {
    title: 'refresh=1设置',
    align:"center",
    dataIndex: 'refreshNum'
   },
   {
    title: '调查规则   1公开, 2私有, 3令牌',
    align:"center",
    dataIndex: 'rule'
   },
   {
    title: '令牌',
    align:"center",
    dataIndex: 'ruleCode'
   },
   {
    title: '结束方式1手动结束   2依据结束时间  3依据收到的份数',
    align:"center",
    dataIndex: 'endType'
   },
   {
    title: '是否依据收到的份数结束',
    align:"center",
    dataIndex: 'ynEndNum'
   },
   {
    title: '收到的份数',
    align:"center",
    dataIndex: 'endNum'
   },
   {
    title: '是否依据设置的时间结束',
    align:"center",
    dataIndex: 'ynEndTime'
   },
   {
    title: '结束时间',
    align:"center",
    dataIndex: 'endTime'
   },
   {
    title: '问卷说明',
    align:"center",
    dataIndex: 'surveyNote'
   },
   {
    title: '问卷下面有多少题目数',
    align:"center",
    dataIndex: 'surveyQuNum'
   },
   {
    title: '可以回答的最少选项数目',
    align:"center",
    dataIndex: 'anitemLeastNum'
   },
   {
    title: '可以回答的最多选项数目',
    align:"center",
    dataIndex: 'anitemMostNum'
   },
   {
    title: '只有邮件邀请唯一链接的受访者可回答  1启用 0不启用',
    align:"center",
    dataIndex: 'mailonly'
   },
   {
    title: '显示分享',
    align:"center",
    dataIndex: 'showShareSurvey'
   },
   {
    title: '静态HTML保存路径',
    align:"center",
    dataIndex: 'showAnswerDa'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '每台电脑或手机只能答一次',
    field: 'effective',
    component: 'InputNumber',
  },
  {
    label: '有效性间隔时间',
    field: 'effectiveTime',
    component: 'InputNumber',
  },
  {
    label: '每个IP只能答一次 1是 0否',
    field: 'effectiveIp',
    component: 'InputNumber',
  },
  {
    label: '防刷新  1启用 0不启用',
    field: 'refresh',
    component: 'Input',
  },
  {
    label: 'refresh=1设置',
    field: 'refreshNum',
    component: 'Input',
  },
  {
    label: '调查规则   1公开, 2私有, 3令牌',
    field: 'rule',
    component: 'Input',
  },
  {
    label: '令牌',
    field: 'ruleCode',
    component: 'Input',
  },
  {
    label: '结束方式1手动结束   2依据结束时间  3依据收到的份数',
    field: 'endType',
    component: 'Input',
  },
  {
    label: '是否依据收到的份数结束',
    field: 'ynEndNum',
    component: 'Input',
  },
  {
    label: '收到的份数',
    field: 'endNum',
    component: 'Input',
  },
  {
    label: '是否依据设置的时间结束',
    field: 'ynEndTime',
    component: 'Input',
  },
  {
    label: '结束时间',
    field: 'endTime',
    component: 'Input',
  },
  {
    label: '问卷说明',
    field: 'surveyNote',
    component: 'Input',
  },
  {
    label: '问卷下面有多少题目数',
    field: 'surveyQuNum',
    component: 'Input',
  },
  {
    label: '可以回答的最少选项数目',
    field: 'anitemLeastNum',
    component: 'Input',
  },
  {
    label: '可以回答的最多选项数目',
    field: 'anitemMostNum',
    component: 'Input',
  },
  {
    label: '只有邮件邀请唯一链接的受访者可回答  1启用 0不启用',
    field: 'mailonly',
    component: 'Input',
  },
  {
    label: '显示分享',
    field: 'showShareSurvey',
    component: 'Input',
  },
  {
    label: '静态HTML保存路径',
    field: 'showAnswerDa',
    component: 'Input',
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