<template>
  <a-tabs defaultActiveKey="1" class="waterTabs">
    <a-tab-pane tab="饮水安全信息" key="1" >
      <a-row>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol1" :wrapperCol="wrapperCol1" label="是否存在饮水安全问题" v-bind="validateInfos.isWaterSafe">
            <j-dict-select-tag v-model:value="formData.isWaterSafe" :string-to-number="true" dictCode="yn" :disabled="formDisabled" placeholder="请选择"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol1" :wrapperCol="wrapperCol1" label="饮水来源" v-bind="validateInfos.waterSource">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.waterSource" :stringToNumber="true" placeholder="请选择" dictCode="waterSourceDict" />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol1" :wrapperCol="wrapperCol1" label="是否获得水柜建造补助" v-bind="validateInfos.isSubsidy">
            <j-dict-select-tag v-model:value="formData.isSubsidy" :string-to-number="true" dictCode="yn" :disabled="formDisabled" placeholder="请选择"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol1" :wrapperCol="wrapperCol1" label="家庭水柜建设时间" v-bind="validateInfos.subsidyDate">
            <a-date-picker
              placeholder="请选择"
              v-model:value="formData.subsidyDate"
              value-format="YYYY-MM-DD"
              style="width: 100%"
              :disabled="formDisabled"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol1" :wrapperCol="wrapperCol1" label="家庭水柜补助金额" v-bind="validateInfos.subsidyAmount">
            <div style="display: flex; align-items: center">
              <a-input v-model:value="formData.subsidyAmount" type="number" placeholder="请输入" :disabled="formDisabled"></a-input>
              <span>（元）</span>
            </div>
          </a-form-item>
        </a-col>

      </a-row>
    </a-tab-pane>
  </a-tabs>
<!--  <a-spin :spinning="confirmLoading">-->
<!--    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--      -->
<!--    </a-form>-->
<!--  </a-spin>-->
</template>

<script lang="ts" setup>
import {ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted, watch} from 'vue';
import {defHttp} from '/@/utils/http/axios';
import {useMessage} from '/@/hooks/web/useMessage';
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import {Form} from 'ant-design-vue';

const props = defineProps({
  formDisabled: {type: Boolean, default: false},

  formData: {
    type: Object, default: null
  },

  formBpm: {type: Boolean, default: true}
});
const formRef = ref();
const useForm = Form.useForm;
const emit = defineEmits(['register', 'ok']);
let formData = ref<Record<string, any>>({
});
const {createMessage} = useMessage();
const labelCol = ref<any>({xs: {span: 12}, sm: {span: 7}});
const wrapperCol = ref<any>({xs: {span: 12}, sm: {span: 14}});
const labelCol1 = ref<any>({xs: {span: 12}, sm: {span: 10}});
const wrapperCol1 = ref<any>({xs: {span: 12}, sm: {span: 11}});
const confirmLoading = ref<boolean>(false);
//表单验证
const validatorRules = {

};
const {resetFields, validate, validateInfos} = useForm(formData, validatorRules, {immediate: true});

// // 表单禁用
// const disabled = computed(() => {
//   if (props.formBpm === true) {
//     if (props.formData.disabled === false) {
//       return false;
//     } else {
//       return true;
//     }
//   }
//   return props.formDisabled;
// });

function getBackData(){
  return formData.value;
}



watch(() => props.formData, (newVal, oldVal) => {
  formData.value=Object.assign({}, props.formData);
  console.log("formData====>",formData)
})

defineExpose({
  getBackData,
});

</script>

<style lang="less" scoped>
.antd-modal-form {
  height: 500px !important;
  overflow-y: auto;
  padding: 14px;
}
</style>
