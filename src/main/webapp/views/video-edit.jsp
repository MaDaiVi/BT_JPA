
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa video</title>
</head>
<body>

    <%@ include file="topbar.jsp"%>

    <h2>Sửa video</h2>

    <p style="color: red;">${error}</p>

    <form action="${pageContext.request.contextPath}/video/edit"
          method="post"
          enctype="multipart/form-data">

        <input type="hidden" name="videoId" value="${video.videoId}">

        <label>Mã video:</label>
        <input type="text" value="${video.videoId}" disabled>
        <br><br>

        <label>Tiêu đề:</label>
        <input type="text" name="title" value="${video.title}"
               required maxlength="500">
        <br><br>

        <label>Mô tả:</label>
        <textarea name="description" maxlength="500">${video.description}</textarea>
        <br><br>

        <label>Poster (đường dẫn ảnh):</label>
        <input type="text" name="poster" value="${video.poster}"
               maxlength="500">
        <br><br>

        <label>Video hiện tại:</label>
        <p>${video.videoPath}</p>

        <label>Thay video mới (không bắt buộc):</label>
        <input type="file" name="videoFile" accept="video/*">
        <br><br>

        <label>Danh mục:</label>
        <select name="cateId" required>
            <c:forEach items="${categories}" var="category">
                <option value="${category.cateId}"
                    ${category.cateId == video.category.cateId ? 'selected' : ''}>
                    ${category.cateName}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <label>Trạng thái:</label>
        <select name="active">
            <option value="1" ${video.active == 1 ? 'selected' : ''}>
                Hoạt động
            </option>
            <option value="0" ${video.active == 0 ? 'selected' : ''}>
                Ngừng hoạt động
            </option>
        </select>
        <br><br>

        <button type="submit">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/video">Quay lại</a>

    </form>

</body>
</html>
