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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        String uri = req.getRequestURI();
        User user = (User) session.getAttribute("user");
        
        if (uri.contains("/sign-in.jsp") || uri.contains("/account/sign-in")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.contains("/admin/")) {
            if (user == null) {
                req.setAttribute("message", "Vui lòng đăng nhập!");
                req.getRequestDispatcher("/sign-in.jsp").forward(req, resp);
                return;
            }
            if (!user.getAdmin()) { // Đảm bảo User có hàm isAdmin() hoặc getAdmin()
                req.setAttribute("message", "Bạn không có quyền truy cập vùng Quản trị!");
                req.getRequestDispatcher("/sign-in.jsp").forward(req, resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}