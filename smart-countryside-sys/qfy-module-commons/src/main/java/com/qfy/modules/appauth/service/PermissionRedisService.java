package com.qfy.modules.appauth.service;/**
 * @title: PermissionRedis
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2023/4/18 19:19
 */

import com.qfy.modules.appauth.mapper.AppRolePermissionMapper;
import com.qfy.modules.appauth.model.AppRolePermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author     ：马强
 * @date       ：Created in 2023/4/18 19:19
 * @description：APP应用权限缓存
 * @modified By：
 * @version:
 */
@Service
@Slf4j
public class PermissionRedisService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionRedisService.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppRolePermissionMapper appRolePermissionMapper;


    /**
     * 系统初始化，将所有项目菜单加入到redis中
     * @param
     */
    public void initPermissionToRedis(){

        long end = System.currentTimeMillis();
        //查询说有任务
        List<Map<String,Object>> projectIds = appRolePermissionMapper.queryAllProjectBySys();
        if(projectIds == null || projectIds.size() <=0 ){
            return;
        }
        long start = System.currentTimeMillis();
        logger.info("开始初始化项目权限==>项目数量：" + projectIds.size());
        //查询所有项目
        for (Map<String, Object> item : projectIds) {
            Integer tenantId = Integer.parseInt(item.get("tenant_id").toString());
            String projectId = item.get("id").toString();
            Map<String, Set<String>> map =  allRolePermissionForRole(projectId,tenantId);
            if (map == null){
                continue;
            }
            //移除 -1权限菜单
            for (Map.Entry<String, Set<String>> entry : map.entrySet() ){
                Set<String> value = entry.getValue();
                if(value.contains("-1")){
                    redisUtil.del(projectId +"_" + entry.getKey());
                }else {
                    String str = String.join(",", value);
                    redisUtil.set(projectId +"_" + entry.getKey(), str);
                }
            }
        }


        logger.info("结束初始化项目权限==>用时"+(end-start)+"毫秒" );

    }

    /**
     * 添加项目，将此项目计入到redis
     * @param projectId
     */
    public void addPermissionToRedis(String projectId,Integer tenantId) {
        Map<String, Set<String>> map =  allRolePermissionForRole(projectId,tenantId);
        if (map != null) {
            //移除 -1权限菜单
            for (Map.Entry<String, Set<String>> entry : map.entrySet() ){
                Set<String> value = entry.getValue();
                if(value.contains("-1")){
                    redisUtil.del(projectId +"_" + entry.getKey());
                }else {
                    String str = String.join(",", value);
                    redisUtil.set(projectId +"_" + entry.getKey(), str);
                }
            }
        }
    }

    /**
     * 删除项目 将项目中redis数据清空
     * @param projectId
     */
    public void deletePermissionToRedis(String projectId,Integer tenantId) {
        Map<String, Set<String>> map =  allRolePermissionForRole(projectId,tenantId);
        //移除 -1权限菜单
        for (Map.Entry<String, Set<String>> entry : map.entrySet() ){
            redisUtil.del(projectId +"_" + entry.getKey());
        }
    }



    private Map<String, Set<String>> allRolePermissionForRole(String refData,Integer tenantId) {
        List<AppRolePermissionVO> _list = appRolePermissionMapper.selectAllRolePermission(refData,tenantId);
        if(_list == null || _list.size() <= 0 ){
            return null;
        }
        Map<String, Set<String>> _map = new HashMap<>();
        //key：roleCode value: perms集合
        for (AppRolePermissionVO vo : _list){
            Set<String> permsList = _map.get(vo.getRoleCode());
            if(permsList == null || permsList.size() <= 0){
                permsList = new HashSet<>();
            }
            if(vo.getPermissionId() == -1){
                permsList.add("-1");
            }else {
                permsList.add(vo.getPerms());
            }
            _map.put(vo.getRoleCode(),permsList);
        }

        return  _map;
    }


}
