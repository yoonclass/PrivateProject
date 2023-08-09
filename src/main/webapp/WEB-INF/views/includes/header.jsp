<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- jstl fmt 라이브러리에서 제공하는 날짜/시간, 다국어지원 등의 기능 사용  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 사용자 정의 태그 라이브러리를 JSP 페이지에서 사용할 수 있도록 선언 -->
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Site</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>var ctxPath = '${ctxPath}'</script>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">홈</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/book_report/list">독후감</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/book_list/list">도서목록</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/visitor/list">방명록</a>
        </li>
    </ul>
</nav>