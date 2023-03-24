<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/customerService/freeBoard/main.css" rel="styleSheet" type="text/css">
<link href="/css/customerService/freeBoard/detail.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
 	$(function(){
		getReplyList();
		if(${!roleMember}){
			$(".btnAni01").attr("onclick", 'location.href="/main/login"');
			$(".btnAni01 span").html("login");
		}
	});
	function replyWrite(){
		var formData = $("#contentForm").serialize();
		$.ajax({
			url		:'/customerService/freeboard/replyWrite',
			type	:'POST',
			data	:formData,
			success	:function(){
				$("#replyContent").val("");
				getReplyList();
			},
			error : function(request,status,error){
	            	alert("request.status = "+request.status+"\nmessage = "+request.responseText+"/nerror = "+error);
			}
		});
		return false;
	}
	function modReplyForm(replyNo,replyContent,username,replyRegdate){
		str = "<tr><td>수정</td></tr>";
		str2="<tr id='mod"+replyNo+
			"'><td>"+username+
			"</td><td><textarea rows='3' cols='130' placeholder='"+replyContent+
			"' name=modReplyContent>"+replyContent+"</textarea></td><td>"+replyRegdate+
			"<br><button type='button'class='btn btn-outline-secondary' onclick='modReply("+replyNo+", modReplyContent.value)'>확인</button>"+
			"<button type='reset'class='btn btn-outline-secondary' onclick='modCancel("+replyNo+")'>취소</button></td></tr>";
		$("#replyTable tbody #"+replyNo).hide();
		$(".modReplyButton*").hide();
		$("#replyTable tbody #"+replyNo).after(str2);
	}
	function modCancel(replyNo){
		$("#replyTable tbody #"+replyNo).show();
		$("#replyTable tbody #mod"+replyNo).remove();
		$(".modReplyButton").show();
	}
	function modReply(modReplyNo, modContent){
		$.ajax({
			url		: "/customerService/freeboard/modReply",
			type 	: "POST", 
			data	: {replyNo:modReplyNo, replyContent:modContent},
			dataType: "text",
			success	: function(data){
				var result = data
				if(result>=1){
					alert("수정을 성공했습니다.");
				}
				getReplyList();
			}
		});
	}
	function delReply(replyNo){
		if(confirm("댓글을 삭제하시겠습니까?")){
			$.ajax({
				url		: "/customerService/freeboard/delReply",
				type 	: "POST", 
				data	: {replyNo:replyNo},
				dataType: "text",
				success	: function(data){
					var result = data
					if(result>=1){
						alert("삭제를 성공했습니다.");
					}
					getReplyList();
				}
			});
		} else return false;
	}
	function getReplyList(){
		$.ajax({
			url		:'/customerService/freeboard/replyList',
			data	:{freeboardNo:${freeboard.freeboardNo}},
			dataType:'json',
			success	:function(data){
				var str = "";
				if(data.length>0){
					$(data).each(function(){
						var str2 =	"<tr id="+this.replyNo+"><td>"+this.username;
						if(this.role=='ROLE_ADMIN') str2 +="&nbsp<img alt='관리자' src='/img/freeboard/free-icon-crown-931979.png' class='AdminIcon'>";
						str2 += "</td><td style='white-space: pre;'>"+this.replyContent;
						<sec:authorize access="isAuthenticated()">
							if(this.id==${principal.id}){
								str2 += "&nbsp<a class='postDetailModButton' onmouseover='this.style.cursor=\"hand\"' class='modReplyButton' onclick='modReplyForm(\""+
										this.replyNo+"\",\""+
										this.replyContent+"\",\""+
										this.username+"\",\""+
										this.replyRegdate+
										"\")'>수정</a>";
							}
							if(${roleAdmin} || this.id==${principal.id}){
								str2 += "&nbsp<a class='postDetailModButton' onmouseover='this.style.cursor=\"hand\"' class='modReplyButton' "+
										"onclick='delReply("+this.replyNo+")'>삭제</a>";
							}
						</sec:authorize>
						str2 += "</td><td>"+this.replyRegdate+"</td></tr>";
						str += str2;
					});
				} else {
					str = "<tr><td style='text-align: center;' colspan='3'>등록된 댓글이 없습니다.</td></tr>"
				}
				$('#insertReply *').remove();
				$('#insertReply').append(str);
				$('.AdminIcon').parent().parent().attr('class','adminTr');
			}
		});
	}
	function delChk(){
		if(confirm("정말 게시글을 삭제하시겠습니가? \n 삭제한 정보는 복구가 불가능합니다.")){
			location.href="/customerService/freeboard/delete?freeboardNo="+${freeboard.freeboardNo}
		}
	}
	function recommandAct(){
		if(${recommandLog!=0}&&!confirm("이미 추천을 한 게시글입니다. 추천을 취소하시겠습니까?")){
			return false;
		} else {
			$.ajax({
				url			: '/customerService/freeboard/recommandAct',
				type		: "POST",
				data		: {recommandLog:${recommandLog}, freeboardNo:${freeboard.freeboardNo}},
				dataType	: 'text',
				success		: function(data){
					var msg = data;
					alert(msg);
					location.reload();
				}
			});
		}
	}
