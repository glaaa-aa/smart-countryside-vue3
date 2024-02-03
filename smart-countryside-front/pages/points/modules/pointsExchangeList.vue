<template>
  <div class="pointsList">
    <!-- 导航栏 -->
    <!-- <navigation title="积分记录"></navigation> -->
    <!-- 选择器 -->
    <!-- <div class="selectBox">
      <div @click="jiedaobanClick" class="celectItem two_fs_list">
        <div>{{ districtName || "请选择" }}</div>
        <u-icon
          v-if="!treePickerShow1"
          color="white"
          size="16"
          name="arrow-down"
        ></u-icon>
        <u-icon v-else color="white" size="16" name="arrow-up"></u-icon>
      </div>
    </div> -->
    <div class="head">
      <!-- 搜索 -->
      <view style="padding: 10px 0 0 10px; display: flex; align-items: center">
        <view class="search">
          <u-search
            placeholder="按户主/户编号/手机号查询"
            :showAction="false"
            shape="square"
            v-model="searchValue"
            @search="searchEnter"
            @change="changeSearch"
            borderColor="#c9c9c9"
            bgColor="#fff"
          ></u-search>
        </view>
        <view class="filter" @click="filterClick">
          <view style="color: #000; font-size: 27px; width: 70px">
            <u-button
              type="primary"
              color="#13AFA5"
              size="18"
              text="查询"
            ></u-button>
          </view>
        </view>
      </view>
    </div>
    <!-- 列表 -->
    <div class="donationRecord" v-if="dataList.length" style="">
      <scroll-view
        class="content"
        :scroll-y="true"
        @scrolltolower="scrolltolower"
      >
        <div class="item" v-for="item in dataList" :key="item.uid">
          <div class="right">
            <div
              style="
                display: flex;
                justify-content: space-between;
                border-bottom: 1px solid #e5e5e5;
                padding: 10px 10px;
              "
            >
              <div>2023-11-22 13:30:05</div>
              <div>+{{ item.totalAmount }}积分</div>
            </div>
            <div
              style="
                display: flex;
                justify-content: space-between;
                padding: 5px 10px;
                box-sizing: border-box;
                min-width: 100%;
              "
            >
              <div>
                <div class="listIten">
                  <div class="name_txt">物品名称:</div>
                  <div>{{ item.realname }}</div>
                </div>
                <div class="listIten">
                  <div class="name_txt">备注:</div>
                  <div>{{ item.address || "-" }}</div>
                </div>
              </div>
              <div>
                <!-- <u--image
                  :showLoading="true"
                  :src="qiehuanImg"
                  width="50px"
                  height="50px"
                ></u--image> -->
              </div>
            </div>
            <div
              @click="goDetail(item)"
              style="
                display: flex;
                justify-content: space-between;
                border-top: 1px solid #e5e5e5;
                padding: 10px 10px;
              "
            >
              <div style="color: #b6b6b6; font-size: 14px">查看详情</div>
              <div>
                <u-icon color="#A8A8A8" name="arrow-right" size="20"></u-icon>
              </div>
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
      text="暂无记录"
    ></u-empty>
    <!-- 村庄选择器 -->
    <!-- <ba-tree-picker
      title="请选择村庄"
      ref="treePicker1"
      :multiple="MultiSelect1"
      @select-change="selectChange1"
      @cancel="treePickerShow1 = false"
      :localdata="jiedaoList"
      valueKey="id"
      textKey="name"
      childrenKey="children"
      :checked="wcCodeChecked1"
      id="baTreePicker"
    /> -->
  </div>
</template>

