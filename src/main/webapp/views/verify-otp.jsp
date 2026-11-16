<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận OTP</title>
</head>
<body>

    <h2>Xác nhận tài khoản</h2>

    <p>Vui lòng kiểm tra email và nhập mã OTP gồm 6 chữ số.</p>

    <%
        String alert = (String) request.getAttribute("alert");

        if (alert != null) {
    %>
        <p><%= alert %></p>
    <%
        }
    %>

    <!-- Form xác nhận OTP -->
    <form action="${pageContext.request.contextPath}/verify-otp" method="post">

        <label>Mã OTP:</label>

        <input
            type="text"
            name="otp"
            maxlength="6"
            minlength="6"
            pattern="[0-9]{6}"
            required
        >

        <button type="submit">Xác nhận</button>

    </form>

    <hr>

    <!-- Form gửi lại OTP -->
    <form action="${pageContext.request.contextPath}/resend-otp" method="post">

        <button type="submit">Gửi lại mã OTP</button>

    </form>

</body>
</html>