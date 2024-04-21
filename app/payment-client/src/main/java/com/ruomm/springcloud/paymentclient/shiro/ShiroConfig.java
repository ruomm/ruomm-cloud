package com.ruomm.springcloud.paymentclient.shiro;

import com.ruomm.springcloud.paymentclient.shiro.filter.UserAuthenticationFilter;
import com.ruomm.springcloud.paymentclient.shiro.realm.UserAuthorizingRealm;
import jakarta.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 牛牛-wanruome@126.com
 * @version 1.0
 * @copyright www.ruomm.com
 * @create 2024/4/20 18:24
 */
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfig {

    private final static String AUTHC_STR = "authc";
    private final static String ANON_STR = "anon";

//    @Getter
//    @Setter
//    private List<String> anon_uri;

    /**
     * 验证授权、认证
     *
     * @return shiroRealm 授权认证
     */
    @Bean
    public UserAuthorizingRealm shiroRealm(){
        return new UserAuthorizingRealm();
    }

    /**
     * session manager
     *
     * @param shiroRealm  授权认证
     * @return  安全管理
     */
    @Bean
    @ConditionalOnClass(UserAuthorizingRealm.class)
    public SecurityManager securityManager(UserAuthorizingRealm shiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    @Bean
    public UserAuthenticationFilter userAuthenticationFilter(){
        UserAuthenticationFilter userAuthenticationFilter =  new UserAuthenticationFilter();
        return userAuthenticationFilter;
    }

    @Bean
    public FilterRegistrationBean<UserAuthenticationFilter> tokenFilterFilterRegistrationBean(UserAuthenticationFilter customAuthenticationFilter){
        FilterRegistrationBean<UserAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customAuthenticationFilter);
        // 不让该filter出现在全局过滤器链中
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager session 管理
     * @return shiro 过滤工厂
     */
    @Bean
    @ConditionalOnClass(value = {UserAuthenticationFilter.class,SecurityManager.class})
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, UserAuthenticationFilter customAuthenticationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义过滤器
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
//        filterMap.put("restful_return", customAuthenticationFilter);
//        filterMap.put("customAuthenticationFilter", customAuthenticationFilter);
        filterMap.put(AUTHC_STR, customAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        //URI过滤
        Map<String,String> map = new LinkedHashMap<>();


        // 不进行token校验的接口
//        anon_uri.forEach(item -> map.put(item,ANON_STR));
        // 不进行token校验的接口
        map.put("/auth/users/login",ANON_STR);
        // 进行token校验的接口
        map.put("/auth/users/create",AUTHC_STR);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
}
