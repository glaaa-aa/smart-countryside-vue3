package com.qfy.modules.files.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.model.FolderTreeModel;
import org.jeecg.common.exception.JeecgBootException;

import java.util.List;
import java.util.Map;

/**
 * @Description: 文件夹管理
 * @Author: jeecg-boot
 * @Date:   2022-02-19
 * @Version: V1.0
 */
public interface IFileFolderService extends IService<FileFolder> {

    FileFolder getById2(String id);
    /**
     * 删除文件夹和下级文件夹 文件
     * @param folderId
     * @throws JeecgBootException
     */
    public void deleteFolderAndFiles(String folderId) throws JeecgBootException;
    /**
     * 根据关联数据编码和类型 查询唯一数据
     * @param refCode
     * @param refType
     * @return
     */
//    public FileFolder queryUnique(String refCode, String refType);

    /**
     * @param folderUrl 文件夹路径 如果不存在 将自动创建
     * @Author: 马强
     * @Description:
     * @Date: 2022/2/28 19:42
     **/
    public FileFolder getPartentFolder(String folderUrl) throws JeecgBootException;

    /**
     * @Author: 马强
     * @Description:
     * @Date: 2022/2/28 19:42
     **/
    public FileFolder saveFolderForRef(String folderName,String refCode, String refType) throws JeecgBootException;
    /**
     * 添加子文件夹
     * @param fileFolder
     */
    public FileFolder saveSubFolder(FileFolder fileFolder) throws JeecgBootException;

    /**
     * 删除文件夹和文件
     * @param refCode
     * @param refType
     * @throws JeecgBootException
     */
    public void deleteByRef(String refCode) throws JeecgBootException;
    /**
     * @Title: queryTreeList
     * @Author: 马强
     * @Description: 根据分类数据查询所有数据
     * @param refCode
     * @param refType
     * @Date: 2022/2/20 15:21
     * @throws
     **/
    List<FolderTreeModel> queryTreeList(String refCode,String refType);


    /**
     * 项目文件接口
     * @param
     * @return
     */
    public Map<String,Object> projectFile(Integer id, String pid) throws JeecgBootException;





}
