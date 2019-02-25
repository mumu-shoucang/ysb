$(document).ready(function() {
	var vue = new Vue({
		el : "#orderApp",
		data : {
			orderStuList : orderStatusList,
			orderList : [],
			inputtext:{
				orderNo: '',
				refSign:'',
				orderAmt:'',
				orderStatus: '',
				startTime: '',
				endTime: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : "/order/getOrderPage.controller?1=1",
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
					refSign:'',
					orderAmt:'',
					orderStatus: '',
					startTime: '',
					endTime: '',
				};
				this.query();
			},
			getByKey : function(status){
				var index = status;
				if(this.orderStuList[index].key==status){
					return this.orderStuList[index].value;
				}
			},
			see : function(oid){
				window.location.href="orderDetail.html?oid="+oid;
			},
			goToWithDraw : function(oid){
				function withDraw(){
					QueryMain.loadData({
						argUrl: "/order/withDraw?1=1",
						paramdata:{
							id:oid
						},
						callback: function(response) {
							DialogTemplate.showInfoMsg('结算请求已发起,请稍后根据日志查看请求结果!','');
						},
						error : function(data) {	
							DialogTemplate.showInfoMsg('发起结算请求失败!','');
						}
					});	
				};
				DialogTemplate.showConfirm("确定要手动发起结算吗？",withDraw,'');
			},
			exportFun : function(){
				var url = QueryMain.getCxtPath()+"/order/export?1=1&orderNo="+vue.inputtext.orderNo+"&refSign="+vue.inputtext.refSign+"&orderAmt="+vue.inputtext.orderAmt+"&orderStatus="
				+vue.inputtext.orderStatus+"&startTime="+vue.inputtext.startTime+"&endTime="+vue.inputtext.endTime;
				window.location = url;
			},
		},
		mounted: function() {
			this.query();
		}
	});
});

