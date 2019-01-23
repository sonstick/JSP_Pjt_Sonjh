<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<div class="event_banner">
        <img src="${root}images/event_banner.png">
    </div>
    <section class="main_container">
        <article>
            <div class="product">
                <ul>
                	<c:forEach var="dto" items="${dtos}">
                		<li>
               				<div class="p_l_thumbs">
	                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
	                				<img src="${root}/images/product/${dto.p_thumbs}">
	                			</a>
	                		</div>
	                		<div class="p_l_info">
	                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
	                				${dto.country} | ${dto.city}
	                			</a>
	                		</div>
	                		<div class="p_l_subject">
	                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
	                				${dto.p_name}
	                			</a>
	                		</div>
	                	</li>
                	</c:forEach>
                </ul>
            </div>
        </article>
    </section>
</body>
</html>