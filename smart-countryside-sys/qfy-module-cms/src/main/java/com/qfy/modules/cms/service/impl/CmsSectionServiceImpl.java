package com.qfy.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.cms.entity.CmsSection;
import com.qfy.modules.cms.mapper.CmsSectionMapper;
import com.qfy.modules.cms.service.ICmsSectionService;
import com.qfy.modules.commons.model.TreeSelectModel;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 栏目管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
@Service
public class CmsSectionServiceImpl extends ServiceImpl<CmsSectionMapper, CmsSection> implements ICmsSectionService {

	@Override
	public void addCmsSection(CmsSection cmsSection) {
	   //新增时设置hasChild为0
	    cmsSection.setHasChild(ICmsSectionService.NOCHILD);
		if(oConvertUtils.isEmpty(cmsSection.getPid())){
			cmsSection.setPid(ICmsSectionService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			CmsSection parent = baseMapper.selectById(cmsSection.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(cmsSection);
	}

	@Override
	public void updateCmsSection(CmsSection cmsSection) {
		CmsSection entity = this.getById(cmsSection.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = cmsSection.getPid();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				cmsSection.setPid(ICmsSectionService.ROOT_PID_VALUE);
			}
			if(!ICmsSectionService.ROOT_PID_VALUE.equals(cmsSection.getPid())) {
				baseMapper.updateTreeNodeStatus(cmsSection.getPid(), ICmsSectionService.HASCHILD);
			}
		}
		baseMapper.updateById(cmsSection);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCmsSection(String id) throws JeecgBootException {
		//查询选中节点下所有子节点一并删除
        id = this.queryTreeChildIds(id);
        if(id.indexOf(",")>0) {
            StringBuffer sb = new StringBuffer();
            String[] idArr = id.split(",");
            for (String idVal : idArr) {
                if(idVal != null){
                    CmsSection cmsSection = this.getById(idVal);
                    String pidVal = cmsSection.getPid();
                    //查询此节点上一级是否还有其他子节点
                    List<CmsSection> dataList = baseMapper.selectList(new QueryWrapper<CmsSection>().eq("pid", pidVal).notIn("id",Arrays.asList(idArr)));
                    if((dataList == null || dataList.size()==0) && !Arrays.asList(idArr).contains(pidVal)
                            && !sb.toString().contains(pidVal)){
                        //如果当前节点原本有子节点 现在木有了，更新状态
                        sb.append(pidVal).append(",");
                    }
                }
            }
            //批量删除节点
            baseMapper.deleteBatchIds(Arrays.asList(idArr));
            //修改已无子节点的标识
            String[] pidArr = sb.toString().split(",");
            for(String pid : pidArr){
                this.updateOldParentNode(pid);
            }
        }else{
            CmsSection cmsSection = this.getById(id);
            if(cmsSection==null) {
                throw new JeecgBootException("未找到对应实体");
            }
            updateOldParentNode(cmsSection.getPid());
            baseMapper.deleteById(id);
        }
	}

	@Override
    public List<CmsSection> queryTreeListNoPage(QueryWrapper<CmsSection> queryWrapper) {
        List<CmsSection> dataList = baseMapper.selectList(queryWrapper);
        List<CmsSection> mapList = new ArrayList<>();
        for(CmsSection data : dataList){
            String pidVal = data.getPid();
            //递归查询子节点的根节点
            if(pidVal != null && !"0".equals(pidVal)){
                CmsSection rootVal = this.getTreeRoot(pidVal);
                if(rootVal != null && !mapList.contains(rootVal)){
                    mapList.add(rootVal);
                }
            }else{
                if(!mapList.contains(data)){
                    mapList.add(data);
                }
            }
        }
        return mapList;
    }

    @Override
    public String queryIdByCode(String code) {
        return baseMapper.queryIdByCode(code);
    }

    /**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!ICmsSectionService.ROOT_PID_VALUE.equals(pid)) {
			Long count = baseMapper.selectCount(new QueryWrapper<CmsSection>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, ICmsSectionService.NOCHILD);
			}
		}
	}

	/**
     * 递归查询节点的根节点
     * @param pidVal
     * @return
     */
    private CmsSection getTreeRoot(String pidVal){
        CmsSection data =  baseMapper.selectById(pidVal);
        if(data != null && !"0".equals(data.getPid())){
            return this.getTreeRoot(data.getPid());
        }else{
            return data;
        }
    }

    /**
     * 根据id查询所有子节点id
     * @param ids
     * @return
     */
    private String queryTreeChildIds(String ids) {
        //获取id数组
        String[] idArr = ids.split(",");
        StringBuffer sb = new StringBuffer();
        for (String pidVal : idArr) {
            if(pidVal != null){
                if(!sb.toString().contains(pidVal)){
                    if(sb.toString().length() > 0){
                        sb.append(",");
                    }
                    sb.append(pidVal);
                    this.getTreeChildIds(pidVal,sb);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 递归查询所有子节点
     * @param pidVal
     * @param sb
     * @return
     */
    private StringBuffer getTreeChildIds(String pidVal,StringBuffer sb){
        List<CmsSection> dataList = baseMapper.selectList(new QueryWrapper<CmsSection>().eq("pid", pidVal));
        if(dataList != null && dataList.size()>0){
            for(CmsSection tree : dataList) {
                if(!sb.toString().contains(tree.getId())){
                    sb.append(",").append(tree.getId());
                }
                this.getTreeChildIds(tree.getId(),sb);
            }
        }
        return sb;
    }


    @Override
    public List<TreeSelectModel> queryListByPid(String pid, Map<String, String> condition) {
        if(oConvertUtils.isEmpty(pid)) {
            pid = ROOT_PID_VALUE;
        }
        return baseMapper.queryListByPid(pid,condition);
    }


}
