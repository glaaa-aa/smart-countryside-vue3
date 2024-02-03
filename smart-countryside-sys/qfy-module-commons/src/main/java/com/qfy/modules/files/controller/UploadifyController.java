package com.qfy.modules.files.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qfy.modules.files.entity.FileFolder;
import com.qfy.modules.files.model.FolderRefTypeEnum;
import com.qfy.modules.files.service.IFileFolderService;
import com.qfy.modules.files.service.IFilesManagerService;
import com.qfy.modules.files.service.impl.UploadFilesThread;
import com.qfy.modules.files.tools.CompressUtil;
import com.qfy.modules.files.tools.UploadifyVo;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传功能
 * @Author 马强
 * @since 2022-03-01
 */
@Slf4j
@RestController
@RequestMapping("/sys/uploadify")
public class UploadifyController {

    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IFilesManagerService filesManagerService;
    @Autowired
    private IFileFolderService fileFolderService;
    @Autowired
    private UploadFilesThread filesThread;

    @Value(value = "${jeecg.path.upload}")
    private String uploadpath;

    /**
     * 本地：local minio：minio 阿里：alioss
     */
    @Value(value="${jeecg.uploadType}")
    private String uploadType;

    /**
     * @Author 政辉
     * @return
     */
    @GetMapping("/403")
    public Result<?> noauth()  {
        return Result.error("没有权限，请联系管理员授权");
    }

    /**
     * 文件上传统一方法
     * @param request  folderCode或者folderId 二选一
     * @param response
     * @return
     */
    @PostMapping(value = "/upload")
    public Result<Map> upload(HttpServletRequest request, HttpServletResponse response) {
        Result<Map> result = new Result<>();
        String savePath = "";
        UploadifyVo uploadifyVo = new UploadifyVo();
        //参数输入二选一
        String bizPath = request.getParameter("biz"); //根文件夹
        String folderId = request.getParameter("folderId"); //文件夹ID
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象


        String izZip = request.getParameter("izZip"); //是否执行压缩 0:否 1是
        String desWidth = request.getParameter("desWidth"); //文件夹ID

        uploadifyVo.setBiz(bizPath);
        uploadifyVo.setFolderId(folderId);

        if(org.apache.commons.lang.StringUtils.isNotEmpty(izZip)){
            uploadifyVo.setIzZip(Integer.parseInt(izZip));
        }
        if(org.apache.commons.lang.StringUtils.isNotEmpty(desWidth)){
            uploadifyVo.setDesWidth(Integer.parseInt(desWidth));
        }
        log.info("上传图片加载===》"+JSONObject.toJSONString(uploadifyVo));

        if(oConvertUtils.isEmpty(bizPath)){
            bizPath =  FolderRefTypeEnum.valOfCode(bizPath).getCode();
        }
        if(!StringUtils.isEmpty(folderId)) {
            FileFolder fileFolder = fileFolderService.getById(folderId);
            bizPath = (fileFolder.getRefType()==null? "upload":fileFolder.getRefType() ) +"/"+ fileFolder.getId();
        }

        String date =  DateUtils.formatDate("yyyyMMdd");
        //添加文件夹
        bizPath = bizPath +"/"+ date.substring(0,6);
        bizPath = bizPath +"/"+ date.substring(6);

        String fileId = System.currentTimeMillis()+"";//生成文件ID

        String oldName = file.getOriginalFilename();// 获取文件名
        oldName = CommonUtils.getFileName(oldName);
        if(CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)){
            savePath = this.uploadLocal(file,fileId,bizPath);
        }else{
            savePath = CommonUtils.upload(file, bizPath, uploadType);
        }
        try {
            //判断是否压缩
            if(uploadifyVo.getIzZip() != null && uploadifyVo.getIzZip().intValue() == 1 ){
                log.info("执行压缩---- 获取文件路径" + uploadpath +File.separator + savePath);
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
    //                log.info("图片宽度---" + bufferedImage.getWidth());
                // 上传文件的宽度
                int width = bufferedImage.getWidth();
                uploadifyVo.setSrcWidth(width);
                uploadifyVo.setRootPath(uploadpath);
                uploadifyVo.setSavePath(savePath);
                savePath = CompressUtil.compress(savePath,uploadifyVo);
                log.info(savePath);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("fileId",fileId);
        resultMap.put("savePath",savePath);
        resultMap.put("furl",savePath);
        resultMap.put("name",oldName);

        if(oConvertUtils.isNotEmpty(savePath)){
            result.setMessage(savePath);
            result.setSuccess(true);
            result.setResult(resultMap);
        }else {
            result.setMessage("上传失败！");
            result.setSuccess(false);
        }

        /**
         * 使用数据库表来管理上传的文件
         */
        //路径
        filesThread.setFilesManagerService(filesManagerService);
        filesThread.setRootPath(uploadpath);
        //相对路径
        filesThread.setPath(savePath);

        //文件
        filesThread.setOldName(oldName);
        filesThread.setFileName(savePath.substring(savePath.lastIndexOf("/") + 1));
        filesThread.setFileSize(file.getSize());

        filesThread.setFolderId(folderId);
        filesThread.setFileId(fileId);
        new Thread(filesThread).start();
        return result;

    }


    /**
     * 文件上传统一方法 多文件批量上传
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/uploadMulti")
    public Result<?> uploadMulti(HttpServletRequest request, HttpServletResponse response) {
        Result<?> result = new Result<>();
        String savePath = "";
        String bizPath = request.getParameter("biz");
        String folderId = request.getParameter("folderId"); //文件夹Id
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList  = multipartRequest.getFiles("file");// 获取上传文件对象
        if(oConvertUtils.isEmpty(bizPath)){
            if(CommonConstant.UPLOAD_TYPE_OSS.equals(uploadType)){
                //未指定目录，则用阿里云默认目录 upload
                bizPath = "upload";
            }else{
                bizPath = "upload";
            }
        }

        bizPath = bizPath +"/"+ DateUtils.formatDate("yyyyMMdd");

        String fileId = System.currentTimeMillis()+"";//生成文件ID

        for (MultipartFile mf : fileList){
            if(CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)){
                savePath = savePath + this.uploadLocal(mf,fileId,bizPath) +",";
            }else{
                savePath = savePath + CommonUtils.upload(mf, bizPath, uploadType)+",";
            }

            //文件名称
            String oldName = mf.getOriginalFilename();// 获取文件名
            oldName = CommonUtils.getFileName(oldName);

            /**
             * 使用数据库表来管理上传的文件
             */
//          LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            filesThread.setFilesManagerService(filesManagerService);

            filesThread.setPath(savePath);
            filesThread.setRootPath(uploadpath);
            filesThread.setFolderId(folderId);

            filesThread.setFileName(savePath.substring(savePath.lastIndexOf("/") + 1));
            filesThread.setOldName(oldName);
            filesThread.setFileId(fileId);
            filesThread.setFileSize(mf.getSize());

            new Thread(filesThread).start();
        }

