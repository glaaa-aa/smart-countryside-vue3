package com.qfy.modules.smartform.api.common;

public class JwtConstants {

	//在没有用于生成token的时候验证
	public static final String JWT_NOUSER_LOGIN_KEY = "noUser";
	/**
	 * 上线需要变更
	 */
	public static final String JWT_SECRET = "JYJ5Qv2WF4lA6jPl5GKuAG";

	public static final String AUTHORIZATION = "X-AUTH-TOKEN";
	public static final String X_AUTH_UUID = "X-AUTH-UUID";
	public static final String CURRENT_USER_NAME = "CURRENT_TOKEN_USER_NAME";
	public static final String CURRENT_TOKEN_CLAIMS = "CURRENT_TOKEN_CLAIMS";
	public static final long TOKEN_EXPIRES_HOUR = 2;

}
