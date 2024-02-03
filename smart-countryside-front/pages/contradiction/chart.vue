<template>
  <view
    class="statisticalData"
    style="padding-top: 10px"
    :style="dateShow || dateShow2 ? 'height:100vh;overflow: hidden;' : ''"
  >
  <!-- 查询区域 -->
    <view class="item">
      <view
        style="
          width: 100%;
          border-radius: 10px;
          box-shadow: 1px 0px 10px rgba(0, 0, 0, 0.4);
          padding: 10px;
          background-color: #d9edff;
          min-height: 110px;
        "
      >
        <view
          style="
            display: flex;
            align-items: center;
            width: 100%;
            padding: 3px 0;
          "
        >
          <view style="width: 30%">行政区划：</view>
          <view
            @click="btnClick"
            class="select"
            style="width: 74%; position: relative"
          >
            <u--textarea
              autoHeight="true"
              maxlength="-1"
              fontSize="18"
              disabled
              placeholder="请选择行政区划"
              border="none"
              v-model="wcName"
            ></u--textarea>
            <view
              style="
                position: absolute;
                right: 5px;
                top: 50%;
                transform: translate(0, -50%);
              "
            >
              <u-icon name="arrow-right" size="18"></u-icon>
            </view>
          </view>
        </view>
        <view
          style="
            display: flex;
            align-items: center;
            width: 100%;
            padding: 3px 0;
          "
        >
          <view style="width: 30%">隐患周期：</view>
          <view style="width: 34%" @click="dateShow = true">
            <u--input
              placeholder="请选择开始时间"
              v-model="weixStdate"
              disabled
              border="surround"
            ></u--input>
          </view>
          <view style="padding: 0 3px"> ~ </view>
          <view style="width: 34%" @click="dateShow2 = true">
            <u--input
              placeholder="请选择截止时间"
              v-model="weixEddate"
              disabled
              border="surround"
            ></u--input>
          </view>
        </view>
      </view>
    </view>
    <view class="item">
      <view class="title">隐患信息概览</view>
      <view class="chart1" v-if="chartData.series[0].data.length">
        <qiun-data-charts
          type="ring"
          :animation="false"
          :opts="opts"
          :chartData="chartData"
          :canvas2d="true"
        />
      </view>
      <view class="chart1" v-if="!chartData.series[0].data.length">
        <u-empty
          mode="data"
          icon="http://cdn.uviewui.com/uview/empty/data.png"
          text="暂无隐患信息概览"
        >
        </u-empty>
      </view>
    </view>
    <ba-tree-picker
      ref="treePicker"
      @cancel="treeCancel"
      :multiple="false"
      @select-change="selectChange"
      title="选择行政区"
      :localdata="listData"
      valueKey="id"
      textKey="name"
      childrenKey="children"
    />
    <u-datetime-picker
      :show="dateShow"
      v-model="stdateTime"
      :minDate="minDate"
      :maxDate="maxDate"
      mode="year-month"
      @confirm="dateConfirm"
      @cancel="dateShow = false"
    ></u-datetime-picker>
    <u-datetime-picker
      :show="dateShow2"
      v-model="eddateTime"
      :minDate="minDate"
      :maxDate="maxDate"
      mode="year-month"
      @confirm="dateConfirm2"
      @cancel="dateShow2 = false"
    ></u-datetime-picker>
  </view>
</template>

