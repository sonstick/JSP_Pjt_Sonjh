<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.OrderVO" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/order.css" />
	<script>
		function orderCancel() {
			var result = confirm("정말 취소하시겠습니까?");
			if(result == true) {
				window.location='bookingCancel.order?no=${vo.no}';
			}
		}
	</script>
</head>
<body>
	<section class="main_container">
        <article>
        	<div class="order_wrap">
				<h2>예약정보</h2>
        		<hr>
        		<table>
        			<tr>
        				<th>예약번호</th>
        				<td>${vo.no}</td>
        				<th>예약자명</th>
        				<td>${vo.name}</td>
        			</tr>
        			<tr>
        				<th>전화번호</th>
        				<td>${vo.phone}</td>
        				<th>이메일</th>
        				<td>${vo.email}</td>
        			</tr>
        			<tr>
        				<th>여행국가</th>
        				<td>${vo.country}</td>
        				<th>여행도시</th>
        				<td>${vo.city}</td>
        			</tr>
        			<tr>
        				<th>예약상품</th>
        				<td colspan="3">${vo.p_name}</td>
        			</tr>
        			<tr>
        				<th>출발일</th>
        				<td>${vo.depart_date}</td>
        				<th>도착일</th>
        				<td>${vo.return_date}</td>
        			</tr>
        		</table>
        	</div>
        	<div class="mypage_booking_controls">
        		<button type="button" onclick="orderCancel();">예약취소</button>
			</div>
        </article>
    </section>
</body>
</html>