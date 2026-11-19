
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Đăng ký tài khoản</title>

<style>
.register-page {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: calc(100vh - 180px);
	padding: 20px;
}

.register-box {
	width: 100%;
	max-width: 500px;
	padding: 30px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

.register-box h2 {
	text-align: center;
	margin-bottom: 25px;
}

.register-box input {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	box-sizing: border-box;
}

.register-box .form-group {
	margin-bottom: 15px;
}

.register-box button {
	width: 100%;
	padding: 10px;
}

.register-box .alert {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}
</style>

<div class="register-page">
	<div class="register-box">

		<h2>ĐĂNG KÝ TÀI KHOẢN</h2>

		<c:if test="${not empty alert}">
			<div class="alert">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/register"
			method="post">

			<div class="form-group">
				<label>Tên đăng nhập:</label> <input type="text" name="username"
					class="form-control" required>
			</div>

			<div class="form-group">
				<label>Mật khẩu:</label> <input type="password" name="password"
					class="form-control" required>
			</div>

			<div class="form-group">
				<label>Email:</label> <input type="email" name="email"
					class="form-control">
			</div>

			<div class="form-group">
				<label>Họ và tên:</label> <input type="text" name="fullname"
					class="form-control">
			</div>

			<div class="form-group">
				<label>Số điện thoại:</label> <input type="text" name="phone"
					class="form-control">
			</div>

			<button type="submit" class="btn btn-primary">Đăng ký</button>
		</form>

		<p class="text-center mt-3">
			Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">
				Đăng nhập </a>
		</p>

	</div>
</div>
