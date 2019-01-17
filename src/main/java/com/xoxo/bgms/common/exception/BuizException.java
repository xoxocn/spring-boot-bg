package com.xoxo.bgms.common.exception;

import com.xoxo.bgms.common.enums.ExceptionEnum;

/**
 * @Package com.learn.ssmmodules.common.exception
 * @Description 业务异常类
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-28 17:25
 */

public class BuizException extends CommonException{

    public BuizException(String code, String msg){
        super(code,msg);
}

    public BuizException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }

}
