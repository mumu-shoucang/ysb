$(document).ready(function() {
	var vue = new Vue({
		el: '#add',
		
		data: {
			modules: [],
			resourceName: '',
			resourceUrl: '',
			parentCode: ''
		},
		methods: {
			init: function() {
				QueryMain.loadData({
					argUrl: '/res/listAllModules?1=1',
					paramdata: {
					},
					callback: function(response) {
						vue.modules = response.list;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			add: function() {
				QueryMain.loadData({
					argUrl: '/res/addRes?1=1',
					paramdata: {
						resourceName : this.resourceName,
						resourceUrl : this.resourceUrl,
						parentCode : this.parentCode
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

