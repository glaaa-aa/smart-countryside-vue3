<template>
  <div class="pointsExchange">
    <!-- 导航栏 -->
    <navigation title="人数列表"></navigation>
    <!-- tabs切换1 -->
    <div style="margin-bottom: 5px;
        background: #fff">
      <u-tabs
        :list="list2"
        itemStyle="width: 50%; height: 34px;"
        lineWidth="60"
        lineHeight="1"
        activeStyle="color:#1658C8;font-size: 16px;"
        inactiveStyle="font-size: 16px;"
        lineColor="#1658C8"
        @change="changeTabs"
        :current="currentNum"
        :scrollable="false"
      ></u-tabs>
    </div>
    <div
      class="two_fs_list"
      v-if="dataList.length>0"
      style="padding: 5px 10px 8px;"
    >共计<span>{{dataList.length}}</span>人</div>
    <div
      class="donationRecord"
      v-if="dataList.length"
      style="background: white"
    >

      <scroll-view
        class="content"
        :scroll-y="true"
        @scrolltolower="scrolltolower"
      >
        <div
          class="item"
          v-for="item in dataList"
          :key="item.id"
        >
          <div class="left">
            <u-avatar
              :src="item.img"
              style="margin: 0px auto;"
              shape="circle"
              size="55"
            ></u-avatar>
          </div>
          <div class="right two_fs_list">
            <div style="">
              <div>
                <u--text
                  :lines="2"
                  :text="item.name"
                  size="18"
                ></u--text>
              </div>
              <div style="padding: 8rpx 0;">

                {{ mobileStr(item.phone)||'—'}}
              </div>

            </div>
          </div>
        </div>
        <u-loadmore color="##adacac" :status="status" />
      </scroll-view>
    </div>
    <u-empty
      v-else
      mode="data"
      icon="http://cdn.uviewui.com/uview/empty/data.png"
      text="暂无列表"
    ></u-empty>
  </div>
</template>

<script>
import { getImgUrl, mobileStr } from "@/common/utils.js";
export default {
  data() {
    return {
      status: "nomore",
      current: 0,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [],
      //tabs切换1
      list2: [
        { name: "总人数" },
        { name: "已报名" },
        { name: "已签到" },
      ],
      currentNum: 0,
    };
  },
  created() {
    this.getData(this.current);
  },
  onLoad(e) {
    this.currentNum = e.pageId;
  },
  methods: {
    getImgUrl,
    mobileStr,
    //tabs切换1
    changeTabs(item) {
      console.log(item);
      this.currentNum = item.index;
    },
    //下拉加载
    scrolltolower() {
      console.log("下拉加载");
      this.current = ++this.current;
      if (this.pages < this.current) {
        uni.showToast({
          title: "没有更多数据了",
          icon: "none",
          duration: 1000,
        });
        this.status = "nomore";
        return;
      }
      if (this.flag) {
        setTimeout(() => {
          this.status = "loading";
          this.getData(this.current);
        }, 100);
      }
    },

    ok() {
      this.current = 1;
      this.pages = 0;
      this.dataList = [];
      this.getData(this.current);
    },
    getData(current) {
      this.dataList = [
        { id: 1, phone: '13037354303', name: '马化腾', img: '1https://cdn.uviewui.com/uview/goods/1.jpg', },
        { id: 2, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/5.jpg', },
        { id: 3, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/4.jpg', },
        { id: 4, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 5, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 6, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 7, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 8, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 9, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 10, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 11, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 12, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        { id: 13, phone: '13037354303', name: '马化腾', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      ]
      // this.$apis
      //   .juankuanRecords({
      //     pageNo: current,
      //     pageSize: this.pageSize,
      //   })
      //   .then((res) => {
      //     console.log("个人记录==>", res);
      //     if (res.success) {
      //       this.current = res.result.current;
      //       this.pages = res.result.pages;
      //       this.pageSize = res.result.size;
      //       res.result.records.forEach((item) => {
      //         if (item.avatar == null) {
      //           item.avatar =
      //             "http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg";
      //         }
      //       });
      //       this.Data = this.Data.concat(res.result.records);
      //       this.flag = true;
      //     }
      //   });
    },
  },
};
</script>

<style lang="less" scoped>
.pointsExchange {
  padding-top: 50px;
}
.donationRecord {
  padding: 10px 10px;
  .content {
    height: 100%;
    max-height: 80vh;
    // overflow: auto;
    .item {
      display: flex;
      padding: 5px 0px;
      border-bottom: 1rpx solid #efefef;
      .left {
        // width: 25%;
        padding: 5px 5px 5px 20px;
      }
      .right {
        width: 75%;
        padding: 0px 8px 0px 5px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-bottom: 4rpx;
        // border-bottom: 1rpx solid #efefef;
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
