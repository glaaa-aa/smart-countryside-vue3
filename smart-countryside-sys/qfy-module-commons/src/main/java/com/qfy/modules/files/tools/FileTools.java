package com.qfy.modules.files.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2021/12/7 23:07
 */
public class FileTools {

    public static Map<String,String> fileFormat(String fileName){
        Map<String,String> formatMap = new HashMap<>();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if("doc,docx,wps".contains(suffix)){
            formatMap.put("format", FileType.DOC);
            formatMap.put("icon", "file-word");
        }else if("exe,bat,sh".contains(suffix)){
            formatMap.put("format", FileType.EXE);
            formatMap.put("icon", "file-unknown");
        }else if("xls,xlsx".contains(suffix)){
            formatMap.put("format", FileType.XLS);
            formatMap.put("icon", "file-excel");
        }else if("pdf".equals(suffix)){
            formatMap.put("format", FileType.PDF);
            formatMap.put("icon", "file-pdf");
        }else if("ppt,pptx".contains(suffix)){
            formatMap.put("format", FileType.PPT);
            formatMap.put("icon", "file-ppt");
        }else if("jpg,jpeg,gif,bmp,png".contains(suffix)){
            formatMap.put("format", FileType.IMAGES);
            formatMap.put("icon", "file-jpg");
        }else if("js,css,html".contains(suffix)){
            formatMap.put("format", FileType.HTML);
            formatMap.put("icon", "file");
        }else if("txt".equals(suffix)){
            formatMap.put("format", FileType.TXT);
            formatMap.put("icon", "file-text");
        }else if("zip,rar,7z".contains(suffix)){
            formatMap.put("format", FileType.ZIP);
            formatMap.put("icon", "file");
        }else if("mp4,avi,rm,rmvb,mkv,wmv,m4v,3gp,ogg,flv,mp3".contains(suffix)){
            formatMap.put("format", FileType.MEDIA);
            formatMap.put("icon", "video-camera");
        }else {
            formatMap.put("format", FileType.UNKNOWN);
            formatMap.put("icon", "file-unknown");
        }

        return formatMap;
    }

    class FileType{
        private static final String DIR = "文件夹";
        private static final String IMAGES = "图片";
        private static final String DOC = "word文档";
        private static final String XLS = "excel表格";
        private static final String PPT = "PPT幻灯片";
        private static final String PDF = "pdf文档";
        private static final String TXT = "文本文档";
        private static final String EXE = "可执行文件";
        private static final String SHELL = "可执行脚本";
        private static final String JS = "脚本文件";
        private static final String CSS = "样式文件";
        private static final String ZIP = "压缩文件";
        private static final String HTML = "网页文件";
        private static final String VIDEO = "视频文件";
        private static final String AUDIO = "音频文件";
        private static final String MEDIA = "音视频文件";
        private static final String UNKNOWN = "未知文件";
    }
}
