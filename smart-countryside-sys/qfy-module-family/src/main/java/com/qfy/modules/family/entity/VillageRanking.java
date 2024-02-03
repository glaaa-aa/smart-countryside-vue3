package com.qfy.modules.family.entity;

import java.io.Serializable;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * 行政村人口与居民户数排行
 * @author lanls
 * @version v1.0.0
 * @since 2023/12/11 22:58
 */
@Data
public class VillageRanking implements Serializable {

    private static final long serialVersionUID = 5253277963679599641L;

    /**
     * 行政村编码
     */
    @Dict(dictTable = "qfy_sys_organization", dicCode = "code", dicText = "name")
    private String orgCode;

    /**
     * 总人口
     */
    private Long totalPopulation;

    /**
     * 总户数
     */
    private Long totalHouseholds;

    /**
     * 总贫困户数
     */
    private Long totalPoverty;

    /**
     * 总易安搬迁户数
     */
    private Long totalRelocate;
}
