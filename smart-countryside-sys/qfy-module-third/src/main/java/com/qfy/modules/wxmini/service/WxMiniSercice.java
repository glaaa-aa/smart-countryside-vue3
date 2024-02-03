package com.qfy.modules.wxmini.service;/**
 * @title: CreateWxaCode
 * @projectName qfy-jeecg-system
 * @description: TODO
 * @author 马强
 * @date 2022/6/13 16:15
 */

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import java.io.File;
import java.util.List;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author     ：马强
 * @date       ：Created in 2022/6/13 16:15
 * @description：
 * @modified By：
 * @version:
 */
public interface WxMiniSercice {

    /**
     * 创建小程序二维码
     * @param miniPage
     * @param filePath 文件路径
     * @return
     */
    public File createWxaCode(String miniPage,String filePath) throws WxErrorException;

    /**
     * 发送模板消息
     * @param uniformMessage
     * @return: boolean
     * @author: lanls
     * @date: 2022/8/16 11:11
     * @description:
     */
    boolean pushSubscribeMsg(WxMaSubscribeMessage subscribeMessage);

    boolean pushSubscribeMsg(List<String> toUsers, List<WxMaSubscribeMessage.MsgData> dataList, String templateId,
                             String pageUrl);
}
