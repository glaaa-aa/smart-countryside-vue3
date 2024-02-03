package com.qfy.modules.files.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.files.entity.FilesManager;
import com.qfy.modules.files.model.FileManagerVo;
import com.qfy.modules.files.service.IFilesManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: 上传文件管理
* @Author: jeecg-boot
* @Date:   2022-03-01
* @Version: V1.0
*/
@Api(tags="上传文件管理")
@RestController
@RequestMapping("/files/sysfilesManager")
@Slf4j
public class SysFilesManagerController extends JeecgController<FilesManager, IFilesManagerService> {

    @Autowired
    private IFilesManagerService filesManagerService;

    /**
     * 分页列表查询
     * @param  fileManagerVo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "文件管理-分页列表查询")
    @ApiOperation(value="文件管理-分页列表查询", notes="文件管理-分页列表查询")
    @GetMapping(value = "/rootList")
    public Result<?> queryPageList(FileManagerVo fileManagerVo,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
                                   HttpServletRequest req) {
        if(fileManagerVo.getPid() == null){
            fileManagerVo.setPid("0");
        }

        Page<FileManagerVo> page = new Page<FileManagerVo>(pageNo, pageSize);
        IPage<FileManagerVo> pageList = filesManagerService.queryListBy(page,fileManagerVo);
        return Result.OK(pageList);
    }

}
