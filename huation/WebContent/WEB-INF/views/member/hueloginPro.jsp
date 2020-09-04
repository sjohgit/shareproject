<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/member/huationMain.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	<c:if test="${check == 1}">
		alert("안녕하세요.");
		document.location.href = '/huation/member/huationMain.huation';
	</c:if>


	
<c:if test="${check == 0}">
	
			alert("아이디 또는 비밀번호를 확인해주세요.");
			history.go(-1);
		
</c:if>
</script>

