package com.qfy.modules.city3d.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PeopleDrugDiscover;
import com.qfy.modules.city3d.service.DrugDiscoverService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.Res;

@Controller
@RequestMapping("/drugDiscover")
public class DrugDiscoverController extends CrossOriginController {
	@Autowired
	private DrugDiscoverService drugDiscoverService;

    @RequestMapping("/getByPeopleId")
    public @ResponseBody Object getByPeopleId(@RequestBody Map param) {
        String ppId = (String) param.get("peopleId");
        if (StringUtils.isEmpty(ppId)) {
            return null;
        }
        List<PeopleDrugDiscover> list = drugDiscoverService.findByPpId(ppId);

        return list;
    }

    @RequestMapping("/save")
    public @ResponseBody Object save(@RequestBody PeopleDrugDiscover entity) {
    	if(StringUtils.isBlank(entity.getDrugDiscoverId())){
    		CharacterUtils characterUtils = new CharacterUtils();
    		String drugDiscoverId = characterUtils.getUUID();
    		entity.setDrugDiscoverId(drugDiscoverId);
    		int result = drugDiscoverService.save(entity);
            return Res.success();
    	}else{
    		int result = drugDiscoverService.updateByPrimaryKey(entity);
    		return Res.success();
    	}

    }

    @RequestMapping("/delete")
    public @ResponseBody Object delete(@RequestBody Map param) {
        String id = (String) param.get("drugDiscoverId");
        int result = drugDiscoverService.deleteByPrimaryKey(id);
        return Res.success();
    }
}
