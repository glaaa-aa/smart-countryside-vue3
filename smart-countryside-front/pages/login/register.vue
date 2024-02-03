<template>
  <div class="login">
    <image class="loginBackground" :src="loginBj" mode=""></image>
    <div class="loginContent">
      <div style="padding: 15px 15px 15px">
        <div style="display: flex">
          <span style="font-size: 22px">欢迎注册</span>
          <img
            style="width: 25px; height: 25px; margin-left: 6px"
            src="../../static/index/guzhang.png"
            alt=""
          />
        </div>
        <div style="color: #ccc; font-size: 13px; margin-top: 10px">
          注册信息提交后，经村委审核通过，可进行积分申报
        </div>
        <u--form
          labelPosition="left"
          :model="model"
          :rules="rules"
          ref="uForm"
          class="form1"
        >
          <!-- 账号登录 -->
          <div style="margin-top: 20px">
            <u-form-item label="姓名：" labelWidth="80" prop="username">
              <u--input
                v-model="model.username"
                placeholder-class="placeholder-style"
                placeholder="请输入姓名"
              ></u--input>
            </u-form-item>

            <u-form-item label="手机号：" labelWidth="80" prop="phone">
              <u--input
                type="number"
                v-model="model.phone"
                placeholder-class="placeholder-style"
                placeholder="请输入手机号"
              ></u--input>
            </u-form-item>

            <u-form-item
              label="行政区划："
              labelWidth="80"
              prop="wcCode"
              @click="echoDefault(model.wcCode)"
            >
              <div
                style="
                  padding: 6px 9px;
                  border-radius: 4px;
                  display: flex;
                  border: 1px solid #dadbde;
                "
              >
                <u--input
                  v-model="model.wcCode"
                  disabled
                  disabledColor="#ffffff"
                  placeholder="请选择行政区划"
                  style="pointer-events: none"
                  border="none"
                ></u--input>
                <u-icon slot="right" name="arrow-right"></u-icon>
              </div>
            </u-form-item>

            <div style="text-align: center">
              <button class="btn" @click="submit">注册</button>
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
          margin-bottom: 20px;
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
    </div>
    <!-- 行政区划-->
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
import loginVue from "./login.vue";
import "@/static/style/style.css";
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
      //行政区划
      currentIcon: require("@/static/index/jt_down.png"),
      defaultIcon: require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [], //区域数据源
      districtName: "", //所属片区值
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
          message: "请输入姓名",
          trigger: ["blur", "change"],
        },
        phone: [
          {
            pattern:
              /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
            // 正则检验前先将值转为字符串
            transform(value) {
              return String(value);
            },
            required: true,
            trigger: ["blur", "change"],
            message: "请输入正确的手机号",
          },
        ],
        wcCode: {
          type: "string",
          required: true,
          len: 4,
          message: "请选择行政区划",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onLoad() {},
  onShow() {
    console.log("onShow");
  },
  onReady() {
    this.loadOpenId();
  },
  methods: {
    //查询行政区划
    //  getProjectList() {
    //   this.$apis.departList({ uid: this.userInfo.id }).then((res) => {
    //     console.log("查询所属片区", res);
    //     if (res.result != null) {
    //       this.jiedaoList = res.result;
    //       this.districtName = this.recursion(
    //         this.jiedaoList,
    //         this.model1.orgCode
    //       ).name;
    //       console.log(this.districtName, this.model1.orgCode, "所属片区名称");
    //     } else {
    //       this.jiedaoList = [];
    //     }
    //   });
    // },
    //点击选择行政区划
    echoDefault(id) {
      console.log(id);
      // if (id) {
        const selectIds = [id];
        this.checkedTreeData(this.jiedaoList, selectIds);
      // }
      this.$refs.nextTreeRef.showTree = true;
    },
    //行政区划勾选
    changeVerify(arr) {
      // 注意：返回非空字符串会阻止原有行为，并提示返回的字符串
      // 如果函数体不做return返回值，即验证通过，控件正常处理业务
      console.log("已选择的数据", arr);
      if (arr && arr.length > 4) {
        return "最多可以选择4个节点";
      }
    },
    //行政区划确认
    onconfirm(list) {
      // localStorage.setItem("userCode", list.at(-1).code);
      this.model1.orgCode = list.at(-1).code;
      this.districtName = list.at(-1).name;
      // this.gethuzhuType(1);
      console.log(this.districtName, this.model1.orgCode);
    },
    oncancel() {
      // 清除treeData的选中状态
      this.checkedTreeData(this.jiedaoList, []);
    },
    checkedTreeData(treeData, selectIds) {
      console.log(treeData, selectIds, "处理1111");
      // 注意 vue2当数据深嵌套时，如果没有在treeData里面初始化checked属性，那在改变数据的时候直接将checked属性赋值为true，这时候ui界面有可能不会更新，
      // 这时候建议使用this.$set去更新checked属性值，或者在初始化this.jiedaoList的时候初始化checked属性
      (treeData || []).map((item) => {
        console.log(selectIds.indexOf(item.id) !== -1);
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
    // 获取openId
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
    // 登录
    submit() {
      let that = this;
      this.$refs.uForm
        .validate()
        .then((res) => {
          uni.$u.toast(res, "校验通过");
          if (this.checkboxValue1.length < 1)
            return uni.$u.toast("请勾选阅读协议！");
          console.log(that.model);
          return;
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
        })
        .catch((errors) => {
          console.log(errors, that.model);
        });
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
    top: 16%;
    left: 50%;
    transform: translate(-50%);
    width: 90%;
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
