console.log('register.js');
$(function(){
	let uploadResultList = [];
	
	let showUploadResult = function(attachList){
		let fileList = '';
		$.each(attachList,function(i,e){
			uploadResultList.push(e); // 첨부파일 배열의 요소로 추가
			fileList += `
			<li class="list-group-item" data-uuid="${e.uuid}">
				<div class="float-left">`
			if(e.fileType){ // 이미지 파일인 경우 섬네일 표시
				let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName; 
				let encodingFilePath = encodeURIComponent(filePath); // uri 인코딩 
				fileList +=`
					<div class="thumnail d-inline-block mr-3">
						<img alt="" src="${ctxPath}/files/display?fileName=${encodingFilePath}">	
					</div>`
			} else { // 이미지 파일이 아닐 때 
				fileList +=` 
					<div class="thumnail d-inline-block mr-3" style="width:40px">
						<img alt="" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
					</div>`
			}
			fileList +=		
					`<div class="d-inline-block">
						<a href="#">${e.fileName}</a>
					</div>
				</div>
				<div class="float-right">
					<a href="#" class="delete">삭제</a>
				</div>
			</li>`				
		});
		$('.uploadResultDiv ul').append(fileList);
	}

// 파일 업로드 이벤트 //파일이 변할 때 formData, Files 생성
	$('[type="file"]').change(function(){
		let formData = new FormData(); //HTML 폼 데이터를 쉽게 생성하고 전송할 수 있는 객체 FormData 객체생성
		let files = this.files;//파일 선택 요소에서 선택한 파일 목록 가져옴
		
		//반복문을 통해서 파일을 받아옴(register.jsp-input 태그의 name 속성과 동일)
		for(let f of files){	//선택한 파일 목록 순회하면서
			if(!checkExtension(f.size)) {
				$(this).val('');
				return;
			}
			formData.append('uploadFile', f);//formData에 선택한 파일목록 추가
		}							
		
		$.ajax({	//파일 업로드 요청
			url : `${ctxPath}/files/upload`, 
			type : 'post', 
			processData : false, 
			contentType : false, 
			data : formData, 
			dataType : 'json', 
			success : function(attachList){//서버 응답이 성공적으로 도착했을 때 실행할 콜백함수를 정의
				showUploadResult(attachList);	// 업로드 결과 표시
				console.log(uploadResultList);
			}
		})
	})
	
	$('.register').click(function(){
		let form = $('form');
		console.log(uploadResultList);
		if(uploadResultList.length>0){ // 첨부파일이 있으면 
			$.each(uploadResultList, function(i,e){
				let uuid = $('<input/>',{type:'hidden', name:`attachList[${i}].uuid`, value:`${e.uuid}`})
				let fileName = $('<input/>',{type:'hidden', name:`attachList[${i}].fileName`, value:`${e.fileName}`})
				let fileType = $('<input/>',{type:'hidden', name:`attachList[${i}].fileType`, value:`${e.fileType}`})
				let uploadPath = $('<input/>',{type:'hidden', name:`attachList[${i}].uploadPath`, value:`${e.uploadPath}`})
				form.append(uuid)
					.append(fileName)
					.append(fileType)
					.append(uploadPath)
			})
		}
		form.submit();	
	})
	
	// 첨부파일 삭제 
	$('.uploadResultDiv ul').on('click','.delete',function(e){
	e.preventDefault(); 
	let uuid = $(this).closest('li').data('uuid');
	let targetFileIdx = -1;
	let targetFile = null;
	
	$.each(uploadResultList,function(i,e){
		if(e.uuid == uuid){
			targetFileIdx = i; 
			targetFile = e;
			return;
		}		
	})
	
	if(targetFileIdx!=-1) uploadResultList.splice(targetFileIdx,1);
	console.log(uploadResultList);
	
	console.log('삭제 대상 파일 객체 :');	
	console.log(targetFile);
	
	$.ajax({
			type : 'post',
			url : `${ctxPath}/files/deleteFile`, 
			data : targetFile, 
			success : function(result){
				console.log(result);
			} 
	});

	$(this).closest('li').remove();
	})
})
