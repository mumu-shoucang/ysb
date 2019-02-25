$(document).ready(function() {
	
	var vue = new Vue({
		el: "#app",
		data: {
			statisticsList: [],
			inputtext: {
				statisticsType: '', // 推荐人标识
				exRefSign: '', // 不包括的推荐人标识
				startDate: '',
				endDate: ''
			},
			isAllChecked: false,
			batchList: [],
			checkedList: [],
			transferSmsCode: '',
			transferInfo: '',
			smsError: '',
			batchObj: {}, // 用于去重
			checkedObj: {} // // 用于去重
		},
		
		methods: {
			query: function() {
				initPagination({
					url: "/statistics/getUntransferStatistics?1=1",
					formData: this.inputtext,
					callback: function(response) {
						vue.statisticsList = response;
						vue.isAllChecked = false;
						vue.checkedList = [];
					}
				});
			},
			reset: function() {
				this.inputtext = {};
				this.query();
			},
			joinBatch: function() {
				$.each(vue.checkedList, function(i, checkedId) {
					if(!vue.batchObj[checkedId]) { // 不存在
						vue.batchObj[checkedId] = 1;
						$.each(vue.statisticsList, function(index, stat) {
							if(stat.id == checkedId) {
								vue.batchList.push(stat);
							}
						})
					}
				});
			},
			delFromBatch: function(id) {
				
				vue.batchObj[id] = 0;
				var delIndex;
				$.each(vue.batchList, function(index, item) {
					if(item.id == id) {
						delIndex = index;
					}
				});
				vue.batchList.splice(delIndex, 1);
			}, 
			showTip: function() {
				if(!vue.batchList.length) {
					DialogTemplate.showInfoMsg("请添加转账批次");
					return;
				}
				vue.transferSmsCode= '';
				vue.smsError= '';
				$('#showTransferConfirm').modal();
			},
			sendSMS: function() {
				
				var sendBtn = $("#sendBtn");
				var time=60;
			    	var t=setInterval(function  () {
			    		time--;
			    		sendBtn.text(time+"s后重发");
			    		sendBtn.attr("disabled","disabled");
			    	    if (time==0) {
			    	        clearInterval(t);
			    	        sendBtn.text("发送验证码");
			    	        sendBtn.removeAttr("disabled");
			    	    }
			    	}, 1000);
				
				
				QueryMain.loadData({
					argUrl: '/transfer/sendSMS?1=1',
					paramdata: {
						msg: vue.transferInfo
					},
					callback: function(response) {
					},
					error: function(data) {	
					}
				});	
			},
			doTransfer: function() {
				
				if(!vue.transferSmsCode) {
					return;
				}
				
				jQuery.ajax({
					url : '/' + window.location.pathname.split('/')[1] + '/transfer/batchTransfer?1=1',
					headers: {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
					type : "POST",
					async : true,
					dataType : "json",
					data : JSON.stringify({
						smsCode: vue.transferSmsCode,
						statPos: vue.batchList
					}),
					success : function(response) {
						if ("status" in response && response.status == "SUCCESS") {
							if(response.body.smsError) {
								vue.smsError = response.body.smsError;
							} else {
								$('#showTransferConfirm').modal('hide');
								DialogTemplate.showInfoMsg(response.body.desc, function() {
									window.location.reload();
								});
							}
						} else {
							vue.smsError = '系统繁忙，稍后重试';
						}
					},
					error : function(data, status) {
						vue.smsError = '系统繁忙，稍后重试';
					}
				});
			}
		},
		watch: {
			isAllChecked: function(newVal, oldVal) {
				if (newVal) {
					$.each(vue.statisticsList, function(index, stat) {
						vue.checkedList.push(stat.id);
					});
				} else {
					vue.checkedList = [];
				}
			},
			batchList: function(newList, oldVal) {
				var total = 0;
				$.each(newList, function(index, stat) {
					total = total + stat.tradeCommisstion * 100;
				});
				vue.transferInfo = "本批次总转账金额为" + total/100 + "元";
			}
		},
		mounted: function() {
			this.query();
		}
	});
	
});