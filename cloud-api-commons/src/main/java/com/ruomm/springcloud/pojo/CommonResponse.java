package com.ruomm.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright wanruome@163.com
 * @create 2022/4/11 15:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonResponse<T>{
    private int code;
    private String message;
    private List<CommonFieldError> errors;
    private T data;
    public CommonResponse(int code) {
        this.code = code;
        this.message = message;
    }
    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public CommonResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
