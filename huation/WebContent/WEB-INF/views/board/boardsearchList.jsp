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
	<b>공지사항(전체글:${count})</b>
</center>


<table  >
	<tr>
		<td>
			<a href="boardwriteForm.huation">글쓰기</a>
			<a href="downExcelForm.huation">엑셀로 내려받기</a>
			<a href="uploadExcelForm.huation">DB로 저장하기</a>
		</td>
	</tr>
</table>
<table>
	<form name="search" method="post" action="/huation/board/boardsearchList.huation">

    <select name="search_option">
        <option value="subject" selected>제목</option>
        <option value="writer" >작성자</option>
        <option value="content" >내용</option> 
        <option value="all" >작성자+내용+제목</option>      
	</select>
    <input name="keyword" type="search">
    <input type="submit" value="조회">
</form>
</table>

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

		<c:forEach var="a" items="${searchlist}">
			<c:set var="number" value="${number}" />
			<tr height="30">
				<td align="center" width="50">${a.num}</td>
			<c:if test="${a.re_step == 0}">
				<th align="left" width="100">
					<a href="boardcontent.huation?num=${a.num}&pageNum=${currentPage}"/>${a.subject}
				</td>
			</c:if>
			<c:if test="${a.re_step > 0}">
			 	<th align="left" width="100">
			 		
				<c:forEach begin="1" end="${a.re_step}">
                            &nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                 </c:forEach>
                        <span>RE : </span><a href="boardcontent.huation?num=${a.num}&pageNum=${currentPage}"/>${a.subject}
                	
                </td>        
            </c:if>				
				<td align="center" width="150">${a.writer}</td>
				<td align="center" width="100">${a.reg_date}</td>
				<td align="center" width="50">${a.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	<table align="center">
		
			<tr>
				<td align="center"><input type="button" value="글목록" 
	       					onclick="document.location.href='/huation/board/boardList.huation?pageNum=${pageNum}'"></td>
			</tr>
		
	</table>

	


</c:if>
</div>

<%-- 		</td>
				<td align="center" width="100">
                   <c:if test="${a.re_level > 0}">
                        <c:forEach begin="1" end="${a.re_level}">
                            &nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                        </c:forEach>
                        RE : 
                    </c:if>				
				</td>
				 --%>