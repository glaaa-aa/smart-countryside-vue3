package com.qfy.modules.city3d.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qfy.modules.city3d.po.EquipmentRel;
import com.qfy.modules.city3d.po.ManPos;
import com.qfy.modules.city3d.po.Police;
import com.qfy.modules.city3d.service.EquipmentRelService;
import com.qfy.modules.city3d.service.ManPosService;
import com.qfy.modules.city3d.service.PoliceService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/police")
public class PoliceController extends CrossOriginController {
	@Autowired
	private PoliceService policeService;

	@Autowired
	private ManPosService manPosService;

	@Autowired
	private EquipmentRelService equipmentRelService;


	@RequestMapping("/getPolice")
	public @ResponseBody List<Police> getPolice() throws Exception {
		List<Police> result = policeService.getPolice();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,@RequestParam("policeId") String policeId,
			@RequestParam("name") String name,@RequestParam("identity") String identity,@RequestParam("gender") String gender,
			@RequestParam("nation") String nation,@RequestParam("birth") String birth,@RequestParam("jobDate") String jobDate,
			@RequestParam("education") String education,@RequestParam("origin") String origin,@RequestParam("post") String post,
			@RequestParam("policeRank") String policeRank,@RequestParam("deviceNum") String deviceNum,@RequestParam("deviceType") String deviceType,
			@RequestParam("image") String image,@RequestParam("equips") List<String> equips,HttpServletRequest request) throws Exception{
		Police police = new Police();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/man/man_"+name+extName;
					police.setImage(fileName);
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
		police.setPoliceId(uid);
		police.setName(name);
		police.setIdentity(identity);
		police.setGender(gender);
		police.setNation(nation);
		police.setBirth(birth);
		police.setJobDate(jobDate);
		police.setEducation(education);
		police.setOrigin(origin);
		police.setPost(post);
		police.setPoliceRank(policeRank);
		police.setDeviceNum(deviceNum);
		police.setDeviceType(deviceType);
		int result = policeService.insert(police);
		for(String equipId: equips) {
			EquipmentRel equipmentRel = new EquipmentRel();
			String equipRelId = characterUtils.getUUID();
			equipmentRel.setEquipRelId(equipRelId);
			equipmentRel.setEquipId(equipId);
			equipmentRel.setRelId(uid);
			equipmentRelService.insertEquipmentRel(equipmentRel);
		}
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestParam("files") MultipartFile[] files,@RequestParam("policeId") String policeId,
			@RequestParam("name") String name,@RequestParam("identity") String identity,@RequestParam("gender") String gender,
			@RequestParam("nation") String nation,@RequestParam("birth") String birth,@RequestParam("jobDate") String jobDate,
			@RequestParam("education") String education,@RequestParam("origin") String origin,@RequestParam("post") String post,
			@RequestParam("policeRank") String policeRank,@RequestParam("deviceNum") String deviceNum,@RequestParam("deviceType") String deviceType,
			@RequestParam("image") String image,@RequestParam("equips") List<String> equips,HttpServletRequest request) throws Exception{
		equipmentRelService.deleteEquipmentRel(policeId);
		Police police = new Police();
		police.setImage(image);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/man/man_"+name+extName;
					police.setImage(fileName);
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
		police.setPoliceId(policeId);
		police.setName(name);
		police.setIdentity(identity);
		police.setGender(gender);
		police.setNation(nation);
		police.setBirth(birth);
		police.setJobDate(jobDate);
		police.setEducation(education);
		police.setOrigin(origin);
		police.setPost(post);
		police.setPoliceRank(policeRank);
		police.setDeviceNum(deviceNum);
		police.setDeviceType(deviceType);
		int result = policeService.updateByPrimaryKey(police);
		CharacterUtils characterUtils = new CharacterUtils();
		for(String equipId: equips) {
			EquipmentRel equipmentRel = new EquipmentRel();
			String equipRelId = characterUtils.getUUID();
			equipmentRel.setEquipRelId(equipRelId);
			equipmentRel.setEquipId(equipId);
			equipmentRel.setRelId(policeId);
			equipmentRelService.insertEquipmentRel(equipmentRel);
		}
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Police police) throws Exception{
		if(police.getImage()!=null&&police.getImage()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+police.getImage();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = policeService.deleteByPrimaryKey(police.getPoliceId());
		int result0 = equipmentRelService.deleteEquipmentRel(police.getPoliceId());
		result = result0 == 0 ? 2 : result;
		return result;
	}

	@RequestMapping("/getManRealPosAll")
	public @ResponseBody List<ManPos> getManRealPosAll() throws Exception{
		List<ManPos> result = manPosService.getManPosAll();
		return result;
	}

	@RequestMapping("/getManPosByDn")
	public @ResponseBody List<ManPos> getManPosByDn(@RequestBody ManPos manPos) throws Exception{
		List<ManPos> result = manPosService.getManPosByDn(manPos);
		return result;
	}

	@RequestMapping("/getManPosWithNameAndOrigin")
	public @ResponseBody List<Map<String,String>> getManPosWithNameAndOrigin(@RequestBody ManPos manPos) {
		List<ManPos> result = manPosService.getManPosWithNameAndOrigin(manPos.getDeviceNums());
		List<Map<String,String>> ManPosS = new ArrayList();
		for(ManPos mp:result){
			Map<String,String> map = new HashMap();
			map.put("name", mp.getPolice().getName());
			map.put("origin", mp.getPolice().getOrigin());
			map.put("realId", mp.getRealId());
			map.put("realPos", mp.getRealPos());
			map.put("realTime", mp.getRealTime());
			map.put("deviceNum", mp.getDeviceNum());
			map.put("realType", mp.getRealType());
			ManPosS.add(map);
		}
		return ManPosS;
	}

	@RequestMapping("/getPoliceRealDateWithManPos")
	public @ResponseBody List<Map<String,String>> getPoliceRealDateWithManPos() throws Exception{
		List<Police> result = policeService.getPoliceRealDateWithManPos();
		List<Map<String,String>> PoliceRealDateWithManPos = new ArrayList();
		for(Police p:result){
			Map<String,String> map = new HashMap();
			map.put("policeId", p.getPoliceId());
			map.put("netId", p.getNetId());
			map.put("depId", p.getDepId());
			map.put("name", p.getName());
			map.put("identity", p.getIdentity());
			map.put("gender", p.getGender());
			map.put("birth", p.getBirth());
			map.put("nation", p.getNation());
			map.put("origin", p.getOrigin());
			map.put("post", p.getPost());
			map.put("jobDate", p.getJobDate());
			map.put("policeRank", p.getPoliceRank());
			map.put("education", p.getEducation());
			map.put("deviceNum", p.getDeviceNum());
			map.put("deviceType", p.getDeviceType());
			map.put("deviceDate", p.getDeviceDate());
			map.put("realId", p.getManPos().getRealId());
			map.put("longitude", p.getManPos().getRealPos().split(",")[0]);
			map.put("latitude", p.getManPos().getRealPos().split(",")[1]);
			map.put("elevation", p.getManPos().getRealPos().split(",")[2]);
			map.put("realTime", p.getManPos().getRealTime());
			map.put("realType", p.getManPos().getRealType());
			map.put("realPos", p.getManPos().getRealPos());
			PoliceRealDateWithManPos.add(map);
		}
		return PoliceRealDateWithManPos;
	}

	@RequestMapping("/getPoliceAndEquipmentsByPuid")
	public @ResponseBody Map<String,Object> getPoliceAndEquipmentsByPuid(@RequestBody Police police) throws Exception {
		Map<String,Object> resultmap = new HashMap();
		try {
			Police result = policeService.getPoliceAndEquipmentsByPuid(police.getDeviceNum());
			resultmap.put("policeId", result.getPoliceId());
			resultmap.put("netId", result.getNetId());
			resultmap.put("depId", result.getDepId());
			resultmap.put("name", result.getName());
			resultmap.put("identity", result.getIdentity());
			resultmap.put("gender", result.getGender());
			resultmap.put("birth", result.getBirth());
			resultmap.put("nation", result.getNation());
			resultmap.put("origin", result.getOrigin());
			resultmap.put("post", result.getPost());
			resultmap.put("jobDate", result.getJobDate());
			resultmap.put("policeRank", result.getPoliceRank());
			resultmap.put("education", result.getEducation());
			resultmap.put("deviceNum", result.getDeviceNum());
			resultmap.put("deviceType", result.getDeviceType());
			resultmap.put("deviceDate", result.getDeviceDate());
			resultmap.put("image", result.getImage());
			resultmap.put("policeEquipments", result.getEquipmentRels());
			ManPos params = new ManPos();
			params.setDeviceNum(result.getDeviceNum());
			List<ManPos> manPos = manPosService.getManPosByDn(params);
			resultmap.put("realTime", manPos.get(0).getRealTime());
		} catch (Exception e) {
			List<Police> result = policeService.findPoliceByPuid(police.getDeviceNum());
			resultmap.put("policeId", result.get(0).getPoliceId());
			resultmap.put("netId", result.get(0).getNetId());
			resultmap.put("depId", result.get(0).getDepId());
			resultmap.put("name", result.get(0).getName());
			resultmap.put("identity", result.get(0).getIdentity());
			resultmap.put("gender", result.get(0).getGender());
			resultmap.put("birth", result.get(0).getBirth());
			resultmap.put("nation", result.get(0).getNation());
			resultmap.put("origin", result.get(0).getOrigin());
			resultmap.put("post", result.get(0).getPost());
			resultmap.put("jobDate", result.get(0).getJobDate());
			resultmap.put("policeRank", result.get(0).getPoliceRank());
			resultmap.put("education", result.get(0).getEducation());
			resultmap.put("deviceNum", result.get(0).getDeviceNum());
			resultmap.put("deviceType", result.get(0).getDeviceType());
			resultmap.put("deviceDate", result.get(0).getDeviceDate());
			resultmap.put("image", result.get(0).getImage());
			resultmap.put("policeEquipments", null);
			ManPos params = new ManPos();
			params.setDeviceNum(result.get(0).getDeviceNum());
			List<ManPos> manPos = manPosService.getManPosByDn(params);
			resultmap.put("realTime", manPos.get(0).getRealTime());
		}
		return resultmap;
	}

}