        if(oConvertUtils.isNotEmpty(savePath)){
            savePath =  savePath.substring(0,savePath.length()-1);
            result.setMessage(savePath);
            result.setSuccess(true);
        }else {
            result.setMessage("上传失败！");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 本地文件上传
     * @param mf 文件
     * @param bizPath  自定义路径
     * @return
     */
    private String uploadLocal(MultipartFile mf,String fileId, String bizPath){
        try {
            String ctxPath = uploadpath;
            String fileName = null;
            File file = new File(ctxPath + File.separator + bizPath + File.separator );
            if (!file.exists()) {
                file.mkdirs();// 创建文件根目录
            }
            String orgName = mf.getOriginalFilename();// 获取文件名
            orgName = CommonUtils.getFileName(orgName);
            if(orgName.indexOf(".")!=-1){
                fileName =fileId + orgName.substring(orgName.lastIndexOf("."));
            }else{
                fileName = orgName+ "" + fileId;
            }
            String savePath = file.getPath() + File.separator + fileName;
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
            String dbpath = null;
            if(oConvertUtils.isNotEmpty(bizPath)){
                dbpath = bizPath + File.separator + fileName;
            }else{
                dbpath = fileName;
            }
            if (dbpath.contains("\\")) {
                dbpath = dbpath.replace("\\", "/");
            }
            return dbpath;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }


    /**
     * 预览图片&下载文件
     * 请求地址：http://localhost:8080/common/static/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/static/**")
    public void view(HttpServletRequest request, HttpServletResponse response) {
        // ISO-8859-1 ==> UTF-8 进行编码转换
        String imgPath = extractPathFromPattern(request);
        if(oConvertUtils.isEmpty(imgPath) || imgPath=="null"){
            return;
        }
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            imgPath = imgPath.replace("..", "");
            if (imgPath.endsWith(",")) {
                imgPath = imgPath.substring(0, imgPath.length() - 1);
            }
            String filePath = uploadpath + File.separator + imgPath;
            File file = new File(filePath);
            if(!file.exists()){
                response.setStatus(404);
                throw new RuntimeException("文件["+imgPath+"]不存在..");
            }
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
            inputStream = new BufferedInputStream(new FileInputStream(filePath));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            log.error("预览文件失败" + e.getMessage());
            response.setStatus(404);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }

//	/**
//	 * 下载文件
//	 * 请求地址：http://localhost:8080/common/download/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
//	 *
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@GetMapping(value = "/download/**")
//	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// ISO-8859-1 ==> UTF-8 进行编码转换
//		String filePath = extractPathFromPattern(request);
//		// 其余处理略
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		try {
//			filePath = filePath.replace("..", "");
//			if (filePath.endsWith(",")) {
//				filePath = filePath.substring(0, filePath.length() - 1);
//			}
//			String localPath = uploadpath;
//			String downloadFilePath = localPath + File.separator + filePath;
//			File file = new File(downloadFilePath);
//	         if (file.exists()) {
//	         	response.setContentType("application/force-download");// 设置强制下载不打开            
//	 			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
//	 			inputStream = new BufferedInputStream(new FileInputStream(file));
//	 			outputStream = response.getOutputStream();
//	 			byte[] buf = new byte[1024];
//	 			int len;
//	 			while ((len = inputStream.read(buf)) > 0) {
//	 				outputStream.write(buf, 0, len);
//	 			}
//	 			response.flushBuffer();
//	         }
//
//		} catch (Exception e) {
//			log.info("文件下载失败" + e.getMessage());
//			// e.printStackTrace();
//		} finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (outputStream != null) {
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

    /**
     * @功能：pdf预览Iframe
     * @param modelAndView
     * @return
     */
    @RequestMapping("/pdf/pdfPreviewIframe")
    public ModelAndView pdfPreviewIframe(ModelAndView modelAndView) {
        modelAndView.setViewName("pdfPreviewIframe");
        return modelAndView;
    }

    /**
     *  把指定URL后的字符串全部截断当成参数
     *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }

    /**
     * 中转HTTP请求，解决跨域问题
     *
     * @param url 必填：请求地址
     * @return
     */
    @RequestMapping("/transitRESTful")
    public Result transitRESTful(@RequestParam("url") String url, HttpServletRequest request) {
        try {
            ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
            // 中转请求method、body
            HttpMethod method = httpRequest.getMethod();
            JSONObject params;
            try {
                params = JSON.parseObject(JSON.toJSONString(httpRequest.getBody()));
            } catch (Exception e) {
                params = new JSONObject();
            }
            // 中转请求问号参数
            JSONObject variables = JSON.parseObject(JSON.toJSONString(request.getParameterMap()));
            variables.remove("url");
            // 在 headers 里传递Token
            String token = TokenUtils.getTokenByRequest(request);
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Access-Token", token);
            // 发送请求
            String httpURL = URLDecoder.decode(url, "UTF-8");
            ResponseEntity<String> response = RestUtil.request(httpURL, method, headers , variables, params, String.class);
            // 封装返回结果
            Result<Object> result = new Result<>();
            int statusCode = response.getStatusCodeValue();
            result.setCode(statusCode);
            result.setSuccess(statusCode == 200);
            String responseBody = response.getBody();
            try {
                // 尝试将返回结果转为JSON
                Object json = JSON.parse(responseBody);
                result.setResult(json);
            } catch (Exception e) {
                // 转成JSON失败，直接返回原始数据
                result.setResult(responseBody);
            }
            return result;
        } catch (Exception e) {
            log.debug("中转HTTP请求失败", e);
            return Result.error(e.getMessage());
        }
    }

}
