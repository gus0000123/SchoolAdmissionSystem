var first_run = true;

var infoFormValue = new Array(0);
var infoFieldName = [ '#firstname', '#lastname', '#apartment', '#address', '#city', '#province', '#country', '#postal', '#home_no', '#work_no', '#mobile_no' ];

// For loop
var i = 0;

/*******************************************INFO FORM*****************************************************/

$(document).ready(function() {
	for (i = 0; i < infoFieldName.length; i++) {
		$(infoFieldName[i]).click(function(e) {
			removeError($('#' + e.target.name));
		});
	}
});

function addError(obj) {
	obj.addClass('error');
	obj.prop('placeholder', 'Please enter correct information');
}

function removeError(obj) {
	obj.removeClass('error');
	obj.prop('placeholder', '');
}

function recordForm() {
	infoFormValue = [];
	for (i = 0; i < infoFieldName.length; i++) {
		infoFormValue.push($(infoFieldName[i]).val());
	}
}

function resetForm() {
	if (infoFormValue.length == infoFieldName.length) {
		for (i = 0; i < infoFieldName.length; i++) {
			$(infoFieldName[i]).val(infoFormValue[i]);
		}
	}
}

function unlockInput(e, id) {
	e.preventDefault();
	
	if (first_run) {
		first_run = false;
		recordForm();
	}
	$(id).prop('disabled', false);
}

function lockAll() {
	for (i = 0; i < infoFieldName.length; i++) {
		$(infoFieldName[i]).prop('disabled', true);
		removeError($(infoFieldName[i]));
	}
}

function submitInfo(e) {
	e.preventDefault();
	
	if (!first_run) {
		// For now, if any field is not empty, it will pass, else fail
		var counter = 0;
		for (i = 0; i < infoFieldName.length; i++) {
			if ($(infoFieldName[i]).val() != '') {
				removeError($(infoFieldName[i]));
				counter++;
			} else {
				addError($(infoFieldName[i]));
			}
		}
	
		if (counter >= infoFieldName.length - 3) {
			countdownConfirmation(2);
			lockAll();
			recordForm();
		} else {
			countdownLoading(2);
		}
	}
}

function lockResetForm(e) {
	e.preventDefault();
	first_run = true;
	lockAll();
	resetForm();
}

/******************************************************PASSWORD FORM*********************************************************/

var passwordForm = ['#cur-password', '#new-password', '#cfm-password'];
var passwordTextForm = ['#cur-password-txt', '#new-password-txt', '#cfm-password-txt'];

$(document).ready(function() {
	for (i = 0; i < passwordForm.length; i++) {
		$(passwordForm[i]).click(function(e) {
			removeError($('#' + e.target.id));
			removeError($('#' + e.target.id + '-txt'));
		});
		$(passwordTextForm[i]).click(function(e) {
			removeError($('#' + e.target.id));
			removeError($('#' + e.target.id.substring(0, e.target.id.length - 4)));
		});
	}
});

function showHideText(id, e) {
	e.preventDefault();
	
	var passwordField = $(id);
	var textField = $(id + '-txt');
	var btn = $(id + '-btn');
	
	if (passwordField.css('display') == 'none') {
		// Copy value
		passwordField.val(textField.val());
		textField.val('');
		
		// Change display
		passwordField.css('display', 'inline');
		textField.css('display', 'none');
		btn.html('Show password');
	} else {
		// Copy value
		textField.val(passwordField.val());
		passwordField.val('');
		
		// Change display
		passwordField.css('display', 'none');
		textField.css('display', 'inline');
		btn.html('Hide password');
	}
}

function resetAllPasswords() {
	for (i = 0; i < passwordForm.length; i++) {
		$(passwordForm[i]).val('');
		$(passwordTextForm[i]).val('');
		removeError($(passwordForm[i]));
		removeError($(passwordTextForm[i]));
	}
}

function submitPassword(e) {
	e.preventDefault();
	
	var counter = 0;
	
	// Validate current password, check empty for now
	if ($(passwordForm[0]).val() != '') {
		counter++;
	} else if ($(passwordTextForm[0]).val() != '') {
		counter++;
	} else {
		addError($(passwordForm[0]));
		addError($(passwordTextForm[0]));
	}
	
	// Validate new password, check empty for now
	var newPassword = '';
	if ($(passwordForm[1]).val() != '') {
		counter++;
		newPassword = $(passwordForm[1]).val();
	} else if ($(passwordTextForm[1]).val() != '') {
		counter++;
		newPassword = $(passwordTextForm[1]).val();
	} else {
		addError($(passwordForm[1]));
		addError($(passwordTextForm[1]));
	}
	
	// Validate confirm password, check if matched with new password
	if (newPassword != '' && newPassword == $(passwordForm[2]).val()) {
		counter++;
	} else if (newPassword != '' && newPassword == $(passwordTextForm[2]).val()) {
		counter++;
	} else {
		addError($(passwordForm[2]));
		addError($(passwordTextForm[2]));
	}
	
	if (counter == passwordForm.length) {
		countdownConfirmation(2);
		resetAllPasswords();
	} else {
		countdownLoading(2);
	}
}