import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '矛盾纠纷编号',
    align:"center",
    dataIndex: 'tensionCode'
   },
  // {
  //   title: '人员id',
  //   align: "center",
  //   dataIndex: 'uid'
  // },
  {
    title: '人员姓名',
    align: "center",
    dataIndex: 'realname'
  },
  {
    title: '单位',
    align: "center",
    dataIndex: 'unit'
  },
  {
    title: '单位地点',
    align: "center",
    dataIndex: 'address'
  },
  {
    title: '联系电话',
    align: "center",
    dataIndex: 'phone'
  },
  {
    title: '所属部门编码',
    align: "center",
    dataIndex: 'sysOrgCode'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remarks'
  },
  {
    title: '创建人',
    align: "center",
    dataIndex: 'createBy'
  },
  {
    title: '创建时间',
    align: "center",
    dataIndex: 'createTime',
    customRender: ({ text }) => {
      return !text ? "" : (text.length > 10 ? text.substr(0, 10) : text)
    },
  },
  {
    title: '修改人',
    align: "center",
    dataIndex: 'updateBy'
  },
  {
    title: '修改时间',
    align: "center",
    dataIndex: 'updateTime',
    customRender: ({ text }) => {
      return !text ? "" : (text.length > 10 ? text.substr(0, 10) : text)
    },
  },
  //  {
  //   title: '租户id',
  //   align:"center",
  //   dataIndex: 'tenantId'
  //  },
  //  {
  //   title: '是否删除 0：否，1：是',
  //   align:"center",
  //   dataIndex: 'delFlag'
  //  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "人员姓名",
    field: "realname",
    component: 'JInput',
  },
  {
    label: "联系电话",
    field: "phone",
    component: 'JInput',
  },
  {
    label: "单位",
    field: "unit",
    component: 'JInput',
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '矛盾纠纷编号',
    field: 'tensionCode',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入矛盾纠纷编号!' },
      ];
    },
  },
  // {
  //   label: '人员id',
  //   field: 'uid',
  //   component: 'Input',
  // },
  {
    label: '人员姓名',
    field: 'realname',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入人员姓名!' },
      ];
    },
  },
  {
    label: '单位',
    field: 'unit',
    component: 'Input',
  },
  {
    label: '单位地点',
    field: 'address',
    component: 'Input',
  },
  {
    label: '联系电话',
    field: 'phone',
    component: 'Input',
  },
  // {
  //   label: '创建人',
  //   field: 'createBy',
  //   component: 'Input',
  // },
  // {
  //   label: '创建时间',
  //   field: 'createTime',
  //   component: 'DatePicker',
  // },
  // {
  //   label: '修改人',
  //   field: 'updateBy',
  //   component: 'Input',
  // },
  // {
  //   label: '修改时间',
  //   field: 'updateTime',
  //   component: 'DatePicker',
  // },
  {
    label: '所属部门编码',
    field: 'sysOrgCode',
    component: 'Input',
  },
  // {
  //   label: '租户id',
  //   field: 'tenantId',
  //   component: 'InputNumber',
  // },
  {
    label: '备注',
    field: 'remarks',
    component: 'InputTextArea',
    colProps: { span: 12 },
  },
  // {
  //   label: '是否删除 0：否，1：是',
  //   field: 'delFlag',
  //   component: 'InputNumber',
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
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
