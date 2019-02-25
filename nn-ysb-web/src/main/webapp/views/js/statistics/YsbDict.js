var YsbDict = {};
(function($, ns) {
	
	var getTextFrom = function(dict, key) {
		var text = "";
		$.each(dict, function(index, item) {
			if (key == item.key) {
				text = item.value;
			}
		});
		return text;
	};
	
	var statisticsType = [{
		key : '1', value : '系统商费率'
	}
	,{
		key : '2', value : '推荐人佣金'
	}];
	
	var transferStatus = [{
		key: '0', value: '失败'
	}, {
		key: '1', value: '成功'
	}, {
		key: '2', value: '未转'
	}];
	
	var withdrawStatus = [{
		key: '0', value: '已创建'
	}, {
		key: '1', value: '结算中'
	}, {
		key: '2', value: '结算成功'
	}, {
		key: '3', value: '结算失败'
	}];
	
	var yeeWithdrawStatus = [{
		key: 'RECEIVED', value: '已接受'
	}, {
		key: 'PROCESSING', value: '处理中'
	}, {
		key: 'SUCCESSED', value: '打款成功'
	}, {
		key: 'FAILED', value: '打款失败'
	}, {
		key: 'REFUNED', value: '已退款'
	}, {
		key: 'CANCELLED', value: '已撤销'
	}];
	
	var createOptsWith = function(dict, eleId, hasSelect) {
		var fr = document.createDocumentFragment();
		
		if(hasSelect) {
			var opt = document.createElement("option");
			opt.value = " ";
			opt.innerText = "请选择";
			fr.appendChild(opt);
		}
		
		$.each(dict, function(index, item) {
			var opt = document.createElement("option");
			opt.value = item.key;
			opt.innerText = item.value;
			fr.appendChild(opt);
		});
		$("#" + eleId).append(fr);
	};
	ns.getStatisticsTypeText = function(key) {
		return getTextFrom(statisticsType, key);
	};
	ns.getTransferStatus = function(key) {
		return getTextFrom(transferStatus, key);
	};
	ns.transferStatus = function() {
		return transferStatus;
	};
	ns.getWithdrawStatus = function(key) {
		return getTextFrom(withdrawStatus, key);
	};
	ns.getYeeWithdrawStatus = function(key) {
		return getTextFrom(yeeWithdrawStatus, key);
	};
})(jQuery, YsbDict);