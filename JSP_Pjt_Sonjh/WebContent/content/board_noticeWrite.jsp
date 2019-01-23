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
			<form action="noticeWritePro.board" method="post">
			<input type="hidden" name="no" value="${no}">
			<input type="hidden" name="pageNum" value="${pageNum}">
				<div class="board">
					<div class="board_info">
						<h2>
							<label>
								제목 : 
								<input type="text" style="width: 798px;"  name="subject" placeholder="제목을 입력해주세요." required autofocus>
							</label>
						</h2>
					</div>
					<div class="board_content">
						<ul>
							<li>
								<label>
									국가 : 
									<input type="text" name="country" placeholder="국가를 입력해주세요." required>
								</label>
							</li>
							<li>
								<label>
									도시 : 
									<input type="text" name="city" placeholder="도시를 입력해주세요." required>
								</label>
							</li>
						</ul>
						<textarea name="content" placeholder="내용을 입력해주세요."></textarea>
					</div>
					<div class="board_footer">
	                	<ul>
	                		<li><input type="button" onclick="window.location='noticelist.board?pageNum=${pageNum}'" value="목록보기"></li>
	                		<li><input type="submit" value="작성하기"></li>
	                	</ul>
	                </div>
				</div>
			</form>
		</article>
	</section>
</body>
</html>