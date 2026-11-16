<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
</head>
<body>

    <h2>Quên mật khẩu</h2>

    <p>Nhập email đã đăng ký để nhận mã OTP.</p>

    <%
        String alert = (String) request.getAttribute("alert");

        if (alert != null) {
    %>
        <p><%= alert %></p>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/forgot-password" method="post">

        <label>Email:</label>

        <input type="email" name="email" required>

        <button type="submit">Gửi mã OTP</button>

    </form>

    <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>

</body>
</html>