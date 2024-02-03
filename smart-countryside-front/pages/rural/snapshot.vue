<template>
  <div class="snapshot">
    <!-- 导航栏 -->
    <!-- <navigation title="随手拍"></navigation> -->
    <!-- 表单内容 -->
    <div class="content">
      <u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
        <!-- 类型 -->
        <div class="forItme">
          <u-form-item
            label="类型"
            prop="typeCode"
            required
            ref="item1"
            labelWidth="90"
          >
          </u-form-item>
          <div class="checkbox">
            <u-checkbox-group
              v-model="model1.typeCode"
              placement="row"
              @change="checkboxChange"
            >
              <u-checkbox
                :customStyle="{ marginBottom: '8px' }"
                v-for="(item, index) in checkboxList1"
                :key="index"
                :label="item.name"
                :name="item.id"
              >
              </u-checkbox>
            </u-checkbox-group>
          </div>
        </div>
        <!-- 标题 -->
        <div class="forItme">
          <u-form-item
            label="标题"
            prop="titleTxt"
            required
            ref="item1"
            labelWidth="90"
          >
          </u-form-item>
          <u--input
            v-model="model1.titleTxt"
            disabledColor="#ffffff"
            placeholder="请输入主要内容(例如：消防隐患)"
          ></u--input>
        </div>
        <!-- 详情 -->
        <div class="forItme">
          <u-form-item
            class="content"
            label="详情"
            prop="content"
            ref="item1"
            labelWidth="90"
          >
          </u-form-item>
          <div class="remarks">
            <u-textarea
              @input="inputContent"
              height="100"
              v-model="model1.content"
              disabledColor="#ffffff"
              placeholder="请输入详情，上传图片或视频"
              maxlength="-1"
            ></u-textarea>
          </div>
          <!-- 上传 -->
          <div style="display: flex; padding: 10px 0px">
            <div class="heardImg2" style="margin-right: 10px">
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
                      style="font-size: 22px; color: #169bd5"
                    >
                    </span>
                    <span style="margin-left: 6px">上传图片</span>
                  </div>
                </div>
              </u-upload>
            </div>
            <div class="heardImg2" style="margin-right: 10px">
              <u-upload
                width="60"
                height="60"
                :fileList="fileList2"
                @afterRead="afterRead2"
                @delete="deletePic2"
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
                      style="font-size: 22px; color: #1658c8"
                    >
                    </span>
                    <span style="margin-left: 6px">上传视频</span>
                  </div>
                </div>
              </u-upload>
            </div>
          </div>
        </div>
        <div class="forItme" style="padding-bottom: 0px" @click="locationClick">
          <!-- 事发地点 -->
          <u-form-item
            label="事发地点"
            prop="address"
            required
            ref="item1"
            labelWidth="90"
          >
            <!-- <u--input
            v-model="model1.address"
            disabledColor="#ffffff"
            placeholder="请输入详细地址"
          ></u--input> -->
          <div style="    display: flex;align-items: center;">
            <u--textarea
              v-model="model1.address"
              disabledColor="#ffffff"
              placeholder="请输入详细地址"
              disabled
              border="none"
              style="pointer-events: none"
              autoHeight
              maxlength="-1"
            ></u--textarea>
            <div
              class="iconfont icon-dingwei"
              style="font-size: 22px; color: #1658c8; font-weight: bold"
            ></div>
          </div>
          </u-form-item>
        </div>
        <!-- 定位 -->
        <!-- <u-form-item
            label="当前位置 ："
            prop="location"
            borderBottom
            @click="locationClick"
            ref="item1"
            leftIcon="*"
            labelWidth="115px"
            :leftIconStyle="{ color: 'red' }"
          >
            <div class="location">
              <u--textarea
                fontSize="18"
                v-model="model.s5"
                placeholder="请输入详细地址"
                disabled
                style="width: 90%"
                :autoHeight="true"
                border="none"
              ></u--textarea>
            </div>
            <u-icon slot="right" name="map" size="20"></u-icon>
          </u-form-item> -->
        <!-- 发布按钮 -->
        <view class="pitchOn" style="padding: 20px">
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
      </u--form>
    </div>
    <!-- 定位 -->
    <u-modal
      :show="modalShow"
      content="检测到您未授权获取位置权限，将无法操作内容,是否去设置打开。"
      @cancel="modalCancel"
      :showCancelButton="true"
      @confirm="modalConfirm"
    ></u-modal>
  </div>
