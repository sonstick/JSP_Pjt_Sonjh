<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="vo.OrderVO" %>
<%@ page import="vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/board.css" />
</head>
<body>
	<section class="main_container">
		<article>
			<div class="my_info">
				<div class="my_info_left">
					<div class="my_info_profile">
						<img src="${root}images/${vo.member_image}">
					</div>
					<div class="my_info_list">
						<ul>
							<li><a href="modify_pwdcheck.go">정보 수정</a></li>
                            <li><a href="bookinglist.go">예약 내역</a></li>
                            <li><a href="scraplist.go">스크랩 목록</a></li>
						</ul>
					</div>
				</div>
				<div class="my_info_right">
					<div class="my_info_right_inner">
						<h2>- 예약 내역 -</h2>
						<div>
							<table class="mypage_bookinglist">
								<thead>
									<tr>
										<th>예약번호</th>
										<th>여행지</th>
										<th>예약상품</th>
										<th>예약일</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="mypage_tbody">
							<table class="mypage_bookinglist">
								<tbody>
									<c:if test="${bookingCnt != 0}">
										<c:forEach var="dto" items="${bookingDtos}">
											<tr>
												<td>
													${dto.no}
												</td>
												<td>${dto.country} | ${dto.city}</td>
												<td>
													<a href="bookingInfo.go?no=${dto.no}&pageNum=${pageNum}&number=${number+1}">${dto.p_name}</a>
												</td>
												<td>
													<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${bookingCnt == 0}">
										<tr>
											<td style="width: 599px;height: 200px;">예약정보가 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
								<tfoot class="pager">
									<tr>
										<c:if test="${bookingCnt > 0}">
											<td style="width: 599px;">
												<!-- 처음<< | 이전블록< -->
												<c:if test="${bookingStartPage > bookingPageBlock}">
													<a href="bookinglist.go">&#60;&#60;</a>
													<a href="bookinglist.go?pageNum=${bookingCurrentPage - bookingPageBlock}">&#60;</a>
												</c:if>
						
												<!-- 블록 내의 페이지 번호 -->
												<c:forEach var="i" begin="${bookingStartPage}" end="${bookingEndPage}">
													<c:if test="${i == bookingCurrentPage}">
														<span><b class="this_page">${i}</b></span>
													</c:if>
													<c:if test="${i != bookingCurrentPage}">
														<a href="bookinglist.go?pageNum=${i}">${i}</a>
													</c:if>
												</c:forEach>
						
												<!-- 다음블록> | 마지막>> -->
												<c:if test="${bookingPageCount > bookingEndPage}">
														<a href="bookinglist.go?pageNum=${bookingCurrentPage + bookingPageBlock}">&#62;</a>
														<a href="bookinglist.go?pageNum=${bookingPageCount}">&#62;&#62;</a>
												</c:if>
											</td>
										</c:if>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</article>
	</section>
</body>
</html>