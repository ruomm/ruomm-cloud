package com.ruomm.springcloud.paymentclient.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2022/4/15 10:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "ruomm")
public class ConfigProperties {
    @Value(value = "${spring.profiles.active}")
    private String profilesActive;
    @Value(value = "${server.port}")
    private int serverPort;
/*    @Value(value = "${server.servlet.context-path}")
    private String serverContextPath;*/
    /**
     * 日志输出路径
     */
    private String logPath;
    /**
     * #用户jwt有效时间（min）
     */
    private int userTokenExpireMins;
    /**
     * #用户jwt限制 0.不限制 1.单点登录 n.限制n点登录 -1.完全不校验
     */
    private String userTokenCtrl;
    /**
     * 全局授权标识，修改此值，所有用户的授权都会过期
     */
    private String authGlobalTag;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * http请求参数配置
     */
    private ConfigHttp httpConfig;
    /**
     * 短信验证码配置
     */
    private ConfigVerifyCode verifyCodeConfig;
}
