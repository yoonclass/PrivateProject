<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-12">
		<h1> </h1>
	</div>
</div>

<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">독후감 목록</div>
				<div class="card-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>번호</th> 
								<th>제목</th>
								<th>작성자</th>
								<th>좋아요</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="report">
							<tr>
								<td>${report.bno}</td>
									<td>
									<a class="move" href="${report.bno}">${report.title }
									${report.replyCnt == 0 ? '': [report.replyCnt]}</a>
									</td>
								<td>${report.writer }</td>
								<td>${report.likeHit}</td>
								<td><tf:formatDateTime value="${report.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				
					<!-- 페이징 -->
					<ul class="pagination justify-content-center">
						<!-- 시작 페이지 숫자에서 한 페이지에 표시되는 게시물 수만큼 차감하여 이동  -->
						<c:if test="${page.prev}">
							<li class="page-item">
								<a class="page-link" href="${page.startPage-page.displayPageNum}">이전페이지</a>
							</li>
						</c:if>
						<!-- pagelink라는 값이 있을 때 pagelink로 이동 -->
						<c:forEach begin="${page.startPage}" end="${page.endPage }" var="pagelink">
							<li class="page-item ${pagelink == page.criteria.pageNum ? 'active':''}">
								<a class="page-link" href="${pagelink}">${pagelink}</a>
							</li>
						</c:forEach>
						<!--  끝페이지에서 1을 더함 : 다음 페이지 번호-->
						<c:if test="${page.next }">
							<li class="page-item">
								<a class="page-link" href="?pageNum=${page.endPage+1}">[다음페이지]</a>
							</li>
						</c:if>
					</ul>
					<sec:authorize access="isAuthenticated()">
					<div>
						<button id="regBtn" class="btn btn-xs btn-primary float-right">독후감 쓰기</button>
					</div>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>

<form id="listForm" action="${ctxPath}/book_report/list" method="get">
	<input type="hidden" name="pageNum" value="${page.criteria.pageNum}">
	<input type="hidden" name="amount" value="${page.criteria.amount}">
</form>

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
	
	//페이지 이동
	$('.pagination a').click(function(e){	//페이지 모든 a 링크에 이벤트 추가
		e.preventDefault();	//기본동작 중지
		let pageNum = $(this).attr('href');	//href 속성값 pageNum에 저장
		listForm.find('input[name="pageNum"]').val(pageNum)
		listForm.submit();
	})
	
	// 조회 페이지 이동 
	$('.move').click(function(e){
		e.preventDefault();
		let bnoValue = $(this).attr('href');
		listForm.append($('<input/>',{type : 'hidden', name : 'bno', value : bnoValue}))
				.attr('action','${ctxPath}/book_report/get')
				.submit();
	});
	
	//독후감 쓰기 버튼 누를 경우 등록 페이지로 이동
	$('#regBtn').on('click',function(){
		self.location = "${ctxPath}/book_report/register"
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