package com.qfy.modules.files.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.entity.FilesManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 文件夹管理
 * @Author: jeecg-boot
 * @Date:   2022-02-19
 * @Version: V1.0
 */
public interface FileFolderMapper extends BaseMapper<FileFolder> {


    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from oss_file_folder where id = #{id}")
    public FileFolder getById(@Param("id")String id);
    /**
     * 根据父级ID 查询子集文件夹
     * @return string
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from oss_file_folder where pid = #{pid}")
    public List<FileFolder> getFolderByPid(@Param("pid")String pid);

    /**
     * 根据类型获取最顶级文件夹
     * @return string
     */
//    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from oss_file_folder where pid =0 and ref_type=#{refType}")
    public FileFolder getTopCodeByRef(@Param("refType")String refType);

    /**
     * 查询文件夹是否存在
     * @return string
     */
//    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from oss_file_folder where pid = #{pid} and folder_name=#{folderName}")
    public FileFolder getFolderByName(@Param("pid")String pid, @Param("folderName")String folderName);

    /**
     * 获取最顶级的文件夹信息
     * @param refCode
     * @param refType
     * @return
     */
    public FileFolder getUniqueByRef(@Param("pid")String pid, @Param("refCode")String refCode, @Param("refType")String refType);

    /**
     * 根据外检删除文件夹
     * @param refCode
     * @param refType
     * @return
     */
    public boolean deleteByRef(@Param("refCode")String refCode, @Param("refType")String refType);

    /**
     * 获取子级最大的编码  用于获取下一个编码
     * @return string
     */
    @Select("select max(folder_code) max_code from oss_file_folder where pid =#{pid}")
    public String getMaxChildCode(String pid);


    /**
     * 获取项目文件夹
     * @return string
     */
    @Select("SELECT * from oss_file_folder where  project_id=${projectid} and pid=${pid}")
    public List<FileFolder> getprojectFile(@Param("projectid")Integer projectid,@Param("pid")String pid);


    /**
     * 获取项目文件
     * @return string
     */
    @Select("SELECT * from oss_files_manager where folder_id=${pid}")
    public List<FilesManager> getprojectManager(@Param("pid")String pid);
}
