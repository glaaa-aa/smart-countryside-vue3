package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.FaceIdentityMapperC;
import com.qfy.modules.city3d.po.FaceIdentity;
import com.qfy.modules.city3d.service.FaceIdentityService;
import org.springframework.stereotype.Service;

@Service
public class FaceIdentityImpl implements FaceIdentityService {

	@Autowired
	private FaceIdentityMapperC faceIdentityMapperC;

	@Override
	public int insert(FaceIdentity faceIdentity) {
		int result = faceIdentityMapperC.insert(faceIdentity);
		return result;
	}

	@Override
	public FaceIdentity getFaceIdentityAndVideoById(String faceId) {
		FaceIdentity result = faceIdentityMapperC.getFaceIdentityAndVideoById(faceId);
		return result;
	}

	@Override
	public List<FaceIdentity> getFaceIdentity() {
		List<FaceIdentity> result = faceIdentityMapperC.selectByExample(null);
		return result;
	}

	@Override
	public int updateByPrimaryKey(FaceIdentity faceIdentity) {
		int result = faceIdentityMapperC.updateByPrimaryKey(faceIdentity);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String faceId) {
		int result = faceIdentityMapperC.deleteByPrimaryKey(faceId);
		return result;
	}


	@Override
	public List<FaceIdentity> getFaceIdentityByRepoTypeandUserId(FaceIdentity faceIdentity) {
		List<FaceIdentity> result = faceIdentityMapperC.getFaceIdentityByRepoTypeandUserId(faceIdentity);
		return result;
	}

	@Override
	public int batchDelete(List<String> faceIds) {
		int result = faceIdentityMapperC.batchDelete(faceIds);
		return result;
	}
}