</template>
<script>
import navigation from "@/components/navigation/navigation";
import store from "@/store/index.js";
import "@/static/style/style.css";
export default {
  components: {
    // navigation,
  },
  data() {
    return {
      model1: {
        typeName: "",
        typeCode: null,
        titleTxt: "",
        content: "",
        workImgs1: "", // 拍照
        workImgs2: "", // 拍照
        address: "",
      },
      model: {
        s2: "",
        s5: "", //当前定位
      },
      // 定位
      modalShow: false,
      authorization: false,
      locationd: {},
      // 紧急状态单选框
      checkboxValue1: [],
      // 基本案列数据
      checkboxList1: [
        {
          id: 1,
          name: "环境卫生",
          disabled: false,
        },
        {
          id: 2,
          name: "消防隐患",
          disabled: false,
        },
        {
          id: 3,
          name: "其他问题",
          disabled: false,
        },
      ],
      //上传附件1
      action: this.$mConfig.baseUrl + this.$apis.upload,
      fileList1: [],
      urls1: [],
      //上传附件2
      fileList2: [],
      urls2: [],
      rules: {
        typeCode: {
          type: "string",
          required: true,
          message: "请选择类型",
          trigger: ["blur", "change"],
        },
        titleTxt: {
          type: "string",
          required: true,
          message: "请填写标题",
          trigger: ["blur", "change"],
        },
        address: {
          type: "string",
          required: true,
          message: "请填写详细地址",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onReady() {
    this.$refs.uForm.setRules(this.rules);
  },
  onLoad(e) {
    // 定位
    //  this.dingweiLoad();
  },
  methods: {
    // 定位
    dingweiLoad() {
      let that = this;
      uni.authorize({
        scope: "scope.userLocation",
        success(res) {
          console.log("----------------定位-------------------", res);
          uni.getLocation({
            type: "wgs84",
            success: function (res) {
              let latitude = res.latitude;
              let longitude = res.longitude;
              // 油箱项目地图密钥
              const qqmapsdk = new QQMapWX({
                key: "HCOBZ-IPVCJ-NJ5FP-KDHXO-WI5WH-H2F7G",
              });
              qqmapsdk.reverseGeocoder({
                latitude,
                longitude,
                success: function (resd) {
                  that.locationd = resd.result;
                  that.model.s2 =
                    that.locationd.location.lng +
                    "," +
                    that.locationd.location.lat;
                  that.model.s5 = that.locationd.address;
                  that.authorization = true;
                },
              });
            },
          });
        },
        fail() {
          that.modalShow = true;
        },
      });
    },
    locationClick() {
      if (!this.authorization) {
        this.modalShow = true;
        return;
      }
      let that = this;
      uni.chooseLocation({
        latitude: that.locationd.location.lat,
        longitude: that.locationd.location.lng,
        success: function (res) {
          that.model.s2 = res.longitude + "," + res.longitude;
          that.model.s5 = res.address;
        },
        fail: function (res) {
          console.log("选择位置错误：", res);
        },
      });
    },
    modalCancel() {
      this.modalShow = false;
    },
    modalConfirm() {
      let that = this;
      uni.openSetting({
        success(res) {
          uni.getLocation({
            type: "wgs84",
            isHighAccuracy: true,
            success: function (res) {
              let latitude = res.latitude;
              let longitude = res.longitude;
              that.latitude = latitude;
              that.longitude = longitude;
              // 油箱项目地图密钥
              const qqmapsdk = new QQMapWX({
                key: "HCOBZ-IPVCJ-NJ5FP-KDHXO-WI5WH-H2F7G",
              });
              qqmapsdk.reverseGeocoder({
                latitude,
                longitude,
                success: function (resd) {
                  that.locationd = resd.result;
                  that.model.s2 =
                    resd.result.location.lng + "," + resd.result.location.lat;
                  that.model.s5 = resd.result.address;
                  that.authorization = true;
                  // that.model.wcName = that.locationd.ad_info.city + that.locationd.ad_info.district
                  console.log(
                    "-------------定位--------------",
                    that.locationd
                  );
                },
              });
            },
          });
        },
      });
      this.modalShow = false;
    },
    // 类型
    checkboxChange(n) {
      console.log(n);
    },
    inputContent(e) {
      console.log(e);
    },
    // 删除图片1
    deletePic1(event) {
      this.fileList1.splice(event.index, 1);
      this.urls1.splice(event.index, 1);
    },
    // 新增图片1
    async afterRead1(event) {
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(event.file);
      for (let i = 0; i < lists.length; i++) {
        const result = await this.uploadFilePromise(lists[i].url);
        let url = this.$mConfig.assetsPath + JSON.parse(result).message;
        this.urls1.push(JSON.parse(result).message);
        this.fileList1.push({
          url: url,
        });
        this.model1.workImgs1 = this.urls1.join(",");
      }
    },
    // 删除图片2
    deletePic2(event) {
      this.fileList2.splice(event.index, 1);
      this.urls2.splice(event.index, 1);
    },
    // 新增图片2
    async afterRead2(event) {
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(event.file);
      for (let i = 0; i < lists.length; i++) {
        const result = await this.uploadFilePromise(lists[i].url);
        let url = this.$mConfig.assetsPath + JSON.parse(result).message;
        this.urls2.push(JSON.parse(result).message);
        this.fileList2.push({
          url: url,
        });
        this.model2.workImgs2 = this.urls2.join(",");
      }
    },
    uploadFilePromise(url) {
      console.log("this.action", this.action, url);
      // let token = store.state.token
      return new Promise((resolve, reject) => {
        let a = uni.uploadFile({
          url: this.action, // 仅为示例，非真实的接口地址
          filePath: url,
          name: "file",
          formData: {
            biz: "article",
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
            }, 1000);
          },
        });
      });
    },
    //提交
    submitAddWork() {
      // uni.navigateTo({
      //   url: "/pages/contradiction/allot",
      // });
      // 定位
      if (!this.authorization) {
        this.modalShow = true;
        return;
      }
      // 类型选择
      if (Array.isArray(this.model1.typeCode)) {
        this.model1.typeCode = this.model1.typeCode.join(",");
      } else {
        this.model1.typeCode = null;
      }
      console.log("提交参数", this.model1);
      this.$refs.uForm
        .validate()
        .then((r) => {
          uni.$u.toast("校验通过");
          // if (this.model1.workImgs) {
          //   this.model1.workImgs = this.model1.workImgs.join(",");
          // } else {
          //   this.model1.workImgs = "";
          // }
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
.snapshot {
  padding: 8px 8px 0px;
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
  .content {
    // background: white;
    // border-radius: 8px;
    // margin-top: 10px;
    // padding: 10px;
    .forItme {
      background: white;
      padding: 0px 15px 15px;
      border-radius: 10px;
      margin-bottom: 8px;
    }
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
    padding: 4px;
    padding-bottom: 5px;
  }
}
.checkbox /deep/ .u-checkbox-group--row {
  justify-content: space-between;
}
</style>