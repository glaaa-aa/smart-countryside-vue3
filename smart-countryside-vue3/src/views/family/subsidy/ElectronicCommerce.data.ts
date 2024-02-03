import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '电商网点名称',
        align: "center",
        dataIndex: 'name'
    },
    {
        title: '归属单位',
        align: "center",
        dataIndex: 'buildUnit'
    },
    {
        title: '建立时间',
        align: "center",
        dataIndex: 'buildTime',
        // customRender:function (text) {
        //     return !text?"":(text.length>10?text.substr(0,10):text)
        // }
    },
    {
        title: '投资资金（万元）',
        align: "center",
        dataIndex: 'buildFunds'
    },
    {
        title: '相关材料照片',
        align: "center",
        dataIndex: 'archivalMaterial',
        slots: { customRender: 'img' },
        // scopedSlots: { customRender: 'imgSlot' }
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
        label: "电商名称",
        field: "name",
        component: 'Input', //TODO 范围查询
        //colProps: {span: 6},
    },
];
//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '电商名称',
        field: 'name',
        component: 'Input',
        dynamicRules: ({ model, schema }) => {
            return [
                { required: true, message: '请输入电商名称!' },
            ];
        },
    },
    {
        label: '归属单位',
        field: 'buildUnit',
        component: 'Input',
    },
    {
        label: '建立时间',
        field: 'buildTime',
        component: 'MonthPicker',
        componentProps: {
            format: 'YYYY-MM',
            valueFormat: 'YYYY-MM'
        },
    },
    {
        label: '投资资金（万元）',
        field: 'buildFunds',
        component: 'InputNumber',
    },
    {
        label: '相关合同及审核材料附件',
        field: 'archivalMaterial',
        component: 'JImageUpload',
        componentProps: {},
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
