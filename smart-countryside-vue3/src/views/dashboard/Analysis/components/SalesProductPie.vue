<template>
  <Card title="矛盾纠纷" :loading="loading">
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
    }
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
        tooltip: {
          trigger: 'item',
        },

        series: [
          {
            name: '矛盾纠纷',
            type: 'pie',
            radius: '80%',
            center: ['50%', '50%'],
            color: ['#5ab1ef', '#b6a2de', '#67e0e3'],
            data: [
              { value: props.EchartsObj ? props.EchartsObj?.submitted : 0, name: '已提交' },
              { value: props.EchartsObj ? props.EchartsObj?.underway : 0, name: '调解中' },
              { value: props.EchartsObj ? props.EchartsObj?.completed : 0, name: '已结束' },
            ].sort(function (a, b) {
              return a.value - b.value;
            }),
            roseType: 'radius',
            animationType: 'scale',
            animationEasing: 'exponentialInOut',
            animationDelay: function () {
              return Math.random() * 400;
            },
          },
        ],
      });
    },
    { immediate: true }
  );
</script>
