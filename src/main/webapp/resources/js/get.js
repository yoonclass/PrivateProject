console.log('get.js');
$(function(){

	let bnoValue = $('[name="bno"]').val();
	$.getJSON(
		`${ctxPath}/book/getAttachList`,
		{bno : bnoValue},
		function(attachList){
			console.log(attachList);
	});
})

$.getJSON(`${ctxPath}/board/getAttachList`,{bno:bnoValue},function(attachList){
	let fileList = '';
	$(attachList).each(function(i,e){
		fileList += `<li class="list-group-item" data-uuid="${e.uuid}">
			<div class="float-left">`
		if(e.fileType){ // 이미지 파일인 경우 섬네일 표시
			let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName; 
			let encodingFilePath = encodeURIComponent(filePath);
			fileList +=`
				<div class="thumnail d-inline-block mr-3">
					<img alt="" src="${ctxPath}/files/display?fileName=${encodingFilePath}">	
				</div>				
			`
		} else {
			fileList +=` 
				<div class="thumnail d-inline-block mr-3" style="width:40px">
					<img alt="" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
				</div>`
		}
		fileList +=		
			`<div class="d-inline-block">
				${e.fileName}
			</div>
			</div>
			<div class="float-right">`
		if(e.fileType){
			fileList += `<a href="${e.uploadPath+"/"+e.uuid+"_"+e.fileName}" class="showIamge">원본보기</a>`
		}else{
			fileList += `<a href="${e.uploadPath+"/"+e.uuid+"_"+e.fileName}" class="download">다운로드</a>`
		} 
		fileList += `		
			</div>
		</li>`			
	});
	$('.uploadResultDiv ul').html(fileList);
});

