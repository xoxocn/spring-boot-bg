package com.xoxo.bgms.common.VO;

import lombok.Data;

/**
 * @Package com.xoxo.product.VO
 * @Description 返回结果集
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-03 14:31
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;


}
