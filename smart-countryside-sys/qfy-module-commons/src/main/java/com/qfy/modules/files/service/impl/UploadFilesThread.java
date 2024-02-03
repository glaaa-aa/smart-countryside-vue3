package com.qfy.modules.files.service.impl;

import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.service.IFileFolderService;
import com.qfy.modules.files.service.IFilesManagerService;
import com.qfy.modules.files.tools.FileTools;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * TODO
 * @author lanls
 * @version 1.0
 * @date 2021/12/7 22:39
 */
@Data
@Service
public class UploadFilesThread implements Runnable{

    public static final Logger logger = LoggerFactory.getLogger(UploadFilesThread.class);
    @Autowired
    private IFileFolderService fileFolderService;
    private IFilesManagerService filesManagerService;

    private String fileId;//文件ID //先生成id,
    private String oldName;
    private String fileName;
    private Long fileSize;

    private String folderId;
    private String path;
    private String rootPath; //物理根路径
//    private String sysOrgCode; //所属机构



    @Override
    public void run() {
        try {
            String filePath = path;
            filePath = filePath.replace(rootPath,"");// 剔除根目录路径
            if (StringUtils.startsWith( filePath,"/")){
                filePath = filePath.substring(1,filePath.lastIndexOf("/"));//剔除文件名，便得到文件所在的各级目录
            }else {
                filePath = filePath.substring(0,filePath.lastIndexOf("/"));//剔除文件名，便得到文件所在的各级目录
            }

            //判断文件夹是否存在
            FileFolder fileFolder = null;
            if(!StringUtils.isEmpty(folderId)){
                fileFolder = fileFolderService.getById2(folderId);
            }else {
                fileFolder = fileFolderService.getPartentFolder(filePath);
            }

            Map<String, String> formatMap = FileTools.fileFormat(fileName);
            FilesManager manager = new FilesManager();
            manager.setFurl(path);
            manager.setFolderId(fileFolder.getId());
            manager.setName(fileName);
            manager.setIcon(formatMap.get("icon"));
            manager.setFpath(rootPath +"/"+ path);
            manager.setSize(fileSize.intValue() / 1024);
            manager.setFormat(formatMap.get("format"));
            manager.setOldName(oldName);
            manager.setTenantId(fileFolder.getTenantId());
            manager.setId(fileId);
            filesManagerService.save(manager);
        } catch(Exception e) {
            logger.error("文件存库失败：", e);
        }
    }
}
