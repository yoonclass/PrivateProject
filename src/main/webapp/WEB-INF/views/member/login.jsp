<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container login_area d-flex justify-content-center align-items-center">
	<div class="w-50">
		<h1 class="text-center py-3">로그인 페이지</h1>
		<form method="post" action="${ctxPath}/member/login">
			<div class="form-group">
				<input type="text" class="form-control" name="memberId" placeholder="아이디">
			</div>
			<div class="form-group">
				<input type="password" class="form-control"  name="memberPwd" placeholder="비밀번호">
			</div>
			<button class="form-control btn btn-outline-primary" >로그인</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<!-- 			CSRF(Cross-Site Request Forgery) 보호를 위한 토큰을 HTML 폼에 포함 -->
		</form>
	</div>
</div>

<%@ include file="../includes/footer.jsp"%>