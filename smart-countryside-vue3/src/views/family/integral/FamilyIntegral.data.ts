import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
import {list} from "@/views/family/integral/FamilyIntegral.api";
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '数据年份',
        align: "center",
        dataIndex: 'dataYear',
        width:90
    },
    {
        title: '行政村',
        align: "center",
        dataIndex: 'village_dictText',
        width:100
    },
    {
        title: '片区',
        align: "center",
        dataIndex: 'naturalTun_dictText',
        width:100
    },
    {
        title: '自然屯',
        align: "center",
        dataIndex: 'team_dictText',
        width:100
    },
    {
        title: '户编号',
        align: "center",
        dataIndex: 'familyNumber',
        width:100
    },
    {
        title: '户主',
        align: "center",
        dataIndex: 'familyManager_dictText',
        width:100
        // customRender: function (text, row) {
        //   let reg = /^[0-9]+.?[0-9]*$/;// 判断是否是纯数字
        //   if (reg.test(text)){
        //     return row.ext1;
        //   } else {
        //     return text;
        //   }
        // }
    },
    {
        title: '基础分',
        align: "center",
        dataIndex: 'base'
    },
    {
        title: '年度总积分',
        align: "center",
        // sorter: true,
        dataIndex: 'total'
    },
    {
        title: '备注',
        align: "center",
        dataIndex: 'remarks'
    },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
    {
        label: "户编号",
        field: 'familyNumber',
        component: 'Input',
    },
    {
        label: "户主",
        field: 'name',
        component: 'Input',
    },
    {
        label: '行政村',
        field: 'orgCode',
        component: 'JTreeSelect',
        componentProps: {
            dict: 'qfy_sys_organization,name,code',
            pidField: 'pcode',
            pidValue: "A02",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
        },
    },

];
//表单数据
export const formSchema: FormSchema[] = [
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
