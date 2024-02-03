package com.qfy.modules.files.tools;/**
 * @title: UploadifyVo
 * @projectName qfy-dagz-manage-backstage
 * @description: TODO
 * @author 马强
 * @date 2022/10/31 15:56
 */

import lombok.Data;

import java.text.DecimalFormat;

/**
 * @author     ：马强
 * @date       ：Created in 2022/10/31 15:56
 * @description：文件上传参数
 * @modified By：
 * @version:
 */
@Data
public class UploadifyVo {

    //文件压缩目录
    private String zipFolder = "_zip";
    private String rootPath;

    //参数二选一 或者两个都传
    private String biz; //文件目录
    private String folderId; //文件夹Id

    private Integer izZip = 0; //是否压缩  是否执行压缩功能  true:执行压缩
    private Double scale = 0.6 ; //压缩比例

    private Integer srcWidth; //原始文件宽度
    private Integer srcHegiht;//原始文件高度
    private Integer desWidth = 350; //压缩到宽度  宽度优先 如果
    private Integer desHeight; //压缩高度


    /*************返回结果***********/
    private String savePath;


    //获取宽度
    public Double getScale() {
        if(srcWidth != null && desWidth!= null){
            DecimalFormat format = new DecimalFormat("#.00");
            String str = format.format(desWidth.doubleValue()/srcWidth);
            double four = Double.parseDouble(str);
            if(four >= 1 ){
                return  1.0;
            }
            return four;
        }else {
            return scale;
        }
    }

}
