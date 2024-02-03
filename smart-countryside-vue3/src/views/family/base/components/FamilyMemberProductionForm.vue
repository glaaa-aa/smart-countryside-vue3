<template>
  <a-tabs defaultActiveKey="1" class="productionTabs">
    <a-tab-pane tab="产业信息" key="1">
    <a-row>
        <a-col :span="8">
          <a-form-item label="旱地（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.gengdArea">
            <a-input-number
              v-model:value="formData.gengdArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%">
            </a-input-number>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="旱地实种（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.dryLandPlantArea">
            <a-input-number
              v-model:value="formData.dryLandPlantArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="水田（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.paddyArea">
            <a-input-number
              v-model:value="formData.paddyArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="水田实种（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.paddyPlantArea">
            <a-input-number
              v-model:value="formData.paddyPlantArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="林地（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.lindArea">
            <a-input-number
              v-model:value="formData.lindArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="生态林（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.ecologicalForestArea">
            <a-input-number
              v-model:value="formData.ecologicalForestArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="果类种植（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.fruitArea">
            <a-input-number
              v-model:value="formData.fruitArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="油茶种植（亩）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.teaOilArea">
            <a-input-number
              v-model:value="formData.teaOilArea"
              :min="0"
              :precision="2"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="养殖猪（头）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.totalPig">
            <a-input-number
              v-model:value="formData.totalPig"
              :min="0"
              :precision="0"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="养殖牛（头）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.totalCattle">
            <a-input-number
              v-model:value="formData.totalCattle"
              :min="0"
              :precision="0"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="养殖羊（只）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.totalSheep">
            <a-input-number
              v-model:value="formData.totalSheep"
              :min="0"
              :precision="0"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="养殖鸡（只）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.totalChicken">
            <a-input-number
              v-model:value="formData.totalChicken"
              :min="0"
              :precision="0"
              :disabled="formDisabled"
              placeholder="请输入"
              style="width: 100%"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="贷款种类" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.loanType">
            <!-- <j-dict-select-tag v-model="formData.loanType" placeholder="请选择" :disabled="formDisabled" dict-code="loanType" /> -->
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.loanType"
                            :stringToNumber="true" placeholder="请选择" dictCode="loanType"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="贷款金额（元）" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.loanAmount">
            <a-input-number v-model:value="formData.loanAmount" placeholder="请输入"
                            :disabled="formDisabled" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其它" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.totalOther">
            <a-input v-model:value="formData.totalOther" placeholder="请输入" :disabled="formDisabled"
                     style="width: 100%"/>
          </a-form-item>
        </a-col>
    </a-row>
    </a-tab-pane>

    <a-tab-pane tab="帮扶信息" key="2">
      <a-row>
        <a-col :span="16">
          <a-form-item label="金融服务" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.finance">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.finance"
                             placeholder="请选择" dictCode="product_finance"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.financeOthers">

            <a-input v-model:value="formData.financeOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label="销售产品" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.sale">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.sale"
                             placeholder="请选择" dictCode="product_sale"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.saleOthers">
            <a-input v-model:value="formData.saleOthers" :disabled="formDisabled" placeholder="请输入"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label="代种代养" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.plantBreed">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.plantBreed"
                            placeholder="请选择"
                            dictCode="product_varietyImprovement"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.plantBreedOthers">
            <a-input v-model:value="formData.plantBreedOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label="技术培训指导" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.technicalTraining">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.technicalTraining"
                            placeholder="请选择"
                            dictCode="product_technicalTraining"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.technicalTrainingOthers">
            <a-input v-model:value="formData.technicalTrainingOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label="品种改良" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.varietyImprovement">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.varietyImprovement"
                            placeholder="请选择"
                            dictCode="product_varietyImprovement"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.varietyImprovementOthers">
            <a-input v-model:value="formData.varietyImprovementOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label="提供社会化服务" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.socializationService">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.socializationService"
                            placeholder="请选择"
                            dictCode="product_socializationService"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.socializationServiceOthers">
            <a-input v-model:value="formData.socializationServiceOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="产业配套基础设施建设" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.facilityConstruction">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.facilityConstruction"
                            placeholder="请选择"
                            dictCode="product_facilityConstruction"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.facilityConstructionOthers">
            <a-input v-model:value="formData.facilityConstructionOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="获得产业以奖代补情况" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.rewardCompensate">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.rewardCompensate"
                            placeholder="请选择"
                            dictCode="product_rewardCompensate"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.rewardCompensateOthers">
            <a-input v-model:value="formData.rewardCompensateOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="入股分红" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.stockDividends">
            <JDictSelectTag :disabled="formDisabled"
                            v-model:value="formData.stockDividends"
                            placeholder="请选择"
                            dictCode="product_stockDividends"
            />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.stockDividendsOthers">
            <a-input v-model:value="formData.stockDividendsOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="产业项目资产收益分配" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.incomeDistribution">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.loanType"
                           placeholder="请选择"
                            dictCode="product_incomeDistribution"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.incomeDistributionOthers">
            <a-input v-model:value="formData.incomeDistributionOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="产业项目吸纳务工" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.inWorks">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.inWorks"
                             placeholder="请选择" dictCode="product_inWorks"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.inWorksOthers">
            <a-input v-model:value="formData.inWorksOthers" :disabled="formDisabled"
                     placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="土地流转" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.land">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.land"
                             placeholder="请选择" dictCode="product_land"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.landOthers">
            <a-input v-model:value="formData.landOthers" :disabled="formDisabled" placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="边贸政策" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.trade">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.trade"
                             placeholder="请选择" dictCode="product_trade"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.tradeOthers">
            <a-input v-model:value="formData.tradeOthers" :disabled="formDisabled" placeholder="请输入"/>
          </a-form-item>
        </a-col>

        <a-col :span="16">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.others">
            <JDictSelectTag :disabled="formDisabled" v-model:value="formData.others"
                             placeholder="请选择" dictCode="product_others"/>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol"
                       v-bind="validateInfos.othersOthers">
            <a-input v-model:value="formData.othersOthers" :disabled="formDisabled" placeholder="请输入"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-tab-pane>

    <a-tab-pane tab="帮扶企业" key="3">
      <a-row>
        <AssistEnterpriseRelList :main-id="formData.familyNumber"></AssistEnterpriseRelList>
      </a-row>
    </a-tab-pane>
  </a-tabs>
</template>

<script lang="ts" setup>
import {ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted, watch} from 'vue';
import {defHttp} from '/@/utils/http/axios';
import {useMessage} from '/@/hooks/web/useMessage';
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import {Form} from 'ant-design-vue';
import AssistEnterpriseRelList from '/@/views/family/assist/AssistEnterpriseRelListList.vue';

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
let formData = ref<Record<string, any>>({});
const {createMessage} = useMessage();
const labelCol = ref<any>({xs: {span: 12}, sm: {span: 7}});
const wrapperCol = ref<any>({xs: {span: 12}, sm: {span: 14}});
const confirmLoading = ref<boolean>(false);
//表单验证
const validatorRules = {};
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
  formData.value = Object.assign({}, props.formData);
  console.log("formData====>", formData)
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
