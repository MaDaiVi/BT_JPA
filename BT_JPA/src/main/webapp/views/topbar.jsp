
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<style>
    .topbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px 25px;
        background-color: #f2f2f2;
        border-bottom: 1px solid #ddd;
    }

    .topbar a {
        text-decoration: none;
        color: #333;
        margin-left: 15px;
    }

    .topbar a:hover {
        color: #007bff;
    }

    .topbar .brand {
        font-size: 18px;
        font-weight: bold;
        margin-left: 0;
    }
</style>

<div class="topbar">
    <%-- Tên website --%>
    <div>
        <a class="brand" href="${pageContext.request.contextPath}/home">
            BAI TAP LAP TRINH WEB
        </a>
    </div>

    <div>
        <c:choose>
            <%-- Đã đăng nhập --%>
            <c:when test="${not empty sessionScope.account}">
                <span>
                    Xin chào, ${sessionScope.account.fullName}
                </span>

                <%-- Chỉ Admin mới được quản lý Category --%>
                <c:if test="${sessionScope.account.roleid == 1}">
                    <a href="${pageContext.request.contextPath}/category">
                        Quản lý danh mục
                    </a>
                </c:if>

                <%-- Các vai trò còn lại xem thông tin tài khoản --%>
                <c:if test="${sessionScope.account.roleid != 1}">
                    <a href="${pageContext.request.contextPath}/home">
                        Thông tin tài khoản
                    </a>
                </c:if>

                <a href="${pageContext.request.contextPath}/logout">
                    Đăng xuất
                </a>
            </c:when>

            <%-- Chưa đăng nhập --%>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">
                    Đăng nhập
                </a>

                <a href="${pageContext.request.contextPath}/register">
                    Đăng ký
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
