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

import com.qfy.modules.city3d.po.PeopleMachine;
import com.qfy.modules.city3d.service.PeopleMachineService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/peopleMachine")
public class PeopleMachineController extends CrossOriginController {
	@Autowired
	private PeopleMachineService peopleMachineService ;

	@RequestMapping("/getPeopleMachine")
	public @ResponseBody List<PeopleMachine> getPeopleMachine() throws Exception{
		List<PeopleMachine> result = peopleMachineService.selectPeopleMachineAll();
		return result;
	}

    @RequestMapping("/insert")
    public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,
    		@RequestParam("machineId") String machineId,
			@RequestParam("ppId") String ppId,
			@RequestParam("qrCode") String qrCode,
			@RequestParam("machineType") String machineType,
			@RequestParam("remark") String remark,
			@RequestParam("imgUrl") String imgUrl,
			HttpServletRequest request) throws Exception {
    	PeopleMachine peopleMachine = new PeopleMachine();
    	for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/machine/machine_"+machineType+qrCode+extName;
					peopleMachine.setImgUrl(fileName);
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
		peopleMachine.setMachineId(uid);
		peopleMachine.setPpId(ppId);
		peopleMachine.setQrCode(qrCode);
		peopleMachine.setRemark(remark);
		peopleMachine.setMachineType(machineType);
        int result = peopleMachineService.save(peopleMachine);
        return result;
    }

    @RequestMapping("/update")
    public @ResponseBody int update(@RequestParam("files") MultipartFile[] files,
    		@RequestParam("machineId") String machineId,
			@RequestParam("ppId") String ppId,
			@RequestParam("qrCode") String qrCode,
			@RequestParam("machineType") String machineType,
			@RequestParam("remark") String remark,
			@RequestParam("imgUrl") String imgUrl,
			HttpServletRequest request) throws Exception {
    	PeopleMachine peopleMachine = new PeopleMachine();
    	peopleMachine.setImgUrl(imgUrl);
    	for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/machine/machine_"+machineType+qrCode+extName;
					peopleMachine.setImgUrl(fileName);
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
    	peopleMachine.setMachineId(machineId);
		peopleMachine.setPpId(ppId);
		peopleMachine.setQrCode(qrCode);
		peopleMachine.setRemark(remark);
		peopleMachine.setMachineType(machineType);
        int result = peopleMachineService.updateByPrimaryKey(peopleMachine);
        return result;
    }

    @RequestMapping("/delete")
    public @ResponseBody int delete(@RequestBody PeopleMachine peopleMachine) throws Exception {
    	if(peopleMachine.getImgUrl()!=null&&peopleMachine.getImgUrl()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+peopleMachine.getImgUrl();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
        int result = peopleMachineService.deleteByPrimaryKey(peopleMachine.getMachineId());
        return result;
    }

    @RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PeopleMachine> peopleMachines) throws Exception {
		List<String> machineIds = new ArrayList();
		for(PeopleMachine peopleMachine : peopleMachines) {
			machineIds.add(peopleMachine.getMachineId());
		}
		int result = peopleMachineService.batchDelete(machineIds);
		return result;
    }
}
