<template>
  <div class="pointsView">
    <!-- 导航栏 -->
    <!-- <navigation
      style="background: none;"
      title="积分排行"
    ></navigation> -->
    <!-- 头部背景 -->
    <div class="heard">
      <image
        style="width: 100%; height: 28vh"
        class="loginBackground"
        :src="bjImg"
        mode=""
      ></image>
      <!-- 返回 -->
      <!-- <view
        style="
          position: absolute;
          right: 3%;
          top: 3%;
          padding: 6px 10px;
          font-size: 14px;
          color: white;
          font-weight: bold;
          text-decoration: underline;
        "
        @click="gobackClick"
        >返回上一页</view
      > -->
      <!-- <div class="heardCont jifen_fs">
        <div style="padding-top: 25px">
          <div v-if="dataList.length > 1">
            <u-avatar
              :src="dataList[0].img || ''"
              style="margin: 0px auto"
              shape="circle"
              size="40"
            ></u-avatar>
            <div
              style="
                width: 4em;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              "
            >
              {{ dataList[1].realname }}
            </div>
            <div>{{ dataList[1].totalAmount }}</div>
          </div>
        </div>
        <div style="">
          <div v-if="dataList.length > 0">
            <u-avatar
              :src="dataList[1].img || ''"
              style="margin: 0px auto"
              shape="circle"
              size="40"
            ></u-avatar>
            <div
              style="
                width: 4em;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              "
            >
              {{ dataList[0].realname }}
            </div>
            <div>{{ dataList[0].totalAmount }}</div>
          </div>
        </div>
        <div style="padding-top: 25px">
          <div v-if="dataList.length > 2">
            <u-avatar
              :src="dataList[2].img || ''"
              style="margin: 0px auto"
              shape="circle"
              size="40"
            ></u-avatar>
            <div
              style="
                width: 4em;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              "
            >
              {{ dataList[2].realname }}
            </div>
            <div>{{ dataList[2].totalAmount }}</div>
          </div>
        </div>
      </div> -->
      <div class="jifen_fs heardCont2">
        <div>积分说明:</div>
        总积分=基础分+总加分+总扣分<br />
        其中基础分是农户默认的基础积分；<br />
        上年积分不计入当年积分；<br />
        发生重大违法事项时，当年积分清零
      </div>
      <!-- 切换村 -->
      <div
        @click.stop="echoDefault(districtCode)"
        style="
          position: absolute;
          bottom: 5px;
          right: 5px;
          display: flex;
          align-items: center;
          background: white;
          color: #007aff;
          padding: 8px 15px;
          border-radius: 20px;
        "
      >
        <div style="max-width: 80px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;">{{ districtName || "请选择" }}</div>
        <div
          style="margin-left: 5px; font-size: 20px"
          class="iconfont icon-qiehuanchengshi"
        ></div>
      </div>
    </div>

    <div class="box">
      <div class="contBox">
        <!-- tabs切换2 -->
        <!-- <div class="tabs">
          <div
            class="tabsItem"
            :class="tabsActive==item.id?'tabsItem2':'tabsItem'"
            v-for="item in tabsList"
            :key="item.id"
            @click="clickTabs(item)"
          >{{ item.title }}</div>
        </div> -->
        <!-- 列表排名 -->
        <div class="listBox" v-if="dataList.length">
          <div
            class=""
            style="
              width: 100%;
              background: white;
              border-top: 1px solid #e5e5e5;
            "
          >
            <div class="listItem2" style="margin: 10px 0px 6px 15px">
              <div style="width: 10%">排名</div>
              <div style="text-align: center; width: 22%">农户</div>
              <div style="text-align: center; width: 18%">基础分</div>
              <div style="text-align: center; width: 15%">加分</div>
              <div style="text-align: center; width: 15%">扣分</div>
              <div style="text-align: center; width: 17%">总积分</div>
            </div>
          </div>
          <scroll-view
            class="content"
            :scroll-y="true"
            @scrolltolower="scrolltolower"
          >
            <div
              class="listItem"
              :style="{ background: item.sort % 2 == 0 ? '' : '#F5F5F5' }"
              v-for="(item, index) in dataList"
              :key="item.id"
            >
              <div style="width: 10%" v-if="index == 0">
                <u--image
                  :showLoading="true"
                  :src="sort1"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="width: 10%" v-else-if="index == 1">
                <u--image
                  :showLoading="true"
                  :src="sort2"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="width: 10%" v-else-if="index == 2">
                <u--image
                  :showLoading="true"
                  :src="sort3"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="text-indent: 5px; width: 10%" v-else>
                {{ item.sort }}
              </div>
              <div style="width: 25%; justify-content: center; display: flex">
                <!-- <u-avatar
                  :src="item.img || ''"
                  shape="circle"
                  size="30"
                ></u-avatar> -->
                <div style="margin-left: 5px">
                  <div style="display: flex; align-items: center">
                    <div
                      style="
                        display: inline-block;
                        margin-right: 5px;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        white-space: nowrap;
                        max-width: 64px;
                        font-size: 16px;
                      "
                    >
                      {{ item.realname }}
                    </div>
                    <!-- <div style="color: #606266;font-size: 14px;display: inline-block">{{
                      item.code
                    }}</div> -->
                  </div>
                  <!-- <div style="color: #606266;margin-top: 5px;font-size: 14px;">{{ item.phone }}</div> -->
                </div>
              </div>
              <div style="text-align: center; width: 15%">
                {{ item.baseScore || 0 }}
              </div>
              <div style="color: #13afa5; text-align: center; width: 18%">
                +{{ item.plusAmount || 0 }}
              </div>
              <div style="color: red; text-align: center; width: 18%">
                -{{ item.reduceAmount || 0 }}
              </div>
              <div
                style="text-align: center; font-weight: bold; width: 17%"
                :style="{ color: index > 2 ? '' : '#0187F8' }"
              >
                {{ item.totalAmount || 0 }}
              </div>
            </div>
            <u-loadmore color="##adacac" :status="status" />
          </scroll-view>
          <!-- <div
            class=""
            style="
              width: 100%;
              background: white;
              border-top: 1px solid #e5e5e5;
              position: fixed;
              bottom: 0;
            "
          >
            <div class="listItem jifen_fs">
              <div style="width: 10%" v-if="dataObj.sort == 1">
                <u--image
                  :showLoading="true"
                  src="../../static/index/1.png"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="width: 10%" v-else-if="dataObj.sort == 2">
                <u--image
                  :showLoading="true"
                  src="../../static/index/2.png"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="width: 10%" v-else-if="dataObj.sort == 3">
                <u--image
                  :showLoading="true"
                  src="../../static/index/3.png"
                  width="20px"
                  height="25px"
                ></u--image>
              </div>
              <div style="width: 10%" v-if="dataObj.sort">
                {{ dataObj.sort }}
              </div>
              <div
                style="
                  font-size: 13px;
                  color: #888;
                  width: 10%;
                "
                v-else
              >
                暂无
              </div>
              <div style="text-align: center;width: 40%;">
                <div style="margin-left: 5px">
                  <div style="display: flex;
    align-items: center;">
                    <span
                      style="
                        display: inline-block;
                        margin-right: 10px;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        white-space: nowrap;
                      "
                      >{{ dataObj.realname }}</span
                    >
                    <span style="margin-bottom: 5px; display: inline-block">{{
                      dataObj.code
                    }}</span>
                  </div>
                  <div>{{ dataObj.phone }}</div>
                </div>
              </div>
              <div style="text-align: center;width: 17%">+{{ dataObj.plusAmount || 0 }}</div>
              <div style="text-align: center;width: 13%">+{{ dataObj.plusAmount || 0 }}</div>
              <div style="text-align: center;width: 15%">-{{ dataObj.reduceAmount || 0 }}</div>
              <div style="color: #13afa5;  width: 15%">
                {{ dataObj.totalAmount || "0" }}积分
              </div>
            </div>
          </div> -->
        </div>
        <u-empty
          v-if="!dataList.length"
          mode="data"
          icon="http://cdn.uviewui.com/uview/empty/data.png"
          text="暂无排名"
        ></u-empty>
      </div>
    </div>
    <!-- 所属片区选择器 -->
    <!-- <ba-tree-picker
      title="请选择所属片区"
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
import pointsDetail from "./modules/pointsDetail.vue";
import pointsRecode from "./modules/pointsRecode.vue";
import navigation from "../../components/navigation/navigation";
import baTreePicker from "./modules/ba-tree-picker.vue";
import { getImgUrl, recursion } from "@/common/utils.js";
export default {
  components: {
    pointsDetail,
    pointsRecode,
    navigation,
    baTreePicker,
  },
  data() {
    return {
      userInfo: {},
      userCode: "",
      sort1: require("@/static/index/1.png"),
      sort2: require("@/static/index/2.png"),
      sort3: require("@/static/index/3.png"),
      bjImg: require("@/static/index/jfph_bj2.jpg"),
      //tabs切换2
      tabsList: [
        { id: 1, title: "周榜" },
        { id: 2, title: "月榜" },
        { id: 3, title: "年榜" },
      ],
      tabsActive: 1,
      //所属片区选择
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
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [],
      dataObj: {},
    };
  },
  created() {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
    // this.districtCode = this.userInfo.districtCode;
    wx.getStorage({
        key: "userCode",
        success: (res) => {
          console.log("getStorage==userCode===>", res.data);
          this.districtCode = res.data;
        },
      });
    // this.districtCode = localStorage.getItem("userCode") || "";
    this.getData(this.current);
    this.getProjectList(); //查询所属片区
  },
  methods: {
    getImgUrl,
    recursion,
    //tabs切换2
    clickTabs(item) {
      console.log(item);
      this.tabsActive = item.id;
    },
    // 返回
    gobackClick() {
      uni.redirectTo({
        url: '/pages/index/index',
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
    //  jiedaobanClick() {
    //   this.$refs.treePicker1._show();
    //   this.treePickerShow1 = true;
    // },
    // selectChange1(selectedList, selectedNames) {
    //   console.log(selectedList, selectedNames, "选择行政区划");
    //   this.districtName = selectedNames;
    //   this.districtCode = selectedList[0];
    //   this.getData(1);
    //   console.log(this.districtCode,'code');
    //   this.treePickerShow1 = false;
    // },
    //点击选择区域
    echoDefault(id) {
      console.log(id);
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
      // console.log(treeData, selectIds,'值111111111');
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
    // 查询积分排行
    getData(current) {
      if (current == 1) {
        this.dataList = [];
      }
      let obj = {
        orgCode: this.districtCode,
        pageNo: current,
        pageSize: this.pageSize,
      };
      this.$apis.ranking(obj).then((res) => {
        console.log("====查询积分排行1==", res);
        if (res.success) {
          this.current = res.result.current;
          this.pages = res.result.pages;
          this.pageSize = res.result.size;
          this.dataList = this.dataList.concat(res.result.records);
          this.flag = true;
        }
      });
      let obj2 = {
        familyNo: this.userInfo.id,
        orgCode: this.userInfo.districtCode,
        pageNo: current,
        pageSize: this.pageSize,
      };
      this.$apis.ranking(obj2).then((res) => {
        console.log("====查询积分排行2==", res);
        if (res.success) {
          if (res.result.records.length > 0) {
            this.dataObj = res.result.records[0];
          } else {
            this.dataObj = {
              uid: 1,
              code: 2023001,
              phone: 13037354303,
              reduceAmount: "0",
              plusAmount: "0",
              totalAmount: "0",
              realname: this.userInfo.realname,
              img: "1https://cdn.uviewui.com/uview/goods/1.jpg",
            };
          }
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.pointsView {
  //头部内容
  .heard {
    position: relative;
    height: 28vh;
    // .heardCont {
    //   position: absolute;
    //   top: 50%;
    //   left: 50%;
    //   color: white;
    //   transform: translate(-50%, -50%);
    //   display: flex;
    //   text-align: center;
    //   justify-content: space-evenly;
    //   width: 250px;
    //   padding: 10px 0;
    // }
    .heardCont2 {
      position: absolute;
      bottom: 10%;
      left: 2%;
      color: #e5e5e5;
      width: 288px;
      font-size: 14px;
      line-height: 20px;
    }
  }
  //tabs切换2
  .tabs {
    display: flex;
    margin: 10px 30px;
    background: white;
    font-size: 14px;
    border: 1px solid #b3b3b3;
    border-radius: 20px;
    .tabsItem {
      padding: 4px 0px;
      width: 120px;
      text-align: center;
      color: #848484;
      padding: 8px 0px;
    }
    .tabsItem2 {
      border: 1px solid #1658c8;
      color: #1658c8;
      border-radius: 20px;
      width: 120px;
      text-align: center;
      padding: 8px 0px;
    }
  }
  .box {
    position: relative;
    // padding: 0px 10px;
    width: 100%;
    // height: 100%;
    .contBox {
      background: white;
      position: absolute;
      // top: -23px;
      left: 50%;
      transform: translate(-50%);
      // width: 90%;
      width: 100%;
      border-radius: 10px;
      .content {
        // height: 515px;
        // height: 475px;
        height: 65vh;
        overflow: hidden;
        padding: 5px 0;
        padding-bottom: 10px;
      }
      .listItem {
        display: flex;
        justify-content: space-between;
        // align-items: center;
        padding: 15px 15px 15px;
        padding-right: 0px;
        font-size: 17px;
      }
      .listItem2 {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 10px 15px 15px;
        font-size: 16px;

        color: #adacac;
      }
    }
  }
}
</style>