<template>
  <div>
    <BasicForm @register="registerForm" ref="formRef"/>
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated  @change="handleChangeTabs">
          <a-tab-pane tab="矛盾纠纷涉及人员表" key="dissensionInvolvedUser" :forceRender="true">
            <JVxeTable
              keep-source
              resizable
              ref="dissensionInvolvedUser"
              v-if="dissensionInvolvedUserTable.show"
              :loading="dissensionInvolvedUserTable.loading"
              :columns="dissensionInvolvedUserTable.columns"
              :dataSource="dissensionInvolvedUserTable.dataSource"
              :height="340"
              :rowNumber="true"
              :rowSelection="true"
              :disabled="formDisabled"
              :toolbar="true"
            />
          </a-tab-pane>
          <a-tab-pane tab="纠纷负责处理人员表" key="dissensionResponsibleUser" :forceRender="true">
            <JVxeTable
              keep-source
              resizable
              ref="dissensionResponsibleUser"
              v-if="dissensionResponsibleUserTable.show"
              :loading="dissensionResponsibleUserTable.loading"
              :columns="dissensionResponsibleUserTable.columns"
              :dataSource="dissensionResponsibleUserTable.dataSource"
              :height="340"
              :rowNumber="true"
              :rowSelection="true"
              :disabled="formDisabled"
              :toolbar="true"
            />
          </a-tab-pane>
    </a-tabs>

    <div style="width: 100%;text-align: center" v-if="!formDisabled">
      <a-button @click="handleSubmit" pre-icon="ant-design:check" type="primary">提 交</a-button>
    </div>
  </div>
</template>

<script lang="ts">

  import {BasicForm, useForm} from '/@/components/Form/index';
  import { computed, defineComponent, reactive, ref, unref } from 'vue';
  import {defHttp} from '/@/utils/http/axios';
  import { propTypes } from '/@/utils/propTypes';
  import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods';
  import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
  import {getBpmFormSchema,dissensionInvolvedUserColumns,dissensionResponsibleUserColumns} from '../Dissension.data';
  import {saveOrUpdate,dissensionInvolvedUserList,dissensionResponsibleUserList} from '../Dissension.api';

  export default defineComponent({
    name: "DissensionForm",
    components:{
      BasicForm,
    },
    props:{
      formData: propTypes.object.def({}),
      formBpm: propTypes.bool.def(true),
    },
    setup(props){
      const [registerForm, { setFieldsValue, setProps }] = useForm({
        labelWidth: 150,
        schemas: getBpmFormSchema(props.formData),
        showActionButtonGroup: false,
        baseColProps: {span: 8}
      });

      const formDisabled = computed(()=>{
        if(props.formData.disabled === false){
          return false;
        }
        return true;
      });

      const refKeys = ref(['dissensionInvolvedUser', 'dissensionResponsibleUser', ]);
      const activeKey = ref('dissensionInvolvedUser');
      const dissensionInvolvedUser = ref();
      const dissensionResponsibleUser = ref();
      const tableRefs = {dissensionInvolvedUser, dissensionResponsibleUser, };
      const dissensionInvolvedUserTable = reactive({
        loading: false,
        dataSource: [],
        columns:dissensionInvolvedUserColumns,
        show: false
      })
      const dissensionResponsibleUserTable = reactive({
        loading: false,
        dataSource: [],
        columns:dissensionResponsibleUserColumns,
        show: false
      })

      const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys,validateSubForm);

      function classifyIntoFormData(allValues) {
        let main = Object.assign({}, allValues.formValue)
        return {
          ...main, // 展开
          dissensionInvolvedUserList: allValues.tablesValue[0].tableData,
          dissensionResponsibleUserList: allValues.tablesValue[1].tableData,
        }
      }

      //表单提交事件
      async function requestAddOrEdit(values) {
        await saveOrUpdate(values, true);
      }

      const queryByIdUrl = '/dissension/dissension/queryById';
      async function initFormData(){
        let params = {id: props.formData.dataId};
        const data = await defHttp.get({url: queryByIdUrl, params});
        //设置表单的值
        await setFieldsValue({...data});
        requestSubTableData(dissensionInvolvedUserList, {id: data.id}, dissensionInvolvedUserTable, ()=>{
          dissensionInvolvedUserTable.show = true;
        });
        requestSubTableData(dissensionResponsibleUserList, {id: data.id}, dissensionResponsibleUserTable, ()=>{
          dissensionResponsibleUserTable.show = true;
        });
        //默认是禁用
        await setProps({disabled: formDisabled.value})
      }

      initFormData();

      return {
        registerForm,
        formDisabled,
        formRef,
        handleSubmit,
        activeKey,
        handleChangeTabs,
        dissensionInvolvedUser,
        dissensionResponsibleUser,
        dissensionInvolvedUserTable,
        dissensionResponsibleUserTable,
      }
    }
  });
</script>
