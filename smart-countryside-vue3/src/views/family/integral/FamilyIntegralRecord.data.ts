import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
// @ts-ignore
export const columns: BasicColumn[] = [
    {
        title: '户编号',
        align: "center",
        dataIndex: 'familyNumber'
    },
    {
        title: '户主',
        align: "center",
        dataIndex: 'familyManager_dictText'
    },
    {
        title: '行政村',
        align: "center",
        dataIndex: 'village_dictText'
    },
    {
        title: '片区',
        align: "center",
        dataIndex: 'naturalTun_dictText'
    },
    {
        title: '自然屯',
        align: "center",
        dataIndex: 'team_dictText'
    },
    {
        title: '加（扣）分项目',
        align: "center",
        dataIndex: 'chkidPlus_dictText',
        customRender:({record}) =>{
            // @ts-ignore
            if(record.chkidPlus && record.chkidPlus_dictText){
                // @ts-ignore
                return record.chkidPlus_dictText;
            }
            // @ts-ignore
            return record.chkidReduce_dictText;
        }
    },
    {
        title: '积分类别',
        align: "center",
        dataIndex: 'ext3',
        customRender:({record})=> {
          // @ts-ignore
            return record.ext3 =='1' ? '加分' : '扣分';
        }
    },
    {
        title: '加（扣）分值',
        align: "center",
        dataIndex: 'scorePlus',
        customRender: ({record})=> {
            // @ts-ignore
          if(record.scorePlus){
              // @ts-ignore
            return record.scorePlus;
          }
            // @ts-ignore
          return record.scoreReduce;
        }
    },
    {
        title: '加（扣）分事由',
        align: "center",
        dataIndex: 'itemPlus',
        // scopedSlots: { customRender: 'ellipsisText'}
    },
    {
        title: '登记时间',
        align: "center",
        dataIndex: 'createTime',
        customRender:  ({record}) =>{
            // @ts-ignore
            return !record.createTime?"":(record.createTime.length>10?record.createTime.substr(0,10):record.createTime)
        }
    },
    {
        title: '数据年份',
        align: "center",
        dataIndex: 'dataYear',
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
    field: 'familyManager',
    component: 'Input',
  },
  {
    label: '行政村',
    field: 'orgCode',
    component: 'JTreeSelect',
    componentProps: {
      dict: 'qfy_sys_organization,name,code',
      pidField: 'pcode',
      pidValue: "0",
      hasChildField: 'has_child',
      converIsLeafVal: 1,
    },
    // colProps: {span: 2},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '家庭户信息',
        field: 'divisionLine',
        component: 'Divider',
        colProps: {
            span: 30
        },
        componentProps: {
            //是否虚线
            dashed: false,
            //分割线标题的位置（left | right | center）
            orientation: 'center',
            //文字是否显示为普通正文样式
            plain: true,
            //水平还是垂直类型（horizontal | vertical）
            type: 'horizontal',
        },
    },
    {
        label: '户编号',
        field: 'familyNumber',
        component: 'Input',
        componentProps: {
            disabled: true,
        },
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入户主编号!'},
                {...rules.duplicateCheckRule('qfy_family_base', 'family_number', model, schema)[0]},
            ];
        },
    },

    {
        label: '户主',
        field: 'familyManager',
        component: 'JSelectFamilyMember',
        componentProps: {
            labelKey: 'name',
            rowKey: 'id',
            showSelected: true,
        },

    },
    {
        label: '行政村',
        field: 'village',
        component: 'JTreeSelect',
        componentProps: {
            dict: "qfy_sys_organization,name,code",
            pidField: "pcode",
            pidValue: "A02",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
            disabled: true
        }
    },
    {
        label: '片区',
        field: 's1',
        component: 'JTreeSelect',
        componentProps: {
            dict: "qfy_sys_organization,name,code",
            pidField: 'pcode',
            pidValue: "A02",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
            placeholder: "请选择父级节点",
            disabled: true
        },
    },
    {
        label: '自然村屯',
        field: 'aturalCode',
        component: 'JTreeSelect',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请选择正确的自然村屯!'},
            ];
        },
        componentProps: {
            dict: "qfy_sys_organization,name,code",
            pidField: 'pcode',
            pidValue: "A02",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
            placeholder: "请选择父级节点",
            disabled: true
        },

    },
    {
        label: '加（扣）分项目',
        field: 'divisionLine',
        component: 'Divider',
        colProps: {
            span: 30
        },

        componentProps: {
            //是否虚线
            dashed: false,
            //分割线标题的位置（left | right | center）
            orientation: 'center',
            //文字是否显示为普通正文样式
            plain: true,
            //水平还是垂直类型（horizontal | vertical）
            type: 'horizontal',
            // borderWidth:'100%',
        },
    },

    {
        label: '评分类别',
        field: 'category',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: "score_category",
            type: "radio",
        },
        defaultValue: '1',
    },
    {
        label: '登记年份',
        field: 'dataYear',
        component: 'Input',
        componentProps: {
            disabled: true
        },
        defaultValue: (new Date()).getFullYear(),
    },

    // {
    //     label: '加分项目',
    //     field: 'itemPlus',
    //     component: 'JSelectCheckItem',
    //     componentProps: {
    //         labelKey: 'name',
    //         rowKey: 'id',
    //         showSelected: true,
    //     }
    // },

    {
        label: '加分分值',
        field: 'scorePlus',
        component: 'InputNumber',
    },
    {
        label: '减分项目',
        field: 'chkidReduce',
        component: 'JSelectCheckItem',
        componentProps: {
            labelKey: 'name',
            rowKey: 'id',
            showSelected: true,
        }
    },
    {
        label: '减分事项',
        field: 'itemReduce',
        component: 'Input',
    },
    {
        label: '减分分值',
        field: 'scoreReduce',
        component: 'InputNumber',
    },
    {
        label: '事项详情',
        field: 'itemPlus',
        component: 'InputTextArea',
    },
    // TODO 主键隐藏字段，目前写死为ID
    {
        label: '',
        field: 'id',
        component: 'Input',
        show: false,
    },
];
