import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';

import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';

//列表数据
export const columns: BasicColumn[] = [
    {
        title: '乡村',
        align: "center",
        dataIndex: 'orgCode_dictText'
    },
    {
        title: '行政村',
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
        title: '家庭类型',
        align: "center",
        dataIndex: 'familyType_dictText'
    },
    {
        title: '家庭人口',
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
        dataIndex: 'address',
    },
    {
        title: '脱贫年度',
        align: "center",
        dataIndex: 'tuopinYear',

    },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
    {
        label: "户主编号",
        field: "familyNumber",
        component: 'JInput', //TODO 范围查询
        colProps: {span: 6},
    },
    {
        label: "户主姓名",
        field: "name",
        component: 'JInput', //TODO 范围查询
        colProps: {span: 6},
    },
    {
        label: '行政村',
        field: 'orgCode',
        component: 'JTreeSelect',
        colProps: {span: 6},
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
        colProps: {span: 6},
    },
];
//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '户编号',
        field: 'familyNumber',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入户主编号!'},
                {...rules.duplicateCheckRule('qfy_family_base', 'family_number', model, schema)[0]},
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
        label: '身份证号',
        field: 'idCard',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入身份证号!'},
                {
                    pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '请输入正确的身份证号!'
                },
            ];
        },
    },
    {
        label: '民族',
        field: 'nation',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: 'people_nation',
            placeholder: "请选择",
        },
    },
    {
        label: '乡村',
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
                {required: true, message: '请选择正确的乡村!'},
            ];
        },
    },
    {
        label: '行政村',
        field: 's1',
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
                {required: true, message: '请选择正确的行政村!'},
            ];
        },
    },
    {
        label: '自然村屯',
        field: 'aturalCode',
        component: 'JTreeSelect',
        componentProps: {
            dict: "qfy_sys_organization,name,code",
            pidField: 'pcode',
            pidValue: "A02",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
            placeholder: "请选择父级节点"
        },

    },
    {
        label: '门牌号',
        field: 'houseNumber',
        component: 'Input',
    },
    {
        label: '联系电话',
        field: 'telephone',
        component: 'Input',
    },
    {
        label: '家庭住址',
        field: 'address',
        component: 'Input',
        // component: 'JDictSelectTag',
    },

    // {
    //     label: '家庭基本信息',
    //     field: 'divisionLine',
    //     component: 'Divider',
    //     colProps: {
    //         span: 20
    //     },
    //     componentProps: {
    //         //是否虚线
    //         dashed: false,
    //         //分割线标题的位置（left | right | center）
    //         orientation: 'center',
    //         //文字是否显示为普通正文样式
    //         plain: true,
    //         //水平还是垂直类型（horizontal | vertical）
    //         type: 'horizontal',
    //     },
    // },

    {
        label: '脱贫年度',
        field: 'tuopinYear',
        component: 'DatePicker',
        componentProps: {
            picker: 'year',
            valueFormat: 'YYYY',
        },

    },
    {
        label: '家庭类型',
        field: 'familyType',
        component: 'JSelectMultiple',
        componentProps: {
            dictCode: "family_type"
        },
    },
    {
        label: '家庭人口总数',
        field: 'totalPerson',
        component: 'InputNumber',
    },
    // {
    //     label: '是否搬迁户',
    //     field: 'isRelocate',
    //     component: 'JDictSelectTag',
    //     componentProps: {
    //         dictCode: "yn",
    //         type: "radio",
    //         stringToNumber: true,
    //     },
    //     defaultValue: '0',
    // },
    // {
    //     label: '是否在大中型安置区',
    //     field: 'isBigRelocate',
    //     component: 'JDictSelectTag',
    //     componentProps: {
    //         dictCode: "yn",
    //         type: "radio",
    //         stringToNumber: true,
    //     },
    //     defaultValue: '0',
    // },
    // {
    //     label: '搬迁时间',
    //     field: 'relocateDate',
    //     component: 'DatePicker',
    //     componentProps: {
    //         valueFormat: 'YYYY-MM-DD',
    //     },
    // },
    // {
    //     label: '安置区机构',
    //     field: 'relocateOrgCode',
    //     component: 'JTreeSelect',
    //     componentProps: {
    //         dict: "qfy_sys_organization,name,code",
    //         pidField: "pcode",
    //         pidValue: "0",
    //         hasChildField: 'has_child',
    //         converIsLeafVal: 1,
    //     },
    // },
    // {
    //     label: '安置区住址',
    //     field: 'relocateAddress',
    //     component: 'Input',
    // },
    // {
    //     label: '风险信息',
    //     field: 'divisionLine',
    //     component: 'Divider',
    //     colProps: {
    //         span: 20
    //     },
    //     componentProps: {
    //         //是否虚线
    //         dashed: false,
    //         //分割线标题的位置（left | right | center）
    //         orientation: 'center',
    //         //文字是否显示为普通正文样式
    //         plain: true,
    //         //水平还是垂直类型（horizontal | vertical）
    //         type: 'horizontal',
    //     },
    // },
    // {
    //     label: '检测主要风险',
    //     field: 'monitorType',
    //     component: 'Input',
    // },
    // {
    //     label: '风险识别时间',
    //     field: 'discernDate',
    //     component: 'DatePicker',
    //     componentProps: {
    //         valueFormat: 'YYYY-MM-DD',
    //         placeholder: '请选择风险识别时间'
    //     },
    // },
    // {
    //     label: '风险是否消除',
    //     field: 'isEliminate',
    //     component: 'JDictSelectTag',
    //     componentProps: {
    //         dictCode: "yn"
    //     },
    // },
    // {
    //     label: '风险消除时间',
    //     field: 'eliminateDate',
    //     component: 'DatePicker',
    //     componentProps: {
    //         valueFormat: 'YYYY-MM-DD',
    //         placeholder: '请选择风险消除时间'
    //     },
    // },
    // {
    //     label: '备注',
    //     field: 'remarks',
    //     component: 'Input',
    // },

    // {
    //     label: '地址id',
    //     field: 'addId',
    //     component: 'Input',
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
