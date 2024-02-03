import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title:'企业名称',
        align:"center",
        dataIndex: 'name'
    },
    {
        title:'企业Logo',
        align:"center",
        dataIndex: 'logo',
        slots: { customRender: 'img' },
        // scopedSlots: {customRender: 'imgSlot'}
    },
    {
        title:'企业法人',
        align:"center",
        dataIndex: 'legalPerson'
    },
    {
        title:'联系电话',
        align:"center",
        dataIndex: 'telephone'
    },
    {
        title:'联系地址',
        align:"center",
        dataIndex: 'address'
    },
    {
        title:'经营范围',
        align:"center",
        dataIndex: 'businessScope',
        // scopedSlots: {customRender: 'ellipsisText'}
    },
    {
        title:'企业执照',
        align:"center",
        dataIndex: 'licenseImage',
        slots: { customRender: 'img' },
        // scopedSlots: {customRender: 'imgSlot'}
    },
    {
        title:'是否龙头企业',
        align:"center",
        dataIndex: 'isLeader_dictText'
    }
];
//查询数据
export const searchFormSchema: FormSchema[] = [
     {
      label: "企业名称",
      field: "name",
      component: 'Input', //TODO 范围查询
      //colProps: {span: 6},
	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '企业名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入企业名称!'},
          ];
     },
  },
  {
    label: '企业Logo',
    field: 'logo',
     component: 'JImageUpload',
     componentProps:{
      },
  },
  {
    label: '企业法人',
    field: 'legalPerson',
    component: 'Input',
  },
  {
    label: '企业执照',
    field: 'licenseImage',
     component: 'JImageUpload',
     componentProps:{
      },
  },
  {
    label: '是否龙头企业',
    field: 'isLeader',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入是否龙头企业!'},
          ];
     },
  },
  {
    label: '联系电话',
    field: 'telephone',
    component: 'Input',
  },
  {
    label: '公司地址',
    field: 'address',
    component: 'Input',
  },
  {
    label: '经营范围',
    field: 'businessScope',
    component: 'InputTextArea',
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
