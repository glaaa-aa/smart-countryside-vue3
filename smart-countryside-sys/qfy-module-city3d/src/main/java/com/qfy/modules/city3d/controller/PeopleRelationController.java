package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.PeopleRelation;
import com.qfy.modules.city3d.service.PeopleRelationService;

@Controller
@RequestMapping("/peopleRelation")
public class PeopleRelationController extends CrossOriginController {
	@Autowired
	private PeopleRelationService peopleRelationService ;

	@RequestMapping("/selectPeopleRelationById")
	public @ResponseBody List<PeopleRelation> selectPeopleRelationById(String ppId) throws Exception{
		List<PeopleRelation> result = peopleRelationService.selectPeopleRelationById(ppId);
		return result;
	}
}
