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

import com.qfy.modules.city3d.po.PeopleKnife;
import com.qfy.modules.city3d.service.PeopleKnifeService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/peopleKnife")
public class PeopleKnifeController extends CrossOriginController {
	@Autowired
	private PeopleKnifeService peopleKnifeService ;

	@RequestMapping("/getPeopleKnife")
	public @ResponseBody List<PeopleKnife> getPeopleKnife() throws Exception {
		List<PeopleKnife> result = peopleKnifeService.getPeopleKnife();
		return result;
	}

    @RequestMapping("/insert")
    public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,@RequestParam("knifeId") String knifeId,
			@RequestParam("ppId") String ppId,@RequestParam("knifeLenth") String knifeLenth,
			@RequestParam("qrCode") String qrCode,@RequestParam("fixedSituation") String fixedSituation,
			@RequestParam("knifeType") String knifeType,@RequestParam("remark") String remark,
			@RequestParam("imgUrl") String imgUrl,
			HttpServletRequest request) throws Exception{
    	PeopleKnife peopleKnife = new PeopleKnife();
    	for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/knife/knife_"+knifeType+qrCode+extName;
					peopleKnife.setImgUrl(fileName);
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
		peopleKnife.setKnifeId(uid);
		peopleKnife.setPpId(ppId);
		peopleKnife.setKnifeLenth(knifeLenth);
		peopleKnife.setQrCode(qrCode);
		peopleKnife.setFixedSituation(fixedSituation);
		peopleKnife.setKnifeType(knifeType);
		peopleKnife.setRemark(remark);
        int result = peopleKnifeService.save(peopleKnife);
        return result;
    }

    @RequestMapping("/update")
    public @ResponseBody int update(@RequestParam("files") MultipartFile[] files,@RequestParam("knifeId") String knifeId,
			@RequestParam("ppId") String ppId,@RequestParam("knifeLenth") String knifeLenth,
			@RequestParam("qrCode") String qrCode,@RequestParam("fixedSituation") String fixedSituation,
			@RequestParam("knifeType") String knifeType,@RequestParam("remark") String remark,
			@RequestParam("imgUrl") String imgUrl,
			HttpServletRequest request) throws Exception{
    	PeopleKnife peopleKnife = new PeopleKnife();
    	peopleKnife.setImgUrl(imgUrl);
    	for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/knife/knife_"+knifeType+qrCode+extName;
					peopleKnife.setImgUrl(fileName);
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
    	peopleKnife.setKnifeId(knifeId);
		peopleKnife.setPpId(ppId);
		peopleKnife.setKnifeLenth(knifeLenth);
		peopleKnife.setQrCode(qrCode);
		peopleKnife.setFixedSituation(fixedSituation);
		peopleKnife.setKnifeType(knifeType);
		peopleKnife.setRemark(remark);
		int result = peopleKnifeService.updateByPrimaryKey(peopleKnife);
		return result;
    }

    @RequestMapping("/delete")
    public @ResponseBody int delete(@RequestBody PeopleKnife peopleKnife) throws Exception{
    	if(peopleKnife.getImgUrl()!=null&&peopleKnife.getImgUrl()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+peopleKnife.getImgUrl();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
        int result = peopleKnifeService.deleteByPrimaryKey(peopleKnife.getKnifeId());
        return result;
    }

    @RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<PeopleKnife> peopleKnifes) throws Exception {
		List<String> knifeIds = new ArrayList();
		for(PeopleKnife peopleKnife : peopleKnifes) {
			knifeIds.add(peopleKnife.getKnifeId());
		}
		int result = peopleKnifeService.batchDelete(knifeIds);
		return result;
    }
}
