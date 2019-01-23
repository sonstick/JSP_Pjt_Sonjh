<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.OrderVO" %>
<%@ page import="vo.MemberVO" %>
<!DOCTYPE html>
<html>
<body>
	<section class="main_container">
		<article>
			<div class="my_info">
				<div class="my_info_left">
					<div class="my_info_profile">
						<img src="${root}images/default.jpg">
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
						<h2>- 스크랩 목록 -</h2>
						<div class="scrap">
							<ul>
								<c:if test="${scrapCnt != 0}">
									<c:forEach var="dto" items="${scrapDtos}">
											<li>
												<div>
													<a href="${root}productView.product?p_no=${dto.p_no}&pageNum=1">
														<img src="${root}/images/product/${dto.p_thumbs}">
													</a>
												</div>
												<div class="scrap_subject">
													<script>
														function scrapDel() {
															var result = confirm("정말 삭제하시겠습니까?");
															if(result == true) {
																window.location='scrapDel.order?p_no=${dto.p_no}';
															}
														}
													</script>
													<a href="${root}productView.product?p_no=${dto.p_no}&pageNum=1" >${dto.p_name}</a>
													<button type="button" onclick="scrapDel();"><span>삭제하기</span><img src="${root}images/recycle_bin.png" style="width: 20px; height: 20px;"></button>
												</div>
											</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
						<div class="pager" style="margin-top: 80px;">
							<c:if test="${scrapCnt > 0}">
								<c:if test="${ScrapStartPage > ScrapPageBlock}">
									<a href="scraplist.go">&#60;&#60;</a>
									<a href="scraplist.go?pageNum=${scrapCurrentPage - scrapPageBlock}">&#60;</a>
								</c:if>
							</c:if>
							<c:forEach var="i" begin="${scrapStartPage}" end="${scrapEndPage}">
								<c:if test="${i == scrapCurrentPage}">
									<span><b class="this_page">${i}</b></span>
								</c:if>
								<c:if test="${i != scrapCurrentPage}">
									<a href="scraplist.go?pageNum=${i}">${i}</a>
								</c:if>
							</c:forEach>
							<c:if test="${scrapPageCount > scrapEndPage}">
									<a href="scraplist.go?pageNum=${scrapCurrentPage + scrapPageBlock}">&#62;</a>
									<a href="scraplist.go?pageNum=${scrapgPageCount}">&#62;&#62;</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</article>
	</section>
</body>
</html>