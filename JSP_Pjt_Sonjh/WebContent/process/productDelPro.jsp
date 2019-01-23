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
			errorAlert(DeleteError);
		</script>
	</c:if>
	<c:if test="${deleteCnt != 0}">
		<script type="text/javascript">
			alert("상품이 삭제되었습니다..");
			<c:redirect url="product.admin" />
		</script>
	</c:if>
</body>
</html>