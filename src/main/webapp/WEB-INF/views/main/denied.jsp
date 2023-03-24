<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% String context = request.getContextPath(); %>
<script src="<%=context %>/common/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		alert("페이지에 접근할 권한이 없습니다.\n${errMsg}");
		location.href='${prevPage}';
	});
</script>
</head>
<body>
</body>
</html>