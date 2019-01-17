package com.xoxo.bgms.common.exception;

import com.xoxo.bgms.common.enums.ResponseEnum;

/**
 * @Package com.learn.ssmmodules.common.exception
 * @Description 业务异常类
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-28 17:25
 */

public class RespBaseException extends CommonException{

    public RespBaseException(String code, String msg){
        super(code,msg);
}

    public RespBaseException(ResponseEnum responseEnum){
        super(responseEnum.getCode(),responseEnum.getMsg());
    }

}
