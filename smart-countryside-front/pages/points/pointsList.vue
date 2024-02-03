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
    <div
      class="head"
      style="
        background: white;
        border-top: 12px solid #1658C8;
        border-left: 12px solid #1658C8;
        border-right: 12px solid #1658C8;
        padding: 5px 0px 20px;
      "
    >
      <!-- 返回 -->
      <!-- <view
        style="
          position: absolute;
          right: 3%;
          padding: 6px 10px;
          font-size: 14px;
          color: #3c9cff;
          font-weight: bold;
          text-decoration: underline;
        "
        @click="gobackClick"
        >返回上一页</view
      > -->
      <div
        @click.stop="echoDefault(districtCode)"
        style="
          width: 150px;
          align-items: center;
          padding: 5px 10px;
          display: flex;
        "
      >
        <span
          style="
            max-width: 85%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            font-size: 17px;
            font-weight: bold;
          "
          >{{ districtName || "请选择" }}</span
        >
        <div
          style="margin-left: 5px; font-size: 21px"
          class="iconfont icon-qiehuanchengshi"
        ></div>
        <!-- <u--image
          :showLoading="true"
          :src="qiehuanImg"
          width="18px"
          height="18px"
        ></u--image> -->
      </div>
      <!-- 搜索 -->
      <view style="padding: 10px 0 0 10px; display: flex; align-items: center">
        <view class="search">
          <u-search
            placeholder="请输入姓名"
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
              color="#1658C8"
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
        <div
          class="item"
          v-for="item in dataList"
          :key="item.uid"
          @click="goDetail(item)"
        >
          <div class="rightBox list_fs">
            <div style="min-width: 100%">
              <div style="display: flex; justify-content: space-between">
                <div class="listIten">
                  <div class="listTile">姓名:</div>
                  <div class="listVal">{{ item.realname }}</div>
                </div>
                <div class="jifen" style="">
                  {{ item.totalAmount || 0 }}
                </div>
              </div>
              <div class="listIten">
                <div class="listTile">户编号:</div>
                <div class="listVal">{{ item.code }}</div>
              </div>
              <div class="listIten">
                <div class="listTile">手机号:</div>
                <div class="listVal">{{ item.phone || "-" }}</div>
              </div>
              <div class="listIten">
                <div class="listTile">家庭地址:</div>
                <div class="listVal">{{ item.address }}</div>
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
      text="暂无明细"
    ></u-empty>
    <!-- 所属片区 -->
    <!-- <ba-tree-picker
      title="请选择区域"
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
    <next-tree
      valueKey="id"
      labelKey="name"
      childrenKey="children"
      title="请选择区域"
      ref="nextTreeRef"
      :currentIcon="currentIcon"
      :defaultIcon="defaultIcon"
      :checkStrictly="checkStrictly"
      :selectParent="selectParent"
      :multiple="multiple"
      :treeData="jiedaoList"
      @cancel="oncancel"
      @confirm="onconfirm"
      :changeVerify="changeVerify"
      themeColor="#1658C8"
    >
    </next-tree>
  </div>
</template>

