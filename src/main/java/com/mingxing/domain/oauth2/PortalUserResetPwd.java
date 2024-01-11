package com.mingxing.domain.oauth2;

public class PortalUserResetPwd {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 密码
     */
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PortalUserResetPwd(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}