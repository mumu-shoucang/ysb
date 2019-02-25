$(document).ready(function() {
	var vue = new Vue({
		el : "#merchantApp",
		data : {
			merchantFlist : merchantFlagList, 
			merchantList:[],
			inputtext:{
				merchantCode: '',
				loginMobile: '',
				startTime: '',
				endTime: '',
			}
		},
		methods : {
			query : function() {
				initPagination({
					url : "/merchant/getMerchantPage.controller?1=1",
					formData : this.inputtext,
					callback : function(response, formData) {
						vue.merchantList = response;
						vue.inputtext = formData;
					}
				});
			},
			reset : function(){
				this.inputtext = {
					merchantCode: '',
					loginMobile: '',
					startTime: '',
					endTime: '',
				};
				this.query();
			},
			getByValue : function(flag){
				var index = flag;
				if(this.merchantFlist[index].key==flag){
					return this.merchantFlist[index].value;
				}
			},
			feeSet : function(mid){
				var secondRecharge = function(){
					QueryMain.loadData({
						argUrl: "/merchant/updateFeeSet.controller?1=1",
						paramdata: {
							id : mid
						},
						callback: function(response) {
							var fn = function(){
								window.location.reload();
							};
							if(response.isSuc==true){
								DialogTemplate.showInfoMsg('设置成功!',fn);
							}else if(response.fail==false){
								DialogTemplate.showInfoMsg('设置失败!','');
							}
							
						},error : function(data) {
							DialogTemplate.showInfoMsg('出现异常!','');
						}
					});
				};
				DialogTemplate.showConfirm('是否重新设置费率',secondRecharge,'');
			},
			bankAccountNo : function(card){
				if(card==null||card==''){
					return "";
				}else{
					return card.substr(0,5)+"******"+card.substr(-5);
				}
			},
			see : function(mid){
				window.location.href="merchantDetail.html?mid="+mid;
			}
		},
		mounted: function() {
			this.query();
		}
	});
});

