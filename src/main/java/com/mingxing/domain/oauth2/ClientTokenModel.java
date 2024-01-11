package com.mingxing.domain.oauth2;

import java.io.Serializable;

public class ClientTokenModel implements Serializable {
    private static final long serialVersionUID = -6541180061782004705L;
    public String client_token;
    public String client_id;
    public String scope;
    public long expires_in;

    public ClientTokenModel() {
    }

    public String getClient_token() {
        return client_token;
    }

    public void setClient_token(String client_token) {
        this.client_token = client_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
