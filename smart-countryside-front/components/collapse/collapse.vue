<template>
  <view class="collapse">
    <view class="collapse" v-for="(item, index) in list" :key="index">
      <view class="headline" @click="tabsClick">
        <u-icon name="arrow-down-fill" v-if="!tabsIndex"></u-icon>
        <u-icon name="arrow-up-fill" v-else></u-icon>
        <view class="headlineName">{{ item.headlineName }}</view>
        <view>{{ item.quantity }}</view>
      </view>
      <view v-if="tabsIndex">
        <card :shuju="item.data"></card>
      </view>
    </view>
  </view>
</template>
  
  <script>
import card from "../../components/card/card.vue";
export default {
  name: "collapse",
  components: { card },
  props: ["list"],
  data() {
    return {
      tabsIndex: 0,
    };
  },
  methods: {
    tabsClick() {
      console.log("222");
      this.tabsIndex = !this.tabsIndex;
    },
  },
};
</script>
  
<style lang="less" scoped>
.headline {
  display: flex;
  margin-bottom: 10px;
  .headlineName {
    margin: 0 5px;
  }
}
</style>
  