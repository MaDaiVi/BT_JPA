
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Chỉnh sửa danh mục</title>

<div class="container">
	<div class="card shadow-sm mx-auto" style="max-width: 600px;">

		<div class="card-header bg-warning">
			<h4 class="mb-0">Chỉnh sửa danh mục</h4>
		</div>

		<div class="card-body">

			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/category/edit"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="cateId" value="${category.cateId}">

				<div class="mb-3">
					<label class="form-label">Mã danh mục:</label> <input type="text"
						class="form-control" value="${category.cateId}" disabled>
				</div>

				<div class="mb-3">
					<label class="form-label">Tên danh mục:</label> <input type="text"
						name="cateName" class="form-control" value="${category.cateName}"
						required maxlength="255">
				</div>

				<div class="mb-3">
					<label class="form-label">Hình ảnh hiện tại:</label>

					<div class="mt-2">
						<c:if test="${not empty category.icons}">
							<img
								src="${pageContext.request.contextPath}/image?fname=${category.icons}"
								alt="${category.cateName}" width="120" height="120"
								class="rounded" style="object-fit: cover;">
						</c:if>

						<c:if test="${empty category.icons}">
							<p class="text-muted">Danh mục chưa có hình ảnh.</p>
						</c:if>
					</div>
				</div>

				<div class="mb-3">
					<label class="form-label">Chọn hình ảnh mới:</label> <input
						type="file" name="icons" class="form-control" accept="image/*">
				</div>

				<div class="d-flex gap-2">
					<button type="submit" class="btn btn-warning">
						<i class="bi bi-pencil-square"></i> Cập nhật
					</button>

					<a href="${pageContext.request.contextPath}/category"
						class="btn btn-secondary"> Quay lại </a>
				</div>

			</form>
		</div>
	</div>
</div>
