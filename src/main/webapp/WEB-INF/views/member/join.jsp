<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<style>
.join_area {
	height: 50vh
}	
</style>

<div class="container join_area d-flex justify-content-center align-items-center">
	<div class="col-2">
		<ul class="list-group">
			<li class="list-group-item">이용약관</li>
			<li class="list-group-item active">회원가입</li>
		</ul>		
	</div>
	<div class="w-50">
		<h1 class="text-center py-3">회원가입</h1>
		<form:form action="${ctxPath}/member/join" modelAttribute="memberVO">
			<div class="form-group row">
				<div class="col-9">
					<form:input class="form-control" path="memberId" placeholder="아이디를 입력해주세요"/>
				</div>
				<div class="col-3">
					<button type="button" class="btn btn-outline-info form-control idCheck">ID중복확인</button>
				</div>
			</div>
			<div class="form-group">
				<form:input class="form-control"  path="memberName" placeholder="이름"/>
			</div>
			<div class="form-group row">
				<div class="col-9">
					<form:input class="form-control"  path="memberEmail" placeholder="이메일을 입력해주세요"/>
				</div>				
				<div class="col-3">
					<button type="button" class="btn btn-outline-info form-control emailCheck">이메일 인증</button>
				</div>
			</div>	
			<div class="form-group row">
				<div class="col-9">
					<form:input class="form-control"  path="memberEmail" placeholder="인증번호를 입력해주세요"/>
				</div>				
				<div class="col-3">
					<button type="button" class="btn btn-outline-info form-control emailNumCheck">확인</button>
				</div>
			</div>
			<div class="form-group">
				<form:password class="form-control"  path="memberPwd" placeholder="비밀번호를 입력해주세요"/>
			</div>
			<div class="form-group">
				<form:password class="form-control"  path="memberPwd" placeholder="비밀번호 확인"/>
			</div>
			<button type="button" class="form-control btn btn-outline-primary join" >회원가입</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
	</div>
</div>

<script>
$(function(){
	let idCheckFlag = false; //중복 체크값 false 기본값
	
	
	//아이디 중복확인 눌렀을 때
	$('.idCheck').click(function(){
		let idInput = $('#memberId')	//아이디 입력 필드
		let memberId =  $('#memberId').val()	//입력된 ID값 
		
		
		if(idInput.attr('readonly')){ // 이미 값이 입력된 경우 
			idInput.attr('readonly',false);//입력가능상태로 변경
			idInput.focus();
			$(this).html('ID중복확인');// 버튼 텍스트 변경
			idCheckFlag = false;//아이디 중복 체크 상태 초기화
			return; 
		}
		
		if(memberId=='') {	//값 입력 없는 경우
			alert('아이디를 입력하세요')
			return; 			
		}
		
		$.ajax({ // 중복검사 
			type : 'post', 
			url : '${ctxPath}/member/idCheck',
			data : { memberId : memberId },
			async : false,	//응답 받을 때까지 대기
			success : function(result){
				console.log(result);
				if(result){ // 사용할 수 있는 경우
					alert('사용할 수 있는 아이디 입니다.')
					idCheckFlag = true;
					$('.idCheck').html('변경');
					idInput.attr('readonly',true);//읽기 전용으로 변경
					
				} else { // 중복되는 경우 
					alert('사용할수 없는 아이디입니다.');
					idInput.focus();
				}
			}
		});
				
	});
	
	//회원가입할 때 ID중복 체크 했는지 확인
	$('.join').click(function(){
		if(!idCheckFlag){
			alert('ID 중복체크 바람');
			return;	//회원 가입 중지
		} 
		$('#memberVO').submit(); 
	});
});
</script>