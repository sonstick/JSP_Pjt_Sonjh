<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/board.css" />
	<link rel="stylesheet" href="${root}css/board_invoice.css" />
</head>
<body>
	<section class="main_container">
        <article>
            <div class="board">
            		<div class="board_info">
		            	<h2 class="board_title">문의사항</h2>
		            	<span class="board_sub">등록된 게시물 : ${cnt}개</span>
		            	<div class="hidden_obj"></div>
		            </div>
                <div class="board_head">
                    <table>
                        <thead>
                            <tr>
                                <th class="column1">번호</th>
                                <th class="column2">답변여부</th>
                                <th class="column3">제목</th>
                                <th class="column4">작성자</th>
                                <th class="column5">등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${cnt > 0}">
                        		<c:forEach var="dto" items="${dtos}">
                        			<c:if test="${sessionScope.sessionAuth=='H'}">
                        				<tr>
	                        				<td>
	                        					${number}
												<c:set var ="number" value="${number-1}" />
	                        				</td>
	                        				<td>
	                        					<c:if test="${dto.cmt == 'isNull'}">
	                        						답변전
	                        					</c:if>
	                        					<c:if test="${dto.cmt != 'isNull'}">
	                        						답변완료
	                        					</c:if>
	                        				</td>
	                        				<td>
	                        					<a href="invoiceView.board?no=${dto.no}&pageNum=${pageNum}&number=${number+1}">${dto.subject}</a>
	                        				</td>
	                        				<td>
	                        					${dto.name}
	                        				</td>
	                        				<td>
	                        					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
	                        				</td>
	                        			</tr>
                        			</c:if>
                        			<c:if test="${sessionScope.sessionAuth!='H'}">
                        				<tr>
	                        				<td>
	                        					${number}
												<c:set var ="number" value="${number-1}" />
	                        				</td>
	                        				<td>
	                        					<c:if test="${dto.cmt == 'isNull'}">
	                        						답변전
	                        					</c:if>
	                        					<c:if test="${dto.cmt != 'isNull'}">
	                        						답변완료
	                        					</c:if>
	                        				</td>
	                        				<td>
	                        					<a href="invoicePwdCheck.board?no=${dto.no}&pageNum=${pageNum}&number=${number+1}">${dto.subject}</a>
	                        				</td>
	                        				<td>
	                        					${dto.name}
	                        				</td>
	                        				<td>
	                        					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
	                        				</td>
	                        			</tr>
		                        	</c:if>
                        		</c:forEach>
                        	</c:if>
                            <c:if test="${cnt == 0}">
                        		<tr>
                        			<td colspan="5" style="text-align: center;">등록된 게시물이 없습니다.</td>
                        		</tr>
                        	</c:if>
                        </tbody>
                    </table>
                </div>
                <div class="pager board_pager">
                    <ol>
						<c:if test="${cnt > 0}">
							<!-- 처음<< | 이전블록< -->
							<c:if test="${startPage > pageBlock}">
								<li>
									<a href="invoicelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${currentPage - pageBlock}">&#60;</a>
								</li>
							</c:if>
	
							<!-- 블록 내의 페이지 번호 -->
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<li>
										<span><b class="this_page">${i}</b></span>
									</li>
								</c:if>
	
								<c:if test="${i != currentPage}">
									<li>
										<a href="invoicelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
	
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${pageCount > endPage}">
								<li>
									<a href="invoicelist.board?pageNum=${currentPage + pageBlock}">&#62;</a>
								</li>
								<li>
									<a href="invoicelist.board?pageNum=${pageCount}">&#62;&#62;</a>
								</li>
							</c:if>
						</c:if>
					</ol>
                </div>
                <c:if test="${sessionScope.sessionId != null}">
                	<c:if test="${sessionScope.sessionAuth!='H'}">
	                	<div class="board_footer">
		                	<ul>
		                		<li><a href="invoiceWriteForm.board?no=0&pageNum=${pageNum}">문의하기</a></li>
		                	</ul>
		                </div>
	                </c:if>
                </c:if>
            </div>
        </article>
    </section>
</body>
</html>