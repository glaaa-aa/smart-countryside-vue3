package com.qfy.modules.files.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.model.FileManagerVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 上传文件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
public interface FilesManagerMapper extends BaseMapper<FilesManager> {

    @Delete("delete from oss_files_manager where folder_id = #{folderId}")
    public void deleteByFolderId(@Param("folderId")String folderId);

    /**
     *  前端查询
     * @param page 分页参数
     * @param fileFolder 条件查询
     * @return
     */
    public List<FilesManager> queryListByRefCode(Page<FilesManager> page, @Param("fileFolder") FileFolder fileFolder);

    /**
     *  前端查询 文件夹和文件综合查询
     * @param page 分页参数
     * @param fileManagerVo 条件查询
     * @return
     */
    public List<FileManagerVo> queryListBy(Page<FileManagerVo> page, @Param("fileManagerVo") FileManagerVo fileManagerVo);

}
