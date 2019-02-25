$(document).ready(function() {
	var vue = new Vue({
		el: "#app",
		data: {
			dataList: [],
			inputtext: {
				transferStatus: '1',
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
					transferStatus: '1',
					startDate: '',
					endDate: '',
					opUser: '',
					opDate: '',
					refSign: ''
				};
				this.query();
			}
		},
		
		mounted: function() {
			this.query();
		}
	});
});