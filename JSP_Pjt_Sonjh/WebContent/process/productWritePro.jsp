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
			alert("상품이 등록되었습니다.");
			window.location='product.admin';
		</script>
	</c:if>
</body>
</html>