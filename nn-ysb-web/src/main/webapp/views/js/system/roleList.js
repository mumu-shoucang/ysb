$(document).ready(function() {
	var vue = new Vue({
		el : '#app',
		data : {
			list: [],
			inputtext: {
				roleName: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : '/role/listPage?1=1',
					formData : this.inputtext,
					callback : function(response, formData) {
						vue.list = response;
						vue.inputtext = formData;
					}
				});
			},
			reset : function() {
				this.inputtext = {};
				this.query();
			},
			openAddWin : function() {
				DialogTemplate.showPage('添加', 450, 400, 'roleAdd.html');
			},
			showUpdateWin: function(roleCode) {
				DialogTemplate.showPage('更新', 450, 400, 'roleUpdate.html' + '?roleCode=' + roleCode);
			},
			enableRole: function(id) {
				this.endisableRole('/role/enable?1=1', id);
			},
			disableRole: function(id) {
				this.endisableRole('/role/disable?1=1', id);
			},
			endisableRole: function(url, id) {
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

