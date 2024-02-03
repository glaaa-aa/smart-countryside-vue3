import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '帮扶企业',
    align: "center",
    dataIndex: 'eid_dictText'
  },
  {
    title: '联农带农方式',
    align: "center",
    dataIndex: 'assistMethod_dictText'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'ext1'
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '农户编号',
    field: 'familyNumber',
    component: 'Input',
    show: false
  },
  {
    label: '帮扶企业',
    field: 'eid',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'qfy_cooperative_enterprises,name,id',
      placeholder: '请选择',
    }
  },
  {
    label: '联农带农方式',
    field: 'assistMethod',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'assistMethod',
      placeholder: '请选择',
    },

  },

  {
    label: '备注',
    field: 'ext1',
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
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
