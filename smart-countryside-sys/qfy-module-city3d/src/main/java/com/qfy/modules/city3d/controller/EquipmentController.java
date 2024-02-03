package com.qfy.modules.city3d.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qfy.modules.city3d.po.Equipment;
import com.qfy.modules.city3d.service.EquipmentService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/equipment")
public class EquipmentController extends CrossOriginController {
	@Autowired
	private EquipmentService equipmentService;

	//新增装备
	@RequestMapping("/insertEquipment")
	public @ResponseBody int insertEquipment(@RequestParam("files") MultipartFile[] files,
			@RequestParam("equipmentId") String equipmentId,
			@RequestParam("equipmentType") String equipmentType,
			@RequestParam("equipmentName") String equipmentName,
			@RequestParam("equipmentGrade") String equipmentGrade,
			@RequestParam("equipmentPicture") String equipmentPicture,
			HttpServletRequest request) throws IOException{
		Equipment equipment = new Equipment();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "equipmentpicture/eq_"+equipmentType+"_"+equipmentName+"_"+new Date().getTime()+extName;
					equipment.setEquipmentPicture(fileName);
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
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		equipment.setEquipmentId(uid);
		equipment.setEquipmentType(equipmentType);
		equipment.setEquipmentName(equipmentName);
		equipment.setEquipmentGrade(equipmentGrade);
		int result = equipmentService.insertEquipment(equipment);
		return result;
	}

	//删除装备
	@RequestMapping("/deleteEquipment")
	public @ResponseBody int deleteEquipment(@RequestBody Equipment equipment) throws IOException{
		if(equipment.getEquipmentPicture()!=null&&equipment.getEquipmentPicture()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+equipment.getEquipmentPicture();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = equipmentService.deleteEquipment(equipment.getEquipmentId());
		return result;
	}

	//更改装备
	@RequestMapping("/updateEquipment")
	public @ResponseBody int updateEquipment(@RequestParam("files") MultipartFile[] files,
			@RequestParam("equipmentId") String equipmentId,
			@RequestParam("equipmentType") String equipmentType,
			@RequestParam("equipmentName") String equipmentName,
			@RequestParam("equipmentGrade") String equipmentGrade,
			@RequestParam("equipmentPicture") String equipmentPicture,
			HttpServletRequest request) throws Exception{
		Equipment equipment = new Equipment();
		equipment.setEquipmentPicture(equipmentPicture);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "equipmentpicture/eq_"+equipmentType+"_"+equipmentName+"_"+new Date().getTime()+extName;
					equipment.setEquipmentPicture(fileName);
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
		equipment.setEquipmentId(equipmentId);
		equipment.setEquipmentType(equipmentType);
		equipment.setEquipmentName(equipmentName);
		equipment.setEquipmentGrade(equipmentGrade);
		int result = equipmentService.updateEquipment(equipment);
		return result;
	}

	//查询装备
	@RequestMapping("/selectEquipmentByType")
	public @ResponseBody List<Equipment> selectEquipmentByType(@RequestBody Equipment equipment){
		List<Equipment> result;
		if(!"".equals(equipment.getEquipmentType()) && equipment.getEquipmentType()!=null){
			result=equipmentService.selectEquipmentByType(equipment.getEquipmentType());
		}else{
			result=equipmentService.selectEquipmentAllOrderByType();
		}
		return result;
	}
}
