package com.ruomm.springcloud.authserver.jwt.utils;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruomm.javax.corex.StringUtils;
import com.ruomm.springcloud.authserver.config.ConfigProperties;
import com.ruomm.springcloud.authserver.jwt.claim.UserClaim;
import com.ruomm.springcloud.authserver.utils.AppUtils;
import com.ruomm.springcloud.exception.WebAppException;

import java.util.Date;

/**
 * @author 牛牛-wanruome@126.com
 * @version 1.0
 * @copyright www.ruomm.com
 * @create 2024/3/3 9:05
 */
public class JWTUtils {
    /**
     * 获取token
     * @param u user
     * @return token
     */
    public static String getToken(UserClaim u,String secret, ConfigProperties configProperties) {
        Date date = new Date();
        long expiresAt = 0;
        if (configProperties.getUserTokenExpireMins()<=0 || configProperties.getUserTokenExpireMins()>=60*24*365){
            expiresAt = date.getTime()  + 720*60l*1000l;
        } else {
            expiresAt = date.getTime()+configProperties.getUserTokenExpireMins()*60l*1000l;
        }
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userClaim", JSON.toJSONString(u));
        return builder.withExpiresAt(new Date(expiresAt))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token,String secret) throws WebAppException {
        if(StringUtils.isEmpty(token)){
            throw new WebAppException(AppUtils.ERROR_TOKEN,"token不能为空");
        }
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secret)).build();
        return build.verify(token);
    }

}
