package com.mingxing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author quyanchun
 * @date 2024/1/8
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "portal-token.oauth2", ignoreUnknownFields = true)
public class PortalTokenOauth2Config {
    private static String serverHost;
    private static String clientId;
    private static String clientSecret;

    public static String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public static String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
