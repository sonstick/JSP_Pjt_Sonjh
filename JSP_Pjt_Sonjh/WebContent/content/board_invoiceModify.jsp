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
			<form action="invoiceModifyPro.board" method="post">
			<input type="hidden" name="no" value="${dto.no}">
			<input type="hidden" name="pageNum" value="${pageNum}">
				<div class="board">
					<div class="board_info">
						<h2>
							<input type="text" name="subject" placeholder="${dto.subject}" required autofocus>
							<span class="board_sub" style="font-size: 12px;margin-left: 20px;position: relative; top: 14px;">
								<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
							</span>
							<span class="board_sub" style="position: relative; top: 8px;">
								작성자
							</span>
						</h2>
					</div>
					<div class="board_content">
						<textarea name="content" placeholder="${dto.content}"></textarea>
					</div>
					<div class="board_footer">
	                	<ul>
	                		<li><input type="button" onclick="window.location='invoicelist.board?pageNum=${pageNum}'" value="목록보기"></li>
	                		<li><input type="submit" value="수정하기"></li>
	                	</ul>
	                </div>
				</div>
			</form>
		</article>
	</section>
</body>
</html>