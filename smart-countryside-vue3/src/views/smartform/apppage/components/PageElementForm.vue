<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="父级节点" v-bind="validateInfos.pid">
            <j-tree-select
              placeholder="请选择上级id"
              v-model:value="formData.pid"
              dict="qfy_app_page_element,title,id"
              pidField="pid"
              pidValue="0"
              hasChildField="has_child"
              conver-is-leaf-val ="1"
              :disabled="disabled">
            </j-tree-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="组编号" v-bind="validateInfos.groupNo">
            <a-input v-model:value="formData.groupNo" placeholder="请输入组编号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="标题" v-bind="validateInfos.title">
            <a-input v-model:value="formData.title" placeholder="请输入标题" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="摘要" v-bind="validateInfos.digest">
            <a-input v-model:value="formData.digest" placeholder="请输入摘要" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="跳转方式" v-bind="validateInfos.jumpMode">
            <j-dict-select-tag type='radio' v-model:value="formData.jumpMode" dictCode="yn" placeholder="请选择跳转方式(0是链接 1是图片)" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="外部链接或内容图链接" v-bind="validateInfos.exteriorPath">
            <a-input v-model:value="formData.exteriorPath" placeholder="请输入外部链接或内容图链接" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="标题图" v-bind="validateInfos.titleImg">
            <a-input v-model:value="formData.titleImg" placeholder="请输入标题图" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否生效 " v-bind="validateInfos.iseffect">
            <j-dict-select-tag type='radio' v-model:value="formData.iseffect" dictCode="yn" placeholder="请选择是否生效 0否 1是" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="阅读次数" v-bind="validateInfos.readyCount">
	          <a-input-number v-model:value="formData.readyCount" placeholder="请输入阅读次数" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="排序" v-bind="validateInfos.sortno">
	          <a-input-number v-model:value="formData.sortno" placeholder="请输入排序" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="菜单类型" v-bind="validateInfos.menuType">
            <a-input v-model:value="formData.menuType" placeholder="请输入菜单类型(0是资讯轮播 1是资讯广告 2是商城轮播 3是商城广告）" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="背景图" v-bind="validateInfos.bgImg">
            <a-input v-model:value="formData.bgImg" placeholder="请输入背景图" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="app/ios 跳转路径" v-bind="validateInfos.appforward">
            <a-input v-model:value="formData.appforward" placeholder="请输入app/ios 跳转路径" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否使用角色权限" v-bind="validateInfos.hasRoleAuth">
            <a-input v-model:value="formData.hasRoleAuth" placeholder="请输入是否使用角色权限" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, unref, defineProps, computed, onMounted } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import JTreeSelect from '/@/components/Form/src/jeecg/components/JTreeSelect.vue';
  import { getValueType } from '/@/utils';
  import {loadTreeData, saveOrUpdateDict} from '../PageElement.api';
  import { Form } from 'ant-design-vue';
  
  const useForm = Form.useForm;
  const formRef = ref();
  const isUpdate = ref(true);
  const expandedRowKeys = ref([]);
  const treeData = ref([]);
  const pidField = ref<string>('pid');
  const emit = defineEmits(['register', 'ok']);
  let model: Nullable<Recordable> = null;
  const formData = reactive<Record<string, any>>({
    id: '',
    pid: undefined,
    groupNo: '',   
    title: '',   
    digest: '',   
    jumpMode: undefined,
    exteriorPath: '',   
    titleImg: '',   
    iseffect: undefined,
    readyCount: undefined,
    sortno: undefined,
    menuType: '',   
    bgImg: '',   
    appforward: '',   
    hasRoleAuth: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
    pid: [{ required: true, message: '请输入上级id!'},],
    title: [{ required: true, message: '请输入标题!'},],
    titleImg: [{ required: true, message: '请输入标题图!'},],
    iseffect: [{ required: true, message: '请输入是否生效 0否 1是!'},],
    readyCount: [{ required: true, message: '请输入阅读次数!'},],
    sortno: [{ required: true, message: '请输入排序!'},],
    menuType: [{ required: true, message: '请输入菜单类型(0是资讯轮播 1是资讯广告 2是商城轮播 3是商城广告）!'},],
  };
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });
  const props = defineProps({
    formDisabled: { type: Boolean, default: false },
    formData: { type: Object, default: ()=>{} },
    formBpm: { type: Boolean, default: true }
  });

  // 表单禁用
  const disabled = computed(()=>{
    if(props.formBpm === true){
      if(props.formData.disabled === false){
        return false;
      }else{
        return true;
      }
    }
    return props.formDisabled;
  });

  
  /**
   * 新增
   */
  function add(obj = {}) {
    edit(obj);
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(async () => {
      resetFields();
      expandedRowKeys.value = [];
      treeData.value = await loadTreeData({ async: false, pcode: '' });
      //赋值
      Object.assign(formData, record);
      model = record
    });
  }

  /**
   * 根据pid获取展开的节点
   * @param pid
   * @param arr
   */
  function getExpandKeysByPid(pid, arr) {
    if (pid && arr && arr.length > 0) {
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].key == pid && unref(expandedRowKeys).indexOf(pid) < 0) {
          expandedRowKeys.value.push(arr[i].key);
          getExpandKeysByPid(arr[i]['parentId'], unref(treeData));
        } else {
          getExpandKeysByPid(pid, arr[i].children);
        }
      }
    }
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    // 触发表单验证
    await validate();
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    if (formData.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in formData) {
      //如果该数据是数组并且是字符串类型
      if (formData[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          formData[data] = formData[data].join(',');
        }
      }
    }
    await saveOrUpdateDict(formData, isUpdate.value)
      .then(async (res) => {
        if (res.success) {
          await getExpandKeysByPid(formData['pid'], unref(treeData));
          delete formData['children'];
          emit('ok', {
            isUpdate: unref(isUpdate),
            values: { ...formData },
            expandedArr: unref(expandedRowKeys).reverse(),
            // 是否更改了父级节点
            changeParent: model != null && model['pid'] != formData['pid'],
          });
          createMessage.success(res.message);
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  /**
   * 值改变事件触发
   * @param key
   * @param value
   */
  function handleFormChange(key, value) {
    formData[key] = value;
  }

  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    height: 500px !important;
    overflow-y: auto;
    padding: 14px;
  }
</style>
