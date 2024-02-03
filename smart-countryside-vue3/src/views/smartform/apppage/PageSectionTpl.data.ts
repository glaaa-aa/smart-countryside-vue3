import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'qyid',
    align:"center",
    dataIndex: 'tenantId'
   },
   {
    title: '标题',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: '作者',
    align:"center",
    dataIndex: 'author'
   },
   {
    title: '摘要',
    align:"center",
    dataIndex: 'digest'
   },
   {
    title: 'webh5地址',
    align:"center",
    dataIndex: 'webUrl'
   },
   {
    title: 'miniUrl',
    align:"center",
    dataIndex: 'miniUrl'
   },
   {
    title: '标题图',
    align:"center",
    dataIndex: 'titleImg'
   },
   {
    title: '是否生效 0否 1是',
    align:"center",
    dataIndex: 'iseffect'
   },
   {
    title: '排序',
    align:"center",
    dataIndex: 'sortno'
   },
   {
    title: '菜单类型(0是产品1是推广 2是优惠券 3是文字）',
    align:"center",
    dataIndex: 'tplType'
   },
   {
    title: '数据列数',
    align:"center",
    dataIndex: 'dataColumn'
   },
   {
    title: '权限type  商户1 平台2  商户和平台3(1+2)',
    align:"center",
    dataIndex: 'authtype'
   },
   {
    title: '删除标识',
    align:"center",
    dataIndex: 'delFlag'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remarks'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'qyid',
    field: 'tenantId',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入qyid!'},
          ];
     },
  },
  {
    label: '标题',
    field: 'title',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入标题!'},
          ];
     },
  },
  {
    label: '作者',
    field: 'author',
    component: 'Input',
  },
  {
    label: '摘要',
    field: 'digest',
    component: 'Input',
  },
  {
    label: 'webh5地址',
    field: 'webUrl',
    component: 'Input',
  },
  {
    label: 'miniUrl',
    field: 'miniUrl',
    component: 'Input',
  },
  {
    label: '标题图',
    field: 'titleImg',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入标题图!'},
          ];
     },
  },
  {
    label: '是否生效 0否 1是',
    field: 'iseffect',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入是否生效 0否 1是!'},
          ];
     },
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
    label: '菜单类型(0是产品1是推广 2是优惠券 3是文字）',
    field: 'tplType',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入菜单类型(0是产品1是推广 2是优惠券 3是文字）!'},
          ];
     },
  },
  {
    label: '数据列数',
    field: 'dataColumn',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入数据列数!'},
          ];
     },
  },
  {
    label: '权限type  商户1 平台2  商户和平台3(1+2)',
    field: 'authtype',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入权限type  商户1 平台2  商户和平台3(1+2)!'},
          ];
     },
  },
  {
    label: '删除标识',
    field: 'delFlag',
    component: 'InputNumber',
  },
  {
    label: '备注',
    field: 'remarks',
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