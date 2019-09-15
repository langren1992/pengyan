package com.anserx.pengyan.common.exception.enums;

import com.anserx.pengyan.common.exception.assertion.ArgumentExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements ArgumentExceptionAssert {

    VALID_ERROR(6000, "参数校验异常"),

    ;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
