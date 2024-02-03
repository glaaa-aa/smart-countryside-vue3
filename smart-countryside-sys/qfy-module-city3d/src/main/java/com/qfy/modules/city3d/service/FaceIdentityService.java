package com.qfy.modules.city3d.service;

import com.qfy.modules.city3d.po.FaceIdentity;
import java.util.List;

public interface FaceIdentityService {
	public int insert(FaceIdentity faceIdentity);
	public FaceIdentity getFaceIdentityAndVideoById(String faceId);
	public List<FaceIdentity> getFaceIdentity();
	public int updateByPrimaryKey(FaceIdentity faceIdentity);
	public int deleteByPrimaryKey(String faceId);
	public List<FaceIdentity> getFaceIdentityByRepoTypeandUserId(FaceIdentity faceIdentity);
	public int batchDelete(List<String> faceIds);
}
