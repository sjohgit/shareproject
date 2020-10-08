<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file = "/WEB-INF/views/member/huationMain.jsp" %>
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

   
<br>
<script language="javascript">
	function commentinsert(){
		
			$.ajax({
			type : "POST",
			/* dataType : "json", */
			url : "commentinsert.do",
			data : {"writer":$("input[name=writer]").val(),
			      	"content":$("textarea[name=content]").val(),
			      	"pageNum":$("input[name=pageNum]").val(),
			      	"num":$("input[name=num]").val()},
			success : function(data){
					window.location.reload();
			}
			});
	}

	function deletecontent(){
		alert("게시물이 삭제됩니다");
			$.ajax({
			type : "POST",
			url : "deletecontent.do",
			data : {"num":$("input[name=num]").val()},
			success : function(data){
				document.location.href=data;

			}
			       
		});
		
	}
	


	function deletecomment(){
		alert("댓글이 삭제됩니다");
			$.ajax({
			type : "POST",
			url : "deletecomment.do",
			data : {"num":$("input[name=num]").val(),
					"pageNum":$("input[name=pageNum]").val(),
					"renum":$("input[name=renum]").val()},
			success : function(data){
				window.location.reload();

			}
			       
		});
		
	}
	
</script>


<form>

	<c:forEach var ="b" items="${contentList}">
		<table class="type11" width="500"  cellspacing="0" cellpadding="0" align="center" font-size= "12pt">  
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
	       					onclick="window.open('/huation/aboard/updateForm.do?num=${b.getNum()}&pageNum=${pageNum}','window_name','width=430,height=500,location=no,status=no,scrollbars=yes');">
		   					&nbsp;&nbsp;&nbsp;&nbsp;
		   					
		  				<input type="button"value="삭제하기" OnClick="deletecontent()" >
		   					&nbsp;&nbsp;&nbsp;&nbsp;
		   			</c:if>
						<input type="button" value="글목록" 
	       					onclick="document.location.href='/huation/aboard/List.do?pageNum=${pageNum}'">
	       					&nbsp;&nbsp;&nbsp;&nbsp;
	       			<c:if test="${sessionScope.memId !=null}">
		   				<input type="button" value="답글달기" 
	       					onclick="window.open('/huation/aboard/replyForm.do?num=${b.getNum()}&pageNum=${pageNum}','window팝업','width=1000', 'height=1000, menubar=no,status=no,toolbar=no');">
		   						 
		   			</c:if>
	       					
    			</td>
 			 </tr>
		</table>
		</c:forEach>
</form>


<c:if test="${sessionScope.memId !=null}">
<form name="boardcommentFrom.huation" method="post">
<table align="center">
<tr>
	<td width="70">
		<input type="hidden" value="${pageNum}" name="pageNum"/>
		<input type="hidden" value="${num}" name="num"/>
		
		<input type="text" size="10" maxlength="10" name="writer" value="${sessionScope.memId}" readonly>
    </td>
    
    <td  width="330" >
     	<textarea name="content" rows="5" cols="40" required></textarea> 
    </td>
    <td>
		<input type="button" id="refresh" value="댓글쓰기" OnClick="commentinsert()" > 		
	</td>
</tr>
</table>

</form>

</c:if>
<c:if test="${count==0}">
	<table width="1000" board="1" cellpadding="0" cellspacing="0" align="center" border="1">
		<tr>
			<td align="center">
				댓글이 아직 없습니다..			
			</td>
		</tr>	
	</table>
</c:if>



	<table width="700" cellpadding="0" cellspacing="0" align="center" id="replycontent"> 
    	<tr height="30"> 
      		
      		<td align="center"  width="250" >작성자</td> 
      		<td align="center"  width="100" >댓글내용</td>
      		<td align="center"  width="150" >작성일</td>
      		<td align="center"  width="80" >삭제</td> 
		 </tr>
		 <tbody>
<c:forEach var="a" items="${commentList}">
<c:if test="${count!=0}">
	<c:set var="number" value="${number}"/>
		<tr height="30">
		
			<input type="hidden" name="num" value="${a.num}">
			<input type="hidden" name="renum" value="${a.renum}">
			<td align="center" width="150">${a.writer}</td>
			<td align="center" width="150">${a.content}</td>
			<td align="center" width="100">${a.reg_date}</td>
			<c:if test="${sessionScope.memId eq a.writer}">
			<td><input type="button"value="삭제하기" OnClick="deletecomment()" >
			</td>
			</c:if>
			</tr>
			</c:if>	
</c:forEach>

		
	
</table>



<%--  <table align ="center">
<c:if test="${count > 0}"> 
<tr>
	<td>		
		<c:if test="${startPage > 10}"> 
        	<a href="/huation/aboard/content.do?pageNum=${startPage-10}">[이전]</a>
		</c:if>
			<c:forEach var="b" begin="${startPage}" end="${endPage}" step="1">
        		<a href="/huation/aboard/content.do?pageNum=${b}">[${b}]</a>
			</c:forEach>

        <c:if test= "${endPage < pageCount}">
        	<a href="/huation/aboard/content.do?pageNum=${startPage+10}">[다음]</a>
        </c:if>
    </td>
</tr>
</c:if> 
</table> --%>
</div>


