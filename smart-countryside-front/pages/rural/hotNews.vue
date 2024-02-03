<template>
  <div class='ruralActivityAdd'>
    <!-- 导航栏 -->
    <!-- <navigation title="热点资讯"></navigation> -->
    <!-- 表单内容 -->
    <div class="content">
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
        <!-- 资讯标题 -->
        <u-form-item
          label="资讯标题："
          prop="s1"
          required
          ref="item1"
          labelWidth="90"
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
        <!-- 资讯内容 -->
        <u-form-item
          class="address"
          label="资讯内容："
          prop="address"
          required
          ref="item1"
          labelWidth="90"
        >
        </u-form-item>
          <view style="padding-bottom: 5px;">
            <piaoyiEditor
              :values="model1.workContent"
              :maxlength="3000"
              @changes="saveContens"
              :readOnly="readOnly"
              :photoUrl="photoUrl"
              :api="api"
              :name="name"
              placeholder="请输入资讯内容"
              minHeight="200px"
            />
          </view>
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
  </div>
</template>
<script>
// import xuanze from "./xuanze.vue";
import navigation from "@/components/navigation/navigation";
import store from "@/store/index.js";
import { timestampToTime } from "@/common/utils.js";
import piaoyiEditor from '@/uni_modules/piaoyi-editor/components/piaoyi-editor/piaoyi-editor.vue';
export default {
  components: {
    // xuanze,
    navigation,
    piaoyiEditor,
  },
  data() {
    return {
      model1: {
      },
      //封面上传
      bjImg: require("@/static/index/hdcj_bj.jpg"),
      fileList1: [],
      action: this.$mConfig.baseUrl + this.$apis.upload,
       //富文本编辑器
       readOnly: false, //是否只读
      photoUrl: this.$mConfig.baseUrl, //服务器图片域名或者ip
      api: this.$apis.img_upload, //上传图片接口地址
      txt: '',
      name: 'file',
      values: '',
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
    //富文本编辑器
    saveContens(e) {
      console.log(e, '富文本编辑器');
      this.model1.workContent = e.html
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
     //发布
     submitAddWork() { }
  },
}
</script>
<style lang="scss" scoped>
.ruralActivityAdd {
  // padding: 0px 5px 0px;
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
    width: 90px;
    height: 22px;
    // color: white;
    font-size: 14px;
    padding: 5px;
  }
  .content {
    background: white;
    border-radius: 8px;
    // margin-top: 10px;
    padding: 0px 0px 10px;
  }
  .content /deep/ .u-form-item {
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
  .remarks /deep/ .u-textarea {
    padding: 0px;
    padding-bottom: 5px;
    margin: 0 20px;
    background: #eee;
  }
}
</style>