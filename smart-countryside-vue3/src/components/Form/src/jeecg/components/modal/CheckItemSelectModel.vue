<!--用户选择框-->
<template>
  <div>
    <BasicModal
      v-bind="$attrs"
      @register="register"
      :title="modalTitle"
      :width="showSelected ? '1200px' : '900px'"
      wrapClassName="j-user-select-modal"
      @ok="handleOk"
      destroyOnClose
      @visible-change="visibleChange"
    >
      <a-row>
        <a-col :span="showSelected ? 18 : 24">
          <BasicTable
            ref="tableRef"
            :columns="columns"
            :scroll="tableScroll"
            v-bind="getBindValue"
            :useSearchForm="true"
            :formConfig="formConfig"
            :api="getCheckItemList"
            :searchInfo="searchInfo"
            :rowSelection="rowSelection"
            :indexColumnProps="indexColumnProps"
            :showIndexColumn="false"
            :afterFetch="afterFetch"
          >
            <!-- update-begin-author:taoyan date:2022-5-25 for: VUEN-1112一对多 用户选择 未显示选择条数，及清空 -->
            <template #tableTitle></template>
            <!-- update-end-author:taoyan date:2022-5-25 for: VUEN-1112一对多 用户选择 未显示选择条数，及清空 -->
          </BasicTable>
        </a-col>
        <a-col :span="showSelected ? 6 : 0">
          <BasicTable
            v-bind="selectedTable"
            :dataSource="selectRows"
            :useSearchForm="true"
            :formConfig="{ showActionButtonGroup: false, baseRowStyle: { minHeight: '40px' } }"
          >
            <!--操作栏-->
            <template #action="{ record }">
              <a href="javascript:void(0)" @click="handleDeleteSelected(record)"><Icon icon="ant-design:delete-outlined"></Icon></a>
            </template>
          </BasicTable>
        </a-col>
      </a-row>
    </BasicModal>
  </div>
