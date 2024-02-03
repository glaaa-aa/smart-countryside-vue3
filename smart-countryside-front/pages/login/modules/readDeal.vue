<template>
  <!-- 阅读协议页 -->
  <div class="readDeal">
    <div class="btn_read">
      <!-- 隐私协议 -->
      <div v-if="flag == 'yszc'" class="content">
        <u-parse :content="cmsSection.description"></u-parse>
      </div>
      <!-- 用户协议 -->
      <div v-if="flag == 'yhxy'" class="content">
        <u-parse :content="cmsSection.description"></u-parse>
      </div>
      <div style="margin-top: 60rpx">
        <button
          class="btn"
          type="primary"
          @click="isRead"
          style="
            height: 44px;
            line-height: 44px;
            background: #1990ff;
            font-size: 19px;
            border-radius: 10px;
          "
        >
          已阅读
        </button>
      </div>
    </div>
  </div>
</template>
  <script>
import loginVue from "../login.vue";
export default {
  data() {
    return {
      id: "1",
      flag: "",
      cmsSection: {
        nameCode: "",
        description: "",
      },
      // content: "",
      ids: "8", //
    };
  },

  onLoad: function (option) {
    console.log("编号=======>", option.flag);
    this.flag = option.flag;
    this.cmsSection.nameCode = option.flag;
    this.$apis.sectionList(this.cmsSection).then((res) => {
      console.log("协议内容", res);
      this.cmsSection.description = res.result[0].description;
      console.log("协议内容========>", this.cmsSection);
    });
  },
  methods: {
    // getData() {
    //   let urlarticle = this.$apis.url.article;
    //   this.$http.get(urlarticle + this.ids).then((res) => {
    //     if (res) {
    //       console.log("---", res);
    //       if (res.success) {
    //         this.content = res.result.detailContent;
    //       }
    //     }
    //   });
    // },
    //点击已阅读
    isRead() {
      // uni.showToast({
      //   title: "已阅读",
      //   duration: 2000,
      //   success: () => {
      //     setTimeout(() => {

      //     }, 600);
      //   },
      // });
      uni.navigateBack({
        delete: 1,
      });
    },
  },
};
</script>
  <style lang="scss" scoped>
.readDeal {
  padding: 15px 15px;
  background: white;
  .title {
    font-size: 17px;
    margin-bottom: 15rpx;
  }
  .title1 {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 15rpx;
  }
  .title2 {
    font-size: 15px;
    margin-bottom: 15rpx;
  }
}
.content {
  min-height: 85vh;
}
</style>