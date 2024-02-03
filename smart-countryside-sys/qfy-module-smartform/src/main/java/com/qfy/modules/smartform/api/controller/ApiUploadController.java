package com.qfy.modules.smartform.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Title:CgUploadController
 * @description:智能表单文件上传控制器
 * @author 赵俊夫
 * @date Jul 24, 2013 9:10:44 PM
 * @version V1.0
 */
//@Scope("prototype")
@Controller
@RequestMapping("/api/uploadController")
public class ApiUploadController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ApiUploadController.class);

//	@Autowired
//	private SystemService systemService;
//
//
//	/**
//	 * 自动上传保存附件资源的方式
//	 * @return
//	 */
//	@RequestMapping(params = "ajaxSaveFile")
//	@ResponseBody
//	public AjaxJson ajaxSaveFile(HttpServletRequest request) {
//		AjaxJson ajaxJson = new AjaxJson();
//		Map<String, Object> attributes = new HashMap<String, Object>();
//		try {
//			Enumeration<String> paramNames = request.getHeaderNames();
//			while (paramNames.hasMoreElements()) {
//				String paramName =  paramNames.nextElement();
//				attributes.put(paramName, request.getHeader(paramName));
//			}
//			String token = request.getHeader("token");
//
//
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//			String uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
//			// 文件数据库保存路径
//			String path = uploadbasepath + "/";// 文件保存在硬盘的相对路径
//			String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path;// 文件的硬盘真实路径
//			realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
//			path += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
//			File file = new File(realPath);
//			if (!file.exists()) {
//				file.mkdirs();// 创建文件时间子目录
//			}
//
//			if(fileMap != null && !fileMap.isEmpty()){
//				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//					MultipartFile mf = entity.getValue();// 获取上传文件对象
//					String fileName = mf.getOriginalFilename();// 获取文件名
//					String swfName = PinyinUtil.getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));// 取文件名首字母作为SWF文件名
//					String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
//					String noextfilename=DateUtils.getDataString(DateUtils.yyyymmddhhmmss)+StringUtil.random(8);//自定义文件名称
//					String myfilename=noextfilename+"."+extend;//自定义文件名称
//					String savePath = realPath + myfilename;// 文件保存全路径
//					write2Disk(mf, extend, savePath);
//					TSAttachment attachment = new TSAttachment();
//					attachment.setId(UUID.randomUUID().toString().replace("-", ""));
//					attachment.setAttachmenttitle(fileName.substring(0,fileName.lastIndexOf(".")));
//					attachment.setCreatedate(new Timestamp(new Date().getTime()));
//					attachment.setExtend(extend);
//					attachment.setRealpath(path + myfilename);
//					attachment.setBusinessKey("app");
//					String globalSwfTransformFlag = ResourceUtil.getConfigByName("swf.transform.flag");
//					if("true".equals(globalSwfTransformFlag) && !FileUtils.isPicture(extend)){
//						attachment.setSwfpath( path + FileUtils.getFilePrefix(myfilename) + ".swf");
//						SwfToolsUtil.convert2SWF(savePath);
//					}
//
//					systemService.save(attachment);
//					attributes.put("url", path + myfilename);
//					attributes.put("name", fileName);
//					attributes.put("swfpath", attachment.getSwfpath());
//					attributes.put("fileid", attachment.getId());
//				}
//			}
//			ajaxJson.setAttributes(attributes);
//		} catch (Exception e) {
//			e.printStackTrace();
//			ajaxJson.setSuccess(false);
//			ajaxJson.setMsg(e.getMessage());
//		}
//		return ajaxJson;
//	}

	/**
	 * 保存文件的具体操作
	 * @param mf
	 * @param extend
	 * @param savePath
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private void write2Disk(MultipartFile mf, String extend, String savePath)
			throws IOException, UnsupportedEncodingException, FileNotFoundException {
		File savefile = new File(savePath);
		if("txt".equals(extend)){
			//利用utf-8字符集的固定首行隐藏编码原理
			//Unicode:FF FE   UTF-8:EF BB
			byte[] allbytes = mf.getBytes();
			try{
				String head1 = toHexString(allbytes[0]);
				String head2 = toHexString(allbytes[1]);
				if("ef".equals(head1) && "bb".equals(head2)){
					//UTF-8
					String contents = new String(mf.getBytes(),"UTF-8");
					if(StringUtils.isNotBlank(contents)){
						OutputStream out = new FileOutputStream(savePath);
						out.write(contents.getBytes());
						out.close();
					}
				}  else {
					//GBK
					String contents = new String(mf.getBytes(),"GBK");
					OutputStream out = new FileOutputStream(savePath);
					out.write(contents.getBytes());
					out.close();
				}
			  } catch(Exception e){
				  String contents = new String(mf.getBytes(),"UTF-8");
					if(StringUtils.isNotBlank(contents)){
						OutputStream out = new FileOutputStream(savePath);
						out.write(contents.getBytes());
						out.close();
					}
			}
		} else {
			FileCopyUtils.copy(mf.getBytes(), savefile);
		}
	}

	private String toHexString(int index){
        String hexString = Integer.toHexString(index);
        // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充
        hexString = hexString.substring(hexString.length() -2);
        return hexString;
	}


	//TODO 1.更新表cgform_uploadfiles的时候，是根据页面传过来的ID以xx结尾判断：历史OR新增文件,这个功能应该在java代码中查询数据库校验
	//TODO 2.新增文件 但是不提交表单 那么附件表就会产生垃圾数据 需要写个xx功能 清除垃圾文件/数据
	//TODO 3.上传代码在宏中定义，但是依赖部分页面JS代码，通用性不好。

}
