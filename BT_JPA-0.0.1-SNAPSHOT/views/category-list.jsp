
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
</head>

<body>

    <%@ include file="topbar.jsp"%>

    <h2>Danh sách danh mục</h2>

    <!-- Tìm kiếm danh mục -->
    <form action="${pageContext.request.contextPath}/category" method="get">
        <input type="text" name="keyword"
               placeholder="Nhập tên danh mục cần tìm..."
               value="${keyword}">

        <button type="submit">Tìm kiếm</button>

        <a href="${pageContext.request.contextPath}/category">
            Làm mới
        </a>
    </form>

    <br>

    <a href="${pageContext.request.contextPath}/category/add">
        Thêm danh mục
    </a>

    <br><br>

    <!-- Bảng danh sách Category -->
    <table border="1" cellpadding="10" cellspacing="0">

        <thead>
            <tr>
                <th>ID</th>
                <th>Tên danh mục</th>
                <th>Hình ảnh</th>
                <th>Thao tác</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.cateId}</td>

                    <td>${category.cateName}</td>

                    <!-- Hiển thị hình ảnh -->
                    <td>
                        <c:if test="${not empty category.icons}">
                            <img
                                src="${pageContext.request.contextPath}/image?fname=${category.icons}"
                                width="80"
                                height="80"
                                style="object-fit: cover; border-radius: 5px;">
                        </c:if>

                        <c:if test="${empty category.icons}">
                            Chưa có ảnh
                        </c:if>
                    </td>

                    <!-- Các thao tác -->
                    <td>
                        <a href="${pageContext.request.contextPath}/category/edit?id=${category.cateId}">
                            Sửa
                        </a>

                        |

                        <a href="${pageContext.request.contextPath}/category/delete?id=${category.cateId}"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?')">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty categories}">
                <tr>
                    <td colspan="4">Chưa có danh mục nào!</td>
                </tr>
            </c:if>

        </tbody>
    </table>

</body>
</html>
