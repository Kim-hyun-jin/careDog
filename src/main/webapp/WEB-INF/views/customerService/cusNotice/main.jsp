<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- cdn 방식 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
 integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

<style type="text/css">
 tr {
   line-height: 20px;
   min-height: 20px;
   height: 20px;
}
</style>
</head>
<body>
	
    <div class="container my-5">

           		<form class="form-inline justify-content-end" name="searchForm" action="/customerService/cusNotice/search?page=1&size=10" method="post" value="${keyword }">
           		
				    <select class="select my-2" id="sorting" name="sorting" >
					    <option id="selected" value="desc"  ${sorting == 'desc'? 'selected':'' }>최신순</option>					    
					    <option value="asc" ${sorting == 'asc'? 'selected':'' }>오래된순</option>
					</select>
				  	
					<select class="select my-2" id="category" name="category" >
					    <option value="title" id="selected" ${category == 'title'? 'selected': '' }  >제목</option>					    
					    <option value="content" ${category == 'content'? 'selected': '' }>내용</option>
					    <option value="titleContent" ${category == 'titleContent'? 'selected': '' }>제목+내용</option>
				  	</select>
				  	
				 	<input class="form-control mr-sm-2 my-2" type="text" placeholder="Search" aria-label="Search" name="keyword" value="${keyword }">
				    <input class="btn btn-outline-secondary my-2 my-sm-0 " type="submit"  value="검색">
				   <!--  <input class="btn btn-outline-secondary my-2 my-sm-0 clearBtn " type="button"  value="지우기"> -->
			  </form>
        <table class="table caption-top">
            <caption>공지사항</caption>    

            <thead>

              <tr>
                <th scope="col">#</th>
                <th scope="col">제목</th>
                <th scope="col">내용</th>
                <th scope="col">작성일</th>
                <th scope="col">작성자</th>
              </tr>
            </thead>
            <tbody>
            	
              <c:forEach var="list" items="${noticeList }">
	              <tr>
	                <th scope="row">${list.cusNo}</th>
	                <td><a href="/customerService/cusNotice/detail?cusNo=${list.cusNo}" class="text-decoration-none"> ${list.title} </a></td>
	                <td class="d-inline-block text-truncate" style="max-width: 150px;">${list.content}</td>
	                
	                <td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd" type="date"/></td>
	                <td>관리자</td>
	              </tr>
              </c:forEach>
              
            </tbody>
          </table>


<%-- totalPage: ${totalPage } --%>


          <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
            
              
               <c:if test="${responseDTO.prev }">
	              <li class="page-item pre">
	                <a class="page-link" href="<%=context %>/customerService/cusNotice/main?page=${responseDTO.startPage -1 }&size=10&sorting=${sorting}"  aria-label="Previous">
	                  <span aria-hidden="true">&laquo;</span>
	                  <span class="sr-only">Prev</span>
	                </a>
	              </li>
            	</c:if>
            	
              <c:forEach var="page"  begin="${responseDTO.startPage }" end="${responseDTO.endPage }" >
              	
              	<li class="page-item" >
              		<a class="page-link ${page == pageNum ? 'active': ''}" href="/customerService/cusNotice/main?page=${page }&size=10&sorting=${sorting}">${page}</a></li>
              	
              </c:forEach>
              
              <c:if test="${responseDTO.next }">
	              <li class="page-item next">
	                <a class="page-link" href="<%=context %>/customerService/cusNotice/main?page=${responseDTO.endPage +1 }&size=10&sorting=${sorting}" aria-label="Next">
	                  <span aria-hidden="true">&raquo;</span> 
	                  <span class="sr-only">Next</span>
	                </a>
	              </li>
              </c:if>
            </ul>
          </nav>
          
         <!--  메인 첫 조회시 권한에 따라 노출 여부 결정 -->
         <sec:authorize access="hasRole('ADMIN')">
	          <a  href="/customerService/cusNotice/write">
	          	<button type="button" class="btn btn-outline-secondary justify-content-end">공지등록</button>
	          </a>
         </sec:authorize>
        </div>
    
    
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	<script type="text/javascript">
		let select = document.querySelector('.select');

		
		function searchRequest(){ //onchange => ajax(json) 변경가능
			const formObj = document.querySelector("form");
			let sorting = document.getElementById('sorting');
			let sortingVal = sorting.options[sorting.selectedIndex].value;
			//let searchForm = document.searchForm;
			alert("selected value: "+ sorting.options[sorting.selectedIndex].value);
		
		}	
	</script>
</body>
</html>