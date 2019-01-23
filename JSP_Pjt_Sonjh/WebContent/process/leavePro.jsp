<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		body {
			display: none;
		}
	</style>
</head>
<body>
	<c:if test="${deleteCnt == 0}">
		<script type="text/javascript">
			errorAlert(deleteError);
		</script>
	</c:if>
	<c:if test="${deleteCnt != 0}">
		<script type="text/javascript">
			alert("회원탈퇴가 완료 되었습니다.");
			window.location='${root}leaveSucess.go';
		</script>
	</c:if>
</body>
</html>