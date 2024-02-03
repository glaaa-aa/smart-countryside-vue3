<template>
  <div>
    <div
      class="donationRecord"
      v-if="dataList.length"
      style="background: white"
    >
      <scroll-view
        class="content"
        :scroll-y="true"
        @scrolltolower="$emit('scrolltolower')"
      >
        <div class="item" v-for="item in dataList" :key="item.uid">
          <div class="left">
            <u--image
              :showLoading="true"
              :src="item.img"
              width="80px"
              height="80px"
            ></u--image>
          </div>
          <div class="center">
            <div style="">
              <div>
                <u--text :lines="1" :text="item.reason" size="16"></u--text>
              </div>
              <!-- <div class="three_fs" style="padding: 8rpx 0">
                {{ item.score || "—" }}积分
              </div> -->
              <div class="three_fs" style="padding: 8rpx 0; color: #a29f9f">
                {{ item.date }}
              </div>
            </div>
          </div>
          <div class="right three_fs"> {{ item.score || "—" }}积分</div>
        </div>
        <u-loadmore color="##adacac" :status="status" />
      </scroll-view>
    </div>
    <u-empty
      v-if="!dataList.length"
      mode="data"
      icon="http://cdn.uviewui.com/uview/empty/data.png"
      text="暂无明细"
    ></u-empty>
  </div>
</template>

<script>
import { getImgUrl } from "@/common/utils.js";
export default {
  props: {
    dataList: {
      type: Array,
      default: [],
    },
    status: {
        type: String,
        default: "nomore",
      },
  },
  data() {
    return {
      // status: "nomore",
      // current: 1,
      // pages: 0,
      // pageSize: 20,
      // flag: false,
      // dataList: [
      //   {
      //     uid: 1,
      //     reason:
      //       "测试测试测试测试测试测试测试测试踩踩踩测试测试测试测试测试从",
      //     score: 233,
      //     date: "2023-02-14",
      //   },
      // ],
    };
  },
  created() {
    // this.getData(this.current);
  },

  methods: {
    getImgUrl,
    //下拉加载
    // scrolltolower() {
    //   console.log("下拉加载");
    //   this.current = ++this.current;
    //   if (this.pages < this.current) {
    //     uni.showToast({
    //       title: "没有更多数据了",
    //       icon: "none",
    //       duration: 1000,
    //     });
    //     this.status = "nomore";
    //     return;
    //   }
    //   if (this.flag) {
    //     setTimeout(() => {
    //       this.status = "loading";
    //       this.getData(this.current);
    //     }, 100);
    //   }
    // },
    // ok() {
    //   this.current = 1;
    //   this.pages = 0;
    //   this.dataList = [];
    //   this.getData(this.current);
    // },
  },
};
</script>

<style lang="less" scoped>
.donationRecord {
  padding: 10px 10px;
  .content {
    height: 100%;
    max-height: 390px;
    // overflow: auto;
    .item {
      display: flex;
      padding: 5px 0px;
      border-bottom: 1rpx solid #efefef;
      align-items: center;
      .left {
        width: 22%;
      }
      .center {
        width: 65%;
        padding: 0px 8px 0px 5px;
        display: flex;
        justify-content: space-between;
        padding-bottom: 4rpx;
        // border-bottom: 1rpx solid #efefef;
      }
      .right {
        width: 20%;
        font-size: 14px;
        text-align: right;
        color: red;
      }
    }
  }
}
.donationRecord /deep/ .u-input {
  background-color: #fff !important;
  border-radius: 20rpx !important;
  padding: 0 18rpx !important;

  // width: 50%;
}
</style>
