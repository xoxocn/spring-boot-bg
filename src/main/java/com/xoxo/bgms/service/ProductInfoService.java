package com.xoxo.bgms.service;

import com.xoxo.bgms.dto.ProductInfoDTO;

import java.util.List;

/**
 * @Package com.xoxo.bgms.service
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-01-08 15:45
 */
public interface ProductInfoService {

    List<ProductInfoDTO> findProductInfos();

}
