package com.mingxing.utils;

import com.mingxing.config.PortalTokenOauth2Config;
import com.mingxing.domain.TResult;
import com.mingxing.domain.oauth2.AccessTokenModel;
import com.mingxing.domain.oauth2.PortalUser;
import com.mingxing.service.RestHandle;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送请求
 *
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
public class Oauth2TokenUtil {
    private static PortalTokenOauth2Config portalTokenOauth2Config;
    private static RestHandle restHandle;


    @Resource
    public void setPortalTokenOauth2Config(PortalTokenOauth2Config portalTokenOauth2Config) {
        Oauth2TokenUtil.portalTokenOauth2Config = portalTokenOauth2Config;
        restHandle = new RestHandle();
    }


    public static AccessTokenModel tokenByToken(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "authorization_code");
        map.put("code", code);
        return restHandle.token(portalTokenOauth2Config.getServerHost(),
                portalTokenOauth2Config.getClientId(),
                portalTokenOauth2Config.getClientSecret(),
                map).checkAndGetData(AccessTokenModel.class);
    }
    public static AccessTokenModel tokenByPassword(String username,String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("username", username);
        map.put("password", password);
        return restHandle.token(portalTokenOauth2Config.getServerHost(),
                portalTokenOauth2Config.getClientId(),
                portalTokenOauth2Config.getClientSecret(),
                map).checkAndGetData(AccessTokenModel.class);
    }
    public static TResult logout(String accessToken) {
        return restHandle.logout(portalTokenOauth2Config.getServerHost(),
                portalTokenOauth2Config.getClientId(),
                portalTokenOauth2Config.getClientSecret(),
                accessToken);
    }

    public static AccessTokenModel refresh(String refreshToken) {
        return restHandle.refresh(portalTokenOauth2Config.getServerHost(),
                portalTokenOauth2Config.getClientId(),
                portalTokenOauth2Config.getClientSecret()
                , refreshToken).checkAndGetData(AccessTokenModel.class);
    }

    public static PortalUser userinfo(String accessToken) {
        return restHandle.userinfo(portalTokenOauth2Config.getServerHost(), accessToken).checkAndGetData(PortalUser.class);
    }
}
