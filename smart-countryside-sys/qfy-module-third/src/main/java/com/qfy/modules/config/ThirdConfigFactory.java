package com.qfy.modules.config;
/**
 * @title: SystemConstant
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2022/12/8 17:17
 */

import com.qfy.modules.config.entity.ThirdConfig;
import com.qfy.modules.config.service.IThirdConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author     ：马强
 * @date       ：Created in 2022/12/8 17:17
 * @description：系统全局变量
 * @modified By：
 * @version:
 */
@Configuration
public class ThirdConfigFactory implements InitializingBean, Serializable {
    public static Map<Integer,Map<String, ThirdConfig>> thirdConfigMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(ThirdConfigFactory.class);
    @Autowired
    private IThirdConfigService thirdConfigService;

    /**
     * 获取参数数据
     * @param tenantId
     * @param thirdType
     * @return
     */
    public static ThirdConfig getConfig(Integer tenantId,String thirdType){
        Map<String, ThirdConfig> maps = thirdConfigMap.get(tenantId);
        return  maps.get(thirdType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化第三方参数配置 ==============>thirdConfig<==============");
        thirdConfigService.reload();
    }

}
