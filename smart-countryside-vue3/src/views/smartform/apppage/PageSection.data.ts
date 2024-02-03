import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '网站首页id',
    align:"center",
    dataIndex: 'pageId'
   },
   {
    title: '网站模板名称',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: '摘要',
    align:"center",
    dataIndex: 'digest'
   },
   {
    title: '标题图',
    align:"center",
    dataIndex: 'titleimg'
   },
   {
    title: '排序',
    align:"center",
    dataIndex: 'sortno'
   },
   {
    title: '几行',
    align:"center",
    dataIndex: 'dataRow'
   },
   {
    title: '几列',
    align:"center",
    dataIndex: 'dataCol'
   },
   {
    title: '数据总量',
    align:"center",
    dataIndex: 'dataTotal'
   },
   {
    title: '关联模板类型',
    align:"center",
    dataIndex: 'refSrc'
   },
   {
    title: '关联数据编码',
    align:"center",
    dataIndex: 'refData'
   },
   {
    title: '模板类型id',
    align:"center",
    dataIndex: 'sectionTplId'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "网站首页id",
      field: 'pageId',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "网站模板名称",
      field: 'title',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '网站首页id',
    field: 'pageId',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入网站首页id!'},
          ];
     },
  },
  {
    label: '网站模板名称',
    field: 'title',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入网站模板名称!'},
          ];
     },
  },
  {
    label: '摘要',
    field: 'digest',
    component: 'Input',
  },
  {
    label: '标题图',
    field: 'titleimg',
    component: 'Input',
  },
  {
    label: '排序',
    field: 'sortno',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入排序!'},
          ];
     },
  },
  {
    label: '几行',
    field: 'dataRow',
    component: 'InputNumber',
  },
  {
    label: '几列',
    field: 'dataCol',
    component: 'InputNumber',
  },
  {
    label: '数据总量',
    field: 'dataTotal',
    component: 'InputNumber',
  },
  {
    label: '关联模板类型',
    field: 'refSrc',
    component: 'Input',
  },
  {
    label: '关联数据编码',
    field: 'refData',
    component: 'Input',
  },
  {
    label: '模板类型id',
    field: 'sectionTplId',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入模板类型id!'},
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
