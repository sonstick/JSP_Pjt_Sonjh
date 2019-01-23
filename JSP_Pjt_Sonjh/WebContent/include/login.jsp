<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${root}js/login.js"></script>
</head>
<body>
	<div class="window">
		<div class="login_wrap">
			<div class="login_window">
				<form action="logInPro.go" method="post">
					<fieldset>
						<legend>로그인</legend>
						<ul>
							<li><span>ID</span> <input type="text" name="id"
								maxlength="32" placeholder="아이디를 입력해주세요" required autofocus>
							</li>
							<li><span>비밀번호</span> <input type="password" name="pwd"
								maxlength="32" placeholder="비밀번호를 입력해주세요" required></li>
							<li class="signin"><input type="submit" value="로그인">
								<span><a href="${root}signup.go">회원가입</a></span></li>
						</ul>
					</fieldset>
				</form>
				<input type="button" class="close" value="창 닫기">
			</div>
		</div>
	</div>
</body>
</html>