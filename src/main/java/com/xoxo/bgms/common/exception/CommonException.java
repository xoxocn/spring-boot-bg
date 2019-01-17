package com.xoxo.bgms.common.exception;


import lombok.Getter;

/**
 * @Package com.xoxo.bgms.common.enums
 * @Description 所有自定义异常必须继承此类
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-01-08 18:21
 */
@Getter
public class CommonException extends RuntimeException{
    protected  String code;

    public CommonException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
