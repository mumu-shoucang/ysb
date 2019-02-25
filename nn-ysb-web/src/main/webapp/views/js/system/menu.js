$(document).ready(function() {
	var vue = new Vue({
		el: '#app',
		data: {
			loginName: '',
			menus: ''
		},
		methods : {
			query: function() {
				QueryMain.loadData({
					argUrl: '/user/menu?1=1',
					paramdata: {
					},
					callback: function(response) {
						vue.loginName = response.loginName;
						vue.menus = response.menus;
					},
					error: function(data) {
					}
				});
			},
			onoffSubmenu: function(event, a, b, c) {
				var target = event.target;
				var nodeName = target.nodeName;
				if(nodeName == 'A') {
					if($(target).parent('li').hasClass('open')) {
						$(target).siblings('ul').hide();
						$(target).parent('li').removeClass('open')
					} else {
						$(target).siblings('ul').show();
						$(target).parent('li').addClass('open')
					}
				} else {
					if($(target).parent('a').parent('li').hasClass('open')) {
						$(target).parent('a').siblings('ul').hide();
						$(target).parent('a').parent('li').removeClass('open')
					} else {
						$(target).parent('a').siblings('ul').show();
						$(target).parent('a').parent('li').addClass('open')
					}
				}
				
			},
			loadBody: function(url) {
				var iframeWin = window.open('/' + window.location.pathname.split('/')[1]+url,'cbody');
			},
			changeFrameHeight: function(){
				
				// to logout
				if(document.getElementById('coby').contentDocument.URL.indexOf('/login.html') != -1) {
					this.logout();
				}
				
				// set frame height
	        	var cHeight = document.documentElement.clientHeight - $("div.page-header").height();
	    		$("div.page-content").height(cHeight);
	            var iframe = document.getElementById("coby");
	            iframe.height=cHeight;
	        },
	        logout: function() {
	        	window.location = '/' + window.location.pathname.split('/')[1] + '/sys/logout';
	        }
			
		},
		mounted: function() {
			this.query();
		}
	});
});

