package com.qfy.modules.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.api.domain.FamilyRoster;
import com.qfy.modules.api.service.IFamilyRosterService;
import com.qfy.modules.family.entity.FamilyMember;
import javax.annotation.Resource;
import org.jeecg.common.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/18 15:24
 */
@RestController
@RequestMapping("/api/resident")
public class FamilyMemerController {

    public static final Logger logger = LoggerFactory.getLogger(FamilyMemerController.class);

    @Resource
    private IFamilyRosterService rosterService;

    /**
     * 获取农户户主列表
     *
     * @param name 户主姓名（可选）
     * @param orgCode 组织代码
     * @param pageNo 页码（默认为1）
     * @param pageSize 页大小（默认为10）
     * @return 结果对象
     */
    @GetMapping("/roster")
    public Result getRosterList(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "orgCode") String orgCode,
                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page(pageNo, pageSize);
        IPage<FamilyRoster> result =  rosterService.queryList(page, name, orgCode);
        return Result.OK(result);
    }

    /**
     * 获取家庭成员列表---全部居民列表
     *
     * @param name 成员姓名
     * @param orgCode 组织代码
     * @param idCard 身份证号码
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return 结果对象
     */
    @GetMapping("/family/members")
    public Result getFamilyMembers(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "orgCode") String orgCode,
                                  @RequestParam(name = "idCard", required = false) String idCard,
                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Page page = new Page(pageNo, pageSize);
        IPage<FamilyRoster> result =  rosterService.queryList(page, name, orgCode, idCard);
        return Result.OK(result);
    }

    @GetMapping("/family/members/details")
    public Result getFamilyMembersDetails(@RequestParam(name = "idCard") String idCard) {

        FamilyMember member = rosterService.queryByIdCard(idCard);
        return Result.OK(member);
    }
}
