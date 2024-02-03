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
        <div
          class="itemBox"
          v-for="item in dataList"
          :key="item.uid"
        >
          <div class="right two_fs">
            <div style="">
              <div style="display: flex; align-items: center">
                <div style="">{{ item.reason || "" }}</div>
              </div>
              <div
                class="three_fs"  
                style="padding: 8rpx 0; color: #a29f9f"
              >
                {{ item.date }}
              </div>
            </div>
            <div class="fontWeight"v-if="item.categoryName=='加分'" style="font-size: 18px;color:#007aff;display: flex; align-items: center; text-align:center">
              +{{ item.score||0 }}
            </div>
            <div  v-else style="font-size: 18px;color: red; display: flex; align-items: center; text-align:center">
              -{{ item.score||0 }}
            </div>
          </div>
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
  props:{
    dataList: {
				type: Array,
				default: [],
		},
    status: {
				type: String,
				default: 'nomore',
		},
  },
  data() {
    return {
      // status: "nomore",
      // current: 1,
      // pages: 0,
      // pageSize: 20,
      // flag: false,
      // Data: [],
    };
  },
  created() {
    // this.getData(this.current);
  },

  methods: {
    getImgUrl,
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
    //   this.Data = [];
    //   this.getData(this.current);
    // },
  },
};
</script>

<style lang="less" scoped>
.donationRecord {
  padding: 10px 0;
  .content {
    height: 100%;
    max-height: 348px;
    // max-height: 415px;
    // overflow: auto;
    .itemBox {
      display: flex;
      padding: 10px 10px;
      .left {
        width: 80rpx;
      }
      .right {
        width: calc(100%);
        padding: 0px 8px 0px 5px;
        display: flex;
        justify-content: space-between;
        padding-bottom: 4rpx;
        border-bottom: 1rpx solid #efefef;
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
