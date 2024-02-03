<template>
  <div class='ruralActivity'>
    <!-- 导航栏 -->
    <!-- <navigation title="我发起的"></navigation> -->
    <!-- 活动列表 -->
    <ruralList :dataList="dataList"></ruralList>
  </div>
</template>
<script>
import navigation from "@/components/navigation/navigation";
import { getImgUrl } from "@/common/utils.js";
import ruralList from './ruralList.vue'
import "@/static/style/style.css";
export default {
  components: {
    navigation,
    ruralList
  },
  data() {
    return {
      dataList: [],
    }
  },
  created() {
    this.getData(this.current);
  },
  methods: {
    getImgUrl,
    // 子组件触发父组件事件
    fatherMethod() {
      console.log('测试');
      this.getData(this.current);
    },
    //列表查询
    getData(current) {
      this.dataList = [
        { id: 1, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/1.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 2, num: '2023/10/12 星期四', state: 2, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/5.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 3, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/4.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 4, num: '2023/10/12 星期四', state: 2, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 5, num: '2023/10/12 星期四', state: 2, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 6, num: '2023/10/12 星期四', state: 2, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 7, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 8, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 9, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 10, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 11, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 12, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
        { id: 13, num: '2023/10/12 星期四', state: 1, time: '2023-10-19 14:00', img: 'https://cdn.uviewui.com/uview/goods/3.jpg', title: "关于uView的取名来由，首字母u来自于uni-app首字母，uni-app是基Vuejs，Vue和View(延伸为UI、视图之意)同音，同时view组件uni-app中最础最重要的组件，故取名uView，表达源于uni-app和Vue之意，同时在此也对它示感谢。", },
      ]
    },
  },
}
</script>
<style lang="scss" scoped>
.ruralActivity {
  padding: 0px 5px 5px;
}
</style>