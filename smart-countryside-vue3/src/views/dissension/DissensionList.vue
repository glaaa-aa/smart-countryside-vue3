<template>
  <div>
    <!--引用表格-->
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <!--插槽:table标题-->
      <template #file="{ text }">
        <div style="width: 100%; overflow: hidden">
          <span v-if="!text" style="font-size: 12px; font-style: italic">无文件</span>
          <a-button
            v-else
            :ghost="true"
            v-for="(itm, index) in JSON.parse(text)"
            :style="{ marginTop: index !== 0 ? '10px' : '0px' }"
            :key="index"
            type="primary"
            preIcon="ant-design:download"
            size="small"
            @click="downloadFile(itm.filePath)"
          >
            <a-tooltip>
              <template #title>{{ itm.fileName }}</template>
              {{ itm.fileName }}
            </a-tooltip>
          </a-button>
        </div>
      </template>
      <template #tableTitle>
        <a-button type="primary" @click="handleAdd" v-auth="'dissension:qfy_dissension:add'" preIcon="ant-design:plus-outlined"> 新增</a-button>
<!--        <a-button type="primary" preIcon="ant-design:export-outlined" @click="onExportXls"> 导出</a-button>-->
<!--        <j-upload-button type="primary" preIcon="ant-design:import-outlined" @click="onImportXls">导入</j-upload-button>-->
        <a-dropdown v-if="selectedRowKeys.length > 0">
          <template #overlay>
            <a-menu>
              <a-menu-item key="1" @click="batchHandleDelete">
                <Icon icon="ant-design:delete-outlined"></Icon>
                删除
              </a-menu-item>
            </a-menu>
          </template>
          <a-button
            >批量操作
            <Icon icon="mdi:chevron-down"></Icon>
          </a-button>
        </a-dropdown>
      </template>
      <!--操作栏-->
      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)" :dropDownActions="getDropDownAction(record)" />
      </template>
      <!--字段回显插槽-->
      <template v-slot:bodyCell="{ column, record, index, text }"> </template>
    </BasicTable>
    <!-- 表单区域 -->
    <DissensionModal @register="registerModal" @success="handleSuccess"></DissensionModal>
  </div>
</template>

<script lang="ts" name="dissension-dissension" setup>
import { ref, computed, unref, onBeforeUnmount } from 'vue';
import { BasicTable, useTable, TableAction } from '/@/components/Table';
import { useListPage } from '/@/hooks/system/useListPage';
import { useModal } from '/@/components/Modal';
import DissensionModal from './components/DissensionModal.vue';
import { columns, searchFormSchema } from './Dissension.data';
import { list, deleteOne, batchDelete, getImportUrl, getExportUrl } from './Dissension.api';
import { downloadFile } from '/@/utils/common/renderUtils';
import { useUserStore } from '/@/store/modules/user';
const checkedKeys = ref<Array<string | number>>([]);
const userStore = useUserStore();
import { useGlobSetting } from '/@/hooks/setting';
const globSetting = useGlobSetting();
const baseUploadUrl = globSetting.uploadUrl;
//注册model
const [registerModal, { openModal }] = useModal();
//注册table数据
const { prefixCls, tableContext, onExportXls, onImportXls } = useListPage({
  tableProps: {
    title: '矛盾纠纷',
    api: list,
    columns,
    canResize: false,
    formConfig: {
      // labelWidth: 100,
      schemas: searchFormSchema,
      autoSubmitOnEnter: true,
      showAdvancedButton: true,
      fieldMapToNumber: [],
      fieldMapToTime: [],
    },
    actionColumn: {
      width: 160,
      fixed: 'right',
    },
  },
  exportConfig: {
    name: '矛盾纠纷',
    url: getExportUrl,
  },
  importConfig: {
    url: getImportUrl,
    success: handleSuccess,
  },
});

const [registerTable, { reload }, { rowSelection, selectedRowKeys }] = tableContext;

function testForm() {
  window.location.href=`/diaowen/design.html`
}

/**
 * 新增事件
 */
function handleAdd() {
  localStorage.setItem('dissensionEdit', 'false');
  openModal(true, {
    isUpdate: false,
    showFooter: true,
  });
}
/**
 * 编辑事件
 */
function handleEdit(record: Recordable) {
  localStorage.setItem('dissensionEdit', 'false');
  openModal(true, {
    record,
    isUpdate: true,
    showFooter: true,
  });
}
/**
 *  分派事件
 */
function handleAssign(record: Recordable) {
  localStorage.setItem('dissensionEdit', 'true');
  openModal(true, {
    record,
    isUpdate: true,
    showFooter: true,
    isAssign: true,
  });
}
/**
 * 详情
 */
function handleDetail(record: Recordable) {
  localStorage.setItem('dissensionEdit', 'true');
  openModal(true, {
    record,
    isUpdate: true,
    showFooter: false,
    isAssign: false,
    isDetail: true,
  });
}
/**
 * 删除事件
 */
async function handleDelete(record) {
  await deleteOne({ id: record.id }, handleSuccess);
}
/**
 * 批量删除事件
 */
async function batchHandleDelete() {
  await batchDelete({ ids: selectedRowKeys.value }, handleSuccess);
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
  if (record.state === '1') {
    return [
      {
        label: '编辑',
        onClick: handleEdit.bind(null, record),
        auth: 'dissension:qfy_dissension:edit',
      },
      {
        label: '反馈',
        onClick: handleAssign.bind(null, record),
      },
    ];
  } else if (record.state === '2') {
    return [
      {
        label: '反馈',
        onClick: handleAssign.bind(null, record),
      },
    ];
  } else {
    return [];
  }
}

/**
 * 下拉操作栏
 */
function getDropDownAction(record) {
  if (record.state === '1') {
    return [
      {
        label: '详情',
        onClick: handleDetail.bind(null, record),
      },
      {
        label: '删除',
        popConfirm: {
          title: '是否确认删除',
          confirm: handleDelete.bind(null, record),
          placement: 'topLeft',
        },
      },
    ];
  } else {
    return [
      {
        label: '详情',
        onClick: handleDetail.bind(null, record),
      },
    ];
  }
}

onBeforeUnmount(() => {
  localStorage.removeItem('dissensionEdit');
});
</script>

<style scoped>
</style>
