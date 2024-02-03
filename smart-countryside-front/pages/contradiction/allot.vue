<template>
  <div class="mediate">
    <!-- 导航栏 -->
    <!-- <navigation title="待分派"></navigation> -->
    <!-- 内容 -->
    <div class="cont two_fs_list" v-if="dataList.length">
      <div
        class="cell1 cell2"
        style="
          background: white;
          margin-bottom: 10px;
          border-radius: 10px;
          padding: 10px 0px 10px 5px;
          display: flex;
          box-sizing: border-box;
          justify-content: space-between;
        "
      >
        <u-search
          placeholder="请输入涉及人员/纠纷名称"
          searchIconSize="0"
          v-model="searchObj.queryParam"
          @search="searchClick"
          @custom="searchClick"
          @change="changeInput"
          clearabled
          @clear="clickClear"
        >
          <template slot="label">
            <div
              @click="clickSezer"
              style="
                display: flex;
                align-items: center;
                border: 1px solid #ddd;
                padding: 1px 5px;
              "
            >
              <div class="left_text" style="font-size: 18px; color: #999">
                筛选
              </div>
              <u-icon name="arrow-down" color="#2979ff" size="18"></u-icon>
            </div>
          </template>
        </u-search>
      </div>
      <div
        v-if="dataList.length"
        style="margin-bottom: 5px; max-height: 88vh; overflow-y: auto"
      >
        <div
          class="contItem"
          v-for="item in dataList"
          :key="item.id"
          @click.stop="goDetails(item)"
        >
          <div class="cont1">
            <div
              style="
                max-width: 75%;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                font-weight: bold;
              "
            >
              {{ item.title || "" }}
            </div>
            <div style="display: flex; align-items: center">
              <div v-if="item.state == 1" style="color: #28b61c">待调解</div>
              <div v-if="item.state == 2" style="color: #1658c8">调解中</div>
              <div v-if="item.state == 3" style="color: #828282">已结束</div>
              <div style="margin-left: 6px">
                <u-icon name="arrow-right" color="#828282" size="18"></u-icon>
              </div>
            </div>
          </div>
          <div class="cont2">
            <div class="cont2_1" v-if="item.s1">
              <span class="mim-wthdh-itemTile">负责人：</span>
              <span
              class="itemTxt"
                style="margin-right: 10px"
                >{{ item.s1 || "待定" }}</span
              >
              <span class="itemTxt">{{ item.phone || "" }}</span>
            </div>
            <div class="cont2_1" v-if="item.s2">
              <span class="mim-wthdh-itemTile">调解员：</span>
              <span
                class="itemTxt"
                >{{ item.s2 ? filterStr(item.s2) : "待定" }}</span
              >
            </div>
            <div class="cont2_1">
              <span>纠纷类型：</span>
              <span class="itemTxt">{{ getDict(item.category) || "" }}</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile">涉及人员：</span>
              <span class="itemTxt">{{ item.persons || "" }}</span>
            </div>
            <div class="cont2_1">
              <span>行政区划：</span>
              <span class="itemTxt">{{ item.orgName || "-" }}</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile">发生地点：</span>
              <span class="itemTxt">{{ item.address || "-" }}</span>
            </div>

            <!-- <div class="cont2_1">
            <span>涉及人数：</span>
            <span>{{ item.numbers || "" }}</span>
          </div> -->
            <div class="cont2_1">
              <span>发生时间：</span>
              <span class="itemTxt">{{ item.happenDate || "" }}</span>
            </div>
            <div class="cont2_1">
              <span>受理时间：</span>
              <span class="itemTxt">{{ item.acceptDate || "" }}</span>
            </div>
            <div class="cont2_1">
              <span class="mim-wthdh-itemTile">矛盾信息：</span>
              <span class="itemTxt">-</span>
            </div>
          </div>
          <div class="steps">
            <div
              style="
                border: 1px solid #1ea5e3;
                border-radius: 6px;
                padding: 5px 25px;
              "
              @click.stop="goMediate(item)"
            >
              去处置
            </div>
            <!-- <div @click="goMediate2(item)" style="padding: 5px 10px">
              部门转办
            </div> -->
          </div>
        </div>
      </div>
    </div>
    <u-empty
      v-if="!dataList.length"
      mode="list"
      icon="http://cdn.uviewui.com/uview/empty/list.png"
    >
    </u-empty>
    <!-- 筛选器 -->
    <Sizer
      :sizerShow="sizerShow"
      :list="filterData"
      @cancel="filterCancel"
      @confirm="sizerClick"
    ></Sizer>
  </div>
