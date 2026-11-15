
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
</head>
<body>
    <h2>ĐĂNG KÝ TÀI KHOẢN</h2>

    <c:if test="${not empty alert}">
        <p style="color: red;">${alert}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">

        <p>
            Tên đăng nhập:
            <input type="text" name="username" required>
        </p>

        <p>
            Mật khẩu:
            <input type="password" name="password" required>
        </p>

        <p>
            Email:
            <input type="email" name="email">
        </p>

        <p>
            Họ và tên:
            <input type="text" name="fullname">
        </p>

        <p>
            Số điện thoại:
            <input type="text" name="phone">
        </p>

        <button type="submit">Đăng ký</button>
    </form>

    <p>
        Đã có tài khoản?
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
</body>
</html>
