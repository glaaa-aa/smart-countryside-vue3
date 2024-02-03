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

import com.qfy.modules.city3d.po.NetMan;
import com.qfy.modules.city3d.service.NetManService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/netMan")
public class NetManController extends CrossOriginController {
	@Autowired
	private NetManService netManService;

	@RequestMapping("/getNetMan")
	public @ResponseBody List<NetMan> getNetMan()  throws Exception {
		List<NetMan> result = netManService.getNetMan();
		return result;
	}

	@RequestMapping("/getNetManById")
	public @ResponseBody NetMan getNetManById(@RequestBody NetMan netMan)  throws Exception {
		NetMan result = netManService.getNetManById(netMan.getNetManId());
		return result;
	}

	@RequestMapping("/getNetManByType")
	public @ResponseBody List<NetMan> getNetManByType(@RequestBody NetMan netMan)  throws Exception {
		List<NetMan> result = netManService.getNetManByType(netMan.getType());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
			@RequestParam("netManId") String netManId,
			@RequestParam("name") String name,
			@RequestParam("post") String post,
			@RequestParam("duty") String duty,
			@RequestParam("tel") String tel,
			@RequestParam("party") String party,
			@RequestParam("jobTime") String jobTime,
			@RequestParam("note") String note,
			@RequestParam("type") String type,
			HttpServletRequest request)  throws Exception {
		NetMan netMan = new NetMan();
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/netMan/"+name+uid+extName;
					netMan.setImgurl(fileName);
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
		netMan.setNetManId(uid);
		netMan.setName(name);
		netMan.setPost(post);
		netMan.setDuty(duty);
		netMan.setTel(tel);
		netMan.setParty(party);
		netMan.setJobTime(jobTime);
		netMan.setNote(note);
		netMan.setType(type);
		int result = netManService.insert(netMan);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestParam("files") MultipartFile[] files,
			@RequestParam("netManId") String netManId,
			@RequestParam("name") String name,
			@RequestParam("post") String post,
			@RequestParam("duty") String duty,
			@RequestParam("tel") String tel,
			@RequestParam("party") String party,
			@RequestParam("jobTime") String jobTime,
			@RequestParam("note") String note,
			@RequestParam("type") String type,
			@RequestParam("imgurl") String imgurl,
			HttpServletRequest request)  throws Exception {
		NetMan netMan = new NetMan();
		netMan.setImgurl(imgurl);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
			    	String fileName = "";
			    	String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/netMan/"+name+netManId+extName;
					netMan.setImgurl(fileName);
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
		netMan.setNetManId(netManId);
		netMan.setName(name);
		netMan.setPost(post);
		netMan.setDuty(duty);
		netMan.setTel(tel);
		netMan.setParty(party);
		netMan.setJobTime(jobTime);
		netMan.setNote(note);
		netMan.setType(type);
		int result = netManService.updateByPrimaryKey(netMan);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody NetMan netMan)  throws Exception {
		if(netMan.getImgurl()!=null&&netMan.getImgurl()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+netMan.getImgurl();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = netManService.deleteByPrimaryKey(netMan.getNetManId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<NetMan> netMans) throws Exception {
		List<String> netManIds = new ArrayList();
		for(NetMan netMan : netMans) {
			netManIds.add(netMan.getNetManId());
		}
		int result = netManService.batchDelete(netManIds);
		return result;
	}
}
