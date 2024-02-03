package com.qfy.modules.city3d.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.city3d.po.SysApp;
import com.qfy.modules.city3d.service.SysAppService;
import com.qfy.modules.city3d.util.CharacterUtils;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sysApp")
public class SysAppController extends JeecgController<SysApp, SysAppService> {
	@Autowired
	private SysAppService sysAppService;

	@RequestMapping("/getSysApp")
	public @ResponseBody List<SysApp> getSysApp() throws Exception {
		List<SysApp> result = sysAppService.getSysApp();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
			@RequestParam("appId") String appId,
			@RequestParam("appName") String appName,
			@RequestParam("appUiMode") String appUiMode,
			@RequestParam("appPos") String appPos,
			@RequestParam("appImage") String appImage,
			@RequestParam("appTerrain") String appTerrain,
			HttpServletRequest request) throws Exception {
		SysApp sysApp = new SysApp();
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/logo/"+appName+uid+extName;
					sysApp.setAppLogo(fileName);
					String path = request.getServletContext().getRealPath("").replace("\\city3dAPI","") + "/city3dfile/" + fileName;
			        File newFile = new File(path);
			    	//父级目录不在就创建一个
	                if (!newFile.getParentFile().exists()){
	                	newFile.mkdirs();
	                }
			        file.transferTo(newFile);//通过CommonsMultipartFile的方法直接写文件
			    } catch (IllegalStateException e) {
			        e.printStackTrace();
			    }
			}
		}
		sysApp.setAppId(uid);
		sysApp.setAppName(appName);
		sysApp.setAppPos(appPos);
		sysApp.setAppUiMode(appUiMode);
		sysApp.setAppImage(appImage);
		sysApp.setAppTerrain(appTerrain);
		int result = sysAppService.insert(sysApp);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestParam("files") MultipartFile[] files,
			@RequestParam("appId") String appId,
			@RequestParam("appName") String appName,
			@RequestParam("appUiMode") String appUiMode,
			@RequestParam("appPos") String appPos,
			@RequestParam("appLogo") String appLogo,
			@RequestParam("appImage") String appImage,
			@RequestParam("appTerrain") String appTerrain,
			HttpServletRequest request) throws Exception {
		SysApp sysApp = new SysApp();
		sysApp.setAppLogo(appLogo);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/logo/"+appName+appId+extName;
					sysApp.setAppLogo(fileName);
					String path = request.getServletContext().getRealPath("").replace("\\city3dAPI","") + "/city3dfile/" + fileName;
			        File newFile = new File(path);
			    	//父级目录不在就创建一个
	                if (!newFile.getParentFile().exists()){
	                	newFile.mkdirs();
	                }
			        file.transferTo(newFile);//通过CommonsMultipartFile的方法直接写文件
			    } catch (IllegalStateException e) {
			        e.printStackTrace();
			    }
			}
		}
		sysApp.setAppId(appId);
		sysApp.setAppName(appName);
		sysApp.setAppPos(appPos);
		sysApp.setAppUiMode(appUiMode);
		sysApp.setAppImage(appImage);
		sysApp.setAppTerrain(appTerrain);
		int result = sysAppService.updateByPrimaryKey(sysApp);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody SysApp sysApp) throws Exception {
		if(sysApp.getAppLogo()!=null&&sysApp.getAppLogo()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+sysApp.getAppLogo();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = sysAppService.deleteByPrimaryKey(sysApp.getAppId());
		return result;
	}

	@RequestMapping("/getAppById")
	public @ResponseBody SysApp getAppById(@RequestBody SysApp sysApp) throws Exception {
		SysApp result = sysAppService.getAppById(sysApp.getAppId());
		return result;
	}

	/**
	 * 分页列表查询
	 *
	 * @param sysApp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "前端应用-分页列表查询")
	@ApiOperation(value="前端应用-分页列表查询", notes="前端应用-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SysApp>> queryPageList(SysApp sysApp,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											   HttpServletRequest req) {
		QueryWrapper<SysApp> queryWrapper = QueryGenerator.initQueryWrapper(sysApp, req.getParameterMap());
		Page<SysApp> page = new Page<SysApp>(pageNo, pageSize);
		IPage<SysApp> pageList = sysAppService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param sysApp
	 * @return
	 */
	@AutoLog(value = "前端应用-添加")
	@ApiOperation(value="前端应用-添加", notes="前端应用-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SysApp sysApp) {
		sysAppService.save(sysApp);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param sysApp
	 * @return
	 */
	@AutoLog(value = "前端应用-编辑")
	@ApiOperation(value="前端应用-编辑", notes="前端应用-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SysApp sysApp) {
		sysAppService.updateById(sysApp);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "前端应用-通过id删除")
	@ApiOperation(value="前端应用-通过id删除", notes="前端应用-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		sysAppService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "前端应用-批量删除")
	@ApiOperation(value="前端应用-批量删除", notes="前端应用-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysAppService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "前端应用-通过id查询")
	@ApiOperation(value="前端应用-通过id查询", notes="前端应用-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SysApp> queryById(@RequestParam(name="id",required=true) String id) {
		SysApp sysApp = sysAppService.getById(id);
		if(sysApp==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sysApp);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param sysApp
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, SysApp sysApp) {
		return super.exportXls(request, sysApp, SysApp.class, "前端应用");
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
		return super.importExcel(request, response, SysApp.class);
	}
}
