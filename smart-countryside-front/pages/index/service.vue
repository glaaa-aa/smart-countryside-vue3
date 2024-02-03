<template>
  <div class="service">
    <!-- 导航栏 -->
    <!-- <view class="navigation">
      <u-icon
        name="arrow-left"
        class="goBack"
        size="17"
        @click="gobackClick"
      ></u-icon>
      <view>服务</view>
      <view></view>
    </view> -->
    <!-- 九宫格 -->
    <div class="content">
      <view class="gridBox" v-for="i in gridList" :key="i.id">
        <div
          class=""
          style="
            margin-bottom: 8px;
            border-bottom: 1px solid #f2f2f2;
            padding: 15px 15px 8px;
            font-size: 20px;
            font-weight: bold;
          "
        >
          {{ i.title }}
        </div>
        <div v-for="item in i.arr" :key="item.id" style="padding: 0 15px 0">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              :text="item.title"
              size="18"
              bold
            >
            </u--text>
          </div>
          <u-grid :border="false" col="5">
            <u-grid-item
              v-for="(is, listIndex) in item.list1"
              :key="listIndex"
              @click="clickGird(is)"
            >
              <u-icon
                :customStyle="{ paddingTop: 20 + 'rpx' }"
                :name="is.name"
              ></u-icon>
              <text class="two_fs">{{ is.title }}</text>
            </u-grid-item>
          </u-grid>
        </div>
        <div v-if="!i.arr.length" style="height: 50px;"></div>
      </view>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //九宫格
      gridList: [
        {
          id: 1,
          title: "社会基层治理",
          arr: [
            {
              id: 1,
              title: "矛盾纠纷",
              list1: [
                {
                  name: require("@/static/index/grid10.png"),
                  title: "矛盾排查",
                  url: "/pages/contradiction/mediate",
                },
                {
                  name: require("@/static/index/grid5.png"),
                  title: "调解分派",
                  url: "/pages/contradiction/allot",
                },

                {
                  name: require("@/static/index/grid12.png"),
                  title: "处理反馈",
                  url: "/pages/contradiction/feedback",
                },
              ],
            },
          ],
        },
        {
          id: 2,
          title: "乡村治理",
          arr: [
            {
              id: 2,
              title: "积分管理",
              list1: [
                {
                  name: require("@/static/index/grid7.png"),
                  title: "积分查看",
                  url: "/pages/points/pointsList",
                },
                {
                  name: require("@/static/index/grid8.png"),
                  title: "积分排行",
                  url: "/pages/points/pointsRanking",
                },
                {
                  name: require("@/static/index/grid9.png"),
                  title: "积分填报",
                  url: "/pages/points/pointsFill",
                },
                {
                  name: require("@/static/index/grid2.png"),
                  title: "积分兑换",
                  url: "/pages/points/modules/pointsExchangeForm",
                  flag: true,
                },
              ],
            },
          ],
        },
        // {
        //   id: 3,
        //   title: "乡村共治1",
        //   arr: [
        //     {
        //       id: 3,
        //       title: "乡村共治",
        //       list1: [
        //         {
        //           name: require("@/static/index/grid4.png"),
        //           title: "村民活动",
        //           url: "/pages/rural/ruralActivity",
        //         },
        //         {
        //           name: require("@/static/index/grid1.png"),
        //           title: "随手拍",
        //           url: "/pages/rural/snapshot",
        //         },

        //         {
        //           name: require("@/static/index/grid3.png"),
        //           title: "填报问卷",
        //           url: "/pages/rural/fillQuestionnaire",
        //         },
        //         {
        //           name: require("@/static/index/grid6.png"),
        //           title: "热点资讯",
        //           url: "/pages/rural/hotNews",
        //         },
        //       ],
        //     },
        //   ],
        // },
        // {
        //   id: 4,
        //   title: "乡村治理4",
        //   arr: [
        //     {
        //       id: 4,
        //       title: "平安法治",
        //       list1: [
        //         {
        //           id: 1,
        //           name: require("@/static/index/grid13.png"),
        //           title: "一证查通",
        //           url: "https://www.baidu.com/",
        //           flag: true,
        //         },
        //         {
        //           name: require("@/static/index/grid14.png"),
        //           title: "反诈宣传",
        //           url: "/pages/publicSecurity/publicSecurityList",
        //           flag: false,
        //         },

        //         {
        //           id: 2,
        //           name: require("@/static/index/grid15.png"),
        //           title: "法律服务",
        //           url: "https://www.baidu.com/",
        //           flag: true,
        //         },
        //       ],
        //     },
        //   ],
        // },
        {
          id: 5,
          title: "数据看板",
          arr: [],
        },
      ],
    };
  },
  methods: {
    gobackClick() {
      uni.switchTab({
        url: "/pages/index/index",
      });
    },
    //九宫格
    clickGird(item) {
      if (item.flag) return uni.$u.toast("待开放...");
      console.log(item);
      uni.navigateTo({
        url: item.url,
      });
    },
  },
};
</script>
<style lang="scss" scoped>
// 导航
.navigation {
  height: 44px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 35px 0 15px;
  background-color: #fff;
  z-index: 1000;
  font-weight: 700;
}
.service {
  padding: 0px 0px 50px;
  .content {
    padding: 10px;
  }
}
.gridBox {
  background: white;
  // padding: 15px 15px 15px;
  border-radius: 10px;
  margin-bottom: 10px;
  padding-bottom: 10px;
}
.gridBox /deep/ .u-grid {
  margin: 10px;
}
.gridBox /deep/ .u-grid-item {
  width: 25% !important;
}

.gridBox /deep/ .u-icon__img {
  height: 50px !important;
  width: 50px !important;
  padding: 8px 0 !important;
}
.jifen /deep/ .u-icon--right {
  flex-direction: row;
  align-items: center;
  width: 5px;
  height: 19px;
  background: #1658c8;
  margin-right: 5px;
}
</style>