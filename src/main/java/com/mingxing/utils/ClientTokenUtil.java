package com.mingxing.utils;

import com.mingxing.config.PortalTokenOauth2Config;
import com.mingxing.domain.TResult;
import com.mingxing.domain.TTableResult;
import com.mingxing.domain.oauth2.ClientTokenModel;
import com.mingxing.domain.oauth2.PortalDept;
import com.mingxing.domain.oauth2.PortalUser;
import com.mingxing.domain.oauth2.PortalUserResetPwd;
import com.mingxing.enums.PortalDeptType;
import com.mingxing.service.RestHandle;

import javax.annotation.Resource;

/**
 * 发送请求
 *
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
public class ClientTokenUtil {
    // 将成员变量声明为静态变量
    private static PortalTokenOauth2Config portalTokenOauth2Config;
    private static RestHandle restHandle;

    @Resource
    public void setPortalTokenOauth2Config(PortalTokenOauth2Config portalTokenOauth2Config) {
        ClientTokenUtil.portalTokenOauth2Config = portalTokenOauth2Config;
        restHandle = new RestHandle();
    }


    public static ClientTokenModel clientToken() {
        return restHandle.clientToken(portalTokenOauth2Config.getServerHost(),
                portalTokenOauth2Config.getClientId(),
                portalTokenOauth2Config.getClientSecret()).checkAndGetData(ClientTokenModel.class);
    }

    public static TTableResult<PortalDept> deptInfo(String clientToken, PortalDeptType portalDeptType) {
        return restHandle.deptInfo(portalTokenOauth2Config.getServerHost(), clientToken, portalDeptType.name());
    }

    public static TTableResult<PortalUser> userList(String clientToken, Long deptId, String nickName, String userName, String phonenumber, Integer pageNum, Integer pageSize) {
        return restHandle.userList(portalTokenOauth2Config.getServerHost(), clientToken, deptId, nickName, userName, phonenumber, pageNum, pageSize);
    }

    public static TResult resetPassword(String clientToken, Long userId, String password) {
        return restHandle.resetPassword(portalTokenOauth2Config.getServerHost(), clientToken, new PortalUserResetPwd(userId, password));
    }
}
