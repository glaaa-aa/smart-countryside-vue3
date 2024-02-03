<template>
  <div class="">
    <!-- 评论通知 -->
    <view class="comments_ul">
      <view class="comments_li" v-for="item in comments" :key="item.id">{{
        item.text
      }}</view>
    </view>
    <!-- 通知输入框 -->
    <view class="search_box">
      <u-search
        :showAction="true"
        actionText="发送"
        placeholder="输入评论内容，@通知他人"
      ></u-search>
    </view>
  </div>
</template>
<script>
export default {
  props: {
    comments: {
      type: Array,
    },
  },
  data() {
    return {};
  },
};
</script>
<style lang="scss" scoped>
//评论通知
.comments_ul {
  background: #f2f2f2;
  margin-bottom: 45px;
  .comments_li {
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    font-size: 12px;
    color: #999;
  }
}
//通知输入框
.search_box {
  position: fixed;
  bottom: 0px;
  left: 0px;
  width: 100%;
  padding: 10px 15px;
  background: white;
}
</style>