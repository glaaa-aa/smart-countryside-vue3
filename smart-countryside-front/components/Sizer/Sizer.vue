<template>
  <div>
    <div class="Sizer" v-if="sizerShow">
      <div class="SizerContent">
        <div
          style="
            width: 100%;
            height: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom: 1px solid #f7f9fa;
          "
        >
          {{ sizerTitle }}
        </div>
        <div class="content">
          <div class="contentItem" v-for="(item, index) in Data" :key="index">
            <div class="title">{{ item.title }}</div>
            <div class="optionContent">
              <div
                class="option"
                v-if="item.type == 'ordinary'"
                :class="items._select ? 'optionAction' : ''"
                v-for="(items, indexs) in item.data"
                :key="indexs"
                @click="optionClick(index, indexs, item.key)"
              >
                {{ items.name }}
              </div>
            </div>
            <div class="radio" v-if="item.type == 'radio'">
              <u-radio-group
                v-model="item.value"
                placement="column"
                size="24"
                @change="groupChange"
              >
                <u-radio
                  :customStyle="{ marginBottom: '12px' }"
                  v-for="(itemd, indexd) in item.data"
                  :key="indexd"
                  :label="itemd.name"
                  :name="itemd.value"
                  labelSize="18"
                >
                </u-radio>
              </u-radio-group>
            </div>
            <div v-if="item.type == 'year'">
              <!-- 时间 -->
              <div class="year">
                <div class="optionTime" :class="startTime ? 'date' : ''">
                  <input
                    type="text"
                    v-model="startTime"
                    placeholder="请选择开始时间"
                    :disabled="true"
                    @click="startTimeClick"
                  />
                  <div
                    v-if="startTime"
                    style="width: 36px; display: flex; justify-content: center"
                    @click="emptyTime('startTime')"
                  >
                    <u-icon name="close-circle" size="24"></u-icon>
                  </div>
                </div>
                <div
                  class="optionTime"
                  style="padding: 0; background-color: #fff"
                >
                  ~
                </div>
                <div
                  class="optionTime"
                  :class="endTime ? 'date' : ''"
                  style="margin-right: 0"
                >
                  <input
                    type="text"
                    v-model="endTime"
                    placeholder="请选择截止时间"
                    :disabled="true"
                    @click="endTimeClick"
                  />
                  <div
                    v-if="endTime"
                    style="width: 36px; display: flex; justify-content: center"
                    @click="emptyTime('endTime')"
                  >
                    <u-icon name="close-circle" size="24"></u-icon>
                  </div>
                </div>
              </div>
            </div>
            <!-- 行政区选择 -->
            <div v-if="item.type == 'wcCode'">
              <div class="administrativeDivision">
                <div @click="administrativeDivision">
                  <u--textarea
                    autoHeight="true"
                    v-model="wcName"
                    maxlength="-1"
                    fontSize="18"
                    disabled
                    placeholder="请选择行政区划"
                    border="none"
                  ></u--textarea>
                </div>
                <div
                  v-if="wcName"
                  style="
                    position: absolute;
                    right: 0;
                    top: 50%;
                    transform: translate(0, -50%);
                    width: 50px;
                    height: 100%;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    z-index: 100;
                  "
                  @click.stop="clearWcCode"
                >
                  <u-icon name="close-circle" size="24"></u-icon>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="btn">
          <div class="btns cancel" @click="cancelClick">
            <div>取消</div>
          </div>
          <div style="width: 3%"></div>
          <div class="btns cancel" @click="resetClick">
            <div>重置</div>
          </div>
          <div style="width: 3%"></div>
          <div class="btns confirm" @click="selectClick">
            <div>确定</div>
          </div>
        </div>
      </div>
    </div>
    <u-datetime-picker
      v-if="dateType == 'startTime'"
      :show="dateShow"
      :minDate="minDate"
      :maxDate="maxDate"
      v-model="date"
      mode="date"
      @confirm="dateConfirm"
      @cancel="dateCancel"
    ></u-datetime-picker>
    <u-datetime-picker
      v-if="dateType == 'endTime'"
      :show="dateShow"
      :minDate="minDate"
      v-model="date"
      mode="date"
      @confirm="dateConfirm"
      :maxDate="maxDate"
      @cancel="dateCancel"
    ></u-datetime-picker>
    <ba-tree-picker
      ref="treePicker"
      :multiple="false"
      @select-change="selectChange"
      title="选择行政区"
      :localdata="listData"
      valueKey="id"
      textKey="name"
      childrenKey="children"
      id="baTreePicker"
    />
  </div>
