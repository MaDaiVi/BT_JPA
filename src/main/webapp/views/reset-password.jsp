
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
</head>
<body>

    <h2>Đặt lại mật khẩu</h2>

    <c:if test="${not empty alert}">
        <p style="color: red;">${alert}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/reset-password"
          method="post">

        <div>
            <label>Mã OTP:</label>
            <input type="text" name="otpCode"
                   maxlength="6" required>
        </div>

        <br>

        <div>
            <label>Mật khẩu mới:</label>
            <input type="password" name="newPassword"
                   required>
        </div>

        <br>

        <div>
            <label>Xác nhận mật khẩu mới:</label>
            <input type="password" name="confirmPassword"
                   required>
        </div>

        <br>

        <button type="submit">Đổi mật khẩu</button>
    </form>

    <br>

    <a href="${pageContext.request.contextPath}/login">
        Quay lại đăng nhập
    </a>

</body>
</html>
