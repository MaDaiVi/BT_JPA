
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- Tiêu đề trang -->
<div class="container py-4">

	<!-- Lời chào -->
	<div class="mb-4">
		<h2>Khám phá video</h2>
		<p class="text-muted">Chào mừng bạn đến với hệ thống quản lý video
			BT_JPA.</p>
	</div>

	<!-- Khu vực dành cho Admin -->
	<c:if test="${sessionScope.account.roleid == 1}">
		<div class="mb-4">
			<a href="${pageContext.request.contextPath}/video/add"
				class="btn btn-primary"> <i class="bi bi-plus-circle"></i> Thêm
				video mới
			</a>
		</div>
	</c:if>

	<!-- Danh sách video -->
	<h4 class="mb-3">10 video mới nhất</h4>

	<c:if test="${empty latestVideos}">
		<div class="alert alert-info">Hiện tại chưa có video nào.</div>
	</c:if>

	<div class="row g-4">

		<c:forEach items="${latestVideos}" var="video">

			<div class="col-md-6 col-lg-4">

				<div class="card h-100 shadow-sm">

					<!-- Ảnh đại diện video -->
					<c:if test="${not empty video.poster}">
						<img
							src="${pageContext.request.contextPath}/image?fname=${video.poster}"
							class="card-img-top" alt="${video.title}"
							style="height: 200px; object-fit: cover;">
					</c:if>

					<c:if test="${empty video.poster}">
						<div
							class="bg-light d-flex align-items-center
                                    justify-content-center"
							style="height: 200px;">
							<i class="bi bi-play-circle text-secondary"
								style="font-size: 60px;"></i>
						</div>
					</c:if>

					<!-- Nội dung card -->
					<div class="card-body d-flex flex-column">

						<h5 class="card-title">${video.title}</h5>

						<p class="card-text text-muted">
							<c:choose>
								<c:when test="${not empty video.description}">
                                    ${video.description}
                                </c:when>
								<c:otherwise>
                                    Chưa có mô tả cho video này.
                                </c:otherwise>
							</c:choose>
						</p>

						<p class="text-muted small mt-auto">
							<i class="bi bi-eye"></i> Lượt xem: ${video.views}
						</p>

						<a
							href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}"
							class="btn btn-outline-primary"> Xem chi tiết </a>

					</div>
				</div>

			</div>

		</c:forEach>

	</div>

	<!-- Xem tất cả -->
	<div class="text-center mt-4">
		<a href="${pageContext.request.contextPath}/video"
			class="btn btn-dark"> Xem tất cả video <i
			class="bi bi-arrow-right"></i>
		</a>
	</div>

</div>
