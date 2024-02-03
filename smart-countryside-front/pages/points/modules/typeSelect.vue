<template>
  <div>
    <u-popup :show="show" @close="cancel">
      <!-- <view class="title">{{ popupTitle }}</view> -->
      <div class="contentBox" style="padding-top: 15px; padding: 20rpx">
        <u-search
          v-if="showSearch"
          @custom="search"
          @search="search"
          @change="search"
          :placeholder="placeholder"
          v-model="keyword"
        ></u-search>
        <u-gap v-if="showSearch" height="15"></u-gap>
        <scroll-view
          :scroll-top="scrollTop"
          scroll-y="true"
          class="scroll-Y"
          @scrolltolower="$emit('lower')"
        >
          <radio-group
            v-if="type == 'radio'"
            @change="radioChange"
            class="agreement_radio"
          >
            <div
              v-for="(item, index) in dataLists"
              :key="index"
              class="two_fs"
              style="
                display: flex;
                justify-content: space-between;
                padding: 10px 0;
                border-bottom: 1px solid #eee;
                padding: 10px 0;
                margin-bottom: 10px;
              "
            >
              <div style="width: 10%">
                <radio :value="String(index)" :checked="item.checked" />
              </div>
              <div style="width: 90%">
                <div style="margin-bottom: 5px; display: flex">
                  <div
                    style="
                      text-overflow: ellipsis;
                      overflow: hidden;
                      word-break: break-all;
                      white-space: nowrap;
                      display: flex;
                    "
                    class="text_left"
                  >
                    <div style="font-weight: bold; min-width: 80px">
                      积分类别：
                    </div>
                    <div>{{ item.name }}</div>
                  </div>
                  <div style="margin-left: 40px" class="text_bottom">
                    {{ item.score }}分
                  </div>
                </div>
                <div
                  style="
                    text-overflow: ellipsis;
                    overflow: hidden;
                    word-break: break-all;
                    display: flex;
                  "
                  :style="
                    item.remark.length < 13
                      ? 'textAlign: center'
                      : 'textAlign: left'
                  "
                  class="text_left"
                >
                  <div>
                    <span style="font-weight: bold">量化指标：</span
                    >{{ item.remark }}
                  </div>
                </div>
              </div>
            </div>
          </radio-group>
          <checkbox-group
            v-if="type == 'checkbox'"
            @change="checkboxChange"
            class="agreement_radio"
          >
            <div
              v-for="(item, index) in dataLists"
              :key="index"
              class="two_fs"
              style="
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 0;
                border-bottom: 1px solid #eee;
              "
            >
              <checkbox :value="String(index)" :checked="item.checked" />
              <text style="width: 10%">
                {{ index + 1 }}
              </text>
              <text
                style="
                  text-overflow: ellipsis;
                  overflow: hidden;
                  word-break: break-all;
                  white-space: nowrap;
                  width: 25%;
                "
                class="text_left"
                >{{ item.name }}
              </text>
              <text
                style="
                  text-overflow: ellipsis;
                  overflow: hidden;
                  word-break: break-all;
                  white-space: nowrap;
                  width: 33%;
                "
                class="text_left"
                >{{ item.content }}
              </text>
              <text style="text-align: right; width: 15%" class="text_bottom"
                >{{ item.score }}分</text
              >
            </div>
          </checkbox-group>
          <u-empty
            v-if="!dataLists.length"
            mode="data"
            icon="http://cdn.uviewui.com/uview/empty/data.png"
            text="暂无分类选择"
          ></u-empty>
        </scroll-view>

        <u-gap height="45"></u-gap>

        <div class="bottons">
          <u-row>
            <u-col customStyle="padding:0 10rpx 20rpx 20rpx" span="6">
              <u-button @click="cancel">取消</u-button>
            </u-col>
            <u-col customStyle="padding:0 20rpx 20rpx 10rpx" span="6">
              <u-button
                @click="submit"
                type="primary"
                throttleTime="1000"
                :disabled="
                  JSON.stringify(radioData) === '{}' &&
                  checkboxData.length === 0
                "
                >确认</u-button
              >
            </u-col>
          </u-row>
        </div>
      </div>
    </u-popup>
  </div>
