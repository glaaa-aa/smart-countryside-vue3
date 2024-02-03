package com.qfy.modules.files.tools;/**
 * @title: CompressUtil
 * @projectName qfy-dagz-manage-backstage
 * @description: TODO
 * @author 马强
 * @date 2022/10/31 15:54
 */

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @author     ：马强
 * @date       ：Created in 2022/10/31 15:54
 * @description：图片压缩
 * @modified By：
 * @version:
 */
@Slf4j
public class CompressUtil {

    /**
     * 图片压缩处理
     * @param savePath
     * @param uploadifyVo
     * @return
     */
    public static String compress(String savePath,UploadifyVo uploadifyVo) throws IOException {
        log.info("compress上传图片加载===》"+ JSONObject.toJSONString(uploadifyVo));
        log.info("compress上传图片加载===》"+ savePath);
        String fileName = savePath.substring(savePath.lastIndexOf(".") );
        String _zipPath = savePath.substring(0,savePath.lastIndexOf("."));
        _zipPath += uploadifyVo.getZipFolder();

        log.info("源文件===》"+uploadifyVo.getRootPath() + File.separator + savePath);
        log.info("目标地址===》"+ uploadifyVo.getRootPath()  +  File.separator +_zipPath + fileName);

        Thumbnails.of(uploadifyVo.getRootPath() + File.separator + savePath)
                .scale(uploadifyVo.getScale())
                .toFile(uploadifyVo.getRootPath()  +  File.separator +_zipPath + fileName);
        return _zipPath + fileName;
    }


}
