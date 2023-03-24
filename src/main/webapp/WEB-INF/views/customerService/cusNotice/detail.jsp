<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style type="text/css">

</style>
<body>

	<!-- <div id="main" class=""> -->
		<div class="container my-5">
			<form action="#" method="post">
				
				
				<input type="hidden" id="custNo" name="custNo" value="${notice.cusNo }">
					<div class="mb-3">
					  <label for="title" class="form-label">제목</label>
					  <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" readonly="readonly"
					  value="${notice.title }">
					</div>
					<div class="mb-3">
					  <label for="content" class="form-label">내용</label>
					  <textarea class="form-control" id="content" rows="15" readonly="readonly" >${notice.content}</textarea>
					</div>
					
<!-- 					<div class="mb-3">
					  <label for="formFileMultiple" class="form-label">첨부파일</label>
					  <input class="form-control" type="file" id="formFileMultiple" multiple readonly="readonly">
					</div> -->
					<i class="bi bi-download"></i>
					
					<sec:authorize access="hasRole('ADMIN')">
						<a href="/customerService/cusNotice/modify?cusNo=${notice.cusNo }">
							<button type="button" class="btn btn-outline-secondary" >수정하기</button>
						</a>
					</sec:authorize>
					
					<a href="/customerService/cusNotice/main?page=1&size=10&sorting=desc">
						<button type="button" class="btn btn-outline-secondary">목록으로</button>
					</a>
				
			</form>
		</div>
	<!-- </div> -->
    
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>