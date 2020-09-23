<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>dkssyd</h1>

<script>
	alert("댓글이 삭제되었습니다.")
	document.location.href='/huation/board/content.do?pageNum=' + ${pageNum} + '&num=' + ${num};
</script>
   