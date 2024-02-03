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

import com.qfy.modules.city3d.po.MapImage;
import com.qfy.modules.city3d.service.MapImageService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/mapImage")
public class MapImageController extends CrossOriginController {
	@Autowired
	private MapImageService mapImageService;

	@RequestMapping("/getMapImage")
	public @ResponseBody List<MapImage> getMapImage() throws Exception {
		List<MapImage> result = mapImageService.getMapImage();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
			@RequestParam("imageName") String imageName,
			@RequestParam("imagePos") String imagePos,
			@RequestParam("imageTime") String imageTime,
			@RequestParam("imagePid") String imagePid,
			@RequestParam("imageType") String imageType,
			HttpServletRequest request) throws Exception {
		MapImage mapImage = new MapImage();
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "mapimage/"+imageName+uid+extName;
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
		mapImage.setImageId(uid);
		mapImage.setImageName(imageName);
		mapImage.setImagePos(imagePos);
		mapImage.setImageTime(imageTime);
		mapImage.setImagePid(imagePid);
		mapImage.setImageType(imageType);
		int result = mapImageService.insert(mapImage);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody MapImage mapImage) throws Exception {
		try {
			String extName = mapImage.getImageName().substring(mapImage.getImageName().indexOf("."));
			String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\mapimage\\"+mapImage.getImageName()+mapImage.getImageId()+extName;
			File fileDel = new File(path);
			fileDel.createNewFile();
			fileDel.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		int result = mapImageService.deleteByPrimaryKey(mapImage.getImageId());
		return result;
	}

	@RequestMapping("/deleteImage")
	public @ResponseBody int deleteImage(@RequestBody MapImage mapImage) throws Exception {
		try {
			String extName = mapImage.getImageName().substring(mapImage.getImageName().indexOf("."));
			String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\mapimage\\"+mapImage.getImageName()+mapImage.getImageId()+extName;
			File fileDel = new File(path);
			fileDel.createNewFile();
			fileDel.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<MapImage> mapImages) throws Exception {
		List<String> imageIds = new ArrayList();
		for(MapImage mapImage : mapImages) {
			imageIds.add(mapImage.getImageId());
			try {
				String extName = mapImage.getImageName().substring(mapImage.getImageName().indexOf("."));
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\mapimage\\"+mapImage.getImageName()+mapImage.getImageId()+extName;
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = mapImageService.batchDelete(imageIds);
		return result;
	}

	@RequestMapping("/getMapImageByPid")
	public @ResponseBody List<MapImage> getMapImageByPid(@RequestBody MapImage mapImage) throws Exception {
		List<MapImage> result = mapImageService.getMapImageByPid(mapImage.getImagePid());
		return result;
	}
}
