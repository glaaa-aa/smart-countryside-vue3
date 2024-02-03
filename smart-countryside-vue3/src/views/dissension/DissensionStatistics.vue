<template>
  <div>
    <div id="main"></div>
    <!--引用表格-->
    <div class="body">
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
<!--          <a-button type="primary" preIcon="ant-design:export-outlined" @click="onExportXls"> 导出</a-button>-->
<!--          <j-upload-button type="primary" preIcon="ant-design:import-outlined" @click="onImportXls">导入</j-upload-button>-->
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
    </div>
    <!-- 表单区域 -->
    <DissensionStatisticsModal @register="registerModal" @success="handleSuccess"></DissensionStatisticsModal>
  </div>
</template>

<script lang="ts" name="dissension-dissension" setup>
import * as echarts from 'echarts';
import { ref, reactive, unref, onMounted, onBeforeMount } from 'vue';
import { BasicTable, useTable, TableAction } from '/@/components/Table';
import { useListPage } from '/@/hooks/system/useListPage';
import { useModal } from '/@/components/Modal';
import DissensionStatisticsModal from './components/DissensionStatisticsModal.vue';
import { columns, searchFormSchema } from './DissensionStatistics.data';
import { list, deleteOne, batchDelete, getImportUrl, getExportUrl, echartsData } from './DissensionStatistics.api';
import { downloadFile } from '/@/utils/common/renderUtils';
import { useUserStore } from '/@/store/modules/user';
const checkedKeys = ref<Array<string | number>>([]);
const chartsData = ref<any[]>([]);
const stateObj = ref<any>({ state: '1' });
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
    useSearchForm: false,
    searchInfo: stateObj,
    formConfig: {
      //labelWidth: 120,
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

onBeforeMount(async () => {
  const data = await echartsData();
  chartsData.value = [
    {
      value: data.submitted ? data.submitted : 0,
      stateType: '1',
      itemStyle: {
        color: '#409EFF',
      },
    },
    {
      value: data.underway ? data.underway : 0,
      stateType: '2',
      itemStyle: {
        color: '#909399',
      },
    },
    {
      value: data.completed ? data.completed : 0,
      stateType: '3',
      itemStyle: {
        color: '#67C23A',
      },
    },
  ];
  init();
});

function init() {
  const myChart = echarts.init(document.getElementById('main'));
  myChart.on('click', function (params) {
    stateObj.value.state = params.data?.stateType;
    reload()
  });
  const option = {
    legend: {
      top: 'bottom',
    },
    tooltip: {
      trigger: 'item',
    },
    xAxis: {
      type: 'category',
      data: ['已提交', '调解中', '已结束'],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        barWidth: 50,
        data: chartsData.value,
        type: 'bar',
      },
    ],
  };
  myChart.setOption(option);
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
  return [
    {
      label: '详情',
      onClick: handleDetail.bind(null, record),
    },
  ];
}

/**
 * 下拉操作栏
 */
function getDropDownAction(record) {
  return [];
}
</script>

<style scoped>
#main {
  height: 300px;
  background: #fff;
  margin: 10px;
  padding-bottom: 20px;
  box-sizing: border-box;
}
.body {
  margin: 10px;
}
</style>
