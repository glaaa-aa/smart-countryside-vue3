package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import java.util.HashMap;
import java.util.List;
@DS("postgresql")
public interface GeneralMapper {
	List<HashMap<String, String>> countPeople();

	List<HashMap<String, String>> countAdd();

	List<HashMap<String, String>> countDep();

	List<HashMap<String, String>> countPatrol();

	List<HashMap<String, String>> countCam();

	List<HashMap<String, String>> countMan();
}
