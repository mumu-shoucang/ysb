$(document).ready(function() {
	var vue = new Vue({
		el: "#app",
		data: {
			dataList: [],
			inputtext: {
				transferStatus: '0',
				startDate: '',
				endDate: '',
				opUser: '',
				opDate: '',
				refSign: ''
			},
		},
		
		methods: {
			query: function() {
				initPagination({
					url: "/transferLog/listPage?1=1",
					formData: this.inputtext,
					callback: function(response) {
						vue.dataList = response;
					}
				});
			},
			reset: function() {
				this.inputtext = {
					transferStatus: '0',
					startDate: '',
					endDate: '',
					opUser: '',
					opDate: '',
					refSign: ''
				};
				this.query();
			},
			singleTransfer: function(item) {
				QueryMain.loadData({
					argUrl: '/transfer/failover?1=1',
					paramdata: item,
					callback: function(response) {
						DialogTemplate.showInfoMsg(response.desc, function() {
							window.location.reload();
						});
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