<template>
  <view class="">
    <!-- 内容 -->
    <div class="cont two_fs_list">
      <u--form
        labelPosition="left"
        :model="model1"
        :rules="rules"
        ref="uForm"
        :labelStyle="{ marginLeft: '15px' }"
      >
        <!-- 纠纷信息 -->
        <div class="module1">
          <div
            style="
              margin-bottom: 8px;
              border-bottom: 1px solid #f2f2f2;
              padding: 10px 0px 2px 0px;
              display: flex;
              justify-content: space-between;
            "
          >
            <div style="font-size: 20px; font-weight: bold">纠纷信息</div>
          </div>
          <!-- 纠纷名称 -->
          <u-form-item label="纠纷名称：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ dataList.title || "-" }}</div>
          </u-form-item>
          <!-- 纠纷类型 -->
          <u-form-item label="纠纷类型：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ getDict(dataList.category) || "-" }}
            </div>
          </u-form-item>
          <!-- 涉及人员 -->
          <u-form-item label="涉及人员：" ref="item1" labelWidth="110">
            <div style="display: flex; flex-wrap: wrap">
              <div
                class="two_fs_list itemTxt"
                v-for="(i, n) in dataList.involvedUsers"
                :key="n"
              >
                {{ i.realname
                }}{{ dataList.involvedUsers.length - 1 == n ? "" : "，" }}
              </div>
            </div>
          </u-form-item>
          <!-- 行政区划 -->
          <u-form-item label="行政区划：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ dataList.orgName || "-" }}
            </div>
          </u-form-item>
          <!-- 发生地点 -->
          <u-form-item label="发生地点：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ dataList.address || "-" }}</div>
          </u-form-item>
          <!-- 发生时间 -->
          <u-form-item label="发生时间：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ dataList.happenDate || "-" }}
            </div>
          </u-form-item>
          <!-- 受理时间 -->
          <u-form-item label="受理时间：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">
              {{ dataList.acceptDate || "-" }}
            </div>
          </u-form-item>
          <!-- 涉及人数 -->
          <!-- <u-form-item label="涉及人数：" ref="item1" labelWidth="110">
            <div class="two_fs_list itemTxt">{{ dataList.numbers || 0 }}</div>
          </u-form-item> -->
          <!-- 纠纷描述 -->
          <u-form-item label="纠纷描述：" ref="item1" labelWidth="110">
            <!-- <u-parse  :content="content"></u-parse> -->
            <div class="two_fs_list itemTxt">{{ dataList.content || "-" }}</div>
          </u-form-item>
          <!-- 备注 -->
          <u-form-item
            v-if="dataList.remarks"
            label="备注："
            ref="item1"
            labelWidth="110"
          >
            <div class="two_fs_list itemTxt">{{ dataList.remarks || "-" }}</div>
          </u-form-item>
          <!-- 材料上传 -->
          <u-form-item
            v-if="imgData.length || fileList1.length"
            label="材料上传："
            ref="item1"
            labelWidth="110"
          >
          </u-form-item>
          <div style="padding-left: 15px" v-if="fileList1.length">
            <u-upload
              width="60"
              height="60"
              :fileList="fileList1"
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

        <!-- 材料上传 -->
        <!-- <div class="module1" v-if="imgData.length || fileList1.length">
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
        </div> -->
        <!-- 填报信息 -->
        <div class="module1 module5" v-if="pageId == 1">
          <div
            style="
              margin-bottom: 8px;
              border-bottom: 1px solid #f2f2f2;
              padding: 10px 0px 2px 0px;
              display: flex;
              justify-content: space-between;
            "
          >
            <div style="font-size: 20px; font-weight: bold">
              第<span>2</span> 次调解信息
            </div>
          </div>
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="村委人员信息"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <!-- 责任人员 -->
          <u-form-item
            @click="peopleClick('1', model1.respPersons)"
            label="责任人员："
            prop="respPersons"
            required
            ref="item1"
            labelWidth="125"
            borderBottom
          >
            <u--input
              v-model="model1.s1"
              disabled
              disabledColor="#ffffff"
              placeholder="请选择"
              border="none"
              style="pointer-events: none"
            ></u--input>
            <u-icon slot="right" name="arrow-right"></u-icon>
          </u-form-item>
          <!-- 参与人员 -->
          <u-form-item
            label="参与人员："
            prop="dissensionResponsibleUserList"
            required
            ref="item1"
            labelWidth="120"
            :borderBottom="!model1.dissensionResponsibleUserList.length"
          >
            <u-icon
              @click="peopleClick('2', model1.dissensionResponsibleUserList)"
              slot="right"
              name="plus-circle"
              size="22"
              color="#1658C8"
            ></u-icon>
          </u-form-item>
          <div
            class="info two_fs"
            style="
              display: flex;
              justify-content: space-between;
              padding: 10px 0px 0px 15px;
              border-bottom: 1px solid #eaebec;
              font-size: 15px;
            "
            v-for="(item, index) in model1.dissensionResponsibleUserList"
            :key="index"
            v-if="model1.dissensionResponsibleUserList.length"
          >
            <div>
              <div style="display: flex; margin-bottom: 10px">
                <div class="infoItem" style="margin-right: 10px">
                  {{ item.realname }}
                </div>
                <div class="infoItem" style="margin-right: 10px">
                  {{ item.phone }}
                </div>
                <div class="infoItem">{{ item.unit || "" }}</div>
              </div>
              <div class="infoItem infoItem3" v-if="item.remarks">
                备注：{{ item.remarks }}
              </div>
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
          <u-popup
            :show="popupShow"
            @close="popupShow = false"
            mode="center"
            round="10"
          >
            <div>
              <div
                style="
                  font-weight: bold;
                  text-align: center;
                  border-bottom: 1px solid #ccc;
                  padding: 10px;
                  font-size: 18px;
                "
              >
                添加人员
              </div>
              <!-- 内容 -->
              <div
                class="cont"
                style="
                  background: white;
                  border-radius: 8px;
                  margin-top: 10px;
                  padding: 5px 20px 10px;
                "
              >
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
                    v-model="model2.realname"
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
                    v-model="model2.phone"
                    placeholder="请输入"
                    border="none"
                    type="number"
                  ></u--input>
                </u-form-item>
                <!-- 单位 -->
                <u-form-item
                  label="单位："
                  prop="unit"
                  ref="item1"
                  labelWidth="90"
                  borderBottom
                >
                  <u--input
                    v-model="model2.unit"
                    placeholder="请输入"
                    border="none"
                    type="unit"
                  ></u--input>
                </u-form-item>
                <!-- 备注 -->
                <u-form-item
                  label="备注："
                  prop="remarks"
                  ref="item1"
                  labelWidth="90"
                  borderBottom
                >
                  <u--input
                    v-model="model2.remarks"
                    placeholder="请输入"
                    border="none"
                    type="text"
                  ></u--input>
                </u-form-item>
              </div>
              <!-- 新增 -->
              <div class="pitchOn">
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
                  @click="addPersonnel"
                >
                  添加
                </button>
              </div>
            </div>
          </u-popup>
          <!-- 填报人员 -->
          <u-form-item
            label="填报人员："
            ref="item1"
            labelWidth="90"
            borderBottom
          >
            <div class="two_fs_list itemTxt">{{ model1.userName || "" }}</div>
          </u-form-item>
          <!-- 填报时间 -->
          <u-form-item
            label="填报时间："
            ref="item1"
            labelWidth="90"
            borderBottom
          >
            <div class="two_fs_list itemTxt">{{ model1.fillTime || "" }}</div>
          </u-form-item>
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="调解反馈"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <u-form-item
            label="达成协议："
            prop="protocolState"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <u-radio-group
              v-model="radiovalue1"
              placement="row"
              @change="groupChange1"
              iconPlacement="left"
            >
              <u-radio
                :customStyle="{ margin: '0 15px 10px 0' }"
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
          </u-form-item>
          <u-form-item
            label="处理状态："
            prop="state"
            required
            ref="item1"
            labelWidth="110"
            class="radioState"
            borderBottom
          >
            <u-radio-group
              v-model="radiovalue2"
              placement="row"
              @change="groupChange2"
              iconPlacement="left"
            >
              <u-radio
                :customStyle="{ margin: '0 15px 10px 0' }"
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
          </u-form-item>
          <u-form-item
            label="紧急状态："
            prop="emergency"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <u-radio-group
              v-model="radiovalue3"
              placement="row"
              @change="groupChange3"
              iconPlacement="left"
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
          </u-form-item>

          <!-- 处置结果 -->
          <u-form-item
            label="处置结果："
            prop="results"
            required
            ref="item1"
            labelWidth="110"
            borderBottom
          >
            <u--textarea
              cursor-spacing="50"
              autoHeight
              v-model="model1.results"
              disabledColor="#ffffff"
              placeholder="请填写处置结果"
              border="none"
            ></u--textarea>
          </u-form-item>
          <!-- 备注 -->
          <u-form-item label="备注：" ref="item1" labelWidth="110" borderBottom>
            <u--textarea
              cursor-spacing="50"
              autoHeight
              v-model="model1.remarks"
              disabledColor="#ffffff"
              placeholder="请填写备注"
              border="none"
            ></u--textarea>
          </u-form-item>
          <!-- 附件 -->
          <u-form-item
            label="附件："
            prop="attachments"
            required
            ref="item1"
            labelWidth="110"
          >
          </u-form-item>
          <div style="padding-left: 15px">
            <u-upload
              width="60"
              height="60"
              :fileList="fileList2"
              @afterRead="afterRead2"
              @delete="deletePic2"
              name="file"
              :multiple="false"
              maxCount="5"
            >
            </u-upload>
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
        </div>
        <!-- 历史调解信息 -->
        <div
          class="module1"
          v-if="dataList.involvedUsers.length"
          v-for="(item, index) in dataList.involvedUsers"
          :key="index"
        >
          <div
            style="
              margin-bottom: 8px;
              border-bottom: 1px solid #f2f2f2;
              padding: 10px 0px 2px 0px;
              display: flex;
              justify-content: space-between;
            "
          >
            <div style="font-size: 20px; font-weight: bold">
              第<span>{{ index + 1 }}</span> 次调解信息
            </div>
          </div>
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="人员信息"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <div class="cont2">
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">主要负责人：</span>
              <span class="two_fs_list itemTxt">张晓华</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">参与人：</span>
              <span class="two_fs_list itemTxt">王虎、吴小天</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">填报时间：</span>
              <span class="two_fs_list itemTxt">2020-12-25 05:12:12</span>
            </div>
          </div>
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="调解反馈"
              size="18"
              margin="10px 0"
              bold
            >
            </u--text>
          </div>
          <div class="cont2">
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">处理状态：</span>
              <span class="two_fs_list itemTxt">已解决</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">处置结果：</span>
              <span class="two_fs_list itemTxt"
                >经调解，未达成一直意见，需继续调解</span
              >
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile2">备注：</span>
              <span class="two_fs_list itemTxt">村民陆丽丽 参加</span>
            </div>
            <div class="cont2_1">
              <div class="mim-wthdh-itemTile2">附件上传：</div>
              <div style="padding-left: 15px" v-if="fileList1.length">
                <u-upload
                  width="60"
                  height="60"
                  :fileList="fileList1"
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
          </div>
        </div>
      </u--form>
    </div>
    <!-- 下一步 -->
    <view class="pitchOn2" v-if="pageId == 1">
      <button
        style="
          background-color: #f9ac65;
          width: 48%;
          height: 48px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 15px;
        "
        @click="cancelSubmit"
      >
        取消
      </button>
      <button
        style="
          background-color: #1658c8;
          width: 48%;
          height: 45px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 15px;
        "
        @click="submitAddWork"
      >
        提交
      </button>
    </view>
    <!-- 选择人员信息 -->
    <peoplePicker
      :show="peopleShow"
      :type="peopleType"
      popupTitle="选择人员"
      :name="peopleName"
      :dataLists="peopleList"
      placeholder="按姓名/单位查询"
      @cancel="peopleCancel"
      @search="peopleSearch"
      @submit="peopleConfirm"
      @addPeople="clickAdd"
      @scrolltolower="scrolltolower"
    ></peoplePicker>
  </view>
