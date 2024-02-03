<template>
  <view class="" style="padding: 0px 10px 60px">
    <!-- 导航栏 -->
    <!-- <navigation title="矛盾受理"></navigation> -->
    <!-- 内容 -->
    <div class="contentBox">
      <u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
        <!-- 纠纷名称 -->
        <u-form-item
          label="纠纷名称："
          prop="title"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.title"
            disabledColor="#ffffff"
            placeholder="请填写"
            border="none"
          ></u--input>
        </u-form-item>
        <!-- 纠纷类型 -->
        <u-form-item
          label="纠纷类型："
          prop="category"
          required
          @click="xuanzeShow = true"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="categoryName"
            disabled
            disabledColor="#ffffff"
            placeholder="请选择"
            border="none"
            style="pointer-events: none"
          ></u--input>
          <u-icon slot="right" name="arrow-right"></u-icon>
        </u-form-item>
        <!-- 行政区划 -->
        <u-form-item
          @click="echoDefault(model1.orgCode)"
          label="行政区划："
          prop="orgCode"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="districtName"
            disabled
            disabledColor="#ffffff"
            placeholder="请选择"
            border="none"
            style="pointer-events: none"
          ></u--input>
          <u-icon slot="right" name="arrow-right"></u-icon>
        </u-form-item>
        <!-- 发生地点 -->
        <u-form-item
          label="发生地点："
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <!-- <u--input
            v-model="model1.address"
            disabledColor="#ffffff"
            placeholder="请填写"
            border="none"
          ></u--input> -->
          <u--textarea
            autoHeight
            cursor-spacing="50"
            v-model="model1.address"
            disabledColor="#ffffff"
            placeholder="请填写"
            border="none"
          ></u--textarea>
        </u-form-item>

        <!-- 发生时间 -->
        <u-form-item
          @click="beginTimeShow1 = true"
          label="发生时间："
          prop="happenDate"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.happenDate"
            disabled
            disabledColor="#ffffff"
            placeholder="请选择"
            border="none"
            style="pointer-events: none"
          ></u--input>
          <u-icon slot="right" name="arrow-right"></u-icon>
        </u-form-item>
        <!-- 受理时间 -->
        <u-form-item
          @click="beginTimeShow2 = true"
          label="受理时间："
          prop="acceptDate"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.acceptDate"
            disabled
            disabledColor="#ffffff"
            placeholder="请选择"
            border="none"
            style="pointer-events: none"
          ></u--input>
          <u-icon slot="right" name="arrow-right"></u-icon>
        </u-form-item>
        <!-- 设计人数 -->
        <!-- <u-form-item
          label="涉及人数："
          prop="numbers"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.numbers"
            placeholder="请输入"
            border="none"
            type="number"
          ></u--input>
        </u-form-item> -->

        <!-- 纠纷描述 -->
        <u-form-item
          label="纠纷描述："
          prop="content"
          required
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--textarea
            autoHeight
            cursor-spacing="50"
            v-model="model1.content"
            disabledColor="#ffffff"
            placeholder="请描述活动详情"
            border="none"
          ></u--textarea>
        </u-form-item>
        <!-- <div class="textarea">
          <u--textarea
            cursor-spacing="50"
            v-model="model1.content"
            disabledColor="#ffffff"
            placeholder="请描述活动详情"
            border="none"
          ></u--textarea>
        </div> -->
        <u-form-item
          label="备注："
          prop="remarks"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--textarea
            autoHeight
            cursor-spacing="50"
            v-model="model1.remarks"
            disabledColor="#ffffff"
            placeholder="请填写"
            border="none"
          ></u--textarea>
        </u-form-item>
        <!-- <div class="textarea">
          <u--textarea
            cursor-spacing="50"
            v-model="model1.remarks"
            disabledColor="#ffffff"
            placeholder="请输入"
            border="none"
          ></u--textarea>
        </div> -->
        <!-- 提交材料 -->
        <u-form-item
          label="提交材料："
          prop="dataFiles"
          ref="item1"
          labelWidth="90"
        >
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
        >
        </u-upload>
        <!-- <uni-file-picker
          file-mediatype="all"
          v-model="fileList3"
          mode="grid"
          @select="selectFile"
          @progress="progress"
          @success="success"
          @delete="deletephoto"
          @fail="fail"
          ref="upload"
          limit="5"
        /> -->
      </u--form>
    </div>
    <!-- 下一步 -->
    <view class="pitchOn">
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
        下一步
      </button>
    </view>
    <!-- 纠纷类型选择器 -->
    <xuanze
      title="请选择纠纷类型"
      :show="xuanzeShow"
      @close="xuanzeShow = false"
      :Data="disputeList"
      :MultiSelect="MultiSelect"
      @confirm="xuanzeConfirm"
      @confirmSearch="confirmSearch"
      :activeShow="activeShow"
    ></xuanze>
    <!-- 发生时间选择器 -->
    <u-datetime-picker
      :show="beginTimeShow1"
      mode="datetime"
      ref="picker"
      @cancel="beginTimeShow1 = false"
      @confirm="beginTimeConfirm1"
      @close="beginTimeShow1 = false"
      closeOnClickOverlay
      v-model="model1.happenDate"
    ></u-datetime-picker>
    <!-- 受理时间选择器 -->
    <u-datetime-picker
      :show="beginTimeShow2"
      mode="datetime"
      @cancel="beginTimeShow2 = false"
      @confirm="beginTimeConfirm2"
      @close="beginTimeShow2 = false"
      closeOnClickOverlay
      v-model="model1.acceptDate"
    ></u-datetime-picker>
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
  </view>
