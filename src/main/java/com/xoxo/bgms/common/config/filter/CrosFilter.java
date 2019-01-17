//package com.xoxo.prjname.common.config.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Package com.learn.ssmmodules.config.bean.filter
// * @Description 跨域过滤器
// * @Author xiehua@zhongshuheyi.com
// * @Date 2018-11-09 16:51
// */
//public class CrosFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
//        httpServletResponse.addHeader("Access-Control-Allow-Methods","*");
//        httpServletResponse.addHeader("Access-Control-Allow-Headers","Content-Type");
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
