package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.Model;
import com.qfy.modules.city3d.service.ModelService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/bimCaseModel")
public class BimCaseModelController extends CrossOriginController{
	@Autowired
	private ModelService modelService;

	@RequestMapping("/getBimCaseModel")
	public @ResponseBody List<Model> getBimCaseModel() throws Exception {
		List<Model> result = modelService.getModel();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
			@RequestParam("modelId") String modelId,
			@RequestParam("modelName") String modelName,
			@RequestParam("modelDep") String modelDep,
			@RequestParam("modelScale") String modelScale,
			@RequestParam("modelUrl") String modelUrl,
			HttpServletRequest request) throws Exception {
		Model model = new Model();
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		for (MultipartFile file: files) {
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/casemodel/"+modelName+uid+extName;
					model.setModelImg(fileName);
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
		model.setModelId(uid);
		model.setModelName(modelName);
		model.setModelDep(modelDep);
		model.setModelScale(modelScale);
		model.setModelUrl(modelUrl);
		int result = modelService.insert(model);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestParam("files") MultipartFile[] files,
			@RequestParam("modelId") String modelId,
			@RequestParam("modelName") String modelName,
			@RequestParam("modelDep") String modelDep,
			@RequestParam("modelScale") String modelScale,
			@RequestParam("modelUrl") String modelUrl,
			@RequestParam("modelImg") String modelImg,
			HttpServletRequest request) throws Exception {
		Model model = new Model();
		model.setModelImg(modelImg);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			    	fileName = "image/casemodel/"+modelName+modelId+extName;
					model.setModelImg(fileName);
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
		model.setModelId(modelId);
		model.setModelName(modelName);
		model.setModelDep(modelDep);
		model.setModelScale(modelScale);
		model.setModelUrl(modelUrl);
		int result = modelService.updateByPrimaryKey(model);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Model model) throws Exception {
		if(model.getModelImg()!=null&&model.getModelImg()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+model.getModelImg();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = modelService.deleteByPrimaryKey(model.getModelId());
		return result;
	}
}
