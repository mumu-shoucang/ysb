$(document).ready(function() {
	var pars = QueryMain.getPars();
	var vue = new Vue({
		el : "#withdrawOrderDetailApp",
		data : {
			oid : pars['oid'],
			yeeCustomerNumber : pars['yeeCustomerNumber'],
			orderNo : pars['orderNo'],
			merchant : [],
			order : [],
			withdrawOrderLogList : []
		},
		methods : {
			queryMerchant : function() {
				QueryMain.loadData({
					argUrl: "/merchant/getMerchantByYeecustomerNumber?1=1",
					paramdata: {
						yeeCustomerNumber : this.yeeCustomerNumber
					},
					callback: function(response) {
						vue.merchant = response;
					}
				});
			},
			queryWithdrawOrder : function() {
				QueryMain.loadData({
					argUrl: "/withdraw/getWithdrawOrderByOid?1=1",
					paramdata: {
						oid : this.oid
					},
					callback: function(response) {
						vue.order = response;
					}
				});
			},
			queryWithdrawOrderLog : function() {
				QueryMain.loadData({
					argUrl: "/withdraw/getWithdrawLog?1=1",
					paramdata: {
						orderNo : this.orderNo
					},
					callback: function(response) {
						vue.withdrawOrderLogList = response;
					}
				});
			}
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
			this.queryMerchant();
			this.queryWithdrawOrder();
			this.queryWithdrawOrderLog();
		}
	});
});

