<template>
<a-tabs defaultActiveKey="1" class="baseTabs">
  <a-tab-pane tab="户基本信息" key="1" >
    <a-row>
      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="行政村"
        >
          <j-tree-select
            ref="treeSelect"
            placeholder="请选择"
            v-model:value="formData.orgCode"
            dict="qfy_sys_organization,name,code"
            pidField="pcode"
            pidValue="A02"
            @change="changeOrgCode"
            disabled>
          </j-tree-select>
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="片区">
          <j-tree-select
            ref="treeSelect"
            placeholder="请选择"
            v-model:value="formData.s1"
            dict="qfy_sys_organization,name,code"
            pidField="pcode"
            pidValue="A02"
            @change="changeOrgCode"
            disabled>
          </j-tree-select>
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="自然村屯"
                     v-bind="validateInfos.aturalName">
          <j-tree-select
            ref="treeSelect"
            placeholder="请选择"
            v-model:value="formData.aturalCode"
            dict="qfy_sys_organization,name,code"
            pidField="pcode"
            pidValue="A02"
            @change="changeOrgCode"
            disabled>
          </j-tree-select>

        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="联系电话"
                     v-bind="validateInfos.telephone">
          <a-input v-model:value="formData.telephone" placeholder="请输入" :disabled="formDisabled"/>
        </a-form-item>
      </a-col>

      <a-col :span="12">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="家庭地址"
                     v-bind="validateInfos.address">
          <a-input disabled v-model:value="formData.address" placeholder="请输入家庭地址"
                   :disabled="disabled"></a-input>
        </a-form-item>
      </a-col>

      <a-col :span="8">

        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="是否搬迁户">
          <JDictSelectTag type="radio"  v-model:value="formData.isRelocate" :disabled="formDisabled"
                          dictCode="yn"
                          :string-to-number="true"
                          placeholder="请选择"
          />

        </a-form-item>
      </a-col>


      <a-col :span="8">
        <a-form-item label="是否在大中型安置区" :labelCol="{ span: 12}"
                           :wrapperCol="{span: 12}" prop="isBigRelocate">

          <j-dict-select-tag type="radio" v-model:value="formData.isBigRelocate"
                             :disabled="formDisabled" dictCode="yn" :string-to-number="true"
                             placeholder="请选择"/>
        </a-form-item>
      </a-col>


      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="搬迁时间"
                     v-bind="validateInfos.relocateDate">
          <a-date-picker
            placeholder="请选择搬迁时间"
            v-model:value="formData.relocateDate"
            value-format="YYYY-MM-DD"
            style="width: 100%"
            :disabled="disabled"
          />
        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="安置区机构"
                     v-bind="validateInfos.relocateOrgCode">
          <j-tree-select
            ref="treeSelect"
            placeholder="请选择"
            v-model:value="formData.relocateOrgCode"
            dict="qfy_sys_organization,name,code"
            pidField="pcode"
            pidValue="0"
            @change="changeRelocateOrg"
            :disabled="disabled">
          </j-tree-select>
        </a-form-item>
      </a-col>

      <a-col :span="12">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="安置区住址"
                     v-bind="validateInfos.relocateAddress">
          <a-input v-model:value="formData.relocateAddress" placeholder="请输入安置区住址"
                   :disabled="disabled"></a-input>
        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="检测主要风险"
                     v-bind="validateInfos.monitorType">
          <a-input v-model:value="formData.monitorType" placeholder="请输入"
                   :disabled="disabled"></a-input>
        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="风险识别时间"
                     v-bind="validateInfos.discernDate">
          <a-date-picker
            placeholder="请选择搬迁时间"
            v-model:value="formData.discernDate"
            value-format="YYYY-MM"
            picker="month"
            style="width: 100%"
            :disabled="disabled"
          />
        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="风险是否消除"
                     v-bind="validateInfos.isEliminate">

          <j-dict-select-tag v-model:value="formData.isEliminate" :disabled="formDisabled" dictCode="yn" :string-to-number="true"
                             placeholder="请选择"/>

        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="风险消除时间"
                     v-bind="validateInfos.eliminateDate">
          <a-date-picker
            placeholder="请选择"
            v-model:value="formData.eliminateDate"
            value-format="YYYY-MM-DD"
            style="width: 100%"
            :disabled="disabled"
          />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="备注"
                     v-bind="validateInfos.remarks">
          <a-input v-model:value="formData.remarks" placeholder="请输入备注"
                   :disabled="disabled"></a-input>
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
import JTreeSelect from "@/components/Form/src/jeecg/components/JTreeSelect.vue";

const props = defineProps({
  formDisabled: {type: Boolean, default: false},

  formData: {
    type: Object, default: () => {
    }
  },

  formBpm: {type: Boolean, default: true}
});
const formRef = ref();
const useForm = Form.useForm;
const emit = defineEmits(['register', 'ok']);
let formData = ref<Record<string, any>>({});
const {createMessage} = useMessage();
const labelCol = ref<any>({xs: {span: 12}, sm: {span: 7}});
const wrapperCol = ref<any>({xs: {span: 12}, sm: {span: 14}});
const confirmLoading = ref<boolean>(false);
//表单验证
const validatorRules = {

};
const {resetFields, validate, validateInfos} = useForm(formData, validatorRules, {immediate: true});

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

function getBackData(){
  return formData.value;
}

function changeOrgCode(val, text) {
  formData.orgName = text;
}

function changeRelocateOrg(val, text) {
  formData.relocateOrgName = text;
}

watch(() => props.formData, (newVal, oldVal) => {
  formData.value = props.formData;
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
