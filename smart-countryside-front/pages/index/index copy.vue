<template>
  <view class="index" style="">
    <!-- 导航栏 -->
    <!-- <navigation title="首页"></navigation> -->
    <!-- <view class="navigation">
      <view
        style="font-size: 22px; color: #888"
        @click="goIndexClick"
        class="iconfont icon-zhuye"
      >
      </view>
      <view>首页</view>
      <view></view>
    </view> -->
    <!-- 轮播图 -->
    <div class="" style="position: relative">
      <!-- <u-swiper
        :autoplay="false"
        interval="5000"
        duration="500"
        :list="list3"
        indicatorMode="line"
        circular
        height="165"
      ></u-swiper> -->
      <u--image
        :showLoading="true"
        :src="list3[0]"
        width="100%"
        height="165px"
      ></u--image>
      <!-- <div
        @click="jiedaobanClick"
        style="
          position: absolute;
          top: 10px;
          left: 50%;
          transform: translate(-50%);
          z-index: 999;
        "
      >
        <span style="font-weight: bold">{{ districtName }}</span>
        <span
          class="iconfont icon-qiehuan"
          style="color: #007aff; font-size: 18px"
        ></span>
      </div> -->
    </div>
    <!-- 个人信息 -->
    <div style="margin-top: 5px; background: #f4f4f4; padding: 5px 0">
      <div
        style="
          display: flex;
          justify-content: space-between;
          padding: 10px 10px;
          position: relative;
          background: white;
        "
        @click="goMyInfo"
      >
        <div style="display: flex; align-items: center">
          <!-- <u-avatar size="55" :src="getImgUrl(userInfo.avatar)"></u-avatar> -->
          <div style="">
            <div style="" class="two_fs">
              <span style="margin-right: 10px">{{ userInfo.realname }}</span>
              <span style="color: #909399; font-size: 15px">{{
                userInfo.phone
              }}</span>
            </div>
            <!-- <div style="margin-bottom: 4px" class="fs_info_two">
              {{ userInfo.phone }}
            </div>
            <div style="color: #606266" class="fs_info_three">
              {{ userInfo.address || "" }}
            </div> -->
          </div>
        </div>
        <div
          class="fs_info_four"
          @click.stop="echoDefault(userCode)"
          style="
            position: absolute;
            right: 8px;
            top: 8px;
            background: rgb(19, 175, 165);
            color: white;
            border-radius: 5px;
            padding: 5px 2px 5px 10px;
            text-align: center;
            display: flex;
            align-items: center;
            max-width: 111px;
          "
        >
          <div
            style="
              max-width: 100px;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
              font-weight: bold;
            "
          >
            {{ districtName || "请选择" }}
          </div>

          <u-icon color="white" name="arrow-right"></u-icon>
        </div>
      </div>
    </div>
    <!-- 积分管理 -->
    <div class="paddingBottom10 paddingLeft10 paddingRight10">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="积分管理"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
      </div>
      <!-- 九宫格 -->
      <view class="gridBox">
        <u-grid :border="false" col="4">
          <u-grid-item
            v-for="(item, index) in list1"
            :key="index"
            @click="clickGird(item)"
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

    <!-- 村庄风采 -->
    <div class="hamlet" style="padding: 1px 10px 10px; background: #e5e5e570">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="村庄风采"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
        <div
          class="fontSize15"
          style="color: #909399"
          @click="goHotNewsList('czfc')"
          v-if="list2.length >= 3 ? true : false"
        >
          更多>
        </div>
      </div>
      <u-scroll-list :indicator="list2.length > 3 ? true : false">
        <div
          v-for="(item, index) in filteredVillageData"
          :key="index"
          class=""
          @click="goDetails(item)"
          style="
            width: 130px;
            border-radius: 8px;
            background: white;
            margin-right: 12px;
          "
        >
          <div class="cunzhuang">
            <u--image
              :showLoading="true"
              :src="getImgUrl(item.titleImg)"
              width="130px"
              height="80px"
              radius="8px 8px 0 0"
            ></u--image>
            <div class="peopleNum">
              <span
                style="font-size: 14px"
                class="iconfont icon-human-handsup"
              ></span>
              <span>{{ item.num || 0 }}</span>
            </div>
          </div>
          <div
            class="three_fs"
            style="
              padding: 5px;
              max-width: 100%;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
            "
          >
            {{ item.title || "清湖村" }}
          </div>
        </div>
      </u-scroll-list>
    </div>

    <!-- 热点资讯 -->
    <div class="paddingLeft10 paddingRight10" style="padding-bottom: 15px">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="热点资讯"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
        <div
          @click="goHotNewsList('xwlb')"
          class="fontSize14"
          style="color: #909399"
        >
          更多>
        </div>
      </div>
      <div
        class="message"
        v-for="(item, index) in filteredNewData"
        :key="index"
        @click="goDetails(item)"
        style="
          border-bottom: 1px solid #eee;
          padding: 5px;
          display: flex;
          justify-content: space-between;
        "
        :style="
          index === list4.length - 1
            ? 'border-bottom: 1px solid none'
            : 'border-bottom: 1px solid #eee'
        "
      >
        <div
          class="two_fs"
          style="
            display: flex;
            flex-direction: column;
            justify-content: space-around;
          "
        >
          <div
            style="
              word-break: break-all;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 2;
              overflow: hidden;
            "
            class=""
          >
            {{ item.title }}
          </div>
          <div class="three_fs" style="margin-top: 10px; color: #909399">
            {{ item.publishTime.substring(0, 10) }}
          </div>
        </div>
        <div v-if="item.titleImg">
          <u--image
            :showLoading="true"
            :src="getImgUrl(item.titleImg)"
            width="130px"
            height="80px"
            radius="8"
          ></u--image>
        </div>
      </div>
    </div>
    <!-- 所属片区-->
    <!-- <ba-tree-picker
      title="请所属片区"
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
      :height="600"
    >
    </next-tree>
  </view>
