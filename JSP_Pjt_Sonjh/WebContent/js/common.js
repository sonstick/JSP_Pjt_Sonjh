/**
 * 
 */

var msg_id = "아이디를 입력해 주세요.";
var msg_pwd = "비밀번호를 입력해 주세요.";
var msg_re_pwd = "비밀번호를 확인해 주세요.";
var msg_pwd_check = "비밀번호가 일치하지 않습니다.";
var msg_name = "이름을 입력해 주세요.";
var msg_email = "이메일 주소를 입력해 주세요.";
var msg_emailChk = "이메일 형식이 일치하지 않습니다.";
var msg_confirmId = "중복 확인을 해주세요";

var loginError = "로그인이 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var insertError = "회원가입에 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var updateError = "회원정보 수정에 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var deleteError = "회원 탈퇴에 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var passwordError = "입력하신 비밀번호가 일치하지 않습니다.\n확인 후 다시 시도해 주세요.";
var writeError = "게시물 작성이 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var deleteBoardError = "게시물 식제가 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var insertBookingError = "예약이 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var deleteBookingError = "예약 취소가 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var orderViewError = "예약정보 조회가 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var loginBookingError = "회원정보가 없습니다.\n로그인 하여 주세요.";
var insertScrapError = "스크랩이 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var updateInvoiceError = "문의 수정에 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var updateNoticeceError = "공지사항 수정에 실패하였습니다.\n확인 후 다시 시도해 주세요.";
var updateProductError = "상품정보 수정에 실패하였습니다.\n확인 후 다시 시도해 주세요.";

function errorAlert(errorMsg) {
	alert(errorMsg);
	window.history.back();
}

function confirmId() {
	if(!document.signupform.id.value) {
		alert(msg_id);
		document.signupform.id.focus();
		return false;
	}

	var url = "confirmId.go?id="+document.signupform.id.value;
	window.open(url, "confirm", "menubar=no, width=380px, height=180px");
}

function signupCheck() {
	if(document.signupform.hiddenId.value == 0){
		alert(msg_confirmId);
		document.signupform.idCheck.focus();
		return false;
	} else if(document.signupform.pwd.value != document.signupform.re_pwd.value) {
		alert(msg_pwd_check);
		document.signupform.pwd.value="";
		document.signupform.re_pwd.value="";
		document.signupform.pwd.focus();
		return false;
	}
}

function setId(id) {
	opener.document.signupform.id.value=id;
	opener.document.signupform.hiddenId.value = "1";
	opener.document.signupform.pwd.focus();
	self.close();
}