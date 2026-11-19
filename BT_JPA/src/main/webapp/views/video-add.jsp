
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm video</title>

<div class="container">
    <div class="card shadow-sm mx-auto" style="max-width: 700px;">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Thêm video</h4>
        </div>

        <div class="card-body">

            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/video/add"
                  method="post"
                  enctype="multipart/form-data">

                <div class="mb-3">
                    <label class="form-label">Mã video:</label>
                    <input type="text" name="videoId"
                           class="form-control"
                           maxlength="50" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Tiêu đề:</label>
                    <input type="text" name="title"
                           class="form-control"
                           maxlength="500" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Mô tả:</label>
                    <textarea name="description"
                              class="form-control"
                              rows="4"
                              maxlength="500"></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Poster (đường dẫn ảnh):</label>
                    <input type="text" name="poster"
                           class="form-control"
                           maxlength="500"
                           placeholder="uploads/categories/ten-anh.jpg">
                </div>

                <div class="mb-3">
                    <label class="form-label">Chọn video:</label>
                    <input type="file" name="videoFile"
                           class="form-control"
                           accept="video/*" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Danh mục:</label>
                    <select name="cateId" class="form-select" required>
                        <option value="">-- Chọn danh mục --</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.cateId}">
                                ${category.cateName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-plus-circle"></i> Thêm video
                    </button>

                    <a href="${pageContext.request.contextPath}/video"
                       class="btn btn-secondary">
                        Quay lại
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>
