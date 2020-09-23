<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "//WEB-INF/views/member/huationMain.jsp" %>

<html>
<head>
	<title>일반 회원가입</title>



<script language="javascript">
	function checkIt(){
		if (userinput.pw.value != userinput.checkpw.value) {
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
		if(userinput.pw.value.length <8 || userinput.pw.value.length > 12){
			alert("비밀번호를 8~12자 사이로 입력하세요");
			return false;
		}
		var email = userinput.email.value
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

			if(exptext.test(email)==false){

		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			

			alert("이메일형식이 올바르지 않습니다.");

			userinput.email.focus();

		return false;
	}
		
	}
</script>


<script>
	function openConfirmid(userinput) {
		if (userinput.id.value == "") { //값없으면 리턴
			alert("아이디를 입력해주세요."); //값없을시 경고창
			return;
		}


		url = "/huation/member/huecheckmember.do?id=" + userinput.id.value;
		open(
				url,
				"confirm",
				"toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no, width=500, height=400");
		//confirmId.jsp의 id value값을 가져옴
	}
</script>
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
	width:120px;
	padding: 6px;
	font-weight: bold;
	vertical-align: top;
	color: #000;
	background: #ddd ;
	
}
table.type11 td {
	text-align: left;
	padding: 6px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	
}
</style>
</head>



<div align="center">
	<form  action="/huation/member/huememberPro.do"
			name="userinput" onSubmit="return checkIt()">

		<h2>회원가입</h2>
		
		<table class="type11">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" >
					<input type="button" value="아이디 중복확인"
					Onclick="openConfirmid(this.form)"></td>
			</tr>
			<tr>
				<th class>비밀번호</th>
				<td><input type="password" name="pw" id ="pw" placeholder="8~12사이로 입력하세요." required></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="checkpw" id="checkpw" required></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required></td>
			</tr>
			
			<tr>
				<th>핸드폰 번호</th>
				<td><input type="text" name="phone" maxlength="11"
					placeholder="- 제외" required></td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input type="text" name="email"
					placeholder="ex)abc@naver.com" required></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="가입하기">
					<input type="button" value="돌아가기" onclick="window.location='/huation/member/huelogin.do'" />
				</th>
			</tr>

		</table>
	</form>
	</div>

</section>
