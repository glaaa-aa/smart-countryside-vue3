<template>
  <div class="my">
    <!-- 头部背景 -->
    <!-- <div class="heard">
      <image
        style="width: 100%; height: 100%"
        class="loginBackground"
        :src="bjImg"
        mode=""
      ></image>
      <div class="heardCont">
        <u-avatar
          src="1http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg"
          style="margin: 0px auto"
          shape="circle"
          size="60"
        ></u-avatar>
        <div class="fz_my_message" style="font-weight: bold; margin-top: 15px">
          <span style="margin-right: 5px;">村民</span>
          <span>{{ mobileStr("13037354303") || "" }}</span>
        </div>
      </div>
    </div> -->
    <!-- 个人信息 -->
    <div
      style="margin: 2px 0px 8px 0px; padding-top: 5px; background-color: white"
    >
      <div
        style="
          display: flex;
          justify-content: space-between;
          padding: 10px 10px;
          background-image: url('../../static/index/index_info2.jpg');
          background-size: 77% 120%;
          position: relative;
        "
         @click="goMyInfo"
      >
        <div style="display: flex; align-items: center">
          <u-avatar size="60" :src="getImgUrl(userInfo.avatar)"></u-avatar>
          <div style="margin-left: 10px">
            <div style="margin-bottom: 5px" class="fs_info_two">
              <span style="font-weight: bold; margin-right: 10px">{{
                userInfo.realname
              }}</span>
              <span>{{ userInfo.orgCode || "" }}</span>
            </div>
            <div style="margin-bottom: 4px" class="fs_info_two">
              {{ userInfo.phone|| "" }}
            </div>
            <div style="color: #606266" class="fs_info_three">
              {{ userInfo.address || "" }}
            </div>
          </div>
        </div>
        <!-- <div
          class="fs_info_four"
          @click="goMyInfo"
          style="
            position: absolute;
            right: 4px;
            top: 4px;
            background: #007aff;
            color: white;
            border-radius: 15px;
            padding: 2px 5px;
            text-align: center;
            z-index: 999;
          "
        >
          <div>查看更多></div>
        </div> -->
        <div
          style="
            position: absolute;
            right: 4px;
            top: 35%;
            z-index: 999;
            transform: translate(-50%);
          "
        >
          <u-icon name="arrow-right" color="#2979ff" size="22"></u-icon>
        </div>
      </div>
    </div>
    <!-- 我的积分 -->
    <div class="model1 paddingBottom10 paddingLeft10 paddingRight10">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="我的积分"
          size="18"
          bold
          margin="0 0 10px 0"
        >
        </u--text>
      </div>
      <!-- 九宫格 -->
      <view class="gridBox1">
        <u-grid :border="false" col="3">
          <u-grid-item
            v-for="(item, index) in list1"
            :key="index"
            @click="clickGird1(item)"
          >
            <u-icon
              :customStyle="{ paddingTop: 20 + 'rpx' }"
              :name="item.name"
            ></u-icon>
            <text class="two_fs">{{ item.title }}</text>
          </u-grid-item>
        </u-grid>
      </view>
    </div>
    <!-- 其他服务 -->
    <div class="model1 paddingBottom10 paddingLeft10 paddingRight10">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="其他服务"
          size="18"
          bold
          margin="0 0 10px 0"
        >
        </u--text>
      </div>
      <!-- 九宫格 -->
      <view class="gridBox2">
        <u-grid :border="false" col="3">
          <u-grid-item
            v-for="(item, index) in list2"
            :key="index"
            @click="clickGird2(item)"
          >
            <!-- <u-icon
              :customStyle="{paddingTop:20+'rpx'}"
              :name="item.name"
            ></u-icon> -->
            <div
              style="font-size: 38px; color: #8a8a8a; margin-bottom: 5px"
              :class="item.name"
            ></div>
            <text class="two_fs">{{ item.title }}</text>
          </u-grid-item>
        </u-grid>
      </view>
    </div>
  </div>
</template>
<script>
import { getImgUrl, mobileStr } from "@/common/utils.js";
import "@/static/style/style.css";
export default {
  data() {
    return {
      userInfo: {},
      avatarSrc:
        "1http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg",
      // bjImg: require("@/static/index/login_bj.jpg"),
      //我的积分
      list1: [
        {
          name: require("@/static/index/grid7.png"),
          title: "积分查看",
          url: "/pages/points/pointsList",
        },
        {
          name: require("@/static/index/grid8.png"),
          title: "积分排名",
          url: "/pages/points/pointsRanking",
        },
        {
          name: require("@/static/index/grid9.png"),
          title: "积分填报",
          url: "/pages/points/pointsFill",
        },
      ],
      //其他服务
      list2: [
        {
          name: "iconfont icon-paizhao",
          title: "随手拍记录",
          flag: true,
          url: "/pages/rural/snapshot",
        },
        {
          name: "iconfont icon-wenjuantiaocha",
          title: "填报问卷",
          flag: true,
          url: "/pages/rural/fillQuestionnaire",
        },

        {
          name: "iconfont icon-a-zu1319",
          title: "我的活动",
          flag: true,
          // url: '/pages/rural/ruralActivity'
        },
        {
          name: "iconfont icon-yijianfankui",
          title: "意见反馈",
          flag: true,
          // url: "/pages/rural/ruralActivity",
        },
        {
          name: "iconfont icon-kefu",
          title: "帮助中心",
          flag: true,
          // url: "/pages/contradiction/mediate",
        },
        {
          name: "iconfont icon-guanyuwomen",
          title: "关于我们",
          flag: true,
          // url: "/pages/contradiction/mediate",
        },
      ],
    };
  },
  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
  },
  methods: {
    getImgUrl,
    mobileStr,
    //查看居民详细信息
    goMyInfo() {
      uni.navigateTo({
        url: "/pages/index/myInfo",
      });
    },
    //我的积分
    clickGird1(item) {
      uni.navigateTo({
        url: item.url,
      });
    },
    //其他服务
    clickGird2(item) {
      if (item.flag) return uni.$u.toast("待开放...");
      uni.navigateTo({
        url: item.url,
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.heard {
  position: relative;
  height: 215px;
  .heardCont {
    position: absolute;
    top: 54%;
    left: 50%;
    color: white;
    transform: translate(-50%, -50%);
    text-align: center;
    width: 250px;
    padding: 10px 0;
  }
}
//标题
.jifen {
  display: flex;
  align-items: center;
}
.jifen /deep/ .u-icon--right {
  flex-direction: row;
  align-items: center;
  width: 5px;
  height: 19px;
  background: #1658c8;
  margin-right: 5px;
}
.model1 {
  background: white;
  padding: 15px 15px;
  margin-bottom: 8px;
}
.gridBox1 /deep/ .u-icon__img {
  height: 60px !important;
  width: 60px !important;
  padding: 8px 0 !important;
}
.gridBox2 /deep/ .u-grid-item {
  margin-bottom: 15px;
}
</style>