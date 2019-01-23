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
			errorAlert(updateProductError)
		</script>
	</c:if>
	<c:if test="${updateCnt != 0}">
		<script type="text/javascript">
			alert("상품이 수정되었습니다.");
			window.location='product.admin?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>