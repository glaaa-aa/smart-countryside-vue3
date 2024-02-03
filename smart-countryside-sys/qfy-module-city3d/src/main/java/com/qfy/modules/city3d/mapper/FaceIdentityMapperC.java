package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.FaceIdentity;
import java.util.List;

@DS("postgresql")
public interface FaceIdentityMapperC extends FaceIdentityMapper {
	public FaceIdentity getFaceIdentityAndVideoById(String faceId);
	public List<FaceIdentity> getFaceIdentityByRepoTypeandUserId(FaceIdentity faceIdentity);
	public int batchDelete(List<String> faceIds);
}
