<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/customerService/freeBoard/write.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
	$(function(){
		var options = $('#categorySelect').find("option");
		options.each(function(){
			var optionVal = $(this).val();
			if(optionVal=='${freeboard.category}'){
				$(this).attr("selected","selected");
			}
		});
	});
	var seqNum = ${maxClassSeqNum};
	function submitChk(category){
		$("#content").val($("#boardContentDiv").html());
		if(category==""){
			alert('카테고리를 선택해주세요.');
			$("#categorySelect").focus();
			return false;
		} else if($("#content").val()==""){
			alert('내용을 입력해주세요.');
			$("#boardContentDiv").focus();
			return false;
		} else {
			if(confirm("게시글을 수정하시겠습니까?")) return true;
			else return false;
		}
			
	}
	function ajaxFileUpload(){
		if($("#formFile").val()){
			if($("#formFile")[0].files[0].size > 3 * 1024 * 1024){
				alert("첨부파일 사이즈는 3MB 이내로 등록 가능합니다.");
				$("#formFile").val('');
			} else {
			const formData = new FormData();
				formData.append("image", $("#formFile")[0].files[0]);
				$.ajax({
					type		: "POST",
					url			: "/customerService/freeboard/ajaxFileUpload",
					processData	: false,
				    contentType	: false,
				    data		: formData,
				    dataType	: 'json',
				    success: function(data){
				    	var filename = "/images/freeboardImg/"+data[0];
				    	var imgClass = "imgSet"+seqNum;
				    	var contentImg = "<img alt='본문이미지' src="+filename+" class="+imgClass+">";
				    	$("#boardContentDiv").append(contentImg);
				    	var imgMod ="<div class='"+imgClass+" btn btn-outline-secondary'><a onclick='imginsert(\""+filename+"\",\""+imgClass+"\")'>"+data[1]+"</a> "+
				    				"<img src='/img/freeboard/x버튼.png' onclick='delImgs(\""+data[0]+"\",\""+imgClass+"\")' class='freeboardXbutton'>"+
				    				"<input type='hidden' value=\""+data[0]+"\" name='imgPath'><input type='hidden' value=\""+imgClass+"\" name='imgClass'></div> ";
				    	$("#imageList").append(imgMod);
				    	seqNum+=1;
				    }
				});
			}
		}
	}
	function imginsert(filename,name){
		var contentImg = "<img alt='본문이미지' src="+filename+" class="+name+">";
		$("#boardContentDiv").append(contentImg);
	}
	function delImgs(filename,name){
		$("."+name).remove();
		$.ajax({
			type		: "POST",
			url			: "/customerService/freeboard/ajaxFileDelete",
		    data		: {filename:filename}
	    });
	}
	function delModImg(filename,name){
		$("."+name).remove();
		$("#imageList").append("<input type='hidden' value=\'"+filename+"\' name='modImgPath'>");
		
	}
	function beforeUnload(){
		var formData = $("#contentForm").serialize();
		$.ajax({
			type		: "POST",
			url			: "/customerService/freeboard/ajaxUnloadFileDelete",
			data		: formData
		});
	}
	$(document).on("submit", "form", function(event){
        window.onbeforeunload = null;
	});
</script>
</head>
<body onbeforeunload="beforeUnload()">
<div class="mainContents">
	<div class="container">
		<br>
		<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
			<span class="fs-5 fw-semibold">게시글수정</span>
		</a>
		<form action="/customerService/freeboard/modify" method="post" id="contentForm" onsubmit="return submitChk(category.value)">
			<div class="mb-3">
			<input type="hidden" name="id" value="${freeboard.id }">
			<input type="hidden" name="freeboardNo" value="${freeboard.freeboardNo }">
			<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" required="required" value="${freeboard.title }">
			</div>
			<select class="form-select my-3" aria-label="카테고리 선택" id="categorySelect" name="category">
			  	<c:forEach var="category" items="${comm }">
			  		<c:choose>
				  		<c:when test="${category.mcd==999 }">
				  		</c:when>
				  		<c:when test="${category.mcd==1 }">
				  			<c:choose>
					  			<c:when test="${roleAdmin }">
						  			<option value="${category.commDetail }" >${category.commDetail }</option>
					  			</c:when>
				  			</c:choose>
				  		</c:when>
				  		<c:otherwise>
				  			<option value="${category.commDetail }">${category.commDetail }</option>
				  		</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			</select>
			<div class="mb-3">
			</div>
			<div class="buttons mb-3">
				<input type="button" class="BOLD btn btn-outline-secondary" value="Bold" onclick="document.execCommand('bold')" /> 
				<input type="button" class="ITALIC btn btn-outline-secondary" value="Italic" onclick="document.execCommand('Italic')" />
				<input type="button" class="UNDERBAR btn btn-outline-secondary" value="underline" onclick="document.execCommand('Underline')" />
				<button type="button" class="aignLeft btn btn-outline-secondary" onclick="document.execCommand('justifyleft')">
					<i class="fas fa-align-left">좌</i>
				</button>
				<button type="button" class="aignCenter btn btn-outline-secondary" onclick="document.execCommand('justifycenter')">
					<i class="fas fa-align-center">중앙</i>
				</button>
				<button type="button" class="aignRight btn btn-outline-secondary" value="오른쪽 정렬" onclick="document.execCommand('justifyright')">
					<i class="fas fa-align-right">우</i>
				</button>
			</div>
				<div contenteditable="true" class="boardContentDiv form-control mb-3" id="boardContentDiv">${freeboard.content }</div>
				<textarea class="form-control" id="content" name="content" style="display:none;"></textarea>
			<div class="mb-3">
				<input class="form-control mb-3" type="file" id="formFile" onchange="ajaxFileUpload()" accept=".gif, .jpg, .png">
				<div id="imageList">
					<c:forEach var="img" items="${freeboardImgs }">
						<div class='${img.imgClass } btn btn-outline-secondary'>
							<a onclick="imginsert('/images/freeboardImg/${img.imgPath}','${img.imgClass }')">${img.orgImg}</a>
			    			<img src='/img/freeboard/x버튼.png' onclick="delModImg('${img.imgPath}','${img.imgClass }')" class='freeboardXbutton'>
			    		</div>
					</c:forEach>
				</div>	
			</div>
			<button type="submit" class="btn btn-outline-secondary">완료</button>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>