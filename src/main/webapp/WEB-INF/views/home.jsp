<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>

<style>
  .h1-container {
    display: flex;
    justify-content: space-around;
    align-items: center;
    padding: 0 20px;
  }
  
  .h1-container h1 {
    border-bottom: 2px solid #000; /* 실선 스타일 설정 */
    padding-bottom: 10px; /* 실선과 텍스트 사이 여백 설정 */
  }
  
  /* 추가 스타일링: 좌우 정렬 */
  .table-container {
    display: flex;
    justify-content: space-between;
    padding: 20px;
  }
  
  /* 반응형 폭 조정 */
  .col-md-6 {
    flex-basis: 50%;
    max-width: 50%;
  }
  
   /* 추가 스타일링: 내용 들여쓰기 */
  .table-content {
    padding: 20px;
    
  }
  
  .table{
  	width: 50%;
  	margin: 0 auto;
  }

</style>
<div class="h1-container">
	<h1>최신 도서</h1>
	<h1>인기 독후감</h1>
</div>
<div class="table-container">
	<div class="col-md-6">
	<table class="table table-striped table-bordered table-hover">
		<c:forEach items="${latestBooks}" var="latestBooks">
			<tr>
				<td><strong>${latestBooks.title}</strong></td>
			</tr>
			<tr>
				<td>
					<div class="table-content">
					<c:choose>
						<c:when test="${fn:length(latestBooks.content) > 0}">
							${fn:substring(latestBooks.content, 0, 20)}... <!-- content를 20자까지만 표시하고 생략 표시 -->
						</c:when>
						<c:otherwise>
							${latestBooks.content} <!-- content가 20자 이하일 경우 전체 내용 표시 -->
						</c:otherwise>
					</c:choose>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>	
	</div>
	<div class="col-md-6">
	<table class="table table-striped table-bordered table-hover">
		<c:forEach items="${rankReports}" var="rankReports">
			<tr>
				<td><strong>${rankReports.title}</strong></td>
			</tr>
			<tr>
				<td>
					<div class="table-content">
					<c:choose>
						<c:when test="${fn:length(rankReports.content) > 0}">
							${fn:substring(rankReports.content, 0, 20)}... <!-- content를 20자까지만 표시하고 생략 표시 -->
						</c:when>
						<c:otherwise>
							${rankReports.content} <!-- content가 20자 이하일 경우 전체 내용 표시 -->
						</c:otherwise>
					</c:choose>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>	
	</div>
</div>
<%@ include file="includes/footer.jsp"%>
