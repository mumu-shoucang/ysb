$(document).ready(function() {
	var vue = new Vue({
		el : '#app',
		
		data : {
			list: [],
			inputtext: {
				resourceName: '',
			}
		},
		
		methods : {
			query : function() {
				initPagination({
					url : '/res/listResPage?1=1',
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
			openAddResWin : function() {
				DialogTemplate.showPage('添加', 450, 340, 'resourceAdd.html');
			},
			showUpdateWin: function(resCode) {
				DialogTemplate.showPage('更新', 450, 340, 'resourceUpdate.html' + '?resCode=' + resCode);
			},
			enableRes: function(resCode, parentCode) {
				this.endisableRes('/res/enableRes?1=1', resCode, parentCode);
			},
			disableRes: function(resCode) {
				this.endisableRes('/res/disableRes?1=1', resCode, '');
			},
			endisableRes: function(url, resCode, parentCode) {
				QueryMain.loadData({
					argUrl: url,
					paramdata: {
						resCode: resCode,
						parentCode: parentCode
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

