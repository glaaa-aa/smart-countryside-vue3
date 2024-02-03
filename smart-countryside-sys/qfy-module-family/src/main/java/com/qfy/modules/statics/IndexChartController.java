package com.qfy.modules.statics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.family.entity.VillageRanking;
import com.qfy.modules.family.service.IFamilyBaseService;
import com.qfy.modules.family.service.IFamilyMemberService;
import java.util.List;
import javax.annotation.Resource;
import org.jeecg.common.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页图表统计
 * @author lanls
 * @version v1.0.0
 * @since 2023/11/29 17:42
 */
@RestController
@RequestMapping("/index/chart")
public class IndexChartController {

    public static final Logger logger = LoggerFactory.getLogger(IndexChartController.class);
    @Resource
    private IFamilyBaseService familyBaseService;
    @Resource
    private IFamilyMemberService familyMemberService;

    @GetMapping("/indexChartData")
    public Result indexChartData() {
        List<VillageRanking> villageRankings = familyBaseService.selectIndexChartStatics();
        return Result.OK(villageRankings);
    }

    @GetMapping("/villageRankings")
    public Result villageRankings() {
        List<VillageRanking> villageRankings = familyBaseService.selectIndexVillageRanking();
        IPage<VillageRanking> page = new Page<>(1,30);
        page.setRecords(villageRankings);
        return Result.OK(page);
    }
}
