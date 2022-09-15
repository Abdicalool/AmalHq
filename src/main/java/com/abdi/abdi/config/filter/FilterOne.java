package com.abdi.abdi.config.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class FilterOne extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     try{
         filterChain.doFilter(request,response);
     }catch (RuntimeException e){
         response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
     }
    }
}
