<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script>
/* 이미지 미리보기 */
var loadFile1 = function(event) {
	var output = document.getElementById('output1');
	output.src = URL.createObjectURL(event.target.files[0]);
};
 
var loadFile2 = function(event) {
	var output = document.getElementById('output2');
	output.src = URL.createObjectURL(event.target.files[0]);
};

var loadFile3 = function(event) {
	var output = document.getElementById('output3');
	output.src = URL.createObjectURL(event.target.files[0]);
};

var loadFile4 = function(event) {
	var output = document.getElementById('output4');
	output.src = URL.createObjectURL(event.target.files[0]);
};

var loadFile5 = function(event) {
	var output = document.getElementById('output5');
	output.src = URL.createObjectURL(event.target.files[0]);
};
  
/*
	input type="file" 에 추가할 내용 : accept="image/*" onchange="loadFile(event)"
	img에 추가할 속성 : id="output"
*/
</script>
</head>
<body>
	<article>
		<div class="container">
			<div class="product_table">
    			<form action="productModifyPro.product" method="post" enctype="multipart/form-data">
	    			<input type="hidden" name="p_no" value="${dto.p_no}">
					<input type="hidden" name="pageNum" value="${pageNum}">
	    			<table>
	    				<tr>
	    					<th>상품명</th>
	    					<td colspan="3">
	    						<input type="text" name="p_name" placeholder="${dto.p_name}" required autofocus>
	    					</td>
	    				</tr>
	    				<tr>
	    					<th>여행국가</th>
	    					<td>
	    						<input type="text" name="country" placeholder="${dto.country}" required>
	    					</td>
	    					<th style="padding-left: 100px;">여행도시</th>
	    					<td>
	    						<input type="text" name="city" placeholder="${dto.city}" required>
	    					</td>
	    				</tr>
	    				<tr>
	    					<th style="height: 200px;">이미지</th>
	    					<td colspan="3">
	    						<span>
	    							<label for="p_thumbs" class="fileContainer">
	    							<img src="${root}/images/product/${dto.p_thumbs}" id="output1">
		    							<input type="file" name="p_thumbs" required accept="image/*" onchange="loadFile1(event)">
		    							목록 이미지
		    						</label>
	    						</span>
	    						<span>
		    						<label for="p_thumbs" class="fileContainer">
		    						<img src="${root}/images/product/${dto.p_image_1}" id="output2">
		    							<input type="file" name="p_image_1" required accept="image/*" onchange="loadFile2(event)">
		    							이미지 1
		    						</label>
	    						</span>
	    						<span>
		    						<label for="p_thumbs" class="fileContainer">
		    						<img src="${root}/images/product/${dto.p_image_2}" id="output3">
		    							<input type="file" name="p_image_2" required accept="image/*" onchange="loadFile3(event)">
		    							이미지 2
		    						</label>
	    						</span>
	    						<span>
		    						<label for="p_thumbs" class="fileContainer">
		    						<img src="${root}/images/product/${dto.p_image_3}" id="output4">
		    							<input type="file" name="p_image_3" accept="image/*" onchange="loadFile4(event)">
		    							이미지 3
		    						</label>
	    						</span>
	    						<span>
		    						<label for="p_thumbs" class="fileContainer">
		    						<img src="${root}/images/product/${dto.p_image_4}" id="output5">
		    							<input type="file" name="p_image_4" accept="image/*" onchange="loadFile5(event)">
		    							이미지 4
		    						</label>
	    						</span>
	    					</td>
	    				</tr>
	    				<tr>
	    					<th style="vertical-align: top; padding-top: 10px;">내용</th>
	    					<td colspan="3" style="height: 330px;">
	    						<textarea name="p_content" placeholder="${dto.p_content}" required></textarea>
	    					</td>
	    				</tr>
	    				<tr>
	    					<td colspan="3"></td>
	    					<td>
	    						<input type="submit" value="수정하기">
	    					</td>
	    				</tr>
	    			</table>
    			</form>    			
       		</div>
		</div>
	</article>
</body>
</html>