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

    <h2>Danh sách Video</h2>

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
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>