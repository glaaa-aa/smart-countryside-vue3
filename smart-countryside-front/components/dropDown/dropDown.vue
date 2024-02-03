<template>
	<view class="dropDown">
		<view class="header" @click="openClick">
			<view class="title">{{title}}</view>
			<u-icon name="arrow-down" v-if="!show"></u-icon>
			<u-icon name="arrow-up" v-else></u-icon>
		</view>
		<view class="maskLayer" v-if="show" @click.stop="ddClick">
			<scroll-view :scroll-y="true" class="content">
				<view v-show="type == 'nogrouping'" v-for="(item,index) in list" :key="index" class="item" @click="itemClick(index)">
                    <u-icon :name="icon"></u-icon>
                    <view>{{item.name}}</view>
                </view>
				<view v-show="type == 'grouping'" class="itemd" v-for="(item, index) in list" :key="index">
					<view class="titles">{{ item.title }}</view>
					<view class="items" v-for="(items, indexs) in item.content" :key="indexs">
						<view class="contents">
							<u-icon :name="items.icon"></u-icon>
							<view>{{ items.name }}</view>
						</view>
					</view>
				</view>
			</scroll-view>
			
		</view>
		
		
	</view>
</template>

<script>
	export default {
        name:"dropDown",
        props: ['list','icon','type'],
		data() {
			return {
				show:false,
				title:'北京奥易克斯科技有限公司'
			}
		},
		methods: {
			openClick(){
				console.log("111")
				this.show = !this.show
                
			},
			itemClick(i){
				console.log("2222",i)
                // console.log(this.list)
				this.list.forEach((item,index) => {
					if(i == index){
						console.log("66",item.name)
						this.title=item.name
					}
				})
				this.show=false
			},
			ddClick(){
				this.show=false
			}
		}
	}
</script>

<style lang="less" scoped>
    html,body {
    height: 0;
}
	page {
		background-color: #fff;
        
	}
    .dropDown {
        // position: absolute;
        // top: 0;
        // left: 0;
        // right: 0;
        // height: 100%;
        // z-index: 4000;
        overflow: hidden;
    }
.header {
	display: flex;
	align-items: center;
	// padding-bottom: 15px;
    background-color: #fff;	
}
.title {
	margin-right: 6px;
}
.maskLayer {
	height: calc(100vh - 73px);
    overflow: hidden;
	background-color: rgba(0, 0, 0, 0.5);
    position: absolute;
    left: 0;
    right: 0;
    z-index: 100;
    

	.content {
	// padding-top: 10px;
	max-height: 70vh;
	overflow: hidden;
	background-color: #fff;
    padding:10px;
    padding-bottom: 0;
}
	.item {
        display: flex;
		padding: 10px 0px 10px 0px;
		
		border-bottom: 1px solid #f6f6f6;
	}
}
.itemd {
  // width: 100%;
  // margin: 10px 0;
  margin-bottom: 10px;
  .titles {
    margin-top: 10px;
    color: #7f7f7f;
  }
  .items {
    .contents {
      display: flex;
      padding: 10px;
      border-bottom: 1px solid #f2f2f2;
      font-size: 16px;
    }
    .contents:last-child {
      border-bottom: none;
    }
  }
}
</style>
