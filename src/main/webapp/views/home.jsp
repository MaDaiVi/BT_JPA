<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>Đăng nhập thành công!</h2>

    <p>Xin chào: ${user.fullName}</p>
    <p>Username: ${user.userName}</p>
    <p>Email: ${user.email}</p>
    <p>Vai trò: ${user.roleid}</p>

    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>

    <hr>

    <h2>10 video mới nhất</h2>

    <c:if test="${empty latestVideos}">
        <p>Chưa có video nào.</p>
    </c:if>

    <c:forEach items="${latestVideos}" var="video">
        <div>
            <h3>
                <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">
                    ${video.title}
                </a>
            </h3>

            <p>${video.description}</p>

            <p>Lượt xem: ${video.views}</p>
        </div>

        <hr>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/video">
        Xem tất cả video của tôi
    </a>

</body>
</html>