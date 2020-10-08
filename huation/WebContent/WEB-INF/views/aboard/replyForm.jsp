<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>   
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
<!-- <script language="javascript">
	function replyinsert(){
		var params = jQuery("#boardwriteForm.huation").serialize();
			$.ajax({
			type : "POST",
			url : "replyinsert.do",
			data : params,
			success : function(data){
				document.location.href=data;
			}
		});
		
	}
</script> -->
<script language="javascript">
	function replyinsert(){
		alert("bbb")
			$.ajax({
			type : "POST",
			url : "replyinsert.do",
			data : {"writer":$("input[name=writer]").val(),
			       "subject":$("input[name=subject]").val(),
			       "content":$("textarea[name=content]").val(),
			       "re_step":$("input[name=re_step]").val(),
			       "re_level":$("input[name=re_level").val(),
			       "ref":$("input[name=ref]").val()},
			success : function(data){
				document.location.href=data;
			}
		});
		
	}
</script>
<center>
	<b>답글쓰기</b>
</center>
<br>
<div align="center">

<form name="boardwriteForm.huation" method="post" onsubmit="return writeSave()">
	
		<c:forEach var="a" items="${contentList}">
			<input type="hidden" name="ref" value="${a.ref}"/>
			<input type="hidden" name="re_step" value="0"/>
		 	<input type="hidden" name="re_level" value="0"/>
		</c:forEach>


			
			
	<table class="type11"  width="700" cellspacing="0" cellpadding="0"
		align="center">
		
		<tr>
			<th width="100" align="center">작성자</th>
			<td width="330"> ${ sessionScope.memId }
				<input type="hidden" size="10" maxlength="10" name="writer" value=${ sessionScope.memId }>
			</td>
		</tr>		
		<tr>
			<th width="100" align="center">제 목</th>
			<td width="330"><input type="text" size="60" maxlength="50"
				name="subject" required></td>
		</tr>
		 	
		<tr>
			<th width="100" align="center">내 용</th>
			<td width="330"><textarea name="content" rows="13" cols="60" required></textarea> 
			</td>
		</tr>
		<tr>
			<td colspan=2 align="center">
				<input type="button" OnClick="replyinsert()"value="글쓰기">
				<input type="reset" value="다시작성"> 
				<input type="button" value="목록보기" OnClick="javascript:history.go(-1)">
			</td>
		</tr>

	</table>
</form>
</div>



