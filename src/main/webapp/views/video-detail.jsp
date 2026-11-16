
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${video.title}</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>${video.title}</h2>

    <p>${video.description}</p>

    <c:if test="${not empty video.poster}">
        <img src="${pageContext.request.contextPath}/${video.poster}"
             alt="${video.title}"
             width="200">
    </c:if>

    <br><br>

    <c:choose>
        <c:when test="${not empty video.videoPath}">
            <video width="640" controls>
                <source
                    src="${pageContext.request.contextPath}/${video.videoPath}"
                    type="video/mp4">
                Trình duyệt không hỗ trợ phát video.
            </video>
        </c:when>

        <c:otherwise>
            <p>Video chưa có file để phát.</p>
        </c:otherwise>
    </c:choose>

    <p>Lượt xem: ${video.views}</p>

    <p>Danh mục: ${video.category.cateName}</p>

    <a href="${pageContext.request.contextPath}/home">Quay về trang chủ</a>
    |
    <a href="${pageContext.request.contextPath}/video">Video của tôi</a>

</body>
</html>
