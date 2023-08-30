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
					<label for="uploadFile">
						<img id="resultImage" class="rounded-circle" src="${ctxPath}/resources/images/profile.jpg" style="width: 200px">
					</label>
					
					<input type="file" name="userImage" id="uploadFile" style=" display: none; width: 100%;height: 100%">
				</div>
				<div class="form-group text-center">
				    <button class="btn btn-outline-info btn-xs form-control deleteProfile" style="width: 100px;">사진 삭제</button>
				</div>
			<form action="${ctxPath}/member/changePwd" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group">
					<input type="text" name="memberId" class="form-control form-control-lg" readonly="readonly" value="${vo.memberId}">
				</div>
				<div class="form-group">
					<input type="text" class="form-control form-control-lg" readonly="readonly" value="${vo.memberName}">
				</div>
				<div class="form-group">
					<input type="text" class="form-control form-control-lg" readonly="readonly" value="${vo.memberEmail}">
				</div>
				
				<!-- 비밀번호 변경 -->
				<div class="form-group">
				    현재 비밀번호 : <input type="password" name="currentPwd" class="form-control currentPwd" autocomplete="current-password">				
				   </div>
				<div class="form-group">
				    새 비밀번호 : <input type="password" name="newPwd" class="form-control newPwd" autocomplete="new-password">
				</div>
				<div class="form-group">
				    비밀번호 재확인 : <input type="password" class="form-control confirmNewPwd" autocomplete="new-password">
				</div>
				<div class="form-group">
				    <button class="btn btn-outline-info btn-lg form-control changePwd">변경 완료</button>
				</div>
			</form>
		</div>
	</div>
	
</div> <!--  container end -->

<%@ include file="../includes/footer.jsp"%>

<script>
$(function(){
	let uploadResultList = [];
	
	// 파일 업로드 이벤트 //파일이 변할 때 formData, Files 생성
	$('[type="file"]').change(function(){
		let formData = new FormData(); //HTML 폼 데이터를 쉽게 생성하고 전송할 수 있는 객체 FormData 객체생성
		let files = this.files;	//파일 선택 요소에서 선택한 파일 목록 가져옴
		
		
		//반복문을 통해서 파일을 받아옴(register.jsp-input 태그의 name 속성과 동일)
		for(let f of files){	//선택한 파일 목록 순회하면서
			formData.append('uploadFile', f);	//formData에 선택한 파일목록 추가
		}						
		
		$.ajax({	//파일 업로드 요청
			url : '${ctxPath}/profile/upload', 
			type : 'post', 
			processData : false, 
			contentType : false, 
			data : formData, 
			dataType : 'json', 
			success : function(result){//서버 응답이 성공적으로 도착했을 때 실행할 콜백함수를 정의
				const uuid = result[0].uuid
				const fileName = result[0].fileName
				$('#resultImage').attr('src','${ctxPath}/profile/display?fileName='+uuid+'_'+fileName)
			}
		});
	})
	
	// 프로필 이미지 초기화
    $(document).ready(function() {
        $.ajax({
            url: '${ctxPath}/profile/getProfileImage', // 새로 추가한 엔드포인트
            type: 'get',
            dataType: 'json',
            success: function(result){
                if (result.uuid && result.fileName) {
                    $('#resultImage').attr('src', '${ctxPath}/profile/display?fileName=' + result.uuid + '_' + result.fileName);
                } else {
                    // 기본 사진으로 교체
                    $('#resultImage').attr('src', '${ctxPath}/resources/images/profile.jpg');
                }
            },
        });
    });
	
	//사진 삭제
	$('.deleteProfile').click(function(){
		$.ajax({
	        type: 'post',
	        url: '${ctxPath}/profile/deleteProfileImage', // 새로 추가한 엔드포인트
	        success: function(result){
                // 사진 삭제 후, 기본 사진으로 교체
                $('#resultImage').attr('src', '${ctxPath}/resources/images/profile.jpg');
	        }
	    });
	})
	/*
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
                    alert('성공적으로 변경되었습니다.');
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
	*/
});
</script>