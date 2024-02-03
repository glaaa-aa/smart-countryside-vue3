<template>
  <view>
    <!-- 文字选择器 -->
    <u-picker
      :show="state.show"
      :columns="columns"
      @confirm="okTime"
      @cancel="unTime"
      :closeOnClickOverlay="true"
      @close="closeTime"
    ></u-picker>
  </view>
</template>

<script>
export default {
  props: {
    state: {
      type: Object,
    },
    columns: {
      style: Array,
    },
    okTime: {
      type: Function,
    },
    unTime: {
      type: Function,
    },
    closeTime: {
      type: Function,
    },
  },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style>
</style>
