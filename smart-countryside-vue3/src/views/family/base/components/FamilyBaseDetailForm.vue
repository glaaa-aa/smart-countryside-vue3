<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :model="formData" :labelCol="labelCol"
            :wrapperCol="wrapperCol">
      <a-row>
        <a-divider>户主信息</a-divider>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="户主姓名"
                       v-bind="validateInfos.name">
            <a-input disabled v-model:value="formData.familyBase.name" placeholder="请输入户主姓名"
                     :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" disabled label="性别"
                       v-bind="validateInfos.sex">
             <JDictSelectTag type="radio" v-model:value="formData.familyBase.sex" placeholder="请选择" disabled dictCode="sex"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="民族"
                       v-bind="validateInfos.nation">
            <JDictSelectTag disabled v-model:value="formData.familyBase.nation" placeholder="请选择"
                            dict-code="people_nation"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="身份证号"
                       v-bind="validateInfos.idCard">
            <a-input disabled v-model:value="formData.familyBase.idCard"
                     placeholder="请输入身份证号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="年份"
                       v-bind="validateInfos.year">
            <j-dict-select-tag v-model:value="formData.year" title="年份" dictCode="family_year"
                               placeholder="年份" :disabled="formDisabled"
                               @change="changeYear"/>
          </a-form-item>
        </a-col>
      </a-row>
      <FamilyMemberBaseForm v-has="'base'" ref="base"
                            :form-data="formData.familyBase"></FamilyMemberBaseForm>
      <FamilyMemberHouseForm v-has="'house'" ref="house"
                             :form-data="formData.familyHouse"></FamilyMemberHouseForm>
      <FamilyMemberWaterForm v-has="'water'" ref="water"
                             :form-data="formData.familyWater"></FamilyMemberWaterForm>
      <FamilyMemberProductionForm v-has="'production'" ref="production"
                                  :form-data="formData.familyProduction"></FamilyMemberProductionForm>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
import {ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted} from 'vue';
import {defHttp} from '/@/utils/http/axios';
import {ActionItem, BasicColumn, BasicTable, TableAction} from '/@/components/Table';
import {useListPage} from '/@/hooks/system/useListPage';
import {useMessage} from '/@/hooks/web/useMessage';
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import {saveOrUpdate, queryByFamilyAndYear} from '../FamilyBaseDetail.api';
import {Form} from 'ant-design-vue';
import {duplicateValidate} from '/@/utils/helper/validator';
import FamilyMemberBaseForm from './FamilyMemberBaseForm.vue';
import FamilyMemberHouseForm from './FamilyMemberHouseForm.vue';
import FamilyMemberWaterForm from './FamilyMemberWaterForm.vue';
import FamilyMemberProductionForm from './FamilyMemberProductionForm.vue';
import Action from "@/views/demo/comp/scroll/Action.vue";
import {valid} from "mockjs";

const props = defineProps({
  formDisabled: {type: Boolean, default: false},
  formData: {
    type: Object, default: () => {
    }
  },
  formBpm: {type: Boolean, default: true},
});
const formRef = ref();

const base = ref(null);
const house = ref(null);
const water = ref(null);
const production = ref(null);

const useForm = Form.useForm;
const emit = defineEmits(['register', 'ok']);
const formData = reactive<Record<string, any>>({
  id: null, //户id
  familyNumber: null, //户编号
  year: null, //年份
  familyBase: {},
  familyHouse: {},
  familyWater: {},
  familyProduction: {},
});
const {createMessage} = useMessage();
const labelCol = ref<any>({xs: {span: 12}, sm: {span: 7}});
const wrapperCol = ref<any>({xs: {span: 12}, sm: {span: 14}});

let confirmLoading = ref<boolean>(false);
const activeKey = ref('1');
//表单验证
const validatorRules = {
  //   aturalCode: [{ required: true, message: '请输入自然村屯编号!' }],
  // familyNumber: [{ required: true, message: '请输入户主编号!' }, { validator: familyNumberDuplicatevalidate }],
  name: [{required: true, message: '请输入户主姓名!'}],
  idCard: [
    {required: true, message: '请输入身份证号!'},
    // {
    //   pattern:
    //     '^[1-9]d{5}(18|19|([23]d))d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)d{3}[0-9Xx]$)|(^[1-9]d{7}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)d{3}',
    //   message: '不符合校验规则!',
    // },
  ],
};
const {resetFields, validate, validateInfos} = useForm(formData, validatorRules, {immediate: true});
//定义表格列
const columns: BasicColumn[] = [
  {
    title: '帮扶企业',
    align: 'center',
    dataIndex: 'eid_dictText',
  },
  {
    title: '联农带农方式',
    align: 'center',
    dataIndex: 'assistMethod_dictText',
  },
  {
    title: '备注',
    align: 'center',
    dataIndex: 'ext1',
  },
];
// 列表页面公共参数、方法
const {tableContext} = useListPage({
  designScope: 'basic-table-demo-ajax',
  tableProps: {
    title: '用户列表',
    // api: demoListApi,
    columns: columns,
    actionColumn: {
      width: 120,
    },
    //定义rowSelection的类型，默认是checkbox多选，可以设置成radio单选
    rowSelection: {type: 'checkbox'},
  },
});
console.log('tableContext', tableContext);

