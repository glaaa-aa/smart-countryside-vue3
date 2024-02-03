package com.qfy.modules.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.api.domain.Points;
import com.qfy.modules.api.domain.PointsHistory;
import com.qfy.modules.api.domain.PointsRanking;
import com.qfy.modules.api.domain.PointsRecord;
import com.qfy.modules.api.service.IPointsService;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分相关接口
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/16 17:45
 */
@RestController
@RequestMapping("/api/resident")
public class PointsController {

    public static final Logger logger = LoggerFactory.getLogger(PointsController.class);


    @Resource
    private ISysBaseAPI sysBaseAPI;
    @Resource
    private IPointsService pointsService;

    /**
     * 查询用户的积分列表
     * @param uid 用户ID（可选）
     * @param orgCode 组织代码
     * @param pageNo 页号（默认为1）
     * @param pageSize 每页数量（默认为10）
     * @return 结果对象
     */
    @GetMapping("/integral/list")
    public Result getPoints(@RequestParam(name = "uid", required = false) String uid,
                            @RequestParam(name = "orgCode") String orgCode,
                            @RequestParam(name = "realName", required = false) String realName,
                            @RequestParam(name = "familyNo", required = false) String familyNo,
                            @RequestParam(name = "telephone", required = false) String telephone,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        LoginUser loginUser;
        if (StringUtils.isBlank(uid)) {
            loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        } else {
            loginUser = sysBaseAPI.getUserById(uid);
        }

        if (loginUser == null) {
            return Result.error("用户不存在或者未登录。");
        }

        /*if (!StringUtils.equals(orgCode, loginUser.getOrgCode())){
            logger.error("积分获取失败，用户orgCode[{}]与请求参数orgCode[{}]不一致.",orgCode,loginUser.getOrgCode());
            return Result.error("积分获取失败");
        }*/

        IPage<PointsRanking> page = new Page<>(pageNo,pageSize);
        IPage<PointsRanking> result;
        // 村委角色
        if (loginUser.getUserIdentity() == 2){
            result = pointsService.getPointsPages(page, realName, familyNo,telephone, orgCode);
        } else {
            result = pointsService.getPointsPages(page,realName, loginUser.getId(), telephone, orgCode);
        }
        return Result.ok(result);
    }

    /**
     * 查询用户积分历史记录
     * @param orgCode 组织代码
     * @param realName 实名认证姓名
     * @param familyNo 家庭号
     * @param telephone 电话号码
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return 结果对象
     */
    @GetMapping("/integral/history")
    public Result getHistory(@RequestParam(name = "orgCode") String orgCode,
                             @RequestParam(name = "realName", required = false) String realName,
                             @RequestParam(name = "familyNo", required = false) String familyNo,
                             @RequestParam(name = "telephone", required = false) String telephone,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        IPage<PointsHistory> page = new Page<>(pageNo,pageSize);
        IPage<PointsHistory> result = pointsService.getHistoryPages(page,realName, familyNo, telephone, orgCode);
        return Result.ok(result);
    }

    /**
     * 获取积分记录列表
     * @param familyNo：家庭号码
     * @return Result：返回结果对象
     */
    @GetMapping("/integral/details")
    public Result getPointsDetails(@RequestParam(name = "familyNo") String familyNo,
                                 @RequestParam(name = "month", required = false) String month,
                                 @RequestParam(name = "category", required = false) Integer category,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        IPage<PointsRecord> page = new Page<>(pageNo,pageSize);
        IPage<PointsRecord> result = pointsService.getPointsDetails(page,familyNo,month, category);
        return Result.ok(result);
    }

    /**
     * 获取积分兑换列表
     * @param familyNo 家庭号
     * @param orgCode 组织码
     * @param pageNo 页号
     * @param pageSize 页面大小
     * @return 结果对象
     */
    @GetMapping("/integral/exchange/list")
    public Result getPointsTotal(@RequestParam(name = "familyNo", required = false) String familyNo,
                                @RequestParam(name = "orgCode", required = false) String orgCode,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        // TODO 目前暂未有此业务需要，此接口待实现

        return Result.OK();
    }

    /**
     * 获取积分排名
     *
     * @param familyNo 家庭号码（可选）
     * @param orgCode 组织代码
     * @param pageNo 页码（默认为1）
     * @param pageSize 页面大小（默认为10）
     * @return 结果对象
     */
    @GetMapping("/integral/ranking")
    public Result getPointsRanking(@RequestParam(name = "familyNo", required = false) String familyNo,
                                    @RequestParam(name = "orgCode") String orgCode,
                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Page<PointsRanking> page = new Page<>(pageNo,pageSize);
        IPage<PointsRanking> result = pointsService.getPointsRankings(page,familyNo, orgCode);
        return Result.OK(result);
    }

    /**
     * 添加积分--积分填报
     * @param points 积分信息
     * @return 结果
     */
    @PostMapping("/integral/add")
    public Result addPoints(@RequestBody Points points) {

        // 检查积分类别是否为空
        if (StringUtils.isBlank(points.getCategory())){
            return Result.error("积分类别不能为空");
        }

        // 检查积分值是否为空
        if (StringUtils.isBlank(points.getScore())){
            return Result.error("积分值不能为空");
        }

        // 检查积分项是否为空
        if (StringUtils.isBlank(points.getCkId())){
            return Result.error("积分项不能为空");
        }

        // 检查农户编号是否为空
        if (StringUtils.isBlank(points.getFamilyNo())){
            return Result.error("农户编号不能为空");
        }

        // 调用积分服务类添加积分
        Result result = pointsService.addPoints(points);
        return result;
    }


//    /**
//     * 获取积分项列表
//     * @param category 积分类别
//     * @return 积分项列表结果对象
//     */
//    @GetMapping("/integral/items")
//    public Result getPointsItems(@RequestParam(name = "category") Integer category) {
//        return pointsService.getPointsItems(category);
//    }
}
