<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<!DOCTYPE html>
<html>
<body>
	<section class="main_container">
        <article>
            <div class="my_info">
                <div class="my_info_left">
                    <div class="my_info_profile">
                    	<img src="${root}images/${vo.member_image}">
                    </div>
                    <div class="my_info_list">
                        <ul>
                            <li><a href="modify_pwdcheck.go">정보 수정</a></li>
                            <li><a href="bookinglist.go">예약 내역</a></li>
                            <li><a href="scraplist.go">스크랩 목록</a></li>
                        </ul>
                    </div>
                </div>
                <div class="my_info_right">
                    <div class="my_info_right_inner">
                        <h2>- 마이페이지 -</h2>
                        <table class="mypage">
                            <tr>
                                <th>이름</th>
                                <td>${vo.name}</td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>${vo.email}</td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>${vo.phone}</td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>${vo.address}</td>
                            </tr>
                        </table>
                        <div class="leave"><a href="leave_pwdcheck.go">TRAVEL ASIA 떠나기(회원 탈퇴)</a></div>
                    </div>
                </div>
            </div>
        </article>
    </section>
</body>
</html>