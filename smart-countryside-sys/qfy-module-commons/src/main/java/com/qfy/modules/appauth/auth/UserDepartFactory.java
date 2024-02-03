package com.qfy.modules.appauth.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qfy.modules.appauth.model.UserDepratBean;
import com.qfy.modules.commons.service.ISystemUserService;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName: SerialUserDepartFactory
 * @Description: 初始化用户 分配的分管部门和负责部门
 * @author 马强
 * @date 2022-10-24
 */
@Configuration
public class UserDepartFactory implements InitializingBean, Serializable {
    private static final long serialVersionUID = -2831322335781190028L;
    private static final Logger logger = LoggerFactory.getLogger(UserDepartFactory.class);
    private static final String startPrefix = "USERDEPART-";

    @Autowired
    private ISystemUserService systemUserService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        initData(null);
    }

    public static UserDepartFactory getInstance () {
        return SpringContextHolder.getBean(UserDepartFactory.class);
    }

    /**
     * 获取用户所属分管部门和部门
     * @param username
     * @return
     */
    public static UserDepratBean userDepart(String username){
        return getInstance()._getUserDepart(username);
    }

    private UserDepratBean _getUserDepart(String username){
        Object value = redisUtil.get(startPrefix + username);
        UserDepratBean userDepratPage = null;
        if(value == null){
            userDepratPage = new UserDepratBean();
            userDepratPage.setUsername(username);
            return userDepratPage;
        }
        userDepratPage = JSON.parseObject(value.toString(), UserDepratBean.class);
        userDepratPage.setUsername(username);
        return userDepratPage;
    }


    /**
     * 数据初始化
     */
    private void initData(Integer tenantId){
        //查询所有项目
        long start = System.currentTimeMillis();
        List<String> userList = systemUserService.selectAllTenntUser(tenantId);
        for (String username : userList){
            Map<String, Object> _map = systemUserService.selectUserDepart(username);
            if(_map == null || _map.size() <= 0 ){
                continue;
            }
            redisUtil.set(startPrefix + username, JSONObject.toJSONString(_map));
        }
        long end = System.currentTimeMillis();
        logger.info("结束初始化用户分管部门和负责部门==>用时"+(end-start)+"毫秒" );
    }


    /**
     * 线程安全的写法 初始化数据
     */
    public static class initUserDepartThread implements Callable {
        private Integer tenantId;

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        public Object call() throws Exception {
            Thread.sleep(1500);
            getInstance().initData(tenantId);
            return null;
        }
    }


}
