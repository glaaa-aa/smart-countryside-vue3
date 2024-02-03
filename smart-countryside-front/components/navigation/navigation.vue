<template>
  <view class="navigation">
    <view
      style="font-size: 22px;color:#888"
      @click="goIndexClick"
      class="iconfont icon-zhuye"
    >
    </view>

    <view>{{title}}</view>
    <view
      style="font-size: 21px;color:#14237B"
      @click="gobackClick"
      class="iconfont icon-fanhui"
    ></view>
  </view>
</template>

<script>
export default {
  name: "navigation",
  props: ['title', 'urls'],
  data() {
    return {

    };
  },
  methods: {
    goIndexClick() {
      uni.switchTab({
        url: "/pages/index/index",
      });
    },
    gobackClick() {
      uni.navigateBack({
        delta: 1,
      });
    }
  },
}
</script>

<style lang="less" scoped>
.navigation {
  height: 44px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 20px;
  background-color: #fff;
  z-index: 1000;
  font-weight: 700;
}
</style>