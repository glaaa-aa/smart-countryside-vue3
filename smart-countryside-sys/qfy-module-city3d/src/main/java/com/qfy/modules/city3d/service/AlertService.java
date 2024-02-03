package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.Alert;
import java.util.List;

public interface AlertService {
	public int insertAlert(Alert alert);
	public List<Alert> getAlert();
	public int updateByPrimaryKey(Alert alert);
	public int deleteByPrimaryKey(String id);
	public int batchDelete(List<String> ids);
}
