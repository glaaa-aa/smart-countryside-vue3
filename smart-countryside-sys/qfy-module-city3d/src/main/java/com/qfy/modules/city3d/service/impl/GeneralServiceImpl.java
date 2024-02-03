package com.qfy.modules.city3d.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AddressMapper;
import com.qfy.modules.city3d.mapper.AlarmMapper;
import com.qfy.modules.city3d.mapper.AlertMapper;
import com.qfy.modules.city3d.mapper.BimMapper;
import com.qfy.modules.city3d.mapper.BuildingMapper;
import com.qfy.modules.city3d.mapper.CarMapper;
import com.qfy.modules.city3d.mapper.CompanyMapper;
import com.qfy.modules.city3d.mapper.CoverageMapper;
import com.qfy.modules.city3d.mapper.DatalayerMapper;
import com.qfy.modules.city3d.mapper.EplanMapper;
import com.qfy.modules.city3d.mapper.EquipmentMapper;
import com.qfy.modules.city3d.mapper.FaceIdentityMapper;
import com.qfy.modules.city3d.mapper.GeneralMapper;
import com.qfy.modules.city3d.mapper.HouseMapper;
import com.qfy.modules.city3d.mapper.KeyPeopleMapper;
import com.qfy.modules.city3d.mapper.NetMapper;
import com.qfy.modules.city3d.mapper.PeopleBaseMapper;
import com.qfy.modules.city3d.mapper.PlanMapper;
import com.qfy.modules.city3d.mapper.PoliceCaseMapper;
import com.qfy.modules.city3d.mapper.PoliceMapper;
import com.qfy.modules.city3d.mapper.PoliceNetMapper;
import com.qfy.modules.city3d.mapper.RoamMapper;
import com.qfy.modules.city3d.mapper.SecurityMapper;
import com.qfy.modules.city3d.mapper.SysParaMapper;
import com.qfy.modules.city3d.mapper.Tiles3dMapper;
import com.qfy.modules.city3d.mapper.VideoMapper;
import com.qfy.modules.city3d.mapper.WallMapper;
import com.qfy.modules.city3d.po.SysPara;
import com.qfy.modules.city3d.po.VideoExample;
import com.qfy.modules.city3d.service.GeneralService;
import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	private GeneralMapper generalMapper;
	@Autowired
	private PoliceCaseMapper policeCaseMapper;
	@Autowired
	private WallMapper wallMapper;
	@Autowired
	private FaceIdentityMapper faceIdentityMapper;
	@Autowired
	private AlertMapper alertMapper;
	@Autowired
	private AlarmMapper alarmMapper;
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private PoliceMapper policeMapper;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private PeopleBaseMapper peopleBaseMapper;
	@Autowired
	private HouseMapper houseMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private NetMapper netMapper;
	@Autowired
	private SecurityMapper securityMapper;
	@Autowired
	private EplanMapper eplanMapper;
	@Autowired
	private PlanMapper planMapper;
	@Autowired
	private BimMapper bimMapper;
	@Autowired
	private KeyPeopleMapper keyPeopleMapper;
	@Autowired
	private CoverageMapper coverageMapper;
	@Autowired
	private PoliceNetMapper policeNetMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private Tiles3dMapper tiles3dMapper;
	@Autowired
	private DatalayerMapper datalayerMapper;
	@Autowired
	private RoamMapper roamMapper;
	@Autowired
	private SysParaMapper sysParaMapper;


	@Override
	public List<HashMap<String, String>> countPeople() {
		List<HashMap<String, String>> result = generalMapper.countPeople();
		return result;
	}

	@Override
	public List<HashMap<String, String>> countAdd() {
		List<HashMap<String, String>> result = generalMapper.countAdd();
		return result;
	}

	@Override
	public List<HashMap<String, String>> countDep() {
		List<HashMap<String, String>> result = generalMapper.countDep();
		return result;
	}

	@Override
	public List<HashMap<String, String>> countPatrol() {
		List<HashMap<String, String>> result = generalMapper.countPatrol();
		return result;
	}

	@Override
	public List<HashMap<String, String>> countCam() {
		List<HashMap<String, String>> result = generalMapper.countCam();
		return result;
	}

	@Override
	public List<HashMap<String, String>> countMan() {
		List<HashMap<String, String>> result = generalMapper.countMan();
		return result;
	}

	@Override
	public List<SysPara> getMain() {
		List<SysPara> result = sysParaMapper.selectByExample(null);
		return result;
	}

	@Override
	public List<Object> countGeneralByExample() {
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(policeCaseMapper.countByExample(null));
		resultList.add(wallMapper.countByExample(null));
		resultList.add(faceIdentityMapper.countByExample(null));
		resultList.add(alertMapper.countByExample(null));
		resultList.add(alarmMapper.countByExample(null));
		VideoExample example = new VideoExample();
		example.or().andCamIsnodeEqualTo(false);
		resultList.add(videoMapper.countByExample(example));
		resultList.add(policeMapper.countByExample(null));
		resultList.add(carMapper.countByExample(null));
		resultList.add(equipmentMapper.countByExample(null));
		resultList.add(addressMapper.countByExample(null));
		resultList.add(peopleBaseMapper.countByExample(null));
		resultList.add(houseMapper.countByExample(null));
		resultList.add(companyMapper.countByExample(null));
		resultList.add(netMapper.countByExample(null));
		resultList.add(securityMapper.countByExample(null));
		resultList.add(eplanMapper.countByExample(null));
		resultList.add(planMapper.countByExample(null));
		resultList.add(bimMapper.countByExample(null));
		resultList.add(keyPeopleMapper.countByExample(null));
		resultList.add(coverageMapper.countByExample(null));
		resultList.add(policeNetMapper.countByExample(null));
		resultList.add(buildingMapper.countByExample(null));
		resultList.add(tiles3dMapper.countByExample(null));
		resultList.add(datalayerMapper.countByExample(null));
		resultList.add(roamMapper.countByExample(null));
		return resultList;
	}
}
