
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Đặt lại mật khẩu</title>

<style>
.reset-page {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: calc(100vh - 180px);
	padding: 20px;
}

.reset-box {
	width: 100%;
	max-width: 450px;
	padding: 30px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

.reset-box h2 {
	text-align: center;
	margin-bottom: 25px;
}

.reset-box .form-group {
	margin-bottom: 15px;
}

.reset-box input {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	box-sizing: border-box;
}

.reset-box button {
	width: 100%;
	padding: 10px;
}

.reset-box .alert {
	color: red;
	margin-bottom: 15px;
	text-align: center;
}
</style>

<div class="reset-page">
	<div class="reset-box">

		<h2>Đặt lại mật khẩu</h2>

		<c:if test="${not empty alert}">
			<p class="alert">${alert}</p>
		</c:if>

		<form action="${pageContext.request.contextPath}/reset-password"
			method="post">

			<div class="form-group">
				<label>Mã OTP:</label> <input type="text" name="otpCode"
					maxlength="6" class="form-control" required>
			</div>

			<div class="form-group">
				<label>Mật khẩu mới:</label> <input type="password"
					name="newPassword" class="form-control" required>
			</div>

			<div class="form-group">
				<label>Xác nhận mật khẩu mới:</label> <input type="password"
					name="confirmPassword" class="form-control" required>
			</div>

			<button type="submit" class="btn btn-primary">Đổi mật khẩu</button>

		</form>

		<p class="text-center mt-3">
			<a href="${pageContext.request.contextPath}/login"> Quay lại đăng
				nhập </a>
		</p>

	</div>
</div>
