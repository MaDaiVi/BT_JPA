<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Video theo danh mục</title>
</head>
<body>

	<%@ include file="topbar.jsp"%>

	<h2>Danh sách Video thuộc danh mục: ${category.cateName}</h2>

	<a href="${pageContext.request.contextPath}/category"> Quay lại
		danh sách danh mục </a> |

	<a href="${pageContext.request.contextPath}/video/add"> Thêm Video
	</a> |

	<a href="${pageContext.request.contextPath}/video"> Quản lý tất cả
		Video </a>

	<br>
	<br>

	<table border="1" cellpadding="10" cellspacing="0">
		<thead>
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
					<td>${video.title}</td>
					<td>${video.description}</td>
					<td>${video.poster}</td>
					<td>${video.views}</td>

					<td><c:choose>
							<c:when test="${video.active == 1}">
                        Đang hoạt động
                    </c:when>
							<c:otherwise>
                        Ngừng hoạt động
                    </c:otherwise>
						</c:choose></td>

					<!-- Thao tác -->
					<td><a
						href="${pageContext.request.contextPath}/video/edit?videoId=${video.videoId}">
							Sửa </a> | <a
						href="${pageContext.request.contextPath}/video/delete?videoId=${video.videoId}"
						onclick="return confirm('Bạn có chắc chắn muốn xóa video này?')">
							Xóa </a></td>
				</tr>
			</c:forEach>

			<c:if test="${empty videos}">
				<tr>
					<td colspan="7">Danh mục này chưa có video nào!</td>
				</tr>
			</c:if>
		</tbody>
	</table>

</body>
</html>