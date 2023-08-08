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
					<button class="btn btn-light modify">수정페이지</button>
					<button class="btn btn-info list">목록으로</button>						
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 게시물번호 담음 -->
<form>
	<input type="hidden" name="bno"  value="${report.bno}">
</form>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let getBno = $('form');
	
	// 수정페이지 
	$('.modify').click(function(){
		getBno.attr('action','${ctxPath}/book_report/modify')
				.attr('method','get')
				.submit();
	});
	
	// 목록으로
	$('.list').click(function(){
		getBno.find('[name="bno"]').remove();
		getBno.attr('action','${ctxPath}/book_report/list')
				.attr('method','get')
				.submit();
	});
});
</script>