</template>

<script>
/**
 * 公共选择下拉框，基于uview。支持下拉加载、列表搜索、单选｜多选
 * @author qianziyu
 * @description 弹出层选择器，基于uview中u-popup实现
 * @property {Array}			dataLists			数据列表
 * @property {String}			name				列表显示的字段名
 * @property {Boolean}			show				是否展示弹窗 (默认 false )
 * @property {String}			type				选择类型 单选｜多选 (默认 单选 )
 * @property {Boolean}			showSearch			是否显示搜索框 (默认 true )
 * @property {String}	        popupTitle			列表标题
 * @property {String}			placeholder			搜索框placeholder
 * @event {Function} search 搜索事件，返回keyword
 * @event {Function} lower 滑动到底部触发，用于下拉加载新数据
 * @event {Function} cancel 组件关闭事件
 * @event {Function} submit 提交按钮,返回选中的列表数据
 * @example <common-select :show="show" :popupTitle="popupTitle" @cancel="show=false" @search="selectSearch" name="cworkStationName" @submit="onsubmit"
    :dataLists="dataLists" placeholder="输入工站名称搜索"></common-select>
 */
export default {
  name: "qianziyu-select",
  props: {
    dataLists: {
      default: {},
      type: Array,
    },
    name: {
      default: "name",
    },
    show: {
      default: false,
      type: Boolean,
    },
    type: {
      default: "radio",
      type: String,
    },
    showSearch: {
      default: true,
      type: Boolean,
    },
    popupTitle: {
      default: "列表选择",
      type: String,
    },
    placeholder: {
      default: "请输入搜索内容",
    },
  },
  data() {
    return {
      keyword: "",
      scrollTop: 0,
      checkboxData: [],
      checkboxValue: [],
      radioData: {},
      radioValue: "",
      biaoshi: 0, //处理取消后是否禁用确认按钮
    };
  },
  methods: {
    // 多选
    checkboxChange(n) {
      let arr = n.target.value;
      this.checkboxData = [];
      arr.forEach((key) => {
        this.checkboxData.push(this.dataLists[key]);
      });
    },
    //单选
    radioChange(n) {
      let arr = n.target.value;
      this.radioData = this.dataLists[arr];
    },
    //点击搜索触发
    search() {
      this.$emit("search", this.keyword);
    },
    clear() {
      this.$emit("clear", this.keyword);
    },
    //点击取消按钮触发
    cancel() {
      if (!this.biaoshi) {
        this.radioData = {};
      }
      this.$emit("cancel");
    },
    //提交触发
    submit() {
      if (this.type == "radio") {
        if (JSON.stringify(this.radioData) == "{}") {
          uni.$u.toast("请选择数据");
          return;
        }
        this.$emit("submit", [this.radioData]);
      } else if (this.type == "checkbox") {
        if (this.checkboxData.length == 0) {
          uni.$u.toast("请选择数据");
          return;
        }
        this.$emit("submit", this.checkboxData);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.u-popup {
  .title {
    border-bottom: 1px solid #f2f2f2;
    padding: 20rpx;
    text-align: center;
    font-weight: bold;
    font-size: 18px;
  }
}

.scroll-Y {
  // height: 650rpx;
  height: 92vh;
}

.bottons {
  background-color: white;
  position: fixed;
  left: 0;
  bottom: 0;
  right: 0;
  bottom: constant(safe-area-inset-bottom);
  bottom: env(safe-area-inset-bottom);
}
.contentBox /deep/ .u-search__content {
  height: 40px;
  border-radius: 8px 0px 0px 8px !important;
}
.contentBox /deep/ .u-search__action--active {
  width: 60px;
  background: #1e98d7;
  color: white;
  height: 40px;
  border-radius: 0px 8px 8px 0;
  line-height: 40px;
  margin-left: 0 !important;
  font-size: 17px;
}
</style>
