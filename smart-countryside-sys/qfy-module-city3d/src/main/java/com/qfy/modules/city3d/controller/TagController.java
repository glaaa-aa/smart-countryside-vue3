package com.qfy.modules.city3d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.Viewtag;
import com.qfy.modules.city3d.service.TagService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/tag")
public class TagController extends CrossOriginController {
	@Autowired
	private TagService tagService;

	@RequestMapping("/getTag")
	public @ResponseBody List<Viewtag> getTag() throws Exception {
		List<Viewtag> result = tagService.getTag();
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody Viewtag tag) throws Exception {
		int result = tagService.deleteByPrimaryKey(tag.getTagId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Viewtag tag) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		tag.setTagId(uid);
		int result = tagService.insert(tag);
		return result;
	}
}
