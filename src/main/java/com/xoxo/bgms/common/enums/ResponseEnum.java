/**
 * @Title: ResponseEnum.java
 * @Package: com.zshy.infocoin.common.domain
 * @author LZG, liuzhongguochn@gmail.com
 * Copyright (c) 2018 北京中数合一科技有限公司
 */
package com.xoxo.bgms.common.enums;

/**
 * @Description:    基本枚举类
 * @Author:         xiehua@zhongshuheyi.com
 * @CreateDate:     2018/11/8 14:39
 */
public enum ResponseEnum {

    success("200", "OK"),
    server_error("500", "服务器错误"),
    query_not_exist("IC0001", "查询不到此信息"),
    sign_fail("IC0002", "报文验签不通过"),
    param_blank("IC0003", "请求参数不能为空"),
    login_fail("IC0004", "请尝试重新登录！");

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}