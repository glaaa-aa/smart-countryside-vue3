package com.qfy.modules.smartform.api.common;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class ValidTokenUtil {

    public static String checkReq(HttpServletRequest request){
        String token = request.getParameter("token");
//        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = "402882ff6a903f16016a904280d00001";
        }
        return token;
    }
}
