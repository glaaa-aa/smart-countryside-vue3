package com.qfy.modules.files.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.model.FileManagerVo;
import org.jeecg.common.exception.JeecgBootException;


/**
 * @Description: 上传文件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
public interface IFilesManagerService extends IService<FilesManager> {
    /**
     * 根据文件夹ID删除文件
     * @param folderId
     * @throws JeecgBootException
     */
    public void deleteByFolderId(String folderId) throws JeecgBootException;

    public Page<FilesManager> queryListByRefCode(Page<FilesManager> page, FileFolder fileFolder);

    public Page<FileManagerVo> queryListBy(Page<FileManagerVo> page, FileManagerVo fileManagerVo);


}
