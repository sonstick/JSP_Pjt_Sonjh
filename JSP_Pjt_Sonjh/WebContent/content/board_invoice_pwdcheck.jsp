<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		.board {
			width: 360px;
			margin: 0 auto;
			padding: 184px 0;
		}
		
		.board>form>input {
			line-height: 1;
		}
		
		.board>form>input[type=submit] {
			background-color: #367fa9;
			color: #fff;
			border-radius: 5px;
			font-size: 16px;
			cursor: pointer;
			padding: 5px;
			width: 50px;
			height: 32px;
		}
		
		.board>form>input[type=submit]:hover {
			opacity: .8;
			font-weight: 600;
		}
		
		.board>form>input[type=password] {
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
			<div class="board">
				<form action="invoiceViewPro.board?no=${no}&pageNum=${pageNum}&number=${number+1}" method="post">
					<span>비밀번호 확인 : </span>
					<input type="password" name="pwd" maxlength="32" placeholder="비밀번호를 입력해주세요" required autofocus>
					<input type="submit" value="확인">
				</form>
			</div>
		</article>
	</section>
</body>
</html>