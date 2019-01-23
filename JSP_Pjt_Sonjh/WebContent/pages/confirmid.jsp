<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복확인</title>
<style>
	ul {
		width: 300px;
		margin: 30px auto;
	}
	
	li {
		text-align: center;
		list-style-type: none;
		line-height: 24px;
	}
	
	li:first-child {
		margin: 15px;
	}
	
	input[type=text] {
		background: #efefef;
		border: 1px solid #ddd;
		padding: 5px;
	}
	
	input[type=submit], input[type=reset] {
		background-color: #367fa9;
		color: #fff;
		border-radius: 5px;
		font-size: 16px;
		cursor: pointer;
		padding: 5px;
	}
	
	input[type=button] {
		background-color: #367fa9;
		color: #fff;
		border-radius: 5px;
		font-size: 16px;
		cursor: pointer;
		padding: 5px;
		width: 100px;
	}
	
	input[type=submit]:hover, input[type=reset]:hover, input[type=button]:hover {
		opacity: .8;
		font-weight: 600;
	}
	
	span {
		color: #c11300;
		font-weight: 600;
	}
</style>
</head>
<body>
	<form action="confirmId.go">
		<div>
			<c:if test="${selectCnt == 1}">
				<ul>
					<li><p><span>${id}</span>(이)가 이미 존재합니다.</p><p>다른 ID를 입력해 주세요.</p></li>
					<li>
						<input type="text" name="id" maxlength="20" required autofocus>
						<input type="submit" value="확인">
						<input type="reset" value="취소" onclick="self.close();">
					</li>
				</ul>
			</c:if>
			<c:if test="${selectCnt != 1}">
				<ul style="margin-top:50px;">
					<li>${id}은(는) 사용할 수 있습니다.</li>
					<li>
						<input type="button" value="확인" onclick="setId('${id}');" placeholder="${id}">
					</li>
				</ul>
			</c:if>
		</div>
	</form>
</body>
</html>