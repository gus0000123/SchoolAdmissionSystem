var loginTable = $('#login-table');
var registerTable = $('#register-table');
var loginFieldName = ['#login-user', '#login-password'];
var registerFieldName = ['#register-user', '#register-first-name', '#register-last-name', '#register-email', '#register-password', '#register-confirm-password'];

function switchToLogin() {
	loginTable.css("display", "block");
	registerTable.css("display", "none");
	
	var i = 0;
	for (i; i < loginFieldName.length; i++) {
		$(loginFieldName[i]).val('');
		$(loginFieldName[i] + '-error').css('display', 'none');
	}
}

function signin(e) {
	e.preventDefault();

	var counter = 0;
	
	var loginUser = $('#login-user').val();
	var loginPassword = $('#login-password').val();
	
	// Show error message
	if (loginUser != '') {
		$('#login-user-error').css('display', 'none');
		counter++;
		if (loginPassword != '') {
			$('#login-password-error').css('display', 'none');
			counter++;
		} else {
			$('#login-password-error').css('display', 'block');
		}
	} else {
		$('#login-user-error').css('display', 'block');
		$('#login-password-error').css('display', 'block');
	}
	
	// Send data to server
	if (counter == 2) {
		countdownLoading(0);
		setTimeout(function () { $('#login-form').submit() }, 5000);
	} else {
		countdownLoading(2);
	}
}

function switchToRegister() {
	countdownLoading(2);
	
	loginTable.css("display", "none");
	registerTable.css("display", "block");
	
	var i = 0;
	for (i; i < registerFieldName.length; i++) {
		$(registerFieldName[i]).val('');
		$(registerFieldName[i] + '-error').css('display', 'none');
	}
}

function register(e) {
	e.preventDefault();
	
	var counter = 0;
	
	var i = 0;
	for (i; i < registerFieldName.length; i++) {
		if ($(registerFieldName[i]).val() != '') {
			$(registerFieldName[i] + '-error').css('display', 'none');
			counter++;
		} else {
			$(registerFieldName[i] + '-error').css('display', 'block');
		}
	}
	
	// Need to send data to servlet
	if (counter == registerFieldName.length) {
		// Need popup for success and fail
		countdownConfirmation(2);
	} else {
		countdownLoading(2);
	}
}