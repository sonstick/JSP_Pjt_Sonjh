<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/signup.css" />
</head>
<body>
	<section class="main_container container">
        <h2>회원가입</h2>
        <hr>
        <div class="signup_wrap">
            <form action="signupPro.go" method="post" name="signupform" onsubmit="return signupCheck();">
            <input type="hidden" name="hiddenId" value="0">
                <div class="signup_div">
	                <h3>필수입력</h3>
	                <ul class="signup_ul">
	                    <li>
	                        <label>
								<span class="label">ID</span>
		                        <input type="text" maxlength="32" style="width:160px;" placeholder="아이디를 입력해주세요" name="id" required autofocus>
		                        <input type="button" class="confirmbtn" value="중복확인" name="idCheck" onclick="confirmId();">
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">비밀번호</span>
	                        	<input type="password" maxlength="32" placeholder="비밀번호를 입력해주세요" name="pwd" required>
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">비밀번호 확인</span>
	                        	<input type="password" maxlength="32" placeholder="비밀번호를 한번 더 입력해주세요" name="re_pwd" required>
	                        </label>
	                    </li>
	                </ul>
                </div>
				<div class="signup_div signup_div_right_col">
					<ul class="signup_ul">
						<li>
	                        <label>
	                        	<span class="label">이름</span>
	                        	<input type="text" maxlength="10" placeholder="이름을 입력해주세요" name="name" required>
	                        </label>
	                    </li>
						<li>
	                        <label>
	                        	<span class="label">이메일 주소</span>
	                        	<input type="email" maxlength="32" placeholder="이메일 주소를 입력해주세요" name="email" required>
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">전화번호</span>
	                        	<input type="text" maxlength="30" placeholder="전화번호를 입력해주세요" name="phone" required>
	                        </label>
	                    </li>
					</ul>
                 </div>
                 
                 <div class="signup_div_notnull">
                 	<h3>선택입력</h3>
					<ul class="signup_ul">
	                    <li>
	                        <label>
	                        	<span class="label">주소</span>
	                        	<input type="text" maxlength="100" placeholder="주소를 입력해주세요" name="address">
	                        </label>
	                    </li>
	                </ul>
                 </div>
                <div class="button" id="button">
                    <input type="submit" class="signupbtn" value="회원가입">
                    <input type="reset" class="cancelbtn" value="취소">
                </div>
            </form>
        </div>
    </section>
</body>
</html>