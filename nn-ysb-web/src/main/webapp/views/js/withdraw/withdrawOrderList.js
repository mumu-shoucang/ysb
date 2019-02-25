$(document).ready(function() {
	var vue = new Vue({
		el : "#withdrawApp",
		data : {
			orderList : [],
			inputtext:{
				orderNo: '',
				withdrawStatus: '',
				withdrawAmt: '',
				startTime: '',
				endTime: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : "/withdraw/getOrderPage.controller?1=1",
					formData : this.inputtext,
					callback : function(response, formData) {
						vue.orderList = response;
						vue.inputtext = formData;
					}
				});
			},
			reset : function(){
				this.inputtext = {
					orderNo: '',
					withdrawAmt:'',
					withdrawStatus: '',
					startTime: '',
					endTime: '',
				};
				this.query();
			},
			show : function(oid,yeeCustomerNumber,orderNo){
				window.location.href="withdrawOrderDetail.html?oid="+oid+"&yeeCustomerNumber="+yeeCustomerNumber+"&orderNo="+orderNo;
			}
		},
		mounted: function() {
			this.query();
		}
	});
});

