<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/Vertx/js/jquery-1.10.2.min.js"></script>
<script src="/Vertx/js/socket.io.js"></script>
<script>
	$(document).ready(function() {
		// 서버로부터 채팅 내용을 받는 부분
		var socket = io.connect("http://192.168.0.51:12345");  //서버연결 
		socket.on('response', function(msg){// 서버로부터 채팅메세지를 계속 받고있다. .. 
			$('#msgs').append(msg.id + msg.msg+'<BR>');		// 채팅 메세지 받아 출력 부분..
		//	$('#msgs').append(msg.id+'<BR>');		// 채팅 메세지 받아 출력 부분..
			/*파라미터로 넘어오는 id 값이 java인지 유효성 검사 
			if(msg.id == 'java'){
				$('#msgs').append(msg.msg+'<BR>');
			}
			*/
		});
		socket.on('aaa', function(msg){
			 /* $("h1").html(msg.id);  */     // id만 출력
		});
		
		// 서버로 채팅 내용을 보내는 부분
		// 텍스트박스내부의 채팅 내용 보내기
		$("#sendBtn").bind("click", function() {	
		//	var msg = $("input[name=chat]").val();
			// id 속성은 css를 사용하기 위해 쓴다 (name 속성은 jsp에서 파라미터를 처리하기 위해 쓴다)
			// 단일 속성: id(#id명), 여러 곳에 쓸 경우: class(.class명)
			// val(): 작성된 값
			var msg = $("#chat").val();
			// 'msg'가 VertxSample.java 파일의 socket.on을 찾아간다(보낼 곳의 이름)
			// msg:msg의 앞 msg는 파라미터 이름. VertxSample.java 파일의 event.getString("msg"))에 파라미터값이 넘겨진다
			// 1개가 아닌 2개의 파라미터값도 보낼 수 있다
			socket.emit('msg',{msg:msg, id:"${sessionScope.memId}"+"님 :"});
		});
	});
</script>
</head>

<!-- <body>
	<h1>Main</h1>
	<input type="text" id="chat" />
	<input type="button" value="send" id="sendBtn" /><br />
	 <span id="msgs"></span>
	 
</body> -->
<body><br/>
	
	<h3 align="center" >Chat</h3>
	

	
	<!-- 채팅박스 -->
		
	<div id="chatScroll" style="width:40%; height:300px; overflow:auto; border:1px solid; margin: 0 30% 0 30%; border-radius: 10px;" >
		<table id="msgs" width="100%" cellspacing="10" cellpadding="10"	align="center"></table>
	</div>
<table>
<tr align ="right">
	<td>접속중인회원:</td>
	<td>${sessionScope.memId}</td>

</tr>
</table>	
<table style="width:30%;" border="0" align="center">
<tr>
	<td><input type="text"  id="chat" size="40" tabindex="2"/></td>
		
	<td><input type="button"value="send" id="sendBtn" tabindex="3" /></td><br />
</tr>
</table>
<!-- 	</div> -->
	
</body>
</html>