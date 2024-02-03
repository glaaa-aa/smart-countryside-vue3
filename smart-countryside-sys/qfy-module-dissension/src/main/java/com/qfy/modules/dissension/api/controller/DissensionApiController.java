package com.qfy.modules.dissension.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.dissension.api.service.IDissensionApiService;
import com.qfy.modules.dissension.api.vo.DissensionDetailsResult;
import com.qfy.modules.dissension.entity.*;
import com.qfy.modules.dissension.service.IDissensionInvolvedUserService;
import com.qfy.modules.dissension.service.IDissensionRecordsService;
import com.qfy.modules.dissension.service.AndonRepairMoManService;
import com.qfy.modules.dissension.service.impl.AndonRepairMoServiceImpl;
import com.qfy.modules.dissension.vo.DissensionPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api(tags="矛盾纠纷客户端API")
@RestController
@RequestMapping("/api/resident/dissension")
@Slf4j
public class DissensionApiController {

    @Autowired
    private IDissensionApiService dissensionService;
    @Autowired
    private IDissensionInvolvedUserService involvedUserService;
    @Autowired
    private AndonRepairMoManService responsibleUserService;
    @Autowired
    private IDissensionRecordsService recordsService;
    @Autowired
    private AndonRepairMoServiceImpl andonRepairMoService;

    /**
     * 纠纷列表查询
     *
     * @param dissension
     * @return
     */
    @ApiOperation(value="纠纷-列表查询", notes="纠纷-列表查询")
    @GetMapping(value = "/list")
    public Result<List<DissensionDetailsResult>> queryList(Dissension dissension,
                                                           @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                           @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                           HttpServletRequest req) {
        List<DissensionDetailsResult> dataList=dissensionService.getDissensionDetails(dissension);
        return Result.OK(dataList);
    }

    /**
     * 查询纠纷详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value="查询纠纷详情", notes="查询纠纷详情")
    @GetMapping(value = "/details")
    public Result<DissensionDetailsResult> queryDetails(@RequestParam(name="id",required=true) String id) {
        DissensionDetailsResult detailsResult=new DissensionDetailsResult();
        Dissension dissension = dissensionService.getById(id);
        if(dissension==null){
            return Result.error("未找到数据");
        }
        BeanUtils.copyProperties(dissension,detailsResult);
//        List<DissensionRecords> dissensionRecords = recordsService.selectByCode(dissension.getCode());
//        detailsResult.setDissensionLog(dissensionRecords);
        List<DissensionInvolvedUser> involvedUsers = involvedUserService.selectByMainId(dissension.getCode());
        detailsResult.setInvolvedUsers(involvedUsers);
        List<AndonRepairMoMan> responsibleUsers = responsibleUserService.selectByMainId(dissension.getCode());
        detailsResult.setResponsibleUsers(responsibleUsers);
        return Result.OK(detailsResult);
    }

    /**
     * 纠纷调解次数查询
     *
     * @param moCode
     * @return
     */
    @ApiOperation(value="纠纷调解次数查询", notes="纠纷调解次数查询")
    @GetMapping(value = "/queryMediateList")
    public Result<List<AndonRepairMo>> queryMediateList(String moCode, HttpServletRequest req) {
        QueryWrapper<AndonRepairMo> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("mo_code",moCode);
        List<AndonRepairMo> list = andonRepairMoService.list(queryWrapper);
        return Result.OK(list);
    }


    /**
     * 添加纠纷
     * @param dissensionPage
     * @return
     */
    @ApiOperation(value="纠纷-添加", notes="纠纷-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody DissensionPage dissensionPage) {
        Dissension dissension = new Dissension();
        BeanUtils.copyProperties(dissensionPage, dissension);
        dissensionService.saveMain(dissension, dissensionPage.getDissensionInvolvedUserList());
        return Result.OK("添加成功！");
    }

    /**
     *  纠纷-反馈
     *
     * @param dissensionPage
     * @return
     */
    @AutoLog(value = "纠纷-反馈")
    @ApiOperation(value="纠纷-反馈", notes="纠纷-反馈")
    @RequestMapping(value = "/feedback", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> feedback(@RequestBody DissensionPage dissensionPage) {
        try {
            Dissension dissension = new Dissension();
            BeanUtils.copyProperties(dissensionPage, dissension);
            Dissension dissensionEntity = dissensionService.getById(dissension.getId());
            dissension.setCode(dissensionEntity.getCode());
            dissensionService.feedback(dissension,dissensionPage.getAndonRepairMoManList());
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
        return Result.OK("编辑成功!");
    }


//    /**
//     * 纠纷反馈列表
//     *
//     * @return
//     */
//    @ApiOperation(value="纠纷反馈列表", notes="纠纷反馈列表")
//    @GetMapping(value = "/principalList")
//    public Result<List<DissensionDetailsResult>> principalList() {
//        List<DissensionDetailsResult> detailsResults=new ArrayList<>();
//        LoginUser sysUser=(LoginUser) SecurityUtils.getSubject().getPrincipal();
//        QueryWrapper<Dissension> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("resp_persons",sysUser.getUsername());
//        queryWrapper.eq("state","2");
//        queryWrapper.orderByDesc("happen_date");
//        List<Dissension> list = dissensionService.list(queryWrapper);
//        if(ObjectUtils.isEmpty(list)){
//            return Result.error("未找到对应数据");
//        }
//        AndonRepairMoMan responsibleUser = responsibleUserService.selectByPrincipal(list.get(0).getCode());
//        for (Dissension dissension : list) {
//            DissensionDetailsResult result=new DissensionDetailsResult();
//            if(ObjectUtils.isNotEmpty(responsibleUser)){
//                result.setPhone(responsibleUser.getPhone());
//            }
//
//            BeanUtils.copyProperties(dissension,result);
//            detailsResults.add(result);
//        }
//        return Result.OK(detailsResults);
//    }
}
