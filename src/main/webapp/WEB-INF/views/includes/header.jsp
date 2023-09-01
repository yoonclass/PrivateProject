<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl fmt 라이브러리에서 제공하는 날짜/시간, 다국어지원 등의 기능 사용  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 사용자 정의 태그 라이브러리를 JSP 페이지에서 사용할 수 있도록 선언 -->
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>

<!-- 현재 사용자의 인증 정보를 가져오기 위한 태그 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- <form:form>이나 <form:input>과 같이 form 접두사를 사용하여 Spring의 폼 태그를 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="authInfo"/>
	<sec:authentication property="principal.memberVO.authList" var="authList"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>What can you do here? :)</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>var ctxPath = '${ctxPath}'</script>
<script>
let duplicateLogin = '${duplicateLogin}'
let csrfHeaderName = "${_csrf.headerName}"; 
let csrfTokenValue = "${_csrf.token}"
let memberId = "${authInfo.memberId}";
let auth = "${authList}"

$(document).ajaxSend(function(e, xhr, options){
	xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
})

	if(duplicateLogin){
		alert(duplicateLogin);
	}

$(function(){
	$('.logout').click(function(e){
		e.preventDefault();
		//post 요청을 보낼 form을 동적으로 생성
		let form = $('<form>',{action:$(this).attr('href'), method:'post'});
		//CSRF 토큰 전송
		form.append($('<input>',{type:'hidden',name:'${_csrf.parameterName}', value:'${_csrf.token}'}))
			.appendTo('body')
			.submit();
	});
})

function checkExtension(fileSize){
	let maxSize = 314572800; // 300MB
	if(fileSize > maxSize) {
		alert('파일크기는 최대 300MB까지 업로드 가능합니다.');
		return false; 
	}
	return true;
}	
</script>
</head>

<body>
<nav class="navbar navbar-expand-sm bg-light">
	<div class="container-fluid">
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
      </ul>
      
    <div class="collapse navbar-collapse justify-content-end">  
   		<ul class="navbar-nav">
	    	<sec:authorize access="isAnonymous()">
		  	<li class="nav-item">
	    		<a class="nav-link" href="${ctxPath}/join/step1">회원가입</a>
			</li>
		  	<li class="nav-item">
		        <a class="nav-link" href="${ctxPath}/login">로그인</a>
		    </li>
		    </sec:authorize>
		    <sec:authorize access="isAuthenticated()">
		    <li class="nav-item">
			  <a class="nav-link" href="${ctxPath}/myPage">${authInfo.memberId}</a>
			</li>
	    	<li class="nav-item">
	       		<a class="nav-link logout" href="${ctxPath}/member/logout">로그아웃</a>
	     	</li>
	     	</sec:authorize>
		</ul>
		<table>
		    <thead>
		        <tr>
		            <th>카테고리 ID</th>
		            <th>카테고리 이름</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${categories}" var="category">
		            <tr>
		                <td>${category.categoryId}</td>
		                <td>${category.categoryName}</td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
    </div>
	</div>
</nav>

