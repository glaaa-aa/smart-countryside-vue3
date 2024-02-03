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

import com.qfy.modules.city3d.po.PeopleVehicle;
import com.qfy.modules.city3d.service.PeopleVehicleService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/peopleVehicle")
public class PeopleVehicleController extends CrossOriginController {
	@Autowired
	private PeopleVehicleService peopleVehicleService;

	@RequestMapping("/getPeopleVehicle")
	public @ResponseBody List<PeopleVehicle> getPeopleVehicle() throws Exception{
		List<PeopleVehicle> result = peopleVehicleService.selectPeopleVehicleAll();
		return result;
	}

    @RequestMapping("/insert")
    public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,@RequestParam("vehicleId") String vehicleId,
			@RequestParam("ppId") String ppId,@RequestParam("carBrand") String carBrand,
			@RequestParam("carCode") String carCode,@RequestParam("carNo") String carNo,@RequestParam("carType") String carType,
			@RequestParam("color") String color,@RequestParam("engineNo") String engineNo,@RequestParam("imgurl") String imgurl,
			@RequestParam("registerData") String registerData,@RequestParam("remark") String remark,
			HttpServletRequest request) throws Exception{
    	PeopleVehicle peopleVehicle = new PeopleVehicle();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/vehicle/vehicle_"+carNo+carCode+extName;
					peopleVehicle.setImgurl(fileName);
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
		peopleVehicle.setVehicleId(uid);
		peopleVehicle.setPpId(ppId);
		peopleVehicle.setCarBrand(carBrand);
		peopleVehicle.setCarCode(carCode);
		peopleVehicle.setCarNo(carNo);
		peopleVehicle.setCarType(carType);
		peopleVehicle.setColor(color);
		peopleVehicle.setEngineNo(engineNo);
		peopleVehicle.setRegisterData(registerData);
		peopleVehicle.setRemark(remark);
		int result = peopleVehicleService.insert(peopleVehicle);
        return result;
    }

    @RequestMapping("/update")
    public @ResponseBody int update(@RequestParam("files") MultipartFile[] files,@RequestParam("vehicleId") String vehicleId,
			@RequestParam("ppId") String ppId,@RequestParam("carBrand") String carBrand,
			@RequestParam("carCode") String carCode,@RequestParam("carNo") String carNo,@RequestParam("carType") String carType,
			@RequestParam("color") String color,@RequestParam("engineNo") String engineNo,@RequestParam("imgurl") String imgurl,
			@RequestParam("registerData") String registerData,@RequestParam("remark") String remark,
			HttpServletRequest request) throws Exception{
    	PeopleVehicle peopleVehicle = new PeopleVehicle();
    	peopleVehicle.setImgurl(imgurl);
    	for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/vehicle/vehicle_"+carNo+carCode+extName;
					peopleVehicle.setImgurl(fileName);
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
    	peopleVehicle.setVehicleId(vehicleId);
		peopleVehicle.setPpId(ppId);
		peopleVehicle.setCarBrand(carBrand);
		peopleVehicle.setCarCode(carCode);
		peopleVehicle.setCarNo(carNo);
		peopleVehicle.setCarType(carType);
		peopleVehicle.setColor(color);
		peopleVehicle.setEngineNo(engineNo);
		peopleVehicle.setRegisterData(registerData);
		peopleVehicle.setRemark(remark);
		int result = peopleVehicleService.updateByPrimaryKey(peopleVehicle);
		return result;
    }

    @RequestMapping("/delete")
    public @ResponseBody int delete(@RequestBody PeopleVehicle peopleVehicle) throws Exception{
    	if(peopleVehicle.getImgurl()!=null&&peopleVehicle.getImgurl()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+peopleVehicle.getImgurl();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
        int result = peopleVehicleService.deleteByPrimaryKey(peopleVehicle.getVehicleId());
        return result;
    }

    @RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PeopleVehicle> peopleVehicles) throws Exception {
		List<String> vehicleIds = new ArrayList();
		for(PeopleVehicle peopleVehicle : peopleVehicles) {
			vehicleIds.add(peopleVehicle.getVehicleId());
		}
		int result = peopleVehicleService.batchDelete(vehicleIds);
		return result;
    }
}
