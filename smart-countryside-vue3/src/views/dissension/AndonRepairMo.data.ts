import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '调解',
    align: "center",
    dataIndex: 's2'
  },
  {
    title: '纠纷编号',
    align: "center",
    dataIndex: 'moCode'
  },
  {
    title: '主要负责人',
    align: "center",
    dataIndex: 'notifierName'
  },
  {
    title: '参与人员',
    align: "center",
    dataIndex: 's1'
  },
  {
    title: '调解时间',
    align: "center",
    dataIndex: 'shenpTime'
  },
  {
    title: '达成协议',
    align: "center",
    dataIndex: 'weixType_dictText'
  },
  {
    title: '处理状态',
    align: "center",
    dataIndex: 'weixState_dictText'
  },
  {
    title: '紧急状态',
    align: "center",
    dataIndex: 'criticalLevel_dictText'
  },
  {
    title: '处理结果',
    align: "center",
    dataIndex: 'shenpResult'
  },

];
//查询数据
export const searchFormSchema: FormSchema[] = [];
//表单数据
export const formSchema: FormSchema[] = [
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
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
