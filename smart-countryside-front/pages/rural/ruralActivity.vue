<template>
  <div class='ruralActivity'>
    <!-- 导航栏 -->
    <!-- <navigation title="村民活动"></navigation> -->
    <!-- 九宫格 -->
    <view class="gridBox">
      <u-grid
        :border="false"
        col="4"
      >
        <u-grid-item
          v-for="(item,index) in list1"
          :key="index"
          @click="clickGird(item)"
        >
          <div class="gridCont">
            <div
              style="font-weight: 500;font-size: 30px;
                color: #13227A;
                margin-bottom: 3px;"
              :class="item.name"
            ></div>
            <text
              class="two_fs"
              size="12"
            >{{item.title}}</text>
          </div>
        </u-grid-item>
      </u-grid>
    </view>
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
  data() {
    return {
      //九宫格
      list1: [{
        name: 'iconfont icon-faqirenwupingyi',
        title: '创建活动',
        url: '/pages/rural/modules/ruralActivityAdd'
      },
      {
        name: 'iconfont icon-ico',
        title: '扫码签到',
        url: '/pages/rural/modules/ruralActivitySignIn'
      },

      {
        name: 'iconfont icon-yewuliufaqibeifen2x',
        title: '我发起的',
        url: '/pages/rural/modules/myInitiate'
      },
      {
        name: 'iconfont icon-ziyuan193',
        title: '我参与的',
        url: '/pages/rural/modules/myJoin'
      },
      ],
      //活动列表
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [],
    }
  },
  created() {
    this.getData(this.current);
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
          this.getData(this.current);
        }, 100);
      }
    },
    //列表查询
    getData(current) {
      this.dataList = [
        { id: 1, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/1.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 2, num: '2023/10/12 星期四', state: 2, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/5.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 3, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/4.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 4, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 5, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 6, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 7, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 8, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 9, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 10, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 11, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 12, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        // { id: 13, num: '2023/10/12 星期四',state:1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
      ]
    },
    //九宫格
    clickGird(item) {
      // this.$refs.uToast.success(`点击了第${item + 1}个`)
      uni.navigateTo({
        url: item.url,
      });
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
  padding: 8px 10px 10px;
  //九宫格样式
  .gridBox {
    .gridCont {
      background: white;
      padding: 5px 0px 10px;
      display: flex;
      flex-direction: column;
      align-items: center;
      width: 100%;
      font-size: 12px;
      border-radius: 10px;
    }
  }
  .gridBox /deep/ .u-grid-item {
    width: 22% !important;
  }
  .gridBox /deep/ .u-grid {
    justify-content: space-between !important;
  }
  //列表样式
  .donationRecord {
    padding: 0px 10px 5px;
    margin-top: 10px;
    border-radius: 10px;
    .content {
      height: 100%;
      max-height: 570px;
      // overflow: auto;
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