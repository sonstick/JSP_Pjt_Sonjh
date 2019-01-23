<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Travel Asia Control panel </title>
<%@ include file="../setting_admin.jsp" %>
<script>
	function admin() {
		if(${sessionScope.sessionAuth!='H'}) {
			alert("접근 권한이 없습니다.");
			window.location="main.go";
		}
	}
</script>
</head>
<body onload="admin();">
	<header>
        <div class="logo">
            <h1><a href="${root}main.go"><img src="${root}images/logo.png"></a></h1>
        </div>
        <div class="gnb">
            <h2><a href="${root}main.admin">관리자 센터</a><small>( Control panel )</small></h2>
        </div>
    </header>
</body>
</html>