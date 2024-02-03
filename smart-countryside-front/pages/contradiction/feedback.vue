<template>
  <div class="mediate">
    <!-- 导航栏 -->
    <!-- <navigation title="处理反馈"></navigation> -->
    <!-- 内容 -->
    <div class="cont two_fs_list" v-if="dataList.length">
      <div class="contItem" v-for="item in dataList" :key="item.id">
        <div class="cont2">
          <div
            class="cont2_1 cont2_2"
            style="
              justify-content: space-between;
              display: flex;
              justify-content: space-between;
              border-bottom: 1px solid #f1f1f1;
              padding: 10rpx 0px;
            "
          >
            <div
              style="
                max-width: 80%;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                font-weight: bold;
              "
            >
              <!-- <span>姓名：</span> -->
              <span>{{ item.title || "-" }}</span>
            </div>
            <div>
              <span>待处理</span>
            </div>
          </div>
          <!-- <div class="cont2_1">
            <span>联系电话：</span>
            <span>{{ item.phone }}</span>
          </div> -->
          <div class="cont2_1">
            <span>负责人：</span>
            <span class="itemTxt">{{ item.s1 || "" }}</span>
          </div>
          <div class="cont2_1">
            <div style="width: 23%">调解员：</div>
            <div class="itemTxt" style="width: 77%">
              {{ item.s2 ? filterStr(item.s2) : "" }}
            </div>
          </div>
          <div class="cont2_1">
            <span>当事人：</span>
            <span class="itemTxt">{{ item.persons ? filterStr(item.persons) :"" }}</span>
          </div>
          <div class="cont2_1">
            <span>纠纷类型：</span>
            <span class="itemTxt">{{ getDict(item.category) }}</span>
          </div>
          <div class="cont2_1">
            <span>涉及人数：</span>
            <span class="itemTxt">{{ item.numbers }}</span>
          </div>
          <div class="cont2_1">
            <div style="width: 29%">纠纷描述：</div>
            <div class="itemTxt" style="width: 71%">
              {{ item.content || "" }}
            </div>
          </div>
          <div class="cont2_1">
            <span>发生时间：</span>
            <span class="itemTxt">{{ item.happenDate }}</span>
          </div>
          <div class="cont2_1">
            <span>排查时间：</span>
            <span class="itemTxt">{{ item.acceptDate }}</span>
          </div>
        </div>
        <div class="steps">
          <div
            @click="goHandle(item)"
            style="
              padding: 4px 25px;
              border: 1px solid #1658c8;
              border-radius: 6px;
            "
          >
            提交反馈
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
  </div>
</template>
<script>
import "@/static/style/style.css";
import navigation from "@/components/navigation/navigation";
import { getImgUrl, mobileStr, recursion, filterStr } from "@/common/utils.js";

export default {
  components: {
    navigation,
  },
  data() {
    return {
      tabsActive: 1,
      dataList: [],
      dictModel: [],
    };
  },
  onShow() {
    this.getData();
  },
  onLoad() {
    this.getTypeData();
  },

  methods: {
    filterStr,
    //去处理
    getData() {
      this.$apis.principalList().then((res) => {
        console.log("查询列表", res);
        if (res.success) {
          this.dataList = res.result;
        } else {
          this.dataList = [];
        }
      });
    },
    getTypeData() {
      this.$apis.getDictItems("disputes_type").then((res) => {
        this.dictModel = res;
      });
    },
    getDict(value) {
      console.log("value=====>", value);
      let label = "";
      this.dictModel.forEach((itm) => {
        if (itm.value == value) {
          label = itm.text;
        }
      });
      return label;
    },

    goHandle(item) {
      uni.navigateTo({
        url: "/pages/contradiction/modules/feedbackSubmitForm?id=" + item.id,
      });
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
      margin-top: 10px;
      padding: 10px 10px;
      border-radius: 10px;
    }
    .cont1 {
      display: flex;
      justify-content: space-between;
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
      padding: 20px 0px 10px;
      color: #1658c8;
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