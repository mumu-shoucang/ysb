/**
 * 定义命名空间.
 */
(function(sino) {
	// namespace
	sino.ns('tool');
})(sino);

/**
 * 定义Base工具方法
 */
(function(sino) {
	/**
	 * 定义Base类型.
	 */
	sino.tool.Base = function() {
	};

	/**
	 * Returns true if the passed value is not undefined.
	 */
	sino.tool.Base.isDefined = function(v) {
		return typeof v !== 'undefined';
	};

	/**
	 * 是否为空.
	 * <p>
	 * 下面两种情型认为是空:
	 * <ul>
	 * <li>为null</li>
	 * <li>为undefined</li>
	 * </ul>
	 */
	sino.tool.Base.isEmpty = function(obj) {
		return v === null || v === undefined;
	};
})(sino);

/**
 * Uri工具
 */
(function(sino) {
	/**
	 * 定义URLUtil类型.
	 */
	sino.tool.URLUtil = function() {
	};

})(sino);

/**
 * 字符串工具.
 */
(function(sino) {
	/**
	 * 定义StringUtil类型.
	 */
	sino.tool.StringUtil = function() {
	};

	/**
	 * 是否为空.
	 * 
	 * <p>
	 * 如果sourceStr字符串为null,undefined,''返回真.
	 * 
	 * @param sourceStr
	 *            源字符串
	 * @returns true if blank
	 */
	sino.tool.StringUtil.isBlank = function(sourceStr) {
		if (sino.isString(sourceStr) && '' != sourceStr) {
			return false;
		}

		return true;
	};

	/**
	 * 去掉字符串首末两端空隔.
	 * <p>
	 * 空类型,返回''.
	 */
	sino.tool.StringUtil.trim = function(sourceStr) {
		if (!sino.tool.Base.isEmpty(sourceStr)) {
			var trimLeft = /^[\s]+/;
			var trimRight = /[\s]+$/;
			sourceStr.toString().replace(trimLeft, "").replace(trimRight, "");
		}
		return '';
	};
})(sino);

/**
 * 验证工具
 */
