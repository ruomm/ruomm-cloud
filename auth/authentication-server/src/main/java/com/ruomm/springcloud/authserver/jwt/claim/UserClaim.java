package com.ruomm.springcloud.authserver.jwt.claim;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 牛牛-wanruome@126.com
 * @version 1.0
 * @copyright www.ruomm.com
 * @create 2024/3/3 9:03
 */
@Getter
@Setter
@ToString
public class UserClaim {
    private String userId;
}
