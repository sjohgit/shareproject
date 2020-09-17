<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<head>
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
	
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #000;
	background: #ddd ;
	
}
table.type11 td {
	text-align: center;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	
}
</style>
</head>

<title>게시판</title>


<div align="center">
<center>
	<b>AJAX게시판(전체글:${count})</b>
</center>

<table  >
	<tr>
		<td>
			<a href=aboardwriteForm.huation>글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count==0}">
	<table  width="700" board="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
</c:if>
<c:if test="${count!=0}">
	<table class="type11" width="700" cellpadding="0" cellspacing="0"
		align="center">
		<tr height="30">
			<th align="center" width="50">번 호</th>
			<th align="center" width="250">제 목</th>
			<th align="center" width="100">작성자</th>
			<th align="center" width="160">작성일</th>
			<th align="center" width="50">조 회</th>
		</tr>

		<c:forEach var="a" items="${noticeList}">
			<c:set var="number" value="${number}" />
			<tr height="30">
				<td align="center" width="50">${a.num}</td>
				<td align="center" width="100">
				<a href="aboardcontent.huation?num=${a.num}&pageNum=${currentPage}">${a.subject}</a>
				<td align="center" width="150">${a.writer}</td>
				<td align="center" width="100">${a.reg_date}</td>
				<td align="center" width="50">${a.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	<table align="center">
		<c:if test="${count > 0}">
			<tr>
				<td><c:if test="${startPage > 10}">
						<a
							href="/huation/aboard/aboardList.huation?pageNum=${startPage-10}">[이전]</a>
					</c:if> <c:forEach var="b" begin="${startPage}" end="${endPage}" step="1">
						<a href="/huation/aboard/aboardList.huation?pageNum=${b}">[${b}]</a>
					</c:forEach> <c:if test="${endPage < pageCount}">
						<a
							href="/huation/aboard/aboardList.huation?pageNum=${startPage+10}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>


</c:if>
</div>

