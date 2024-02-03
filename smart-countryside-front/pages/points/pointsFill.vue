<template>
  <div
    class="pointsView"
    :style="
      huzhuShow || typeShow
        ? { overflow: 'hidden', paddingTop: '0px' }
        : { paddingTop: '0px' }
    "
  >
    <!-- 导航栏 -->
    <!-- <navigation title="积分填报"></navigation> -->
    <!-- 头部背景 -->
    <div class="heard">
      <!-- 返回 -->
      <!-- <view
        style="
          position: absolute;
          right: 0%;
          top: 0%;
          padding: 6px 10px;
          font-size: 14px;
          color: #fff;
          font-weight: bold;
          text-decoration: underline;
          z-index: 9;
        "
        @click="gobackClick"
        >返回上一页</view
      > -->
      <image
        style="width: 100%; height: 170px"
        class="loginBackground"
        :src="bjImg"
        mode=""
      ></image>
    </div>

    <div class="box">
      <div class="contBox">
        <div class="jifen1">
          <div class="jifen">
            <u--text
              prefixIcon=" "
              iconStyle="font-size: 19px"
              text="积分填报"
              size="18"
              bold
              margin="10px 0"
            >
            </u--text>
          </div>
          <div style="" @click="goLookList">
            <u-icon
              name="clock"
              color="#1658C8"
              label="积分记录"
              labelColor="#1658C8"
              size="18"
              labelSize="16"
            ></u-icon>
          </div>
        </div>
        <!-- 表单 -->
        <div class="content" style="padding: 0px 15px 10px">
          <u--form
            labelPosition="left"
            :model="model1"
            :rules="rules"
            ref="uForm"
          >
            <!-- <u--text
              color="#1658C8"
              size="20"
              bold
              lineHeight="30"
              text="清湖村村民积分制表单收集"
            ></u--text> -->

            <!-- 所属片区 -->
            <u-form-item
              label="所属片区"
              prop="orgCode"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
              @click="echoDefault(model1.orgCode)"
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
            <!-- 户主-->
            <u-form-item
              label="户主"
              prop="realname"
              required
              @click="huzhuClick(model1.realname, model1.familyNo)"
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.realname"
                disabled
                disabledColor="#ffffff"
                placeholder="请选择"
                border="none"
                style="pointer-events: none"
              ></u--input>
              <u-icon slot="right" name="arrow-right"></u-icon>
            </u-form-item>
            <!-- 户编号-->
            <u-form-item
              label="户编号"
              prop="familyNo"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.familyNo"
                disabled
                disabledColor="#ffffff"
                placeholder="请填写户编号"
                border="none"
              ></u--input>
            </u-form-item>
            <!-- 手机号-->
            <!-- <u-form-item
              label="手机号"
              prop="phone"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.phone"
                disabledColor="#ffffff"
                placeholder="请填写手机号"
                border="none"
              ></u--input>
            </u-form-item> -->
            <!-- 积分类型-->
            <u-form-item
              label="积分类型"
              prop="category"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <div class="pointsTabs" style="display: flex">
                <div
                  :class="pointsTypeShow ? 'pointsTabs_adtive' : ''"
                  @click="jifenTypeClick('1')"
                  class="pointsTabs_item u-input__content__field-wrapper__field"
                >
                  <div style="height: 100%; line-height: 30px">加分</div>
                  <div
                    v-if="pointsTypeShow"
                    style="position: absolute; right: -2px; bottom: -2px"
                  >
                    <u-icon
                      color="#1658C8"
                      name="checkmark-circle-fill"
                    ></u-icon>
                  </div>
                </div>
                <div
                  :class="pointsTypeShow ? '' : 'pointsTabs_adtive'"
                  @click="jifenTypeClick('2')"
                  class="pointsTabs_item u-input__content__field-wrapper__field"
                >
                  <div style="height: 100%; line-height: 30px">减分</div>
                  <div
                    v-if="!pointsTypeShow"
                    style="position: absolute; right: -2px; bottom: -2px"
                  >
                    <u-icon
                      color="#1658C8"
                      name="checkmark-circle-fill"
                    ></u-icon>
                  </div>
                </div>
              </div>
            </u-form-item>
            <!-- 积分项目-->
            <u-form-item
              label="积分项目"
              prop="ckName"
              required
              @click="typeClick(model1.ckName, model1.ckId)"
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <div class="fenleiBox" style="width: 100%; display: flex">
                <u--textarea
                  v-model="model1.ckName"
                  disabled
                  placeholder="请选择"
                  border="none"
                  style="pointer-events: none"
                  autoHeight
                ></u--textarea>
                <u-icon slot="right" name="arrow-right"></u-icon>
              </div>
            </u-form-item>
            <!-- 积分项目说明 -->
            <u-form-item label="积分项目说明" ref="item1" labelWidth="90">
            </u-form-item>
            <div class="textarea">
              <u--textarea
                autoHeight
                v-model="model1.alias"
                disabledColor="#ffffff"
                placeholder="系统自动显示，无需填写"
                maxlength="-1"
                disabled
              ></u--textarea>
            </div>

            <!-- 量化指标 -->
            <u-form-item label="量化指标" ref="item1" labelWidth="90">
            </u-form-item>
            <div class="textarea">
              <u--textarea
                autoHeight
                v-model="model1.remark"
                disabledColor="#ffffff"
                placeholder="系统自动显示，无需填写"
                maxlength="-1"
                disabled
              ></u--textarea>
            </div>

            <!-- 事项详情-->
            <u-form-item label="事项详情" ref="item1" labelWidth="90">
            </u-form-item>
            <div class="textarea">
              <u--textarea
                cursor-spacing="50"
                height="100"
                v-model="model1.reason"
                disabledColor="#ffffff"
                placeholder="如有特殊积分项，请输入内容"
                maxlength="-1"
              ></u--textarea>
            </div>
            <!-- 加分分值-->
            <u-form-item
              :label="pointsTypeShow ? '加分分值' : '减分分值'"
              prop="score"
              required
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.score"
                disabledColor="#ffffff"
                placeholder="请填写加分分值"
                type="number"
                :disabled="false"
              ></u--input>
            </u-form-item>
            <!-- 基础分清零 -->
            <!-- <u-form-item
                label="基础分清零"
                prop="s1"
                required
                ref="item1"
                labelWidth="110"
              >
                <u-radio-group
                  v-model="radiovalue1"
                  placement="row"
                  @change="groupChange1"
                  iconPlacement="left"
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
              </u-form-item> -->
            <!-- 当年积分清零(总积分) -->
            <!-- <u-form-item
                label="当年积分清零(总积分)"
                prop="s1"
                required
                ref="item1"
                labelWidth="110"
              >
                <u-radio-group
                  v-model="radiovalue1"
                  placement="row"
                  @change="groupChange1"
                  iconPlacement="left"
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
              </u-form-item> -->

            <!-- 登记年份 -->
            <u-form-item
              label="登记年份"
              prop="year"
              ref="item1"
              labelWidth="90"
              borderBottom
            >
              <u--input
                v-model="model1.year"
                disabled
                disabledColor="#ffffff"
                placeholder="请选择"
                border="none"
                style="pointer-events: none; width: 30px"
              ></u--input>
              <!-- <u-icon slot="right" name="arrow-right"></u-icon> -->
            </u-form-item>
            <!-- 上传附件 -->
            <u-form-item label="上传附件" ref="item1" labelWidth="90">
            </u-form-item>
            <div class="Img">
              <u-upload
                :fileList="fileList1"
                @afterRead="afterRead"
                @delete="deletePic"
                name="1"
                multiple
                :maxCount="10"
                :previewFullImage="true"
              ></u-upload>
            </div>
          </u--form>
          <!-- 提交 -->
          <view class="pitchOn">
            <button
              :disabled="isSubmitting"
              style="
                background-color: #1658c8;
                width: 100%;
                height: 45px;
                line-height: 45px;
                color: rgb(255, 255, 255);
                font-size: 20px;
                border-radius: 25px;
                margin: 20px 0;
              "
              @click="submitAddWork"
            >
              提交
            </button>
          </view>
        </div>
      </div>
    </div>
    <!-- 所属片区 -->
    <!-- <ba-tree-picker
      title="请选择所属片区"
      ref="treePicker1"
      :multiple="MultiSelect1"
      @select-change="selectChange1"
      @cancel="treePickerShow1 = false"
      :localdata="jiedaoList"
      valueKey="code"
      textKey="name"
      childrenKey="children"
      :checked="wcCodeChecked1"
      id="baTreePicker"
    /> -->
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
    <!-- 户主选择 -->
    <qianziyu-select
      :show="huzhuShow"
      type="radio"
      popupTitle="选择户主"
      name="cworkStationName"
      :dataLists="huzhuList"
      placeholder="按户主/户编号查询"
      @cancel="huzhuShow = false"
      @search="huzhuSearch"
      @submit="huzhuConfirm"
      @scrolltolower="scrolltolower"
      ref="huzhuRef"
    ></qianziyu-select>
    <!-- 积分项目 -->
    <typeSelect
      :show="typeShow"
      type="radio"
      popupTitle="选择积分项目"
      name="cworkStationName"
      :dataLists="typeList"
      placeholder="按类型查询"
      @cancel="typeCancel"
      @search="typeSearch"
      @submit="typeConfirm"
      :showSearch="false"
      ref="typeSelectRef"
    ></typeSelect>
    <!-- 登记时间选择器 -->
    <!--    <u-datetime-picker
      v-model="model1.year"
      :show="beginTimeShow1"
      mode="year"
      :minDate="Number(new Date())"
      :maxDate="Number(new Date())"
      @cancel="beginTimeShow1 = false"
      @confirm="beginTimeConfirm1"
      @close="beginTimeShow1 = false"
      closeOnClickOverlay
    ></u-datetime-picker>-->
  </div>
