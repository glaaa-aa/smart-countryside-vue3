<template>
  <div>
    <!--引用表格-->
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <!--插槽:table标题-->
      <template #tableTitle>
<!--        <a-button type="primary" @click="handleAdd" preIcon="ant-design:plus-outlined"> 新增-->
<!--        </a-button>-->
<!--        <a-button type="primary" preIcon="ant-design:export-outlined" @click="onExportXls"> 导出-->
<!--        </a-button>-->
<!--        <j-upload-button type="primary" preIcon="ant-design:import-outlined" @click="onImportXls">-->
<!--          导入-->
<!--        </j-upload-button>-->
        <a-dropdown v-if="selectedRowKeys.length > 0">
          <template #overlay>
            <a-menu>
              <a-menu-item key="1" @click="batchHandleDelete">
                <Icon icon="ant-design:delete-outlined"></Icon>
                删除
              </a-menu-item>
            </a-menu>
          </template>
          <a-button>批量操作
            <Icon icon="mdi:chevron-down"></Icon>
          </a-button>
        </a-dropdown>
      </template>
      <!--操作栏-->
      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)"
                     :dropDownActions="getDropDownAction(record)"/>
      </template>
      <!--字段回显插槽-->
      <template v-slot:bodyCell="{ column, record, index, text }">
      </template>
    </BasicTable>
    <!-- 表单区域 -->
    <AndonRepairMoModal @register="registerModal" @success="handleSuccess"></AndonRepairMoModal>
  </div>
</template>

<script lang="ts" name="andonRepairMo" setup>
import {ref, computed, unref,watch} from 'vue';
import {BasicTable, useTable, TableAction} from '/@/components/Table';
import {useModal} from '/@/components/Modal';
import {useListPage} from '/@/hooks/system/useListPage'
import AndonRepairMoModal from './components/AndonRepairMoModal.vue'
import {columns, searchFormSchema} from './AndonRepairMo.data';
import {list, deleteOne, batchDelete, getImportUrl, getExportUrl} from './AndonRepairMo.api';
import {downloadFile} from '/@/utils/common/renderUtils';
import {useUserStore} from '/@/store/modules/user';
import {defineProps} from "vue/dist/vue";

const checkedKeys = ref<Array<string | number>>([]);
const userStore = useUserStore();
//注册model
const [registerModal, {openModal}] = useModal();
//注册table数据
const {prefixCls, tableContext, onExportXls, onImportXls} = useListPage({
  tableProps: {
    title: '维修工单表',
    api: (params)=>list({...params,moCode:props.moCode}),
    columns,
    canResize: false,
    useSearchForm: false,
    formConfig: {
      //labelWidth: 120,
      schemas: searchFormSchema,
      autoSubmitOnEnter: true,
      showAdvancedButton: true,
      fieldMapToNumber: [],
      fieldMapToTime: [],
    },
    showActionColumn: false,
    actionColumn: {
      width: 120,
      fixed: 'right'
    },
  },
  exportConfig: {
    name: "维修工单表",
    url: getExportUrl,
  },
  importConfig: {
    url: getImportUrl,
    success: handleSuccess
  },
})

const props = defineProps({
  moCode: {
    type: String,
    default: '',
    required: false
  },
})

watch(() => props.moCode, (newVal, oldVal) => {
  reload()
})

const [registerTable, {reload}, {rowSelection, selectedRowKeys}] = tableContext

/**
 * 新增事件
 */
function handleAdd() {
  openModal(true, {
    isUpdate: false,
    showFooter: true,
  });
}

/**
 * 编辑事件
 */
function handleEdit(record: Recordable) {
  openModal(true, {
    record,
    isUpdate: true,
    showFooter: true,
  });
}

/**
 * 详情
 */
function handleDetail(record: Recordable) {
  openModal(true, {
    record,
    isUpdate: true,
    showFooter: false,
  });
}

/**
 * 删除事件
 */
async function handleDelete(record) {
  await deleteOne({id: record.id}, handleSuccess);
}

/**
 * 批量删除事件
 */
async function batchHandleDelete() {
  await batchDelete({ids: selectedRowKeys.value}, handleSuccess);
}

/**
 * 成功回调
 */
function handleSuccess() {
  (selectedRowKeys.value = []) && reload();
}

/**
 * 操作栏
 */
function getTableAction(record) {
  return []
}

/**
 * 下拉操作栏
 */
function getDropDownAction(record) {
  return []
}


</script>

<style scoped>

</style>