//BasicTable绑定注册
const [registerTable, {reload}, {rowSelection, selectedRows, selectedRowKeys}] = tableContext;

/**
 * 操作栏
 */
function getTableAction(record): ActionItem[] {
  return [
    {
      label: '编辑',
      onClick: handleEdit.bind(null, record),
    },
  ];
}

function handleEdit(record) {
  console.log(record);
}

// 表单禁用
const disabled = computed(() => {
  if (props.formBpm === true) {
    if (props.formData.disabled === false) {
      return false;
    } else {
      return true;
    }
  }
  return props.formDisabled;
});

/**
 * 新增
 */
function add() {
  edit({});
}

/**
 * 编辑
 */
async function edit(record) {
  // dataCode.value = JSON.parse(JSON.stringify(record)).familyNumber
  // console.log('编辑record===》',JSON.parse(JSON.stringify(record)));
  if (record && record.familyNumber) {
    await getFamiliyData(record.familyNumber, 2024);
  } else {
    // nextTick(() => {
    //   resetFields();
    //   //赋值
    //   Object.assign(formData,  record);
    // });
  }
}

//根据数据加载详情信息
async function getFamiliyData(familyNumber, year) {
  await queryByFamilyAndYear({familyNumber: familyNumber, year: year}).then((res) => {
    console.log("result==>", res)
    if (res.familyProduction) {
      res.familyProduction['familyNumber'] = familyNumber;
    } else {
      res['familyProduction'] = {'familyNumber': familyNumber}
    }
    nextTick(() => {
      resetFields();
      //赋值
      Object.assign(formData, res);
    });
  }).finally(() => {
    confirmLoading.value = false;
  });
}

function changeYear(val) {
  getFamiliyData(formData.familyNumber, val);
}

// tabs切换
function tabsChange(n) {
  console.log('tabs切换', n);
  if (n == '3') {
    // demoListApi()
  }
}

/**
 * 提交数据
 */
async function submitForm() {
    const valid = await validate();
    if (valid) {
      let params = {};
      params.id = formData.id;
      params.year = formData.year;
      params.familyNumber = formData.familyNumber;
      var hasBase = document.getElementsByClassName('baseTabs');
      var hasProduction = document.getElementsByClassName('productionTabs');
      var hasWater = document.getElementsByClassName('waterTabs');
      var hasHouse = document.getElementsByClassName('houseTabs');
      if (hasBase.length > 0) {
        params.hasBase = true;
        params["familyBase"] = base.value.getBackData();
      } else {
        params["familyBase"] = formData.familyBase;
      }

      if (hasHouse.length > 0) {
        params.hasHouse = true;
        params["familyHouse"] = house.value.getBackData();
      }
      if (hasWater.length > 0) {
        params.hasWater = true;
        params["familyWater"] = water.value.getBackData();
      }
      if (hasProduction.length > 0) {
        params.hasProduction = true;
        params["familyProduction"] = production.value.getBackData();
      }

      confirmLoading.value = true;
      const isUpdate = ref<boolean>(false);
      //时间格式化
      let model = params;
      if (model.id) {
        isUpdate.value = true;
      }
      console.log("请求参数", params);
      // return false;
      await saveOrUpdate(model, isUpdate.value).then((res) => {
        emit('ok');

      }).finally(() => {
        confirmLoading.value = false;
      });
    }

}




/**
 * 值改变事件触发
 * @param key
 * @param value
 */
function handleFormChange(key, value) {
  console.log(key, value, '选择村');
  formData[key] = value;
}

async function familyNumberDuplicatevalidate(_r, value) {
  return duplicateValidate('qfy_family_base', 'family_number', value, formData.id || '');
}

defineExpose({
  add,
  edit,
  submitForm,
});
</script>

<style lang="less" scoped>
.antd-modal-form {
  height: 500px !important;
  overflow-y: auto;
  padding: 14px;
}

.ant-form-item {
  margin-bottom: 8px;
}
</style>
