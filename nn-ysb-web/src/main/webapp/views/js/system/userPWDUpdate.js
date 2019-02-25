$(document).ready(function() {
	
	(function() {
		$('#app').height(document.documentElement.clientHeight)
	})();
	
	var vue = new Vue({
		el: '#app',
		data: {
			user: {
				userName: '',
				loginName: '',
				newLoginPwd: ''
			}
		},
		methods: {
			init: function() {
				
				var par = QueryMain.getPars();
				this.userCode = par['userCode'];
				
				QueryMain.loadData({
					argUrl: '/user/getOnlyUserByCode?1=1',
					paramdata: {
						userCode: this.userCode
					},
					callback: function(response) {
						vue.user = response.user;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			updatePWD: function() {
				QueryMain.loadData({
					argUrl: '/user/updatePWD?1=1',
					paramdata: {
						userCode: vue.userCode,
						loginName: vue.user.loginName,
						loginPwd: vue.user.newLoginPwd
					},
					callback: function(response) {
						var fn = function(){
							parent.window.location.reload();
						};
						if(response.isSuc) {
							DialogTemplate.showInfoMsg('成功!', fn);
						} else {
							DialogTemplate.showInfoMsg('失败!','');
						}
					},
					error: function(data) {	
						DialogTemplate.showInfoMsg('失败!','');
					}
				});	
			},
			cancle: function() {
				parent.window.cd.close();
			}
		},
		mounted: function() {
			this.init();
		}
	});
});

