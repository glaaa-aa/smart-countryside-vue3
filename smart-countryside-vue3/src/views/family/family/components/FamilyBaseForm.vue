<template>
  <a-spin :spinning="loading">
    <a-form v-bind="formItemLayout">
      <a-row>

        <a-col :span="4" v-show="false">
          <a-form-item label="户主编号" v-bind="validateInfos.familyNumber">
            <a-input v-model:value="formData.familyNumber" disabled placeholder="请输入户主编号"
                     :disabled="false"></a-input>
          </a-form-item>
        </a-col>

        <a-col :span="6">
          <a-form-item label="户主" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入户主姓名"
                     :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>

        <a-col :span="4">
          <a-form-item label="性别" v-bind="validateInfos.sex">
            <j-dict-select-tag type="radio" v-model:value="formData.sex"
                               title="性别" disabled dictCode="sex" placeholder="请选择性别" />
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item label="身份证号" v-bind="validateInfos.idCard">
            <a-input v-model:value="formData.idCard" placeholder="请输入身份证号"
                     :disabled="true"></a-input>
          </a-form-item>
        </a-col>

        <a-col :span="4">
          <a-form-item label="年份" v-bind="validateInfos.year">

            <JDictSelectTag v-model:value="formData.year" title="年份" dict-code="family_year" :string-to-number="true"  placeholder="年份" @change="changeYears"/>
          </a-form-item>
        </a-col>

      </a-row>
    </a-form>

    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated>
      <a-tab-pane tab="家庭成员" key="familyMember" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          resizable
          ref="familyMemberTableRef"
          :loading="familyMemberTable.loading"
          :columns="familyMemberTable.columns"
          :dataSource="familyMemberTable.dataSource"
          :height="340"
          :disabled="disabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script lang="ts">
import {defineComponent, ref, reactive, computed, toRaw, onMounted} from 'vue';
import {defHttp} from '/@/utils/http/axios';
import {useValidateAntFormAndTable} from '/@/hooks/system/useJvxeMethods';
import {queryFamilyMemberListByMainId, queryDataById, saveOrUpdate} from '../FamilyBase.api';
import {JVxeTable} from '/@/components/jeecg/JVxeTable';
import {familyMemberColumns} from '../FamilyBase.data';
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import JCategorySelect from '/@/components/Form/src/jeecg/components/JCategorySelect.vue';
import {duplicateValidate} from '/@/utils/helper/validator'
import {Form} from 'ant-design-vue';

const useForm = Form.useForm;

export default defineComponent({
  name: "FamilyBaseForm",
  components: {
    JDictSelectTag,
    JCategorySelect,
    JVxeTable,
  },
  props: {
    formDisabled: {
      type: Boolean,
      default: false
    },
    formData: {
      type: Object, default: () => {
      }
    },
    formBpm: {type: Boolean, default: true}
  },
  emits: ['success'],
  setup(props, {emit}) {
    const loading = ref(false);
    const familyMemberTableRef = ref();
    const familyMemberTable = reactive<Record<string, any>>({
      loading: false,
      columns: familyMemberColumns,
      dataSource: []
    });
    const activeKey = ref('familyMember');
    const formData = reactive<Record<string, any>>({
      id: '',
      familyNumber: '',
      name: '',
      sex: '',
      idCard: '',
      year: null
    });

    let familyNumber=ref()

    //表单验证
    const validatorRules = reactive({
      aturalCode: [{required: true, message: '请输入自然村屯编号!'},],
      familyNumber: [{
        required: true,
        message: '请输入户主编号!'
      }, {validator: familyNumberDuplicatevalidate}],
      name: [{required: true, message: '请输入户主姓名!'},],
      // idCard: [{
      //   required: true,
      //   message: '请输入身份证号!'
      // }, {
      //   pattern: '^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{7}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}',
      //   message: '不符合校验规则!'
      // },],
    });
    const {
      resetFields,
      validate,
      validateInfos
    } = useForm(formData, validatorRules, {immediate: true});
    const dbData = {};
    const formItemLayout = {
      labelCol: {xs: {span: 24}, sm: {span: 5}},
      wrapperCol: {xs: {span: 24}, sm: {span: 16}},
    };

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


    async function changeYears(val) {
      // console.log("val===>",val)
      // console.log("familyNumber===>",familyNumber)
      const familyMemberDataList = await queryFamilyMemberListByMainId(familyNumber,val);
      // const familyMemberDataList = await queryFamilyMemberListByMainId(row['familyNumber']);
      familyMemberTable.dataSource = [...familyMemberDataList];
    }

    function add() {
      resetFields();
      familyMemberTable.dataSource = [];
    }

    async function edit(row) {
      console.log("row=====>",row)
      Object.keys(row).map(k => {
        formData[k] = row[k];
      });
      //主表数据
      // await queryMainData(row.id);
     familyNumber = row['familyNumber']

      //子表数据
      const familyMemberDataList = await queryFamilyMemberListByMainId(row['familyNumber'],row['year']);
      // const familyMemberDataList = await queryFamilyMemberListByMainId(row['familyNumber']);
      familyMemberTable.dataSource = [...familyMemberDataList];
    }

    async function queryMainData(id) {
      const row = await queryDataById(id);
      Object.keys(row).map(k => {
        formData[k] = row[k];
      });
    }

    const {getSubFormAndTableData, transformData} = useValidateAntFormAndTable(activeKey, {
      'familyMember': familyMemberTableRef,
    });

    async function getFormData() {
      await validate();
      return transformData(toRaw(formData))
    }

    async function submitForm() {
      const mainData = await getFormData();
      const subData = await getSubFormAndTableData();
      const values = Object.assign({}, dbData, mainData, subData);
      console.log('表单提交数据', values)
      const isUpdate = values.id ? true : false
      await saveOrUpdate(values, isUpdate);
      //关闭弹窗
      emit('success');
    }

    function setFieldsValue(values) {
      if (values) {
        Object.keys(values).map(k => {
          formData[k] = values[k];
        });
      }
    }

    /**
     * 值改变事件触发-树控件回调
     * @param key
     * @param value
     */
    function handleFormChange(key, value) {
      formData[key] = value;
    }

    async function familyNumberDuplicatevalidate(_r, value) {
      return duplicateValidate('qfy_family_base', 'family_number', value, formData.id || '')
    }

    return {
      familyMemberTableRef,
      familyMemberTable,
      validatorRules,
      validateInfos,
      activeKey,
      loading,
      formData,
      setFieldsValue,
      handleFormChange,
      formItemLayout,
      disabled,
      getFormData,
      submitForm,
      add,
      edit,
      changeYears,
    }
  }
});
</script>
