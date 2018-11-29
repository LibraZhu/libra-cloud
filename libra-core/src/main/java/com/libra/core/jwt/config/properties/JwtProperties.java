package com.libra.core.jwt.config.properties;

/**
 * @author Libra
 * @date 2018/11/28
 * @description jwt相关配置
 */
public class JwtProperties {
    /**
     * jwt的秘钥
     */
    private String secret = "u47REBDLrxfd79w";

    /**
     * jwt过期时间(单位:秒)(默认:1天)
     */
    private Long expiration = 82800L;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
