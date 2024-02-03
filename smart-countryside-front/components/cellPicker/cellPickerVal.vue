<template>
  <view>
    <u-cell
      @click="state.show = true"
      icon="setting-fill"
      :title="state.defaultVal"
      :border="false"
      :clickable="true"
      :isLink="true"
      :titleStyle="
        state.defaultVal != state.defaulttxt
          ? state.titleStyle2
          : state.titleStyle
      "
    ></u-cell>
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
      type: Array,
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
