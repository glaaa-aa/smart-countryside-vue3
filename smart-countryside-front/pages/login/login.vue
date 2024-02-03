<template>
  <div class="login">
    <image class="loginBackground" :src="loginBj" mode=""></image>
    <div class="loginContent">
      <div style="padding: 25px 25px 30px">
        <u--text text="数智乡村综合管理平台" size="22" align="center"></u--text>
        <!-- <div class="tabs">
        <div
          :style="{ color: !loginChange ? '' : '#B8B8B8' }"
          @click="loginChange = false"
        >
          密码登录
        </div>
        <div style="color: #b8b8b8; margin: 0px 5px">|</div>
        <div
          :style="{ color: loginChange ? '' : '#B8B8B8' }"
          @click="loginChange = true"
        >
          验证码登录
        </div>
      </div> -->
        <u--form
          labelPosition="left"
          :model="model"
          :rules="rules"
          ref="uForm"
          class="form1"
        >
          <!-- 账号登录 -->
          <div v-if="!loginChange" style="margin-top: 20px">
            <u-form-item label="账号" prop="username" borderBottom>
              <u--input
                v-model="model.username"
                border="none"
                placeholder-class="placeholder-style"
                placeholder="请输入账号"
              ></u--input>
            </u-form-item>
            <u-form-item label="密码" prop="password" borderBottom>
              <div style="display: flex">
                <u--input
                  v-if="passwordShow"
                  type="password"
                  v-model="model.password"
                  placeholder-class="placeholder-style"
                  border="none"
                  placeholder="请输入密码"
                ></u--input>
                <u--input
                  v-else
                  type="text"
                  v-model="model.password"
                  border="none"
                  placeholder-class="placeholder-style"
                  placeholder="请输入密码"
                ></u--input>
                <div
                  style="width: 50px; display: flex; justify-content: center"
                  @click="passwordShowClick"
                >
                  <u-icon
                    v-if="passwordShow"
                    name="eye-fill"
                    color="#909399"
                    size="22"
                  ></u-icon>
                  <u-icon
                    v-else
                    name="eye-off"
                    color="#909399"
                    size="24"
                  ></u-icon>
                </div>
              </div>
            </u-form-item>
            <div style="text-align: center">
              <button class="btn" @click="submit">立即登录</button>
              <div>
                <div
                @click="cancelBtn"
                style="
                  text-align: center;
                  font-size: 14px;
                  color: red;
                  display: inline-block;
                  border-bottom: 1px solid red;
                  line-height: 16px;
                  padding: 0px 2px;
                  margin-top: 10px;
                  float: left;
                "
              >
                立即退出
              </div>
              <div
                @click="cancelBtn2"
                style="
                  text-align: center;
                  font-size: 14px;
                  color: #1658C8;
    border-bottom: 1px solid #1658C8;
                  display: inline-block;
                  line-height: 16px;
                  padding: 0px 2px;
                  margin-top: 10px;
                  float: right;
                "
              >
                注册账号
              </div>
              </div>
              
            </div>
            <!-- </u-transition> -->
            <!-- 验证码登录 -->
            <!-- <u-transition
          mode="fade-left"
          :show="loginChange"
          :customStyle="{ float: 'left' }"
        > -->
          </div>
          <div v-if="loginChange" style="margin-top: 20px">
            <u-form-item label="手机号" prop="phone" labelWidth="60" borderBottom>
              <u--input
                v-model="model.phone"
                border="none"
                placeholder-class="placeholder-style"
                placeholder="请输入手机号"
              ></u--input>
            </u-form-item>
            <u-form-item label="验证码" prop="captcha" labelWidth="60" borderBottom>
              <div style="display: flex">
                <u--input
                  type="text"
                  v-model="model.captcha"
                  placeholder-class="placeholder-style"
                  border="none"
                  placeholder="请输入验证码"
                ></u--input>
                <div style="line-height: 23px">
                  <u-toast ref="uToast"></u-toast>
                  <u-code
                    :seconds="seconds"
                    @end="end"
                    @start="start"
                    ref="uCode"
                    @change="codeChange"
                  ></u-code>
                  <div style="color: #1658c8" @tap="getCode">{{ tips }}</div>
                </div>
              </div>
            </u-form-item>
            <div>
              <button class="btn" @click="submit">立即登录</button>
            </div>
          </div>
        </u--form>
      </div>
      <div
        style="
          width: 100%;
          display: flex;
          align-items: center;
          justify-content: center;
        "
      >
        <u-checkbox-group
          v-model="checkboxValue1"
          @change="checkboxChange"
          placement="column"
          shape="circle"
        >
          <u-checkbox
            :customStyle="{ margin: '8px 0px' }"
            name="0"
            :checked="false"
          >
          </u-checkbox>
        </u-checkbox-group>
        <div style="color: #818181; font-size: 14px">
          <span>我已阅读并同意</span>
          <span style="color: #1990ff" @click="goUserAgreement('yhxy')"
            >《用户协议》 </span
          >和
          <span style="color: #1990ff" @click="goUserAgreement('yszc')"
            >《隐私政策》</span
          >
        </div>
      </div>

      <!-- 说明 -->
      <div
        style="
          padding: 0 15px 20px;
          font-size: 12px;
          margin-top: 15px;
          font-weight: bold;
        "
      >
        <div style="display: flex">
          <span style="font-size: 14px; margin-right: 3px; color: red">*</span
          >该系统只支持内部人员使用，暂时不对外开放。
        </div>
        <div style="display: flex">
          <span style="font-size: 14px; margin-right: 3px; color: red">*</span
          >内部人员请使用后台已注册账号密码登录，若登录遇到问题请联系系统管理员。
        </div>
        <!-- <div style="display: flex;"><span style="    font-size: 14px;margin-right: 3px;color: red;">*</span>本程序由河池市交通安全委员会为开展全市交通安全管理活动，联合公安交警部门、政府部门实施涉及存在交通安全隐患的驾驶人、车辆、道路、企业等信息进行采集、管理等职能。有关采集信息涉及隐私信息可查看《隐私政策》。</div>
						<div style="display: flex;"><span style="    font-size: 14px;margin-right: 3px;color: red;">*</span>本程序仅供河池市各级交警部门、各级政府单位工作人员使用。
						</div> -->
      </div>
    </div>
  </div>
