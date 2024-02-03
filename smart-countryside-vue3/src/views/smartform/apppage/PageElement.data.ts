import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '上级id',
    align: 'center',
    dataIndex: 'pid'
  },
  {
    title: '组编号',
    align: 'center',
    dataIndex: 'groupNo'
  },
  {
    title: '标题',
    align: 'center',
    dataIndex: 'title'
  },
  {
    title: '摘要',
    align: 'center',
    dataIndex: 'digest'
  },
  {
    title: '跳转方式',
    align: 'center',
    dataIndex: 'jumpMode_dictText'
  },
  {
    title: '外部链接或内容图链接',
    align: 'center',
    dataIndex: 'exteriorPath'
  },
  {
    title: '标题图',
    align: 'center',
    dataIndex: 'titleImg'
  },
  {
    title: '是否生效',
    align: 'center',
    dataIndex: 'iseffect_dictText'
  },
  {
    title: '阅读次数',
    align: 'center',
    dataIndex: 'readyCount'
  },
  {
    title: '排序',
    align: 'center',
    dataIndex: 'sortno'
  },
  {
    title: '菜单类型',
    align: 'center',
    dataIndex: 'menuType'
  },
  {
    title: '背景图',
    align: 'center',
    dataIndex: 'bgImg'
  },
  {
    title: 'app/ios 跳转路径',
    align: 'center',
    dataIndex: 'appforward'
  },
  {
    title: '是否使用角色权限',
    align: 'center',
    dataIndex: 'hasRoleAuth'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "组编号",
    field: "groupNo",
    component: 'Input',
    //colProps: {span: 6},
  },
  {
    label: "标题",
    field: "title",
    component: 'Input',
    //colProps: {span: 6},
  },
  {
    label: "跳转方式(0是链接 1是图片)",
    field: "jumpMode",
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "yn"
    },
    //colProps: {span: 6},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '上级id',
    field: 'pid',
    component: 'JTreeSelect',
    componentProps: {
      dict: "qfy_app_page_element,title,id",
      pidField: "pid",
      pidValue: "0",
      hasChildField: "has_child",
      converIsLeafVal: 1,
    },
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入上级id!' },
             ];
    },
  },
  {
    label: '组编号',
    field: 'groupNo',
    component: 'Input',
  },
  {
    label: '标题',
    field: 'title',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入标题!' },
             ];
    },
  },
  {
    label: '摘要',
    field: 'digest',
    component: 'Input',
  },
  {
    label: '跳转方式',
    field: 'jumpMode',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode: "yn"
     },
  },
  {
    label: '外部链接或内容图链接',
    field: 'exteriorPath',
    component: 'Input',
  },
  {
    label: '标题图',
    field: 'titleImg',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入标题图!' },
             ];
    },
  },
  {
    label: '是否生效',
    field: 'iseffect',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode: ""
     },
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入是否生效 0否 1是!' },
             ];
    },
  },
  {
    label: '阅读次数',
    field: 'readyCount',
    component: 'InputNumber',
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入阅读次数!' },
             ];
    },
  },
  {
    label: '排序',
    field: 'sortno',
    component: 'InputNumber',
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入排序!' },
             ];
    },
  },
  {
    label: '菜单类型',
    field: 'menuType',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
              { required: true, message: '请输入菜单类型!' },
             ];
    },
  },
  {
    label: '背景图',
    field: 'bgImg',
    component: 'Input',
  },
  {
    label: 'app/ios 跳转路径',
    field: 'appforward',
    component: 'Input',
  },
  {
    label: '是否使用角色权限',
    field: 'hasRoleAuth',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
