package com.mingxing.utils;

import com.mingxing.config.PortalTokenOauth2Config;
import com.mingxing.domain.TResult;
import com.mingxing.domain.TTableResult;
import com.mingxing.domain.oauth2.ClientTokenModel;
import com.mingxing.domain.oauth2.PortalDept;
import com.mingxing.domain.oauth2.PortalUser;
import com.mingxing.domain.oauth2.PortalUserResetPwd;
import com.mingxing.enums.PortalDeptType;
import com.mingxing.service.RestService;
import org.springframework.stereotype.Component;

/**
 * 发送请求
 *
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
@Component
public class ClientTokenUtil {
    // 将成员变量声明为静态变量
    private static RestService restService= new RestService();


    public static ClientTokenModel clientToken() {
        return restService.clientToken(PortalTokenOauth2Config.getServerHost(),
                PortalTokenOauth2Config.getClientId(),
                PortalTokenOauth2Config.getClientSecret()).checkAndGetData(ClientTokenModel.class);
    }

    public static TTableResult<PortalDept> deptInfo(String clientToken, PortalDeptType portalDeptType) {
        return restService.deptInfo(PortalTokenOauth2Config.getServerHost(), clientToken, portalDeptType.name());
    }

    public static TTableResult<PortalUser> userList(String clientToken, Long deptId, String nickName, String userName, String phonenumber, Integer pageNum, Integer pageSize) {
        return restService.userList(PortalTokenOauth2Config.getServerHost(), clientToken, deptId, nickName, userName, phonenumber, pageNum, pageSize);
    }

    public static TResult resetPassword(String clientToken, Long userId, String password) {
        return restService.resetPassword(PortalTokenOauth2Config.getServerHost(), clientToken, new PortalUserResetPwd(userId, password));
    }
}
