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
			<c:if test="${sessionScope.sessionAuth=='H'}">
				<form action="invoiceWritePro.board" method="post">
					<div class="board">
						<div class="board_info">
							<h2>
								<label>
									제목 :
									<c:if test="${sessionScope.sessionAuth=='H'}">
										<input type="text" style="width: 798px;" name="subject" placeholder="제목을 입력해주세요." required autofocus>
										<input type="hidden" name="name" value="TravelAsia">
										<input type="hidden" name="no" value="${no}">
										<input type="hidden" name="pageNum" value="${pageNum}">
									</c:if>
								</label>
							</h2>
						</div>
						<div class="board_content">
							<textarea name="content" placeholder="내용을 입력해주세요."></textarea>
						</div>
						<div class="board_footer">
		                	<ul>
		                		<li><input type="button" onclick="window.location='invoicelist.board?pageNum=${pageNum}'" value="목록보기"></li>
		                		<li><input type="submit" value="작성하기"></li>
		                	</ul>
		                </div>
					</div>
				</form>
			</c:if>
			
			<c:if test="${sessionScope.sessionAuth!='H'}">
				<form action="invoiceWritePro.board" method="post">
					<input type="hidden" name="no" value="${no}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<div class="board">
						<div class="board_info">
							<h2>
								<label>
									제목 :
									<input type="text" style="width: 798px;" name="subject" placeholder="제목을 입력해주세요." required autofocus>
								</label>
							</h2>
						</div>
						<div class="board_content">
							<ul>
								<li>
									<label>
										이름 : 
										<input type="text" name="name" placeholder="이름을 입력해주세요." required>
									</label>
								</li>
								<li>
									<label>
										비밀번호 : 
										<input type="password" name="pwd" placeholder="비밀번호를 입력해주세요." required>
									</label>
								</li>
							</ul>
							<textarea name="content" placeholder="내용을 입력해주세요." required></textarea>
						</div>
						<div class="board_footer">
		                	<ul>
		                		<li><input type="button" onclick="window.location='${root}invoicelist.board?pageNum=${pageNum}'" value="목록보기"></li>
		                		<li><input type="submit" value="작성하기"></li>
		                	</ul>
		                </div>
					</div>
				</form>
			</c:if>
		</article>
	</section>
</body>
</html>