import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '机构/部门名称',
    align: 'center',
    dataIndex: 'name'
   },
   {
    title: '机构编码',
    align: 'center',
    dataIndex: 'code'
   },
   {
    title: '描述',
    align: 'center',
    dataIndex: 'description'
   },
   // {
   //  title: '是否有子节点',
   //  align: 'center',
   //  dataIndex: 'hasChild'
   // },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '父级节点',
    field: 'pid',
    component: 'JTreeSelect',
    componentProps: {
      dict: "qfy_sys_organization,name,id",
      pidField: "pid",
      pidValue: "0",
      hasChildField: "has_child",
    },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入父级节点!'},
          ];
     },
  },
  {
    label: '机构/部门名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入机构/部门名称!'},
          ];
     },
  },
  // {
  //   label: '描述',
  //   field: 'description',
  //   component: 'InputTextArea',
  // },
  // {
  //   label: '是否有子节点',
  //   field: 'hasChild',
  //   component: 'Input',
  // },
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