<script>
import baTreePicker from "@/components/ba-tree-picker/ba-tree-picker.vue";
import { formatDate } from "common/data";
export default {
  name: "statisticalData",
  components: {
    baTreePicker,
  },
  data() {
    return {
      queryDeductDs: null,
      treePickerShow: false,
      treeData: [],
      listData: [],
      wcCode: "",
      wcName: "",
      weixStdate: "",
      weixEddate: "",
      dateShow: false,
      dateShow2: false,
      minDate: 1672502400000,
      stdateTime: 0,
      eddateTime: 0,
      maxDate: Number(new Date()),
      // 隐患信息概览
      chartData: {
        series: [
          {
            data: [],
          },
        ],
      },
      opts: {
        rotate: false,
        rotateLock: false,
        color: [
          "#1890FF",
          "#91CB74",
          "#FAC858",
          "#EE6666",
          "#73C0DE",
          "#3CA272",
          "#FC8452",
          "#9A60B4",
          "#ea7ccc",
        ],
        padding: [5, 5, 5, 5],
        dataLabel: true,
        legend: {
          show: true,
          position: "right",
          lineHeight: 25,
        },
        title: {
          name: "",
          fontSize: 15,
          color: "#666666",
        },
        subtitle: {
          name: "",
          fontSize: 25,
          color: "#7cb5ec",
        },
        extra: {
          ring: {
            ringWidth: 60,
            activeOpacity: 0.5,
            activeRadius: 10,
            offsetAngle: 0,
            labelWidth: 15,
            border: false,
            borderWidth: 3,
            borderColor: "#FFFFFF",
          },
        },
      },
    };
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
    timestampToTime(timestamp) {
      let date = new Date(timestamp);
      let Y = date.getFullYear() + "-";
      let M =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      let D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      return Y + M;
    },
    getSysOrgList(jurisdictions) {
      this.listData = [];
      this.treeData = [];
      this.$apis
        .departList({
          id: jurisdictions,
        })
        .then((res) => {
          // console.log("行政区划===>", res);
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
              this.wcCode = Data[0].id;
              this.wcName = Data[0].fullName;
              this.listData = Data;
            } else {
              let datas = this.treeData.filter((item) => {
                return item.id == jurisdictions;
              });
              // console.log("----------datas-----------------------",datas)
              this.wcCode = datas[0].id;
              this.wcName = datas[0].fullName;
              this.listData = datas;
            }
          }
        });
    },
    btnClick() {
      // console.log("------")
      // this.$refs.selectAddress.show();
      // if (this.pageId == this.pageNum2) return;
      this.$refs.treePicker._show();
    },
    //行政区选择
    selectChange(selectedList, selectedNames) {
      // console.log("-------------------", selectedList, selectedNames)
      this.wcCode = selectedList[0];
      this.wcName = selectedNames;
      this.get(this.wcCode, this.weixStdate, this.weixEddate);
    },
    treeCancel() {},
    dateConfirm(value) {
      // if(tyep == 'weixStdate'){

      // 	this.weixStdate = value
      // }
      // if(tyep == 'weixEddate'){
      // 	this.weixEddate = value
      // }
      this.stdateTime = value.value;
      this.weixStdate = this.timestampToTime(value.value);
      this.get(this.wcCode, this.weixStdate, this.weixEddate);
      this.dateShow = false;
    },
    dateConfirm2(value) {
      // if(tyep == 'weixStdate'){
      // 	this.weixStdate = value
      // }
      // if(tyep == 'weixEddate'){
      // 	this.weixEddate = value
      // }
      // console.log("--------this.stdateTime----------this.stdateTime---------",this.stdateTime,value.value)
      if (this.stdateTime > value.value) {
        uni.showToast({
          title: "请选择截止时间不能早于开始时间",
          icon: "none",
          duration: 1000,
        });
        return;
      }
      this.weixEddate = this.timestampToTime(value.value);
      this.dateShow2 = false;
      this.get(this.wcCode, this.weixStdate, this.weixEddate);
    },
    // 隐患信息概览
    getQueryCarList(jurisdictions, weixStdate, weixEddate) {
      this.$apis
        .queryCarList({
          wcCode: jurisdictions,
          weixStdate,
          weixEddate,
        })
        .then((res) => {
          console.log("隐患信息概览===>", res.result);
          if (res.success) {
            let data = JSON.parse(JSON.stringify(res.result.returnList));
            this.chartData.series[0].data = data;
          }
        });
    },
    get(jurisdictions, weixStdate, weixEddate) {
      clearInterval(this.queryDeductDs);
      this.weixStdate = weixStdate;
      this.weixEddate = weixEddate;
      // this.getQueryCarList(jurisdictions, weixStdate, weixEddate);
    },
  },
  onShow() {
    clearInterval(this.queryDeductDs);
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth();
    let weixStdate = year + "-" + (month < 10 ? "0" + month : month);
    let weixEddate =
      year + "-" + (month + 1 < 10 ? "0" + (month + 1) : month + 1);
    let userInfo = this.$store.state.userInfo;
    let jurisdictions = userInfo.jurisdictions;
    let stdateTime = weixStdate + "-01" + " " + "00:00:00";
    let eddateTime = weixEddate + "-01" + " " + "00:00:00";
    this.stdateTime = new Date(stdateTime).valueOf();
    this.eddateTime = new Date(eddateTime).valueOf();
    this.getSysOrgList(jurisdictions);
    this.get(jurisdictions, weixStdate, weixEddate);
  },
  onLoad() {
    
  },
  onLaunch() {
   
  },
};
</script>
<style lang="less" scoped>
.statisticalData {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 18px;

  .item {
    width: 94%;
    margin-bottom: 20px;
  }
}

