package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.CaseMaterial;

public interface CaseMaterialService {
	int uploadImg(CaseMaterial caseMaterial);

	List<CaseMaterial> getCaseMaterialByIdAndType(CaseMaterial caseMaterial);

	int deleteCaseMaterial(String caseMaterialId);
}
