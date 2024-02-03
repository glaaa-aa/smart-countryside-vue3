<template>
  <div class="alumniInformation">
    <!-- 头部背景 -->
    <!-- <div class="header_img">
        <image :src="btnBj" style="width: 100%; height: 100%"></image>
      </div> -->
    <!-- <div class="head_box"></div> -->

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
    <!-- 活动列表 -->
    <div class="" style="padding: 20rpx">
      <div
        class="activeList"
        v-for="item in activeList"
        :key="item.id"
        @click="goDetails(item)"
      >
        <div
          class="two_fs"
          style="
            display: flex;
            flex-direction: column;
            justify-content: space-between;
          "
        >
          <div
            style="
              word-break: break-all;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 2;
              overflow: hidden;
            "
            class=""
          >
            {{ item.title }}
          </div>
          <div class="three_fs" v-if="item.publishTime" style="margin-top: 10px; color: #909399">
            {{ item.publishTime.substring(0, 10) }}
          </div>
        </div>
        <div v-if="item.titleImg">
          <u--image
            :showLoading="true"
            :src="getImgUrl(item.titleImg)"
            width="130px"
            height="80px"
            radius="8"
          ></u--image>
        </div>
      </div>
    </div>
    <u-empty
      v-if="!activeList.length"
      mode="data"
      icon="http://cdn.uviewui.com/uview/empty/data.png"
      text="列表为空"
    ></u-empty>
  </div>
</template>
  <script>
import { getImgUrl, mobileStr, recursion } from "@/common/utils.js";
export default {
  data() {
    return {
      activeList: [],
      cmsSection: {
        nameCode: "",
      },
      btnBj: this.$mAssetsPath.xiaoqing_bj,
      // btnBj: this.$mAssetsPath.z,
      pages: 0,
      current: 1,
      flag: false, //节流阀
    };
  },
  onReachBottom() {
    console.log("666");
    this.current = ++this.current;
    console.log("---current---", this.current);
    if (this.pages < this.current) {
      console.log("没有更多数据了");
      uni.showToast({
        title: "没有更多数据了",
        icon: "none",
        duration: 1000,
      });
      return;
    }
    if (this.flag) {
      this.getData(this.current);
    }
  },
  methods: {
    getImgUrl,
    // 返回
    gobackClick() {
      uni.navigateBack({
        delta: 1,
      });
    },
    goDetails(item) {
      uni.navigateTo({
        url: `/pages/rural/modules/hotNewsDetails?id=${
          item.id
        }&dataList=${JSON.stringify(item)}`,
      });
    },
    //查询文章内容
    // getData(current) {
    //   let obj = {
    //     nameCode: this.cmsSection.nameCode,
    //     pageNo: current,
    //     pageSize: this.pageSize,
    //     realName: this.searchValue,
    //   };
    //   this.$apis.getSectionAll(obj).then((res) => {
    //     console.log("查询文章内容========>", res);
    //     if (res.success) {
    //       this.activeList = this.activeList.concat(res.result.records);
    //       this.pages = res.result.pages;
    //       this.current = res.result.current;
    //       this.flag = true;
    //     }
    //   });
    // },
  },
  onLoad(option) {
    this.cmsSection.nameCode = option.nameCode;
    //查询文章内容
    this.$apis.getSectionAll(this.cmsSection).then((res) => {
      this.activeList = res.result.sort((a, b) => a.sort - b.sort);
      console.log("文章内容========>", this.activeList);
    });
    // this.getData(this.current);
  },
};
</script>
  <style lang="scss" scoped>
.alumniInformation {
  background: #f2f2f2;
  min-height: 100vh;
  .header_img {
    height: 240rpx;
  }
}

//活动列表
.activeList {
  background: white;
  margin-bottom: 20rpx;
  padding: 20rpx 30rpx;
  border-radius: 20rpx;
  padding-bottom: 30rpx;
  display: flex;
  justify-content: space-between;

  .timeStr {
    color: #b5b5b5;
  }

  .content {
    width: 100%;
    display: flex;
    justify-content: space-between;
    box-sizing: border-box;
    margin-top: 10rpx;
    align-items: center;

    .left {
      min-width: 68%;
      display: flex;
      flex-direction: column;
      justify-content: space-around;

      .left_p2 {
        display: flex;
        // font-size: 26rpx;
        color: #cccccc;

        .p1 {
          margin-right: 12rpx;
        }
      }
    }

    .right {
      width: 32%;
      display: flex;
      // flex-direction: column;
      // align-items: center;
      margin-left: 10rpx;
      padding-top: 18rpx;

      .state {
        border: 1px solid #999999;
        padding: 6rpx 20rpx;
        font-size: 24rpx;
        text-align: center;
        display: inline-block;
        margin-top: 20rpx;
        border-radius: 6rpx;
        color: #999999;
      }
    }
  }
}
</style>
  