</template>
<script lang="ts">
import {defineComponent, unref, ref, watch, reactive, toRefs} from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { getCheckItemList } from '/@/api/common/api';
  import { createAsyncComponent } from '/@/utils/factory/createAsyncComponent';
  import { useSelectBiz } from '/@/components/Form/src/jeecg/hooks/useSelectBiz';
  import { useAttrs } from '/@/hooks/core/useAttrs';
  import { selectProps } from '/@/components/Form/src/jeecg/props/props';

  export default defineComponent({
    name: 'CheckItemSelectModel',
    components: {
      //此处需要异步加载BasicTable
      BasicModal,
      BasicTable: createAsyncComponent(() => import('/@/components/Table/src/BasicTable.vue'), {
        loading: true,
      }),
    },
    props: {
      ...selectProps,
      //选择框标题
      modalTitle: {
        type: String,
        default: '请选择加分项',
      },
      //update-begin---author:wangshuai ---date:20230703  for：【QQYUN-5685】5、离职人员可以选自己------------
      //排除用户id的集合
      excludeUserIdList: {
        type: Array,
        default: [],
      },
      //update-end---author:wangshuai ---date:20230703  for：【QQYUN-5685】5、离职人员可以选自己------------
    },
    emits: ['register', 'getSelectResult'],
    setup(props, { emit, refs }) {
      // update-begin-author:taoyan date:2022-5-24 for: VUEN-1086 【移动端】用户选择 查询按钮 效果不好 列表展示没有滚动条
      const tableScroll = ref<any>({ x: false });
      const tableRef = ref();
      //注册弹框
      const [register, { closeModal }] = useModalInner(() => {
        if (window.innerWidth < 900) {
          tableScroll.value = { x: 900 };
        } else {
          tableScroll.value = { x: false };
        }
        //update-begin-author:taoyan date:2022-6-2 for: VUEN-1112 一对多 用户选择 未显示选择条数，及清空
        setTimeout(() => {
          if (tableRef.value) {
            tableRef.value.setSelectedRowKeys(selectValues['value'] || []);
          }
        }, 800);
        //update-end-author:taoyan date:2022-6-2 for: VUEN-1112 一对多 用户选择 未显示选择条数，及清空
      });
      // update-end-author:taoyan date:2022-5-24 for: VUEN-1086 【移动端】用户选择 查询按钮 效果不好 列表展示没有滚动条
      const attrs = useAttrs();
      //表格配置
      const config = {
        canResize: false,
        bordered: true,
        size: 'small',
      };
      const getBindValue = Object.assign({}, unref(props), unref(attrs), config);
      const [{ rowSelection, visibleChange, selectValues, indexColumnProps, getSelectResult, handleDeleteSelected, selectRows }] = useSelectBiz(
        getCheckItemList,
        getBindValue,
      );
      const searchInfo = ref(props.params);
      // update-begin--author:liaozhiyang---date:20230811---for：【issues/657】右侧选中列表删除无效
      watch(rowSelection.selectedRowKeys, (newVal) => {
        //update-begin---author:wangshuai ---date: 20230829  for：null指针异常导致控制台报错页面不显示------------
        if(tableRef.value){
          tableRef.value.setSelectedRowKeys(newVal);
        }
        //update-end---author:wangshuai ---date: 20230829 for：null指针异常导致控制台报错页面不显示------------
      });
      // update-end--author:liaozhiyang---date:20230811---for：【issues/657】右侧选中列表删除无效
      //查询form
      const formConfig = {
        baseColProps: {
          xs: 24,
          sm: 8,
          md: 6,
          lg: 8,
          xl: 6,
          xxl: 6,
        },
        //update-begin-author:taoyan date:2022-5-24 for: VUEN-1086 【移动端】用户选择 查询按钮 效果不好 列表展示没有滚动条---查询表单按钮的栅格布局和表单的保持一致
        actionColOptions: {
          xs: 30,
          sm: 8,
          md: 8,
          lg: 8,
          xl: 8,
          xxl: 8,
        },
        //update-end-author:taoyan date:2022-5-24 for: VUEN-1086 【移动端】用户选择 查询按钮 效果不好 列表展示没有滚动条---查询表单按钮的栅格布局和表单的保持一致
        schemas: [
          {
            label: '积分项目',
            field: 'name',
            component: 'JInput',
            colProps:{
              span:12
            },
          },
        ],
      };
      //定义表格列
      const columns = [
        {
          title: '积分项目',
          dataIndex: 'name',
          align: 'center',
          width: 60
        },
        {
          title: '加分值',
          dataIndex: 'qualifiedscore',
          align: 'center',
          width: '12.5%'
        },
        {
          title:'项目说明',
          dataIndex: 'defaultVal',
          align:"center",
          width: '25%',
          scopedSlots: {
            customRender: 'ellipsisText'
          }
        },
        {
          title: '扣分值',
          dataIndex: 'unqualifiedscore',
          align: 'center',
          width: '12.5%'
        },
        {
          title: '量化指标',
          dataIndex: 'remark',
          align: 'center',
          width: '25%',
          scopedSlots: {
            customRender: 'ellipsisText'
          }
        },
      ];
      //已选择的table信息
      const selectedTable = {
        pagination: false,
        indexColumnProps:false,
        scroll: { y: 390 },
        size: 'small',
        canResize: false,
        bordered: true,
        rowKey: 'id',
        columns: [
          {
            title: '姓名',
            dataIndex: 'name',
            width: 40,
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            width: 40,
            slots: { customRender: 'action' },
          },
        ],
      };
      /**
       * 确定选择
       */
      function handleOk() {

        getSelectResult((options, values) => {
          //回传选项和已选择的值
          emit('getSelectResult', options, values);
          //关闭弹窗
          closeModal();
        });
      }
      
      //update-begin---author:wangshuai ---date:20230703  for：【QQYUN-5685】5、离职人员可以选自己------------
      /**
       * 用户返回结果逻辑查询
       */
      function afterFetch(record) {
        let excludeList = props.excludeUserIdList;
        if(!excludeList){
          return record;
        }
        let arr:any[] = [];
        //如果存在过滤用户id集合，并且后台返回的数据不为空
        if(excludeList.length>0 && record && record.length>0){
          for(let item of record){
            if(excludeList.indexOf(item.id)<0){
              arr.push({...item})
            }
          }
          return arr;
        }
        return record;
      }

      //update-end---author:wangshuai ---date:20230703  for：【QQYUN-5685】5、离职人员可以选自己------------

      return {
        //config,
        handleOk,
        searchInfo,
        register,
        indexColumnProps,
        visibleChange,
        getBindValue,
        getCheckItemList,
        formConfig,
        columns,
        rowSelection,
        selectRows,
        selectedTable,
        handleDeleteSelected,
        tableScroll,
        tableRef,
        afterFetch,
      };
    },
  });
</script>
