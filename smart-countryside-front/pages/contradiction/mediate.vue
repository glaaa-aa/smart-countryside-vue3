<template>
  <div class="mediate">
    <!-- 导航栏 -->
    <!-- <navigation title="我的调解"></navigation> -->
    <!-- tabs切换 -->
    <div class="tabs two_fs_list">
      <div
        class="tabsItem"
        :class="tabsActive == item.state ? 'tabsItem2' : 'tabsItem'"
        v-for="item in tabsList"
        :key="item.state"
        @click="clickTabs(item)"
      >
        {{ item.title }}
      </div>
    </div>
    <!-- 内容 -->
    <div class="cont two_fs_list" v-if="dataList.length" style="margin-bottom: 5px; max-height: 86vh; overflow-y: auto">
      <div
        class="contItem"
        v-for="item in dataList"
        :key="item.id"
        @click="goDetails(item)"
      >
        <div class="cont1">
          <div
            style="
              max-width: 80%;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
              font-weight: bold;
            "
          >
            {{ item.title || "" }}
          </div>
          <div style="display: flex; align-items: center">
            <div v-if="item.state == 1" style="color: #28b61c">已提交</div>
            <div v-if="item.state == 2" style="color: #1658c8">调解中</div>
            <div v-if="item.state == 3" style="color: #828282">已结束</div>
            <div style="margin-left: 6px">
              <u-icon name="arrow-right" color="#828282" size="18"></u-icon>
            </div>
          </div>
        </div>
        <div class="cont2">
          <div class="cont2_1">
            <span style="min-width: 80px">当事人：</span>
            <span class="itemTxt">{{
              item.persons ? item.persons.replace(/,/g, "，") : "-"
            }}</span>
          </div>
          <div class="cont2_1">
            <span>纠纷类型：</span>
            <span class="itemTxt">{{ getDict(item.category) }}</span>
          </div>
          <div
            class="cont2_1"
            style="display: flex; justify-content: space-between"
          >
            <div>
              <span>行政区划：</span>
              <span class="itemTxt">{{ item.orgName }}</span>
            </div>
          </div>
          <div class="cont2_1">
            <span>处置次数：</span>
            <span class="itemTxt">0</span>
          </div>
          <div class="cont2_1">
            <span>提交时间：</span>
            <span class="itemTxt">{{ item.happenDate }}</span>
          </div>
          <div class="cont2_1">
            <span>受理时间：</span>
            <span class="itemTxt">{{ item.acceptDate }}</span>
          </div>
        </div>
        <div class="steps">
            <!-- <div
              v-if="!item.s2"
              style="padding: 5px 10px"
              @click="goMediate(item)"
            >
              现场处置
            </div> -->
            <!-- <div @click="goMediate2(item)" style="padding: 5px 10px">
              部门转办
            </div> -->
          </div>
        <!-- 调解流程 -->
        <!-- <div class="steps">
          <u-steps
            :current="item.dissensionLog.length || 0"
            dot
            direction="column"
          >
            <u-steps-item
              v-for="(i, index) in item.dissensionLog"
              :key="index"
              :title="i.s2"
              :desc="i.handleDate"
            ></u-steps-item>
          </u-steps>
        </div> -->
      </div>
    </div>
    <u-empty
      v-if="!dataList.length"
      mode="list"
      icon="http://cdn.uviewui.com/uview/empty/list.png"
    >
    </u-empty>
    <!-- 创建 -->
    <!-- <div class="addBtn" @click="goAdd">
      <u-icon color="white" name="plus" size="14" bold></u-icon>
      <span>创建</span>
    </div> -->
  </div>
</template>
<script>
import navigation from "@/components/navigation/navigation";
import "@/static/style/style.css";
let label = "12";
export default {
  components: {
    navigation,
  },
  data() {
    return {
      //下拉加载
      status: "nomore",
      current: 1,
      pages: 0,
      pageSize: 20,
      flag: false,
      dataList: [],
      //tab切换
      tabsList: [
        { state: "", title: "全部" },
        { state: "1", title: "已提交" },
        { state: "2", title: "调解中" },
        { state: "3", title: "已结束" },
      ],
      tabsActive: "",
      dataList: [],
      dictModel: [],
    };
  },
  onShow() {
    this.getData(this.current, this.tabsActive);
  },
  onLoad() {
    this.getTypeData();
  },
  methods: {
    //下拉加载
    scrolltolower() {
      console.log("下拉加载");
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
          this.getData(this.current);
        }, 100);
      }
    },
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        console.log("查询纠纷类型字典===>", res);
        this.dictModel = res;
      });
    },
    getDict(value) {
      let label = "";
      this.dictModel.forEach((itm) => {
        if (itm.value == value) {
          label = itm.text;
        }
      });
      console.log(label,'label111');
      return label;
    },
    //tabs切换
    clickTabs(item) {
      console.log(item);
      this.tabsActive = item.state;
      this.getData(this.current, item.state);
    },
    //跳转详情
    goDetails(item) {
      uni.navigateTo({
        url: `/pages/contradiction/modules/mediateDetails?id=${item.id}`,
      });
    },
    //添加矛盾排查  
    goAdd() {
      uni.navigateTo({
        url: "/pages/contradiction/modules/mediateNextForm",
      });
    },
    //跳转现场处置
    goMediate(item) {
      uni.navigateTo({
        url:
          "/pages/contradiction/modules/sceneSubmitForm?obj=" +
          JSON.stringify(item),
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
    // 查询列表
    getData(current, state) {
      let obj = {
        state: state || "",
      };
      console.log("obj======>", obj);
      this.$apis.dissensionList(obj).then((res) => {
        console.log("查询列表", res);
        if (res.success) {
          res.result.forEach(item => {
            console.log(item.category,'item.category');
            item.category_txt =this.getDict(item.category) 
          });
          this.dataList = res.result;
          console.log("dataList==============>", this.dataList);
          this.flag = true;
        }
      });
    },
  },

  // filters:{

  //     toUpperCase(value){
  //         //转化大写过滤器
  //         return value.toUpperCase();
  //     },
  // }
};
</script>
<style lang="scss" scoped>
.mediate {
  padding: 0px 10px 10px;
  //tabs切换
  .tabs {
    display: flex;
    padding: 13px 10px;
    justify-content: space-between;
    background: white;
    margin-top: 10px;
    border-radius: 10px;
    .tabsItem {
      padding: 6px 0px;
      border-radius: 18px;
      width: 80px;
      text-align: center;
    }
    .tabsItem2 {
      background: #1658c8;
      color: white;
      padding: 6px 0px;
      border-radius: 18px;
      width: 80px;
      text-align: center;
    }
  }
  //内容列表
  .cont {
    .contItem {
      background: white;
      margin-top: 10px;
      padding: 10px 10px;
      border-radius: 10px;
    }
    .cont1 {
      display: flex;
      justify-content: space-between;
      border-bottom: 1px solid #f1f1f1;
      padding: 5px 0px 5px 5px;
    }
    .cont2 {
      // border-bottom: 1px solid #f1f1f1;
      padding: 10px 5px;
      .itemTxt {
        color: #9a9a9a;
      }
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
  // 日志
  .steps /deep/ .u-text__value--content {
    font-size: 15px !important;
  }
  .steps /deep/ .u-text__value--tips {
    font-size: 14px !important;
  }
  //创建按钮
  .addBtn {
    background: #1658c8;
    border-radius: 50%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 112rpx;
    height: 112rpx;
    color: white;
    font-size: 14px;
    position: fixed;
    right: 15px;
    bottom: 25%;
    padding: 5px;
    font-weight: bold;
  }
}
</style>