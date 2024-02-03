package com.qfy.modules.city3d.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qfy.modules.city3d.po.MapPhotos;
import com.qfy.modules.city3d.service.MapPhotosService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/mapPhotos")
public class MapPhotosController extends CrossOriginController {
	@Autowired
	private MapPhotosService mapPhotosService;

	@RequestMapping("/getMapPhotos")
	public @ResponseBody List<MapPhotos> getMapPhotos() throws Exception {
		List<MapPhotos> result = mapPhotosService.getMapPhotos();
		return result;
	}

	@RequestMapping("/getMapPhotosByTime")
	public @ResponseBody List<MapPhotos> getMapPhotosByTime(@RequestBody MapPhotos mapPhotos) throws Exception {
		String startTime = mapPhotos.getPhotoName();
		String endTime = mapPhotos.getPhotoTime();
		List<MapPhotos> result = mapPhotosService.getMapPhotosByTime(startTime, endTime);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
			@RequestParam("photoName") String photoName,
			@RequestParam("photoPos") String photoPos,
			@RequestParam("photoTime") String photoTime,
			HttpServletRequest request) throws Exception {
		MapPhotos mapPhotos = new MapPhotos();
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "mapphotos/"+photoName+uid+extName;
					String path = request.getServletContext().getRealPath("").replace("\\city3dAPI","") + "/city3dfile/" + fileName;
			        File newFile = new File(path);
			    	//父级目录不在就创建一个
	                if (!newFile.getParentFile().exists()){
	                	newFile.mkdirs();
	                }
			        file.transferTo(newFile);//通过CommonsMultipartFile的方法直接写文件
			    } catch (IllegalStateException e) {
			        e.printStackTrace();
			    }
			}
		}
		mapPhotos.setPhotoId(uid);
		mapPhotos.setPhotoName(photoName);
		mapPhotos.setPhotoPos(photoPos);
		mapPhotos.setPhotoTime(photoTime);
		int result = mapPhotosService.insert(mapPhotos);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody MapPhotos mapPhotos) throws Exception {
		try {
			String extName = mapPhotos.getPhotoName().substring(mapPhotos.getPhotoName().indexOf("."));
			String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\mapphotos\\"+mapPhotos.getPhotoName()+mapPhotos.getPhotoId()+extName;
			File fileDel = new File(path);
			fileDel.createNewFile();
			fileDel.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		int result = mapPhotosService.deleteByPrimaryKey(mapPhotos.getPhotoId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<MapPhotos> mapPhotoses) throws Exception {
		List<String> photoIds = new ArrayList();
		for(MapPhotos mapPhotos : mapPhotoses) {
			photoIds.add(mapPhotos.getPhotoId());
			try {
				String extName = mapPhotos.getPhotoName().substring(mapPhotos.getPhotoName().indexOf("."));
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\mapphotos\\"+mapPhotos.getPhotoName()+mapPhotos.getPhotoId()+extName;
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = mapPhotosService.batchDelete(photoIds);
		return result;
	}
}
