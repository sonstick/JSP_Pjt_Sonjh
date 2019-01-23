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
	<c:if test="${updateCnt == 0}">
		<script type="text/javascript">
			errorAlert(updateError);
		</script>
	</c:if>
	<c:if test="${updateCnt != 0}">
		<script type="text/javascript">
			alert("회원정보 수정이 완료 되었습니다.");
			window.location='member.admin';
		</script>
	</c:if>
</body>
</html>