package com.addlove.service.goods.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lw
 */
@Component
@ConfigurationProperties(prefix = "user")
public class UserInit {
    /**
     * 保存session
     */
    private static Map<String, Long> session = new HashMap<>();

    /**
     * sessionKey
     */
    public final static String SESSIONKEY = "user_session_key";

    /**
     * @return session
     */
    public static Map<String, Long> getSession() {
        return session;
    }
}
