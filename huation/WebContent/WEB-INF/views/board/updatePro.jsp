<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>
    <script>
    	alert('수정되었습니다.');
    	document.location.href='/huation/board/List.do?pageNum='+${pageNum};
    </script>
