package com.ruomm.springcloud.paymentserver.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruomm.javax.corex.StringUtils;
import com.ruomm.springcloud.paymentserver.config.AppConfig;
import com.ruomm.springcloud.paymentserver.jwt.claim.UserClaim;
import com.ruomm.springcloud.paymentserver.utils.AppUtils;
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
    public static String getToken(UserClaim u, int userTokenExpireSecond) {
        Date date = new Date();
        long expiresAt = 0;
        if (userTokenExpireSecond<=0 || userTokenExpireSecond>=1000*60*24*365){
            expiresAt = date.getTime()  + 720*60l*1000l;
        } else {
            expiresAt = date.getTime()+userTokenExpireSecond*1000l;
        }
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId",u.getUserId());
        builder.withClaim("userName",u.getUserName());
//        builder.withClaim("userClaim", JSON.toJSONString(u));
        return builder.withExpiresAt(new Date(expiresAt))
                .sign(Algorithm.HMAC256(AppConfig.JWT_SECRET));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws WebAppException {
        if(StringUtils.isEmpty(token)){
            throw new WebAppException(AppUtils.ERROR_TOKEN,"token不能为空");
        }
        JWTVerifier build = JWT.require(Algorithm.HMAC256(AppConfig.JWT_SECRET)).build();
        return build.verify(token);
    }

}
