package com.qfy.modules.subsidy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.subsidy.entity.AssistPerson;
import com.qfy.modules.subsidy.service.IAssistPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.config.StaticConfig;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 帮扶联系人
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Api(tags="帮扶联系人")
@RestController
@RequestMapping("/family/assistPerson")
@Slf4j
public class AssistPersonController extends JeecgController<AssistPerson, IAssistPersonService> {

	@Resource
	private IAssistPersonService assistPersonService;
	@Resource
	private ISysBaseAPI sysBaseAPI;


	/**
	 * 分页列表查询
	 *
	 * @param assistPerson
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "帮扶联系人-分页列表查询")
	@ApiOperation(value="帮扶联系人-分页列表查询", notes="帮扶联系人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssistPerson>> queryPageList(AssistPerson assistPerson,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) {
		QueryWrapper<AssistPerson> queryWrapper = QueryGenerator.initQueryWrapper(assistPerson, req.getParameterMap());
		Page<AssistPerson> page = new Page<AssistPerson>(pageNo, pageSize);
		IPage<AssistPerson> pageList = assistPersonService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param person
	 * @return
	 */
	@AutoLog(value = "帮扶联系人-添加")
	@ApiOperation(value="帮扶联系人-添加", notes="帮扶联系人-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AssistPerson person) {
		assistPersonService.save(person);

		StaticConfig config = SpringContextUtils.getBean(StaticConfig.class);
		String accessRole = config.getLinkPersonAccessRole();
		LoginUser user = new LoginUser();
		user.setUsername(person.getTelephone());
		user.setRealname(person.getRealname());
		user.setTelephone(person.getTelephone());
		user.setPassword(person.getTelephone());
		user.setPhone(person.getTelephone());
		user.setId(person.getId());
		user.setRelTenantIds("1");

		sysBaseAPI.saveUser(user, accessRole,null);

		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param assistPerson
	 * @return
	 */
	@AutoLog(value = "帮扶联系人-编辑")
	@ApiOperation(value="帮扶联系人-编辑", notes="帮扶联系人-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AssistPerson assistPerson) {
		assistPersonService.updateById(assistPerson);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "帮扶联系人-通过id删除")
	@ApiOperation(value="帮扶联系人-通过id删除", notes="帮扶联系人-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id") String id) {
		assistPersonService.removeById(id);
		sysBaseAPI.delUser(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "帮扶联系人-批量删除")
	@ApiOperation(value="帮扶联系人-批量删除", notes="帮扶联系人-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids") String ids) {
		assistPersonService.removeByIds(Arrays.asList(ids.split(",")));
		sysBaseAPI.batchDelUser(ids);
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "帮扶联系人-通过id查询")
	@ApiOperation(value="帮扶联系人-通过id查询", notes="帮扶联系人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssistPerson> queryById(@RequestParam(name="id",required=true) String id) {
		AssistPerson assistPerson = assistPersonService.getById(id);
		if(assistPerson==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(assistPerson);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assistPerson
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssistPerson assistPerson) {
        return super.exportXls(request, assistPerson, AssistPerson.class, "帮扶联系人");
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
        return super.importExcel(request, response, AssistPerson.class);
    }

}
