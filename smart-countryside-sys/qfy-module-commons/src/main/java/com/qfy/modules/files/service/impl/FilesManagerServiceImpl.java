package com.qfy.modules.files.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.mapper.FilesManagerMapper;
import com.qfy.modules.files.model.FileManagerVo;
import com.qfy.modules.files.service.IFilesManagerService;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 上传文件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Service
public class FilesManagerServiceImpl extends ServiceImpl<FilesManagerMapper, FilesManager> implements IFilesManagerService {

    @Autowired
    private FilesManagerMapper filesManagerMapper;

    @Override
    @Transactional(rollbackFor = JeecgBootException.class)
    public void deleteByFolderId(String folderId) throws JeecgBootException {
        //1.删除物理文件 查询文件的路径 并执行删除

        //2.删除数据
        filesManagerMapper.deleteByFolderId(folderId);

    }

    @Override
    public Page<FilesManager> queryListByRefCode(Page<FilesManager> page, FileFolder fileFolder) {
        return  page.setRecords(filesManagerMapper.queryListByRefCode(page,fileFolder)) ;
    }


    @Override
    public Page<FileManagerVo> queryListBy(Page<FileManagerVo> page, FileManagerVo fileManagerVo) {
        return  page.setRecords(filesManagerMapper.queryListBy(page,fileManagerVo)) ;
    }


}
