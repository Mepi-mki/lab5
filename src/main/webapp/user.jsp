<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container { max-width: 800px; margin-top: 30px; }
        .nav-tabs .nav-link.active { font-weight: bold; color: #0d6efd; }
    </style>
</head>
<body>
     <div style="background-color: #f8f9fa; padding: 10px; text-align: right; border-bottom: 1px solid #ddd;">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <b>Welcome you</b> | 
            <a href="${pageContext.request.contextPath}/sign-in.jsp">Đăng nhập</a>
        </c:when>
        <c:otherwise>
            <b>Welcome ${sessionScope.user.fullname}</b> | 
            <a href="${pageContext.request.contextPath}/account/sign-out">Đăng xuất</a>
            
            <c:if test="${sessionScope.user.admin}">
                | <a href="${pageContext.request.contextPath}/admin/index.jsp">Quản trị</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
    <div class="container">
        <h2 class="mb-4 text-primary">QUẢN LÝ USER</h2>

        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link ${empty form.id ? 'active' : ''}" id="list-tab" data-bs-toggle="tab" data-bs-target="#list" type="button" role="tab">DANH SÁCH</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link ${not empty form.id ? 'active' : ''}" id="form-tab" data-bs-toggle="tab" data-bs-target="#form" type="button" role="tab">CẬP NHẬT</button>
            </li>
        </ul>

<div style="padding: 15px;">
    <h2>Visitors: ${applicationScope.visitors}</h2>
</div>

        <div class="tab-content border border-top-0 p-4" id="myTabContent">
            
            <div class="tab-pane fade ${empty form.id ? 'show active' : ''}" id="list" role="tabpanel">
                <table class="table table-bordered table-hover">
                    <thead class="table-primary text-center">
                        <tr>
                            <th>ID</th>
                            <th>PASSWORD</th>
                            <th>FULLNAME</th>
                            <th>EMAIL</th>
                            <th>ROLE</th>
                            <th>ACTION</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.password}</td>
                                <td>${item.fullname}</td>
                                <td>${item.email}</td>
                                <td>${item.admin ? 'Admin' : 'User'}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/user/edit/${item.id}" class="text-decoration-none fw-bold">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade ${not empty form.id ? 'show active' : ''}" id="form" role="tabpanel">
                <form action="${pageContext.request.contextPath}/user/index" method="post">
                    <div class="mb-3">
                        <label>Username</label>
                        <input type="text" name="id" value="${form.id}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Password</label>
                        <input type="password" name="password" value="${form.password}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Fullname</label>
                        <input type="text" name="fullname" value="${form.fullname}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Email Address</label>
                        <input type="email" name="email" value="${form.email}" class="form-control" required>
                    </div>
                    <div class="row mb-4">
                        <div class="col-md-6">
                            <label class="d-block mb-2">Role</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="admin" value="true" ${form.admin ? 'checked' : ''}>
                                <label class="form-check-label">Admin</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="admin" value="false" ${!form.admin ? 'checked' : ''}>
                                <label class="form-check-label">User</label>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div>
                        <button type="submit" formaction="${pageContext.request.contextPath}/user/create" class="btn btn-outline-primary">Create</button>
                        <button type="submit" formaction="${pageContext.request.contextPath}/user/update" class="btn btn-outline-success">Update</button>
                        <button type="submit" formaction="${pageContext.request.contextPath}/user/delete" class="btn btn-outline-danger">Delete</button>
                        <a href="${pageContext.request.contextPath}/user/reset" class="btn btn-outline-secondary">Reset</a>
                    </div>
                </form>
            </div>

        </div>
    </div>
</body>
</html>