<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/customerService/freeBoard/main.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
	function categorySearchAct(searchString){
		location.href="/customerService/freeboard/search?searchOption=category&searchString="+searchString;
	}
	$(function(){
		$('.AdminIcon').parent().parent().attr('class','adminTr');
		var options = $('.searchOption').find("option");
		options.each(function(){
			var optionVal = $(this).val();
			if(optionVal=='${freeboardSearchSet.searchOption}'){
				$(this).attr("selected","selected");
			}
		});
	});
</script>
</head>
<body>
	<div class="mainContents">
		<div class="container">
			<br>
			<a href="/customerService/freeboard/main" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold">자유게시판</span>
			</a>
    		<form action="/customerService/freeboard/search">
		    	<div class="searchArea navbar navbar-light bg-light LMRposition">
		    		<div class="categorySelect">
		    			<label>카테고리 이동</label><br>
					    <select class="custom-select" id="categorySearch" name="categorySearch" onchange="categorySearchAct(categorySearch.value)">
							<option value="">전체</option>
							<c:forEach var="category" items="${categoryList }">
						  		<c:choose>
							  		<c:when test="${category.mcd==999 }">
							  			<option value="" style="display: none;">게시판선택</option>
							  		</c:when>
							  		<c:when test="${freeboardSearchSet.searchString==category.commDetail }">
							  			<option value="${category.commDetail }" selected="selected">${category.commDetail }</option>
							  		</c:when>
							  		<c:otherwise>
							  			<option value="${category.commDetail }">${category.commDetail }</option>
							  		</c:otherwise>
						  		</c:choose>
						  	</c:forEach>
					  	</select>
					</div>
					<div>
						<c:choose>
							<c:when test="${freeboardSearchSet.searchString==''}">
								<h1><b>전체</b></h1><h5> 게시글</h5>
							</c:when>
							<c:when test="${freeboardSearchSet.searchOption=='category' }">
								<h1><b>${freeboardSearchSet.searchString }</b></h1><h5> 카테고리</h5>
							</c:when>
							<c:otherwise>
								<h1><b>"${freeboardSearchSet.searchString }"</b></h1><h5>에 대한 검색 결과<b>[${pageMaker.total }건]</b></h5>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="keywordSearch">
						<a class="navbar-brand">검색하기</a><br>
						<select class="searchOption" name="searchOption" >
							<option value="" selected="selected">전체</option>
							<option value="username">작성자</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="regdate">작성일</option>
						</select>
						<input class="searchString" type="text" placeholder="Search" name="searchString">
						<button type="submit" class="btn btn-outline-secondary">Search</button>
					</div>
				</div>
			</form>
	        <table class="table caption-top">
	            <thead>
	              <tr>
	               <th scope="col" style="width: 5%;">#</th>
	                <th scope="col" style="width: 45%;">제목</th>
	                <th scope="col" style="width: 15%;">작성자</th>
	                <th scope="col" style="width: 7%;">추천수</th>
	                <th scope="col" style="width: 8%;">조회수</th>
	                <th scope="col" style="width: 20%;">작성일</th>
	              </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var="list" items="${latestNotices }">
						<tr style="background-color: #F0F0F0;">
							<th scope="row">${list.freeboardNo}</th>
							<td>
								<a href="/customerService/freeboard/search?searchOption=category&searchString=${list.category}" class="categoryLink">[${list.category}]</a>
								<a href="/customerService/freeboard/detail?freeboardNo=${list.freeboardNo}">${list.title}</a>
							</td>
							<td>
								${list.username }
								<c:if test="${list.role == 'ROLE_ADMIN' }">
									<img alt="관리자" src="/img/freeboard/free-icon-crown-931979.png" class='AdminIcon'>
								</c:if>
							</td>
							<td>${list.recommand}</td>
							<td>${list.viewCount }</td>
							<td>${list.regdate}</td>
						</tr>
					</c:forEach>
					<c:forEach var="list" items="${freeList }">
						<tr>
							<th scope="row">${list.freeboardNo}</th>
							<td>
								<a href="/customerService/freeboard/search?searchOption=category&searchString=${list.category}" class="categoryLink">[${list.category}]</a>
								<a href="/customerService/freeboard/detail?freeboardNo=${list.freeboardNo}">${list.title} </a>
							</td>
							<td>
								${list.username }
								<c:if test="${list.role == 'ROLE_ADMIN' }">
									<img alt="관리자" src="/img/freeboard/free-icon-crown-931979.png" class='AdminIcon'>
								</c:if>
							</td>
							<td>${list.recommand}</td>
							<td>${list.viewCount }</td>
							<td>${list.regdate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	        <nav aria-label="Page navigation example" class="LMRposition">
	        	<div class="LMRpositionLeft"></div>
	            <ul class="pagination justify-content-center">
				<c:if test="${pageMaker.startPage > pageMaker.pageBlock }">
					<a class="page-link" href="<%=context %>/customerService/freeboard/searchPage?pageNum=${pageMaker.startPage-pageMaker.pageBlock }"  aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
					<span class="sr-only">Previous</span>
					</a>
				</c:if>
		        <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
		            <li class="page-item">
		            	<a class="page-link" href="<%=context %>/customerService/freeboard/searchPage?pageNum=${num }">${num }</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.endPage < pageMaker.totalPage }">
					<a class="page-link" href="<%=context %>/customerService/freeboard/searchPage?pageNum=${pageMaker.startPage+pageMaker.pageBlock }" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
						<span class="sr-only">Next</span>
					</a>
				</c:if>
	            </ul>
				<a  href="/customerService/freeboard/write">
					<sec:authorize access="isAuthenticated()">
						<button type="button" class="btn btn-outline-secondary justify-content-end">글쓰기</button>
					</sec:authorize>
				</a>
			</nav>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		if(${DeletePostResult!=null}){
			if(${DeletePostResult==0}) alert("게시글 삭제에 실패했습니다.");
			else alert("게시글 삭제에 성공했습니다.");
		}
	});
/* 	$(document).ready(function(){
		var actionForm = $("#actionForm");
		$(".paginate_btn a").on("click", function(e){
			e.preventDefault();
			console.log('click');
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			alert('submit');
			actionForm.submit();
		});
	}); */
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>