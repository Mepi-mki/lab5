package com.example.Servlet;

import java.io.IOException;  

import com.example.DAO.UserDAO;  
import com.example.Entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/account/sign-in", "/account/sign-out"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        // 1. Xử lý khi người dùng bấm nút Đăng xuất
        if (uri.contains("sign-out")) {
            HttpSession session = req.getSession();
            session.removeAttribute("user"); // Xóa đối tượng user khỏi session
            resp.sendRedirect(req.getContextPath() + "/index.jsp"); // Đẩy về trang chủ
            return;
        }
        
        // 2. Mặc định: Trả về giao diện form đăng nhập
        req.getRequestDispatcher("/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form sign-in.jsp gửi lên
        String id = req.getParameter("username");
        String pw = req.getParameter("password");
        
        try {
            UserDAO dao = new UserDAO();
            User user = dao.findById(id); 
            
            // Kiểm tra xem user có tồn tại và mật khẩu có khớp không
            if (user != null && user.getPassword().equals(pw)) {
                
                // ĐĂNG NHẬP THÀNH CÔNG: Lưu user vào Session (Cực kỳ quan trọng cho Bài 4 Lab 7)
                req.getSession().setAttribute("user", user); 
                
                // Chuyển hướng về trang chủ
                resp.sendRedirect(req.getContextPath() + "/index.jsp"); 
            } else {
                // ĐĂNG NHẬP THẤT BẠI: Báo lỗi và quay lại form
                req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
                req.getRequestDispatcher("/sign-in.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi hệ thống khi đăng nhập!");
            req.getRequestDispatcher("/sign-in.jsp").forward(req, resp);
        }
    }
}