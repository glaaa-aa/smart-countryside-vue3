package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.PoliceCase;
import java.util.List;

@DS("postgresql")
public interface PoliceCaseMapperC extends PoliceCaseMapper {
	List<PoliceCase> getCase();

	PoliceCase selectCaseandNetById(String caseId);
}
