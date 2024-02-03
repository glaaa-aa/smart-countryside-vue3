package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 设备信息
 * @date 2019-04-17 15:53:21
 */
@Controller
@RequestMapping("/api/meterController")
public class ApiMeterController {

    private static final Logger logger = LoggerFactory.getLogger(ApiMeterController.class);

//    @Autowired
//    private SystemService systemService;
//    @Autowired
//    private WaterInfoServiceI waterInfoServiceI;
//
//    @Autowired
//    private WaterUseInfoServiceI waterUseInfoServiceI;
//
//    /**
//     * 表详情详情
//     *
//     * @param request
//     * @param meterNo
//     * @return
//     */
//    @RequestMapping(params = "waterdetails")
//    @ResponseBody
//    public AjaxJson waterdetails(HttpServletRequest request, @RequestParam(required = true) String meterNo) {
//        String message = null;
//        AjaxJson j = new AjaxJson();
//        message = "访问成功";
//        String token = ValidTokenUtil.checkReq(request);
//        if (StringUtil.isEmpty(token)) {
//            j.setMsg("API认证失败");
//            j.setSuccess(false);
//            return j;
//        }
//        String sql = "select meter_num as meterno,sbstate,build_num as buildnum,room_num as roomnum,floor,site from water_info  where meter_num=? ";
//        Map<String, Object> resultMap = waterInfoServiceI.findOneForJdbc(sql, meterNo);
//
//
//        String sqlc = "select time,reading,meter_num  from water_use_info where meter_num=?  order by create_date desc limit 0,1";
//
//        Map<String, Object> resultMaps = waterInfoServiceI.findOneForJdbc(sqlc, meterNo);
//
//        if (resultMaps != null) {
//            resultMap.put("time", resultMaps.get("time") == null ? "" : resultMaps.get("time").toString());
//            resultMap.put("reading", resultMaps.get("reading") == null ? "" : resultMaps.get("reading").toString());
//        } else {
//            resultMap.put("time", "");
//            resultMap.put("reading", "");
//        }
//        j.setAttributes(resultMap);
//        return j;
//    }
//
//
//    /**
//     * 表详情详情
//     *
//     * @param request
//     * @param meterNo
//     * @return
//     */
//    @RequestMapping(params = "saveWateruseinfo")
//    @ResponseBody
//    public AjaxJson saveWateruseinfo(HttpServletRequest request, @RequestParam(required = true) String meterNo, @RequestParam(required = true) String sbstate, @RequestParam(required = true) String usereading) throws Exception {
//        String message = null;
//        AjaxJson j = new AjaxJson();
//        message = "访问成功";
//        String token = ValidTokenUtil.checkReq(request);
//        if (StringUtil.isEmpty(token)) {
//            j.setMsg("API认证失败");
//            j.setSuccess(false);
//            return j;
//        }
//
//        String sqlc = "select time,reading,meter_num  from water_use_info where meter_num=?  order by create_date desc limit 0,1";
//
//        Map<String, Object> resultMaps = waterInfoServiceI.findOneForJdbc(sqlc, meterNo);
//        WaterUseCostEntity waterusecost = new WaterUseCostEntity();
//        waterusecost.setTime(DateUtils.formatDate());
//        waterusecost.setMeterNum(meterNo);
//        waterusecost.setReading(Double.valueOf(usereading));
//
//        if (resultMaps != null) {
//            if (resultMaps.get("reading") == null) {
//                waterusecost.setPrereading(0.0);
//            } else {
//                waterusecost.setPrereading(Double.valueOf(resultMaps.get("reading").toString()));
//            }
//        } else {
//            waterusecost.setPrereading(0.0);
//        }
//        Double reading=waterusecost.getReading()-waterusecost.getPrereading();
//        WaterInfoEntity waterInfo=waterInfoServiceI.findUniqueByProperty(WaterInfoEntity.class,"meterNum",meterNo);
//        waterusecost.setUsereading(reading*waterInfo.getMultiply());
//        waterusecost.setSbstate(Integer.valueOf(sbstate));
//        waterusecost.setCreateDate(new Date());
//        waterUseInfoServiceI.save(waterusecost);
//
//
//        WaterUseInfoEntity entity = new WaterUseInfoEntity();
//        entity.setSbstate(Integer.valueOf(sbstate));
//        entity.setUsereading(reading);
//        entity.setReading(Double.valueOf(usereading));
//        entity.setTime(DateUtils.formatDate());
//        entity.setMeterNum(meterNo);
//        entity.setCreateDate(new Date());
//        waterUseInfoServiceI.save(entity);
//
//
//        return j;
//    }
//
//
//
//    /**
//     * 读数
//     *
//     * @param request
//     * @param meterNo
//     * @return
//     */
//    @RequestMapping(params = "getReadingList")
//    @ResponseBody
//    public AjaxJson getReadingList(HttpServletRequest request, @RequestParam(required = true) String meterNo) {
//        String message = null;
//        AjaxJson j = new AjaxJson();
//        message = "访问成功";
//        String token = ValidTokenUtil.checkReq(request);
//        if (StringUtil.isEmpty(token)) {
//            j.setMsg("API认证失败");
//            j.setSuccess(false);
//            return j;
//        }
//        Map<String, Object> resultMa=new HashMap<String, Object>();
//        String sql = "select meter_num,reading,usereading,create_date from water_use_info where meter_num=? order by create_date desC";
//        List<Map<String, Object>> mapList = waterInfoServiceI.findForJdbc(sql, meterNo);
//        resultMa.put("readingList",mapList);
//        j.setAttributes(resultMa);
//        return j;
//    }


}
