<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
	<article>
		<div class="container">
			<div class="admin_tbl">
				<h4>예약관리</h4>
				<hr>
				<table>
					<colgroup>
						<col width="6%" />
						<col width="14%" />
						<col width="9%" />
						<col width="29%" />
						<col width="15%" />
						<col width="23%" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>예약번호</th>
							<th>예약고객</th>
							<th>예약상품</th>
							<th>고객 연락처</th>
							<th>예약일정</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${bookingCnt > 0}">
							<c:forEach var="dto" items="${bookingDtos}">
								<tr>
									<td>
										${bookingNumber}
										<c:set var ="bookingNumber" value="${bookingNumber-1}" />
									</td>
									<td>
										${dto.no}
									</td>
									<td>
										${dto.name}
									</td>
									<td class="subject">
										${dto.p_name}
									</td>
									<td>
										${dto.phone}
									</td>
									<td>
										<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.depart_date}" /> - 
										<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.return_date}" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${bookingCnt == 0}">
							<tr>
								<td colspan="6" style="text-align: center;height: 500px;">현재 등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="pager">
					<ol>
						<c:if test="${bookingCnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${bookingStartPage > bookingPageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${bookingCurrentPage - bookingPageBlock}">&#60;</a>
								</li>
							</c:if>
				
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${bookingStartPage}" end="${bookingEndPage}">
								<c:if test="${i == bookingCurrentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
				
								<c:if test="${i != bookingCurrentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
				
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${bookingPageCount > bookingEndPage}">
								<li>
									<a href="invoicelist.board?pageNum=${bookingCurrentPage + bookingPageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${bookingPageCount}">&#62;&#62;</a>
								</li>
							</c:if>
						</c:if>
					</ol>
				</div>
			</div>
		</div>
	</article>
</body>
</html>