package com.qfy.modules.city3d.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.CompanyPanorama;
import com.qfy.modules.city3d.service.CompanyPanoramaService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.Res;

@Controller
@RequestMapping("/companyPanorama")
public class CompanyPanoramaController extends CrossOriginController {
	@Autowired
	private CompanyPanoramaService companyPanoramaService;

	@RequestMapping("/getCompanyPanoramaByCompanyId")
	public @ResponseBody List<CompanyPanorama> getCompanyPanoramaByCompanyId(String companyId) throws Exception{
		List<CompanyPanorama> result = companyPanoramaService.getCompanyPanoramaByCompanyId(companyId);
		return result;
	}

    @RequestMapping("/getByCompanyId")
    public @ResponseBody Object getByCompanyId(@RequestBody Map param) {
        String companyId = (String) param.get("companyId");
        if (StringUtils.isEmpty(companyId)) {
            return null;
        }
        List<CompanyPanorama> list = companyPanoramaService.findByCompanyId(companyId);

        return list;
    }

    @RequestMapping("/save")
    public @ResponseBody Object save(@RequestBody CompanyPanorama entity) {
    	if(StringUtils.isBlank(entity.getPanoramaId())){
    		CharacterUtils characterUtils = new CharacterUtils();
    		String panoramaId = characterUtils.getUUID();
    		entity.setPanoramaId(panoramaId);
        	int result = companyPanoramaService.save(entity);
            return Res.success();
    	}else{
    		int result = companyPanoramaService.updateByPrimaryKey(entity);
    		return Res.success();
    	}

    }

    @RequestMapping("/delete")
    public @ResponseBody Object delete(@RequestBody Map param) {
        String id = (String) param.get("panoramaId");
        int result = companyPanoramaService.deleteByPrimaryKey(id);
        //companyPanoramaRepository.deleteById(id);
        return Res.success();
    }
}
