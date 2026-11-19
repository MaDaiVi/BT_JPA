
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Quản lý danh mục</title>

<div class="container-fluid">

	<div class="d-flex justify-content-between align-items-center mb-4">
		<h2>Danh sách danh mục</h2>

		<a href="${pageContext.request.contextPath}/category/add"
			class="btn btn-primary"> <i class="bi bi-plus-circle"></i> Thêm
			danh mục
		</a>
	</div>

	<!-- Tìm kiếm danh mục -->
	<form action="${pageContext.request.contextPath}/category" method="get"
		class="row g-2 mb-4">

		<div class="col-md-5">
			<input type="text" name="keyword" class="form-control"
				placeholder="Nhập tên danh mục cần tìm..." value="${keyword}">
		</div>

		<div class="col-auto">
			<button type="submit" class="btn btn-primary">
				<i class="bi bi-search"></i> Tìm kiếm
			</button>

			<a href="${pageContext.request.contextPath}/category"
				class="btn btn-secondary"> Làm mới </a>
		</div>
	</form>

	<!-- Bảng danh sách Category -->
	<div class="table-responsive">
		<table
			class="table table-bordered table-striped table-hover align-middle">

			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Tên danh mục</th>
					<th>Hình ảnh</th>
					<th>Thao tác</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${categories}" var="category">
					<tr>
						<td>${category.cateId}</td>

						<td>${category.cateName}</td>

						<!-- Hiển thị hình ảnh -->
						<td><c:if test="${not empty category.icons}">
								<img
									src="${pageContext.request.contextPath}/image?fname=${category.icons}"
									alt="${category.cateName}" width="80" height="80"
									style="object-fit: cover; border-radius: 5px;">
							</c:if> <c:if test="${empty category.icons}">
								<span class="text-muted">Chưa có ảnh</span>
							</c:if></td>

						<!-- Các thao tác -->
						<td><a
							href="${pageContext.request.contextPath}/category/videos?id=${category.cateId}"
							class="btn btn-sm btn-info text-white"> Xem video </a> <a
							href="${pageContext.request.contextPath}/category/edit?id=${category.cateId}"
							class="btn btn-sm btn-warning"> Sửa </a> <a
							href="${pageContext.request.contextPath}/category/delete?id=${category.cateId}"
							class="btn btn-sm btn-danger"
							onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">
								Xóa </a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty categories}">
					<tr>
						<td colspan="4" class="text-center">Chưa có danh mục nào!</td>
					</tr>
				</c:if>
			</tbody>

		</table>
	</div>

</div>
