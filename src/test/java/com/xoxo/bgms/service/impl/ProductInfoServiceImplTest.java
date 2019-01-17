package com.xoxo.bgms.service.impl;

import com.xoxo.bgms.BgmsApplicationTests;
import com.xoxo.bgms.dto.ProductInfoDTO;
import com.xoxo.bgms.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @Package com.xoxo.bgms.service.impl
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-01-09 16:58
 */
public class ProductInfoServiceImplTest extends BgmsApplicationTests {

    @Resource
    private ProductInfoService productInfoService;

    @Test
    public void findProductInfos() throws Exception {
        List<ProductInfoDTO> productInfos = productInfoService.findProductInfos();
        Assert.assertTrue(productInfos.size()==3);
        System.out.println(Arrays.toString(productInfos.toArray()));
    }

}