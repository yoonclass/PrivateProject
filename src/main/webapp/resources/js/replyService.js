console.log('Reply Moudule');

var replyService = {
	
	//등록
	add : function (reply, callback, error){
		$.ajax({
			type : 'post',
			url : `${ctxPath}/replies/new`,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utr-8",
			success : function(result){
				if(callback) callback(result);
			},
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	//댓글 목록 조회 
	//getList라는 함수를 정의하고, 서버로부터 댓글 목록을 조회하는 Ajax 요청을 보냅니다.
	getList : function(param, callback, error){
		let bno = param.bno;	
		let page = param.page || 1;
		
		$.ajax({
			type : 'get', 
			url : `${ctxPath}/replies/pages/${bno}/${page}`, 
			success : function(replyPageDTO){
				if(callback) callback(replyPageDTO.replyCount, replyPageDTO.list);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	//댓글 조회
	get : function (rno, callback, error){
		$.getJSON(`${ctxPath}/replies/${rno}`,function(data){
			if(callback) callback(data);	
		}).fail(function(xhr, status, er){
			if(error) error(er);
		});
	},
	
	//댓글 수정
	update : function (reply, callback, error){
		$.ajax({
			type : 'put', 
			url : `${ctxPath}/replies/${reply.rno}`, 
			data : JSON.stringify(reply), 
			contentType : "application/json; charset=utf-8", 
			success : function(result){
				if(callback) callback(result);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	remove : function(rno,callback,error){
		$.ajax({
			type : 'delete', 
			url : `${ctxPath}/replies/${rno}`, 
			success : function(result){
				if(callback) callback(result);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	}
}