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
				<h4>공지사항 | 이벤트</h4>
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
							<th>여행국가</th>
							<th>여행도시</th>
							<th>제목</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${noticeCnt > 0}">
							<c:forEach var="dto" items="${noticeDtos}">
								<tr>
									<td>
										${noticeNumber}
										<c:set var ="noticeNumber" value="${noticeNumber-1}" />
									</td>
									<td>
										${dto.country}
									</td>
									<td>
										${dto.city}
									</td>
									<td class="subject">
										<a href="noticeView.board?no=${dto.no}&pageNum=${noticePageNum}&number=${noticeNumber+1}" target="_blank">${dto.subject}</a>
									</td>
									<td>
										<fmt:formatDate type="both" pattern="yyyy.MM.dd" value="${dto.reg_date}" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${noticeCnt == 0}">
							<tr>
								<td colspan="6" style="text-align: center;height: 500px;">현재 등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="pager">
					<ol>
						<c:if test="${noticeCnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${noticeStartPage > noticePageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${noticeCurrentPage - noticePageBlock}">&#60;</a>
								</li>
							</c:if>
				
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${noticeStartPage}" end="${noticeEndPage}">
								<c:if test="${i == noticeCurrentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
				
								<c:if test="${i != noticeCurrentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
				
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${noticePageCount > noticeEndPage}">
								<li>
									<a href="invoicelist.board?pageNum=${noticeCurrentPage + noticePageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${noticePageCount}">&#62;&#62;</a>
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