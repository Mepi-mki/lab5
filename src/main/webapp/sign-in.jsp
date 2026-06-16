<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập Hệ Thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .login-container { max-width: 400px; margin-top: 100px; }
    </style>
</head>
<body>
    <div class="container login-container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center py-3">
                <h4 class="mb-0 text-uppercase fw-bold">Đăng Nhập</h4>
            </div>
            <div class="card-body p-4">
                
                <%-- Hiển thị thông báo lỗi hoặc thành công nếu có --%>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger text-center py-2" role="alert">
                        ${message}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/account/sign-in" method="post">
                    <div class="mb-3">
                        <label class="form-label fw-bold">Username</label>
                        <input type="text" name="username" class="form-control" placeholder="Nhập tên đăng nhập" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
                    </div>
                    <div class="d-grid gap-2 mt-4">
                        <button type="submit" class="btn btn-primary fw-bold">ĐĂNG NHẬP</button>
                        <a href="${pageContext.request.contextPath}/user.jsp" class="btn btn-light border text-secondary">Quay lại Trang chủ</a>
                    </div>
                </form>
                
            </div>
        </div>
    </div>
</body>
</html>