</template>
<script>
import "@/static/style/style.css";
import color from "uview-ui/libs/config/color";
import { getImgUrl, mobileStr, recursion, filterStr } from "@/common/utils.js";
import Sizer from "@/components/Sizer/Sizer.vue";
export default {
  components: {
    Sizer,
  },
  data() {
    return {
      tabsActive: 1,
      dataList: [],
      dictModel: [], //纠纷类型字典
      //搜索参数
      searchObj: {
        // notifier: "",
        pageNo: 1, //分页
        queryParam: "", //搜索值
        callState: "", //状态值
        beginTime: "",
        endTime: "",
        // causeCode: "", //隐患信息
        // callType: "1",
        // wcCode: null,
      },
      //筛选器
      sizerShow: false,
      filterData: [
        {
          title: "发生时间",
          key: "years",
          type: "year",
          data: [],
        },
        // {
        //   title: "状态选择",
        //   key: "callState",
        //   type: "ordinary",
        //   value: null,
        //   data: [
        //     {value:'1',name:'待调解'},
        //     {value:'2',name:'进行中'},
        //     {value:'3',name:'已完成'},
        //   ],
        // },

        // {
        //   title: "隐患信息",
        //   key: "causeCode",
        //   type: "radio",
        //   value: "",
        //   data: [],
        // },
      ],
    };
  },
  created() {
    this.getTypeData();
  },
  onShow() {
    this.getDissensionList();
  },
  methods: {
    filterStr,
    // 查询列表
    getDissensionList() {
      let obj = {
        state: "",
      };
      console.log(obj);
      this.$apis.dissensionList(obj).then((res) => {
        console.log("查询列表", res);
        // if (res.result.dataFiles) {
        //     res.result.dataFiles = res.result.dataFiles.split(",");
        //   }
        this.dataList = res.result;
      });
    },
    //搜索
    searchClick(e) {
      this.searchObj.queryParam = e;
      // this.dissensionList(this.searchObj)


      // this.$apis.dissensionList(this.searchObj).then((res) => {
      //   console.log("搜索===>", res);
      //   if (res.success) {
      //     res.result.records.forEach((item) => {
      //       item.faultCodeLength = item.faultCode.split(",").length;
      //     });
      //     this.dataList = res.result.records;
      //   }
      // });

    },
    changeInput(value) {
      if (!value) {
        this.dataList = [];
        this.searchObj.pageNo = 1;
        this.searchClick(value);
      }
    },
    clickClear() {
      this.searchObj.queryParam = "";
    },
    //筛选器
    clickSezer() {
      this.sizerShow = true;
    },
    filterCancel() {
      this.sizerShow = false;
    },
    //筛选确定
    sizerClick(e) {
      console.log(e,'筛选确定');
      this.searchObj.callState = e.callState;
      this.searchObj.beginTime = e.beginTime;
      this.searchObj.endTime = e.endTime;
      this.searchObj.causeCode = e.causeCode;
      this.searchClick(this.searchObj.queryParam);
      this.sizerShow = false;
    },
    //跳转现场处置
    goMediate(item) {
      uni.navigateTo({
        url: "/pages/contradiction/modules/feedbackSubmitForm?id=" + item.id+"&&pageId=1",
      });
    },
    //跳转部门转办
    goMediate2(item) {
      uni.navigateTo({
        url:
          "/pages/contradiction/modules/transferSubmitForm?obj=" +
          JSON.stringify(item),
      });
    },
    //查看详情
    goDetails(item) {
      console.log("item===============>", item);
      // uni.navigateTo({
      //   url: "/pages/contradiction/modules/mediateDetails?id=" + item.id,
      // });
      uni.navigateTo({
        url: "/pages/contradiction/modules/feedbackSubmitForm?id=" + item.id,
      });
    },
    // 查询纠纷类型字典
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        this.dictModel = res;
      });
    },
    // 处理返回字典文本
    getDict(value) {
      let label = "";
      this.dictModel.forEach((itm) => {
        if (itm.value == value) {
          label = itm.text;
        }
      });
      return label;
    },
  },
};
</script>
<style lang="scss" scoped>
.mediate {
  padding: 0px 10px 10px;

  //内容列表
  .cont {
    .contItem {
      background: white;
      margin-bottom: 10px;
      padding: 10px 10px;
      border-radius: 10px;
    }
    .cont1 {
      display: flex;
      justify-content: space-between;
      border-bottom: 1px solid #f1f1f1;
      padding: 10px 5px;
    }
    .cont2 {
      border-bottom: 1px solid #f1f1f1;
      padding: 10px 5px;
      .cont2_1 {
        line-height: 28px;
        display: flex;
      }
    }
    .steps {
      padding-top: 10px;
      color: #1ea5e3;
      display: flex;
      justify-content: space-around;
    }
  }
  //创建按钮
  .addBtn {
    background: #1658c8;
    border-radius: 50%;
    display: flex;
    align-items: center;
    width: 50px;
    height: 50px;
    color: white;
    font-size: 14px;
    position: fixed;
    right: 15px;
    bottom: 25%;
    padding: 5px;
  }
}
</style>