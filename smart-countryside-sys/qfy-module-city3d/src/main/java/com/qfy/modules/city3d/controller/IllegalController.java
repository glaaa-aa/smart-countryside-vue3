package com.qfy.modules.city3d.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PeopleIllegal;
import com.qfy.modules.city3d.service.PeopleIllegalService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.Res;
@Controller
@RequestMapping("/illegal")
public class IllegalController extends CrossOriginController {
	@Autowired
	private PeopleIllegalService peopleIllegalService;

    @RequestMapping("/getByPeopleId")
    public @ResponseBody Object getByPeopleId(@RequestBody Map param) {
        String ppId = (String) param.get("peopleId");
        if (StringUtils.isEmpty(ppId)) {
            return null;
        }
        List<PeopleIllegal> list = peopleIllegalService.findByPpId(ppId);

        return list;
    }

    @RequestMapping("/save")
    public @ResponseBody Object save(@RequestBody PeopleIllegal entity) {
    	if(StringUtils.isBlank(entity.getIllegalId())){
    		CharacterUtils characterUtils = new CharacterUtils();
    		String illegalId = characterUtils.getUUID();
    		entity.setIllegalId(illegalId);
            int result = peopleIllegalService.save(entity);
            return Res.success();
    	}else{
    		int result = peopleIllegalService.updateByPrimaryKey(entity);
    		return Res.success();
    	}
    }

    @RequestMapping("/delete")
    public @ResponseBody Object delete(@RequestBody Map param) {
        String id = (String) param.get("illegalId");
        int result = peopleIllegalService.deleteById(id);
        return Res.success();
    }
}
