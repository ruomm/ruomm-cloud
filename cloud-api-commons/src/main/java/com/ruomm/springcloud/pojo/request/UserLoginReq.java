package com.ruomm.springcloud.pojo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author 牛牛-wanruome@126.com
 * @version 1.0
 * @copyright www.ruomm.com
 * @create 2024/2/25 9:46
 */
//网络通信 一定要实现序列化
//使用lombok  没有配置的童鞋要去百度查一下  jar我们导入了  需要在idea装一个插件就可以了
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserLoginReq implements Serializable {
    @NotEmpty(message = "用户名必须填写，且必须为4-16位")
    @Length(min = 4,max = 16,message = "用户名必须填写，且必须为4-16位")
    private String userName;
    @NotEmpty(message = "密码必须填写，且必须为8-32位")
    @Length(min = 8,max = 32,message = "密码必须填写，且必须为8-32位")
    private String password;
//    @NotEmpty(message = "验证码必须填写")
    @Length(min = 4,max = 8,message = "验证码格式不正确")
    private String verifyCode;
}
