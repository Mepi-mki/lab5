package com.example.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DAO.UserDAO;
import com.example.Entity.User;

@WebServlet({
    "/user/index", 
    "/user/edit/*", 
    "/user/create", 
    "/user/update", 
    "/user/delete", 
    "/user/reset",
})
public class UserServlet extends HttpServlet {

    private UserDAO dao = new UserDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();

        User user = new User();
        // Lấy dữ liệu từ form nếu có
        try {
            user.setId(req.getParameter("id"));
            user.setPassword(req.getParameter("password"));
            user.setFullname(req.getParameter("fullname"));
            user.setEmail(req.getParameter("email"));
            user.setAdmin(Boolean.parseBoolean(req.getParameter("admin")));
        } catch (Exception e) {
            // Bỏ qua lỗi parse nếu form trống
        }

        if (uri.contains("edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            user = dao.findById(id);
        } else if (uri.contains("create")) {
            dao.create(user);
            user = new User(); // Reset form sau khi thêm
        } else if (uri.contains("update")) {
            dao.update(user);
        } else if (uri.contains("delete")) {
            dao.remove(user.getId());
            user = new User(); // Reset form sau khi xóa
        } else if (uri.contains("reset")) {
            user = new User();
        }

        // Truyền user đang thao tác và danh sách sang JSP
        req.setAttribute("form", user);
        List<User> items = dao.findAll();
        req.setAttribute("items", items);

        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}