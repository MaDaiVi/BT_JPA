<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Đăng nhập</title>

<style>
.login-page {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: calc(100vh - 180px);
	padding: 20px;
}

.login-box {
	background: white;
	width: 100%;
	max-width: 400px;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

.login-box h2 {
	text-align: center;
	margin-bottom: 20px;
}

.login-box input[type="text"], .login-box input[type="password"] {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	box-sizing: border-box;
}

.login-box button {
	width: 100%;
	padding: 10px;
	background: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 5px;
}

.login-box .alert {
	color: red;
	text-align: center;
	margin-bottom: 10px;
}
</style>

<div class="login-page">
	<div class="login-box">

		<h2>Đăng nhập</h2>

		<c:if test="${alert != null}">
			<div class="alert">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post">

			<input type="text" name="username" placeholder="Tên đăng nhập"
				required> <input type="password" name="password"
				placeholder="Mật khẩu" required> <label> <input
				type="checkbox" name="remember"> Ghi nhớ đăng nhập
			</label> <br>
			<br>

			<button type="submit">Đăng nhập</button>

			<div>
				<a href="${pageContext.request.contextPath}/forgot-password">
					Quên mật khẩu? </a>
			</div>

		</form>

		<p style="text-align: center;">
			Chưa có tài khoản? <a
				href="${pageContext.request.contextPath}/register"> Đăng ký </a>
		</p>

	</div>
</div>