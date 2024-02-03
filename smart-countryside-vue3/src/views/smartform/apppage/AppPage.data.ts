import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '标题',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: '发布时间',
    align:"center",
    dataIndex: 'publishTime',
    customRender:({text}) =>{
      return !text?"":(text.length>10?text.substr(0,10):text)
    },
   },
   {
    title: '摘要',
    align:"center",
    dataIndex: 'digest'
   },
   {
    title: '是否发布',
    align:"center",
    dataIndex: 'isfb_dictText'
   },
   {
    title: '页面类型',
    align:"center",
    dataIndex: 'webType'
   },
   {
    title: '页面头部颜色',
    align:"center",
    dataIndex: 'frontColor'
   },
   {
    title: '页面头部颜色',
    align:"center",
    dataIndex: 'headerBgcolor'
   },
   {
    title: '页面访问地址',
    align:"center",
    dataIndex: 'pageUrl'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remarks'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "标题",
      field: 'title',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
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
    label: '摘要',
    field: 'digest',
    component: 'Input',
  },
  {
    label: '是否发布',
    field: 'isfb',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入是否发布!'},
          ];
     },
  },
  {
    label: '页面类型',
    field: 'webType',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入页面类型!'},
          ];
     },
  },
  {
    label: '页面头部颜色',
    field: 'frontColor',
    component: 'Input',
  },
  {
    label: '页面头部颜色',
    field: 'headerBgcolor',
    component: 'Input',
  },
  {
    label: '页面访问地址',
    field: 'pageUrl',
    component: 'Input',
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
