<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
	<article>
	    <div class="container admin_main">
	        <div class="product">
	            <h4>여행상품</h4>
	            <hr>
	            <ul>
	            	<c:if test="${productCnt > 0}">
	            		<c:forEach var="dto" items="${productDtos}">
	            			 <li>
	            			 	<a href="productModify.product?p_no=${dto.p_no}&pageNum=1">
		            			 	<b>${dto.p_name}</b> 상품이 등록되었습니다.
			            			 <span class="admin_date">
			            			 	<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.p_reg_date}" />
			            			 </span>
	            			 	</a>
	            			 </li>
	            		</c:forEach>
	            	</c:if>
	            	<c:if test="${productCnt == 0}">
						<li style="text-align: center;height: 155px;">현재 등록된 상품이 없습니다.</li>
					</c:if>
	            </ul>
	        </div>
	        <div class="notice">
	            <h4>공지사항</h4>
	            <hr>
	            <ul>
	            	<c:if test="${noticeCnt > 0}">
	            		<c:forEach var="dto" items="${noticeDtos}">
	            			<li>
			                    <a href="noticeView.board?no=${dto.no}&pageNum=1&number=${number+1}">
			                    	<span class="admin_notice">
				                    	${dto.country} | ${dto.city}
				                    </span>
				                    ${dto.subject}
				                    <span class="admin_date">
				                    	<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
				                    </span>
			                    </a>
			                 </li>
	            		</c:forEach>
	            	</c:if>
	            	<c:if test="${noticeCnt == 0}">
						<li style="text-align: center;height: 155px;">현재 등록된 게시물이 없습니다.</li>
					</c:if>
	            </ul>
	        </div>
	        <div class="invoice">
	            <h4>고객문의</h4>
	            <hr>
	            <ul>
		            <c:if test="${invoiceCnt > 0}">
	            		<c:forEach var="dto" items="${invoiceDtos}">
	            			<li>
			                    <a href="invoiceView.board?no=${dto.no}&pageNum=1&number=${number+1}">
			                    	<span class="admin_notice">
				                    	<c:if test="${dto.cmt != 'isNull'}">
				                    		답변완료
				                    	</c:if>
				                    	<c:if test="${dto.cmt == 'isNull'}">
				                    		답변전
				                    	</c:if>
				                    </span>
				                    "${dto.name}"님의 문의가 등록되었습니다. (${dto.subject})
				                    <span class="admin_date">
				                    	<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
				                    </span>
			                    </a>
			                 </li>
	            		</c:forEach>
	            	</c:if>
	            	<c:if test="${invoiceCnt == 0}">
						<li style="text-align: center;height: 155px;">현재 등록된 게시물이 없습니다.</li>
					</c:if>
	            </ul>
	        </div>
	    </div>
	</article>
</body>
</html>