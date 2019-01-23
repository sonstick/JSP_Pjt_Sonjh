<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="vo.NoticeVO" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${root}css/board.css" />
</head>
<body>
	<section class="main_container">
		<article>
			<div class="board">
				<div class="board_info">
					<h2>
						<span class="board_title_option" style="position: relative; top: 2px;">
							${dto.country} | ${dto.city}
						</span>
						${dto.subject}
						<span class="board_sub" style="font-size: 12px;margin-left: 20px;position: relative; top: 14px;">
							<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
						</span>
						<span class="board_sub" style="position: relative; top: 12px;">
							Travel Asia
						</span>
					</h2>
				</div>
				<div class="board_content">
					${dto.content}
				</div>
				<div class="board_footer">
                	<ul>
                		<li><a href="noticelist.board?pageNum=${pageNum}">목록보기</a></li>
                		<c:if test="${sessionScope.sessionAuth=='H'}">
                			<li><a href="noticeModify.board?no=${dto.no}&pageNum=${pageNum}">수정하기</a></li>
                			<li><a href="deleteNotice.board?no=${dto.no}&pageNum=${pageNum}" style="background:#c11300;">삭제하기</a></li>
                		</c:if>
                	</ul>
                </div>
			</div>
		</article>
	</section>
</body>
</html>