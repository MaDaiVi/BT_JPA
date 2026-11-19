
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>${video.title}</title>

<div class="container">
	<div class="card shadow-sm mx-auto" style="max-width: 900px;">

		<div class="card-header bg-primary text-white">
			<h3 class="mb-0">${video.title}</h3>
		</div>

		<div class="card-body">

			<p class="text-muted">${video.description}</p>

			<c:if test="${not empty video.poster}">
				<div class="text-center mb-4">
					<img src="${pageContext.request.contextPath}/${video.poster}"
						alt="${video.title}" class="img-fluid rounded"
						style="max-width: 300px;">
				</div>
			</c:if>

			<div class="text-center mb-4">
				<c:choose>
					<c:when test="${not empty video.videoPath}">
						<video class="w-100 rounded" style="max-width: 800px;" controls>
							<source
								src="${pageContext.request.contextPath}/${video.videoPath}"
								type="video/mp4">

							Trình duyệt không hỗ trợ phát video.
						</video>
					</c:when>

					<c:otherwise>
						<div class="alert alert-warning">Video chưa có file để phát.
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<p>
						<strong>Lượt xem:</strong> ${video.views}
					</p>
				</div>

				<div class="col-md-6">
					<p>
						<strong>Danh mục:</strong> ${video.category.cateName}
					</p>
				</div>
			</div>

			<hr>

			<div class="d-flex gap-2">
				<a href="${pageContext.request.contextPath}/home"
					class="btn btn-secondary"> <i class="bi bi-house"></i> Trang
					chủ
				</a> <a href="${pageContext.request.contextPath}/video"
					class="btn btn-primary"> <i class="bi bi-camera-video"></i>
					Danh sách video
				</a>
			</div>

		</div>
	</div>
</div>
