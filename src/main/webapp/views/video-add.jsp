<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm video</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>Thêm video</h2>

    <p style="color: red;">${error}</p>

    <form action="${pageContext.request.contextPath}/video/add"
          method="post">

        <label>Mã video:</label>
        <input type="text" name="videoId" required maxlength="50">
        <br><br>

        <label>Tiêu đề:</label>
        <input type="text" name="title" required maxlength="500">
        <br><br>

        <label>Mô tả:</label>
        <textarea name="description" maxlength="500"></textarea>
        <br><br>

        <label>Poster:</label>
        <input type="text" name="poster" maxlength="500">
        <br><br>

        <label>Danh mục:</label>
        <select name="cateId" required>
            <c:forEach items="${categories}" var="category">
                <option value="${category.cateId}">
                    ${category.cateName}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <button type="submit">Thêm</button>

        <a href="${pageContext.request.contextPath}/video">
            Quay lại
        </a>

    </form>

</body>
</html>