package com.qfy.modules.apppage.element.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.apppage.element.entity.PageElement;
import com.qfy.modules.apppage.element.service.IPageElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.SelectTreeModel;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 页面元素管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Api(tags="页面元素管理")
@RestController
@RequestMapping("/apppage/element/pageElement")
@Slf4j
public class PageElementController extends JeecgController<PageElement, IPageElementService>{
	@Autowired
	private IPageElementService pageElementService;

	/**
	 * 分页列表查询
	 *
	 * @param pageElement
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "页面元素管理-分页列表查询")
	@ApiOperation(value="页面元素管理-分页列表查询", notes="页面元素管理-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<IPage<PageElement>> queryPageList(PageElement pageElement,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String hasQuery = req.getParameter("hasQuery");
        if(hasQuery != null && "true".equals(hasQuery)){
            QueryWrapper<PageElement> queryWrapper =  QueryGenerator.initQueryWrapper(pageElement, req.getParameterMap());
            List<PageElement> list = pageElementService.queryTreeListNoPage(queryWrapper);
            IPage<PageElement> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        }else{
            String parentId = pageElement.getPid();
            if (oConvertUtils.isEmpty(parentId)) {
                parentId = "0";
            }
            pageElement.setPid(null);
            QueryWrapper<PageElement> queryWrapper = QueryGenerator.initQueryWrapper(pageElement, req.getParameterMap());
            // 使用 eq 防止模糊查询
            queryWrapper.eq("pid", parentId);
            Page<PageElement> page = new Page<PageElement>(pageNo, pageSize);
            IPage<PageElement> pageList = pageElementService.page(page, queryWrapper);
            return Result.OK(pageList);
        }
	}

	 /**
	  * 【vue3专用】加载节点的子数据
	  *
	  * @param pid
	  * @return
	  */
	 @RequestMapping(value = "/loadTreeChildren", method = RequestMethod.GET)
	 public Result<List<SelectTreeModel>> loadTreeChildren(@RequestParam(name = "pid") String pid) {
		 Result<List<SelectTreeModel>> result = new Result<>();
		 try {
			 List<SelectTreeModel> ls = pageElementService.queryListByPid(pid);
			 result.setResult(ls);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 e.printStackTrace();
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 【vue3专用】加载一级节点/如果是同步 则所有数据
	  *
	  * @param async
	  * @param pcode
	  * @return
	  */
	 @RequestMapping(value = "/loadTreeRoot", method = RequestMethod.GET)
	 public Result<List<SelectTreeModel>> loadTreeRoot(@RequestParam(name = "async") Boolean async, @RequestParam(name = "pcode") String pcode) {
		 Result<List<SelectTreeModel>> result = new Result<>();
		 try {
			 List<SelectTreeModel> ls = pageElementService.queryListByCode(pcode);
			 if (!async) {
				 loadAllChildren(ls);
			 }
			 result.setResult(ls);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 e.printStackTrace();
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 【vue3专用】递归求子节点 同步加载用到
	  *
	  * @param ls
	  */
	 private void loadAllChildren(List<SelectTreeModel> ls) {
		 for (SelectTreeModel tsm : ls) {
			 List<SelectTreeModel> temp = pageElementService.queryListByPid(tsm.getKey());
			 if (temp != null && temp.size() > 0) {
				 tsm.setChildren(temp);
				 loadAllChildren(temp);
			 }
		 }
	 }

	 /**
      * 获取子数据
      * @param pageElement
      * @param req
      * @return
      */
	//@AutoLog(value = "页面元素管理-获取子数据")
	@ApiOperation(value="页面元素管理-获取子数据", notes="页面元素管理-获取子数据")
	@GetMapping(value = "/childList")
	public Result<IPage<PageElement>> queryPageList(PageElement pageElement,HttpServletRequest req) {
		QueryWrapper<PageElement> queryWrapper = QueryGenerator.initQueryWrapper(pageElement, req.getParameterMap());
		List<PageElement> list = pageElementService.list(queryWrapper);
		IPage<PageElement> pageList = new Page<>(1, 10, list.size());
        pageList.setRecords(list);
		return Result.OK(pageList);
	}

    /**
      * 批量查询子节点
      * @param parentIds 父ID（多个采用半角逗号分割）
      * @return 返回 IPage
      * @param parentIds
      * @return
      */
	//@AutoLog(value = "页面元素管理-批量获取子数据")
    @ApiOperation(value="页面元素管理-批量获取子数据", notes="页面元素管理-批量获取子数据")
    @GetMapping("/getChildListBatch")
    public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
        try {
            QueryWrapper<PageElement> queryWrapper = new QueryWrapper<>();
            List<String> parentIdList = Arrays.asList(parentIds.split(","));
            queryWrapper.in("pid", parentIdList);
            List<PageElement> list = pageElementService.list(queryWrapper);
            IPage<PageElement> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("批量查询子节点失败：" + e.getMessage());
        }
    }
	
	/**
	 *   添加
	 *
	 * @param pageElement
	 * @return
	 */
	@AutoLog(value = "页面元素管理-添加")
	@ApiOperation(value="页面元素管理-添加", notes="页面元素管理-添加")
//    @RequiresPermissions("apppage.element:qfy_app_page_element:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PageElement pageElement) {
		pageElementService.addPageElement(pageElement);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pageElement
	 * @return
	 */
	@AutoLog(value = "页面元素管理-编辑")
	@ApiOperation(value="页面元素管理-编辑", notes="页面元素管理-编辑")
//    @RequiresPermissions("apppage.element:qfy_app_page_element:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PageElement pageElement) {
		pageElementService.updatePageElement(pageElement);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "页面元素管理-通过id删除")
	@ApiOperation(value="页面元素管理-通过id删除", notes="页面元素管理-通过id删除")
//    @RequiresPermissions("apppage.element:qfy_app_page_element:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		pageElementService.deletePageElement(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "页面元素管理-批量删除")
	@ApiOperation(value="页面元素管理-批量删除", notes="页面元素管理-批量删除")
//    @RequiresPermissions("apppage.element:qfy_app_page_element:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pageElementService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "页面元素管理-通过id查询")
	@ApiOperation(value="页面元素管理-通过id查询", notes="页面元素管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PageElement> queryById(@RequestParam(name="id",required=true) String id) {
		PageElement pageElement = pageElementService.getById(id);
		if(pageElement==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(pageElement);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param pageElement
    */
//    @RequiresPermissions("apppage.element:qfy_app_page_element:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PageElement pageElement) {
		return super.exportXls(request, pageElement, PageElement.class, "页面元素管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("apppage.element:qfy_app_page_element:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		return super.importExcel(request, response, PageElement.class);
    }

}
