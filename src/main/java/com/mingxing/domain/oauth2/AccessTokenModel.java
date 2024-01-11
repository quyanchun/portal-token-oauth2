package com.mingxing.domain.oauth2;

import java.io.Serializable;

public class AccessTokenModel implements Serializable {
    private static final long serialVersionUID = -6541180061782004705L;
    public String access_token;
    public String refresh_token;
    public long expires_in;
    public long refresh_expires_in;
    public String client_id;
    public Object loginId;
    public String openid;
    public String scope;

    public AccessTokenModel() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public long getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public void setRefresh_expires_in(long refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Object getLoginId() {
        return loginId;
    }

    public void setLoginId(Object loginId) {
        this.loginId = loginId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
