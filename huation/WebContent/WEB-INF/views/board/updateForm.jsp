<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>게시판</title>




<center><b>글수정</b></center>


<div align="center">
<br>
<div align="centent">
<form method="post" 
enctype="multipart/form-data" 
name="boardupdateForm" 
action="/huation/board/updatePro.do?pageNum=${pageNum}">
<c:forEach var ="c" items="${updatelist}">
	<table class="type11" width="400"  cellspacing="0" cellpadding="0" align="center">
  		<tr>
    		<th  width="70"  align="center">이 름</th>
    		<td align="left" width="330">
       			${c.getWriter()}
       			<input type="hidden" name="writer" value="${c.getWriter()}">
	   			<input type="hidden" name="num" value="${c.getNum()}">
	   		</td>
  		</tr>
  		<tr>
    		<th  width="70" align="center" >첨부파일</th>
    		<td align="left" width="330">
       			<input type="text" size="40" maxlength="50" name="oldfile" value="${c.getFiles()}" disabled>
       			<input type="file" name="file"/>
       		</td> 
  		</tr>
  		<tr>
    		<th  width="70" align="center" >제 목</th>
    		<td align="left" width="330">
       			<input type="text" size="40" maxlength="50" name="subject" value="${c.getSubject()}"></td>
  		</tr>

  		<tr>
    		<th  width="70" align="center" >내 용</th>
    		<td align="left" width="330">
     			<textarea name="content" rows="13" cols="40">${c.getContent()}</textarea></td>
  		</tr>
  		<tr>      
   			<td colspan=2 align="center"> 
     			<input type="submit" value="글수정" >  
     			<input type="reset" value="다시작성">
     			<input type="button" value="목록보기" onclick="document.location.href='/huation/board/List.do?pageNum=${pageNum}'">
   			</td>
 		</tr>
 	</table>
 </c:forEach>
 </form>
 </div>
 
 </div>