</template>
<script>
import pointsDetail from "./modules/pointsDetail.vue";
import pointsRecode from "./modules/pointsRecode.vue";
import navigation from "../../components/navigation/navigation";
import typeSelect from "./modules/typeSelect.vue";
import "@/static/style/style.css";
// import baTreePicker from "@/components/ba-tree-picker/ba-tree-picker.vue";
// import { timestampToTime } from "@/common/utils.js";
import store from "@/store/index.js";
import { getImgUrl, recursion } from "@/common/utils.js";
export default {
  components: {
    pointsDetail,
    pointsRecode,
    navigation,
    typeSelect,
    // baTreePicker,
  },
  data() {
    return {
      userInfo: {},
      bjImg: require("@/static/index/jftb_bj.jpg"),
      isSubmitting: false, //防止频繁点击
      //上传附件
      action: this.$mConfig.baseUrl + this.$apis.upload,
      fileList1: [],
      urls: [],
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      model1: {
        uid: "", //户主id
        orgCode: "", //所属行政区域
        realname: "", //户主姓名
        familyNo: "", //户编号
        // phone: "", //手机号码
        category: "1", //积分类型
        ckName: "", //积分项目
        ckId: "", //积分项目id
        alias: "", //积分项目说明
        remark: "", //量化指标
        reason: "", //事项详情
        score: "0", //加分分值
        // jcjf:0,//基础积分
        // zjf:0,//总积分
        year: "", //登记年份,
        workImgs: "", // 拍照
      },
      //所属片区
      currentIcon: require("@/static/index/jt_down.png"),
      defaultIcon: require("@/static/index/jt_right.png"),
      multiple: false, //是否多选
      selectParent: true, //是否可以选父级
      checkStrictly: false,
      jiedaoList: [], //区域数据源
      districtName: "", //所属片区值

      //户主选择
      huzhuList: [],
      huzhuShow: false,
      //积分项目选择
      typeList: [],
      typeShow: false,
      //积分类型
      pointsTypeShow: true,
      pointsType: [
        { id: "1", title: "加分", show: true, icon: "checkmark-circle-fill" },
        { id: "2", title: "减分", show: false, icon: "checkmark-circle-fill" },
      ],
      // 基础分清零
      radiolist1: [
        {
          id: 0,
          name: "否",
          disabled: false,
        },
        {
          id: 1,
          name: "是",
          disabled: false,
        },
      ],
      radiovalue1: 0,
      // 当年积分清零(总积分)
      radiolist2: [
        {
          id: 0,
          name: "否",
          disabled: false,
        },
        {
          id: 1,
          name: "是",
          disabled: false,
        },
      ],
      //登记时间
      beginTimeShow1: false,
      radiovalue2: 0,
      rules: {
        orgCode: {
          type: "string",
          required: true,
          message: "请选择所属片区",
          trigger: ["blur", "change"],
        },
        realname: {
          type: "string",
          required: true,
          message: "请选择户主",
          trigger: ["blur", "change"],
        },
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
        category: {
          type: "string",
          required: true,
          message: "请选择积分类型",
          trigger: ["blur", "change"],
        },
        ckName: {
          type: "string",
          required: true,
          message: "请选择积分项目",
          trigger: ["blur", "change"],
        },
        score: {
          type: "string",
          required: true,
          message: "请填写加分分值",
          trigger: ["blur", "change"],
        },
        remark: {
          type: "string",
          required: true,
          message: "请填写加分事项",
          trigger: ["blur", "change"],
        },
        /*year: {
          type: "number",
          required: true,
          message: "请选择登记年份",
          trigger: ["blur", "change"],
        },*/
      },
    };
  },
  created() {
    this.userInfo = this.$store.state.userInfo || {};
    wx.getStorage({
      key: "userCode",
      success: (res) => {
        console.log("getStorage==userCode===>", res.data);
        this.model1.orgCode = res.data;
      },
    });
    // this.model1.orgCode = localStorage.getItem("userCode") || "";

    this.gethuzhuType(this.current); //查询户主
    this.getType(); //积分项目
    this.getProjectList(); //查询区域
    this.model1.year = this.handleCurrDate() + "年";
  },
  onReady() {
    //如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    // 返回
    gobackClick() {
      uni.redirectTo({
        url: "/pages/index/index",
      });
    },
    // timestampToTime,
    //查看填报记录
    recursion,
    goLookList() {
      uni.navigateTo({
        url: "/pages/points/pointsFillList",
      });
    },
    //下拉加载
    scrolltolower() {
      console.log("下拉加载", this.current);
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
          this.gethuzhuType(this.current);
        }, 100);
      }
    },
    // 删除图片
    deletePic(event) {
      this.fileList1.splice(event.index, 1);
      this.urls.splice(event.index, 1);
    },
    // 新增图片
    async afterRead(event) {
      // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
      let lists = [].concat(event.file);
      console.log(event, "新增图");
      for (let i = 0; i < lists.length; i++) {
        const result = await this.uploadFilePromise(lists[i].url);
        const resultObj = JSON.parse(result);
        console.log("上传图片结果===>", resultObj);
        if (resultObj.success) {
          let url = this.$mConfig.assetsPath + resultObj.result.furl;
          this.urls.push(resultObj.result.furl);
          this.fileList1.push({
            url: url,
          });
          this.model1.workImgs = this.urls.join(",");
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
            // console.log("--res---", res);
            setTimeout(() => {
              resolve(res.data);
            }, 1000);
          },
        });
      });
    },
    //查询所属片区
    getProjectList() {
      this.$apis.departList({ uid: this.userInfo.id }).then((res) => {
        console.log("查询所属片区", res);
        if (res.result != null) {
          this.jiedaoList = res.result;
          this.districtName = this.recursion(
            this.jiedaoList,
            this.model1.orgCode
          ).name;
        } else {
          this.jiedaoList = [];
        }
      });
    },
    //所属片区
    // jiedaobanClick() {
    //   this.$refs.treePicker1._show();
    //   this.treePickerShow1 = true;
    // },
    // selectChange1(selectedList, selectedNames) {
    //   console.log(selectedList, selectedNames, "选择行政区划");
    //   this.model1.orgCode = selectedList[0];
    //   console.log("所属片区", this.model1.orgCode);
    //   this.districtName = selectedNames;
    //   this.treePickerShow1 = false;
    // },
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
      this.gethuzhuType(1);
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
    //选择积分类型
    clickTabs(item) {
      if (item.id) {
        item.show = !item.show;
      } else {
        item.show = !item.show;
      }
    },
    //户主选择
    huzhuClick(name, code) {
      if (code) {
        this.$refs.huzhuRef.biaoshi = 1;
      } else {
        this.$refs.huzhuRef.biaoshi = 0;
      }
      if (code) {
        let aa = code.split(",");
        for (var i = 0; i < this.huzhuList.length; i++) {
          if (aa.indexOf(String(this.huzhuList[i].familyNo)) >= 0) {
            this.huzhuList[i].checked = true;
          } else {
            this.huzhuList[i].checked = false;
          }
        }
        this.huzhuShow = true;
      } else {
        this.huzhuShow = true;
      }
    },
    huzhuConfirm(arr) {
      console.log("---户主选择--->", arr);
      var name = "";
      var code = "";
      var phone = "";
      for (var i = 0; i < arr.length; i++) {
        name += arr[i].realname + ",";
        code += arr[i].familyNo + ",";
        if (arr[i].phone) {
          phone += arr[i].phone + ",";
        }
      }
      //去掉最后一个逗号(如果不需要去掉，就不用写)
      if (name.length > 0) {
        name = name.substr(0, name.length - 1);
        code = code.substr(0, code.length - 1);
        if (phone.length > 0) {
          phone = phone.substr(0, phone.length - 1);
        }
      }
      this.model1.realname = name;
      this.model1.familyNo = code;
      this.model1.phone = phone;
      this.model1.uid = arr[0].username;
      this.huzhuShow = false;
    },
    huzhuSearch(val) {
      console.log(val);
      if (!val) {
        this.gethuzhuType(this.current); //查询纠纷类型
      } else {
        let searchList = this.huzhuList.filter(
          (item) =>
            item.realname.indexOf(val) >= 0 ||
            String(item.familyNo).indexOf(val) >= 0
        );
        this.huzhuList = searchList;
      }
    },
    //查询户主
    gethuzhuType(current) {
      this.$apis
        .roster({
          orgCode: this.model1.orgCode,
          pageNo: current,
          pageSize: this.pageSize,
        })
        .then((res) => {
          console.log("====查询户主==", res);
          if (res.success) {
            this.current = res.result.current;
            this.pages = res.result.pages;
            this.pageSize = res.result.size;
            res.result.records.forEach((item) => {
              item.checked = false;
              if (item.avatar == null) {
                item.img =
                  "1http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg";
              }
            });
            this.huzhuList = this.huzhuList.concat(res.result.records);
            this.flag = true;
          }
        });
    },
    //选择积分类型
    jifenTypeClick(num) {
      this.model1.ckName = "";
      this.model1.ckId = "";
      this.model1.alias = "";
      this.model1.remark = "";
      this.model1.score = "0";
      if (num == "2") {
        this.pointsTypeShow = false;
        this.model1.category = num;
        this.getType();
      } else {
        this.pointsTypeShow = true;
        this.model1.category = num;
        this.getType();
      }
    },
    //积分项目选择
    typeClick(name, code) {
      uni.setNavigationBarTitle({
        title: "积分项目",
      });
      if (code) {
        this.$refs.typeSelectRef.biaoshi = 1;
      } else {
        this.$refs.typeSelectRef.biaoshi = 0;
      }
      if (code) {
        let arr = code.split(",");
        for (var i = 0; i < this.typeList.length; i++) {
          if (arr.indexOf(String(this.typeList[i].chkId)) >= 0) {
            this.typeList[i].checked = true;
          } else {
            this.typeList[i].checked = false;
          }
        }
        this.typeShow = true;
      } else {
        this.typeShow = true;
      }
    },
    typeConfirm(arr) {
      console.log("分类=>", arr);
      var name = "";
      var ckId = "";
      var remark = "";
      var score = "";
      var alias = "";
      var content = "";
      for (var i = 0; i < arr.length; i++) {
        name += arr[i].name + ",";
        ckId += arr[i].chkId + ",";
        remark += arr[i].remark + ",";
        score += arr[i].score + ",";
        alias += arr[i].alias + ",";
        content += arr[i].content + ",";
      }
      //去掉最后一个逗号(如果不需要去掉，就不用写)
      if (name.length > 0) {
        name = name.substr(0, name.length - 1);
        ckId = ckId.substr(0, ckId.length - 1);
        remark = remark.substr(0, remark.length - 1);
        score = score.substr(0, score.length - 1);
        alias = alias.substr(0, alias.length - 1);
        content = content.substr(0, content.length - 1);
      }
      this.model1.ckName = name;
      this.model1.ckId = ckId;
      if (alias == "null") {
        this.model1.alias = "";
      } else {
        this.model1.alias = alias; //积分项目说明
      }
      if (remark == "null") {
        this.model1.remark = "";
      } else {
        this.model1.remark = remark; //量化指标
      }
      // if (content == "null") {
      //   this.model1.reason = "";
      // } else {
      //   this.model1.reason = content; //积分项目说明
      // }
      this.model1.score = score;
      this.typeShow = false;
      uni.setNavigationBarTitle({
        title: "积分填报",
      });
    },
    typeCancel() {
      this.typeShow = false;
      uni.setNavigationBarTitle({
        title: "积分填报",
      });
    },
    typeSearch(val) {
      if (!val) {
        this.getType(); //查询纠纷类型
      } else {
        let searchList = this.typeList.filter(
          (item) =>
            item.name.indexOf(val) >= 0 || String(item.remark).indexOf(val) >= 0
        );
        this.typeList = searchList;
      }
    },
    //查询积分项目
    getType() {
      this.$apis
        .integralItems({ category: this.model1.category })
        .then((res) => {
          console.log("====查询积分项目==", res);
          if (res.result.length > 0) {
            this.typeList = res.result;
          }
        });
    },
    //基础分清零单选框
    // groupChange1(n) {
    //   console.log("groupChange1", n);
    // },
    //当年积分清零(总积分)单选框
    // groupChange2(n) {
    //   console.log("groupChange2", n);
    // },
    //登记时间选择
    // async beginTimeConfirm1(value) {
    //   this.model1.year = await this.timestampToTime(value.value);
    //   console.log(value, this.model1.year);
    //   this.beginTimeShow1 = false;
    // },
    // timestampToTime(timestamp) {
    //   let date = new Date(timestamp);
    //   let Y = date.getFullYear();
    //   return Y;
    // },
    //获取当前年份
    handleCurrDate() {
      // 获取当前日期
      var date = new Date();
      // 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
      var nowDate = date.getFullYear();
      return nowDate;
    },
    //清空表单
    clearData() {
      this.model1 = {
        uid: "", //户主id
        orgCode: "", //所属行政区域
        realname: "", //户主姓名
        familyNo: "", //户编号
        // phone: "", //手机号码
        category: "1", //积分类型
        ckName: "", //积分项目
        ckId: "", //积分项目id
        score: "0", //加分分值
        remark: "", //加分事项
        // jcjf:0,//基础积分
        // zjf:0,//总积分
        // year: "", //登记年份,
        workImgs: "", // 拍照
      };
      this.fileList1 = [];
      this.urls = [];
      this.districtName = "";
      this.pointsTypeShow = true;
    },
    //提交
    submitAddWork() {
      this.$refs.uForm
        .validate()
        .then((r) => {
          console.log(r);
          // this.model1.uid = this.userInfo.id;
          this.isSubmitting = true;
          this.$apis.integralAdd(this.model1).then((res) => {
            console.log("---添加-->>>>", res);
            console.log("---表单model1-->>>>", this.model1);
            if (res.success) {
              uni.showToast({
                title: "提交成功！",
                icon: "success",
                duration: 1000,
              });
              setTimeout(() => {
                uni.navigateTo({
                  url: "/pages/points/pointsFillList",
                });
                this.clearData();
              }, 1500);
              //防止按钮频繁点击
              setTimeout(() => {
                this.isSubmitting = false;
              }, 2000);
            } else {
              uni.showToast({
                title: res.message,
                icon: "none",
                duration: 1000,
              });
              setTimeout(() => {
                this.isSubmitting = false;
              }, 2000);
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
<style lang="scss" scoped>
.pointsView {
  position: relative;
  height: 100%;
  //头部内容
  .heard {
    position: relative;
    height: 170px;
  }
  .box {
    position: relative;
    // padding: 0px 10px;
    .contBox {
      background: white;
      width: 100%;
      // position: absolute;
      // transform: translate(-50%);
      // top: -27px;
      // left: 50%;
      // border-radius: 10px;
    }
  }
}
.pointsTabs_item {
  border: 1px solid #b5b5b5;
  color: #b5b5b5;
  width: 60px;
  height: 30px;
  border-radius: 10px;
  text-align: center;
  position: relative;
  margin-right: 40px;
}
.pointsTabs_adtive {
  border: 1px solid #1658c8;
  color: #1658c8;
}
.fenleiBox /deep/ .u-textarea {
  padding: 0px;
}
//
.jifen1 {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 15px 0 8px;
}
.jifen /deep/ .u-icon--right {
  flex-direction: row;
  align-items: center;
  width: 5px;
  height: 19px;
  background: #1658c8;
  margin-right: 5px;
}
</style>
