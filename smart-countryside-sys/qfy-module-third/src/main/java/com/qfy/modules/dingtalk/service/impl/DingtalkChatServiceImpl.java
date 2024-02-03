package com.qfy.modules.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkexclusive_1_0.models.SearchOrgInnerGroupInfoHeaders;
import com.aliyun.dingtalkexclusive_1_0.models.SearchOrgInnerGroupInfoRequest;
import com.aliyun.dingtalkexclusive_1_0.models.SearchOrgInnerGroupInfoResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.jeecg.dingtalk.api.base.JdtBaseAPI;
import com.jeecg.dingtalk.api.core.response.Response;
import com.jeecg.dingtalk.api.core.util.HttpUtil;
import com.jeecg.dingtalk.api.core.vo.AccessToken;
import com.qfy.modules.commons.entity.ChatGroupCreateDO;
import com.qfy.modules.config.ThirdAppEnum;
import com.qfy.modules.config.ThirdConfigFactory;
import com.qfy.modules.config.entity.ThirdConfig;
import com.qfy.modules.dingtalk.service.DingtalkChatService;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.system.service.ISysDepartService;
import org.jeecg.modules.system.service.ISysThirdAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


/**
 * 第三方App对接：钉钉实现类
 * @author: jeecg-boot
 */
@Slf4j
@Service
public class DingtalkChatServiceImpl implements DingtalkChatService {

    @Autowired
    private ISysDepartService sysDepartService;
    @Autowired
    private ISysThirdAccountService thirdAccountService;

    /**
     * 第三方APP类型，当前固定为 dingtalk
     */
    public final String THIRD_TYPE = ThirdAppEnum.DINGTALK.getCode();

    public String getAccessToken(Integer tenantId) {
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);

        String appKey = thirdConfig.getClientId();
        String appSecret = thirdConfig.getClientSecret();
        AccessToken accessToken = JdtBaseAPI.getAccessToken(appKey, appSecret);
        if (accessToken != null) {
            return accessToken.getAccessToken();
        }
        log.warn("获取AccessToken失败");
        return null;
    }

    @Override
    public Response<Map> createChatGroup(ChatGroupCreateDO chatGroupCreateDO){
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(chatGroupCreateDO.getTenantId(),THIRD_TYPE);
        if (thirdConfig == null) {
            return  null;
        }

        chatGroupCreateDO.setTemplate_id(thirdConfig.getChatTemplateId());
        String accessToken = this.getAccessToken(chatGroupCreateDO.getTenantId());

        String api = "https://oapi.dingtalk.com/topapi/im/chat/scenegroup/create?access_token=%s";
        String url = String.format(api, accessToken);
        String body = JSON.toJSONString(chatGroupCreateDO);


//        body.put("title",chatGroupCreateDO.getChatName());
//        body.put("template_id","f9ed3f3d-37a8-417e-9e5c-1af0bc7def27");
//        body.put("owner_user_id",thirdAccount.getThirdUserId());
////        body.put("UserIds","072*****,013*****");
//        body.put("memberUserIds",member);
//        body.put("uuid",chatGroupCreateDO.getProjectId());
        Response<Map> result = HttpUtil.post(url,body);
        log.info("群聊==》"+ JSON.toJSONString(result));
        return result;
    }

    public static com.aliyun.dingtalkexclusive_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkexclusive_1_0.Client(config);
    }

    @Override
    public void getDindtalkChat() throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
        String accessToken = this.getAccessToken(2);
        com.aliyun.dingtalkexclusive_1_0.Client client = createClient();
        SearchOrgInnerGroupInfoHeaders searchOrgInnerGroupInfoHeaders = new SearchOrgInnerGroupInfoHeaders();
        searchOrgInnerGroupInfoHeaders.xAcsDingtalkAccessToken = accessToken;
        SearchOrgInnerGroupInfoRequest searchOrgInnerGroupInfoRequest = new SearchOrgInnerGroupInfoRequest()
//                .setGroupMembersCountEnd(100)
//                .setSyncToDingpan(1)
//                .setGroupOwner("user123")
//                .setCreateTimeEnd(1618546742L)
                .setPageSize(20)
//                .setCreateTimeStart(1618546755L)
                .setUuid(uuid)
//                .setGroupMembersCountStart(1)
//                .setLastActiveTimeEnd(1618546999L)
                .setOperatorUserId("01180314591249870")
//                .setGroupName("群1")
                .setPageStart(1)
                .setLastActiveTimeStart(1618546999L);
        try {
            SearchOrgInnerGroupInfoResponseBody responseBody = client.searchOrgInnerGroupInfoWithOptions(searchOrgInnerGroupInfoRequest, searchOrgInnerGroupInfoHeaders, new RuntimeOptions()).getBody();
            log.info(JSON.toJSONString(responseBody));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
    }

    @Override
    public String sendChatMessage(String chatId, String msgText,Integer tenantId) {
        String accessToken = this.getAccessToken(tenantId);
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/send");
        OapiChatSendRequest req = new OapiChatSendRequest();
        req.setChatid(chatId);
        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
        text.setContent(msgText);
        msg.setText(text);
        msg.setMsgtype("text");
        req.setMsg(msg);

        try {
            OapiChatSendResponse response = client.execute(req, accessToken);
            if (response.isSuccess()) {
                String messageId = response.getMessageId();
                log.info("messageId: {}", messageId);
                return messageId;
            } else {
                log.warn("Call api return failed. raw resp: {}", response);
                log.warn("raw resp.code: {}, resp.msg: {}", response.getErrcode(), response.getMessage());
                return response.getMessage();
            }
        } catch (ApiException e) {
            log.error("Call api error.", e);
            throw new RuntimeException(e);
        }
    }


}
