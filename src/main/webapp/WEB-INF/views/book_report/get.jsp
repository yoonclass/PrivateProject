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
				<div class="getBtns">
					<button data-oper='modify' class="btn btn-light modify">수정페이지</button>
					<button data-oper='list' class="btn btn-info list">목록으로</button>	
				</div>					
			</div>
		</div>
	</div>
</div>

<h3 class="mt-5">댓글</h3>
<div class="row">
	<div class="col-12">
		<ul class="list-group chat">
			<li class="list-group-item" data-rno="댓글번호" >
				<div class="d-flex justify-content-between">
				  <div class="d-flex">
				    <div class="user_image mr-3" style="width: 75px">
				      <img class="rounded-circle" src="${ctxPath}/resources/images/userImage.png" style="width: 100%">
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
	<textarea  rows="6" placeholder="댓글을 작성해주세요" 
		maxlength="400" class="replyContent form-control"></textarea>
	<div class="text-right">
		<div class="submit p-2">
			<span class="btn btn-outline-info col-2 replyer">홍길동</span>
			<button class="btn btn-outline-primary col-3">등록</button>
		</div>
	</div>
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
		getForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
			   .append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
			   .attr('method','get')
			
		if(operation=='modify'){
			getForm.attr('action','${ctxPath}/book_report/modify')
		} else if(operation=='list'){
			getForm.attr('action','${ctxPath}/book_report/list')
		}
		getForm.submit()
	})
})
</script>

<!-- replyService : 댓글 CRUD  // reply.js : 댓글 목록 표시 -->
<script src="${ctxPath}/resources/js/replyService.js"></script>
<script src="${ctxPath}/resources/js/reply.js"></script>