package com.sc.ribbon.user.dto;

import lombok.Data;

/**
 * @author yuantongqin
 * description:
 * 2020/4/25
 */
@Data
public class CommonResult<T> {

    private String message;

    private Integer code;

    private T body;

    public CommonResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CommonResult() {
    }
}
