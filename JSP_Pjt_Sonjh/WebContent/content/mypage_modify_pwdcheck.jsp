<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		.my_info_right_inner>form {
			position: relative;
			top: 110px;
			left: 100px;
		}
		
		input {
			line-height: 1;
		}
		
		input[type=submit] {
			background-color: #367fa9;
			color: #fff;
			border-radius: 5px;
			font-size: 16px;
			cursor: pointer;
			padding: 5px;
			width: 50px;
			height: 32px;
		}
		
		input[type=submit]:hover {
			opacity: .8;
			font-weight: 600;
		}
		
		input[type=password] {
			background: #efefef;
			border: 1px solid #ddd;
			padding: 5px;
			margin-left: 10px;
			font-size: 14px;
			height: 20px;
		}
	</style>
</head>
<body>
	<section class="main_container">
		<article>
			<div class="my_info">
				<div class="my_info_left">
					<div class="my_info_profile">
						<img src="${root}images/${vo.member_image}">
					</div>
					<div class="my_info_list">
						<ul>
							<li><a href="modify_pwdcheck.go">정보 수정</a></li>
                            <li><a href="bookinglist.go">예약 내역</a></li>
                            <li><a href="scraplist.go">스크랩 목록</a></li>
						</ul>
					</div>
				</div>
				<div class="my_info_right">
					<div class="my_info_right_inner">
						<form action="${root}modify.go" method="post">
							<span>비밀번호 확인 : </span>
							<input type="password" name="pwd" maxlength="32" placeholder="비밀번호를 입력해주세요" required autofocus>
							<input type="submit" value="확인">
						</form>
					</div>
				</div>
			</div>
		</article>
	</section>
</body>
</html>