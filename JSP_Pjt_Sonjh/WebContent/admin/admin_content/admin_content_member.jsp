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
				<h4>회원관리</h4>
				<hr>
				<table>
					<colgroup>
						<col width="6%" />
						<col width="10%" />
						<col width="9%" />
						<col width="20%" />
						<col width="15%" />
						<col width="41%" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일 주소</th>
							<th>전화번호</th>
							<th>주소</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${memberCnt > 0}">
							<c:forEach var="dto" items="${memberDtos}">
								<tr>
									<td>
									${memberNumber}
									<c:set var ="memberNumber" value="${memberNumber-1}" />
									</td>
									<td>
										<a href="memberInfo.go?id=${dto.id}">${dto.id}</a>
									</td>
									<td>
										<a href="memberInfo.go?id=${dto.id}">${dto.name}</a>
									</td>
									<td>
										<a href="memberInfo.go?id=${dto.id}">${dto.email}</a>
									</td>
									<td>
										<a href="memberInfo.go?id=${dto.id}">${dto.phone}</a>
									</td>
									<td>
										<a href="memberInfo.go?id=${dto.id}">${dto.address}</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${memberCnt == 0}">
							<tr>
								<td colspan="6" style="text-align: center;height: 500px;">회원이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="pager">
					<ol>
						<c:if test="${memberCnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${memberStartPage > memberPageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${memberCurrentPage - memberPageBlock}">&#60;</a>
								</li>
							</c:if>
				
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${memberStartPage}" end="${memberEndPage}">
								<c:if test="${i == memberCurrentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
				
								<c:if test="${i != memberCurrentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
				
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${memberPageCount > memberEndPage}">
								<li>
									<a href="invoicelist.board?pageNum=${memberCurrentPage + memberPageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${memberPageCount}">&#62;&#62;</a>
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