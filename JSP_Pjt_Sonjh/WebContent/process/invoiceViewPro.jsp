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
	<c:if test="${pwdCnt == 0}">
		<script type="text/javascript">
			errorAlert(msg_pwd_check);
		</script>
	</c:if>
	<c:if test="${pwdCnt != 0}">
		<script type="text/javascript">
			window.location='${root}invoiceView.board?no=${no}&pageNum=${pageNum}&number=${number+1}';
		</script>
	</c:if>
</body>
</html>