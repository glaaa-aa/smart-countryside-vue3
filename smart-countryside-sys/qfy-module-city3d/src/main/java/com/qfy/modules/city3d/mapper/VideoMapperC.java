package com.qfy.modules.city3d.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qfy.modules.city3d.po.Video;
import java.util.List;

@DS("postgresql")
public interface VideoMapperC extends VideoMapper {
	List<Video> getRHPCVideo(String positionStr);
}
