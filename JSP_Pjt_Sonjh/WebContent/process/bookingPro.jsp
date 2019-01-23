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
			errorAlert(insertBookingError);
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<script type="text/javascript">
			alert("예약이 완료되었습니다..");
			window.location='${root}bookinglist.go';
		</script>
	</c:if>
</body>
</html>