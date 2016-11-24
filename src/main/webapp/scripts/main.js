function showLoading() { 
	$('#black-div').css('display', 'flex');
	$('#black-div .loading-wrapper').css('display', 'block');
}
function hideLoading() {
	$('#black-div').css('display', 'none');
	$('#black-div .loading-wrapper').css('display', 'none');
}

function countdownLoading(time) {
	showLoading();
	if (time > 0)
	{
		setTimeout(function() {
			hideLoading();
		}, time * 1000);
	}
}

function closeConfirmation() {
	$('#black-div .confirmation-wrapper').css('display', 'none');
	countdownLoading(2);
}

function countdownConfirmation(time) {
	showLoading();
	setTimeout(function() {
		$('#black-div .loading-wrapper').css('display', 'none');
		$('#black-div .confirmation-wrapper').css('display', 'block');
	}, time * 1000);
}

function toggleFilter(e) {
	e.preventDefault();
	
	if ($('#advance-filter').css('display') == 'none')
	{
		$('#advance-filter').css('display', 'block');
		$('#filter-toggle').html('Hide');
	}
	else
	{
		$('#advance-filter').css('display', 'none');
		$('#filter-toggle').html('Advanced Filter');
	}
}