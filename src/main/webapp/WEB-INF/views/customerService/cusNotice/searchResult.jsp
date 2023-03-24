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
<%-- <c:forEach var="list" items="${resultList }">
	<p>${list.title }</p>
</c:forEach> --%>

    <div class="container my-5">

           		<form class="form-inline justify-content-end" action="/customerService/cusNotice/search?page=1&size=10" method="post">
			  
				    <select class="select" id="sorting" name="sorting" >
					    <option id="selected" ${sorting == 'desc'? 'selected':'' }  value="desc">최신순</option>					    
					    <option value="asc" ${sorting == 'asc'? 'selected':'' }>오래된순</option>
					</select>
				  	
					<select class="select" id="category" name="category" >
					    <option id="selected" ${category == 'title'? 'selected': '' }  value="title">제목</option>					    
					    <option ${category == 'content'? 'selected': '' } value="content">내용</option>
					    <option ${category == 'titleContent'? 'selected': '' } value="titleContent">제목+내용</option>
				  	</select>
				  	
				 	<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="keyword" value="${keyword }">
				    <input class="btn btn-outline-secondary my-2 my-sm-0" type="submit" value="검색">
			  </form>
			            
         <a href="/customerService/cusNotice/main?page=1&size=10&sorting=desc">
			<button type="button" class="btn btn-outline-secondary">목록으로</button>
		</a>
			  
<%-- 	<p>search keyword: ${keyword }</p>
		<p>search category: ${category }</p>
		<p>search sorting: ${sorting }</p>
		<p>search total: ${total }</p> 
		<p>totalPage:${list.pageable.pageSize }</p>		
--%>
        <table class="table caption-top">
            <caption>공지사항</caption>
            

            <thead>

              <tr>
                <th scope="col">#</th>
                <th scope="col">제목</th>
                <th scope="col">내용</th>
                <th scope="col">작성일</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <tbody>
            	
              <c:forEach var="list" items="${noticeList }">
	              <tr>
	                <th scope="row">${list.cusNo}</th>
	                <td><a href="/customerService/cusNotice/detail?cusNo=${list.cusNo}" class="text-decoration-none">${list.title} </a></td>
	                <td class="d-inline-block text-truncate" style="max-width: 150px;">${list.content}</td>
	                
	                <td>${list.regdate}</td>
	                <td>100</td>
	              </tr>
              </c:forEach>
              

            </tbody>
          </table>






          <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
            
              
               <c:if test="${searchDTO.prev }">
	              <li class="page-item pre">
	                <a class="page-link" href="<%=context %>/customerService/cusNotice/search?page=${searchDTO.startPage -1 }&size=10&sorting=${sorting}"  aria-label="Previous">
	                  <span aria-hidden="true">&laquo;</span>
	                  <span class="sr-only">Prev</span>
	                </a>
	              </li>
            	</c:if>
            	
              <c:forEach var="page"  begin="${searchDTO.startPage }" end="${searchDTO.endPage }" >
              	
              	<li class="page-item" >
              		<a class="page-link ${page == pageNum ? 'active': ''}" href="/customerService/cusNotice/search?page=${page }&size=10&sorting=${sorting}">${page}</a></li>
              	
              </c:forEach>
              
              <c:if test="${searchDTO.next }">
	              <li class="page-item next">
	                <a class="page-link" href="<%=context %>/customerService/cusNotice/search?page=${searchDTO.endPage +1 }&size=10&sorting=${sorting}" aria-label="Next">
	                  <span aria-hidden="true">&raquo;</span> 
	                  <span class="sr-only">Next</span>
	                </a>
	              </li>
              </c:if>
            </ul>
          </nav>
          
         <!--  메인 첫 조회시 권한에 따라 보여줄지 말지 결정 -->
         <sec:authorize access="hasRole('ADMIN') or hasRole('MEMBER')">
	          <a  href="/customerService/cusNotice/write">
	          <button type="button" class="btn btn-outline-secondary justify-content-end">공지등록</button>
	          </a>
         </sec:authorize> 
        </div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>