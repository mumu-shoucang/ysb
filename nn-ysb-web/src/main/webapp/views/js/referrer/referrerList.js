$(document).ready(function() {
	var vue = new Vue({
		el : "#referrerApp",
		data : {
			referrerStatus : referrerStatusList,
			referrerList:[],
			t0WithdrawFee:'', 
			inputtext:{
				refSign: '',
				refStatus:'',
				startTime: '',
				endTime: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : "/referrer/getReferrerPage.controller?1=1",
					formData : this.inputtext,
					callback : function(response, formData) {
						vue.referrerList = response;
						vue.inputtext = formData;
					}
				});
			},
			queryTow : function() {
				QueryMain.loadData({
					argUrl: "/referrer/getT0WithdrawFee?1=1",
					callback: function(response) {
						if(response!=null){
							vue.t0WithdrawFee = response;
						}
					},
					error : function(data) {	
						DialogTemplate.showInfoMsg('查询失败!','');
					}
				});	
			},
			reset : function(){
				this.inputtext = {
					refSign: '',
					refStatus:'',
					startTime: '',
					endTime: '',
				};
				this.query();
			},
			bankAccountNo : function(card){
				if(card==null||card==''){
					return "--";
				}else{
					return card.substr(0,5)+"******"+card.substr(-5);
				}
			},
			addReferrer : function(){
				DialogTemplate.showPage("添加窗口", 520, 500, "referrerAdd.html?rt0WithdrawFee="+this.t0WithdrawFee);
			},
			getByValue : function(status){
				var index = status;
				if(this.referrerStatus[index].key==status){
					return this.referrerStatus[index].value;
				}
			},
			updateEnable : function(rid){
				var secondRecharge = function(){
					QueryMain.loadData({
						argUrl: "/referrer/updateEnable?1=1",
						paramdata: {
							id : rid,refStatus : 1,
						},
						callback: function(response) {
							var fn = function(){
								window.location.reload();
							};
							DialogTemplate.showInfoMsg('启用成功!',fn);
							
						},error : function(data) {
							DialogTemplate.showInfoMsg('启用失败!','');
						}
					});
				};
				DialogTemplate.showConfirm('是否启用',secondRecharge,'');
			},
			updateDisable : function(rid){
				var secondRecharge = function(){
					QueryMain.loadData({
						argUrl: "/referrer/updateDisable?1=1",
						paramdata: {
							id : rid,refStatus : 0,
						},
						callback: function(response) {
							var fn = function(){
								window.location.reload();
							};
							DialogTemplate.showInfoMsg('禁用成功!',fn);
							
						},error : function(data) {
							DialogTemplate.showInfoMsg('禁用失败!','');
						}
					});
				};
				DialogTemplate.showConfirm('是否禁用',secondRecharge,'');
			},
			update : function(obj){
				DialogTemplate.showPage("修改窗口", 520, 590, "referrerUpdate.html?rid="+obj.id+"&rname="+obj.name+"&rmobile="+obj.mobile+
						"&rcommissionRate="+obj.commissionRate+"&ridCard="+obj.idCard+"&rckBankCard="+obj.ckBankCard+"&rbasicCommission="+obj.basicCommission
						+"&rt0WithdrawFee="+this.t0WithdrawFee);
			}
			
		},
		mounted: function() {
			this.query();
			this.queryTow();
		}
	});
});

