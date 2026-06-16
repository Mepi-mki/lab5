package com.example.Filter;

import java.io.IOException;

import com.example.Entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/admin/*")
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();
        
        String username = (user != null) ? user.getId() : "Guest";
        System.out.println("[LOG] Tài khoản: " + username + " truy cập: " + uri);
        
        chain.doFilter(request, response);
    }
}