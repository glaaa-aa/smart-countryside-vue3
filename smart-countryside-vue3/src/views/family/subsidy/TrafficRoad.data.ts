import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '道路名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '道路长度(米)',
    align: "center",
    dataIndex: 'roadLength'
  },
  {
    title: '是否有护栏',
    align: "center",
    dataIndex: 's2_dictText'
  },
  {
    title: '护栏长度(米)',
    align: "center",
    dataIndex: 's3'
  },
  {
    title: '道路材料',
    align: "center",
    dataIndex: 'buildMaterial_dictText'
  },
  {
    title: '建造单位',
    align: "center",
    dataIndex: 'buildUnit'
  },
  {
    title: '建造时间',
    align: "center",
    dataIndex: 'buildTime',
    // customRender:function (text) {
    //     return !text?"":(text.length>10?text.substr(0,10):text)
    // }
  },
  {
    title: '建造资金（万元）',
    align: "center",
    dataIndex: 'buildFunds'
  },
  {
    title: '相关材料照片',
    align: "center",
    dataIndex: 'archivalMaterial',
    slots: { customRender: 'img' },
    // scopedSlots: {customRender: 'imgSlot'}
  },
  {
    title: '地图坐标地址',
    align: "center",
    dataIndex: 's1'
  }
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "道路名称",
    field: "name",
    component: 'Input', //TODO 范围查询
    //colProps: {span: 6},
  },
  {
    label: "建造时间",
    field: 'buildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
    //colProps: {span: 6},
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '道路名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入道路名称!' },
      ];
    },
  },
  {
    label: '道路长度(米)',
    field: 'roadLength',
    component: 'InputNumber',
  },
  {
    label: '是否有护栏',
    field: 's2_dictText',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: "yn"
    },
  },
  {
    label: '护栏总长度（米）',
    field: 's3',
    component: 'InputNumber',
  },
  {
    label: '道路材料',
    field: 'buildMaterial',
    component: 'JSearchSelect',
    componentProps: {
      dict: "buildMaterialDict",
      stringToNumber: true
    },
  },
  {
    label: '建造单位',
    field: 'buildUnit',
    component: 'Input',
  },
  {
    label: '建造时间',
    field: 'buildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '建造资金（万元）',
    field: 'buildFunds',
    component: 'InputNumber',
  },
  {
    label: '一期硬化日期',
    field: 'harden1Time',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '一期硬化投资金额（万元）',
    field: 'harden1Money',
    component: 'InputNumber',
  },
  {
    label: '二期硬化日期',
    field: 'harden2Time',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '二期硬化投资金额（万元）',
    field: 'harden2Money',
    component: 'InputNumber',
  },
  {
    label: '三期硬化日期',
    field: 'harden3Time',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '三期硬化投资金额（万元）',
    field: 'harden3Money',
    component: 'InputNumber',
  },
  {
    label: '一期护栏建造日期',
    field: 'rail1BuildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '一期护栏投资（万元）',
    field: 'rail1BuildMoney',
    component: 'InputNumber',
  },
  {
    label: '二期护栏建造日期',
    field: 'rail2BuildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '二期护栏投资（万元）',
    field: 'rail2BuildMoney',
    component: 'InputNumber',
  },
  {
    label: '三期护栏建造日期',
    field: 'rail3BuildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '三期护栏投资（万元）',
    field: 'rail3BuildMoney',
    component: 'InputNumber',
  },
  {
    label: '四期护栏建造日期',
    field: 'rail4BuildTime',
    component: 'MonthPicker',
    componentProps: {
      format: 'YYYY-MM',
      valueFormat: 'YYYY-MM'
    },
  },
  {
    label: '四期护栏投资（万元）',
    field: 'rail4BuildMoney',
    component: 'InputNumber',
  },
  {
    label: '相关合同及审核材料附件',
    field: 'archivalMaterial',
    component: 'JImageUpload',
    componentProps: {
    },
  },
  {
    label: '地图定位坐标地址',
    field: 'addId',
    component: 'JSelectCheckPosition',
    componentProps: {
      labelKey: 'addName',
      rowKey: 'addId',
      showSelected: true
    }
  },
  // {
  //   label: '预留字段',
  //   field: 's2',
  //   component: 'JDictSelectTag',
  //   componentProps: {
  //     dictCode: "yn"
  //   },
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
