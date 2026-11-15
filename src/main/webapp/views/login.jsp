<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

<style>
body {
	font-family: Arial;
	background: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-box {
	background: white;
	width: 350px;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

h2 {
	text-align: center;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 10px;
	background: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 5px;
}

.alert {
	color: red;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>

<body>

	<div class="login-box">

		<h2>Đăng nhập</h2>

		<!-- Hiển thị thông báo lỗi -->
		<c:if test="${alert != null}">
			<div class="alert">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post">

			<input type="text" name="username" placeholder="Tên đăng nhập"
				required> <input type="password" name="password"
				placeholder="Mật khẩu" required> <label> <input
				type="checkbox" name="remember"> Ghi nhớ đăng nhập
			</label> <br> <br>

			<button type="submit">Đăng nhập</button>

		</form>

		<p style="text-align: center;">
			Chưa có tài khoản? <a
				href="${pageContext.request.contextPath}/register">Đăng ký</a>
		</p>

	</div>

</body>
</html>