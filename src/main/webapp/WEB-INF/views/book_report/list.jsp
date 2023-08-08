<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
		<div class="col-12">
			<h1 class="page-header">독후감</h1>
		</div>
	</div>

<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">독후감 목록 페이지</div>
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
									<a href="${ctxPath}/book_report/get?bno=${report.bno}">${report.title}</a>
									</td>
								<td>${report.writer }</td>
								<td>1</td>
								<td><tf:formatDateTime value="${report.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<c:if test="${p.prev }">
						<a href="?pageNum=${p.startPage-p.displayPageNum}">[이전페이지]</a>
					</c:if>
					<c:forEach begin="${p.startPage}" end="${p.endPage }" var="pagelink">
						<a href="?pageNum=${pagelink}" class="${ pagelink == p.criteria.pageNum ? 'active':''}">[${pagelink}]</a>
					</c:forEach>
					<c:if test="${p.next }">
						<a href="?pageNum=${p.endPage+1}">[다음페이지]</a>
					</c:if>
					<div>
						<button id="regBtn" class="btn btn-xs btn-primary float-right">독후감 쓰기</button>
					</div>
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
	let result = "${result}"
	
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