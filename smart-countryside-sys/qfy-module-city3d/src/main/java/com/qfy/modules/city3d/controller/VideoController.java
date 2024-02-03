package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.Live;
import com.qfy.modules.city3d.po.Video;
import com.qfy.modules.city3d.service.LiveService;
import com.qfy.modules.city3d.service.VideoService;
import com.qfy.modules.city3d.util.CharacterUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/video")
public class VideoController extends CrossOriginController {
	@Autowired
	private VideoService videoService;

	@Autowired
	private LiveService liveService;

	@RequestMapping("/getVideoAndNodes")
	public @ResponseBody List<Video> getVideoAndNodes() throws Exception {
		List<Video> result = videoService.getVideoAndNodes();
		return result;
	}

	@RequestMapping("/countCam")
	public @ResponseBody int countCam() throws Exception {
		int count = videoService.countCam();
		return count;
	}

	@RequestMapping("/getVideosIsNode")
	public @ResponseBody List<Video> getVideosIsNode() throws Exception {
		List<Video> result = videoService.getVideosIsNode();
		return result;
	}

	@RequestMapping("/getVideoById")
	public @ResponseBody Video getVideoById(@RequestBody Video Video) throws Exception {
		Video result = videoService.getVideoById(Video.getId());
		return result;
	}

	@RequestMapping("/getRHPCVideo")
	public @ResponseBody List<Video> getRHPCVideo(@RequestBody Video Video) throws Exception {
		String[] positionArr = Video.getCamPosition().split(",");
		String positionStr = positionArr[0]+" "+positionArr[1];
		List<Video> result = videoService.getRHPCVideo(positionStr);
		return result;
	}

	@RequestMapping("/getVideoNoNodes")
	public @ResponseBody List<Video> getVideoNoNodes() throws Exception {
		List<Video> result = videoService.getVideoNoNodes();
		return result;
	}

	@RequestMapping("/getVideoByMacUrl")
	public @ResponseBody Video getVideoByMacUrl(@RequestBody Video video) throws Exception {
		Video result = videoService.getVideoByMacUrl(video.getMacUrl());
		return result;
	}

	//同一摄像头位置变更
	@RequestMapping("/getVideoListByMacUrl")
	public @ResponseBody List<Video> getVideoListByMacUrl(@RequestBody Video video) throws Exception {
		List<Video> result = videoService.getVideoListByMacUrl(video.getMacUrl());
		return result;
	}

	//视频port查重
	@RequestMapping("/testVideoRepetitionByCamPort")
	public @ResponseBody String testVideoRepetitionByCamPort(@RequestBody Video video) throws Exception {
		String camPort = video.getCamPort();
		if(camPort != null && !"".equals(camPort)){
			if(camPort.contains("/live/mystream")){
				int result = videoService.countVideoByCamPort(camPort);
				if(result == 0){
					return "no repetition";
				}else{
					return "repetition";
				}
			}else{
				return "no repetition";
			}
		}else{
			return "camPort is empty";
		}
	}

	@RequestMapping("/startVideoConverter")
	public @ResponseBody Boolean startVideoConverter(@RequestBody Video Video) throws Exception {
		//在live表里面判断进程是否存在//dummy
		int islive = liveService.countByVideoId(Video.getId());
		if(islive==0&&Video.getCamRtsp()!=null&&Video.getCamPort()!=null&&Video.getCamRtsp()!=""&&Video.getCamPort()!=""){
			String commandStr = Video.getCamAdd()+" /b -I -vvv \""
					+Video.getCamRtsp()
					+"\" --sout=\"#transcode{vcodec=theo,vb=12000,acodec=vorb,ab=128,"
					+"channels=2,samplerate=44100}:http{mux=ogg,dst="
					+Video.getCamPort()+"}\" --sout-all --sout-keep";
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec(commandStr);
				List<String> tasklist = new ArrayList<String>();
				Process processt = runtime.exec("tasklist");
				BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream()));
				String readLine = "";
				while ((readLine = br.readLine()) != null) {
					if ("".equals(readLine)) {
						continue;
					}
					tasklist.add(readLine+" ");
				}
				// 获取每列最长的长度
				String maxRow = tasklist.get(1) + "";
				String[] maxCol = maxRow.split(" ");
				// 定义映像名称数组
				String[] taskName = new String[tasklist.size()];
				// 定义 PID数组
				String[] taskPid = new String[tasklist.size()];
				List<Live> liveList = liveService.getLiveAll();

