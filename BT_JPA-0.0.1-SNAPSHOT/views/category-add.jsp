<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
</head>
<body>

	<%@ include file="topbar.jsp"%>

	<h2>Thêm danh mục</h2>

	<p style="color: red;">${error}</p>

	<form action="${pageContext.request.contextPath}/category/add"
		method="post" enctype="multipart/form-data">

		<label>Tên danh mục:</label> <input type="text" name="cateName"
			required maxlength="255"> <br> <br>

		<div>
			<label>Hình ảnh:</label> <input type="file" name="icons"
				accept="image/*">
		</div>
		<button type="submit">Thêm</button>

		<a href="${pageContext.request.contextPath}/category"> Quay lại </a>

	</form>

</body>
</html>