package com.qfy.modules.family.util;

import java.io.Serializable;
import java.util.Random;

import com.qfy.modules.family.service.IFamilyBaseService;
import com.xkcoding.http.util.StringUtil;
import org.jeecg.common.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @ClassName: SerialOrderID
 * @Description: 生成订单序列号
 * @author 马强
 * @date 2015-7-14 上午11:05:11
 *
 */
@Component
public class SerialNumberID implements InitializingBean, Serializable {

    /**
     * @Fields serialVersionUID : 用一句话描述这个变量表示什么
     */

    private static final long serialVersionUID = -2831322335781190028L;
    private static final Logger logger = LoggerFactory.getLogger(SerialNumberID.class);
    private long autoIncreaseNo = -1L;// 用于自增序列号
    private String maxOrderNo = null;
    public static class OrderIDHolder {
//        private Date   timestamp;    // 时间戳，先不加入订单号
        private long   increateNo;   // 自增序列，6位
//        private String selfIdentify; // 个性化标识，1位

//        public Date getTimestamp() {
//            return timestamp;
//        }
//        public void setTimestamp(Date timestamp) {
//            this.timestamp = timestamp;
//        }

        public long getIncreateNo() {
            return increateNo;
        }

        public void setIncreateNo(long increateNo) {
            this.increateNo = increateNo;
        }

//        public String getSelfIdentify() {
//            return selfIdentify;
//        }
//
//        public void setSelfIdentify(String selfIdentify) {
//            this.selfIdentify = selfIdentify;
//        }

        public OrderIDHolder(long increateNo, String selfIdentify) {
            super();
//            this.timestamp = new Date();
            this.increateNo = increateNo;
//            this.selfIdentify = selfIdentify;
        }

        public OrderIDHolder(long increateNo) {
            super();
//            this.timestamp = new Date();
            this.increateNo = increateNo;
//            this.selfIdentify = String.format("%01d", new Random().nextInt(10));
        }

        /**
         *
         * @Title: valueOf
         * @Description: 生成订单号（格式仿淘宝）
         * @return
         * @throws
         */
        @Override
        public String toString() {
            return increateNo +"";
//            return increateNo + selfIdentify;
        }

        /**
         *
         * @Title: valueOf
         * @Description: 实例化方法
         * @param orderNo
         * @return
         * @throws
         */
        public static OrderIDHolder valueOf(String orderNo) {
            OrderIDHolder holder = null;
            try {
//                long increateNo = Long.valueOf(orderNo.substring(0, orderNo.length()-1));
//                String selfIdentify = orderNo.substring(orderNo.length()-1);
//                holder = new OrderIDHolder(increateNo, selfIdentify);
                long increateNo = Long.valueOf(orderNo);
                holder = new OrderIDHolder(increateNo, null);
            } catch (Exception e) {
                logger.error("订单号【{}】格式错误.", orderNo);
            }

            return holder;
        }
    }

    @Autowired
    private IFamilyBaseService familyBaseService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //项目初始化的时候 查询数据为空 所有在执行next方法的时候 再次执行reload()
//        reload();
    }

    public static SerialNumberID getInstance () {
        return SpringContextHolder.getBean(SerialNumberID.class);
    }

    /**
     *
     * @Title: reload
     * @Description: 重新初始化
     * @throws
     */
    public void reload() {
        maxOrderNo = familyBaseService.getMaxNumber();
        if (StringUtil.isEmpty(maxOrderNo)) {// 设置初始值
            autoIncreaseNo = 100000L;
            maxOrderNo = new OrderIDHolder(autoIncreaseNo).toString();
        } else {
            OrderIDHolder holder = OrderIDHolder.valueOf(maxOrderNo);
            if (null != holder) {
                autoIncreaseNo = holder.getIncreateNo();
            }
        }
    }

    /**
     *
     * @Title: nextNO
     * @Description: 获取下一个订单号
     * @return
     * @throws
     */
    public static String nextNO() {
    	return nextNO(false);
    }

    /**
     * @Title: nextNO
     * @Description: 获取下一个订单号
     * @param synchronizeWithDb 是否与数据库同步
     * @return
     * @throws
     */
    public static String nextNO(boolean synchronizeWithDb) {
        SerialNumberID instance = SerialNumberID.getInstance();
        if (synchronizeWithDb || instance.maxOrderNo ==null) {
            instance.reload();
        }
        if (-1 != instance.autoIncreaseNo) {
            instance.autoIncreaseNo++;
            instance.maxOrderNo = new OrderIDHolder(instance.autoIncreaseNo).toString();
        } else {
            instance.maxOrderNo = null;
            logger.error("订单号初始化失败。");
        }
        return instance.maxOrderNo;
    }

    /**
     *
     * @Title: maxNo
     * @Description: 获取当前最大的订单号
     * @return
     * @throws
     */
    private static String maxNo() {
        return SerialNumberID.getInstance().maxOrderNo;
    }
}