</template>

<script>
import "@/static/style/style.css";
import store from "@/store/index.js";
import xuanze from "./xuanze.vue";
import navigation from "@/components/navigation/navigation";
import piaoyiEditor from "@/uni_modules/piaoyi-editor/components/piaoyi-editor/piaoyi-editor.vue";
import { timestampToTime4, recursion } from "@/common/utils.js";
export default {
  components: {
    xuanze,
    navigation,
    piaoyiEditor,
  },
  data() {
    return {
      //图片
      fileList1: [],
      action: this.$mConfig.baseUrl + this.$apis.upload,
      model1: {
        orgCode: "", //行政区划id
        category: "", //纠纷id
        // disputeName: "", //纠纷名称
        happenDate: "", //发生时间
        acceptDate: "", //受理时间
        // numbers:null,//涉及人员总数
        content: "", //纠纷描述
        remarks: "", //备注
        dataFiles: [], //图片
        dissensionInvolvedUserList: [], //新增人员列表
      },
      //行政区划
      currentIcon: require("@/static/index/jt_down.png"),
      defaultIcon: require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [], //区域数据源
      districtName: "", //所属片区值
      //富文本编辑器
      readOnly: false, //是否只读
      photoUrl: this.$mConfig.baseUrl, //服务器图片域名或者ip
      api: this.$apis.img_upload, //上传图片接口地址
      txt: "",
      name: "file",
      values: "",
      //纠纷类型
      categoryName: "",
      disputeList: [],
      xuanzeShow: false,
      MultiSelect: false,
      activeShow: 0,
      //发生时间
      beginTimeShow1: false,
      //受理时间
      beginTimeShow2: false,
      rules: {
        orgCode: {
          type: "string",
          required: true,
          message: "请请选择行政区划",
          trigger: ["blur", "change"],
        },
        title: {
          type: "string",
          required: true,
          message: "请填写纠纷名称",
          trigger: ["blur", "change"],
        },
        category: {
          type: "string",
          required: true,
          message: "请选择纠纷类型",
          trigger: ["blur", "change"],
        },
        happenDate: {
          type: "string",
          required: true,
          message: "请选择时间",
          trigger: ["blur", "change"],
        },
        acceptDate: {
          type: "string",
          required: true,
          message: "请选择时间",
          trigger: ["blur", "change"],
        },
        content: {
          type: "string",
          required: true,
          message: "请填写纠纷描述",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    console.log("e==>", e, "userInfo==>", this.userInfo);
    this.getDisputeType(); //查询纠纷类型
    this.getProjectList(); //查询行政区划
    if (e.id) {
      // this.getWorkDetail(e.id); //查询列表详情
    }
    this.model1.happenDate = this.timestampToTime4(Number(new Date()));
    this.model1.acceptDate = this.timestampToTime4(Number(new Date()));
    wx.getStorage({
      key: "userCode",
      success: (res) => {
        console.log("getStorage==userCode===>", res.data);
        this.model1.orgCode = res.data;
      },
    });
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  onShow() {},
  created() {},
  mounted() {
    // this.model1.beginTime = this.getCurrentDate();
    // console.log(" this.model1.beginTime ", this.model1.beginTime);
  },
  onShareAppMessage(res) {
    if (res.from === "button") {
      // 来自页面内分享按钮
      console.log(res.target);
    }
    return {
      title: "多功能富文本编辑器！",
      path: "/pages/editor/editor",
    };
  },
  onShareTimeline(res) {
    if (res.from === "button") {
      // 来自页面内分享按钮
      console.log(res.target);
    }
    return {
      title: "多功能富文本编辑器！",
    };
  },
  methods: {
    timestampToTime4,
    recursion,
    // // 文件上传
    // selectFile(e) {
    //   // 根据所选图片的个数，多次调用上传函数
    //   console.log(e, "选择文件");
    //   let promises = [];
    //   for (let i = 0; i < e.tempFilePaths.length; i++) {
    //     const promise = this.uploadFiles(e.tempFilePaths, i, e.tempFiles[0]);
    //     console.log(promise, "promise");
    //     promises.push(promise);
    //   }
    //   console.log(promises, "promises");
    //   Promise.all(promises).then((res) => {
    //     console.log(res, "111111");
    //   });
    // },
    // // 上传函数
    // async uploadFiles(tempFilePaths, i, fileObj) {
    //   console.log(fileObj, "fileObj");
    //   let that = this;
    //   await uni.uploadFile({
    //     url: that.action, //后端用于处理图片并返回图片地址的接口
    //     filePath: tempFilePaths[i],
    //     name: "file",
    //     formData: {
    //       biz: "avatar",
    //       // izZip: 1,
    //       // desWidth: 150,
    //     },
    //     //  header:that.header,
    //     header: {
    //       "X-Access-Token": store.state.token,
    //       "Http-Source": "wxMini",
    //       "tenant-id": 1,
    //       // config.header['Content-Type'] = "content-type:application/x-www-form-urlencoded";
    //       // "Content-Type": "application/json", // //设置后端需要的常用的格式就好，特殊情况调用的时候单独设置
    //       "X-AUTH-UUID": store.getters.guid,
    //     },
    //     success: (res) => {
    //       let data = JSON.parse(res.data) || {}; //返回的是字符串，需要转成对象格式
    //       console.log(data, res, "resssssssss");
    //       this.model1.dataFiles.push({
    //         name: fileObj.name,
    //         url: data.message,
    //         fileSize: fileObj.uuid,
    //         extname: fileObj.extname,
    //       });
    //       console.log(this.model1.dataFiles, "dataFiles");
    //     },
    //     fail: () => {
    //       console.log("err");
    //     },
    //   });
    // },
    // // 移出图片函数
    // async deletephoto(e) {
    //   console.log(e, "移出");
    //   let index = this.model1.dataFiles.findIndex((ele) => {
    //     return ele.fileSize === e.tempFile.uuid;
    //   });
    //   this.model1.dataFiles.splice(index, 1);
    //   console.log(this.model1.dataFiles, "删除后");
    // },
    //查询行政区划
    getProjectList() {
      this.$apis.departList({ uid: this.userInfo.id }).then((res) => {
        console.log("查询所属片区", res);
        if (res.result != null) {
          this.jiedaoList = res.result;
          this.districtName = this.recursion(
            this.jiedaoList,
            this.model1.orgCode
          ).name;
          console.log(this.districtName, this.model1.orgCode, "所属片区名称");
        } else {
          this.jiedaoList = [];
        }
      });
    },
    //点击选择行政区划
    echoDefault(id) {
      console.log(id);
      const selectIds = [id];
      this.checkedTreeData(this.jiedaoList, selectIds);
      // this.multiple = true
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
    //富文本编辑器
    saveContens(e) {
      console.log(e, "富文本编辑器");
      this.model1.content = e.html;
    },

    //获取当前时间
    getCurrentDate() {
      let now = new Date();
      let year = now.getFullYear();
      let month = now.getMonth() + 1;
      let day = now.getDate();
      return year + "-" + month + "-" + day;
    },
    //查询纠纷类型字典
    // getWorkDetail(id) {
    //   this.$apis.workDailyDetail({ id: id }).then((res) => {
    //     console.log("====查看详情==", res);
    //     if (res.success) {
    //       if (res.result.workImgs) {
    //         res.result.workImgs = res.result.workImgs.split(",");
    //       }
    //       this.model1 = res.result;
    //       //图片回显处理
    //       if (this.model1.workImgs.length) {
    //         this.model1.workImgs.forEach((item) => {
    //           this.fileList1.push({
    //             url: this.$mConfig.assetsPath + item,
    //           });
    //         });
    //       }

    //       console.log("====查看详情=======", this.model1);
    //     }
    //   });
    // },
    //查询纠纷类型
    getDisputeType() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        console.log("查询纠纷类型字典===>", res);
        this.disputeList = res;
        this.categoryName = this.disputeList[0].title;
        this.model1.category = this.disputeList[0].value;
        console.log(this.categoryName, "this.categoryName");
      });
    },
    //纠纷类型选择
    xuanzeConfirm(index, value, type) {
      console.log("========index, value, type======", index, value, type);
      this.categoryName = value.title;
      this.model1.category = value.value;
      this.xuanzeShow = false;
    },
    // 搜索接收子组件事件
    confirmSearch(val) {
      if (!val) {
        this.getDisputeType(); //查询纠纷类型
      } else {
        let searchList = this.disputeList.filter(
          (item) => item.title.indexOf(val) >= 0
        );
        this.disputeList = searchList;
      }
    },
    // 删除图片
    deletePic(e) {
      console.log("删除图片", e);
      this.fileList1.splice(e.index, 1);
      this.model1.dataFiles.splice(e.index, 1);
      console.log("删除后的图片", this.fileList1, this.model1.dataFiles);
    },
    // 新增图片
    async afterRead(e) {
      console.log(e, "新增图片");
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(e.file);
      console.log(lists, "lists");
      for (let i = 0; i < lists.length; i++) {
        console.log(lists[i], "lists[i].url");
        const result = await this.uploadFilePromise(lists[i].url);
        const resultObj = JSON.parse(result);
        console.log("上传图片结果===>", resultObj);
        if (resultObj.success) {
          let url = this.$mConfig.assetsPath + resultObj.result.furl;
          this.fileList1.push({
            url: url,
          });
          this.model1.dataFiles.push({
            fileName: resultObj.result.name,
            fileSize: resultObj.result.fileId,
            fileType: e.file.type,
            filePath: resultObj.result.furl,
          });
          uni.showToast({
            title: resultObj.message,
            icon: "none",
            duration: 1000,
          });
        } else {
          uni.showToast({
            title: "图片上传失败",
            icon: "none",
            duration: 1000,
          });
        }

        console.log("上传图片的地址", this.fileList1, this.model1.dataFiles);
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
            console.log(res, "uploadFilePromise");
            setTimeout(() => {
              resolve(res.data); //返回请求的数据
            }, 1000);
          },
        });
      });
    },

    //发生时间选择
    async beginTimeConfirm1(value) {
      this.model1.happenDate = await this.timestampToTime4(value.value);
      console.log(this.model1.happenDate);
      this.beginTimeShow1 = false;
    },
    //受理时间选择
    async beginTimeConfirm2(value) {
      console.log(value);
      this.model1.acceptDate = await this.timestampToTime4(value.value);
      this.beginTimeShow2 = false;
      console.log(" this.model1.acceptDate", this.model1.acceptDate);
    },
    //下一步
    submitAddWork() {
      this.$refs.uForm
        .validate()
        .then((r) => {
          // uni.$u.toast('校验通过')
          console.log(this.model1.dataFiles, "this.model1.dataFiles");
          if (this.model1.dataFiles) {
            this.model1.dataFiles = JSON.stringify(this.model1.dataFiles);
          }
          console.log(r, this.model1);
          uni.navigateTo({
            url:
              "/pages/contradiction/modules/mediateSubmitForm?obj=" +
              encodeURIComponent(JSON.stringify(this.model1)),
          });
        })
        .catch((errors) => {
          console.log("errors", errors);
          uni.$u.toast("请完善表单内容！");
        });
    },
  },
};
</script>

<style lang="less" scoped>
.contentBox {
  background: white;
  border-radius: 8px;
  margin-top: 10px;
  padding: 0px 15px 10px;
  .textarea {
    padding-bottom: 15px;
    border-bottom: 1px solid #f1f1f1;
  }
  .textarea /deep/ .u-textarea {
    padding: 4px;
    background: #fbfbfb;
  }
}
.contentBox /deep/ .u-textarea{
     padding: 0px !important; 
}
//下一步按钮
.pitchOn {
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 30px 15px;
  z-index: 9;
}
</style>