				for (int i = 0; i < tasklist.size(); i++) {
					String data = tasklist.get(i) + "";
    					for (int j = 0; j < maxCol.length; j++) {
  						switch (j) {
						case 0:
							taskName[i] = data.substring(0, maxCol[j].length()+1);
 							data = data.substring(maxCol[j].length()+1);
							break;
						case 1:
 							taskPid[i] = data.substring(0, maxCol[j].length()+1);
							break;
						}
					}
    				String taskNameStr = taskName[i].trim();
    				String taskPidStr = taskPid[i].trim();
					if(taskNameStr.equals("vlc.exe")&&!listContains(liveList,taskPidStr)){
						Live live = new Live();
						live.setPid(taskPid[i].trim());
						live.setVideoId(Video.getId());
						liveService.insert(live);
					}
				}
			} catch (IOException e) {
		        e.printStackTrace();
		    }
			return true;
		}else {
			return false;
		}
	}

	private Boolean listContains(List<Live> liveList, String taskPidStr){
		Boolean result = false;
		for (int i = 0; i < liveList.size(); i++) {
			Live live = liveList.get(i);
			if(live.getPid().split(",")[0].equals(taskPidStr)){
				return true;
			}else{
				result = false;
			}
		}
		return result;
	}

	@RequestMapping("/stopVideoConverter")
	public @ResponseBody Boolean stopVideoConverter(@RequestBody Video Video) throws Exception {
		List<Live> livevideos = liveService.selectByVideoId(Video.getId());
		if(livevideos.size()==0) {
			return false;
		}else {
			Live livevideo = livevideos.get(0);
			int isDeleted = liveService.deleteByPrimaryKey(livevideo.getPid());
			if(isDeleted==0) {
				return false;
			}else {
				Runtime.getRuntime().exec("Taskkill /f /IM " + livevideo.getPid());
			}
			return true;
		}
	}

	@RequestMapping("/clearVideoConverter")
	public @ResponseBody int clearVideoConverter() throws Exception {
		int result = 1;
		Runtime runtime = Runtime.getRuntime();
		List<String> tasklist = new ArrayList<String>();
		Process processt = runtime.exec("tasklist");
		BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream()));
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			if ("".equals(readLine)) {
				continue;
			}
			tasklist.add(readLine+" ");
		}
		// 获取每列最长的长度
		String maxRow = tasklist.get(1) + "";
		String[] maxCol = maxRow.split(" ");
		// 定义映像名称数组
		String[] taskName = new String[tasklist.size()];
		// 定义 PID数组
		String[] taskPid = new String[tasklist.size()];
		for (int i = 0; i < tasklist.size(); i++) {
			String data = tasklist.get(i) + "";
				for (int j = 0; j < maxCol.length; j++) {
					switch (j) {
				case 0:
					taskName[i] = data.substring(0, maxCol[j].length()+1);
						data = data.substring(maxCol[j].length()+1);
					break;
				case 1:
						taskPid[i] = data.substring(0, maxCol[j].length()+1);
					break;
				}
			}
			String taskNameStr = taskName[i].trim();
			String taskPidStr = taskPid[i].trim();
			if(taskNameStr.equals("vlc.exe")){
				Runtime.getRuntime().exec("Taskkill /f /IM " + taskPidStr);
				result = liveService.deleteByPrimaryKey(taskPidStr);
			}
		}
		return result;
	}

	@RequestMapping("/freshNginx")
	public @ResponseBody Boolean freshNginx(@RequestBody Video Video) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		//停止当前nginx
		List<String> tasklist = new ArrayList<String>();
		Process processt = runtime.exec("tasklist");
		BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream()));
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			if ("".equals(readLine)) {
				continue;
			}
			tasklist.add(readLine+" ");
		}
		// 获取每列最长的长度
		String maxRow = tasklist.get(1) + "";
		String[] maxCol = maxRow.split(" ");
		// 定义映像名称数组
		String[] taskName = new String[tasklist.size()];
		// 定义 PID数组
		String[] taskPid = new String[tasklist.size()];
		for (int i = 0; i < tasklist.size(); i++) {
			String data = tasklist.get(i) + "";
			for (int j = 0; j < maxCol.length; j++) {
				switch (j) {
				case 0:
					taskName[i] = data.substring(0, maxCol[j].length()+1);
					data = data.substring(maxCol[j].length()+1);
					break;
				case 1:
					taskPid[i] = data.substring(0, maxCol[j].length()+1);
					break;
				}
			}
			String taskNameStr = taskName[i].trim();
			String taskPidStr = taskPid[i].trim();
			if(taskNameStr.equals("nginx.exe")){
				runtime.exec("Taskkill /f /IM " + taskPidStr);//关闭正在运行的nginx
			}
		}

		//获取全部视频信息，根据url的前四个字母判断是网络服务还是本地文件，并且port和rtsp不能为空，
		List<String> nginxConf = new ArrayList<String>();
		//逐行获取现有配置文件全部内容
		String confStr = Video.getCamAdd() + "conf\\nginx.conf";
		@SuppressWarnings("resource")
		BufferedReader bufferr = new BufferedReader(new FileReader(confStr));
		String data = bufferr.readLine();//一次读入一行，直到读入null为文件结束
		while(data!=null){
			//System.out.println(data);
			nginxConf.add(data);
			data = bufferr.readLine(); //接着读下一行
		}
		Boolean isServerin = false;
		List<Video> videoList = videoService.getVideoNoNodes();
		for (int j = 0; j < videoList.size(); j++) {
			isServerin = false;
			if(!videoList.get(j).getCamUrl().isEmpty()) {
				if(videoList.get(j).getCamUrl().substring(0, 4).equals("http")) {
					String rtsp = videoList.get(j).getCamRtsp();
					String port = videoList.get(j).getCamPort();
					if(rtsp!=null&&port!=null) {
						//修改nginx.conf文件的代理server
						String[] urlArr = videoList.get(j).getCamUrl().split(":");
						for (int i = 0; i < nginxConf.size(); i++) {
							//判断server是否已经存在
							if(nginxConf.get(i).length()>7&&nginxConf.get(i).substring(0, 6).equals("listen")) {
								if(nginxConf.get(i).substring(7).equals(urlArr[2]+";")
										&&nginxConf.get(i+1).substring(12).equals(urlArr[1].substring(2)+";")) {
									isServerin = true;
									//如果已经存在，检查参数是否正确，如不正确则修正
									if(!nginxConf.get(i+1).substring(12).equals(urlArr[1].substring(2)+";")) {
										nginxConf.set(i+1,"server_name "+urlArr[1].substring(2)+";");
									}
									String vlcOggUrl = urlArr[0]+":"+urlArr[1]+videoList.get(j).getCamPort()+";";
									if(!nginxConf.get(i+7).substring(11).equals(vlcOggUrl)) {
										nginxConf.set(i+7,"proxy_pass "+vlcOggUrl);
									}
								}
							}
						}

						List<String> newServer = new ArrayList<String>();
						if(!isServerin) {
							newServer.add("server {");
							newServer.add("listen "+urlArr[2]+";");
							newServer.add("server_name "+urlArr[1].substring(2)+";");
							newServer.add("location / {");
							newServer.add("add_header Access-Control-Allow-Origin *;");
							newServer.add("add_header 'Access-Control-Allow-Credentials' 'true';");
							newServer.add("add_header Access-Control-Allow-Headers X-Requested-With;");
							newServer.add("add_header 'Access-Control-Allow-Methods' 'GET, PUT, POST, DELETE, OPTIONS';");
							newServer.add("proxy_pass "+urlArr[0]+":"+urlArr[1]+videoList.get(j).getCamPort()+";");
							newServer.add("}");
							newServer.add("}");
							nginxConf.addAll(nginxConf.size()-1, newServer);
						}
					}
				}
			}
		}

		//将重新组织的conf逐行写入文件
		FileWriter fw = new FileWriter(confStr);
		for (int i = 0; i < nginxConf.size(); i++) {
			//System.out.println(nginxConf.get(i));
			fw.write(nginxConf.get(i)+"\r\n");
		}
		fw.close();

		//启动nginx
		String rootpath = Video.getCamAdd().substring(0,2);
        String nginxStr = "cmd /c "+rootpath+" & cd "+Video.getCamAdd()+" & start nginx";
		runtime.exec(nginxStr);

		return isServerin;
	}

	@RequestMapping("/startffVideoConverter")
	public @ResponseBody Boolean startffVideoConverter(@RequestBody Video Video) throws Exception {
		int islive = liveService.countByVideoId(Video.getId());
		//在live表里面判断进程是否存在
		if(islive==0&&Video.getCamRtsp()!=null&&Video.getCamPort()!=null&&Video.getCamRtsp()!=""&&Video.getCamPort()!=""){
			String rootpath = Video.getCamAdd().substring(0,2);
			String commandStr = "cmd /k"+rootpath+" & cd "+Video.getCamAdd()
					+" & start ffmpeg -rtsp_transport tcp -i "
					+Video.getCamRtsp()
					+" -preset ultrafast -tune zerolatency"
					+" -rc-lookahead 0"
					+" -vcodec libx264 -threads 16"
					+" -b:v 20480k -s 720x576 -r 22 -an -b:a 128k"
					+" -f flv "
					+Video.getCamPort();
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec(commandStr);//启动转码

				//获取进程，添加转码记录
				List<String> tasklist = new ArrayList<String>();
				Process processt = runtime.exec("tasklist");
				BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream()));
				String readLine = "";
				while ((readLine = br.readLine()) != null) {
					if ("".equals(readLine)) {
						continue;
					}
					tasklist.add(readLine+" ");
				}
				// 获取每列最长的长度
				String maxRow = tasklist.get(1) + "";
				String[] maxCol = maxRow.split(" ");
				// 定义映像名称数组
				String[] taskName = new String[tasklist.size()];
				// 定义 PID数组
				String[] taskPid = new String[tasklist.size()];

				List<Live> liveList = liveService.getLiveAll();
				List<String> pidArr = new ArrayList<String>();
				for (int n = 0; n < liveList.size(); n++) {
					Live live = liveList.get(n);
					pidArr.add(live.getPid());
				}
				for (int i = 0; i < tasklist.size(); i++) {
					String data = tasklist.get(i) + "";
    					for (int j = 0; j < maxCol.length; j++) {
  						switch (j) {
						case 0:
							taskName[i] = data.substring(0, maxCol[j].length()+1);
 							data = data.substring(maxCol[j].length()+1);
							break;
						case 1:
 							taskPid[i] = data.substring(0, maxCol[j].length()+1);
							break;
						}
					}
    				String taskNameStr = taskName[i].trim();
    				String taskPidStr = taskPid[i].trim();
					if(taskNameStr.equals("ffmpeg.exe")&&!pidArr.contains(taskPidStr)){
						Live live = new Live();
						live.setPid(taskPid[i].trim());
						live.setVideoId(Video.getId());
						liveService.insert(live);
						break;
					}
				}
			} catch (IOException e) {
		        e.printStackTrace();
		    }
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/stopffVideoConverter")
	public @ResponseBody Boolean stopffVideoConverter(@RequestBody Video Video) throws Exception {
		List<Live> livevideos = liveService.selectByVideoId(Video.getId());
		if(livevideos.size()==0) {
			return false;
		}else {
			Live livevideo = livevideos.get(0);
			int isDeleted = liveService.deleteByPrimaryKey(livevideo.getPid());
			if(isDeleted==0) {
				return false;
			}else {
				Runtime.getRuntime().exec("Taskkill /f /IM " + livevideo.getPid());
			}
			return true;
		}
	}

	@RequestMapping("/clearffVideoConverter")
	public @ResponseBody int clearffVideoConverter() throws Exception {
		int result = 1;
		Runtime runtime = Runtime.getRuntime();
		List<String> tasklist = new ArrayList<String>();
		Process processt = runtime.exec("tasklist");
		BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream()));
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			if ("".equals(readLine)) {
				continue;
			}
			tasklist.add(readLine+" ");
		}
		// 获取每列最长的长度
		String maxRow = tasklist.get(1) + "";
		String[] maxCol = maxRow.split(" ");
		// 定义映像名称数组
		String[] taskName = new String[tasklist.size()];
		// 定义 PID数组
		String[] taskPid = new String[tasklist.size()];
		for (int i = 0; i < tasklist.size(); i++) {
			String data = tasklist.get(i) + "";
				for (int j = 0; j < maxCol.length; j++) {
					switch (j) {
				case 0:
					taskName[i] = data.substring(0, maxCol[j].length()+1);
						data = data.substring(maxCol[j].length()+1);
					break;
				case 1:
						taskPid[i] = data.substring(0, maxCol[j].length()+1);
					break;
				}
			}
			String taskNameStr = taskName[i].trim();
			String taskPidStr = taskPid[i].trim();
			if(taskNameStr.equals("ffmpeg.exe")){
				Runtime.getRuntime().exec("Taskkill /f /IM " + taskPidStr);
				result = liveService.deleteByPrimaryKey(taskPidStr);
			}
		}
		return result;
	}

	@SuppressWarnings("resource")
	@RequestMapping("/freshNginxff")
	public @ResponseBody Boolean freshNginxff(@RequestBody Video Video) throws Exception {
		//清除所有转码记录
		List<Live> liveList = liveService.getLiveAll();
		for (int i = 0; i < liveList.size(); i++) {
			liveService.deleteByPrimaryKey(liveList.get(i).getPid());
		}

		//重启nginx
		Runtime runtime = Runtime.getRuntime();
		List<String> tasklist = new ArrayList<String>();
		Process processt = runtime.exec("tasklist");
		BufferedReader br = new BufferedReader(new InputStreamReader(processt.getInputStream(),"UTF-8"));
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			if ("".equals(readLine)) {
				continue;
			}
			tasklist.add(readLine+" ");
		}
		// 获取每列最长的长度
		String maxRow = tasklist.get(1) + "";
		String[] maxCol = maxRow.split(" ");
		// 定义映像名称数组
		String[] taskName = new String[tasklist.size()];
		// 定义 PID数组
		String[] taskPid = new String[tasklist.size()];
		for (int i = 0; i < tasklist.size(); i++) {
			String data = tasklist.get(i) + "";
			for (int j = 0; j < maxCol.length; j++) {
				switch (j) {
				case 0:
					taskName[i] = data.substring(0, maxCol[j].length()+1);
					data = data.substring(maxCol[j].length()+1);
					break;
				case 1:
					taskPid[i] = data.substring(0, maxCol[j].length()+1);
					break;
				}
			}
			String taskNameStr = taskName[i].trim();
			String taskPidStr = taskPid[i].trim();
			if(taskNameStr.equals("nginx.exe")){
				runtime.exec("Taskkill /f /IM " + taskPidStr);//关闭正在运行的nginx
			}
		}

		//获取全部视频信息，根据url的前四个字母判断是网络服务还是本地文件，并且port和rtsp不能为空，
		List<String> nginxConf = new ArrayList<String>();
		//逐行获取现有配置文件全部内容
		String confStr = Video.getCamAdd() + "conf\\nginx.conf";

		InputStreamReader reader = new InputStreamReader(new FileInputStream(confStr), "UTF-8");
		BufferedReader bufferr = new BufferedReader(reader);
		String data = bufferr.readLine();//一次读入一行，直到读入null为文件结束
		while(data!=null){
			//System.out.println(data);
			nginxConf.add(data);
			data = bufferr.readLine(); //接着读下一行
		}

		//清除原有rtmp服务
		Boolean isStartDelete = false;
		for (int i = 0; i < nginxConf.size() - 1; i++) {
			if(isStartDelete) {
				nginxConf.remove(i--);
			}
			if(nginxConf.get(i).equals("rtmp {")) {
				isStartDelete = true;
			}
		}

		List<Video> videoList = videoService.getVideoNoNodes();
		ArrayList<String> portArrNoRepeat = new ArrayList<String>();
		String serverName = "";
		for (int j = 0; j < videoList.size(); j++) {
			String port = videoList.get(j).getCamPort()==null?"":videoList.get(j).getCamPort();
			if(!port.isEmpty()) {
				if(port.substring(0, 4).equals("rtmp")) {
					String rtsp = videoList.get(j).getCamRtsp()==null?"":videoList.get(j).getCamRtsp();
					String camUrl = videoList.get(j).getCamUrl()==null?"":videoList.get(j).getCamUrl();
					if(!rtsp.isEmpty()&&!camUrl.isEmpty()) {
						String[] portArr = port.split(":");
						serverName = portArr[1].substring(2, portArr[1].length());
						String portStr = portArr[2].substring(0, 4);
						if(!portArrNoRepeat.contains(portStr)) {
							//添加nginx.conf文件的代理server
							List<String> newServer = new ArrayList<String>();
							newServer.add("server {");
							newServer.add("listen "+portStr+";");
							newServer.add("server_name "+serverName+";");
							newServer.add("chunk_size 4000;");
							newServer.add("application live {");
							newServer.add("live on;");
							newServer.add("gop_cache on;");
							newServer.add("}");
							newServer.add("}");
							nginxConf.addAll(nginxConf.size()-1, newServer);

							portArrNoRepeat.add(portStr);
						}
					}
				}
			}
		}

		List<String> newServer = new ArrayList<String>();
		newServer.add("server {");
		newServer.add("listen 19999;");
		if(serverName.equals("")) {
			serverName = "127.0.0.1";
		}
		newServer.add("server_name "+serverName+";");

		newServer.add("chunk_size 4000;");
		newServer.add("application live {");
		newServer.add("live on;");
		newServer.add("gop_cache on;");
		newServer.add("}");
		newServer.add("}");
		nginxConf.addAll(nginxConf.size()-1, newServer);

		//将重新组织的conf逐行写入文件
//		FileWriter fw = new FileWriter(confStr);
//		for (int i = 0; i < nginxConf.size(); i++) {
//			//System.out.println(nginxConf.get(i));
//			fw.write(nginxConf.get(i)+"\r\n");
//		}
//		fw.close();

		/** 解决没有设置utf-8编码格式导致nginx.conf文件写入新内容后无法读取的问题 update by lanls,2022.07.15 */
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(confStr), "UTF-8");
		for (int i = 0; i < nginxConf.size(); i++) {
			writer.write(nginxConf.get(i)+"\r\n");
		}
		writer.close();

		//启动nginx
		String rootpath = Video.getCamAdd().substring(0,2);
        String nginxStr = "cmd /c "+rootpath+" & cd "+Video.getCamAdd()+" & start nginx";
		runtime.exec(nginxStr);

		return isStartDelete;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Video Video) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		Video.setId(uid);
		int result = videoService.insert(Video);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Video Video) throws Exception{
		int result = videoService.updateByPrimaryKey(Video);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Video Video) throws Exception{
		int result = videoService.deleteByPrimaryKey(Video.getId());
		return result;
	}
}
