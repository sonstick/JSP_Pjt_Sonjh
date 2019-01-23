<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<script>
		function scrap() {
			if(${sessionScope.sessionId != null}){
				var result = confirm("스크랩 하시겠습니까?");
				if(result == true) {
					window.location="scrap.order?p_no=${dto.p_no}";
				}
			} else {
				alert("회원정보가 없습니다.\n로그인 하여 주세요.");
			}
		}
	</script>
</head>
<body>
	<section class="main_container">
        <article>
        	<div class="product_wrap">
        		<div class="product_header">
        			<h2><span>${dto.country} | ${dto.city}</span>${dto.p_name}</h2>
        			<c:if test="${sessionScope.sessionId != null}">
	        			<%-- 
	        			<ul class="order">
	        				<li>
	        					<a href="booking.order?p_no=${dto.p_no}">예약하기</a>
	        				</li>
	        				<li>
	        					<a href="scrap.order">스크랩하기</a>
	        				</li>
	        			</ul>
	        			--%>
        			</c:if>
        		</div>
        		<c:if test="${sessionScope.sessionAuth!='H'}">
        			<div id="quick">
						<ul class="order">
							<li>-예약 및 스크랩-</li>
		       				<li>
		       					<button type="button" onclick="window.location='booking.order?p_no=${dto.p_no}'">예약하기</button>
		       					<%-- <a href="booking.order?p_no=${dto.p_no}"><span>예약하기</span></a> --%>
		       				</li>
		       				<li>
		       					<button type="button" onclick="scrap();">스크랩하기</button>
		       				</li>
		       			</ul>
					</div>
        		</c:if>
				
				<!-- 퀵스크롤 -->
				<script type="text/javascript">
					var quick_menu = $('#quick');
					var quick_top = 0;
					quick_menu.css('top', $(window).height());
					$(document).ready(function () {
						quick_menu.animate({
							"top": $(document).scrollTop() + quick_top + "px"
						}, 500);
						$(window).scroll(function () {
							quick_menu.stop();
							quick_menu.animate({
								"top": $(document).scrollTop() + quick_top + "px"
							}, 500);
						});
					});
				</script>
        		<div class="product_content">
        			<p>${dto.p_content}</p>
        			<img src="${root}images/product/${dto.p_image_1}">
        			<c:if test="${dto.p_image_2 != p_default.jpg}">
        				<img src="${root}images/product/${dto.p_image_2}">
        			</c:if>
        			<c:if test="${dto.p_image_3 != p_default.jpg}">
        				<img src="${root}images/product/${dto.p_image_3}">
        			</c:if>
        			<c:if test="${dto.p_image_4 != p_default.jpg}">
        				<img src="${root}images/product/${dto.p_image_4}">
        			</c:if>
        		</div>
        	</div>
        </article>
    </section>
</body>
</html>