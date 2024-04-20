package com.ruomm.springcloud.pojo.respose;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/1/31 22:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserCreateResp implements Serializable {
    private Long userId;
    private String userName;
    private String nickName;
    private String bindPhone;
}
