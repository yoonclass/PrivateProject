<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
		<div class="col-12">
			<h1 class="page-header">도서 목록</h1>
		</div>
	</div>

<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">도서 목록 페이지</div>
				<div class="card-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>번호</th> 
								<th>제목</th>
								<th>좋아요</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="book">
							<tr>
								<td>${book.bno}</td>
									<td>
									<a class="move" href="${book.bno}">${book.title }</a>
									</td>
								<td>1</td>
								<td><tf:formatDateTime value="${book.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				
				
					<div>
						<button id="regBtn" class="btn btn-xs btn-primary float-right">도서 추가</button>
					</div>
				</div>
			</div>
		</div>
	</div>


<%@ include file="../includes/footer.jsp"%>

<!-- Modal -->
<div class="modal fade" id="listModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">등록 결과</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                등록이 완료되었습니다.
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<script>
$(function(){
	let result = "${result}"	//등록,수정,삭제 모델 객체
	let listForm = $('#listForm');	//페이지 이동 Form
	
	// 조회 페이지 이동 
	$('.move').click(function(e){
		e.preventDefault();
		let bnoValue = $(this).attr('href');
		listForm.append($('<input/>',{type : 'hidden', name : 'bno', value : bnoValue}))
				.attr('action','${ctxPath}/book_list/get')
				.submit();
	});
	
	//독후감 쓰기 버튼 누를 경우 등록 페이지로 이동
	$('#regBtn').on('click',function(){
		self.location = "${ctxPath}/book_list/register"
	})
	
// 모달
	checkModal(result)
	
	function checkModal(result){
		if(result=='')	return
		let operation = "${operation}"
		if(operation=='register'){		
			$('.modal-body').html('게시글 ' + result + '번이 등록되었습니다.')
		} else if (operation=='modify'){		
			$('.modal-body').html('게시글 ' + result + '번이 수정되었습니다.')
		} else if (operation=='remove'){		
			$('.modal-body').html('게시글 ' + result + '번이 삭제되었습니다.')
		} 
		$('#listModal').modal('show')
	}
})
</script>