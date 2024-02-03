package com.qfy.modules.hr.info.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.hr.info.entity.HrPerson;
import com.qfy.modules.hr.info.service.IHrPersonService;
import com.xkcoding.http.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysDepartModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @Description: 人员档案
* @Author: jeecg-boot
* @Date:   2021-12-17
* @Version: V1.0
*/
@Api(tags="人员档案")
@RestController
@RequestMapping("/info/hrPerson")
@Slf4j
public class HrPersonController {
   @Autowired
   private IHrPersonService hrPersonService;
   @Autowired
   private ISysBaseAPI sysBaseAPI;


   /**
    * 分页列表查询
    *
    * @param hrPerson
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "人员档案-分页列表查询")
   @ApiOperation(value="人员档案-分页列表查询", notes="人员档案-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(HrPerson hrPerson,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       String orgCode = hrPerson.getOrgCode();
       hrPerson.setOrgCode(null);
       QueryWrapper<HrPerson> queryWrapper = QueryGenerator.initQueryWrapper(hrPerson, req.getParameterMap());
       //组织机构 根据部门模糊查询
       if(StringUtil.isNotEmpty(orgCode)){
           queryWrapper.likeRight("org_code",orgCode);
       }
       queryWrapper.eq("del_flag",0);

       Page<HrPerson> page = new Page<HrPerson>(pageNo, pageSize);
       IPage<HrPerson> pageList = hrPersonService.page(page, queryWrapper);
       return Result.OK(pageList);
   }

    /**
     *   添加
     *
     * @param hrPerson
     * @return
     */
    @AutoLog(value = "hr_person-添加")
    @ApiOperation(value="hr_person-添加", notes="hr_person-添加")
//    @RequiresPermissions("dissension:qfy_hr_person:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody HrPerson hrPerson) {
        hrPersonService.saveHrPerson(hrPerson);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     *
     * @param hrPerson
     * @return
     */
    @AutoLog(value = "hr_person-编辑")
    @ApiOperation(value="hr_person-编辑", notes="hr_person-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody HrPerson hrPerson) {
        hrPersonService.updateMain(hrPerson);
        return Result.OK("编辑成功!");
    }

   /**
    *   添加
    *
    * @param hrPersonPage
    * @return
    */
//   @AutoLog(value = "人员档案-添加")
//   @ApiOperation(value="人员档案-添加", notes="人员档案-添加")
//   @PostMapping(value = "/add")
//   public Result<?> add(@RequestBody HrPersonPage hrPersonPage) {
//       HrPerson hrPerson = new HrPerson();
//       BeanUtils.copyProperties(hrPersonPage, hrPerson);
////       hrPerson.setBpmStatus("1");
////       hrPersonService.saveMain(hrPerson, hrPersonPage.getHrEduList(),hrPersonPage.getHrJobList());
//       return Result.OK("添加成功！");
//   }

   /**
    *  编辑
    *
    * @param hrPersonPage
    * @return
    */
//   @AutoLog(value = "人员档案-编辑")
//   @ApiOperation(value="人员档案-编辑", notes="人员档案-编辑")
//   @PutMapping(value = "/edit")
//   public Result<?> edit(@RequestBody HrPersonPage hrPersonPage) {
//       HrPerson hrPerson = new HrPerson();
//       BeanUtils.copyProperties(hrPersonPage, hrPerson);
//       HrPerson hrPersonEntity = hrPersonService.getById(hrPerson.getId());
//       if(hrPersonEntity==null) {
//           return Result.error("未找到对应数据");
//       }
//       hrPersonService.updateMain(hrPerson, hrPersonPage.getHrEduList(),hrPersonPage.getHrJobList());
//       return Result.OK("编辑成功!");
//   }

   /**
    *   通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "人员档案-通过id删除")
   @ApiOperation(value="人员档案-通过id删除", notes="人员档案-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       hrPersonService.delMain(id);
       return Result.OK("删除成功!");
   }

   /**
    *  批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "人员档案-批量删除")
   @ApiOperation(value="人员档案-批量删除", notes="人员档案-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.hrPersonService.delBatchMain(Arrays.asList(ids.split(",")));
       return Result.OK("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "人员档案-通过id查询")
   @ApiOperation(value="人员档案-通过id查询", notes="人员档案-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       HrPerson hrPerson = hrPersonService.getById(id);
       if(hrPerson==null) {
           return Result.error("未找到对应数据");
       }
       return Result.OK(hrPerson);

   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
//   @AutoLog(value = "学历信息通过主表ID查询")
//   @ApiOperation(value="学历信息主表ID查询", notes="学历信息-通主表ID查询")
//   @GetMapping(value = "/queryHrEduByMainId")
//   public Result<?> queryHrEduListByMainId(@RequestParam(name="id",required=true) String id) {
//       List<HrEdu> hrEduList = hrEduService.selectByMainId(id);
//       return Result.OK(hrEduList);
//   }
//   /**
//    * 通过id查询
//    *
//    * @param id
//    * @return
//    */
//   @AutoLog(value = "工作信息通过主表ID查询")
//   @ApiOperation(value="工作信息主表ID查询", notes="工作信息-通主表ID查询")
//   @GetMapping(value = "/queryHrJobByMainId")
//   public Result<?> queryHrJobListByMainId(@RequestParam(name="id",required=true) String id) {
//       List<HrJob> hrJobList = hrJobService.selectByMainId(id);
//       return Result.OK(hrJobList);
//   }

