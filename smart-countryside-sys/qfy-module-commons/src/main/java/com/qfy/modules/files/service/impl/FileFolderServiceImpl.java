package com.qfy.modules.files.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.mapper.FileFolderMapper;
import com.qfy.modules.files.model.FileManagerVo;
import com.qfy.modules.files.model.FolderRefTypeEnum;
import com.qfy.modules.files.model.FolderTreeModel;
import com.qfy.modules.files.service.IFileFolderService;
import com.qfy.modules.files.service.IFilesManagerService;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @Description: 文件夹管理
 * @Author: jeecg-boot
 * @Date:   2022-02-19
 * @Version: V1.0
 */
@Service
public class FileFolderServiceImpl extends ServiceImpl<FileFolderMapper, FileFolder> implements IFileFolderService {
    @Autowired
    private FileFolderMapper fileFolderMapper;
    @Autowired
    private IFilesManagerService filesManagerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFolderAndFiles(String folderId) throws JeecgBootException {
        //删除文件
        removeById(folderId);
        filesManagerService.deleteByFolderId(folderId);
        //查询下级文件夹
        deleteByParentId(folderId);
    }
    //循环删除文件夹和文件
    @Transactional(rollbackFor = JeecgBootException.class)
    private void deleteByParentId(String pid) throws  JeecgBootException{
        List<FileFolder> list = fileFolderMapper.getFolderByPid(pid);
        if(list != null && list.size() > 0){
            for (FileFolder folder : list){
                removeById(folder.getId());
                filesManagerService.deleteByFolderId(folder.getId());
                //循环删除子集
                deleteByParentId(pid);
            }
        }
    }

    @Override
    public FileFolder getPartentFolder(String folderUrl) throws JeecgBootException {
        String[] folderArr = folderUrl.split("/");

        FolderRefTypeEnum refTypeEnum = FolderRefTypeEnum.valOfCode(folderArr[0]);

        //判断父级文件夹是否存在
        FileFolder parent = fileFolderMapper.getTopCodeByRef(folderArr[0]);
        if(parent == null ){
            parent = new FileFolder();
            parent.setRefType(folderArr[0]);
            parent.setFolderName(folderArr[0]);
            parent.setIzXuniPath(refTypeEnum.getIsXuNiPath());
            parent.setPid("0");
            parent.setFolderUrl(folderArr[0]+"/");
            parent = saveSubFolder(parent);
        }

        //临时存储上级文件夹
        FileManagerVo _vo = handleVo(parent);

        FileFolder result = parent;

        for(int i = 1;i <= folderArr.length-1;i++){
            //判断当前文件夹是否存在
            String folderName = folderArr[i];
            if(!StringUtils.isEmpty(folderName)){
                //根据Pid和name 判断是否存
                FileFolder fileFolder = fileFolderMapper.getFolderByName(_vo.getId(), folderName);
                if(fileFolder == null){
                    fileFolder = new FileFolder();
                    fileFolder.setPid(_vo.getId());
                    fileFolder.setFolderName(folderName);
                    fileFolder.setRefType(_vo.getRefType());
                    fileFolder.setFolderUrl(_vo.getFurl()+folderName+"/");
                    fileFolder.setIzXuniPath(_vo.isIzXuniPath());
                    fileFolder.setPCode("0");
                    save(fileFolder);
                }
                _vo = handleVo(fileFolder);
                result = fileFolder;
            }
        }

        return result;
    }

    private FileManagerVo handleVo(FileFolder fileFolder){
        FileManagerVo _vo = new FileManagerVo();
        _vo.setId(fileFolder.getId());
        _vo.setFurl(fileFolder.getFolderUrl());
        _vo.setRefType(fileFolder.getRefType());
        _vo.setRefCode(fileFolder.getRefCode());
        _vo.setFileName(fileFolder.getFolderName());
        _vo.setPid(fileFolder.getPid());
        _vo.setIzXuniPath(fileFolder.isIzXuniPath());
        return _vo;
    }

