import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '员工号',
        align: "center",
        dataIndex: 'personNo'
    },
    {
        title: '姓名',
        align: "center",
        dataIndex: 'cname'
    },
    {
        title: '性别',
        align: "center",
        dataIndex: 'sex'
    },
    {
        title: '联系电话',
        align: "center",
        dataIndex: 'phone'
    },
    {
        title: '行政区划',
        align: "center",
        dataIndex: 'orgName'
    },
    {
        title: '所属组织机构',
        align: "center",
        dataIndex: 'departName'
    },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
    {
        label: "姓名",
        field: "cname",
        component: 'JInput', //TODO 范围查询
    },
];
//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '员工号',
        field: 'personNo',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入员工号!'},
            ];
        },
        dynamicDisabled: () => {
            if (localStorage.getItem("isDisabled") === 'true') {
                return true;
            } else {
                return false
            }
        },
    },
    {
        label: '姓名',
        field: 'cname',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入姓名!'},
            ];
        },
    },
    {
        label: '联系电话',
        field: 'phone',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入联系电话!'},
            ];
        },
    },
    {
        label: '性别',
        field: 'sex',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: "sex"
        },
    },
    {
        label: '行政区划',
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
                {required: true, message: '行政区划!'},
            ];
        }
    },

    {
        label: '所属组织机构',
        field: 'departCode',
        component: 'JSelectDept',
        componentProps:{
            labelKey:'departName',
            rowKey:'orgCode'
        },
    },


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
