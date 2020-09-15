<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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

<h1>회원정보수정</h1>
<li>비밀번호를 한번 더 입력해주세요.</li>

<form action="/huation/member/huationmodifycheckPro.huation?">
	<table class="type11">
		<tr align="center">
			<th>아이디</th>
			<td>
				<input type="hidden" name= "id" value="${sessionScope.memId}"/>
				<input type="text" name="id" value = "${sessionScope.memId}" disabled/>
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
				<input type="submit" value="수정하기" />
			</th>
		</tr>

		
	</table>
	


	
</form>

</center>

</html>