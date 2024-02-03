<template>
  <div class="myInfo">
    <div class="headBox two_fs">
      <div style="margin: 0 20px">
        <u-avatar
          @click="viewImg(userInfo.avatar)"
          size="60"
          :src="userInfo.avatar ? getImgUrl(userInfo.avatar) : avatarSrc"
        ></u-avatar>
      </div>
      <div>
        <div style="font-size: 18px">{{ userInfo.realname }}</div>
        <div style="line-height: 24px">{{ userInfo.phone }}</div>
        <div>{{ userInfo.districtCode_txt }}</div>
      </div>
    </div>
    <div class="content">
      <u-cell-group :border="false">
        <u-cell title="我的填报" isLink url="/pages/points/pointsFill">
          <template slot="icon">
            <div
              style="font-size: 28px; color: #2ba246"
              class="iconfont icon-daiwotianxie"
            ></div>
          </template>
        </u-cell>
        <u-cell title="我的积分" isLink url="/pages/points/pointsFillList">
          <template slot="icon">
            <div
              style="font-size: 28px; color: #3d70ed"
              class="iconfont icon-xinyujifen"
            ></div>
          </template>
        </u-cell>
      </u-cell-group>
    </div>
    <div
      style="
        position: fixed;
        width: 76%;
        bottom: 4%;
        left: 50%;
        transform: translate(-50%);
      "
    >
      <!-- <u-button @click="loginOut" type="error" text="退出登录"></u-button> -->
      <button
        style="
          background-color: #1658c8;
          width: 100%;
          height: 45px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 25px;
        "
        @click="loginOut"
      >
        退出登录
      </button>
    </div>
  </div>
</template>

<script>
import { getImgUrl, mobileStr, recursion } from "@/common/utils.js";
import "@/static/style/style.css";
export default {
  name: "RuralGovernanceFrontMobileMasterMyInfo",

  data() {
    return {
      userInfo: {},
      avatarSrc:
        "1http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg",
      //村民基本信息
      basicInfo: {},
      idCard: false,
      jiedaoList: [], //行政区划
    };
  },

  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("userInfo", this.userInfo);
    if (this.userInfo.districtCode) {
      this.getProjectList(this.userInfo.districtCode); //查询所属片区
    }

    if (e.idCard) {
      this.idCard = true;
    }
  },

  methods: {
    getImgUrl,
    recursion,
    //头像预览
    viewImg(imgUrl) {
      if (imgUrl) {
        uni.previewImage({
          current: 0,
          urls: [this.$mConfig.assetsPath + imgUrl],
        });
      } else {
        uni.previewImage({
          current: 0,
          urls: [
            "data:image/jpg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAAA8AAD/4QMraHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjREMEQwRkY0RjgwNDExRUE5OTY2RDgxODY3NkJFODMxIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjREMEQwRkY1RjgwNDExRUE5OTY2RDgxODY3NkJFODMxIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NEQwRDBGRjJGODA0MTFFQTk5NjZEODE4Njc2QkU4MzEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NEQwRDBGRjNGODA0MTFFQTk5NjZEODE4Njc2QkU4MzEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7/7gAOQWRvYmUAZMAAAAAB/9sAhAAGBAQEBQQGBQUGCQYFBgkLCAYG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",
          ],
        });
      }
    },
    //查询行政区划
    getProjectList(districtCode) {
      let obj = {
        uid: this.userInfo.id,
        // orgCode: this.userInfo.orgCode
      };
      this.$apis.departList(obj).then((res) => {
        console.log("查询行政区划", res);
        this.jiedaoList = res.result;
        this.userInfo.districtCode_txt = this.recursion(
          this.jiedaoList,
          districtCode
        ).name;
        console.log(districtCode, this.districtName, "管辖区");
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
    //退出登录
    loginOut() {
      uni.showModal({
        title: "提示",
        content: "是否确认退出登录！",
        confirmText: "确定",
        success: (res) => {
          if (res.confirm) {
            this.$apis
              .goOut({ thirdType: "weixin", username: this.userInfo.id })
              .then((res) => {
                if (res.success) {
                  console.log("退出登录===>", res);
                  wx.clearStorageSync();
                  wx.clearStorage();
                  uni.redirectTo({ url: "/pages/login/login" }); //退出成功页面跳转
                } else {
                  uni.$u.toast(res.message);
                }
              });
          } else if (res.cancel) {
            console.log("取消");
          }
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.myInfo {
  // background: #ffffff;
  .headBox {
    background: #ffffff;
    display: flex;
    justify-content: start;
    align-items: center;
    padding: 20px 10px 20px 15px;
    margin-bottom: 10px;
  }
  .content {
    padding: 0px 10px;
    background: white;
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
.content /deep/ .u-cell__title-text {
  font-size: 17px !important;
}
.content /deep/ .u-cell__value {
  font-size: 17px !important;
}
.content /deep/ .u-line{
    vertical-align: middle;
    border-color: #9e9e9e47 !important;
}
</style>