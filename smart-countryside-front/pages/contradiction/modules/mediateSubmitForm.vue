<template>
  <view class="" style="padding: 0px 10px 60px">
    <!-- <u--text
      prefixIcon=" "
      iconStyle="font-size: 19px"
      text="矛盾双方人员信息"
      size="20"
      bold
      margin="15px 0"
    ></u--text> -->
    <!-- 列表 -->
    <u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
      <div
        class="cont"
        style="
          background: white;
          border-radius: 8px;
          margin-top: 10px;
        "
        :style="{padding:dataList.dissensionInvolvedUserList.length?'5px 5px 10px 15px':'0px'}"
      >
        <div
          class="info two_fs"
          v-for="(item, index) in dataList.dissensionInvolvedUserList"
          :key="index"
          v-if="dataList.dissensionInvolvedUserList.length"
        >
          <div>
            <div style="display: flex; margin-bottom: 10px">
              <div class="infoItem" style="margin-right: 10px">
                {{ item.realname }}
              </div>
              <div class="infoItem">{{ item.phone }}</div>
            </div>
            <div class="infoItem infoItem3">{{ item.address }}</div>
          </div>
          <div style="max-width: 8%">
            <u-icon
              @click="deleteItem(index)"
              name="trash-fill"
              color="#F97575"
              size="28"
            ></u-icon>
          </div>
        </div>
        <div class="addInfo" v-if="!popupShow">
          <u-icon
            @click="clickAdd"
            name="plus"
            label="新增当事人信息"
            color="#2979ff"
            size="18"
            labelSize="18"
            bold
            labelColor="#2979ff"
          ></u-icon>
        </div>
      </div>
      <!-- <u-button @click="submitAddWork" type="primary" text="提交"></u-button> -->
      <!-- 提交 -->
      <view class="pitchOn" v-if="!popupShow">
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
          @click="submitAddWork"
        >
          提交
        </button>
      </view>
      <!-- <u-popup
        :show="popupShow"
        @close="popupShow = false"
        mode="center"
        round="10"
      > -->
        <div v-if="popupShow">
          <!-- 内容 -->
          <div
            class="cont"
            style="
              background: white;
              border-radius: 8px;
              margin-top: 10px;
              padding: 5px 15px 10px;
            "
          >
            <!-- <u--form
            labelPosition="left"
            :model="model1"
            :rules="rules"
            ref="uForm"
          > -->
            <!-- 姓名 -->
            <u-form-item
              label="姓名："
              prop="realname"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.realname"
                placeholder="请输入"
                border="none"
                type="text"
              ></u--input>
            </u-form-item>
            <!-- 手机号 -->
            <u-form-item
              label="手机号："
              prop="phone"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.phone"
                placeholder="请输入"
                border="none"
                type="number"
              ></u--input>
            </u-form-item>
            <!-- 身份证 -->
            <u-form-item
              label="身份证："
              prop="idCard"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.idCard"
                placeholder="请输入"
                border="none"
                type="idcard"
              ></u--input>
            </u-form-item>
            <!-- 家庭地址 -->
            <u-form-item
              label="家庭地址："
              prop="address"
              required
              ref="item1"
              labelWidth="90"
            >
              <u--input
                v-model="model1.address"
                placeholder="请输入"
                border="none"
                type="text"
              ></u--input>
            </u-form-item>
            <!-- </u--form> -->
          </div>
          <!-- 新增 -->
          <div class="pitchOn">
            <button
              style="
                background-color: #1658c8;
                height: 45px;
                line-height: 45px;
                color: rgb(255, 255, 255);
                font-size: 20px;
                border-radius: 25px;
              "
              :style="{width:dataList.dissensionInvolvedUserList.length?'45%':'100%'}"
              @click="addPersonnel"
            >
              新增
            </button>
            <button
              v-if="dataList.dissensionInvolvedUserList.length"
              style="
                background-color: #7D7E80;
                width: 45%;
                height: 45px;
                line-height: 45px;
                color: rgb(255, 255, 255);
                font-size: 20px;
                border-radius: 25px;
              "
              @click="cancelPersonnel"
            >
              取消
            </button>
          </div>
        </div>
      <!-- </u-popup> -->
    </u--form>
  </view>
</template>

<script>
import "@/static/style/style.css";
import store from "@/store/index.js";
import xuanze from "./xuanze.vue";
import navigation from "@/components/navigation/navigation";
import piaoyiEditor from "@/uni_modules/piaoyi-editor/components/piaoyi-editor/piaoyi-editor.vue";

