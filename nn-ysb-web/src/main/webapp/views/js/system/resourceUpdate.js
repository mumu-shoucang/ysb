$(document).ready(function() {
	var vue = new Vue({
		el: '#app',
		data: {
			res: {
				resourceName: '',
				resourceCode: ''
			},
			modules: []
		},
		methods: {
			init: function() {
				var par = QueryMain.getPars();
				this.resourceCode = par['resCode'];
				QueryMain.loadData({
					argUrl: '/res/getByCode?1=1',
					paramdata: {
						resCode : this.resourceCode
					},
					callback: function(response) {
						vue.res = response.res;
						vue.modules = response.modules;
					},
					error: function(data) {
						// do noting
					}
				});
			},
			update: function() {
				QueryMain.loadData({
					argUrl: '/res/updateRes?1=1',
					paramdata: this.res,
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

