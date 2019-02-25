$(document).ready(function() {
	var vue = new Vue({
		el : "#statisticsApp",
		
		data : {
			statisticsList:[],
			inputtext:{
				statisticsType : '',
				startDate : '',
				endDate : '',
				commissionTransferStat: ''
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
				this.inputtext.startDate = '',
				this.inputtext.endDate = '',
				this.inputtext.statisticsType = '',
				this.inputtext.commissionTransferStat = '',
				this.query();
			},
			exportFun : function(){
//				QueryMain.loadData({
//					argUrl: "/statistics/export?1=1",
//					paramdata: this.inputtext,
//					callback: function(response) {
//						DialogTemplate.showInfoMsg('导出成功!','');
//					}
//				});
				var url = QueryMain.getCxtPath()+"/statistics/export?1=1&startDate="+this.inputtext.startDate
				+"&endDate="+this.inputtext.endDate+"&statisticsType="+this.inputtext.statisticsType+"&commissionTransferStat="+this.inputtext.commissionTransferStat;
				window.location = url;
			},
		},
		
		mounted: function() {
			this.query();
		}
	});
});