</script>
</head>
<body>
<div class="mainContents">
	<div class="container">
	<br>
	<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
		<span class="fs-5 fw-semibold">게시글</span>
	</a>
		<div class="col-lg-12" id="detaildiv">
			<form id="contentForm" onsubmit="return replyWrite()">
				<input type="hidden" value="${freeboard.freeboardNo }" name="freeboardNo">
				<table class="table table-striped">
					<thead>
						<tr>
							<th colspan="2">
							<a href="/customerService/freeboard/search?searchOption=category&searchString=${list.category}" class="categoryLink">
							[${freeboard.category}]</a><h4 style="display: inline;">${freeboard.title }</h4></th>
							<th>등록번호 : ${freeboard.freeboardNo }</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>작성자 : 
								<c:choose>
									<c:when test="${freeboard.role == 'ROLE_TEACHER' }">[선생님]</c:when>
									<c:when test="${freeboard.role == 'ROLE_MEMBER' }">[일반회원]</c:when>
									<c:when test="${freeboard.role == 'ROLE_ADMIN' }"><b>[관리자]</b></c:when>
								</c:choose>${freeboard.username }
								<c:if test="${freeboard.role =='ROLE_ADMIN' }">
									<img alt='관리자' src='/img/freeboard/free-icon-crown-931979.png' class='AdminIcon'>
								</c:if>
							</td>
							<td>작성일 : ${freeboard.regdate }</td>
							<td>조회 : ${freeboard.viewCount }</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="freeboardContentView mb-3">${freeboard.content }<br/>
								<div class="button_container mb-3"><br/>
									<button class="btnAni01" type="button" onclick="recommandAct()"><span>LIKE ${freeboard.recommand }</span></button>
								</div>
							</div></td>
						</tr>
					</tbody>
				</table>
				<table class="table table-striped" id="replyTable">
					<thead>
						<tr>
							<th style="width: 15%;">작성자</th>
							<th style="width: 70%;">내용</th>
							<th style="width: 15%;">작성일</th>
						</tr>
					</thead>
					<tbody id="insertReply">
					</tbody>
					<tfoot>
						<tr>
							<sec:authorize access="isAuthenticated()">
								<input type="hidden" value="${principal.id }" name="id">
								<td>${principal.username }</td>
								<td><textarea cols="130" rows="5" name="replyContent" id="replyContent" placeholder="내용을 입력해 주세요." required="required" wrap="hard"></textarea></td>
								<td><input type="submit" class="btn btn-outline-secondary" value="댓글등록"></td>
							</sec:authorize>
							<sec:authorize access="isAnonymous()">
								<td colspan="3">댓글을 등록하시려면 <a href="/main/login">로그인</a> 해주세요.</td>
							</sec:authorize>
						</tr>
					</tfoot>
				</table>
			</form>
			<div class="detailBtns mb-3">
				<div class="detailBtnsLeft">
				</div>
				<div class="postListButtons detailBtnsCenter">
					<c:if test="${otherPosts[1]!=null && otherPosts[1]!='' }">
						<input type="button" value="다음글"  class="btn btn-outline-secondary" onclick="location.href='/customerService/freeboard/detail?freeboardNo=${otherPosts[1]}'">
					</c:if>
					<input type="button" value="목록" class="btn btn-outline-secondary" onclick="location.href='/customerService/freeboard/main'">
					<c:if test="${otherPosts[0]!=null && otherPosts[0]!='' }">
						<input type="button" value="이전글" class="btn btn-outline-secondary" onclick="location.href='/customerService/freeboard/detail?freeboardNo=${otherPosts[0]}'">
					</c:if>
					<script type="text/javascript">
						function countChkBtn(){
							$("input.countChkBtn").val($(".countChkBtn").val()+1)
						}
					</script>
				</div>
				<div class="modButtons detailBtnsRight">
					<c:choose>
						<c:when test="${roleAdmin || principal.id == freeboard.id }">
							<input type="button" value="삭제" onclick="delChk()" class="btn btn-outline-secondary">
							<c:if test="${principal.id == freeboard.id }">
								<input type="button" value="수정" class="btn btn-outline-secondary"
								onclick="location.href='/customerService/freeboard/modify?username=${freeboard.username}&freeboardNo=${freeboard.freeboardNo }'">
							</c:if>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>