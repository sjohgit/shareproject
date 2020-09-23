<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<script type="text/JavaScript"
	src="http://code.jquery.com/jquery-1.7.min.js"></script>
	
<script language="javascript">
	function checkIt(){
		if (userinput.pw.value != userinput.checkpw.value) {
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
		if(userinput.pw.value.length <8 || userinput.pw.value.length > 12){
			alert("비밀번호를 8~12자 사이로 입력하세요");
			return false;
		}
		var email = userinput.email.value
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

			if(exptext.test(email)==false){

		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			

			alert("이메일형식이 올바르지 않습니다.");

			userinput.email.focus();

		return false;
	}
		
	}
</script>
<html>
<head>
<title>회원정보수정</title>

</head>




<style type="text/css">
table.type11 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	font-size:14px;
	
}
table.type11 th {
	
	padding: 6px;
	font-weight: bold;
	vertical-align: top;
	color: #000;
	background: #ddd ;
	
}
table.type11 td {
	text-align: left;
	padding: 6px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;

}
</style>
<div align="center">

	<form action="/huation/member/huationmodifyPro.do" method="post"
		name="userinput" onsubmit="return checkIt()">
		<c:forEach var ="a" items="${list}">
		<table class="type11">
			<tr>
				<th colspan="2" align="center"><font size="+1"><b>회원정보수정</b></font>
				</th>
			</tr>
			<tr>
				<th width="200" align="center">아이디</th>
				<td width="400">${a.id}</td>
			</tr>
			<tr>
				<th width="200" align="center">비밀번호</th>
				<td width="400"><input type="password" name="pw" id="pw" size="10">
				</td>
			</tr>
			<tr>
				<th width="200" align="center">비밀번호 확인</th>
				<td width="400"><input type="password" name="checkpw" id="checkpw"  size="10">
				</td>
			</tr>
			<tr>
				<th width="200" align="center">이름</th>
				<td width="400"><input type="text" name="name"
					value="${a.name}"></td>
			</tr>
			<tr>
				<th width="200" align="center">핸드폰번호</th>
				<td width="400"><input type="text" name="phone"
					value="${a.phone}"></td>
			</tr>
			<tr>
				<th width="200" align="center">이메일</th>
				<td width="400"><input type="text" name="email"
					value="${a.email}"placeholder="ex)abc@naver.com" required></td>
			</tr>
			<tr>
				<td colspan="3" align="center" ><input type="submit"
					value="수정하기"></td>
			</tr>
		</table>
		</c:forEach>
	</form>

</div>

</html>





























































