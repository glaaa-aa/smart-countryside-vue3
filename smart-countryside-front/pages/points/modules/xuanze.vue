<template>
  <view>
    <view
      class="select"
      v-if="show"
    >
      <view class="selectContent">
        <view class="header">
          <!-- <view @click="closeClick">
						<u-icon name="close" size="17" color="#aaa"></u-icon>
					</view> -->
          <view
            style="padding: 6px"
            @click="closeClick"
          >取消</view>
          <view class="title">{{ title }}</view>
          <view
            v-if="MultiSelect"
            style="color: #3ca8ff; padding: 6px"
            @click="confirmClick"
          >确定</view>
          <view
            v-else
            style="width: 10px"
          ></view>
        </view>
        <view class="search">
          <u-search
            placeholder="请输入关键字"
            shape="square"
            :showAction="false"
            v-model="queryParam"
            @change="searchVal"
          ></u-search>
        </view>
        <view
          class="content"
          v-if="MultiSelect"
        >
          <view
            class="item"
            v-for="(item, index) in Data"
            :key="index"
            @click="xuanzeClick(index, item)"
          >
            <!-- <view class="itemName" :class="type == 'taskState' ? 'bg' : ''">{{item.title}}</view> -->
            <view class="itemName">{{ item.title }}</view>
            <view
              class="itemCheck"
              v-if="item.show"
            >
              <u-icon
                name="checkmark"
                size="17"
                color="#3f8dd9"
              ></u-icon>
            </view>
          </view>
        </view>
        <view
          class="content"
          v-else
        >
          <view
            class="item"
            v-for="(item, index) in Data"
            :key="index"
            @click="xuanzeClick(index, item)"
          >
            <view class="itemName">{{ item.title }}</view>
            <!-- <view class="itemName" :class="type == 'taskState' ? 'bg' : ''">{{item.title}}</view> -->
            <view
              class="itemCheck"
              v-if="activeShows == index"
            >
              <u-icon
                name="checkmark"
                size="17"
                color="#3f8dd9"
              ></u-icon>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  name: "xuanze",
  props: {
    show: {
      type: Boolean,
      default: false,
    },
    Data: {
      type: Array,
      default: [],
    },
    activeShow: {
      type: Number,
      default: null,
    },
    type: {
      type: String,
      default: "",
    },
    MultiSelect: {
      type: Boolean,
      default: false,
    },
    title: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      activeShows: null,
      arr: [],
      data: [],
      queryParam: "", //搜索值
    };
  },
  created() {
    this.activeShows = this.activeShow
    // this.activeShows = null;
  },
  watch: {
    Data: function (newVal, oldVal) {
      console.log('newVal, oldVal', newVal, oldVal);
      this.data = this.Data;
      // this.activeShows = null;
    },
  },
  methods: {
    xuanzeClick(index, value) {
      console.log(index, value, this.Data);
      if (this.MultiSelect) {
        this.Data[index].count = this.Data[index].count + 1;
        if (this.Data[index].count % 2 != 0) {
          this.Data[index].show = true;
          this.arr.push(value);
        }
        if (this.Data[index].count % 2 == 0) {
          this.Data[index].show = false;
          let i = this.arr.indexOf(value);
          this.arr.splice(i, 1);
        }
      } else {
        this.activeShows = index;
        this.$emit("confirm", index, value, this.type);
      }
    },
    //点击确定
    confirmClick() {
      this.$emit("confirm", 0, this.arr, this.type);
    },
    closeClick() {
      this.$emit("close");
    },
    //搜索
    searchVal(n) {
      console.log(n, "子组件转入父组件");
      this.$emit("confirmSearch", n);
      // this.data = this.data.filter((item) => item.title.includes(n));
      // console.log(this.data);

    },
  },
};
</script>

<style lang="less" scoped>
.select {
  width: 100vw;
  height: 100vh;
  // background-color: blue;
  background-color: rgba(0, 0, 0, 0.65);
  position: absolute;
  left: 0;
  right: 0;
  top: 5px;
  z-index: 20;
}
.selectContent {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 91vh;
  background-color: #fff;
  border-radius: 12px 12px 0 0;
  padding: 15px;
  z-index: 20;
}
.header {
  color: #aaaaaa;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  // padding: 6px 0;
}
.title {
  margin-left: 5px;
}
.search {
  margin-top: 18px;
  margin-bottom: 10px;
}
.content {
  height: 75vh;
  overflow: auto;
}
.item {
  border-bottom: 1px solid #efefef;
  padding: 10px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.itemName {
  border-radius: 13px;
  display: inline-block;
  padding: 2px 9px;
}
.bg {
  background-color: #56abfb;
  color: #fff;
}
.itemCheck {
  padding-right: 10px;
}
</style>