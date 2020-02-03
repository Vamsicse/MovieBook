package com.galaxy.movie.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Vamsi Krishna Myalapalli
 * @since 12/27/2019
 */
@Component
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("\n*******************************************************************");
        System.out.println("Security Filter Layer: ");
        System.out.println("\tRequest Type:--> " + httpServletRequest.getMethod());
        System.out.println("\tRequest URI:---> " + httpServletRequest.getRequestURI());
        System.out.println("*******************************************************************\n");
        chain.doFilter(httpServletRequest, response);
    }

}
