<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						${dto.subject}
						<span class="board_sub" style="font-size: 12px;margin-left: 20px;position: relative; top: 14px;">
							<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
						</span>
						<span class="board_sub" style="position: relative; top: 12px;">
							${dto.name}
						</span>
					</h2>
				</div>
				<div class="board_content">
					${dto.content}
					<c:if test="${dto.cmt != 'isNull'}">
						<div class="cmt_view">${dto.cmt}</div>
					</c:if>
				</div>
				<div class="board_footer">
					<c:if test="${sessionScope.sessionAuth=='H'}">
						<form action="invoiceCmt.board" method="post" class="cmt">
							<input type="hidden" name="no" value="${dto.no}">
							<input type="hidden" name="pageNum" value="${pageNum}">
							<textarea name="cmt" placeholder="답변을 입력해주세요." autofocus></textarea>
							<input type="submit" value="답변하기">
						</form>
					</c:if>
                	<ul>
                		<li><a href="invoicelist.board?pageNum=${pageNum}">목록보기</a></li>
                		<li><a href="invoiceModify.board?no=${dto.no}&pageNum=${pageNum}">수정하기</a></li>
                		<li><a href="deleteInvoice.board?no=${dto.no}&pageNum=${pageNum}" style="background: #c11300;">삭제하기</a></li>
                	</ul>
                </div>
			</div>
		</article>
	</section>
</body>
</html>