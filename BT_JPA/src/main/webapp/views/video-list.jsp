
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Danh sách video</title>

<div class="container-fluid py-4">

	<%-- Tiêu đề và nút thêm video --%>
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h2>Danh sách video</h2>

		<c:if test="${not empty user and user.roleid == 1}">
			<a href="${pageContext.request.contextPath}/video/add"
				class="btn btn-primary"> <i class="bi bi-plus-circle"></i> Thêm
				video
			</a>
		</c:if>
	</div>

	<p>Số video hiển thị: ${videos.size()}</p>

	<%-- Bảng danh sách video --%>
	<div class="table-responsive">
		<table
			class="table table-bordered table-striped table-hover align-middle">

			<thead class="table-dark">
				<tr>
					<th>Video ID</th>
					<th>Tiêu đề</th>
					<th>Mô tả</th>
					<th>Poster</th>
					<th>Lượt xem</th>

					<%-- Chỉ Admin mới thấy trạng thái --%>
					<c:if test="${not empty user and user.roleid == 1}">
						<th>Trạng thái</th>
					</c:if>

					<th>Danh mục</th>

					<%-- Chỉ Admin mới thấy thao tác --%>
					<c:if test="${not empty user and user.roleid == 1}">
						<th>Thao tác</th>
					</c:if>
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

						<%-- Trạng thái chỉ dành cho Admin --%>
						<c:if test="${not empty user and user.roleid == 1}">
							<td><c:choose>
									<c:when test="${video.active == 1}">
										<span class="badge bg-success"> Đang hoạt động </span>
									</c:when>

									<c:otherwise>
										<span class="badge bg-secondary"> Ngừng hoạt động </span>
									</c:otherwise>
								</c:choose></td>
						</c:if>

						<td>${video.category.cateName}</td>

						<%-- Nút sửa, xóa chỉ dành cho Admin --%>
						<c:if test="${not empty user and user.roleid == 1}">
							<td><a
								href="${pageContext.request.contextPath}/video/edit?videoId=${video.videoId}"
								class="btn btn-sm btn-warning"> Sửa </a> <a
								href="${pageContext.request.contextPath}/video/delete?videoId=${video.videoId}"
								class="btn btn-sm btn-danger"
								onclick="return confirm('Bạn có chắc chắn muốn xóa video này không?');">
									Xóa </a></td>
						</c:if>
					</tr>
				</c:forEach>

				<%-- Trường hợp không có video --%>
				<c:if test="${empty videos}">
					<tr>
						<c:choose>
							<c:when test="${not empty user and user.roleid == 1}">
								<td colspan="8" class="text-center">Hiện tại chưa có video
									nào.</td>
							</c:when>

							<c:otherwise>
								<td colspan="6" class="text-center">Hiện tại chưa có video
									nào.</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<%-- Phân trang --%>
	<c:if test="${totalPages > 1}">
		<nav>
			<ul class="pagination justify-content-center">

				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/video?page=${currentPage - 1}">
							Trang trước </a></li>
				</c:if>

				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item ${i == currentPage ? 'active' : ''}"><a
						class="page-link"
						href="${pageContext.request.contextPath}/video?page=${i}">
							${i} </a></li>
				</c:forEach>

				<c:if test="${currentPage < totalPages}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/video?page=${currentPage + 1}">
							Trang sau </a></li>
				</c:if>

			</ul>
		</nav>
	</c:if>

	<%-- Quay về trang chủ --%>
	<div class="mt-3">
		<a href="${pageContext.request.contextPath}/home"
			class="btn btn-outline-secondary"> Quay về trang chủ </a>
	</div>

</div>
