<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<form class="modifyForm" action="${ctxPath}/book_report/modify" method="post">

						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name="title" value="${report.title}" />
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="10" name="content">${report.content}</textarea>
						</div>
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name="writer" value="${report.writer}" readonly="readonly"/>
						</div>
						<button type="button" data-oper='modify' class="btn btn-light">수정</button>
						<button type="button" data-oper='remove' class="btn btn-danger">삭제</button>
						<button type="button" data-oper='list' class="btn btn-info">목록</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="bno" id="bno" value="${report.bno}">
					</form>						
				</div>
			</div>
		</div>
	</div>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let modifyForm= $('.modifyForm')	
	console.log("modifyForm 작동");
	
	$('button').click(function(){
	let operation = $(this).data('oper')
		modifyForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
				  .append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
		if(operation=='list'){ // 목록으로
			modifyForm.attr('action','${ctxPath}/book_report/list')
					  .attr('method','get')
		} else if(operation=='remove'){ // 삭제처리
			modifyForm.attr('action','${ctxPath}/book_report/remove')
					  .attr('method','post')
		}
		modifyForm.submit();
	})
})
</script>