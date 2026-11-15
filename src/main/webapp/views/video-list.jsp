<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Video</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>Danh sách Video</h2>

    <!-- Tìm kiếm video -->
    <form action="${pageContext.request.contextPath}/video" method="get">
        <input type="text" name="keyword"
               placeholder="Nhập tiêu đề video..."
               value="${keyword}">

        <button type="submit">Tìm kiếm</button>
        <a href="${pageContext.request.contextPath}/video">Làm mới</a>
    </form>

    <br>

    <!-- Nút thêm video -->
    <a href="${pageContext.request.contextPath}/video/add">
        <button type="button">Thêm video</button>
    </a>

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
                    <td>${video.title}</td>
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
                        <a href="${pageContext.request.contextPath}/video/edit?videoId=${video.videoId}">
                            Sửa
                        </a>

                        |

                        <a href="${pageContext.request.contextPath}/video/delete?videoId=${video.videoId}"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa video này không?');">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty videos}">
                <tr>
                    <td colspan="8">Không có video nào!</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>