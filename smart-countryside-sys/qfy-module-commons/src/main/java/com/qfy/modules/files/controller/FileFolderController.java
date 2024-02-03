package com.qfy.modules.files.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.model.FolderTreeModel;
import com.qfy.modules.files.service.IFileFolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 文件夹管理
 * @Author: jeecg-boot
 * @Date:   2022-02-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="文件夹管理")
@RestController
@RequestMapping("/files/fileFolder")
public class FileFolderController extends JeecgController<FileFolder, IFileFolderService> {
	@Autowired
	private IFileFolderService fileFolderService;

	 /**
	  * 查询数据 查出所有部门,并以树结构数据格式响应给前端
	  * @return
	  */
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<FolderTreeModel>> queryTreeList(String refCode, String refType) {
		 Result<List<FolderTreeModel>> result = new Result<>();
		 try {
			 // 从内存中读取
			 List<FolderTreeModel> list = fileFolderService.queryTreeList(refCode,refType);
			 result.setResult(list);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }


	/**
	 * 分页列表查询
	 * @param fileFolder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文件夹管理-分页列表查询")
	@ApiOperation(value="文件夹管理-分页列表查询", notes="文件夹管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FileFolder fileFolder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FileFolder> queryWrapper = QueryGenerator.initQueryWrapper(fileFolder, req.getParameterMap());
		Page<FileFolder> page = new Page<FileFolder>(pageNo, pageSize);
		IPage<FileFolder> pageList = fileFolderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 * @param fileFolder
	 * @return
	 */
	@AutoLog(value = "文件夹管理-添加")
	@ApiOperation(value="文件夹管理-添加", notes="文件夹管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FileFolder fileFolder) {
		try {
			fileFolderService.saveSubFolder(fileFolder);
			return Result.OK("添加成功！");
		}catch (Exception e){
			return Result.error(e.getMessage());
		}
	}

	/**
	 * 编辑
	 *
	 * @param fileFolder
	 * @return
	 */
	@AutoLog(value = "文件夹管理-编辑")
	@ApiOperation(value="文件夹管理-编辑", notes="文件夹管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FileFolder fileFolder) {
		fileFolderService.updateById(fileFolder);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件夹管理-通过id删除")
	@ApiOperation(value="文件夹管理-通过id删除", notes="文件夹管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			fileFolderService.deleteFolderAndFiles(id);
			return Result.OK("删除成功!");
		}catch (Exception e){
			e.printStackTrace();
			return Result.error(e.getMessage());
		}

	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文件夹管理-批量删除")
	@ApiOperation(value="文件夹管理-批量删除", notes="文件夹管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fileFolderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件夹管理-通过id查询")
	@ApiOperation(value="文件夹管理-通过id查询", notes="文件夹管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FileFolder fileFolder = fileFolderService.getById(id);
		return Result.OK(fileFolder);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fileFolder
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, FileFolder fileFolder) {
      return super.exportXls(request, fileFolder, FileFolder.class, "文件夹管理");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, FileFolder.class);
  }

}
