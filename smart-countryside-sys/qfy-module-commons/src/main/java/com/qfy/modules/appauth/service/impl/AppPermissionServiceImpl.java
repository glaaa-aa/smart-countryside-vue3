package com.qfy.modules.appauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.appauth.entity.AppPermission;
import com.qfy.modules.appauth.mapper.AppPermissionMapper;
import com.qfy.modules.appauth.model.AppPermissionTree;
import com.qfy.modules.appauth.service.IAppPermissionService;
import org.jeecg.common.constant.CommonConstant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 移动端按钮权限控制
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Service
public class AppPermissionServiceImpl extends ServiceImpl<AppPermissionMapper, AppPermission> implements IAppPermissionService {

    /**
     * queryTreeList 对应 queryTreeList 查询所有的部门数据,以树结构形式响应给前端
     */
    @Override
    public List<AppPermissionTree> queryTreeList() {
        LambdaQueryWrapper<AppPermission> query = new LambdaQueryWrapper<AppPermission>();
        query.eq(AppPermission::getDelFlag, CommonConstant.DEL_FLAG_0.toString());
        query.orderByAsc(AppPermission::getSortNo);
        List<AppPermission> list = this.list(query);
        if (list == null ){
            return null;
        }

        //该方法是s将SysDepart类型的list集合转换成ProjGroupTree类型的集合
        List<AppPermissionTree> _tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AppPermission projGroup = list.get(i);
            _tempList.add(new AppPermissionTree(projGroup));
        }

        // 调用streamDataToTreeList方法生成树状数据
        List<AppPermissionTree> treeList = streamDataToTreeList(_tempList);
        setEmptyChildrenAsNull(treeList);
        return treeList;
    }



    /**
     * stream方法转换成树形结构
     * @param treeList
     * @return
     */
    private List<AppPermissionTree> streamDataToTreeList(List<AppPermissionTree> treeList) {
        List<AppPermissionTree> list = treeList.stream()
                // 筛选出父节点
                .filter(t -> t.getParentId().equals("0"))
                // 设置父节点的子节点列表
                .map(item -> {item.setChildren(streamGetChildren(item, treeList)); return item;})
                .collect(Collectors.toList());
        return list;
    }

    /**
     * stream 方式递归查找子节点列表
     * @return
     */
    private List<AppPermissionTree> streamGetChildren(AppPermissionTree tree, List<AppPermissionTree> treeList) {
        List<AppPermissionTree> list = treeList.stream()
                .filter(t -> t.getParentId().equals(tree.getId()))
                .map(item -> {item.setChildren(streamGetChildren(item, treeList)); return item;})
                .collect(Collectors.toList());
        return list;
    }

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private void setEmptyChildrenAsNull(List<AppPermissionTree> treeList) {
        for (int i = 0; i < treeList.size(); i++) {
            AppPermissionTree model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setLeaf(false);
            }
        }
    }

}
