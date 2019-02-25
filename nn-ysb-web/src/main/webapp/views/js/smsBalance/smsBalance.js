$(document).ready(function() {
	var vue = new Vue({
		el: "#app",
		data: {
			balanceInfo: ''
		},
		
		methods: {
			query: function() {
				QueryMain.loadData({
					argUrl: '/smsBalance/balanceQuery?1=1',
					callback: function(response) {
						vue.balanceInfo = '短信余量：' + (response ? response : '0');
					},
					error: function(data) {	
						DialogTemplate.showInfoMsg('系统繁忙，稍后重试','');
					}
				});	
			}
		},
		mounted: function() {
			this.query();
		}
	});
});