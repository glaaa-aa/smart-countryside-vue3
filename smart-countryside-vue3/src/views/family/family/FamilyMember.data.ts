import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '年份',
    align: "center",
    dataIndex: 'year'
  },
  {
    title: '户编号',
    align: "center",
    dataIndex: 'familyNumber'
  },
  {
    title: '乡村',
    align: "center",
    dataIndex: 'village'
  },
  {
    title: '行政村',
    align: "center",
    dataIndex: 'tunZone'
  },
  {
    title: '自然村屯',
    align: "center",
    dataIndex: 'aturalTun'
  },
  {
    title: '与户主关系',
    align: "center",
    dataIndex: 'familyRel_dictText'
  },
  {
    title: '姓名',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '性别',
    align: "center",
    dataIndex: 'sex_dictText'
  },
  {
    title: '身份证',
    align: "center",
    dataIndex: 'idCard',
    width: 180,
    customRender: function (text) {
      // if (text) {
      //     let prefix = text.substr(0, 6);
      //     let suffix = text.substr(14, 4);
      //     return prefix + "********" + suffix;
      // }
      return text;
    }
  },
  {
    title: '出生日期',
    align: "center",
    dataIndex: 's1'
  },
  {
    title: '年龄',
    align: "center",
    dataIndex: 's8'
  },
  {
    title: '民族',
    align: "center",
    dataIndex: 'nation_dictText'
  },
  {
    title: '联系电话',
    align: "center",
    dataIndex: 'telephone'
  },
  {
    title: '在校生情况',
    align: "center",
    dataIndex: 'inSchool_dictText'
  },
  {
    title: '政治面貌',
    align: "center",
    dataIndex: 'zhengzhiMm_dictText'
  },
  {
    title: '文化程度',
    align: "center",
    dataIndex: 'wenhuaCc_dictText'
  },
  {
    title: '健康状况',
    align: "center",
    dataIndex: 'health_dictText'
  },
  {
    title: '劳动技能',
    align: "center",
    dataIndex: 'laborSkills_dictText'
  },
  {
    title: '务工地点',
    align: "center",
    dataIndex: 'workingAddress'
  },
  {
    title: '务工单位',
    align: "center",
    dataIndex: 'workingDepart'
  },
  {
    title: '务工时间',
    align: "center",
    dataIndex: 'workingDate'
  },
  {
    title: '区外务工',
    align: "center",
    dataIndex: 's9_dictText'
  },
  {
    title: '参加居民医保',
    align: "center",
    dataIndex: 'townYiliao_dictText'
  },
  {
    title: '参加职工医保',
    align: "center",
    dataIndex: 'cityYiliao_dictText'
  },
  {
    title: '参加大病医保',
    align: "center",
    dataIndex: 'dabingYiliao_dictText'
  },
  {
    title: '医保参保时间',
    align: "center",
    dataIndex: 's4'
  },
  {
    title: '医保参保凭据',
    align: "center",
    dataIndex: 's5',
    // scopedSlots: {customRender: 'imgSlot'}
  },
  {
    title: '参加居民养老',
    align: "center",
    dataIndex: 'townYanglao_dictText'
  },
  {
    title: '参加职工养老',
    align: "center",
    dataIndex: 'cityYanglao_dictText'
  },
  {
    title: '养老参保时间',
    align: "center",
    dataIndex: 's6'
  },
  {
    title: '养老参保凭据',
    align: "center",
    dataIndex: 's7',
    // scopedSlots: {customRender: 'imgSlot'}
  },
  {
    title: '享受低保',
    align: "center",
    dataIndex: 'townMinBaozheng_dictText'
  },
  {
    title: '享受低保档次',
    align: "center",
    dataIndex: 'minBaozhengLevel_dictText'
  },
  {
    title: '特困供养人员',
    align: "center",
    dataIndex: 'supportMember_dictText'
  },
  {
    title: '是否健在',
    align: "center",
    dataIndex: 's2_dictText'
  },
  {
    title: '死亡时间',
    align: "center",
    dataIndex: 's3'
  },
  {
    title: '年份',
    align: "center",
    dataIndex: 'year'
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "户编号",
    field: "familyNumber",
    component: 'JInput', //TODO 范围查询
    colProps: {span: 6},
  },
  {
    label: "姓名",
    field: "name",
    component: 'JInput', //TODO 范围查询
    colProps: {span: 6},
  },
  {
    label: "身份证",
    field: 'idCard',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "行政区划",
    field: 'orgName',
    component: 'JTreeSelect',
    componentProps: {
      dict: "qfy_sys_organization,name,code",
      pidField: 'pcode',
      pidValue: "A02",
      hasChildField: 'has_child',
      converIsLeafVal: 1,
    },
    colProps: {span: 6},
  },
  {
    label: "年份",
    field: 'year',
    component: 'DatePicker',
    colProps: {span: 6},
    componentProps: {
      picker: 'year',
    },
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '户编号',
    field: 'familyNumber',
    component: 'Input',
    componentProps: {
      disabled: true,
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: false, message: '请输入户编号!'},
      ];
    },
  },

  {
    label: '与户主关系',
    field: 'familyRel',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'familyRelDict',
      placeholder: '请选择',
      stringToNumber: true,
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入与户主关系!'},
      ];
    },
  },


  {
    label: '身份证',
    field: 'idCard',
    component: 'Input',
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入身份证!'},
      ];
    },
  },

  {
    label: '姓名',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请输入姓名!'},
      ];
    },
  },

  {
    label: '性别',
    field: 'sex',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'sex',
    },
  },

  {
    label: '民族',
    field: 'nation',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'people_nation',
      placeholder: '请选择',
    },
  },
  {
    label: '联系电话',
    field: 'telephone',
    component: 'Input',
  },
  {
    label: '在校生情况',
    field: 'inSchool',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'inSchoolDict',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '政治面貌',
    field: 'zhengzhiMm',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'zhengzhiMmDict',
      placeholder: '请选择',
    },
  },
  {
    label: '文化程度',
    field: 'wenhuaCc',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'wenhuaCcDict',
      placeholder: '请选择',
    },
  },
  {
    label: '健康状况',
    field: 'health',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'healthDict',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '残疾属性',
    field: 'disability',
    component:"Input",
    componentProps: {
      dictCode: 'disabilityDict',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '劳动技能',
    field: 'laborSkills',
    component:"Input",
    componentProps: {
      dictCode: 'laborSkillsDict',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '务工地址',
    field: 'workingAddress',
    component: 'Input',
  },
  {
    label: '务工单位',
    field: 'workingDepart',
    component: 'Input',
  },
  {
    label: '务工时间',
    field: 'workingDate',
    component: 'DatePicker',
    componentProps: {
      showTime: false,
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '是否参加城乡居民基本医疗保险',
    field: 'townYiliao',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '是否参加城镇职工基本医疗保险',
    field: 'cityYiliao',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '是否参加大病医疗保险',
    field: 'dabingYiliao',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '是否参加城乡居民基本养老保险',
    field: 'townYanglao',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '是否参加城镇职工基本养老保险',
    field: 'cityYanglao',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '是否享受城乡居民最低生活保障',
    field: 'townMinBaozheng',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '享受城乡居民最低生活保障档次',
    field: 'minBaozhengLevel',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '开始享受城乡居民最低生活保障时间',
    field: 'minBaozhengDate',
    component: 'DatePicker',
    componentProps: {
      showTime: false,
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '开始获得特困供养时间',
    field: 'supportDate',
    component: 'DatePicker',
    componentProps: {
      showTime: false,
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '是否特困供养人员',
    field: 'supportMember',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择',
      stringToNumber: true,
    },
  },
  {
    label: '年份',
    field: 'year',
    component: 'DatePicker',
    componentProps: {
      picker: 'year',
      valueFormat: 'YYYY'
    },
    dynamicRules: ({model, schema}) => {
      return [
        {required: true, message: '请选择年份!'},
      ];
    },
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
