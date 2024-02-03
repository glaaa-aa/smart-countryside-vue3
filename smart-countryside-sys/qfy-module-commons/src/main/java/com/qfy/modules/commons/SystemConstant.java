package com.qfy.modules.commons;/**
 * @title: SystemConstant
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2022/12/8 17:17
 */

import com.qfy.modules.commons.service.ICmnParamsService;
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

public class SystemConstant implements InitializingBean, Serializable {
    public static Map<String,String> sysMapValue = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SystemConstant.class);
    @Autowired
    private ICmnParamsService paramsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化系统全局变量====>SystemConstant");
        paramsService.reload();
    }

}
