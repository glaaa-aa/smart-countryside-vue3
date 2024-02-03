<template>
  <div class="pointsView">
    <!-- 导航栏 -->
    <!-- <navigation
      style="background: none;"
      title="积分记录"
    ></navigation> -->
    <!-- 头部背景 -->
    <!-- <div class="heard">
      <image
        style="width: 100%; height: 218px"
        class="loginBackground"
        :src="bjImg"
        mode=""
      ></image>
      <div class="heardCont">
        <div>
          <span style="font-size: 26px"
            >总计{{ dataObj.totalAmount || 0 }}</span
          >
          <span style="font-size: 20px">积分</span>
        </div>
        <div
          @click="goHandle"
          style="
            padding: 4px 10px;
            border: 1px solid rgb(255, 255, 255);
            background: rgb(255, 255, 255);
            color: #007aff;
            border-radius: 6px;
            margin-top: 10px;
            text-align: center;
            width: 70px;
            font-weight: bold;
          "
        >
          积分兑换
        </div>
      </div>
    </div>
    <div
      class="pointsNum"
      style="
        display: flex;
        justify-content: space-around;
        background: white;
        padding: 10px 10px;
        text-align: center;
        color: #007aff;
      "
    >
      <div>
        <div class="two_fs" style="margin-bottom: 5px">总积分</div>
        <div class="two_fs_list fontWeight">{{ dataObj.totalAmount || 0 }}</div>
      </div>
      <div>
        <div class="two_fs" style="margin-bottom: 5px">已兑换</div>
        <div class="two_fs_list fontWeight">0</div>
      </div>
      <div>
        <div class="two_fs" style="margin-bottom: 5px">未兑换</div>
        <div class="two_fs_list fontWeight">0</div>
      </div>
    </div> -->
    <!-- 头部 -->
    <div class="headBox">
      <!-- 返回 -->
      <!-- <view
        style="
          position: absolute;
          right: 2%;
          top: 5px;
          padding: 6px 10px;
          font-size: 14px;
          color: #fff;
          font-weight: bold;
          text-decoration: underline;
        "
        @click="gobackClick"
        >返回上一页</view
      > -->
      <div class="topBox">
        <div>
          <div
            @click="beginTimeShow1 = true"
            style="
              padding-top: 8px;
              display: flex;
              align-items: center;
              font-size: 16px;
            "
          >
            <span style="margin-right: 5px">{{ timeVal }}</span>
            <u-icon
              v-if="!beginTimeShow1"
              color="white"
              size="15"
              name="arrow-down-fill"
            ></u-icon>
            <u-icon
              v-else
              color="white"
              size="15"
              name="arrow-up-fill"
            ></u-icon>
          </div>
          <div style="margin-top: 12px">
            <span >
              <span style="font-size: 12px">当月</span>
              <span style="font-size: 22px; font-weight: bold">{{ dataObj.baseScore || 0 }}</span>
              <span style="margin-left: 3px; font-size: 12px">积分</span>
              </span>
            <span style="margin: 0 15px; font-size: 14px">|</span>
            <span style="font-size: 12px">
              累计
              <span style="font-size: 22px;font-weight: bold">{{
                dataObj.totalAmount || 0
              }}</span>
              <span style="margin-left: 3px; font-size: 12px">积分</span>
            </span>
          </div>
          <div style="margin: 5px; font-size: 14px">上年积分不计入当年积分</div>
        </div>
        <div style="margin-top: 20px">
          <u--image
            :showLoading="true"
            :src="headSrc"
            width="120px"
            height="80px"
          ></u--image>
        </div>
      </div>
      <!-- <div class="bottomBox">
        <div>已扣分：0</div>
        <div>已兑换：0</div>
        <div>未兑换：0</div>
      </div> -->
    </div>
    <!-- tabs切换1 -->
    <div class="tabs1" style="margin-top: 2px; background: #fff">
      <u-tabs
        :list="list2"
        itemStyle="width: 50%; height: 40px;"
        lineWidth="100"
        lineHeight="1"
        activeStyle="color:#007aff;"
        inactiveStyle="color:#9b9c9f;"
        lineColor="#007aff"
        @change="changeTabs"
        :current="tabCurrent"
        :scrollable="false"
      ></u-tabs>
    </div>

    <div class="content" style="margin-top: 2px">
      <!-- 积分收支明细 -->
      <div class="studentsPage" v-if="tabCurrent == 0">
        <!-- tabs切换2 -->
        <div class="tabs2">
          <div
            class="tabsItem"
            :class="tabsActive == item.id ? 'tabsItem2' : 'tabsItem'"
            v-for="item in tabsList"
            :key="item.id"
            @click="clickTabs(item)"
          >
            {{ item.title }}
          </div>
        </div>
        <pointsDetail
          id="pointsDetail"
          :status="status1"
          @scrolltolower="scrolltolower1"
          :dataList="dataList1"
        ></pointsDetail>
      </div>
      <!-- 兑换记录 -->
      <div class="studentsPage" v-if="tabCurrent == 1">
        <pointsRecode
          id="pointsRecode"
          :status="status2"
          @scrolltolower="scrolltolower2"
          :dataList="dataList2"
        ></pointsRecode>
      </div>
    </div>
    <!-- 时间选择器 -->
    <u-datetime-picker
      v-model="timeVal"
      :show="beginTimeShow1"
      mode="year-month"
      :maxDate="Number(new Date())"
      @cancel="beginTimeShow1 = false"
      @confirm="beginTimeConfirm1"
      @close="beginTimeShow1 = false"
      closeOnClickOverlay
    ></u-datetime-picker>
  </div>
