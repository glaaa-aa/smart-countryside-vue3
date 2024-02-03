<template>
  <div class='ruralActivityAdd'>
    <!-- 导航栏 -->
    <!-- <navigation title="活动创建"></navigation> -->
    <!-- 表单内容 -->
    <div class="contentBox">
      <u--form
        labelPosition="left"
        :model="model1"
        :rules="rules"
        ref="uForm"
      >
        <!-- 上传封面 -->
        <div class="heardImg">
          <u-upload
            width="60"
            height="60"
            :fileList="fileList1"
            @afterRead="afterRead1"
            @delete="deletePic1"
            name="file"
            :multiple="false"
            maxCount="1"
            accept="image"
            :previewFullImage="true"
          >
            <div style="position: relative;">
              <image
                :src="bjImg"
                mode="widthFix"
                style="width: 100%;height: 100px;"
              ></image>

              <div class="bjBtn">
                <span> <u-icon
                    color="#000"
                    name="plus"
                    size="13"
                  ></u-icon></span>
                <span style="margin-left: 6px;">上传封面</span>
              </div>
            </div>
          </u-upload>
        </div>
        <!-- 活动标题 -->
        <u-form-item
          label="活动标题"
          prop="s1"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.s1"
            disabledColor="#ffffff"
            placeholder="请输入标题"
            border="none"
            inputAlign="right"
          ></u--input>
        </u-form-item>
        <!-- 活动地点 -->
        <u-form-item
          class="address"
          label="活动地点"
          prop="address"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >

         <div class="address">
          <u--textarea
            v-model="model1.address"
            disabledColor="#ffffff"
            placeholder="请输入活动地点"
            border="none"
            autoHeight
            style="text-align: right;"
          ></u--textarea>
         </div>
        </u-form-item>
        <!-- 报名截止时间 -->
        <u-form-item
          @click="offTimeShow = true"
          label="报名截止时间"
          prop="offTime"
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.offTime"
            disabled
            disabledColor="#ffffff"
            placeholder="请设置报名截止时间"
            border="none"
            inputAlign="right"
            style="pointer-events:none"
          ></u--input>
        </u-form-item>
        <!-- 活动开始时间 -->
        <u-form-item
          @click="beginTimeShow = true"
          label="活动开始时间"
          prop="beginTime"
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.beginTime"
            disabled
            disabledColor="#ffffff"
            placeholder="请设置活动开始时间"
            border="none"
            inputAlign="right"
            style="pointer-events:none"
          ></u--input>
        </u-form-item>
        <!-- 活动结束时间 -->
        <u-form-item
          @click="endTimeShow = true"
          label="活动结束时间"
          prop="endTime"
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.endTime"
            disabled
            disabledColor="#ffffff"
            placeholder="请设置活动结束时间"
            border="none"
            inputAlign="right"
            style="pointer-events:none"
          ></u--input>
        </u-form-item>
        <!-- 报名人数 -->
        <u-form-item
          label="报名人数"
          prop="num"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.num"
            style="number"
            disabledColor="#ffffff"
            placeholder="请输入"
            border="none"
            inputAlign="right"
          ></u--input>
        </u-form-item>
        <!-- 限制人数 -->
        <u-form-item
          label="限制人数"
          prop="num2"
          required
          ref="item1"
          labelWidth="110"
          borderBottom
        >
          <u--input
            v-model="model1.num2"
            style="number"
            disabledColor="#ffffff"
            placeholder="请输入"
            border="none"
            inputAlign="right"
          ></u--input>
        </u-form-item>
        <!-- 活动介绍 -->
        <u-form-item
          class="address"
          label="活动介绍"
          prop="address"
          ref="item1"
          labelWidth="110"
        >

        </u-form-item>
        <div class="textarea">
          <u--textarea
            height="100"
            v-model="model1.address"
            disabledColor="#ffffff"
            placeholder="请描述活动详情"
          ></u--textarea>
        </div>
        <div style="display: flex;
          padding: 10px 20px;">
          <!-- 上传封面 -->
          <div
            class="heardImg2"
            style="margin-right: 10px;"
          >
            <u-upload
              width="60"
              height="60"
              :fileList="fileList1"
              @afterRead="afterRead1"
              @delete="deletePic1"
              name="file"
              :multiple="false"
              maxCount="5"
              accept="image"
              :previewFullImage="true"
            >
              <div>
                <div class="bjBtn2">
                  <span
                    class="iconfont icon-p_tupian"
                    style="font-size: 22px;
                    color: #169BD5;"
                  >
                  </span>
                  <span style="margin-left: 6px;">上传图片</span>
                </div>
              </div>
            </u-upload>
          </div>
          <div
            class="heardImg2"
            style="margin-right: 10px;"
          >
            <u-upload
              width="60"
              height="60"
              :fileList="fileList1"
              @afterRead="afterRead1"
              @delete="deletePic1"
              name="file"
              :multiple="false"
              maxCount="5"
              accept="video"
              :previewFullImage="true"
            >
              <div>
                <div class="bjBtn2">
                  <span
                    class="iconfont icon-shipinsheying"
                    style="font-size: 22px;
                    color: #1658C8;"
                  >
                  </span>
                  <span style="margin-left: 6px;">上传视频</span>
                </div>
              </div>
            </u-upload>
          </div>
        </div>
        <!-- 发布按钮 -->
        <view
          class="pitchOn"
          style="padding: 20px;"
        >
          <button
            style="
          background-color: #1658C8;
          width: 100%;
          height: 45px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 25px;
        "
            @click="submitAddWork"
          >
            发布
          </button>
        </view>
      </u--form>

    </div>
    <!-- 截止选择器 -->
    <u-datetime-picker
      :show="offTimeShow"
      mode="date"
      :minDate="Number(new Date())"
      @cancel="offTimeCancel"
      @confirm="offTimeConfirm"
    ></u-datetime-picker>
    <!-- 时间选择器 -->
    <u-datetime-picker
      :show="beginTimeShow"
      mode="date"
      :minDate="Number(new Date())"
      @cancel="beginTimeCancel"
      @confirm="beginTimeConfirm"
    ></u-datetime-picker>
    <!-- 结束选择器 -->
    <u-datetime-picker
      :show="endTimeShow"
      mode="date"
      :minDate="Number(new Date())"
      @cancel="endTimeCancel"
      @confirm="endTimeConfirm"
    ></u-datetime-picker>
  </div>
