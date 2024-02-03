<template>
  <div class="myInfo">
    <div class="headBox">
      <!-- <u-avatar size="60" :src="avatarSrc"></u-avatar> -->
      <u--image
        :showLoading="true"
        :src="src1"
        width="40px"
        height="40px"
      ></u--image>
      <div style="margin-left: 10px; font-size: 22px; color: #fff">
        兑换成功
      </div>
    </div>
    <div class="content">
      <!-- <div class="jifen">
        <u--text
          prefixIcon=" "
          iconStyle="font-size: 19px"
          text="基本信息"
          size="18"
          bold
          margin="10px 0"
        >
        </u--text>
      </div> -->
      <div class="cont">
        <div class="contItem">
          <div class="titleTxt">兑换积分值</div>
          <div class="contTxt" style="color: red">-10积分</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">兑换物品名称</div>
          <div class="contTxt">毛巾</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">备注</div>
          <div class="contTxt">今日兑换一条毛巾</div>
        </div>
        <div class="contItem">
          <div class="titleTxt">物品图片</div>
          <!-- <div class="contTxt">
            <u--image
              :showLoading="true"
              :src="src2"
              width="90px"
              height="70px"
            ></u--image>
          </div> -->
        </div>
        <div class="contItem">
          <div class="titleTxt">兑换日期</div>
          <div class="contTxt">2023-11-22 14:00</div>
        </div>
      </div>
      <div>
        <button class="btn" @click="backIndex">返回首页</button>
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
      src1: require("@/static/index/jfdh2.png"),
      // src2: require("@/static/index/redian2.jpg"),
      //村民基本信息
      basicInfo: {},
      idCard: false,
    };
  },

  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
    if (e.idCard) {
      this.idCard = true;
    }
  },

  methods: {
    backIndex() {
      uni.reLaunch({
        url: "/pages/index/index",
      });
    },
    //查询村民基本信息
    getFamilyMembers(uid) {
      this.$apis.familyMembers().then((res) => {
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
  },
};
</script>

<style lang="scss" scoped>
.myInfo {
  background: #13afa5;
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
    // flex-direction: column;
    justify-content: center;
    align-items: center;
    background: #13afa5;
    height: 20vh;
  }
  .content {
    padding: 0px 10px;
    margin-top: 2px;
    background: #ffffff;
    height: 80vh;
    border-radius: 10px 10px 0 0;
    overflow: hidden;
    position: relative;
    .cont {
      padding: 30px 10px;
      .contItem {
        display: flex;
        margin-bottom: 30px;
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
.btn {
  margin-top: 40px;
  background-color: #fff;
  border: 1px solid #797979;
  color: #797979;
  height: 44px;
  line-height: 44px;
  font-size: 17px;
  border-radius: 10px;
  position: absolute;
  width: 90%;
  left: 50%;
  bottom: 5%;
  transform: translate(-50%);
}
</style>