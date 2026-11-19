
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Video theo danh mục</title>

<div class="container-fluid">

	<div class="mb-4">
		<h2>Danh sách Video thuộc danh mục: ${category.cateName}</h2>
	</div>

	<div class="mb-3 d-flex flex-wrap gap-2">
		<a href="${pageContext.request.contextPath}/category"
			class="btn btn-secondary"> <i class="bi bi-arrow-left"></i> Danh
			sách danh mục
		</a> <a href="${pageContext.request.contextPath}/video/add"
			class="btn btn-primary"> <i class="bi bi-plus-circle"></i> Thêm
			Video
		</a> <a href="${pageContext.request.contextPath}/video"
			class="btn btn-outline-primary"> Quản lý tất cả Video </a>
	</div>

	<div class="table-responsive">
		<table
			class="table table-bordered table-striped table-hover align-middle">

			<thead class="table-dark">
				<tr>
					<th>ID Video</th>
					<th>Tiêu đề</th>
					<th>Mô tả</th>
					<th>Poster</th>
					<th>Lượt xem</th>
					<th>Trạng thái</th>
					<th>Thao tác</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${videos}" var="video">
					<tr>
						<td>${video.videoId}</td>

						<td><a
							href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">
								${video.title} </a></td>

						<td>${video.description}</td>

						<td>${video.poster}</td>

						<td>${video.views}</td>

						<td><c:choose>
								<c:when test="${video.active == 1}">
									<span class="badge bg-success"> Đang hoạt động </span>
								</c:when>

								<c:otherwise>
									<span class="badge bg-secondary"> Ngừng hoạt động </span>
								</c:otherwise>
							</c:choose></td>

						<!-- Thao tác -->
						<td><a
							href="${pageContext.request.contextPath}/video/edit?videoId=${video.videoId}"
							class="btn btn-sm btn-warning"> Sửa </a> <a
							href="${pageContext.request.contextPath}/video/delete?videoId=${video.videoId}"
							class="btn btn-sm btn-danger"
							onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">
								Xóa </a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty videos}">
					<tr>
						<td colspan="7" class="text-center">Danh mục này chưa có
							video nào!</td>
					</tr>
				</c:if>
			</tbody>

		</table>
	</div>

</div>
