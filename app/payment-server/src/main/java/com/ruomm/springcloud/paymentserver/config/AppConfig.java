package com.ruomm.springcloud.paymentserver.config;

import com.ruomm.javax.basex.TokenHelper;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/2/1 0:14
 */
public class AppConfig {
    public final static TokenHelper TOKEN_HELPER = new TokenHelper(TokenHelper.TOKEN_NUMBER + TokenHelper.TOKEN_LETTER_LOWER_CASE, 16);
    public final static String JWT_SECRET = "cqCjzDC0Pvny5EtzN7eoKuGDJvtx";
}