(function(sino) {
	/**
	 * 定义Val类型.
	 */
	sino.tool.Val = function(config) {
	};

	/**
	 * 判断是否是手机号.
	 * 
	 * @param phoneNo
	 *            手机号
	 * @returns boolean true if is mobilePhone
	 */
	sino.tool.Val.isMobilePhone = function(phoneNo) {
		return /(13\d{9})|(15\d{9})|(18\d{9})/.test(phoneNo);
	};

	/**
	 * 判断是否是座机号.
	 * 
	 * @param phoneNo
	 *            座机号
	 * @returns boolean true if is telephone
	 */
	sino.tool.Val.isTelPhone = function(phoneNo) {
		return /(\d+)*-?\d{5,8}(-?\d+)*/.test(phoneNo);
	};

	/**
	 * 判断是否是汉字或是字母.
	 * 
	 * <p>
	 * unallowLetter 为 true时表示不可以为字母
	 * 
	 * @param text
	 *            文本
	 * @returns boolean true if is Cn
	 */
	sino.tool.Val.isCnLetter = function(text, unallowLetter) {
		if (unallowLetter) {
			return /^[\u4E00-\u9FA5]+$/.test(text);
		}
		return /^[A-Za-z\u4E00-\u9FA5]+$/.test(text);
	};

	/**
	 * 判断是否为字母数字.
	 * 
	 * <p>
	 * unallowDigit 为 true时表示只能是字母.
	 * 
	 * @param text
	 *            文本
	 */
	sino.tool.Val.isLetterDigit = function(text, unallowDigit) {
		if (unallowDigit) {
			return /^[A-Za-z]+$/.test(text);
		}
		return /^[A-Za-z\d]+$/.test(text);
	};
	/**
	 * 验证是否是负数。
	 * <p>
	 * @param v
	 * 		要验证的值。
	 */
	sino.tool.Val.isNegative = function (v){
		return /^-(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(v);
	};
	/**
	 * 判断text文本取值范围是否在指定的模式内.
	 * 
	 * <p>
	 * options {[CN|EN|en|DI]:[true|flash],other:[\.|-|\\]}.
	 * ex:{CN:true,EN:true,DI:true,en:true,other:'-_'}
	 * 表示text的取值范围是：中文字符、英文（大小写）字符、数字、'-' 以及 '_'
	 * 
	 * @param text
	 *            文本
	 */
	sino.tool.Val.textSeleRange = function(text, options) {
		var s = '^[';
		if(options.EN){
			s = s + 'A-Z';
		}
		if(options.en){
			s = s + 'a-z';
		}
		if(options.CN){
			s = s + '\\u4E00-\\u9FA5';
		}
		if(options.DI){
			s = s + '\\d';
		}
		if(options.other){
			s = s + options.other;
		}
		s = s + ']+$';
		if(s == '^[]+$'){
			return true;
		}
		var rg = new RegExp(s,'g');
		return rg.test(text);
	};
	
	/**
	 * 验证身份证信息.
	 * 
	 * <p>
	 * @param v
	 *           身份证号码.
	 */
	sino.tool.Val.isCNID = function (v){
		if(!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v))) return false;
		var provinceCode = parseInt(v.substr(0,2));
		if((provinceCode < 11) || (provinceCode > 91)) return false;
		var forTestDate = v.length == 18 ? v : v.substr(0,6)+"19"+v.substr(6,15);
		var birthday = forTestDate.substr(6,8);
		if(!sino.tool.Val.isDate(birthday,'yyyyMMdd')) return false;
		if(v.length == 18) {
			v = v.replace(/x$/i,"a");
			var verifyCode = 0;
			for(var i = 17;i >= 0;i--)   
            	verifyCode += (Math.pow(2,i) % 11) * parseInt(v.charAt(17 - i),11);
            if(verifyCode % 11 != 1) return false;
		}
		return true;
	};
	
	/**
	 * 验证日期是否符合格式.
	 * 
	 * <p>
	 * @param v
	 *            为日期文本，如2011-02-03.
	 * @param dateFormat
	 * 			  为日期要验证的格式。格式中须是年(yyyy)月(MM)日(dd)三者的组合，目前仅有此功能。
	 */
	sino.tool.Val.isDate = function(v,dateFormat) {
		var MONTH = "MM";
	   	var DAY = "dd";
	   	var YEAR = "yyyy";
		var regex = '^'+dateFormat.replace(YEAR,'\\d{4}').replace(MONTH,'\\d{2}').replace(DAY,'\\d{2}')+'$';
		if(!new RegExp(regex).test(v)) return false;
		var year = v.substr(dateFormat.indexOf(YEAR),4);
		var month = v.substr(dateFormat.indexOf(MONTH),2);
		var day = v.substr(dateFormat.indexOf(DAY),2);
		
		var d = new Date(year+"/"+month+"/"+day);
		return ( parseInt(month, 10) == (1+d.getMonth()) ) && 
					(parseInt(day, 10) == d.getDate()) && 
					(parseInt(year, 10) == d.getFullYear() );		
	};
	
	/**
	 * 日期文本与目标文本框中日期的比较.
	 * 
	 * <p>
	 * @param v
	 *            为日期文本，如'2011-02-03'.
	 * @param options
	 * 			  options.p : 为日期要验证的格式。格式中须是年(yyyy)月(MM)日(dd)三者的组合，(yyyy[-/]MM[-/]dd)目前仅有此功能。
	 * 			  options.src : 为被比较的日期文本,如'2011-02-05'。
	 * 			  options.type : case >> {'lt' <;'gt' >;'leq' <=;geq >=;eq ==} 
	 */
	sino.tool.Val.isDateGThan = function(v,options) {
		var MONTH = "MM";
	   	var DAY = "dd";
	   	var YEAR = "yyyy";
		var dateFormat = options.p ? options.p : 'yyyy-MM-dd';
		var regex = '^'+dateFormat.replace(YEAR,'\\d{4}').replace(MONTH,'\\d{2}').replace(DAY,'\\d{2}')+'$';
		var re = new RegExp(regex);
		if(!re.test(v)) return false;
		if(!options.src || !re.test(options.src)) return false;
		switch (options.type){
			case 'lt' : return v < options.src;
			case 'leq' : return v <= options.src;
			case 'gt' : return v > options.src;
			case 'geq' : return v >= options.src;
			default : return v == options.src;
		}
		return false;
	};
	
	/**
	 * 验证是否是小数，小数点个数是否小于等于指定值。
	 * 如果给定值不存在或者给定值不是number类型的，只验证v是否是小数。
	 * <p>
	 * @param v
	 * 		要验证的值。
	 * @param o
	 * 		{num:5} o.num：小数点个数必须小于等于o.num。
	 */
	sino.tool.Val.isDecimal = function (v,o){
		if(!/^(([1-9]\d*)|(\d))([\.]\d+)?$/.test(v))return false;
		if(o && o.num && typeof o.num=='number'){
			v = v + '';
			if(v.indexOf('.')==-1)return true;
			var s = v.substring(v.indexOf('.')+1);
			return s.length > 0 && s.length <= o.num;
		}
		return true;
	};
	/**
	 * 验证是否是6位数的邮编号码
	 */
	sino.tool.Val.isPostcode = function(v){
		return (!isNaN(v) && !/^\s+$/.test(v)) && (v.length==6);
	};
})(sino);