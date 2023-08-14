<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container login_area d-flex justify-content-center align-items-center">
	<div class="w-50">
		<h1 class="text-center py-3">로그인 페이지</h1>
		<form method="post" action="${ctxPath}/member/login">
			<div class="form-group">
				<input type="text" class="form-control" name="memberId" value="${memberId}" placeholder="아이디">
			</div>
			<div class="form-group">
				<input type="password" class="form-control"  name="memberPwd" placeholder="비밀번호">
			</div>
			<c:if test="${not empty LoginFail}">
				<p style="color: red;font-size: 10px;">${LoginFail}</p>
			</c:if>
			<label>
				<input type="checkbox" name="remember-me" class="mr-2" id="rememberMeCheckbox" onclick="showRememberMeModal()">자동로그인
			</label>
			<button class="form-control btn btn-outline-primary" >로그인</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<!-- 			CSRF(Cross-Site Request Forgery) 보호를 위한 토큰을 HTML 폼에 포함 -->
		</form>
	</div>
</div>

<!-- 모달 창 -->
<div id="rememberMeModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="rememberMeModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rememberMeModalLabel">자동 로그인 설정</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>7일 동안 로그인이 유지됩니다.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<script>
function showRememberMeModal() {
    if (document.getElementById('rememberMeCheckbox').checked) {
        $('#rememberMeModal').modal('show');
    } else {
        // 로그인 처리
        document.querySelector('form').submit(); // 모달 없이 바로 폼 전송
    }
}
</script>

<%@ include file="../includes/footer.jsp"%>