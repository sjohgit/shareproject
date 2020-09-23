<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<html>
<head>
	<title>회원 로그인</title>
	
	<style type="text/css">
table.type11 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	font-size:14px;
	width: 400px;
}
table.type11 th {
	width:80px;
	padding: 6px;
	font-weight: bold;
	vertical-align: top;
	color: #000;
	background: #ddd ;
	
}
table.type11 td {
	text-align: center;
	padding: 6px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	
}
</style>

</head>


<center>

<h1>회원 로그인</h1>
<form action="/huation/member/hueloginPro.do">
	<table class="type11">
		<tr align="center">
			<th>아이디</th>
			<td>
				<input type="text" name="id" required />
			</td>
		</tr>
		<tr align="center">
			<th>비밀번호</th>
			<td>
				<input type="password" name="pw" required />
			</td>
		</tr>
		<tr align="center">
			<th colspan="2">
				<input type="submit" value="로그인" />
			</th>
		</tr>

		
	</table>
	
</form>

</center>

</html>