</template>
<script>
import "@/static/style/style.css";
import pointsDetail from "./modules/pointsDetail.vue";
import pointsRecode from "./modules/pointsRecode.vue";
import navigation from "../../components/navigation/navigation";
import { timestampToTime3 } from "@/common/utils.js";
import loginVue from "../login/login.vue";
export default {
  components: {
    pointsDetail,
    pointsRecode,
    navigation,
  },
  data() {
    return {
      // bjImg: require("@/static/index/jfck_bj.jpg"),
      headSrc: require("@/static/index/jfdh.png"),
      //时间选择器
      beginTimeShow1: false,
      timeVal: "",
      //tabs切换1
      list2: [{ name: "积分台账" }, { name: "兑换记录" }],
      tabCurrent: 0,
      //tabs切换2
      tabsList: [
        { id: 0, title: "全部" },
        { id: 1, title: "已加分" },
        { id: 2, title: "已扣分" },
        // { id: 4, title: '已兑换' },
      ],
      tabsActive: 0,
      dataObj: {},
      //积分明细
      dataList1: [],
      //下拉加载1
      status1: "nomore",
      current1: 1,
      pages1: 0,
      pageSize1: 20,
      flag1: false,
      //积分兑换记录
      dataList2: [],
      //下拉加载2
      status2: "nomore",
      current2: 1,
      pages2: 0,
      pageSize2: 20,
      flag2: false,
    };
  },
  onLoad(e) {
    console.log(e);
    if (e.uid) {
      this.dataObj = JSON.parse(e.obj) || {};
      console.log("页面参数", this.dataObj);
      
      this.getIntegralExchangeList(1); //兑换记录
    }
  },
  mounted() {
    this.doHandleYear();
  },
  methods: {
    timestampToTime3,
    //获取当前时间
    doHandleYear() {
      let date = new Date();
      let Y = date.getFullYear() + "-";
      let M =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      this.timeVal = Y + M;
      this.getIntegralDetails(1); //积分台账
    },
     // 返回
     gobackClick() {
      uni.navigateBack({
        delta: 1,
      });
    },
    //下拉加载1
    scrolltolower1() {
      console.log("下拉加载1");
      this.current1 = ++this.current1;
      if (this.pages1 < this.current1) {
        uni.showToast({
          title: "没有更多数据了",
          icon: "none",
          duration: 1000,
        });
        this.status1 = "nomore";
        return;
      }
      if (this.flag1) {
        setTimeout(() => {
          this.status1 = "loading";
          this.getIntegralDetails(this.current1);
        }, 100);
      }
    },
    //下拉加载2
    scrolltolower2() {
      console.log("下拉加载2");
      this.current2 = ++this.current2;
      if (this.pages2 < this.current2) {
        uni.showToast({
          title: "没有更多数据了",
          icon: "none",
          duration: 1000,
        });
        this.status2 = "nomore";
        return;
      }
      if (this.flag2) {
        setTimeout(() => {
          this.status2 = "loading";
          this.getIntegralDetails(this.current2);
        }, 100);
      }
    },
    //登记时间选择
    async beginTimeConfirm1(value) {
      this.timeVal = await this.timestampToTime3(value.value);
      this.getIntegralDetails(1);
      this.beginTimeShow1 = false;
      console.log(this.timeVal);
    },
    // timestampToTime3(timestamp) {
    //   let date = new Date(timestamp);
    //   let Y = date.getFullYear();
    //   let M =
    //     date.getMonth() + 1 < 10
    //       ? "0" + (date.getMonth() + 1)
    //       : date.getMonth() + 1;
    //   return Y + M;
    // },
    //tabs切换1
    changeTabs(item) {
      console.log(item);
      this.tabCurrent = item.index;
    },
    //tabs切换2
    clickTabs(item) {
      console.log(item);
      this.tabsActive = item.id;
      this.getIntegralDetails(1);
    },
    goHandle() {
      uni.navigateTo({
        url: "/pages/points/modules/pointsExchangeForm",
      });
    },
    //查询积分明细
    getIntegralDetails(current) {
      if (current == 1) {
        this.dataList1 = [];
      }
      let obj = {
        familyNo: this.dataObj.code,
        category: this.tabsActive,
        month: this.timeVal,
        pageNo: current,
        pageSize: this.pageSize1,
      };
      console.log("查询参数", obj);
      this.$apis.integralDetails(obj).then((res) => {
        console.log("查询积分明细==>", res);
        if (res.result != null) {
          this.current1 = res.result.current;
          this.pages1 = res.result.pages;
          this.pageSize1 = res.result.size;
          this.dataList1 = this.dataList1.concat(res.result.records);
          this.flag1 = true;
        } else {
          this.dataList1 = [];
        }
      });
    },
    //查询积分兑换记录
    getIntegralExchangeList(current) {
      let obj = {
        familyNo: this.dataObj.code,
        pageNo: current,
        pageSize: this.pageSize2,
      };
      // this.dataList2 = [
      //   {
      //     uid: 1,
      //     score: "99",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/1.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 2,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/5.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 3,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/4.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 4,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 5,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 6,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 7,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 8,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 9,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 10,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 11,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 12,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      //   {
      //     uid: 13,
      //     score: "100",
      //     date: "2023-10-19 14:00",
      //     img: "https://cdn.uviewui.com/uview/goods/3.jpg",
      //     reason:
      //       "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。",
      //   },
      // ];
      this.$apis.integralExchangeList(obj).then((res) => {
        console.log("查询积分兑换记录==>", res);
        if (res.result != null) {
          // this.dataList2 = res.result.records;
          this.current2 = res.result.current;
          this.pages2 = res.result.pages;
          this.pageSize2 = res.result.size;
          this.dataList2 = this.dataList2.concat(res.result.records);
          this.flag2 = true;
        } else {
          this.dataList2 = [];
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.pointsView {
  //头部内容
  // .heard {
  //   position: relative;
  //   .heardCont {
  //     position: absolute;
  //     top: 30%;
  //     right: 10%;
  //     color: white;
  //   }
  // }
  .headBox {
    // height: 150px;
    height: 120px;
    background: #007aff;
    color: white;
    .topBox {
      height: 70%;
      padding: 10px 0px 5px 15px;
      box-sizing: border-box;
      display: flex;
      justify-content: space-between;
    }
    .bottomBox {
      height: 30%;
      background: #007aff;
      display: flex;
      justify-content: space-around;
      align-items: center;
      font-size: 14px;
    }
  }
  .tabs1 /deep/ .u-tabs__wrapper__nav__item__text {
    font-size: 18px !important;
  }
  //tabs切换2
  .tabs2 {
    display: flex;
    padding: 10px 0px;
    background: white;
    margin-top: 2px;
    font-size: 16px;
    .tabsItem {
      // padding: 4px 0px;
      // width: 80px;
      border-radius: 6px;
      padding: 4px 8px;
      background: #e4e4e4;
      color: #8a8a96;
      text-align: center;
      margin-left: 10px;
      min-width: 55px;
    }
    .tabsItem2 {
      background: #007aff;
      border-radius: 6px;
      padding: 4px 8px;
      color: white;
      text-align: center;
      min-width: 55px;
    }
  }
}
</style>