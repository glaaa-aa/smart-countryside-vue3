package com.qfy.modules.dissension.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfy.modules.dissension.entity.Dissension;
import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import com.qfy.modules.dissension.service.IDissensionInvolvedUserService;
import com.qfy.modules.dissension.service.AndonRepairMoManService;
import com.qfy.modules.dissension.service.IDissensionService;
import com.qfy.modules.dissension.vo.DissensionPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * @Description: 矛盾纠纷
 * @Author: jeecg-boot
 * @Date: 2023-12-26
 * @Version: V1.0
 */
@Api(tags = "矛盾纠纷")
@RestController
@RequestMapping("/dissension/dissension")
@Slf4j
public class DissensionController {
    @Autowired
    private IDissensionService dissensionService;
    @Autowired
    private IDissensionInvolvedUserService qfyDissensionInvolvedUserService;
    @Autowired
    private AndonRepairMoManService andonRepairMoManService;


    /**
     * 分页列表查询
     *
     * @param dissension
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "矛盾纠纷-分页列表查询")
    @ApiOperation(value = "矛盾纠纷-分页列表查询", notes = "矛盾纠纷-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<Dissension>> queryPageList(Dissension dissension,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        QueryWrapper<Dissension> queryWrapper = QueryGenerator.initQueryWrapper(dissension, req.getParameterMap());
        Page<Dissension> page = new Page<Dissension>(pageNo, pageSize);
        IPage<Dissension> pageList = dissensionService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 纠纷反馈管理分页列表查询
     *
     * @param dissension
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "纠纷反馈管理分页列表查询")
    @ApiOperation(value = "纠纷反馈管理分页列表查询", notes = "纠纷反馈管理分页列表查询")
    @GetMapping(value = "/principalList")
    public Result<IPage<Dissension>> principalList(Dissension dissension,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Dissension> queryWrapper = QueryGenerator.initQueryWrapper(dissension, req.getParameterMap());
        if (sysUser.getUserIdentity() == 20) {
            queryWrapper.like("org_code", sysUser.getDistrictCode());
        } else if (sysUser.getUserIdentity() == 40) {
            queryWrapper.like("org_code", sysUser.getDistrictCode());
        } else if (sysUser.getUserIdentity() == 60) {
            queryWrapper.eq("resp_persons", sysUser.getUsername());
        }
        Page<Dissension> page = new Page<Dissension>(pageNo, pageSize);
        IPage<Dissension> pageList = dissensionService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param dissensionPage
     * @return
     */
    @AutoLog(value = "矛盾纠纷-添加")
    @ApiOperation(value = "矛盾纠纷-添加", notes = "矛盾纠纷-添加")
    @RequiresPermissions("dissension:qfy_dissension:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody DissensionPage dissensionPage) {
        Dissension dissension = new Dissension();
        BeanUtils.copyProperties(dissensionPage, dissension);
        dissensionService.saveMain(dissension, dissensionPage.getDissensionInvolvedUserList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dissensionPage
     * @return
     */
    @AutoLog(value = "矛盾纠纷-编辑")
    @ApiOperation(value = "矛盾纠纷-编辑", notes = "矛盾纠纷-编辑")
    @RequiresPermissions("dissension:qfy_dissension:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody DissensionPage dissensionPage) {
        Dissension dissension = new Dissension();
        BeanUtils.copyProperties(dissensionPage, dissension);
        Dissension dissensionEntity = dissensionService.getById(dissension.getId());
        dissension.setCode(dissensionEntity.getCode());
        if (dissensionEntity == null) {
            return Result.error("未找到对应数据");
        }
        dissensionService.updateMain(dissension, dissensionPage.getDissensionInvolvedUserList(), dissensionPage.getAndonRepairMoManList());
        return Result.OK("编辑成功!");
    }

    /**
     * 反馈
     *
     * @param dissensionPage
     * @return
     */
    @AutoLog(value = "矛盾纠纷-反馈")
    @ApiOperation(value = "矛盾纠纷-反馈", notes = "矛盾纠纷-反馈")
    @RequestMapping(value = "/addAssign", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> addAssign(@RequestBody DissensionPage dissensionPage) {
        try {
            Dissension dissension = new Dissension();
            BeanUtils.copyProperties(dissensionPage, dissension);
            Dissension dissensionEntity = dissensionService.getById(dissension.getId());
            dissension.setCode(dissensionEntity.getCode());
            dissensionService.addAssign(dissension, dissensionPage.getAndonRepairMoManList());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.OK("编辑成功!");
    }

    /**
     * 矛盾纠纷-统计报表
     *
     * @param state
     * @return
     */
    @AutoLog(value = "矛盾纠纷-统计报表")
    @ApiOperation(value = "矛盾纠纷-统计报表", notes = "矛盾纠纷-统计报表")
    @GetMapping(value = "/getCount")
    public Result<Map<String, Object>> getCount(String state) {
        Map<String, Object> map = dissensionService.queryDissensionCount(state);
        return Result.OK(map);
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "矛盾纠纷-通过id删除")
    @ApiOperation(value = "矛盾纠纷-通过id删除", notes = "矛盾纠纷-通过id删除")
    @RequiresPermissions("dissension:qfy_dissension:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        dissensionService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "矛盾纠纷-批量删除")
    @ApiOperation(value = "矛盾纠纷-批量删除", notes = "矛盾纠纷-批量删除")
    @RequiresPermissions("dissension:qfy_dissension:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dissensionService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "矛盾纠纷-通过id查询")
    @ApiOperation(value = "矛盾纠纷-通过id查询", notes = "矛盾纠纷-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Dissension> queryById(@RequestParam(name = "id", required = true) String id) {
        Dissension dissension = dissensionService.getById(id);
        if (dissension == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(dissension);

    }

    /**
     * 通过mainId查询矛盾纠纷涉及人员
     *
     * @param mainId
     * @return
     */
    @ApiOperation(value = "矛盾纠纷涉及人员-通过mainId查询", notes = "矛盾纠纷涉及人员-通过mainId查询")
    @GetMapping(value = "/dissensionUserList")
    public Result<List<DissensionInvolvedUser>> dissensionUserList(@RequestParam(name = "mainId", required = true) String mainId) {
        List<DissensionInvolvedUser> dissensionInvolvedUsers = qfyDissensionInvolvedUserService.selectByMainId(mainId);
        if (dissensionInvolvedUsers == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(dissensionInvolvedUsers);
    }

    /**
     * 通过mainId查询矛盾纠纷负责人
     *
     * @param mainId
     * @return
     */
    @ApiOperation(value = "矛盾纠纷涉及负责人-通过mainId查询", notes = "矛盾纠纷涉及负责人-通过mainId查询")
    @GetMapping(value = "/andonRepairMoMan")
    public Result<List<AndonRepairMoMan>> dissensionAndonRepairMoMan(@RequestParam(name = "mainId", required = true) String mainId) {
        List<AndonRepairMoMan> qepairDissensionMEN = andonRepairMoManService.selectByMainId(mainId);
        if (qepairDissensionMEN == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(qepairDissensionMEN);
    }


    /**
     * 导出excel
     *
     * @param request
     * @param dissension
     */
    @RequiresPermissions("dissension:qfy_dissension:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dissension dissension) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<Dissension> queryWrapper = QueryGenerator.initQueryWrapper(dissension, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //Step.2 获取导出数据
        List<Dissension> dissensionList = dissensionService.list(queryWrapper);

        // Step.3 组装pageList
        List<DissensionPage> pageList = new ArrayList<DissensionPage>();
        for (Dissension main : dissensionList) {
            DissensionPage vo = new DissensionPage();
            BeanUtils.copyProperties(main, vo);
            List<DissensionInvolvedUser> qfyDissensionInvolvedUserList = qfyDissensionInvolvedUserService.selectByMainId(main.getId());
            vo.setDissensionInvolvedUserList(qfyDissensionInvolvedUserList);
            List<AndonRepairMoMan> qfyAndonRepairMoManList = andonRepairMoManService.selectByMainId(main.getId());
            vo.setAndonRepairMoManList(qfyAndonRepairMoManList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "矛盾纠纷列表");
        mv.addObject(NormalExcelConstants.CLASS, DissensionPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("矛盾纠纷数据", "导出人:" + sysUser.getRealname(), "矛盾纠纷"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("dissension:qfy_dissension:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<DissensionPage> list = ExcelImportUtil.importExcel(file.getInputStream(), DissensionPage.class, params);
                for (DissensionPage page : list) {
                    Dissension po = new Dissension();
                    BeanUtils.copyProperties(page, po);
                    dissensionService.saveMain(po, page.getDissensionInvolvedUserList());
//                  dissensionService.saveMain(po, page.getDissensionInvolvedUserList(),page.getDissensionResponsibleUserList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}
