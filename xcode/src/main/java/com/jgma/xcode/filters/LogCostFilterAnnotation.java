package com.jgma.xcode.filters;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。
 * 除了这个注解以外，我们还需在启动类中加另外一个注解：@ServletComponetScan，指定扫描的包。
 */
@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class LogCostFilterAnnotation implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LogFilter2 Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