</template>

<script>
import baTreePicker from "@/components/ba-tree-picker/ba-tree-picker.vue";
export default {
  name: "Sizer",
  props: {
    sizerShow: {
      type: Boolean,
      default: false,
    },
    list: {
      type: Array,
      default: [],
    },
    type: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      sizerTitle: "筛选",
      selectIndex: null,
      Data: [],
      radio: "",
      dateShow: false,
      endTime: "",
      startTime: "",
      dateType: "",
      minDate: 1672502400000,
      maxDate: Number(new Date()),
      dataValue: {},
      date: Number(new Date()),
      treeData: [],
      listData: [],
      wcCode: "",
      wcName: "",
      jurisdictions: "",
    };
  },
  onLoad() {},
  created() {
    this.init(this.list);
    let userInfo = this.$store.state.userInfo;
    console.log("userInfo==>", userInfo);
    let jurisdictions = userInfo.districtCode;
    this.jurisdictions = jurisdictions;
    this.getSysOrgList(jurisdictions);
  },
  mounted() {},
  watch: {
    list: {
      handler(newVal, oldVal) {
        this.list = newVal;
        this.init(newVal);
      },
      deep: true, // 深度监听父组件传过来对象变化
    },
  },
  methods: {
    treeDataFun(data) {
      let arr = [];
      data.forEach((item) => {
        arr.push({
          id: item.id,
          name: item.name,
          fullName: item.fullName,
          pid: item.pid,
          children: item.children,
        });
        if (item.children.length) {
          this.treeDataFun(item.children);
        }
        if (arr.length) {
          this.treeData = [...this.treeData, ...arr];
        }
      });
    },
    getSysOrgList(jurisdictions) {
      this.listData = [];
      this.treeData = [];
      this.$apis
        .departList({
          id: jurisdictions,
        })
        .then((res) => {
          console.log("行政区划size===>", res, jurisdictions);
          if (res.success) {
            let data = res.result || [];
            this.treeDataFun(data);
            let multi = jurisdictions.indexOf(",");
            if (multi != -1) {
              let arr = jurisdictions.split(",");
              let Data = [];
              arr.forEach((item) => {
                let datas = this.treeData.filter((items) => {
                  return items.id == item;
                });
                Data.push(datas[0]);
              });
              // if (this.wcCodeChecked) {
              //   Data.reverse();
              // }
              this.listData = Data;
            } else {
              let datas = this.treeData.filter((item) => {
                return item.id == jurisdictions;
              });
              // console.log("----------datas-----------------------",datas)

              this.listData = datas;
            }
          }
        });
    },
    administrativeDivision() {
      this.$refs.treePicker._show();
    },
    selectChange(selectedList, selectedNames) {
      console.log("-------------------", selectedList, selectedNames);
      this.wcCode = selectedList[0];
      this.wcName = selectedNames;
      // this.get(this.wcCode,this.weixStdate,this.weixEddate)
    },
    clearWcCode() {
      this.wcCode = "";
      this.wcName = "";
      this.selectComponent("#baTreePicker").$vm._initTree();
    },
    // 初始选中状态
    init(val) {
      val.forEach((item) => {
        item.data.forEach((items) => {
          items._select = false;
          if (item.value == items.value) {
            items._select = true;
          }
        });
      });
      this.Data = val;
    },
    optionClick(index, indexs, key) {
      console.log(index, indexs, key, "点击");
      this.Data.forEach((item) => {
        if (item.key == key) {
          item.data.forEach((items, indexd) => {
            if (indexd == indexs) {
              console.log("---->>>", indexd, indexs);
              if (item.value == items.value) {
                item.value = null;
              } else {
                item.value = items.value;
              }
            }
          });
        }
      });
    },
    startTimeClick() {
      this.dateType = "startTime";
      this.dateShow = true;
    },
    endTimeClick() {
      this.dateType = "endTime";
      this.dateShow = true;
    },
    dateCancel() {
      this.dateShow = false;
    },
    dateConfirm(value) {
      let date = this.timestampToTime(value.value);
      if (this.dateType == "startTime") {
        this.startTime = date;
      }
      if (this.dateType == "endTime") {
        // console.log("------时间------",this.startTime,date)
        let startTimes = new Date(this.startTime);
        let endTimes = new Date(date);
        let time = startTimes.getTime();
        let time2 = endTimes.getTime();
        // console.log("------时间------",time,time2)
        if (time > time2) {
          uni.showToast({
            title: "请选择开始时间后的日期",
            icon: "none",
            duration: 1000,
          });
          return;
        }
        this.endTime = date;
      }
      this.dateShow = false;
    },
    emptyTime(type) {
      if (type == "startTime") {
        this.startTime = "";
      }
      if (type == "endTime") {
        this.endTime = "";
      }
    },
    groupChange(name) {
      this.radio = name;
    },
    cancelClick() {
      this.$emit("cancel");
    },
    resetClick() {
      this.Data.forEach((item) => {
        item.value = "";
        if (item.type == "radio") {
          this.dataValue[item.key] = "";
        }
        item.data.forEach((items) => {
          this.dataValue[item.key] = "";
        });
      });
      this.radio = "";
      this.startTime = "";
      this.endTime = "";
      this.wcCode = "";
      this.wcName = "";
      this.selectComponent("#baTreePicker").$vm._initTree();
    },
    selectClick() {
      this.Data.forEach((item) => {
        item.data.forEach((items) => {
          if (items._select) {
            this.dataValue[item.key] = items.value;
          }
        });
      });
      if (this.radio) {
        this.Data.forEach((item) => {
          if (item.type == "radio") {
            this.dataValue[item.key] = this.radio;
          }
        });
      } else {
        this.Data.forEach((item) => {
          if (item.type == "radio") {
            this.dataValue[item.key] = "";
          }
        });
      }
      if (this.startTime) {
        this.dataValue.beginTime = this.startTime;
      } else {
        this.dataValue.beginTime = "";
      }
      if (this.endTime) {
        this.dataValue.endTime = this.endTime;
      } else {
        this.dataValue.endTime = "";
      }

      if (this.wcCode) {
        this.dataValue.wcCode = this.wcCode;
      } else {
        this.dataValue.wcCode = this.jurisdictions;
      }
      this.$emit("confirm", this.dataValue);
    },
    //将时间戳转换成日期格式
    timestampToTime(timestamp) {
      let date = new Date(timestamp);
      let Y = date.getFullYear() + "-";
      let M =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1) + "-"
          : date.getMonth() + 1 + "-";
      let D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      return Y + M + D;
    },
  },
};
</script>