</template>

<script>
import "@/static/style/style.css";
import store from "@/store/index.js";
import xuanze from "./xuanze.vue";
import peoplePicker from "./peoplePicker.vue";
import navigation from "@/components/navigation/navigation";
import piaoyiEditor from "@/uni_modules/piaoyi-editor/components/piaoyi-editor/piaoyi-editor.vue";
import {
  getImgUrl,
  timestampToTime4,
  playphone,
  filterStr,
} from "@/common/utils.js";
export default {
  components: {
    xuanze,
    piaoyiEditor,
    navigation,
    peoplePicker,
  },
  data() {
    return {
      pageId: 0, //控制显示按钮，填写内容id
      model1: {
        respPerson: [], //负责人员信息
        respPersons: "", //负责人员code
        s1: "", //负责人员名称
        phone: "", //负责人员电话
        dissensionResponsibleUserList: [], //参与人员
        userName: "", //登录人名称
        fillTime: "", //填报时间

        protocolState: 1, //协议方式
        state: "", //处理状态
        emergency: 1, //紧急状态
        results: "", //反馈内容
        attachments: [], //反馈附件
      },
      // 达成协议单选框
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
          name: "已解决",
          disabled: false,
        },
        {
          id: 2,
          name: "未解决",
          disabled: false,
        },
        {
          id: 3,
          name: "无法调解",
          disabled: false,
        },
      ],
      radiovalue2: 0,
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
      imgData: [], //回显文
      imgData2: [], //回显文件2
      fileList1: [], //回显图片
      fileList2: [], //回显图片2
      action: this.$mConfig.baseUrl + this.$apis.upload,

      // 详情数据
      dataList: {},

      model2: {
        realname: "", //人员姓名
        phone: "",
        unit: "", //单位
        remarks: "", //备注
      },
      dictModel: [], //纠纷类型字段
      //表单弹窗
      popupShow: false,
      //选择责任人员
      peopleList: [],
      peopleShow: false,
      peopleType: "radio",
      peopleName: "1",
      //主要责任人员
      zhuyaoList: [],
      // 主要责任人员下拉加载
      status2: "nomore",
      current2: 1,
      pages2: 0,
      pageSize2: 20,
      flag2: false,
      //多选参与人员
      fuzeList: [],
      //多选参与人员下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      rules: {
        respPersons: {
          type: "string",
          required: true,
          message: "请选择主要负责人",
          trigger: ["blur", "change"],
        },
        dissensionResponsibleUserList: {
          type: "array",
          required: true,
          message: "请选择参与人员",
          trigger: ["blur", "change"],
        },
        protocolState: {
          type: "number",
          required: true,
          message: "请选择达成协议",
          trigger: ["blur", "change"],
        },
        state: {
          type: "number",
          required: true,
          message: "请选择处理状态",
          trigger: ["blur", "change"],
        },
        emergency: {
          type: "number",
          required: true,
          message: "请选择紧急状态",
          trigger: ["blur", "change"],
        },
        results: {
          type: "string",
          required: true,
          message: "请填写处置结果",
          trigger: ["blur", "change"],
        },
        attachments: {
          type: "array",
          required: true,
          message: "请上传附件",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onLoad(e) {
    this.userInfo = store.state.userInfo || {};
    this.model1.userName = this.userInfo.realname;
    this.model1.fillTime = this.timestampToTime4(Number(new Date()));
    console.log("e==>", e, "userInfo==>", this.userInfo);
    this.pageId = e.pageId || 0; //控制按钮与填写内容
    if (e.id) {
      this.getWorkDetail(e.id); //查询列表详情
    }
    this.getTypeData();
    this.getpeopleType2(this.current2); //查询主要人员
    this.getpeopleType(this.current); //查询负责人员
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
  methods: {
    getImgUrl,
    filterStr,
    playphone,
    timestampToTime4,
    //下拉加载
    scrolltolower(type) {
      if (type == "checkbox") {
        console.log("多选参与人员下拉加载", type, this.current);
        this.current = ++this.current;
        if (this.pages < this.current) {
          uni.showToast({
            title: "没有更多数据了",
            icon: "none",
            duration: 1000,
          });
          this.status = "nomore";
          return;
        }
        if (this.flag) {
          setTimeout(() => {
            this.status = "loading";
            this.getpeopleType(this.current);
          }, 100);
        }
      } else {
        console.log("主要负责人下拉加载", type, this.current2);
        this.current2 = ++this.current2;
        if (this.pages2 < this.current2) {
          uni.showToast({
            title: "没有更多数据了",
            icon: "none",
            duration: 1000,
          });
          this.status2 = "nomore";
          return;
        }
        if (this.flag2) {
          setTimeout(() => {
            this.status2 = "loading";
            this.getpeopleType2(this.current2);
          }, 100);
        }
      }
    },
    //选择人员
    peopleClick(num, val) {
      console.log(num, val, "选择人员");
      if (num == "1") {
        //编辑单选组件回显
        if (val) {
          this.zhuyaoList.find((item) => {
            if (item.username == val) {
              item.checked = true;
            } else {
              item.checked = false;
            }
          });
        }
        this.peopleList = this.zhuyaoList;
        this.peopleType = "radio";
        this.peopleName = "1";
      } else {
        console.log(val, "val");
        //编辑多选组件回显
        if (val && val.length > 0) {
          for (let i = 0; i < this.fuzeList.length; i++) {
            for (let item = 0; item < val.length; item++) {
              if (this.fuzeList[i].id == val[item].uid) {
                this.fuzeList[i].checked = true;
                break;
              } else {
                this.fuzeList[i].checked = false;
              }
            }
          }
        }
        this.peopleList = this.fuzeList;
        this.peopleType = "checkbox";
        this.peopleName = "2";
      }
      this.peopleShow = true;
    },
    peopleCancel() {
      this.peopleShow = false;
    },
    peopleConfirm(arr, type) {
      console.log(arr, type, "aaa");
      if (type == "1") {
        this.model1.respPersons = arr[0].personNo; //账号
        this.model1.s1 = arr[0].cname;
        this.model1.phone = arr[0].phone;
        this.model1.respPerson = {
          uid: arr[0].id,
          realname: arr[0].cname,
          unit: arr[0].post,
          phone: arr[0].phone,
        };
        // 选择后清除校验文字
        // this.$refs.uForm.validate("respPersons");
      } else {
        this.model1.dissensionResponsibleUserList = [];
        arr.forEach((item) => {
          this.model1.dissensionResponsibleUserList.push({
            realname: item.cname || "",
            phone: item.phone || "",
            unit: item.post || "",
            remarks: item.remarks || "",
            uid: item.id,
          });
        });
        // 选择后清除校验文字
        console.log(
          this.model1.dissensionResponsibleUserList,
          "this.$refs.uForm"
        );
      }
      this.peopleShow = false;
    },
    peopleSearch(val, type) {
      console.log(val, type);
      if (!val) {
        if (type == "1") {
          // this.getpeopleType2(); //查询主要人员
          this.peopleList = this.zhuyaoList;
        } else {
          // this.getpeopleType(); //查询负责人员
          this.peopleList = this.fuzeList;
        }
      } else {
        let searchList = this.peopleList.filter(
          (item) =>
            item.cname.indexOf(val) >= 0 || String(item.post).indexOf(val) >= 0
        );
        this.peopleList = searchList;
      }
    },
    //点击添加人员
    clickAdd() {
      this.peopleShow = false; //关闭多选人员
      this.popupShow = true;
      this.model2.realname = "";
      this.model2.phone = "";
      this.model2.unit = "";
      this.model2.remarks = "";
    },
    addPersonnel() {
      var reg =
        /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
      if (!this.model2.realname) {
        return uni.$u.toast("请输入姓名");
      } else if (!this.model2.phone) {
        return uni.$u.toast("请输入手机号");
      } else if (!reg.test(this.model2.phone)) {
        return uni.$u.toast("请输入正确的手机号");
      } else {
        this.popupShow = false;
        let dataForm = {
          realname: this.model2.realname,
          phone: this.model2.phone,
          unit: this.model2.unit,
          remarks: this.model2.remarks,
          uid: new Date().getTime(),
        };
        this.model1.dissensionResponsibleUserList.push(dataForm);
        console.log(
          "表单",
          this.model1,
          this.model1.dissensionResponsibleUserList
        );
        uni.$u.toast("新增成功");
        // 选择后清除校验文字
        this.$refs.uForm.validate("dissensionResponsibleUserList");
        return;
      }
    },
    //人员删除
    deleteItem(id) {
      console.log(id);
      uni.showModal({
        title: "提示",
        content: "你确定要删除吗",
        success: (res) => {
          if (res.confirm) {
            var index = this.model1.dissensionResponsibleUserList.findIndex(
              (item, index) => {
                return index == id;
              }
            );
            this.model1.dissensionResponsibleUserList.splice(index, 1);
            uni.$u.toast("删除成功！");
          } else if (res.cancel) {
            console.log("取消");
          }
        },
      });
    },
    //查询主要人员
    getpeopleType2(current) {
      // this.zhuyaoList = [
      //   {
      //     id: "1",
      //     realname: "史相豪",
      //     checked: false,
      //     uid: 2023001,
      //     phone: 15367846144,
      //     unit: "某某某单位某某某单位某某某单位某某某单位某某某单位",
      //     img: "https://cdn.uviewui.com/uview/goods/1.jpg",
      //   },
      // ];
      let obj = {
        pageNo: current,
        pageSize: this.pageSize,
      };
      this.$apis.hrPersonList(obj).then((res) => {
        console.log("查询主要人员", res);
        if (res.result != null) {
          this.current2 = res.result.current;
          this.pages2 = res.result.pages;
          this.pageSize2 = res.result.size;

          res.result.records.forEach((item) => {
            item.checked = false;
          });
          this.zhuyaoList = this.zhuyaoList.concat(res.result.records);
          this.peopleList = this.zhuyaoList;
          this.flag2 = true;
          // this.zhuyaoList = res.result.records;
          console.log(this.zhuyaoList, "查询主要人员");
        }
      });
    },
    //查询负责人员
    getpeopleType(current) {
      // this.fuzeList = [
      //   {
      //     id: "12",
      //     realname: "刘德华",
      //     checked: false,
      //     uid: 2023012,
      //     phone: 15367846144,
      //     unit: "某某某单位某某某单位某某某单位某某某单位某某某单位",
      //     img: "https://cdn.uviewui.com/uview/goods/1.jpg",
      //   },
      // ];
      let obj = {
        pageNo: current,
        pageSize: this.pageSize,
      };
      this.$apis.hrPersonList(obj).then((res) => {
        console.log("查询负责人员", res);
        if (res.result != null) {
          this.current = res.result.current;
          this.pages = res.result.pages;
          this.pageSize = res.result.size;

          res.result.records.forEach((item) => {
            item.checked = false;
          });
          this.fuzeList = this.fuzeList.concat(res.result.records);
          this.peopleList = this.fuzeList;
          this.flag = true;
          console.log(this.fuzeList);
        }
      });
    },
    // 删除图片
    deletePic2(event) {
      console.log("删除图片", event);
      this.fileList2.splice(event.index, 1);
      this.model1.attachments.splice(event.index, 1);
      console.log("删除后的图片", this.fileList2, this.model1.attachments);
    },
    // 新增图片
    async afterRead2(event) {
      console.log(event, "新增图片");
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(event.file);
      for (let i = 0; i < lists.length; i++) {
        console.log(lists[i], "lists[i].url");
        const result = await this.uploadFilePromise(lists[i].url);
        const resultObj = JSON.parse(result);
        console.log("上传图片结果===>", resultObj);
        if (resultObj.success) {
          if (
            resultObj.result.furl.includes(".jpg") ||
            resultObj.result.furl.includes(".png") ||
            resultObj.result.furl.includes(".jpeg")
          ) {
            let url = this.$mConfig.assetsPath + resultObj.result.furl;
            this.fileList2.push({
              url: url,
            });
          } else {
            this.imgData2.push({
              fileName: resultObj.result.name,
              fileSize: resultObj.result.fileId,
              fileType: event.file.type,
              filePath: resultObj.result.furl,
            });
            console.log(this.imgData2, "上传文件 this.imgData2");
          }

          this.model1.attachments.push({
            fileName: resultObj.result.name,
            fileSize: resultObj.result.fileId,
            fileType: event.file.type,
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

        console.log("上传图片的地址", this.fileList2, this.model1.attachments);
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
              resolve(res.data); //返回请求的数据
            }, 1000);
          },
        });
      });
    },
    //点击附件下载
    clickDownloadFile(item) {
      let url = item.filePath;
      let type = item.fileType;
      console.log(item);
      let link = "";
      if (item.filePath.includes("http://")) {
        var reg2 = new RegExp("http://");
        var a2 = item.filePath.replace(reg2, "");
        link = this.$mConfig.baseUrl + "/" + a2;
      } else {
        link = this.$mConfig.baseUrl + "/" + item.filePath;
      }
      uni.showLoading({
        title: "加载中",
      });
      // this.$emit("click")
      console.log(item, link, "点击事件");
      uni.downloadFile({
        url: link, //下载地址接口返回
        success: (data) => {
          console.log(data, "下载成功后的返回值:data");
          // if (data.statusCode === 200) {
          //   if (type=== 'jpg' || type === 'jpeg' || type === 'png' || type === 'gif' ) {
          //         //如果是图片走这里
          //     uni.previewImage({
          //             current: 0,
          //             urls: [url],
          //     })
          // }
          //文件保存到本地
          uni.saveFile({
            tempFilePath: data.tempFilePath, //临时路径
            success: function (res) {
              console.log(res, "文件保存到本地111");
              setTimeout(() => {
                //打开文档查看
                uni.openDocument({
                  filePath: encodeURI(res.savedFilePath),
                  showMenu: true, //关键点
                  success: (res) => {
                    console.log(res, "打开文档成功");
                  },
                  fail: (err) => {
                    console.log(err, "打开文档失败", err);
                    uni.showToast({
                      icon: "none",
                      title: "打开文档失败", //保存路径
                      duration: 3000,
                    });
                  },
                });
                uni.hideLoading();
              }, 3000);
            },
          });
          // }
        },
        fail: (err) => {
          console.log(err);
          uni.showToast({
            icon: "none",
            mask: true,
            title: "失败请重新下载",
          });
        },
      });
    },
    //达成协议单选框
    groupChange1(n) {
      console.log("groupChange1", n);
      this.model1.protocolState = n;
    },
    //处理状态单选框
    groupChange2(n) {
      console.log("groupChange2", n);
      this.model1.state = n;
    },
    //紧急状态单选框
    groupChange3(n) {
      console.log("groupChange3", n);
      this.model1.emergency = n;
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
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        this.dictModel = res;
        console.log(res, "查询纠纷类型字典");
      });
    },
    getDict(value) {
      console.log("value=====>", value);
      let label = "";
      this.dictModel.forEach((item) => {
        if (item.value == value) {
          label = item.text;
        }
      });
      console.log(label, "labelaa");
      return label;
    },
    //查询列表详情
    getWorkDetail(id) {
      let imgArr = [];
      let imgArr2 = [];
      this.$apis.dissensionDetails({ id: id }).then((res) => {
        console.log("====查看详情==", res);
        // this.radiovalue1 = Number(res.result.protocolState) || 0;
        // this.radiovalue2 = Number(res.result.state) || 0;
        // this.radiovalue3 = Number(res.result.emergency) || 0;
        res.result.dataFiles = JSON.parse(res.result.dataFiles) || [];
        if (res.result.attachments) {
          res.result.attachments = JSON.parse(res.result.attachments) || [];
        } else {
          res.result.attachments = [];
        }
        imgArr = res.result.dataFiles;
        imgArr2 = res.result.attachments;
        console.log(imgArr);
        // 附件1
        if (imgArr == "[]") {
          imgArr = [];
        }
        if (imgArr.length) {
          imgArr.forEach((item) => {
            console.log(item, "后缀");
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
          console.log(this.imgData, "this.imgData");
        }
        // 附件2
        if (imgArr == "[]") {
          imgArr = [];
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
          res.result.dissensionResponsibleUserList = [];
          this.dataList = res.result;
          console.log("====查看详情dataList=======", this.dataList);
        }
      });
    },
    //下一步
    submitAddWork() {
      this.$refs.uForm
        .validate()
        .then((r) => {
          uni.$u.toast("模拟校验通过");
          // console.log(r, this.model1);
          // console.log(this.model1.dataFiles, "this.model1.dataFiles");
          // if (this.model1.dataFiles) {
          //   this.model1.dataFiles = JSON.stringify(this.model1.dataFiles);
          // }
          // this.$apis.dissensionFeedback(this.model1).then((res) => {
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
    // 取消
    cancelSubmit() {
      wx.navigateBack({
        delta: 1,
      });
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
.cont2 {
  .cont2_1 {
    line-height: 28px;
    display: flex;
    .mim-wthdh-itemTile2 {
      min-width: 205rpx;
    }
  }
}
.cont /deep/ .u-form-item__body__left__content__required {
  left: 5%;
}
.pitchOn {
  height: 60px;
  width: 85%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  z-index: 9;
}
//提交按钮
.pitchOn2 {
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 30px 15px;
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
  padding: 1px 0 !important;
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