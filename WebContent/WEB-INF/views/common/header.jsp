<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member"%>
<%@ page import="broker.model.vo.Broker"%>
<%
	//session : 선언없이 사용할 수 있는 jsp내장객체
	Member memberLoggedIn = (Member) session.getAttribute("memberLoggedIn");
	Broker brokerLoggedIn = (Broker) session.getAttribute("brokerLoggedIn");

	//쿠키관련
	Cookie[] cookies = request.getCookies();
	boolean saveIdChecked = false;
	String saveIdValue = "";

	if (cookies != null) {
		for (Cookie c : cookies) {
			String k = c.getName();
			String v = c.getValue();

			//saveId 쿠키 존재여부 확인
			if ("saveId".equals(k)) {
				saveIdChecked = true;
				saveIdValue = v; //memberId
			}
		}

	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DamoaBang</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
<script>
//로그인
$(function(){
	$("#login").submit(function(){
		let $loginId = $("#loginId");
		let $loginPwd = $("#loginPwd");
		
		if($loginId.val().length == 0) {
			alert("아이디를 입력하세요.");
			$loginId.focus();
			return false;
		}
		
		if($loginPwd.val().length == 0) {
			alert("비밀번호를 입력하세요.");
			$loginPwd.focus();
			return false;
		}
		
		return true;
	});
	
});

//중복체크
$(document).ready(function(){
	$("#userId").blur(function(){
		
		let memberId = $("#userId").val();
		$.ajax({
			url : "<%= request.getContextPath() %>/member/checkIdDuplicate",
			method: "GET", 
			data: {"memberId": memberId}, 
			success: function(data){
				if(data=="notUsable"){
					// 아이디 중복 시 문구
					$("#id_check").text("사용 불가");
					$("#id_check").css("color", "red");
					$("#submit").attr("disabled", true);
				}else if(memberId.length!=0&&data=="usable"){
					$("#id_check").text("사용가능");
					$("#id_check").css("color", "blue");
				}
			}, error : function() {
					console.log("실패");
			}
		});
		
	});
	
})

	//회원 가입
	$(function() {
		$("#register").submit(function() {
			//아이디검사
			let $memberId = $("#userId");

			if (!/^[\w]{4,}$/.test($memberId.val())) {
				alert("아이디가 유효하지 않습니다.");
				$memberId.focus();
				return false;
			}

			//아이디 중복검사 
			/* let $isIdValid = $("#isIdValid");
			if($isIdValid.val() == 0){
				alert("아이디 중복검사 해주세요.");
				return false;
			} */

			//비밀번호 검사
			let $pwd1 = $("#userPwd");
			let $pwd2 = $("#userPwdChk");

			if ($pwd1.val() !== $pwd2.val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$pwd1.focus();
				return false;
			}

			return true;
		});
	});
	//마이페이지 연결 리스트(토글버튼처럼 구현)
	$(document).ready(function() {

		$(".nav-user-link").click(function() {
			if ($(".profile__list").css("display") == "none")
				$(".profile__list").show();
			else
				$(".profile__list").hide();
		});
	});

	$(document).ready(function() {
		$(".filter-select__header").click(function() {
			if ($(".filter-select__list").css("display") == "none")
				$(".filter-select__list").show();
			else
				$(".filter-select__list").hide();
		});
	});
</script>
</head>
<body>
	<header class="navbar navbar-expand" id="mainNav">
		<div class="container">
			<a href="<%=request.getContextPath()%>" class="navbar-brand">다모아방</a>
			<div class="navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav">


					<%if (memberLoggedIn != null || brokerLoggedIn != null) {%>
						<%if (memberLoggedIn != null && brokerLoggedIn == null) {%>
									<%if (memberLoggedIn.getMemberRole().equals("U")) {%>
										<li class="nav-item-community"><a href="<%=request.getContextPath()%>/community/communityQnA" class="nav-link">커뮤니티</a></li>
										<li class="nav-item-room"><a href="<%=request.getContextPath()%>/board/boardList" class="nav-link">방 찾기</a></li>
										<li class="nav-item"><a href="#" class="nav-user-link"><img style="background-color: rgb(255, 136, 81); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" /></a></li>
		
										<ul class="profile__list">
											<li class="login-profile" id="login-profile-img"><img style="background-color: rgb(255, 136, 81); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" />
											<li class="login-profile"><%=memberLoggedIn.getMemberId()%></li>
											<li class="login-profile" id="login-profile-email"><%=memberLoggedIn.getEmail()%></li>
											<li class="login-mypage" id="login-mypage-info"><div onclick="location.href='<%=request.getContextPath()%>/member/memberProfileView'">내 정보 보기</div>
											<li class="login-mypage" id="login-mypage-logout"><div onclick="location.href='<%=request.getContextPath()%>/member/logout'">로그아웃</div></li>
										</ul>
									<%} else {%>
										<li class="nav-item-community"><a href="<%=request.getContextPath()%>/community/communityQnA" class="nav-link">커뮤니티</a></li>
										<li class="nav-item-room"><a href="<%=request.getContextPath()%>/board/boardList" class="nav-link">방 찾기</a></li>
										<li class="nav-item"><a href="#" class="nav-user-link"><img style="background-color: rgb(0, 0, 255); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" /></a></li>
						
										<ul class="profile__list">
											<li class="login-profile" id="login-profile-img"><img style="background-color: rgb(0, 0, 255); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" />
											<li class="login-profile"><%=memberLoggedIn.getMemberId()%></li>
											<li class="login-profile" id="login-profile-email"><%=memberLoggedIn.getEmail()%></li>
											<li class="login-mypage" id="login-mypage-info"><div onclick="location.href='<%=request.getContextPath()%>/admin/memberList';">회원정보 조회</div></li>
											<li class="login-mypage" id="login-mypage-logout"><div onclick="location.href='<%=request.getContextPath()%>/member/logout'">로그아웃</div></li>
										</ul>
									<% } %>
							<% } else { %>
								<li class="nav-item-community"><a href="<%=request.getContextPath()%>/community/communityQnA" class="nav-link">커뮤니티</a></li>
								<li class="nav-item-room"><a href="<%=request.getContextPath()%>/brokerBoard/boardList" class="nav-link">방 찾기</a></li>
								<li class="nav-item"><a href="#" class="nav-user-link"><img style="background-color: rgb(0, 255, 0); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" /></a></li>
			
								<ul class="profile__list">
									<li class="login-profile" id="login-profile-img"><img style="background-color: rgb(0, 255, 0); border-radius: 60%;" src="<%=request.getContextPath()%>/images/user.png" /></li>
									<li class="login-profile"><%=brokerLoggedIn.getBr_name()%></li>
									<li class="login-profile" id="login-profile-email"><%=brokerLoggedIn.getEmail()%></li>
									<li class="login-mypage" id="login-mypage-info"><div onclick="location.href='<%=request.getContextPath()%>/broker/brokerProfileView'">내 정보 보기</div></li>
									<li class="login-mypage" id="login-mypage-logout"><div onclick="location.href='<%=request.getContextPath()%>/broker/logout'">로그아웃</div></li>
								</ul>
							<% } %>
					<% } else { %>
						<li class="nav-item"><a href="<%=request.getContextPath()%>/community/communityQnA" class="nav-link">커뮤니티</a></li>
						<li class="nav-item"><a href="<%=request.getContextPath()%>/board/boardList" class="nav-link">방 찾기</a></li>
						<li class="nav-item"><a href="#" class="nav-link" onclick="signupbtn()">로그인</a></li>
					<% } %>
				</ul>
			
						</div>
		</div>
	</header>


	<!-- 로그인 창 -->
	<div id="signWrap"></div>
	<div id="wrap">
		<div class="form-wrap">
			<div class="button-wrap">
				<div id="btn"></div>
				<button type="button" id="loginBtn" class="togglebtn" onclick="login()">로그인</button>
				<button type="button" id="registerBtn" class="togglebtn" onclick="register()">회원가입</button>
			</div>
			<input type="button" class="HeaderCloseBtn" id="closeBtn" value="x" onclick="closeBtn();">

			<form action="<%=request.getContextPath()%>/member/login" id="login" method="post" class="input-group">
				<div class="social-icons">
					<img src="<%=request.getContextPath()%>/images/naver.png" alt="naver"> <img src="<%=request.getContextPath()%>/images/facebook.png" alt="facebook"> <img src="<%=request.getContextPath()%>/images/google.png" alt="google">
				</div>
				<input type="text" id="loginId" name="memberId" class="input-field" placeholder="아이디를 입력해주세요" required value="<%=saveIdChecked ? saveIdValue : ""%>" /> <input type="password" id="loginPwd" name="password" class="input-field" placeholder="비밀번호를 입력해주세요" required>
				<div id="chkWrap">
					<input type="checkbox" class="checkbox" name="saveId" <%=saveIdChecked ? "checked" : ""%> />아이디 저장 <span class="checkbox brokerChk" id="br_frm" onclick="location.href='<%=request.getContextPath()%>/broker/index'">중개인이세요?</span>
				</div>
				<input type="submit" class="submit" value="로그인" /> <a id="findLink" onclick="findLink()">비밀번호 찾기</a>
			</form>
			<form id="register" action="<%=request.getContextPath()%>/member/enroll" method="post" onsubmit="return resisterVal();" class="input-group">
				<input type="text" id="userId" name="memberId" class="input-field" placeholder="User ID" required>
				<div id="id_check"></div>
				<input type="email" id="userEmail" name="email" class="input-field" placeholder=abc@xyz.com required> <input type="password" id="userPwd" name="password" class="input-field" placeholder="Enter Password" required> <input type="password" id="userPwdChk" class="input-field" placeholder="Enter Password Check" required> <input type="tel" id="userPhone" name="phone" class="input-field" placeholder="(-없이)01012345678" maxlength="11" required>
				<button class="submit">회원가입</button>
				<a href="<%=request.getContextPath()%>/broker/enroll">중개인 회원가입</a>
			</form>
		</div>
	</div>
	<!-- 비밀번호 찾기 추가!!! -->
	<div id="find_wrap">
		<div class="findFrm-wrap">
			<input type="button" id="find_closeBtn" value="x" onclick="find_closeBtn();">
			<p>비밀번호 찾기</p>
			<form action="<%=request.getContextPath()%>/member/findPwd" id="find-pwd" method="post" class="input-group">
				<input type="text" id="input-email" name="email" class="input-field" placeholder="이메일을 입력해주세요" required /> 
				<input type="submit" class="findId-submit" value="이메일 전송" />
			</form>
		</div>
	</div>
	<script>
		//로그인 관련
		let x = document.getElementById("login");
		let y = document.getElementById("register");
		let z = document.getElementById("btn");
		let loginbtn = document.getElementById("loginBtn");
		let registerbtn = document.getElementById("registerBtn");

		function login() {
			x.style.left = "50px";
			y.style.left = "450px";
			z.style.left = "0";
			loginbtn.style.color = "white";
			registerbtn.style.color = "black";

		}
		function register() {
			x.style.left = "-400px";
			y.style.left = "50px";
			z.style.left = "110px";
			loginbtn.style.color = "black";
			registerbtn.style.color = "white";

		}
		function signupbtn() {
			wrap.style.display = "block";
			signWrap.style.display = "block";
		}
		function closeBtn() {
			wrap.style.display = "none";
			signWrap.style.display = "none";
			$("#register").children().val('');
			$("#loginPwd").val('');
			$("#id_check").text("");
		}
		/* 추가!!!!! */
		function find_closeBtn() {
			find_wrap.style.display = "none";
			signWrap.style.display = "none";
			$("#register").children().val('');
			$("#loginPwd").val('');
			$("#id_check").text("");
		}
		function findLink() {
			wrap.style.display = "none";
			find_wrap.style.display = "block";
			$("#register").children().val('');
			$("#loginPwd").val('');
			$("#id_check").text("");
		}
	</script>