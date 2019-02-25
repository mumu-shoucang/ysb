$(document).ready(function() {
	var vue = new Vue({
		el : "#addReferrerApp",
		data : {
			name : '',
			mobile : '',
			refSign : '',
			commissionRate :'',
			idCard : '',
			ckBankCard : '',
			basicCommission : '',
			t0WithdrawFee : '',
			yeeCustomerNumber: ''
		},
		computed: {
			rt0WithdrawFee: function() {
				// from requestURI
				var par = QueryMain.getPars();
				this.t0WithdrawFee = par['rt0WithdrawFee'];
				return par['rt0WithdrawFee'];
			},
		},
		methods : {
			add : function() {
				if(this.commissionRate==''||this.mobile==''||this.idCard==''||this.ckBankCard==''||this.basicCommission==''){
					DialogTemplate.showInfoMsg('有未填写的信息，请确认！','');
				}else{
					/*if(!/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(this.mobile)){
						DialogTemplate.showInfoMsg('请输入正确的手机号！','');
					}else{
						
					}*/
					if(this.basicCommission<=this.t0WithdrawFee){
						QueryMain.loadData({
							argUrl: "/referrer/add?1=1",
							paramdata: {
								name : this.name,
								mobile : this.mobile,
								refSign : this.refSign,
								commissionRate : this.commissionRate,
								idCard : this.idCard,
								ckBankCard : this.ckBankCard,
								basicCommission : this.basicCommission,
								yeeCustomerNumber: this.yeeCustomerNumber
							},
							callback: function(response) {
								var fn = function(){
									parent.window.location.reload();
								};
								DialogTemplate.showInfoMsg('添加成功!',fn);
							},error : function(data) {
								DialogTemplate.showInfoMsg('添加失败!','');
							}
						});	
					}else{
						DialogTemplate.showInfoMsg('基础佣金不能大于 T0 自助结算基本费率','');
					}
				}
			},
			getYeeCustomerNumber: function() {
				QueryMain.loadData({
					argUrl: "/merchant/getYeeCustomerNumber?1=1",
					paramdata: {
						idCard : this.idCard
					},
					callback: function(response) {
						vue.yeeCustomerNumber = response.yeeCustomerNumber ? response.yeeCustomerNumber : '';
						vue.ckBankCard = response.bankAccountNo ? response.bankAccountNo : '';
					},
					error : function(data) {
						// do noting for now
					}
				});	
			}
		}
	});
});



