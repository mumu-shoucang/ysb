$(document).ready(function() {
	var vue = new Vue({
		el : "#orderDetailApp",
		data : {
			oid : location.search.substr(5),
			order : [],
			orderLogList : [],
		},
		methods : {
			query : function() {
				QueryMain.loadData({
					argUrl: "/order/getOrderDetails?1=1",
					paramdata: {
						id : this.oid
					},
					callback: function(response) {
						vue.order = response;
					}
				});
			},
			queryTow : function() {
				QueryMain.loadData({
					argUrl: "/order/getOrderLogDetails?1=1",
					paramdata: {
						id : this.oid
					},
					callback: function(response) {
						vue.orderLogList = response;
					}
				});
			},
			bankAccountNo : function(card){
				if(card==null||card==''){
					return "";
				}else{
					return card.substr(0,5)+"******"+card.substr(-5);
				}
			},
		},
		filters : {
			showDesc: function(orderStatus){
				if(orderStatus=='0'){
					return '已创建';
				}else if(orderStatus=='1'){
					return '支付中';
				}else if(orderStatus=='2'){
					return '支付成功';
				}else if(orderStatus=='3'){
					return '结算成功';
				}else if(orderStatus=='4'){
					return '交易关闭';
				}
				return "未知状态";
			},
			yeeWithdrawStatus : function(status){
				if(status=='RECEIVED'){
					return '已接受';
				}else if(status=='PROCESSING'){
					return '处理中';
				}else if(status=='SUCCESSED'){
					return '打款成功';
				}else if(status=='FAILED'){
					return '打款失败';
				}else if(status=='REFUNED'){
					return '已退款';
				}else if(status=='CANCELLED'){
					return '已撤销';
				}else{
					return "未知状态";
				}
			},
			yeePayStatus : function(status){
				if(status=='INIT'){
					return '未支付';
				}else if(status=='SUCCESS'){
					return '成功';
				}else if(status=='FAIL'){
					return '失败';
				}else if(status=='FROZEN'){
					return '冻结';
				}else if(status=='THAWED'){
					return '解冻';
				}else if(status=='REVERSE'){
					return '冲正';
				}else{
					return '未知状态';
				}
			}
		},
		mounted: function() {
			this.query();
			this.queryTow();
		}
	});
});