   /**
   * 导出excel
   *
   * @param request
   * @param hrPerson
   */
//   @RequestMapping(value = "/exportXls")
//   public ModelAndView exportXls(HttpServletRequest request, HrPerson hrPerson) {
//     // Step.1 组装查询条件查询数据
//     QueryWrapper<HrPerson> queryWrapper = QueryGenerator.initQueryWrapper(hrPerson, request.getParameterMap());
//     LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//
//     //Step.2 获取导出数据
//     List<HrPerson> queryList = hrPersonService.list(queryWrapper);
//     // 过滤选中数据
//     String selections = request.getParameter("selections");
//     List<HrPerson> hrPersonList = new ArrayList<HrPerson>();
//     if(oConvertUtils.isEmpty(selections)) {
//         hrPersonList = queryList;
//     }else {
//         List<String> selectionList = Arrays.asList(selections.split(","));
//         hrPersonList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
//     }
//
//     // Step.3 组装pageList
//     List<HrPersonPage> pageList = new ArrayList<HrPersonPage>();
//     for (HrPerson main : hrPersonList) {
//         HrPersonPage vo = new HrPersonPage();
//         BeanUtils.copyProperties(main, vo);
//         List<HrEdu> hrEduList = hrEduService.selectByMainId(main.getId());
//         vo.setHrEduList(hrEduList);
//         List<HrJob> hrJobList = hrJobService.selectByMainId(main.getId());
//         vo.setHrJobList(hrJobList);
//         pageList.add(vo);
//     }
//
//     // Step.4 AutoPoi 导出Excel
//     ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//     mv.addObject(NormalExcelConstants.FILE_NAME, "人员档案列表");
//     mv.addObject(NormalExcelConstants.CLASS, HrPersonPage.class);
//     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("人员档案数据", "导出人:"+sysUser.getRealname(), "人员档案"));
//     mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
//     return mv;
//   }
//
//   /**
//   * 通过excel导入数据
//   *
//   * @param request
//   * @param response
//   * @return
//   */
//   @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//   public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//     Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//     for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//         MultipartFile file = entity.getValue();// 获取上传文件对象
//         ImportParams params = new ImportParams();
//         params.setTitleRows(2);
//         params.setHeadRows(1);
//         params.setNeedSave(true);
//         try {
//             List<HrPersonPage> list = ExcelImportUtil.importExcel(file.getInputStream(), HrPersonPage.class, params);
//             for (HrPersonPage page : list) {
//                 HrPerson po = new HrPerson();
//                 BeanUtils.copyProperties(page, po);
//                 hrPersonService.saveMain(po, page.getHrEduList(),page.getHrJobList());
//             }
//             return Result.OK("文件导入成功！数据行数:" + list.size());
//         } catch (Exception e) {
//             log.error(e.getMessage(),e);
//             return Result.error("文件导入失败:"+e.getMessage());
//         } finally {
//             try {
//                 file.getInputStream().close();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//     return Result.OK("文件导入失败！");
//   }
//
//
//    /**
//     * 头像上传-批量
//     * @param request
//     * @param response
//     * @return
//     */
//    @AutoLog(value = "人员管理")
//    @ApiOperation(value="头像上传-批量", notes="头像上传-批量")
//    @RequestMapping(value = "/userImgUpload", method = RequestMethod.POST)
//    public Result<?> userImgUpload(HttpServletRequest request, HttpServletResponse response) {
//
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
////		 String userType = request.getParameter("userType");
////		 if(oConvertUtils.isEmpty(userType)){
////			 return Result.error("参数错误");
////		 }
//        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        List<SchUserImg> list = hrPersonService.AddUserImg(fileMap,loginUser);
//
//        return Result.ok(list.get(0).getImgUrl());
//    }

    /**
     * 头像上传-指定人员
     * @param request
     * @param response
     * @return
     */
    @AutoLog(value = "人员管理")
    @ApiOperation(value="头像上传", notes="头像上传")
    @RequestMapping(value = "/imgUploadByUserId", method = RequestMethod.POST)
    public Result<?> imgUploadByUserId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            log.info("-----------imgUploadByUserId--------"+userId);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象

            if(oConvertUtils.isEmpty(userId)){
                return Result.error("请选择人员");
            }
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            hrPersonService.AddUserImgByCode(file,userId,loginUser);
            return Result.ok("上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }

}
