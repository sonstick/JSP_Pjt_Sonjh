<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
    <article>
        <div class="container admin_main">
        	<c:if test="${sessionScope.sessionAuth=='H'}">
	        	<div class="order">
	                <h4>예약현황</h4>
	                <hr>
	                <ul>
	                	<c:if test="${bookingCnt > 0}">
	                		<c:forEach var="dto" items="${bookingDtos}">
	                			<li>
	                				<a href="booking.admin">
		                				"${dto.name}" 님이 예약을 하였습니다.
		                				<span class="admin_date">
		                					<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
		                				</span>
		                			</a>
	                			</li>
	                		</c:forEach>
	                	</c:if>
	                </ul>
	            </div>
	            <div class="new_member">
	                <h4>신규가입</h4>
	                <hr>
	                <ul>
	                	<c:if test="${memberCnt > 0}">
	                		<c:forEach var="dto" items="${memberDtos}">
	                			<c:if test="${dto.auth != 'H'}">
	                				<li>
	                					<a href="memberInfo.go?id=${dto.id}">
			                				"${dto.name}" 님이 가입하였습니다.
					                        <span class="admin_date">
					                        	<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
					                        </span>
										</a>
				                    </li>
	                			</c:if>
	                		</c:forEach>
	                	</c:if>
	                	<c:if test="${memberCnt == 0}">
	                   		<c:if test="${dto.auth != 'H'}">
	                   			<li>회원이 없습니다.</li>
	                   		</c:if>
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
		                					<fmt:formatDate type="both" pattern="yyyy년 MM월 dd일, mm분" value="${dto.reg_date}" />
		                				</span>
		                			</a>
	                			</li>
	                		</c:forEach>
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
		                				<span class="admin_invoice">
		                					"${dto.name}" | 
		                					<c:if test="${dto.cmt == 'isNull'}">
		                						답변전
		                					</c:if>
		                					<c:if test="${dto.cmt != 'isNull'}">
		                						답변완료
		                					</c:if>
		                				</span>
		                				<span style="max-width: 500px; display: inline-block; overflow: hidden;">
		                					${dto.subject}
		                				</span>
		                				<span class="admin_date">
		                					<fmt:formatDate type="both" pattern="yyyy년 MM월 dd일, mm분" value="${dto.reg_date}" />
		                				</span>
		                			</a>
	                			</li>
	                		</c:forEach>
	                	</c:if>
	                </ul>
	            </div>
        	</c:if>
        	<c:if test="${sessionScope.sessionAuth!='H'}">
        		<img src="${root}images/security.png" style="width: 100%; margin-top:200px;">
        	</c:if>
        </div>
    </article>
</body>
</html>