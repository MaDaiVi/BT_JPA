<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa danh mục</title>
</head>
<body>

	<%@ include file="topbar.jsp"%>

	<h2>Chỉnh sửa danh mục</h2>

	<p style="color: red;">${error}</p>

	<form action="${pageContext.request.contextPath}/category/edit"
		method="post" enctype="multipart/form-data">

		<input type="hidden" name="cateId" value="${category.cateId}">

		<label>Mã danh mục:</label> <input type="text"
			value="${category.cateId}" disabled> <br> <br> <label>Tên
			danh mục:</label> <input type="text" name="cateName"
			value="${category.cateName}" required maxlength="255"> <br>
		<br>

		<div>
			<label>Hình ảnh hiện tại:</label>
			<c:if test="${not empty category.icons}">
				<img
					src="${pageContext.request.contextPath}/image?fname=${category.icons}"
					width="120" height="120" style="object-fit: cover;">
			</c:if>
		</div>

		<div>
			<label>Chọn hình ảnh mới:</label> <input type="file" name="icons"
				accept="image/*">
		</div>


		<button type="submit">Cập nhật</button>

		<a href="${pageContext.request.contextPath}/category"> Quay lại </a>

	</form>

</body>
</html>