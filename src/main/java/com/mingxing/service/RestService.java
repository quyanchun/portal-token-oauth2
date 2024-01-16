package com.mingxing.service;

import com.dtflys.forest.Forest;
import com.dtflys.forest.annotation.JSONBody;
import com.mingxing.domain.TResult;
import com.mingxing.domain.TTableResult;
import com.mingxing.domain.oauth2.*;

import java.util.Map;

/**
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
public class RestService {

    private static final String tokenUrl = "{url}/api/oauth2/token";

    /**
     * 获取accessToken
     *
     * @param url
     * @return
     */
    public TResult<AccessTokenModel> token(String url, String clientId, String clientSecret, Map<String, Object> map) {
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        return  Forest.post(setUrl(tokenUrl, url)).addBody(map).execute(TResult.class);
    }

    private static final String logoutUrl = "{url}/api/oauth2/doLogout" +
            "?client_id={clientId}" +
            "&client_secret={clientSecret}" +
            "&access_token={accessToken}";

    /**
     * 登出
     *
     * @param url
     * @return
     */
    public TResult logout(String url, String clientId, String clientSecret, String accessToken) {
        return Forest.get(setUrl(logoutUrl, url, clientId, clientSecret, accessToken)).execute(TResult.class);
    }

    private static final String refreshUrl = "{url}/api/oauth2/refresh" +
            "?grant_type=refresh_token" +
            "&client_id={clientId}" +
            "&client_secret={clientSecret}" +
            "&refresh_token={refreshToken}";

    /**
     * 获取accessToken
     *
     * @param url
     * @return
     */
    public TResult<AccessTokenModel> refresh(String url, String clientId, String clientSecret, String refreshToken) {
        return (TResult<AccessTokenModel>) Forest.get(setUrl(refreshUrl, url, clientId, clientSecret, refreshToken)).execute(TResult.class);
    }

    private static final String userinfoUrl = "{url}/api/oauth2/userinfo?access_token={accessToken}";

    /**
     * 获取用户信息
     *
     * @param accessToken
     * @return
     */
    public TResult<PortalUser> userinfo(String url, String accessToken) {
        return (TResult<PortalUser>) Forest.get(setUrl(userinfoUrl, url, accessToken)).execute(TResult.class);
    }

    private static final String clientTokenUrl = "{url}/api/oauth2/client_token?grant_type=client_credentials&client_id={clientId}&client_secret={clientSecret}";

    public TResult<ClientTokenModel> clientToken(String url, String clientId, String clientSecret) {
        return (TResult<ClientTokenModel>) Forest.get(setUrl(clientTokenUrl, url, clientId, clientSecret)).execute(TResult.class);
    }


    private static final String deptInfoUrl = "{url}/api/oauth2/deptInfo?client_token={clientToken}&type={type}";

    public TTableResult<PortalDept> deptInfo(String url, String clientToken, String type) {
        return (TTableResult<PortalDept>) Forest.get(setUrl(deptInfoUrl, url, clientToken, type)).execute(TTableResult.class);
    }


    private static final String userListUrl = "{url}/api/oauth2/userList?client_token={clientToken}" +
            "&deptId={deptId}" +
            "&nickName={nickName}" +
            "&userName={userName}" +
            "&phonenumber={phonenumber}" +
            "&pageNum={pageNum}" +
            "&pageSize={pageSize}";

    public TTableResult<PortalUser> userList(String url, String clientToken
            , Long deptId
            , String nickName
            , String userName
            , String phonenumber
            , Integer pageNum
            , Integer pageSize) {
        return (TTableResult<PortalUser>) Forest.get(setUrl(userListUrl, url, clientToken, deptId, nickName, userName, phonenumber, pageNum, pageSize)).execute(TTableResult.class);
    }


    private static final String resetPasswordUrl = "{url}/api/oauth2/reset-password?client_token={clientToken}";

    public TResult resetPassword(String url, String clientToken, @JSONBody PortalUserResetPwd portalUserResetPas) {
        return (TResult<PortalDept>) Forest.post(setUrl(resetPasswordUrl, url, clientToken)).contentTypeJson().addBody(portalUserResetPas).execute(TResult.class);
    }


    private static String setUrl(String input, Object... args) {
        for (Object replacement : args) {
            if (null == replacement)
                replacement = "";
            input = input.replace(input.substring(input.indexOf("{"), input.indexOf("}") + 1), String.valueOf(replacement));
        }
        return input;
    }
}
