package org.jeecg.common.constant;

/**
 * @Description: 操作类型
 * @author: jeecg-boot
 * @date: 2022/3/31 10:05
 */
public enum UserTypeEnum {

    /**
     * 平台管理员
     */
    superAdmin(1, "平台管理员"),

    /**
     * 企业管理员
     */
    QyAdmin(3, "企业管理员"),

    PutUser(5,"普通注册用户");

    int type;
    String name;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 构造器
     *
     * @param type 类型
     * @param name 编码(请求方式)
     */
    UserTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


}
