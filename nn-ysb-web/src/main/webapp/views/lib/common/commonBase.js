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

//split
function initPagination(opt) {
	
	// default value
	opt.pageNum = 1;
	
	// get from cache 
	if(document.referrer.indexOf('menu.html') == -1 && document.referrer.indexOf('login.html') == -1 && !window.event) {
		var storeStr = localStorage.getItem(window.location.href);
		var storeObj = JSON.parse(storeStr);
		
		if(storeObj) {
			opt.url = storeObj.url;
			opt.pageNum = storeObj.pageNum;
			opt.formData = storeObj.formData;
		}
	}
	
	$.jqPaginator('#pagination', {
        totalPages: opt.pageNum,
        visiblePages: 6,
        currentPage: opt.pageNum,
        first: '<li class="page-item"><a class="page-link" href="javascript:;">首页</a></li>',
        prev: '<li class="page-item"><a class="page-link" href="javascript:;">上一页</a></li>',
        next: '<li class="page-item"><a class="page-link" href="javascript:;">下一页</a></li>',
        last: '<li class="page-item"><a class="page-link" href="javascript:;">尾页</a></li>',
        page: '<li class="page-item"><a class="page-link" href="javascript:;">{{page}}</a></li>',

        onPageChange: function(currentPage) {
	        	QueryMain.initTable({
	        			argUrl : opt.url + QueryMain.setPage(currentPage),
					paramdata : opt.formData,
					callback : function(data) {
						if(data.total>0){
							// config page info
							$("#pagination").jqPaginator('option', {
						        totalPages: data.pages,
						        visiblePages: 6,
						        currentPage: data.pageNum
						    });
							$("#totalCount").text(data.total);
							$("#currentPage").text(data.pageNum);
							$("#totalPages").text(data.pages);
							
							// store this request
							localStorage.setItem(window.location.href, JSON.stringify({ref: document.referrer, url: opt.url, pageNum: data.pageNum, formData: opt.formData}));
						}else{
							$("#totalCount").text(data.total);
							$("#currentPage").text(data.pageNum);
							$("#totalPages").text(data.pages+1);
						}
						
						// callback
						opt.callback(data.list, opt.formData);
					},
					error : function() {
						alert("数据加载错误");
					}
	        	});
        }
    });
}

/**
 * artDialog
 */
var DialogTemplate = {
	/**
	 * 成功信息提示框
	 * @param message 信息内容。
	 * @param fun 关闭提示框后的执行方法。可为空。
	 */
	//信息类型（“success”：成功；“error”：失败、错误；“warning”：警告；“question”：问题、询问；“info”：信息；）
	showSuccessMsg : function(message, fun) {
		var content = "<div class='fc-693 fb dialog-tip' style='padding-bottom:10px;'>" +
					"<div class='row'>" +
					"<div class='col-4'> <i class='fa fa-check-circle-o'></i></div>" +
					"<div class='col-8 text-left' style='margin-top:30px;'>" +
					"<p >" + message + "</p>" +
					"</div></div></div>";
		showMessage(content, fun);
	},
	/**
	 * 失败或错误信息提示框
	 * @param message 信息内容。
	 * @param fun 关闭提示框后的执行方法。可为空。
	 */
	showErrorMsg : function(message, fun) {
		var content = "<div class='fc-e00 fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-times-circle-o'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>"
						+ message +"</p></div></div></div>";
		showMessage(content, fun);
	},
	/**
	 * 警告信息提示框
	 * @param message 信息内容。
	 * @param fun 关闭提示框后的执行方法。可为空。
	 */
	showWarningMsg : function(message, fun) {
		var content = "<div class='fc-f60 fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-exclamation-triangle'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>" 
						+ message + "</p></div></div></div>";
		showMessage(content, fun);
	},
	/**
	 * 提示信息提示框
	 * @param message 信息内容。
	 * @param fun 关闭提示框后的执行方法。可为空。
	 */
	showQuestionMsg : function(message, fun) {
		var content = "<div class='fc-39e fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-question-circle'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>" 
						+ message + "</p></div></div></div>";
		showMessage(content, fun);
	},
	/**
	 * 普通信息提示框
	 * @param message 信息内容。
	 * @param fun 关闭提示框后的执行方法。可为空。
	 */
	showInfoMsg : function(message, fun) {
		var content = "<div class='fc-39e fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-info-circle'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>" 
						+ message + "</p></div></div></div>";
		showMessage(content, fun);
	},
	/**
	 * 信息对话框
	 * @param message 信息内容。
	 * @param okFun 点击“确认”按钮后，关闭对话框后的执行方法。可为空。
	 * @param cancelFun 点击“取消”按钮后，关闭对话框后的执行方法。可为空。
	 */
	showConfirm : function(message, okFun, cancelFun) {
		var text = "<div class='fc-f60 fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-question-circle'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>"
					+ message + "</p></div></div></div>";
		dialog({
			fixed : true,
			width : 400,
			content : text,
			button : [ {
				value : '取消',
				callback : function() {
					this.close().remove();
					if (cancelFun) {
						cancelFun();
					}
					return false;
				}
			}, {
				value : '确定',
				callback : function() {
					this.close().remove();
					if (okFun) {
						okFun();
					}
					return false;
				},
				autofocus : true
			} ]
		}).showModal();
	},
	/**
	 * 是否删除询问对话框
	 * @param message 信息内容。
	 * @param fun 点击“确认”按钮后，关闭对话框后的执行方法。可为空。
	 */
	delConfirm : function(fun) {
		var text = "<div class='fc-f60 fb dialog-tip' style='padding-bottom:10px'><div class='row'><div class='col-4'><i class='fa fa-question-circle'></i></div><div class='col-8 text-left' style='margin-top:30px'><p>确定要删除吗？</p></div></div></div>";
		dialog({
			fixed : true,
			width : 400,
			content : text,
			okValue : "确定",
			ok : function() {
				this.close().remove();
				if (fun) {
					fun();
				}
				return false;
			},
			cancelValue : "取消",
			cancel : function() {
			}
		}).showModal();
	},
	/**
	 * 显示页面对话框
	 * @param titleText 标题
	 * @param w	宽度
	 * @param h	高度
	 * @param urlpath 页面链接地址
	 * @param fun 关闭对话框后执行的方法，可为空
	 */
	showPage : function(titleText, w, h, urlpath, fun) {
		var cd = dialog({
			title : titleText,
			fixed : true,
			width : w,
			height : h,
			url : urlpath,
			cancelValue : "取消",
			onclose : function() {
				this.close().remove();
				if (fun) {
					fun();
				}
			}
		});
		window.cd = cd;
		cd.showModal();
	}
};

/**
 * 显示信息提示框
 * @param text 信息内容。
 * @param fun 关闭提示框后的执行方法。可为空。
 */
var showMessage = function(text, fun) {
	if (text == null || text == undefined || text == '') {
		text = "<div class='fc-f60 fb dialog-tip'><div class='col-md-3 col-sm-3 col-xs-3'><i class='fa fa-exclamation-triangle'></i></div><div class='col-md-9 col-sm-9 col-xs-9'><p > <span>"
				+ message + "</span></p></div></div>";
	}
	dialog({
		fixed : true,
		width : 330,
		content : text,
		button : [ {
			value : '确定',
			callback : function() {
				this.close().remove();
				if (fun) {
					fun();
				}
//				return false;
			}
		} ]
	}).showModal();
};