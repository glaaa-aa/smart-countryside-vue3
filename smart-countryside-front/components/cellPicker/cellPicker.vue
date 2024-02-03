<template>
  <view>
    <u-cell
      @click="state.show = true"
      :title="state.defaultVal"
      :border="false"
      :clickable="true"
      :isLink="true"
      :titleStyle="state.defaultVal == state.defaulttxt ? state.titleStyle : {}"
    >
      <template slot="icon">
        <u-icon class="cell_icon" :name="state.icon"></u-icon>
      </template>
    </u-cell>
    <!-- 时间选择器 -->
    <u-datetime-picker
      :show="state.show"
      v-model="state.value"
      mode="date"
      @confirm="okTime"
      @cancel="unTime"
      :closeOnClickOverlay="true"
      @close="closeTime"
    ></u-datetime-picker>
  </view>
</template>

<script>
export default {
  props: {
    state: {
      type: Object,
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

<style lang="less" scoped>
.cell_icon {
  margin-right: 10px;
}
</style>
