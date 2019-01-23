<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${root}css/admin_member.css" />
<script>
	function goOut() {
		var result = confirm("정말 ${vo.name}님을 떠나보내시겠습니까?");
		if(result == true) {
			window.location='adminLeave.go?id=${vo.id}';
		}
	}
</script>
</head>
<body>
	<article>
		<div class="container">
			<form action="adminModifyPro.go" method="post">
                <div class="admin_member_div">
	                <ul>
	                    <li>
	                        <label>
								<span class="label">ID</span>
								<input type="text" name="id" value="${vo.id}" readonly class="readonly">
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">비밀번호</span>
	                        	<input type="password" maxlength="32" name="pwd" value="${vo.pwd}" autofocus required>
	                        </label>
	                    </li>
						<li>
	                        <label>
	                        	<span class="label">이름</span>
	                        	<input type="text" name="name" value="${vo.name}" readonly class="readonly">
	                        </label>
	                    </li>
						<li>
	                        <label>
	                        	<span class="label">이메일 주소</span>
	                        	<input type="text" name="email" value="${vo.email}" readonly class="readonly">
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">전화번호</span>
	                        	<input type="text" maxlength="30" value="${vo.phone}" name="phone" required>
	                        </label>
	                    </li>
	                    <li>
	                        <label>
	                        	<span class="label">주소</span>
	                        	<input type="text" maxlength="100" value="${vo.address}" name="address">
	                        </label>
	                    </li>
	                </ul>
                 </div>
                <div class="button_set">
                    <input type="submit" value="정보수정" class="admin_member_btn">
                    <input type="button" value="회원탈퇴" class="admin_member_btn" onclick="goOut();" style="background: #c11300">
                </div>
            </form>
		</div>
	</article>
</body>
</html>