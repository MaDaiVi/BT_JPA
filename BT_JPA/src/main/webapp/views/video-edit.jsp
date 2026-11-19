
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Sửa video</title>

<div class="container">
	<div class="card shadow-sm mx-auto" style="max-width: 700px;">
		<div class="card-header bg-warning">
			<h4 class="mb-0">Sửa video</h4>
		</div>

		<div class="card-body">

			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/video/edit"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="videoId" value="${video.videoId}">

				<div class="mb-3">
					<label class="form-label">Mã video:</label> <input type="text"
						class="form-control" value="${video.videoId}" disabled>
				</div>

				<div class="mb-3">
					<label class="form-label">Tiêu đề:</label> <input type="text"
						name="title" class="form-control" value="${video.title}" required
						maxlength="500">
				</div>

				<div class="mb-3">
					<label class="form-label">Mô tả:</label>
					<textarea name="description" class="form-control" rows="4"
						maxlength="500">${video.description}</textarea>
				</div>

				<div class="mb-3">
					<label class="form-label">Poster (đường dẫn ảnh):</label> <input
						type="text" name="poster" class="form-control"
						value="${video.poster}" maxlength="500">
				</div>

				<div class="mb-3">
					<label class="form-label">Video hiện tại:</label>
					<p class="form-control-plaintext">${video.videoPath}</p>
				</div>

				<div class="mb-3">
					<label class="form-label">Thay video mới (không bắt buộc):</label>
					<input type="file" name="videoFile" class="form-control"
						accept="video/*">
				</div>

				<div class="mb-3">
					<label class="form-label">Danh mục:</label> <select name="cateId"
						class="form-select" required>
						<c:forEach items="${categories}" var="category">
							<option value="${category.cateId}"
								${category.cateId == video.category.cateId ? 'selected' : ''}>
								${category.cateName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">Trạng thái:</label> <select name="active"
						class="form-select">
						<option value="1" ${video.active == 1 ? 'selected' : ''}>
							Hoạt động</option>

						<option value="0" ${video.active == 0 ? 'selected' : ''}>
							Ngừng hoạt động</option>
					</select>
				</div>

				<div class="d-flex gap-2">
					<button type="submit" class="btn btn-warning">
						<i class="bi bi-pencil-square"></i> Cập nhật
					</button>

					<a href="${pageContext.request.contextPath}/video"
						class="btn btn-secondary"> Quay lại </a>
				</div>

			</form>
		</div>
	</div>
</div>
