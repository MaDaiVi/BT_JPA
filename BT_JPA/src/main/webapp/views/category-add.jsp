
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title>Thêm danh mục</title>

<div class="container">
	<div class="card shadow-sm mx-auto" style="max-width: 600px;">

		<div class="card-header bg-primary text-white">
			<h4 class="mb-0">Thêm danh mục</h4>
		</div>

		<div class="card-body">

			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/category/add"
				method="post" enctype="multipart/form-data">

				<div class="mb-3">
					<label class="form-label">Tên danh mục:</label> <input type="text"
						name="cateName" class="form-control" maxlength="255" required>
				</div>

				<div class="mb-3">
					<label class="form-label">Hình ảnh:</label> <input type="file"
						name="icons" class="form-control" accept="image/*">
				</div>

				<div class="d-flex gap-2">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-plus-circle"></i> Thêm
					</button>

					<a href="${pageContext.request.contextPath}/category"
						class="btn btn-secondary"> Quay lại </a>
				</div>

			</form>

		</div>
	</div>
</div>
