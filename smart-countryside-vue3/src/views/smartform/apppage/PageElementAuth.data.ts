import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '租户id',
    align:"center",
    dataIndex: 'tenantId'
   },
   {
    title: '元素ID',
    align:"center",
    dataIndex: 'elementId'
   },
   {
    title: '行政区划权限',
    align:"center",
    dataIndex: 'orgCode'
   },
   {
    title: '组织机构权限',
    align:"center",
    dataIndex: 'departCode_dictText'
   },
   {
    title: '角色权限',
    align:"center",
    dataIndex: 'roleCode'
   },
   {
    title: '用户身份',
    align:"center",
    dataIndex: 'userIdentity'
   },
   {
    title: '权限控制类型',
    align:"center",
    dataIndex: 'authType'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '租户id',
    field: 'tenantId',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入租户id!'},
          ];
     },
  },
  {
    label: '元素ID',
    field: 'elementId',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入元素ID!'},
          ];
     },
  },
  {
    label: '行政区划权限',
    field: 'orgCode',
    component: 'Input',
  },
  {
    label: '组织机构权限',
    field: 'departCode',
     component: 'JSelectDept',
  },
  {
    label: '角色权限',
    field: 'roleCode',
    component: 'Input',
  },
  {
    label: '用户身份',
    field: 'userIdentity',
    component: 'InputNumber',
  },
  {
    label: '权限控制类型',
    field: 'authType',
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