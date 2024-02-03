<template>
	<view>
		<u-grid :col="col" :border="false">
			<u-grid-item v-for="(item,index) in myList">
				<u-image width="200rpx" height="200rpx" :src="item" fade mode="aspectFill" @tap="show(index)">
					<u-loading slot="loading"></u-loading>
				</u-image>
			</u-grid-item>
		</u-grid>
	</view>
</template>

<script>
	export default {
		name:"phonegrid",
		data() {
			return {
				myList:[]
			}
		},
		props:{
			col:3,
			list:Array
		},
		watch:{
			list(){
				this.list.forEach((item,index)=>{
					if(item.search(/http/i)!=0){
						this.myList.push(getApp().globalData.$http.config.host +'/'+ item)
					}else{
						this.myList.push(item)
					}
				})
			}
		},
		methods:{
			show(i){
				uni.previewImage({
					urls: this.list,
					current: i
				});
			}
		}
	}
</script>

<style>

</style>
