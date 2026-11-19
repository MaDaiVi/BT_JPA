
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title>Xác nhận OTP</title>

<style>
.otp-page {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: calc(100vh - 180px);
	padding: 20px;
}

.otp-box {
	width: 100%;
	max-width: 450px;
	padding: 30px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 10px #ccc;
}

.otp-box h2 {
	text-align: center;
	margin-bottom: 15px;
}

.otp-box input {
	width: 100%;
	padding: 10px;
	margin: 10px 0 15px;
	box-sizing: border-box;
	text-align: center;
	letter-spacing: 5px;
	font-size: 20px;
}

.otp-box button {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
}

.otp-box .alert {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}
</style>

<div class="otp-page">
	<div class="otp-box">

		<h2>Xác nhận tài khoản</h2>

		<p>Vui lòng kiểm tra email và nhập mã OTP gồm 6 chữ số.</p>

		<%
		String alert = (String) request.getAttribute("alert");
		if (alert != null) {
		%>
		<p class="alert"><%=alert%></p>
		<%
		}
		%>

		<!-- Form xác nhận OTP -->
		<form action="${pageContext.request.contextPath}/verify-otp"
			method="post">

			<label>Mã OTP:</label> <input type="text" name="otp" maxlength="6"
				minlength="6" pattern="[0-9]{6}" class="form-control"
				placeholder="Nhập mã OTP" required>

			<button type="submit" class="btn btn-primary">Xác nhận</button>
		</form>

		<hr>

		<!-- Form gửi lại OTP -->
		<form action="${pageContext.request.contextPath}/resend-otp"
			method="post">

			<button type="submit" class="btn btn-outline-secondary">Gửi
				lại mã OTP</button>
		</form>

	</div>
</div>
