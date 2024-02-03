import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '耕地面积（亩）',
    field: 'gengdArea',
    component: 'InputNumber',
  },
  {
    label: '林地面积（亩）',
    field: 'lindArea',
    component: 'InputNumber',
  },
  {
    label: '养殖猪数量',
    field: 'totalPig',
    component: 'InputNumber',
  },
  {
    label: '养殖牛数量',
    field: 'totalCattle',
    component: 'InputNumber',
  },
  {
    label: '养殖羊数量',
    field: 'totalSheep',
    component: 'InputNumber',
  },
  {
    label: '养殖鸡数量',
    field: 'totalChicken',
    component: 'InputNumber',
  },
  {
    label: '金融服务',
    field: 'finance',
    component: 'Input',
  },
  {
    label: '销售产品',
    field: 'sale',
    component: 'Input',
  },
  {
    label: '代种代养',
    field: 'plantBreed',
    component: 'Input',
  },
  {
    label: '技术培训',
    field: 'technicalTraining',
    component: 'Input',
  },
  {
    label: '品种改良',
    field: 'varietyImprovement',
    component: 'Input',
  },
  {
    label: '社会化服务',
    field: 'socializationService',
    component: 'Input',
  },
  {
    label: '设施建设',
    field: 'facilityConstruction',
    component: 'Input',
  },
  {
    label: '以奖代补',
    field: 'rewardCompensate',
    component: 'Input',
  },
  {
    label: '入股分红',
    field: 'stockDividends',
    component: 'Input',
  },
  {
    label: '收益分配',
    field: 'incomeDistribution',
    component: 'Input',
  },
  {
    label: '务工',
    field: 'inWorks',
    component: 'Input',
  },
  {
    label: '土地流转',
    field: 'land',
    component: 'Input',
  },
  {
    label: '边贸政策',
    field: 'trade',
    component: 'Input',
  },
  {
    label: '其它帮扶',
    field: 'others',
    component: 'Input',
  },
  {
    label: '金融服务（其他）',
    field: 'financeOthers',
    component: 'Input',
  },
  {
    label: '销售产品（其他）',
    field: 'saleOthers',
    component: 'Input',
  },
  {
    label: '代种代养（其他）',
    field: 'plantBreedOthers',
    component: 'Input',
  },
  {
    label: '技术培训（其他）',
    field: 'technicalTrainingOthers',
    component: 'Input',
  },
  {
    label: '品种改良（其他）',
    field: 'varietyImprovementOthers',
    component: 'Input',
  },
  {
    label: '社会化服务（其他）',
    field: 'socializationServiceOthers',
    component: 'Input',
  },
  {
    label: '设施建设（其他）',
    field: 'facilityConstructionOthers',
    component: 'Input',
  },
  {
    label: '以奖代补（其他）',
    field: 'rewardCompensateOthers',
    component: 'Input',
  },
  {
    label: '入股分红（其他）',
    field: 'stockDividendsOthers',
    component: 'Input',
  },
  {
    label: '收益分配（其他）',
    field: 'incomeDistributionOthers',
    component: 'Input',
  },
  {
    label: '务工（其他）',
    field: 'inWorksOthers',
    component: 'Input',
  },
  {
    label: '土地流转（其他）',
    field: 'landOthers',
    component: 'Input',
  },
  {
    label: '边贸政策（其他）',
    field: 'tradeOthers',
    component: 'Input',
  },
  {
    label: '其它帮扶（其他）',
    field: 'othersOthers',
    component: 'Input',
  },
  {
    label: '水田面积（亩）',
    field: 'paddyArea',
    component: 'InputNumber',
  },
  {
    label: '水田实种面积（亩）',
    field: 'paddyPlantArea',
    component: 'InputNumber',
  },
  {
    label: '旱地实种面积（亩）',
    field: 'dryLandPlantArea',
    component: 'InputNumber',
  },
  {
    label: '贷款类型',
    field: 'loanType',
    component: 'InputNumber',
  },
  {
    label: '贷款金额（万元）',
    field: 'loanAmount',
    component: 'InputNumber',
  },
  {
    label: '生态林面积（亩）',
    field: 'ecologicalForestArea',
    component: 'InputNumber',
  },
  {
    label: '水果类种植面积（亩）',
    field: 'fruitArea',
    component: 'InputNumber',
  },
  {
    label: '油茶种植面积（亩）',
    field: 'teaOilArea',
    component: 'InputNumber',
  },
  {
    label: '合作社、龙头企业',
    field: 'cooperative',
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
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
