<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
}
table.type11 th {
	width:100px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #000;
	background: #ddd ;
	
}
table.type11 td {
	text-align: left;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	
}
</style>
</head>





<center>
	<b>글쓰기</b>
</center>
<br>
<div align="center">

<form name="boardwriteForm.huation"
      method="post" 
	  enctype="multipart/form-data"
	  action="/huation/board/boardreplyPro.huation" 
	  onsubmit="return writeSave()">
	
		<c:forEach var="a" items="${contentList}">
			<input type="hidden" name="ref" value="${a.ref}"/>
			<input type="hidden" name="re_step" value="0"/>
		</c:forEach> 	
			<input type="hidden" name="re_level" value="0"/>
	
			
			
	<table class="type11"  cellspacing="0" cellpadding="0"
		align="center">
		
		<tr>
			<th width="100" align="center">작성자</th>
			<td width="330"> ${ sessionScope.memId }
				<input type="hidden" size="10" maxlength="10" name="writer" value=${ sessionScope.memId }>
			</td>
		</tr>
		<tr>
			<th width="100" align="center">첨부파일 </th>
			<td width="330"><input type="file" size="40" maxlength="50"
				name="files" ></td>
		</tr>		
		<tr>
			<th width="100" align="center">제 목</th>
			<td width="330"><input type="text" size="40" maxlength="50"
				name="subject" required></td>
		</tr>
	
		<tr>
			<th width="100" align="center">내 용</th>
			<td width="330"><textarea name="content" rows="13" cols="40" required></textarea> 
			</td>
		</tr>
		<tr>
			<td colspan=2 align="center">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시작성"> 
				<input type="button" value="목록보기" OnClick="javascript:history.go(-1)">
			</td>
		</tr>

	</table>
</form>
</div>
