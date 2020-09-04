<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>



<article>



<h1 align = "center">이 게시물을 삭제하시겠습니까?</h1>
<table align = "center">
	<tr>
		<td>
			<input type="button" value="예" onclick="location.href='/huation/board/boarddeletePro.huation?pageNum=${pageNum}&num=${num}'" />
			<input type="button" value="아니오" onClick="location.href='/huation/board/boardList.huation?pageNum=${pageNum}'">
		</td>
	<tr>	

</table>
</article>