    @Override
    public FileFolder saveFolderForRef(String folderName, String refCode, String refType) throws JeecgBootException {
        try {
            //判断父级文件夹是否存在
            FileFolder parent = getPartentFolder(refType);
            //判断文件夹是否存在
            FileFolder fileFolder = fileFolderMapper.getUniqueByRef(parent.getId(), refCode,refType);
            if(fileFolder == null){
                fileFolder = new FileFolder();
                fileFolder.setCreateTime(new Date());
                fileFolder.setDelFlag(0);
            }

            fileFolder.setIzXuniPath(parent.isIzXuniPath());
            fileFolder.setFolderName(folderName);
            fileFolder.setRefType(refType);
            fileFolder.setRefCode(refCode);
            fileFolder.setProjectId(refCode);
            fileFolder.setPid(parent.getId());
            fileFolder.setPCode("0");
            fileFolder.setFolderUrl(parent.getFolderUrl() + folderName +"/");

            saveOrUpdate(fileFolder);
            return fileFolder;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 最大编码
     * @param pid
     * @return
     */
    private String getMaxCode(String pid){
        String maxCode = fileFolderMapper.getMaxChildCode(pid);
        return  maxCode;
    }

    @Override
    public FileFolder saveSubFolder(FileFolder fileFolder) throws JeecgBootException {
        //判断最大的编码
        if (StringUtils.isEmpty(fileFolder.getPid()) || fileFolder.getPid().equals("0")){
            //判断父级文件夹是否存在
            String refType=fileFolder.getRefType();
            if(StringUtils.isNotEmpty(refType)){
                if(refType.equals(FolderRefTypeEnum.task.getCode())){
                    refType=FolderRefTypeEnum.project.getCode();
                }
            }

            FileFolder parent = fileFolderMapper.getTopCodeByRef(refType);
            if(StringUtils.isEmpty(fileFolder.getProjectId())){
                fileFolder.setProjectId(fileFolder.getRefCode());
            }
            if(parent != null ){
                fileFolder.setPid(parent.getId());
                fileFolder.setPCode("0");
                fileFolder.setDelFlag(0);
                fileFolder.setFolderUrl(parent.getFolderUrl()+fileFolder.getFolderName()+"/");
            }else {

                fileFolder.setPid("0");
                fileFolder.setPCode("0");
                fileFolder.setDelFlag(0);
                fileFolder.setFolderUrl(fileFolder.getFolderName()+"/");
            }
        } else {
            FileFolder parent = getById(fileFolder.getPid());
            fileFolder.setProjectId(parent.getProjectId());
            if(StringUtils.isEmpty(fileFolder.getRefType())){
                fileFolder.setRefType(parent.getRefType());
            }
            if(StringUtils.isEmpty(fileFolder.getRefCode())){
                fileFolder.setRefCode(parent.getRefCode());
            }
            fileFolder.setIzXuniPath(parent.isIzXuniPath());
            fileFolder.setFolderUrl(parent.getFolderUrl() +fileFolder.getFolderName() +"/");
        }
        save(fileFolder);
        return fileFolder;
    }

    @Override
    public void deleteByRef(String refCode) throws JeecgBootException {
        fileFolderMapper.deleteByRef(refCode,FolderRefTypeEnum.project.getCode());
    }

    @Override
    public List<FolderTreeModel> queryTreeList(String refCode, String refType) {
        LambdaQueryWrapper<FileFolder> query = new LambdaQueryWrapper<FileFolder>();
//        if(!StringUtils.isEmpty(refType)){
//
//            query.eq(FileFolder::getRefType, refType);
//        }
        if(!StringUtils.isEmpty(refCode)){
            query.eq(FileFolder::getProjectId, refCode);
        }
        query.orderByAsc(FileFolder::getCreateTime);
        List<FileFolder> list = this.list(query);

        if(list == null || list.size() <= 0){
            return null;
        }

        //组装tree结构数据
        List<FolderTreeModel> topList = new ArrayList<>();

        if(!StringUtils.isEmpty(refCode)){
            for (FileFolder fileFolder : list){
                if(fileFolder.getPCode()!= null && fileFolder.getPCode().equals("0")){
                    FolderTreeModel model = new FolderTreeModel(fileFolder);
                    topList.add(model);
                }
            }
        }else {
            for (FileFolder fileFolder : list){
                if(fileFolder.getPid() == null || fileFolder.getPid().equals("0") ){
                    FolderTreeModel model = new FolderTreeModel(fileFolder);
                    topList.add(model);
                }
            }
        }

        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<FolderTreeModel> listResult = wrapTreeDataToTreeList(topList,list);
        setEmptyChildrenAsNull(listResult);
        return listResult;
    }



    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<FolderTreeModel> wrapTreeDataToTreeList(List<FolderTreeModel> parentList, List<FileFolder> fileFolderList) {
        if(parentList == null || parentList.size() <= 0){
            return null;
        }
        for (FolderTreeModel treeModel : parentList){
            List<FolderTreeModel> children = findChildren(treeModel.getId(),fileFolderList);
            treeModel.setChildren(children);
            wrapTreeDataToTreeList(children,fileFolderList);
        }
        return parentList;
    }


    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    private static List<FolderTreeModel> findChildren(String pid, List<FileFolder> fileFolderList) {
        List<FolderTreeModel> treeList = new ArrayList<>();
        for (FileFolder fileFolder : fileFolderList){
            if(fileFolder.getPid() != null && pid.equals(fileFolder.getPid())){
                FolderTreeModel treeModel = new FolderTreeModel(fileFolder);
                treeList.add(treeModel);
            }
        }
        return treeList;
    }

    /**
     * queryTreeList的子方法 ====3====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<FolderTreeModel> treeList) {
        if(treeList == null){
            return;
        }

        for (int i = 0; i < treeList.size(); i++) {
            FolderTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setIsLeaf(false);
            }
        }
    }


    @Override
    public FileFolder getById2(String id) {
        return fileFolderMapper.getById(id);
    }



    @Override
    public Map<String,Object> projectFile(Integer id, String pid) throws  JeecgBootException {
        Map<String,Object> returns = new HashMap<String,Object>();
        try {
            if(pid.equals("0")){
                FileFolder parent = fileFolderMapper.getTopCodeByRef( FolderRefTypeEnum.project.getCode());
                pid=parent.getId();
            }
            List<FileFolder> listfile = fileFolderMapper.getprojectFile(id,pid);
            List<FilesManager> listmanager=new ArrayList<FilesManager>();
            if(!pid.equals("0")){
                listmanager= fileFolderMapper.getprojectManager(pid);
            }
            returns.put("listmanager",listmanager);
            returns.put("litmap",listfile);
        }catch (Exception e){
            e.printStackTrace();
            throw new JeecgBootException("加载数据错误，请检查");
        }
        return returns;
    }
}
