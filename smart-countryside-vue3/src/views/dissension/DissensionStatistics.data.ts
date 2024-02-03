import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { JVxeTypes, JVxeColumn } from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '矛盾纠纷编号',
    align: "center",
    dataIndex: 'code'
  },
  {
    title: '矛盾纠纷名称',
    align: "center",
    dataIndex: 'title'
  },
  {
    title: '矛盾纠纷内容',
    align: "center",
    dataIndex: 'content'
  },
  {
    title: '相关材料附件',
    align: "center",
    dataIndex: 'dataFiles',
    slots: { customRender: 'file' },
  },
  {
    title: '发生时间',
    align: "center",
    dataIndex: 'happenDate'
  },
  {
    title: '受理时间',
    align: "center",
    dataIndex: 'acceptDate'
  },
  {
    title: '主要负责人',
    align: "center",
    dataIndex: 's1'
  },
  {
    title: '涉及人员',
    align: "center",
    dataIndex: 'persons'
  },
  {
    title: '涉及人员总数',
    align: "center",
    dataIndex: 'numbers'
  },
  {
    title: '发生地点',
    align: "center",
    dataIndex: 'address'
  },
  // {
  //   title: '经纬度',
  //   align: "center",
  //   dataIndex: 'lnglat'
  // },
  {
    title: '矛盾纠纷类型',
    align: "center",
    dataIndex: 'category_dictText'
  },
  {
    title: '矛盾纠纷状态',
    align: "center",
    dataIndex: 'state_dictText'
  },
  {
    title: '管辖单位',
    align: "center",
    dataIndex: 'adminUnit'
  },
  {
    title: '责任单位',
    align: "center",
    dataIndex: 'respUnit'
  },
  {
    title: '协商方式',
    align: "center",
    dataIndex: 'protocolState_dictText'
  },
  {
    title: '紧急程度',
    align: "center",
    dataIndex: 'emergency_dictText'
  },
  {
    title: '反馈内容',
    align: "center",
    dataIndex: 'results'
  },
  {
    title: '反馈材料相关附件',
    align: "center",
    dataIndex: 'attachments',
    slots: { customRender: 'file' },
  },
  // {
  //   title: '租户id',
  //   align: "center",
  //   dataIndex: 'tenantId'
  // },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remarks'
  },
  // {
  //   title: '是否删除 0：否，1：是',
  //   align: "center",
  //   dataIndex: 'delFlag'
  // },
];
//查询数据
export const searchFormSchema: FormSchema[] = [];
//表单数据
export const formSchema: FormSchema[] = [
  // {
  //   label: '矛盾纠纷编号',
  //   field: 'code',
  //   component: 'Input',
  //   dynamicRules: ({ model, schema }) => {
  //     return [
  //       { required: true, message: '请输入矛盾纠纷编号!' },
  //     ];
  //   },
  // },
  {
    label: '矛盾纠纷名称',
    field: 'title',
    component: 'Input',
  },
  {
    label: '矛盾纠纷类型',
    field: 'category',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: "disputes_type"
    },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入矛盾纠纷类型!' },
      ];
    },
  },
  // {
  //   label: '矛盾纠纷状态',
  //   field: 'state',
  //   component: 'Input',
  //   dynamicRules: ({ model, schema }) => {
  //     return [
  //       { required: true, message: '请输入矛盾纠纷状态!' },
  //     ];
  //   },
  // },
  {
    label: '发生时间',
    field: 'happenDate',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入发生时间!' },
      ];
    },
  },
  {
    label: '发生地点',
    field: 'address',
    component: 'Input',
  },
  {
    label: '受理时间',
    field: 'acceptDate',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入受理时间!' },
      ];
    },
  },
  // {
  //   label: '涉及人员',
  //   field: 'persons',
  //   component: 'Input',
  //   dynamicRules: ({model,schema}) => {
  //         return [
  //                { required: true, message: '请输入涉及人员!'},
  //         ];
  //    },
  // },
  // {
  //   label: '涉及人员总数',
  //   field: 'numbers',
  //   component: 'InputNumber',
  //   dynamicRules: ({model,schema}) => {
  //         return [
  //                { required: true, message: '请输入涉及人员总数!'},
  //         ];
  //    },
  // },
  // {
  //   label: '经纬度',
  //   field: 'lnglat',
  //   component: 'Input',
  // },
  // {
  //   label: '协商方式',
  //   field: 'protocolState',
  //   component: 'Input',
  // },
  // {
  //   label: '紧急程度',
  //   field: 'emergency',
  //   component: 'Input',
  // },
  {
    label: '管辖单位',
    field: 'adminUnit',
    component: 'Input',
  },
  {
    label: '责任单位',
    field: 'respUnit',
    component: 'Input',
  },
  {
    label: '相关材料附件',
    field: 'dataFiles',
    component: 'JUpload',
    componentProps: { returnUrl: false },
    colProps: { span: 24 },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入相关材料附件!' },
      ];
    },
  },
  // {
  //   label: '反馈材料相关附件',
  //   field: 'attachments',
  //   component: 'JUpload',
  //   colProps: { span: 12 },
  // },
  {
    label: '矛盾纠纷内容',
    field: 'content',
    component: 'InputTextArea',
    colProps: { span: 24 },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入矛盾纠纷内容!' },
      ];
    },
  },
  // {
  //   label: '反馈内容',
  //   field: 'results',
  //   component: 'InputTextArea',
  //   colProps: { span: 24 },
  // },
  // {
  //   label: '租户id',
  //   field: 'tenantId',
  //   component: 'InputNumber',
  //   show: false
  // },
  {
    label: '备注',
    field: 'remarks',
    component: 'InputTextArea',
    colProps: { span: 24 },
  },
  {
    label: '',
    field: 'key',
    component: 'InputTextArea',
    slot: 'separate',
    colProps: { span: 24 },
  },
  {
    label: '主要负责人',
    field: 'respPersons',
    component: 'JSelectUserByDept',
    slot: 'JSelectUserByDept',
    colProps: { span: 12 },
    ifShow: () => {
      if(localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请选择主要负责人!' },
      ];
    },
  },
  // {
  //   label: '是否删除 0：否，1：是',
  //   field: 'delFlag',
  //   component: 'InputNumber',
  //   show: false
  // },
  // TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
];
//子表单数据
//子表表格配置
export const dissensionInvolvedUserColumns: JVxeColumn[] = [
  // {
  //   title: '矛盾纠纷编号',
  //   key: 'tensionCode',
  //   type: JVxeTypes.input,
  //   width:"200px",
  //   placeholder: '请输入${title}',
  //   defaultValue:'',
  //     validateRules: [
  //       { required: true, message: '${title}不能为空' },
  //     ],
  // },
  {
    title: '人员姓名',
    key: 'realname',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '人员性别',
    key: 'sex',
    type: JVxeTypes.select,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
    dictCode: 'sex',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '身份证号',
    key: 'idCard',
    type: JVxeTypes.inputNumber,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '家庭地点',
    key: 'address',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  {
    title: '联系电话',
    key: 'phone',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  // {
  //   title: '租户id',
  //   key: 'tenantId',
  //   type: JVxeTypes.inputNumber,
  //   width:"200px",
  //   placeholder: '请输入${title}',
  //   defaultValue:'',
  // },
  {
    title: '备注',
    key: 'remarks',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  // {
  //   title: '是否删除 0：否，1：是',
  //   key: 'delFlag',
  //   type: JVxeTypes.inputNumber,
  //   width:"200px",
  //   placeholder: '请输入${title}',
  //   defaultValue:'',
  // },
]
export const dissensionResponsibleUserColumns: JVxeColumn[] = [
  // {
  //   title: '矛盾纠纷编号',
  //   key: 'tensionCode',
  //   type: JVxeTypes.input,
  //   width: "200px",
  //   placeholder: '请输入${title}',
  //   defaultValue: '',
  //   validateRules: [
  //     { required: true, message: '${title}不能为空' },
  //   ],
  // },
  // {
  //   title: '是否为主要负责人',
  //   key: 'princiPal',
  //   type: JVxeTypes.select,
  //   placeholder: '请输入${title}',
  //   defaultValue: '',
  //   dictCode: 'yn',
  //   validateRules: [
  //     { required: true, message: '${title}不能为空' },
  //   ],
  // },
  {
    title: '人员姓名',
    key: 'realname',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '联系电话',
    key: 'phone',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '单位',
    key: 'unit',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  // {
  //   title: '单位地点',
  //   key: 'address',
  //   type: JVxeTypes.input,
  //   width: "200px",
  //   placeholder: '请输入${title}',
  //   defaultValue: '',
  // },
  // {
  //   title: '租户id',
  //   key: 'tenantId',
  //   type: JVxeTypes.inputNumber,
  //   width: "200px",
  //   placeholder: '请输入${title}',
  //   defaultValue: '',
  // },
  {
    title: '备注',
    key: 'remarks',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  // {
  //   title: '是否删除 0：否，1：是',
  //   key: 'delFlag',
  //   type: JVxeTypes.inputNumber,
  //   width: "200px",
  //   placeholder: '请输入${title}',
  //   defaultValue: '',
  // },
]


/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
