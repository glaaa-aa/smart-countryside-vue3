package com.qfy.modules.files.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.model.FolderRefTypeEnum;
import com.qfy.modules.files.service.IFilesManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 上传文件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Api(tags="上传文件管理")
@RestController
@RequestMapping("/files/filesManager")
@Slf4j
public class FilesManagerController extends JeecgController<FilesManager, IFilesManagerService> {
	 @Autowired
	 private IFilesManagerService filesManagerService;

	 @Value("${jeecg.path.upload}")
	 private String uploadRootPath;

	/**
	 * 分页列表查询
	 * @param filesManager
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "上传文件管理-分页列表查询")
	@ApiOperation(value="上传文件管理-分页列表查询", notes="上传文件管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FilesManager filesManager,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
								   HttpServletRequest req) {
		if(filesManager == null || filesManager.getFolderId() == null){
			filesManager.setFolderId("-1");
		}
		QueryWrapper<FilesManager> queryWrapper = QueryGenerator.initQueryWrapper(filesManager, req.getParameterMap());
		Page<FilesManager> page = new Page<FilesManager>(pageNo, pageSize);
		IPage<FilesManager> pageList = filesManagerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 根据项目查询项目下所有文件
	  * @param fileFolder
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "根据项目查询项目下所有文件-分页列表查询")
	 @ApiOperation(value="根据项目查询项目下所有文件", notes="根据项目查询项目下所有文件")
	 @GetMapping(value = "/queryPageListByFolder")
	 public Result<?> queryPageListByFolder(FileFolder fileFolder,
											@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											@RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
											HttpServletRequest req) {
		 fileFolder.setRefType(FolderRefTypeEnum.project.getCode());
//		 QueryWrapper<FileFolder> queryWrapper = QueryGenerator.initQueryWrapper(fileFolder, req.getParameterMap());
		 Page<FilesManager> page = new Page<FilesManager>(pageNo, pageSize);
		 IPage<FilesManager> pageList = filesManagerService.queryListByRefCode(page,fileFolder);
		 return Result.OK(pageList);
	 }

	/**
	 * 添加
	 * @param filesManager
	 * @return
	 */
	@AutoLog(value = "上传文件管理-添加")
	@ApiOperation(value="上传文件管理-添加", notes="上传文件管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FilesManager filesManager) {
//		filesManagerService.save(filesManager);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 * @param filesManager
	 * @return
	 */
	@AutoLog(value = "上传文件管理-编辑")
	@ApiOperation(value="上传文件管理-编辑", notes="上传文件管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FilesManager filesManager) {
//		filesManagerService.updateById(filesManager);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上传文件管理-通过id删除")
	@ApiOperation(value="上传文件管理-通过id删除", notes="上传文件管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		filesManagerService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "上传文件管理-批量删除")
	@ApiOperation(value="上传文件管理-批量删除", notes="上传文件管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.filesManagerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上传文件管理-通过id查询")
	@ApiOperation(value="上传文件管理-通过id查询", notes="上传文件管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FilesManager filesManager = filesManagerService.getById(id);
		if(filesManager==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(filesManager);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param filesManager
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FilesManager filesManager) {
        return super.exportXls(request, filesManager, FilesManager.class, "上传文件管理");
    }

    /**
	* 通过excel导入数据
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FilesManager.class);
    }

}
