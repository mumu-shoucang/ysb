var QueryMain = {
	getCxtPath : function() {
		return '/' + window.location.pathname.split('/')[1];
	},

	getPars : function() {
		var paramObj = {
		};
		if (window.location.search === "")
			return paramObj;

		var paramStr = window.location.search, eqIndex = paramStr.indexOf("?");
		var extpar = paramStr.substring(eqIndex + 1);
		var paramArray = extpar.split("&");

		$.each(paramArray, function(index, item) {
			var itemArray = item.split("="), key = itemArray[0], value = itemArray[1] === null
					|| itemArray[1] === undefined ? ""
					: decodeURIComponent(itemArray[1]);
			if (key)
				paramObj[key] = value;
		});
		return paramObj;
	},
	
	initTable : function(obj) { // 异步加载
		this.loadData(obj);
	},

	setPage : function(pageNo) {
		var startd = pageNo == 1 ? 1 : (pageNo - 1) * 10 + 1;
		var params = "&pageNo=" + pageNo + "&start=" + startd + "&limit=10";
		return params;
	},

	loadData : function(obj) {
		var radomT = "&v=" + (new Date()).getTime();
		var url = this.getCxtPath() + obj.argUrl + radomT;
		jQuery.ajax({
			url : url,
			type : "POST",
			async : true,
			dataType : "json",
			data : obj.paramdata,
			success : function(response) {
				if ("status" in response && response.status == "SUCCESS") {
					obj.callback(response.body);
				} else {
					obj.error(response.body || {});
				}
			},
			error : function(data, status) {
				console.log(data);
				console.log(status);
			}
		});
	}
}