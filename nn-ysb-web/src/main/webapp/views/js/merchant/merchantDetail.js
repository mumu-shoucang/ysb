$(document).ready(function() {
	var vue = new Vue({
		el : "#merchantDetailApp",
		data : {
			mid : location.search.substr(5),
			merchant: {},
			merchantCreditCards: []
			
		},
		methods : {
			query : function() {
				QueryMain.loadData({
					argUrl: "/merchant/getMerchantDetails?1=1",
					paramdata: {
						id : this.mid
					},
					callback: function(response) {
						vue.merchant = response.merchant;
						vue.merchantCreditCards = response.merchantCreditCards;
					}
				});
			},
			bankAccountNo : function(card){
				if(card==null||card==''){
					return "--";
				}else{
					return card.substr(0,5)+"******"+card.substr(-5);
				}
			},
		},
		filters : {
			dateFilters: function(date){	
				var newDate = date;
				if(newDate==null||newDate==''){
					return '--';
				}
				return newDate.substr(0,10);
			}
		},
		mounted: function() {
			this.query();		}
	});
});

