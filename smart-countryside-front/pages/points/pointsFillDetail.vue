<template>
  <div class="myInfo">
    <div class="headBox">
      <!-- <u-avatar size="60" :src="avatarSrc"></u-avatar> -->
      <!-- 返回 -->
      <!-- <view
        style="
          position: absolute;
          right: 0%;
          top: 5px;
          padding: 6px 10px;
          font-size: 14px;
          color: #3c9cff;
          font-weight: bold;
          text-decoration: underline;
          z-index: 9;
        "
        @click="gobackClick"
        >返回上一页</view
      > -->
      <u--image
        v-if="basicInfo.categoryName == '减分'"
        :showLoading="true"
        :src="src2"
        width="80px"
        height="100px"
      ></u--image>
      <u--image
        v-else
        :showLoading="true"
        :src="src1"
        width="80px"
        height="100px"
      ></u--image>
      <div
        style="font-size: 20px; color: #13afa5"
        v-if="basicInfo.categoryName == '减分'"
      >
        减{{ basicInfo.score || 0 }}积分
      </div>
      <div style="font-size: 20px; color: #13afa5" v-else>
        加{{ basicInfo.score || 0 }}积分
      </div>
    </div>
    <div class="content">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="基本信息"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
      </div>
      <div class="cont">
        <div class="contItem">
          <div class="titleTxt">所属片区</div>
          <div class="contTxt">{{ basicInfo.orgCode_dictText || "-" }}</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">户主</div>
          <div class="contTxt">{{ basicInfo.realName || "-" }}</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">户编号</div>
          <div class="contTxt">{{ basicInfo.familyNo || "-" }}</div>
        </div>
        <!-- <div class="contItem">
          <div class="titleTxt">手机号</div>
          <div class="contTxt">15898767654</div>
        </div> -->
      </div>
    </div>
    <div class="content">
      <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="积分信息"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
      </div>
      <div class="cont">
        <div class="contItem">
          <div class="titleTxt">积分类型</div>
          <div class="contTxt">{{ basicInfo.categoryName || "-" }}</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">积分项目</div>
          <div class="contTxt">{{ basicInfo.itemName || "-" }}</div>
        </div>
        <!-- <div class="contItem">
          <div class="titleTxt">积分项目说明</div>
          <div class="contTxt">{{ basicInfo.alias || "-" }}</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">{{ basicInfo.categoryName }}量化指标</div>
          <div class="contTxt">{{ basicInfo.remark || "-" }}</div>
        </div> -->
        <div class="contItem">
          <div class="titleTxt">{{ basicInfo.categoryName }}事项详情</div>
          <div class="contTxt">{{ basicInfo.reason || "-" }}</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">{{ basicInfo.categoryName }}分值</div>
          <div
            class="contTxt"
            v-if="basicInfo.categoryName == '减分'"
            style="color: #ff0a0a"
          >
            <span>{{ basicInfo.score || 0 }}</span
            >积分
          </div>
          <div class="contTxt" v-else style="color: #13afa5">
            <span>{{ basicInfo.score || 0 }}</span
            >积分
          </div>
        </div>
        <div class="contItem">
          <div class="titleTxt">登记年份</div>
          <div class="contTxt">{{ basicInfo.year || "-" }}</div>
        </div>
        <!-- <div class="contItem">
          <div class="titleTxt">基础分清零</div>
          <div class="contTxt">否</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">当年积分清零<span style="font-size: 12px;">(总积分)</span></div>
          <div class="contTxt">否</div>
        </div> -->
        <div class="contItem">
          <div class="titleTxt">填报日期</div>
          <div class="contTxt">{{ basicInfo.submitDate }}</div>
        </div>
        <div class="" v-if="basicInfo.workImgs">
          <div class="titleTxt">上传附件</div>
          <div style="padding: 10px 0">
            <u-album
              :urls="basicInfo.imgs"
              rowCount="4"
              multipleSize="70"
              singleSize="70"
            ></u-album>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RuralGovernanceFrontMobileMasterMyInfo",

  data() {
    return {
      userInfo: {},
      districtCode: "",
      src1: require("@/static/index/tianbao1.png"),
      src2: require("@/static/index/tianbao2.png"),
      //村民基本信息
      basicInfo: {},
      idCard: false,
      dataList: [],
    };
  },

  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    wx.getStorage({
        key: "userCode",
        success: (res) => {
          console.log("getStorage==userCode===>", res.data);
          this.districtCode = res.data;
        },
      });
    // this.districtCode = localStorage.getItem("userCode") || "";
    if (e.familyNo) {
      this.basicInfo = JSON.parse(e.obj) || {};
      if (this.basicInfo.workImgs) {
        let imgsarr = this.basicInfo.workImgs.split(",") || [];
        let _temp = [];
        console.log(this.basicInfo, "基本详情");
        imgsarr.forEach((item) => {
          _temp.push(this.$mConfig.assetsPath + item);
        });
        this.basicInfo.imgs = _temp;
      }
    }
    console.log("userInfo", this.userInfo, e, this.basicInfo);
  },
  methods: {
    // 返回
    gobackClick() {
      uni.navigateBack({
        delta: 1,
      });
    },
    //查询填报历史详情
    getData(current, familyNo) {
      if (current == 1) {
        this.dataList = [];
      }
      let obj = {
        familyNo: familyNo,
        orgCode: this.districtCode,
        pageNo: current,
        pageSize: this.pageSize,
        realName: this.searchValue,
      };
      console.log("查询参数==>", obj);
      this.$apis.integralHistory(obj).then((res) => {
        console.log("查询填报历史详情==>", res);
        if (res.success) {
          this.current = res.result.current;
          this.pages = res.result.pages;
          this.pageSize = res.result.size;
          this.dataList = this.dataList.concat(res.result.records);
          this.flag = true;
          console.log("查询填报历史详情==>", this.dataList);
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.myInfo {
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

  .headBox {
    height: 150px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: #ffffff;
  }
  .content {
    padding: 0px 10px;
    margin-top: 2px;
    background: #ffffff;
    .cont {
      padding: 10px 0 10px 30px;
      font-size: 17px;
      .contItem {
        display: flex;
        margin-bottom: 20px;
        .titleTxt {
          min-width: 150px;
        }
      }
    }
  }
  .content /deep/ .u-cell__body {
    display: flex;
    flex-direction: row;
    box-sizing: border-box;
    padding: 10px 5px 10px 2px;
    font-size: 15px;
    color: #303133;
    align-items: center;
  }
}
</style>