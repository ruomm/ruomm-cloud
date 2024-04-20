package com.ruomm.springcloud.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 牛牛-wanruome@126.com
 * @version 1.0
 * @copyright www.ruomm.com
 * @create 2024/4/20 21:21
 */
@Getter
@Setter
@ToString
public class Payment {
    /**
     * remark: 主键ID
     */
    private Long id;

    /**
     * remark: 名称
     */
    private String serial;
}
