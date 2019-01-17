package com.xoxo.bgms.common.enums;

/**
 * @Package com.learn.ssmmodules.common.enums
 * @Description 异常枚举类
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-28 18:05
 */
public enum  ExceptionEnum {
    //业务异常
    buiz_ex001("EX001","自定义业务异常1"),
    buiz_ex002("EX002","自定义业务异常2");

    //db异常
    //db_ex001.....

    private String code;
    private String msg;

    ExceptionEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
