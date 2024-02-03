import Vue from 'vue'
import Vuex from 'vuex'
import $mConfig from '@/config/index.config.js'
import $mRoutesConfig from '@/config/routes.config.js'
import $mRouter from '@/common/router.js'

Vue.use(Vuex)

const TOKEN = uni.getStorageSync("token") || "";
// const OPENID = uni.getStorageSync("openId") || "";
const USERID = uni.getStorageSync("userId") || "";
const USER_INFO = uni.getStorageSync("userInfo") || {};
const CUSTOMER_INFO = uni.getStorageSync("customerInfo") || {};

const store = new Vuex.Store({
	state: {
		// 是否強制登录
		forcedLogin:$mConfig.forcedLogin,
		// 前端token
		token: TOKEN,
		//用户id
		userId:USERID,
		// 用户openid
		// openId: OPENID,
		// 用户信息 头像 昵称
		userInfo: USER_INFO
	},
	getters: {
		// 用户是否登录
		hasLogin: state => {
			if (state.userId) {
				return true;
			} else {
				return false
			}
		},
		//UUID生成、获取
		guid: function() {
			try {
				var value = uni.getStorageSync('myuuid')
				if (value) {
					return value
				} else {
					let uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
						var r = Math.random() * 16 | 0,
							v = c == 'x' ? r : (r & 0x3 | 0x8);
						return v.toString(16);
					});
					console.log("uuid生成成功：", uuid)
					uni.setStorage({
						key: "myuuid",
						data: uuid,
						success: (res) => {},
						fail: () => {
							uni.showToast({
								title: 'UUID生成失败，请联系管理员',
								icon: 'fail'
							})
							return null
						}
					})
					return uuid
				}
			} catch (e) {
		
			}
		},
		
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token;
			uni.setStorageSync("token", token);
		},
		SET_USERID(state, userId) {
			state.userId = userId;
			uni.setStorageSync("userId", userId);
		},
		// SET_OPENID(state, openId) {
		// 	state.openId = openId;
		// 	uni.setStorageSync("openId", openId);
		// },
		SET_USERINFO(state, userInfo) {
			state.userInfo = userInfo;
			uni.setStorageSync("userInfo", userInfo);
		},
		SET_CODE(state, code) {
			state.code = code;
			uni.setStorageSync("code", code);
		},
		SET_LOGOUT(state) {
			state.token = "";
			uni.setStorageSync("token", "");
		}
	},
	actions: {
		// 登录过期 重新登录
		reLogin({
			commit
		}, info) {
			commit("SET_TOKEN","");
			$mRouter.redirectTo({
				route:$mRoutesConfig.login
			});
		}
	},
	
	
	
	
	
})

export default store
