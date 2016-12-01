var first_run = true;

var infoFormValue = new Array(0);
var infoFieldName = [ '#firstname', '#lastname' ,'#middlename', '#apartment', '#address', '#city', '#province', '#country', '#postal', '#telephone', '#gender', '#sin' ];

// For loop
var i = 0;

/*******************************************INFO FORM*****************************************************/

$(document).ready(function()
{
	recordForm();
});

function addError(obj) {
	obj.addClass('error');
	obj.prop('placeholder', 'Please enter correct information');
}

function removeError(obj) {
	obj.removeClass('error');
	obj.prop('placeholder', '');
}

function recordForm()
{
	infoFormValue = [];
	for (i = 0; i < infoFieldName.length; i++)
	{
		if (infoFieldName[i] != '#country' && infoFieldName[i] != '#gender')
		{
			infoFormValue.push($(infoFieldName[i]).val());
		}
		else
		{
			infoFormValue.push($(infoFieldName[i]).find(':selected').val());
		}
	}
}

function resetForm()
{
	if (infoFormValue.length == infoFieldName.length) {
		for (i = 0; i < infoFieldName.length; i++) {
			$(infoFieldName[i]).val(infoFormValue[i]);
		}
	}
}

function submitInfo(e)
{
	if ($(infoFieldName[0]).val().length > 0
			&& $(infoFieldName[1]).val().length > 0)
	{
		countdownLoading(0);
	}
}

function lockResetForm(e)
{
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