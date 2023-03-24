<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>

	<div id="main">
		<div class="container my-5">
		
			<form action="/customerService/cusNotice/modify?cusNo=${notice.cusNo }" method="post">
				<input type="hidden" id="custNo" name="custNo" value="${notice.cusNo }">
				<input type="hidden" id="id" name="id" value="10"> <!-- 회원아이디 ${notice.id }/${freeBoard.id } -->
				
					<div class="mb-3">
					  <label for="title" class="form-label">제목</label>
					  <input type="text" class="form-control" id="title" name="title"
					  value="${notice.title }">
					</div>
					<div class="mb-3">
					  <label for="content" class="form-label">내용</label>
					  <textarea class="form-control" id="content" rows="3" name="content">${notice.content}</textarea>
					</div>
					
					<div class="mb-3">
					  <label for="formFileMultiple" class="form-label">첨부파일</label>
					  <input class="form-control" type="file" id="formFileMultiple" multiple>
					</div>
					
					
						<button type="submit" class="btn btn-outline-secondary" >수정완료</button>
						<button type="button" class="btn btn-outline-secondary deleteBtn" >삭제하기</button>
					
					<a href="/customerService/cusNotice/main?page=1&size=10&sorting=desc">
						<button type="button" class="btn btn-outline-secondary">목록으로</button>
					</a>				
			</form>
			
		</div>
	</div>
	
	<script type="text/javascript">
		const formObj = document.querySelector("form");
		
		document.querySelector(".deleteBtn").addEventListener("click", function(e){
			e.preventDefault();
			e.stopPropagation();
			
			if(window.confirm("정말 삭제하시겠습니까?")){
				formObj.action = "/customerService/cusNotice/delete?cusNo="+ ${notice.cusNo };
				formObj.method = "post";
				formObj.submit();
			}
		}, false);
	</script>
    
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>