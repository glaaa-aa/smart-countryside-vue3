import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { Console } from 'console';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '水柜名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '蓄水容积(立方)',
    align: "center",
    dataIndex: 'capacity'
  },
  {
    title: '建造材料',
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
    title: '相关合同及审核材料附件',
    align: "center",
    dataIndex: 'archivalMaterial',
    slots: { customRender: 'img' },
    // scopedSlots: { customRender: 'imgSlot'}
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
    label: "水柜名称",
    field: "name",
    component: 'Input', //TODO 范围查询
    //colProps: {span: 6},
  },
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '水柜名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({ model, schema }) => {
      return [
        { required: true, message: '请输入水柜名称!' },
      ];
    },
  },
  {
    label: '蓄水容积(立方)',
    field: 'capacity',
    component: 'InputNumber',
  },
  {
    label: '建造材料',
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
