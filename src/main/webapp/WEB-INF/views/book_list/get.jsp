<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
	
<div class="row">
	<div class="col-12">
		<div class="card">
			<ul class="pagination justify-content-center">
				<h1><li>${book.title}</li></h1>
			</ul>
			<div class="card-body">
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="10" name="content" readonly="readonly">${book.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value="${book.writer}" readonly="readonly"/>
				</div>
				
				<ul class="pagination justify-content-end">
					<li>작성 ${book.regDate}</li>
				</ul>
				<div class="getBtns">
					<button data-oper='modify' class="btn btn-light modify">수정</button>
					<button data-oper='list' class="btn btn-info list">목록</button>	
				</div>					
			</div>
		</div>
	</div>
</div>

<!-- 게시물번호 담음 -->
<form>
	<input type="hidden" name="bno" id="bno"  value="${book.bno}">
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
			getForm.attr('action','${ctxPath}/book_list/modify')
		} else if(operation=='list'){
			getForm.attr('action','${ctxPath}/book_list/list')
		}
		getForm.submit()
	})
})
</script>
