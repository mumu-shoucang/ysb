$(document).ready(function() {
	var vue = new Vue({
		el: '#moduleAdd',
		
		data: {
			resourceName: ''
		},
		methods: {
			add: function() {
				QueryMain.loadData({
					argUrl: '/res/addModule?1=1',
					paramdata: {
						resourceName : this.resourceName,
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
		}
	});
});

