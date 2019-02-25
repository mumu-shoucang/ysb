$(document).ready(function() {
	var vue = new Vue({
		el : "#updateReferrerApp",
		data : {
			id : '',
			name : '',
			mobile : '',
			commissionRate :'',
			idCard : '',
			ckBankCard : '',
			basicCommission : '',
			t0WithdrawFee : '',
		},
		computed: {
			rid: function() {
				// from requestURI
				var par = QueryMain.getPars();
				this.id = par['rid'];
				return par['rid'];
			},
			rname: function() {
				// from requestURI
				var par = QueryMain.getPars();
				this.name = par['rname'];
				return par['rname'];
			},
			rmobile : function (){
				var par = QueryMain.getPars();
				this.mobile = par['rmobile'];
				return par['rmobile'];
			},
			rcommissionRate : function(){
				var par = QueryMain.getPars();
				this.commissionRate = par['rcommissionRate'];
				return par['rcommissionRate'];
			},
			ridCard : function(){
				var par = QueryMain.getPars();
				this.idCard = par['ridCard'];
				return par['ridCard'];
			},
			rckBankCard : function(){
				var par = QueryMain.getPars();
				this.ckBankCard = par['rckBankCard'];
				return par['rckBankCard'];
			},
			rbasicCommission : function(){
				var par = QueryMain.getPars();
				this.basicCommission = par['rbasicCommission'];
				return par['rbasicCommission'];
			},
			rt0WithdrawFee : function(){
				var par = QueryMain.getPars();
				this.t0WithdrawFee = par['rt0WithdrawFee'];
				return par['rt0WithdrawFee'];
			},
			
		},
		methods : {		
			update : function() {
				if(this.name==''||this.commissionRate==''||this.mobile==''||this.idCard==''||this.ckBankCard==''||this.basicCommission==''){
					DialogTemplate.showInfoMsg('请填写正确的数据！','');
				}else{
					if(this.basicCommission<=this.t0WithdrawFee){
						QueryMain.loadData({
							argUrl: "/referrer/update?1=1",
							paramdata: {
								id : this.id,
								name : this.name,
								mobile : this.mobile,
								commissionRate : this.commissionRate,
								idCard : this.idCard,	
								ckBankCard : this.ckBankCard,
								basicCommission : this.basicCommission,
							},
							callback: function(response) {
								var fn = function(){
									parent.window.location.reload();
								};
								DialogTemplate.showInfoMsg('修改成功!',fn);
							},error : function(data) {	
								DialogTemplate.showInfoMsg('修改失败!','');
							}
						});
					}else{
						DialogTemplate.showInfoMsg('基础佣金不能大于 T0 自助结算基本费率','');
					}
				}
			},
			query : function(){
				if(this.idCard=='null'){
					this.idCard='';
				}
				if(this.ckBankCard=='null'){
					this.ckBankCard = '';
				}
				if(this.basicCommission=='null'){
					this.basicCommission = '0';
				}
			}
		},
		mounted: function() {
			this.query();
		}
	});
});



