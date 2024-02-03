<template>
  <div class="md:flex">
    <template v-for="(item, index) in data" :key="item.title">
      <Card
        size="small"
        :loading="loading"
        :title="item.title"
        class="md:w-1/4 w-full !md:mt-0 !mt-4"
        :class="[index + 1 < 4 && '!md:mr-4']"
        :canExpan="false"
      >
        <template #extra>
          <Tag :color="item.color">{{ item.action }}</Tag>
        </template>

        <div class="py-4 px-4 flex justify-between">
          <CountTo :startVal="0" :endVal="item.value" class="text-2xl" />
          <Icon :icon="item.icon" :size="40" />
        </div>

        <div class="p-2 px-4 flex justify-between">
          <span>总{{ item.title }}</span>
          <CountTo :startVal="1" :endVal="item.total" />
        </div>
      </Card>
    </template>
  </div>
</template>
<script lang="ts" setup>
import { ref, watchEffect } from 'vue';
import { CountTo } from '/@/components/CountTo/index';
import { Icon } from '/@/components/Icon';
import { Tag, Card } from 'ant-design-vue';
import { growCardList } from '../data';

const props = defineProps({
  loading: {
    type: Boolean,
  },
  EchartsObj: {
    type: Object,
    default: undefined,
  },
});

const data = ref<any[]>([]);

watchEffect(() => {
  data.value = [
    {
      title: '矛盾纠纷',
      icon: 'transaction|svg',
      value: props.EchartsObj ? props.EchartsObj?.count : 0,
      total: props.EchartsObj ? props.EchartsObj?.count : 0,
      color: 'purple',
      action: '年',
    },
    {
      title: '已提交',
      icon: 'visit-count|svg',
      value: props.EchartsObj ? props.EchartsObj?.submitted : 0,
      total: props.EchartsObj ? props.EchartsObj?.submitted : 0,
      color: 'green',
      action: '月',
    },
    {
      title: '调解中',
      icon: 'total-sales|svg',
      value: props.EchartsObj ? props.EchartsObj?.underway : 0,
      total: props.EchartsObj ? props.EchartsObj?.underway : 0,
      color: 'blue',
      action: '月',
    },
    {
      title: '已结束',
      icon: 'download-count|svg',
      value: props.EchartsObj ? props.EchartsObj?.completed : 0,
      total: props.EchartsObj ? props.EchartsObj?.completed : 0,
      color: 'orange',
      action: '周',
    },
  ];
});
</script>
