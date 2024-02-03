package com.qfy.modules.api.controller;

import com.qfy.modules.api.service.IAdminDivisionService;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 行政区划
 * @author lanls
 * @version 1.0
 * @date 2023/11/20 1:31
 */
@RestController
@RequestMapping("/api/sys/district")
public class AdminDivisionController {

    public static final Logger logger = LoggerFactory.getLogger(AdminDivisionController.class);

    @Resource
    private IAdminDivisionService divisionService;

    @GetMapping("/list")
    public Result getDistrictList(@RequestParam(name = "uid", required = false) String uid,
                                  @RequestParam(name = "orgCode",defaultValue = "A02A15", required = false) String orgCode,
                                  @RequestParam(name = "pid", defaultValue = "A02", required = false) String pid) {

        if (StringUtils.isNotBlank(uid)) {
            // 根据用户id获取用户所管辖的行政区划编码
        }
        return divisionService.getDivisionList(orgCode, pid);
    }
}
