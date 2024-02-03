<template>
  <div class="alumniInformationDetail">
    <!-- 返回 -->
    <!-- <view
      style="
        padding: 6px 10px 0px;
        font-size: 14px;
        color: #3c9cff;
        font-weight: bold;
        text-decoration: underline;
        text-align: right;
      "
      @click="gobackClick"
      >返回上一页</view
    > -->
    <div class="contents" v-if="dataList.id">
      <!-- <div class="title">{{ dataList.title }}</div> -->
      <u-parse :content="dataList.detailContent"></u-parse>
    </div>
    <u-empty
      v-if="!dataList.id"
      mode="data"
      icon="http://cdn.uviewui.com/uview/empty/data.png"
      text="暂无内容"
    ></u-empty>
  </div>
</template>
  <script>
export default {
  data() {
    return {
      id: null,
      dataList: {},
    };
  },
  onLoad(e) {
    // console.log("页面参数", e);
    if (e.id) {
      this.getData(e.id);
    }
  },
  methods: {
    // 返回
    gobackClick() {
      uni.navigateBack({
        delta: 1,
      });
    },
    getData(id) {
      //内容详情
      this.$apis.getArticleId(id).then((res) => {
        console.log("内容详情========>", res);
        if (res.success) {
          this.dataList = res.result;
        }
      });
    },
  },

  //分享
  // onShareAppMessage(options) {
  //   return {
  //     path: `/pages/cms/newsDetail?id=${this.id}`,
  //     success: function (res) {
  //       console.log("分享成功");
  //     },
  //   };
  // },
  // onShareTimeline() {
  //   return {
  //     title: "都高一百周年校园资讯", //转发标题
  //     query: `id=${this.id}`,
  //     imageUrl: this.$mAssetsPath.index_logo, //标题图片
  //   };
  // },
};
</script>
  <style lang="scss" scoped>
.alumniInformationDetail {
  background-color: #fff;
  font-size: 32rpx;
  height: 100vh;
  overflow: auto;
  .contents {
    padding: 0px 16px 16px;

    .title {
      margin-bottom: 20rpx;
      text-align: center;
      font-weight: 700;
      font-size: 34rpx;
    }
  }
}
</style>
  