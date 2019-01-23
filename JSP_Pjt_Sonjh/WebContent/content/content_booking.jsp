<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/order.css" />
</head>
<body>
	<section class="main_container">
        <article>
        	<div class="order_wrap">
        		<form action="bookingPro.order" method="get">
        			<c:if test="${sessionScope.sessionId != null}">
        				<input type="hidden" name="p_no" value="${productDto.p_no}">
	        			<h2>상품 예약</h2>
	        			<hr>
	       				<ul class="order_ul">
		                	<li>
		                		<ul>
		                			<li>가는날</li>
		                			<li>
		                				<input type="date" name="depart_date" required="required">
		                			</li>
		                		</ul>
		                	</li>
		                	<li>
		                		<ul>
		                			<li>오는날</li>
		                			<li>
		                				<input type="date" name="return_date" required="required">
		                			</li>
		                		</ul>
		                	</li>
		                </ul>
		                <div class="button">
		                	 <input type="submit" class="orderbtn" value="예약하기">
		                </div>
	       				<h3>상품정보</h3>
		                <table>
		                	<tr>
		                		<th rowspan="3"><img src="${root}images/product/${productDto.p_thumbs}" alt="" /></th>
		                		<th>상품명</th>
		                		<td>${productDto.p_name}</td>
		                	</tr>
		                	<tr>
		                		<th>여행국가</th>
		                		<td>${productDto.country}</td>
		                	</tr>
		                	<tr>
		                		<th>여행도시</th>
		                		<td>${productDto.city}</td>
		                	</tr>
		                </table>
        			</c:if>
        			<c:if test="${sessionScope.sessionId == null}">
        				<script type="text/javascript">
							errorAlert(loginBookingError);
						</script>
        			</c:if>
        		</form>
        	</div>
        </article>
    </section>
</body>
</html>