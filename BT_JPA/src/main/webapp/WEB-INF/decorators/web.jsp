<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title><sitemesh:write property="title" /></title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
	rel="stylesheet">

<sitemesh:write property="head" />

<style>
body {
	min-height: 100vh;
	display: flex;
	flex-direction: column;
	align-items: stretch;
	background-color: #f8f9fa;
}

nav.navbar {
	width: 100%;
}

footer {
	width: 100%;
}

main {
	flex: 1;
	padding: 24px 0;
}

footer {
	background-color: #212529;
	color: white;
	text-align: center;
	padding: 16px;
}
</style>
</head>

<body>

	<%@ include file="/commons/web/header.jsp"%>

	<main class="container">
		<sitemesh:write property="body" />
	</main>

	<%@ include file="/commons/web/footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>