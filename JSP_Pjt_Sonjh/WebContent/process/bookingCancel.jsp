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
			errorAlert(deleteBookingError)
		</script>
	</c:if>
	<c:if test="${deleteCnt != 0}">
		<script type="text/javascript">
			alert("예약이 성공적으로 취소되었습니다.");
			<c:redirect url="bookinglist.go" />
		</script>
	</c:if>
</body>
</html>