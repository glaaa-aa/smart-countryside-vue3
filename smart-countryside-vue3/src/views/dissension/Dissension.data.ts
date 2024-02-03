import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
import {JVxeTypes, JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '纠纷编号',
    align: "center",
    dataIndex: 'code'
  },
  {
    title: '纠纷名称',
    align: "center",
    dataIndex: 'title'
  },
  {
    title: '纠纷内容',
    align: "center",
    dataIndex: 'content'
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
  {
    title: '纠纷类型',
    align: "center",
    dataIndex: 'category_dictText'
  },
  {
    title: '纠纷状态',
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
    title: '反馈内容',
    align: "center",
    dataIndex: 'results'
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "纠纷编号",
    field: "code",
    component: 'JInput',
  },
  {
    label: "纠纷名称",
    field: "title",
    component: 'JInput',
  },
  {
    label: "主要负责人",
    field: "s1",
    component: 'JInput',
  },
  {
    label: "涉及人员",
    field: "persons",
    component: 'JInput',
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '纠纷名称',
    field: 'title',
    component: 'Input',
  },
  {
    label: '纠纷类型',
    field: 'category',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: "disputes_type"
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入纠纷类型!'},
      ];
    },
  },
  {
    label: '发生时间',
    field: 'happenDate',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入发生时间!'},
      ];
    },
  },
  {
    label: '行政区划',
    field: 'orgCode',
    component: 'JTreeSelect',
    componentProps: {
      dict: "qfy_sys_organization,name,code",
      pidField: 'pcode',
      pidValue: "A02",
      hasChildField: 'has_child',
      converIsLeafVal: 1,
      placeholder: "请选择父级节点"
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '行政区划!'},
      ];
    }
  },
  {
    label: '发生地址',
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
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入受理时间!'},
      ];
    },
  },
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
    componentProps: {
      returnUrl: false,
      fileType: "file"
    },
    colProps: {span: 24},
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入相关材料附件!'},
      ];
    },
  },
  {
    label: '纠纷内容',
    field: 'content',
    component: 'InputTextArea',
    colProps: {span: 24},
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入矛盾纠纷内容!'},
      ];
    },
  },
  {
    label: '备注',
    field: 'remarks',
    component: 'InputTextArea',
    colProps: {span: 24},
  },
  {
    label: '达成协议',
    field: 'protocolState',
    component: 'JDictSelectTag',
    slot: 'protocolState',
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请选择达成协议!'},
      ];
    },
  },

  {
    label: '处理状态',
    field: 'state',
    component: 'JDictSelectTag',
    slot: 'state',
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请选择处理状态!',},
      ];
    },
  },

  {
    label: '紧急状态',
    field: 'emergency',
    component: 'JDictSelectTag',
    slot: 'emergency',
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请选择紧急状态!'},
      ];
    },
  },
  {
    label: '反馈内容',
    field: 'results',
    component: 'InputTextArea',
    slot: 'resultss',
    colProps: {span: 24},
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
  },

  {
    label: '反馈材料附件',
    field: 'attachments',
    component: 'JUpload',
    slot: 'attachments',
    componentProps: {returnUrl: false},
    colProps: {span: 24},
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
  },

  {
    label: '主要负责人',
    field: 'respPersons',
    component: 'JSelectHrPerson',
    slot: 'JSelectHrPerson',
    componentProps: {
      labelKey: 'cname',
      rowKey: 'personNo',
      isRadioSelection: true,
    },
    colProps: {span: 12},
    ifShow: () => {
      if (localStorage.getItem("dissensionEdit") === 'false') {
        return false;
      } else {
        return true
      }
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请选择主要负责人!'},
      ];
    },
  },

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
  {
    title: '姓名',
    key: 'realname',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      {required: true, message: '${title}不能为空'},
    ],
  },
  {
    title: '性别',
    key: 'sex',
    type: JVxeTypes.select,
    width: "100px",
    placeholder: '请输入${title}',
    defaultValue: '',
    dictCode: 'sex',
    validateRules: [
      {required: true, message: '${title}不能为空'},
    ],
  },
  {
    title: '身份证号',
    key: 'idCard',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      {required: true, message: '${title}不能为空'},
      {
        message: '请输入正确的${title}',
        pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      }
    ],
  },
  {
    title: '联系电话',
    key: 'phone',
    type: JVxeTypes.input,
    width: "150px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      {required: true, message: '${title}不能为空'},
      {
        message: '请输入正确的${title}',
        pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      }
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
    title: '备注',
    key: 'remarks',
    type: JVxeTypes.input,
    width: "200px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
]


export const andonRepairMoManList: JVxeColumn[] = [
  {
    title: '人员姓名',
    key: 'dutyMan',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      {required: true, message: '${title}不能为空'},
    ],
  },
  {
    title: '联系电话',
    key: 'phone',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      {required: true, message: '${title}不能为空'},
      {
        message: '请输入正确的${title}',
        pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      }
    ],
  },
  {
    title: '单位',
    key: 'unit',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  {
    title: '备注',
    key: 'remarks',
    type: JVxeTypes.input,
    placeholder: '请输入${title}',
    defaultValue: '',
  },
]


/**
 * 流程表单调用这个方法获取formSchema
 * @param param
 */
export function getBpmFormSchema(_formData): FormSchema[] {
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
