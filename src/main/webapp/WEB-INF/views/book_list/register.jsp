<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">도서 등록</div>
				<div class="card-body">
					<form action="${ctxPath}/book_list/register" method="post">
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name="title" placeholder="제목을 입력하세요"/>
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="10" name="content" placeholder="내용을 입력하세요"></textarea>
						</div>
						<div class="form-group">
							<label>작성자 </label>
							<input class="form-control" name="writer" value="${authInfo.memberId}" readonly="readonly"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<button class="btn btn-light">등록</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row my-5">
	<div class="col-lg-12">
		<div class="card">
			<div class="card-header">
				<h4>파일 첨부</h4>
			</div>
			<div class="card-body">
				<div class="uploadDiv">
					<input type="file" name="uploadFile" multiple="multiple">
				</div>
				<div class="uploadResultDiv"> <!-- 파일업로드 결과 보여주기  -->
					<ul class="list-group"></ul>
				</div>
			</div> <!-- panel-body -->
		</div> <!-- panel end -->
	</div> <!-- col end -->
</div><!-- row end -->
<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
// 파일 업로드 이벤트 
	$('input[type="file"]').change(function(){
		let formData = new FormData(); 
		let files = this.files;
		
		for(let f of files){
			formData.append('uploadFile', f);
		}
		
		$.ajax({
			url : `${ctxPath}/files/upload`, 
			type : 'post', 
			processData : false, 
			contentType : false, 
			data : formData, 
			dataType : 'json', 
			success : function(result){
				console.log(result)
			}
		})
	})
})
</script>