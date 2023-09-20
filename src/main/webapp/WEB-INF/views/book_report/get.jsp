<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
	
<div class="row">
	<div class="col-12">
		<div class="card">
			<ul class="pagination justify-content-center">
				<h1><li>${report.title}</li></h1>
			</ul>
			<div class="card-body">
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="10" name="content" readonly="readonly">${report.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value="${report.writer}" readonly="readonly"/>
				</div>
				
				<ul class="pagination justify-content-end">
					<li>작성 ${report.regDate}</li>
					<li>수정 ${report.updateDate}</li>
				</ul>
				<div class="getBtns d-flex justify-content-between">
					<div>
						<sec:authorize access="isAuthenticated() and principal.username==#report.writer or hasRole('ROLE_ADMIN')">
							<button data-oper='modify' class="btn btn-light modify">변경</button>
						</sec:authorize>
						<button data-oper='list' class="btn btn-info list">목록</button>
					</div>
					<div>		
						<sec:authorize access="isAuthenticated()">
							<a class="btn btn-outline-danger like">추천</a>
						</sec:authorize>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<h3 class="mt-5">댓글${report.replyCnt == 0 ? '': [report.replyCnt]}</h3>
<div class="row">
	<div class="col-12">
		<ul class="list-group chat">
			<li class="list-group-item" data-rno="댓글번호" >
				<div class="d-flex justify-content-between">
				  <div class="d-flex">
				    <div class="user_image mr-3" style="width: 75px">
				    
				      <img class="rounded-circle" src="${ctxPath}/resources/images/profile.jpg" style="width: 120px">
				    </div>
				    <div class="comment_wrap">
				      <div class="comment_info">
				        <span class="userName badge badge-pill badge-info mr-2">홍길동</span>
				        <span class="badge badge-dark">2023-06-20 09:30</span>
				      </div>
				      <div class="comment_content py-2">댓글 내용입니다. </div>
				    </div>
				  </div>
				  <div class="reply_modify">
				    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">변경</button>
				    <div class="dropdown-menu">						   
				      <a class="dropdown-item" href="modify">수정</a>
				      <a class="dropdown-item" href="delete">삭제</a>
				    </div>
				  </div>
				 </div>
			</li>
		</ul>		
	</div>
</div>

<div class="row mt-3">
	<div class="col-12 pagination_wrap"></div>
</div>

<!-- 댓글작성 -->	
<div class="my-3 replyWriterForm">
	<sec:authorize access="isAnonymous()">
		<textarea  rows="6" placeholder="로그인한 사용자만 댓글을 쓸 수 있습니다." readonly="readonly" 
			maxlength="400" class="replyContent form-control"></textarea>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<textarea  rows="6" placeholder="댓글을 작성해주세요" 
			maxlength="400" class="replyContent form-control" name="reply"></textarea>
		<div class="text-right">
			<div class="submit p-2">
				<span class="btn btn-outline-info col-2 replyer">${authInfo.memberId}</span>
				<button class="btn btn-outline-primary col-3 reply">등록</button>
			</div>
		</div>
	</sec:authorize>
</div>

<!-- 게시물번호 담음 -->
<form>
	<input type="hidden" name="bno" id="bno"  value="${report.bno}">
</form>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let getForm = $('form')
	
	// 수정+목록 페이지 이동 
	$('.getBtns button').click(function(){
		let operation = $(this).data('oper')
		let type = '${criteria.type}'
		let keyword = '${criteria.keyword}'
		
		getForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
			   .append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
			   .attr('method','get')
			
		if(type&&keyword){
		getForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}
		
		if(operation=='modify'){
			getForm.attr('action','${ctxPath}/book_report/modify')
		} else if (operation=='list'){
			getForm.attr('action','${ctxPath}/book_report/list')
		}
		getForm.submit()
	})
	
	//댓글 등록
    $('.reply').click(function() {
        var reply = $('[name="reply"]').val(); // 제목 입력란의 값 가져오기
        if (reply === "") {
            alert("댓글을 입력해주세요."); // 알림창 띄우기
            return false; // 등록 취소
        }
    });
	
	//추천
	$('.like').click(function(e){
		e.preventDefault();
		let bno = $('[name="bno"]').val();
		$.ajax({
			type : 'post',
			url : '${ctxPath}/book_report/like',
			data : {id : '${authInfo.getMemberId()}', bno : bno},
			success : function(message){
				alert(message)
				isLike() //요청 성공시 isLike 호출
			}
		})
	})
	
	//추천여부
	function isLike(){
		let bno = $('[name="bno"]').val();
		$.ajax({
			type : 'post',
			url : '${ctxPath}/book_report/islike',
			data : {id : '${authInfo.getMemberId()}', bno : bno},
			success : function(result){
				if(result){
					$('.like').html('추천취소')
				} else {
					$('.like').html('추천')
				}
			}
		})
	}
	if('${authInfo.getMemberId()}'!=''){	//이미 호출했는지 확인
		isLike();
	}
})
</script>

<!-- replyService : 댓글 CRUD  // reply.js : 댓글 목록 표시 -->
<script src="${ctxPath}/resources/js/replyService.js"></script>
<script src="${ctxPath}/resources/js/reply.js"></script>