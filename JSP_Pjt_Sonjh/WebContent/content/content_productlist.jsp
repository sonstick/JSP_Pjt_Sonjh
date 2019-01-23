<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
	<section class="main_container">
        <article>
            <div class="productlist_info">
	            <div style="margin: 30px 0;">
	            	<span class="productlist_title">여행상품</span>
	            	<span class="productlist_cnt">등록된 상품 수 : ${cnt}개</span>
	            	<div class="hidden_obj"></div>
	            </div>
               	<ul>
               		<c:if test="${cnt > 0}">
                		<c:forEach var="dto" items="${dtos}">
                			<li>
                				<div class="p_l_thumbs">
		                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
		                				<img src="${root}/images/product/${dto.p_thumbs}">
		                			</a>
		                		</div>
		                		<div class="p_l_info">
		                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
		                				${dto.country} | ${dto.city}
		                			</a>
		                		</div>
		                		<div class="p_l_subject">
		                			<a href="productView.product?p_no=${dto.p_no}&pageNum=${pageNum}">
		                				${dto.p_name}
		                			</a>
		                		</div>
		                	</li>
                		</c:forEach>
                	</c:if>
                </ul>
               	<c:if test="${cnt == 0}">
               		<div style="width: 960px; height: 400px; display: table-cell; text-align: center; vertical-align: middle;">
	               		상품을 준비중입니다.
               		</div>
               	</c:if>
            </div>
			<div class="pager productlist_footer">
				<ol>
					<c:if test="${cnt > 0}">
						<!-- 처음<< | 이전블록< -->
						<c:if test="${startPage > pageBlock}">
							<li>
								<a href="list.product">&#60;&#60;</a>
							</li>
							<li>
								<a href="list.product?pageNum=${currentPage - pageBlock}">&#60;</a>
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
									<a href="list.product?pageNum=${i}">${i}</a>
								</li>
							</c:if>
						</c:forEach>

						<!-- 다음블록> | 마지막>> -->
						<c:if test="${pageCount > endPage}">
							<li>
								<a href="list.product?pageNum=${currentPage + pageBlock}">&#62;</a>
							</li>
							<li>
								<a href="list.product?pageNum=${pageCount}">&#62;&#62;</a>
							</li>
						</c:if>
					</c:if>
				</ol>
			</div>
        </article>
    </section>
</body>
</html>