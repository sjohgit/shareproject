<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>   
<title>게시판</title>
<head>
<style type="text/css">
table.type11 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	font-size:14px;
	width: 700px;
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



<div align="center">

<center><b>글내용 보기</b></center>
<br>
<form>

	<c:forEach var ="b" items="${contentList}">
		<table class="type11" width="500"  cellspacing="0" cellpadding="0" align="center">  
  			<tr height="30">
    			<th align="center" width="125">글번호</th>
    			<td align="center" width="125">
	     			${b.num}
	     		</td>
    			<th align="center" width="125">조회수</th>
    			<td align="center" width="125" align="center">
	     			${b.getReadcount()}
	     		</td>
  			</tr>
  			<tr height="30">
    			<th align="center" width="125">작성자</th>
    			<td align="center" width="125" align="center">
	    		 ${ b.getWriter()}
	    		</td>
    			<th align="center" width="125">작성일</th>
    			<td align="center" width="125" align="center">
				${b.getReg_date()}
				</td>
  			</tr>
  			<tr height="30">
    			<th align="center" width="125">글제목</th>
    			<td align="center" width="375" align="center" colspan="3">
	     		${b.getSubject()}
	     		</td>
  			</tr>
  			<tr>
    			<th align="center" width="125">글내용</th>
    			<td align="left" width="375" colspan="3"><pre>${b.getContent()}</pre></td>
  			</tr>
  			<tr height="30">      
    			<td colspan="4"align="right" > 
	  				<c:if test="${sessionScope.memId eq b.writer}">
		  				<input type="button" value="글수정" 
	       					onclick="document.location.href='/huation/board/boardupdateForm.huation?num=${b.getNum()}&pageNum=${pageNum}'">
		   					&nbsp;&nbsp;&nbsp;&nbsp;
		  				<input type="button" value="글삭제" 
	       					onclick="document.location.href='/huation/board/boarddeleteForm.huation?num=${b.getNum()}&pageNum=${pageNum}'">
		   					&nbsp;&nbsp;&nbsp;&nbsp;
		   			</c:if>
						<input type="button" value="글목록" 
	       					onclick="document.location.href='/huation/board/boardList.huation?pageNum=${pageNum}'">
    			</td>
 			 </tr>
		</table>
		</c:forEach>
</form>
</div>
