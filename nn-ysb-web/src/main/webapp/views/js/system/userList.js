$(document).ready(function() {
	var vue = new Vue({
		el : '#app',
		data : {
			list: [],
			inputtext: {
				loginName: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : '/user/listPage?1=1',
					formData : this.inputtext,
					callback : function(response, formData) {
						vue.list = response;
						vue.inputtext = formData;
					}
				});
			},
			reset : function(){
				this.inputtext = {};
				this.query();
			},
			openAddWin: function() {
				DialogTemplate.showPage('添加', 450, 420, 'userAdd.html');
			},
			showUpdateWin: function(userCode) {
				DialogTemplate.showPage('更新', 450, 400, 'userUpdate.html' + '?userCode=' + userCode);
			},
			showUpdatePWDWin: function(userCode) {
				DialogTemplate.showPage('密码更新', 380, 330, 'userPWDUpdate.html' + '?userCode=' + userCode);
			},
			enableUser: function(id) {
				this.endisableUser('/user/enable?1=1', id);
			},
			disableUser: function(id) {
				this.endisableUser('/user/disable?1=1', id);
			},
			endisableUser: function(url, id) {
				QueryMain.loadData({
					argUrl: url,
					paramdata: {
						id : id
					},
					callback: function(response) {
						var fn = function(){
							window.location.reload();
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
			}
		},
		mounted: function() {
			this.query();
		}
	});
});

