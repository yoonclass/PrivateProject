<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<form action="${ctxPath}/book_report/modify" method="post">

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
						
						<input type="hidden" name="bno"  value="${report.bno}">
					</form>						
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let modifyReport= $('form')	
// 	console.log(modifyReport);
	$('button').click(function(){
		let operation = $(this).data('oper')

		if(operation=='list'){ // 목록으로
			self.location = '${ctxPath}/book_report/list'
			return
		} else if(operation=='remove'){ // 삭제처리
			modifyReport.attr('action','${ctxPath}/book_report/remove')
						.submit()
		}
		modifyReport.submit()
	})
})
</script>