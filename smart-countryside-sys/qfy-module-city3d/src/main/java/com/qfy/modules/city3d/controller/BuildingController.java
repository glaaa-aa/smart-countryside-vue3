package com.qfy.modules.city3d.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.WKTReader;

import com.qfy.modules.city3d.po.Building;
import com.qfy.modules.city3d.service.BuildingService;
import com.qfy.modules.city3d.util.CharacterUtils;

@Controller
@RequestMapping("/building")
public class BuildingController extends CrossOriginController{
	@Autowired
	private BuildingService buildingService;

	@RequestMapping("/getBuilding")
	public @ResponseBody List<Building> getBuilding() throws Exception {
		List<Building> result = buildingService.getBuilding();
		return result;
	}

	@RequestMapping("/getBuildingById")
	public @ResponseBody Building getBuildingById(@RequestBody Building building) throws Exception {
		Building result = buildingService.getBuildingById(building.getBuildingId());
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Building building) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		building.setBuildingId(uid);
		int result = buildingService.insert(building);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int update(@RequestBody Building building) throws Exception {
		int result = buildingService.updateByPrimaryKey(building);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int delete(@RequestBody Building building) throws Exception {
		int result = buildingService.deleteByPrimaryKey(building.getBuildingId());
		return result;
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody int batchDelete(@RequestBody List<Building> buildings) throws Exception {
		List<String> buildingIds = new ArrayList();
		for(Building building : buildings) {
			buildingIds.add(building.getBuildingId());
		}
		int result = buildingService.batchDelete(buildingIds);
		return result;
	}

	@RequestMapping("/queryBuilding")
	public @ResponseBody List<Building> queryBuilding(@RequestBody Building building) throws Exception {
		List<Building> result = buildingService.queryBuilding(building);
		return result;
	}

	@RequestMapping("/selectbyshp")
	public @ResponseBody List<Building> selectbyshp(String filename,HttpServletRequest request) throws Exception {
		ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
		List<Building> result = new ArrayList<Building>();
		List<Building> buildingList = buildingService.getBuilding();
		try {
			//读取shp
			String savePath = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\shp\\"+filename;
			ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File(savePath).toURI().toURL());
			sds.setCharset(Charset.forName("UTF-8"));
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            SimpleFeatureIterator itertor = featureSource.getFeatures().features();
            WKTReader reader = new WKTReader(geometryFactory);
            int n = 0;

            while(itertor.hasNext()) {
            	SimpleFeature feature = itertor.next();
            	MultiPolygon geom = (MultiPolygon) feature.getAttribute("the_geom");
            	String geomStr = "POLYGON(("+geom.toString().substring(16, geom.toString().length()-3)+"))";
            	//System.out.println(geomStr);
            	String[] valueArr = geom.toString().substring(16, geom.toString().length()-3).split(",");
            	String valueStr = new String();
            	for (int i = 0; i < valueArr.length; i++) {
            		String[] valuexy = valueArr[i].split(" ");
            		if(i==0) {
            			valueStr += valuexy[0]+","+valuexy[1]+",";
            		}else {
            			valueStr += valuexy[1]+","+valuexy[2]+",";
            		}
				}
                String valueStrResult = valueStr.substring(0, valueStr.length()-1);
            	Building queryshp = new Building();
            	queryshp.setBuildingId("qshp_"+n);
            	n++;
            	queryshp.setCoordinates(valueStrResult);
            	result.add(queryshp);

            	//shp框选范围
            	Geometry shpgeometry = reader.read(geomStr);
                for (int i = 0; i < buildingList.size(); i++) {
                	String coordinates = buildingList.get(i).getCoordinates();
                	String[] coordinatesArr = coordinates.split(",");
                	String gtlineStr = "POLYGON((";
                	for (int j = 0; j < coordinatesArr.length; j+=2) {
                		gtlineStr += coordinatesArr[j] + " " + coordinatesArr[j+1] + ",";
					}
                	gtlineStr += coordinatesArr[0] + " " + coordinatesArr[1] + "))";
                	Geometry bugeometry = reader.read(gtlineStr);
                	//相交
                	if(shpgeometry.intersects(bugeometry)) {
                		Building resultBu = buildingList.get(i);
                		result.add(resultBu);
                	}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}

	@RequestMapping("/saveShp")
	public @ResponseBody int saveShp(String filename,HttpServletRequest request) throws Exception {
		ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
		DbaseFileReader dbfreader = null;
		int result = 0;
		try {
			//读取shp
			String savePath = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\shp\\"+filename;
			ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File(savePath).toURI().toURL());
			sds.setCharset(Charset.forName("UTF-8"));
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            SimpleFeatureIterator itertor = featureSource.getFeatures().features();

            //读取dbf
            String dbffilename = filename.substring(0,filename.lastIndexOf("."))+".dbf";
            String dbfPath = System.getProperty("catalina.home")+"\\webapps\\city3dfile\\shp\\"+dbffilename;
            dbfreader = new DbaseFileReader(new ShpFiles(dbfPath), false, Charset.forName("GBK"));
	        DbaseFileHeader dbfheader = dbfreader.getHeader();
	        int numFields = dbfheader.getNumFields();

            while(itertor.hasNext()&&dbfreader.hasNext()) {
            	SimpleFeature feature = itertor.next();
            	MultiPolygon geom = (MultiPolygon) feature.getAttribute( "the_geom" );
            	String geomStr = geom.toString().substring(16, geom.toString().length()-3).split("\\)")[0];
            	String[] valueArr = geomStr.split(",");
            	String valueStr = new String();
            	for (int i = 0; i < valueArr.length; i++) {
            		String[] valuexy = valueArr[i].split(" ");
            		if(i==0) {
            			valueStr += valuexy[0]+","+valuexy[1]+",";
            		}else {
            			valueStr += valuexy[1]+","+valuexy[2]+",";
            		}
				}
                String valueStrResult = valueStr.substring(0, valueStr.length()-1);

                Building building = new Building();
                CharacterUtils characterUtils = new CharacterUtils();
        		String uid = characterUtils.getUUID();
        		building.setBuildingId(uid);
                building.setCoordinates(valueStrResult);

                Object[] entry = dbfreader.readEntry();
                for (int j = 0; j < numFields; j++) {
					String title = dbfheader.getFieldName(j);
					switch (title) {
					case "dbnum":
						building.setDbnum(entry[j].toString());
						break;
					case "tdxz":
						building.setTdxz(entry[j].toString());
						break;
					case "yxgs":
						building.setYxgs(entry[j].toString());
						break;
					case "jzzdmj":
						building.setJzzdmj(entry[j].toString());
						break;
					case "sjjzmj":
						building.setSjjzmj(entry[j].toString());
						break;
					case "fwcs":
						building.setFwcs(entry[j].toString());
						break;
					case "fwjg":
						building.setFwjg(entry[j].toString());
						break;
					case "zdh":
						building.setZdh(entry[j].toString());
						break;
					case "fczh":
						building.setFczh(entry[j].toString());
						break;
					default:
						break;
					}
				}
                result = buildingService.insert(building);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
}
