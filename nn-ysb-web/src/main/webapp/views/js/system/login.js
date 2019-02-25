$(document).ready(function() {
	var doLogin = function() {
		var ctxPath = '/' + window.location.pathname.split('/')[1];
		jQuery.ajax({
			url : ctxPath + '/sys/login',
			type : 'POST',
			async : false,
			dataType : 'json',
			data : {
				loginName : $('#loginName').val().trim(),
				loginPwd: $('#loginPwd').val().trim()
			},
			success : function(response) {
				if ('status' in response && response.status == 'SUCCESS') {
					var code = response.body.code;
					if('0000' == code) {
						window.location = 'menu.html';
					} else {
						$("#logerror").text(response.body.desc);
					}
				}
			},
			error : function(data, status) {
				console.log(data);
				console.log(status);
			}
		});
	}
	
	$(window).keydown(function(event){
		if(event.keyCode == 13) {
			doLogin();
		}
	});
	$('#loginBtn').click(function() {
		doLogin();
	});
});

