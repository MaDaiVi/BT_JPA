
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title>Quên mật khẩu</title>

<style>
.forgot-page {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: calc(100vh - 180px);
	padding: 20px;
}

.forgot-box {
	width: 100%;
	max-width: 450px;
	padding: 30px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

.forgot-box h2 {
	text-align: center;
	margin-bottom: 15px;
}

.forgot-box input {
	width: 100%;
	padding: 10px;
	margin: 10px 0 15px;
	box-sizing: border-box;
}

.forgot-box button {
	width: 100%;
	padding: 10px;
}

.forgot-box .alert {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}
</style>

<div class="forgot-page">
	<div class="forgot-box">

		<h2>Quên mật khẩu</h2>

		<p>Nhập email đã đăng ký để nhận mã OTP.</p>

		<%
		String alert = (String) request.getAttribute("alert");
		if (alert != null) {
		%>
		<p class="alert"><%=alert%></p>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/forgot-password"
			method="post">

			<label>Email:</label> <input type="email" name="email"
				class="form-control" required>

			<button type="submit" class="btn btn-primary">Gửi mã OTP</button>

		</form>

		<p class="text-center mt-3">
			<a href="${pageContext.request.contextPath}/login"> Quay lại đăng
				nhập </a>
		</p>

	</div>
</div>
