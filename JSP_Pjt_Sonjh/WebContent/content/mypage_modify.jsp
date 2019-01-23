<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
/* 이미지 미리보기 */
var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
  };
  
/*
	input type="file" 에 추가할 내용 : accept="image/*" onchange="loadFile(event)"
	img에 추가할 속성 : id="output"
	
*/
</script>
</head>
<body>
	<section class="main_container">
		<article>
			<form action="${root}modifyPro.go" method="post" enctype="multipart/form-data">
			<!--
				파일을 업로드 하기 위해서는 form 태그를 post 방식으로 전송하고, 인코딩 타입을 multipart/form-data로 지정해야 한다.
			-->
				<div class="my_info">
					<div class="my_info_left">
						<div class="my_info_profile">
							<label for="member_image" class="fileContainer"><input type="file" name="member_image" accept="image/*" onchange="loadFile(event)" style="width: 10px;">이미지 변경</label>
							
							<img src="${root}images/${vo.member_image}" style="opacity:0.1;border:1px solid #333;" id="output">
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
							<c:if test="${selectCnt==1 or selectCnt==2}">
								<h2>- 정보 수정 -</h2>
								<table class="mypage_modify">
									<tr>
										<th>이름</th>
										<td>${vo.name}</td>
									</tr>
									<tr>
										<th>비밀번호</th>
										<td><input type="password" maxlength="32" name="pwd" placeholder="비밀번호를 입력해주세요" required></td>
									</tr>
									<tr>
										<th>비밀번호 확인</th>
										<td><input type="password" maxlength="32" name="re_pwd" placeholder="비밀번호를 한번 더 입력해주세요" required></td>
									</tr>
									<tr>
										<th>이메일</th>
										<td><input type="email" maxlength="32" placeholder="이메일 주소를 입력해주세요" name="email" required></td>
									</tr>
									<tr>
										<th>전화번호</th>
										<td><input type="text" maxlength="30" placeholder="전화번호를 입력해주세요" name="phone" required></td>
									</tr>
									<tr>
										<th>주소</th>
										<td><input type="text" maxlength="100" placeholder="주소를 입력해주세요" name="address"></td>
									</tr>
								</table>
								<div class="info_modify">
									<input type="submit" value="정보 저장">
								</div>
							</c:if>
							<c:if test="${selectCnt!=1 and selectCnt!=2}">
								<div style="width: 100%;height:100%;">
									<img src="${root}images/pwd.gif" style="max-width: 600px;max-height:300px;;">
									<script type="text/javascript">
										setTimeout(function() {
											errorAlert(passwordError);
										}, 300);
									</script>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</article>
	</section>
</body>
</html>