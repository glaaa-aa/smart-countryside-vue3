<template>
  <view class="">
    <!-- 内容 -->
    <div class="cont">
      <u--form
        labelPosition="left"
        :model="model1"
        :rules="rules"
        ref="uForm"
        :labelStyle="{ marginLeft: '15px' }"
      >
        <!-- 事件基本信息 -->
        <div class="module1">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="事件基本信息"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <!-- 纠纷名称 -->
          <u-form-item label="纠纷名称：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.title || "-" }}</div>
          </u-form-item>
          <!-- 纠纷类型 -->
          <u-form-item label="纠纷类型：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ getDict(model1.category) || "-" }}
            </div>
          </u-form-item>
          <!-- 行政区划 -->
          <u-form-item label="行政区划：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ model1.orgName || "-" }}
            </div>
          </u-form-item>
          <!-- 发生地点 -->
          <u-form-item label="发生地点：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.address || "-" }}</div>
          </u-form-item>
          <!-- 发生时间 -->
          <u-form-item label="发生时间：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ model1.happenDate || "-" }}
            </div>
          </u-form-item>
          <!-- 受理时间 -->
          <u-form-item label="受理时间：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ model1.acceptDate || "-" }}
            </div>
          </u-form-item>
          <!-- 涉及人数 -->
          <u-form-item label="涉及人数：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.numbers || 0 }}</div>
          </u-form-item>
          <!-- 纠纷描述 -->
          <u-form-item label="纠纷描述：" ref="item1" labelWidth="110">
            <!-- <u-parse  :content="content"></u-parse> -->
            <div class="two_fs_list itemTxt">{{ model1.content || "-" }}</div>
          </u-form-item>
          <!-- 备注 -->
          <u-form-item
            v-if="model1.remarks"
            label="备注："
            ref="item1"
            labelWidth="110"
          >
            <div class="two_fs_list itemTxt">{{ model1.remarks || "-" }}</div>
          </u-form-item>
        </div>
        <!-- 涉及人员信息 -->
        <div class="module1">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="涉及人员信息"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>

          <div
            :style="{
              borderBottom:
                model1.involvedUsers.length - 1 == index
                  ? 'none'
                  : '1px solid #EEEEEE',
            }"
            v-for="(item, index) in model1.involvedUsers"
            :key="index"
          >
            <!-- 当事人 -->
            <u-form-item
              :label="'当事人' + Number(index + 1) + '：'"
              ref="item1"
              labelWidth="110"
            >
              <div class="two_fs_list itemTxt">{{ item.realname || "" }}</div>
            </u-form-item>
            <!-- 证件号码 -->
            <u-form-item label="证件号码：" ref="item1" labelWidth="110">
              <div class="two_fs_list itemTxt">{{ item.idCard || "" }}</div>
            </u-form-item>
            <!-- 联系方式 -->
            <u-form-item label="联系方式：" ref="item1" labelWidth="110">
              <div
                class="two_fs_list itemTxt"
                @click="playphone(item.phone)"
                style="font-weight: bold; color: #0187f6"
              >
                {{ item.phone }}
              </div>
            </u-form-item>
            <!-- 家庭住址 -->
            <u-form-item label="家庭住址：" ref="item1" labelWidth="110">
              <div class="two_fs_list itemTxt">{{ item.address || "" }}</div>
            </u-form-item>
          </div>
          <div style="color: #606266" v-if="!model1.involvedUsers.length">
            暂无数据
          </div>
        </div>
        <!-- 材料上传 -->
        <div class="module1" v-if="imgData.length || fileList1.length">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="材料上传"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <div style="padding-left: 15px" v-if="fileList1.length">
            <u-upload
              width="60"
              height="60"
              :fileList="fileList1"
              @afterRead="afterRead"
              @delete="deletePic"
              name="file"
              :multiple="false"
              :maxCount="fileList1.length"
              accept="image"
              :deletable="false"
            >
            </u-upload>
          </div>

          <div v-if="imgData.length">
            <uni-file-picker
              limit="5"
              file-mediatype="all"
              v-model="imgData"
              :del-icon="false"
              :readonly="true"
            ></uni-file-picker>
          </div>
        </div>

        <!-- 分派人员 -->
        <div class="module1" v-if="model1.state == '2' || model1.state == '3'">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="分派人员"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <!-- 行政区划 -->
          <!-- <u-form-item label="行政区划：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.orgName || "" }}</div>
          </u-form-item> -->
          <!-- 责任单位 -->
          <!-- <u-form-item label="责任单位：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.respUnit || "" }}</div>
          </u-form-item> -->
          <!-- 负责人员 -->
          <u-form-item label="负责人员：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ model1.s1 || "-" }}</div>
          </u-form-item>
          <!-- 调解员 -->
          <u-form-item label="调解员：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              <!-- <span v-if="model1.s2">{{
                model1.s2 ? model1.s2.replace(/,/g, "，") : ""
              }}</span> -->
              <span v-if="model1.s2">{{
                model1.s2 ? filterStr(model1.s2) : ""
              }}</span>
              <!-- <span>{{ model1.s2.indexOf(model1.s1) !== -1?'': '，'+model1.s1 }}</span> -->
            </div>
          </u-form-item>
        </div>
        <!-- 反馈内容 -->
        <div class="module1 module5" v-if="model1.state == '3'">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="反馈内容"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <!-- 达成协议 -->
          <u-form-item
            label="达成协议："
            prop="s1"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <div style="margin-bottom: 8px">
              <u-radio-group
                v-model="radiovalue1"
                placement="row"
                @change="groupChange1"
                iconPlacement="left"
                disabled
              >
                <u-radio
                  :customStyle="{ marginRight: '15px' }"
                  v-for="(item, index) in radiolist1"
                  :key="index"
                  :label="item.name"
                  :name="item.id"
                  labelSize="17px"
                  iconSize="20px"
                  size="22"
                >
                </u-radio>
              </u-radio-group>
            </div>
          </u-form-item>
          <!-- 处理状态 -->
          <u-form-item
            label="处理状态："
            prop="s1"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <div style="margin-bottom: 8px">
              <u-radio-group
                v-model="radiovalue2"
                placement="row"
                @change="groupChange2"
                iconPlacement="left"
                disabled
              >
                <u-radio
                  :customStyle="{ marginRight: '15px', marginBottom: '5px' }"
                  v-for="(item, index) in radiolist2"
                  :key="index"
                  :label="item.name"
                  :name="item.id"
                  labelSize="17px"
                  iconSize="20px"
                  size="22"
                >
                </u-radio>
              </u-radio-group>
            </div>
          </u-form-item>
          <!-- 紧急状态 -->
          <u-form-item
            label="紧急状态："
            prop="s1"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <div style="margin-bottom: 8px">
              <u-radio-group
                v-model="radiovalue3"
                placement="row"
                @change="groupChange3"
                iconPlacement="left"
                disabled
              >
                <u-radio
                  :customStyle="{ marginRight: '15px' }"
                  v-for="(item, index) in radiolist3"
                  :key="index"
                  :label="item.name"
                  :name="item.id"
                  labelSize="17px"
                  iconSize="20px"
                  size="22"
                >
                </u-radio>
              </u-radio-group>
            </div>
          </u-form-item>
          <!-- 反馈内容 -->
          <u-form-item label="反馈内容：" ref="item1" labelWidth="110" borderBottom>
            <u--textarea
              cursor-spacing="50"
              autoHeight
              v-model="model1.results"
              disabledColor="#ffffff"
              placeholder="请填写反馈内容"
              disabled
              border="none"
            ></u--textarea>
          </u-form-item>
          <!-- <div class="textarea">
           
          </div> -->
          <!-- 附件2 -->
          <u-form-item label="附件：" ref="item1" labelWidth="110">
          </u-form-item>
          <div style="padding-left: 15px" v-if="fileList2.length">
            <u-upload
              width="60"
              height="60"
              :fileList="fileList2"
              name="file"
              :multiple="false"
              :maxCount="fileList2.length"
              accept="image"
              :deletable="false"
            >
            </u-upload>
          </div>
          <div v-if="imgData2.length">
            <uni-file-picker
              limit="5"
              file-mediatype="all"
              v-model="imgData2"
              :del-icon="false"
              :readonly="true"
            ></uni-file-picker>
          </div>
        </div>
        <!-- 矛盾调解流程 -->
        <div class="module1">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="矛盾调解流程"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <!-- <div class="steps">
            <u-steps
              :current="model1.dissensionLog.length || 0"
              dot
              direction="column"
            >
              <u-steps-item
                v-for="i in model1.dissensionLog"
                :key="i.id"
                :title="i.s2"
                :desc="i.handleDate"
              ></u-steps-item>
            </u-steps>
          </div> -->
          <!-- 时间轴 -->
          <div
            class="steps_box"
            v-for="(item, index) in model1.dissensionLog.slice().reverse()"
            :key="item.id"
          >
            <div class="steps_left">流程标题</div>
            <div :class="index == '0' ? 'steps_right_bag' : 'steps_right'">
              <div class="item_time" style="display: flex">
                <span>{{ item.handleDate }}</span>
              </div>
              <div class="item_txt" style="display: flex">
                <u-parse :content="item.s2"></u-parse>
              </div>
              <!-- 图片 -->
              <div style="padding-top: 5px">
                <div style="padding-top: 5px">
                  <u-upload
                    :fileList="fileList2"
                    name="1"
                    multiple
                    :maxCount="fileList2.length"
                    :deletable="false"
                    width="60"
                    height="60"
                  ></u-upload>
                </div>
              </div>
            </div>
          </div>
        </div>
      </u--form>
    </div>
    <!-- 下一步 -->
    <!-- <view class="pitchOn">
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
        提交
      </button>
    </view> -->
  </view>
