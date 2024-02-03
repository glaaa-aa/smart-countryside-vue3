import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '行政村',
        align: "center",
        dataIndex: 'orgCode_dictText'
    },
    {
        title: '片区',
        align: "center",
        dataIndex: 's1_dictText'
    },
    {
        title: '自然村屯',
        align: "center",
        dataIndex: 'aturalCode_dictText'
    },
    {
        title: '门牌号',
        align: "center",
        dataIndex: 'houseNumber'
    },
    {
        title: "户编号",
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
        title: '家庭地址',
        align: "center",
        dataIndex: 'address'
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
        title: '脱贫年度',
        align: "center",
        dataIndex: 'tuopinYear'
    },
    {
        title: '地址id',
        align: "center",
        dataIndex: 'addId'
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
        component: 'JInput',
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
    {
        label: "身份证号",
        field: 'idCard',
        component: 'Input',
        colProps: { span: 6 },
    },
];
//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '行政村',
        field: 'orgName',
        component: 'JCategorySelect',
        componentProps: {
            pcode: "", //TODO back和事件未添加，暂时有问题
        },
    },
    {
        label: '自然村屯',
        field: 'aturalName',
        component: 'JCategorySelect',
        componentProps: {
            pcode: "", //TODO back和事件未添加，暂时有问题
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
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入户主编号!'},
            ];
        },
    },
    {
        label: '户主姓名',
        field: 'name',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入户主姓名!'},
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
        label: '户类型',
        field: 'familyType',
        component: 'Input',
    },
    {
        label: '脱贫年度',
        field: 'tuopinYear',
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


/**
 * 流程表单调用这个方法获取formSchema
 * @param param
 */
export function getBpmFormSchema(_formData): FormSchema[] {
    // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
    return formSchema;
}
