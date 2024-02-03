import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import {rules} from '/@/utils/helper/validator';
import {render} from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '文章标题',
        align: "left",
        sorter: true,
        dataIndex: 'title'
    },
    {
        title: '文章栏目',
        align: "center",
        width: 120,
        dataIndex: 'secId_dictText'
    },
    {
        title: '内容类型',
        align: "center",
        width: 100,
        dataIndex: 'articleType_dictText'
    },
    {
        title: '作者',
        align: "center",
        width: 80,
        dataIndex: 'author'
    },
    {
        title: '是否发布',
        align: "center",
        width: 80,
        dataIndex: 'isPublish_dictText'
    },
    {
        title: '发布时间',
        align: "center",
        dataIndex: 'publishTime',
        width: 110,
        customRender:function (text) {
            // return !text?"":(text.length>10?text.substr(0,10):text)
        }
    },
    {
        title: '排序',
        align: "center",
        width: 80,
        dataIndex: 'sort'
    },
    {
        title: '是否置顶',
        align: "center",
        width: 80,
        dataIndex: 'isTop_dictText'
    },
    {
        title: '阅读次数',
        width: 80,
        align: "center",
        dataIndex: 'readyCount'
    },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
    {
        label: '文章栏目',
        field: 'secId',
        component: 'JTreeSelect',
        componentProps: {
            dict: 'qfy_cms_section,name,id',
            pidField: 'pid',
            pidValue:"0",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
        },
    },
    {
        label: "文章标题",
        field: 'title',
        component: 'Input',
        //colProps: {span: 6},
    },
];
//表单数据
export const formSchema: FormSchema[] = [
    {
        label: '文章标题',
        field: 'title',
        component: 'Input',
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入文章标题!'},
            ];
        },
    },
    {
        label: '文章栏目',
        field: 'secId',
        component: 'JTreeSelect',
        componentProps: {
            dict: 'qfy_cms_section,name,id',
            pidField: 'pid',
            pidValue:"0",
            hasChildField: 'has_child',
            converIsLeafVal: 1,
        },
      dynamicRules: ({model, schema}) => {
        return [
          {required: true, message: '请输入文章栏目!'},
        ];
      },
    },

    {
      label: '副标题',
      field: 'shortTitle',
      component: 'Input',
    },
    {
        label: '内容类型',
        field: 'articleType',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: "article_type",
        },
    },

    {
        label: '作者',
        field: 'author',
        component: 'Input',
    },
    {
        label: '是否外链',
        field: 'isExterior',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: 'yn',
            placeholder: '请选择外部链接',
           stringToNumber: true,
        },
    },

    {
        label: '是否发布',
        field: 'isPublish',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: 'yn',
            placeholder: '是否发布',
            stringToNumber: true,
        },
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入是否发布!'},
            ];
        }

    },

    {
        label: '外部链接',
        field: 'exteriorPath',
        component: 'Input',

    },
    // {
    //     label: '是否发布',
    //     field: 'isPublish',
    //     component: 'JDictSelectTag',
    //     componentProps: {
    //         dictCode: 'yn',
    //         placeholder: '请选择是否置顶',
    //       stringToNumber: true,
    //     }
    // },


    {
        label: '是否显示',
        field: 'isShow',
        component: 'JDictSelectTag',
        componentProps: {
            dictCode: 'yn',
          stringToNumber: true,
        },
    },
  {
    label: '是否置顶',
    field: 'isTop',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'yn',
      placeholder: '请选择是否置顶',
      stringToNumber: true,
    }
  },
    {
        label: '排序',
        field: 'sort',
        component: 'InputNumber',
        defaultValue:1,
        dynamicRules: ({model, schema}) => {
            return [
                {required: true, message: '请输入排序!'},
            ];
        },
    },

  {
    label: '发布时间',
    field: 'publishTime',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
  },
    {
        label: '摘要',
        field: 'digest',
        component: 'InputTextArea',
        colProps: {
            span: 24,
        },
    },

    {
        label: '缩略图',
        field: 'titleImg',
        component: 'JImageUpload',
        componentProps: {},
        colProps: {
            span: 24,
        },
    },
    {
        label: '文章内容',
        field: 'detailContent',
        component: 'JEditor',
        labelWidth:100,
        colProps: {
            span: 24,
        },
    },

    // TODO 主键隐藏字段，目前写死为ID
    {
        label: 'ID',
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