</template>
<script>
// import xuanze from "./xuanze.vue";
import navigation from "@/components/navigation/navigation";
import store from "@/store/index.js";
import { timestampToTime } from "@/common/utils.js";
import "@/static/style/style.css";
export default {
  components: {
    // xuanze,
    navigation,
  },
  data() {
    return {
      model1: {
      },
      bjImg: require("@/static/index/hdcj_bj.jpg"),
      //封面上传
      fileList1: [],
      action: this.$mConfig.baseUrl + this.$apis.upload,
      offTimeShow: false, //截止时间选择
      beginTimeShow: false, //开始时间选择
      endTimeShow: false, //结束时间选择
      rules: {
        s1: {
          type: "string",
          required: true,
          message: "请选择纠纷类型",
          trigger: ["blur", "change"],
        },
        workIntro: {
          type: "string",
          required: true,
          message: "请填写设计人数",
          trigger: ["blur", "change"],
        },
        beginTime: {
          type: "string",
          required: true,
          message: "请选择时间",
          trigger: ["blur", "change"],
        },
      },
    }
  },
  onReady() {
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    timestampToTime,
    //截止时间选择器
    offTimeCancel() {
      this.offTimeShow = false;
    },
    offTimeConfirm(value) {
      console.log(value);
      this.model1.offTime = this.timestampToTime(value.value);
      this.offTimeShow = false;
    },
    //开始时间选择器
    beginTimeCancel() {
      this.beginTimeShow = false;
    },
    beginTimeConfirm(value) {
      console.log(value);
      this.model1.beginTime = this.timestampToTime(value.value);
      this.beginTimeShow = false;
    },
    //结束时间选择器
    endTimeCancel() {
      this.endTimeShow = false;
    },
    endTimeConfirm(value) {
      console.log(value);
      this.model1.endTime = this.timestampToTime(value.value);
      this.endTimeShow = false;
    },
    // 删除图片
    deletePic1(event) {
      console.log("删除图片", event);
      this.fileList1.splice(event.index, 1);
      this.model1.workImgs.splice(event.index, 1);
      console.log("删除后的图片", this.fileList1, this.model1.workImgs);
    },
    // 新增图片
    async afterRead1(event) {
      console.log(event, "新增图片2");
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
      console.log(store,'store');
      return new Promise((resolve, reject) => {
        let a = uni.uploadFile({
          url: this.action, // 仅为示例，非真实的接口地址
          filePath: url,
          name: "file",
          formData: {
            biz: "article",
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
            console.log("--res---", res);
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
    //发布
    submitAddWork() { }
  },
}
</script>
<style lang="scss" scoped>
.ruralActivityAdd {
  padding: 0px 5px 0px;
  //上传封面按钮
  .bjBtn {
    background: #469496;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 90px;
    height: 22px;
    // color: white;
    font-size: 14px;
    position: absolute;
    right: 2%;
    bottom: 7%;
    padding: 5px;
  }
  .bjBtn2 {
    border: 1px solid #ccdaf3;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 210rpx;
    height: 80rpx;
    font-size: 14px;
    padding: 5px;
  }
  .contentBox {
    background: white;
    border-radius: 8px;
    margin-top: 10px;
    padding: 0px 0px 10px;
  }
  .contentBox /deep/ .u-form-item {
    padding: 0px 20px;
  }
  .heardImg /deep/ .u-upload__wrap {
    display: flex;
  }
  .heardImg /deep/ uni-view {
    width: 100%;
  }
  .heardImg /deep/ uni-image > div {
    height: 180px;
  }
  .heardImg /deep/ uni-image {
    height: 180px !important;
  }
  .address /deep/ .u-textarea {
    padding: 0px;
  }
  .textarea /deep/ .u-textarea {
    padding: 0px;
    padding-bottom: 5px;
    margin: 0 20px;
    background: #fbfbfb;
  }
  .address /deep/ .u-textarea__field{
    text-align: right;
  }
}
</style>