<script>
import { getImgUrl, recursion } from "@/common/utils.js";
import xuanze from "./modules/xuanze.vue";
import navigation from "@/components/navigation/navigation";
import baTreePicker from "./modules/ba-tree-picker.vue";
import "@/static/style/style.css";
export default {
  components: {
    xuanze,
    navigation,
    baTreePicker,
  },
  data() {
    return {
      userInfo: {},
      // qiehuanImg: require("@/static/index/qiehuan.png"),
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [], //数据源
      //请选择区域
      currentIcon:require("@/static/index/jt_down.png"),
      defaultIcon:require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [], //区域数据源
      districtCode: "", //所属片区code
      districtName: "", //所属片区名称
      // treePickerShow1: false,
      // MultiSelect1: false,
      // wcCodeChecked1: "",
      searchValue: "", //搜索值
      model: {
        realName: "",
        telephone: "",
      },
    };
  },
  onLoad() {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
    // this.districtCode = this.userInfo.districtCode;
    wx.getStorage({
      key: "userCode",
      success:(res)=> {
        console.log('getStorage==userCode===>',res.data)
        this.districtCode = res.data
        console.log( this.districtCode,11111111);
      }
    })
    // this.districtCode = localStorage.getItem("userCode") || "";
    
    this.getData(this.current);
    this.getProjectList(); //查询所属片区
  },

  methods: {
    getImgUrl,
    recursion,
    goDetail(item) {
      uni.navigateTo({
        url: `/pages/points/pointsView?uid=${item.code}&obj=${JSON.stringify(
          item
        )}`,
      });
    },
    // 返回
    gobackClick() {
      uni.redirectTo({
        url: '/pages/index/index',
      });
    },
    //下拉加载
    scrolltolower(e) {
      console.log("下拉加载", e);
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
      this.status = "nomore";
    },
    //搜索中执行
    changeSearch(val) {
      if (val == "") {
        this.workData = [];
        this.current = 1;
        this.getData(this.current);
        this.status = "nomore";
      }
    },
    //点击搜索按钮
    filterClick() {
      this.workData = [];
      this.current = 1;
      this.getData(this.current);
      // this.status = "nomore";
    },
    //查询所属片区
    getProjectList() {
      let obj = {
        uid: this.userInfo.id,
        // orgCode: this.userInfo.orgCode
      };
      this.$apis.departList(obj).then((res) => {
        console.log("查询所属片区", res);
        this.jiedaoList = res.result;
        this.districtCode = this.recursion(
          this.jiedaoList,
          this.districtCode
        ).code;
        this.districtName = this.recursion(
          this.jiedaoList,
          this.districtCode
        ).name;
        // console.log(this.districtCode,this.districtName,'管辖区');
      });
    },
    //所属片区选择器
    // jiedaobanClick() {
    //   this.$refs.treePicker1._show();
    //   this.treePickerShow1 = true;
    // },
    // selectChange1(selectedList, selectedNames) {
    //   console.log(selectedList, selectedNames, "选择街道办");
    //   this.districtName = selectedNames;
    //   this.districtCode = selectedList[0];
    //   this.getData(1);
    //   this.treePickerShow1 = false;
    // },
    //点击选择区域
    echoDefault(id) {
      // console.log(id);
      const selectIds = [id];
      this.checkedTreeData(this.jiedaoList, selectIds);
      // this.multiple = true
      this.$refs.nextTreeRef.showTree = true;
    },
    //区域勾选
    changeVerify(arr) {
      // 注意：返回非空字符串会阻止原有行为，并提示返回的字符串
      // 如果函数体不做return返回值，即验证通过，控件正常处理业务
      console.log("已选择的数据", arr);
      if (arr && arr.length > 4) {
        return "最多可以选择4个节点";
      }
    },
    //选择区域勾选/确定
    onconfirm(list) {
      wx.setStorage({ key: "userCode", data: list.at(-1).code });
      // localStorage.setItem("userCode", list.at(-1).code);
      wx.getStorage({
        key: "userCode",
        success: (res) => {
          console.log("getStorage==userCode===>", res.data);
          this.districtCode = res.data;
        },
      });
      // this.districtCode = localStorage.getItem("userCode") || "";
      this.districtName = list.at(-1).name;
      this.getData(1);
      // console.log(this.districtName, this.districtCode);
    },
    oncancel() {
      // 清除treeData的选中状态
      this.checkedTreeData(this.jiedaoList, []);
    },
    checkedTreeData(treeData, selectIds) {
      // 注意 vue2当数据深嵌套时，如果没有在treeData里面初始化checked属性，那在改变数据的时候直接将checked属性赋值为true，这时候ui界面有可能不会更新，
      // 这时候建议使用this.$set去更新checked属性值，或者在初始化this.jiedaoList的时候初始化checked属性
      (treeData || []).map((item) => {
        if (selectIds.indexOf(item.id) !== -1) {
          item.checked = true;
        } else {
          item.checked = false;
        }
        if (item.children && item.children.length) {
          this.checkedTreeData(item.children, selectIds);
        }
      });
    },
    //查询列表
    getData(current) {
      if (current == 1) {
        this.dataList = [];
      }
      let obj = {
        // uid: this.userInfo.id,
        orgCode: this.districtCode,
        pageNo: current,
        pageSize: this.pageSize,
        realName: this.searchValue,
        // telephone: this.model.telephone,
      };
      console.log("查询列表obj==>", obj);
      this.$apis.integralList(obj).then((res) => {
        console.log("查询列表==>", res);
        if (res.success) {
          this.current = res.result.current;
          this.pages = res.result.pages;
          this.pageSize = res.result.size;
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

//所属片区选择器
.selectBox {
  padding: 5px 10px;
  background: white;
  margin-bottom: 5px;
  .celectItem {
    display: flex;
    justify-content: space-between;
    background: #1658C8;
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
      padding: 10px 5px;
      background: white;
      margin-bottom: 8px;
      border-radius: 6px;
      .left {
        width: 18%;
        padding: 5px 0 5px 10px;
      }
      .rightBox {
        width: 100%;
        padding: 0px 8px 0px 5px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-bottom: 4rpx;
        .listIten {
          display: flex;
          line-height: 32px;
          width: 100%;
          
          .listTile {
            width: 26%;
          }
          .listVal {
            width: 74%;
            color: #9a9a9a;
          }
        }
        .jifen {
          width: 66px;
          height: 34px;
          line-height: 34px;
          text-align: center;
          padding-left: 10px;
          border-radius: 3px;
          background: url(../../static/index/jifen_box.png) no-repeat center;
          background-size: 100% 100%;
          color: #12AFA5;
          position: absolute;
          right: 2%;
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
