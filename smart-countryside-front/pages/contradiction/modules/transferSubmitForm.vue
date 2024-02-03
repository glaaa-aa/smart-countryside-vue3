<template>
  <view class="" style="padding: 0px 10px 60px">
    <!-- 导航栏 -->
    <!-- <navigation title="分派人员"></navigation> -->
    <!-- 内容 -->
    <u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
      <div class="contBox two_fs_list">
        <div class="jifen">
          <u--text
            prefixIcon=" "
            iconStyle="font-size: 19px"
            text="矛盾信息"
            size="18"
            margin="10px 0"
            bold
          >
          </u--text>
        </div>
        <!-- 纠纷名称 -->
        <u-form-item
          label="纠纷名称："
          prop="title"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model2.title"
            disabled
            border="none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
        </u-form-item>
        <!-- 纠纷类型 -->
        <u-form-item
          label="纠纷类型："
          prop="category"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.category_txt"
            disabled
            border="none"
            style="pointer-events: none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
          <!-- <u-icon slot="right" name="arrow-right"></u-icon> -->
        </u-form-item>
        <!-- 行政区划 -->
        <u-form-item
          label="行政区划："
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model2.orgName"
            disabled
            border="none"
            style="pointer-events: none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
          <!-- <u-icon slot="right" name="arrow-right"></u-icon> -->
        </u-form-item>
        <!-- 发生时间 -->
        <u-form-item
          label="发生时间："
          prop="happenDate"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model2.happenDate"
            disabled
            border="none"
            style="pointer-events: none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
          <!-- <u-icon slot="right" name="arrow-right"></u-icon> -->
        </u-form-item>
        <!-- 受理时间 -->
        <u-form-item
          label="受理时间："
          prop="acceptDate"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model2.acceptDate"
            disabled
            border="none"
            style="pointer-events: none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
          <!-- <u-icon slot="right" name="arrow-right"></u-icon> -->
        </u-form-item>
        <!-- 纠纷描述 -->
        <u-form-item
          label="纠纷描述："
          prop="content"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <!-- <u--textarea
            cursor-spacing="50"
            autoHeight
            v-model="model2.content"
            disabled
            border="none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--textarea> -->
          <div class="two_fs_list" style="color: #c0c4cc;">{{model2.content||''  }}</div>
        </u-form-item>
        <u-form-item
          v-if="model2.remarks"
          label="备注："
          prop="remarks"
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <!-- <u--textarea
            cursor-spacing="50"
            autoHeight
            v-model="model2.remarks"
            disabled
            placeholder="请填写"
            border="none"
          ></u--textarea> -->
          <div class="two_fs_list" style="color: #c0c4cc;">{{model2.remarks  }}</div>
        </u-form-item>
      </div>
      <div class="contBox two_fs_list">
        <div class="jifen">
          <u--text
            prefixIcon=" "
            iconStyle="font-size: 19px"
            text="现场处置信息"
            size="18"
            margin="10px 0"
            bold
          >
          </u--text>
        </div>
        <!-- 现场处置人 -->
        <u-form-item
          label="现场处置人："
          ref="item1"
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.czr"
            disabled
            border="none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
        </u-form-item>
        <!-- 参与人员 -->
        <u-form-item
          label="参与人员："
          prop="dissensionResponsibleUserList"
          ref="item1"
          labelWidth="120"
          borderBottom
        >
          <u-icon
            @click="peopleClick('2', model1.dissensionResponsibleUserList)"
            slot="right"
            name="plus-circle"
            size="22"
            color="#1658C8"
          ></u-icon>
        </u-form-item>
        <!-- 处置情况 -->
        <u-form-item
          label="处置情况："
          prop="qingkuang"
          ref="item1"
          required
          labelWidth="90"
          borderBottom
        >
          <u--input
            v-model="model1.qingkuang"
            placeholder="请填写"
            border="none"
            disabledColor="#ffffff"
            color="#c0c4cc"
          ></u--input>
        </u-form-item>
        <!-- 处置结果 -->
        <u-form-item
          label="处置结果 ："
          @click="zhendiShow2 = true"
          ref="item1"
          prop="abnormalCode"
          required
          labelWidth="90"
          borderBottom
        >
          <u--input
            fontSize="18"
            :color="colorDisabled"
            v-model="model1.abnormalCode"
            disabled
            disabledColor="#ffffff"
            color="#c0c4cc"
            placeholder="请选择处置结果"
            border="none"
          ></u--input>
          <u-icon slot="right" name="arrow-right"></u-icon>
        </u-form-item>
        <div v-if="model1.abnormalCode !='已解决'">
          <!-- 主要责任人 -->
          <u-form-item
            @click="peopleClick('1', model1.respPersons)"
            label="主要责任人："
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
              color="#c0c4cc"
              placeholder="请选择"
              border="none"
              style="pointer-events: none"
            ></u--input>
            <u-icon slot="right" name="arrow-right"></u-icon>
            <!-- <div
            style="display: flex; justify-content: flex-end"
            v-if="!model1.respPerson.uid"
          >
            <u-icon
              @click="peopleClick('1', model1.respPerson.uid)"
              slot="right"
              name="plus-circle"
              size="22"
              color="#1658C8"
            ></u-icon>
          </div>
          <div style="display: flex; justify-content: flex-end" v-else>
            <u-icon
              @click="peopleClick('1', model1.respPerson.uid)"
              slot="right"
              name="edit-pen"
              size="22"
              color="#1658C8"
            ></u-icon>
          </div> -->
          </u-form-item>
          <!-- <ul
          class="two_fs"
          style="border-bottom: 1px solid #eee; padding-bottom: 5px"
        >
          <li style="margin-bottom: 8px; display: flex; align-items: center">
            <div style="min-width: 23%">{{ model1.respPerson.realname }}</div>
            <div style="min-width: 40%">
              {{ model1.respPerson.phone || "" }}
            </div>
            <div>{{ model1.respPerson.unit || "" }}</div>
          </li>
        </ul> -->
          <!-- 负责人员 -->
          <u-form-item
            label="负责人员："
            prop="dissensionResponsibleUserList"
            required
            ref="item1"
            labelWidth="120"
          >
            <!-- <u-icon
          v-if="model1.dissensionResponsibleUserList.length<=0"
            @click="peopleClick('2',model1.dissensionResponsibleUserList)"
            slot="right"
            name="plus-circle"
            size="22"
            color="#1658C8"
          ></u-icon>
          <u-icon
          v-else
            @click="peopleClick('2',model1.dissensionResponsibleUserList)"
            slot="right"
            name="edit-pen"
            size="22"
            color="#1658C8"
          ></u-icon> -->
            <!-- <u-icon
            @click="clickAdd"
            slot="right"
            name="plus-circle"
            size="22"
            color="#1658C8"
          ></u-icon> -->
            <u-icon
              @click="peopleClick('2', model1.dissensionResponsibleUserList)"
              slot="right"
              name="plus-circle"
              size="22"
              color="#1658C8"
            ></u-icon>
          </u-form-item>
          <!-- <ul class="two_fs">
          <li
            v-for="item in model1.dissensionResponsibleUserList"
            :key="item.uid"
            style="margin-bottom: 8px; display: flex; align-items: center"
          >
            <div style="min-width: 23%">{{ item.realname }}</div>
            <div style="min-width: 40%">{{ item.phone }}</div>
            <div>{{ item.unit }}</div>
          </li>
        </ul> -->
          <div
            class="info two_fs"
            style="
              display: flex;
              justify-content: space-between;
              padding: 15px 0;
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
                <!-- 单位 -->
                <u-form-item
                  label="单位："
                  prop="unit"
                  ref="item1"
                  labelWidth="90"
                  borderBottom
                >
                  <u--input
                    v-model="model1.unit"
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
                    v-model="model1.remarks"
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
        </div>
         <!-- 上传照片 -->
         <u-form-item
          label="上传照片："
          prop="dataFiles"
          required
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
      </div>
    </u--form>
    <!-- 下一步 -->
    <view class="pitchOn2">
      <button
        style="
          background-color: #1658c8;
          width: 45%;
          height: 45px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 10px;
        "
        @click="submitAddWork"
      >
        去分派
      </button>
      <button
        style="
          background-color: #f9ac65;
          width: 45%;
          height: 45px;
          line-height: 45px;
          color: rgb(255, 255, 255);
          font-size: 20px;
          border-radius: 10px;
        "
        @click="submitAddWork"
      >
        部门转办
      </button>
    </view>
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
    <!-- 处置结果 -->
    <u-picker
      :show="zhendiShow2"
      :columns="zhendiList2"
      keyName="label"
      closeOnClickOverlay
      @close="zhendiShow2 = false"
      @confirm="zhendiSelect2"
      @cancel="zhendiShow2 = false"
    ></u-picker>
    <!-- 责任单位 -->
    <ba-tree-picker
      title="请选择责任单位"
      ref="treePicker2"
      :multiple="MultiSelect2"
      @select-change="selectChange2"
      @cancel="treePickerShow2 = false"
      :localdata="danweiList"
      valueKey="id"
      textKey="name"
      childrenKey="children"
      :checked="wcCodeChecked2"
      id="baTreePicker"
    />
    <!-- 选择人员信息 -->
    <peoplePicker
      :show="peopleShow"
      :type="peopleType"
      popupTitle="选择人员"
      :name="peopleName"
      :dataLists="peopleList"
      placeholder="按姓名/单位查询"
      @cancel="peopleShow = false"
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
// import baTreePicker from "@/components/ba-tree-picker/ba-tree-picker.vue";
import { getImgUrl, mobileStr, recursion } from "@/common/utils.js";
export default {
  components: {
    xuanze,
    peoplePicker,
    navigation,
    // baTreePicker
  },
  data() {
    return {
      userInfo: {},
      //表单弹窗
      popupShow: false,
      //图片
      fileList1: [],
      action: this.$mConfig.baseUrl + this.$apis.upload,

      // 提交的参数
      model2: {},
      // 表单数据
      model1: {
        orgCode: "", //行政区划id
        respUnitId: "", //责任单位id
        category_txt: "", //纠纷类型文本
        dataFiles:[],
        respPerson: {
          uid: "",
          realname: "",
          unit: "",
          phone: "",
        }, //主要责任人信息
        respPersons: "", //主要责任人id
        s1: "", //主要责任人姓名
        dissensionResponsibleUserList: [], //负责人
        realname: "", //人员姓名
        phone: "",
        unit: "", //单位
        remarks: "", //备注
      },
      dictModel: [], //纠纷类型字典
      //行政区划
      currentIcon: require("@/static/index/jt_down.png"),
      defaultIcon: require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [], //区域数据源
      districtName: "", //所属片区值
      //处置结果
      zhendiShow2: false,
      zhendiList2: [
        [
          {
            label: "已解决",
            id: 1,
            // ...
          },
          {
            label: "未解决",
            id: 2,
          },
          {
            label: "无法调解",
            id: 3,
          },
        ],
      ],
      //责任单位
      danweiList: [],
      treePickerShow2: false,
      MultiSelect2: false,
      wcCodeChecked2: "",
      respUnit: "", //责任单位值
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
      //多选负责人员
      fuzeList: [],
      //多选负责人员下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      // 表单校验
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
          message: "请选择责任人员",
          trigger: ["blur", "change"],
        },
      },
    };
  },
  onLoad(e) {
    this.userInfo = this.$store.state.userInfo || {};
    // this.model1.orgCode = localStorage.getItem("userCode") || "";
    this.getTypeData();
    this.model2 = JSON.parse(e.obj) || {};
    this.model1.s1 = this.model2.s1 || "";
    this.model1.respPersons = this.model2.respPersons;
    console.log(this.model2, "传的表单");
    wx.getStorage({
      key: "userCode",
      success: (res) => {
        console.log("getStorage==userCode===>", res.data);
        this.model1.orgCode = res.data;
      },
    });
    this.getProjectList(e.uid); //查询行政区划/责任单位
    this.getpeopleType2(this.current2); //查询主要人员
    this.getpeopleType(this.current); //查询负责人员
    console.log("ee", this.model1, e);
    if (e.id) {
    }

    this.userInfo = store.state.userInfo;
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    recursion,
    //处置结果
    zhendiSelect2(e) {
      console.log(e);
      this.model1.abnormalCode = e.value[0].label;
      this.zhendiShow2 = false;
    },
    //点击添加人员
    clickAdd() {
      this.peopleShow = false; //关闭多选人员
      this.popupShow = true;
      this.model1.realname = "";
      this.model1.phone = "";
      this.model1.unit = "";
      this.model1.remarks = "";
    },
    addPersonnel() {
      var reg =
        /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
      if (!this.model1.realname) {
        return uni.$u.toast("请输入姓名");
      } else if (!this.model1.phone) {
        return uni.$u.toast("请输入手机号");
      } else if (!reg.test(this.model1.phone)) {
        return uni.$u.toast("请输入正确的手机号");
      } else {
        this.popupShow = false;
        let dataForm = {
          realname: this.model1.realname,
          phone: this.model1.phone,
          unit: this.model1.unit,
          remarks: this.model1.remarks,
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
    //查询行政区划/责任单位
    getProjectList() {
      this.$apis.departList({ uid: this.userInfo.id }).then((res) => {
        console.log("查询所属片区", res);
        if (res.result != null) {
          this.jiedaoList = res.result;
          console.log(this.districtName, this.model1.orgCode, "所属片区名称");
          this.districtName = this.recursion(
            this.jiedaoList,
            this.model1.orgCode
          ).name;
        } else {
          this.jiedaoList = [];
        }
      });
    },
    //点击选择区域
    echoDefault(id) {
      console.log(id);
      const selectIds = [id];
      this.checkedTreeData(this.jiedaoList, selectIds);
      // this.multiple = true
      this.$refs.nextTreeRef.showTree = true;
    },
    //区域勾选
    changeVerify(arr) {
      // 注意：返回非空字符串会阻止原有行为，并提示返回的字符串
      // 如果函数体不做return返回值，即验证通过，控件正常处理业务
      console.log("已选择的数据", arr);
      if (arr && arr.length > 4) {
        return "最多可以选择4个节点";
      }
    },
    //选择区域勾选/确定
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
      // 注意 vue2当数据深嵌套时，如果没有在treeData里面初始化checked属性，那在改变数据的时候直接将checked属性赋值为true，这时候ui界面有可能不会更新，
      // 这时候建议使用this.$set去更新checked属性值，或者在初始化this.jiedaoList的时候初始化checked属性
      (treeData || []).map((item) => {
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
    // 查询纠纷类型字典
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        console.log("查询纠纷类型字典===>", res);
        this.dictModel = res;
        this.model1.category_txt = this.getDict(this.model2.category);
      });
    },
    // 处理返回字典文本
    getDict(value) {
      let label = "";
      console.log(this.dictModel, value, "3333333");
      this.dictModel.forEach((itm) => {
        console.log(itm.value == value, itm.value, value, "label222");
        if (itm.value == value) {
          label = itm.text;
          console.log(label, "label111");
        }
      });
      return label;
    },
    //下拉加载
    scrolltolower(type) {
      if (type == "checkbox") {
        console.log("多选负责人员下拉加载", type, this.current);
        this.current = ++this.current;
        console.log("aaa", this.pages, this.current);
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
        console.log("aaa", this.pages2, this.current2);
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
        if (val.length > 0) {
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
        this.$refs.uForm.validate("respPersons");
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
        this.$refs.uForm.validate("respPersons");
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
      // this.$apis.participants().then((res) => {
      //   console.log('查询主要人员', res);
      //   if (res.result !=null) {
      //     this.zhuyaoList = res.result
      //   }
      // })
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
          // this.fuzeList = res.result.records;F
        }
      });
    },
    // 删除图片
    deletePic(e) {
      console.log("删除图片", e);
      console.log("删除前的图片", this.fileList1, this.model1.dataFiles);
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
    //完成分派
    submitAddWork() {
      // uni.navigateTo({
      //   url: "/pages/contradiction/allot",
      // });
      this.model2.respPersons = this.model1.respPersons;

      let newArr = this.model1.dissensionResponsibleUserList.filter(
        (item, index) => {
          return delete item.uid; // 注意：打印一下delete item.home返回的是true
        }
      );
      this.model2.dissensionResponsibleUserList = newArr;
      // this.model2.adminUnit = this.model1.orgCode;
      console.log(this.model2, "提交的参数");
      this.$refs.uForm
        .validate()
        .then((r) => {
          console.log("校验通过", this.model2);
          this.$apis.dissensionAssign(this.model2).then((res) => {
            console.log("---添加-->>>>", res, this.model2);
            if (res.success) {
              uni.showToast({
                title: res.message,
                icon: "success",
                duration: 1000,
                success() {
                  setTimeout(() => {
                    wx.navigateBack({
                      delta: 1,
                    });
                  }, 1000);
                },
              });
            } else {
              console.log("失败");
              uni.showToast({
                title: res.message,
                icon: "none",
                duration: 1000,
              });
            }
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
.contBox {
  background: white;
  border-radius: 8px;
  margin-top: 10px;
  padding: 0px 15px 10px;
  .jifen {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #eaebec;
  }
  .jifen /deep/ .u-icon--right {
    flex-direction: row;
    align-items: center;
    width: 5px;
    height: 19px;
    background: #1658c8;
    margin-right: 5px;
  }
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
.pitchOn2 {
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
  z-index: 9;
}
// 文本域样式
.contBox /deep/ .u-textarea {
  padding: 0px !important;
}
// .contBox /deep/ .u-textarea__field {
//   background: #f5f7fa !important;
// }
</style>