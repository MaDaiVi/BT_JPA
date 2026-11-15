<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<%@ include file="topbar.jsp"%>

	<h2>Đăng nhập thành công!</h2>

	<p>Xin chào: ${user.fullName}</p>

	<p>Username: ${user.userName}</p>

	<p>Email: ${user.email}</p>

	<p>Vai trò: ${user.roleid}</p>

	<a href="${pageContext.request.contextPath}/logout"> Đăng xuất </a>

</body>
</html>