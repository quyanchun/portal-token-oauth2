package com.mingxing.utils;

import com.mingxing.config.PortalTokenOauth2Config;
import com.mingxing.domain.TResult;
import com.mingxing.domain.oauth2.AccessTokenModel;
import com.mingxing.domain.oauth2.PortalUser;
import com.mingxing.service.RestService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送请求
 *
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
@Component
public class Oauth2TokenUtil {
    private static RestService restService= new RestService();

    public static AccessTokenModel tokenByCode(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "authorization_code");
        map.put("code", code);
        return restService.token(PortalTokenOauth2Config.getServerHost(),
                PortalTokenOauth2Config.getClientId(),
                PortalTokenOauth2Config.getClientSecret(),
                map).checkAndGetData(AccessTokenModel.class);
    }
    public static AccessTokenModel tokenByPassword(String username,String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("username", username);
        map.put("password", password);
        return restService.token(PortalTokenOauth2Config.getServerHost(),
                PortalTokenOauth2Config.getClientId(),
                PortalTokenOauth2Config.getClientSecret(),
                map).checkAndGetData(AccessTokenModel.class);
    }
    public static TResult logout(String accessToken) {
        return restService.logout(PortalTokenOauth2Config.getServerHost(),
                PortalTokenOauth2Config.getClientId(),
                PortalTokenOauth2Config.getClientSecret(),
                accessToken);
    }

    public static AccessTokenModel refresh(String refreshToken) {
        return restService.refresh(PortalTokenOauth2Config.getServerHost(),
                PortalTokenOauth2Config.getClientId(),
                PortalTokenOauth2Config.getClientSecret()
                , refreshToken).checkAndGetData(AccessTokenModel.class);
    }

    public static PortalUser userinfo(String accessToken) {
        return restService.userinfo(PortalTokenOauth2Config.getServerHost(), accessToken).checkAndGetData(PortalUser.class);
    }
}
