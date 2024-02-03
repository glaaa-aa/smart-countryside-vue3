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

import com.qfy.modules.city3d.po.Car;
import com.qfy.modules.city3d.po.EquipmentRel;
import com.qfy.modules.city3d.po.VehicleRealData;
import com.qfy.modules.city3d.service.CarService;
import com.qfy.modules.city3d.service.EquipmentRelService;
import com.qfy.modules.city3d.service.VehicleRealDataService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/car")
public class CarController extends CrossOriginController{
	@Autowired
	private CarService carService;

	@Autowired
	private VehicleRealDataService vehicleRealDataService;

	@Autowired
	private EquipmentRelService equipmentRelService;

	@RequestMapping("/getAllCars")
	public @ResponseBody List<Car> getAllCars() throws Exception {
		List<Car> result = carService.getPoliceCarList();
		return result;
	}

	@RequestMapping("/getCarRealgpsByPhone")
	public @ResponseBody List<VehicleRealData> getCarRealgpsByPhone(@RequestBody Car car) throws Exception {
		String phone = car.getCardNum();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("simNo", phone);
		VehicleRealData realData = vehicleRealDataService.getVehicleByPhone(params);
		List<VehicleRealData> realdataList = new ArrayList<VehicleRealData>();
		realdataList.add(realData);
		return realdataList;
	}

	@RequestMapping("/getPoliceCarRealDataPart")
	public @ResponseBody List<Object> getPoliceCarRealDataPart(@RequestBody Car car) throws Exception {
		List<Car> carList = carService.getPoliceCarListPart(car.getCarLicenses());
		List<String> simNos = new ArrayList<String>();
		for(Car car0 : carList){
			simNos.add(car0.getCardNum());
		}
		List<Object> realdataList = new ArrayList<Object>();
		List<VehicleRealData> realDatas = vehicleRealDataService.getVehiclePartByPhone(simNos);
		for (int i = 0; i < carList.size(); i++) {
			Map<String, Object> result = new HashMap();
			result.put("car", carList.get(i));
			for(VehicleRealData vehicleRealData : realDatas){
				if(vehicleRealData.getSimNo().equals(carList.get(i).getCardNum())){
					result.put("realdata", vehicleRealData);
					break;
				}
			}
			realdataList.add(result);
		}
		return realdataList;
	}

