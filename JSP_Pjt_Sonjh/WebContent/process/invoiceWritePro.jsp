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
	<c:if test="${insertCnt == 0}">
		<script type="text/javascript">
			errorAlert(writeError)
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<script type="text/javascript">
			alert("글이 등록되었습니다.");
			window.location='invoicelist.board?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>