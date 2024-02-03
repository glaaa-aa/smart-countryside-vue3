<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="12">
          <a-form-item label="户编号" :labelCol="labelCol" :wrapperCol="wrapperCol" v-bind="validateInfos.familyNumber">
            <a-input v-model:value="formData.familyNumber" placeholder="请输入户编号" disabled></a-input>
          </a-form-item>
        </a-col>

        <a-col :span="12">
          <a-form-item label="户主" :labelCol="labelCol" :wrapperCol="wrapperCol" v-bind="validateInfos.familyManager">
            <JSelectFamilyMember
              v-model:value="formData.familyManager"
              placeholder="请选择"
              :showSelected="true"
              :isRadioSelection="true"
              @change="selectFamily"
            />
          </a-form-item>
        </a-col>

        <a-col :span="12">
          <a-form-item label="行政村" :labelCol="labelCol" :wrapperCol="wrapperCol" v-bind="validateInfos.village">
            <j-tree-select
              ref="treeSelect"
              placeholder="请选择父级节点"
              v-model:value="formData.village"
              dict="qfy_sys_organization,name,code"
              pidField="pcode"
              pidValue="A02"
              hasChildField="has_child"
              converIsLeafVal="1"
              disabled
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="片区" :labelCol="labelCol" :wrapperCol="wrapperCol" v-bind="validateInfos.naturalTun">
            <j-tree-select
              ref="treeSelect"
              placeholder="请选择父级节点"
              v-model:value="formData.naturalTun"
              dict="qfy_sys_organization,name,code"
              pidField="pcode"
              pidValue="A02"
              disabled
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="自然屯" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="team">
            <j-tree-select
              ref="treeSelect"
              placeholder="请选择父级节点"
              v-model:value="formData.team"
              dict="qfy_sys_organization,name,code"
              pidField="pcode"
              pidValue="A02"
              @change="changeOrgCode"
              disabled
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-divider>加（扣）分项目</a-divider>
      <a-row>
        <a-col :span="12">
          <a-form-item label="评分类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <JDictSelectTag
              type="radio"
              v-model:value="formData.category"
              :stringToNumber="true"
              dict-code="score_category"
              @change="changeCategory"
            />
            <a-input v-model:value="formData.ext3" :hidden="true" />
          </a-form-item>
        </a-col>

        <a-col :span="12">
          <a-form-item label="登记年份" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="dataYear">
            <a-input v-model:value="formData.dataYear" placeholder="请输入" disabled />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row v-if="String(formData.category) === '1'">
        <a-col :span="12">
          <a-form-item label="加分项目" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="chkidPlus">
            <j-select-check-item
              :isRadioSelection="true"
              :showSelected="true"
              v-model:value="formData.chkidPlus_dictText"
              placeholder="请选择加分项"
              @getSelectResult="selectCheckItem"
              rowKey="name"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="加分分值" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="scorePlus">
            <a-input-number v-model:value="formData.scorePlus" placeholder="请输入" :min="0" :max="1000" style="width: 100%" />
          </a-form-item>
        </a-col>
        <a-col :span="24" v-if="itemTypes.length > 0">
          <a-form-item label="加分等级" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
            <a-radio-group v-model:value="formData.scorePlus">
              <a-radio v-for="item in itemTypes" :key="item.id" :value="item.score">
                {{ item.name }}
              </a-radio>
            </a-radio-group>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事项详情" :labelCol="labelCol3" :wrapperCol="wrapperCol3" prop="itemPlus">
            <a-textarea v-model:value="formData.itemPlus" placeholder="请输入" rows="3" />
          </a-form-item>
        </a-col>
      </a-row>

      <a-row v-if="String(formData.category) === '2'">
        <a-col :span="12">
          <a-form-item label="扣分项目" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="chkidReduce">
            <j-select-check-item
              :isRadioSelection="true"
              :showSelected="true"
              v-model:value="formData.chkidReduce_dictText"
              placeholder="请选择扣分项"
              @getSelectResult="selectCheckItem"
              rowKey="name"
            />
            <!--            <j-select-check-item v-model:value="formData.chkidReduce" placeholder="请选择扣分项"-->
            <!--                                 @getSelectResult="selectCheckItem"/>-->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="减分分值" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="scoreReduce">
            <a-input-number v-model:value="formData.scoreReduce" placeholder="请输入" :min="0" :max="1000" style="width: 100%" />
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事项详情" :labelCol="labelCol3" :wrapperCol="wrapperCol3" prop="itemReduce">
            <a-textarea v-model:value="formData.itemReduce" placeholder="请输入" rows="3" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-item label="附件（图片）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ext2">
            <j-image-upload v-model:value="formData.ext2" placeholder="请选择" is-multiple />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remarks">
            <a-textarea v-model:value="formData.remarks" placeholder="请输入" rows="3" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
import { ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted, defineComponent } from 'vue';
import { defHttp } from '/@/utils/http/axios';
import { useMessage } from '/@/hooks/web/useMessage';
import { getValueType } from '/@/utils';
import JTreeSelect from '/@/components/Form/src/jeecg/components/JTreeSelect.vue';
import JSelectFamilyMember from '/@/components/Form/src/jeecg/components/JSelectFamilyMember.vue';
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import JSelectCheckItem from '/@/components/Form/src/jeecg/components/JSelectCheckItem.vue';
import JImageUpload from '/@/components/Form/src/jeecg/components/JImageUpload.vue';
import { saveOrUpdate, getAction, list } from '../FamilyIntegralRecord.api';
import { Form } from 'ant-design-vue';

