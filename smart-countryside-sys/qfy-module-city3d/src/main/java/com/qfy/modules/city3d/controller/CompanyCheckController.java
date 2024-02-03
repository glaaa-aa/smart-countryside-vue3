package com.qfy.modules.city3d.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.CompanyCheck;
import com.qfy.modules.city3d.service.CompanyCheckService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.Res;

@Controller
@RequestMapping("/companyCheck")
public class CompanyCheckController extends CrossOriginController {
	@Autowired
	private CompanyCheckService companyCheckService;

    @RequestMapping("/getByCompanyId")
    public @ResponseBody Object getByCompanyId(@RequestBody Map param) {
        String companyId = (String) param.get("companyId");
        if (StringUtils.isEmpty(companyId)) {
            return null;
        }
        List<CompanyCheck> list = companyCheckService.findByCompanyId(companyId);

        return list;
    }

    @RequestMapping("/save")
    public @ResponseBody Object save(@RequestBody CompanyCheck entity) {
    	if(StringUtils.isBlank(entity.getCompanyCheckId())){
    		CharacterUtils characterUtils = new CharacterUtils();
    		String companyCheckId = characterUtils.getUUID();
    		entity.setCompanyCheckId(companyCheckId);
    		int result = companyCheckService.save(entity);
            return Res.success();
    	}else{
    		int result = companyCheckService.updateByPrimaryKey(entity);
    		return Res.success();
    	}
    }

    @RequestMapping("/delete")
    public @ResponseBody Object delete(@RequestBody Map param) {
        String id = (String) param.get("companyCheckId");
        int result = companyCheckService.deleteByPrimaryKey(id);
        return Res.success();
    }

}
