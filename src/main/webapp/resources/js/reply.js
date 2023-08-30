$(function(){
	
	let bnoValue = $('[name="bno"]').val() //페이지 내에서 name 속성이 "bno"인 요소의 값을 가져와 bnoValue 변수에 저장
	let replyContainer = $('.chat');	//클래스가 "chat"인 요소를 선택하여 replyContainer 변수(댓글 표시될 컨테이너)에 저장
	
	let pageNum = 1
	let paginationWrap = $('.pagination_wrap')
	
	let showReplyPage = function(replyCount){
		let endNum = Math.ceil(pageNum/10.0)*10	
		let startNum = endNum - 9
		let tempEndNum = Math.ceil(replyCount/10.0)
		
			let prev = startNum!=1
			let next = endNum<tempEndNum
			if(endNum>tempEndNum) endNum=tempEndNum	
		
		let pagination = '<ul class="pagination">';
		
		//이전 버튼
		if(prev){
			pagination += `<li class="page-item">
				<a class="page-link" href="${startNum-1}">이전</a><li>`
		}
			for(let pageLink=startNum; pageLink<= endNum ; pageLink++){ // 페이지 버튼
		let active = (pageNum==pageLink) ? 'active':''; // 현재페이지버튼 활성화
		pagination += `<li class="page-item ${active}">
				<a class="page-link" href="${pageLink}">${pageLink}</a></li>`
		}
		if(next){ // 다음 버튼 
			pagination += `<li class="page-item">
					<a class="page-link" href="${endNum+1}">다음</a></li>`
		}
		pagination += '</ul>'
		paginationWrap.html(pagination);
	}
	
	let showList = function(page){	//댓글 표시하는 함수이며, page 매개변수로 현재 페이지 번호 받음
	let param = {bno:bnoValue, page : page||1};
	console.log("param"+param);
	//요청에 필요한 파라미터를 객체 형태로 생성, bnoValue와 현재 페이지 번호인 page(기본값:1)를 포함
		
		replyService.getList(param,function(replyCount,list){	//댓글 목록 가져옴, 이 때 param은 콜백 함수 내에서 처리됨
			
			//댓글이 존재하지 않을 경우
			if(replyCount==0){
				replyContainer.html('등록된 댓글이 없습니다.');
				return
			}
			
			let replyList='';
			$.each(list,function(idx,elem){	//idx : 인덱스 값 //elem : 댓글 정보
				console.log(elem)
				/*
				<img class="rounded-circle" 
					      src="${ctxPath}/profile/display?fileName=${elem.uuid}_${elem.fileName}"
					       style="width: 100%">
				*/
			
				//댓글 목록을 reply list에 추가
				replyList += 
				`<li class="list-group-item" data-rno="${elem.rno}" >
					<div class="d-flex justify-content-between">
					  <div class="d-flex">
					    <div class="user_image mr-3" style="width: 75px">`
					    if (elem.uuid && elem.fileName) {
					    replyList +=
					   	`<img class="rounded-circle" 
					      src="${ctxPath}/profile/display?fileName=${elem.uuid}_${elem.fileName}"
					       style="width: 100%">`
					     } else {
					     replyList +=
					   	`<img class="rounded-circle" 
					      src="${ctxPath}/resources/images/profile.jpg" style="width: 100%">`
						}											       
					     replyList +=
					    `</div>
					    <div class="comment_wrap">
					      <div class="comment_info">
					        <span class="userName badge badge-pill badge-info mr-2">${elem.replyer}</span>
					        <span class="badge badge-dark">${elem.replyDate}</span>
					      </div>
					      <div class="comment_content py-2">${elem.reply}</div>
					    </div>
					  </div>`
					      if(memberId == elem.replyer) {
					        // 사용자 본인의 댓글인 경우 수정/삭제 가능
					        replyList +=
					        `<div class="reply_modify">
					            <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">변경</button>
					            <div class="dropdown-menu">						   
					                <a class="dropdown-item" href="modify">수정</a>
					                <a class="dropdown-item" href="delete">삭제</a>
					            </div>
					        </div>`;
					    } else if(auth.includes('ROLE_ADMIN')) {
					        // 관리자인 경우 삭제 가능
					        replyList +=
					        `<div class="reply_modify">
					            <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">변경</button>
					            <div class="dropdown-menu">						   
					                <a class="dropdown-item" href="delete">삭제</a>
					            </div>
					        </div>`;
					    }
					  replyList+=
					 `</div>
					</li>`				
			});
			replyContainer.html(replyList);	//생성한 댓글 목록을 replyContainer에 설정하여 화면에 표시
			showReplyPage(replyCount); //해당 게시물의 전체 댓글 개수에 따라서 페이지를 표시
		});
	}
	showList(1);
	//페이지가 로드될 때 showList 함수를 호출
	
	// 페이지 이동 이벤트
	paginationWrap.on('click','li a', function(e){
		e.preventDefault();
		pageNum = $(this).attr('href');
		showList(pageNum);
	});
	
	// 댓글 추가 
	$('.submit button').click(function(){
		let reply = { // 입력 데이터 객체  
			bno : bnoValue, // 게시물 번호 
			reply : $('.replyContent').val(), // 내용
			replyer : $('.replyer').html()	// 작성자
		}
		
		replyService.add(reply,function(result){ // 댓글 추가 처리 
			if(result=='success'){
				alert('댓글을 등록하였습니다.');
			} else {
				alert('댓글 등록 실패');
			}
			$('.replyContent').val(''); // 댓글입력창 초기화 
			showList(1); // 목록 갱신		
		});
	});
	
	// 댓글 수정 및 삭제 처리 
	// 이벤트 위임 
	$('.chat').on('click','.reply_modify a',function(e){
		e.preventDefault();// a태그  기본동작 금지
		let rno = $(this).closest('li').data('rno'); // 댓글 번호 가져오기
		let operation = $(this).attr('href');// 수정/삭제 동작 결정
		let replyer = $(this).closest('li').find('.userName').text();
		// 작성자가 일치하지 않거나 관리자가 아니면
		if(replyer!=memberId && !auth.includes('ROLE_ADMIN')){
			return;
		}
		if(operation=='delete'){ // 삭제 처리 
			replyService.remove(rno,function(result){
				if(result=='success'){
					alert(rno+'번 댓글을 삭제하였습니다.');
					showList(pageNum); // 목록 갱신
				} else {
					alert('댓글 삭제 실패');
				}
			});
			return; 
		}
	
	if(operation=='modify'){ // 수정처리 
				
		let replyUpdateForm = $('.replyWriterForm').clone(); // 댓글쓰기폼 복사.
		replyUpdateForm.attr('class','replyUpdateForm'); // 클래스명 변경
		let updateBtn = replyUpdateForm.find('.submit button').html('수정'); // 수정처리 버튼
		
		let listTag = $(this).closest('li'); // 현재 댓글의 li 태그를 찾음 
		let replyUpdateFormLength = listTag.find('.replyUpdateForm').length; // 댓글수정폼 존재 여부
		if(replyUpdateFormLength>0) { // 댓글수정폼이 추가되어있다면 
			listTag.find('.replyUpdateForm').remove(); // 기존의 수정폼 삭제
			$(this).html('수정'); // 취소 버튼을 수정버튼으로 
			$(this).next().show(); // 삭제 버튼 다시 보이게 
			return;
		} 
		
		// 조회 메소드 호출 :  댓글 조회 후 수정폼에 나타냄 
		replyService.get(rno,function(data){
			replyUpdateForm.find('.replyContent').val(data.reply);
			replyUpdateForm.find('.replyer').html(data.replyer);
		})	
		
		$(this).closest('li').append(replyUpdateForm); // 아래에 추가
		$(this).html('취소'); // 수정 버튼을 취소버튼으로 변경
		$(this).next().hide(); // 삭제 버튼 숨김
		
		updateBtn.click(function(){ // 수정 처리 이벤트 
			let replyVO = { // 수정 처리 메소드 매개값
				rno :  rno,
				reply : replyUpdateForm.find('.replyContent').val() 
			} 
			// 수정 처리  메소드 호출
			replyService.update(replyVO, function(result){
				alert(rno+'번 댓글을 수정하였습니다.');
				showList(pageNum); // 목록 갱신
			});
		})			
		return; 
		}
	});
})