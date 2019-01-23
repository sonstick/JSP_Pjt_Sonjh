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
				<h4>고객 문의사항</h4>
				<hr>
				<table>
					<colgroup>
						<col width="6%" />
						<col width="15%" />
						<col width="9%" />
						<col width="53%" />
						<col width="13%" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>답변여부</th>
							<th>고객명</th>
							<th>제목</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${invoiceCnt > 0}">
							<c:forEach var="dto" items="${invoiceDtos}">
								<tr>
									<td>
										${invoiceNumber}
										<c:set var ="invoiceNumber" value="${invoiceNumber-1}" />
									</td>
									<td>
										<c:if test="${dto.cmt != 'isNull'}">
			                    			답변완료
				                    	</c:if>
				                    	<c:if test="${dto.cmt == 'isNull'}">
				                    		답변전
				                    	</c:if>
									</td>
									<td>
										${dto.name}
									</td>
									<td class="subject">
										<a href="invoiceView.board?no=${dto.no}&pageNum=${invoicePageNum}&number=${invoiceNumber+1}" target="_blank">${dto.subject}</a>
									</td>
									<td>
										<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${invoiceCnt == 0}">
							<tr>
								<td colspan="6" style="text-align: center;height: 500px;">현재 등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="pager">
					<ol>
						<c:if test="${invoiceCnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${invoiceStartPage > invoicePageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${invoiceCurrentPage - invoicePageBlock}">&#60;</a>
								</li>
							</c:if>
				
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${invoiceStartPage}" end="${invoiceEndPage}">
								<c:if test="${i == invoiceCurrentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
				
								<c:if test="${i != invoiceCurrentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
				
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${invoicePageCount > invoiceEndPage}">
								<li>
									<a href="invoicelist.board?pageNum=${invoiceCurrentPage + invoicePageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${invoicePageCount}">&#62;&#62;</a>
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