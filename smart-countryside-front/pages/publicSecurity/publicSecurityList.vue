<template>
  <div class="pointsList">
    <!-- 导航栏 -->
    <!-- <navigation title="反诈宣传"></navigation> -->
    <!-- 列表 -->
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
        <div class="item" v-for="item in dataList" :key="item.id">
          <div class="right fz_two_fs_list">
            <div>
              <div style="display: flex; margin-bottom: 4px">
                <div
                  style="
                    width: 30%;
                    background: #95b5ff;
                    color: #1658c8;
                    height: 25px;
                    line-height: 25px;
                    text-align: center;
                    margin-right: 10px;
                    border-radius: 5px;
                  "
                >
                  {{ item.name }}
                </div>
                <div
                  style="
                    width: 70%;
                    word-break: break-all;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 2;
                    overflow: hidden;
                  "
                >
                  {{ item.address }}
                </div>
              </div>
              <div class="three_fs" style="margin-top: 10px; color: #909399">
                {{ item.time || "—" }}
              </div>
            </div>
          </div>
          <div class="left">
            <u--image
              :showLoading="true"
              :src="item.img"
              width="100px"
              height="70px"
              radius="8"
            ></u--image>
          </div>
        </div>
        <u-loadmore color="##adacac" :status="status" />
      </scroll-view>
    </div>
    <u-empty
      v-else
      mode="data"
      icon="http://cdn.uviewui.com/uview/empty/data.png"
      text="暂无明细"
    ></u-empty>
  </div>
</template>

<script>
import { getImgUrl } from "@/common/utils.js";
import navigation from "@/components/navigation/navigation";
export default {
  components: {
    navigation,
  },
  data() {
    return {
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [], //数据源
    };
  },
  onLoad() {
    this.getData(this.current);
  },

  methods: {
    getImgUrl,
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
    //查询列表
    getData(current) {
      this.dataList = [
        {
          id: 2023001,
          name: "反诈宣传",
          time: "2023-10-10",
          address: "春节反诈套路整理，千万不要上当!",
          img: "https://cdn.uviewui.com/uview/goods/1.jpg",
        },
        {
          id: 2023002,
          name: "反诈宣传",
          time: "2023-10-10",
          address:
            "春节反诈套路整理，千万不要上当!春节反诈套路整理，千万不要上当!生活中该如何防骗呢?",
          img: "https://cdn.uviewui.com/uview/goods/5.jpg",
        },
        {
          id: 2023003,
          name: "反诈宣传",
          time: "2023-10-10",
          address:
            "春节反诈套路整理，千万不要上当!春节反诈套路整理，千万不要上当!",
          img: "https://cdn.uviewui.com/uview/goods/4.jpg",
        },
        // { id: 2023004, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023005, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023006, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023007, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: '1https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023008, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: '1https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023009, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023010, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023011, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023012, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
        // { id: 2023013, name: '反诈宣传', time: '2023-10-10', address: '生活中该如何防骗呢?', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      ];
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
.pointsList {
  padding-top: 5px;
}

// 列表
.donationRecord {
  padding: 10px 10px;
  .content {
    height: 100%;
    max-height: 97vh;
    // overflow: auto;
    .item {
      display: flex;
      padding: 10px 0px;
      border-bottom: 1rpx solid #efefef;
      .left {
        width: 30%;
        // padding: 5px 0 5px 10px;
      }
      .right {
        width: 70%;
        padding: 0px 8px 0px 5px;
        // display: flex;
        // align-items: center;
        // justify-content: space-between;
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