<style lang="less" scoped>
.Sizer {
  width: 100vw;
  height: 100vh;
  z-index: 1000;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.65);
}

.SizerContent {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #fff;
  width: 100%;
  padding-bottom: 7px;
  border-radius: 10px 10px 0 0;
}

.content {
  width: 100%;
  padding: 10px 10px 0 20px;
  margin-bottom: 66px;
  max-height: 60vh;
  overflow: auto;
}

.contentItem {
  padding-bottom: 20px;
}

.title {
  font-weight: 700;
  padding-bottom: 10px;
  font-size: 20px;
}

.optionContent {
  display: flex;
  font-size: 16px;
  flex-wrap: wrap;
}

.option {
  // padding: 5px 12px;
  padding: 5px 10px 5px 10px;
  background-color: #f2f1f6;
  border-radius: 15px;
  display: inline-block;
  margin: 0 10px 10px 0;
  position: relative;
  font-size: 18px;
  min-width: 80px;
  text-align: center;
}
.optionTime {
  display: flex;
  align-items: center;
  padding: 5px 0px 5px 10px;
  margin: 0 8px 10px 0;
  background-color: #f2f1f6;
  border-radius: 15px;
  position: relative;
  font-size: 18px;
}

.optionAction {
  background-color: #1c9aee;
  color: #fff;
}

.btn {
  display: flex;
  width: 100%;
  position: fixed;
  bottom: 20px;
  padding-bottom: 6px;
  padding: 0 20px 10px 20px;
  font-size: 20px;
}
.btns {
  width: 31%;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
}
.cancel {
  background-color: #ebecee;
}

.confirm {
  // width: 31%;
  // height: 36px;
  background-color: #1c9aee;
  // display: flex;
  // justify-content: center;
  // align-items: center;
  border-radius: 6px;
  color: #fff;
}
.year {
  display: flex;
  align-items: center;
}
.date input {
  // width: 198rpx;
}
.content/deep/.u-radio__icon-wrap--circle {
  width: 28px !important;
  height: 28px !important;
}
.radio {
  padding-left: 5px;
}
.administrativeDivision {
  position: relative;
}
</style>
