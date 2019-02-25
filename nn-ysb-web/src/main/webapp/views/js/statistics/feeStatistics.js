$(document).ready(function() {
	var vue = new Vue({
		el : "#statisticsApp",
		
		data : {
			statisticsList:[],
			inputtext:{
				statisticsType : '1',
				statisticsDate : '',
			}
		},
		
		methods : {
			query : function() {
				initPagination({
					url : "/statistics/getStatistics.controller?1=1",
					formData : this.inputtext,
					callback : function(response) {
						vue.statisticsList = response;
					}
				});
			},
			
			reset : function(){
				this.inputtext.statisticsDate = '',
				this.query();
			}
		},
		
		mounted: function() {
			this.query();
		}
	});
});