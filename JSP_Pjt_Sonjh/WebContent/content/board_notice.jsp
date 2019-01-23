<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/board.css" />
	<link rel="stylesheet" href="${root}css/board_notice.css" />
</head>
<body>
	<section class="main_container">
        <article>
            <div class="board">
           		<div class="board_info">
	            	<h2 class="board_title">공지사항</h2>
	            	<span class="board_sub">등록된 게시물 : ${cnt}개</span>
	            	<div class="hidden_obj"></div>
	            </div>
                <div class="board_head">
                    <table>
                        <thead>
                            <tr>
                                <th class="column1">번호</th>
                                <th class="column2">국가</th>
                                <th class="column3">도시</th>
                                <th class="column4">제목</th>
                                <th class="column5">글쓴이</th>
                                <th class="column6">등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${cnt > 0}">
                        		<c:forEach var="dto" items="${dtos}">
                        			<tr>
                        				<td>
                        					${number}
											<c:set var ="number" value="${number-1}" />
                        				</td>
                        				<td>
                        					${dto.country}
                        				</td>
                        				<td>
                        					${dto.city}
                        				</td>
                        				<td>
                        					<a href="noticeView.board?no=${dto.no}&pageNum=${pageNum}&number=${number+1}">${dto.subject}
                        						<%-- <span style="font-size:8px;margin-left:8px;">${dto.readCnt}</span> --%>
                        					</a>
                        				</td>
                        				<td>
                        					Travel Asia
                        				</td>
                        				<td>
                        					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
                        				</td>
                        			</tr>
                        		</c:forEach>
                        	</c:if>
                        	<c:if test="${cnt == 0}">
                        		<tr>
                        			<td colspan="4" style="text-align: center;">현재 공지사항이 없습니다.</td>
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
									<a href="noticelist.board">&#60;&#60;</a>
								</li>
								<li>
									<a href="noticelist.board?pageNum=${currentPage - pageBlock}">&#60;</a>
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
										<a href="noticelist.board?pageNum=${i}">${i}</a>
									</li>
								</c:if>
							</c:forEach>
	
							<!-- 다음블록> | 마지막>> -->
							<c:if test="${pageCount > endPage}">
								<li>
									<a href="noticelist.board?pageNum=${currentPage + pageBlock}">&#62;</a>
								</li>
								<li>
									<a href="noticelist.board?pageNum=${pageCount}">&#62;&#62;</a>
								</li>
							</c:if>
						</c:if>
					</ol>
                </div>
                <c:if test="${sessionScope.sessionAuth=='H'}">
                	<div class="board_footer">
	                	<ul>
	                		<li><a href="noticeWriteForm.board?pageNum=${pageNum}">공지작성</a></li>
	                	</ul>
	                </div>
                </c:if>
            </div>
        </article>
    </section>
</body>
</html>