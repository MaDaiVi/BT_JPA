<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Video của tôi</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>Video của tôi</h2>

    <p>Tổng số video: ${videos.size()}</p>

    <c:if test="${user.roleid == 1}">
        <a href="${pageContext.request.contextPath}/video/add">
            <button type="button">Thêm video</button>
        </a>
    </c:if>

    <br><br>

    <table border="1" cellpadding="10" cellspacing="0">

        <thead>
            <tr>
                <th>Video ID</th>
                <th>Tiêu đề</th>
                <th>Mô tả</th>
                <th>Poster</th>
                <th>Lượt xem</th>
                <th>Trạng thái</th>
                <th>Danh mục</th>
                <th>Thao tác</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${videos}" var="video">

                <tr>
                    <td>${video.videoId}</td>

                    <td>
                        <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">
                            ${video.title}
                        </a>
                    </td>

                    <td>${video.description}</td>

                    <td>${video.poster}</td>

                    <td>${video.views}</td>

                    <td>
                        <c:choose>
                            <c:when test="${video.active == 1}">
                                Đang hoạt động
                            </c:when>
                            <c:otherwise>
                                Ngừng hoạt động
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>${video.category.cateName}</td>

                    <td>
                        <c:if test="${user.roleid == 1}">

                            <a href="${pageContext.request.contextPath}/video/edit?videoId=${video.videoId}">
                                Sửa
                            </a>

                            |

                            <a href="${pageContext.request.contextPath}/video/delete?videoId=${video.videoId}"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa video này không?');">
                                Xóa
                            </a>

                        </c:if>
                    </td>
                </tr>

            </c:forEach>

            <c:if test="${empty videos}">
                <tr>
                    <td colspan="8">Bạn chưa có video nào!</td>
                </tr>
            </c:if>

        </tbody>

    </table>

    <br>

    <!-- Phân trang -->
    <c:if test="${totalPages > 1}">

        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/video?page=${currentPage - 1}">
                Trang trước
            </a>
        </c:if>

        <c:forEach begin="1" end="${totalPages}" var="i">

            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/video?page=${i}">
                        ${i}
                    </a>
                </c:otherwise>
            </c:choose>

        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/video?page=${currentPage + 1}">
                Trang sau
            </a>
        </c:if>

    </c:if>

    <br><br>

    <a href="${pageContext.request.contextPath}/home">Quay về trang chủ</a>

</body>
</html>