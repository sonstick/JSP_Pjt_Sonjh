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
				<h4>여행상품</h4>
				<hr>
				<table>
					<colgroup>
						<col width="6%" />
						<col width="15%" />
						<col width="15%" />
						<col width="52%" />
						<col width="13%" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>상품번호</th>
							<th>여행도시</th>
							<th>상품명</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${productCnt > 0}">
							<c:forEach var="dto" items="${productDtos}">
								<tr>
									<td>
										${productNumber}
										<c:set var ="productNumber" value="${productNumber-1}" />
									</td>
									<td>
										${dto.p_no}
									</td>
									<td>
										${dto.country} | ${dto.city}
									</td>
									<td class="subject product_hover">
										<a href="productView.product?p_no=${dto.p_no}&pageNum=${productPageNum}" target="_blank">${dto.p_name}</a>
										<span class="hover_view">
											<script>
												function productDel(p_no) {
													var result = confirm("정말 삭제하시겠습니까?");
													if(result == true) {
														window.location="productDel.product?p_no="+p_no;
													}
												}
											</script>
											<%-- <a href="productModify.product?p_no=${dto.p_no}&pageNum=${productPageNum}">상품수정</a> --%>
											<button type="button" onclick="window.location='productModify.product?p_no=${dto.p_no}&pageNum=${productPageNum}'">상품수정</button>
											<button type="button" style="background:#c11300;" onclick="productDel(${dto.p_no});">상품삭제</button>
											<!-- <a href="" style="background:#c11300;">상품삭제</a> -->
										</span>
									</td>
									<td>
										<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.p_reg_date}" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${productCnt == 0}">
							<tr>
								<td colspan="6" style="text-align: center;height: 500px;">현재 등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="pager">
					<ol>
						<c:if test="${productCnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${productStartPage > productPageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${productCurrentPage - productPageBlock}">&#60;</a>
								</li>
							</c:if>
				
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${productStartPage}" end="${productEndPage}">
								<c:if test="${i == productCurrentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
				
								<c:if test="${i != productCurrentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
				
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${productPageCount > productEndPage}">
								<li>
									<a href="invoicelist.board?pageNum=${productCurrentPage + productPageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${productPageCount}">&#62;&#62;</a>
								</li>
							</c:if>
						</c:if>
					</ol>
				</div>
				<button type="button" class="admin_btn" onclick="window.location='productWriteForm.product?p_no=0&pageNum=1'">상품등록</button>
			</div>
		</div>
	</article>
</body>
</html>