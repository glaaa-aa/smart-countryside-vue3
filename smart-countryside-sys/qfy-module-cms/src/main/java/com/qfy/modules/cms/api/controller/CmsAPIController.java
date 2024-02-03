package com.qfy.modules.cms.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.cms.api.vo.*;
import com.qfy.modules.cms.entity.CmsArticle;
import com.qfy.modules.cms.entity.CmsSection;
import com.qfy.modules.cms.service.ICmsArticleService;
import com.qfy.modules.cms.service.ICmsSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO CMS客户端接口
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/17 9:28
 */
@Api(tags = "CMS客户端API")
@RestController
@RequestMapping("/apiCms/cms")
public class CmsAPIController {

    public static final Logger logger = LoggerFactory.getLogger(CmsAPIController.class);

    @Autowired
    private ICmsArticleService articleService;
    @Autowired
    private ICmsSectionService sectionService;

    /**
     * 文章列表
     * @return
     */
    @AutoLog(value = "CMS管理API-获取文章列表")
    @ApiOperation(value = "CMS管理API-获取文章列表", notes = "CMS管理API-获取文章列表接口")
    @GetMapping(value = "/article/list")
    public Result<List<ArticleDetailsResult>> articleList(CmsArticle cmsArticle){
        Result result = new Result();

        QueryWrapper<CmsArticle> wrapper = new QueryWrapper();
        wrapper.eq("is_show", 1); // 是否显示
        wrapper.eq("is_publish", 1); // 是否发布
        wrapper.eq("is_check", 0); // 是否审核
        wrapper.eq("check_state", 0); // 审核状态
        wrapper.eq("del_flag", 0); // 是否删除
        if(StringUtils.isNotEmpty(cmsArticle.getSecId())){
            wrapper.eq("sec_id", cmsArticle.getSecId());
        }

        List<ArticleDetailsResult> detailsResults = new ArrayList<>();
        try {
            List<CmsArticle> articleList = articleService.list(wrapper);
            for(CmsArticle article : articleList){
                ArticleDetailsResult details = new ArticleDetailsResult();
                BeanUtils.copyProperties(article,details );
                detailsResults.add(details);
            }
            result.success("查询成功！");
        } catch (Exception e){
//            logger.error("查询文章异常：", e);
            result.error500("查询失败，请稍后再试！");
        }
        result.setResult(detailsResults);

        return result;
    }

    /**
     * 文章详情
     * @return
     */
    @AutoLog(value = "CMS管理API-获取文章详情")
    @ApiOperation(value = "CMS管理API-获取文章详情", notes = "CMS管理API-获取文章详情接口")
    @ApiImplicitParam(value = "文章id", name = "id", paramType = "path", dataType = "Integer", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/article/{id}")
    public Result<ArticleDetailsResult> articleDetails(@PathVariable(name = "id") String id){
        Result result = new Result();
        try {
            CmsArticle article = articleService.getById(id);
            ArticleDetailsResult details = new ArticleDetailsResult();
            BeanUtils.copyProperties(article, details);
            result.setResult(details);
            result.success("查询成功！");
        } catch (Exception e){
//            logger.error("查询文章异常：", e);
            result.error500("查询失败，请稍后再试！");
        }
        return result;
    }

    /**
     * 栏目列表
     * @return
     */
    @AutoLog(value = "CMS管理API-获取栏目列表")
    @ApiOperation(value = "CMS管理API-获取栏目列表", notes = "CMS管理API-获取栏目列表接口")
    @GetMapping(value = "/section/list")
    public Result<List<SectionDetailsResult>> sectionList(CmsSection cmsSection){
        Result result = new Result();

        QueryWrapper<CmsSection> wrapper = new QueryWrapper();
        wrapper.eq("is_show", 1); // 是否显示
        wrapper.eq("is_check", 0); // 是否审核
        wrapper.eq("check_state", 0); // 审核状态
        wrapper.eq("del_flag", 0); // 是否删除
        if(StringUtils.isNotEmpty(cmsSection.getNameCode())){
            wrapper.eq("name_code",cmsSection.getNameCode());
        }

        List<SectionDetailsResult> detailsResults = new ArrayList<>();
        try {
            List<CmsSection> sectionList = sectionService.list(wrapper);
            for (CmsSection section : sectionList){

                SectionDetailsResult details = new SectionDetailsResult();

                BeanUtils.copyProperties(section,details);
                detailsResults.add(details);
            }
            result.success("查询成功！");
        } catch(Exception e) {
//            logger.error("查询栏目异常：", e);
            result.error500("查询失败，请稍后再试！");
        }
        result.setResult(detailsResults);
        return result;
    }

    /**
     * 栏目详情
     * @return
     */
    @AutoLog(value = "CMS管理API-获取栏目详情")
    @ApiOperation(value = "CMS管理API-获取栏目详情", notes = "CMS管理API-获取栏目详情接口")
    @ApiImplicitParam(value = "栏目id", name = "id", paramType = "path", dataType = "Integer", required = true, dataTypeClass = String.class)
    @GetMapping(value = "/section/{id}")
    public Result<SectionDetailsResult> sectionDetails(@PathVariable(name = "id") String id){
        Result result = new Result();
        SectionDetailsResult details = null;
        try {
            CmsSection section = sectionService.getById(id);
            details = new SectionDetailsResult();
            BeanUtils.copyProperties(section, details);
            result.success("查询成功！");
        } catch(Exception e) {
//            logger.error("查询栏目异常：", e);
            result.error500("查询失败，请稍后再试！");
        }
        result.setResult(details);
        return result;
    }


    /**
     * 查询栏目内容
     * @return
     */
    @AutoLog(value = "CMS管理API-获取栏目内容")
    @ApiOperation(value = "CMS管理API-获取栏目内容", notes = "CMS管理API-获取栏栏目内容")
    @ApiImplicitParam(value = "栏目id", name = "id", paramType = "path", dataType = "String", required = true, dataTypeClass = CmsSection.class)
    @GetMapping(value = "/section/getSectionAll")
    public Result<ArticleDetailsResult> sectionData(CmsSection cmsSection){
        Result result = new Result();
        QueryWrapper<CmsSection> wrapper = new QueryWrapper();
        wrapper.eq("is_show", 1); // 是否显示
        wrapper.eq("is_check", 0); // 是否审核
        wrapper.eq("check_state", 0); // 审核状态
        wrapper.eq("del_flag", 0); // 是否删除
        wrapper.eq("name_code",cmsSection.getNameCode());
        List<ArticleDetailsResult> details = new ArrayList<>();
        try {
            CmsSection section = sectionService.getOne(wrapper);
            List<CmsArticle> list = articleService.list(new QueryWrapper<CmsArticle>().eq("sec_id", section.getId()));
            for (CmsArticle cmsArticle : list) {
                ArticleDetailsResult articleDetailsResult=new ArticleDetailsResult();
                BeanUtils.copyProperties(cmsArticle,articleDetailsResult);
                details.add(articleDetailsResult);
            }
            result.success("查询成功！");
        } catch(Exception e) {
            result.error500("查询失败，请稍后再试！");
        }
        result.setResult(details);
        return result;
    }
}
