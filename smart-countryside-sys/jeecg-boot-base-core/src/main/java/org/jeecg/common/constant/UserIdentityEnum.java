package org.jeecg.common.constant;

/**
 * @Description: 操作类型
 * @author: jeecg-boot
 * @date: 2022/3/31 10:05
 */
public enum UserIdentityEnum {

    /****************说明************************/
    /**
     *  0-10 范围都属于 admin
     *  11-20 属于 fenGuan
     *  21-40 属于 DepartHead
     *  41-60 属于 projectMember
     */
    admin(10, "领导==>可以查询所有数据"),
    fenGuan(20, "分管领导==>可以查看及管理分管部门的任务"),
    DepartHead(40,"部门领导==>可以查看及管理负责的部门的任务"),
    DepartManger(50,"部门业务管理员==>可以查看及管理本部门的任务"),
    puTongUser(60,"干警/普通员工==>可以查看及管理自己创建的，自己作为参与人或负责人的任务");

    int type;
    String alias;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 构造器
     * @param type 类型
     * @param alias 编码(请求方式)
     */
    UserIdentityEnum(int type, String alias) {
        this.type = type;
        this.alias = alias;
    }

}
