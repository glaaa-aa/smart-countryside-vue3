package com.qfy.modules.commons.service;

public interface ICommonService<T> {
    void save(T entity);
    void edit(T entity);

    /**
     * 公共处理方法
     * @param entity
     * @return
     */
    String handle(T entity);
}
