$(document).ready(function() {
	var vue = new Vue({
		el: "#app",
		data: {
			bTypes: [{
				key: '1', value: 'T0 自助结算可用余额'
			}, {
				key: '2', value: 'T1 自助结算可用余额'
			}, {
				key: '3', value: '账户余额'
			}],
			yeeCustomerNumber: '',
			balanceType: '1',
			balanceInfo: ''
		},
		
		methods: {
			query: function() {
				if(!vue.yeeCustomerNumber) {
					return;
				}
				QueryMain.loadData({
					argUrl: '/balance/balanceQuery?1=1',
					paramdata: {
						yeeCustomerNumber: vue.yeeCustomerNumber,
						balanceType: vue.balanceType
					},
					callback: function(response) {
						vue.balanceInfo = '余额：' + (response.balance ? response.balance : '0.00')  + '元';
					},
					error: function(data) {	
						DialogTemplate.showInfoMsg('系统繁忙，稍后重试','');
					}
				});	
			}
		}
	});
});