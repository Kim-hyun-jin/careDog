<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>caution</title>
<style type="text/css">
.chatdiv{
	position:fixed;
	right:500px;
	bottom:300px;
}
.chatButton{
	float:right;
	width:100px;
	height:100px;
	background-color:yellow;
	border-radius:50%;
	text-align: center;
}
.chatArea{
	display: none;
}
		.chatting{
			background-color: #000;
			width: 500px;
			height: 500px;
			overflow: auto;
		}
		.chatting .me{
			color: #F6F6F6;
			text-align: right;
		}
		.chatting .others{
			color: #FFE400;
			text-align: left;
		}
		input{
			width: 330px;
			height: 25px;
		}
		#yourMsg{
			display: none;
		}
		#yourNameDel{
			display: none;
		}
</style>
<script type="text/javascript">
	function chatBtn(){
		if($(".chatArea").css('display')=='none'){
			$(".chatArea").show();
		} else {
			$(".chatArea").hide();
		}
	}
</script>
</head>
<body>
	<div class="chatdiv">
		<div class="chatArea">채팅을해보자.
			<input type="hidden" id="sessionId" value="">
			<div id="meName"></div>
			<div id="chatting" class="chatting">
			</div>
			<div id="member" class="member">
			</div>
			<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName"></th>
					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
				</tr>
			</table>
		</div>
	</div>
	<div id="yourMsg">
		<table class="inputTable">
			<tr>
				<th>메시지</th>
				<th><input id="chatting2" placeholder="보내실 메시지를 입력하세요."></th>
				<th><button onclick="send()" id="sendBtn">보내기</button></th>
			</tr>
		</table>
		</div>
		<a class="chatButton" onclick="chatBtn()"><b>채팅</b>으로<br/>문의하기</a>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>