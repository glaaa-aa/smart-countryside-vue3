<template>
  <div class="pointsExchangeForm" style="background: white">
    <!-- 导航栏 -->
    <!-- <navigation
      style="background: none;"
      title="积分兑换"
    ></navigation> -->
    <!-- 头部背景图 -->
    <image
      style="width: 100%; height: 180px"
      class="loginBackground"
      :src="bjImg"
      mode=""
    ></image>
    <!-- 表单 -->
    <div class="content" style="padding: 0px 15px 10px">
      <u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
        <!-- 兑换积分值-->
        <u-form-item
          label="兑换积分值"
          prop="num"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.num"
            disabledColor="#ffffff"
            placeholder="请填写兑换积分值"
            border="none"
            type="number"
          ></u--input>
        </u-form-item>
        <!-- 兑换物品名称-->
        <u-form-item
          label="兑换物品名称"
          prop="name"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.name"
            disabledColor="#ffffff"
            placeholder="请填写兑换物品名称"
            border="none"
          ></u--input>
        </u-form-item>
        <!-- 备注-->
        <u-form-item label="备注" ref="item1" labelWidth="110" borderBottom>
          <u--input
            v-model="model1.remarks"
            disabledColor="#ffffff"
            placeholder="请填写备注"
            border="none"
          ></u--input>
        </u-form-item>
        <!-- 上传图片-->
        <u-form-item label="上传图片" ref="item1" labelWidth="110">
        </u-form-item>
        <u-upload
          width="60"
          height="60"
          :fileList="fileList1"
          @afterRead="afterRead"
          @delete="deletePic"
          name="file"
          :multiple="false"
          maxCount="5"
          accept="image"
        >
        </u-upload>
      </u--form>
      <!-- 提交 -->
      <view class="pitchOn">
        <button
          style="
            background-color: #007aff;
            width: 100%;
            height: 45px;
            line-height: 45px;
            color: rgb(255, 255, 255);
            font-size: 20px;
            border-radius: 25px;
            margin-top: 30px;
          "
          @click="submitAddWork"
        >
          提交
        </button>
        <div
          @click="goLookList"
          style="
            padding: 5px;
            text-align: center;
            width: 100px;
            margin: 0 auto;
            margin-top: 30px;
            color: #007aff;
          "
        >
          历史记录
        </div>
      </view>
    </div>
  </div>
</template>
<script>
import navigation from "@/components/navigation/navigation";
import store from "@/store/index.js";
export default {
  components: {
    navigation,
  },
  data() {
    return {
      bjImg: require("@/static/index/jfdh_bj.jpg"),
      model1: {
        num: "",
        name: "",
        remarks: "",
        workImgs: [],
      },
      //图片
      fileList1: [],
      action: this.$mConfig.baseUrl + this.$apis.upload,
      rules: {
        num: {
          type: "string",
          required: true,
          message: "请填写兑换积分值",
          trigger: ["blur", "change"],
        },
        name: {
          type: "string",
          required: true,
          message: "请填写兑换物品名称",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    // 删除图片
    deletePic(event) {
      console.log("删除图片", event);
      this.fileList1.splice(event.index, 1);
      this.model1.workImgs.splice(event.index, 1);
      console.log("删除后的图片", this.fileList1, this.model1.workImgs);
    },
    // 新增图片
    async afterRead(event) {
      console.log(event, "新增图片");
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(event.file);
      for (let i = 0; i < lists.length; i++) {
        console.log(lists[i], "lists[i].url");
        const result = await this.uploadFilePromise(lists[i].url);
        let url = this.$mConfig.assetsPath + JSON.parse(result).message;
        this.fileList1.push({
          url: url,
        });
        this.model1.workImgs.push(JSON.parse(result).message);
        console.log("上传图片的地址", this.fileList1, this.model1.workImgs);
      }
    },
    uploadFilePromise(url) {
      return new Promise((resolve, reject) => {
        let a = uni.uploadFile({
          url: this.action, // 仅为示例，非真实的接口地址
          filePath: url,
          name: "file",
          formData: {
            biz: "avatar",
            // izZip: 1,
            // desWidth: 150,
          },

           header: {
            "X-Access-Token": store.state.token,
            "Http-Source": "wxMini",
            "tenant-id": 1,
            // config.header['Content-Type'] = "content-type:application/x-www-form-urlencoded";
            // "Content-Type": "application/json", // //设置后端需要的常用的格式就好，特殊情况调用的时候单独设置
            "X-AUTH-UUID": store.getters.guid,
          },
          success: (res) => {
            setTimeout(() => {
              resolve(res.data);
              let dataObj = JSON.parse(res.data) || {};
              console.log(dataObj, "图片上传结果");
              if (!dataObj.success) {
                uni.showToast({
                  title: "图片上传失败",
                  icon: "none",
                  duration: 1000,
                });
                return;
              }
            }, 1000);
          },
        });
      });
    },
    //查看兑换记录
    goLookList() {
      uni.navigateTo({
        url: "/pages/points/modules/pointsExchangeList",
      });
    },
    //提交
    submitAddWork() {
      console.log("---添加-->>>>");
      this.$refs.uForm
        .validate()
        .then((r) => {
          console.log(r);
          uni.$u.toast("校验通过");
          // this.$apis.workDailyAdd(this.model1).then((res) => {
          //   console.log("---添加-->>>>", res, this.model1);
          //   if (res.success) {
          //     uni.showToast({
          //       title: res.message,
          //       icon: "success",
          //       duration: 1000,
          //       success() {
          //         setTimeout(() => {
          //           wx.navigateBack({
          //             delta: 1,
          //           });
          //         }, 1000);
          //       },
          //     });
          //   } else {
          //     uni.showToast({
          //       title: res.message,
          //       icon: "none",
          //       duration: 1000,
          //     });
          //   }
          // });
        })
        .catch((errors) => {
          console.log("errors", errors);
          uni.$u.toast("请完善表单内容！");
        });
    },
  },
};
</script>
<style lang="scss" scoped>
.pointsExchangeForm {
  padding-top: 0px;
}
</style>