</template>

<script>
import "./style.css";
import "@/static/style/style.css";
import navigation from "@/components/navigation/navigation";
import dropDown from "@/components/dropDown/dropDown";
import baTreePicker from "@/components/ba-tree-picker/ba-tree-picker.vue";
import { getImgUrl, mobileStr, recursion } from "@/common/utils.js";
export default {
  components: {
    navigation,
    dropDown,
    baTreePicker,
  },
  data() {
    return {
      userInfo: {},
      userCode: "",
      cmsSection: {
        nameCode: "",
      },
      avatarSrc:
        "1http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg",
      //轮播图
      list3: [
        require("@/static/index/index_bj0.jpg"),
        // require("@/static/index/index_bj.jpg"),
        // require("@/static/index/index_bj3.jpg"),
        // require("@/static/index/index_bj4.png"),
      ],
      //所属片区
      currentIcon: require("@/static/index/jt_down.png"),
      defaultIcon: require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [],
      districtName: "", //所属片区值
      districtCode: null,
      // treePickerShow1: false,
      // MultiSelect1: false,
      // wcCodeChecked1: "",

      //村民基本信息
      basicInfo: {},
      //村庄风采
      list2: [
        // {
        //   img: require("@/static/index/cunzhuang.jpg"),
        //   name: "加佛村",
        //   num: 200,
        // },
        // {
        //   img: require("@/static/index/cunzhuang2.jpg"),
        //   name: "五仁村",
        //   num: 100,
        // },
        // {
        //   img: "https://cdn.uviewui.com/uview/goods/3.jpg",
        // },
        // {
        //   img: "https://cdn.uviewui.com/uview/goods/4.jpg",
        // },
        // {
        //   img: "https://cdn.uviewui.com/uview/goods/5.jpg",
        // },
      ],

      //热点咨询
      list4: [
        // {
        //   img: require("@/static/index/redian.jpg"),
        //   title: "如何提高小麦产量",
        //   time: "2022-10-21",
        // },
        // {
        //   img: "",
        //   title: `【沿着总书记的足迹】数字技术赋能乡村建设`,
        //   time: "2022-10-28",
        // },
        // {
        //   img: require("@/static/index/redian2.jpg"),
        //   title: `乡村行·看振兴：“数字乡村一张图”描绘未来美好图景`,
        //   time: "2022-10-28",
        // },
      ],
      // //九宫格
      list1: [
        {
          name: require("@/static/index/grid7_7.png"),
          title: "积分查看",
          url: "/pages/points/pointsList",
          flag: false,
        },
        {
          name: require("@/static/index/grid8.png"),
          title: "积分排行",
          url: "/pages/points/pointsRanking",
          flag: false,
        },

        {
          name: require("@/static/index/grid9.png"),
          title: "积分填报",
          url: "/pages/points/pointsFill",
          flag: false,
        },
        // {
        //   name: require("@/static/index/grid2_21.png"),
        //   title: "积分兑换",
        //   url: "/pages/points/modules/pointsExchangeForm",
        //   flag: true,
        // },
      ],
    };
  },
  onShow() {},
  onLoad(e) {
    console.log("asndbnbdsa");
    this.userInfo = this.$store.state.userInfo || {};
    this.userCode = localStorage.getItem("userCode") || "";
    let token = this.$store.state.token;
    console.log("userInfo", this.userCode, token, this.userInfo);
    let obj = {
      orgCode: this.userInfo.orgCode,
      name: this.userInfo.name,
      idCard: this.userInfo.idCard,
      pageNo: "1",
      pageSize: "1",
    };
    this.getProjectList(); //查询区域
    // this.getFamilyMembers(this.userInfo.id);//查询村民基本信息
  },
  created() {
    this.getNewLis();
    this.getVillage();
  },
  computed: {
    filteredNewData() {
      return this.list4.slice(0, 5);
    },
    filteredVillageData() {
      return this.list2.slice(0, 3);
    },
  },
  methods: {
    getImgUrl,
    recursion,

    //查看居民详细信息
    goMyInfo() {
      uni.navigateTo({
        url: "/pages/index/myInfo",
      });
    },
    //查询区域
    getProjectList() {
      let obj = {
        uid: this.userInfo.id,
        // orgCode: this.userInfo.orgCode
      };
      this.$apis.departList(obj).then((res) => {
        console.log("查询区域", res);
        this.jiedaoList = res.result;
        this.districtCode = this.recursion(this.jiedaoList, this.userCode).code;
        this.userCode = this.recursion(this.jiedaoList, this.userCode).code;
        this.districtName = this.recursion(this.jiedaoList, this.userCode).name;
        console.log(this.districtCode, this.districtName, "管辖区");
      });
    },

    //所属片区
    // jiedaobanClick() {
    //   console.log(11);
    //   this.$refs.treePicker1._show();
    //   this.treePickerShow1 = true;
    // },
    // selectChange1(selectedList, selectedNames) {
    //   console.log(selectedList[0], selectedNames, "所属片区");
    //   localStorage.setItem("userCode", selectedList.at(-1));
    //   this.userCode = localStorage.getItem("userCode") || "";
    //   this.districtName = selectedNames;
    //   this.treePickerShow1 = false;
    // },
    //点击所属片区
    echoDefault(id) {
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
    //所属片区勾选/确定
    onconfirm(list) {
      wx.setStorage({ key: "userCode", data: list.at(-1).code });
      // localStorage.setItem("userCode", list.at(-1).code);
      wx.getStorage({
        key: "userCode",
        success: (res) => {
          console.log("getStorage==userCode===>", res.data);
          this.userCode = res.data;
        },
      });
      // this.userCode = localStorage.getItem("userCode") || "";
      this.districtName = list.at(-1).name;
      console.log(this.districtName, this.userCode);
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
    //查询村民基本信息
    getFamilyMembers(idCard) {
      console.log("查询村民基本信息idCard==>", idCard);
      this.$apis.familyMembersDetails({ idCard: idCard }).then((res) => {
        console.log("查询村民基本信息==>", res);
        this.basicInfo = res.result;
        // if (res.success) {
        //   this.current = res.result.current;
        //   this.pages = res.result.pages;
        //   this.pageSize = res.result.size;
        //   res.result.records.forEach((item) => {
        //     if (item.avatar == null) {
        //       item.avatar =
        //         "http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg";
        //     }
        //   });
        //   this.Data = this.Data.concat(res.result.records);
        //   this.flag = true;
        // }
      });
    },
    //九宫格
    clickGird(item) {
      // if (item.flag) return uni.$u.toast('待开放...')
      uni.navigateTo({
        url: item.url,
      });
    },
    addClick() {
      console.log("添加");
      this.$refs.uToast.show({
        type: "none",
        message: "添加",
        duration: 1000,
      });
    },

    //查看更多
    goHotNewsList(nameCode) {
      uni.navigateTo({
        url: "/pages/rural/modules/hotNewsList?nameCode=" + nameCode,
      });
    },
    //查看详情
    goDetails(item) {
      uni.navigateTo({
        url: `/pages/rural/modules/hotNewsDetails?id=${
          item.id
        }&dataList=${JSON.stringify(item)}`,
      });
    },
    //查询新闻列表
    getNewLis() {
      this.cmsSection.nameCode = "xwlb";
      this.$apis.getSectionAll(this.cmsSection).then((res) => {
        console.log("新闻列表========>", res);
        if (res.result != null) {
          this.list4 = res.result.slice(0, 3).sort((a, b) => a.sort - b.sort);
        }
      });
    },
    //查询村庄风采
    getVillage() {
      this.cmsSection.nameCode = "czfc";
      this.$apis.getSectionAll(this.cmsSection).then((res) => {
        console.log("村庄风采========>", res);
        if (res.result != null) {
          this.list2 = res.result.sort((a, b) => a.sort - b.sort);
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
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
.index {
  padding: 0px 0px 50px;
  height: 100%;
  .add {
    width: 50px;
    height: 50px;
    background-color: #1a9cec;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    bottom: 40px;
    right: 40px;
  }
  .slide {
    height: calc(100vh - 130px);
  }
  .content {
    margin-bottom: 15px;
    .item {
      display: flex;
      align-items: center;
      margin-bottom: 8px;
      .title {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .text {
        width: 80%;
      }
      .picture {
        // width: 20%;
        .itemImg {
          width: 60px;
          height: 60px;
          border-radius: 10px;
          margin-right: 10px;
          .Img {
            width: 100%;
            height: 100%;
            border-radius: 10px;
          }
        }
      }
    }
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
  background: #13afa5;
  margin-right: 5px;
}
.gridBox /deep/ .u-grid-item {
  margin-bottom: 8px;
}
.gridBox /deep/ .u-icon__img {
  height: 50px !important;
  width: 50px !important;
  padding: 8px 0 !important;
}
//村庄风采
.hamlet {
  .cunzhuang {
    width: 100%;
    position: relative;
    .peopleNum {
      position: absolute;
      left: 5px;
      bottom: 0;
      color: white;
      font-size: 12px;
    }
  }
}
.hamlet /deep/ .u-scroll-list {
  padding-bottom: 0px;
}
</style>
