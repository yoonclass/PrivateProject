<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="d-flex justify-content-center">
		<div class="w-50 my-5">
			<div class="jumbotron">
				<h3>내 정보</h3>
			</div>
			<div class="userImage d-flex justify-content-center my-3">
				<label for="imageUpload">
					<img class="rounded-circle" src="${ctxPath}/resources/images/google.png" style="width: 120px">
				</label>
				<input type="file" name="userImage" id="imageUpload" style=" display: none;width: 100%;height: 100%">
			</div>
			<form action="${ctxPath}/member/changePwd" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group">
					<input type="text" name="memberId" class="form-control form-control-lg" readonly="readonly" value="${vo.memberId}">
				</div>
				<div class="form-group">
					<input type="text" name="memberName" class="form-control form-control-lg" readonly="readonly" value="${vo.memberName}">
				</div>
				<div class="form-group">
					<input type="text" name="memberEmail" class="form-control form-control-lg" readonly="readonly" value="${vo.memberEmail}">
				</div>
				
				<!-- 비밀번호 변경 -->
				<div class="form-group">
				    현재 비밀번호 : <input type="password" class="form-control currentPwd" autocomplete="current-password">
				</div>
				<div class="form-group">
				    새 비밀번호 : <input type="password" class="form-control newPwd" autocomplete="new-password">
				</div>
				<div class="form-group">
				    비밀번호 재확인 : <input type="password" class="form-control confirmNewPwd" autocomplete="new-password">
				</div>
				<div class="form-group">
				    <button type="button" class="btn btn-outline-info btn-lg form-control changePwd">변경 완료</button>
				</div>
			</form>
		</div>
	</div>
	
</div> <!--  container end -->

<%@ include file="../includes/footer.jsp"%>

<script>
$(function(){
    $('.changePwd').click(function(){
        var memberId = $('[name="memberId"]').val();
        var memberName = $('[name="memberName"]').val();
        var memberEmail = $('[name="memberEmail"]').val();
        var currentPwd = $('.currentPwd').val();
        var newPwd = $('.newPwd').val();
        var confirmNewPwd = $('.confirmNewPwd').val();
        
        if (newPwd !== confirmNewPwd) {
            alert('새 비밀번호와 비밀번호 재확인이 일치하지 않습니다.');
            return;
        }
        
        $.ajax({
            type: 'post',
            url: '${ctxPath}/member/changePwd',
            data: {
                memberId: memberId,
                memberName: memberName,
                memberEmail: memberEmail,
                currentPwd: currentPwd,
                newPwd: newPwd
            },
            success: function(result){
                if (result === 'success') {
                    alert('비밀번호가 성공적으로 변경되었습니다.');
                    window.location.href = '${ctxPath}/';
                } else {
                    alert('변경 실패: ' + result);
                }
            },
            error: function(xhr, status, er){
                alert('변경 실패: ' + xhr.responseText);
            }
        });
    });
});
</script>