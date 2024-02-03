import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '栏目名称',
    align: 'left',
    dataIndex: 'name',
  },
  {
    title: '栏目编码',
    align: 'left',
    dataIndex: 'nameCode',
  },
  {
    title: '栏目分类',
    align: 'left',
    dataIndex: 'type_dictText',
  },
  {
    title: '排序',
    align: 'left',
    dataIndex: 'sort',
  },
  {
    title: '是否显示',
    align: 'left',
    dataIndex: 'isShow_dictText',
  },
  // {
  //   title: '是否导航',
  //   align: 'left',
  //   dataIndex: 'isShowIndex_dictText',
  // },
  {
    title: '显示类型',
    align: 'left',
    dataIndex: 'showType_dictText',
  },
  {
    title: '简介',
    align: 'left',
    dataIndex: 'introduction',
  },

];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: '栏目编码',
    field: 'nameCode',
    component: 'Input',
    colProps: { span: 5 },
  },
  {
    label: '栏目名称',
    field: 'name',
    component: 'JInput',
    colProps: { span: 5 },
  },
  {
    label: '是否显示',
    field: 'isShow',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
    colProps: { span: 5 },
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '栏目名称',
    field: 'name',
    component: 'Input',
    dynamicRules: () => {
      return [{ required: true, message: '请输入栏目名称!' }];
    },
  },
  {
    label: '栏目编码',
    field: 'nameCode',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
        { ...rules.duplicateCheckRule('qfy_cms_section', 'name_code', model, schema, true)[0] },
        { required: true, message: '请输入栏目编码!' },
      ];
    },
  },
  {
    label: '上级栏目',
    field: 'pid',
    component: 'JTreeSelect',
    componentProps: {
      dict: "qfy_cms_section,name,id",
      pidField: "pid",
      pidValue: "0",
      hasChildField: "has_child",
    },
  },
  {
    label: '栏目分类',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'section_type',
      placeholder: '请选择',
    },
    dynamicRules: () => {
      return [{ required: true, message: '请选择栏目分类!' }];
    },
  },
  {
    label: '排序',
    field: 'sort',
    component: 'InputNumber',
    defaultValue:1,
    dynamicRules: () => {
      return [{ required: true, message: '请填写排序!' }];
    },
  },
  {
    label: '是否显示',
    field: 'isShow',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
    dynamicRules: ({ model, schema }) => {
      return [{ required: true, message: '请输入是否显示!' }];
    },
  },

  // {
  //   label: '是否导航',
  //   field: 'isShowIndex',
  //   component: 'JDictSelectTag',
  //   componentProps: {
  //     dictCode: 'yn',
  //     placeholder: '请选择',
  //     stringToNumber: true,
  //   },
  // },
  {
    label: '显示类型',
    field: 'showType',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'showType',
      placeholder: '请选择',
      rows: 5,
    },
  },
  // {
  //   label: '外部链接',
  //   field: 'exteriorPath',
  //   component: 'Input',
  // },

  // {
  //   label: '新窗口打开',
  //   field: 'isBlank',
  //   component: 'JDictSelectTag',
  //   componentProps: {
  //     dictCode: 'yn',
  //     placeholder: '请选择',
  //     stringToNumber: true,
  //   },
  // },
  // {
  //     label: '关键字',
  //     field: 'metaKeyword',
  //     component: 'Input',
  // },

  {
    label: '缩略图',
    field: 'titleImg',
    component: 'JImageUpload',
    componentProps: {},
    colProps: {
      span: 24,
    },
  },
  {
    label: '简介',
    field: 'introduction',
    component: 'InputTextArea',
    // componentProps:{
    //   showCount: true, //是否显示字数
    // },
    colProps: {
      span: 24,
    },
  },
  {
    label: '描述',
    field: 'description',
    component: 'JEditor',
    colProps: {
      span: 24,
    },
  },
  {
    label: 'ID',
    field: 'id',
    component: 'Input',
    show: false,
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
