package com.qfy.modules.city3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfy.modules.city3d.po.FaceIdentity;
import com.qfy.modules.city3d.service.FaceIdentityService;

@Controller
@RequestMapping("/faceIdentity")
public class FaceIdentityController extends CrossOriginController {
	@Autowired
	private FaceIdentityService faceIdentityService;

	@RequestMapping("/getFaceIdentity")
	public @ResponseBody List<FaceIdentity> getFaceIdentity() throws Exception {
		List<FaceIdentity> result = faceIdentityService.getFaceIdentity();
		return result;
	}

	@RequestMapping("/getFaceIdentityAndVideoById")
	public @ResponseBody FaceIdentity getFaceIdentityAndVideoById(@RequestBody FaceIdentity faceIdentity) throws Exception {
		FaceIdentity result = faceIdentityService.getFaceIdentityAndVideoById(faceIdentity.getFaceId());
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody FaceIdentity faceIdentity) throws Exception {
		int result = faceIdentityService.updateByPrimaryKey(faceIdentity);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody FaceIdentity faceIdentity) throws Exception {
		int result = faceIdentityService.deleteByPrimaryKey(faceIdentity.getFaceId());
		return result;
	}

	@RequestMapping("/getFaceIdentityByRepoTypeandUserId")
	public @ResponseBody List<FaceIdentity> getFaceIdentityByRepoTypeandUserId(@RequestBody FaceIdentity faceIdentity) throws Exception {
		List<FaceIdentity> result = faceIdentityService.getFaceIdentityByRepoTypeandUserId(faceIdentity);
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<FaceIdentity> faceIdentitys) throws Exception {
		List<String> faceIds = new ArrayList();
		for(FaceIdentity faceIdentity : faceIdentitys) {
			faceIds.add(faceIdentity.getFaceId());
		}
		int result = faceIdentityService.batchDelete(faceIds);
		return result;
	}
}