</template>

<script>
import "@/static/style/style.css";
import store from "@/store/index.js";
import xuanze from "./xuanze.vue";
import navigation from "@/components/navigation/navigation";
import piaoyiEditor from "@/uni_modules/piaoyi-editor/components/piaoyi-editor/piaoyi-editor.vue";
import { getImgUrl, playphone, recursion, filterStr } from "@/common/utils.js";

export default {
  components: {
    xuanze,
    piaoyiEditor,
    navigation,
  },
  data() {
    return {
      //纠纷描述
      content: `
					<p style="color:red">露从今夜白，月是故乡明</p>
				`,
      radiolist1: [
        {
          id: 1,
          name: "口头",
          disabled: false,
        },
        {
          id: 2,
          name: "书面",
          disabled: false,
        },
      ],
      radiovalue1: 1,
      // 处理状态单选框
      radiolist2: [
        {
          id: 1,
          name: "已提交",
          disabled: false,
        },
        {
          id: 2,
          name: "iE走流程",
          disabled: false,
        },
        {
          id: 3,
          name: "已解决",
          disabled: false,
        },
      ],
      radiovalue2: 1,
      // 紧急状态单选框
      radiolist3: [
        {
          id: 1,
          name: "高",
          disabled: false,
        },
        {
          id: 2,
          name: "中",
          disabled: false,
        },
        {
          id: 3,
          name: "低",
          disabled: false,
        },
      ],
      radiovalue3: 1,
      //图片
      fileList1: [], //附件1
      fileList2: [], //附件2
      action: this.$mConfig.baseUrl + this.$apis.upload,
      model1: {
        baseInfo: {}, //基本信息
        numbers: null, //涉及人数
        dataFiles: [], //相关材料
        involvedUsers: [], //涉及人员信息
        adminUnit: "", //行政区划
        respUnit: "", //责任单位
        respPersons: "", //负责人员
        protocolState: "", //协议方式
        state: "", //处理状态
        emergency: "", //紧急状态
        results: "", //反馈内容
        attachment: "", //反馈附件
        logs: [], //矛盾调解流程
      },
      //富文本编辑器
      readOnly: false, //是否只读
      photoUrl: this.$mConfig.baseUrl, //服务器图片域名或者ip
      api: this.$apis.img_upload, //上传图片接口地址
      txt: "",
      name: "file",
      values: "",

      workData: {},
      Data: [],
      projectList: [], //项目列表数据
      xuanzeShow: false,
      MultiSelect: false,
      type: "",
      title: "",
      beginTimeShow: false, //开始时间选择
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
      dictModel: [],
      imgData: [], //回显上传文件1
      imgData2: [], //回显上传文件2
    };
  },
  onLoad(e) {
    console.log("ee", e);
    this.getTypeData();
    if (e.id) {
      this.getWorkDetail(e.id); //查询列表详情
    }

    this.userInfo = store.state.userInfo;
    // this.getProjectList(); //查询项目名称
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  onShow() {},
  created() {},
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
    getImgUrl,
    filterStr,
    playphone,
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
    //       let data = JSON.parse(res.data); //返回的是字符串，需要转成对象格式
    //       console.log(data, "resssssssss");
    //       this.model1.attachment.push({
    //         name: fileObj.name,
    //         url: data.message,
    //         fileSize: fileObj.uuid,
    //         extname: fileObj.extname,
    //       });
    //       console.log(this.model1.attachment, "attachment");
    //     },
    //     fail: () => {
    //       console.log("err");
    //     },
    //   });
    // },
    // // 移出图片函数
    // async deletephoto(e) {
    //   console.log(e, "移出");
    //   let index = this.model1.attachment.findIndex((ele) => {
    //     return ele.fileSize === e.tempFile.uuid;
    //   });
    //   this.model1.attachment.splice(index, 1);
    //   console.log(this.model1.attachment, "删除后");
    // },
    //达成协议单选框
    groupChange1(n) {
      console.log("groupChange1", n);
    },
    //处理状态单选框
    groupChange2(n) {
      console.log("groupChange2", n);
    },
    //紧急状态单选框
    groupChange3(n) {
      console.log("groupChange3", n);
    },
    //富文本编辑器
    saveContens(e) {
      console.log(e, "富文本编辑器");
      this.model1.workContent = e.html;
    },
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        this.dictModel = res;
        console.log(res, "字典");
      });
    },
    getDict(value) {
      // console.log("value=====>", value);
      let label = "";
      this.dictModel.forEach((itm) => {
        if (itm.value == value) {
          label = itm.text;
        }
      });
      return label;
    },

    //获取当前时间
    getCurrentDate() {
      let now = new Date();
      let year = now.getFullYear();
      let month = now.getMonth() + 1;
      let day = now.getDate();
      return year + "-" + month + "-" + day;
    },
    //查询列表详情
    getWorkDetail(id) {
      let imgArr = [];
      let imgArr2 = [];
      this.$apis.dissensionDetails({ id: id }).then((res) => {
        console.log("====查看详情==", res);
        this.radiovalue1 = Number(res.result.protocolState) || 0;
        this.radiovalue2 = Number(res.result.state) || 0;
        this.radiovalue3 = Number(res.result.emergency) || 0;

        res.result.dataFiles = JSON.parse(res.result.dataFiles) || [];
        res.result.attachments = JSON.parse(res.result.attachments) || [];
        imgArr = res.result.dataFiles;
        imgArr2 = res.result.attachments;
        // console.log(imgArr, "imgArr");
        // 附件1
        if (imgArr == "[]") {
          imgArr = [];
        }
        // console.log(imgArr, "imgArr");
        if (imgArr.length) {
          imgArr.forEach((item) => {
            // console.log(item, "后缀");
            if (
              item.filePath.includes(".jpg") ||
              item.filePath.includes(".png") ||
              item.filePath.includes(".jpeg")
            ) {
              this.fileList1.push({
                url: this.$mConfig.baseUrl + "/" + item.filePath,
              });
            } else {
              if (item.fileName) {
                this.imgData.push({
                  name: item.fileName,
                  url: item.filePath,
                  fileSize: item.fileSize,
                  extname:
                    "." +
                    item.filePath.substring(item.filePath.lastIndexOf(".") + 1),
                });
              } else {
                this.imgData.push({
                  name: item.filePath.substr(-17, 17),
                  url: item.filePath,
                  fileSize: item.fileSize,
                  extname:
                    "." +
                    item.filePath.substring(item.filePath.lastIndexOf(".") + 1),
                });
              }
            }
          });
          // console.log(
          //   this.imgData,
          //   "<==this.imgData",
          //   " this.fileList1==>",
          //   this.fileList1
          // );
        }
        // 附件2
        if (imgArr2 == "[]") {
          imgArr2 = [];
        }
        if (imgArr2.length) {
          imgArr2.forEach((item) => {
            // console.log(item, "后缀");
            if (
              item.filePath.includes(".jpg") ||
              item.filePath.includes(".png") ||
              item.filePath.includes(".jpeg")
            ) {
              this.fileList2.push({
                url: this.$mConfig.baseUrl + "/" + item.filePath,
              });
            } else {
              if (item.fileName) {
                this.imgData2.push({
                  name: item.fileName,
                  url: item.filePath,
                  fileSize: item.fileSize,
                  extname:
                    "." +
                    item.filePath.substring(item.filePath.lastIndexOf(".") + 1),
                });
              } else {
                // this.imgData2.push(item);
                this.imgData2.push({
                  name: item.filePath.substr(-17, 17),
                  url: item.filePath,
                  fileSize: item.fileSize,
                  extname:
                    "." +
                    item.filePath.substring(item.filePath.lastIndexOf(".") + 1),
                });
              }
            }
          });
          console.log(this.imgData2, "this.imgData2");
        }
        if (res.success) {
          this.model1 = res.result;
          console.log("====查看详情2222=======", this.model1);
        }
      });
    },
    //查询项目名称
    // getProjectList() {
    //   this.$apis
    //     .projectList({ memberCode: this.userInfo.username })
    //     .then((res) => {
    //       console.log("---项目-->>>>", res);
    //       if (res.success) {
    //         this.projectList = res.result;
    //         this.projectList.forEach((item) => {
    //           item.title = item.text;
    //         });
    //       }
    //     });
    // },
    // 搜索接收子组件事件
    confirmSearch(val) {
      if (!val) {
        this.getProjectList(); //查询项目名称
      } else {
        let searchList = this.projectList.filter(
          (item) => item.title.indexOf(val) >= 0
        );
        this.projectList = searchList;
      }
    },
    // 删除图片
    deletePic(event) {
      console.log("删除图片", event);
      this.fileList1.splice(event.index, 1);
      this.model1.dataFiles.splice(event.index, 1);
      console.log("删除后的图片", this.fileList1, this.model1.dataFiles);
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
        this.model1.dataFiles.push(JSON.parse(result).message);
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
    // xuanzeConfirm(index, value, type) {
    //   console.log("========index, value, type======", index, value, type);
    //   this.model1.s1 = value.title;
    //   this.model1.projectId = value.value;
    //   console.log("--------projectId----------", this.model1.projectId);
    //   this.xuanzeShow = false;
    // },
    //项目
    // projectClick() {
    //   this.xuanzeShow = true;
    //   this.type = "project";
    //   this.title = "请选择项目";
    // },
    // xuanzeClose() {
    //   this.xuanzeShow = false;
    // },
    // beginTimeClick() {
    //   this.beginTimeShow = true;
    // },
    // beginTimeCancel() {
    //   this.beginTimeShow = false;
    // },
    // beginTimeConfirm(value) {
    //   this.model1.beginTime = this.timestampToTime(value.value);
    //   this.beginTimeShow = false;
    // },
    //将时间戳转换成日期格式
    // timestampToTime(timestamp) {
    //   let date = new Date(timestamp);
    //   let Y = date.getFullYear() + "-";
    //   let M =
    //     date.getMonth() + 1 < 10
    //       ? "0" + (date.getMonth() + 1) + "-"
    //       : date.getMonth() + 1 + "-";
    //   let D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    //   return Y + M + D;
    // },
    submitAddWork() {
      uni.navigateTo({
        url: "/pages/contradiction/modules/mediateSubmitForm",
      });
      // this.$refs.uForm
      //   .validate()
      //   .then((r) => {
      //     console.log(r);
      //     // uni.$u.toast('校验通过')
      //     if (this.model1.dataFiles) {
      //       this.model1.dataFiles = this.model1.dataFiles.join(",");
      //     } else {
      //       this.model1.dataFiles = "";
      //     }
      //     this.$apis.workDailyAdd(this.model1).then((res) => {
      //       console.log("---添加-->>>>", res, this.model1);
      //       if (res.success) {
      //         uni.showToast({
      //           title: res.message,
      //           icon: "success",
      //           duration: 1000,
      //           success() {
      //             setTimeout(() => {
      //               wx.navigateBack({
      //                 delta: 1,
      //               });
      //             }, 1000);
      //           },
      //         });
      //       } else {
      //         uni.showToast({
      //           title: res.message,
      //           icon: "none",
      //           duration: 1000,
      //         });
      //       }
      //     });
      //   })
      //   .catch((errors) => {
      //     console.log("errors", errors);
      //     uni.$u.toast("请完善表单内容！");
      //   });
    },
  },
};
</script>