const props = defineProps({
  formDisabled: { type: Boolean, default: false },
  formData: {
    type: Object,
    default: () => {},
  },
  formBpm: { type: Boolean, default: true },
});
const formRef = ref();
const useForm = Form.useForm;
const emit = defineEmits(['register', 'ok']);
const formData = reactive<Record<string, any>>({
  id: '',
  familyNumber: '',
  familyManager: '',
  village: '',
  naturalTun: '',
  chkidPlus: '',
  itemPlus: '',
  scorePlus: undefined,
  chkidReduce: '',
  itemReduce: '',
  scoreReduce: undefined,
  ext1: '',
  ext2: '',
  ext3: '',
  totalClear: undefined,
  dataYear: new Date().getFullYear(),
  remarks: '',
  category: 1,
});

let itemTypes = [];
const labelCol1 = ref<any>({ xs: { span: 24 }, sm: { span: 3 } });
const wrapperCol1 = ref<any>({ xs: { span: 24 }, sm: { span: 20 } });
const labelCol3 = ref<any>({ xs: { span: 24 }, sm: { span: 3 } });
const wrapperCol3 = ref<any>({ xs: { span: 24 }, sm: { span: 19 } });

const { createMessage } = useMessage();
const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
const confirmLoading = ref<boolean>(false);
//表单验证
const validatorRules = {
  familyNumber: [{ required: true, message: '请输入户编号!' }],
};
const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });

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
function onClearData() {}

/**
 * 新增
 */
function add() {
  console.log(this.$refs.formRef.resetFields(), 'aaaaaaaaaaaaaaaaaa');

  this.$refs.formRef.resetFields();
  console.log(formData.category, formData.ext1, formData, '表单');
  // formData.category=Number(formData.category)
  console.log(formData, '表单2');
  edit({});
}

function selectFamily(item) {
  if (item != '') {
    console.log('item2222222222', item);
    defHttp.get({ url: '/family/familyMember/list?idCard=' + item }).then((res) => {
      console.log('res3333333', res);
      formData.ext1 = res.records[0].name;
      formData.familyNumber = res.records[0].familyNumber;
      formData.team = res.records[0].aturalTun;
      formData.naturalTun = res.records[0].tunZone;
      formData.village = res.records[0].village;
    });
  }else{
    formData.team = '';
    formData.chkidPlus_dictText = '';
  }
}

// function selectMember(row) {
//   console.log("-->", row)
//   formData.ext1 = row[0].name;
//   formData.familyNumber = row[0].familyNumber;
//   formData.team = row[0].aturalTun;
//   formData.naturalTun = row[0].tunZone;
//   formData.village = row[0].village;
// }

function changeOrgCode(val, text) {
  if (val.length == 15) {
    formData.naturalTun = val.substring(0, 12);
    formData.village = val.substring(0, 9);
  }
}

function changeCategory(val) {
  console.log('--category->', val);
  formData.ext3 = val;
}

async function getCheckItem(id) {
  try {
    const res = await defHttp.get({ url: '/smartform/checkItem/list', params: { name: id } });
    return res.records;
  } catch (error) {
    console.error('Error fetching check items:', error);
    return [];
  }
}

async function selectCheckItem(options, values) {
  let item = await getCheckItem(values);

  if (item.length > 0) {
    console.log('--item--->', item);
    itemTypes = [];
    formData.desc = item[0].defaultVal;
    formData.target = item[0].remark;
    console.log('--item--->', formData.category);
    if (formData.category === 1) {
      formData.itemPlus = item[0].vtandardPic;
      formData.scorePlus = item[0].qualifiedscore;
      formData.chkidPlus = item[0].name;
      if (item[0].itemtype === 'A06') {
        getAction({ chkid: item[0].value, column: 'id', order: 'asc' }).then((res) => {
          if (res.success) {
            let choice = res.result.records;
            choice.forEach((item) => {
              itemTypes.push({
                id: item.id,
                name: item.label,
                score: item.score,
              });
            });
          }
        });
      }
    } else {
      formData.itemReduce = item[0].violationPic;
      formData.scoreReduce = item[0].unqualifiedscore;
      formData.chkidReduce = item[0].name;
      console.log('--formData.itemReduce->', formData.itemReduce, item[0].unqualifiedscore);
    }
  }
}

/**
 * 编辑
 */
function edit(record) {
  nextTick(() => {
    resetFields();
    //赋值
    if (record.ext3 != undefined) {
      formData.category = Number(record.ext3);
    }
    Object.assign(formData, record);
  });
}

/**
 * 提交数据
 */
async function submitForm() {
  // 触发表单验证
  await validate();
  confirmLoading.value = true;
  const isUpdate = ref<boolean>(false);
  //时间格式化
  let model = formData;
  if (model.id) {
    isUpdate.value = true;
  }
  //循环数据
  for (let data in model) {
    //如果该数据是数组并且是字符串类型
    if (model[data] instanceof Array) {
      let valueType = getValueType(formRef.value.getProps, data);
      //如果是字符串类型的需要变成以逗号分割的字符串
      if (valueType === 'string') {
        model[data] = model[data].join(',');
      }
    }
  }
  // return false
  await saveOrUpdate(model, isUpdate.value)
    .then((res) => {
      if (res.success) {
        createMessage.success(res.message);
        emit('ok');
      } else {
        createMessage.warning(res.message);
      }
    })
    .finally(() => {
      confirmLoading.value = false;
    });
}

defineExpose({
  add,
  edit,
  submitForm,
  onClearData,
});
</script>

<style lang="less" scoped>
.antd-modal-form {
  height: 500px !important;
  overflow-y: auto;
  padding: 14px;
}
</style>
