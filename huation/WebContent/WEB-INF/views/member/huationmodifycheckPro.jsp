<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script>
	<c:if test="${check == 0}">
		alert("비밀번호가 맞지않습니다.")
		history.go(-1);
	</c:if>
		<c:if test="${check == 1}">
		document.location.href = '/huation/member/huationmodifyForm.do';
	</c:if>	
	</script>