<script>
import { getImgUrl, recursion } from "@/common/utils.js";
// import xuanze from "./modules/xuanze.vue";
import navigation from "@/components/navigation/navigation";
// import baTreePicker from "./modules/ba-tree-picker.vue";
export default {
  components: {
    // xuanze,
    navigation,
    // baTreePicker,
  },
  data() {
    return {
      userInfo: {},
      // qiehuanImg: require("@/static/index/redian2.jpg"),
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [], //数据源
      //村庄选择
      jiedaoList: [],
      treePickerShow1: false,
      MultiSelect1: false,
      wcCodeChecked1: "",
      districtCode: "", //村庄code
      districtName: "", //村庄名称
      searchValue: "",
    };
  },
  created() {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
    this.districtCode = this.userInfo.districtCode;
    this.getData(this.current);
    this.getProjectList(); //查询村庄
  },

  methods: {
    getImgUrl,
    recursion,
    goDetail(item) {
      uni.navigateTo({
        url: `/pages/points/modules/pointsExchangeDetail?uid=${
          item.code
        }&obj=${JSON.stringify(item)}`,
      });
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
    //搜索回车执行
    searchEnter(value) {
      this.workData = [];
      console.log("value", value);
      this.current = 1;
      this.getData(this.current);
      // this.status = "nomore";
    },
    //搜索中执行
    changeSearch(val) {
      if (val == "") {
        this.workData = [];
        this.current = 1;
        this.getData(this.current);
        // this.status = "nomore";
      }
    },
    //点击搜索按钮
    filterClick() {
      this.workData = [];
      this.current = 1;
      this.getData(this.current);
      // this.status = "nomore";
    },
    //查询村庄
    getProjectList() {
      let obj = {
        uid: this.userInfo.id,
        // orgCode: this.userInfo.orgCode
      };
      this.$apis.departList(obj).then((res) => {
        console.log("查询村庄", res);
        this.jiedaoList = res.result;
        this.districtCode = this.recursion(
          this.jiedaoList,
          this.userInfo.districtCode
        ).code;
        this.districtName = this.recursion(
          this.jiedaoList,
          this.userInfo.districtCode
        ).name;
        // console.log(this.districtCode,this.districtName,'管辖区');
      });
    },
    //村庄选择器
    jiedaobanClick() {
      this.$refs.treePicker1._show();
      this.treePickerShow1 = true;
    },
    selectChange1(selectedList, selectedNames) {
      console.log(selectedList, selectedNames, "选择行政区划");
      this.districtName = selectedNames;
      this.districtCode = selectedList[0];
      this.getData(1);
      this.treePickerShow1 = false;
    },
    //查询列表
    getData(current) {
      // this.dataList = [
      //   { id: 2023001, num: '99', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/1.jpg', },
      //   { id: 2023002, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村广东省深圳市龙华区清湖村广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/5.jpg', },
      //   { id: 2023003, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/4.jpg', },
      //   { id: 2023004, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023005, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023006, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023007, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: '1https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023008, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: '1https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023009, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023010, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023011, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023012, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      //   { id: 2023013, num: '100', name: '马来西亚', phone: 13037354303, address: '广东省深圳市龙华区清湖村', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', },
      // ]
      if (current == 1) {
        this.dataList = [];
      }
      let obj = {
        uid: this.userInfo.id,
        orgCode: this.districtCode,
        pageNo: current,
        pageSize: this.pageSize,
      };
      console.log("查询列表obj==>", obj);
      this.$apis.integralList(obj).then((res) => {
        console.log("查询列表==>", res);
        if (res.success) {
          this.current = res.result.current;
          this.pages = res.result.pages;
          this.pageSize = res.result.size;
          res.result.records.forEach((item) => {
            if (item.avatar == null) {
              item.avatar =
                "http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg";
            }
          });
          this.dataList = this.dataList.concat(res.result.records);
          this.flag = true;
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.pointsList {
  padding-top: 0px;
}

//村庄选择器
.selectBox {
  padding: 5px 10px;
  background: white;
  margin-bottom: 5px;
  .celectItem {
    display: flex;
    justify-content: space-between;
    background: #13afa5;
    color: white;
    padding: 10px 5px;
    border-radius: 5px;
    font-weight: bold;
  }
}
// 列表
.donationRecord {
  padding: 10px 10px;
  .content {
    height: 100%;
    max-height: 89vh;
    // overflow: auto;
    .item {
      display: flex;
      // padding: 10px 5px;
      background: white;
      margin-bottom: 8px;
      border-radius: 6px;
      .left {
        width: 18%;
        padding: 5px 0 5px 10px;
      }
      .right {
        width: 100%;
        // padding: 0px 8px 0px 5px;
        // display: flex;
        // align-items: center;
        // justify-content: space-between;
        // padding-bottom: 4rpx;
        // border-bottom: 1rpx solid #efefef;
        .listIten {
          display: flex;
          line-height: 32px;
          .name_txt {
            width: 80px;
            color: #b6b6b6;
          }
        }
        .jifen {
          width: 66px;
          height: 34px;
          line-height: 34px;
          text-align: center;
          padding-left: 10px;
          border-radius: 3px;
          background: url(@/static/index/jifen_box.png) no-repeat center;
          background-size: 100% 100%;
          color: #13afa5;
        }
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
.search /deep/ .u-search__content {
  height: 40px !important;
}
.search {
  width: 75%;
}

.filter {
  width: 40px;
  height: 100%;
  padding-left: 10px;
}
.filter /deep/ .u-button__text {
  font-size: 17px !important;
}
</style>