.quantity {
  // padding: 5px 0 0 0;
}

.quantity1 {
  display: flex;
  align-items: center;
  height: 70%;
  font-size: 23px;
}

.progressBar {
  width: 100%;
  height: 7px;
  background-color: rgba(245, 91, 43, 0.1);
  border-radius: 10px;
  position: relative;

  .aa {
    height: 7px;
    position: absolute;
    top: 0;
    border-radius: 10px;
    background-color: #f36033;
  }
}

.comtents {
  width: 100%;
  background-color: #eef0fc;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 1px 0px 10px rgba(0, 0, 0, 0.4);

  .content {
    display: flex;
    flex-wrap: wrap;
  }

  .items {
    width: 49%;
    background-color: #fff;
    border-radius: 10px;
    margin-bottom: 2%;
    padding: 10px;
  }
}

.right {
  margin-right: 2%;
}

.title {
  margin-bottom: 10px;
  margin-left: 10px;
  font-size: 20px;
}

.chart1,
.chart2 {
  width: 100%;
  height: 200px;
  border-radius: 10px;
  box-shadow: 1px 0px 10px rgba(0, 0, 0, 0.4);
  // z-index: 10;
}

.percentage {
  text-align: right;
  font-size: 15px;
  margin-top: -16px;
}

.rankingList {
  width: 100%;
  border-radius: 10px;
  box-shadow: 1px 0px 10px rgba(0, 0, 0, 0.4);
  display: flex;
  padding: 12px;
  min-height: 200px;
}

.hiddenDanger {
  width: 50%;
  padding-right: 1%;
}

.hiddenDangerItem {
  display: flex;
  padding-bottom: 12px;
  font-size: 17px;
}

.governBottom {
  display: flex;
  align-items: center;
  padding: 20px;
  padding-right: 15px;
  margin-left: 18px;
  margin-top: 20px;
}

.governBottomItem {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: #000;
  margin: 0 12px;
  position: relative;
}

.governBottomItem:first-child {
  margin-left: 0;
}

.governBottomItemContent {
  width: 70px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  border-radius: 5px;
  background-color: blue;
  color: #fff;
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate(-50%, -75%);
}

.triangle {
  width: 0;
  height: 0;
  border: 10px solid transparent;
  border-top-color: blue;
  position: absolute;
  bottom: -19px;
  left: 50%;
  transform: translate(-50%, 0);
}

.timelinessRatio {
  height: 100px;
}

.timelinessRatioContent {
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: space-between;
}

.timelinessRatioContentItem {
  height: 100%;
  width: 30%;
}

.table {
  width: 100%;
}

.tableHeader,
.tableBody {
  // border: 1px solid rgba(0, 0, 0, 0.3);
}

.tableHeader {
  display: flex;
  font-weight: 700;
  background-color: #3285ff;
  color: #fff;
}

.tableBody {
  border-top: none;
  font-size: 17px;
}

.tableHeaderContent,
.tableBodyContent {
  padding: 2px 0;
  width: 50%;
  text-align: center;
  // color: #f36033;
}

// .tableHeaderContent:nth-child(1),
// .tableBodyContent:nth-child(1) {
//   width: 30%;
// }
// .tableHeaderContent:nth-child(2),
// .tableBodyContent:nth-child(2) {
//   width: 48%;
// }
// .tableHeaderContent:nth-child(3),
// .tableBodyContent:nth-child(3) {
//   width: 22%;
// }
.tableHeaderItem,
.tableBodyItem {
  text-align: center;
  // border-right: 1px solid rgba(0, 0, 0, 0.3);
}

.tableBodyContentItem {
  display: flex;
  width: 100%;
  // border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.tableBodyContentItem:last-child {
  border-bottom: none;
}

.tableHeaderItem:last-child,
.tableBodyItem:last-child {
  border-right: none;
}
//时间选择器
.statisticalData /deep/.u-toolbar__wrapper__cancel {
  color: #909193 !important;
  font-size: 18px !important;
}
.statisticalData /deep/.u-toolbar__wrapper__confirm {
  color: #909193 !important;
  font-size: 18px !important;
}
.statisticalData /deep/.u-picker__view__column__item {
  font-size: 18px !important;
}
.statisticalData /deep/ .u-textarea__field,
.statisticalData /deep/ .u-input__content__field-wrapper__field {
  font-size: 18px !important;
}
.select /deep/ .u-textarea {
  padding: 6px 9px;
}
</style>
