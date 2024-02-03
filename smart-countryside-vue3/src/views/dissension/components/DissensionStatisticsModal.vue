<template>
    <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :width="1140" @ok="handleSubmit">
      <BasicForm @register="registerForm" ref="formRef">
        <template #JSelectUserByDept="{ model, field }">
          <JSelectUserByDept v-model:value="model[field]" :disabled="!name" :isRadioSelection="true" />
        </template>
        <template #separate="{ model, field }">
          <a-divider orientation="left" v-if="!name && !detail">涉及人员</a-divider>
          <a-divider orientation="left" v-if="name && !detail">分派人员</a-divider>
          <a-divider orientation="left" v-if="detail">人员信息</a-divider>
        </template>
      </BasicForm>
      <!-- 子表单区域 -->
      <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs" v-if="!detail">
        <a-tab-pane tab="矛盾纠纷涉及人员" key="dissensionInvolvedUser" :forceRender="true" v-if="!name">
          <JVxeTable
            keep-source
            resizable
            ref="dissensionInvolvedUser"
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
        <a-tab-pane v-else tab="矛盾纠纷负责人员" key="dissensionResponsibleUser" :forceRender="true">
          <JVxeTable
            keep-source
            resizable
            ref="dissensionInvolvedUser"
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
      <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs" v-else>
        <a-tab-pane tab="矛盾纠纷涉及人员" key="dissensionInvolvedUser" :forceRender="true">
          <JVxeTable
            keep-source
            resizable
            ref="dissensionInvolvedUser"
            :loading="dissensionInvolvedUserTable.loading"
            :columns="dissensionInvolvedUserTable.columns"
            :dataSource="dissensionInvolvedUserTable.dataSource"
            :height="340"
            :rowNumber="true"
            :rowSelection="true"
            :disabled="formDisabled"
            :toolbar="!detail"
          />
        </a-tab-pane>
        <a-tab-pane tab="矛盾纠纷负责人员" key="dissensionResponsibleUser" :forceRender="true">
          <JVxeTable
            keep-source
            resizable
            ref="dissensionInvolvedUser"
            :loading="dissensionResponsibleUserTable.loading"
            :columns="dissensionResponsibleUserTable.columns"
            :dataSource="dissensionResponsibleUserTable.dataSource"
            :height="340"
            :rowNumber="true"
            :rowSelection="true"
            :disabled="formDisabled"
            :toolbar="!detail"
          />
        </a-tab-pane>
      </a-tabs>
    </BasicModal>
  </template>
  
  <script lang="ts" setup>
  import { ref, computed, unref, reactive } from 'vue';
  import JSelectUserByDept from '/@/components/Form/src/jeecg/components/JSelectUserByDept.vue';
  import { defHttp } from '/@/utils/http/axios';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { JVxeTable } from '/@/components/jeecg/JVxeTable';
  import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts';
  import { formSchema, dissensionInvolvedUserColumns, dissensionResponsibleUserColumns } from '../Dissension.data';
  import { saveOrUpdate, dissensionInvolvedUserList, dissensionResponsibleUserList, addAssign } from '../Dissension.api';
  import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
  // Emits声明
  const emit = defineEmits(['register', 'success']);
  const isUpdate = ref(true);
  const name = ref(false);
  const detail = ref(false);
  const code = ref('');
  const formDisabled = ref(false);
  const refKeys = ref(['dissensionInvolvedUser']);
  const activeKey = ref('dissensionInvolvedUser');
  const InvolvedUser = ref([]);
  const ResponsibleUser = ref([]);
  const dissensionInvolvedUser = ref();
  const dissensionResponsibleUser = ref();
  const tableRefs = { dissensionInvolvedUser };
  const dissensionInvolvedUserTable = reactive({
    loading: false,
    dataSource: [],
    columns: dissensionInvolvedUserColumns,
  });
  const dissensionResponsibleUserTable = reactive({
    loading: false,
    dataSource: [],
    columns: dissensionResponsibleUserColumns,
  });
  //表单配置
  const [registerForm, { setProps, resetFields, setFieldsValue, validate }] = useForm({
    labelWidth: 130,
    schemas: name.value ? [] : formSchema,
    showActionButtonGroup: false,
    baseColProps: { span: 8 },
  });
  //表单赋值
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    //重置表单
    await reset();
    if (data?.isAssign) {
      name.value = true;
    } else {
      name.value = false;
    }
    if (data?.isDetail) {
      detail.value = true;
    } else {
      detail.value = false;
    }
    if (data?.isAssign) {
      activeKey.value = 'dissensionResponsibleUser';
    } else {
      activeKey.value = 'dissensionInvolvedUser';
    }
    setModalProps({ confirmLoading: false, showCancelBtn: data?.showFooter, showOkBtn: data?.showFooter });
    isUpdate.value = !!data?.isUpdate;
    code.value = data?.record?.code;
    formDisabled.value = !data?.showFooter;
    if (unref(isUpdate)) {
      //表单赋值
      await setFieldsValue({
        ...data.record,
      });
      getData(data?.record?.code);
      requestSubTableData(dissensionResponsibleUserList, { mainId: data?.record?.code }, dissensionResponsibleUserTable);
      requestSubTableData(dissensionInvolvedUserList, { mainId: data?.record?.code }, dissensionInvolvedUserTable);
    }
    // 隐藏底部时禁用整个表单
    setProps({ disabled: data?.isDetail ? data?.isDetail : data?.isAssign });
  });
  //方法配置
  const [handleChangeTabs, handleSubmit, requestSubTableData, formRef] = useJvxeMethod(
    requestAddOrEdit,
    classifyIntoFormData,
    tableRefs,
    activeKey,
    refKeys
  );
  
  function getData(code) {
    defHttp.get({ url: dissensionInvolvedUserList, params: { mainId: code } }).then((res) => {
      InvolvedUser.value = res;
    });
    defHttp.get({ url: dissensionResponsibleUserList, params: { mainId: code } }).then((res) => {
      ResponsibleUser.value = res;
    });
  }
  
  //设置标题
  const title = computed(() => (!unref(isUpdate) ? '新增' : name.value ? '分派人员' : '编辑'));
  
  async function reset() {
    await resetFields();
    activeKey.value = 'dissensionInvolvedUser';
    dissensionInvolvedUserTable.dataSource = [];
    dissensionResponsibleUserTable.dataSource = [];
  }
  function classifyIntoFormData(allValues) {
    let main = Object.assign({}, allValues.formValue);
    return {
      ...main, // 展开
      dissensionInvolvedUserList: !unref(isUpdate)
        ? allValues.tablesValue[0].tableData
        : name.value
        ? InvolvedUser.value
        : allValues.tablesValue[0].tableData,
      dissensionResponsibleUserList: !unref(isUpdate) ? ResponsibleUser.value : name.value ? allValues.tablesValue[0].tableData : ResponsibleUser.value,
    };
  }
  //表单提交事件
  async function requestAddOrEdit(values) {
    try {
      setModalProps({ confirmLoading: true });
      //提交表单
      const newObj = Object.assign({ code: code.value }, values);
      if (!unref(isUpdate) || !name.value) {
        await saveOrUpdate(values, isUpdate.value);
      } else {
        await addAssign(newObj);
      }
      //关闭弹窗
      closeModal();
      //刷新列表
      emit('success');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }
  </script>
  
  <style lang="less" scoped>
  /** 时间和数字输入框样式 */
  :deep(.ant-input-number) {
    width: 100%;
  }
  
  :deep(.ant-calendar-picker) {
    width: 100%;
  }
  </style>
  