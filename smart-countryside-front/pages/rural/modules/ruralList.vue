<template>
  <div class='ruralActivity'>
    <!-- 活动列表 -->
    <div
      class="donationRecord"
      style="background: white"
      v-if="dataList.length"
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
          @click="goDetails(item)"
        >
          <div class="left">
            <u--image
              :showLoading="true"
              :src="item.img"
              width="115px"
              height="80px"
            ></u--image>
          </div>
          <div class="right three_fs">
            <div style="">
              <div>
                <u--text
                  :lines="1"
                  :text="item.title"
                  size="17"
                ></u--text>
              </div>
              <div style="padding: 8rpx 0;">
                {{ item.num ||'—'}}
              </div>
              <div style="display:flex;justify-content: space-between;align-items: center;">
                <div style=" padding: 8rpx 0; ">
                  已有<span>12</span>/<span>30</span>参与
                </div>
                <div class="cyBtn">
                  <u-button
                    v-if="item.state=='1'"
                    type="primary"
                    size="mini"
                    text="已参与"
                    color="#FF9595"
                    class="two_fs"
                    customStyle="font-weight: bold;margin: 0;width: 66px;"
                  ></u-button>
                  <u-button
                    v-if="item.state=='2'"
                    type="primary"
                    size="mini"
                    text="在线报名"
                    color="#95B5FF"
                    customStyle="font-weight: bold;margin: 0;width: 66px;"
                  ></u-button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <u-loadmore color="##adacac" :status="status" />
      </scroll-view>
    </div>
    <div>
      <u-empty
        v-if="!dataList.length"
        mode="data"
        icon="http://cdn.uviewui.com/uview/empty/data.png"
        text="暂无活动"
      ></u-empty>
    </div>
  </div>
</template>
<script>
import navigation from "@/components/navigation/navigation";
import { getImgUrl } from "@/common/utils.js";
import "@/static/style/style.css";
export default {
  components: {
    navigation
  },
  props: {
    dataList: {
      type: Array,
      default: [],
    },
  },

  data() {
    return {
      //活动列表
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
    }
  },
  methods: {
    getImgUrl,
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
          // this.getData(this.current);
          this.childMethod();
        }, 100);
      }
    },
    childMethod() {
      this.$parent.fatherMethod();
    },
    // 查看详情
    goDetails(item) {
      uni.navigateTo({
        url: `/pages/rural/modules/ruralActivitySignIn?id=${item.id}`,
      });
    },
  },
}
</script>
<style lang="scss" scoped>
.ruralActivity {
  //列表样式
  .donationRecord {
    padding: 0px 10px 5px;
    margin-top: 10px;
    border-radius: 10px;
    .content {
      height: 100%;
      // max-height: 570px;
      max-height: 97vh;
      .item {
        display: flex;
        padding: 20px 0px;
        border-bottom: 1rpx solid #efefef;
        .left {
          width: 35%;
        }
        .right {
          width: 65%;
          padding-left: 5px;
          display: flex;
          justify-content: space-between;
          line-height: 22px;
          .cyBtn /deep/ .u-button__text {
            font-size: 15px !important;
          }
        }
      }
    }
  }
}
</style>