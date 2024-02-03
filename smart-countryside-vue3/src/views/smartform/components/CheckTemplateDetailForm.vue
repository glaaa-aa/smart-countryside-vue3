<template>
    <view>
        <!--标题和返回-->
		<cu-custom :bgColor="NavBarColor" isBack :backRouterName="backRouteName">
			<block slot="backText">返回</block>
			<block slot="content">模板权限表</block>
		</cu-custom>
		 <!--表单区域-->
		<view>
			<form>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">关联模板id：</text></view>
                  <input  placeholder="请输入关联模板id" v-model="model.templateId"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">每台电脑或手机只能答一次：</text></view>
                  <input type="number" placeholder="请输入每台电脑或手机只能答一次" v-model="model.effective"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">有效性间隔时间：</text></view>
                  <input type="number" placeholder="请输入有效性间隔时间" v-model="model.effectiveTime"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">每个IP只能答一次 1是 0否：</text></view>
                  <input type="number" placeholder="请输入每个IP只能答一次 1是 0否" v-model="model.effectiveIp"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">防刷新  1启用 0不启用：</text></view>
                  <input  placeholder="请输入防刷新  1启用 0不启用" v-model="model.refresh"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">refresh=1设置：</text></view>
                  <input  placeholder="请输入refresh=1设置" v-model="model.refreshNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">调查规则   1公开, 2私有, 3令牌：</text></view>
                  <input  placeholder="请输入调查规则   1公开, 2私有, 3令牌" v-model="model.rule"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">令牌：</text></view>
                  <input  placeholder="请输入令牌" v-model="model.ruleCode"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">结束方式1手动结束   2依据结束时间  3依据收到的份数：</text></view>
                  <input  placeholder="请输入结束方式1手动结束   2依据结束时间  3依据收到的份数" v-model="model.endType"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">是否依据收到的份数结束：</text></view>
                  <input  placeholder="请输入是否依据收到的份数结束" v-model="model.ynEndNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">收到的份数：</text></view>
                  <input  placeholder="请输入收到的份数" v-model="model.endNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">是否依据设置的时间结束：</text></view>
                  <input  placeholder="请输入是否依据设置的时间结束" v-model="model.ynEndTime"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">结束时间：</text></view>
                  <input  placeholder="请输入结束时间" v-model="model.endTime"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">问卷说明：</text></view>
                  <input  placeholder="请输入问卷说明" v-model="model.surveyNote"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">问卷下面有多少题目数：</text></view>
                  <input  placeholder="请输入问卷下面有多少题目数" v-model="model.surveyQuNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">可以回答的最少选项数目：</text></view>
                  <input  placeholder="请输入可以回答的最少选项数目" v-model="model.anitemLeastNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">可以回答的最多选项数目：</text></view>
                  <input  placeholder="请输入可以回答的最多选项数目" v-model="model.anitemMostNum"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">只有邮件邀请唯一链接的受访者可回答  1启用 0不启用：</text></view>
                  <input  placeholder="请输入只有邮件邀请唯一链接的受访者可回答  1启用 0不启用" v-model="model.mailonly"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">显示分享：</text></view>
                  <input  placeholder="请输入显示分享" v-model="model.showShareSurvey"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">静态HTML保存路径：</text></view>
                  <input  placeholder="请输入静态HTML保存路径" v-model="model.showAnswerDa"/>
                </view>
              </view>
				<view class="padding">
					<button class="cu-btn block bg-blue margin-tb-sm lg" @click="onSubmit">
						<text v-if="loading" class="cuIcon-loading2 cuIconfont-spin"></text>提交
					</button>
				</view>
			</form>
		</view>
    </view>
</template>

<script>
    import myDate from '@/components/my-componets/my-date.vue'

    export default {
        name: "CheckTemplateDetailForm",
        components:{ myDate },
        props:{
          formData:{
              type:Object,
              default:()=>{},
              required:false
          }
        },
        data(){
            return {
				CustomBar: this.CustomBar,
				NavBarColor: this.NavBarColor,
				loading:false,
                model: {},
                backRouteName:'index',
                url: {
                  queryById: "/smartform/checkTemplateDetail/queryById",
                  add: "/smartform/checkTemplateDetail/add",
                  edit: "/smartform/checkTemplateDetail/edit",
                },
            }
        },
        created(){
             this.initFormData();
        },
        methods:{
           initFormData(){
               if(this.formData){
                    let dataId = this.formData.dataId;
                    this.$http.get(this.url.queryById,{params:{id:dataId}}).then((res)=>{
                        if(res.data.success){
                            console.log("表单数据",res);
                            this.model = res.data.result;
                        }
                    })
                }
            },
            onSubmit() {
                let myForm = {...this.model};
                this.loading = true;
                let url = myForm.id?this.url.edit:this.url.add;
				this.$http.post(url,myForm).then(res=>{
				   console.log("res",res)
				   this.loading = false
				   this.$Router.push({name:this.backRouteName})
				}).catch(()=>{
					this.loading = false
				});
            }
        }
    }
</script>
