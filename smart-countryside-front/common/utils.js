import indexConfig from "@/config/index.config.js";

module.exports = {

  /**
   * @param {Object} url 图片相对路径
   */
  getImgUrl(url) {
    return indexConfig.assetsPath + url;
  },
  //手机号中间四位加*号
  mobileStr(str) {
    if (str.length > 7) {
      return str.substring(0, 3) + '****' + str.substring(7, str.length);
    } else {
      return str.substring(0, str.length - 1) + '****';
    }

  },
   // 拨打电话
   playphone(phone) {
    if (phone) {
      uni.makePhoneCall({
        // 手机号 这里可以直接写号码如 12345 也可以写获取号码的字段如this.mobile
        phoneNumber: phone,
        // 成功回调
        success: (res) => {
          console.log("调用成功!");
        },
        // 失败回调
        fail: (res) => {
          console.log("调用失败!");
        },
      });
    }
  },
  //根据code循环获取树结构对应的code对象
  // data-->树结构数据源
  // code-->某个节点code
  recursion(data,current){
    var result = null
    if (!data) {
      return
    }
    for (var i in data) {
      if (result !==null) {
        break;
      }
      var item = data[i];
      if (item.code==current) {
        result = item;
        break;
      }else if (item.children && item.children.length>0) {
        result = this.recursion(item.children,current)
      }
    }
    return result
  },
  // 根据逗号分隔去掉重复字符串
  filterStr(str) { //str:'aaa,bbb,ccc,bbb'
    var answer = "";
    answer = str.split(",").filter(function (item, pos, self) {
      return self.indexOf(item) == pos;
    });
    return answer.toString().replace(/,/g, "，");
  },
  //将时间戳转换成日期格式
  timestampToTime(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear() + "-";
    let M =
      date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1) + "-"
        : date.getMonth() + 1 + "-";
    let D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    // let h = (date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':');
    // let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
    return Y + M + D;
  },
   //将时间戳转换成年格式
   timestampToTime2(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear();
    // let M =
    //   date.getMonth() + 1 < 10
    //     ? "0" + (date.getMonth() + 1) + "-"
    //     : date.getMonth() + 1 + "-";
    // let D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    // let h = (date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':');
    // let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
    return Y;
  },
  //将时间戳转换成年月格式
  timestampToTime3(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear() + "-";
    let M =
      date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1)
        : date.getMonth() + 1;
   
    return Y + M;
  },
  // 年月日时分秒
  timestampToTime4(timestamp) {
    var date=new Date(timestamp);
    //年
    var year=date.getFullYear();
    //月
    var month=(date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
    //日
    var day=date.getDate()<10?"0"+date.getDate():date.getDate();
    //时
    var hh=date.getHours()<10?"0"+date.getHours():date.getHours();
    //分
    var mm=date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes();
    //秒
    var ss=date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds();
 
    var nowTime = year+"-"+month+"-"+day+" "+hh+":"+mm+":"+ss;
 
    return nowTime;
  },
  /*
   * 将cityNo 转 cityName
   * cityData:原数据
   * cityNo：二级地区编码
   */
  getCityName(cityData, cityNo) {
    if (!cityNo) return;
    if (!(cityData instanceof Array)) return;
    // 9112
    cityNo += "";
    for (let i = 0; i < cityData.length; i++) {
      let sheng = cityData[i];
      for (let j = 0; j < sheng.children.length; j++) {
        let shi = sheng.children[j];
        if (shi.value == cityNo) {
          // 使用return 终止循环
          return `${sheng.label}-${shi.label}`;
        }
      }
    }
  },

  /*
   * obj 转 params字符串参数
   * 例子：{a:1,b:2} => a=1&b=2
   */
  objParseParam(obj) {
    let paramsStr = "";
    if (obj instanceof Array) return paramsStr;
    if (!(obj instanceof Object)) return paramsStr;
    for (let key in obj) {
      paramsStr += `${key}=${obj[key]}&`;
    }
    return paramsStr.substring(0, paramsStr.length - 1);
  },

  /*
   * obj 转 路由地址带参数
   * 例子：{a:1,b:2} => /pages/index/index?a=1&b=2
   */
  objParseUrlAndParam(path, obj) {
    let url = path || "/";
    let paramsStr = "";
    if (obj instanceof Array) return url;
    if (!(obj instanceof Object)) return url;
    paramsStr = this.objParseParam(obj);
    paramsStr && (url += "?");
    url += paramsStr;
    return url;
  },

  /*
   * 获取url字符串参数
   */
  getRequestParameters(locationhref) {
    let href = locationhref || "";
    let theRequest = new Object();
    let str = href.split("?")[1];
    if (str != undefined) {
      let strs = str.split("&");
      for (let i = 0; i < strs.length; i++) {
        theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
      }
    }
    return theRequest;
  },

  /*
   * 银行卡每四位空格
   */
  formatCardNo(cardNo) {
    cardNo += "";
    return cardNo
      .replace(/\s/g, "")
      .replace(/[^\d]/g, "")
      .replace(/(\d{4})(?=\d)/g, "$1 ");
  },

  /**
   * 乘法，解决js精度损失问题
   * @param {*} arg1
   * @param {*} arg2
   */
  accMul(arg1, arg2) {
    arg1 = arg1 || 0;
    var m = 0,
      s1 = arg1.toString(),
      s2 = arg2.toString();
    try {
      m += s1.split(".")[1].length;
    } catch (e) { }
    try {
      m += s2.split(".")[1].length;
    } catch (e) { }
    return (
      (Number(s1.replace(".", "")) * Number(s2.replace(".", ""))) /
      Math.pow(10, m)
    );
  },
  handleCurrDate() {
    // 获取当前日期
    var date = new Date();

    // 获取当前月份
    var nowMonth = date.getMonth() + 1;

    // 获取当前是几号
    var strDate = date.getDate();

    // 添加分隔符“-”
    var seperator = "-";

    // 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
      nowMonth = "0" + nowMonth;
    }

    // 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }

    // 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
    var nowDate =
      date.getFullYear() + seperator + nowMonth + seperator + strDate;

    return nowDate;
  },
  //获取当前年份
  getCurrYear() {
    // 获取当前日期
    var date = new Date();
    var nowDate = date.getFullYear();
    return nowDate;
  },
};