</template>
<script>
import loginVue from "./login.vue";

export default {
  data() {
    return {
      // 退出小程序
      tsTitle: "温馨提示",
      tsContent: "是否退出?",
      //验证码
      tips: "",
      // refCode: null,
      seconds: 10,
      // loginBj: require("@/static/index/login_bj.jpg"),
      loginBj: this.$mAssetsPath.main_logo,
      //登录切换
      loginChange: false,
      passwordShow: true, //密码显示
      model: {
        username: null,
        password: null,
        captcha: null,
        openid: null,
        thirdType: "weixin",
      },
      checkboxValue1: [], //阅读协议勾选
      rules: {
        username: {
          type: "string",
          required: true,
          message: "请输入账号",
          trigger: ["blur", "change"],
        },
        password: {
          type: "string",
          required: true,
          message: "请输入密码",
          trigger: ["blur", "change"],
        },
        phone: [
          {
            pattern:/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
            // 正则检验前先将值转为字符串
            transform(value) {
              return String(value);
            },
            required: true,
            trigger: ["blur", "change"],
            message: "请输入正确的手机号",
          },
        ],
        captcha: {
          type: "string",
          required: true,
          len: 4,
          message: "请填写4位验证码",
          trigger: ["blur"],
        },
      },
    };
  },
  onLoad() {
    // this.model.username = localStorage.getItem("username") || "";
    // this.model.password = localStorage.getItem("password") || "";
    // wx.getStorage({
    //   key: "username",
    //   success: (res) => {
    //     console.log("getStorageusername===>", res.data);
    //     this.model.username = res.data;
    //   },
    // });
    // wx.getStorage({
    //   key: "password",
    //   success: (res) => {
    //     console.log("getStorage==password=>", res.data);
    //     this.model.password = res.data;
    //   },
    // });

    console.log(this.model, "mdoel");
  },
  onShow() {
    console.log("onShow");
  },
  onReady() {
    this.loadOpenId();
  },
  methods: {
    //立即退出
    cancelBtn() {
      wx.showModal({
        title: this.tsTitle, // 提示的标题
        content: this.tsContent, // 提示的内容
        showCancel: true, // 是否显示取消按钮，默认true
        cancelText: "我再想想", // 取消按钮的文字，最多4个字符
        cancelColor: "#000000", // 取消按钮的文字颜色，必须是16进制格式的颜色字符串
        confirmText: "退出应用", // 确认按钮的文字，最多4个字符
        confirmColor: "#576B95", // 确认按钮的文字颜色，必须是 16 进制格式的颜色字符串
        success: function (res) {
          console.log("接口调用成功的回调函数");

          if (res.confirm) {
            console.log("用户点击确定");
            //在需要退出小程序的地方调用添加下面代码即可(js文件中)
            wx.exitMiniProgram({ success: (res) => {} });
          } else if (res.cancel) {
            console.log("用户点击取消");
          }
        },
        fail: function () {
          console.log("接口调用失败的回调函数");
        },
        complete: function () {
          console.log("接口调用结束的回调函数（调用成功、失败都会执行）");
        },
      });
    },
    // 去注册
    cancelBtn2(){
      uni.navigateTo({
        url: "/pages/login/register",
      });
    },
    //验证码
    codeChange(text) {
      console.log(text, "验证码");
      this.tips = text;
    },
    getCode() {
      if (this.$refs.uCode.canGetCode) {
        // 模拟向后端请求验证码
        uni.showLoading({
          title: "正在获取验证码",
        });
        setTimeout(() => {
          uni.hideLoading();
          // 这里此提示会被this.start()方法中的提示覆盖
          uni.$u.toast("验证码已发送");
          // 通知验证码组件内部开始倒计时
          this.$refs.uCode.start();
        }, 2000);
      } else {
        uni.$u.toast("倒计时结束后再发送");
      }
    },
    end() {
      uni.$u.toast("倒计时结束");
    },
    start() {
      uni.$u.toast("倒计时开始");
    },
    loadOpenId() {
      let that = this;
      uni.getProvider({
        service: "oauth",
        success: async (res) => {
          console.log(res, "微信11");
          if (res.provider.indexOf("weixin") >= 0) {
            //获取code
            await uni.login({
              provider: "weixin",
              scopes: "auth_user",
              success: async (loginRes) => {
                console.log("刷新tokenCOde响应", loginRes);
                await that.$http
                  .post(`/sys/thirdApp/getThirdId/weixin`, {
                    code: loginRes.code,
                    thirdType: "weixin",
                  })
                  .then(async (r) => {
                    console.log("微信请求后login", r);
                    if (r.success) {
                      let result = r.result;
                      that.model.openid = result.openid;
                    } else {
                      uni.showToast({
                        title: "微信授权失败，请重试",
                        icon: "none",
                        duration: 2000,
                      });
                    }
                  });
              },
              fail: (err) => {
                reject(err);
              },
            });
          } else {
            // 没有平台供应商   默认H5
            uni.showToast({
              title: "仅支持微信小程序登录",
              icon: "none",
              duration: 1000,
            });
          }
        },
      });
    },
    //阅读协议勾选
    checkboxChange(n) {
      console.log(n);
    },
    //阅读用户协议
    goUserAgreement(nameCode) {
      uni.navigateTo({
        url: "/pages/login/modules/readDeal?flag=" + nameCode,
      });
    },
    // 登录
    submit() {
      this.$refs.uForm
        .validate()
        .then((res) => {
          // uni.$u.toast("校验通过");
          if (this.checkboxValue1.length < 1)
            return uni.$u.toast("请勾选阅读协议！");
          let that = this;
          console.log(that.model);

          //登录接口
          that.$apis.wxLogin(that.model).then((res) => {
            console.log(res, "登录", that.model);
            if (res.success) {
              wx.setStorage({
                key: "userCode",
                data: res.result.userInfo.districtCode,
              });
              that.$store.commit("SET_TOKEN", res.result.token);
              that.$store.commit("SET_USERID", res.result.userInfo.username);
              that.$store.commit("SET_USERINFO", res.result.userInfo);
              uni.reLaunch({
                url: "/pages/index/index",
              });
            } else {
              uni.showToast({
                title: res.message,
                icon: "none",
                duration: 2000,
              });
            }
          });

          //测试
          // uni.switchTab({
          //   url: "/pages/index/index",
          // });
        })
        .catch((errors) => {
          // console.log(this.$refs.uForm2)
        });
    },
    passwordShowClick() {
      this.passwordShow = !this.passwordShow;
      console.log("66", this.passwordShow);
    },
  },
};
</script>