	@RequestMapping("/getPoliceCarRealData")
	public @ResponseBody List<Object> getPoliceCarRealData() throws Exception {
		List<Car> carList = carService.getPoliceCarList();
		List<String> simNos = new ArrayList<String>();
		for(Car car0 : carList){
			simNos.add(car0.getCardNum());
		}
		List<Object> realdataList = new ArrayList<Object>();
		List<VehicleRealData> realDatas = vehicleRealDataService.getVehiclePartByPhone(simNos);
		for (int i = 0; i < carList.size(); i++) {
			Map<String,Object> result = new HashMap();
			result.put("car", carList.get(i));
			for(VehicleRealData vehicleRealData : realDatas){
				if(vehicleRealData.getSimNo().equals(carList.get(i).getCardNum())){
					result.put("realdata", vehicleRealData);
					break;
				}
			}
			realdataList.add(result);
		}
		return realdataList;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestParam("files") MultipartFile[] files,@RequestParam("carId") String carId,
			@RequestParam("carLicense") String carLicense,@RequestParam("carStyle") String carStyle,@RequestParam("carDate") String carDate,
			@RequestParam("driver") String driver,@RequestParam("policeType") String policeType,@RequestParam("cardNum") String cardNum,
			@RequestParam("image") String image,@RequestParam("equips") List<String> equips,HttpServletRequest request) throws Exception{
		Car car = new Car();
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/car/car_"+carLicense+extName;
					car.setImage(fileName);
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
		car.setCarId(uid);
		car.setCarLicense(carLicense);
		car.setCarStyle(carStyle);
		car.setCarDate(carDate);
		car.setDriver(driver);
		car.setPoliceType(policeType);
		car.setCardNum(cardNum);
		int result = carService.insert(car);
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
	public @ResponseBody int updateByPrimaryKey(@RequestParam("files") MultipartFile[] files,@RequestParam("carId") String carId,
			@RequestParam("carLicense") String carLicense,@RequestParam("carStyle") String carStyle,@RequestParam("carDate") String carDate,
			@RequestParam("driver") String driver,@RequestParam("policeType") String policeType,@RequestParam("cardNum") String cardNum,
			@RequestParam("image") String image,@RequestParam("equips") List<String> equips,HttpServletRequest request) throws Exception{
		equipmentRelService.deleteEquipmentRel(carId);
		Car car = new Car();
		car.setImage(image);
		for (MultipartFile file: files){
			if (!file.isEmpty()){
			    try {
					String fileName = "";
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					fileName = "image/car/car_"+carLicense+extName;
					car.setImage(fileName);
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
		car.setCarId(carId);
		car.setCarLicense(carLicense);
		car.setCarStyle(carStyle);
		car.setCarDate(carDate);
		car.setDriver(driver);
		car.setPoliceType(policeType);
		car.setCardNum(cardNum);
		int result = carService.updateByPrimaryKey(car);
		CharacterUtils characterUtils = new CharacterUtils();
		for(String equipId: equips) {
			EquipmentRel equipmentRel = new EquipmentRel();
			String equipRelId = characterUtils.getUUID();
			equipmentRel.setEquipRelId(equipRelId);
			equipmentRel.setEquipId(equipId);
			equipmentRel.setRelId(carId);
			equipmentRelService.insertEquipmentRel(equipmentRel);
		}
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody Car car) throws Exception{
		if(car.getImage()!=null&&car.getImage()!="") {
			try {
				String path = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\"+car.getImage();
				File fileDel = new File(path);
				fileDel.createNewFile();
				fileDel.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		int result = carService.deleteByPrimaryKey(car.getCarId());
		int result0 = equipmentRelService.deleteEquipmentRel(car.getCarId());
		result = result0 == 0 ? 2 : result;
		return result;
	}

	@RequestMapping("/getCarAndEquipmentsByPuid")
	public @ResponseBody Map<String,Object> getCarAndEquipmentsByPuid(@RequestBody Car car) throws Exception{
		Map<String,Object> resultmap = new HashMap();
		try {
			Car result = carService.getCarAndEquipmentsByPuid(car.getCarId());
			resultmap.put("carId", result.getCarId());
			resultmap.put("depId", result.getDepId());
			resultmap.put("carLicense", result.getCarLicense());
			resultmap.put("carStyle", result.getCarStyle());
			resultmap.put("carDate", result.getCarDate());
			resultmap.put("driver", result.getDriver());
			resultmap.put("policeType", result.getPoliceType());
			resultmap.put("deviceId", result.getDeviceId());
			resultmap.put("cardNum", result.getCardNum());
			resultmap.put("image", result.getImage());
			resultmap.put("CarEquipments", result.getEquipmentRels());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("simNo", result.getCardNum());
			VehicleRealData realData = vehicleRealDataService.getVehicleByPhone(params);
			resultmap.put("updateDate", realData.getUpdateDate());
			resultmap.put("velocity", realData.getVelocity());
			resultmap.put("direction", realData.getDirection());
			resultmap.put("gas", realData.getGas());
			resultmap.put("mileage", realData.getMileage());
		} catch (Exception e) {
			List<Car> result = carService.findCarByPuid(car.getCarId());
			resultmap.put("carId", result.get(0).getCarId());
			resultmap.put("depId", result.get(0).getDepId());
			resultmap.put("carLicense", result.get(0).getCarLicense());
			resultmap.put("carStyle", result.get(0).getCarStyle());
			resultmap.put("carDate", result.get(0).getCarDate());
			resultmap.put("driver", result.get(0).getDriver());
			resultmap.put("policeType", result.get(0).getPoliceType());
			resultmap.put("deviceId", result.get(0).getDeviceId());
			resultmap.put("cardNum", result.get(0).getCardNum());
			resultmap.put("image", result.get(0).getImage());
			resultmap.put("CarEquipments", null);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("simNo", result.get(0).getCardNum());
			VehicleRealData realData = vehicleRealDataService.getVehicleByPhone(params);
			resultmap.put("updateDate", realData.getUpdateDate());
			resultmap.put("velocity", realData.getVelocity());
			resultmap.put("direction", realData.getDirection());
			resultmap.put("gas", realData.getGas());
			resultmap.put("mileage", realData.getMileage());
		}
		return resultmap;
	}
}
