package com.qfy.modules.wxmini.service.impl;/**
 * @title: CreateWxaCode
 * @projectName qfy-jeecg-system
 * @description: TODO
 * @author 马强
 * @date 2022/6/13 16:15
 */

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.qfy.modules.wxmini.service.WxMiniSercice;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author     ：马强
 * @date       ：Created in 2022/6/13 16:15
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class WxMiniSerciceImpl implements WxMiniSercice {
    public static final Logger logger = LoggerFactory.getLogger(WxMiniSerciceImpl.class);
    @Autowired
    private WxMaService wxMaService;
    @Value("${jeecg.path.upload}")
    private String uploadPath;
//    @Value("${wx.miniapp.file-path}")
//	private String filePath;

    @Override
    public File createWxaCode(String miniPage,String filePath) throws WxErrorException {
        File dir = new File(uploadPath + filePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = wxMaService.getQrcodeService().createWxaCode(miniPage,uploadPath + filePath);
        return file;
    }

    @Override
    public boolean pushSubscribeMsg(WxMaSubscribeMessage subscribeMessage) {

        try {
//            WxMaUniformMessage uniformMessage = new WxMaUniformMessage();
//            uniformMessage.setMiniProgram(null);
//            uniformMessage.setAppid("appid");
//            uniformMessage.setUrl("pages/index/announcement/announcement");

//            uniformMessage.setData(dataList);
//            uniformMessage.setEmphasisKeyword("");
//            uniformMessage.setFormId(formid);
//            uniformMessage.setPage("pages/index/announcement/announcement");
//            uniformMessage.setTemplateId("");
//            uniformMessage.setToUser(openid);

            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
            return true;
        } catch(Exception e) {
            logger.error("小程序消息发送失败：", e);
        }
        return false;
    }

    @Override
    public boolean pushSubscribeMsg(List<String> toUsers, List<WxMaSubscribeMessage.MsgData> dataList, String templateId, String pageUrl) {

        if (CollectionUtils.isEmpty(toUsers)){
            logger.error("没有可以推送服务通知的用户！");
            return false;
        }

        if (StringUtils.isBlank(templateId)){
            logger.error("没有找到相应的订阅消息模板！");
            return false;
        }

        if (CollectionUtils.isEmpty(dataList)){
            logger.error("订阅消息模板【{}】没有设置相应的参数！", templateId);
            return false;
        }

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setData(dataList); // 小程序模板数据
        subscribeMessage.setTemplateId(templateId);// 小程序模板ID
//        subscribeMessage.setToUser(""); // 消息接收人
        subscribeMessage.setPage(pageUrl);// 小程序页面路径
        try {
            for (String toUser : toUsers) {
                subscribeMessage.setToUser(toUser); // 消息接收人:openid
                wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);// 小程序的订阅消息方式
            }
            return true;
        } catch(WxErrorException e) {
            logger.error("推送微信服务通知异常：", e);
        }
        return false;
    }
}
