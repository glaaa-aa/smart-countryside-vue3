import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '住房面积',
    field: 'zfArea',
    component: 'InputNumber',
  },
  {
    label: '层数',
    field: 'zfFloor',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '住房等级',
    field: 'zfLevel',
    component: 'InputNumber',
  },
  {
    label: '是否危房改造户',
    field: 'isReform',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入是否危房改造户!'},
          ];
     },
  },
  {
    label: '危房改造时间',
    field: 'reformDate',
    component: 'Input',
  },
  {
    label: '危房改造资金',
    field: 'reformAmount',
    component: 'InputNumber',
  },
  {
    label: 's2',
    field: 's2',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入s2!'},
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
