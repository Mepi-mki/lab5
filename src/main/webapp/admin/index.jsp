<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hệ Thống Quản Trị</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card shadow border-0">
            <div class="card-header bg-danger text-white d-flex justify-content-between align-items-center py-3">
                <h4 class="mb-0 fw-bold text-uppercase">Hệ Thống Quản Trị (Admin Panel)</h4>
                <div>
                    <span class="me-3 fw-semibold">Admin: ${sessionScope.user.fullname}</span>
                    <a href="${pageContext.request.contextPath}/user.jsp" class="btn btn-sm btn-outline-light fw-bold">Xem trang chủ</a>
                </div>
            </div>
            <div class="card-body p-5 bg-white text-center">
                <h1 class="text-success fw-bold mb-3">Xác Thực Thành Công!</h1>
                <p class="text-muted fs-5">Tài khoản của bạn có toàn quyền quản trị hệ thống và đã được ghi nhận qua hệ thống kiểm duyệt bảo mật.</p>
                
                <hr class="my-4">
                
                <div class="row g-4 mt-2 justify-content-center">
                    <div class="col-md-5">
                        <div class="card h-100 border p-3 hover-shadow">
                            <div class="card-body">
                                <h5 class="card-title fw-bold text-primary">QUẢN LÝ NGƯỜI DÙNG</h5>
                                <p class="card-text text-muted small">Thêm, sửa, xóa thông tin tài khoản và phân quyền chức năng thành viên.</p>
                                <%-- Đường dẫn trỏ tới trang user.jsp của bạn --%>
                                <a href="${pageContext.request.contextPath}/user/index" class="btn btn-primary btn-sm px-4 fw-bold mt-2">Truy cập</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="card h-100 border p-3 hover-shadow">
                            <div class="card-body">
                                <h5 class="card-title fw-bold text-secondary">NHẬT KÝ HỆ THỐNG</h5>
                                <p class="card-text text-muted small">Xem lịch sử ghi log truy cập các tài nguyên vùng cấm từ LoggerFilter.</p>
                                <button class="btn btn-secondary btn-sm px-4 fw-bold mt-2" disabled>Đang phát triển</button>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="mt-5">
                    <a href="${pageContext.request.contextPath}/account/sign-out" class="text-danger text-decoration-none fw-bold small">👉 Đăng xuất khỏi hệ thống quản trị</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>