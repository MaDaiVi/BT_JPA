
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
	<div class="container-fluid">

		<!-- Logo -->
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">
			<i class="bi bi-play-circle-fill"></i> BT_JPA
		</a>

		<!-- Nút thu gọn trên màn hình nhỏ -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#mainNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="mainNavbar">

			<!-- Menu bên trái -->
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/home"> Trang chủ </a></li>

				<c:if
					test="${not empty sessionScope.account
                              and sessionScope.account.roleid == 1}">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/video"> Quản lý video
					</a></li>
				</c:if>
			</ul>

			<!-- Khu vực tài khoản bên phải -->
			<ul class="navbar-nav">

				<c:choose>
					<%-- Chưa đăng nhập --%>
					<c:when test="${empty sessionScope.account}">
						<li class="nav-item"><a class="btn btn-outline-light me-2"
							href="${pageContext.request.contextPath}/login"> Đăng nhập </a></li>
						<li class="nav-item"><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/register"> Đăng ký </a>
						</li>
					</c:when>

					<%-- Đã đăng nhập --%>
					<c:otherwise>
						<li class="nav-item d-flex align-items-center me-3"><span
							class="navbar-text text-white"> Xin chào, <strong>${sessionScope.account.fullName}</strong>
						</span></li>
						<li class="nav-item"><a class="btn btn-outline-light"
							href="${pageContext.request.contextPath}/logout"> Đăng xuất </a>
						</li>
					</c:otherwise>
				</c:choose>s

			</ul>
		</div>
	</div>
</nav>
