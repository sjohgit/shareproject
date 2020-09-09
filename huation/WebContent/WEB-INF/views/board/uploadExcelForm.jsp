<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
     <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
</head>
<body>
<!--  <script type="text/javascript">
    function doExcelUploadProcess(){
        var f = new FormData(document.getElementById('form1'));
        $.ajax({
            url: "uploadExcelFile.huation",
            data: f:f,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
                console.log(data);
                document.getElementById('result').innerHTML = JSON.stringify(data);
            }
        })
    }


</script>  

 <form id="form1" name="form1" method="post" enctype="multipart/form-data">
    <input type="file" id="fileInput" name="fileInput">
    <button type="button" onclick="doExcelUploadProcess()">엑셀업로드작업</button> 
 </form>    -->
    
    
 <form id="form1" name="form1" method="post" enctype="multipart/form-data" action="/huation/board/uploadExcellistForm.huation">
    <input type="file" id="excelfile" name="excelfile">
    <button type="submit">엑셀업로드작업</button>
</form> 
<div id="result">
</div>
</body>
</html>
