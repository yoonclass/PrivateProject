<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<style>
.join_area {
	height: 50vh
}	
</style>

<div class="container">
	
	<div class="row my-5">
		<div class="col-2">
		<ul class="list-group">
			<li class="list-group-item">이용약관</li>
			<li class="list-group-item active">회원가입</li>
		</ul>		
	</div>
	
	<div class="col-6 mx-auto">
		<h1 class="text-center py-3">회원가입</h1>
		<form:form action="${ctxPath}/member/join" modelAttribute="memberVO">
		
		<!-- ID,이름 -->
			<div class="form-group row">
			<div class="col-9">
				<form:input class="form-control" path="memberId" placeholder="아이디를 입력해주세요"/>
			</div>
			<div class="col-3">
				<button type="button" class="btn btn-outline-info form-control idCheck">ID중복확인</button>
			</div>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="memberName" placeholder="이름"/>
			</div>
			
		<!-- 이메일 -->
			<div class="form-group row">
			<div class="col-9">
				<form:input class="form-control" path="memberEmail" placeholder="이메일"/>
			</div>				
			<div class="col-3">
				<button type="button" class="form-control btn btn-outline-info" id="emailCheckBtn">이메일 인증</button>
			</div>
			</div>	
			
		<!-- 이메일 인증번호 -->
			<div class="form-group row">
			<div class="col-9">
				<input class="form-control" id="checkInput" placeholder="인증번호를 입력해주세요" disabled="disabled" maxlength="6"/>
			</div>				
			<div class="col-3">
				<button type="button" class="form-control btn btn-outline-primary InputCheckBtn">확인</button>
			</div>
			</div>
			
		<!-- 비밀번호 -->
			<div class="form-group">
				<form:password class="form-control " path="memberPwd" name="memberPwd" placeholder="비밀번호를 입력해주세요"/>
			</div>
			<div class="form-group">
				<input type="password" class="form-control confirmPwd" placeholder="비밀번호 확인">
			</div>
			
		<!-- 회원가입 -->
			<button type="button" class="form-control btn btn-outline-primary join" >회원가입</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
		</div>
	</div>
</div>
<script>
$(function(){
	
	//아이디 중복 검사
	let idCheckFlag = false;
		
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
	//=======================================
	//이메일 인증
	let authForm = $('#authForm')
	let code = null //인증번호
	let submitFlag = {	//이메일 주소, 인증 여부 판단
		email : '',
		isAuth : false,
	}
	
	$('#emailCheckBtn').click(function(){
		const email = $('#memberEmail').val(); //이메일
		const checkInput = $('#checkInput')
		
		if(email==''){	//이메일 입력 없는 경우
			alert('이메일을 입력하세요')
			return;
		}
		
		$.ajax({
			type: 'get',
			url : '${ctxPath}/mailCheck?email='+email, 
			success : function(result){
				submitFlag.email = email;
				checkInput.prop('disabled',false);
				code = result
				alert('인증번호가 전송되었습니다.')
			}
		})
	})
	
	//인증 일치 여부 확인
	$('.InputCheckBtn').click(function(){
		  const enteredCode = $('#checkInput').val(); // 입력한 인증번호
	        if (enteredCode === code) { // 입력한 인증번호가 저장된 코드와 일치하는 경우
	            submitFlag.isAuth = true;
	            alert('인증되었습니다.');
	            $('#checkInput').prop('disabled',true);
	        } else {
	            submitFlag.isAuth = false;
	            alert('인증번호가 일치하지 않습니다.');
	            $('#checkInput').val('')//입력값 초기화
	            $('#checkInput').prop('disabled',true);//입력 필드 비활성화
	        }
		})
		
	//회원가입할 때 ID중복 체크, 이메일 인증 했는지 확인
	$('.join').click(function(){
		
		//비밀번호 일치 확인
		var newPwd = $('#memberPwd').val();
	    var confirmPwd = $('.confirmPwd').val();
	        
	        if (newPwd !== confirmPwd) {
	            alert('비밀번호가 일치하지 않습니다.');
	            return;
	        }
	        
		if(!idCheckFlag){
			alert('ID 중복체크 바람');
			return;	//회원 가입 중지
		} 
		
	$('#email').val(submitFlag.email);
	      if (!submitFlag.isAuth) {
	          alert('인증되지 않았습니다.');
	          return;
	      }
		$('#memberVO').submit();
	});
	 // 회원가입 성공 시 메시지 출력
    const successMessage = "${message}";
    if (successMessage) {
        alert(successMessage);
    }
});
</script>