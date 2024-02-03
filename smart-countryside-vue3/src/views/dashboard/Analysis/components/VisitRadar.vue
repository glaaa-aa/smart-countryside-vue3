<template>
  <Card title="转化率" :loading="loading">
    <div ref="chartRef" :style="{ width, height }"></div>
  </Card>
</template>
<script lang="ts" setup>
import { Ref, ref, watch } from 'vue';
import { Card } from 'ant-design-vue';
import { useECharts } from '/@/hooks/web/useECharts';

const props = defineProps({
  loading: Boolean,
  width: {
    type: String as PropType<string>,
    default: '100%',
  },
  height: {
    type: String as PropType<string>,
    default: '300px',
  },
  EchartsObj: {
    type: Object,
    default: undefined,
  },
});

const chartRef = ref<HTMLDivElement | null>(null);
const { setOptions } = useECharts(chartRef as Ref<HTMLDivElement>);
watch(
  () => props.loading,
  () => {
    if (props.loading) {
      return;
    }
    setOptions({
      legend: {
        bottom: 0,
        data: ['访问', '购买'],
      },
      tooltip: {},
      radar: {
        radius: '60%',
        splitNumber: 8,
        indicator: [
          {
            name: '已提交',
          },
          {
            name: '调解中',
          },
          {
            name: '已结束',
          },
          // {
          //   name: '手机',
          // },
          // {
          //   name: 'Ipad',
          // },
          // {
          //   name: '耳机',
          // },
        ],
      },
      series: [
        {
          type: 'radar' as 'custom',
          symbolSize: 0,
          areaStyle: {
            shadowBlur: 0,
            shadowColor: 'rgba(0,0,0,.2)',
            shadowOffsetX: 0,
            shadowOffsetY: 10,
            opacity: 1,
          },
          data: [
            {
              value: [props.EchartsObj ? props.EchartsObj?.submitted : 0, 0, 0],
              name: '已提交',
              itemStyle: {
                color: '#b6a2de',
              },
            },
            {
              value: [0, props.EchartsObj ? props.EchartsObj?.underway : 0, 0],
              name: '调解中',
              itemStyle: {
                color: '#5ab1ef',
              },
            },
            {
              value: [0, 0, props.EchartsObj ? props.EchartsObj?.completed : 0],
              name: '已结束',
              itemStyle: {
                color: '#67E0E3',
              },
            },
          ],
        },
      ],
    });
  },
  { immediate: true }
);
</script>
