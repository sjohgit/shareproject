<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>  
<html>
<head>
    <title>엑셀업로드</title>
     <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
</head>
<body>

    
    
 <form id="form1" name="form1" method="post" enctype="multipart/form-data" action="/huation/board/uploadExcellistForm.huation">
    <input type="file" id="excelfile" name="excelfile">
    <button type="submit">엑셀업로드작업</button>
</form> 
<div id="result">
</div>
</body>
</html>