export default {
  components: {
    xuanze,
    navigation,
    piaoyiEditor,
  },
  data() {
    return {
      model1: {
        realname: "",
        phone: "",
        idCard: "",
        address: "",
      },
      dataList: {
        dissensionInvolvedUserList: [],
      }, //列表数据
      //表单弹窗
      popupShow: true,
      rules: {
        realname: {
          type: "string",
          required: true,
          message: "请输入姓名",
          trigger: ["blur", "change"],
        },
        // phone: {
        //   type: "string",
        //   required: true,
        //   message: "请输入手机号",
        //   trigger: ["blur", "change"],
        // },
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
        // idCard: {
        //   type: "string",
        //   required: true,
        //   message: "请输入身份证号码",
        //   trigger: ["blur", "change"],
        // },
        idCard: [
          {
            pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            // 正则检验前先将值转为字符串
            transform(value) {
              return String(value);
            },
            required: true,
            trigger: ["blur", "change"],
            message: "请输入正确的身份证号码",
          },
        ],
        address: {
          type: "string",
          required: true,
          message: "请输入家庭地址",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onLoad(e) {
    console.log("e", e, this.dataList);
    this.userInfo = store.state.userInfo;
    // this.getProjectList(); //查询列表
    if (e.obj) {
      this.dataList = JSON.parse(decodeURIComponent(e.obj));
      console.log(this.dataList, "数据");
      // if (!this.dataList.dissensionInvolvedUserList.length) {
      //   this.popupShow = true
      // }
    }
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    //点击添加人员
    clickAdd() {
      this.popupShow = true;
      this.model1 = {
        realname: "",
        phone: "",
        idCard: "",
        address: "",
      };
    },
    //查询列表
    getProjectList() {
      this.dataList = [
        {
          id: 1,
          realname: "欧阳娜娜",
          phone: 18888888888,
          address:
            "深圳市龙华区龙华书刊大厦深圳市龙华区龙华书刊大厦深圳市龙华区龙华书刊大厦",
        },
        {
          id: 2,
          realname: "马云",
          phone: 18888888888,
          address: "深圳市龙华区龙华书刊大厦",
        },
        {
          id: 3,
          realname: "马化腾",
          phone: 18888888888,
          address: "深圳市龙华区龙华书刊大厦",
        },
      ];
      // this.$apis
      //   .projectList({ memberCode: this.userInfo.username })
      //   .then((res) => {
      //     console.log("---项目-->>>>", res);
      //     if (res.success) {
      //       this.dataList = res.result;
      //       this.dataList.forEach((item) => {
      //         item.title = item.text;
      //       });
      //     }
      //   });
    },
    //删除
    deleteItem(id) {
      console.log(id);
      uni.showModal({
        title: "提示",
        content: "你确定要删除吗",
        success: (res) => {
          if (res.confirm) {
            var index = this.dataList.dissensionInvolvedUserList.findIndex(
              (item, index) => {
                return index == id;
              }
            );
            this.dataList.dissensionInvolvedUserList.splice(index, 1);
            uni.$u.toast("删除成功！");
          } else if (res.cancel) {
            console.log("取消");
          }
        },
      });
    },
    // 新增负责人员
    addPersonnel() {
      this.$refs.uForm
        .validate()
        .then((r) => {
          console.log(r);
          this.popupShow = false;
          this.dataList.dissensionInvolvedUserList.push(this.model1);
          console.log("表单", this.model1, this.dataList);
          uni.$u.toast("新增成功");
          return;
        })
        .catch((errors) => {
          console.log("errors", errors);
          uni.$u.toast("请完善表单内容！");
        });
    },
     // 取消新增负责人员
     cancelPersonnel() {
      this.popupShow = false;
    },
    // 表单提交
    submitAddWork() {
      console.log("表单", this.model1, this.dataList);
      //接口
      this.$apis.dissensionAdd(this.dataList).then((res) => {
        console.log("---添加-->>>>", res, this.model1, this.dataList);
        if (res.success) {
          uni.showToast({
            title: res.message,
            icon: "success",
            duration: 1000,
            success() {
              setTimeout(() => {
                // wx.navigateBack({
                //   delta: 2,
                // });
                uni.reLaunch({
                  url: "/pages/contradiction/mediate",
                });
              }, 1000);
            },
          });
        } else {
          uni.showToast({
            title: res.message,
            icon: "none",
            duration: 1000,
          });
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.cont {
  .info {
    display: flex;
    justify-content: space-between;
    padding: 15px 0;
    border-bottom: 1px solid #eaebec;
    font-size: 15px;
  }
  .infoItem3 {
    // white-space: nowrap;
    // overflow: hidden;
    // text-overflow: ellipsis;
  }
  .addInfo {
    display: flex;
    justify-content: center;
    padding: 10px 0;
    border-bottom: 1px solid #eaebec;
    margin-bottom: 5px;
  }
  .addInfo /deep/ .u-icon {
    padding: 10px;
  }
}
//提交按钮
.pitchOn {
  height: 60px;
  width: 85%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  z-index: 9;
}
//
</style>