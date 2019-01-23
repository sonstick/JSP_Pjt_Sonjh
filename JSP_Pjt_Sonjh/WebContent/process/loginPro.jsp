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
	<c:if test="${selectCnt == 0}">
		<script type="text/javascript">
			errorAlert(loginError)
		</script>
	</c:if>
	<c:if test="${selectCnt != 0}">
		<script type="text/javascript">
		window.location='main.go';
		/* window.location='sucess.go'; */
		</script>
	</c:if>
</body>
</html>