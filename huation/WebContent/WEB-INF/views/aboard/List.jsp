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

<script language="javascript">
function a(){
	alert("a")
	 /* window.open('writeForm.do','window팝업','width=500, height=500,menubar=no,status=no,toolbar=no');  */
	window.open('writeForm.do','','_blank'); 
	
}



</script>
</head>

<title>게시판</title>


<div align="center">
<center>
	<b>AJAX게시판(전체글:${count})</b>
</center>

<table>
	<form name="search" method="post" action="/huation/aboard/searchList.do">

    <select name="search_option">
        <option value="subject" selected>제목</option>
        <option value="writer" >작성자</option>
        <option value="content" >내용</option> 
        <option value="all" >작성자+내용+제목</option>      
	</select>
    <input name="keyword" type="search" required>
    <input type="submit" value="조회">
</form>
</table>


<c:if test="${count==0}"> 
	<table class="type11" width="1000" cellpadding="0" cellspacing="0" font-size= "12pt"
		align="center">
		<tr height="30">
			<th align="left" width="50">번 호</th>
			<th align="left" width="250">제 목</th>
			<th align="left" width="100">작성자</th>
			<th align="left" width="160">작성일</th>
			<th align="left" width="50">조 회</th>
		</tr>
		<tr height="30">
			<td align="center" colspan="5">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
<c:if test="${sessionScope.memId !=null}">
	<table width="1000" cellpadding="0" cellspacing="0"
		align="center">
		<tr align="right">
			<td align="right">
				<a href="javascript:a();">글쓰기</a>
			</td>
		</tr>
	</table>
</c:if>		
		
</c:if>
<c:if test="${count!=0}">
	<table class="type11" width="1000" cellpadding="0" cellspacing="0"
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
			<c:if test="${a.re_step == 0}">
				<th align="left" width="100">
					<a href="content.do?num=${a.num}&pageNum=${currentPage}">${a.subject}</a>
				</th>
				
			</c:if>
			<c:if test="${a.re_step > 0}">
			 	<th align="left" width="100">
			 		
				<c:forEach begin="1" end="${a.re_step}">
                            &nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                 </c:forEach>
                        <span>RE : </span><a href="content.do?num=${a.num}&pageNum=${currentPage}">${a.subject}</a>
                	
                </th>        
            </c:if>
				<td align="center" width="150">${a.writer}</td>
				<td align="center" width="100">${a.reg_date}</td>
				<td align="center" width="50">${a.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${sessionScope.memId !=null}">
<table width="1000" cellpadding="0" cellspacing="0"
		align="center">
	<tr align="right">
		<td align="right">
			<a href="javascript:a();">글쓰기</a>
			
		</td>
	</tr>
</table>
	</c:if>
	<table align="center">
		<c:if test="${count > 0}">
			<tr>
				<td><c:if test="${startPage > 10}">
						<a
							href="/huation/aboard/List.do?pageNum=${startPage-10}">[이전]</a>
					</c:if> <c:forEach var="b" begin="${startPage}" end="${endPage}" step="1">
						<a href="/huation/aboard/List.do?pageNum=${b}">[${b}]</a>
					</c:forEach> <c:if test="${endPage < pageCount}">
						<a
							href="/huation/aboard/List.do?pageNum=${startPage+10}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>


</c:if>
</div>

