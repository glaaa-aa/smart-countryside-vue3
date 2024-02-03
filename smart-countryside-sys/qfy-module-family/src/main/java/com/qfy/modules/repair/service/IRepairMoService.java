package com.qfy.modules.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jeecg.weibo.exception.BusinessException;
import com.qfy.modules.commons.model.SystemUserSysDepartModel;
import com.qfy.modules.repair.entity.RecordVo;
import com.qfy.modules.repair.entity.RepairMo;
import java.util.List;
import java.util.Map;
import org.jeecg.common.api.vo.Result;

/**
 * @Description: 维修工单
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
public interface IRepairMoService extends IService<RepairMo> {

    /**
     * 根据故障上报/代办ID 生成任务 并执行派工
     * @param recordVo  故障上报表ID
//     * @param userName 派工人员
     * @return
     * @throws BusinessException
     */
    Result addRepairMoAndTask(RecordVo recordVo) throws BusinessException;


   //修改工单信息
   Result updateByMo(RepairMo repairMo) throws BusinessException;


    /**
     * 拒绝或者领取任务
     * @param repairMo
     * @throws BusinessException
     */
    public void acceptOrRefuseMo(RepairMo repairMo) throws BusinessException;



    /**
     * 提交任务
     * @param repairMo
     * @throws BusinessException
     */
    public void updateSub(RepairMo repairMo) throws BusinessException;


    /**
     * 关闭任务
     * @param repairMo
     * @throws BusinessException
     */
    public void updateClose(RepairMo repairMo) throws BusinessException;


    /**
     * 任务完成
     * @param recordVo
     * @throws BusinessException
     */
    public void doRepairMo(RecordVo recordVo) throws BusinessException;

    /**
     * 任务完成审核结束
     * @param repairMo
     * @throws BusinessException
     */
    public void finishRepairMo(RepairMo repairMo) throws BusinessException;

 /**
  * 多表联合查询  查询工单任务
  * @param page 分页
  * @param driverVo 参数
  * @return
  */
 Page<RepairMo> queryRepairMoParam(Page<RepairMo> page, RepairMo driverVo);

 /**
  * 多表联合查询  查询待分派的隐患任务
  * @param page 分页
  * @param driverVo 参数
  * @return
  */
 Page<RepairMo> queryByWaitForAssign(Page<RepairMo> page, RepairMo driverVo);


 /**
  * 多表联合查询 查询责任人信息
  * @param orgCode 登录人的行政区域编码
  * @param jurisdictions 当前隐患的区域编码
  * @return
  */
 public List<SystemUserSysDepartModel> queryPersonneList(String orgCode,String jurisdictions);




 //手机端报表
 public Map<String,Object> queryCountList(RepairMo repairMo);



 public Map<String,Object> queryCarList(RepairMo repairMo);

 public Map<String,Object> queryCarCountList(RepairMo repairMo);

 public Map<String,Object> queryMoCount(RepairMo repairMo);


 public Map<String,Object> queryQxCOUNT(RepairMo repairMo);

 public Map<String,Object> queryCarOrMoList(RepairMo repairMo);


 public Map<String,Object> queryDeductPh(RepairMo repairMo);

 //大屏报表
 public Map<String,Object> queryRank(RepairMo repairMo);


 public Map<String,Object> queryAdministerWc(RepairMo repairMo);

 public Map<String,Object> queryInterestCar(RepairMo repairMo);


 public Map<String,Object> queryClassify(RepairMo repairMo);


 public Map<String,Object> queryDiagram(RepairMo repairMo);


 public Map<String,Object> queryQuantity(RepairMo repairMo);
}
