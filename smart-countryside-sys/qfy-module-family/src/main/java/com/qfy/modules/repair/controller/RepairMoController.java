package com.qfy.modules.repair.controller;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.repair.entity.RecordVo;
import com.qfy.modules.repair.entity.RepairMo;
import com.qfy.modules.repair.enums.RepairMoStateEnum;
import com.qfy.modules.repair.service.IRepairMoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 维修工单
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="维修工单")
@RestController
@RequestMapping("/repair/repairMo")
public class RepairMoController extends JeecgController<RepairMo, IRepairMoService> {
	@Resource
	private IRepairMoService repairMoService;

	@Resource
	private ISysBaseAPI sysBaseAPI;

	/**
	 * 分页列表查询
	 * @param repairMo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "隐患任务-分页列表查询")
	@ApiOperation(value="隐患任务-分页列表查询", notes="隐患任务-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "device/repair/RepairMoList")
	public Result<?> queryPageList(RepairMo repairMo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepairMo> queryWrapper = QueryGenerator.initQueryWrapper(repairMo, req.getParameterMap());
		Page<RepairMo> page = new Page<>(pageNo, pageSize);
		IPage<RepairMo> pageList = repairMoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 分页列表查询
	  * @param repairMo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "我的隐患任务-分页列表查询")
	 @ApiOperation(value="我的隐患任务-分页列表查询", notes="我的隐患任务-分页列表查询")
	 @GetMapping(value = "/mylist")
//	 @PermissionData(pageComponent = "device/repair/RepairMoList")
	 public Result<?> queryPageMylist(RepairMo repairMo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 repairMo.setDutyManId(user.getUsername());
		 QueryWrapper<RepairMo> queryWrapper = QueryGenerator.initQueryWrapper(repairMo, req.getParameterMap());
		 Page<RepairMo> page = new Page<>(pageNo, pageSize);
		 IPage<RepairMo> pageList = repairMoService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }

	 /**
	  * 分页列表查询
	  * @param repairMo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "待安排任务-分页列表查询")
	 @ApiOperation(value="待安排任务-分页列表查询", notes="待安排任务-分页列表查询")
	 @GetMapping(value = "/daplist")
//	 @PermissionData(pageComponent = "device/repair/RepairMoList")
	 public Result<?> queryPageDapList(RepairMo repairMo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
//		 repairMo.setDutyManId("");
		 Page<RepairMo> page = new Page<>(pageNo, pageSize);
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 repairMo.setWcCode(user.getDistrictCode());
//		 System.out.println(DateUtils.getAddMonth(-6));
//		 repairMo.setMoState(RepairMoStateEnum.REPAIR_SUBMIT.getValue());
		 IPage<RepairMo> pageList = repairMoService.queryRepairMoParam(page, repairMo);
		 return Result.OK(pageList);
	 }


	 /**
	  * 分页列表查询
	  * @param repairMo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "待接收任务-分页列表查询")
	 @ApiOperation(value="待接收任务-分页列表查询", notes="待接收任务-分页列表查询")
	 @GetMapping(value = "/djslist")
//	 @PermissionData(pageComponent = "device/repair/RepairMoList")
	 public Result<?> queryPageDjsList(RepairMo repairMo,
									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									   HttpServletRequest req) {
//		 repairMo.setDutyManId("");
		 QueryWrapper<RepairMo> queryWrapper = QueryGenerator.initQueryWrapper(repairMo, req.getParameterMap());
		 Page<RepairMo> page = new Page<>(pageNo, pageSize);
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 queryWrapper.eq("duty_man_id",user.getUsername());
		 queryWrapper.eq("mo_state", RepairMoStateEnum.NEW.getValue());
		 IPage<RepairMo> pageList = repairMoService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }


	 /**
	  * 分页列表查询
	  * @param repairMo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "待处理任务-分页列表查询")
	 @ApiOperation(value="待处理任务-分页列表查询", notes="待处理任务-分页列表查询")
	 @GetMapping(value = "/dcllist")
//	 @PermissionData(pageComponent = "device/repair/RepairMoList")
	 public Result<?> queryPageDclList(RepairMo repairMo,
									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									   HttpServletRequest req) {
//		 repairMo.setDutyManId("");
		 QueryWrapper<RepairMo> queryWrapper = QueryGenerator.initQueryWrapper(repairMo, req.getParameterMap());
		 Page<RepairMo> page = new Page<RepairMo>(pageNo, pageSize);
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 queryWrapper.eq("duty_man_id",user.getUsername());
		 queryWrapper.eq("mo_state", RepairMoStateEnum.CHECK_PASS.getValue());
		 IPage<RepairMo> pageList = repairMoService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }

	 /**
	  * 分页列表查询
	  * @param repairMo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "提交待审核任务-分页列表查询")
	 @ApiOperation(value="提交待审核任务-分页列表查询", notes="提交待审核任务-分页列表查询")
	 @GetMapping(value = "/tjshlist")
//	 @PermissionData(pageComponent = "device/repair/RepairMoList")
	 public Result<?> queryPageTjshlList(RepairMo repairMo,
									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									   HttpServletRequest req) {
//		 QueryWrapper<RepairMo> queryWrapper = QueryGenerator.initQueryWrapper(repairMo, req.getParameterMap());
		 Page<RepairMo> page = new Page<RepairMo>(pageNo, pageSize);
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 repairMo.setWcCode(user.getDistrictCode());
		 repairMo.setMoState(RepairMoStateEnum.REPAIR_SUBMIT.getValue());
		 IPage<RepairMo> pageList = repairMoService.queryRepairMoParam(page, repairMo);
		 return Result.OK(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param repairMo
	 * @return
	 */
	@AutoLog(value = "维修工单-添加")
	@ApiOperation(value="维修工单-添加", notes="维修工单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepairMo repairMo) {
		repairMo.setMoCode(String.valueOf(System.currentTimeMillis()));
		repairMo.setIsOuts(CommonConstant.STATUS_NORMAL);
		repairMo.setDelFlag(CommonConstant.DEL_FLAG_0);
		repairMoService.save(repairMo);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param repairMo
	 * @return
	 */
	@AutoLog(value = "维修工单-编辑")
	@ApiOperation(value="维修工单-编辑", notes="维修工单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepairMo repairMo) {
		try {
			Result result = repairMoService.updateByMo(repairMo);
			return result;
		}catch (Exception e){
			return Result.OK(e.getMessage());
		}
	}


	 /**
	  * 添加任务单并派工
	  * @param recordVo
	  * @return
	  */
	 @AutoLog(value = "隐患批量添加工单")
	 @ApiOperation(value="隐患批量添加工单", notes="隐患批量添加工单")
	 @PutMapping(value = "/addMoAndTask")
	 public Result<?> addMoAndTask(@RequestBody RecordVo recordVo) {
	 	if(recordVo == null || StringUtils.isEmpty(recordVo.getId())){
			return Result.OK("参数隐患id为空!");
		}
		try {
			repairMoService.addRepairMoAndTask(recordVo);
			return Result.OK("隐患任务添加完成!");
		}catch (Exception e){
			return Result.OK(e.getMessage());
		}
	 }

	 /**
	  * 编辑
	  *
	  * @param repairMo
	  * @return
	  */
	 @AutoLog(value = "拒绝或者领取任务")
	 @ApiOperation(value="拒绝或者领取任务", notes="拒绝或者领取任务")
	 @PutMapping(value = "/acceptOrRefuseMo")
	 public Result<?> acceptOrRefuseMo(@RequestBody RepairMo repairMo) {
		 try {
			 repairMoService.acceptOrRefuseMo(repairMo);
			 return Result.OK("操作成功!");
		 }catch (Exception e){
			 return Result.OK(e.getMessage());
		 }
	 }


	 /**
	  * 编辑
	  *
	  * @param repairMo
	  * @return
	  */
	 @AutoLog(value = "提交任务")
	 @ApiOperation(value="提交任务", notes="提交任务")
	 @PutMapping(value = "/updateSubmit")
	 public Result<?> updateSubmit(@RequestBody RepairMo repairMo) {
		 try {
			 repairMoService.updateSub(repairMo);
			 return Result.OK("提交任务成功!");
		 }catch (Exception e){
			 return Result.OK(e.getMessage());
		 }
	 }


	 /**
	  * 关闭任务
	  *
	  * @param repairMo
	  * @return
	  */
	 @AutoLog(value = "关闭任务")
	 @ApiOperation(value="关闭任务", notes="关闭任务")
	 @PutMapping(value = "/updateClose")
	 public Result<?> updateClose(@RequestBody RepairMo repairMo) {
		 try {
			 repairMoService.updateClose(repairMo);
			 return Result.OK("关闭任务成功!");
		 }catch (Exception e){
			 return Result.OK(e.getMessage());
		 }
	 }
	 /**
	  * 审核任务单/任务单完成
	  * @param repairMo
	  * @return
	  */
	 @AutoLog(value = "审核任务单/任务单完成")
	 @ApiOperation(value="审核任务单/任务单完成", notes="审核任务单/任务单完成")
	 @PutMapping(value = "/finishRepairMo")
	 public Result<?> finishRepairMo(@RequestBody RepairMo repairMo) {
		 if(repairMo == null){
			 return Result.OK("参数为空或未派工!");
		 }
		 try {
//			 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//			 recordVo.setShenpMan(user.getRealname());
//			 recordVo.setShenpManId(user.getUsername());
			 repairMoService.finishRepairMo(repairMo);
			 return Result.OK("任务单完成!");
		 }catch (Exception e){
			 return Result.OK(e.getMessage());
		 }
	 }


	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "维修工单-通过id删除")
	@ApiOperation(value="维修工单-通过id删除", notes="维修工单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repairMoService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "维修工单-批量删除")
	@ApiOperation(value="维修工单-批量删除", notes="维修工单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.repairMoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repairMo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepairMo repairMo) {
      return super.exportXls(request, repairMo, RepairMo.class, "维修工单");
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
      return super.importExcel(request, response, RepairMo.class);
  }

}
