package com.qfy.modules.api.service;

import org.jeecg.common.api.vo.Result;

public interface IAdminDivisionService {

    Result getDivisionList(String code,String parentId);
}