<style lang="less">
.login {
  width: 100%;
  height: 100vh;
  position: relative;
  background: white;

  .loginBackground {
    width: 100%;
    height: 100%;
  }

  .loginContent {
    position: absolute;
    top: 22%;
    left: 50%;
    transform: translate(-50%);
    width: 85%;
    // min-height: 800rpx;
    // max-height: 840rpx;
    // padding: 25px;
    border-radius: 5px;
    background: white;
    box-shadow: 0px 0 10px #ccc;
    .tabs {
      display: flex;
      // height: 50px;
      align-items: center;
      font-size: 17px;
      margin: 10px 0 10px 0;
    }
    .loginTitle {
      text-align: center;
      padding-bottom: 20px;
      font-weight: 700;
      font-size: 17px;
    }
    .btn {
      margin-top: 30px;
      background-color: #1658c8;
      color: #fff;
      height: 44px;
      line-height: 44px;
      font-size: 19px;
      border-radius: 10px;
    }
  }
}
.form1 /deep/.u-form-item__body__left__content__label {
  // color: #fff !important;
  font-size: 17px !important;
}
.form1 /deep/ .u-form-item {
  margin-bottom: 20px !important;
}

.form1 /deep/ .u-form-item__body {
  padding: 10px 10px !important;
  background: #eee;
  border-radius: 30px;
}
.form1 /deep/ .u-form-item__body__left {
  min-width: 70px !important;
  width: 70px !important;
  padding-left: 10px;
}
.form1 /deep/ .u-form-item__body__right__message {
  font-size: 15px;
  line-height: 22px;
  color: #f56c6c;
  margin-left: 80px !important;
}
.placeholder-style {
  font-size: 16px;
}
</style>
