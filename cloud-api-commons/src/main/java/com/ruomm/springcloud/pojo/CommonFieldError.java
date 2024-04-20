package com.ruomm.springcloud.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/1/29 22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonFieldError implements Serializable {
    private String field;
    private String message;
}
