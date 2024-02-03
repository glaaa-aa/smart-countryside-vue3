package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.Attribute;
import com.qfy.modules.city3d.service.AttributeDataService;
import com.qfy.modules.city3d.service.AttributeService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attribute")
public class AttributeController extends CrossOriginController{

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private AttributeDataService attributeDataService;

	@RequestMapping("/getAttributeAll")
	public @ResponseBody List<Map<String,String>> getAttributeAll() throws Exception {
		List<Attribute> result = attributeService.getAttributeAll();
		List<Map<String,String>> list = new ArrayList();
		for(Attribute attribute : result){
			Map<String,String> map = new HashMap();
			map.put("id", attribute.getId());
			map.put("attrName", attribute.getAttrName());
			map.put("type", attribute.getType());
			map.put("option", attribute.getOption());
			map.put("normalValue",attribute.getNormalValue());
			map.put("coverageId",attribute.getCoverageId());
			map.put("selectNum",attribute.getSelectNum());
			map.put("orderNum",attribute.getOrderNum());
			map.put("coverageName",attribute.getCoverage().getName());
			list.add(map);
		}
		return list;
	}

	@RequestMapping("/getAttributeOnlyByCoverId")
	public @ResponseBody List<Attribute> getAttributeOnlyByCoverId(@RequestBody Attribute attribute) throws Exception {
		List<Attribute> result = attributeService.getAttributeOnlyByCoverId(attribute.getCoverageId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Attribute attribute) throws Exception{
		int maxOrderNumByCoverageId = attributeService.getAttributeMaxOrderNumByCoverageId(attribute.getCoverageId());
		CharacterUtils characterUtils = new CharacterUtils();
		String id = characterUtils.getUUID();
		attribute.setId(id);
		attribute.setOrderNum(maxOrderNumByCoverageId+"");
		try {
			int result = attributeService.insert(attribute);
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Attribute attribute) throws Exception{
		try {
			int result = attributeService.updateByPrimaryKey(attribute);
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody Object deleteByPrimaryKey(@RequestBody Attribute attribute) throws Exception{
//		List<AttributeData> attributeDatas = attributeDataService.getAttrIdFromAttributeData();
//		for(AttributeData attributeData : attributeDatas){
//			if(attributeData.getAttrId().contains(attribute.getId())){
//				return attributeData.getValue();
//			}
//		}
		int result = attributeService.deleteByPrimaryKey(attribute.getId());
		return result;
	}
}
