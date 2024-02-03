<template>
  <a-tabs defaultActiveKey="1" class="houseTabs">
    <a-tab-pane tab="住房信息" key="1" >
      <a-row>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="住房面积(㎡)" v-bind="validateInfos.zfArea">
            <a-input type="number" v-model:value="formData.zfArea" placeholder="请输入" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="层数" v-bind="validateInfos.zfFloor">
            <a-input type="number" v-model:value="formData.zfFloor" placeholder="请输入" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="房屋结构" v-bind="validateInfos.s2">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.s2" placeholder="请选择" dictCode="houseStructureDict" />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="住房等级" v-bind="validateInfos.zfLevel">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.zfLevel" :stringToNumber="true" placeholder="请选择" dictCode="zfLevelDict" />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="建造时间" v-bind="validateInfos.createTime">
            <a-date-picker
              placeholder="请选择"
              v-model:value="formData.createTime"
              value-format="YYYY-MM"
              picker="month"
              style="width: 100%"
              :disabled="disabled"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="是否危房改造户" v-bind="validateInfos.isReform">
            <JDictSelectTag v-model="formData.isReform" :disabled="formDisabled" dictCode="yn" placeholder="请选择"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="危房改造时间" v-bind="validateInfos.reformDate">
            <a-date-picker
              placeholder="请选择"
              v-model:value="formData.reformDate"
              value-format="YYYY-MM"
              picker="month"
              style="width: 100%"
              :disabled="disabled"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="危房改造资金" v-bind="validateInfos.reformAmount">
            <div style="display: flex; align-items: center">
              <a-input v-model:value="formData.reformAmount" type="number" placeholder="请输入" :disabled="disabled"></a-input>
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
