<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
 <head><meta name="viewport" content="width=device-width, initial-scale=1">

<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

header {
  background-color: #2A517E;
  padding: 5px;
  text-align: center;
  font-size: 20px;
  color: white;
}

nav {
  float: left;
  width: 15%; 
  background: #ECEDC9;
  padding: 20px;
}
 
nav ul {
  list-style-type: none;
  padding: 0;
}

article {
  float: left;
  padding: 20px;
  width: 70%;
  background-color: white;
  
}

/* Clear floats after the columns */
section:after {
  content: "";
  display: table;
  clear: both;
}

a:link {color:black; text-decoration: none;}
a:visited{color:black; text-decoration: none;}
a:hover{color:black; text-decoration: none;}
/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media (max-width: 600px) {
  nav, article {
    width: 100%;
    height: auto;
  }
}
</style>
</head>
<body>

<header>
<h3>(주)휴에이션</h3>

  <h2>신입사원 과제</h2>

	<c:if test="${sessionScope.memId == null}">
    	<input type="button" value="로그인" style="float:right;"
       		onclick="document.location.href='/huation/member/huelogin.huation'">
       	
        <input type="button" value="회원가입" style="float:right;"
       		onclick="document.location.href='/huation/member/huemember.huation'">
    </c:if>
    
    <c:if test="${sessionScope.memId != null}">
    
    <li>${sessionScope.memId}님 안녕하세요</li>
             <input type="button" value="로그아웃" style="float:right;"
       onclick="document.location.href='/huation/member/huelogout.huation'">
    </c:if>  
       
  <br/>
</header>

<section>
  <nav>
    <ul>
    <li>▶메뉴</li><br/>
      <li>●게시판</li>
      <li><a href="/huation/board/boardList.huation">일반게시판</a></li>
      <br/>
      <li>●게시판</li>
      <li><a href="calendar2Form.jsp">AJAX게시판</a></li>
    <br/>
      <li>●그래프</li>
      <li><a href="inForm.jsp">막대그래프</a></li>
        <br/>
   	  <li>●채팅기능</li>
      <li><a href="calendar2Form.jsp">채팅하기</a></li>
 
    </ul>
  </nav>
  

