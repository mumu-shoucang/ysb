$(document).ready(function() {
	var vue = new Vue({
		el: '#app',
		data: {
			resourceName: ''
		},
		computed: {
			resourceCode: function() {
				var par = QueryMain.getPars();
				return par['resCode'];
			}
		},
		methods: {
			init: function() {
				var par = QueryMain.getPars();
				this.resourceName = par['resName'];
			},
			update: function() {
				QueryMain.loadData({
					argUrl: '/res/updateModule?1=1',
					paramdata: {
						resourceName : this.resourceName,
						resourceCode : this.resourceCode
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

