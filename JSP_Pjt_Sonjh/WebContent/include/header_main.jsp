<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아시아 여행을 책임지는 Travel ASIA</title>
<link rel="stylesheet" href="${root}css/header_main.css" />
<%@ include file="/setting.jsp"%>
<!-- 메인 슬라이더 -->
<script>
	$(document).ready(function() {
		$('.slider').bxSlider();
	});
</script>
</head>
<body>
	<div class="gnb">
		<c:if test="${sessionScope.sessionId == null}">
			<ul>
				<li><a href="#" class="openMask">로그인</a></li>
				<li><a href="${root}signup.go">회원가입</a></li>
				<c:choose>
					<c:when test="${selectCnt==-1}">
						<script type="text/javascript">
							alert("비밀번호가 틀렸습니다.");
						</script>
						<li style="float: left;">"비밀번호가 틀렸습니다."</li>
					</c:when>
					<c:when test="${selectCnt==0}">
						<script type="text/javascript">
							alert("ID가 존재하지 않습니다.");
							window.location='${root}signup.go';
						</script>
						<li style="float: left;">"ID가 존재하지 않습니다."</li>
					</c:when>
					<%--
					<c:when test="${selectCnt==2}">
						<script type="text/javascript">
							alert("감사합니다. 회원가입이 완료 되었습니다.");
						</script>
						<li style="float:left;">"감사합니다. 회원가입이 완료 되었습니다."</li>
					</c:when>
					--%>
					<c:when test="${selectCnt==3}">
						<li style="float: left;">"방문을 환영합니다"</li>
					</c:when>
					<c:when test="${selectCnt==4}">
						<li style="float: left;">"탈퇴가 완료되었습니다. 꼭 다시 만나요."</li>
					</c:when>
				</c:choose>
			</ul>
			<%@ include file="/include/login.jsp"%>
		</c:if>

		<c:if test="${sessionScope.sessionId != null}">
			<ul>
				<c:choose>
					<c:when test="${sessionScope.sessionAuth=='H'}">
						<li><a href="logout.go">로그아웃</a></li>
						<li><a href="${root}main.admin">ADMIN</a></li>
						<li style="float: left;"><span style="font-weight: 600;">${sessionScope.sessionName}</span>님
							안녕하세요!</li>
					</c:when>
					<c:when test="${sessionScope.sessionAuth!='H'}">
						<li><a href="logout.go">로그아웃</a></li>
						<li><a href="${root}mypage.go">My Page</a></li>
						<li style="float: left;"><span style="font-weight: 600;">${sessionScope.sessionName}</span>님
							안녕하세요!</li>
					</c:when>
				</c:choose>
			</ul>
		</c:if>
	</div>
	<header>
		<div class="slider" style="overflow:hidden;">
			<div>
				<img src="${root}images/banner_01.jpg" class="zoomin_main">
			</div>
			<div>
				<img src="${root}images/banner_02.jpg" class="zoomin_main">
			</div>
		</div>
		<div class="header">
			<div class="header_wrap">
				<div class="logo">
					<h1>
						<a href="${root}main.go"><img class="zoomin_main" src="${root}images/logo.png"
							alt="TRAVEL ASIA"></a><span>Travel Asia</span>
					</h1>
				</div>
				<nav>
					<ul>
						<li><a href="${root}invoicelist.board">문의사항</a></li>
						<li><a href="${root}noticelist.board">공지사항</a></li>
						<li><a href="${root}list.product">여행상품</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
</body>
</html>