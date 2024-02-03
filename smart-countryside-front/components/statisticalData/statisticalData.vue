<template>
    <view class="statisticalData">
      <view class="item">
        <view class="title">任务按执行者分布</view>
        <view class="chart1">
          <qiun-data-charts type="ring" :opts="opts" :chartData="chartData" />
        </view>
      </view>
      <view class="item">
          <view class="title">概览报表</view>
          <view class="comtents">
          <view v-for="(item,index) in shuju" :key="index" :class="index % 2 == 0 ? 'items right' : 'items'" >
              <view class="titles">{{item.title}}</view>
              <view class="quantity">{{item.quantity}}</view>
              {{item.t/item.quantity*100}}
              <view class="progressBar">
                  
                  <view class="aa" :style="'width:'+item.percentage+'%'"></view>
              </view>
          </view>
      </view>
      </view>
      <view class="item">
        <view class="title">任务燃尽图</view>
        <view class="chart2">
          <qiun-data-charts type="line" :opts="opts2" :chartData="chartData2" />
        </view>
      </view>
      <view class="item">
        <view class="title">项目进展走势图</view>
        <view class="chart2">
          <qiun-data-charts type="line" :opts="opts2" :chartData="chartData2" />
        </view>
      </view>
    </view>
  </template>
      
      <script>
  export default {
    name:'statisticalData',
    data() {
      return {
        chartData: {
          series: [
            {
              data: [
                { name: "一班", value: 50 },
                { name: "二班", value: 30 },
                { name: "三班", value: 20 },
                { name: "四班", value: 18 },
                { name: "五班", value: 8 },
              ],
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
        chartData2: {
          categories: ["2016", "2017", "2018", "2019", "2020", "2021"],
          series: [
            {
              name: "成交量A",
              data: [35, 8, 25, 37, 4, 20],
            },
            {
              name: "成交量B",
              data: [70, 40, 65, 100, 44, 68],
            },
            {
              name: "成交量C",
              data: [100, 80, 95, 150, 112, 132],
            },
          ],
        },
        opts2: {
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
          padding: [15, 10, 0, 15],
          legend: {},
          xAxis: {
            disableGrid: true,
          },
          yAxis: {
            gridType: "dash",
            dashLength: 2,
          },
          extra: {
            line: {
              type: "straight",
              width: 2,
            },
          },
        },
        shuju:[
          {title:'总数',quantity:20,t:2,percentage:10},
          {title:'已完成',quantity:20,t:2,percentage:10},
          {title:'未完成',quantity:10,t:2,percentage:20},
          {title:'已逾期',quantity:20,t:2,percentage:90},
          {title:'今日到期',quantity:20,t:2,percentage:10},
          {title:'逾期完成',quantity:20,t:2,percentage:10},
          {title:'逾期完成',quantity:20,t:2,percentage:10},
          {title:'逾期完成',quantity:20,t:2,percentage:10},
        ]
      };
    },
    methods: {},
    computed:{
      shuliang(){
          
          // let aa=0;
          // this.shuju.forEach((item,index) => {
          // 	aa = item.quantity
          // 	return aa
          // })
          
  
      }
    }
  };
  </script>
      
    <style lang="less" scoped>
  .statisticalData {
    // padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    .item {
      width: 94%;
      margin-bottom: 10px;
      
    }
  }
  .quantity {
      padding: 5px 0;
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
      background-color: #f2f2f2;
      display: flex;
      flex-wrap: wrap;
      padding: 10px;
      border-radius: 10px;
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
  }
  .chart1,
  .chart2 {
    width: 100%;
    height: 200px;
    border-radius: 10px;
    //   box-shadow:;
    box-shadow: 1px 0px 10px rgba(0, 0, 0, 0.4);
  }
  </style>
      