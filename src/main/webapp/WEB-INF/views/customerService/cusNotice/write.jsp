<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 	.uploadResult ul li img{
		width: 20px;
	} */
	.attach {
		width: 20px;
	}
	.img {
		width: 50px;
	}
	#deleteFileBtn {
		margin: 10px;
		border:1px;
	}
</style>
</head>
<body>

<div id="main">
	<div class="container my-5">
		<form action="/customerService/cusNotice/write" method="post">
			
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <input type="text" class="form-control" id="title" name="title" required="required"
				  placeholder="제목을 입력하세요">
				</div>
				
<!-- 				<div class="mb-3">
				  <label for="formFileMultiple" class="form-label uploadDiv">첨부파일</label>
				  <input class="form-control" type="file" name="uploadFile" id="formFileMultiple" multiple >
				</div> -->
							
				<div class="uploadResult">
					<ul></ul>
				</div>					
				
				
				<div class="mb-3">
				  <label for="content" class="form-label">내용</label>
				  <textarea class="form-control" id="content" name="content" rows="3" required="required"></textarea>
				</div>
				

							
				<!-- 연결만, 나중에 redirect처리 -->
				
				<button type="submit" class="btn btn-outline-secondary">완료</button>
<!-- 				<button type="button" class="btn btn-warning" id="uploadBtn" >
					파일업로드
				</button> -->
			

					
		</form>
	</div>
</div>
	
	
	
<script type="text/javascript">


	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		

		
	 	//파일용량체크
		var maxSize = 5242880; //5MB
		function checkSize(fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
		}
	
		//업로드후 보여줄 html 미리 복사
		var cloneObj = $(".uploadDiv").clone();
		//upload 결과 보여주는 부분
		var uploadResult = $(".uploadResult ul");
		
		var fileCallPath ="";
		
		
		function showUploadedFile(uploadResultArr){
			if(!uploadResultArr || uploadResultArr.length == 0){
				return;
			}
			
			var uploadUL = $(".uploadResult ul");
			var str = "";
			
			$(uploadResultArr).each(function(i, obj){
				
				//img type
				if(!obj.image){
					var fileCallPath = encodeURIComponent( 
							obj.uploadPath+ "/" + obj.uuid + '_' + obj.fileName);
					var fileStorePath = encodeURIComponent();
					var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
					
					str += "<li><div><a href=' download?fileName="+obj.fileName+" ' > "+
						   "<img class='attach' src='/img/icon/attachIcon.png'>"+ obj.fileName +"</a>"+
						   "<span data-file=\'"+fileCallPath+"\' data-type=file id='deleteFileBtn'> x </span>"+
					//str += "<button type='button' class='btn btn-circle align-center '>삭제</button><br>";
						   "<input type='hidden' name='fileCallPath' value=\'"+fileCallPath+"\'></div></li>"
					
					/* str += "<li><img class='attach' src='/img/icon/attachIcon.png'>"+ obj.fileName+ "</li>"; */
					
					
				}
				else {
					//str += "<li>"+ obj.fileName+ "</li>";
					var fileCallPath = encodeURIComponent( 
							obj.uploadPath+ "/" + obj.uuid + '_' + obj.fileName);
					
					str += "<li><img class='img' src='/display?fileName="+fileCallPath+"'><li>";				
				}
				
			});
				uploadUL.append(str);
		}
		
		
		
		
 		$('#uploadBtn').on("click", function(e){
			
			//선택한 파일이 없을 경우

			e.preventDefault();
			e.stopPropagation();
			if(window.confirm("파일을 업로드 하시겠습니까?")){
			
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			//add File Data to formData
			for (var i = 0; i < files.length; i++){

				formData.append("uploadFile", files[i]);
			} 
			
			
			
		$.ajax({
				url: '/uploadAjaxAction',
				processData: false,
				contentType: false,
				data: formData,
				type: "POST",
					dataType: 'json',
					success: function(result){
						console.log(result);
						alert("파일 업로드 완료");
						showUploadedFile(result);
						$(".uploadDiv").html(cloneObj.html());
					}//$.ajax
			});
		
		}//if(window.confirm("파일을 업로드 하시겠습니까?")) end
		
		
		
		
	});
	
	
/* 	$("ipnut[type='file']").change(function(e){
		alert('file input changed');
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		for(var i = 0; i < files.length; i++){
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,data:
			formData, type: 'POST',
			
				dataType: 'json',
				success: function(result){
					console.log(result);
					alert("파일 업로드 완료");
					showUploadedFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}//$.ajax
		});
	}); */
	
	$(".uploadResult").on("click", "span", function(e){
		console.log("파일 삭제");
		var targetFile = $(this).data("file");
		console.log(targetFile);
	
	
		var type = $(this).data("type");
		var targetLi = $(this).closest("li");
		
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type:type},
				dataType: 'text',
				type: 'POST',
					success: function(result){
						alert(result);
						targetLi.remove();
					}
			});
	});

});


</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>