<style lang="less" scoped>
.cont {
  padding: 10px;
  .jifen {
    display: flex;
    align-items: center;
    // border-bottom: 1px solid #eaebec;
  }
  .jifen /deep/ .u-icon--right {
    flex-direction: row;
    align-items: center;
    width: 5px;
    height: 19px;
    background: #1658c8;
    margin-right: 5px;
  }
  .module1 {
    padding: 0px 15px 10px;
    background: white;
    margin-top: 2px;
    margin-bottom: 10px;
    border-radius: 10px;
    border: 1px solid #DADBDE;
    .itemTxt {
      color: #9a9a9a;
    }
  }
}
 //步骤条
 .steps_box {
      display: flex;
      align-items: center;
      margin: 10px 0;
      .steps_left {
        width: 100px;
      }
      .steps_right {
        border-left: 3px solid #b9b9b9;
        background: #eee;
        width: 100%;
        padding: 10px 5px;
        font-size: 16px;
        .item_txt {
          line-height: 22px;
        }
        .item_time {
          // color: #475152;
          // margin-top: 5px;
        }
      }
      .steps_right_bag {
        width: 100%;
        padding: 10px 5px;
        font-size: 16px;
        border-left: 3px solid #58aeaa;
        background: #e9f7f7;
        .item_time {
          // color: #475152;
          // margin-top: 5px;
        }
      }
    }
.cont /deep/ .u-form-item__body__left__content__required {
  left: 5%;
}
//提交按钮
.pitchOn {
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 30px 15px;
  z-index: 9;
}
.textarea /deep/ .u-textarea {
  padding: 4px;
  margin: 0 15px;
  background: #fbfbfb;
}
// 单选
.module1 /deep/ .u-radio-group {
  flex-wrap: wrap !important;
}
.radioState /deep/ .u-radio-group--row {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
// 日志
.steps /deep/ .u-text__value--content {
  font-size: 15px !important;
}
.steps /deep/ .u-text__value--tips {
  font-size: 14px !important;
}
// form表单
.module1 /deep/ .u-form-item__body {
  padding: 3px 0 !important;
}
.module5 /deep/ .u-form-item__body {
  padding: 6px 0 !important;
}
// 上传组件边框
.module1 /deep/ .is-text-box {
  border: none;
  border-style: none !important;
}
</style>