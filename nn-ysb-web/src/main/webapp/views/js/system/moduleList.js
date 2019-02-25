$(document).ready(function() {
	var vue = new Vue({
		el: '#app',
		
		data: {
			list: [],
			inputtext: {
				resourceName: '',
			}
		},
		
		methods: {
			query: function() {
				initPagination({
					url: '/res/listModulePage?1=1',
					formData: this.inputtext,
					callback: function(response, formData) {
						vue.list = response;
						vue.inputtext = formData;
					}
				});
			},
			reset: function() {
				this.inputtext = {};
				this.query();
			},
			openAddModuleWin: function() {
				DialogTemplate.showPage('添加', 400, 180, 'moduleAdd.html');
			},
			showUpdateWin: function(resCode, resName) {
				DialogTemplate.showPage('更新', 400, 180, 'moduleUpdate.html' + '?resCode=' + resCode + '&resName=' + resName);
			},
			enableModule: function(resCode) {
				this.endisableModule('/res/enableModule?1=1', resCode);
			},
			disableModule: function(resCode) {
				this.endisableModule('/res/disableModule?1=1', resCode);
			},
			endisableModule: function(url, resCode) {
				QueryMain.loadData({
					argUrl: url,
					paramdata: {
						resCode : resCode
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

