package com.qfy.modules.repair.enums;


/**
 *  维修工单状态  用于andonRecord  andonErrorType
 * @author 马强
 * @date 2021/11/16
 */
public enum RepairMoStateEnum {


    /** 现场处理 1 */
    ONLINE(1,"现场处理"),
    /** 指派处理 2 */
    APPOINT(2,"指派处理"),
    /** 新增任务，待分派 0 */
    NEW(0,"新增"),  //新增待分派
    /** 已接收分派，待处理 10 */
    CHECK_PASS(10,"审核通过（待解决）"), //已分配待处理
    /** 已响应 11 */
    RECORD_REQUEST(11,"已响应"),
    /** 已派工(待完成) 19 */
    REPAIR_ADD(19,"已派工(待完成)"),
    /** 执行中 20 */
    REPAIR_DOING(20,"执行中"),
    /** 执行暂停 21 */
    REPAIR_STOP(21,"执行暂停"),
    /** 已处理,待审核(5) 30 */
    REPAIR_SUBMIT(30,"已处理,待审核"),//已提交待审核
    /** 已处理完成 40 */
    REPAIR_FINISH(40,"已处理完成"), //审核完成 已解决
    /** 已完成，待核销 41 */
    REPAIR_FINISH_WAITTING_CLOSE(41,"待核销"),
    /** 已核销 50 */
    REPAIR_CLIOSE(50,"已关闭");//已关闭



    private Integer value;
    private String alias;

    RepairMoStateEnum(Integer value, String alias){
        this.value = value;
        this.alias = alias;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static RepairMoStateEnum getByValue(Integer value){
        if(value == null) {
            return null;
        }
        for(RepairMoStateEnum val :values()){
            if (val.getValue().equals(value) ){
                return val;
            }
        }
        return  null;
    }

}
