import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '乡村',
    align: "center",
    dataIndex: 'orgCode_dictText',
    customRender: ({ text }) => {
      return render.renderCategoryTree(text, '')
    },
  },
  {
    title: '行政村',
    align: "center",
    dataIndex: 's1_dictText'
  },
  {
    title: '自然村屯',
    align: "center",
    dataIndex: 'aturalCode_dictText',
    customRender: ({ text }) => {
      return render.renderCategoryTree(text, '')
    },
  },
  {
    title: '门牌号',
    align: "center",
    dataIndex: 'houseNumber'
  },
  {
    title: '户主编号',
    align: "center",
    dataIndex: 'familyNumber'
  },
  {
    title: '户类型',
    align: "center",
    dataIndex: 'familyType_dictText'
  },
  {
    title: '户主',
    align: "center",
    dataIndex: 'name',
  },
  {
    title: '性别',
    align: "center",
    dataIndex: 'sex_dictText'
  },
  {
    title: '家庭人口总数',
    align: "center",
    dataIndex: 'totalPerson'
  },
  {
    title: '家庭联系电话',
    align: "center",
    dataIndex: 'telephone'
  },
  {
    title: '家庭地址',
    align: "center",
    dataIndex: 'address'
  },
  {
    title: '脱贫年度',
    align: "center",
    dataIndex: 'tuopinYear'
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "户主编号",
    field: "familyNumber",
    component: 'JInput', //TODO 范围查询
    colProps: { span: 6 },
  },
  {
    label: "户主姓名",
    field: "name",
    component: 'JInput', //TODO 范围查询
    colProps: { span: 6 },
  },
  {
    label: '行政村',
    field: 'orgCode',
    component: 'JTreeSelect',
    colProps: { span: 6 },
    componentProps: {
      dict: 'qfy_sys_organization,name,code',
      pidField: 'pcode',
      pidValue: "0",
      hasChildField: 'has_child',
      converIsLeafVal: 1,
    },
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '乡村',
    field: 'orgName',
    component: 'JCategorySelect',
    componentProps:{
       pcode:"", //TODO back和事件未添加，暂时有问题
    },
  },
  {
    label: '自然村屯编号',
    field: 'aturalCode',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入自然村屯编号!'},
          ];
     },
  },
  {
    label: '自然村屯',
    field: 'aturalName',
    component: 'JCategorySelect',
    componentProps:{
       pcode:"", //TODO back和事件未添加，暂时有问题
    },
  },
  {
    label: '门牌号',
    field: 'houseNumber',
    component: 'Input',
  },
  {
    label: '户主编号',
    field: 'familyNumber',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入户主编号!'},
                 {...rules.duplicateCheckRule('qfy_family_base', 'family_number',model,schema)[0]},
          ];
     },
  },
  {
    label: '户主姓名',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入户主姓名!'},
          ];
     },
  },
  {
    label: '性别',
    field: 'sex',
    component: 'Input',
  },
  {
    label: '身份证号',
    field: 'idCard',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入身份证号!'},
                 { pattern: '^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{7}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}', message: '不符合校验规则!'},
          ];
     },
  },
  {
    label: '民族',
    field: 'nation',
    component: 'Input',
  },
  {
    label: '家庭人口总数',
    field: 'totalPerson',
    component: 'InputNumber',
  },
  {
    label: '家庭联系电话',
    field: 'telephone',
    component: 'Input',
  },
  {
    label: '家庭地址',
    field: 'address',
    component: 'Input',
  },
  {
    label: '是否搬迁户',
    field: 'isRelocate',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
  },
  {
    label: '是否居住在大中型搬迁安置区',
    field: 'isBigRelocate',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
  },
  {
    label: 'relocateOrgCode',
    field: 'relocateOrgCode',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '安置区住址',
    field: 'relocateAddress',
    component: 'Input',
  },
  {
    label: '搬迁时间',
    field: 'relocateDate',
    component: 'DatePicker',
  },
  {
    label: '户类型',
    field: 'familyType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"family_type"
     },
  },
  {
    label: '脱贫年度',
    field: 'tuopinYear',
    component: 'Input',
  },
  {
    label: '易返贫致贫户类型',
    field: 'monitorType',
    component: 'Input',
  },
  {
    label: '风险识别时间 年月',
    field: 'discernDate',
    component: 'Input',
  },
  {
    label: '风险是否消除',
    field: 'isEliminate',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn"
     },
  },
  {
    label: '备注',
    field: 'remarks',
    component: 'Input',
  },
  {
    label: 's10',
    field: 's10',
    component: 'Input',
  },
  {
    label: '地址id',
    field: 'addId',
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
//子表单数据
//子表表格配置
export const familyMemberColumns: JVxeColumn[] = [
  {
    title: '与户主关系',
    key: 'familyRel',
    type: JVxeTypes.select,
    dictCode: "familyRelDict",
    width: "160px",
    placeholder: '请输入${title}',
    defaultValue: '1',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '姓名',
    key: 'name',
    type: JVxeTypes.input,
    width: "160px",
    placeholder: '请输入${title}',
    defaultValue: '',
    validateRules: [
      { required: true, message: '${title}不能为空' },
    ],
  },
  {
    title: '性别',
    key: 'sex',
    type: JVxeTypes.select,
    dictCode: "sex",
    width: "100px",
    placeholder: '请输入${title}',
    defaultValue: '1',
  },
  {
    title: '出生日期',
    key: 's1',
    type: JVxeTypes.date,
    width: "100px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  {
    title: '年龄',
    key: 's8',
    type: JVxeTypes.inputNumber,
    width: "100px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  {
    title: '联系电话',
    key: 'telephone',
    type: JVxeTypes.inputNumber,
    width: "160px",
    placeholder: '请输入${title}',
    defaultValue: '',
  },
  {
    title: '健康状况',
    key: 'health',
    type: JVxeTypes.select,
    dictCode: "healthDict",
    width: "140px",
    placeholder: '请输入${title}',
    defaultValue: '1',
  },
  {
    title: '享受低保',
    key: 'townMinBaozheng',
    type: JVxeTypes.select,
    dictCode: "yn",
    width: "140px",
    placeholder: '请输入${title}',
    defaultValue: '0',
  },
  {
    title: '是否健在',
    key: 's2',
    type: JVxeTypes.select,
    width: "160px",
    dictCode: "yn",
    placeholder: '请输入${title}',
    defaultValue: '1',
  },
  ]
