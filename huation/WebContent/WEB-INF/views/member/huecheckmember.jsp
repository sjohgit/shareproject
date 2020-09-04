<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<script>
	function Wclose(){
		opener.document.userinput.id.value="${memId}";
		self.close();
	}
</script>


<c:if test="${check!=1}">
	${memId}는 사용할 수 있는 아이디입니다<br />
</c:if>

<c:if test="${check==1}">
	<form method = "post" action ="/huation/work/huecheckmember.huation">
		${memId}는 이미 사용중인 아이디입니다 <br />
	
	<input type="text" size="10" maxlength="12" name="id" id="id">
	<input type="submit" name="id" id="id" value="중복확인" > 
		
	</form>
</c:if>	

